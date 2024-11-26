
import android.os.Bundle;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;

package com.example.smartmart;// MainActivity.java
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartmart.Adapter.ProductAdapter;
import com.example.smartmart.DAO.SanPhamDAO;
import com.example.smartmart.DAO.UserDAO;
import com.example.smartmart.models.SanPham;
import com.example.smartmart.models.User;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private SanPhamDAO sanphamDAO;
    private ProductAdapter adapter;
    private List<SanPham> productList = new ArrayList<>();
    DrawerLayout drawerLayout;
    private UserDAO userDAO;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawer_layout);
        ImageView menuButton = findViewById(R.id.menu_button);
        menuButton.setOnClickListener(v -> drawerLayout.openDrawer(GravityCompat.START));
        sanphamDAO = new SanPhamDAO(this);
        userDAO = new UserDAO(this);
        NavigationView navigationView;
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2)); // 2 columns
        User user = (User) getIntent().getSerializableExtra("user");
        productList = sanphamDAO.getAllProducts();
        if (productList != null) {
            adapter = new ProductAdapter(productList, this,user);
            recyclerView.setAdapter(adapter);

        }
        Toolbar toolbar = findViewById(R.id.toolbar);
        EditText searchInput = findViewById(R.id.search_bar);
        navigationView = findViewById(R.id.navigationView);
        View headerLayout = navigationView.getHeaderView(0);


        String email = user.getEmail();
        if (email != null) {
            User userName = userDAO.getUserByEmail(email);
            if (userName != null) {
                // Display user information in the header
                TextView txtTen = headerLayout.findViewById(R.id.txtTen);
                txtTen.setText("Xin Chào " + userName.getNickName());
            }
        }
        navigationView.setNavigationItemSelectedListener(item -> {
            Fragment fragment = null;
            int id = item.getItemId();
            if(id == R.id.mDangxuat) {
                Intent intent = new Intent(MainActivity.this, MHdangnhap.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                return true;
            }else if (id == R.id.taiKhoan) {
                Intent intent = new Intent(MainActivity.this, TaiKhoan.class);
                intent.putExtra("user", user);
                startActivity(intent);
                return true;
            drawerLayout.closeDrawer(GravityCompat.START);
            return true;}else if (id == R.id.nav_product) {
            Intent intent = new Intent(this, ProductManagementActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_category) {
            loadFragment(new CategoryManagementFragment(), "Quản lý danh mục sản phẩm");
        } else if (id == R.id.nav_order) {
            Intent intent = new Intent(this, OrderManagementActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_customer) {
            loadFragment(new CustomerManagementFragment(), "Quản lý khách hàng");
        } else if (id == R.id.nav_support) {
            loadFragment(new SupportFragment(), "Hỗ trợ chăm sóc khách hàng");
        } else if (id == R.id.nav_product_stats) {
            Intent intent = new Intent(this, ProductStatsActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_revenue) {
            Intent intent = new Intent(this, RevenueActivity.class);
            startActivity(intent);
        }
        });

        searchInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                searchProducts(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        });
        ImageView cartButton = findViewById(R.id.cart_button);
        cartButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, GioHang.class);
            intent.putExtra("user", user);
            startActivity(intent);
        });
    }

    private void searchProducts(String keyword) {
        productList.clear();
        productList.addAll(sanphamDAO.searchProducts(keyword));
        adapter.notifyDataSetChanged();
    }

    }



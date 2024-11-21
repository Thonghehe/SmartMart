package com.example.smartmart;// MainActivity.java
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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
import com.example.smartmart.models.SanPham;
import com.example.smartmart.models.User;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private SanPhamDAO sanphamDAO;
    private ProductAdapter adapter;
    private List<SanPham> productList;
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawerLayout = findViewById(R.id.drawer_layout);
        ImageView menuButton = findViewById(R.id.menu_button);
        menuButton.setOnClickListener(v -> drawerLayout.openDrawer(GravityCompat.START));
        sanphamDAO = new SanPhamDAO(this);
        NavigationView navigationView;


        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2)); // 2 columns

        productList = sanphamDAO.getAllProducts();
        adapter = new ProductAdapter(productList,this);
        recyclerView.setAdapter(adapter);
        Toolbar toolbar = findViewById(R.id.toolbar);
        EditText searchInput = findViewById(R.id.search_bar);
        navigationView = findViewById(R.id.navigationView);
        View headerLayout = navigationView.getHeaderView(0);

        User user = (User) getIntent().getSerializableExtra("user");
        if (user != null) {
            TextView txtTen = headerLayout.findViewById(R.id.txtTen);
            txtTen.setText("Xin Chào "+user.getNickName());
        }
        navigationView.setNavigationItemSelectedListener(item -> {
            Fragment fragment = null;
//            if (item.getItemId() == R.id.mQLPhieuMuon) {
//                fragment = new QLPhieuMuonFragment();
//            } else if (item.getItemId() == R.id.mQLLoaiSach) {
//                fragment = new QLLoaiSachFragment();
//            } else if(item.getItemId() == R.id.mDangxuat) {
//                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
//                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                startActivity(intent);
//                return true;
//            }else if(item.getItemId() == R.id.mDoiMK){
//                showDialogDoiMK();
//            }else if(item.getItemId() == R.id.mTop10){
//                fragment = new ThongKeFragment();
//            } else if (item.getItemId() == R.id.mDoanhThu) {
//                fragment = new ThongKeDoanhThuFragment();
//            }else if (item.getItemId() == R.id.mQLThanhVien) {
//                fragment = new QLThanhVienFragment();
//            }else if (item.getItemId() == R.id.mQLSach) {
//                fragment = new QLSachFragment();
//            }

            if (fragment != null) {
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.recyclerView, fragment)
                        .commit();
                toolbar.setTitle(item.getTitle());
            }

            drawerLayout.closeDrawer(GravityCompat.START);

            return true;
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
    }

    private void searchProducts(String keyword) {
        productList.clear();
        productList.addAll(sanphamDAO.searchProducts(keyword));
        adapter.notifyDataSetChanged();
    }
    }


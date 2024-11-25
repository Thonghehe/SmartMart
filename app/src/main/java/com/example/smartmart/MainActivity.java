package com.example.smartmart;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import com.google.android.material.navigation.NavigationView;
import com.example.smartmart.fragments.*;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null) {
            // Khởi chạy ProductManagementActivity khi ứng dụng được mở
            Intent intent = new Intent(this, ProductManagementActivity.class);
            startActivity(intent);
        }
    }



    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_product) {
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
        } else if (id == R.id.nav_profile) {
            loadFragment(new ProfileFragment(), "Thông tin cá nhân");
        } else if (id == R.id.nav_change_password) {
            loadFragment(new ChangePasswordFragment(), "Đổi mật khẩu");
        } else if (id == R.id.nav_logout) {
            // Xử lý đăng xuất ở đây
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private void loadFragment(Fragment fragment, String title) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.content_frame, fragment);
        ft.commit();

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
        }
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}


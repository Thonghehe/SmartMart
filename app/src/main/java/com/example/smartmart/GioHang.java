// GioHang.java
package com.example.smartmart;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartmart.Adapter.ChiTietDonHangAdapter;
import com.example.smartmart.DAO.ChiTietDonHangDAO;
import com.example.smartmart.models.ChiTietDonHang;
import com.example.smartmart.models.User;

import java.util.List;

public class GioHang extends AppCompatActivity {
    private ChiTietDonHangDAO chiTietDonHangDAO;
    private List<ChiTietDonHang> chiTietDonHangList;
    private ChiTietDonHangAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gio_hang);

        chiTietDonHangDAO = new ChiTietDonHangDAO(this);

        // Retrieve the user from the intent
        User user = (User) getIntent().getSerializableExtra("user");

        RecyclerView recyclerView = findViewById(R.id.cartRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Filter ChiTietDonHang by maUser
        chiTietDonHangList = chiTietDonHangDAO.getChiTietDonHangByUserId(user.getMaUser());
        adapter = new ChiTietDonHangAdapter(chiTietDonHangList, this);
        recyclerView.setAdapter(adapter);

        findViewById(R.id.backmain).setOnClickListener((v) -> {
            Intent intent = new Intent();
            intent.putExtra("user", user);
            intent.setClass(GioHang.this, MainActivity.class);
            startActivity(intent);
        });
    }
}
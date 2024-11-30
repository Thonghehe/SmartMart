package com.example.smartmart;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.smartmart.DAO.DonHangDAO;
import com.example.smartmart.adapter.OrderAdapter;
import com.example.smartmart.models.DonHang;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.List;

public class OrderManagementActivity extends AppCompatActivity {
    private DonHangDAO donHangDAO;
    private RecyclerView recyclerView;
    private OrderAdapter orderAdapter;
    private List<DonHang> orderList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_management);

        donHangDAO = new DonHangDAO(this);
        recyclerView = findViewById(R.id.recyclerViewOrders);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FloatingActionButton fabAddOrder = findViewById(R.id.fabAddOrder);
        fabAddOrder.setOnClickListener(v -> {
            Intent intent = new Intent(OrderManagementActivity.this, AddOrderActivity.class);
            startActivity(intent);
        });

        loadOrders();
    }

    private void loadOrders() {
        orderList = donHangDAO.getAllDonHang();
        orderAdapter = new OrderAdapter(orderList, this);
        recyclerView.setAdapter(orderAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadOrders();
    }
}


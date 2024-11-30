package com.example.smartmart;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.smartmart.DAO.DonHangDAO;
import com.example.smartmart.models.DonHang;

public class AddOrderActivity extends AppCompatActivity {
    private EditText etOrderDate, etOrderStatus, etTotalPrice, etPaymentMethod;
    private Button btnSaveOrder;
    private ImageButton btnBack;
    private DonHangDAO donHangDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_order);

        etOrderDate = findViewById(R.id.etOrderDate);
        etOrderStatus = findViewById(R.id.etOrderStatus);
        etTotalPrice = findViewById(R.id.etTotalPrice);
        etPaymentMethod = findViewById(R.id.etPaymentMethod);
        btnSaveOrder = findViewById(R.id.btnSaveOrder);
        btnBack = findViewById(R.id.btnBack);
        donHangDAO = new DonHangDAO(this);

        btnSaveOrder.setOnClickListener(v -> saveOrder());
        btnBack.setOnClickListener(v -> finish());
    }

    private void saveOrder() {
        String orderDate = etOrderDate.getText().toString();
        String status = etOrderStatus.getText().toString();
        double totalPrice = Double.parseDouble(etTotalPrice.getText().toString());
        String paymentMethod = etPaymentMethod.getText().toString();

        DonHang newOrder = new DonHang();
        newOrder.setNgayDatHang(orderDate);
        newOrder.setTrangThai(status);
        newOrder.setTongGia(totalPrice);
        newOrder.setPhuongThucThanhToan(paymentMethod);

        long result = donHangDAO.insertDonHang(newOrder);
        if (result != -1) {
            Toast.makeText(this, "Đã thêm đơn hàng thành công", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this, "Thêm đơn hàng thất bại", Toast.LENGTH_SHORT).show();
        }
    }
}


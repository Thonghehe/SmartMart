package com.example.smartmart;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartmart.Adapter.ProductPayAdapter;
import com.example.smartmart.models.ChiTietDonHang;
import com.example.smartmart.models.User;

import java.text.DecimalFormat;
import java.util.List;

public class ThanhToan extends AppCompatActivity {
    private TextView tvRecipientInfo;

    private TextView tvPaymentDetails;
    private TextView tvTotalAmount;
    private RecyclerView recyclerViewProducts;
    private ProductPayAdapter productPayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thanh_toan);

        tvRecipientInfo = findViewById(R.id.tv_recipient_info);

        tvPaymentDetails = findViewById(R.id.tv_payment_details);
        tvTotalAmount = findViewById(R.id.tv_total_amount);
        recyclerViewProducts = findViewById(R.id.recycler_view_products);

        // Retrieve user and checked products from the intent
        User user = (User) getIntent().getSerializableExtra("user");
        List<ChiTietDonHang> checkedProducts = getIntent().getParcelableArrayListExtra("checkedProducts");

        // Display user information
        if (user != null) {
            String userInfo =
                    "Address: " + user.getDiaChi();
            tvRecipientInfo.setText(userInfo);
        }

        // Display payment details
        if (checkedProducts != null && !checkedProducts.isEmpty()) {
            productPayAdapter = new ProductPayAdapter(checkedProducts);
            recyclerViewProducts.setLayoutManager(new LinearLayoutManager(this));
            recyclerViewProducts.setAdapter(productPayAdapter);

            double totalAmount = 0.0;
            for (ChiTietDonHang product : checkedProducts) {
                totalAmount += product.getGia() * product.getSoLuong();
            }
            tvPaymentDetails.setText("Name: " + user.getNickName() + "\n" +
                    "Email: " + user.getEmail() + "\n" +
                    "Phone: " + user.getSoDienThoai() + "\n");
            DecimalFormat decimalFormat = new DecimalFormat("#,##0,000");
            tvTotalAmount.setText(
                    "Tổng đơn hàng: " + decimalFormat.format(totalAmount) + " đ");
        }

        // Set shipping method and fee (example values)
        Spinner spinnerPaymentMethod = findViewById(R.id.spinner_payment_method);
        ArrayAdapter<CharSequence> paymentMethodAdapter = ArrayAdapter.createFromResource(this,
                R.array.payment_methods, android.R.layout.simple_spinner_item);
        paymentMethodAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPaymentMethod.setAdapter(paymentMethodAdapter);
        findViewById(R.id.backmain).setOnClickListener((v) -> {
            Intent intent = new Intent();
            intent.putExtra("user", user);
            intent.setClass(ThanhToan.this, GioHang.class);
            startActivity(intent);
        });
    }

}
package com.example.smartmart;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.smartmart.models.ChiTietDonHang;
import com.example.smartmart.models.User;

import java.util.List;

public class ThanhToan extends AppCompatActivity {
    private TextView tvRecipientInfo;
    private TextView tvProductName;
    private TextView tvProductPrice;
    private TextView tvProductDesc;
    private TextView tvShippingMethod;
    private TextView tvShippingFee;
    private TextView tvPaymentDetails;
    private TextView tvTotalAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thanh_toan);

        tvRecipientInfo = findViewById(R.id.tv_recipient_info);
        tvProductName = findViewById(R.id.tv_product_name);
        tvProductPrice = findViewById(R.id.tv_product_price);
        tvProductDesc = findViewById(R.id.tv_product_desc);
        tvShippingMethod = findViewById(R.id.tv_shipping_method);
        tvShippingFee = findViewById(R.id.tv_shipping_fee);
        tvPaymentDetails = findViewById(R.id.tv_payment_details);
        tvTotalAmount = findViewById(R.id.tv_total_amount);

        // Retrieve user and checked products from the intent
        User user = (User) getIntent().getSerializableExtra("user");
        List<ChiTietDonHang> checkedProducts = getIntent().getParcelableArrayListExtra("checkedProducts");

        // Display user information
        if (user != null) {
            String userInfo = "Name: " + user.getNickName() + "\n" +
                    "Email: " + user.getEmail() + "\n" +
                    "Phone: " + user.getSoDienThoai() + "\n" +
                    "Address: " + user.getDiaChi();
            tvRecipientInfo.setText(userInfo);
        }

        // Display payment details
        if (checkedProducts != null && !checkedProducts.isEmpty()) {
            ChiTietDonHang product = checkedProducts.get(0);
            tvProductName.setText(product.getTenSanPham());
            tvProductPrice.setText(String.valueOf(product.getGia()));
            tvProductDesc.setText("Quantity: " + product.getSoLuong());

            StringBuilder paymentDetails = new StringBuilder();
            double totalAmount = 0.0;
            for (ChiTietDonHang p : checkedProducts) {
                paymentDetails.append("Product: ").append(p.getTenSanPham())
                        .append("\nPrice: ").append(p.getGia())
                        .append("\nQuantity: ").append(p.getSoLuong())
                        .append("\n\n");
                totalAmount += p.getGia() * p.getSoLuong();
            }
            tvPaymentDetails.setText(paymentDetails.toString());
            tvTotalAmount.setText("Total: " + totalAmount + " đ");
        }

        // Set shipping method and fee (example values)
        tvShippingMethod.setText("Standard Shipping");
        tvShippingFee.setText("Free");
    }
}
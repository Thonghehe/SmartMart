package com.example.smartmart;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.smartmart.DAO.SanPhamDAO;
import com.example.smartmart.models.SanPham;

public class ChiTietSanPham extends AppCompatActivity {
    private SanPhamDAO sanPhamDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_san_pham);

        sanPhamDAO = new SanPhamDAO(this);
        findViewById(R.id.backmain).setOnClickListener((v)->{
            Intent intent = new Intent();
            intent.setClass(ChiTietSanPham.this, MainActivity.class);
            startActivity(intent);
        });
        int productId = getIntent().getIntExtra("product_id", -1);
        if (productId != -1) {
            SanPham product = sanPhamDAO.getProductById(productId);
            if (product != null) {
                TextView productName = findViewById(R.id.product_name);
                TextView productPrice = findViewById(R.id.product_price);
                TextView productDescription = findViewById(R.id.product_description);
                ImageView productImage = findViewById(R.id.product_image);

                productName.setText(product.getTenSanPham());
                productPrice.setText(String.valueOf(product.getGia()));
                productDescription.setText(product.getMoTa());
                Glide.with(this).load(product.getImage_url()).into(productImage);
            }
        }
    }
}
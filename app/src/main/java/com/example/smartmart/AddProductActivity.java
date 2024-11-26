package com.example.smartmart;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.smartmart.DBHelper.DatabaseHelper;

public class AddProductActivity extends AppCompatActivity {
    private EditText etProductName, etProductPrice, etProductDescription;
    private TextView tvQuantity;
    private Button btnAddProduct, btnIncreaseQuantity, btnDecreaseQuantity;
    private ImageButton btnBack;
    private DatabaseHelper dbHelper;
    private int quantity = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        etProductName = findViewById(R.id.etProductName);
        etProductPrice = findViewById(R.id.etProductPrice);
        etProductDescription = findViewById(R.id.etProductDescription);
        tvQuantity = findViewById(R.id.tvQuantity);
        btnAddProduct = findViewById(R.id.btnAddProduct);
        btnIncreaseQuantity = findViewById(R.id.btnIncreaseQuantity);
        btnDecreaseQuantity = findViewById(R.id.btnDecreaseQuantity);
        btnBack = findViewById(R.id.btnBack);
        dbHelper = new DatabaseHelper(this);

        btnIncreaseQuantity.setOnClickListener(v -> {
            quantity++;
            tvQuantity.setText(String.valueOf(quantity));
        });

        btnDecreaseQuantity.setOnClickListener(v -> {
            if (quantity > 1) {
                quantity--;
                tvQuantity.setText(String.valueOf(quantity));
            }
        });

        btnAddProduct.setOnClickListener(v -> addProduct());

        btnBack.setOnClickListener(v -> finish());
    }

    private void addProduct() {
        String name = etProductName.getText().toString();
        double price = Double.parseDouble(etProductPrice.getText().toString());
        String description = etProductDescription.getText().toString();

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_NAME, name);
        values.put(DatabaseHelper.COLUMN_PRICE, price);
        values.put(DatabaseHelper.COLUMN_QUANTITY, quantity);
        values.put(DatabaseHelper.COLUMN_DESCRIPTION, description);

        long newRowId = db.insert(DatabaseHelper.TABLE_PRODUCTS, null, values);
        if (newRowId != -1) {
            Toast.makeText(this, "Sản phẩm đã được thêm thành công", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this, "Không thêm được sản phẩm", Toast.LENGTH_SHORT).show();
        }
    }
}
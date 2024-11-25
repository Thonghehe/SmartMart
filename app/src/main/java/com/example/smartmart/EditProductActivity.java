package com.example.smartmart;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.smartmart.DBHelper.DatabaseHelper;

public class EditProductActivity extends AppCompatActivity {
    private EditText etProductName, etProductPrice, etProductDescription;
    private TextView tvQuantity;
    private Button btnSaveProduct, btnIncreaseQuantity, btnDecreaseQuantity;
    private ImageButton btnBack;
    private DatabaseHelper dbHelper;
    private int productId;
    private int quantity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_product);

        etProductName = findViewById(R.id.etProductName);
        etProductPrice = findViewById(R.id.etProductPrice);
        etProductDescription = findViewById(R.id.etProductDescription);
        tvQuantity = findViewById(R.id.tvQuantity);
        btnSaveProduct = findViewById(R.id.btnSaveProduct);
        btnIncreaseQuantity = findViewById(R.id.btnIncreaseQuantity);
        btnDecreaseQuantity = findViewById(R.id.btnDecreaseQuantity);
        btnBack = findViewById(R.id.btnBack);
        dbHelper = new DatabaseHelper(this);

        productId = getIntent().getIntExtra("PRODUCT_ID", -1);
        loadProductDetails();

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

        btnSaveProduct.setOnClickListener(v -> saveProduct());

        btnBack.setOnClickListener(v -> finish());
    }

    private void loadProductDetails() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(DatabaseHelper.TABLE_PRODUCTS, null, DatabaseHelper.COLUMN_ID + "=?",
                new String[]{String.valueOf(productId)}, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            etProductName.setText(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_NAME)));
            etProductPrice.setText(String.valueOf(cursor.getDouble(cursor.getColumnIndex(DatabaseHelper.COLUMN_PRICE))));
            etProductDescription.setText(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_DESCRIPTION)));
            quantity = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COLUMN_QUANTITY));
            tvQuantity.setText(String.valueOf(quantity));
            cursor.close();
        }
    }

    private void saveProduct() {
        String name = etProductName.getText().toString();
        double price = Double.parseDouble(etProductPrice.getText().toString());
        String description = etProductDescription.getText().toString();

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_NAME, name);
        values.put(DatabaseHelper.COLUMN_PRICE, price);
        values.put(DatabaseHelper.COLUMN_QUANTITY, quantity);
        values.put(DatabaseHelper.COLUMN_DESCRIPTION, description);

        int rowsAffected = db.update(DatabaseHelper.TABLE_PRODUCTS, values, DatabaseHelper.COLUMN_ID + "=?",
                new String[]{String.valueOf(productId)});
        if (rowsAffected > 0) {
            Toast.makeText(this, "Sản phẩm đã được cập nhật", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this, "Không thể cập nhật sản phẩm", Toast.LENGTH_SHORT).show();
        }
    }
}
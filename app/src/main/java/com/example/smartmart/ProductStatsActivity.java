package com.example.smartmart;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.smartmart.models.ProductStat;
import com.example.smartmart.DBHelper.DatabaseHelper;
import java.util.ArrayList;
import java.util.List;
import com.example.smartmart.Adapter.ProductStatsAdapter;

public class ProductStatsActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ProductStatsAdapter adapter;
    private List<ProductStat> productStats;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_stats);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Thống kê sản phẩm");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = findViewById(R.id.recyclerViewStats);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        productStats = new ArrayList<>();
        dbHelper = new DatabaseHelper(this);

        loadTopProducts();

        adapter = new ProductStatsAdapter(productStats);
        recyclerView.setAdapter(adapter);
    }

    private void loadTopProducts() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        // Truy vấn lấy sản phẩm bán chạy nhất
        String query = "SELECT id, name, price, sold " +
                "FROM SanPham " +
                "ORDER BY sold DESC " +
                "LIMIT 5";

        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            int rank = 1;
            do {
                ProductStat stat = new ProductStat();
                stat.setId(cursor.getInt(cursor.getColumnIndexOrThrow("id")));
                stat.setName(cursor.getString(cursor.getColumnIndexOrThrow("name")));
                stat.setPrice(cursor.getDouble(cursor.getColumnIndexOrThrow("price")));
                stat.setSalesCount(cursor.getInt(cursor.getColumnIndexOrThrow("sold")));
                stat.setRank(rank++);
                productStats.add(stat);
            } while (cursor.moveToNext());
        }

        // Gỡ lỗi: Kiểm tra danh sách sản phẩm
        for (ProductStat stat : productStats) {
            System.out.println("Sản phẩm: " + stat.getName() + ", Lượt bán: " + stat.getSalesCount());
        }

        cursor.close();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}

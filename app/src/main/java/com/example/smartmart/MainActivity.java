package com.example.smartmart;// MainActivity.java
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartmart.Adapter.ProductAdapter;
import com.example.smartmart.DAO.SanPhamDAO;
import com.example.smartmart.models.SanPham;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private SanPhamDAO sanphamDAO;
    private ProductAdapter adapter;
    private List<SanPham> productList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sanphamDAO = new SanPhamDAO(this);


        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2)); // 2 columns

        productList = sanphamDAO.getAllProducts();
        adapter = new ProductAdapter(productList,this);
        recyclerView.setAdapter(adapter);
        EditText searchInput = findViewById(R.id.search_bar);
        searchInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                searchProducts(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        });
    }

    private void searchProducts(String keyword) {
        productList.clear();
        productList.addAll(sanphamDAO.searchProducts(keyword));
        adapter.notifyDataSetChanged();
    }
    }


package com.example.smartmart.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.smartmart.EditProductActivity;
import com.example.smartmart.R;
import com.example.smartmart.models.Product;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
    private List<Product> products;
    private Context context;
    private NumberFormat currencyFormat;
    private OnProductDeleteListener deleteListener;

    public interface OnProductDeleteListener {
        void onDeleteProduct(Product product, int position);
    }

    public ProductAdapter(List<Product> products, Context context) {
        this.products = products;
        this.context = context;
        this.currencyFormat = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
    }

    public void setOnProductDeleteListener(OnProductDeleteListener listener) {
        this.deleteListener = listener;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product = products.get(position);
        holder.tvName.setText(product.getName());
        holder.tvPrice.setText(currencyFormat.format(product.getPrice()));
        holder.tvQuantity.setText("Số lượng: " + product.getQuantity());

        Glide.with(context)
                .load(product.getImageUrl())
                .placeholder(R.drawable.placeholder_image)
                .into(holder.ivProduct);

        // Xử lý sự kiện xóa sản phẩm
        holder.btnDelete.setOnClickListener(v -> {
            if (deleteListener != null) {
                showDeleteConfirmationDialog(context, product, position);
            }
        });

        // Xử lý sự kiện chỉnh sửa sản phẩm
        holder.itemView.setOnLongClickListener(v -> {
            Intent intent = new Intent(context, EditProductActivity.class);
            intent.putExtra("PRODUCT_ID", product.getId());
            context.startActivity(intent);
            return true;
        });
    }

    private void showDeleteConfirmationDialog(Context context, Product product, int position) {
        new AlertDialog.Builder(context)
                .setTitle("Xác nhận xóa")
                .setMessage("Bạn có chắc chắn muốn xóa sản phẩm này?")
                .setPositiveButton("Xóa", (dialog, which) -> {
                    if (deleteListener != null) {
                        deleteListener.onDeleteProduct(product, position);
                    }
                })
                .setNegativeButton("Hủy", null)
                .show();
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public void updateList(List<Product> newList) {
        products = newList;
        notifyDataSetChanged();
    }

    static class ProductViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvPrice, tvQuantity;
        ImageButton btnDelete;
        ImageView ivProduct;

        ProductViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            tvQuantity = itemView.findViewById(R.id.tvQuantity);
            btnDelete = itemView.findViewById(R.id.btnDelete);
            ivProduct = itemView.findViewById(R.id.ivProduct);
        }
    }
}

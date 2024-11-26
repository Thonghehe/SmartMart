package com.example.smartmart.Adapter;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.smartmart.DAO.ChiTietDonHangDAO;
import com.example.smartmart.R;
import com.example.smartmart.models.ChiTietDonHang;

import java.text.DecimalFormat;
import java.util.List;

public class ChiTietDonHangAdapter extends RecyclerView.Adapter<ChiTietDonHangAdapter.ViewHolder> {
    private List<ChiTietDonHang> chiTietDonHangList;
    private Context context;

    public ChiTietDonHangAdapter(List<ChiTietDonHang> chiTietDonHangList, Context context) {
        this.chiTietDonHangList = chiTietDonHangList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chi_tiet_don_hang, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ChiTietDonHang chiTietDonHang = chiTietDonHangList.get(position);
        holder.productName.setText(chiTietDonHang.getTenSanPham());
        DecimalFormat decimalFormat = new DecimalFormat("#,##0,000");
        holder.productPrice.setText(decimalFormat.format(chiTietDonHang.getGia()) + " đ");
        holder.quantityEditText.setText(String.valueOf(chiTietDonHang.getSoLuong()));
        holder.checkBox.setChecked(chiTietDonHang.isChecked());
        Glide.with(context).load(chiTietDonHang.getImageUrl()).into(holder.productImage);

        holder.minusButton.setOnClickListener(v -> {
            int quantity = Integer.parseInt(holder.quantityEditText.getText().toString());
            if (quantity > 1) {
                quantity--;
                holder.quantityEditText.setText(String.valueOf(quantity));
                chiTietDonHang.setSoLuong(quantity);
            }
        });

        holder.plusButton.setOnClickListener(v -> {
            int quantity = Integer.parseInt(holder.quantityEditText.getText().toString());
            quantity++;
            holder.quantityEditText.setText(String.valueOf(quantity));
            chiTietDonHang.setSoLuong(quantity);
        });
        holder.quantityEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // No action needed
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // No action needed
            }

            @Override
            public void afterTextChanged(Editable s) {
                try {
                    int quantity = Integer.parseInt(s.toString());
                    chiTietDonHang.setSoLuong(quantity);
                    holder.productPrice.setText(decimalFormat.format(chiTietDonHang.getGia() * quantity) + " đ");
                } catch (NumberFormatException e) {
                    // Handle the exception if the input is not a valid number
                }
            }
        });

        holder.deleteButton.setOnClickListener(v -> {
            ChiTietDonHangDAO chiTietDonHangDAO = new ChiTietDonHangDAO(context);
            chiTietDonHangDAO.deleteChiTietDonHang(chiTietDonHang.getMaChiTietDonHang());
            chiTietDonHangList.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, chiTietDonHangList.size());
        });
    }

    @Override
    public int getItemCount() {
        return chiTietDonHangList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public CheckBox checkBox;
        public ImageView productImage;
        public TextView productName;
        public TextView productPrice;
        public EditText quantityEditText;
        public Button minusButton;
        public Button plusButton;
        public ImageButton deleteButton;

        public ViewHolder(View itemView) {
            super(itemView);
            checkBox = itemView.findViewById(R.id.checkBox);
            productImage = itemView.findViewById(R.id.productImage);
            productName = itemView.findViewById(R.id.productName);
            productPrice = itemView.findViewById(R.id.productPrice);
            quantityEditText = itemView.findViewById(R.id.quantityEditText);
            minusButton = itemView.findViewById(R.id.minusButton);
            plusButton = itemView.findViewById(R.id.plusButton);
            deleteButton = itemView.findViewById(R.id.deleteButton);
        }
    }
}
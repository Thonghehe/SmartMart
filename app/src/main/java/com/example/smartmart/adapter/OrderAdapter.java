package com.example.smartmart.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartmart.EditOrderActivity;
import com.example.smartmart.R;
import com.example.smartmart.models.DonHang;

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {
    private List<DonHang> orderList;
    private Context context;

    public OrderAdapter(List<DonHang> orderList, Context context) {
        this.orderList = orderList;
        this.context = context;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order, parent, false);
        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        DonHang order = orderList.get(position);
        holder.tvOrderId.setText(String.valueOf(order.getMaDonHang()));
        holder.tvOrderDate.setText(order.getNgayDatHang());
        holder.tvOrderStatus.setText(order.getTrangThai());
        holder.tvTotalPrice.setText(String.format("Total: %.2f", order.getTongGia()));

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, EditOrderActivity.class);
            intent.putExtra("ORDER_ID", order.getMaDonHang());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    public void updateOrderList(List<DonHang> newOrderList) {
        this.orderList = newOrderList;
        notifyDataSetChanged();
    }

    public static class OrderViewHolder extends RecyclerView.ViewHolder {
        TextView tvOrderId, tvOrderDate, tvOrderStatus, tvTotalPrice;

        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);
            tvOrderId = itemView.findViewById(R.id.tvOrderId);
            tvOrderDate = itemView.findViewById(R.id.tvOrderDate);
            tvOrderStatus = itemView.findViewById(R.id.tvOrderStatus);
            tvTotalPrice = itemView.findViewById(R.id.tvTotalPrice);
        }
    }
}
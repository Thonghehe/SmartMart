package com.example.smartmart.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartmart.R;
import com.example.smartmart.models.Order;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {
    private List<Order> orders;
    private Context context;
    private NumberFormat currencyFormat;

    public OrderAdapter(Context context, List<Order> orders) {
        this.context = context;
        this.orders = orders;
        this.currencyFormat = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_order, parent, false);
        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        Order order = orders.get(position);

        // Set thông tin đơn hàng
        holder.tvOrderNumber.setText("Đơn hàng #" + order.getId());
        holder.tvOrderDate.setText("Ngày đặt: " + order.getOrderDate());
        holder.tvOrderAmount.setText(currencyFormat.format(order.getTotalAmount()));

        // Hiển thị trạng thái đơn hàng
        holder.tvOrderStatus.setText(order.getStatus());
        holder.tvOrderStatus.setTextColor(getStatusColor(order.getStatus()));
    }


    private int getStatusColor(String status) {
        if ("Đang giao".equals(status)) {
            return ContextCompat.getColor(context, R.color.blue); // Màu xanh cho trạng thái "Đang giao"
        } else if ("Đã giao".equals(status)) {
            return ContextCompat.getColor(context, R.color.red); // Màu đỏ cho trạng thái "Đã giao"
        }
        return ContextCompat.getColor(context, R.color.gray); // Mặc định là màu xám
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    static class OrderViewHolder extends RecyclerView.ViewHolder {
        TextView tvOrderNumber, tvOrderDate, tvOrderAmount, tvOrderStatus;

        OrderViewHolder(View itemView) {
            super(itemView);

            // Ánh xạ các thành phần giao diện
            tvOrderNumber = itemView.findViewById(R.id.tvOrderNumber);
            tvOrderDate = itemView.findViewById(R.id.tvOrderDate);
            tvOrderAmount = itemView.findViewById(R.id.tvOrderAmount);
            tvOrderStatus = itemView.findViewById(R.id.tvOrderStatus);
        }
    }
}
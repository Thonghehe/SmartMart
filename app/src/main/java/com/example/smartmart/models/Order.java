package com.example.smartmart.models;

public class Order {
    private int id;
    private String orderDate;
    private double totalAmount;
    private String status;

    public Order(int id, String orderDate, double totalAmount, String status) {
        this.id = id;
        this.orderDate = orderDate;
        this.totalAmount = totalAmount;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public String getStatus() {
        return status;
    }
}


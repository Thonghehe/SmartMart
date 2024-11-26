// ChiTietDonHang.java
package com.example.smartmart.models;

public class ChiTietDonHang {
    private int maChiTietDonHang;
    private String tenSanPham;
    private String imageUrl;
    private int maSanPham;
    private int maUser;
    private int soLuong;
    private double gia;
    private boolean isChecked;

    public ChiTietDonHang() {
    }

    public ChiTietDonHang(int maChiTietDonHang, String tenSanPham, String imageUrl, int maSanPham, int maUser, int soLuong, double gia, boolean isChecked) {
        this.maChiTietDonHang = maChiTietDonHang;
        this.tenSanPham = tenSanPham;
        this.imageUrl = imageUrl;
        this.maSanPham = maSanPham;
        this.maUser = maUser;
        this.soLuong = soLuong;
        this.gia = gia;
        this.isChecked = isChecked;
    }

    public int getMaChiTietDonHang() {
        return maChiTietDonHang;
    }

    public void setMaChiTietDonHang(int maChiTietDonHang) {
        this.maChiTietDonHang = maChiTietDonHang;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(int maSanPham) {
        this.maSanPham = maSanPham;
    }

    public int getMaUser() {
        return maUser;
    }

    public void setMaUser(int maUser) {
        this.maUser = maUser;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public double getGia() {
        return gia;
    }

    public void setGia(double gia) {
        this.gia = gia;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }
}
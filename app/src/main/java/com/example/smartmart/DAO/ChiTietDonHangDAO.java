// ChiTietDonHangDAO.java
package com.example.smartmart.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.smartmart.DBHelper.DatabaseHelper;
import com.example.smartmart.models.ChiTietDonHang;

import java.util.ArrayList;
import java.util.List;

public class ChiTietDonHangDAO {
    private SQLiteDatabase db;
    private DatabaseHelper dbHelper;

    public ChiTietDonHangDAO(Context context) {
        dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long insertChiTietDonHang(ChiTietDonHang chiTietDonHang) {
        ContentValues values = new ContentValues();
        values.put("maSanPham", chiTietDonHang.getMaSanPham());
        values.put("maUser", chiTietDonHang.getMaUser());
        values.put("soLuong", chiTietDonHang.getSoLuong());
        values.put("gia", chiTietDonHang.getGia());
        values.put("image_url", chiTietDonHang.getImageUrl());
        values.put("tenSanPham", chiTietDonHang.getTenSanPham());
        values.put("isChecked", chiTietDonHang.isChecked() ? 1 : 0);
        return db.insert("ChiTietDonHang", null, values);
    }

    public List<ChiTietDonHang> getAllChiTietDonHang() {
        List<ChiTietDonHang> chiTietDonHangList = new ArrayList<>();
        Cursor cursor = db.query("ChiTietDonHang", null, null, null, null, null, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                ChiTietDonHang chiTietDonHang = new ChiTietDonHang();
                chiTietDonHang.setMaChiTietDonHang(cursor.getInt(cursor.getColumnIndexOrThrow("maChiTietDonHang")));
                chiTietDonHang.setMaSanPham(cursor.getInt(cursor.getColumnIndexOrThrow("maSanPham")));
                chiTietDonHang.setMaUser(cursor.getInt(cursor.getColumnIndexOrThrow("maUser")));
                chiTietDonHang.setSoLuong(cursor.getInt(cursor.getColumnIndexOrThrow("soLuong")));
                chiTietDonHang.setGia(cursor.getDouble(cursor.getColumnIndexOrThrow("gia")));
                chiTietDonHang.setImageUrl(cursor.getString(cursor.getColumnIndexOrThrow("image_url")));
                chiTietDonHang.setTenSanPham(cursor.getString(cursor.getColumnIndexOrThrow("tenSanPham")));
                chiTietDonHang.setChecked(cursor.getInt(cursor.getColumnIndexOrThrow("isChecked")) == 1);
                chiTietDonHangList.add(chiTietDonHang);
            }
            cursor.close();
        }
        return chiTietDonHangList;
    }

    public void close() {
        dbHelper.close();
    }
    public void deleteChiTietDonHang(int maChiTietDonHang) {
        db.delete("ChiTietDonHang", "maChiTietDonHang = ?", new String[]{String.valueOf(maChiTietDonHang)});
    }
    public List<ChiTietDonHang> getChiTietDonHangByUserId(int userId) {
        List<ChiTietDonHang> chiTietDonHangList = new ArrayList<>();
        Cursor cursor = db.query("ChiTietDonHang", null, "maUser = ?", new String[]{String.valueOf(userId)}, null, null, null);

        if (cursor != null) {
            while (cursor.moveToNext()) {
                ChiTietDonHang chiTietDonHang = new ChiTietDonHang();
                chiTietDonHang.setMaChiTietDonHang(cursor.getInt(cursor.getColumnIndexOrThrow("maChiTietDonHang")));
                chiTietDonHang.setMaSanPham(cursor.getInt(cursor.getColumnIndexOrThrow("maSanPham")));
                chiTietDonHang.setMaUser(cursor.getInt(cursor.getColumnIndexOrThrow("maUser")));
                chiTietDonHang.setSoLuong(cursor.getInt(cursor.getColumnIndexOrThrow("soLuong")));
                chiTietDonHang.setGia(cursor.getDouble(cursor.getColumnIndexOrThrow("gia")));
                chiTietDonHang.setImageUrl(cursor.getString(cursor.getColumnIndexOrThrow("image_url")));
                chiTietDonHang.setTenSanPham(cursor.getString(cursor.getColumnIndexOrThrow("tenSanPham")));
                chiTietDonHang.setChecked(cursor.getInt(cursor.getColumnIndexOrThrow("isChecked")) > 0);
                chiTietDonHangList.add(chiTietDonHang);
            }
            cursor.close();
        }

        return chiTietDonHangList;
    }
}
package com.example.smartmart.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.smartmart.DBHelper.DatabaseHelper;
import com.example.smartmart.models.DonHang;

import java.util.ArrayList;
import java.util.List;

public class DonHangDAO {
    private SQLiteDatabase db;

    public DonHangDAO(Context context) {
        DatabaseHelper dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long insertDonHang(DonHang donHang) {
        ContentValues values = new ContentValues();
        values.put("maChiTietDonHang", donHang.getMaChiTietDonHang());
        values.put("maUser", donHang.getMaUser());
        values.put("maSanPham", donHang.getMaSanPham());
        values.put("ngayDatHang", donHang.getNgayDatHang());
        values.put("trangThai", donHang.getTrangThai());
        values.put("ngayThayDoiTrangThai", donHang.getNgayThayDoiTrangThai());
        values.put("tongGia", donHang.getTongGia());
        return db.insert("DonHang", null, values);
    }

    public int updateDonHang(DonHang donHang) {
        ContentValues values = new ContentValues();
        values.put("maChiTietDonHang", donHang.getMaChiTietDonHang());
        values.put("maUser", donHang.getMaUser());
        values.put("maSanPham", donHang.getMaSanPham());
        values.put("ngayDatHang", donHang.getNgayDatHang());
        values.put("trangThai", donHang.getTrangThai());
        values.put("ngayThayDoiTrangThai", donHang.getNgayThayDoiTrangThai());
        values.put("tongGia", donHang.getTongGia());
        return db.update("DonHang", values, "maDonHang = ?", new String[]{String.valueOf(donHang.getMaDonHang())});
    }

    public int deleteDonHang(int maDonHang) {
        return db.delete("DonHang", "maDonHang = ?", new String[]{String.valueOf(maDonHang)});
    }

    public DonHang getDonHang(int maDonHang) {
        Cursor cursor = db.query("DonHang", null, "maDonHang = ?", new String[]{String.valueOf(maDonHang)}, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            DonHang donHang = new DonHang();
            donHang.setMaDonHang(cursor.getInt(cursor.getColumnIndexOrThrow("maDonHang")));
            donHang.setMaChiTietDonHang(cursor.getInt(cursor.getColumnIndexOrThrow("maChiTietDonHang")));
            donHang.setMaUser(cursor.getInt(cursor.getColumnIndexOrThrow("maUser")));
            donHang.setMaSanPham(cursor.getInt(cursor.getColumnIndexOrThrow("maSanPham")));
            donHang.setNgayDatHang(cursor.getString(cursor.getColumnIndexOrThrow("ngayDatHang")));
            donHang.setTrangThai(cursor.getString(cursor.getColumnIndexOrThrow("trangThai")));
            donHang.setNgayThayDoiTrangThai(cursor.getString(cursor.getColumnIndexOrThrow("ngayThayDoiTrangThai")));
            donHang.setTongGia(cursor.getDouble(cursor.getColumnIndexOrThrow("tongGia")));
            cursor.close();
            return donHang;
        }
        return null;
    }

    public List<DonHang> getAllDonHang() {
        List<DonHang> donHangList = new ArrayList<>();
        Cursor cursor = db.query("DonHang", null, null, null, null, null, "ngayDatHang DESC");
        if (cursor != null && cursor.moveToFirst()) {
            do {
                DonHang donHang = new DonHang();
                donHang.setMaDonHang(cursor.getInt(cursor.getColumnIndexOrThrow("maDonHang")));
                donHang.setMaChiTietDonHang(cursor.getInt(cursor.getColumnIndexOrThrow("maChiTietDonHang")));
                donHang.setMaUser(cursor.getInt(cursor.getColumnIndexOrThrow("maUser")));
                donHang.setMaSanPham(cursor.getInt(cursor.getColumnIndexOrThrow("maSanPham")));
                donHang.setNgayDatHang(cursor.getString(cursor.getColumnIndexOrThrow("ngayDatHang")));
                donHang.setTrangThai(cursor.getString(cursor.getColumnIndexOrThrow("trangThai")));
                donHang.setNgayThayDoiTrangThai(cursor.getString(cursor.getColumnIndexOrThrow("ngayThayDoiTrangThai")));
                donHang.setTongGia(cursor.getDouble(cursor.getColumnIndexOrThrow("tongGia")));
                donHangList.add(donHang);
            } while (cursor.moveToNext());
            cursor.close();
        }
        return donHangList;
    }
}
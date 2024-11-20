package com.example.smartmart.DBHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "smartmart4.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_PRODUCTS = "products";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_PRICE = "price";
    public static final String COLUMN_CATEGORY = "category";
    public static final String COLUMN_QUANTITY = "quantity";
    public static final String COLUMN_SOLD = "sold";
    public static final String COLUMN_DATE = "date";
    public static final String COLUMN_IMAGE_URL = "image_url";

    private static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_PRODUCTS + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_NAME + " TEXT, " +
                    COLUMN_DESCRIPTION + " TEXT, " +
                    COLUMN_PRICE + " REAL, " +
                    COLUMN_CATEGORY + " TEXT, " +
                    COLUMN_QUANTITY + " INTEGER, " +
                    COLUMN_SOLD + " INTEGER, " +
                    COLUMN_DATE + " TEXT, " +
                    COLUMN_IMAGE_URL + " TEXT" +
                    ");";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
        db.execSQL("INSERT INTO products (id,name,description,price,category,quantity,sold,date,image_url) " +
                "VALUES (1,'IPhone 16 ProMax 256GB','iPhone 16 series mang đến nhiều nâng cấp quan trọng so với iPhone 15 series, từ hiệu năng, camera, đến các tính năng tiên tiến khác. Được trang bị chip A18 mạnh mẽ hơn, iPhone 16 mang lại hiệu suất vượt trội so với iPhone 15 với chip A16, giúp cải thiện khả năng xử lý đồ họa và tiết kiệm năng lượng tốt hơn\u200B.\n" +
                "\n" +
                "iPhone 16 mang đến sự đột phá với camera \"Fusion\" 48 MP, giúp tạo ra những bức ảnh rõ nét, đặc biệt khi thiếu sáng. Tính năng quay video không gian và chụp ảnh macro biến những khoảnh khắc thành ảnh và video 3D sống động. Nổi bật không kém là nút Camera Control, hỗ trợ thao tác nhanh chóng và điều khiển cảm ứng, đồng thời tương thích với nhiều ứng dụng bên thứ ba.\n Chip Apple A18 Pro 6 nhân\n" +
                "\n" +
                "RAM: 8 GB\n" +
                "\n" +
                "Dung lượng: 256 GB\n" +
                "\n" +
                "Camera sau: Chính 48 MP & Phụ 48 MP, 12 MP\n" +
                "\n" +
                "Camera trước: 12 MP\n" +
                "\n" +
                "Pin 33 giờ, Sạc 20 W',34490000,'Iphone',50,10,'19-11-2024','https://cdn.tgdd.vn/Products/Images/42/329149/iphone-16-pro-max-black-thumb-600x600.jpg')");
        db.execSQL("INSERT INTO products (id,name,description,price,category,quantity,sold,date,image_url) " +
                "VALUES (2,'IPhone 15 ProMax 256GB','Diện mạo đẳng cấp và cực kỳ sang trọng\n" +
                "iPhone 15 Pro Max tiếp tục sẽ là một chiếc điện thoại có màn hình và mặt lưng phẳng đặc trưng đến từ nhà Apple, mang lại vẻ đẹp thanh lịch và sang trọng.\n" +
                "\n" +
                "Chất liệu chủ đạo của iPhone 15 Pro Max vẫn là khung kim loại và mặt lưng kính cường lực, tạo nên sự bền bỉ và chắc chắn. Tuy nhiên, với công nghệ tiên tiến, khung này đã được nâng cấp thành chất liệu titanium thay vì thép không gỉ hay nhôm ở những thế hệ trước." +
                "\n" +
                "Chip Apple A17 Pro 6 nhân\n" +
                "\n" +
                "RAM: 8 GB\n" +
                "\n" +
                "Dung lượng: 512 GB\n" +
                "\n" +
                "Camera sau: Chính 48 MP & Phụ 12 MP, 12 MP\n" +
                "\n" +
                "Camera trước: 12 MP\n" +
                "\n" +
                "Pin 4422 mAh, Sạc 20 W',25990000,'Iphone',50,10,'12-6-2024','https://cdn.tgdd.vn/Products/Images/42/305659/iphone-15-pro-max-black-thumbnew-600x600.jpg')");
    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCTS);
        onCreate(db);
    }
}
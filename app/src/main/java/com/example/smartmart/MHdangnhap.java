package com.example.smartmart;



import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.smartmart.DBHelper.DatabaseHelper;
import com.example.smartmart.models.User;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MHdangnhap extends AppCompatActivity {


    String TAG = "zzzzzzzz";

    //FireBase
    FirebaseAuth auth;
    Button btnDangNhap, btnDangKy;
    TextView btnQuenMatKhau;
    TextInputEditText edtTaiKhoan, edtMatKhau;
    CheckBox chkGhiNho;
    //SharedPreferences
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_mhdangnhap);
        dbHelper = new DatabaseHelper(this);

        // Assume you have a method to handle login

        FirebaseApp.initializeApp(this);
        //Mapping
        auth = FirebaseAuth.getInstance();
        btnDangNhap = findViewById(R.id.btnDangNhap);
        btnDangKy = findViewById(R.id.btnDangKy);
        btnQuenMatKhau = findViewById(R.id.btnQuenMatKhau);
        edtTaiKhoan = findViewById(R.id.edtTaiKhoan);
        edtMatKhau = findViewById(R.id.edtMatKhau);
        chkGhiNho = findViewById(R.id.chkGhiNho);
        chkGhiNho = findViewById(R.id.chkGhiNho);
        pref = getSharedPreferences("user_info", MODE_PRIVATE);
        editor = pref.edit();

        // Load saved email, password
        loadSavedCredentials();

        //Xử lý các sự kiện
        btnDangNhap.setOnClickListener(v -> {
            String email = edtTaiKhoan.getText().toString();
            String password = edtMatKhau.getText().toString();
            handleLogin();
            if(!email.equals("") && !password.equals("")){
                loginUser(email, password);
            } else {
                Toast.makeText(MHdangnhap.this, "Không để trống email hoặc mật khẩu!", Toast.LENGTH_SHORT).show();
            }
        });

        //Chuyển sang màn hình đăng ký
        btnDangKy.setOnClickListener(v -> {
            Intent intent = new Intent(this, MHdangky.class);
            startActivity(intent);
        });

        btnQuenMatKhau.setOnClickListener(v -> {
            Intent intent = new Intent(this, ForgotPasswordActivity.class);
            startActivity(intent);
        });
    }


    private void loginUser(String email, String password) {
        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        Log.d(TAG, "signInWithEmail:success");
                        FirebaseUser user = auth.getCurrentUser();

                        if (chkGhiNho.isChecked()) {
                            editor.putString("email", email);
                            editor.putString("password", password);
                            editor.putBoolean("remember", true);
                        } else {
                            editor.clear();
                        }
                        editor.apply();

                        Intent intent = new Intent(this, MainActivity.class);
                        intent.putExtra("email", email);
                        startActivity(intent);
                        finish();
                    } else {
                        Log.w(TAG, "signInWithEmail:failure", task.getException());
                        Toast.makeText(MHdangnhap.this, "Đăng nhập thất bại!", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void loadSavedCredentials() {
        String savedEmail = pref.getString("email", "");
        String savedPassword = pref.getString("password", "");
        boolean isRemembered = pref.getBoolean("remember", false);

        if(isRemembered){
            edtTaiKhoan.setText(savedEmail);
            edtMatKhau.setText(savedPassword);
            chkGhiNho.setChecked(isRemembered);
        }
    }
    private void handleLogin() {
        String email = edtTaiKhoan.getText().toString();
        String password = edtMatKhau.getText().toString();

        // Validate login credentials (this is just a placeholder, implement your own logic)
        if (validateCredentials(email, password)) {
            User user = dbHelper.getUserByEmail(email);
            if (user != null) {
                // Use the user information as needed
                Log.d("User Info", "Name: " + user.getNickName() + ", Email: " + user.getEmail());
                // Navigate to the next activity
                Intent intent = new Intent(MHdangnhap.this, MainActivity.class);
                intent.putExtra("user", user);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(MHdangnhap.this, "User not found.", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(MHdangnhap.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean validateCredentials(String email, String password) {
        // Implement your own logic to validate the email and password
        return true;
    }
}
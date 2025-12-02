package com.example.lab4ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    private EditText edtEmail, edtPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m001_act_login);
        edtEmail = findViewById(R.id.edtEmail);
        edtPass = findViewById(R.id.edtPass);
        findViewById(R.id.btnLogin).setOnClickListener(v -> showLoginToast());
    }

    private void showLoginToast() {
        String email = edtEmail.getText().toString().trim();
        String pass = edtPass.getText().toString().trim();
        View toastView = LayoutInflater.from(this).inflate(R.layout.toast_login, null, false);
        TextView tv = toastView.findViewById(R.id.tvToast);
        tv.setText("Bạn đã đăng nhập thành công với email: " + email + " và mật khẩu: " + pass);

        Toast t = new Toast(this);
        t.setView(toastView);
        t.setDuration(Toast.LENGTH_SHORT);
        t.setGravity(Gravity.BOTTOM, 0, 120);
        t.show();

        startActivity(new Intent(this, HomeActivity.class));

    }
}

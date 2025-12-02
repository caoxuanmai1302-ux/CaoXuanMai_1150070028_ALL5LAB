package com.example.mai12cung;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class M002DetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m002_act_detail);
        if (getSupportActionBar() != null) getSupportActionBar().hide();

        ImageView iv = findViewById(R.id.iv_detail);
        TextView tvName = findViewById(R.id.tv_detail_name);
        TextView tvContent = findViewById(R.id.tv_detail_content);
        Button btnBack = findViewById(R.id.btn_back);

        iv.setImageResource(getIntent().getIntExtra("image", R.drawable.aries));
        tvName.setText(getIntent().getStringExtra("name"));
        tvContent.setText(getIntent().getStringExtra("content"));

        btnBack.setOnClickListener(v -> finish());
    }
}
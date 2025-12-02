package com.example.lab4ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        TextView btnBai1 = findViewById(R.id.btnBai1);
        btnBai1.setOnClickListener(v -> {
            Intent i = new Intent(HomeActivity.this, LoginActivity.class);
            startActivity(i);
        });

        TextView btnBai2 = findViewById(R.id.btnBai2);
        btnBai2.setOnClickListener(v -> {
            Intent i = new Intent(HomeActivity.this, TopicWordsActivity.class);
            startActivity(i);
        });

        TextView btnBai3 = findViewById(R.id.btnBai3);
        btnBai3.setOnClickListener(v -> {
            Intent i = new Intent(HomeActivity.this, EmoijActivity.class);
            startActivity(i);
        });
    }
}

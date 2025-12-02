package com.example.mai;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class Lab2Activity extends AppCompatActivity {

    Button btnL2Tab1, btnL2Tab2, btnL2Tab3, btnBackLab1;
    LinearLayout box1, box2, box3;
    View l2Overlay;
    Button btnRunSplash, btnShowOverlay, btnOpenProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab2);

        bindViews();
        setupTabs();
        setupActions();

        // Nhận TAB mặc định nếu mở từ Lab1 (0..2). Mặc định tab 0
        int tab = getIntent().getIntExtra("TAB", 0);
        switchTab(tab);
    }

    private void bindViews() {
        btnL2Tab1 = findViewById(R.id.btnL2Tab1);
        btnL2Tab2 = findViewById(R.id.btnL2Tab2);
        btnL2Tab3 = findViewById(R.id.btnL2Tab3);
        btnBackLab1 = findViewById(R.id.btnBackLab1);

        box1 = findViewById(R.id.boxL2_1);
        box2 = findViewById(R.id.boxL2_2);
        box3 = findViewById(R.id.boxL2_3);

        l2Overlay = findViewById(R.id.l2_overlay);

        btnRunSplash = findViewById(R.id.btnRunSplash);
        btnShowOverlay = findViewById(R.id.btnShowOverlay);
        btnOpenProfile = findViewById(R.id.btnOpenProfile);
    }

    private void setupTabs() {
        View.OnClickListener t = v -> {
            if (v == btnL2Tab1) switchTab(0);
            else if (v == btnL2Tab2) switchTab(1);
            else if (v == btnL2Tab3) switchTab(2);
        };
        btnL2Tab1.setOnClickListener(t);
        btnL2Tab2.setOnClickListener(t);
        btnL2Tab3.setOnClickListener(t);
    }

    private void switchTab(int idx) {
        box1.setVisibility(idx == 0 ? View.VISIBLE : View.GONE);
        box2.setVisibility(idx == 1 ? View.VISIBLE : View.GONE);
        box3.setVisibility(idx == 2 ? View.VISIBLE : View.GONE);
    }

    private void setupActions() {
        // Về Lab 1
        btnBackLab1.setOnClickListener(v ->
                startActivity(new Intent(this, MainActivity.class)));

        // Bài 1: chạy Splash, xong quay lại Lab 2
        btnRunSplash.setOnClickListener(v -> {
            Intent it = new Intent(this, M000ActSplash.class);
            it.putExtra("NEXT", "LAB2");
            startActivity(it);
        });

        // Bài 2: demo overlay 2s
        btnShowOverlay.setOnClickListener(v -> {
            l2Overlay.setVisibility(View.VISIBLE);
            new Handler().postDelayed(() -> l2Overlay.setVisibility(View.GONE), 2000);
        });

        // Bài 3: mở Profile
        btnOpenProfile.setOnClickListener(v ->
                startActivity(new Intent(this, M001ActProfile.class)));
    }
}

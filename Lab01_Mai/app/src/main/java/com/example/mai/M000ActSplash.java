package com.example.mai;

import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class M000ActSplash extends AppCompatActivity {

    private final Random rnd = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m001_act_splash);

        FrameLayout root = findViewById(R.id.splashRoot);
        ImageView img = findViewById(R.id.imgSplash);
        View overlay = findViewById(R.id.loading_overlay);

        // random màu
        String[] colorHex = getResources().getStringArray(R.array.splash_colors);
        if (colorHex != null && colorHex.length > 0) {
            String pick = colorHex[rnd.nextInt(colorHex.length)];
            try { root.setBackgroundColor(Color.parseColor(pick)); }
            catch (IllegalArgumentException ignore) { root.setBackgroundColor(Color.parseColor("#303F9F")); }
        } else root.setBackgroundColor(Color.parseColor("#303F9F"));

        // random icon
        int[] iconIds = getDrawableArray(R.array.splash_icons);
        if (iconIds.length > 0) img.setImageResource(iconIds[rnd.nextInt(iconIds.length)]);

        overlay.setVisibility(View.VISIBLE);

        final String next = getIntent().getStringExtra("NEXT");
        new Handler().postDelayed(() -> {
            overlay.setVisibility(View.GONE);
            if ("LAB2".equals(next)) {
                // Quay về Lab 2 Menu (tab mặc định 0)
                startActivity(new Intent(this, Lab2Activity.class));
            } else {
                // Flow launcher hoặc gọi lẻ -> về Lab 1
                startActivity(new Intent(this, MainActivity.class));
            }
            finish();
        }, 1200);
    }

    private int[] getDrawableArray(int resId) {
        TypedArray ta = getResources().obtainTypedArray(resId);
        int[] ids = new int[ta.length()];
        for (int i = 0; i < ta.length(); i++) ids[i] = ta.getResourceId(i, 0);
        ta.recycle();
        return ids;
    }
}

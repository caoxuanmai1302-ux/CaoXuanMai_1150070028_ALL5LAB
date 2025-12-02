package com.example.mai12cung;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class M001MenuActivity extends AppCompatActivity {

    private final int[] zodiacImages = {
            R.drawable.aries, R.drawable.taurus, R.drawable.gemini, R.drawable.cancer,
            R.drawable.leo, R.drawable.virgo, R.drawable.libra, R.drawable.scorpio,
            R.drawable.sagittarius, R.drawable.capricorn, R.drawable.aquarius, R.drawable.pisces
    };

    private final int[] zodiacIds = {
            R.id.iv_aries, R.id.iv_taurus, R.id.iv_gemini, R.id.iv_cancer,
            R.id.iv_leo, R.id.iv_virgo, R.id.iv_libra, R.id.iv_scorpio,
            R.id.iv_sagittarius, R.id.iv_capricorn, R.id.iv_aquarius, R.id.iv_pisces
    };

    private final String[] zodiacNames = {
            "Bạch Dương", "Kim Ngưu", "Song Tử", "Cự Giải", "Sư Tử", "Xử Nữ",
            "Thiên Bình", "Thiên Yết", "Nhân Mã", "Ma Kết", "Bảo Bình", "Song Ngư"
    };

    private final int[] contentRes = {
            R.string.aries_content, R.string.taurus_content, R.string.gemini_content, R.string.cancer_content,
            R.string.leo_content, R.string.virgo_content, R.string.libra_content, R.string.scorpio_content,
            R.string.sagittarius_content, R.string.capricorn_content, R.string.aquarius_content, R.string.pisces_content
    };

    private ImageView ivSelected;
    private TextView tvName, tvContent;
    private Button btnDetail;
    private int currentIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m001_act_menu);
        if (getSupportActionBar() != null) getSupportActionBar().hide();

        ivSelected = findViewById(R.id.iv_selected);
        tvName = findViewById(R.id.tv_name);
        tvContent = findViewById(R.id.tv_content);
        btnDetail = findViewById(R.id.btn_detail);

        for (int i = 0; i < 12; i++) {
            final int index = i;
            findViewById(zodiacIds[i]).setOnClickListener(v -> showInfo(index));
        }

        btnDetail.setOnClickListener(v -> {
            Intent intent = new Intent(M001MenuActivity.this, M002DetailActivity.class);
            intent.putExtra("image", zodiacImages[currentIndex]);
            intent.putExtra("name", zodiacNames[currentIndex]);
            intent.putExtra("content", getString(contentRes[currentIndex]));
            startActivity(intent);
        });

        showInfo(0);
    }

    private void showInfo(int index) {
        currentIndex = index;
        ivSelected.setImageResource(zodiacImages[index]);
        tvName.setText(zodiacNames[index]);
        tvContent.setText(contentRes[index]);
    }
}
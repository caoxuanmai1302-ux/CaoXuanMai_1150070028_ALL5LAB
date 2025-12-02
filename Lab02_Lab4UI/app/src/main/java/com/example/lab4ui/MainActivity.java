package com.example.lab4ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private static final int[] ID_TEXTS = {
            R.string.txt_mess, R.string.txt_flight, R.string.txt_hospital,
            R.string.txt_hotel, R.string.txt_restaurant, R.string.txt_coctail,
            R.string.txt_store, R.string.txt_work, R.string.txt_time,
            R.string.txt_education, R.string.txt_movie
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        LinearLayout lnMain = findViewById(R.id.ln_main);
        lnMain.removeAllViews();

        for (int i = 0; i < ID_TEXTS.length; i++) {
            final int index = i;
            View v = LayoutInflater.from(this).inflate(R.layout.item_topic, lnMain, false);
            TextView tvTopic = v.findViewById(R.id.tv_topic);
            tvTopic.setText(ID_TEXTS[i]);

            v.setOnClickListener(view -> {
                Toast.makeText(this, sampleWords(index), Toast.LENGTH_SHORT).show();
                Intent it = new Intent(this, TopicWordsActivity.class);
                it.putExtra("topicIndex", index);
                startActivity(it);
            });

            lnMain.addView(v);
        }
    }

    private String sampleWords(int idx) {
        String[][] data = sampleData();
        int n = Math.min(3, data[idx].length);
        StringBuilder sb = new StringBuilder("Từ vựng: ");
        for (int i = 0; i < n; i++) {
            if (i > 0) sb.append(", ");
            sb.append(data[idx][i]);
        }
        return sb.toString();
    }

    public static String[][] sampleData() {
        return new String[][] {
                {"hello","thanks","sorry"},
                {"ticket","passport","luggage"},
                {"doctor","medicine","clinic"},
                {"reception","room","key"},
                {"menu","bill","waiter"},
                {"cocktail","wine","beer"},
                {"price","discount","cashier"},
                {"office","meeting","deadline"},
                {"hour","minute","second"},
                {"school","teacher","student"},
                {"movie","cinema","actor"}
        };
    }
}

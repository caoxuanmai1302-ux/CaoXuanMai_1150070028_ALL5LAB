package com.example.mai;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class M001ActProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m001_act_profile);

        ImageView icPhone = findViewById(R.id.icPhone);
        TextView tvPhone1 = findViewById(R.id.tvPhone1);

        icPhone.setOnClickListener(v -> {
            // Lấy số trong TextView, lọc giữ lại ký tự số và dấu +
            String raw = tvPhone1.getText().toString().trim();
            String phone = raw.replaceAll("[^0-9+]", "");
            if (phone.isEmpty()) phone = "0901234567"; // fallback

            // Mở trình quay số (ACTION_DIAL) -> KHÔNG cần xin quyền
            Intent it = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phone));
            startActivity(it);
        });
    }
}

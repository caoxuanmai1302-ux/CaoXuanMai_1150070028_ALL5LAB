package com.example.mai;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import java.util.Locale;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    // Tabs
    Button btnTabCalc, btnTabRandom, btnTabCall;
    LinearLayout boxCalc, boxRandom, boxCall;

    // Nút Lab2
    Button btnLab2Splash, btnLab2Profile;

    // Bài 1: Máy tính
    EditText edtA, edtB;
    TextView tvKQ;
    Button btnAdd, btnSub, btnMul, btnDiv, btnMod;

    // Bài 2: Ngẫu nhiên & Xúc xắc
    Button btnRand, btnDice;
    TextView tvRand, tvDice;
    Random rnd = new Random();

    // Bài 3: Gọi & SMS
    EditText edtPhone, edtSms;
    Button btnDial, btnSms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindViews();
        setupTabs();
        setupCalc();
        setupRandom();
        setupCallSms();
        setupLab2Navigation();

        switchTab(0);
    }

    private void bindViews() {
        // Tab buttons
        btnTabCalc   = findViewById(R.id.btnTabCalc);
        btnTabRandom = findViewById(R.id.btnTabRandom);
        btnTabCall   = findViewById(R.id.btnTabCall);

        // Boxes
        boxCalc   = findViewById(R.id.boxCalc);
        boxRandom = findViewById(R.id.boxRandom);
        boxCall   = findViewById(R.id.boxCall);

        // Lab2 Buttons
        btnLab2Splash  = findViewById(R.id.btnLab2Splash);
        btnLab2Profile = findViewById(R.id.btnLab2Profile);

        // Calc
        edtA = findViewById(R.id.edtA);
        edtB = findViewById(R.id.edtB);
        tvKQ = findViewById(R.id.tvKQ);
        btnAdd = findViewById(R.id.btnAdd);
        btnSub = findViewById(R.id.btnSub);
        btnMul = findViewById(R.id.btnMul);
        btnDiv = findViewById(R.id.btnDiv);
        btnMod = findViewById(R.id.btnMod);

        // Random
        btnRand = findViewById(R.id.btnRand);
        tvRand  = findViewById(R.id.tvRand);
        btnDice = findViewById(R.id.btnDice);
        tvDice  = findViewById(R.id.tvDice);

        // Call/SMS
        edtPhone = findViewById(R.id.edtPhone);
        edtSms   = findViewById(R.id.edtSms);
        btnDial  = findViewById(R.id.btnDial);
        btnSms   = findViewById(R.id.btnSms);
    }

    private void setupTabs() {
        View.OnClickListener tabListener = v -> {
            if (v == btnTabCalc)      switchTab(0);
            else if (v == btnTabRandom) switchTab(1);
            else if (v == btnTabCall)   switchTab(2);
        };
        btnTabCalc.setOnClickListener(tabListener);
        btnTabRandom.setOnClickListener(tabListener);
        btnTabCall.setOnClickListener(tabListener);
    }

    private void switchTab(int idx) {
        boxCalc.setVisibility(  idx == 0 ? View.VISIBLE : View.GONE);
        boxRandom.setVisibility(idx == 1 ? View.VISIBLE : View.GONE);
        boxCall.setVisibility(  idx == 2 ? View.VISIBLE : View.GONE);

        btnTabCalc.setTypeface(null,   idx == 0 ? Typeface.BOLD : Typeface.NORMAL);
        btnTabRandom.setTypeface(null, idx == 1 ? Typeface.BOLD : Typeface.NORMAL);
        btnTabCall.setTypeface(null,   idx == 2 ? Typeface.BOLD : Typeface.NORMAL);
        setTabSelected(idx);
    }

    private void setTabSelected(int idx) {
        btnTabCalc.setSelected(idx == 0);
        btnTabRandom.setSelected(idx == 1);
        btnTabCall.setSelected(idx == 2);
    }

    // ---------- Điều hướng Lab 2 ----------
    private void setupLab2Navigation() {
        // Mở Lab 2 - Tab 0 (Bài 1 Splash)
        btnLab2Splash.setOnClickListener(v -> {
            Intent it = new Intent(this, Lab2Activity.class);
            it.putExtra("TAB", 0);
            startActivity(it);
        });

        // Mở Lab 2 - Tab 2 (Bài 3 Profile)
        btnLab2Profile.setOnClickListener(v -> {
            Intent it = new Intent(this, Lab2Activity.class);
            it.putExtra("TAB", 2);
            startActivity(it);
        });
    }

    // ---------- Bài 1: Máy tính ----------
    private void setupCalc() {
        View.OnClickListener op = v -> {
            String as = edtA.getText().toString().trim();
            String bs = edtB.getText().toString().trim();
            if (as.isEmpty() || bs.isEmpty()) {
                toast("Nhập đủ hai số");
                return;
            }
            double a, b;
            try {
                a = Double.parseDouble(as);
                b = Double.parseDouble(bs);
            } catch (NumberFormatException e) {
                toast("Giá trị không hợp lệ");
                return;
            }
            double kq;
            if (v == btnAdd) kq = a + b;
            else if (v == btnSub) kq = a - b;
            else if (v == btnMul) kq = a * b;
            else if (v == btnDiv) {
                if (b == 0) { toast("Không thể chia cho 0"); return; }
                kq = a / b;
            } else { // btnMod
                if (b == 0) { toast("Không thể chia dư cho 0"); return; }
                kq = a % b;
            }
            tvKQ.setText(String.format(Locale.getDefault(), "Kết quả: %s", trimTrailingZero(kq)));
        };

        btnAdd.setOnClickListener(op);
        btnSub.setOnClickListener(op);
        btnMul.setOnClickListener(op);
        btnDiv.setOnClickListener(op);
        btnMod.setOnClickListener(op);
    }

    // ---------- Bài 2: Ngẫu nhiên & Xúc xắc ----------
    private void setupRandom() {
        btnRand.setOnClickListener(v -> {
            int val = rnd.nextInt(1001); // 0..1000
            tvRand.setText(String.valueOf(val));
        });

        btnDice.setOnClickListener(v -> {
            int face = 1 + rnd.nextInt(6);
            tvDice.setText(getDiceUnicode(face));
        });
    }

    private String getDiceUnicode(int n) {
        switch (n) {
            case 1: return "⚀";
            case 2: return "⚁";
            case 3: return "⚂";
            case 4: return "⚃";
            case 5: return "⚄";
            default: return "⚅";
        }
    }

    // ---------- Bài 3: Gọi & Nhắn tin ----------
    private void setupCallSms() {
        btnDial.setOnClickListener(v -> {
            String phone = edtPhone.getText().toString().trim();
            if (phone.isEmpty()) { toast("Nhập số điện thoại"); return; }
            Intent it = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phone));
            startActivity(it);
        });

        btnSms.setOnClickListener(v -> {
            String phone = edtPhone.getText().toString().trim();
            String msg = edtSms.getText().toString().trim();
            if (phone.isEmpty()) { toast("Nhập số điện thoại"); return; }
            Uri uri = Uri.parse("smsto:" + phone);
            Intent it = new Intent(Intent.ACTION_SENDTO, uri);
            if (!msg.isEmpty()) it.putExtra("sms_body", msg);
            startActivity(it);
        });
    }

    // ---------- Helpers ----------
    private void toast(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

    private String trimTrailingZero(double d) {
        String s = String.format(Locale.getDefault(), "%.12f", d);
        while (s.contains(".") && (s.endsWith("0") || s.endsWith("."))) {
            s = s.substring(0, s.length() - 1);
        }
        return s;
    }
}

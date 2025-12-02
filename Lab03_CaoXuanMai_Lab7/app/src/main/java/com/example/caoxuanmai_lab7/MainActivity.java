package com.example.caoxuanmai_lab7;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {

    private Button btnColoredSelector;
    private Button btnColorSelectorDisabled;
    private Button btnRoundShape;
    private Button btnShapeWithGradient;
    private Button btnSelectorShape;

    private Button btnCustomToast;
    private Button btnCustomDialog;
    private Button btnOpenColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnColoredSelector        = findViewById(R.id.btnColoredSelector);
        btnColorSelectorDisabled  = findViewById(R.id.btnColorSelectorDisabled);
        btnRoundShape             = findViewById(R.id.btnRoundShape);
        btnShapeWithGradient      = findViewById(R.id.btnShapeWithGradient);
        btnSelectorShape          = findViewById(R.id.btnSelectorShape);
        btnCustomToast            = findViewById(R.id.btnCustomToast);
        btnCustomDialog           = findViewById(R.id.btnCustomDialog);
        btnOpenColor             = findViewById(R.id.btnOpenColor);

        View.OnClickListener demoListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (! (v instanceof Button)) return;
                Button b = (Button) v;
                Toast.makeText(MainActivity.this,
                        "Bạn bấm: " + b.getText(),
                        Toast.LENGTH_SHORT).show();
            }
        };

        btnColoredSelector.setOnClickListener(demoListener);
        btnRoundShape.setOnClickListener(demoListener);
        btnShapeWithGradient.setOnClickListener(demoListener);
        btnSelectorShape.setOnClickListener(demoListener);

        btnCustomToast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCustomToast("Custom Toast, made by Xuan Mai");
            }
        });

        btnCustomDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCustomDialog();
            }
        });
        btnOpenColor.setOnClickListener(v -> {
            Intent i = new Intent(MainActivity.this, ColorActivity.class);
            startActivity(i);
        });
    }

    private void showCustomToast(String message) {
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.custom_toast, null);

        TextView txtToast = layout.findViewById(R.id.txtToast);
        txtToast.setText(message);

        Toast toast = new Toast(getApplicationContext());
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.setGravity(Gravity.BOTTOM, 0, 100);
        toast.show();
    }

    private void showCustomDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.custom_dialog, null);

        builder.setView(view);
        AlertDialog dialog = builder.create();

        TextView tvTitle = view.findViewById(R.id.tvDialogTitle);
        TextView tvMessage = view.findViewById(R.id.tvDialogMessage);
        Button btnCancel = view.findViewById(R.id.btnDialogCancel);
        Button btnOK = view.findViewById(R.id.btnDialogOK);

        tvTitle.setText("Đăng nhập");
        tvMessage.setText("Tài khoản: admin\nMật khẩu: ********");

        btnCancel.setOnClickListener(v -> dialog.dismiss());

        btnOK.setOnClickListener(v -> {
            showCustomToast("Đăng nhập thành công!");
            dialog.dismiss();
        });

        dialog.show();
    }
}





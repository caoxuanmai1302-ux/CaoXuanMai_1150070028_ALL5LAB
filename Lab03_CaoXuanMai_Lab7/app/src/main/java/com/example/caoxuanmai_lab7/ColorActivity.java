package com.example.caoxuanmai_lab7;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;

public class ColorActivity extends AppCompatActivity {

    private SeekBar seekR, seekG, seekB;
    private TextView tvRValue, tvGValue, tvBValue;
    private TextView tvCMYValue, tvColorRGB, tvColorCMY;

    private int r = 0, g = 0, b = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color);

        seekR = findViewById(R.id.seekR);
        seekG = findViewById(R.id.seekG);
        seekB = findViewById(R.id.seekB);

        tvRValue = findViewById(R.id.tvRValue);
        tvGValue = findViewById(R.id.tvGValue);
        tvBValue = findViewById(R.id.tvBValue);
        tvCMYValue = findViewById(R.id.tvCMYValue);
        tvColorRGB = findViewById(R.id.tvColorRGB);
        tvColorCMY = findViewById(R.id.tvColorCMY);

        SeekBar.OnSeekBarChangeListener listener = new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (seekBar.getId() == R.id.seekR) {
                    r = progress;
                    tvRValue.setText("R = " + r);
                } else if (seekBar.getId() == R.id.seekG) {
                    g = progress;
                    tvGValue.setText("G = " + g);
                } else if (seekBar.getId() == R.id.seekB) {
                    b = progress;
                    tvBValue.setText("B = " + b);
                }
                updateColors();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        };

        seekR.setOnSeekBarChangeListener(listener);
        seekG.setOnSeekBarChangeListener(listener);
        seekB.setOnSeekBarChangeListener(listener);

        updateColors();
    }

    private void updateColors() {
        int c = 255 - r;
        int m = 255 - g;
        int y = 255 - b;

        tvCMYValue.setText("C = " + c + ", M = " + m + ", Y = " + y);

        int colorRGB = Color.rgb(r, g, b);
        tvColorRGB.setBackgroundColor(colorRGB);

        int colorCMY = Color.rgb(c, m, y);
        tvColorCMY.setBackgroundColor(colorCMY);
    }
}




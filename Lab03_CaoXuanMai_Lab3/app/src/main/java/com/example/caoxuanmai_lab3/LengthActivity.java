package com.example.caoxuanmai_lab3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class LengthActivity extends AppCompatActivity {

    EditText edtNumber;
    Spinner spUnit;
    ListView lvResult;

    String[] units = {"km", "m", "cm", "mm", "inch", "ft", "yd", "mile"};

    double[] toMeter = {
            1000,
            1,
            0.01,
            0.001,
            0.0254,
            0.3048,
            0.9144,
            1609.344
    };

    ArrayList<RowLength> rows = new ArrayList<>();
    LengthAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_length);

        edtNumber = findViewById(R.id.edtNumber);
        spUnit = findViewById(R.id.spUnit);
        lvResult = findViewById(R.id.lvResult);

        ArrayAdapter<String> spAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                units
        );
        spAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spUnit.setAdapter(spAdapter);

        adapter = new LengthAdapter();
        lvResult.setAdapter(adapter);

        edtNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                updateList();
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void afterTextChanged(Editable s) {}
        });

        spUnit.setOnItemSelectedListener(new android.widget.AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(android.widget.AdapterView<?> parent, View view, int position, long id) {
                updateList();
            }
            @Override
            public void onNothingSelected(android.widget.AdapterView<?> parent) {}
        });
    }

    private void updateList() {
        String s = edtNumber.getText().toString().trim();
        rows.clear();

        if (s.isEmpty()) {
            adapter.notifyDataSetChanged();
            return;
        }

        double number = Double.parseDouble(s);
        int base = spUnit.getSelectedItemPosition();

        double numberInMeter = number * toMeter[base];

        for (int i = 0; i < units.length; i++) {
            double value = numberInMeter / toMeter[i];
            rows.add(new RowLength(units[i], value));
        }

        adapter.notifyDataSetChanged();
    }

    private static class RowLength {
        String unit;
        double value;

        RowLength(String unit, double value) {
            this.unit = unit;
            this.value = value;
        }
    }

    private class LengthAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return rows.size();
        }

        @Override
        public Object getItem(int position) {
            return rows.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder h;

            if (convertView == null) {
                convertView = LayoutInflater.from(LengthActivity.this)
                        .inflate(R.layout.item_length, parent, false);
                h = new ViewHolder();
                h.tvUnit = convertView.findViewById(R.id.tvUnit);
                h.tvValue = convertView.findViewById(R.id.tvValue);
                convertView.setTag(h);
            } else {
                h = (ViewHolder) convertView.getTag();
            }

            RowLength r = rows.get(position);
            h.tvUnit.setText(r.unit);
            h.tvValue.setText(String.valueOf(r.value));

            return convertView;
        }

        class ViewHolder {
            TextView tvUnit, tvValue;
        }
    }
}


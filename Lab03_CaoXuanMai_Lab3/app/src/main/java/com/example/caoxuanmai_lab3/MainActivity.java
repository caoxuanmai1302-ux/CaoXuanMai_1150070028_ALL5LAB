package com.example.caoxuanmai_lab3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText edtAmount;
    Spinner spBaseCurrency;
    ListView lvRates;
    Button btnOpenLength;

    String[] codes = {
            "USD", "EUR", "GBP", "INR", "AUD",
            "CAD", "ZAR", "NZD", "JPY", "VND"
    };

    int[] flagRes = {
            R.drawable.flag_us,
            R.drawable.flag_eu,
            R.drawable.flag_gb,
            R.drawable.flag_in,
            R.drawable.flag_au,
            R.drawable.flag_ca,
            R.drawable.flag_za,
            R.drawable.flag_nz,
            R.drawable.flag_jp,
            R.drawable.flag_vn
    };

    double[][] rate = {
            {1,        0.80518, 0.64107, 63.318,  1.21828, 1.16236, 11.16236,1.2931,  118.337,  21385.7},
            {1.24172,  1,       0.79575, 78.6084,1.51366, 1.44314, 14.5371, 1.60576, 146.927,  26561.8},
            {1.56044,  1.25667, 1,       98.7848,1.86133, 1.81355, 18.2683, 2.10791, 184.638,  33374.9},
            {0.0158,   0.01272, 0.01012, 1,      0.01884, 0.01835, 0.183693,0.02043, 1.8691,   337.811},
            {0.82059,  0.66159, 0.5262,  52.086, 1,       0.95416, 9.61148, 1.06158, 97.112,   17567.9},
            {0.86059,  0.69699, 0.55134, 54.5885,1.04805, 1,       10.0732, 1.11258, 101.777,  18401.7},
            {0.08541,  0.06877, 0.05473, 5.40852,0.09924, 0.09924, 1,       0.11037, 10.0996,  1825.87},
            {0.77402,  0.62319, 0.49597, 49.0073,0.94191, 0.89891, 9.06754, 1,       91.5139,  16552.1},
            {0.008486, 0.006406,0.005162,0.51844,0.0103,  0.00983, 0.09983, 0.01093, 1,        180.837},
            {0.00005,  0.00004, 0.00003, 0.002962,0.00006,0.00005, 0.00055, 0.00006, 0.00553,  1}
    };

    ArrayList<RowCurrency> rows = new ArrayList<>();
    RateAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtAmount = findViewById(R.id.edtAmount);
        spBaseCurrency = findViewById(R.id.spBaseCurrency);
        lvRates = findViewById(R.id.lvRates);
        btnOpenLength = findViewById(R.id.btnOpenLength);

        ArrayAdapter<String> spAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                codes
        );
        spAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spBaseCurrency.setAdapter(spAdapter);

        adapter = new RateAdapter();
        lvRates.setAdapter(adapter);

        spBaseCurrency.setSelection(6);

        edtAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                updateList();
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void afterTextChanged(Editable s) {}
        });

        spBaseCurrency.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                updateList();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        btnOpenLength.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, LengthActivity.class);
                startActivity(intent);
            }
        });
    }

    private void updateList() {
        String sAmount = edtAmount.getText().toString().trim();
        rows.clear();

        if (sAmount.isEmpty()) {
            adapter.notifyDataSetChanged();
            return;
        }

        double amount = Double.parseDouble(sAmount);
        int baseIndex = spBaseCurrency.getSelectedItemPosition();

        for (int i = 0; i < codes.length; i++) {
            double value = amount * rate[baseIndex][i];
            rows.add(new RowCurrency(flagRes[i], codes[i], value));
        }

        adapter.notifyDataSetChanged();
    }

    private static class RowCurrency {
        int flagResId;
        String code;
        double value;

        RowCurrency(int flagResId, String code, double value) {
            this.flagResId = flagResId;
            this.code = code;
            this.value = value;
        }
    }

    private class RateAdapter extends BaseAdapter {

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
                LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
                convertView = inflater.inflate(R.layout.item_currency_rate, parent, false);
                h = new ViewHolder();
                h.imgFlag = convertView.findViewById(R.id.imgFlag);
                h.tvCode = convertView.findViewById(R.id.tvCode);
                h.tvValue = convertView.findViewById(R.id.tvValue);
                convertView.setTag(h);
            } else {
                h = (ViewHolder) convertView.getTag();
            }

            RowCurrency r = rows.get(position);
            h.imgFlag.setImageResource(r.flagResId);
            h.tvCode.setText(r.code);
            h.tvValue.setText(String.valueOf(r.value));

            return convertView;
        }

        class ViewHolder {
            ImageView imgFlag;
            TextView tvCode;
            TextView tvValue;
        }
    }
}

package com.example.lab4ui;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;

public class TopicWordsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic_words);

        int topicIndex = getIntent().getIntExtra("topicIndex", 0);
        String[][] data = MainActivity.sampleData();

        ListView lv = findViewById(R.id.lvWords);
        lv.setAdapter(new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, data[topicIndex]));
    }
}

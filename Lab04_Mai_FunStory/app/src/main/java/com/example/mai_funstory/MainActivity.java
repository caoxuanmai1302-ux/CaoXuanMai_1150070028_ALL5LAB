package com.example.mai_funstory;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import android.os.Bundle;

import com.example.mai_funstory.model.StoryEntity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private String topicName = "Trạng Quỳnh";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showFrg(new M000SplashFrg());
    }

    public void showFrg(Fragment frg) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.ln_main, frg)
                .commit();
    }

    public void gotoM001Screen() {
        showFrg(new M001TopicFrg());
    }

    public void gotoM002Screen() {
        M002StoryFrg frg = new M002StoryFrg();
        frg.setTopicName(topicName);
        showFrg(frg);
    }

    public void backToM001Screen() {
        gotoM001Screen();
    }

    public void gotoM003Screen(ArrayList<StoryEntity> list, StoryEntity s) {
        M003DetailStoryFrg frg = new M003DetailStoryFrg();
        frg.setData(topicName, list, s);
        showFrg(frg);
    }
}

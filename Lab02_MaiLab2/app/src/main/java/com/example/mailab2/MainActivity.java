package com.example.mailab2;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<StoryEntity> stories;
    private StoryEntity currentStory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showFrg(new M000SplashFrg(), false);
    }

    public void showFrg(@NonNull Fragment frg, boolean addToBackStack) {
        FragmentTransaction tx = getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.ln_main, frg, frg.getClass().getSimpleName());
        if (addToBackStack) tx.addToBackStack(frg.getClass().getSimpleName());
        tx.commit();
    }

    public void gotoM001Screen() { showFrg(new M001TopicListFrg(), false); }

    public void gotoM002Screen(String topicName) {
        showFrg(M002StoryListFrg.newInstance(topicName), true);
    }

    public void gotoM003Screen(ArrayList<StoryEntity> listStory, StoryEntity story) {
        this.stories = listStory;
        this.currentStory = story;
        showFrg(M003StoryDetailFrg.newInstance(listStory, story), true);
    }

    public void openStoryAt(int index) {
        if (stories == null || index < 0 || index >= stories.size()) return;
        currentStory = stories.get(index);
        showFrg(M003StoryDetailFrg.newInstance(stories, currentStory), false);
    }
}

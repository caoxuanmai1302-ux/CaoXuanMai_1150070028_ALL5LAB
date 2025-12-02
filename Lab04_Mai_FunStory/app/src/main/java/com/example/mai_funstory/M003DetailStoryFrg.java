package com.example.mai_funstory;

import android.content.Context;
import android.os.Bundle;
import android.view.*;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.mai_funstory.adapter.DetailStoryAdapter;
import com.example.mai_funstory.model.StoryEntity;

import java.util.ArrayList;

public class M003DetailStoryFrg extends Fragment {

    private Context context;
    private ArrayList<StoryEntity> list;
    private StoryEntity current;
    private String topicName;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.m003_frg_detail_story, container, false);

        v.findViewById(R.id.iv_back).setVisibility(View.VISIBLE);
        v.findViewById(R.id.iv_back).setOnClickListener(v1 ->
                ((MainActivity)getActivity()).gotoM002Screen()
        );

        ((TextView)v.findViewById(R.id.tv_name)).setText(topicName);

        ViewPager vp = v.findViewById(R.id.vp_story);
        DetailStoryAdapter adapter = new DetailStoryAdapter(list, context);
        vp.setAdapter(adapter);

        vp.setCurrentItem(list.indexOf(current), true);

        return v;
    }

    @Override
    public void onAttach(Context ctx) {
        super.onAttach(ctx);
        context = ctx;
    }

    public void setData(String topic, ArrayList<StoryEntity> list, StoryEntity current) {
        this.topicName = topic;
        this.list = list;
        this.current = current;
    }
}

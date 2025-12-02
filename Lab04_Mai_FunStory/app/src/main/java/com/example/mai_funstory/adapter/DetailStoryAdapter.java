package com.example.mai_funstory.adapter;
import android.content.Context;
import android.view.*;
import android.widget.TextView;
import androidx.viewpager.widget.PagerAdapter;

import com.example.mai_funstory.R;
import com.example.mai_funstory.model.StoryEntity;

import java.util.ArrayList;
import java.util.List;

public class DetailStoryAdapter extends PagerAdapter {

    private final List<StoryEntity> list;
    private final Context context;

    public DetailStoryAdapter(ArrayList<StoryEntity> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int pos) {
        View v = LayoutInflater.from(context)
                .inflate(R.layout.item_detail_story, container, false);

        StoryEntity s = list.get(pos);

        ((TextView)v.findViewById(R.id.tv_name)).setText(s.getName());
        ((TextView)v.findViewById(R.id.tv_content)).setText(s.getContent());

        container.addView(v);
        return v;
    }

    @Override public int getCount() { return list.size(); }
    @Override public boolean isViewFromObject(View v, Object o) { return v == o; }
    @Override public void destroyItem(ViewGroup container, int pos, Object obj) {
        container.removeView((View)obj);
    }
}

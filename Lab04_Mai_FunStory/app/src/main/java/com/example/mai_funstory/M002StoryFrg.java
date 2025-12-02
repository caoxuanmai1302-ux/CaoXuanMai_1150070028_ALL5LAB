package com.example.mai_funstory;

import android.content.Context;
import android.os.Bundle;
import android.view.*;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.*;

import com.example.mai_funstory.adapter.StoryAdapter;
import com.example.mai_funstory.model.StoryEntity;

import java.io.*;
import java.util.ArrayList;

public class M002StoryFrg extends Fragment {

    private Context mContext;
    private String topicName;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.m002_frg_story, container, false);

        v.findViewById(R.id.iv_back).setVisibility(View.VISIBLE);
        v.findViewById(R.id.iv_back).setOnClickListener(v1 ->
                ((MainActivity)getActivity()).backToM001Screen()
        );

        ((TextView)v.findViewById(R.id.tv_name)).setText(topicName);

        RecyclerView rv = v.findViewById(R.id.rv_story);
        ArrayList<StoryEntity> list = readStory();

        StoryAdapter adapter = new StoryAdapter(list, mContext);
        rv.setLayoutManager(new LinearLayoutManager(mContext));
        rv.setAdapter(adapter);

        return v;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    public void setTopicName(String topicName) { this.topicName = topicName; }

    private ArrayList<StoryEntity> readStory() {
        ArrayList<StoryEntity> list = new ArrayList<>();

        try {
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(mContext.getAssets().open("story/trangquynh.txt"), "UTF-8")
            );

            String line;
            while ((line = br.readLine()) != null) {

                String title = line.trim();
                if (title.isEmpty()) continue;

                StringBuilder content = new StringBuilder();
                while ((line = br.readLine()) != null && !line.contains("','0');")) {
                    content.append(line).append("\n");
                }

                list.add(new StoryEntity(topicName, title, content.toString()));
            }

            br.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}

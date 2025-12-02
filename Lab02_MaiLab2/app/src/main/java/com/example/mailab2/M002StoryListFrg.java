package com.example.mailab2;

import android.os.Bundle;
import android.view.*;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.*;
import java.util.ArrayList;

public class M002StoryListFrg extends Fragment {
    private static final String ARG_TOPIC = "arg_topic";
    public static M002StoryListFrg newInstance(String topic){
        Bundle b = new Bundle(); b.putString(ARG_TOPIC, topic);
        M002StoryListFrg f = new M002StoryListFrg(); f.setArguments(b); return f;
    }

    @Nullable @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle s) {
        return inflater.inflate(R.layout.m002_frg_story_list, container, false);
    }

    @Override public void onViewCreated(@NonNull View v, @Nullable Bundle s) {
        String topic = getArguments()!=null ? getArguments().getString(ARG_TOPIC) : "";
        ((TextView) v.findViewById(R.id.tv_topic_header)).setText(getString(R.string.txt_stories_of, topic));
        RecyclerView rv = v.findViewById(R.id.rv_stories);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        ArrayList<StoryEntity> list = StoryRepo.getStoriesByTopic(getContext(), topic);
        rv.setAdapter(new StoryAdapter(list, (story, pos) ->
                ((MainActivity) requireActivity()).gotoM003Screen(list, story)));
    }
}

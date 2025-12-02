package com.example.mailab2;

import android.os.Bundle;
import android.view.*;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.*;
import java.util.Arrays;
import java.util.List;

public class M001TopicListFrg extends Fragment {
    @Nullable @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle s) {
        return inflater.inflate(R.layout.m001_frg_topics, container, false);
    }
    @Override public void onViewCreated(@NonNull View v, @Nullable Bundle s) {
        RecyclerView rv = v.findViewById(R.id.rv_topics);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        rv.addItemDecoration(new DividerItemDecoration(getContext(), RecyclerView.VERTICAL));
        List<String> topics = Arrays.asList(
                getString(R.string.topic_family),
                getString(R.string.topic_school),
                getString(R.string.topic_work),
                getString(R.string.topic_misc)
        );
        rv.setAdapter(new TopicAdapter(topics, topic ->
                ((MainActivity) requireActivity()).gotoM002Screen(topic)));
    }
}

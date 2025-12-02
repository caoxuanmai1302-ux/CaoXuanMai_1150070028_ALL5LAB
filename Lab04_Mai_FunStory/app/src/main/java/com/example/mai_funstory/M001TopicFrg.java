package com.example.mai_funstory;

import android.os.Bundle;
import android.view.*;
import androidx.fragment.app.Fragment;

public class M001TopicFrg extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.m001_frg_topic, container, false);

        v.findViewById(R.id.topic_block).setOnClickListener(v1 ->
                ((MainActivity)getActivity()).gotoM002Screen()
        );

        return v;
    }
}

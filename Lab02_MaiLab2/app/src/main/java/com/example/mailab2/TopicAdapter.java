package com.example.mailab2;

import android.view.*;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class TopicAdapter extends RecyclerView.Adapter<TopicAdapter.VH> {
    interface OnTopicClick { void onClick(String topic); }
    private final List<String> data;
    private final OnTopicClick listener;
    public TopicAdapter(List<String> data, OnTopicClick l){ this.data=data; this.listener=l; }
    @NonNull @Override public VH onCreateViewHolder(@NonNull ViewGroup p, int v){
        return new VH(LayoutInflater.from(p.getContext()).inflate(R.layout.item_topic, p, false));
    }
    @Override public void onBindViewHolder(@NonNull VH h, int pos){
        String topic = data.get(pos);
        h.tv.setText(topic);
        h.itemView.setOnClickListener(v -> { if (listener!=null) listener.onClick(topic); });
    }
    @Override public int getItemCount(){ return data.size(); }
    static class VH extends RecyclerView.ViewHolder{
        TextView tv; VH(@NonNull View item){ super(item); tv=item.findViewById(R.id.tv_topic); }
    }
}

package com.example.mailab2;

import android.view.*;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class StoryAdapter extends RecyclerView.Adapter<StoryAdapter.VH> {
    interface OnStoryClick { void onClick(StoryEntity story, int position); }
    private final ArrayList<StoryEntity> data; private final OnStoryClick listener;
    public StoryAdapter(ArrayList<StoryEntity> d, OnStoryClick l){ data=d; listener=l; }
    @NonNull @Override public VH onCreateViewHolder(@NonNull ViewGroup p, int v){
        return new VH(LayoutInflater.from(p.getContext()).inflate(R.layout.item_story, p, false));
    }
    @Override public void onBindViewHolder(@NonNull VH h, int pos){
        StoryEntity s = data.get(pos);
        h.tvTitle.setText(s.title);
        String preview = s.content.length()>80 ? s.content.substring(0,80)+"..." : s.content;
        h.tvPreview.setText(preview);
        h.itemView.setOnClickListener(v -> { if (listener!=null) listener.onClick(s, h.getBindingAdapterPosition()); });
    }
    @Override public int getItemCount(){ return data.size(); }
    static class VH extends RecyclerView.ViewHolder{
        TextView tvTitle, tvPreview;
        VH(@NonNull View item){ super(item); tvTitle=item.findViewById(R.id.tv_story_title); tvPreview=item.findViewById(R.id.tv_story_preview); }
    }
}

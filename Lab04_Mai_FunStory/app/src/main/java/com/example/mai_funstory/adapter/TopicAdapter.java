package com.example.mai_funstory.adapter;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.mai_funstory.R;

import java.io.InputStream;

public class TopicAdapter extends RecyclerView.Adapter<TopicAdapter.TopicVH> {

    Context context;
    String[] list;
    IClick listener;

    public interface IClick {
        void onClick(String topicName);
    }

    public TopicAdapter(Context context, String[] list, IClick listener) {
        this.context = context;
        this.list = list;
        this.listener = listener;
    }

    @Override
    public TopicVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_topic, parent, false);
        return new TopicVH(v);
    }

    @Override
    public void onBindViewHolder(TopicVH h, int pos) {
        String file = list[pos];
        String topicName = file.replace(".png", "");

        h.tvTopic.setText(topicName);

        try {
            InputStream is = context.getAssets().open("photo/" + file);
            h.imvTopic.setImageBitmap(BitmapFactory.decodeStream(is));
        } catch (Exception e) {
        }

        h.itemView.setOnClickListener(v -> listener.onClick(topicName));
    }

    @Override
    public int getItemCount() {
        return list.length;
    }

    class TopicVH extends RecyclerView.ViewHolder {

        ImageView imvTopic;
        TextView tvTopic;

        public TopicVH(View v) {
            super(v);
            imvTopic = v.findViewById(R.id.imvTopic);
            tvTopic = v.findViewById(R.id.tvTopic);
        }
    }
}

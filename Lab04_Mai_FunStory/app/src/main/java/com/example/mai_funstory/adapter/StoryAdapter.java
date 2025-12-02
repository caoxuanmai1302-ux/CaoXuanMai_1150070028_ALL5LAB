package com.example.mai_funstory.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.*;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mai_funstory.MainActivity;
import com.example.mai_funstory.R;
import com.example.mai_funstory.model.StoryEntity;

import java.io.InputStream;
import java.util.ArrayList;

public class StoryAdapter extends RecyclerView.Adapter<StoryAdapter.Holder> {

    private final ArrayList<StoryEntity> list;
    private final Context context;

    public StoryAdapter(ArrayList<StoryEntity> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(context)
                .inflate(R.layout.item_story, parent, false));
    }

    @Override
    public void onBindViewHolder(Holder h, int pos) {
        StoryEntity s = list.get(pos);
        h.tv.setText(s.getName());

        // Load icon theo số: 1.png, 2.png, ...
        String fileName = (pos + 1) + ".png"; // 1..10

        try {
            InputStream is = context.getAssets().open("photo/" + fileName);
            Bitmap bitmap = BitmapFactory.decodeStream(is);
            h.iv.setImageBitmap(bitmap);
        } catch (Exception e) {
            e.printStackTrace();
        }

        h.itemView.setOnClickListener(v ->
                ((MainActivity) context).gotoM003Screen(list, s)
        );
    }


    @Override
    public int getItemCount() { return list.size(); }

    public static class Holder extends RecyclerView.ViewHolder {
        TextView tv;
        ImageView iv;

        public Holder(View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.tv_story);
            iv = itemView.findViewById(R.id.iv_icon);
        }
    }

}

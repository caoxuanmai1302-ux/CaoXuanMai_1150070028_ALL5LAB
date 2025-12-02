package com.example.mailab2;

import android.content.Context;
import java.util.ArrayList;

public class StoryRepo {
    public static ArrayList<StoryEntity> getStoriesByTopic(Context ctx, String topic){
        ArrayList<StoryEntity> ls = new ArrayList<>();
        if (topic.equals(ctx.getString(R.string.topic_family))) {
            ls.add(new StoryEntity(1, ctx.getString(R.string.s1_title), ctx.getString(R.string.s1_content)));
            ls.add(new StoryEntity(2, ctx.getString(R.string.s2_title), ctx.getString(R.string.s2_content)));
        } else if (topic.equals(ctx.getString(R.string.topic_school))) {
            ls.add(new StoryEntity(3, ctx.getString(R.string.s3_title), ctx.getString(R.string.s3_content)));
        } else if (topic.equals(ctx.getString(R.string.topic_work))) {
            ls.add(new StoryEntity(4, ctx.getString(R.string.s4_title), ctx.getString(R.string.s4_content)));
        } else {
            ls.add(new StoryEntity(5, ctx.getString(R.string.s5_title), ctx.getString(R.string.s5_content)));
        }
        return ls;
    }
}

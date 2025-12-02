package com.example.mailab2;

import android.os.Parcel;
import android.os.Parcelable;

public class StoryEntity implements Parcelable {
    public int id; public String title; public String content;
    public StoryEntity(int id, String title, String content){ this.id=id; this.title=title; this.content=content; }
    protected StoryEntity(Parcel in){ id=in.readInt(); title=in.readString(); content=in.readString(); }
    public static final Creator<StoryEntity> CREATOR = new Creator<StoryEntity>() {
        @Override public StoryEntity createFromParcel(Parcel in){ return new StoryEntity(in); }
        @Override public StoryEntity[] newArray(int size){ return new StoryEntity[size]; }
    };
    @Override public int describeContents(){ return 0; }
    @Override public void writeToParcel(Parcel dest, int flags){ dest.writeInt(id); dest.writeString(title); dest.writeString(content); }
}

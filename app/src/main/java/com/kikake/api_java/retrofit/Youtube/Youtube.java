package com.kikake.api_java.retrofit.Youtube;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;


public class Youtube implements Parcelable {
    public static final Creator<Youtube> CREATOR = new Creator<Youtube>() {
        @Override
        public Youtube createFromParcel(Parcel source) {
            return new Youtube(source);
        }

        @Override
        public Youtube[] newArray(int size) {
            return new Youtube[size];
        }
    };
    @SerializedName("snippet")
    private Snippet snippet;
    @SerializedName("id")
    private Id id;

//    @SerializedName("title")
//    private String name;
//    @SerializedName("description")
//    private String desk;


    public Youtube(Id id, Snippet snippet) {
//        this.desk = desk;
//        this.name = name;

        this.snippet = snippet;
        this.id = id;
    }

    protected Youtube(Parcel in) {
//        this.desc = in.readString();
//        this.date = in.readString();
//        this.name = in.readString();
//        this.video = in.readString();
        this.id = in.readParcelable(Id.class.getClassLoader());
//        this.desk = in.readString();
        this.snippet = in.readParcelable(Snippet.class.getClassLoader());
    }

//    public int getId() {
//        return id;
//    }

//    public float getVote() {
//        return voteAvg;
//    }

//    public String getName() {
//        return name;
//    }

//    public String getVideo() {
//        return video;
//    }

    public Id getId() {
        return id;
    }

//    public String getDesk() {
//        return desk;
//    }

    public Snippet getSnippet() {
        return snippet;
    }
//    public String getDate() {
//        return date;
//    }

//    public String getDesc() {
//        return desc;
//    }

//    public String getImg() {
//        return img;
//    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
//        dest.writeString(this.desc);
//        dest.writeString(this.date);
//        dest.writeString(this.name);
//        dest.writeString(this.desk);
        dest.writeParcelable(id, flags);
//        dest.writeString(this.video);
        dest.writeParcelable(snippet, flags);
//        dest.writeString(this.img);
//        dest.writeInt(this.id);
//        dest.writeFloat(this.voteAvg);
    }
}
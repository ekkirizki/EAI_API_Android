package com.kikake.api_java.retrofit.Youtube;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Thumbnails implements Parcelable {

    @SerializedName("high")
    private High_Thumb high_thumb;

    public High_Thumb getHigh_thumb() {
        return high_thumb;
    }

    public Medium_Thumb getMedium_thumb() {
        return medium_thumb;
    }

    public Default_Thumb getDefault_thumb() {
        return default_thumb;
    }

    @SerializedName("medium")
    private Medium_Thumb medium_thumb;

    @SerializedName("default")
    private Default_Thumb default_thumb;

    protected Thumbnails(Parcel in) {
        high_thumb = in.readParcelable(High_Thumb.class.getClassLoader());
        medium_thumb = in.readParcelable(Medium_Thumb.class.getClassLoader());
        default_thumb = in.readParcelable(Default_Thumb.class.getClassLoader());
    }

    public static final Creator<Thumbnails> CREATOR = new Creator<Thumbnails>() {
        @Override
        public Thumbnails createFromParcel(Parcel in) {
            return new Thumbnails(in);
        }

        @Override
        public Thumbnails[] newArray(int size) {
            return new Thumbnails[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(high_thumb, flags);
        dest.writeParcelable(medium_thumb, flags);
        dest.writeParcelable(default_thumb, flags);
    }
}

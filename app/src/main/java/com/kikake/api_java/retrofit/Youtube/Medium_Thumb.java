package com.kikake.api_java.retrofit.Youtube;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Medium_Thumb implements Parcelable {
    @SerializedName("url")
    private String url;

    @SerializedName("width")
    private String width;

    @SerializedName("height")
    private String height;

    public Medium_Thumb(String url, String width, String height) {
        this.url = url;
        this.width = width;
        this.height = height;
    }

    protected Medium_Thumb(Parcel in) {
        url = in.readString();
        width = in.readString();
        height = in.readString();
    }

    public static final Parcelable.Creator<Medium_Thumb> CREATOR = new Parcelable.Creator<Medium_Thumb>() {
        @Override
        public Medium_Thumb createFromParcel(Parcel in) {
            return new Medium_Thumb(in);
        }

        @Override
        public Medium_Thumb[] newArray(int size) {
            return new Medium_Thumb[size];
        }
    };

    public String getUrl() {
        return url;
    }

    public String getWidth() {
        return width;
    }

    public String getHeight() {
        return height;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(url);
        dest.writeString(width);
        dest.writeString(height);
    }
}

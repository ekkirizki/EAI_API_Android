package com.kikake.api_java.retrofit.Youtube;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Snippet implements Parcelable {
    @SerializedName("description")
    private String desk;

    @SerializedName("title")
    private String judul;

    @SerializedName("thumbnail")
    private Thumbnails thumbnails;

    protected Snippet(Parcel in) {
        desk = in.readString();
        judul = in.readString();
        thumbnails = in.readParcelable(Thumbnails.class.getClassLoader());
    }

    public static final Creator<Snippet> CREATOR = new Creator<Snippet>() {
        @Override
        public Snippet createFromParcel(Parcel in) {
            return new Snippet(in);
        }

        @Override
        public Snippet[] newArray(int size) {
            return new Snippet[size];
        }
    };

    /**
     * Describe the kinds of special objects contained in this Parcelable
     * instance's marshaled representation. For example, if the object will
     * include a file descriptor in the output of {@link #writeToParcel(Parcel, int)},
     * the return value of this method must include the
     * {@link #CONTENTS_FILE_DESCRIPTOR} bit.
     *
     * @return a bitmask indicating the set of special object types marshaled
     * by this Parcelable object instance.
     */
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(desk);
        dest.writeString(judul);
        dest.writeParcelable(thumbnails, flags);
    }

    public String getDesk() {
        return desk;
    }

    public String getJudul() {
        return judul;
    }

    public Thumbnails getThumbnails() {
        return thumbnails;
    }
}

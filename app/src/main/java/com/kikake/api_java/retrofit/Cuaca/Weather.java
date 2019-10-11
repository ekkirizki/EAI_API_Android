package com.kikake.api_java.retrofit.Cuaca;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Weather implements Parcelable {
    @SerializedName("main")
    private String Main;

    @SerializedName("description")
    private String desk;

    @SerializedName("icon")
    private String Icon;

    public Weather(String main, String Desk, String icon){
        this.desk = Desk;
        this.Icon = icon;
        this.Main = main;
    }

    protected Weather(Parcel in) {
        Main = in.readString();
        desk = in.readString();
        Icon = in.readString();
    }

    public static final Creator<Weather> CREATOR = new Creator<Weather>() {
        @Override
        public Weather createFromParcel(Parcel in) {
            return new Weather(in);
        }

        @Override
        public Weather[] newArray(int size) {
            return new Weather[size];
        }
    };

    public String getDesk(){ return desk; }
    public String getIcon(){ return Icon; }
    public String getMain(){ return Main; }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(Main);
        dest.writeString(desk);
        dest.writeString(Icon);
    }
}

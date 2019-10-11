package com.kikake.api_java.retrofit.Cuaca;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Data_Cuaca implements Parcelable {

    @SerializedName("main")
    private Main main;

    @SerializedName("weather")
    private List<Weather> weather;

    @SerializedName("dt_txt")
    private String waktu;

    public static final Creator<Data_Cuaca> CREATOR = new Creator<Data_Cuaca>() {
        @Override
        public Data_Cuaca createFromParcel(Parcel in) {
            return new Data_Cuaca(in);
        }

        @Override
        public Data_Cuaca[] newArray(int size) {
            return new Data_Cuaca[size];
        }
    };

    public Data_Cuaca(Parcel in) {
        weather = new ArrayList<Weather>();
        main = in.readParcelable(Main.class.getClassLoader());
        in.readList(weather, Weather.class.getClassLoader());
        waktu = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public Main getMain() {
        return main;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public String getWaktu() {
        return waktu;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(weather);
        dest.writeParcelable(main, flags);
        dest.writeString(waktu);
    }
}

package com.kikake.api_java.retrofit.Cuaca;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Api_Cuaca {
    private static Retrofit retrofit;
    public static String BASE_URL = "http://api.openweathermap.org/data/2.5/";

    public static Retrofit getApiData(){
        if(retrofit == null){
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}

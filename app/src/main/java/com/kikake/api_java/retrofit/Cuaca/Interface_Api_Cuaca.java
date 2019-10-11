package com.kikake.api_java.retrofit.Cuaca;

import retrofit2.http.GET;
import retrofit2.Call;
import retrofit2.http.Query;

public interface Interface_Api_Cuaca {

    @GET("forecast")
    Call<Response_Cuaca> getCuaca(@Query("appid") String apiKey, @Query("units") String units, @Query("q") String city);
}

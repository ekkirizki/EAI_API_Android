package com.kikake.api_java.retrofit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("movie/")
    Call<ApiResponse> getMovies(@Query("api_key") String apiKey, @Query("language") String language);
}
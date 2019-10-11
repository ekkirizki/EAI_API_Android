package com.kikake.api_java.retrofit.Youtube;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterfaceYoutube {
    @GET("search")
    Call<ApiResponseYoutube> getYoutube(@Query("part") String part,
                                        @Query("channelId") String id,
                                        @Query("key") String key,
                                        @Query("maxResults") int max ,
                                        @Query("order") String order,
                                        @Query("type") String type);
}
package com.kikake.api_java.retrofit;


import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ApiResponse {

    @SerializedName("results")
    private List<Movie> results;

    public List<Movie> getResults() {
        return this.results;
    }
}
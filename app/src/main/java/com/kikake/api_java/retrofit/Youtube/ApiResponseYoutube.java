package com.kikake.api_java.retrofit.Youtube;


import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ApiResponseYoutube {

    @SerializedName("items")
    private List<Youtube> items;

    public List<Youtube> getResults() {
        return this.items;
    }
}
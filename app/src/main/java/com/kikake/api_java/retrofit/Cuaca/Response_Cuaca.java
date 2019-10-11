package com.kikake.api_java.retrofit.Cuaca;

import com.google.gson.annotations.SerializedName;
import com.kikake.api_java.retrofit.Cuaca.Data_Cuaca;

import java.util.ArrayList;

public class Response_Cuaca {

    @SerializedName("list")
    private ArrayList<Data_Cuaca> list;

    public ArrayList<Data_Cuaca> getList() {
        return this.list;
    }

}

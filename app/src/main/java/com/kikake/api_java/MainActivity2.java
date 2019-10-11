package com.kikake.api_java;


import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kikake.api_java.adapter.MainAdapter;
import com.kikake.api_java.retrofit.ApiClient;
import com.kikake.api_java.retrofit.ApiInterface;
import com.kikake.api_java.retrofit.ApiResponse;
import com.kikake.api_java.retrofit.Movie;

import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity2 extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private String API_KEY = "fd779f23434a798e13c0d6e760ee3075";
    private List<Movie> movies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        recyclerView = findViewById(R.id.rv_main);
        layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        showShowList();
    }

    private void showShowList() {
        Locale current = getResources().getConfiguration().locale;
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<ApiResponse> call = apiService.getMovies(API_KEY, current.toLanguageTag());
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                movies = response.body().getResults();
                recyclerView.setAdapter(new MainAdapter(movies, R.layout.item_row));
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                Log.e("retrofit error", t.toString());
            }
        });
    }
}

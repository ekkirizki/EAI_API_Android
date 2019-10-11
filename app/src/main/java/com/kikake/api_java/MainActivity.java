package com.kikake.api_java;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
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

public class MainActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    TextView FB;
    FirebaseUser mUser;
    private static final String TAG = "FacebookLogin";
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private String API_KEY = "fd779f23434a798e13c0d6e760ee3075";
    private List<Movie> movies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.rv_main);
        layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        showShowList();

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

//        FB = findViewById(R.id.facebook);
//        FB.setText(mUser.getDisplayName());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu1 , menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.tbl_akun:
                setTitle(mUser.getDisplayName());
                return true;
            case R.id.tbl_keluar:
                logout();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void Logout(View view) {
        mAuth.signOut();
        LoginManager.getInstance().logOut();
        startActivity(new Intent(MainActivity.this, Login.class));
        finish();
    }

    public void logout(){
        mAuth.signOut();
        LoginManager.getInstance().logOut();
        startActivity(new Intent(MainActivity.this, Login.class));
        finish();
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

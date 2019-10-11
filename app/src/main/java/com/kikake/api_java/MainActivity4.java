package com.kikake.api_java;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.login.LoginManager;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.protobuf.ApiProto;
import com.kikake.api_java.adapter.Youtube_Adapter;
import com.kikake.api_java.retrofit.ApiResponse;
import com.kikake.api_java.retrofit.Youtube.ApiClientYoutube;
import com.kikake.api_java.retrofit.Youtube.ApiInterfaceYoutube;
import com.kikake.api_java.retrofit.Youtube.ApiResponseYoutube;
import com.kikake.api_java.retrofit.Youtube.Youtube;

import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity4 extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private String key = "AIzaSyDM3-iAZC6kWoFHgW5RVtgKKlr2XKKHS9c";
    private List<Youtube> Youtubes;
    private int max = 10;
    private String part = "snippet";
    private String channel = "UCwA5IPY5-Gfj1ACDbwurCNA";
    private String type = "video";
    private String order = "date";

    FirebaseUser mUser;
    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        recyclerView = findViewById(R.id.rv_main4);
        layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        showShowList();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private void showShowList() {
        ApiInterfaceYoutube apiService = ApiClientYoutube.getClient().create(ApiInterfaceYoutube.class);
        Call<ApiResponseYoutube> call = apiService.getYoutube(part, channel, key, max, order, type);
        call.enqueue(new Callback<ApiResponseYoutube>() {
            @Override
            public void onResponse(Call<ApiResponseYoutube> call, Response<ApiResponseYoutube> response) {
                Youtubes = response.body().getResults();
                recyclerView.setAdapter(new Youtube_Adapter(Youtubes, R.layout.youtube));
            }

            @Override
            public void onFailure(Call<ApiResponseYoutube> call, Throwable t) {
                Log.e("retrofit error", t.toString());
            }
        });
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
    public void logout(){
        mAuth.signOut();
        LoginManager.getInstance().logOut();
        startActivity(new Intent(this, Login.class));
        finish();
    }
}

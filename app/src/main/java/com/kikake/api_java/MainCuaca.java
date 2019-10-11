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
import com.kikake.api_java.adapter.Cuaca_Adapter;
import com.kikake.api_java.adapter.MainAdapter;
import com.kikake.api_java.retrofit.ApiClient;
import com.kikake.api_java.retrofit.ApiInterface;
import com.kikake.api_java.retrofit.ApiResponse;
import com.kikake.api_java.retrofit.Movie;
import com.kikake.api_java.retrofit.Cuaca.*;

import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainCuaca extends AppCompatActivity {

    FirebaseAuth mAuth;
    TextView FB;
    FirebaseUser mUser;
    private static final String TAG = "FacebookLogin";
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    String Key = "33bc5f242724a513b19764d71eb9094d";
    String q = "Jakarta";
    String units = "metric";
    private List<Data_Cuaca> Cuaca;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_cuaca);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.rv_main1);
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
        startActivity(new Intent(this, Login.class));
        finish();
    }

    public void logout(){
        mAuth.signOut();
        LoginManager.getInstance().logOut();
        startActivity(new Intent(this, Login.class));
        finish();
    }

    private void showShowList() {
        Locale current = getResources().getConfiguration().locale;
        Interface_Api_Cuaca apiService = Api_Cuaca.getApiData().create(Interface_Api_Cuaca.class);
        Call<Response_Cuaca> call = apiService.getCuaca(Key, units, q);
        call.enqueue(new Callback<Response_Cuaca>() {
            @Override
            public void onResponse(Call<Response_Cuaca> call, Response<Response_Cuaca> response) {
               Cuaca = response.body().getList();
                recyclerView.setAdapter(new Cuaca_Adapter(Cuaca, R.layout.layout_cuaca));
            }

            @Override
            public void onFailure(Call<Response_Cuaca> call, Throwable t) {
                Log.e("retrofit error", t.toString());
            }
        });
    }
}

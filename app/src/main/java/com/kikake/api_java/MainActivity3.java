package com.kikake.api_java;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity3 extends AppCompatActivity {

    FirebaseAuth mAuth;
    FirebaseUser mUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
    }

    public void Film(View view) {
        startActivity(new Intent(this, MainActivity.class));
    }

    public void Cuaca(View view) {
        startActivity(new Intent(this, MainCuaca.class));
    }

    public void Youtube(View view) {
        startActivity(new Intent(this, MainActivity4.class));
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

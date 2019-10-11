package com.kikake.api_java;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.ProgressBar;

public class SplashScreen extends AppCompatActivity {
ProgressBar progressBar;
int counter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //init();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layar_depan);
//        getSupportActionBar().hide();
        progressBar = findViewById(R.id.progressBar);
        counter = 0;
        new CountDownTimer(3000,100){
            @Override
            public void onTick(long millisUntilFinished) {
                counter++;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    progressBar.setProgress(counter*100/(3000/100),true);
                }else{
                    progressBar.setProgress(counter*100/(3000/100));
                }
            }

            @Override
            public void onFinish() {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    progressBar.setProgress(100,true);
                }else{
                    progressBar.setProgress(100);
                }
                startActivity(new Intent(SplashScreen.this, Login.class));
                finish();
            }
        }.start();
    }

    /* private void init(){
        SharedPreferences prefs = getSharedPreferences(getPackageName(), MODE_PRIVATE);
        if (prefs.getBoolean("nightMode", false)) {
            setTheme(R.style.dark);
        } else {
            setTheme(R.style.light);
        }
    }*/
}

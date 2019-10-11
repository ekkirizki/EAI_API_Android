package com.kikake.api_java;

import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.kikake.api_java.retrofit.Movie;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DetailActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        showMovieData();
    }

    private void actionBar(String title) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setElevation(0);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setTitle(title);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void showMovieData() {
        ImageView image = findViewById(R.id.img_item_detail);
        TextView title = findViewById(R.id.tv_detail_title);
        TextView date = findViewById(R.id.tv_detail_date);
        TextView desc = findViewById(R.id.tv_detail_desc);
        Movie data = getIntent().getParcelableExtra("data");
        String imagePath = "https://image.tmdb.org/t/p/original" + data.getImg();
        String mtitle = data.getName();
        String mdate = data.getDate();
        String mdesc = data.getDesc();

        title.setText(mtitle);

        Log.v("image", imagePath);
        Picasso.get().load(imagePath).into(image);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate;
        try {
            Date dateRelease = sdf.parse(mdate);
            formattedDate = DateFormat.format("dd MMM yyyy", dateRelease).toString();
        } catch (ParseException e) {
            e.printStackTrace();
            formattedDate = mdate;
        }
        date.setText(formattedDate);
        desc.setText(mdesc);
        actionBar(mtitle);
    }
}

package com.kikake.api_java;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.kikake.api_java.retrofit.Movie;
import com.kikake.api_java.retrofit.Youtube.Youtube;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DetailYoutube extends YouTubeBaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_youtube);
        Toolbar toolbar = findViewById(R.id.toolbar);
        showMovieData();
    }

    private void showMovieData() {
        YouTubePlayerView Yt = findViewById(R.id.PlayerDetail);
        Youtube datas = getIntent().getParcelableExtra("data");
        String key = "AIzaSyDM3-iAZC6kWoFHgW5RVtgKKlr2XKKHS9c";
        final String video = datas.getId().getId();
        Yt.initialize(key, new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {

                //Enables automatic control of orientation
                youTubePlayer.setFullscreenControlFlags(YouTubePlayer.FULLSCREEN_FLAG_CONTROL_ORIENTATION);

                //Show full screen in landscape mode always
                youTubePlayer.addFullscreenControlFlag(YouTubePlayer.FULLSCREEN_FLAG_ALWAYS_FULLSCREEN_IN_LANDSCAPE);

                //System controls will appear automatically
                youTubePlayer.addFullscreenControlFlag(YouTubePlayer.FULLSCREEN_FLAG_CONTROL_SYSTEM_UI);

                if (!b) {
                    youTubePlayer.cueVideo(video);
                    //youTubePlayer.loadVideo(video);
                }
                else
                {
                    youTubePlayer.play();
                }
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        });
    }
}

package com.kikake.api_java.adapter;

import android.app.Activity;
import android.app.WallpaperInfo;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.common.api.internal.LifecycleCallback;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerFragment;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;
import com.google.android.youtube.player.YouTubePlayerView;
import com.google.android.youtube.player.YouTubeThumbnailLoader;
import com.google.android.youtube.player.YouTubeThumbnailView;
import com.kikake.api_java.DetailActivity;
import com.kikake.api_java.DetailYoutube;
import com.kikake.api_java.MainActivity4;
import com.kikake.api_java.R;
import com.kikake.api_java.retrofit.Youtube.Youtube;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Youtube_Adapter extends RecyclerView.Adapter<Youtube_Adapter.CardViewHolder> {
    private List<Youtube> youtubes;
    private int rowLayout;
    private YouTubePlayerFragment playerFragment;
    private YouTubePlayer mPlayer;
    private Context context;
    private FragmentManager fragmentManager;


    public Youtube_Adapter(List<Youtube> youtubes, int rowLayout){
//            , FragmentManager fragmentManager) {
        this.youtubes = youtubes;
        this.rowLayout = rowLayout;
//        this.fragmentManager = fragmentManager;
    }


    @NonNull
    @Override
    public Youtube_Adapter.CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new CardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CardViewHolder holder, final int position) {
        String key = "AIzaSyDM3-iAZC6kWoFHgW5RVtgKKlr2XKKHS9c";
        final String video_string = youtubes.get(position).getSnippet().getJudul();
        final String video = youtubes.get(position).getId().getId();
//        final String thmb = youtubes.get(position).getSnippet().getThumbnails().getHigh_thumb().getUrl();
        final String deskripsi = youtubes.get(position).getSnippet().getDesk();

        holder.Thmb.initialize(key, new YouTubeThumbnailView.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubeThumbnailView youTubeThumbnailView, final YouTubeThumbnailLoader youTubeThumbnailLoader) {
                youTubeThumbnailLoader.setVideo(video);
                youTubeThumbnailLoader.setOnThumbnailLoadedListener(new YouTubeThumbnailLoader.OnThumbnailLoadedListener() {
                    @Override
                    public void onThumbnailLoaded(YouTubeThumbnailView youTubeThumbnailView, String s) {
                        youTubeThumbnailLoader.release();
                        youTubeThumbnailView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Youtube youtube = new Youtube(youtubes.get(position).getId(), youtubes.get(position).getSnippet());
                                Intent detail = new Intent(v.getContext(), DetailYoutube.class);
                                detail.putExtra("data", youtube);
                                v.getContext().startActivity(detail);
                            }
                        });
                    }

                    @Override
                    public void onThumbnailError(YouTubeThumbnailView youTubeThumbnailView, YouTubeThumbnailLoader.ErrorReason errorReason) {

                    }
                });
            }

            @Override
            public void onInitializationFailure(YouTubeThumbnailView youTubeThumbnailView, YouTubeInitializationResult youTubeInitializationResult) {

            }
        });

        holder.deskripsi.setText(deskripsi);

//        holder.Ytp.initialize(key, new YouTubePlayer.OnInitializedListener() {
//            @Override
//            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
//
//                //Enables automatic control of orientation
//                youTubePlayer.setFullscreenControlFlags(YouTubePlayer.FULLSCREEN_FLAG_CONTROL_ORIENTATION);
//
//                //Show full screen in landscape mode always
//                youTubePlayer.addFullscreenControlFlag(YouTubePlayer.FULLSCREEN_FLAG_ALWAYS_FULLSCREEN_IN_LANDSCAPE);
//
//                //System controls will appear automatically
//                youTubePlayer.addFullscreenControlFlag(YouTubePlayer.FULLSCREEN_FLAG_CONTROL_SYSTEM_UI);
//
//                if (!b) {
//                    youTubePlayer.cueVideo(video);
////                    mPlayer.loadVideo(video);
////                mPlayer.loadVideo(video);
//                }
//                else
//                {
//                    youTubePlayer.play();
//                }
//            }
//
//            @Override
//            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
//                Toast.makeText(context.getApplicationContext(),"Failed to load video", Toast.LENGTH_SHORT).show();
//            }
//        });

//        holder.youTubePlayerView.initialize(key, new YouTubePlayer.OnInitializedListener() {
//            @Override
//            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
//                if (!b) {
//                    youTubePlayer.cueVideo(video_string);
//                }
//            }
//
//            @Override
//            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
//                Toast.makeText(context.getApplicationContext(),"Failed to load video", Toast.LENGTH_SHORT).show();
//            }
//        });



//        final Youtube youtube = youtubes.get(position);
//        Picasso.get().load("https://image.tmdb.org/t/p/original" + youtube.getImg()).fit().into(holder.imgPhoto);
//        holder.tvName.setText(youtube.getName());
//        SimpleDateFormat month_date = new SimpleDateFormat("dd MMMM yyyy", Locale.ENGLISH);
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//
//        String actualDate = youtube.getDate();
//
//        Date date = null;
//        try {
//            date = sdf.parse(actualDate);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//
//        String full_date = month_date.format(date);
//
//        holder.tvDate.setText(full_date);
//        holder.tvDesc.setText(youtube.getDesc());
//        holder.tvVote.setText(String.valueOf(youtube.getVote()));
//        holder.btnMore.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Youtube youtube = new Youtube(youtubes.get(position).getName(), youtubes.get(position).getVideo(), youtubes.get(position).getVideo());
//                Intent detail = new Intent(view.getContext(), DetailActivity.class);
//                detail.putExtra("data", youtube);
//                view.getContext().startActivity(detail);
//            }
//        });
    }


    @Override
    public int getItemCount() {
        return youtubes.size();
    }

//    @Override
//    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b, int position) {
//        boolean wasRestored) {
//            mPlayer = youTubePlayer;
//
//            //Enables automatic control of orientation
//            mPlayer.setFullscreenControlFlags(YouTubePlayer.FULLSCREEN_FLAG_CONTROL_ORIENTATION);
//
//            //Show full screen in landscape mode always
//            mPlayer.addFullscreenControlFlag(YouTubePlayer.FULLSCREEN_FLAG_ALWAYS_FULLSCREEN_IN_LANDSCAPE);
//
//            //System controls will appear automatically
//            mPlayer.addFullscreenControlFlag(YouTubePlayer.FULLSCREEN_FLAG_CONTROL_SYSTEM_UI);
//
//            if (!wasRestored) {
//                //player.cueVideo("9rLZYyMbJic");
//                mPlayer.loadVideo(youtubes.get(position).getVideo());
//            }
//            else
//            {
//                mPlayer.play();
//            }
//        }
//    }
//
//    @Override
//    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
//        mPlayer = null;
//    }


    public class CardViewHolder extends RecyclerView.ViewHolder {
//        private YouTubePlayerFragment Yt;
//        private FrameLayout frame;
//        private YouTubePlayerView Ytp;
        private YouTubeThumbnailView Thmb;
        private TextView deskripsi;

        CardViewHolder(View itemView) {
            super(itemView);

            Thmb = itemView.findViewById(R.id.Thumbnail);
            deskripsi = itemView.findViewById(R.id.deskripsi);
//            Ytp = itemView.findViewById(R.id.Player);

//            frame = itemView.findViewById(R.id.frame);
//            Yt = itemView.findViewById(R.id.player);
//            YTp = itemView.findViewById(R.id.YTP);

//            Yt = new YouTubePlayerFragment();
//            Yt.getFragmentManager().findFragmentById(R.id.player);
//            Yt = ((YouTubePlayerFragment) manager).getFragmentManager().findFragmentById(R.id.player);
//            Yt = (YouTubePlayerFragment) manager.getFragment();
//            Yt = fragmentManager.findFragmentById(R.id.player);

//            Yt.newInstance();

        }
}
}
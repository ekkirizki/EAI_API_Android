package com.kikake.api_java.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kikake.api_java.DetailActivity;
import com.kikake.api_java.R;
import com.kikake.api_java.retrofit.Cuaca.Data_Cuaca;
import com.kikake.api_java.retrofit.Movie;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.CardViewHolder> {
    private List<Movie> movies;
    private int rowLayout;

    public MainAdapter(List<Movie> movies, int rowLayout) {
        this.movies = movies;
        this.rowLayout = rowLayout;
    }


    @NonNull
    @Override
    public MainAdapter.CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new CardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CardViewHolder holder, final int position) {

        final Movie movie = movies.get(position);
        Picasso.get().load("https://image.tmdb.org/t/p/original" + movie.getImg()).fit().into(holder.imgPhoto);
        holder.tvName.setText(movie.getName());
        SimpleDateFormat month_date = new SimpleDateFormat("dd MMMM yyyy", Locale.ENGLISH);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        String actualDate = movie.getDate();

        Date date = null;
        try {
            date = sdf.parse(actualDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        String full_date = month_date.format(date);

        holder.tvDate.setText(full_date);
        holder.tvDesc.setText(movie.getDesc());
        holder.tvVote.setText(String.valueOf(movie.getVote()));
        holder.btnMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Movie movie = new Movie(movies.get(position).getId(), movies.get(position).getName(), movies.get(position).getDesc(), movies.get(position).getDate(), movies.get(position).getImg(), movies.get(position).getVote());
                Intent detail = new Intent(view.getContext(), DetailActivity.class);
                detail.putExtra("data", movie);
                view.getContext().startActivity(detail);
            }
        });

    }


    @Override
    public int getItemCount() {
        return movies.size();
    }


    public class CardViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPhoto;
        TextView tvName, tvDate, tvDesc;
        Button btnMore;
        TextView tvVote;

        CardViewHolder(View itemView) {
            super(itemView);
            imgPhoto = itemView.findViewById(R.id.img_item_photo);
            tvName = itemView.findViewById(R.id.tv_item_name);
            tvDate = itemView.findViewById(R.id.tv_item_date);
            tvDesc = itemView.findViewById(R.id.tv_item_desc);
            tvVote = itemView.findViewById(R.id.tv_rating);
            btnMore = itemView.findViewById(R.id.btn_more);
        }

    }

}
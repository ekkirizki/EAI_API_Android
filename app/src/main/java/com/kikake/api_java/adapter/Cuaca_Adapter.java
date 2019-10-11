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

public class Cuaca_Adapter extends RecyclerView.Adapter<Cuaca_Adapter.CardViewHolder> {
    private List<Data_Cuaca> cuaca1;
    private int rowLayout;

    public Cuaca_Adapter(List<Data_Cuaca> cuaca1, int rowLayout) {
        this.cuaca1 = cuaca1;
        this.rowLayout = rowLayout;
    }


    @NonNull
    @Override
    public Cuaca_Adapter.CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new CardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CardViewHolder holder, final int position) {

        final double derajat = cuaca1.get(position).getMain().getTemp();
        final String Icon = cuaca1.get(position).getWeather().get(0).getIcon();
        final String Deskripsi = cuaca1.get(position).getWeather().get(0).getDesk();
        final String status = cuaca1.get(position).getWeather().get(0).getMain();
        final String waktu = cuaca1.get(position).getWaktu();

        final int derajatin = (int) derajat;

        String deratan = ""+derajatin;

        Picasso.get().load("http://openweathermap.org/img/wn/" + Icon + "@2x.png").fit().into(holder.Icon);
//                "@2x.png").fit().into(holder.Icon);
        holder.Kota.setText("Bandung");

        holder.Suhu.setText(deratan);
        holder.Tanggal.setText(waktu);
    }

    @Override
    public int getItemCount() {
        return cuaca1.size();
    }


    public class CardViewHolder extends RecyclerView.ViewHolder {
        ImageView Icon;
        TextView Kota, Tanggal, Suhu;

        CardViewHolder(View itemView) {
            super(itemView);
            Icon = itemView.findViewById(R.id.IconCuaca);
            Kota = itemView.findViewById(R.id.Kota);
            Tanggal = itemView.findViewById(R.id.Tanggal);
            Suhu = itemView.findViewById(R.id.Temp);
        }

    }

}
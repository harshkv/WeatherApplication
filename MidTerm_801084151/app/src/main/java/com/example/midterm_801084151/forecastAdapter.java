package com.example.midterm_801084151;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import org.ocpsoft.prettytime.PrettyTime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static android.view.LayoutInflater.*;

public class forecastAdapter extends RecyclerView.Adapter<forecastAdapter.viewHolder> {
    ArrayList<weatherForcast> mdata;
    public forecastAdapter(ArrayList<weatherForcast> mdata) {
        this.mdata = mdata;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = from(parent.getContext())
                .inflate(R.layout.each_item, parent, false);
        viewHolder viewHolder = new viewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        weatherForcast weather = mdata.get(position);
        holder.tv_settempf.setText("Temperature " +weather.getTempf());
        holder.tv_sethumif.setText("Humidity "+weather.getHumidityf());
        holder.tv_descf.setText(weather.getDescf());

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date convertedDate = null;
        try {
            convertedDate = dateFormat.parse(weather.datef);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        PrettyTime p  = new PrettyTime();
        long dateinmilli= convertedDate.getTime();
        holder.tv_timef.setText( p.format(new Date(dateinmilli)));

        String url = "http://openweathermap.org/img/wn/" + weather.imageIconf + "@2x.png";
        Picasso.get().load(url).resize(300, 300).centerCrop().into((holder.iv_icon));








}

    @Override
    public int getItemCount() {
        return mdata.size();
    }

    public static class viewHolder extends RecyclerView.ViewHolder{
        TextView tv_timef, tv_settempf, tv_sethumif,tv_descf,tv_time;
        ImageView iv_icon;



        public viewHolder(@NonNull View itemView) {
            super(itemView);
            tv_timef = itemView.findViewById(R.id.tv_time);
            tv_settempf = itemView.findViewById(R.id.tv_settemp);
            tv_sethumif = itemView.findViewById(R.id.tv_sethumi);
            tv_descf = itemView.findViewById(R.id.tv_desc);
            tv_timef = itemView.findViewById(R.id.tv_time);
            iv_icon = itemView.findViewById(R.id.iv_icon);


        }
    }
}

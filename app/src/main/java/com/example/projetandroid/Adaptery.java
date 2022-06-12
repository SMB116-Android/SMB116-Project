package com.example.projetandroid;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.projetandroid.DB.Film.Film;
import com.example.projetandroid.Fragments.DetailsFragment;

import java.util.ArrayList;
import java.util.List;

public class Adaptery extends RecyclerView.Adapter<Adaptery.MyViewHolder> {
    private Context mContext;
    private List<Film> mData;
    private final List<Film> mDataFilm;
    private OnFilmListener mCommunicator;

    public Adaptery(Context mContext, List<Film> mData, OnFilmListener communication) {
        this.mContext = mContext;
        this.mData = mData;
        mCommunicator=communication;
        mDataFilm = mData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        v = inflater.inflate(R.layout.movie_item, parent, false);

        return new MyViewHolder(v, mCommunicator);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.id.setText(mData.get(position).getId());
        Log.i("AUBO", holder.name.toString());
        holder.name.setText(mData.get(position).getName());
        holder.date.setText(mData.get(position).getDate());
        holder.overview.setText(mData.get(position).getShortOverview());
        holder.ratingBar.setRating(mData.get(position).getVoteAverage());

        // Using Glide to display the image
        RequestOptions requestOptions = new RequestOptions();
        requestOptions = requestOptions.transforms(new CenterCrop(), new RoundedCorners(20));
        Glide.with(mContext).load("https://image.tmdb.org/t/p/w500"+mData.get(position).getImg()).apply(requestOptions).into(holder.img);
        DetailsFragment fragmentB=new DetailsFragment();
        Bundle bundle=new Bundle();
        bundle.putString("ID",holder.id.getText().toString());
        fragmentB.setArguments(bundle);
        //holder.context.startActivity(i);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    private void filterList(ArrayList<Film> filterllist) {
        mData = filterllist;
        notifyDataSetChanged();
    }

    public void filter(String text) {
        ArrayList<Film> filteredlist = new ArrayList<>();
        mData = mDataFilm;

            for (Film item : mData) {
                if (item.getName().toLowerCase().contains(text.toLowerCase())) {
                    filteredlist.add(item);
                }
            }
            if (filteredlist.isEmpty()) {
            } else {
                filterList(filteredlist);
            }


    }


    public class MyViewHolder extends RecyclerView.ViewHolder implements RecyclerView.OnClickListener{
        TextView id;
        TextView name;
        ImageView img;
        TextView date;
        TextView overview;
        RatingBar ratingBar;
        OnFilmListener mOnFilmListener;

        private Context context;

        public MyViewHolder(@NonNull View itemView, OnFilmListener Communicator) {
            super(itemView);
            id = itemView.findViewById(R.id.id_txt);
            name = itemView.findViewById(R.id.tvName);
            img = itemView.findViewById(R.id.imageView);
            date = itemView.findViewById(R.id.tvDate);
            overview = itemView.findViewById(R.id.tvOverview);
            ratingBar = itemView.findViewById(R.id.ratingBar);
            context = itemView.getContext();
            mOnFilmListener=Communicator;
            itemView.setClickable(true);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            mOnFilmListener.onFilmClick(getAdapterPosition(),mData.get(getAdapterPosition()).getId());
        }
    }

    public interface OnFilmListener{
        void onFilmClick(int position, String id);
    }
}

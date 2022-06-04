package com.example.projetandroid.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.projetandroid.DB.Film.Film;
import com.example.projetandroid.DB.User.User;
import com.example.projetandroid.R;

import java.util.List;


public class TrendingFragment extends RecyclerView.Adapter<TrendingFragment.WordViewHolder> {

    List<Film> filmsList;

   public TrendingFragment(List<Film> films) {
            this.filmsList = films;
   }

   @NonNull
   @Override
   public WordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_trending, parent, false);
       return new WordViewHolder(itemView);
   }

   @Override
   public void onBindViewHolder(@NonNull WordViewHolder holder, int position) {
       // Retrieve the correct data for that position
       Film data = filmsList.get(position);
       // Add the data to the view
       holder.bind(data);
   }

   @Override
   public int getItemCount() {
       // Return the number of data items to display
       return filmsList.size();
   }

   class UserViewHolder extends RecyclerView.ViewHolder {

       TextView filmTextView;

       public WordViewHolder(@NonNull View itemView) {
           super(itemView);
           filmTextView = itemView.findViewById(R.id.textViewTrending);
       }

       public void bind(Film data) {
           filmTextView.setText(data.get);
       }
   }

}
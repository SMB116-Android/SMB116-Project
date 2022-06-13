package com.example.projetandroid.Fragments;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.projetandroid.Adaptery;
import com.example.projetandroid.AdapteryUserFilm;
import com.example.projetandroid.DB.Film.Film;
import com.example.projetandroid.R;
import com.example.projetandroid.ViewModel.UserViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class FilmFragment extends Fragment {

    FloatingActionButton delete;
    View view;
    List<Film> movieList;
    RecyclerView recyclerView;
    private AdapteryUserFilm adaptery;
    private UserViewModel userViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_film, container, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().show();
        setHasOptionsMenu(true);
        this.userViewModel = UserViewModel.getInstance();

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        movieList = new ArrayList<>();
        recyclerView = view.findViewById(R.id.recyclerViewUserFilm);
        delete = view.findViewById(R.id.floatingDeleteButton);
        int idUser = userViewModel.getCurrentUser().get_id();
        getAllUserFilm(userViewModel.getAllUserFilms(idUser));

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        inflater.inflate(R.menu.search_menu, menu);
        super.onCreateOptionsMenu(menu,inflater);

        MenuItem searchItem = menu.findItem(R.id.actionSearch);

        androidx.appcompat.widget.SearchView searchView = (androidx.appcompat.widget.SearchView) searchItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filter(newText);
                return false;
            }
        });

    }

    private void filter(String text) {
        adaptery.filter(text);
    }

    private void getAllUserFilm(List film){
        for(int i = 0; i < film.size(); i++){
            Film model = new Film();
            model = (Film) film.get(i);
            movieList.add(model);
        }

        PutDataIntoRecyclerView(movieList);
    }

    private void PutDataIntoRecyclerView(List<Film> movieList){

        adaptery = new AdapteryUserFilm(view.getContext(), movieList, communication, communicationDelete);

        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        recyclerView.setAdapter(adaptery);
    }

    AdapteryUserFilm.OnFilmListener communication = new AdapteryUserFilm.OnFilmListener() {
        @Override
        public void onFilmClick(int position, String id) {
            DetailsFragment fragmentB=new DetailsFragment();
            Bundle bundle=new Bundle();
            bundle.putString("ID",id);
            fragmentB.setArguments(bundle);
            FragmentManager manager = getFragmentManager();
            FragmentTransaction transaction=manager.beginTransaction();
            transaction.replace(R.id.container,fragmentB).commit();
        }
    };

    AdapteryUserFilm.OnDeleteFilmListener communicationDelete = new AdapteryUserFilm.OnDeleteFilmListener() {
        @Override
        public void onDeleteFilmClick(int position, Film film) {

        }
    };


}

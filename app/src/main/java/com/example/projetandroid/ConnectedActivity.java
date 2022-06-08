package com.example.projetandroid;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.example.projetandroid.DB.Film.Film;
import com.example.projetandroid.DB.Film.FilmRepository;
import com.example.projetandroid.Fragments.AccountFragment;
import com.example.projetandroid.Fragments.FilmFragment;
import com.example.projetandroid.Fragments.TrendingFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

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

public class ConnectedActivity extends AppCompatActivity {
    private static final String TAG = "ConnectedActivity";
    BottomNavigationView bottomNavigationView;

    TrendingFragment trendingFragment = new TrendingFragment();
    FilmFragment filmFragment = new FilmFragment();
    AccountFragment accountFragment = new AccountFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connected);

        bottomNavigationView = findViewById(R.id.bottom_navigation);

        getSupportFragmentManager().beginTransaction().replace(R.id.container, trendingFragment).commit();

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.trending:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, trendingFragment).commit();
                        return true;
                    case R.id.movie:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, filmFragment).commit();
                        return true;
                    case R.id.manageAccount:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, accountFragment).commit();
                        return true;
                }
                return false;
            }
        });

    }

}
package com.example.projetandroid;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
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

import java.util.List;

public class ConnectedActivity extends AppCompatActivity {

    Repository repository = null;
    private static final String TAG = "ConnectedActivity";
    private ConnectivityManager connectivityManager = null;
    BottomNavigationView bottomNavigationView;

    TrendingFragment trendingFragment = new TrendingFragment();
    FilmFragment filmFragment = new FilmFragment();
    AccountFragment accountFragment = new AccountFragment();



    private void checkConnectionForActiveNetwork(@Nullable Network network) {
        if (network != null) {
            NetworkCapabilities capabilities = connectivityManager.getNetworkCapabilities(network);
            if (capabilities != null) {
                // 2' - Modifier le boolean `hasConnection` si nous avons du transport Wifi OU Cellular
                if (    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
                        capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)){
                }
            }
        }
    }

    private final ConnectivityManager.NetworkCallback networkCallback = new ConnectivityManager.NetworkCallback() {
        @Override
        public void onAvailable(@NonNull Network network) {
            super.onAvailable(network);
            checkConnectionForActiveNetwork(connectivityManager.getActiveNetwork());
        }

        @Override
        public void onLost(@NonNull Network network) {
            super.onLost(network);
            checkConnectionForActiveNetwork(connectivityManager.getActiveNetwork());
        }

        @Override
        public void onCapabilitiesChanged(@NonNull Network network, @NonNull NetworkCapabilities networkCapabilities) {
            super.onCapabilitiesChanged(network, networkCapabilities);
        }
    };

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
        repository = new Repository();

        connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        Network currentNetwork = connectivityManager.getActiveNetwork();
        connectivityManager.registerDefaultNetworkCallback(networkCallback);
        fetchFilmsList();


    }

    private void fetchFilmsList() {
        repository.getFilmList(new FilmListCallBack() {
            @Override
            public void onSuccess(List<Film> todoList) {
                Log.d(TAG, "onSuccess: " + todoList);
            }

            @Override
            public void onFailure(String errorMsg) {
                Log.e(TAG, "onFailure: " + errorMsg);
            }
        });
    }


}
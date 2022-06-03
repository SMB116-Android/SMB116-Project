package com.example.projetandroid;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class MainActivity<connectivityManager> extends AppCompatActivity {

    Repository repository = null;
    private static final String TAG = "MainActivity";
    private ConnectivityManager connectivityManager = null;


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
        setContentView(R.layout.activity_main);

        initializeBottomNavigationView();

        BadgeDrawable badge = bottomNavigationView.getOrCreateBadge(R.id.trending);
        badge.setVisible(true);

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

    private BottomNavigationView bottomNavigationView;
    private TextView textView;

    private void initializeBottomNavigationView() {
        bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            int id = item.getItemId();

            if (id == R.id.trending) {
                setContentView(R.layout.fragment_tending);
                textView.setText(R.string.Trending);
            } else if (id == R.id.movies) {
                setContentView(R.layout.fragment_home);
                textView.setText(R.string.Movies);
            } else if (id == R.id.manageAccount) {
                setContentView(R.layout.fragment_notifications);
                textView.setText(R.string.ManageAccount);
            }

            return true;
        });

        bottomNavigationView.setSelectedItemId(R.id.trending);
    }

}
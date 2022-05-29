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
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

public class MainActivity<connectivityManager> extends AppCompatActivity {

    Repository repository = null;
    private static final String TAG = "MainActivity";
    private ConnectivityManager connectivityManager = null;
    EditText email, password, BirthDate;
    Button signup, signin;

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

        repository = new Repository();

        connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        Network currentNetwork = connectivityManager.getActiveNetwork();
        connectivityManager.registerDefaultNetworkCallback(networkCallback);
        fetchFilmsList();

        email = findViewById(R.id.editTextEmailLogin);
        password = findViewById(R.id.editTextPasswordLogin);
        signup = findViewById(R.id.registerButton);
        signin = findViewById(R.id.loginButton);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
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
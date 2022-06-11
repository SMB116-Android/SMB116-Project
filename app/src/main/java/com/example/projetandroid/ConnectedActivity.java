package com.example.projetandroid;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.projetandroid.DB.User.User;
import com.example.projetandroid.Fragments.AccountFragment;
import com.example.projetandroid.Fragments.DatePickerFragment;
import com.example.projetandroid.Fragments.FilmFragment;
import com.example.projetandroid.Fragments.TrendingFragment;
import com.example.projetandroid.ViewModel.UserViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class ConnectedActivity extends AppCompatActivity {
    private static final String TAG = "ConnectedActivity";
    private UserViewModel userViewModel;
    BottomNavigationView bottomNavigationView;

    TrendingFragment trendingFragment = new TrendingFragment();
    FilmFragment filmFragment = new FilmFragment();
    AccountFragment accountFragment = new AccountFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connected);
        this.userViewModel = UserViewModel.getInstance();

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

    public void returnToMenu() {
        getSupportFragmentManager().beginTransaction().replace(R.id.container, trendingFragment).commit();
    }

}
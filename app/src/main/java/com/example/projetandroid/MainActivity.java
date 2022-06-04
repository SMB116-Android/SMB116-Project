package com.example.projetandroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projetandroid.Fragments.LoginFragment;
import com.example.projetandroid.Fragments.RegisterFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.example.projetandroid.Fragments.DatePickerFragment;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    LoginFragment loginFragment = new LoginFragment();
    RegisterFragment registerFragment = new RegisterFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottom_navigation_login_register);

        getSupportFragmentManager().beginTransaction().replace(R.id.containerLoginRegister, loginFragment).commit();

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.login:
                        getSupportFragmentManager().beginTransaction().replace(R.id.containerLoginRegister, loginFragment).commit();
                        return true;
                    case R.id.register:
                        getSupportFragmentManager().beginTransaction().replace(R.id.containerLoginRegister, registerFragment).commit();
                        return true;
                }
                return false;
            }
        });

    }
    public void datePicker(View v)
    {
        displayDatePicker();
    }

    private void displayDatePicker() {
        DialogFragment fragment = new DatePickerFragment();
        fragment.show(getSupportFragmentManager(), "datePicker");
    }

    public void setSelectedDate(int year, int month, int dayOfMonth) {
        TextView textView = (TextView) findViewById(R.id.birthDateLbl);
        textView.setText(dayOfMonth + "/" + month + "/" + year);
    }



}
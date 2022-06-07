package com.example.projetandroid;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projetandroid.DB.User.User;
import com.example.projetandroid.DB.User.UserRepository;
import com.example.projetandroid.Fragments.LoginFragment;
import com.example.projetandroid.Fragments.RegisterFragment;
import com.example.projetandroid.ViewModel.UserViewModel;
import com.example.projetandroid.ViewModel.ViewModelFactory;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.example.projetandroid.Fragments.DatePickerFragment;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    public static String CURRENT_USER;

    private UserViewModel userViewModel;
    private Button registerButton;

    private static String EMAIL_KEY = "email";
    private String email;
    private User currentUser;

    private SharedPreferences preferences;
    private String sharedPrefFile = "com.example.projetandroid";

    LoginFragment loginFragment = new LoginFragment();
    RegisterFragment registerFragment = new RegisterFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        configureViewModel();

        final Observer<User> setUserObserver = new Observer<User>() {
            @Override
            public void onChanged(@Nullable final User newUser) {
                currentUser = newUser;
            }
        };

        userViewModel.getCurrentUser().observe(this, setUserObserver);


        preferences = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);

        email = preferences.getString(EMAIL_KEY, "");

        //automaticUserLogin();

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

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences.Editor preferencesEditor = preferences.edit();
        preferencesEditor.putString(EMAIL_KEY, userViewModel.getCurrentUser().getValue().get_email());
        preferencesEditor.apply();
    }

    private void automaticUserLogin() {
        if(email != ""){
            login(email);
            Toast.makeText(MainActivity.this, "Data has been saved successfully!   : "+email, Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(MainActivity.this, "Unsucessful "+email, Toast.LENGTH_LONG).show();
        }

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

    public void sendDataToLoginFragment(User user) {

        Bundle bundle=new Bundle();
        bundle.putString("email",user.get_email());
        bundle.putString("password",user.get_password());
        loginFragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.containerLoginRegister, loginFragment).commit();

    }

    // -------------------

    // DATA

    // -------------------

    private void configureViewModel() {

        this.userViewModel = new ViewModelProvider(this, ViewModelFactory.getInstance(this)).get(UserViewModel.class);

    }

    public void insertUser(User user) {

        userViewModel.insertUser(user);

    }

    public boolean login(String email, String password) {

        if(userViewModel.login(email, password)){
            User user = new User();
            user = userViewModel.getUserByEmail(email);
            userViewModel.getCurrentUser().setValue(user);
            Toast.makeText(MainActivity.this, userViewModel.getCurrentUser().getValue().get_email(), Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, ConnectedActivity.class);
            startActivity(intent);
            return true;
        }else{
            return false;
        }

    }

    public void login(String email) {

        User user = new User();
        user = userViewModel.getUserByEmail(email);
        userViewModel.getCurrentUser().setValue(user);
        Intent intent = new Intent(this, ConnectedActivity.class);
        startActivity(intent);

    }


    private void updateView(User user) {

        if (user == null) return;



    }


}
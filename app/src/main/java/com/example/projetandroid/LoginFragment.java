package com.example.projetandroid;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginFragment extends Fragment {

    EditText email, password;
    Button signin;
    TextView noAccount;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        email = view.findViewById(R.id.editTextEmailLogin);
        password = view.findViewById(R.id.editTextPasswordLogin);
        signin = view.findViewById(R.id.loginButton);
        noAccount = view.findViewById(R.id.noAccountButton);

        signin.setOnClickListener(v -> {
            Navigation.findNavController(view).navigate(R.id.loginFragment);
        });

        noAccount.setOnClickListener(v -> {
            Navigation.findNavController(view).navigate(R.id.registerFragment);
        });
    }
}
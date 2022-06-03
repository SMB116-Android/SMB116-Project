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

public class RegisterFragment extends Fragment {

    EditText email, password, birthDate;
    Button signup;
    TextView haveAccount;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        email = view.findViewById(R.id.editTextEmailLogin);
        password = view.findViewById(R.id.editTextPasswordLogin);
        //birthDate = findViewById(R.id.)
        signup = view.findViewById(R.id.registerButton);
        haveAccount = view.findViewById(R.id.haveAccountButton);

        signup.setOnClickListener(v -> {
            Navigation.findNavController(view).navigate(R.id.loginFragment);
        });

        haveAccount.setOnClickListener(v -> {
            Navigation.findNavController(view).navigate(R.id.loginFragment);
        });
    }
}
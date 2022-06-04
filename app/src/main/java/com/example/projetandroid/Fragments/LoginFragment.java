package com.example.projetandroid.Fragments;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.projetandroid.R;

public class LoginFragment extends Fragment {

    EditText email, password;
    Button signin;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        email = view.findViewById(R.id.editTextEmailLogin);
        password = view.findViewById(R.id.editTextPasswordLogin);
        signin = view.findViewById(R.id.loginButton);

        signin.setOnClickListener(v -> {

        });


    }
}
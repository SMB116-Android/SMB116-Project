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
import android.widget.TextView;

import com.example.projetandroid.R;

public class RegisterFragment extends Fragment {

    EditText email, password, birthDate;
    Button signup;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_register, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        email = view.findViewById(R.id.editTextEmailLogin);
        password = view.findViewById(R.id.editTextPasswordLogin);
        //birthDate = findViewById(R.id.)
        signup = view.findViewById(R.id.registerButton);

        signup.setOnClickListener(v -> {

        });

    }
}
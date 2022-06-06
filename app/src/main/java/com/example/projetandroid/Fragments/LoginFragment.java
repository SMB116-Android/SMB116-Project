package com.example.projetandroid.Fragments;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.example.projetandroid.MainActivity;
import android.widget.EditText;
import android.widget.TextView;

import com.example.projetandroid.DB.User.User;
import com.example.projetandroid.R;

public class LoginFragment extends Fragment {

    EditText email, password;

    boolean wrongPassword;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_login, container, false);

        MainActivity mainActivity = (MainActivity) getActivity();

        email = view.findViewById(R.id.editTextEmailLogin);
        password = view.findViewById(R.id.editTextPasswordLogin);
        Button signin = (Button) view.findViewById(R.id.loginButton);

        signin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                wrongPassword = false;
                if(CheckAllFields()){
                    if (email.getText() != null && password.getText() != null) {
                        if(mainActivity.login(email.getText().toString(), password.getText().toString())){

                        }else{
                            wrongPassword = true;
                            CheckAllFields();
                        }

                    }
                    email.setText("");
                    password.setText("");
                }

            }
        });

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onResume() {
        super.onResume();

        Bundle bundle = getArguments();
        if(bundle != null){
            if(bundle.containsKey("email")){
                email.setText(String.valueOf(bundle.getString("email")));
            }

            if(bundle.containsKey("password")){
                password.setText(String.valueOf(bundle.getString("password")));
            }
        }


    }

    private boolean CheckAllFields() {

        if (email.length() == 0) {
            email.setError("Email is required");
            return false;
        }

        if (password.length() == 0) {
            password.setError("Password is required");
            return false;
        }

        if (wrongPassword) {
            password.setError("Password is wrong");
            return false;
        }


        return true;
    }
}
package com.example.projetandroid.Fragments;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

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

public class RegisterFragment extends Fragment {

    EditText emailText, passwordText;

    TextView birthDateText;

    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_register,
                container, false);

        MainActivity mainActivity = (MainActivity) getActivity();

        emailText = view.findViewById(R.id.editTextEmailRegister);
        passwordText = view.findViewById(R.id.editTextPasswordRegister);
        birthDateText = view.findViewById(R.id.birthDateLbl);

        Button button = (Button) view.findViewById(R.id.registerButton);


        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(CheckAllFields()){
                    if (emailText.getText() != null && passwordText.getText() != null &&  birthDateText.getText() != null) {
                        User user = new User(emailText.getText().toString(), passwordText.getText().toString(), birthDateText.getText().toString());
                        mainActivity.insertUser(user);
                        mainActivity.sendDataToLoginFragment(user);
                    }
                    emailText.setText("");
                    passwordText.setText("");
                    birthDateText.setText("");
                }

            }
        });

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }

    private boolean CheckAllFields() {

        if (emailText.length() == 0) {
            emailText.setError("Email is required");
            return false;
        }

        if (!emailText.getText().toString().trim().matches(emailPattern)) {
            emailText.setError("Invalid email address");
            return false;
        }

        if (passwordText.length() == 0) {
            passwordText.setError("Password is required");
            return false;
        }

        if (birthDateText.length() == 0) {
            birthDateText.setError("Birth Date is required");
            return false;
        }

        return true;
    }

}
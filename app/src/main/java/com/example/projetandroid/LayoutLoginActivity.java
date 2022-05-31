package com.example.projetandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LayoutLoginActivity extends AppCompatActivity {

    EditText email, password;
    Button signin;
    TextView noAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_login);

        email = findViewById(R.id.editTextEmailLogin);
        password = findViewById(R.id.editTextPasswordLogin);
        signin = findViewById(R.id.loginButton);
        noAccount = findViewById(R.id.noAccountButton);

        if(signin != null){
            signin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent signIn = new Intent(LayoutLoginActivity.this, LoginActivity.class);
                    startActivity(signIn);
                }
            });
        }

        if(noAccount != null){
            noAccount.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent signUp = new Intent(LayoutLoginActivity.this, LayoutRegisterActivity.class);
                    startActivity(signUp);
                }
            });
        }
    }
}
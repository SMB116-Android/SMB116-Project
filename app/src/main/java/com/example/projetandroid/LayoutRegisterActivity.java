package com.example.projetandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LayoutRegisterActivity extends AppCompatActivity {

    EditText email, password, birthDate;
    Button signup;
    TextView haveAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_register);

        email = findViewById(R.id.editTextEmailLogin);
        password = findViewById(R.id.editTextPasswordLogin);
        //birthDate = findViewById(R.id.)
        signup = findViewById(R.id.registerButton);
        haveAccount = findViewById(R.id.haveAccountButton);

        if(haveAccount != null){
            haveAccount.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent signIn = new Intent(LayoutRegisterActivity.this, LayoutLoginActivity.class);
                    startActivity(signIn);
                }
            });
        }

        if(signup != null){
            signup.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
        }
    }
}
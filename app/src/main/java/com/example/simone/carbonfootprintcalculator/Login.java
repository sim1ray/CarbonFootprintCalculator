package com.example.simone.carbonfootprintcalculator;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;


public class Login extends AppCompatActivity {
    Button exit, login;
    EditText id, pwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        id = findViewById(R.id.usernameEditText);
        pwd = findViewById(R.id.passwordEditText);
        exit = findViewById(R.id.exitButton);
        login = findViewById(R.id.loginButton);

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Welcome.class);
                startActivity(intent);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {

            // string format to communicate : "#470#kim@1234“

            @Override
            public void onClick(View v) {
                String idpwd = "#Login#" + id.getText().toString() + "@" + pwd.getText().toString();
                MainActivity.sendMessageToServer(idpwd);
            }

        });
    }

}

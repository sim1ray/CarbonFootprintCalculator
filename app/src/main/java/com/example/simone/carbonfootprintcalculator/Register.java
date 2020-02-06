package com.example.simone.carbonfootprintcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Register extends AppCompatActivity {

    Button register, exit;
    EditText username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        username = findViewById(R.id.createUsernameEditText);
        password = findViewById(R.id.createPasswordEditText);
        register = findViewById(R.id.registerButton);
        exit = findViewById(R.id.exitButton);

        register.setOnClickListener(new View.OnClickListener() {

            // String format to communicate : "#Register#simone@1234“

            @Override
            public void onClick(View v) {
                String idpwd = "#Register#" + username.getText().toString() + "@" + password.getText().toString();
                MainActivity.sendMessageToServer(idpwd);
            }

        });

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Welcome.class);
                startActivity(intent);
            }
        });


    }

}

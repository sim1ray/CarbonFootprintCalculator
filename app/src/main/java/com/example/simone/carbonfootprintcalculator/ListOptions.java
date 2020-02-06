package com.example.simone.carbonfootprintcalculator;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class ListOptions extends AppCompatActivity {

    RadioGroup radioGroup1, radioGroup2;
    RadioButton radiobutton;
    Button submit, cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_option);

        radioGroup1 = findViewById(R.id.radioGroup1);
        submit = findViewById(R.id.btn_submit);
        cancel = findViewById(R.id.btn_Cancel);

        submit.setOnClickListener(new View.OnClickListener() {  // hit the submit button
            @Override
            public void onClick(View v) {
                int radioId1 = radioGroup1.getCheckedRadioButtonId();
                radiobutton = findViewById(radioId1);

                if (radiobutton.getText().equals("Food")) {
                    Intent intent = new Intent(getApplicationContext(), CalculateFootprint.class);
                    startActivity(intent);
                } else {
                    featureNotAvailable();
                }
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Welcome.class);
                startActivity(intent);
            }
        });
    }

    public void featureNotAvailable() {
        Toast.makeText(this, "This feature is not yet available.", Toast.LENGTH_LONG).show();
    }

}


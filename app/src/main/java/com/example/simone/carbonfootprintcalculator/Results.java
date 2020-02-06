package com.example.simone.carbonfootprintcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

public class Results extends AppCompatActivity {
    EditText co2Result, carResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        co2Result = findViewById(R.id.co2);
        carResult = findViewById(R.id.car);

        //Get the bundle
        Bundle bundle = getIntent().getExtras();

        //Extract the data…
        String co2 = bundle.getString("co2");
        String car = bundle.getString("car");

        // Display calculation results
        co2Result.setText(co2);
        carResult.setText(car);

    }
}

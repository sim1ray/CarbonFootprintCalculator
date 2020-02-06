package com.example.simone.carbonfootprintcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class CalculateFootprint extends AppCompatActivity {
    EditText foodEditText, weightEditText;
    Button calculate;
    Spinner s;
    String unit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate_footprint);

        foodEditText = findViewById(R.id.foodEditText);
        weightEditText = findViewById(R.id.weightEditText);
        s = findViewById(R.id.spin);

        calculate = findViewById(R.id.calculateButton);

        // Get selected unit
        s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                unit = parent.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateFootprint(foodEditText.getText().toString(), weightEditText.getText().toString(), unit);
            }
        });

    }

    public void calculateFootprint(String food, String weight, String unit) {
        Toast.makeText(this, "Calculating carbon footprint...", Toast.LENGTH_SHORT).show();
        String info = "#Calculate#" + food + "@" + weight + "$" + unit;
        MainActivity.sendMessageToServer(info);
    }
}

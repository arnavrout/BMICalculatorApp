package com.arnavrout.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private TextView resultText;

    private EditText ageEditText;
    private EditText feetEditText;
    private EditText inchesEditText;
    private EditText weightEditText;

    private Button calculateButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        setupButtonClickListener();
    }


    private void findViews() {

        resultText = findViewById(R.id.text_view_result);

        ageEditText = findViewById(R.id.edit_text_age);
        feetEditText = findViewById(R.id.edit_text_feet);
        inchesEditText = findViewById(R.id.edit_text_inches);
        weightEditText = findViewById(R.id.edit_text_weight);

        calculateButton = findViewById(R.id.button_calculate);

    }

    private void setupButtonClickListener() {
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateBmi();
            }
        });
    }

    private void calculateBmi() {
        String ageText = ageEditText.getText().toString();
        String feetText = feetEditText.getText().toString();
        String inchesText = inchesEditText.getText().toString();
        String weightText = weightEditText.getText().toString();

        //converting the number 'strings' into 'int' variables
        int age = Integer.parseInt(ageText);
        int feet = Integer.parseInt(feetText);
        int inches = Integer.parseInt(inchesText);
        int weight = Integer.parseInt(weightText);

        int totalInches = (feet * 12) + inches;

        //height in meters is inches * 0.0254
        double heightInMeters = totalInches * 0.0254; //here if we put int it'll produce an error as outcome will be in decimal no.'s

        //BMI formula = weight/height in meters squared
        double bmi = weight / (heightInMeters * heightInMeters);

        DecimalFormat myDecimalFormatter = new DecimalFormat("0.00"); //Result will show upto two decimal places
        String bmiTextResult = myDecimalFormatter.format(bmi);

        String categoryResult;
        if (bmi < 18.5) {
            categoryResult = bmiTextResult + " - You are underweight";
        } else if (bmi > 25) {
            categoryResult = bmiTextResult + " - You are overweight";
        } else {
            categoryResult = bmiTextResult + " - You are a healthy person";
        }

        resultText.setText(categoryResult);


    }
}
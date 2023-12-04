package com.example.button_interactions2;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RadioButton radbtn;
    private RadioGroup rdgroup;
    private Button btnsubmit;
    private Spinner spinner;
    private TextView txtv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        String coursesel = "";
        rdgroup = findViewById(R.id.rdgroup);
        btnsubmit = findViewById(R.id.btnsubmit);
        spinner = findViewById(R.id.spinner);
        txtv = findViewById(R.id.textView2);

        rdgroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                radbtn = findViewById(checkedId);
                String selectedCourse = radbtn.getText().toString();
                updateSpinnerValues(selectedCourse);
            }
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // Handle nothing selected if needed
            }
        });

        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int radioId = rdgroup.getCheckedRadioButtonId();
                radbtn = findViewById(radioId);

                String selectedCourse = radbtn.getText().toString();
                String selectedDegree = spinner.getSelectedItem().toString();

                String message = "Selected Course is " + selectedCourse + ", your preferred degree is " + selectedDegree;
                Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();

                txtv.setVisibility(View.VISIBLE);
                txtv.setText(message);
            }
        });
    }

    private void updateSpinnerValues(String selectedCourse) {
        List<String> courseOptions = new ArrayList<>();

        if ("Computer".equals(selectedCourse)) {
            courseOptions.add("Computer Science");
            courseOptions.add("Information Technology");
        } else if ("Engineering".equals(selectedCourse)) {
            courseOptions.add("Mechanical Engineering");
            courseOptions.add("Electrical Engineering");
        } else if ("Commerce".equals(selectedCourse)) {
            courseOptions.add("Accounting");
            courseOptions.add("Finance");
        } else {
            courseOptions.add("Please Select a Course");
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, courseOptions);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);
    }
}

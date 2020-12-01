package com.example.beststudy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class RadioButtonActivity extends AppCompatActivity {

    RadioGroup rg;
    RadioButton rb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radio_button);
        rg = findViewById(R.id.RGroup);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId==R.id.SGPA){
                    Intent intent = new Intent(RadioButtonActivity.this, CalculateGpa.class);
                    startActivity(intent);
                }
                else if(checkedId==R.id.CGrade){
                    Intent intent = new Intent(RadioButtonActivity.this, GradeCalc.class);
                    startActivity(intent);
                }

            }
        });
    }
}
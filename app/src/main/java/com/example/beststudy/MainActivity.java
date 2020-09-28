package com.example.beststudy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button Schedule;
    private Button Class;
    private Button Professor;
    private Button Grades;
    private TextView ToBeAdded;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //initial button with id which set on activity_main.xml
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.Schedule=(Button)this.findViewById(R.id.schedule);
        this.Class=(Button)this.findViewById(R.id.Class);
        this.Professor=(Button)this.findViewById(R.id.Professor);
        this.Grades=(Button)this.findViewById(R.id.Grades);

        //the Schedule method gonna implement to the function below
        this.Schedule.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,WeekSchedule.class);
                startActivity(intent);
            }
        });

        //the Class method gonna implement to the function below
        this.Class.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v) {

            }
        });

        //the Professor method gonna implement to the function below
        this.Professor.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v) {

            }
        });

        //Clicking "Grades" button opens grade calculator screen
        this.Grades.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, GradeCalc.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onClick(View v) {

    }
}
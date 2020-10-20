package com.example.beststudy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;



public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button Schedule;
    private Button Class;
    private Button Professor;
    private Button Grades;
    private Button Assignments;
    private Button Reminders;
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
        this.Assignments=(Button)this.findViewById(R.id.Assignments);
        this.Reminders=(Button)this.findViewById(R.id.Reminders);

        //the Schedule method gonna implement to the function below
        this.Schedule.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, WeekSchedule.class);
                startActivity(intent);
            }
        });

        //the Schedule method gonna implement to the function below
        this.Reminders.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CalenderReminders.class);
                startActivity(intent);


            }
        });

        //the Class method gonna implement to the function below
        this.Class.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v) {
                Intent i = new Intent (MainActivity.this, Courseclick.class);
                startActivity(i);

            }
        });

        //the Professor method gonna implement to the function below
        this.Professor.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Find The Right Professor", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.ratemyprofessors.com/"));
                startActivity(intent);
            }
        });

        //Clicking "Grades" button opens grade calculator screen
        this.Grades.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, GradeCalc.class);
                startActivity(intent);
            }
        });

        //Clicking "Grades" button opens grade calculator screen
        this.Grades.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, GradeCalc.class);
                startActivity(intent);
            }
        });

        //Assignment Screen
        this.Assignments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AssignmentScreen.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onClick(View v) {

    }
}
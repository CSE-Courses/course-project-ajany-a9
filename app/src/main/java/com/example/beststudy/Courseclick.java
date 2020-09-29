package com.example.beststudy;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.graphics.Color;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

public class Courseclick extends AppCompatActivity implements View.OnClickListener {
    Button NewClass;
    Button ShowCourses;
    String coursename;
    String coursetime;
    String profname;
    String zoomlink;
    ListView CourseList;
    ArrayList<String> allCourse = new ArrayList<>();
    ArrayAdapter<String> adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courseclick);
        this.NewClass=(Button)this.findViewById(R.id.NewClass);
        this.ShowCourses = (Button)this.findViewById(R.id.Appear);
        this.CourseList = (ListView)this.findViewById(R.id.CourseList);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, allCourse);
        CourseList.setAdapter(adapter);


        /*Button to add new course, takes to new course creation screen*/
        this.NewClass.setOnClickListener(new View.OnClickListener(){
            public void onClick(View x){
                Intent i = new Intent(Courseclick.this, Course.class);
                startActivity(i);

            }
        });

        this.ShowCourses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Add new courses to list view*/
                addCourse(v);
            }
        });

    }

    public void addCourse(View view){
        coursename = getIntent().getExtras().getString("CourseName");
        coursetime = getIntent().getExtras().getString("CourseTime");
        profname = getIntent().getExtras().getString("ProfName");
        zoomlink = getIntent().getExtras().getString("ZoomLink");
        String a = coursename + " " + coursetime + "\n" + profname + " " + zoomlink;
        adapter.add(a);
    }

    @Override
    public void onClick(View x){

    }

}
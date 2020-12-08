package com.example.beststudy;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.nio.ShortBuffer;
import java.util.ArrayList;

/**
 * Using Adam's Tuple class framework to hold user data tempo
 * @Param c1: the course name
 * @Param c2: course time (as a string)
 * @Param c3: Professor name
 *
 */

public class Course extends AppCompatActivity {
    Button saved;
    /* Responses from text fields*/
    EditText courseN;
    EditText courseT;
    EditText profN;
    EditText zoomL;
    EditText endCourse;
    EditText courseD;
    ListView AllInput;
    CourseAdapter adapter;
    /* actual string values for tuple*/
    String courseNamestr;
    String courseTimestr;
    String courseEnd;
    String courseDay;
    String profNamestr;
    String zoomLinkstr;
    /*ArrayList to hold courses until we have database*/
    ArrayList<CourseDetail> AllCourse = new ArrayList<>();

   CoursesDataBase data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);
        data = new CoursesDataBase(this);

        saved = findViewById(R.id.Savebutton);
        courseN = findViewById(R.id.editTextCourseName);
        courseT = findViewById(R.id.editTextCourseTime);
        profN = findViewById(R.id.editTextProfName);
        zoomL = findViewById(R.id.editTextZoomLink);
        endCourse = findViewById(R.id.editTextCourseEnd);
        courseD = findViewById(R.id.editTextCourseDay);
        AllInput = findViewById(R.id.CourseList);

        ShowClasses();


        /*When the save button is pressed, the data is retrieved from the text fields
        new Tuple is created and save in ArrayList
        * until we set up database. Then displays course on Courses Page */

        saved.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                courseNamestr = courseN.getText().toString();
                courseTimestr = courseT.getText().toString();
                profNamestr = profN.getText().toString();
                zoomLinkstr = zoomL.getText().toString();
                courseEnd = endCourse.getText().toString();
                courseDay = courseD.getText().toString();
                CourseDetail curr = new CourseDetail(courseNamestr, courseTimestr, courseEnd, profNamestr, zoomLinkstr, courseDay);
                data.insertCourse(courseNamestr,courseTimestr, courseEnd, profNamestr, courseDay, zoomLinkstr);
                AllCourse.add(curr);
                ShowClasses();

            }
        });



    }
    public void ShowClasses(){
        adapter = new CourseAdapter(this, AllCourse);
        AllInput.setAdapter(adapter);
    }


}
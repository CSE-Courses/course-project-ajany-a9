package com.example.beststudy;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Using Adam's Tuple class framework to hold user data tempo
 * @Param c1: the course name
 * @Param c2: course time (as a string)
 * @Param c3: Professor name
 *
 */

class CourseSet{
    public String courseName;
    public String courseTime;
    public String profName;
    public String link;


    public CourseSet(String c1, String c2, String c3, String c4){
        this.courseName = c1;
        this.courseTime = c2;
        this.profName = c3;
        this.link = c4;
    }

}


public class Course extends AppCompatActivity {
    Button saved;
    /* Responses from text fields*/
    EditText courseN;
    EditText courseT;
    EditText profN;
    EditText zoomL;
    /* actual string values for tuple*/
    String courseNamestr;
    String courseTimestr;
    String profNamestr;
    String zoomLinkstr;
    /*ArrayList to hold courses until we have database*/
    ArrayList<CourseSet> AllCourse = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);

        saved = findViewById(R.id.Savebutton);
        courseN = findViewById(R.id.editTextCourseName);
        courseT = findViewById(R.id.editTextCourseTime);
        profN = findViewById(R.id.editTextProfName);
        zoomL = findViewById(R.id.editTextZoomLink);

        /*When the save button is pressed, the data is retrieved from the text fields
        new Tuple is created and save in ArrayList
        * until we set up database. Then displays course on Courses Page */

        saved.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent save = new Intent(Course.this, Courseclick.class);
                courseNamestr = courseN.getText().toString();
                courseTimestr = courseT.getText().toString();
                profNamestr = profN.getText().toString();
                zoomLinkstr = zoomL.getText().toString();
                CourseSet curr = new CourseSet(courseNamestr, courseTimestr, profNamestr, zoomLinkstr);
                AllCourse.add(curr);
                save.putExtra("CourseName", courseNamestr);
                save.putExtra("CourseTime", courseTimestr);
                save.putExtra("ProfName", profNamestr);
                save.putExtra("ZoomLink", zoomLinkstr);
                startActivity(save);
                finish();

            }
        });



    }

}
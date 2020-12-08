package com.example.beststudy;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

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

                courseNamestr = courseN.getText().toString() + " ";
                courseTimestr = courseT.getText().toString() + " ";
                profNamestr = profN.getText().toString() + " ";
                zoomLinkstr = zoomL.getText().toString() + " ";
                courseEnd = endCourse.getText().toString() + " ";
                courseDay = courseD.getText().toString() + " ";
                CourseDetail curr = new CourseDetail(courseNamestr, courseTimestr, courseEnd, profNamestr, zoomLinkstr, courseDay);
                if (data.insertCourse(courseNamestr, courseTimestr, courseEnd, profNamestr, courseDay, zoomLinkstr)) {
                    //AllCourse.add(curr);
                    courseN.setText("");
                    courseT.setText("");
                    profN.setText("");
                    zoomL.setText("");
                    endCourse.setText("");
                    courseD.setText("");
                    AllCourse.clear();
                    ShowClasses();
                } else {
                    Toast.makeText(Course.this, "Course Not Added", Toast.LENGTH_SHORT).show();
                }
            }
            });
        AllInput.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                data.removeCourse(AllCourse.get(position).getClassName());
                AllCourse.remove(position);
                adapter.notifyDataSetChanged();
               //Toast.makeText(getApplicationContext(), "Course Deleted", Toast.LENGTH_SHORT).show();
                return true;
            }
        });



    }
    public void ShowClasses(){
        Cursor cursor = data.viewData();
        if(cursor.getCount() == 0){
            Toast.makeText(this, "No Courses Added", Toast.LENGTH_SHORT).show();
        }
        else{
            while(cursor.moveToNext()){
                CourseDetail toShow = new CourseDetail("", "", "", "", "", "");
                toShow.setClassName(cursor.getString(1));
                toShow.setClassStart(cursor.getString(2));
                toShow.setClassEnd(cursor.getString(3));
                toShow.setClassProf(cursor.getString(4));
                toShow.setClassDay(cursor.getString(5));
                toShow.setClassLink(cursor.getString(6));
                AllCourse.add(toShow);
            }
        }
        adapter = new CourseAdapter(this, AllCourse);
        AllInput.setAdapter(adapter);
    }


}
package com.example.beststudy;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
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
    TextView zoomHolder;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courseclick);
        this.NewClass=(Button)this.findViewById(R.id.NewClass);
        this.ShowCourses = (Button)this.findViewById(R.id.Appear);
        this.CourseList = (ListView)this.findViewById(R.id.CourseList);
        this.zoomHolder = (TextView)this.findViewById(R.id.zoomPart);
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
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                /*Add new courses to list view*/
                addCourse(v);
            }
        });

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void addCourse(View view){
        coursename = getIntent().getExtras().getString("CourseName");
        coursetime = getIntent().getExtras().getString("CourseTime");
        profname = getIntent().getExtras().getString("ProfName");
        zoomlink = getIntent().getExtras().getString("ZoomLink");
        int linkEnd = zoomlink.length();
        zoomHolder.setMovementMethod(LinkMovementMethod.getInstance());
        SpannableString spannedZoomLink = new SpannableString(zoomlink);
        final String asLink = Html.toHtml(spannedZoomLink, Html.TO_HTML_PARAGRAPH_LINES_CONSECUTIVE);

        ClickableSpan clickZoomLink = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(asLink));
                startActivity(intent);
            }
        };
        spannedZoomLink.setSpan(clickZoomLink, 0, linkEnd, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        zoomHolder.setText(spannedZoomLink);
        String a = coursename + " " + coursetime + "\n" + profname ;
        adapter.add(a);

    }

    @Override
    public void onClick(View x){

    }

}
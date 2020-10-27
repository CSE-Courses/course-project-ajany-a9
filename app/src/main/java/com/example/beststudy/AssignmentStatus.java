package com.example.beststudy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AssignmentStatus extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignment_status);

        Intent intent = getIntent();
        String val = intent.getStringExtra("Assignment");

        TextView tv = (TextView) findViewById(R.id.textView);
        tv.setText(val);
    }
}
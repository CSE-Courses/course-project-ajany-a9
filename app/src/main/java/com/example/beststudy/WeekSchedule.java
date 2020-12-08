package com.example.beststudy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.beststudy.ListButtonView;
import com.example.beststudy.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class WeekSchedule extends AppCompatActivity implements View.OnClickListener{
    private FloatingActionButton listButton;

    public static ConstraintLayout mondayConstraintLayout;
    public static ConstraintLayout tuesdayConstraintLayout;
    public static ConstraintLayout wednesdayConstraintLayout;
    public static ConstraintLayout thursdayConstraintLayout;
    public static ConstraintLayout fridayConstraintLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.schedule_layout);

        this.listButton=(FloatingActionButton)findViewById(R.id.listViewButton);
        this.mondayConstraintLayout = (ConstraintLayout) findViewById(R.id.mondayConstraintLayout);
        this.tuesdayConstraintLayout = (ConstraintLayout) findViewById(R.id.tuesdayConstraintLayout);
        this.wednesdayConstraintLayout = (ConstraintLayout) findViewById(R.id.wednesdayConstraintLayout);
        this.thursdayConstraintLayout = (ConstraintLayout) findViewById(R.id.thursdayConstraintLayout);
        this.fridayConstraintLayout = (ConstraintLayout) findViewById(R.id.fridayConstraintLayout);


        //*TextView text=new TextView(this);
        //text.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        //text.setText("test");
        //this.mondayLinerLayout.addView(text);


        this.listButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(WeekSchedule.this, ListButtonView.class);
                startActivity(intent);
            }
        });


    }

    @Override
    public void onClick(View v) {

    }


}
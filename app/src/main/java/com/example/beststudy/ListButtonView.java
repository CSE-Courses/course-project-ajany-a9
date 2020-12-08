package com.example.beststudy;

import android.database.Cursor;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class ListButtonView extends AppCompatActivity implements View.OnClickListener{

    private Button addClassButton;
    private Button removeButton;
    private Spinner spinnerFirstTime;
    private Spinner spinnerSecondTime;
    private Spinner spinnerThirdTime;
    private Spinner spinnerFourthTime;
    private Spinner spinnerWeekday;
    private EditText editTextClassName;
    private timeInspector mondayInspector;
    private timeInspector tuesdayInspector;
    private timeInspector wednesdayInspector;
    private timeInspector thursdayInspector;
    private timeInspector fridayInspector;
    private scheduleDatabase scheduleDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listbutton_layout);
        this.addClassButton=(Button)findViewById(R.id.addClassButton);
        this.removeButton=(Button)findViewById(R.id.removeButton);
        mondayInspector =new timeInspector();
        tuesdayInspector=new timeInspector();
        wednesdayInspector=new timeInspector();
        thursdayInspector=new timeInspector();
        fridayInspector=new timeInspector();

        rememberClass();

        addClassButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
            @Override
            public void onClick(View v) {
                addClassButton();
            }
        });
        removeButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
            @Override
            public void onClick(View v) {
                removeClassButton();
            }
        });
    }

    @Override
    public void onClick(View v) {

    }
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public boolean addClassButton(){
        spinnerFirstTime=(Spinner) findViewById(R.id.spinnerFirstTime);
        String firstTime = spinnerFirstTime.getSelectedItem().toString();

        spinnerSecondTime=(Spinner) findViewById(R.id.spinnerSecondTime);
        String secondTime = spinnerSecondTime.getSelectedItem().toString();

        spinnerThirdTime=(Spinner) findViewById(R.id.spinnerThirdTime);
        String thirdTime = spinnerThirdTime.getSelectedItem().toString();

        spinnerFourthTime=(Spinner) findViewById(R.id.spinnerForthTime);
        String fourthTime = spinnerFourthTime.getSelectedItem().toString();

        spinnerWeekday=(Spinner)findViewById(R.id.spinnerWeekday);
        String weekday= spinnerWeekday.getSelectedItem().toString();

        editTextClassName=(EditText)findViewById(R.id.classNameEText);
        String className= editTextClassName.getText().toString();

        int firstTimeNumber= Integer.valueOf(firstTime);
        int secondTimeNumber= Integer.valueOf(secondTime);
        int thirdTimeNumber = Integer.valueOf(thirdTime);
        int forthTimeNumber = Integer.valueOf(fourthTime);

        String startTime = firstTime +":"+secondTime;
        String endTime = thirdTime +":"+fourthTime;
        String classInfo =className+"  "+"\r\n"+firstTime+":"+secondTime+"  to  "+ thirdTime+":"+fourthTime;


        final float scale = getResources().getDisplayMetrics().density;
        int Width  = (int) (70 * scale);
        int Height1 = (int) (60 * scale);
        int Height2 = (int) (1 * scale);

        TextView dataCell = new TextView(this);
        dataCell.setText(classInfo);
        int height =(thirdTimeNumber-firstTimeNumber)*Height1+(forthTimeNumber-secondTimeNumber)*Height2;
        dataCell.setBackgroundColor(Color.parseColor("#2196F3"));
        ConstraintLayout.LayoutParams params = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT);
        params.topToTop= ConstraintLayout.LayoutParams.PARENT_ID;
        params.setMargins(0,(firstTimeNumber-7)*Height1+secondTimeNumber*Height2,0,0);
        params.width=Width;
        params.height=height;
        dataCell.setLayoutParams(params);


        if (thirdTimeNumber < firstTimeNumber) {
                Toast.makeText(this, "The end time should be greater than start time",
                        Toast.LENGTH_SHORT).show();
                return false;
        }

        if ((thirdTimeNumber == firstTimeNumber) && (forthTimeNumber <= secondTimeNumber)) {
                Toast.makeText(this, "The end time should be greater than start time",
                        Toast.LENGTH_SHORT).show();
                return false;
        }


        if(weekday.equals("Monday")){
            if(mondayInspector.checkTimeBlock(startTime,endTime)==true){
               WeekSchedule.mondayConstraintLayout.addView((TextView)dataCell);
                this.mondayInspector.setTimeBlock(startTime,endTime);
            }
            else Toast.makeText(this, "The time you selected already has class", Toast.LENGTH_SHORT).show();
        }
        if(weekday.equals("Tuesday")){
            if(tuesdayInspector.checkTimeBlock(startTime,endTime)==true){
                WeekSchedule.tuesdayConstraintLayout.addView((TextView)dataCell);
                this.tuesdayInspector.setTimeBlock(startTime,endTime);
            }
            else Toast.makeText(this, "The time you selected already has class", Toast.LENGTH_SHORT).show();
        }
        if(weekday.equals("Wednesday")){
            if(wednesdayInspector.checkTimeBlock(startTime,endTime)==true){
                WeekSchedule.wednesdayConstraintLayout.addView((TextView)dataCell);
                this.wednesdayInspector.setTimeBlock(startTime,endTime);
            }
            else Toast.makeText(this, "The time you selected already has class", Toast.LENGTH_SHORT).show();
        }
        if(weekday.equals("Thursday")){
            if(thursdayInspector.checkTimeBlock(startTime,endTime)==true){
                WeekSchedule.thursdayConstraintLayout.addView((TextView)dataCell);
                this.thursdayInspector.setTimeBlock(startTime,endTime);
            }
            else Toast.makeText(this, "The time you selected already has class", Toast.LENGTH_SHORT).show();
        }
        if(weekday.equals("Friday")){
            if(fridayInspector.checkTimeBlock(startTime,endTime)==true){
                WeekSchedule.fridayConstraintLayout.addView((TextView)dataCell);
                this.fridayInspector.setTimeBlock(startTime,endTime);
            }
            else Toast.makeText(this, "The time you selected already has class", Toast.LENGTH_SHORT).show();
        }

        scheduleDatabase= new scheduleDatabase(this);
        Cursor cursor = scheduleDatabase.scheduleDataCursor();

        if(cursor.getCount() != 0){

            while(cursor.moveToNext()){

                String StartTime = cursor.getString(2);

                if(startTime.equals(StartTime)){
                    Toast.makeText(this, "The time you selected already have class",
                            Toast.LENGTH_SHORT).show();
                    return false;
                }
            }
        }
        scheduleDatabase.insertClass(classInfo+","+weekday,startTime,endTime);

        return true;
    }
    public boolean rememberClass(){

        scheduleDatabase= new scheduleDatabase(this);
        Cursor cursor = scheduleDatabase.scheduleDataCursor();

        if(cursor.getCount() == 0){
            Toast.makeText(this, "No class to show", Toast.LENGTH_SHORT).show();
        }
        else{
            while(cursor.moveToNext()){
                String classInfo = cursor.getString(1);
                String startTime = cursor.getString(2);
                String endTime = cursor.getString(3);
                String weekDay;
                final float scale = getResources().getDisplayMetrics().density;
                int Width  = (int) (70 * scale);
                int Height1 = (int) (60 * scale);
                int Height2 = (int) (1 * scale);

                int firstTimeNumber;
                int secondTimeNumber;
                int thirdTimeNumber;
                int forthTimeNumber;
                String startarr[]=startTime.split(":");
                firstTimeNumber=Integer.valueOf(startarr[0]);
                secondTimeNumber=Integer.valueOf(startarr[1]);
                String endarr[]=endTime.split(":");
                thirdTimeNumber=Integer.valueOf(endarr[0]);
                forthTimeNumber=Integer.valueOf(endarr[1]);

                String class2[]=classInfo.split(",");
                String className=class2[0];
                weekDay=class2[1];

                TextView dataCell = new TextView(this);
                dataCell.setText(className);
                int height =(thirdTimeNumber-firstTimeNumber)*Height1+(forthTimeNumber-secondTimeNumber)*Height2;
                dataCell.setBackgroundColor(Color.parseColor("#2196F3"));
                ConstraintLayout.LayoutParams params = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT);
                params.topToTop= ConstraintLayout.LayoutParams.PARENT_ID;
                params.setMargins(0,(firstTimeNumber-7)*Height1+secondTimeNumber*Height2,0,0);
                params.width=Width;
                params.height=height;
                dataCell.setLayoutParams(params);


                if(weekDay.equals("Monday")){
                    if(mondayInspector.checkTimeBlock(startTime,endTime)==true){
                        WeekSchedule.mondayConstraintLayout.addView((TextView)dataCell);
                        this.mondayInspector.setTimeBlock(startTime,endTime);
                    }
                    else Toast.makeText(this, "The time you selected already has class", Toast.LENGTH_SHORT).show();
                }
                if(weekDay.equals("Tuesday")){
                    if(tuesdayInspector.checkTimeBlock(startTime,endTime)==true){
                        WeekSchedule.tuesdayConstraintLayout.addView((TextView)dataCell);
                        this.tuesdayInspector.setTimeBlock(startTime,endTime);
                    }
                    else Toast.makeText(this, "The time you selected already has class", Toast.LENGTH_SHORT).show();
                }
                if(weekDay.equals("Wednesday")){
                    if(wednesdayInspector.checkTimeBlock(startTime,endTime)==true){
                        WeekSchedule.wednesdayConstraintLayout.addView((TextView)dataCell);
                        this.wednesdayInspector.setTimeBlock(startTime,endTime);
                    }
                    else Toast.makeText(this, "The time you selected already has class", Toast.LENGTH_SHORT).show();
                }
                if(weekDay.equals("Thursday")){
                    if(thursdayInspector.checkTimeBlock(startTime,endTime)==true){
                        WeekSchedule.thursdayConstraintLayout.addView((TextView)dataCell);
                        this.thursdayInspector.setTimeBlock(startTime,endTime);
                    }
                    else Toast.makeText(this, "The time you selected already has class", Toast.LENGTH_SHORT).show();
                }
                if(weekDay.equals("Friday")){
                    if(fridayInspector.checkTimeBlock(startTime,endTime)==true){
                        WeekSchedule.fridayConstraintLayout.addView((TextView)dataCell);
                        this.fridayInspector.setTimeBlock(startTime,endTime);
                    }
                    else Toast.makeText(this, "The time you selected already has class", Toast.LENGTH_SHORT).show();
                }

                scheduleDatabase= new scheduleDatabase(this);
                Cursor cursor1 = scheduleDatabase.scheduleDataCursor();

                if(cursor1.getCount() != 0){

                    while(cursor1.moveToNext()){

                        String StartTime = cursor1.getString(2);

                        if(startTime.equals(StartTime)){
                            Toast.makeText(this, "The time you selected already have class",
                                    Toast.LENGTH_SHORT).show();
                            return false;
                        }
                    }
                }
            }

        }

        return true;
    }
    public boolean  removeClassButton() { //method for removing class from schedule

        scheduleDatabase= new scheduleDatabase(this);
        Cursor cursor = scheduleDatabase.scheduleDataCursor();

        while(cursor.moveToNext()){
            String id = cursor.getString(0);
                scheduleDatabase.removeData(id);
        }


        return true;
    }
}

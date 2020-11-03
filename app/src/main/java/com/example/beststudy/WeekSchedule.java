package com.example.beststudy;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

/**this class create conlon and data array to hold the initial data in the app table of week schedule**/

class initialSchedule{
    private String[] initalCoulum={" Time","Monday  ","Tuesday  ","Wednesday  ","Thursday  ","Friday  "};

    private String[][] data={
            {"07:00am","","","","",""},{"07:30am","","","","",""},
            {"08:00am","","","","",""},{"08:30am","","","","",""},
            {"09:00am","","","","",""},{"09:30am","","","","",""},
            {"10:00am","","","","",""},{"10:30am","","","","",""},
            {"11:00am","","","","",""},{"11:30am","","","","",""},
            {"12:00pm","","","","",""},{"12:30pm","","","","",""},
            {"01:00pm","","","","",""},{"01:30pm","","","","",""},
            {"02:00pm","","","","",""},{"02:30pm","","","","",""},
            {"03:00pm","","","","",""},{"03:30pm","","","","",""},
            {"04:00pm","","","","",""},{"04:30pm","","","","",""},
            {"05:00pm","","","","",""},{"05:30pm","","","","",""},
            {"06:00pm","","","","",""},{"06:30pm","","","","",""},
            {"07:00pm","","","","",""},{"07:30pm","","","","",""},
            {"08:00pm","","","","",""},{"08:30pm","","","","",""},
            {"09:00pm","","","","",""},{"09:30pm","","","","",""},
            {"10:00pm","","","","",""},{"10:30pm","","","","",""},
            {"11:00pm","","","","",""},{"11:30pm","","","","",""},
            {"00:00am","","","","",""},{"00:30am","","","","",""},
            {"01:00am","","","","",""},{"01:30am","","","","",""},
            {"02:00am","","","","",""},{"02:30am","","","","",""},
            {"03:00am","","","","",""},{"03:30am","","","","",""},
            {"04:00am","","","","",""},{"04:30am","","","","",""},
            {"05:00am","","","","",""},{"05:30am","","","","",""},
            {"06:00am","","","","",""},{"06:30am","","","","",""},

            };
    public String[][] getData() {
        return this.data;
    }
    public String[] getInitalColum(){
        return this.initalCoulum;
    }
}

//implement the data from initialSchedule to the table in app schedule//
public class WeekSchedule extends AppCompatActivity implements View.OnClickListener {

    private scheduleDatabase scheduleDatabase;
    private ArrayList<TableRow> rowList = new ArrayList<TableRow>();
    private TableLayout weekScheduleTableLayout;
    private Spinner spinnerFirstTime;
    private Spinner spinnerSecondTime;
    private Spinner spinnerFirstAmOrPm;
    private Spinner spinnerThirdTime;
    private Spinner spinnerFourthTime;
    private Spinner spinnerSecondAmOrPm;
    private Spinner spinnerWeekday;
    private EditText editTextClassName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acctivity_weekschedule);
        initialWeekScheduleTable();
        rememberData();

        final Button addClassButton = findViewById(R.id.addClassButton);
        addClassButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addClassButton();
            }
        });

        final Button removeClassButton = findViewById(R.id.removeClassButton);
        removeClassButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeClassButton();
            }
        });

        final Button swapClassButton = findViewById(R.id.swapClassButton);
        swapClassButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                swapClassButton();
            }
        });

    }

    public ArrayList<TableRow> initialArrayListOfTable(){

        initialSchedule arrayAllData=new initialSchedule();
        String initialColon[];
        initialColon=arrayAllData.getInitalColum();
        String initialData[][];
        initialData=arrayAllData.getData();

        // use a loop to implement the heater//
        int i ;
        TableRow row0=new TableRow(this);
        for(i=0;i<6;i++){
            TextView heater=new TextView(this);
            heater.setText(initialColon[i]);
            row0.addView(heater);
        }
        this.rowList.add(row0);

        // use the inner loop to implement the row of data(time of the schedule)//
        int j;
        int p;
        for(j=0;j<48;j++){

            TableRow obj = new TableRow(this);

            for(p=0;p<6;p++){
                TextView dataCell = new TextView(this);
                dataCell.setText(initialData[j][p]);
                obj.addView(dataCell);
            }

            this.rowList.add(obj);
        }
        return this.rowList;
    }
    //addClassButtonSetting
    public void addClassButton(){
        spinnerFirstTime=(Spinner) findViewById(R.id.spinnerFirstTime);
        String firstTime = spinnerFirstTime.getSelectedItem().toString();

        spinnerSecondTime=(Spinner) findViewById(R.id.spinnerSecondTime);
        String secondTime = spinnerSecondTime.getSelectedItem().toString();

        spinnerFirstAmOrPm=(Spinner) findViewById(R.id.spinnerFirstAmOrPm);
        String firstAmOrPm = spinnerFirstAmOrPm.getSelectedItem().toString();

        spinnerThirdTime=(Spinner) findViewById(R.id.spinnerThirdTime);
        String thirdTime = spinnerThirdTime.getSelectedItem().toString();

        spinnerFourthTime=(Spinner) findViewById(R.id.spinnerForthTime);
        String fourthTime = spinnerFourthTime.getSelectedItem().toString();

        spinnerSecondAmOrPm=(Spinner) findViewById(R.id.spinnerSecondAmOrPm);
        String secondAmOrPm = spinnerSecondAmOrPm.getSelectedItem().toString();

        spinnerWeekday=(Spinner)findViewById(R.id.spinnerWeekday);
        String weekday= spinnerWeekday.getSelectedItem().toString();

        editTextClassName=(EditText)findViewById(R.id.classNameEText);
        String className= editTextClassName.getText().toString();

        int i=0;
        if(weekday.equals("Monday")){i=1;}
        if(weekday.equals("Tuesday")){i=2;}
        if(weekday.equals("Wednesday")){i=3;}
        if(weekday.equals("Thursday")){i=4;}
        if(weekday.equals("Friday")){i=5;}

        int j=0;
        int firstTimeNumber= Integer.valueOf(firstTime);
        int secondTimeNumber= Integer.valueOf(secondTime);
        if(firstTimeNumber>=7&&firstTimeNumber<=12){
            if(firstAmOrPm.equals("am")) {
                if (secondTimeNumber < 30 && secondTimeNumber >= 0) {
                    j = firstTimeNumber * 2 - 13;
                }
                if (secondTimeNumber >= 30 && secondTimeNumber <= 50) {
                    j = firstTimeNumber * 2 - 13 + 1;
                }
            }
            if(firstAmOrPm.equals("pm")&&firstTimeNumber==12) {
                if (secondTimeNumber < 30 && secondTimeNumber >= 0) {
                    j = firstTimeNumber * 2 - 13;
                }
                if (secondTimeNumber >= 30 && secondTimeNumber <= 50) {
                    j = firstTimeNumber * 2 - 13 + 1;
                }
            }
        }
        if(firstTimeNumber>1&&firstTimeNumber<=11){
            if(firstAmOrPm.equals("pm")) {
                if (secondTimeNumber < 30 && secondTimeNumber >= 0) {
                    j = firstTimeNumber * 2 + 11;
                }
                if (secondTimeNumber >= 30 && secondTimeNumber <= 50) {
                    j = firstTimeNumber * 2 +12 ;
                }
            }
        }

        if(firstTimeNumber>=0&&firstTimeNumber<=6) {
            if (firstAmOrPm.equals("am")) {
                if (secondTimeNumber < 30 && secondTimeNumber >= 0) {
                    j = firstTimeNumber * 2 + 35;
                }
                if (secondTimeNumber >= 30 && secondTimeNumber <= 50) {
                    j = firstTimeNumber * 2 + 36;
                }
            }

        }

        String classInfo = className+"  "+firstTime+":"+secondTime+" "
                +firstAmOrPm+"  to  "+ thirdTime+":"+fourthTime+" "+secondAmOrPm;
        TextView dataCell = new TextView(this);
        dataCell.setText(classInfo);
        dataCell.setWidth(10);

        scheduleDatabase= new scheduleDatabase(this);
        scheduleDatabase.insertClass(i,j,classInfo);

        this.rowList.get(j).addView(dataCell,i);

    }

    public void removeClassButton(){ //method for removing class from schedule
        spinnerFirstTime=(Spinner) findViewById(R.id.spinnerFirstTime);
        String firstTime = spinnerFirstTime.getSelectedItem().toString();

        spinnerSecondTime=(Spinner) findViewById(R.id.spinnerSecondTime);
        String secondTime = spinnerSecondTime.getSelectedItem().toString();

        spinnerFirstAmOrPm=(Spinner) findViewById(R.id.spinnerFirstAmOrPm);
        String firstAmOrPm = spinnerFirstAmOrPm.getSelectedItem().toString();

        spinnerThirdTime=(Spinner) findViewById(R.id.spinnerThirdTime);
        String thirdTime = spinnerThirdTime.getSelectedItem().toString();

        spinnerFourthTime=(Spinner) findViewById(R.id.spinnerForthTime);
        String fourthTime = spinnerFourthTime.getSelectedItem().toString();

        spinnerSecondAmOrPm=(Spinner) findViewById(R.id.spinnerSecondAmOrPm);
        String secondAmOrPm = spinnerSecondAmOrPm.getSelectedItem().toString();

        spinnerWeekday=(Spinner)findViewById(R.id.spinnerWeekday);
        String weekday= spinnerWeekday.getSelectedItem().toString();

        editTextClassName=(EditText)findViewById(R.id.classNameEText);
        String className= editTextClassName.getText().toString();

        int i=0;
        if(weekday.equals("Monday")){i=1;}
        if(weekday.equals("Tuesday")){i=2;}
        if(weekday.equals("Wednesday")){i=3;}
        if(weekday.equals("Thursday")){i=4;}
        if(weekday.equals("Friday")){i=5;}

        int j=0;
        int firstTimeNumber= Integer.valueOf(firstTime);
        int secondTimeNumber= Integer.valueOf(secondTime);
        if(firstTimeNumber>=7&&firstTimeNumber<=12){
            if(firstAmOrPm.equals("am")) {
                if (secondTimeNumber < 30 && secondTimeNumber >= 0) {
                    j = firstTimeNumber * 2 - 13;
                }
                if (secondTimeNumber >= 30 && secondTimeNumber <= 50) {
                    j = firstTimeNumber * 2 - 13 + 1;
                }
            }
            if(firstAmOrPm.equals("pm")&&firstTimeNumber==12) {
                if (secondTimeNumber < 30 && secondTimeNumber >= 0) {
                    j = firstTimeNumber * 2 - 13;
                }
                if (secondTimeNumber >= 30 && secondTimeNumber <= 50) {
                    j = firstTimeNumber * 2 - 13 + 1;
                }
            }
        }
        if(firstTimeNumber>1&&firstTimeNumber<=11){
            if(firstAmOrPm.equals("pm")) {
                if (secondTimeNumber < 30 && secondTimeNumber >= 0) {
                    j = firstTimeNumber * 2 + 11;
                }
                if (secondTimeNumber >= 30 && secondTimeNumber <= 50) {
                    j = firstTimeNumber * 2 +12 ;
                }
            }
        }

        if(firstTimeNumber>=0&&firstTimeNumber<=6) {
            if (firstAmOrPm.equals("am")) {
                if (secondTimeNumber < 30 && secondTimeNumber >= 0) {
                    j = firstTimeNumber * 2 + 35;
                }
                if (secondTimeNumber >= 30 && secondTimeNumber <= 50) {
                    j = firstTimeNumber * 2 + 36;
                }
            }

        }

        TextView dataCell = new TextView(this);
        dataCell.setText("");
        dataCell.setWidth(10);

        this.rowList.get(j).removeViewAt(i);

    }

    public void rememberData(){
        scheduleDatabase= new scheduleDatabase(this);
        Cursor cursor = scheduleDatabase.scheduleDataCursor();

        if(cursor.getCount() == 0){
            Toast.makeText(this, "No class to show", Toast.LENGTH_SHORT).show();
        }
        else{
            while(cursor.moveToNext()){
                String classInfo = cursor.getString(1);
                int j = Integer.valueOf(cursor.getString(2));
                int i = Integer.valueOf(cursor.getString(3));
                TextView dataCell = new TextView(this);
                dataCell.setText(classInfo);
                dataCell.setWidth(10);
                this.rowList.get(j).addView(dataCell,i);
            }

        }
    }
    public void swapClassButton(){ //method for swapping class with another class
        removeClassButton();
        addClassButton();
    }
    // add the table rows to table layout//
    public void initialWeekScheduleTable(){
        this.weekScheduleTableLayout = (TableLayout) findViewById(R.id.weekScheduleTableLayout);
        ArrayList<TableRow> tableArrayList=this.initialArrayListOfTable();

        int i;
        for(i=0;i<49;i++){
            this.weekScheduleTableLayout.addView(tableArrayList.get(i));
        }

    }

    @Override
    public void onClick(View v) {

    }
}



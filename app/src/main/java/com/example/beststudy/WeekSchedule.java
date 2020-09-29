package com.example.beststudy;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

/**this class create conlon and data array to hold the initial data in the app table of week schedule**/

class initialSchedule{
    private String[] initalCoulum={" Time","   Monday","   Tuesday","   Wednesday","   Thursday","   Friday"};

    private String[][] data={{"07:00am","","","","",""},{"07:30am","","","","",""},
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
public class WeekSchedule extends AppCompatActivity {
    private ArrayList<TableRow> rowList = new ArrayList<TableRow>();
    private TableLayout weekScheduleTableLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acctivity_weekschedule);

        initialWeekScheduleTable();



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

    // add the table rows to table layout//
    public void initialWeekScheduleTable(){
        this.weekScheduleTableLayout = (TableLayout) findViewById(R.id.weekScheduleTableLayout);
        ArrayList<TableRow> tableArrayList=this.initialArrayListOfTable();

        int i;
        for(i=0;i<49;i++){
            this.weekScheduleTableLayout.addView(tableArrayList.get(i));
        }

    }



}



package com.example.beststudy;

import androidx.appcompat.app.AppCompatActivity;

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
 * Tuple class is used to hold the backing data respective to user entered data.
 * @Param a1: The assignment name
 * @Param a2: The assignment grade
 * @Param a3: The percantage of the total grade the assignment accounts for
 */
class Tuple{
    public String assignment;
    public double grade;
    public double percent;

    public Tuple(String a1, double a2, double a3){
        this.assignment = a1;
        this.grade = a2;
        this.percent = a3;
    }
}

public class GradeCalc extends AppCompatActivity {
    //The grade table and the ArrayList which holds the table data in a usable form
    TableLayout gradeTablelayout;
    ArrayList<Tuple> gradeTableDataArray = new ArrayList<Tuple>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grade);

        initGradeTable();

        final Button buttonAdd = findViewById(R.id.addButton);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                addRowToGradeTable();
            }
        });

        final Button buttonRemove = findViewById(R.id.remButton);
        buttonRemove.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                removeRowFromGradeTable();
            }
        });

        final Button buttonCalc = findViewById(R.id.calcButton);
        buttonCalc.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                calculate();
            }
        });

    }

    /**
     *  Initializes(and displays) the grade table in an empty state.
     * @Params None
     * @Return Void
     */
    public void initGradeTable() {
        //Initialize gradeTableLayout as the Tablelayout defined in the grade_calc.xml file
        gradeTablelayout = (TableLayout) findViewById(R.id.table_main);
        //Make a new row "tbrow0" and add the headers as needed
        TableRow tbrow0 = new TableRow(this);
        TextView tv0 = new TextView(this);
        tv0.setText(" Assessment ");
        tv0.setTextColor(Color.WHITE);
        tbrow0.addView(tv0);
        TextView tv1 = new TextView(this);
        tv1.setText(" Grade ");
        tv1.setTextColor(Color.WHITE);
        tbrow0.addView(tv1);
        TextView tv2 = new TextView(this);
        tv2.setText(" Percentage ");
        tv2.setTextColor(Color.WHITE);
        tbrow0.addView(tv2);
        //Add the newly created first row to the grade table
        gradeTablelayout.addView(tbrow0);
    }

    /**
     * Create and then add a row to the grade table. Adds the
     * backing data for this new entry in the ArrayList gradeTableDataArray
     * @Params None
     * @Return void
     */
    public void addRowToGradeTable(){
        //Get the data from the three user input EditText lines
        EditText inputAssign = findViewById(R.id.inputAssign);
        String inputAssignValue = inputAssign.getText().toString();

        EditText inputGrade = findViewById(R.id.inputGrade);
        String inputGradeValue = inputGrade.getText().toString();
        if(inputGradeValue.length()==0){
            inputGradeValue = "0";
        }

        EditText inputPercent = findViewById(R.id.inputPercent);
        String inputPercentValue = inputPercent.getText().toString();
        if(inputPercentValue.length()==0){
            inputPercentValue = "0";
        }
        //Add the three values(with the latter two converted to doubles) to a newly created Tuple
        Tuple tupleOne = new Tuple(inputAssignValue,
                Double.parseDouble(inputGradeValue),
                Double.parseDouble(inputPercentValue)/100);
        //add the tuple to the gradeTableDataArray
        gradeTableDataArray.add(tupleOne);
        //Create a new row with the values entered(String form)
        TableRow tbrow = new TableRow(this);
        TextView tv0 = new TextView(this);
        tv0.setText(inputAssignValue);
        tv0.setTextColor(Color.WHITE);
        tv0.setGravity(Gravity.CENTER);
        tbrow.addView(tv0);
        TextView tv1 = new TextView(this);
        tv1.setText(inputGradeValue);
        tv1.setTextColor(Color.WHITE);
        tv1.setGravity(Gravity.CENTER);
        tbrow.addView(tv1);
        TextView tv2 = new TextView(this);
        tv2.setText(inputPercentValue+"%");
        tv2.setTextColor(Color.WHITE);
        tv2.setGravity(Gravity.CENTER);
        tbrow.addView(tv2);
        //Add the newly created row to the grade table
        gradeTablelayout.addView(tbrow);
    }

    /**
     * IF the grade table has at least 1 user entered row, remove the last row.
     * Also remove the latest entry in the gradeTableDataArray ArrayList.
     * @Params None
     * @Return void
     */
    public void removeRowFromGradeTable(){
        if(gradeTablelayout.getChildCount() > 1){
            gradeTablelayout.removeViewAt(gradeTablelayout.getChildCount()-1);
            gradeTableDataArray.remove(gradeTableDataArray.size()-1);
        }
    }

    /**
     *   Calculate the average based on the user entered list,
     *   and display the value to the user.
     *   @Params None
     *   @Return Void
     */
    public void calculate(){
        double avgGrade = 0.0;
        //Get all values in user created list
        for(int i = 0; i < gradeTableDataArray.size(); i++){
            avgGrade += gradeTableDataArray.get(i).grade * gradeTableDataArray.get(i).percent;
        }
        //Get the "Average: " EditText item
        TextView tv = (TextView)findViewById(R.id.outputGrade);
        //Set the text to whatever the calculated avg is, 0.0 for an empty list
        tv.setText("Average: " + Double.toString(avgGrade));
    }

}
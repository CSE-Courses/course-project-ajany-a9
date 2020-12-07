package com.example.beststudy;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
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

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;

//Created a temporary GPA button

//**** When Afee finishes his part we need to connect this to the Grades Button and
// remove code from the following files

//MainActivity.java lines: 23-24, 37, 106-111
//activity_main.xml lines: 115-129
//AndroidManifest.xml lines: 47-50

class tripleString{
    public String a;
    public String b;
    public String c;

    public tripleString(String a, String b, String c){
        this.a = a;
        this.b = b;
        this.c = c;
    }
}

public class CalculateGpa extends AppCompatActivity {
    TableLayout gradeTablelayout;
    ArrayList<tripleString> gradeTableDataArray = new ArrayList<>();

    EditText courseName;
    Spinner spinner;
    EditText numOfCredits;
    Button add;

    //double gradePoints;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculategpa);

        initGradeTable();

        courseName = (EditText)findViewById(R.id.courseName);
        numOfCredits = (EditText)findViewById(R.id.credits);
        add = (Button)findViewById(R.id.addInfo);

        spinner = (Spinner) findViewById(R.id.spinnerGrade);
        final String[] grades = {"A", "A-", "B+", "B", "B-", "C+", "C", "C-", "D+", "D", "F"};


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, grades);
        spinner.setAdapter(adapter);


        final Button buttonCalc = findViewById(R.id.calcGpa);
        buttonCalc.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                calculate();
            }
        });

        final Button buttonClear = findViewById(R.id.clearClasses);
        buttonClear.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                clearAll();
            }
        });



        /*spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long id) {
                String grade = parent.getItemAtPosition(i).toString();

                if (grade.equals("A"))
                    gradePoints = 4.00;
                else if (grade.equals("A-"))
                    gradePoints = 3.67;
                else if (grade.equals("B+"))
                    gradePoints = 3.33;
                else if (grade.equals("B"))
                    gradePoints = 3.00;
                else if (grade.equals("B-"))
                    gradePoints = 2.67;
                else if (grade.equals("C+"))
                    gradePoints = 2.33;
                else if (grade.equals("C"))
                    gradePoints = 2.00;
                else if (grade.equals("C-"))
                    gradePoints = 1.67;
                else if (grade.equals("D+"))
                    gradePoints = 1.33;
                else if (grade.equals("D"))
                    gradePoints = 1.00;
                else if (grade.equals("F"))
                    gradePoints = 0;

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }*/

        add.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                addData();
            }
        });
    }

    /**
     * Clears all entered grades and backing data.
     */
    private void clearAll(){
        gradeTableDataArray.clear();
        while(gradeTablelayout.getChildCount() > 1){
            gradeTablelayout.removeViewAt(1);
        }
    }

    //Adam's Code for initializing a table from GradeCalc.java
    //Lines 115-130
    public void initGradeTable() {
        gradeTablelayout = (TableLayout) findViewById(R.id.table_main);
        TableRow tbrow0 = new TableRow(this);
        TextView tv0 = new TextView(this);
        tv0.setText(" Course Name ");
        tv0.setTextColor(Color.BLACK);
        tbrow0.addView(tv0);
        TextView tv1 = new TextView(this);
        tv1.setText(" Grade ");
        tv1.setTextColor(Color.BLACK);
        tbrow0.addView(tv1);
        TextView tv2 = new TextView(this);
        tv2.setText(" Credits ");
        tv2.setTextColor(Color.BLACK);
        tbrow0.addView(tv2);
        gradeTablelayout.addView(tbrow0);
    }

    //Task #47: Entry of Class names, grades, and credit amount
    public void addData(){
        courseName = (EditText)findViewById(R.id.courseName);
        String course = courseName.getText().toString();
        if(course.length() != 0){
            courseName.setText("");
        }

        spinner = (Spinner) findViewById(R.id.spinnerGrade);
        String letterGrade = spinner.getSelectedItem().toString();

        numOfCredits = (EditText)findViewById(R.id.credits);
        String cred = numOfCredits.getText().toString();
        if(cred.length() != 0){
            numOfCredits.setText("");
        }
        if(cred.isEmpty()){
            cred = "0";
        }

        tripleString newData = new tripleString(course, letterGrade, cred);

        //Adam's Code for adding a new row to a table from GradeCalc.java
        //Lines 154-171
        gradeTableDataArray.add(newData);
        TableRow tbrow = new TableRow(this);
        TextView tv0 = new TextView(this);
        tv0.setText(course);
        tv0.setTextColor(Color.BLACK);
        tv0.setGravity(Gravity.CENTER);
        tbrow.addView(tv0);
        TextView tv1 = new TextView(this);
        tv1.setText(letterGrade);
        tv1.setTextColor(Color.BLACK);
        tv1.setGravity(Gravity.CENTER);
        tbrow.addView(tv1);
        TextView tv2 = new TextView(this);
        tv2.setText(cred);
        tv2.setTextColor(Color.BLACK);
        tv2.setGravity(Gravity.CENTER);
        tbrow.addView(tv2);
        gradeTablelayout.addView(tbrow);
    }

    //Task #48

    /**
     * Go through grade table, calculate the GPA and then display it.
     */
    private void calculate(){
        double gpa = 0.00;
        double credits = 0.00;
        //Get all values in user created list
        for(int i = 0; i < gradeTableDataArray.size(); i++){
            String creditCount = gradeTableDataArray.get(i).c;
            double _credits = Double.parseDouble(creditCount);
            credits += _credits;
            gpa += letterToPoints(gradeTableDataArray.get(i).b) * _credits;
        }
        if(credits <= 0.00){
            return;
        }
        gpa /= credits;
        //Get the "Average: " EditText item
        TextView tv = (TextView)findViewById(R.id.outputGpa);
        //Set the text to whatever the calculated avg is, 0.0 for an empty list
        tv.setText("GPA: " + Double.toString(gpa));
    }

    /**
     *
     * @param grade The letter grade [A, F]
     * @return The equilivent point grade [4.00, 0.00]
     */
    private double letterToPoints(String grade) {
        double retVal = 0.00;
        switch(grade)
        {
            case "A":
                retVal = 4.0;
                break;
            case "A-":
                retVal = 3.67;
                break;
            case "B+":
                retVal = 3.33;
                break;
            case "B":
                retVal = 3.00;
                break;
            case "B-":
                retVal = 2.67;
                break;
            case "C+":
                retVal = 2.33;
                break;
            case "C":
                retVal = 2.00;
                break;
            case "C-":
                retVal = 1.67;
                break;
            case "D+":
                retVal = 1.33;
                break;
            case "D":
                retVal = 1.00;
                break;
            case "D-":
                retVal = 0.67;
                break;
            default:
                retVal = 0.00;
                break;
        }
        return retVal;
    }


}

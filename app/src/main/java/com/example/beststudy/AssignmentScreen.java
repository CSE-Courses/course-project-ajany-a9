package com.example.beststudy;

import android.content.DialogInterface;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class AssignmentScreen extends AppCompatActivity {

    AssignmentDatabase db;

    private Button button;
    private EditText input;
    private ArrayList<AssignmentItem> assignments;
    private ArrayAdapter<AssignmentItem> arrayAdapter;
    private ListView listView;
    private Spinner monthSpinner;
    private Spinner daySpinner;
    private Spinner hourSpinner;
    private Spinner minuteSpinner;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks);

        db = new AssignmentDatabase(this);

        assignments = new ArrayList<>();
        button = findViewById(R.id.button);
        input = findViewById(R.id.editText2);
        listView = findViewById(R.id.listView);

        viewData();

        //User Story #8 Mark assignments as completed or in progress
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, final long id) {
                //Create a dialog
                AlertDialog.Builder Builder = new AlertDialog.Builder(AssignmentScreen.this);
                //Set Title
                Builder.setTitle("Assignment Status");
                //Gets the description of the selected assignment
                Builder.setMessage(assignments.get(position).mDescription);
                //Create button for In Progress
                Builder.setPositiveButton("In Progress", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //OnClick on In Progress will highlight the assignment on ListView Yellow
                        listView.getChildAt(position).setBackgroundColor(Color.rgb(255,255,102));
                        assignments.get(position).mStatus = "inProgress";
                        db.updateData(assignments.get(position).mDescription, "inProgress");
                    }
                });
                //Create button for Completed
                Builder.setNegativeButton("Completed", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //OnClick on Completed will highlight the assignment on ListView Green
                        listView.getChildAt(position).setBackgroundColor(Color.rgb(204,255,204));
                        assignments.get(position).mStatus = "completed";
                        db.updateData(assignments.get(position).mDescription, "completed");
                    }
                });
                Builder.show();
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            private final Random random = new Random();

            //User Story #4 Random Encouragement Message
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                String[] toastMessages = new String[]{"Good Job!", "Awesome!", "Well Done!", "Nice!",
                        "Keep up the good work!", "You made it!"};
                int randomIdx = random.nextInt(toastMessages.length-1);
                Toast toast = Toast.makeText(getApplicationContext(), toastMessages[randomIdx], '5');
                toast.setGravity(Gravity.CENTER_VERTICAL,0,0);
                toast.show();

                db.removeData(assignments.get(i).mDescription);
                assignments.remove(i);
                arrayAdapter.notifyDataSetChanged();
                return true;
            }
        });

        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                monthSpinner=(Spinner) findViewById(R.id.month);
                String monthS = monthSpinner.getSelectedItem().toString();

                daySpinner=(Spinner) findViewById(R.id.day);
                String dayS = daySpinner.getSelectedItem().toString();

                hourSpinner=(Spinner) findViewById(R.id.hour);
                String hourS = hourSpinner.getSelectedItem().toString();

                minuteSpinner=(Spinner) findViewById(R.id.minute);
                String minute = minuteSpinner.getSelectedItem().toString();

                String description = monthS+"/"+dayS+" "+hourS+":"+minute+"   "+input.getText().toString();
                String status = "UNSTARTED";

                //Description and status of assignment is added in db
                if(!description.equals("") && db.insertData(description, status)){
                    Toast.makeText(AssignmentScreen.this,"Assignment Added", Toast.LENGTH_SHORT).show();
                    input.setText("");
                    assignments.clear();
                    viewData();
                }else{
                    Toast.makeText(AssignmentScreen.this,"Assignment Not Added", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void viewData(){
        Cursor cursor = db.viewData();

        if(cursor.getCount() == 0){
            Toast.makeText(this, "No assignment to show", Toast.LENGTH_SHORT).show();
        }
        else{
            while(cursor.moveToNext()){
                AssignmentItem item = new AssignmentItem("","");
                item.mDescription = cursor.getString(1);
                item.mStatus = cursor.getString(2);
                assignments.add(item);
            }

            int j =0;

            for(j=0;j<assignments.size();j++) {
                int i = 0;
                String monthS = "";
                String dayS = "";
                String hourS = "";
                String minuteS = "";
                int cursor1 = 0;
                String name = assignments.get(i).mDescription;
                for (i = 0; i < name.length(); i = i + 1) {
                    cursor1++;
                    if (name.charAt(i) == '/') {
                        break;
                    }
                    monthS = monthS + name.charAt(i);
                }
                for (i = cursor1; i < name.length(); i = i + 1) {
                    cursor1++;
                    if (name.charAt(i) == ' ') {
                        break;
                    }
                    dayS = dayS + name.charAt(i);

                }
                for (i = cursor1; i < name.length(); i = i + 1) {
                    cursor1++;
                    if (name.charAt(i) == ':') {
                        break;
                    }
                    hourS = hourS + name.charAt(i);
                }
                for (i = cursor1; i < name.length(); i = i + 1) {
                    cursor1++;
                    if (name.charAt(i) == ' ') {
                        break;
                    }
                    minuteS = minuteS + name.charAt(i);
                }

                int month = Integer.valueOf(monthS);
                int day = Integer.valueOf(dayS);
                int hour = Integer.valueOf(hourS);
                int minute = Integer.valueOf(minuteS);
                int h;
            }

            arrayAdapter = new AssignmentAdapter(this, assignments);
            listView.setAdapter(arrayAdapter);
        }
    }
}

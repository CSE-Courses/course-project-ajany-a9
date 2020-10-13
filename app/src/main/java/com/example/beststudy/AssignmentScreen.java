package com.example.beststudy;

import android.database.Cursor;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

import androidx.appcompat.app.AppCompatActivity;

public class AssignmentScreen extends AppCompatActivity {

    Database db;

    private Button button;
    private EditText input;
    private ArrayList<String> assignments;
    private ArrayAdapter<String> arrayAdapter;
    private ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks);

        db = new Database(this);

        assignments = new ArrayList<>();
        button = findViewById(R.id.button);
        input = findViewById(R.id.editText2);
        listView = findViewById(R.id.listView);

        viewData();

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

                db.removeData(assignments.get(i));
                assignments.remove(i);
                arrayAdapter.notifyDataSetChanged();
                return true;
            }
        });

        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String description = input.getText().toString();
                if(!description.equals("") && db.insertData(description)){
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
        }else{
            while(cursor.moveToNext()){
                assignments.add(cursor.getString(1));
            }
            arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1, assignments);
            listView.setAdapter(arrayAdapter);
        }
    }
}

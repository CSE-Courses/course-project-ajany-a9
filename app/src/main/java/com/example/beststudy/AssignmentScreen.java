package com.example.beststudy;

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
    private ArrayList<String> assignments;
    private ArrayAdapter<String> arrayAdapter;
    private ListView listView;
    private Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks);

        listView = findViewById(R.id.listView);
        button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                addAssignment(view);
            }
        });

        assignments = new ArrayList<String>();
        arrayAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,assignments);
        listView.setAdapter(arrayAdapter);
        listViewListener();
    }

    private void listViewListener(){
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            private final Random random = new Random();

            //USER STORY #4 Random Encouragement Message
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                String[] toastMessages = new String[]{"Good Job!", "Awesome!", "Well Done!", "Nice!",
                        "Keep up the good work!", "You made it!"};
                int randomIdx = random.nextInt(toastMessages.length-1);
                Toast toast = Toast.makeText(getApplicationContext(), toastMessages[randomIdx], '5');
                toast.setGravity(Gravity.CENTER_VERTICAL,0,0);
                toast.show();

                assignments.remove(i);
                arrayAdapter.notifyDataSetChanged();
                return true;
            }
        });
    }

    private void addAssignment(View view){
        EditText input = findViewById(R.id.editText2);
        String description = input.getText().toString();

        if(!(description.equals(""))){
            arrayAdapter.add(description);
            input.setText("");
        }
        else{
            Toast.makeText(getApplicationContext(),"Please add in a valid description",Toast.LENGTH_LONG).show();
        }
    }

}

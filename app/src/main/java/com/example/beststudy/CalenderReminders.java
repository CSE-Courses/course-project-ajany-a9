package com.example.beststudy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import android.text.Html;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

/* Simple class to help make date calculating easier */
class dateTuple{
    public int month;
    public int day;
    public int year;
    public int hour;

    public dateTuple(int a1, int a2, int a3, int a4){
        this.year = a1;
        this.month = a2;
        this.day = a3;
        this.hour = a4;
    }
}

public class CalenderReminders extends AppCompatActivity {
    CalendarView calenderView;
    TextView dateView;
    TextView currDateRems;

    public int _startHours;
    public int _repeatHours;
    public int _repeatMillsInterval;
    public int _selectedYear;
    public int _selectedMonth;
    public int _selectedDay;

    boolean dateChosenYet = false;

    static HashMap<String, String> remDataMap = new HashMap<String, String>();
    NotificationManagerCompat notifManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminders);

        createNotifChannel();
        createAlertChannel();
        initDropdownBars();

        calenderView = (CalendarView)findViewById(R.id.calender_view);
        dateView = (TextView)findViewById(R.id.date_view);
        currDateRems = findViewById(R.id.rems_for_date);

        //Button for adding reminders
        final Button buttonAddReminder = findViewById(R.id.add_rem_button);
        buttonAddReminder.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(!dateChosenYet){
                    currDateRems.setText("You haven't chosen a date yet!");
                }
                else{
                    addReminder();
                    setAlarm();
                    giveNotif();
                }
            }
        });

        //Button for clearing reminders
        final Button buttonClearReminders = findViewById(R.id.clear_rem_button);
        buttonClearReminders.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                clearReminders();
            }
        });

        // Calender listener for date selection
        calenderView.setOnDateChangeListener(
                new CalendarView.OnDateChangeListener() {
                    @Override
                    public void onSelectedDayChange(
                            @NonNull CalendarView view,
                            int year,
                            int month,
                            int dayOfMonth
                    ){
                        String Date =  (month + 1) + "-" + dayOfMonth + "-" + year;
                        _selectedMonth = month + 1;
                        _selectedDay = dayOfMonth;
                        _selectedYear = year;

                        dateChosenYet = true;

                        dateView.setText(Date);
                        showDateReminders();
                    }
                });

        notifManager = NotificationManagerCompat.from(this);

    }//end of onCreate function

    /**
     * Set a timed alert/notification, whether it repeats or not is based on if the respective
     * dropdown bar is at 0 or not.
     */
    private void setAlarm(){
        Intent intent = new Intent(CalenderReminders.this,NotificationReceiver.class);
        PendingIntent pIntent = PendingIntent.getBroadcast(CalenderReminders.this,442,intent,0);
        AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);


        dateTuple dt = dateToTuple(_selectedYear,_selectedMonth,_selectedDay);

        Calendar cal = Calendar.getInstance();

        cal.set(Calendar.YEAR, dt.year);
        cal.set(Calendar.MONTH, dt.month-1);
        cal.set(Calendar.DAY_OF_MONTH, dt.day);
        cal.set(Calendar.HOUR_OF_DAY, dt.hour);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        if(_repeatHours != 0){
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(),
                    _repeatMillsInterval, pIntent);
        }
        else{
            alarmManager.set(AlarmManager.RTC_WAKEUP,cal.getTimeInMillis(),pIntent);
        }

    }

    /* Return a dateTuple object containing values to be used in the alert creation */
    public dateTuple dateToTuple(int year, int month, int day){
        int newYear = year ,newMonth = month ,newDay = day,newHour=0;

        int hoursToSubtract = _startHours;
        while(hoursToSubtract > 0){
            hoursToSubtract--;
            newHour--;
            if(newHour < 0){
                newHour = 23;
                newDay--;
                if(newDay < 1){
                    newDay = 30;
                    newMonth--;
                    if(newMonth < 1){
                        newMonth = 12;
                        newYear--;
                    }
                }
            }
        }

        return new dateTuple(newYear,newMonth,newDay,newHour);
    }

    /* Give an alert notifiying that a reminder has been set */
    private void giveNotif() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "calRems")
                .setSmallIcon(R.drawable.ic_baseline_access_alarm_24)
                .setContentTitle("Reminder added")
                .setContentText("Reminder set for " + dateView.getText().toString())
                .setPriority(NotificationCompat.PRIORITY_HIGH);

        notifManager.notify(100,builder.build());
    }

    /* Create the channel for the instant alert notifiying you set a reminder for DD-MM-YYYY */
    private void createNotifChannel(){
        if(Build.VERSION.SDK_INT >= 26){
            CharSequence name = "remChannel";
            String description = "Channel for reminders";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("calRems", name, importance);
            channel.setDescription(description);
            NotificationManager notifManager = getSystemService(NotificationManager.class);
            notifManager.createNotificationChannel(channel);
        }
    }

    /* Create the channel for the timed alerts */
    private void createAlertChannel(){
        if(Build.VERSION.SDK_INT >= 26){
            CharSequence name = "remChannel";
            String description = "Channel for reminders";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("timedRems", name, importance);
            channel.setDescription(description);

            NotificationManager notifManager = getSystemService(NotificationManager.class);
            notifManager.createNotificationChannel(channel);
        }
    }

    /**Given a 1 char string 'priorityValue', returns an html string
     * representing 'input' with a color ranging from blue to red
     */
    public String getPriorityColor(String priorityValue, String input){
        String output;
        switch(priorityValue)
        {
            case "0"://black
                output = "<font color=#000000>" + input + "</font>";
                break;

            case "1"://blue
                output = "<font color=#0000ff>" + input + "</font>";
                break;

            case "2":
                output = "<font color=#0fa7ff>" + input + "</font>";
                break;

            case "3":
                output = "<font color=#00ffff>" + input + "</font>";
                break;

            case "4":
                output = "<font color=#02ff7c>" + input + "</font>";
                break;

            case "5":
                output = "<font color=#00ff00>" + input + "</font>";
                break;

            case "6":
                output = "<font color=#caff1c>" + input + "</font>";
                break;

            case "7":
                output = "<font color=#ffdd00>" + input + "</font>";
                break;

            case "8":
                output = "<font color=#ffe414>" + input + "</font>";
                break;

            case "9":
                output = "<font color=#ff6f00>" + input + "</font>";
                break;

            case "10"://red
                output = "<font color=#ff0000>" + input + "</font>";
                break;

            default:
                output = "<font color=#000000>" + input + "</font>";
                break;
        }
        return output;
    }

    /** Add a reminder to a date, also gets values for when to start sending alerts relartive
     * to a date and whether/how often the alert should be repeated.
     */
    public void addReminder(){
        Spinner prioritySpinner = (Spinner) findViewById(R.id.spinner_priorities);
        String priorityValue = prioritySpinner.getSelectedItem().toString();

        String date = dateView.getText().toString();
        EditText inputRem = findViewById(R.id.reminder_entry);
        String newReminder = "(" + priorityValue + ") " + inputRem.getText().toString();
        String oldReminders = remDataMap.get(date);
        if(oldReminders == null || oldReminders == ""){
            oldReminders = " \n ";
        }
        String updatedRems = getPriorityColor(priorityValue, newReminder) + "<br>" + oldReminders;
        //currDateRems.setText(updatedRems);
        currDateRems.setText(Html.fromHtml(updatedRems));
        Html.escapeHtml(updatedRems);
        remDataMap.put(date,updatedRems);
        inputRem.getText().clear();

        Spinner repeatSpinner = (Spinner) findViewById(R.id.spinner_hours_repeat);
        String repeatValue = repeatSpinner.getSelectedItem().toString();
        if(repeatValue == "TEST(1min)"){
            _repeatHours =  -1;
            _repeatMillsInterval = 10000; //repeat every 10 seconds but i believe the min is 60000
        }
        else{
            _repeatHours = Integer.parseInt(repeatValue);
            _repeatMillsInterval =  _repeatHours * 60 * 60 * 1000;
        }

        Spinner startSpinner = (Spinner) findViewById(R.id.spinner_hours_start);
        String startValue = startSpinner.getSelectedItem().toString();
        _startHours =  Integer.parseInt(startValue);

    }

    /* Show reminders of a selected date, or notify none exist */
    public void showDateReminders(){
        String rems = remDataMap.get(dateView.getText().toString());
        if(rems == null || rems == ""){
            rems = "No reminders for this date";
        }
        //currDateRems.setText(rems);
        currDateRems.setText(Html.fromHtml(rems));
        ScrollView sv = findViewById(R.id.scroll_rems);
        sv.scrollTo(0,0);
    }

    /* Clear the reminders for a selected date, also stops repeating alerts for that day */
    public void clearReminders(){
        remDataMap.put(dateView.getText().toString(), "");
        currDateRems.setText("No reminders for this date");
        ScrollView sv = findViewById(R.id.scroll_rems);
        sv.scrollTo(0,0);

        Intent intent = new Intent(CalenderReminders.this,NotificationReceiver.class);
        PendingIntent pIntent = PendingIntent.getBroadcast(CalenderReminders.this,442,intent,PendingIntent.FLAG_NO_CREATE);
        AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
        if(pIntent != null && alarmManager != null){
            alarmManager.cancel(pIntent);
        }

    }

    /* Initialize the drop down bars' values */
    private void initDropdownBars(){
        Spinner startDropdown = findViewById(R.id.spinner_hours_start);
        Spinner repeatDropdown = findViewById(R.id.spinner_hours_repeat);
        Spinner priorityDropdown = findViewById(R.id.spinner_priorities);
        String[] startHours = new String[]{"0","1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","36","48","72","96"};
        String [] repeatHours = new String[]{"TEST(1min)","0","1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","36","48","72","96"};
        String[] reminderPriorities = new String[]{"0","1","2","3","4","5","6","7","8","9","10"};
        ArrayAdapter<String> startAdapter =
                new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,startHours);
        ArrayAdapter<String> repeatAdapter =
                new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,repeatHours);
        ArrayAdapter<String> priorityAdapter =
                new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,reminderPriorities);
        startDropdown.setAdapter(startAdapter);
        repeatDropdown.setAdapter(repeatAdapter);
        priorityDropdown.setAdapter(priorityAdapter);
    }

}//end of class CalenderReminders

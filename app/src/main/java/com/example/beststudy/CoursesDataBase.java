package com.example.beststudy;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CoursesDataBase extends SQLiteOpenHelper {
    //Current database
    private static final String DatabaseName = " Courses.db";
    //current database table
    private static final String currentTable = "Course_Table";

    //Database Schema
    private static final String CName = "CourseName";
    private static final String StartTime = "StartTime";
    private static final String EndTime = "EndTime";
    private static final String Professor = "Professor";
    private static final String CourseDay = "CourseDay";
    private static final String Zoom = "ZoomLink";

    private boolean startUp = true;

    private static final String CREATE_TABLE = "CREATE TABLE "+ currentTable+" ("+ " INTEGER PRIMARY KEY, "+ CName + " TEXT," + StartTime + " TEXT,"+ EndTime + " TEXT," + Professor + " TEXT," + CourseDay + " TEXT," + Zoom+ " TEXT" + ")";

    public CoursesDataBase(Context context){
        super(context, DatabaseName, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ currentTable);
        onCreate(db);
    }

    public boolean insertCourse (String name, String start, String end, String instructor, String day, String links){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cV = new ContentValues();
        cV.put(CName, name);
        cV.put(StartTime, start);
        cV.put(EndTime, end);
        cV.put(Professor, instructor);
        cV.put(CourseDay, day);
        cV.put(Zoom, links);
        db.insert(currentTable, null, cV);
        return true;
    }

    public boolean removeCourse (String name){
        SQLiteDatabase db = this.getWritableDatabase();
        long removed = db.delete(currentTable, "CName=?", new String[]{name});
        return true;
    }

    public void OnUpgrade(SQLiteDatabase db, int OldVersion, int NewVersion){
        db.execSQL("DROP TABLE IF EXISTS " + currentTable);
        onCreate(db);
    }

    public Cursor viewData(){
        SQLiteDatabase db = this.getReadableDatabase();
        if(startUp){
            startUp = false;
            OnUpgrade(db, 1, 1);
        }
        String query = "Select * from " + currentTable;
        Cursor cursor = db.rawQuery(query, null);
        return cursor;
    }
}

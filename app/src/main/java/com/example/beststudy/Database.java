package com.example.beststudy;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class Database extends SQLiteOpenHelper {
    //name of the current database
    private static final String DB_NAME = "Assignments.db";
    //name of current database table
    private static final String DB_TABLE = "Assignments_Table";

    //Columns in the database
    private static final String NAME = "NAME";

    //The structure of the database-> table -> columns
    private static final String CREATE_TABLE = "CREATE TABLE "+ DB_TABLE+" ("+ " INTEGER PRIMARY KEY, "+ NAME+ " TEXT"+ ")";


    public Database(Context context){
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //execSQL: Executes a single SQL statement in this case checks if table exists
        db.execSQL("DROP TABLE IF EXISTS "+ DB_TABLE);
        //onCreate is called when database is created for the first time
        onCreate(db);
    }

    //create method to insert data
    public boolean insertData(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValue = new ContentValues();
        contentValue.put(NAME, name);


        long result = db.insert(DB_TABLE, null, contentValue);

        return result != -1;
    }

    //create method to remove data
    public boolean removeData(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(DB_TABLE, "NAME=?", new String[]{name});

        return result > 0;
    }

    //create method to view data
    public Cursor viewData(){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "Select * from "+DB_TABLE;
        Cursor cursor = db.rawQuery(query, null);

        return cursor;
    }
}

package com.example.beststudy;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class AssignmentDatabase extends SQLiteOpenHelper {
    //name of the database
    private static final String DB_NAME = "Assignments.db";
    //name of database table
    private static final String DB_TABLE = "Assignments_Table";

    //Columns in the database
    private static final String DESCRIPTION = "DESCRIPTION";
    private static final String STATUS = "STATUS";

    private static final String CREATE_TABLE = "CREATE TABLE "+ DB_TABLE+" (id "+ " INTEGER PRIMARY KEY, "+ DESCRIPTION + " TEXT, " + STATUS + " TEXT" + ")";

    private static boolean firstRun = true;

    public AssignmentDatabase(Context context){
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
    public boolean insertData(String assignment, String status){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValue = new ContentValues();
        contentValue.put(DESCRIPTION, assignment);
        contentValue.put(STATUS, status);


        long result = db.insert(DB_TABLE, null, contentValue);

        return result != -1;
    }

    //create method to remove data
    public boolean removeData(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(DB_TABLE, "DESCRIPTION=?", new String[]{name});

        return result > 0;
    }

    public boolean updateData(String name, String status){
        ContentValues contentValue = new ContentValues();
        contentValue.put(STATUS, status);

        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.update(DB_TABLE, contentValue, "DESCRIPTION=?", new String[]{name});

        return result > 0;
    }

    //create method to view data
    public Cursor viewData(){
        SQLiteDatabase db = this.getReadableDatabase();
        if (firstRun) {
            firstRun = false;
            onUpgrade(db, 1, 1);
        }
        String query = "Select * from "+DB_TABLE;
        Cursor cursor = db.rawQuery(query, null);

        return cursor;
    }
}

package com.example.beststudy;

import android.content.ContentValues;
import android.content.Context;
import android.database.ContentObservable;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class scheduleDatabase extends SQLiteOpenHelper {
    public static final String CLASS_NAME = "CLASS_NAME";
    public static final String SCHEDULE_TABLE = "SCHEDULE_TABLE";
    public static final String ROW_SCHEDULE = "ROW_SCHEDULE";
    public static final String COLON_SCHEDULE = "COLON_SCHEDULE";

    public scheduleDatabase(@Nullable Context context) {
        super(context, "schedule.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createScheduleTable= "CREATE TABLE " + SCHEDULE_TABLE + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, "
                + CLASS_NAME + " TEXT, " + ROW_SCHEDULE + " INT, " + COLON_SCHEDULE + " INT)";

        db.execSQL(createScheduleTable);
    }

    public boolean insertClass(int i, int j, String classInfo){
        SQLiteDatabase db =this.getWritableDatabase();
        ContentValues cv =new ContentValues();
        cv.put(CLASS_NAME, classInfo);
        cv.put(ROW_SCHEDULE, j);
        cv.put(COLON_SCHEDULE, i);

        Long insert = db.insert(SCHEDULE_TABLE, null, cv);
        if(insert==-1){
            return false;
        }
        else {
            return true;
        }
    }

    public Cursor scheduleDataCursor(){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "Select * from "+SCHEDULE_TABLE;
        Cursor cursor = db.rawQuery(query, null);

        return cursor;
    }
    public boolean removeData(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(SCHEDULE_TABLE, id, null);

        return result > 0;
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

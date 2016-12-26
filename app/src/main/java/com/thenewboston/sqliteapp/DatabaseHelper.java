package com.thenewboston.sqliteapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Hytham on 12/25/2016.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Student.db";
    //private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "Student_table";
    private static final String COL_1 = "ID";
    private static final String COL_2 = "NAME";
    private static final String COL_3 = "SURNAME";
    private static final String COL_4 = "MARKS";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table " + TABLE_NAME +"(ID Integer primary key autoincrement,NAME Text,SURNAME TEXT,"+
                "MARKS INTEGER);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
   db.execSQL("DROP TABLE IF EXIST " + TABLE_NAME);
        onCreate(db);
    }
    public boolean insert(String  name , String surname ,String marks){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_2 , name);
        values.put(COL_3 , surname);
        values.put(COL_4 ,marks);
         long result = db.insert(TABLE_NAME , null ,values);
        if (result == -1)
            return false;
        else
            return true;

    }
    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " +TABLE_NAME ,null);
        return res;
    }
    public boolean updateData(String id ,String  name , String surname ,String marks){
        SQLiteDatabase db =this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_1 , id);
        values.put(COL_2 , name);
        values.put(COL_3 , surname);
        values.put(COL_4 , marks);
        db.update(TABLE_NAME ,values, "ID = ?" , new String[] {id});
        return true;
    }
    public Integer deleteData(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME , "ID = ?" ,new String[] {id});
    }
}

package com.example.user.kelasinid;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "DatabaseHelper";

    private static final String TABLE_NAME = "to_do_table";
    private static final String COL1 = "ID";
    private static final String COL2 = "title";
    private static final String COL3 = "date";
    private static final String COL4 = "time";
    private static final String COL5 = "point";
    private static final String COL6 = "ischecked";


    public DatabaseHelper(Context context) {
        super(context, TABLE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL2 + " TEXT," + COL3 + " TEXT," + COL4 + " TEXT," + COL5 + " TEXT," + COL6 + " TEXT)";
        Log.d(TAG, "onCreate: " + createTable);
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP  TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean addData(toDo addedToDo) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL1, addedToDo.getId());
        contentValues.put(COL2, addedToDo.getTitle());
        contentValues.put(COL3, addedToDo.getDate());
        contentValues.put(COL4, addedToDo.getTime());
        contentValues.put(COL5, addedToDo.getPoint());
        contentValues.put(COL6, "isChecked");

        Log.d(TAG, "addData: Adding " + addedToDo.getTitle() + " to " + TABLE_NAME);

        long result = db.insert(TABLE_NAME, null, contentValues);

        //if date as inserted incorrectly it will return -1
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }


    public ArrayList<toDo> getData() {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor data = db.rawQuery(query, null);
        Cursor cursor = db.rawQuery(query, null);
        toDo todo;
        ArrayList<toDo> model = new ArrayList<toDo>();

        if (cursor.moveToFirst()) {
            do {
                todo = new toDo();
                todo.setId(cursor.getString(cursor.getColumnIndex(COL1)));
                todo.setTitle(cursor.getString(cursor.getColumnIndex(COL2)));
                todo.setDate(cursor.getString(cursor.getColumnIndex(COL3)));
                todo.setTime(cursor.getString(cursor.getColumnIndex(COL4)));
                todo.setPoint(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COL5))));
                todo.setChecked(Boolean.parseBoolean(cursor.getString(cursor.getColumnIndex(COL6))));
                model.add(todo);
                Log.d(TAG, "getData: Date" + cursor.getString(cursor.getColumnIndex(COL3)));
                Log.d(TAG, "getData: Time" + cursor.getString(cursor.getColumnIndex(COL4)));
            } while (cursor.moveToNext());
        }


        return model;
    }


    public void updateData(String id, String title, String Date, String Time, int Point, boolean isChecked, String TextStatus) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE " + TABLE_NAME + " SET " + COL2 + " = '" + title + "'," + COL3 + " = '" + Date + "'," + COL4 + " = '" + Time + "'," + COL5 + " = '" + Point + "'," + COL6 + " = '" + isChecked + "'" +
                " WHERE " + COL1 + " = " + id;
        Log.d(TAG, "updateName: query: " + query);
        Log.d(TAG, "updateName: Setting name to " + title);
        db.execSQL(query);
    }

    public void updatePoint(String id, int Point, boolean isChecked) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE " + TABLE_NAME + " SET " + COL5 + " = '" + Point + "'," + COL6 + " = '" + isChecked + "'" +
                " WHERE " + COL1 + " = " + id;
        Log.d(TAG, "updateName: query: " + query);
        Log.d(TAG, "updateName: Setting point to " + Point);
        db.execSQL(query);
    }

    public void deleteName(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + TABLE_NAME + " WHERE "
                + COL1 + " = '" + id + "'";
        Log.d(TAG, "deleteName: query: " + query);
        Log.d(TAG, "deleteName: Deleting" + id + "from database.");
        db.execSQL(query);
    }
}

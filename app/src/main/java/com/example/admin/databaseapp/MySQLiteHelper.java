package com.example.admin.databaseapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by admin on 5/2/2017.
 */

public class MySQLiteHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Students.db";
    private static final String TABLE_STUDENTS = "students";

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_STUDENTNAME = "name";
    public static final String COLUMN_STUDENTSURNAME = "surname";
    public static final String COLUMN_GRADE = "grade";
    public MySQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_PRODUCTS_TABLE = "CREATE TABLE " +
                TABLE_STUDENTS + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY," + COLUMN_STUDENTNAME
                + " TEXT,"+ COLUMN_STUDENTSURNAME
                + " TEXT," + COLUMN_GRADE+ " INTEGER" + ")";
        db.execSQL(CREATE_PRODUCTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STUDENTS);
        onCreate(db);
    }
    public void addStudent(Student student) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_STUDENTNAME, student.getName());
        values.put(COLUMN_STUDENTSURNAME, student.getSurname());
        values.put(COLUMN_GRADE, student.getGrade());
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_STUDENTS, null, values);
        db.close();
    }
    public Student findStudent(String studentName) {
        String query = "Select * FROM " + TABLE_STUDENTS + " WHERE " + COLUMN_STUDENTNAME + " =  \"" + studentName + "\"";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Student student = new Student();
        if (cursor.moveToFirst()) {
            cursor.moveToFirst();

            student.setId(Integer.parseInt(cursor.getString(0)));
            student.setName(cursor.getString(1));
            student.setSurname(cursor.getString(2));
            student.setGrade(Integer.parseInt(cursor.getString(3)));
            cursor.close();
        } else {
            student = null;
        }
        db.close();
        return student;
    }
    public boolean deleteStudent(String name) {
        boolean result = false;
        String query = "Select * FROM " + TABLE_STUDENTS + " WHERE " + COLUMN_STUDENTNAME + " =  \"" + name + "\"";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Student student = new Student();
        if (cursor.moveToFirst()) {
            student.setId(Integer.parseInt(cursor.getString(0)));
            db.delete(TABLE_STUDENTS, COLUMN_ID + " = ?",
                    new String[] { String.valueOf(student.getId()) });
            cursor.close();
            result = true;
        }
        db.close();
        return result;
    }
}

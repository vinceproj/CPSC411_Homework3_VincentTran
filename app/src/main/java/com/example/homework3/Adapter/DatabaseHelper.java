/*
Vincent Tran
891036956
CPSC 411
HW Assignment #3
*/

package com.example.homework3.Adapter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.homework3.Model.CourseEnrollment;
import com.example.homework3.Model.Student;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
//Helper class for database handling

    //Required constructor
    public DatabaseHelper(@Nullable Context context) {
        super(context, "students.db", null, 1);
    }

    //Create tables if they do not exist
    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("CREATE TABLE IF NOT EXISTS STUDENTS (firstName Text, lastName Text, CWID INTEGER)");
        db.execSQL("CREATE TABLE IF NOT EXISTS COURSES (CourseID Text, Grade Text, Student INTEGER)");
    }

    //Delete and recreate tables when necessary
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS STUDENTS");
        db.execSQL("DROP TABLE IF EXISTS COURSES");
        onCreate(db);
    }

    //Add a row to STUDENTS table
    public boolean insertDataStudent(String firstName, String lastName, Integer CWID){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("firstName", firstName);
        contentValues.put("lastName", lastName);
        contentValues.put("CWID", CWID);

        long result = db.insert("STUDENTS", null, contentValues);

        if(result==-1) {
            return false;
        }else
            return true;
    }

    //Add a row to COURSES table
    public boolean insertDataCourses(String courseID, String grade, Integer studentID){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("CourseID", courseID);
        contentValues.put("Grade", grade);
        contentValues.put("Student", studentID);

        long result = db.insert("COURSES", null, contentValues);

        if(result == -1){
            return false;
        }else
            return true;
    }

    //Get all student data from STUDENTS table; Returns an ArrayList for use
    public ArrayList<Student> getAllData(){
        ArrayList<Student> arrayList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM STUDENTS", null);

        //Cursor is 0 indexed for value traversal
        while(cursor.moveToNext()){
            String firstName = cursor.getString(0);
            String lastName = cursor.getString(1);
            Integer CWID = cursor.getInt(2);
            Student student = new Student(firstName, lastName, CWID);

            arrayList.add(student);

        }
        return arrayList;
    }

    //Get all course data from COURSES table; Returns an ArrayList for use
    public ArrayList<CourseEnrollment> getAllCourses(){
        ArrayList<CourseEnrollment> arrayList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM COURSES", null);

        //Cursor is 0 indexed for value traversal
        while(cursor.moveToNext()){
            String courseID = cursor.getString(0);
            String grade = cursor.getString(1);
            Integer CWID = cursor.getInt(2);
            CourseEnrollment course = new CourseEnrollment(courseID, grade, CWID);

            arrayList.add(course);
        }
        return arrayList;
    }

}

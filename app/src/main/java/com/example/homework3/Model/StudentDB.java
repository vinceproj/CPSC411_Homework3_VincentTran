package com.example.homework3.Model;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.homework3.Adapter.DatabaseHelper;
import com.example.homework3.StudentActivity;

import java.io.File;
import java.util.ArrayList;

public class StudentDB {

    DatabaseHelper databaseHelper;

    private static final StudentDB ourInstance = new StudentDB();

    private ArrayList<Student> mStudentList;

    static public StudentDB getInstance() { return ourInstance;}

    private StudentDB(){
        createStudentObjects();
    }

    public ArrayList<Student> getStudentList(){return mStudentList;}

    public void setStudentList(ArrayList<Student> studentList){mStudentList = studentList;}

    protected void createStudentObjects(){


        mStudentList = new ArrayList<Student>();

        Student student = new Student("Vincent", "Tran", 891036956);
        ArrayList<CourseEnrollment> courseList = new ArrayList<CourseEnrollment>();
        courseList.add(new CourseEnrollment("CPSC411", "A"));
        courseList.add(new CourseEnrollment("CPSC454", "A"));
        student.setCourseList(courseList);
        insertStudent("Vincent", "Tran", 891036956);
        insertCourse("CPSC411", "A", 891036956);
        insertCourse("CPSC454", "A", 891036956);
        mStudentList.add(student);


        student = new Student("Ari", "Arcebal", 891096024);
        courseList = new ArrayList<CourseEnrollment>();
        courseList.add(new CourseEnrollment("CPSC411", "A"));
        courseList.add(new CourseEnrollment("CPSC454", "A"));
        student.setCourseList(courseList);
        insertStudent("Ari", "Arcebal", 891096024);
        insertCourse("CPSC411", "A", 891096024);
        insertCourse("CPSC454", "A", 891096024);
        mStudentList.add(student);
    }

    public void insertStudent(String firstName, String lastName, Integer CWID){
        boolean result = databaseHelper.insertDataStudent(firstName, lastName, CWID);
    }

    public void insertCourse(String courseID, String grade, Integer studentID){
        boolean result = databaseHelper.insertDataCourses(courseID, grade, studentID);
    }
}

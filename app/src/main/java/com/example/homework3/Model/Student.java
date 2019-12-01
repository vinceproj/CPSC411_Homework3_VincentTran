package com.example.homework3.Model;

import java.util.ArrayList;

public class Student {

    protected String mFirstName;
    protected String mLastName;
    protected Integer mCWID;

    protected ArrayList<CourseEnrollment> mCourseList;

    public String getFirstName() {
        return mFirstName;
    }

    public void setFirstName(String firstName) {
        mFirstName = firstName;
    }

    public String getLastName() {
        return mLastName;
    }

    public void setLastName(String lastName) {
        mLastName = lastName;
    }

    public Integer getCWID() {
        return mCWID;
    }

    public void setCWID(Integer CWID) {
        mCWID = CWID;
    }

    public ArrayList<CourseEnrollment> getCourseList() {
        return mCourseList;
    }

    public void setCourseList(ArrayList<CourseEnrollment> courseList) {
        mCourseList = courseList;
    }

    public Student(String fName, String lName, Integer CWID){
        mFirstName = fName;
        mLastName = lName;
        mCWID = CWID;
    }
}

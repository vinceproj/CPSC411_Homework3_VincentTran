package com.example.homework3.Model;

public class CourseEnrollment {

    protected String mCourseID;
    protected String mGrade;
    protected Integer mOwner;

    public String getCourseID() {
        return mCourseID;
    }

    public void setCourseID(String courseID) {
        mCourseID = courseID;
    }

    public String getGrade() {
        return mGrade;
    }

    public void setGrade(String grade) {
        mGrade = grade;
    }

    public CourseEnrollment(String ID, String grade){
        mCourseID = ID;
        mGrade = grade;
    }

    public CourseEnrollment(String ID, String grade, Integer owner){
        mCourseID = ID;
        mGrade = grade;
        mOwner = owner;
    }
}


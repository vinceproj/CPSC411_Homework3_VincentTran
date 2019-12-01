/*
Vincent Tran
891036956
CPSC 411
HW Assignment #3
*/

package com.example.homework3;

import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.homework3.Adapter.DatabaseHelper;
import com.example.homework3.Adapter.SummaryListAdapter;
import com.example.homework3.Model.CourseEnrollment;
import com.example.homework3.Model.Student;
import com.example.homework3.Model.StudentDB;

import java.io.File;
import java.util.ArrayList;

public class SummaryLVActivity extends AppCompatActivity {

    //Vars for handling
    Integer addCourseCounter = 0;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Set view
        setContentView(R.layout.activity_student_detail);

        //Create database helper for database handling
        databaseHelper = new DatabaseHelper(this);
        //Create event handler for button; use function for procedure
        Button button = (Button)findViewById(R.id.add_course_button_id);//Save
        button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                showCourseET();
            }
        });


    }//End of onCreate

    //Handles displaying EditText visibility
    public void showCourseET(){
        if(addCourseCounter<3){
            addCourseCounter++;
        }
        switch (addCourseCounter){
            case 1:
                EditText et_CWID = findViewById(R.id.s_new_CWIDOne_id);
                et_CWID.setVisibility(View.VISIBLE);
                EditText et_Grade = findViewById(R.id.s_newGradeOne_id);
                et_Grade.setVisibility(View.VISIBLE);

                break;
            case 2:
                et_CWID = findViewById(R.id.s_new_CWIDTwo_id);
                et_CWID.setVisibility(View.VISIBLE);
                et_Grade = findViewById(R.id.s_newGradeTwo_id);
                et_Grade.setVisibility(View.VISIBLE);
                break;

            case 3:
                et_CWID = findViewById(R.id.s_new_CWIDThree_id);
                et_CWID.setVisibility(View.VISIBLE);
                et_Grade = findViewById(R.id.s_newGradeThree_id);
                et_Grade.setVisibility(View.VISIBLE);
                Toast.makeText(SummaryLVActivity.this, "Only three courses may be added to a single student", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    //Create menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.summarylv_activity_menu, menu);
        return true;
    }

    //Handle menu
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        //If "Done" selected
        if(item.getItemId() == R.id.action_Done){
            //Handle firstName, lastName, and CWID
            try{
                Intent intent = new Intent(SummaryLVActivity.this, StudentActivity.class);
                EditText editView = findViewById(R.id.s_firstName_id);
                String firstName = editView.getText().toString();
                editView = findViewById(R.id.s_lastName_id);
                String lastName = editView.getText().toString();
                editView = findViewById(R.id.s_CWID_id);
                String CWID_get = editView.getText().toString();
                Integer CWID_set = Integer.parseInt(CWID_get);
                insertStudent(firstName, lastName, CWID_set);//Call database helper function to send student to database
                Student s = new Student(firstName, lastName, CWID_set);



                //Handle Courses; Will accept blank strings for courses
                try {
                    ArrayList<CourseEnrollment> courseList = new ArrayList<CourseEnrollment>();
                    switch (addCourseCounter) {
                        case 1:
                            EditText et_CWID = findViewById(R.id.s_new_CWIDOne_id);
                            String tempCWID = et_CWID.getText().toString();
                            EditText et_Grade = findViewById(R.id.s_newGradeOne_id);
                            String tempGrade = et_Grade.getText().toString();
                            courseList.add(new CourseEnrollment(tempCWID, tempGrade));
                            insertCourse(tempCWID, tempGrade, CWID_set); //Call database helper function to send course to database
                            s.setCourseList(courseList);
                            break;
                        case 2:
                            et_CWID = findViewById(R.id.s_new_CWIDOne_id);
                            tempCWID = et_CWID.getText().toString();
                            et_Grade = findViewById(R.id.s_newGradeOne_id);
                            tempGrade = et_Grade.getText().toString();
                            courseList.add(new CourseEnrollment(tempCWID, tempGrade));
                            insertCourse(tempCWID, tempGrade, CWID_set);//Call database helper function to send course to database
                            et_CWID = findViewById(R.id.s_new_CWIDTwo_id);
                            tempCWID = et_CWID.getText().toString();
                            et_Grade = findViewById(R.id.s_newGradeTwo_id);
                            tempGrade = et_Grade.getText().toString();
                            courseList.add(new CourseEnrollment(tempCWID, tempGrade));
                            insertCourse(tempCWID, tempGrade, CWID_set);//Call database helper function to send course to database
                            s.setCourseList(courseList);

                            break;
                        case 3:
                            et_CWID = findViewById(R.id.s_new_CWIDOne_id);
                            tempCWID = et_CWID.getText().toString();
                            et_Grade = findViewById(R.id.s_newGradeOne_id);
                            tempGrade = et_Grade.getText().toString();
                            courseList.add(new CourseEnrollment(tempCWID, tempGrade));
                            insertCourse(tempCWID, tempGrade, CWID_set);//Call database helper function to send course to database
                            et_CWID = findViewById(R.id.s_new_CWIDTwo_id);
                            tempCWID = et_CWID.getText().toString();
                            et_Grade = findViewById(R.id.s_newGradeTwo_id);
                            tempGrade = et_Grade.getText().toString();
                            courseList.add(new CourseEnrollment(tempCWID, tempGrade));
                            insertCourse(tempCWID, tempGrade, CWID_set);//Call database helper function to send course to database
                            et_CWID = findViewById(R.id.s_new_CWIDThree_id);
                            tempCWID = et_CWID.getText().toString();
                            et_Grade = findViewById(R.id.s_newGradeThree_id);
                            tempGrade = et_Grade.getText().toString();
                            courseList.add(new CourseEnrollment(tempCWID, tempGrade));
                            insertCourse(tempCWID, tempGrade, CWID_set);//Call database helper function to send course to database
                            s.setCourseList(courseList);
                            break;
                    }
                }
                catch(Exception e) {
                    Toast.makeText(SummaryLVActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                }

                //Set new student; No longer needed as adapter generates based on database; Will produce error due to lack of independent StudentDB instantiation
                //StudentDB.getInstance().getStudentList().add(s);


                //Nav to page
                startActivity(intent); }//End of try
            catch(Exception e){
                Toast.makeText(SummaryLVActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            }//End of catch
        }
        return super.onOptionsItemSelected(item);
    }//End of menu item action

    //Call for database functions; Necessary for usability
    public void insertStudent(String firstName, String lastName, Integer CWID){
        boolean result = databaseHelper.insertDataStudent(firstName, lastName, CWID);

        if(result){
        }else
            Toast.makeText(getApplicationContext(), "Fail", Toast.LENGTH_SHORT).show();
    }

    //Call for database functions; Necessary for usability
    public void insertCourse(String courseID, String grade, Integer studentID){
        boolean result = databaseHelper.insertDataCourses(courseID, grade, studentID);

        if(result){
        }else
            Toast.makeText(getApplicationContext(), "Fail", Toast.LENGTH_SHORT).show();
    }


   }

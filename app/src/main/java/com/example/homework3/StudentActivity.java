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
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.homework3.Adapter.DatabaseHelper;
import com.example.homework3.Adapter.MyAdapter;
import com.example.homework3.Adapter.SummaryListAdapter;
import com.example.homework3.Model.Student;

import java.io.File;
import java.util.ArrayList;

public class StudentActivity extends AppCompatActivity {

    //Set vars for long term use
    DatabaseHelper databaseHelper;
    ListView l1;
    ArrayList<Student> arrayList;
    MyAdapter myAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Set view
        setContentView(R.layout.student_list_lv);

        //Set vars for handling
        databaseHelper = new DatabaseHelper(this);
        l1 = (ListView) findViewById(R.id.summary_list_id);
        arrayList = new ArrayList<>();

        //Call function that gets database STUDENTS table info
        loadDataInListView(l1);

    }


    @Override
    protected void onStart(){
        super.onStart();
    }

    //Create menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.student_activity_menu, menu);
        return true;
    }

    //Define actions for menu
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        if(item.getItemId() == R.id.action_Add){
            Intent intent = new Intent(StudentActivity.this, SummaryLVActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    //Send adapter activity and arrayList generated from call
    //Give l1 the adapter to set information; Set for data set changes
    private void loadDataInListView(ListView li){
        arrayList = databaseHelper.getAllData();
        myAdapter = new MyAdapter(this, arrayList);
        li.setAdapter(myAdapter);
        myAdapter.notifyDataSetChanged();
    }
}

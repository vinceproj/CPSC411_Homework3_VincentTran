/*
Vincent Tran
891036956
CPSC 411
HW Assignment #3
*/

package com.example.homework3.Adapter;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.homework3.Model.Student;
import com.example.homework3.Model.StudentDB;
import com.example.homework3.R;

import java.util.ArrayList;

public class MyAdapter extends BaseAdapter {

    //Vars for use
    Context context;
    ArrayList<Student> arrayList;

    //Instantiate vars
    public MyAdapter(Context context, ArrayList<Student> arrayList){
        this.context=context;
        this.arrayList = arrayList;
    }

    @Override
    public int getCount() {
        return this.arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    //Adapter actions
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        //Use inflater for setup
        final View row_view;
        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            row_view = inflater.inflate(R.layout.student_row, parent, false);
        }else row_view = convertView;

        //Get target based on passed in arrayList
        final Student s = arrayList.get(position);

        //Set row_view based on target data
        ((TextView) row_view.findViewById(R.id.first_name)).setText(s.getFirstName());
        ((TextView) row_view.findViewById(R.id.last_name)).setText(s.getLastName());
        ((TextView) row_view.findViewById(R.id.CWID)).setText(s.getCWID().toString());

        return row_view;
    }
}

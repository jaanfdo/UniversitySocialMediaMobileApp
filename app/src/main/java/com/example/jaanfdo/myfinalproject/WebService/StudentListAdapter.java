package com.example.jaanfdo.myfinalproject.WebService;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.jaanfdo.myfinalproject.R;

import java.util.List;

public class StudentListAdapter extends ArrayAdapter<Student> {
    private List<Student> students;
    private Context context;

    public StudentListAdapter(List<Student> students, Context context) {
        super(context, R.layout.studentlayout, students);

        this.students = students;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.studentlayout, parent,false);



        return super.getView(position, convertView, parent);
    }
}

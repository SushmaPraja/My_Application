package com.example.myapplication;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Database.DbHelper;
import com.example.myapplication.adapter.StudentAdapter;
import com.example.myapplication.module.Student;

import java.util.List;

public class ShowStudentActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    DbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_student);

        dbHelper = new DbHelper(this);
        recyclerView = findViewById(R.id.viewStudent);

        List<Student> students = dbHelper.getStudents();

        StudentAdapter studentAdapter = new StudentAdapter(students);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(studentAdapter);
    }


}

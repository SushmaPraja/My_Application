package com.example.myapplication;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.Database.DbHelper;
import com.example.myapplication.module.Student;

import java.util.List;

public class DatabaseActivity extends AppCompatActivity {

    EditText editTextN, editTextE, editTextP;
    Button buttonAdd;
    DbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);

        editTextN = findViewById(R.id.stName);
        editTextE = findViewById(R.id.stEmail);
        editTextP = findViewById(R.id.stPhone);
        buttonAdd = findViewById(R.id.stAdd);

        dbHelper = new DbHelper(this);

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             String n = editTextN.getText().toString();
             String e = editTextE.getText().toString();
             String p = editTextP.getText().toString();

                Student student = new Student(0,n,e,p);

                if(dbHelper.addStudent(student))
                {
                    Toast.makeText(DatabaseActivity.this, "Student Added", Toast.LENGTH_SHORT).show();
                }
            }
        });

        List<Student> students = dbHelper.getStudents();
        for(Student student:students){
            Log.d("id",String.valueOf(student.getId()));
            Log.d("name",student.getName());
            Log.d("email", student.getEmail());
            Log.d("phone", student.getPhone());
        }

    }
}

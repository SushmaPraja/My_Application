package com.example.myapplication;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.api.EmpInt;
import com.example.myapplication.module.Employee;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitEmpActivity extends AppCompatActivity {

    TextView textView;
    EditText etName, etSalary, etAge;
    Button btnAdd;
    Retrofit retrofit;
    EmpInt empInt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit_emp);

        textView = findViewById(R.id.empApiData);
        etName = findViewById(R.id.empName);
        etSalary = findViewById(R.id.empSalary);
        etAge = findViewById(R.id.empAge);
        btnAdd = findViewById(R.id.empAdd);

        getInstance();

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etName.getText().toString();
            String salary= etSalary.getText().toString();
                String age= etAge.getText().toString();
                Employee employee = new Employee(0,name,salary,age);
               updateEmployee(180458, employee);

               // Employee employee = new Employee(0,name,salary,age);  addEmployee(employee);
            }
        });
    }

    private void getInstance(){
         retrofit = new Retrofit.Builder().baseUrl("http://dummy.restapiexample.com/api/v1/")
                .addConverterFactory(GsonConverterFactory.create()).build();
       empInt = retrofit.create(EmpInt.class);
    }

    private void getAllEmployees(){
        Call<List<Employee>> employeeList = empInt.getEmps();
        employeeList.enqueue(new Callback<List<Employee>>() {
            @Override
            public void onResponse(Call<List<Employee>> call, Response<List<Employee>> response) {
                List<Employee> empList = response.body();
                for(Employee emp:empList){
                    Log.d("name", emp.getEmployee_name());
                    textView.append("Name:"+emp.getEmployee_name()+"Salary:"+emp.getEmployee_salary()+"Age:"+emp.getEmployee_age());
                }
            }

            @Override
            public void onFailure(Call<List<Employee>> call, Throwable t) {
                Log.d("ApiEx:",t.getMessage());
            }
        });
    }
    private void getEmpById(){
        Call<Employee> employee = empInt.getEmployeeById(1);
        employee.enqueue(new Callback<Employee>() {
            @Override
            public void onResponse(Call<Employee> call, Response<Employee> response) {
                Toast.makeText(RetrofitEmpActivity.this,response.body().getEmployee_name(),Toast.LENGTH_SHORT);
            }

            @Override
            public void onFailure(Call<Employee> call, Throwable t) {
                Toast.makeText(RetrofitEmpActivity.this, "", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void addEmployee(Employee employee){
        Call<Void> empAdd = empInt.addEmployee(employee);
        empAdd.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(RetrofitEmpActivity.this,"Added",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.d("ApiEx", t.getMessage());
            }
        });
    }

    private  void updateEmployee(int id, Employee employee){
        Call<Void> empUpdate = empInt.updateEmployee(id, employee);
        empUpdate.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(RetrofitEmpActivity.this, "Updated", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(RetrofitEmpActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

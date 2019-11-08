package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button buttonL1, buttonG1, buttonR1, buttonF1, buttonT1, buttonC1,buttonRv; //defining objects


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonL1 = findViewById(R.id.btnL1);
        buttonG1 = findViewById(R.id.btnG1);
        buttonR1 = findViewById(R.id.btnR1);
        buttonF1 = findViewById(R.id.btnF1);
        buttonT1 = findViewById(R.id.btnT1);
        buttonC1 = findViewById(R.id.btnC1);
        buttonRv = findViewById(R.id.btnRv);

        buttonL1.setOnClickListener(this);
        buttonG1.setOnClickListener(this);
        buttonR1.setOnClickListener(this);
        buttonF1.setOnClickListener(this);
        buttonT1.setOnClickListener(this);
        buttonC1.setOnClickListener(this);
        buttonRv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btnT1){
            Intent intent = new Intent(this,T1Activity.class);
            startActivity(intent);
        }
        if(v.getId() == R.id.btnF1){
            Intent intent = new Intent(this,F1Activity.class);
            startActivity(intent);

        }
        if (v.getId() == R.id.btnR1){
            Intent intent = new Intent(this, R1Activity.class);
            startActivity(intent);
        }
        if (v.getId() == R.id.btnL1){
            Intent intent = new Intent(this, L1Activity.class);
            startActivity(intent);
        }
        if(v.getId() == R.id.btnC1){
            Intent intent = new Intent(this, Ct1Activity.class);
            startActivity(intent);
        }
        if(v.getId() == R.id.btnRv){
        Intent intent = new Intent(this, RvActivity.class);
        startActivity(intent);
    }
    }
}

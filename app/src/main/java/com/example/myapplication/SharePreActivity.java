package com.example.myapplication;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SharePreActivity extends AppCompatActivity {

    SharedPreferences sp;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_pre);

        sp = getApplicationContext().getSharedPreferences("mysp", MODE_PRIVATE);
//        editor = sp.edit();
//        editor.putString("name","sushma");
//        editor.putInt("age",22);
//        editor.putBoolean("isMemeber", true);
//        editor.commit();

        String n = sp.getString("name", "Harry");
        int a = sp.getInt("age", 0);
        boolean im = sp.getBoolean("isMemeber", false);
        int b = sp.getInt("zip", 0);
        String add = sp.getString("Address","Nepal");

        Toast.makeText(this, "Name:"+n+"\n"+ "Age: "+a +"\n"+"Member:"+im +"\n"+"Zip: "+b+"\n" + "Address: " + add, Toast.LENGTH_SHORT).show();

    }
}

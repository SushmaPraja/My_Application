package com.example.myapplication;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class FileActivity extends AppCompatActivity {

    EditText editText;
    Button buttonSave, buttonLoad, button01, button02;

    HashMap<String, String> myhm  = new  HashMap();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file);

        editText = findViewById(R.id.fileContent);
        buttonSave = findViewById(R.id.btnSave);
        buttonLoad = findViewById(R.id.btnLoad);
        button01 = findViewById(R.id.btn01);
        button02 = findViewById(R.id.btn02);

        if (isExWritable()){
            Toast.makeText(this, "Yes", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this, "False", Toast.LENGTH_SHORT).show();
        }

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data = editText.getText().toString();
                try {
                    FileOutputStream fos = openFileOutput("myfile.txt",MODE_PRIVATE);
                 fos.write(data.getBytes());
                 editText.getText().clear();
                    Toast.makeText(FileActivity.this, "Saved To" +getFilesDir(), Toast.LENGTH_SHORT).show();

                } catch (FileNotFoundException e) {
                    Log.d("Exc", e.toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });


        buttonLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                   FileInputStream fis = openFileInput("myfile.txt");
                    InputStreamReader isr = new InputStreamReader(fis);
                    BufferedReader br = new BufferedReader(isr);
                   // StringBuilder sb = new StringBuilder();
                    String data;
                    String alldata = "";

                    while ((data=br.readLine()) != null){
                      //  alldata+= data+"\n";
                        String[] wm = data.split("=");

                       //declaraing hashmap
                        myhm.put(wm[0],wm[1]);
                    }

                    editText.setText(alldata);

                } catch (Exception e) {
                    Log.d("Exc", e.toString());
                }

            }
        });



    }

    private boolean isExWritable(){
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            return true;
        } else{
            return false;
        }
    }

    public void askPermission(View view){
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
            }else{
            saveExternal();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode == 1){
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                saveExternal();
            }
            else{
                Toast.makeText(this, "No Permission", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void saveExternal() {
        if (isExWritable()){
            String data = editText.getText().toString();
            try{
                File mydir = new File(getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), "MyApplication2");
                mydir.mkdir();
                File myfile = new File(getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), "MyApplication2/myfile.txt");
                mydir.createNewFile();
                FileOutputStream fos = new FileOutputStream(myfile);
                fos.write(data.getBytes());
                editText.getText().clear();
                Toast.makeText(this, "Saved to" + mydir, Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

package com.example.myapplication;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Random;

public class ImInActivity extends AppCompatActivity implements View.OnClickListener {

    Button buttonCamera, buttonGallery, buttonWebsite, buttonDialer;
    EditText editTextDialer;
    ImageView imageView;
    Bitmap image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_im_in);

        buttonCamera = findViewById(R.id.btnCamera);
        buttonGallery = findViewById(R.id.btnGallery);
        buttonWebsite = findViewById(R.id.btnUrl);
        buttonDialer = findViewById(R.id.btnDialer);
        editTextDialer = findViewById(R.id.et_dialer);
        imageView = findViewById(R.id.ivSelectImg);

        buttonCamera.setOnClickListener(this);
        buttonGallery.setOnClickListener(this);
        buttonWebsite.setOnClickListener(this);
        buttonDialer.setOnClickListener(this);
        editTextDialer.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnCamera:
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 1);
                break;

            case R.id.btnUrl:
            Intent intent1 = new Intent(Intent.ACTION_VIEW);
            intent1.setData(Uri.parse(("http://www.softwarica.edu.np")));
            startActivity(intent1);
                break;

            case R.id.btnGallery:
                Intent intent2 = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent2, 2);
                break;

            case R.id.btnDialer:
                Uri uri = Uri.parse("tel:9413523545");
                Intent intent3 = new Intent(Intent.ACTION_DIAL,uri);
                startActivity(intent3);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1){
            image = (Bitmap)  data.getExtras().get("data");
          //  Bitmap image = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(image);
            askPermission();
        }
        if (requestCode == 2){
            Uri uri = data.getData();
            imageView.setImageURI(uri);
        }
    }

    public void askPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
        }else{
            saveCapImg();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
      //  super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1){
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                saveCapImg();
            }
            else{
                Toast.makeText(this, "No Permission", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void saveCapImg(){
        try{
            File mydir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),"Myapp");
            mydir.mkdir();
            Random random = new Random();
            int a = random.nextInt(1000);
            File file = new File(mydir, "myimg"+a+".jpg");
            FileOutputStream fos = new FileOutputStream(file);
            image.compress(Bitmap.CompressFormat.PNG,100, fos);
            Toast.makeText(this, "Saved to"+mydir, Toast.LENGTH_SHORT).show();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}

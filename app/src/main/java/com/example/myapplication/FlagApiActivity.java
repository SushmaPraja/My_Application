package com.example.myapplication;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.api.EmpInt;
import com.example.myapplication.module.Flag;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FlagApiActivity extends AppCompatActivity {

    TextView textView;
    ImageView imageView;
    Retrofit retrofit;
    EmpInt empInt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flag_api);

        textView = findViewById(R.id.tvCountry);
        imageView = findViewById(R.id.evFlag);
        getInstance();
        getCountryById(5);
    }

    private  void getInstance(){
        retrofit = new Retrofit.Builder().baseUrl("http://sujitg.com.np/api/")
                .addConverterFactory(GsonConverterFactory.create()).build();
        empInt = retrofit.create(EmpInt.class);

    }
    private  void getCountryById(int id){
        Call<Flag> flagCall = empInt.getFlagById(id);
        flagCall.enqueue(new Callback<Flag>() {
            @Override
            public void onResponse(Call<Flag> call, Response<Flag> response) {
                textView.setText(response.body().getCountry());
                Picasso.get().load("http://sujitg.com.np/wc/teams/"+response.body().getFile()).into(imageView);

              //  Toast.makeText(FlagApiActivity.this, "Ok", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Flag> call, Throwable t) {
                Log.d("Ex:", t.getMessage());

            }
        });

    }
}

package com.example.myapplication;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.module.Food;

import java.util.ArrayList;
import java.util.List;

public class RvActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    private List<Food> foods = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rv);

        recyclerView = findViewById(R.id.rvFoods);

        getData();
        MyRvAdapter adapter = new MyRvAdapter(foods);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    private void getData(){
        foods.add(new Food("MoMo","veg","Rs. 150", R.drawable.momo));
        foods.add(new Food("Chowmein","veg","Rs. 170", R.drawable.chowmein));
        foods.add(new Food("Pizza","veg","Rs. 300", R.drawable.pizza));
    }

}

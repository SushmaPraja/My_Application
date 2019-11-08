package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.module.Food;

import java.util.ArrayList;
import java.util.List;

public class MyRvAdapter extends RecyclerView.Adapter<MyRvAdapter.MyHolder> {

   private List<Food> foodList = new ArrayList<>();

    public MyRvAdapter(List<Food> foodList) {
        this.foodList = foodList;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {//layout bind
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_food_rv,parent,false);
        MyHolder myHolder = new MyHolder(view);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        Food food = foodList.get(position);
        holder.tvName.setText(food.getName());
        holder.tvCat.setText(food.getCateg());
        holder.tvPrice.setText(food.getPrice());
        holder.imageView.setImageResource(food.getImg());
//        holder.tvName1.setText(food.getName1());
//        holder.tvCat1.setText(food.getName());
//        holder.tvPrice1.setText(food.getName());
//        holder.imageView1.setImageResource(food.getImg());
//        holder.tvName2.setText(food.getName1());
//        holder.tvCat2.setText(food.getName());
//        holder.tvPrice2.setText(food.getName());
//        holder.imageView2.setImageResource(food.getImg());

    }

    @Override
    public int getItemCount() {
       return foodList.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder{

        ImageView imageView, imageView1, imageView2;
        TextView tvName, tvCat, tvPrice, tvName1, tvCat1, tvPrice1, tvName2, tvCat2, tvPrice2;


        public MyHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.foodImg);
            tvName = itemView.findViewById(R.id.foodName);
            tvCat = itemView.findViewById(R.id.foodCat);
            tvPrice = itemView.findViewById(R.id.foodPrice);
//            imageView1 = itemView.findViewById(R.id.foodImg1);
//           tvName1 = itemView.findViewById(R.id.foodName1);
//            tvCat1 = itemView.findViewById(R.id.foodCat1);
//            tvPrice1 = itemView.findViewById(R.id.foodPrice1);
//            imageView2 = itemView.findViewById(R.id.foodImg2);
//            tvName2 = itemView.findViewById(R.id.foodName2);
//            tvCat2 = itemView.findViewById(R.id.foodCat2);
//            tvPrice2 = itemView.findViewById(R.id.foodPrice2);
        }
    }

}

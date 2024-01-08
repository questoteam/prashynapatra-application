package com.example.questionbank;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.Viewholder> {





private Context context;
private ArrayList<sem_modal> list;

    public RecyclerAdapter(Context context ,ArrayList<sem_modal> list )  {
        this.context = context;
        this.list = list;

    }



    @NonNull
    @Override
    public  RecyclerAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.semno,parent,false);

        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
          holder.textView.setText(list.get(position).getSemno());
        Glide.with(context)
                .load(list.get(position).getImageurl())
                .placeholder(R.drawable.ww)
                .fitCenter()
                .into(holder.imageView);






    }



    @Override
    public int getItemCount() {
        return list.size();
    }



    public  class Viewholder extends RecyclerView.ViewHolder  {
ImageView imageView;
TextView textView;
CardView cardView;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            textView =itemView.findViewById(R.id.textview);
            imageView = itemView.findViewById(R.id.flag);


        }


    }

}

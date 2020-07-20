package com.example.sharethemeal;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyHolder> {
    Context c;
    ArrayList<Model> models;

    public MyAdapter(Context c, ArrayList<Model> models) {
        this.c = c;
        this.models = models;
    }
    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(c).inflate(R.layout.food_items,parent,false);
        return new MyHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        holder.mTitle.setText(models.get(position).getTitle());
        holder.mDescription.setText(models.get(position).getDescription());
        holder.mId.setImageResource(models.get(position).getId());
        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClickListener(final View view, final int position) {
                final String gTitle = models.get(position).getTitle();
                final String gDescription = models.get(position).getDescription();
                new AlertDialog.Builder(view.getRootView().getContext())
                        .setMessage(""+models.get(position).getTitle())
                        .setTitle("Do you want to add this item for Donation")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(c,DonationActivity.class);
                                intent.putExtra("iTitle",gTitle);
                                intent.putExtra("iDescription",gDescription);
                                Toast.makeText(view.getContext(),"Item "+models.get(position).getTitle()+" is added",Toast.LENGTH_SHORT).show();
                                c.startActivity(intent);
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Log.i("Result","Success");
                            }
                        })
                        .show();
            }
        });
    }
    @Override
    public int getItemCount() {
        return models.size();
    }
}

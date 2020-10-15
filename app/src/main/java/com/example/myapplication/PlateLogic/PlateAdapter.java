package com.example.myapplication.PlateLogic;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.model.Plato;

import java.util.List;

public class PlateAdapter extends RecyclerView.Adapter<PlateViewHolder> {

    private PlateRecyclerActivity plateRecyclerActivity;
    private List<Plato> plateList;
    private Boolean addButtonAsk;

    public PlateAdapter(PlateRecyclerActivity plateRecyclerActivity, List<Plato> plateListParameter, Boolean addButtonAsk){
        this.plateRecyclerActivity = plateRecyclerActivity;
        this.plateList = plateListParameter;
        this.addButtonAsk = addButtonAsk;
    }

    public PlateViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fila_plato, parent, false);
        PlateViewHolder plateViewHolder = new PlateViewHolder(view, addButtonAsk);
        return plateViewHolder;
    }

    public void onBindViewHolder(@NonNull final PlateViewHolder plateHolder, int position) {
        plateHolder.cardViewPlate.setTag(position);
        plateHolder.textTitle.setTag(position);
        plateHolder.textPrice.setTag(position);
        plateHolder.btnVer.setTag(position);
        plateHolder.btnAsk.setTag(position);
        plateHolder.plate = plateList.get(position);
        plateHolder.textTitle.setText(plateHolder.plate.getTitle());
        plateHolder.textPrice.setText("Precio: "+plateHolder.plate.getPrice().toString()+"$");

        plateHolder.btnAsk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Plato p = plateList.get(plateHolder.getAdapterPosition());
                plateRecyclerActivity.back(p.getTitle(), p.getPrice());
            }
        });
    }

    public int getItemCount() {
        return plateList.size();
    }
}

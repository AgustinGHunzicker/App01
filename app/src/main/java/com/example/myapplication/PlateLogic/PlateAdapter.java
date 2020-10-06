package com.example.myapplication.PlateLogic;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.model.Plato;

import java.util.List;

public class PlateAdapter extends RecyclerView.Adapter<PlateViewHolder> {

    private List<Plato> plateList;

    public PlateAdapter(List<Plato> plateListParameter){
        plateList = plateListParameter;
    }

    public PlateViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fila_plato, parent, false);
        PlateViewHolder plateViewHolder = new PlateViewHolder(view);
        return plateViewHolder;
    }

    public void onBindViewHolder(@NonNull PlateViewHolder plateHolder, int position) {
        plateHolder.cardViewPlate.setTag(position);
        plateHolder.textTitle.setTag(position);
        plateHolder.textPrice.setTag(position);
        plateHolder.btnVer.setTag(position);

        Plato plate = plateList.get(position);
        plateHolder.textTitle.setText(plate.getTitle());
        plateHolder.textPrice.setText("Precio: "+plate.getPrice().toString()+"$");
    }

    public int getItemCount() {
        return plateList.size();
    }
}

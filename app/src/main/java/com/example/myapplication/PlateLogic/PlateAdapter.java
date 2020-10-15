package com.example.myapplication.PlateLogic;

import android.app.Activity;
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

    private List<Plato> plateList;
    private PlateViewHolder plateHolder;
    public View.OnClickListener listenerAskButton;

    public PlateAdapter(List<Plato> plateListParameter, View.OnClickListener listenerAskButton){
        this.plateList = plateListParameter;
        this.listenerAskButton = listenerAskButton;
    }

    public PlateViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fila_plato, parent, false);
        PlateViewHolder plateViewHolder = new PlateViewHolder(view, listenerAskButton);
        return plateViewHolder;
    }

    public void onBindViewHolder(@NonNull PlateViewHolder plateHolder, int position) {
        this.plateHolder = plateHolder;
        plateHolder.cardViewPlate.setTag(position);
        plateHolder.textTitle.setTag(position);
        plateHolder.textPrice.setTag(position);
        plateHolder.btnVer.setTag(position);
        plateHolder.btnAsk.setTag(position);

        Plato plate = plateList.get(position);
        plateHolder.textTitle.setText(plate.getTitle());
        plateHolder.textPrice.setText("Precio: "+plate.getPrice().toString()+"$");
    }

    public int getItemCount() {
        return plateList.size();
    }

}

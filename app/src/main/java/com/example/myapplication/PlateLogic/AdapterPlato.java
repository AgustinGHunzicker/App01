package com.example.myapplication.PlateLogic;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.activity.ActivityPlateRecycler;
import com.example.myapplication.model.Plato;

import java.util.List;

public class AdapterPlato extends RecyclerView.Adapter<ViewHolderPlato> {

    private ActivityPlateRecycler activityPlateRecycler;
    private List<Plato> plateList;
    private Boolean addButtonAsk;

    public AdapterPlato(ActivityPlateRecycler activityPlateRecycler, List<Plato> plateListParameter, Boolean addButtonAsk){
        this.activityPlateRecycler = activityPlateRecycler;
        this.plateList = plateListParameter;
        this.addButtonAsk = addButtonAsk;
    }

    public ViewHolderPlato onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fila_plato, parent, false);
        ViewHolderPlato viewHolderPlato = new ViewHolderPlato(view, addButtonAsk);
        return viewHolderPlato;
    }

    public void onBindViewHolder(@NonNull final ViewHolderPlato plateHolder, int position) {
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
                activityPlateRecycler.back(p.getTitle(), p.getPrice());
            }
        });
    }

    public int getItemCount() {
        return plateList.size();
    }
}

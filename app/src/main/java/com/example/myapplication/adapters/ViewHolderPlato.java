package com.example.myapplication.adapters;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.model.Plato;

/*
    Holder
    Tendrá como atributos, punteros a instancias de los distintos View que muestra una fila.
*/

public class ViewHolderPlato extends RecyclerView.ViewHolder {

    ImageView plateImage;
    CardView cardViewPlate;
    TextView textTitle;
    TextView textDescription;
    TextView textPrice;
    TextView textCalories;
    Button btnVer;
    Button btnPedirPlato;
    Plato plate;

    public ViewHolderPlato(@NonNull View itemView, Boolean addButttonAsk) {
        super(itemView);
        //this.plateImage = (ImageView) itemView.findViewById(R.id.);
        this.cardViewPlate = (CardView) itemView.findViewById(R.id.cardViewPlate);
        this.textTitle = (TextView) itemView.findViewById(R.id.textTitlePlate);
        //this.textDescription = (TextView) itemView.findViewById(R.id.textDescriptionPlate);
        this.textPrice = (TextView) itemView.findViewById(R.id.textPricePlate);
        //this.textCalories = (TextView) itemView.findViewById(R.id.textCaloriesPlate);
        this.btnVer = (Button) itemView.findViewById(R.id.btnVer);
        this.btnPedirPlato = (Button) itemView.findViewById(R.id.btnAsk);

        if(addButttonAsk){
            btnPedirPlato.setVisibility(View.VISIBLE);
            btnVer.setVisibility(View.GONE);
        }
        else{
            btnVer.setVisibility(View.VISIBLE);
            btnPedirPlato.setVisibility(View.GONE);
        }
    }
}

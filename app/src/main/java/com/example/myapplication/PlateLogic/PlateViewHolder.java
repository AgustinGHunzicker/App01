package com.example.myapplication.PlateLogic;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

public class PlateViewHolder extends RecyclerView.ViewHolder {

    ImageView plateImage;
    CardView cardViewPlate;
    TextView textTitle;
    TextView textDescription;
    TextView textPrice;
    TextView textCalories;
    Button btnVer;


    public PlateViewHolder(@NonNull View itemView) {
        super(itemView);
        //this.plateImage = (ImageView) itemView.findViewById(R.id.);
        this.cardViewPlate = (CardView) itemView.findViewById(R.id.cardViewPlate);
        this.textTitle = (TextView) itemView.findViewById(R.id.textTitlePlate);
        //this.textDescription = (TextView) itemView.findViewById(R.id.textDescriptionPlate);
        this.textPrice = (TextView) itemView.findViewById(R.id.textPricePlate);
        //this.textCalories = (TextView) itemView.findViewById(R.id.textCaloriesPlate);
        this.btnVer = (Button) itemView.findViewById(R.id.btnVer);
     }
}

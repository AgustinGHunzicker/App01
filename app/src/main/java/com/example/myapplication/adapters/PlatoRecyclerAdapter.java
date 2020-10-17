package com.example.myapplication.adapters;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.activity.ActivityPedido;
import com.example.myapplication.activity.ActivityPlateRecycler;
import com.example.myapplication.model.Plato;

import java.util.List;

/*
 El "adapter" gestiona los datos dentro de una lista
 - Un objeto Adaptador actúa como puente entre un AdapterView y los datos de una Vista (View).
 - El adaptador permite el acceso a los elementos de datos
 - Este también es responsable de crear una vista para cada elemento en la colección de datos.
 */
public class PlatoRecyclerAdapter extends RecyclerView.Adapter<ViewHolderPlato> {

    private ActivityPlateRecycler activityPlateRecycler;
    private List<Plato> plateList;
    private Boolean addButtonAsk;

    public PlatoRecyclerAdapter(ActivityPlateRecycler activityPlateRecycler, List<Plato> plateListParameter, Boolean addButtonAsk){
        this.activityPlateRecycler = activityPlateRecycler;
        this.plateList = plateListParameter;
        this.addButtonAsk = addButtonAsk;
    }

    /*
    -   Este método se ejecutará UNA VEZ por cada fila que se visualiza.
    -   Obtenemos una vista y a partir de la misma la inflamos con el layout
     */
    public ViewHolderPlato onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fila_plato, parent, false);
        return new ViewHolderPlato(view, addButtonAsk);
    }

    /*
        El adaptador vincula los ViewHolderPlato con Plato
     */
    public void onBindViewHolder(@NonNull final ViewHolderPlato plateHolder, int position) {
        plateHolder.plate = plateList.get(position);
        plateHolder.cardViewPlate.setTag(position);

        /*-- titulo --*/
        plateHolder.textTitle.setTag(position);
        plateHolder.textTitle.setText(plateHolder.plate.getTitle());

        /*-- precio --*/
        plateHolder.textPrice.setTag(position);
        plateHolder.textPrice.setText("Precio: "+plateHolder.plate.getPrice().toString()+"$");

        /*-- button ver --*/
        plateHolder.btnVer.setTag(position);

        /*-- button pedirPlato --*/
        plateHolder.btnPedirPlato.setTag(position);
        plateHolder.btnPedirPlato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Plato plato = plateList.get(plateHolder.getAdapterPosition());
                Intent intent = new Intent(activityPlateRecycler, ActivityPedido.class);
                intent.putExtra("plato", plato);
                activityPlateRecycler.getIntent().getSerializableExtra("plato");
                activityPlateRecycler.setResult(Activity.RESULT_OK, intent);
                activityPlateRecycler.finish();
            }
        });
    }

    public int getItemCount() {
        return plateList.size();
    }
}

package com.example.myapplication.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.example.myapplication.R;
import com.example.myapplication.model.Plato;
import java.util.List;

public class PlatoAdapter extends ArrayAdapter<Plato> {
    public PlatoAdapter(@NonNull Context context, @NonNull List<Plato> objects) {
        super(context,0, objects);
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        final Plato plato = super.getItem(position);
        View vistaPlato;
        LayoutInflater inflador = LayoutInflater.from(this.getContext());

        //Si la convertView que pasa android  esta vacia, se crea, caso contrario se rehusa cambiandole los datos
        if(convertView==null)
            vistaPlato = inflador.inflate(R.layout.fila_pedido,parent,false);
        else
            vistaPlato = convertView;

        PlatoHolder platoHolder = (PlatoHolder) vistaPlato.getTag();
        if(platoHolder == null){
            platoHolder = new PlatoHolder(vistaPlato);
            vistaPlato.setTag(platoHolder);
        }

        // TextView nombrePlto = vistaPlato.findViewById(R.id.rowNombrePlato);
        platoHolder.nombrePlato.setText(plato.getTitle());
        // TextView nombrePlto = vistaPlato.findViewById(R.id.rowPrecioPlato);
        platoHolder.precioPlato.setText(plato.getPrice().toString());

        return vistaPlato;
    }

    /*
       Nombre de los platos
       Precio de plato
     */
    public class PlatoHolder {
        TextView nombrePlato;
        TextView precioPlato;

        public PlatoHolder(View v){
            nombrePlato = v.findViewById(R.id.rowNombrePlato);
            precioPlato = v.findViewById(R.id.rowPrecioPlato);
        }
    }
}

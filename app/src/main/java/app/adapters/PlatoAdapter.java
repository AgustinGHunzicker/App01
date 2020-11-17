package app.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import SendMeal.app.R;
import app.model.Plato;

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
        if(convertView == null)
            vistaPlato = inflador.inflate(R.layout.fila_pedido, parent,false);
        else
            vistaPlato = convertView;
        PlatoHolder platoHolder = (PlatoHolder) vistaPlato.getTag();

        if(platoHolder == null){
            platoHolder = new PlatoHolder(vistaPlato);
            vistaPlato.setTag(platoHolder);
        }

        platoHolder.cantidadPlato.setText("x1");
        platoHolder.nombrePlato.setText(plato.getTitle());
        platoHolder.precioPlato.setText("$ "+String.format("%.2f", plato.getPrice()));

        return vistaPlato;
    }

    public class PlatoHolder {
        TextView cantidadPlato;
        TextView nombrePlato;
        TextView precioPlato;

        public PlatoHolder(View v){
            cantidadPlato = v.findViewById(R.id.rowCantidadPlato);
            nombrePlato = v.findViewById(R.id.rowNombrePlato);
            precioPlato = v.findViewById(R.id.rowPrecioPlato);
        }
    }

    public void addPlato(Plato plato){
        super.add(plato);
        super.notifyDataSetChanged();
    }
}

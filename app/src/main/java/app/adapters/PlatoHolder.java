package app.adapters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import SendMeal.app.R;
import app.activity.ActivityPedido;
import app.activity.ActivityPlatos;
import app.model.Plato;
import java.util.List;

public class PlatoHolder extends RecyclerView.Adapter<PlatoHolder.ViewHolderPlato> {

    private final ActivityPlatos activityPlatos;
    private final List<Plato> listaPlatos;
    private final Boolean addButtonAsk;

    public PlatoHolder(ActivityPlatos activityPlatos, List<Plato> listaPlatos, Boolean addButtonAsk){
        this.activityPlatos = activityPlatos;
        this.listaPlatos = listaPlatos;
        this.addButtonAsk = addButtonAsk;
    }

    public static class ViewHolderPlato extends RecyclerView.ViewHolder {

        Plato plate;

        ImageView imagePlato;
        CardView cardViewPlato;
        TextView textTituloPlato;
        TextView textPrecioPlato;
        Button btnVer;
        Button btnPedirPlato;


        public ViewHolderPlato(@NonNull View itemView, Boolean addButtonAsk) {
            super(itemView);
            imagePlato = (ImageView) itemView.findViewById(R.id.imagePlato);
            cardViewPlato = itemView.findViewById(R.id.cardViewPlato);
            textTituloPlato = itemView.findViewById(R.id.textTituloPlato);
            textPrecioPlato = (TextView) itemView.findViewById(R.id.textPrecioPlato);
            btnVer = (Button) itemView.findViewById(R.id.btnVer);
            btnPedirPlato = (Button) itemView.findViewById(R.id.btnPedir);

            if(addButtonAsk){
                btnPedirPlato.setVisibility(View.VISIBLE);
                btnVer.setVisibility(View.GONE);
            }
            else{
                btnVer.setVisibility(View.VISIBLE);
                btnPedirPlato.setVisibility(View.GONE);
            }
        }
    }

    @NonNull
    public ViewHolderPlato onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fila_plato, parent, false);
        return new ViewHolderPlato(view, addButtonAsk);
    }

    @SuppressLint("SetTextI18n")
    public void onBindViewHolder(@NonNull final ViewHolderPlato plateHolder, int position) {
        plateHolder.plate = listaPlatos.get(position);

        plateHolder.imagePlato.setTag(position);
        plateHolder.textTituloPlato.setTag(position);
        plateHolder.textPrecioPlato.setTag(position);
        plateHolder.btnVer.setTag(position);
        plateHolder.btnPedirPlato.setTag(position);

        plateHolder.textTituloPlato.setText(plateHolder.plate.getTitulo());
        plateHolder.textPrecioPlato.setText("Precio: "+plateHolder.plate.getPrecio()+"$");
        plateHolder.btnPedirPlato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Plato plato = listaPlatos.get(plateHolder.getAdapterPosition());
                Intent intent = new Intent(activityPlatos, ActivityPedido.class);
                intent.putExtra("plato", plato);
                activityPlatos.getIntent().getSerializableExtra("plato");
                activityPlatos.setResult(Activity.RESULT_OK, intent);
                activityPlatos.finish();
            }
        });
    }

    public int getItemCount() {
        return listaPlatos.size();
    }
}

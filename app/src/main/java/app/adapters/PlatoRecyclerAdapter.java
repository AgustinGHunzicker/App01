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
import app.activity.ActivityPlateRecycler;
import app.model.Plato;
import java.util.List;

/*
 El "adapter" gestiona los datos dentro de una lista
 - Un objeto Adaptador actúa como puente entre un AdapterView y los datos de una Vista (View).
 - El adaptador permite el acceso a los elementos de datos
 - Este también es responsable de crear una vista para cada elemento en la colección de datos.
 */
public class PlatoRecyclerAdapter extends RecyclerView.Adapter<PlatoRecyclerAdapter.ViewHolderPlato> {

    private final ActivityPlateRecycler activityPlateRecycler;
    private final List<Plato> plateList;
    private final Boolean addButtonAsk;

    public PlatoRecyclerAdapter(ActivityPlateRecycler activityPlateRecycler,
                                List<Plato> plateListParameter, Boolean addButtonAsk){

        this.activityPlateRecycler = activityPlateRecycler;
        this.plateList = plateListParameter;
        this.addButtonAsk = addButtonAsk;
    }

    /*
        Holder
        Tendrá como atributos, punteros a instancias de los distintos View que muestra una fila.
    */
    public static class ViewHolderPlato extends RecyclerView.ViewHolder {

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
            this.cardViewPlate = itemView.findViewById(R.id.cardViewPlate);
            this.textTitle = itemView.findViewById(R.id.textTitlePlate);
            //this.textDescription = (TextView) itemView.findViewById(R.id.textDescriptionPlate);
            this.textPrice = (TextView) itemView.findViewById(R.id.textPricePlate);
            //this.textCalories = (TextView) itemView.findViewById(R.id.textCaloriesPlate);
            this.btnVer = (Button) itemView.findViewById(R.id.btnVer);
            this.btnPedirPlato = (Button) itemView.findViewById(R.id.btnPedir);

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

    /*
-   Este método se ejecutará UNA VEZ por cada fila que se visualiza.
-   Obtenemos una vista y a partir de la misma la inflamos con el layout
 */
    @NonNull
    public ViewHolderPlato onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fila_plato, parent, false);
        return new ViewHolderPlato(view, addButtonAsk);
    }

    /*
    El adaptador vincula los ViewHolderPlato con Plato
 */
    @SuppressLint("SetTextI18n")
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

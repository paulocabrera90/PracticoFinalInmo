package com.example.plantilla.ui.contrato;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.plantilla.R;
import com.example.plantilla.modelo.Contrato;
import com.example.plantilla.modelo.Inmueble;
import com.example.plantilla.request.ApiClient;

import java.util.List;

public class ContratoAdapter extends RecyclerView.Adapter<ContratoAdapter.ViewHolder> {
    private List<Inmueble> listaInmueble;
    private Context context;
    private LayoutInflater layoutInflater;

    public ContratoAdapter(List<Inmueble> lista, Context context, LayoutInflater layoutInflater) {
        this.listaInmueble = lista;
        this.context = context;
        this.layoutInflater = layoutInflater;
    }

    @NonNull
    @Override
    public ContratoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.contrato_item, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ContratoAdapter.ViewHolder holder, int position) {
        Inmueble inmueble = listaInmueble.get(position);
        final ApiClient api = ApiClient.getApi();
        final Contrato contrato = api.obtenerContratoVigente(inmueble);
        holder.tvPrecioC.setText("$ " + String.valueOf(listaInmueble.get(position).getPrecio()));
        holder.tvDireccionC.setText(listaInmueble.get(position).getDireccion());
        Glide.with(context)
                .load(listaInmueble.get(position).getImagen())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.ivInmuebleC);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("contrato", contrato);
                Navigation.findNavController(view).navigate(R.id.nav_contrato_detalle, bundle);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listaInmueble.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tvDireccionC, tvPrecioC;
        private ImageView ivInmuebleC;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDireccionC = itemView.findViewById(R.id.textViewDirecContr);
            tvPrecioC = itemView.findViewById(R.id.textViewPrecContr);
            ivInmuebleC = itemView.findViewById(R.id.imagenViewInmuContr);
        }
    }
}

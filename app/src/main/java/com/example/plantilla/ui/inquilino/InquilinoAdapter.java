package com.example.plantilla.ui.inquilino;

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
import com.example.plantilla.modelo.Inmueble;
import com.example.plantilla.modelo.Inquilino;
import com.example.plantilla.request.ApiClient;

import java.util.List;

public class InquilinoAdapter extends RecyclerView.Adapter<InquilinoAdapter.ViewHolder> {

    private List<Inmueble> listaInmueble ;
    private Context context;
    private LayoutInflater layoutInflater;

    public InquilinoAdapter(List<Inmueble> listaInmueble, Context context, LayoutInflater layoutInflater) {
        this.listaInmueble = listaInmueble;
        this.context = context;
        this.layoutInflater = layoutInflater;
    }

    @NonNull
    @Override
    public InquilinoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.inmueble_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Inmueble inmueble = listaInmueble.get(position);
        ApiClient api = ApiClient.getApi();
        final Inquilino inquilino = api.obtenerInquilino(inmueble);
        holder.textViewInquilinoDirec.setText(listaInmueble.get(position).getDireccion());
        holder.textViewInquilinoPrecio.setText(String.valueOf(listaInmueble.get(position).getPrecio()));
        Glide.with(context)
                .load(listaInmueble.get(position).getImagen())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.imagenViewInquilinoInmu);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("inquilino", inquilino);
                //Navigation.findNavController(view).navigate(R.id.inquilinoDetalleFragment, bundle);
            }
        });
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView textViewInquilinoDirec, textViewInquilinoPrecio;
        private ImageView imagenViewInquilinoInmu;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewInquilinoDirec = itemView.findViewById(R.id.textViewInquilinoDirec);
            textViewInquilinoPrecio = itemView.findViewById(R.id.textViewInquilinoPrecio);
            imagenViewInquilinoInmu = itemView.findViewById(R.id.imagenViewInquilinoInmu);
        }
    }

    @Override
    public int getItemCount() {
        return listaInmueble.size();
    }

}

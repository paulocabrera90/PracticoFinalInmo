package com.example.plantilla.ui.inmueble;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
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

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

public class InmuebleAdapter extends RecyclerView.Adapter<InmuebleAdapter.ViewHolder>{
    private Context context;
    private List<Inmueble> listaInmueble ;
    private LayoutInflater layoutInflater;

    public InmuebleAdapter(List<Inmueble> lista, Context context, LayoutInflater layoutInflater) {
        this.listaInmueble = lista;
        this.context = context;
        this.layoutInflater = layoutInflater;
    }

    @NonNull
    @Override
    public InmuebleAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = layoutInflater.inflate(R.layout.inmueble_item, parent, false);
        return new ViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull InmuebleAdapter.ViewHolder holder, int position) {
        final Inmueble inmueble = listaInmueble.get(position);
        holder.tvPrecio.setText("$ " + String.valueOf(listaInmueble.get(position).getPrecio()));
        holder.tvDireccion.setText(listaInmueble.get(position).getDireccion());
        Glide.with(context)
                .load(listaInmueble.get(position).getImagen())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.ivInmueble);
    /*    Bitmap obtener_imagen = get_imagen(listaInmueble.get(position).getImagen());
        holder.ivInmueble.setImageBitmap(obtener_imagen);*/
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("inmueble",(Serializable) inmueble);
                Navigation.findNavController(view).navigate(R.id.nav_inmueble_detalle, bundle);
            }
        });
    }

    private Bitmap get_imagen(String url) {
        Bitmap bm = null;
        try {
            URL _url = new URL(url);
            URLConnection con = _url.openConnection();
            con.connect();
            InputStream is = con.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(is);
            bm = BitmapFactory.decodeStream(bis);
            bis.close();
            is.close();
        } catch (IOException e) {
            new IOException("Error Imagen in", e);
        }
        return bm;
    }

    @Override
    public int getItemCount() {
        return listaInmueble.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tvDireccion, tvPrecio;
        private ImageView ivInmueble;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDireccion = itemView.findViewById(R.id.textViewDirecInmu);
            tvPrecio = itemView.findViewById(R.id.textViewPrecioInmu);
            ivInmueble = itemView.findViewById(R.id.imagenViewInmueble);
        }
    }
}

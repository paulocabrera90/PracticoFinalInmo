package com.example.plantilla.ui.contrato.pago;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.plantilla.R;
import com.example.plantilla.modelo.Pago;

import java.util.List;

public class PagoAdapter extends RecyclerView.Adapter<PagoAdapter.ViewHolder>{
    private List<Pago> lista;
    private Context context;
    private LayoutInflater layoutInflater;

    public PagoAdapter(List<Pago> lista, Context context, LayoutInflater layoutInflater) {
        this.lista = lista;
        this.context = context;
        this.layoutInflater = layoutInflater;
    }

    @NonNull
    @Override
    public PagoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.pago_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PagoAdapter.ViewHolder holder, int position) {
        holder.tvCodigoP.setText(String.valueOf(lista.get(position).getIdPago()));
        holder.tvNroPago.setText(String.valueOf(lista.get(position).getNumero()));
        holder.tvCodigoCP.setText(String.valueOf(lista.get(position).getContrato().getIdContrato()));
        holder.tvImportP.setText(String.valueOf(lista.get(position).getImporte()));
        holder.tvfechaP.setText(lista.get(position).getFechaDePago());
    }
    public int getItemCount() {
        return lista.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvCodigoP, tvNroPago, tvCodigoCP, tvImportP, tvfechaP;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCodigoP = itemView.findViewById(R.id.tvCodigoPago);
            tvNroPago = itemView.findViewById(R.id.tvNroPago);
            tvCodigoCP = itemView.findViewById(R.id.tvCodigoCPago);
            tvImportP = itemView.findViewById(R.id.tvImportePago);
            tvfechaP = itemView.findViewById(R.id.tvFechaPago);
        }
    }
}

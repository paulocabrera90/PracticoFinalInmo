package com.example.plantilla.ui.inmueble;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.plantilla.R;
import com.example.plantilla.databinding.InmuebleDetalleFragmentBinding;
import com.example.plantilla.modelo.Inmueble;

public class InmuebleDetalleFragment extends Fragment {

    private InmuebleDetalleViewModel mViewModel;
    private InmuebleDetalleFragmentBinding binding;

    public static InmuebleDetalleFragment newInstance() {
        return new InmuebleDetalleFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = InmuebleDetalleFragmentBinding.inflate(inflater, container, false);
        mViewModel = new ViewModelProvider(this).get(InmuebleDetalleViewModel.class);
        View root = binding.getRoot();

        final TextView tvCodigo = binding.tvCodigoInmueble;
        final TextView tvAmbiente = binding.tvAmbiestesInmueble;
        final TextView tvDireccion = binding.tvDireccionInmueble;
        final TextView tvPrecio = binding.tvPrecioInmueble;
        final TextView tvUso = binding.tvUsoInmueble;
        final TextView tvTipo = binding.tvTipo;
        final CheckBox cbEstado = binding.cbEstadoInmueble;
        final ImageView imageInmueble = binding.ivFotoInmueble;
        mViewModel.getInmuebleM().observe(getViewLifecycleOwner(), new Observer<Inmueble>() {
            @Override
            public void onChanged(Inmueble inmueble) {
                tvCodigo.setText(inmueble.getIdInmueble() + "");
                tvAmbiente.setText(inmueble.getAmbientes() + "");
                tvDireccion.setText(inmueble.getDireccion());
                tvPrecio.setText(String.valueOf(inmueble.getPrecio()));
                tvUso.setText(inmueble.getUso());
                tvTipo.setText(inmueble.getTipo());
                cbEstado.setChecked(inmueble.isEstado());
                Glide.with(getContext())
                        .load(inmueble.getImagen())
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(imageInmueble);
                cbEstado.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mViewModel.guardarEstado(cbEstado.isChecked());
                    }
                });
            }
        });
        mViewModel.cargar((Inmueble) getArguments().getSerializable("inmueble"));
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(InmuebleDetalleViewModel.class);
        // TODO: Use the ViewModel
    }

}
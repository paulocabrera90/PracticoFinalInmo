package com.example.plantilla.ui.inquilino;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.plantilla.R;
import com.example.plantilla.databinding.InquilinoDetalleFragmentBinding;
import com.example.plantilla.modelo.Inmueble;
import com.example.plantilla.modelo.Inquilino;

public class InquilinoDetalleFragment extends Fragment {

    private InquilinoDetalleViewModel inquilinoDetalleViewModel;
    private InquilinoDetalleFragmentBinding binding;

    public static InquilinoDetalleFragment newInstance() {
        return new InquilinoDetalleFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        inquilinoDetalleViewModel = new ViewModelProvider(this).get(InquilinoDetalleViewModel.class);
        binding = InquilinoDetalleFragmentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        final TextView tvCodigoInqui = binding.textViewCodigoInqui;
        final TextView tvNombreInqui = binding.textViewNombreInqui;
        final TextView tvApellidoInqui = binding.textViewApellidoInqui;
        final TextView tvDniInqui = binding.textViewDniInqui;
        final TextView tvMailInqui = binding.textViewMailInqui;
        final TextView tvTelInqui = binding.textViewTelInqui;
        final TextView tvGaranteInqui = binding.textViewGaranteInqui;
        final TextView tvTelGarante = binding.textViewTelGarante;
        inquilinoDetalleViewModel.getInquilino().observe(getViewLifecycleOwner(), new Observer<Inquilino>() {
            @Override
            public void onChanged(Inquilino inquilino) {
                tvCodigoInqui.setText(inquilino.getIdInquilino() + "");
                tvNombreInqui.setText(inquilino.getNombre());
                tvApellidoInqui.setText(inquilino.getApellido());
                tvDniInqui.setText(inquilino.getDNI()+"");
                tvMailInqui.setText(inquilino.getEmail());
                tvTelInqui.setText(inquilino.getTelefono());
                tvGaranteInqui.setText(inquilino.getNombreGarante());
                tvTelGarante.setText(inquilino.getTelefonoGarante());
            }
        });
        inquilinoDetalleViewModel.cargarInquiDetalle((Inquilino) getArguments().getSerializable("inquilino"));
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        inquilinoDetalleViewModel = new ViewModelProvider(this).get(InquilinoDetalleViewModel.class);
        // TODO: Use the ViewModel
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
package com.example.plantilla.ui.contrato;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.plantilla.R;
import com.example.plantilla.databinding.ContratoDetalleFragmentBinding;
import com.example.plantilla.modelo.Contrato;

public class ContratoDetalleFragment extends Fragment {

    private ContratoDetalleViewModel contratoDetViewModel;
    private ContratoDetalleFragmentBinding binding;

    public static ContratoDetalleFragment newInstance() {
        return new ContratoDetalleFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        contratoDetViewModel = new ViewModelProvider(this).get(ContratoDetalleViewModel.class);
        binding = ContratoDetalleFragmentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        final TextView tvCodigoContrato = binding.textViewCodContr;
        final TextView tvInicioContrato = binding.textViewIniContr;
        final TextView tvFinCOntrato = binding.textViewFinContr;
        final TextView tvMontoContrato = binding.textViewMontContr;
        final TextView tvInquilinoContrato = binding.textViewInquiContr;
        final TextView tvInmuebleContrato = binding.textViewInmuContr;
        final Button btPagos = binding.btnPagos;

        contratoDetViewModel.getContratoM().observe(getViewLifecycleOwner(), new Observer<Contrato>() {
            @Override
            public void onChanged(final Contrato contrato) {
                tvCodigoContrato.setText(contrato.getIdContrato() + "");
                tvInicioContrato.setText(contrato.getFechaInicio());
                tvFinCOntrato.setText(contrato.getFechaFin());
                tvMontoContrato.setText("$ " + contrato.getMontoAlquiler());
                tvInquilinoContrato.setText(contrato.getInquilino().getNombreGarante()+" "+ contrato.getInquilino().getApellido());
                tvInmuebleContrato.setText("Inmueble en" + contrato.getInmueble().getDireccion());
                btPagos.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("pagos", contrato);
                        Navigation.findNavController(view).navigate(R.id.nav_pago_item, bundle);
                    }
                });
            }
        });
        contratoDetViewModel.cargarContrato((Contrato) getArguments().getSerializable("contrato"));
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        contratoDetViewModel = new ViewModelProvider(this).get(ContratoDetalleViewModel.class);
        // TODO: Use the ViewModel
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}
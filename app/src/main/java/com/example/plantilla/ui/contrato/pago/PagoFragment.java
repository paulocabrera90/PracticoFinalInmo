package com.example.plantilla.ui.contrato.pago;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.plantilla.R;
import com.example.plantilla.databinding.PagoFragmentBinding;
import com.example.plantilla.modelo.Pago;

import java.util.List;

public class PagoFragment extends Fragment {
    private RecyclerView recyclerViewPago;
    private PagoFragmentBinding binding;
    private PagoViewModel pagoViewModel;
    private PagoAdapter pagoAdapter;

    public static PagoFragment newInstance() {
        return new PagoFragment();
    }

    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        pagoViewModel = new ViewModelProvider(this).get(PagoViewModel.class);
        binding = PagoFragmentBinding.inflate(inflater, container, false);
        final View root = binding.getRoot();
        recyclerViewPago = root.findViewById(R.id.recyclerViewPagos);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 1, LinearLayoutManager.VERTICAL, false);
        recyclerViewPago.setLayoutManager(gridLayoutManager);
        pagoViewModel.getPagosM().observe(getViewLifecycleOwner(), new Observer<List<Pago>>() {
            @Override
            public void onChanged(List<Pago> pagos) {
                pagoAdapter = new PagoAdapter(pagos, root.getContext(), inflater);
                recyclerViewPago.setAdapter(pagoAdapter);
            }
        });
       // pagoViewModel.cargarPagos((Pago) getArguments().getSerializable("pagos"));
        pagoViewModel.cargarPagos(getArguments());
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        pagoViewModel = new ViewModelProvider(this).get(PagoViewModel.class);
        // TODO: Use the ViewModel
    }

    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}
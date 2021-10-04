package com.example.plantilla.ui.contrato;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.plantilla.R;
import com.example.plantilla.databinding.ContratoFragmentBinding;
import com.example.plantilla.modelo.Inmueble;

import java.util.List;

public class ContratoFragment extends Fragment {

    private RecyclerView recyclerViewContrato;
    private ContratoAdapter contratoAdapter;
    private ContratoViewModel contratoViewModel;
    private ContratoFragmentBinding binding;


    public static ContratoFragment newInstance() {
        return new ContratoFragment();
    }

    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        contratoViewModel = new ViewModelProvider(this).get(ContratoViewModel.class);
        binding = ContratoFragmentBinding.inflate(inflater, container, false);
        final View root = binding.getRoot();
        recyclerViewContrato = root.findViewById(R.id.recyclerViewContratos);
        //GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2, LinearLayoutManager.VERTICAL, false);
        //rvContrato.setLayoutManager(gridLayoutManager);

        recyclerViewContrato.addItemDecoration(new DividerItemDecoration(this.getContext() , DividerItemDecoration.VERTICAL));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getContext());
        recyclerViewContrato.setLayoutManager(linearLayoutManager);

        contratoViewModel.getListaInmueble().observe(getViewLifecycleOwner(), new Observer<List<Inmueble>>() {
            @Override
            public void onChanged(List<Inmueble> inmuebles) {
                contratoAdapter = new ContratoAdapter(inmuebles, root.getContext(), inflater);
                recyclerViewContrato.setAdapter(contratoAdapter);
            }
        });
        contratoViewModel.cargarInmueblesAlquilados();
        return root;
    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        contratoViewModel = new ViewModelProvider(this).get(ContratoViewModel.class);
        // TODO: Use the ViewModel
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}
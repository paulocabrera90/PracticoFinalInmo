package com.example.plantilla.ui.inmueble;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.plantilla.R;
import com.example.plantilla.databinding.FragmentInmuebleBinding;
import com.example.plantilla.modelo.Inmueble;
import com.example.plantilla.request.ApiClient;

import java.util.List;

public class InmuebleFragment extends Fragment {
    private RecyclerView recyclerViewInmueble;
    private InmuebleViewModel inmuebleViewModel;
    private FragmentInmuebleBinding binding;
    private InmuebleAdapter inmuebleAdapter;
    private List<Inmueble> listaInmueble ;
    private ApiClient apiClient;

    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container,@Nullable Bundle savedInstanceState) {
        inmuebleViewModel = new ViewModelProvider(this).get(InmuebleViewModel.class);
        binding = FragmentInmuebleBinding.inflate(inflater, container, false);
        final View rootView = binding.getRoot();
        recyclerViewInmueble = (RecyclerView) rootView.findViewById(R.id.recyclerViewInmuebles);
        recyclerViewInmueble.addItemDecoration(new DividerItemDecoration(this.getContext() , DividerItemDecoration.VERTICAL));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getContext());
        recyclerViewInmueble.setLayoutManager(linearLayoutManager);
        inmuebleViewModel.getListaInmuebles().observe(getViewLifecycleOwner(), new Observer<List<Inmueble>>() {
            @Override
            public void onChanged(List<Inmueble> inmuebles) {
                inmuebleAdapter = new InmuebleAdapter(inmuebles, rootView.getContext(), inflater);
                recyclerViewInmueble.setAdapter(inmuebleAdapter);
            }
        });
        inmuebleViewModel.cargarInmuebles();
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
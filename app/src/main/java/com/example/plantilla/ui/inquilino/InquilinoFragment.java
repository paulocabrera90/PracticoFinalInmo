package com.example.plantilla.ui.inquilino;

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
import com.example.plantilla.databinding.InmuebleDetalleFragmentBinding;
import com.example.plantilla.databinding.InquilinoDetalleFragmentBinding;
import com.example.plantilla.databinding.InquilinoFragmentBinding;
import com.example.plantilla.modelo.Inmueble;

import java.util.List;

public class InquilinoFragment extends Fragment {

    private InquilinoViewModel inquiViewModel;
    private RecyclerView recyclerViewInquilino;
    private InquilinoAdapter inquilinoAdapter;
    private InquilinoFragmentBinding binding;

    public static InquilinoFragment newInstance() {
        return new InquilinoFragment();
    }

    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        inquiViewModel = new ViewModelProvider(this).get(InquilinoViewModel.class);
        binding = InquilinoFragmentBinding.inflate(inflater, container, false);
        final View root = binding.getRoot();
        recyclerViewInquilino = (RecyclerView) root.findViewById(R.id.recyclerViewInquilinos);
        //GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2, LinearLayoutManager.VERTICAL, false);
        //rvInquilino.setLayoutManager(gridLayoutManager);
       LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getContext());
        recyclerViewInquilino.addItemDecoration(new DividerItemDecoration(this.getContext() , DividerItemDecoration.VERTICAL));
        recyclerViewInquilino.setLayoutManager(linearLayoutManager);
        inquiViewModel.getListaInmueble().observe(getViewLifecycleOwner(), new Observer<List<Inmueble>>() {
            @Override
            public void onChanged(List<Inmueble> inmuebles) {
                inquilinoAdapter = new InquilinoAdapter(inmuebles, root.getContext(), inflater);
                recyclerViewInquilino.setAdapter(inquilinoAdapter);
            }
        });
        inquiViewModel.propiedadesAlquiladas();
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}
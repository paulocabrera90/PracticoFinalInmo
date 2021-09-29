package com.example.plantilla.ui.inicio;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.plantilla.R;
import com.example.plantilla.databinding.FragmentInicioBinding;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class InicioFragment extends Fragment {
    private Double LATITUD_INICIO = -33.2883540;
    private Double LONGITUD_INICIO = -66.3101345;
    private LatLng inmobiliaria = new LatLng(LATITUD_INICIO, LONGITUD_INICIO);
    private GoogleMap mapInicio;
    private FragmentInicioBinding binding;


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentInicioBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.mapaInicio);
        if (mapFragment != null) {
            mapFragment.getMapAsync(new MapaActual());
        }
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    private class MapaActual implements OnMapReadyCallback {
        @Override
        public void onMapReady(GoogleMap mapa) {
            mapInicio = mapa;
            mapInicio.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
            CameraPosition camPos = new CameraPosition.Builder()
                    .target(inmobiliaria)
                    .zoom(19)
                    .bearing(50)
                    .tilt(50)
                    .build();
            CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(camPos);
            MarkerOptions marcadorInmueble = new MarkerOptions().position(inmobiliaria);
            marcadorInmueble.title("Inmobiliaria Mitchell");

            mapa.animateCamera(cameraUpdate);
            mapa.addMarker(marcadorInmueble);
        }
    }
}
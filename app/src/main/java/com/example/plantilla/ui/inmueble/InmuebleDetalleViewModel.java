package com.example.plantilla.ui.inmueble;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.plantilla.modelo.Inmueble;
import com.example.plantilla.request.ApiClient;

public class InmuebleDetalleViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    private MutableLiveData<Inmueble> inmuebleMuta;
    private Inmueble inmueble;


    public LiveData<Inmueble> getInmuebleM() {
        if(inmuebleMuta == null){
            inmuebleMuta = new MutableLiveData<>();
        }
        return inmuebleMuta;
    }
    public void cargarInmueble(Inmueble inmueble){
        inmuebleMuta.setValue(inmueble);
    }
    public void guardarEstado(Boolean b){
        ApiClient api = ApiClient.getApi();
        inmuebleMuta.getValue().setEstado(b);
        api.actualizarInmueble(inmueble);
    }
}
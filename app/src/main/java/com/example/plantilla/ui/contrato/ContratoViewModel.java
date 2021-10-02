package com.example.plantilla.ui.contrato;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.plantilla.modelo.Inmueble;
import com.example.plantilla.request.ApiClient;

import java.util.List;

public class ContratoViewModel extends ViewModel {
    // TODO: Implement the ViewModel

    private MutableLiveData<List<Inmueble>> listaInmueble;

    public LiveData<List<Inmueble>> getListaInmueble() {
        if(listaInmueble == null){
            listaInmueble = new MutableLiveData<>();
        }
        return listaInmueble;
    }
    public void cargarInmueblesAlquilados(){
        ApiClient api = ApiClient.getApi();
        listaInmueble.setValue(api.obtenerPropiedadesAlquiladas());
    }
}
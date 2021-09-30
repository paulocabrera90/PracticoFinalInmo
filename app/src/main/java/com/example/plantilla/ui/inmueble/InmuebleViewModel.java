package com.example.plantilla.ui.inmueble;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.plantilla.modelo.Inmueble;
import com.example.plantilla.request.ApiClient;

import java.util.List;

public class InmuebleViewModel extends ViewModel {

    private MutableLiveData<List<Inmueble>> listaInmuebles;

    public LiveData<List<Inmueble>> getListaInmuebles(){

        if(listaInmuebles == null){
            listaInmuebles = new MutableLiveData<>();
        }
        return listaInmuebles;
    }
    public void cargarInmuebles(){
        ApiClient api = ApiClient.getApi();
        listaInmuebles.setValue(api.obtnerPropiedades());
    }

}
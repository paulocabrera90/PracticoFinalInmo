package com.example.plantilla.ui.perfil;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.plantilla.modelo.Propietario;
import com.example.plantilla.request.ApiClient;

public class PerfilViewModel extends ViewModel {

    private MutableLiveData<Boolean> habilitado;
    private MutableLiveData<Propietario> propietario;
    private MutableLiveData<String> textButton;
    private ApiClient api;
    public PerfilViewModel() {
        propietario = new MutableLiveData<>();
        api = ApiClient.getApi();
        habilitado = new MutableLiveData<>();
    }

    public LiveData<String> getTextButton() {
        if(textButton == null){
            textButton = new MutableLiveData<>();
        }
        return textButton;
    }

    public LiveData<Boolean> getHabilitado() {
        if(habilitado == null){
            habilitado = new MutableLiveData<>();
        }
        return habilitado;
    }

    public LiveData<Propietario> getPropietario(){
        if(propietario == null){
            propietario = new MutableLiveData<>();
        }
        return propietario;
    }

    public void cargarPropietario(){
        propietario.setValue(api.obtenerUsuarioActual());
    }
    public void actPerfil(Propietario prop){
        propietario.setValue(prop);
    }

    public void cambiarHabilitado(String t, Propietario p){
        if(t.equals("Editar")){
            habilitado.setValue(true);
            textButton.setValue("Guardar");
        }else {
            habilitado.setValue(false);
            ApiClient api = ApiClient.getApi();
            api.actualizarPerfil(p);
            this.actPerfil(p);
            textButton.setValue("Editar");
        }
    }
}
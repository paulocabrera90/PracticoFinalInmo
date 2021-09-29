package com.example.plantilla.ui.perfil;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.plantilla.modelo.Propietario;
import com.example.plantilla.request.ApiClient;

public class PerfilViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    private MutableLiveData<Boolean> habilitado;
    private MutableLiveData<Propietario> propietarioM;
    private MutableLiveData<String> textButton;
    private ApiClient api;
    public PerfilViewModel() {
        propietarioM = new MutableLiveData<>();
        api = ApiClient.getApi();
        mText = new MutableLiveData<>();
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

    public LiveData<Propietario> getPropietarioM(){
        if(propietarioM == null){
            propietarioM = new MutableLiveData<>();
        }
        return propietarioM;
    }

    public void cargarPropietario(){
        propietarioM.setValue(api.obtenerUsuarioActual());
    }

    public void cambiarHabilitado(String t, Propietario p){
        if(t.equals("Editar")){
            habilitado.setValue(true);
            textButton.setValue("Guardar");
        }else {
            habilitado.setValue(false);
            ApiClient api = ApiClient.getApi();
            api.actualizarPerfil(p);
            textButton.setValue("Editar");
        }
    }
}
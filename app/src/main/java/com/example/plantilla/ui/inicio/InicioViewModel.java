package com.example.plantilla.ui.inicio;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.plantilla.modelo.Propietario;
import com.example.plantilla.request.ApiClient;

public class InicioViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    private MutableLiveData<Propietario> propietario;
    private ApiClient api;

    public InicioViewModel() {
        mText = new MutableLiveData<>();
        propietario = new MutableLiveData<>();
        api = ApiClient.getApi();
        mText.setValue("This is home fragment");
    }

    public LiveData<Propietario> getPropietario(){
        if(propietario == null){
            propietario = new MutableLiveData<>();
        }
        return propietario;
    }

    public LiveData<String> getText() {
        return mText;
    }

    public void refrescarPropietario(){
        propietario.setValue(api.obtenerUsuarioActual());
    }
}
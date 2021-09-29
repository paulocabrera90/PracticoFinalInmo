package com.example.plantilla;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.plantilla.modelo.Propietario;

public class MainActivityViewModel extends ViewModel {
    private MutableLiveData<Propietario> propietario;

    public MainActivityViewModel(){
        propietario = new MutableLiveData<>();
    }

    public MutableLiveData<Propietario> getPropietario(){
        if(propietario == null){
            propietario = new MutableLiveData<>();
        }
        return propietario;
    }

    public void actPerfil(Propietario prop){
        propietario.setValue(prop);
    }
}

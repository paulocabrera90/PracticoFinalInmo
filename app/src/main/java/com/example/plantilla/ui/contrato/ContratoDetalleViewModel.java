package com.example.plantilla.ui.contrato;

import android.os.Bundle;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.plantilla.modelo.Contrato;

public class ContratoDetalleViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    private MutableLiveData<Contrato> contratoM;
    private Contrato contrato;

    public LiveData<Contrato> getContratoM() {
        if(contratoM == null){
            contratoM = new MutableLiveData<>();
        }
        return contratoM;
    }
    public void cargarContrato(Contrato contrato){
        contratoM.setValue(contrato);
    }
}
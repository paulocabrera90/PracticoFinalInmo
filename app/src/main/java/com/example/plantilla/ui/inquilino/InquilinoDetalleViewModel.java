package com.example.plantilla.ui.inquilino;

import android.os.Bundle;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.plantilla.modelo.Inquilino;

public class InquilinoDetalleViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    private MutableLiveData<Inquilino> inquilinoM;
    private Inquilino inquilino;

    public LiveData<Inquilino> getInquilino() {
        if(inquilinoM == null){
            inquilinoM = new MutableLiveData<>();
        }
        return inquilinoM;
    }
    public void cargarInquiDetalle(Inquilino inquilino){
        inquilinoM.setValue(inquilino);
    }
}
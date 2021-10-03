package com.example.plantilla.ui.contrato.pago;

import android.os.Bundle;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.plantilla.modelo.Contrato;
import com.example.plantilla.modelo.Pago;
import com.example.plantilla.request.ApiClient;

import java.util.List;

public class PagoViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    private MutableLiveData<List<Pago>> pagosM;

    public LiveData<List<Pago>> getPagosM() {
        if(pagosM == null){
            pagosM = new MutableLiveData<>();
        }
        return pagosM;
    }

   public void cargarPagos(Contrato contratoPago){
        ApiClient api = ApiClient.getApi();
        pagosM.setValue(api.obtenerPagos(contratoPago));
    }
}
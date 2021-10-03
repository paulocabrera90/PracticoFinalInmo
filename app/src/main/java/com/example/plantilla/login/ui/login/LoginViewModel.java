package com.example.plantilla.login.ui.login;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import android.util.Patterns;

import com.example.plantilla.R;
import com.example.plantilla.login.data.LoginRepository;
import com.example.plantilla.login.data.Result;
import com.example.plantilla.modelo.Propietario;

public class LoginViewModel extends ViewModel {

    private MutableLiveData<LoginFormState> loginFormState = new MutableLiveData<>();
    private MutableLiveData<LoginResult> loginResult = new MutableLiveData<>();
    private LoginRepository loginRepository;
    private MutableLiveData<Boolean> estadoM;
    private MutableLiveData<Double> log;
    private int activador = 0;

    LoginViewModel(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    LiveData<LoginFormState> getLoginFormState() {
        return loginFormState;
    }

    LiveData<LoginResult> getLoginResult() {
        return loginResult;
    }

    public void login(String username, String password) {
        // can be launched in a separate asynchronous job
        Result<Propietario> result = loginRepository.login(username, password);

        if (result instanceof Result.Success) {
            Propietario data = ((Result.Success<Propietario>) result).getData();
            loginResult.setValue(new LoginResult(new LoggedInUserView(data.getNombre())));
        } else {
            loginResult.setValue(new LoginResult(R.string.login_failed));
        }
    }

    public LiveData<Boolean> getEstadoM() {
        if(estadoM == null){
            estadoM = new MutableLiveData<>();
        }
        return estadoM;
    }

    public LiveData<Double> getLog() {
        if(log == null){
            log = new MutableLiveData<>();
        }
        return log;
    }

    public void loginDataChanged(String username, String password) {
        if (!isUserNameValid(username)) {
            loginFormState.setValue(new LoginFormState(R.string.invalid_username, null));
        } else if (!isPasswordValid(password)) {
            loginFormState.setValue(new LoginFormState(null, R.string.invalid_password));
        } else {
            loginFormState.setValue(new LoginFormState(true));
        }
    }

    public void sensorG(float movi){
        if(movi > 1 || movi < -1){
            activador++;
        }
        if(activador > 30){
            //log.setValue(Double.valueOf(movi));
            activador = 0;
            estadoM.setValue(true);

        }
    }

    // A placeholder username validation check
    private boolean isUserNameValid(String username) {
        if (username == null) {
            return false;
        }
        if (username.contains("@")) {
            return Patterns.EMAIL_ADDRESS.matcher(username).matches();
        } else {
            return !username.trim().isEmpty();
        }
    }

    // A placeholder password validation check
    private boolean isPasswordValid(String password) {
        return password != null && password.trim().length() > 2;
    }
}
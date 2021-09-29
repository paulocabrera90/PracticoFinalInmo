package com.example.plantilla.login.data;

import com.example.plantilla.login.data.model.LoggedInUser;
import com.example.plantilla.modelo.Propietario;
import com.example.plantilla.request.ApiClient;

import java.io.IOException;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource {

    public Result<Propietario> login(String username, String password) {

        try {
            // TODO: handle loggedInUser authentication
            Propietario propietario = new Propietario();
            ApiClient api = ApiClient.getApi();
            propietario = api.login(username, password);
            if(propietario != null){
                return new Result.Success<>(propietario);
            }else{
               return null;
            }

        } catch (Exception e) {
            return new Result.Error(new IOException("Error logging in", e));
        }
    }

    public void logout() {
        // TODO: revoke authentication
    }
}
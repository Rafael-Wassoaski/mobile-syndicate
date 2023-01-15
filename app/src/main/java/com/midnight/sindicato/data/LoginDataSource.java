package com.midnight.sindicato.data;

import android.content.res.Resources;

import com.google.gson.Gson;
import com.midnight.sindicato.R;
import com.midnight.sindicato.data.model.CustomUser;
import com.midnight.sindicato.data.model.LoggedInUser;
import com.midnight.sindicato.util.RequestMaker;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource {

    private String baseUrl = Resources.getSystem().getString(R.string.base_url);

    public Result<LoggedInUser> login(String username, String password) {

        CustomUser user = new CustomUser(username, password);
        RequestMaker<CustomUser> request = new RequestMaker<>();
        Call loginRequest = request.post(user, baseUrl);

        //try with resources, pois vamos abrir uma conexão e vai ser fechada automaticamente pelo try with resources
        try(Response response = loginRequest.execute()) {
            String jsonResponse = response.body().string();
            Gson gson = new Gson();
            CustomUser customUserDate = gson.fromJson(jsonResponse, CustomUser.class);

            LoggedInUser loggedInUser =
                    new LoggedInUser(
                            customUserDate.getId().toString(),
                            customUserDate.getName());

            return new Result.Success<>(loggedInUser);
        }catch (IOException e ){
            return new Result.Error(new IOException("Error logging in", e));
        }


    }

    public void logout() {
        // TODO: revoke authentication
    }
}

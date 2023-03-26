package com.midnight.sindicato.data;

import android.content.Context;
import android.util.Log;

import com.midnight.sindicato.R;
import com.midnight.sindicato.data.login.LoginResultState;
import com.midnight.sindicato.data.model.CustomUser;
import com.midnight.sindicato.data.model.LoggedInUser;
import com.midnight.sindicato.ui.login.LoginViewModel;
import com.midnight.sindicato.util.JsonUtils;
import com.midnight.sindicato.util.RequestMaker;

import java.io.IOException;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

import okhttp3.Call;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource {

    private Context context;
    private String baseUrl;

    public LoginDataSource(Context context) {
        this.context = context;
        this.baseUrl = context.getString(R.string.base_url);
    }

    public void login(String username, String password, LoginViewModel loginViewModel) {
        Call call = this.createHttpCall(username, password);
        this.makeRequest(call, loginViewModel);
    }

    private Call createHttpCall(String username, String password) {
        CustomUser user = new CustomUser(username, password);
        RequestMaker<CustomUser> request = new RequestMaker<>();
        String loginUrl = baseUrl + "api/v1/user/login";
        Call loginRequest = request.post(user, loginUrl);

        return loginRequest;
    }

    private Result<LoggedInUser> makeLogin(Call loginRequest) {
        try {

            String jsonResponse = RequestMaker.makeRequest(loginRequest, HttpsURLConnection.HTTP_OK);
            Log.d("Request login", "JSON " +jsonResponse);

            JsonUtils<CustomUser> jsonUtils = new JsonUtils<>(CustomUser.class);
            CustomUser customUserDate = jsonUtils.jsonStringToEntity(jsonResponse);

            LoggedInUser loggedInUser = LoggedInUser.fromCustomUser(customUserDate);
            Log.d("Request login", "login efetuado com sucesso " + loggedInUser.toString());
            return new Result.Success<>(loggedInUser);
        } catch (Exception e) {
            Log.d("Request login", "Erro ao realizar o login " + e.getLocalizedMessage());
            return new Result.Error(new IOException("Erro ao realizar o login", e));
        }
    }

    private void makeRequest(Call call, LoginViewModel loginViewModel) {
        final LoginResultState resultState = new LoginResultState();
        Runnable requestRunnable = new Runnable() {
            @Override
            public void run() {
                Result result = makeLogin(call);
                resultState.setResult(result);
                loginViewModel.loginCallback(resultState.getResult());
            }
        };

        Thread thread = new Thread(requestRunnable);
        thread.start();
    }

    public void logout() {
        // TODO: revoke authentication
    }
}

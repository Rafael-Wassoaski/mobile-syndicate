package com.midnight.sindicato.data;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.midnight.sindicato.R;
import com.midnight.sindicato.data.login.LoginResultState;
import com.midnight.sindicato.data.model.CustomUser;
import com.midnight.sindicato.data.model.LoggedInUser;
import com.midnight.sindicato.ui.login.LoginViewModel;
import com.midnight.sindicato.util.RequestMaker;

import java.io.IOException;

import javax.net.ssl.HttpsURLConnection;

import okhttp3.Call;
import okhttp3.Response;
import okhttp3.internal.http.HttpHeaders;

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

    public void login(String username, String password, LoginViewModel loginViewModel){
        Call call = this.createHttpCall(username, password);
        this.makeRequest(call, loginViewModel);
    }

    private Call createHttpCall(String username, String password){
        CustomUser user = new CustomUser(username, password);
        RequestMaker<CustomUser> request = new RequestMaker<>();
        String loginUrl = baseUrl + "api/v1/user/login";
        Call loginRequest = request.post(user, loginUrl);

        return loginRequest;
    }

    private Result<LoggedInUser> makeLoginRequest(Call loginRequest) {
        //try with resources, pois vamos abrir uma conexão que vai ser fechada automaticamente pelo try with resources
        try(Response response = loginRequest.execute()) {
            if(response.code() != HttpsURLConnection.HTTP_OK){
                Log.d("Login result" , "Erro ao efetuar login " +  response.code());
                return new Result.Error(new IOException("Erro ao realizar o login " + response.code()));
            }

            String jsonResponse = response.body().string();
            Gson gson = new Gson();
            CustomUser customUserDate = gson.fromJson(jsonResponse, CustomUser.class);

            LoggedInUser loggedInUser =
                    new LoggedInUser(
                            customUserDate.getId().toString(),
                            customUserDate.getName());

            Log.d("Login result" , "Login efetuado com sucesso");
            return new Result.Success<>(loggedInUser);
        }catch (Exception e ){
            Log.d("Login result" , "Erro ao efetuar login " + e);
            return new Result.Error(new IOException("Erro ao realizar o login", e));
        }
    }

    private void makeRequest(Call call, LoginViewModel loginViewModel){
        final LoginResultState resultState = new LoginResultState();
        Runnable requestRunnable = new Runnable() {
            @Override
            public void run() {
                Result result = makeLoginRequest(call);
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

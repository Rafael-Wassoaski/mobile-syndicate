package com.midnight.sindicato.util;

import android.util.Log;

import com.google.gson.Gson;
import com.midnight.sindicato.data.Result;

import java.io.IOException;
import java.util.Optional;

import javax.net.ssl.HttpsURLConnection;

import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class RequestMaker<T> {
    private OkHttpClient httpClient = new OkHttpClient();

    public Call post(T entity, String url) {

        Log.d("Request", "Post para " + url);
        Gson gson = new Gson();
        String json = gson.toJson(entity);
        Log.d("Request Info", "Corpo da requisicao: \n" + json);

        RequestBody requestBody = RequestBody.create(json, MediaType.get("application/json; charset=utf-8"));
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody).
                build();

        return httpClient.newCall(request);
    }

    public static String makeRequest(Call call, int acceptedStatus) throws IOException {
        Response response = call.execute();

        if (response.code() != acceptedStatus) {
            Log.d("Request result", "Erro ao realizar request " + response.code() + " mensagem: " + response.body().string());
            throw new IOException("Erro ao realizar o request " + response.code());
        }

        String jsonResponse = response.body().string();
        Log.d("Request result", "Request efetuado com sucesso: " + response.code() + " json " + jsonResponse);
        response.close();

        return jsonResponse;
    }
}

package com.midnight.sindicato.util;

import com.google.gson.Gson;

import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class RequestMaker<T> {
    private OkHttpClient httpClient = new OkHttpClient();

    public Call post(T entity, String url){
        Gson gson = new Gson();
        String userJson = gson.toJson(entity);

        RequestBody requestBody = RequestBody.create(userJson, MediaType.get("application/json; charset=utf-8"));
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody).
                build();

        return  httpClient.newCall(request);
    }
}

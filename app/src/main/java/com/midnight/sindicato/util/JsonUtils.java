package com.midnight.sindicato.util;

import com.google.gson.Gson;
import com.midnight.sindicato.data.model.CustomUser;

public class JsonUtils<T> {
    private Gson gson;
    private final Class<T> tClass;

    public JsonUtils(Class<T> tClass) {
        this.gson = new Gson();
        this.tClass = tClass;
    }

    public T jsonStringToEntity(String json){
        T element = gson.fromJson(json, this.tClass);

        return element;
    }
}

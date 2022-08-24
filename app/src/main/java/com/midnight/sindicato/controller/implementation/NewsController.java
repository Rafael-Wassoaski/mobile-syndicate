package com.midnight.sindicato.controller.implementation;

import android.util.Log;
import android.view.View;

import com.midnight.sindicato.controller.ActionController;

public class NewsController extends ActionController {

    public NewsController(){
        super("Notícias");
    }

    @Override
    public String performAction() {
        return null;
    }

    @Override
    public void clickAction(View view) {
        Log.d("Click aqui", "Clicou news");
    }
}

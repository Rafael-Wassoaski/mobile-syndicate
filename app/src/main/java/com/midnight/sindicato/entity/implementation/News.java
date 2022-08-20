package com.midnight.sindicato.entity.implementation;

import android.util.Log;
import android.view.View;

import com.midnight.sindicato.entity.Action;

public class News extends Action {

    public News(){
        super("Notícias");
    }

    @Override
    public String performeAction() {
        return null;
    }

    @Override
    public void clickAction(View view) {
        Log.d("Click aqui", "Clicou news");
    }
}

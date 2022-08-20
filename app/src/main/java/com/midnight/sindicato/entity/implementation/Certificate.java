package com.midnight.sindicato.entity.implementation;

import android.util.Log;
import android.view.View;

import com.midnight.sindicato.entity.Action;

public class Certificate extends Action {

    public Certificate(){
        super("Atestados");
    }

    @Override
    public String performeAction() {
        return null;
    }

    @Override
    public void clickAction(View view) {
        Log.d("Click aqui", "Clicou Certificate");
    }
}

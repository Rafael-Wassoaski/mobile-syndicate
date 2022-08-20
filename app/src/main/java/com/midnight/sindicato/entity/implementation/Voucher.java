package com.midnight.sindicato.entity.implementation;

import android.util.Log;
import android.view.View;

import com.midnight.sindicato.entity.Action;

public class Voucher extends Action {

    public Voucher(){
        super("Vales");
    }

    @Override
    public String performeAction() {
        return "Voucher";
    }

    @Override
    public void clickAction(View view) {
        Log.d("Click aqui", "Clicou Voucher");
    }
}

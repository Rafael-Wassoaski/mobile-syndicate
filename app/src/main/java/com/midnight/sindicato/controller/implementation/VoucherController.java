package com.midnight.sindicato.controller.implementation;

import android.util.Log;
import android.view.View;

import com.midnight.sindicato.controller.ActionController;

public class VoucherController extends ActionController {

    public VoucherController(){
        super("Vales");
    }

    @Override
    public String performAction() {
        return "Voucher";
    }

    @Override
    public void clickAction(View view) {
        Log.d("Click aqui", "Clicou Voucher");
    }
}

package com.midnight.sindicato.controller.implementation;

import android.util.Log;
import android.view.View;

import com.midnight.sindicato.controller.ActionController;
import com.midnight.sindicato.ui.MainActivity;
import com.midnight.sindicato.ui.voucher.VoucherActivity;

public class VoucherController extends ActionController {

    public VoucherController() {
        super("Vales");
    }

    @Override
    public String performAction() {
        return "Voucher";
    }

    @Override
    protected Class<?> getControllerActivity() {
        return VoucherActivity.class;
    }
}

package com.midnight.sindicato.controller.implementation;

import android.content.Intent;
import android.util.Log;
import android.view.View;

import com.midnight.sindicato.controller.ActionController;
import com.midnight.sindicato.ui.certificate.CertificateActivity;

public class CertificateController extends ActionController {

    public CertificateController() {
        super("Atestados");
    }

    @Override
    public String performAction() {
        return null;
    }

    @Override
    protected Class<?> getControllerActivity() {
        return CertificateActivity.class;
    }
}

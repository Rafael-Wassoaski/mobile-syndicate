package com.midnight.sindicato.util.validators;

import android.content.res.Resources;
import android.text.Editable;

import com.midnight.sindicato.R;

public class PasswordValidation extends EditableValidator{

    public PasswordValidation() {
        this.errorMessage =     Resources.getSystem().getString(R.string.min_password_length);
    }

    @Override
    public void validateEditable(Editable editable) throws Exception {
        String password = editable.toString();

        if(password.length() < 8){
            throw new Exception(errorMessage);
        }
    }

    public void confirmPasswords(String password, String passwordConfirm) throws Exception {
        if (!password.equals(passwordConfirm)) {
            String differentPassword = Resources.getSystem().getString(R.string.differente_passwords);
            throw new Exception(differentPassword);
        }
    }
}

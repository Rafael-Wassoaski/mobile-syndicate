package com.midnight.sindicato.util.validators;

import android.content.res.Resources;
import android.text.Editable;

import com.midnight.sindicato.R;
import com.midnight.sindicato.ui.MainActivity;
import com.midnight.sindicato.ui.SignUpActivity;

public class PasswordValidation extends EditableValidator{

    public PasswordValidation() {
        this.errorMessage = MainActivity.getContext().getString(R.string.min_password_length);
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
            String differentPassword = SignUpActivity.getContext().getString(R.string.differente_passwords);
            throw new Exception(differentPassword);
        }
    }
}

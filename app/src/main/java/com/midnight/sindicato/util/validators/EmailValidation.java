package com.midnight.sindicato.util.validators;

import android.content.res.Resources;
import android.text.Editable;
import android.text.TextUtils;
import android.util.Patterns;

import com.midnight.sindicato.R;

public class EmailValidation extends EditableValidator{

    public EmailValidation() {
        this.errorMessage = Resources.getSystem().getString(R.string.invalid_mail);
    }

    @Override
    public void validateEditable(Editable editable) throws Exception {
        String email = editable.toString();

        if (!TextUtils.isEmpty(email) && !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            throw new Exception(errorMessage);
        }
    }
}

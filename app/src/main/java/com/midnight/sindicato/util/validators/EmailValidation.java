package com.midnight.sindicato.util.validators;

import android.content.res.Resources;
import android.text.Editable;
import android.text.TextUtils;
import android.util.Patterns;

import com.midnight.sindicato.R;
import com.midnight.sindicato.ui.MainActivity;

public class EmailValidation extends EditableValidator{

    @Override
    public void validateEditable(Editable editable) throws Exception {
        String email = editable.toString();
        this.errorMessage = MainActivity.getContext().getString(R.string.invalid_mail);

        if (!TextUtils.isEmpty(email) && !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            throw new Exception(errorMessage);
        }
    }
}

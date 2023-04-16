package com.midnight.sindicato.util.validators;

import android.content.res.Resources;
import android.text.Editable;
import android.text.TextUtils;

import com.midnight.sindicato.R;

public class EmptyFieldValidation extends EditableValidator{

    public EmptyFieldValidation() {
        this.errorMessage = Resources.getSystem().getString(R.string.required_empty_field);
    }

    @Override
    public void validateEditable(Editable editable) throws Exception {
        String fieldValue = editable.toString();

        if (TextUtils.isEmpty(fieldValue)) {
            throw new Exception(errorMessage);
        }
    }
}

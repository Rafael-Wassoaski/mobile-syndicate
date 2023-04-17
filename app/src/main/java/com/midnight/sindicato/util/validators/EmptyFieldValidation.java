package com.midnight.sindicato.util.validators;

import android.content.res.Resources;
import android.text.Editable;
import android.text.TextUtils;
import android.util.Log;

import com.midnight.sindicato.R;
import com.midnight.sindicato.ui.MainActivity;

public class EmptyFieldValidation extends EditableValidator{

    @Override
    public void validateEditable(Editable editable) throws Exception {
        String fieldValue = editable.toString();
        this.errorMessage = MainActivity.getContext().getString(R.string.required_empty_field);

        if (TextUtils.isEmpty(fieldValue)) {
            throw new Exception(errorMessage);
        }
    }
}

package com.midnight.sindicato.util.validators;

import android.content.res.Resources;
import android.text.Editable;

import com.midnight.sindicato.R;

protected abstract class EditableValidator {
    protected String errorMessage = Resources.getSystem().getString(R.string.undefined_error);

    public void validateEditable(Editable editable) throws Exception {
        throw new Exception(errorMessage);
    }
}

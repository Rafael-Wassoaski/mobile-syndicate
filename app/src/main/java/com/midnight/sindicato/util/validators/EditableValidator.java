package com.midnight.sindicato.util.validators;

import android.content.res.Resources;
import android.text.Editable;
import android.widget.Button;

import com.midnight.sindicato.R;

public abstract class EditableValidator {
    protected String errorMessage;

    public abstract void validateEditable(Editable editable) throws Exception;
}

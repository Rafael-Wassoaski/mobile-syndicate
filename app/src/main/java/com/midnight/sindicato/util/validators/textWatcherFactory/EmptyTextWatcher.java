package com.midnight.sindicato.util.validators.textWatcherFactory;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.midnight.sindicato.util.validators.EmptyFieldValidation;

public class EmptyTextWatcher extends TextWatcherFactory {

    @Override
    public TextWatcher getTextWatcher(EditText editText){
        EmptyFieldValidation emptyValidation = new EmptyFieldValidation();

        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                try {
                    editText.setError(null);
                    emptyValidation.validateEditable(editable);
                } catch (Exception e) {
                    editText.setError(e.getLocalizedMessage());
                    e.printStackTrace();
                }
            }
        };
    }
}


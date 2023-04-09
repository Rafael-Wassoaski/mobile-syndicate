package com.midnight.sindicato.util.validators.textWatcherFactory;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.midnight.sindicato.util.validators.EmailValidation;


public class EmailTextWatcher extends TextWatcherFactory {

    @Override
    public TextWatcher getTextWatcher(EditText editText){
        EmailValidation emailValidation = new EmailValidation();

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
                    emailValidation.validateEditable(editable);
                } catch (Exception e) {
                    editText.setError(e.getLocalizedMessage());
                    e.printStackTrace();
                }
            }
        };
    }
}


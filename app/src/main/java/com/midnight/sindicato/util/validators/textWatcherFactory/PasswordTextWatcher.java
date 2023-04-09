package com.midnight.sindicato.util.validators.textWatcherFactory;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.midnight.sindicato.util.validators.EmptyFieldValidation;
import com.midnight.sindicato.util.validators.PasswordValidation;

public class PasswordTextWatcher extends TextWatcherFactory {

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

    public TextWatcher getTextPasswordWatcher(EditText password, EditText confirmPassword){
        EmptyFieldValidation emptyValidation = new EmptyFieldValidation();
        PasswordValidation passwordValidation = new PasswordValidation();

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
                    confirmPassword.setError(null);
                    emptyValidation.validateEditable(editable);

                    String passwordText = password.getText().toString();
                    String passwordConfirmText = confirmPassword.getText().toString();

                    passwordValidation.confirmPasswords(passwordText, passwordConfirmText);
                } catch (Exception e) {
                    confirmPassword.setError(e.getLocalizedMessage());
                    e.printStackTrace();
                }
            }
        };
    }
}


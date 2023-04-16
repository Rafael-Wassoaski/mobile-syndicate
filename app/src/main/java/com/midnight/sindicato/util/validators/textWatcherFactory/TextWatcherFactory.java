package com.midnight.sindicato.util.validators.textWatcherFactory;

import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;

import com.midnight.sindicato.interfaces.FormObservable;
import com.midnight.sindicato.interfaces.FormObserver;

public abstract class TextWatcherFactory implements FormObservable {
    protected FormObserver formObserver;

    public abstract TextWatcher getTextWatcher(EditText editText, Button formButton);

    @Override
    public void notifyTextChange() {
        this.formObserver.onTextFieldChange();
    }

    @Override
    public void setFormObserver(FormObserver formObserver) {
        this.formObserver = formObserver;
    }
}

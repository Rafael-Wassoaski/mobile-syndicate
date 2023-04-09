package com.midnight.sindicato.util.validators.textWatcherFactory;

import android.text.TextWatcher;
import android.widget.EditText;

public abstract class TextWatcherFactory {
    public abstract TextWatcher getTextWatcher(EditText editText);
}

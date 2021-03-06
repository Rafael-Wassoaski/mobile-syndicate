package com.midnight.sindicato;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

public class SignUpActivity extends AppCompatActivity {

    private EditText password;
    private EditText passwordConfirm;
    private Button signUpButton;

    private TextWatcher getPasswordWatcher() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                confirmPasswords();
            }

            @Override
            public void afterTextChanged(Editable editable) {
//                confirmPasswords();
            }
        };
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        password = findViewById(R.id.password_cadastro);
        passwordConfirm = findViewById(R.id.confirm_password_cadastro);
        signUpButton = findViewById(R.id.cadastroButton);

        signUpButton.setActivated(false);

        password.addTextChangedListener(getPasswordWatcher());
        passwordConfirm.addTextChangedListener(getPasswordWatcher());
    }

    private void confirmPasswords() {
        password.setError("aaaaaaa");
        Boolean a = password.getText().toString().equals(passwordConfirm.getText().toString());
        Log.d("teste", "teste " + a.toString());
        Log.d("teste", "password " + password.getText().toString());
        Log.d("teste", "passwordConfirm " + passwordConfirm.getText().toString());

        if (!a) {
            Log.d("teste", "entrou" );

            password.setError("Senhas não coincidem");
            signUpButton.setActivated(false);
            return;
        }
        Log.d("teste", "nao entrou" );

        signUpButton.setActivated(true);
    }
}
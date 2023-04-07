package com.midnight.sindicato.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.midnight.sindicato.R;
import com.midnight.sindicato.data.Result;
import com.midnight.sindicato.entity.User;
import com.midnight.sindicato.util.JsonUtils;
import com.midnight.sindicato.util.RequestMaker;
import com.midnight.sindicato.util.Utils;

import java.io.IOException;
import java.util.Optional;

import javax.net.ssl.HttpsURLConnection;

import okhttp3.Call;

public class SignUpActivity extends AppCompatActivity {

    private EditText password;
    private EditText passwordConfirm;
    private EditText emailText;
    private EditText cpfText;
    private EditText nameText;
    private Button signUpButton;
    private String baseUrl;

    private TextWatcher getPasswordWatcher() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                validatePasswords();
            }
        };
    }

    private TextWatcher getCpfWatcher() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                String cpf = editable.toString();
                isCPFValid(cpf);
            }
        };
    }

    private TextWatcher getEmailWatcher(){
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String email = editable.toString();
                isValidEmail(email);
            }
        };
    }

    private TextWatcher getNameWatcher(){
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String name = editable.toString();
                isNameValid(name);
            }
        };
    }

    private void isValidEmail(String target) {
        emailText.setError(null);

        if (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches()) {
            emailText.setError(getString(R.string.invalid_mail));
            signUpButton.setEnabled(false);
            return;
        }

        emailText.setEnabled(true);
    }


    private void isNameValid(String target) {
        nameText.setError(null);

        if (TextUtils.isEmpty(target)) {
            nameText.setError(getString(R.string.invalid_name));
            signUpButton.setEnabled(false);
            return;
        }

        nameText.setEnabled(true);
    }

    private void isCPFValid(String target) {
        cpfText.setError(null);

        if (TextUtils.isEmpty(target)) {
            cpfText.setError(getString(R.string.invalid_cpf));
            signUpButton.setEnabled(false);
            return;
        }

        cpfText.setEnabled(true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        password = findViewById(R.id.password_cadastro);
        passwordConfirm = findViewById(R.id.confirm_password_cadastro);
        signUpButton = findViewById(R.id.cadastroButton);
        cpfText = findViewById(R.id.cpf_cadastro);
        emailText = findViewById(R.id.email_cadastro);
        nameText = findViewById(R.id.complete_name);
        this.baseUrl = this.getString(R.string.base_url);

        signUpButton.setEnabled(false);

        passwordConfirm.addTextChangedListener(getPasswordWatcher());
        emailText.addTextChangedListener(getEmailWatcher());
        cpfText.addTextChangedListener(getCpfWatcher());
        nameText.addTextChangedListener(getNameWatcher());

        createUser();
    }

    private void createUser() {
        this.signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user = extractFormUserData();
                Thread signInThread = getSigninRequest(user);
                signInThread.start();
            }
        });
    }

    private void signin(String jsonUser) {
        JsonUtils<User> jsonUtils = new JsonUtils(User.class);
        User user = jsonUtils.jsonStringToEntity(jsonUser);
        finish();
    }

    private Thread getSigninRequest(User localUSer) {
        Context context = this;
        Runnable requestRunnable = new Runnable() {
            @Override
            public void run() {
                try {
                    RequestMaker<User> requestMaker = new RequestMaker<>();
                    Call call = requestMaker.post(localUSer, baseUrl + "api/v1/user/new");

                    String json = RequestMaker.makeRequest(call, HttpsURLConnection.HTTP_CREATED);
                    signin(json);
                } catch (IOException exception) {
                    Log.d("Create result", "Nao foi possivel criar o usuar: " + exception.getLocalizedMessage());
                    Utils.showToast(context, "Um erro inexperado ocorreu, tente novamente mais tarde");
                }
            }
        };

        return new Thread(requestRunnable);
    }

    private User extractFormUserData() {

        User user = new User.Builder()
                .cpf(cpfText.getText().toString())
                .email(emailText.getText().toString())
                .username(nameText.getText().toString())
                .password(passwordConfirm.getText().toString())
                .build();

        return user;
    }

    private void validatePasswords(){
        confirmPasswords();
        checkPasswordMinLength(password);
    }

    private void checkPasswordMinLength(EditText passwordField){
        passwordField.setError(null);

        if(passwordField.getText().toString().length() < 8){
            passwordField.setError(getString(R.string.min_password_length));
            signUpButton.setEnabled(false);
            return;
        }

        signUpButton.setEnabled(true);
    }

    private void confirmPasswords() {
        passwordConfirm.setError(null);

        if (!password.getText().toString().equals(passwordConfirm.getText().toString())) {
            passwordConfirm.setError(getString(R.string.differente_passwords));
            signUpButton.setEnabled(false);
            return;
        }

        signUpButton.setEnabled(true);
    }
}
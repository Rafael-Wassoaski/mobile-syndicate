package com.midnight.sindicato;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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
    private Button signUpButton;
    private String baseUrl;

    private TextWatcher getPasswordWatcher() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                confirmPasswords();
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
        this.baseUrl = this.getString(R.string.base_url);

        signUpButton.setEnabled(false);

        passwordConfirm.addTextChangedListener(getPasswordWatcher());

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
        EditText cpfText = findViewById(R.id.cpf_cadastro);
        EditText emailText = findViewById(R.id.email_cadastro);
        EditText nameText = findViewById(R.id.complete_name);

        User user = new User.Builder()
                .cpf(cpfText.toString())
                .email(emailText.toString())
                .username(nameText.toString())
                .password(passwordConfirm.toString())
                .build();

        return user;
    }

    private void confirmPasswords() {
        passwordConfirm.setError(null);

        if (!password.getText().toString().equals(passwordConfirm.getText().toString())) {
            passwordConfirm.setError("Senhas não coincidem");
            signUpButton.setEnabled(false);
            return;
        }

        signUpButton.setEnabled(true);
    }
}
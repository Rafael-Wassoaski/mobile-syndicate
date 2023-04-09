package com.midnight.sindicato.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.midnight.sindicato.R;
import com.midnight.sindicato.entity.User;
import com.midnight.sindicato.util.JsonUtils;
import com.midnight.sindicato.util.RequestMaker;
import com.midnight.sindicato.util.Utils;
import com.midnight.sindicato.util.validators.textWatcherFactory.EmailTextWatcher;
import com.midnight.sindicato.util.validators.textWatcherFactory.EmptyTextWatcher;
import com.midnight.sindicato.util.validators.textWatcherFactory.PasswordTextWatcher;
import com.midnight.sindicato.util.validators.textWatcherFactory.TextWatcherFactory;

import java.io.IOException;

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

        TextWatcherFactory emailTextWatcher = new EmailTextWatcher();
        PasswordTextWatcher passwordTextWatcher = new PasswordTextWatcher();
        TextWatcherFactory emptyTextWatcher = new EmptyTextWatcher();

        passwordConfirm.addTextChangedListener(passwordTextWatcher.getTextPasswordWatcher(password, passwordConfirm));
        emailText.addTextChangedListener(emailTextWatcher.getTextWatcher(emailText));
        cpfText.addTextChangedListener(emptyTextWatcher.getTextWatcher(cpfText));
        nameText.addTextChangedListener(emptyTextWatcher.getTextWatcher(nameText));

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
}
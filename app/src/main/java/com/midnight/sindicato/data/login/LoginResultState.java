package com.midnight.sindicato.data.login;

import com.midnight.sindicato.data.Result;
import com.midnight.sindicato.data.model.LoggedInUser;

import java.io.IOException;

public class LoginResultState {

    private Result result;

    public LoginResultState(){
        this.result = new Result.Error(new Exception("Login inicializado mas nao efetuado"));
    }

    public void setSuccessLogin(LoggedInUser loggedInUser){
        this.result = new  Result.Success<>(loggedInUser);
    }

    public void setFailedLogin(IOException exception){
        this.result = new Result.Error(new IOException("Error no login", exception));
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public Result getResult() {
        return result;
    }
}

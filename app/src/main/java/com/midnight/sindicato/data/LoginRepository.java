package com.midnight.sindicato.data;

import com.midnight.sindicato.data.model.LoggedInUser;
import com.midnight.sindicato.ui.login.LoginViewModel;

/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */
public class LoginRepository {

    private static volatile LoginRepository instance;

    private LoginDataSource dataSource;

    // If user credentials will be cached in local storage, it is recommended it be encrypted
    // @see https://developer.android.com/training/articles/keystore
    private LoggedInUser user = null;

    // private constructor : singleton access
    private LoginRepository(LoginDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static LoginRepository getInstance(LoginDataSource dataSource) {
        if (instance == null) {
            instance = new LoginRepository(dataSource);
        }
        return instance;
    }

    public boolean isLoggedIn() {
        return user != null;
    }

    public void logout() {
        user = null;
        dataSource.logout();
    }

    public void setLoggedUserData(Result<LoggedInUser> result ){
        if (result instanceof Result.Success) {
            this.user = ((Result.Success<LoggedInUser>) result).getData();
        }
    }

    public void login(String username, String password, LoginViewModel loginViewModel) {
        // handle login
        dataSource.login(username, password, loginViewModel);
    }
}
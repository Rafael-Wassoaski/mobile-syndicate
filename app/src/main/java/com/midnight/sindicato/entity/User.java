package com.midnight.sindicato.entity;

public class User {

    private String username;
    private String password;
    private String token;

    public User(String username, String password, String confirmPassword) throws Exception {
        this.username = username;

        if(!confirmPassword.equals(password)){
            throw new Exception("Campos de senha devem ser iguais");
        }
        this.password = password;
    }

    public User(String username, String token) throws Exception {
        this.username = username;
        this.token = token;
    }

    public User(User user, String token) throws Exception {
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getToken() {
        return token;
    }
}

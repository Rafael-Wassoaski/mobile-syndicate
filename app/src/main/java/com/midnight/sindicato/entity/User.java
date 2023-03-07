package com.midnight.sindicato.entity;

public class User {

    private String username;
    private String password;
    private String cpf;
    private String email;
    private String token;

    private User(String username, String password, String cpf, String email) {
        this.username = username;
        this.password = password;
        this.cpf = cpf;
        this.email = email;
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

    public void setToken(String token) {
        this.token = token;
    }

    public static class Builder{
        private String username;
        private String password;
        private String cpf;
        private String email;
        private String token;

        public Builder username(String username) {
            this.username = username;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;

        }

        public Builder cpf(String cpf) {
            this.cpf = cpf;
            return this;

        }

        public Builder email(String email) {
            this.email = email;
            return this;

        }

        public Builder token(String token) {
            this.token = token;
            return this;

        }

        public User build(){
            return new User(username, password, cpf, email);
        }
    }
}

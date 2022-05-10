package com.fitbee.patients.models.Jwt;

public class JwtRequest {

    private String email;
    private String password;

    public JwtRequest(){}

    public JwtRequest(String username,String password){
        this.email =username;
        this.password=password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

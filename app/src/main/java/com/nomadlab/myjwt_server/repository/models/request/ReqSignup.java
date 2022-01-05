package com.nomadlab.myjwt_server.repository.models.request;


public class ReqSignup {
    public String username;
    public String password;
    public String email;

    public ReqSignup(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }
}

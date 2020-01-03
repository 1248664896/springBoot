package com.springboot.musicworld.entity;



public class Admin {

    private String   id              ;
    private String   name            ;
    private String   email           ;
    private String   loginName       ;
    private String   password 		;
    private String   identity        ;
    private String   superAccount 	 ;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getSuperAccount() {
        return superAccount;
    }

    public void setSuperAccount(String superAccount) {
        this.superAccount = superAccount;
    }




}

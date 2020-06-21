package com.ms.demoapp.model;

public class MsUser {
    private String Name;
    private String Password;

    public MsUser() {
    }

    public MsUser(String name, String password) {
        Name = name;
        Password = password;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}

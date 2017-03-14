package com.getirhackathon.preselection.models;

public class RequestParameters {

    private String email, name, gsm;

    public RequestParameters() {
        this.email = "test@test.com";
        this.name = "test";
        this.gsm = "+900000000000";
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGsm() {
        return gsm;
    }

    public void setGsm(String gsm) {
        this.gsm = gsm;
    }
}

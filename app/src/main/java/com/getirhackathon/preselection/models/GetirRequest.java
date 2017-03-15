package com.getirhackathon.preselection.models;

import com.google.gson.annotations.SerializedName;

public class GetirRequest {
    @SerializedName("email")
    private String email;
    @SerializedName("name")
    private String name;
    @SerializedName("gsm")
    private String gsm;

    public GetirRequest() {
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

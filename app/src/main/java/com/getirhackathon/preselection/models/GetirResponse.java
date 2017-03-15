package com.getirhackathon.preselection.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by yutku on 15/03/17.
 */

public class GetirResponse {
    @SerializedName("code")
    int code;
    @SerializedName("msg")
    String msg;
    @SerializedName("elements")
    List<Element> elements;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<Element> getElements() {
        return elements;
    }

    public void setElements(List<Element> elements) {
        this.elements = elements;
    }

}

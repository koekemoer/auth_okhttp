package com.lou.auth_okhttp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by lou on 2017/03/24.
 */

public class CheckLogin {

    @SerializedName("success")
    @Expose
    private Boolean success = false;
    @SerializedName("reqPath")
    @Expose
    private String reqPath;
    @SerializedName("target")
    @Expose
    private String target;
    @SerializedName("api")
    @Expose
    private Boolean api;
    @SerializedName("detail")
    @Expose
    private String detail = null;
    @SerializedName("info")
    @Expose
    private String info;
    //@SerializedName("user")
    //@Expose
    //public String user;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("person")
    @Expose
    private String person;
    @SerializedName("stack")
    @Expose
    private String stack;
    @SerializedName("message")
    @Expose
    private String message;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getReqPath() {
        return reqPath;
    }

    public void setReqPath(String reqPath) {
        this.reqPath = reqPath;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public Boolean getApi() {
        return api;
    }

    public void setApi(Boolean api) {
        this.api = api;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public String getStack() {
        return stack;
    }

    public void setStack(String stack) {
        this.stack = stack;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

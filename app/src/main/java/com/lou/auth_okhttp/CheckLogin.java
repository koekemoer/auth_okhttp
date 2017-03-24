package com.lou.auth_okhttp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by lou on 2017/03/24.
 */

public class CheckLogin {
    @SerializedName("success")
    @Expose
    public Boolean success = false;

    // ------------------------------------------------------

    @SerializedName("reqPath")
    @Expose
    public String reqPath;
    @SerializedName("target")
    @Expose
    public String target;
    @SerializedName("api")
    @Expose
    public Boolean api;
    @SerializedName("detail")
    @Expose
    public String detail = null;
    @SerializedName("info")
    @Expose
    public String info;

    //-----------------------------------------

    //@SerializedName("user")
    //@Expose
    //public String user;
    @SerializedName("username")
    @Expose
    public String username;
    @SerializedName("person")
    @Expose
    public String person;
    @SerializedName("stack")
    @Expose
    public String stack;
    @SerializedName("message")
    @Expose
    public String message;
}

package com.lou.auth_okhttp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by lou on 2017/03/02.
 */

public class LoginInfo {

    @SerializedName("user")
    @Expose
    public User user;
    @SerializedName("hash")
    @Expose
    public String hash;
    @SerializedName("success")
    @Expose
    public Boolean success = false;
    @SerializedName("firstname")
    @Expose
    public String firstname;
    @SerializedName("surname")
    @Expose
    public String surname;

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
    public String detail;
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

}

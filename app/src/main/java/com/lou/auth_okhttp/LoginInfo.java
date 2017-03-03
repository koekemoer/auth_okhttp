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
    public Boolean success;
    @SerializedName("firstname")
    @Expose
    public String firstname;
    @SerializedName("surname")
    @Expose
    public String surname;

}

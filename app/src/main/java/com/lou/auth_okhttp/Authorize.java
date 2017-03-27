package com.lou.auth_okhttp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by lou on 2017/03/16.
 */

public class Authorize {
    @SerializedName("key")
    @Expose
    public String key;
    @SerializedName("ack")
    @Expose
    public boolean ack = false;
    @SerializedName("detail")
    @Expose
    public String detail;
}

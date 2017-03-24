package com.lou.auth_okhttp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by lou on 2017/03/24.
 */

public class Ack {
    @SerializedName("ack")
    @Expose
    public boolean ack = false;
}

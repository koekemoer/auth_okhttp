package com.lou.itsi_authenticator;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by lou on 2017/03/16.
 */

public class Authorize {

    @SerializedName("key")
    @Expose
    private String key;
    @SerializedName("ack")
    @Expose
    private boolean ack = false;
    @SerializedName("detail")
    @Expose
    private String detail;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public boolean isAck() {
        return ack;
    }

    public void setAck(boolean ack) {
        this.ack = ack;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}

package com.lou.auth_okhttp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by lou on 2017/04/05.
 */

public class School {
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("id")
    @Expose
    public String id;
    @SerializedName("guid")
    @Expose
    public String guid;
    @SerializedName("grades")
    @Expose
    public List<Object> grades = null;
    @SerializedName("languages")
    @Expose
    public List<Object> languages = null;
    @SerializedName("dnsadress")
    @Expose
    public String dnsadress;
    @SerializedName("dns")
    @Expose
    public String dns;

}

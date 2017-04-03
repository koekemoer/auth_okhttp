package com.lou.auth_okhttp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by lou on 2017/03/29.
 */

public class Groups {
    @SerializedName("name")
    @Expose
    public String name;
    /*@SerializedName("code")
    @Expose
    public String code;
    @SerializedName("year")
    @Expose
    public Integer year;
    @SerializedName("private")
    @Expose
    public Boolean _private;
    @SerializedName("owner")
    @Expose
    public String owner;
    */@SerializedName("subject")
    @Expose
    public String subject;/*
    @SerializedName("language")
    @Expose
    public String language;
    @SerializedName("grade")
    @Expose
    public List<String> grade = null;
    @SerializedName("custom")
    @Expose
    public String custom;
    @SerializedName("created")
    @Expose
    public Integer created;
    @SerializedName("updated")
    @Expose
    public Integer updated;*/
    @SerializedName("source")
    @Expose
    public String source;
}

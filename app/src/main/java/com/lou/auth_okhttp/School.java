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
    private String name;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("guid")
    @Expose
    private String guid;
    @SerializedName("grades")
    @Expose
    private List<Object> grades = null;
    @SerializedName("languages")
    @Expose
    private List<Object> languages = null;
    @SerializedName("dnsadress")
    @Expose
    private String dnsadress;
    @SerializedName("dns")
    @Expose
    private String dns;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public List<Object> getGrades() {
        return grades;
    }

    public void setGrades(List<Object> grades) {
        this.grades = grades;
    }

    public List<Object> getLanguages() {
        return languages;
    }

    public void setLanguages(List<Object> languages) {
        this.languages = languages;
    }

    public String getDnsadress() {
        return dnsadress;
    }

    public void setDnsadress(String dnsadress) {
        this.dnsadress = dnsadress;
    }

    public String getDns() {
        return dns;
    }

    public void setDns(String dns) {
        this.dns = dns;
    }
}

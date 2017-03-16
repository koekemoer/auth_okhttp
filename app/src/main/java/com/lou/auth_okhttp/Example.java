package com.lou.auth_okhttp;

import android.os.AsyncTask;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Example {

    /*@SerializedName("id")
    @Expose
    public String id;
    @SerializedName("_id")
    @Expose
    public String _id;
    @SerializedName("version")
    @Expose
    public Integer version;
    @SerializedName("pagesChecksum")
    @Expose
    public String pagesChecksum;
    @SerializedName("format")
    @Expose
    public String format;
    @SerializedName("metadata")
    @Expose
    public Metadata metadata;
    @SerializedName("title")
    @Expose
    public String title;
    @SerializedName("filename")
    @Expose
    public String filename;
    @SerializedName("size")
    @Expose
    public Integer size;
    @SerializedName("checksum")
    @Expose
    public String checksum;
    @SerializedName("publisher")
    @Expose
    public String publisher;*/

    @SerializedName("id")
    @Expose
    public String id;
    @SerializedName("_id")
    @Expose
    public String _id;
    @SerializedName("version")
    @Expose
    public Float version;
    @SerializedName("pagesChecksum")
    @Expose
    public String pagesChecksum;
    @SerializedName("format")
    @Expose
    public String format;
    @SerializedName("metadata")
    @Expose
    public Metadata metadata;
    @SerializedName("title")
    @Expose
    public String title;
    @SerializedName("filename")
    @Expose
    public String filename;
    @SerializedName("size")
    @Expose
    public Integer size;
    @SerializedName("checksum")
    @Expose
    public String checksum;
    @SerializedName("publisher")
    @Expose
    public String publisher;
    @SerializedName("mathZipChecksum")
    @Expose
    public String mathZipChecksum;

    @SerializedName("key")
    @Expose
    public String key = null;

}
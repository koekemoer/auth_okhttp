package com.lou.auth_okhttp;

import android.os.AsyncTask;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Example {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("_id")
    @Expose
    private String _id;
    @SerializedName("version")
    @Expose
    private Float version;
    @SerializedName("pagesChecksum")
    @Expose
    private String pagesChecksum;
    @SerializedName("format")
    @Expose
    private String format;
    @SerializedName("metadata")
    @Expose
    private Metadata metadata;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("filename")
    @Expose
    private String filename;
    @SerializedName("size")
    @Expose
    private Integer size;
    @SerializedName("checksum")
    @Expose
    private String checksum;
    @SerializedName("publisher")
    @Expose
    private String publisher;
    @SerializedName("mathZipChecksum")
    @Expose
    private String mathZipChecksum;
    @SerializedName("key")
    @Expose
    private String key = null;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public Float getVersion() {
        return version;
    }

    public void setVersion(Float version) {
        this.version = version;
    }

    public String getPagesChecksum() {
        return pagesChecksum;
    }

    public void setPagesChecksum(String pagesChecksum) {
        this.pagesChecksum = pagesChecksum;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public Metadata getMetadata() {
        return metadata;
    }

    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public String getChecksum() {
        return checksum;
    }

    public void setChecksum(String checksum) {
        this.checksum = checksum;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getMathZipChecksum() {
        return mathZipChecksum;
    }

    public void setMathZipChecksum(String mathZipChecksum) {
        this.mathZipChecksum = mathZipChecksum;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
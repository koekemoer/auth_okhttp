package com.lou.auth_okhttp;

/**
 * Created by lou on 2017/03/07.
 */
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Metadata {
    /*@SerializedName("isbn")
    @Expose
    public String isbn;
    @SerializedName("originalTitle")
    @Expose
    public String originalTitle;
    @SerializedName("title")
    @Expose
    public String title;
    @SerializedName("publisher")
    @Expose
    public String publisher;
    @SerializedName("language")
    @Expose
    public String language;
    @SerializedName("subject")
    @Expose
    public String subject;
    @SerializedName("grade")
    @Expose
    public List<String> grade = null;
    @SerializedName("edition")
    @Expose
    public String edition;
    @SerializedName("encryptor")
    @Expose
    public String encryptor;
    @SerializedName("publicationDate")
    @Expose
    public Integer publicationDate;
    @SerializedName("year")
    @Expose
    public Integer year;
    @SerializedName("bookID")
    @Expose
    public String bookID;
    @SerializedName("version")
    @Expose
    public Integer version;
    @SerializedName("encryptionDate")
    @Expose
    public Integer encryptionDate;
    @SerializedName("format")
    @Expose
    public Integer format;*/

    @SerializedName("format")
    @Expose
    public Integer format;
    @SerializedName("language")
    @Expose
    public String language;
    @SerializedName("grade")
    @Expose
    public List<String> grade = null;
    @SerializedName("originalTitle")
    @Expose
    public String originalTitle;
    @SerializedName("title")
    @Expose
    public String title;
    @SerializedName("subject")
    @Expose
    public String subject;
    @SerializedName("encryptor")
    @Expose
    public String encryptor;
    @SerializedName("isbn")
    @Expose
    public String isbn;
    @SerializedName("publicationDate")
    @Expose
    public Integer publicationDate;
    @SerializedName("publisher")
    @Expose
    public String publisher;
    @SerializedName("year")
    @Expose
    public Integer year;
    @SerializedName("bookID")
    @Expose
    public String bookID;
    @SerializedName("version")
    @Expose
    public Float version;
    @SerializedName("encryptionDate")
    @Expose
    public Integer encryptionDate;
    @SerializedName("edition")
    @Expose
    public String edition;
    @SerializedName("regions")
    @Expose
    public List<String> regions = null;
    @SerializedName("curriculum")
    @Expose
    public List<Object> curriculum = null;
    @SerializedName("markets")
    @Expose
    public List<String> markets = null;
}

package com.lou.auth_okhttp;

/**
 * Created by lou on 2017/03/07.
 */
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Metadata {

    @SerializedName("format")
    @Expose
    private Integer format;
    @SerializedName("language")
    @Expose
    private String language;
    @SerializedName("grade")
    @Expose
    private List<String> grade = null;
    @SerializedName("originalTitle")
    @Expose
    private String originalTitle;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("subject")
    @Expose
    private String subject;
    @SerializedName("encryptor")
    @Expose
    private String encryptor;
    @SerializedName("isbn")
    @Expose
    private String isbn;
    @SerializedName("publicationDate")
    @Expose
    private Integer publicationDate;
    @SerializedName("publisher")
    @Expose
    private String publisher;
    @SerializedName("year")
    @Expose
    private Integer year;
    @SerializedName("bookID")
    @Expose
    private String bookID;
    @SerializedName("version")
    @Expose
    private Float version;
    @SerializedName("encryptionDate")
    @Expose
    private Integer encryptionDate;
    @SerializedName("edition")
    @Expose
    private String edition;
    @SerializedName("regions")
    @Expose
    private List<String> regions = null;
    @SerializedName("curriculum")
    @Expose
    private List<Object> curriculum = null;
    @SerializedName("markets")
    @Expose
    private List<String> markets = null;

    public Integer getFormat() {
        return format;
    }

    public void setFormat(Integer format) {
        this.format = format;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public List<String> getGrade() {
        return grade;
    }

    public void setGrade(List<String> grade) {
        this.grade = grade;
    }

    public void setGrade(int grade) {
        Integer n = new Integer(grade);
        String tmp = n.toString();
        Log.wtf("CHECK DIE SHIT!!!!!!!", tmp);
        //tmp = grade.toString();
        List<String> gd = new ArrayList<String>();
        gd.add(tmp);
        this.grade = gd;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getEncryptor() {
        return encryptor;
    }

    public void setEncryptor(String encryptor) {
        this.encryptor = encryptor;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Integer getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Integer publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getBookID() {
        return bookID;
    }

    public void setBookID(String bookID) {
        this.bookID = bookID;
    }

    public Float getVersion() {
        return version;
    }

    public void setVersion(Float version) {
        this.version = version;
    }

    public Integer getEncryptionDate() {
        return encryptionDate;
    }

    public void setEncryptionDate(Integer encryptionDate) {
        this.encryptionDate = encryptionDate;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public List<String> getRegions() {
        return regions;
    }

    public void setRegions(List<String> regions) {
        this.regions = regions;
    }

    public List<Object> getCurriculum() {
        return curriculum;
    }

    public void setCurriculum(List<Object> curriculum) {
        this.curriculum = curriculum;
    }

    public List<String> getMarkets() {
        return markets;
    }

    public void setMarkets(List<String> markets) {
        this.markets = markets;
    }
}

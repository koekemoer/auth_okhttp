package com.lou.auth_okhttp;

/**
 * Created by lou on 2017/03/07.
 */
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Metadata {
    @SerializedName("isbn")
    @Expose
    private String isbn;
    @SerializedName("originalTitle")
    @Expose
    private String originalTitle;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("publisher")
    @Expose
    private String publisher;
    @SerializedName("language")
    @Expose
    private String language;
    @SerializedName("subject")
    @Expose
    private String subject;
    @SerializedName("grade")
    @Expose
    private List<String> grade = null;
    @SerializedName("edition")
    @Expose
    private String edition;
    @SerializedName("encryptor")
    @Expose
    private String encryptor;
    @SerializedName("publicationDate")
    @Expose
    private Integer publicationDate;
    @SerializedName("year")
    @Expose
    private Integer year;
    @SerializedName("bookID")
    @Expose
    private String bookID;
    @SerializedName("version")
    @Expose
    private Integer version;
    @SerializedName("encryptionDate")
    @Expose
    private Integer encryptionDate;
    @SerializedName("format")
    @Expose
    private Integer format;

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
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

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public List<String> getGrade() {
        return grade;
    }

    public void setGrade(List<String> grade) {
        this.grade = grade;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public String getEncryptor() {
        return encryptor;
    }

    public void setEncryptor(String encryptor) {
        this.encryptor = encryptor;
    }

    public Integer getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Integer publicationDate) {
        this.publicationDate = publicationDate;
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

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Integer getEncryptionDate() {
        return encryptionDate;
    }

    public void setEncryptionDate(Integer encryptionDate) {
        this.encryptionDate = encryptionDate;
    }

    public Integer getFormat() {
        return format;
    }

    public void setFormat(Integer format) {
        this.format = format;
    }
}

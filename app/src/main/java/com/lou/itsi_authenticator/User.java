package com.lou.itsi_authenticator;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by lou on 2017/03/02.
 */

public class User {

    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("_rev")
    @Expose
    private String rev;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("dataSource")
    @Expose
    private String dataSource;
    @SerializedName("created")
    @Expose
    private Integer created;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("firstname")
    @Expose
    private String firstname;
    @SerializedName("surname")
    @Expose
    private String surname;
    @SerializedName("enabled")
    @Expose
    private Boolean enabled;
    @SerializedName("roles")
    @Expose
    private List<String> roles = null;
    @SerializedName("grants")
    @Expose
    private Grants grants;
    @SerializedName("classes")
    @Expose
    private List<String> classes = null;
    @SerializedName("grade")
    @Expose
    public String grade;
    @SerializedName("hermesClasses")
    @Expose
    private List<String> hermesClasses = null;
    @SerializedName("updated")
    @Expose
    private Integer updated;
    @SerializedName("updatedBy")
    @Expose
    private String updatedBy;
    @SerializedName("groupsUpdated")
    @Expose
    private Integer groupsUpdated;
    @SerializedName("email")
    @Expose
    private String email;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRev() {
        return rev;
    }

    public void setRev(String rev) {
        this.rev = rev;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDataSource() {
        return dataSource;
    }

    public void setDataSource(String dataSource) {
        this.dataSource = dataSource;
    }

    public Integer getCreated() {
        return created;
    }

    public void setCreated(Integer created) {
        this.created = created;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public Grants getGrants() {
        return grants;
    }

    public void setGrants(Grants grants) {
        this.grants = grants;
    }

    public List<String> getClasses() {
        return classes;
    }

    public void setClasses(List<String> classes) {
        this.classes = classes;
    }

    public List<String> getHermesClasses() {
        return hermesClasses;
    }

    public void setHermesClasses(List<String> hermesClasses) {
        this.hermesClasses = hermesClasses;
    }

    public Integer getUpdated() {
        return updated;
    }

    public void setUpdated(Integer updated) {
        this.updated = updated;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Integer getGroupsUpdated() {
        return groupsUpdated;
    }

    public void setGroupsUpdated(Integer groupsUpdated) {
        this.groupsUpdated = groupsUpdated;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
}

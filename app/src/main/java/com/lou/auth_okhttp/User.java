package com.lou.auth_okhttp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by lou on 2017/03/02.
 */

public class User {

    @SerializedName("_id")
    @Expose
    public String id;
    @SerializedName("_rev")
    @Expose
    public String rev;
    @SerializedName("type")
    @Expose
    public String type;
    @SerializedName("dataSource")
    @Expose
    public String dataSource;
    @SerializedName("created")
    @Expose
    public Integer created;
    @SerializedName("username")
    @Expose
    public String username;
    @SerializedName("firstname")
    @Expose
    public String firstname;
    @SerializedName("surname")
    @Expose
    public String surname;
    @SerializedName("enabled")
    @Expose
    public Boolean enabled;
    @SerializedName("email")
    @Expose
    public String email;
    @SerializedName("roles")
    @Expose
    public List<String> roles = null;
    @SerializedName("grants")
    @Expose
    public Grants grants;
    @SerializedName("classes")
    @Expose
    public List<String> classes = null;
    @SerializedName("grade")
    @Expose
    public Integer grade;
    @SerializedName("updated")
    @Expose
    public Integer updated;
    @SerializedName("updatedBy")
    @Expose
    public String updatedBy;
    @SerializedName("groupsUpdated")
    @Expose
    public Integer groupsUpdated;
}

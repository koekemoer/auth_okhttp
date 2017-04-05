package com.lou.auth_okhttp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by lou on 2017/04/04.
 */

public class Schools {
    @SerializedName("schools")
    @Expose
    public List<School> schools = null;
}

package com.lou.auth_okhttp;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.RequestBody;

/**
 * Created by lou on 2017/02/21.
 */

public class RequestBuilder {

    public static RequestBody LoginBody(String username, String password/*, String token*/) {
        assert(username != null);
        assert(password != null);


        return new FormBody.Builder()
                //.add("action", "login")
                //.add("format", "json")
                .add("username", username)
                .add("password", password)
                .add("noHash", "0")
                //.add("logintoken", token)
                .build();
    }

    public static HttpUrl buildUrl() {
        return new HttpUrl.Builder()
                .scheme("https")
                .host("app.dev.it.si")
                .addPathSegment("alchemy")
                .addPathSegment("api")
                .addPathSegment("1.0")
                .addPathSegment("login")
                .build();
    }
}

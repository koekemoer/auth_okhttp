package com.lou.auth_okhttp;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.RequestBody;

import static android.R.attr.key;

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

    public static HttpUrl buildUrl(final String username) {
        return new HttpUrl.Builder()
                .scheme("https")
                .host("app.dev.it.si")
                .addPathSegment("alchemy")
                .addPathSegment("api")
                .addPathSegment("1.0")
                //.addPathSegment("login")
                .addPathSegment("users")
                .addPathSegment(username)
                .addPathSegment("books")
                .build();
    }

    //GET(:server:/unity/api/1.0/epubs/:bookid:/key?device=auth_test
    public static HttpUrl buildAuthUrl(final String id) {
        return new HttpUrl.Builder()
                .scheme("https")
                .host("app.dev.it.si")
                .addPathSegment("alchemy")
                .addPathSegment("api")
                .addPathSegment("1.0")
                //.addPathSegment("login")
                .addPathSegment("epubs")
                .addPathSegment(id)
                .addPathSegment("key")
                .addQueryParameter("device", "auth_test")
                .build();
    }

}

package com.lou.auth_okhttp;

import java.io.IOException;

import android.net.Credentials;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.Route;
import okhttp3.Authenticator;
import okhttp3.Request;

import static okhttp3.Credentials.basic;
//import okhttp3.Credentials;

public class MainActivity extends AppCompatActivity {

    private OkHttpClient client;
    private String response;

    String url = "https://app.dev.it.si/alchemy/api/1.0/login";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText etUname = (EditText) findViewById(R.id.et_uname);
        final EditText etPassw = (EditText) findViewById(R.id.et_passw);
        final Button bLogin = (Button) findViewById(R.id.btn_login);

        client = new OkHttpClient();

        //attemptLogin(url);

        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attemptLogin(url);
            }
        });

    }

    private void loadContent() {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                try {
                    response = ApiCall.GET(client, RequestBuilder.buildUrl());
                    Log.d("Response", response);
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }
        }.execute();
    }

    private void attemptLogin(String url) {
        new AsyncTask<String, Void, Void>() {
            protected Void doInBackground(String... params) {
                try {
                    response = ApiCall.POST(
                            client,
                            params[0],
                            RequestBuilder.LoginBody("harvey", "p"/*, "token"*/));

                    Log.d("Response", response);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }
        }.execute(url);
    }


}
package com.lou.auth_okhttp;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import okhttp3.OkHttpClient;

public class UserAreaAct extends AppCompatActivity {

    //private static String user_url = "https://app.dev.it.si/alchemy/api/users/harvey/books";
    private static String response;

    //private OkHttpClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_area);

        Log.d("UserArea:", "UserArea Entered");

        final TextView txt_welcome = (TextView) findViewById(R.id.textView_welcome);
        final TextView txt_test = (TextView) findViewById(R.id.textView_test);

        MainActivity mainActivity = null;
        try {
            mainActivity = new MainActivity();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        //Gson gson = new Gson();
        //LoginInfo obj = gson.fromJson(mainActivity.getResponse(), LoginInfo.class);
        LoginInfo obj = mainActivity.getObj1();

        //client = mainActivity.client;

        //txt_welcome.setText("Welcome " + mainActivity.finalUser);
        txt_welcome.setText("Welcome Mr. " + obj.surname);

        Log.d("UserArea:Username", mainActivity.finalUser);

        //mainActivity.loadContent(mainActivity.finalUser);

        loadContent(mainActivity.getClient(), mainActivity.finalUser);

        txt_test.setText(response);

        /*try {
            response = ApiCall.GET(mainActivity.getClient(), RequestBuilder.buildUrl(mainActivity.finalUser));
            Log.d("GET_Request", response);
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }

    public void loadContent(final OkHttpClient client, final String username) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                try {
                    response = ApiCall.GET(client, RequestBuilder.buildUrl(username) /*user_url*/);
                    Log.d("Response:LoadContent", response);
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }
        }.execute();
    }
}

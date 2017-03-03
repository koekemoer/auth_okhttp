package com.lou.auth_okhttp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class UserAreaAct extends AppCompatActivity {

    //private static String user_url = "https://app.dev.it.si/alchemy/api/users/harvey/books";
    private String response;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_area);

        Log.d("UserArea:", "UserArea Entered");

        final TextView txt_welcome = (TextView) findViewById(R.id.textView_welcome);

        MainActivity mainActivity = null;
        try {
            mainActivity = new MainActivity();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        txt_welcome.setText("Welcome " + mainActivity.finalUser);

        Log.d("UserArea:Username", mainActivity.finalUser);

        //mainActivity.loadContent(mainActivity.finalUser);

        /*try {
            response = ApiCall.GET(mainActivity.getClient(), RequestBuilder.buildUrl(mainActivity.finalUser));
            Log.d("GET_Request", response);
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }
}

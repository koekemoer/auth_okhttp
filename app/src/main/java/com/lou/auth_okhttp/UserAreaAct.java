package com.lou.auth_okhttp;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import okhttp3.OkHttpClient;

public class UserAreaAct extends AppCompatActivity {

    private static String user_url = "https://app.dev.it.si/alchemy/api/users/harvey/books";
    private static String response;

    //public String test = "Wiele";

    //final TextView txt_test = (TextView) findViewById(R.id.textView_test);

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

        LoginInfo obj = mainActivity.getObj1();
        txt_welcome.setText("Welcome Mr. " + obj.surname);
        //Log.d("UserArea:Username", mainActivity.finalUser);

        loadContent(mainActivity.getClient(), obj.user.username.toString());

        //txt_test.setText(response);
        //Toast.makeText(UserAreaAct.this, "After LoadContent", Toast.LENGTH_LONG).show();

    }

    public void loadContent(final OkHttpClient client, final String username) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                //Toast.makeText()
                try {
                    response = ApiCall.GET(client, RequestBuilder.buildUrl(username) /*user_url*/);
                    Log.d("USER_AREA:LoadContent", response);
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }
        }.execute();
    }

    /*public void sendMail() {
        Log.i("Send email", "");
    }*/
}

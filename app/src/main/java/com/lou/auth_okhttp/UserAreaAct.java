package com.lou.auth_okhttp;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import okhttp3.OkHttpClient;

public class UserAreaAct extends AppCompatActivity {

    private static String user_url = "https://app.dev.it.si/alchemy/api/users/harvey/books";
    private static String response;
    private static Example[] objMeta;
    private static Map<String, Object> objMap;
    private static List<Example> objList = new ArrayList<>();

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
        Log.d("UserArea:Username", mainActivity.finalUser);

        loadContent(mainActivity.getClient(), obj.user.username);

        /*Gson gson = new Gson();
        objMeta = gson.fromJson(response, Example[].class);

        for (int i = 0; i < objMeta.length; i++) {
            Log.d("###########BOOK_ID", objMeta[i].metadata.bookID);
            if (objMeta[i].metadata.title == null || objMeta[i].metadata.title == "") {
                Log.d("#########BOOK_TITLE", "NULL OU BUL");
            }
            Log.d("###########BOOK_TITLE", objMeta[i].metadata.title);
        }*/

        Log.i("####################", "AFTER GSON");
        //Log.i("USER_AREA:BookID", objList.get(0).metadata.bookID);
        //Log.i("USER_AREA:Book_Title", objList.get(0).metadata.title);

    }

    public void loadContent(final OkHttpClient client, final String username) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                //Toast.makeText()
                try {
                    response = ApiCall.GET(client, RequestBuilder.buildUrl(username) /*user_url*/);
                    Log.d("USER_AREA:LoadContent", response);

                    Gson gson = new Gson();
                    objMeta = gson.fromJson(response, Example[].class);
                    //objMap = gson.fromJson(response, new TypeToken<Map<String, Object>>(){}.getType());
                    //objList = gson.fromJson(response, new TypeToken<List<Example>>(){}.getType());
                    for (int i = 0; i < objMeta.length; i++) {
                        Log.d("###########BOOK_ID", objMeta[i].metadata.bookID);
                        if (objMeta[i].metadata.title == null || objMeta[i].metadata.title == "") {
                            Log.d("#########BOOK_TITLE", "NULL OU BUL");
                        }
                        Log.d("###########BOOK_TITLE", objMeta[i].metadata.title);
                    }
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

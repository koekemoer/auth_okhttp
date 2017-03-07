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

        //Gson gson = new Gson();
        //LoginInfo obj = gson.fromJson(mainActivity.getResponse(), LoginInfo.class);
        LoginInfo obj = mainActivity.getObj1();

        //client = mainActivity.client;

        //txt_welcome.setText("Welcome " + mainActivity.finalUser);
        txt_welcome.setText("Welcome Mr. " + obj.surname);

        Log.d("UserArea:Username", mainActivity.finalUser);

        //mainActivity.loadContent(obj.user.username);

        loadContent(mainActivity.getClient()/*, obj.user.username.toString()*/);

        txt_test.setText(mainActivity.finalUser);
        Toast.makeText(UserAreaAct.this, "After LoadContent", Toast.LENGTH_LONG).show();
        /*if (response == null) {
            txt_test.setText("Wiele met 'n P");
        }*/

        /*try {
            response = ApiCall.GET(mainActivity.getClient(), RequestBuilder.buildUrl(mainActivity.finalUser));
            Log.d("GET_Request", response);
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }

    public void loadContent(final OkHttpClient client/*, final String username*/) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                //Toast.makeText()
                try {
                    response = ApiCall.GET(client, /*RequestBuilder.buildUrl(username)*/ user_url);
                    Log.d("Response:LoadContent", response);
                    //Toast.makeText(UserAreaAct.this, "UserArea LoadContent", Toast.LENGTH_LONG).show();
                    //test = "Kosie";
                }
                catch (IOException e) {
                    e.printStackTrace();
                    //Toast.makeText(UserAreaAct.this, "CATCH", Toast.LENGTH_LONG).show();
                    //test = "Katools";
                }
                //test = "Buksie";
                return null;
            }
        }.execute();
    }
}

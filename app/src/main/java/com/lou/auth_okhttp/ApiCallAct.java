package com.lou.auth_okhttp;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;

import okhttp3.OkHttpClient;

public class ApiCallAct extends AppCompatActivity {

    private MainActivity mainAct;

    private static String response;

    private final String serverTime = "unity/time";
    private final String iOS = "unity/api/1.0/ios/version";
    private final String androidDns = "unity/api/1.0/android/version";
    private final String windows = "unity/api/1.0/windows/version";
    //private final String bookResc = "unity/api/1.0/epubs/bulk/content/since/0?noDeletes=1&id={x}&id={y}";
    //private final String studentStats = "answers/api/student/{username}";
    //private final String userContent = "unity/api/1.0/epubs/bulk/userContent/since/0?noDeletes=1&id={x}&id={y}";
    private final String events = "unity/api/1.0/calendar/since/0";
    //private String

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_api_call);

        mainAct = null;
        try {
            mainAct = new MainActivity();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        final TextView txt_api = (TextView) findViewById(R.id.txt_api);
        AutoCompleteTextView autoApi = (AutoCompleteTextView) findViewById(R.id.autotxt_api);
        txt_api.setText("API Calls");

        final String dns = mainAct.getDns();
        final OkHttpClient client = mainAct.getClient();

        ArrayList<HashMap<String, String>> calls = new ArrayList<HashMap<String, String>>();
        calls.add(putData("Server Time", serverTime));
        calls.add(putData("iOS Version", iOS));
        calls.add(putData("Android Version", androidDns));
        calls.add(putData("Windows Version", windows));
        calls.add(putData("Upcoming Events", events));

        autoComplete(calls);


    }
    private void autoComplete(ArrayList calls) {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(ApiCallAct.this, android.R.layout.simple_dropdown_item_1line, calls);
        AutoCompleteTextView autoTxt = (AutoCompleteTextView) findViewById(R.id.autotxt_api);
        autoTxt.setThreshold(1);
        autoTxt.setAdapter(adapter);
    }


    public void apicallFunc (final String dns, final OkHttpClient client) {
        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... params) {
                Log.d("###WIELE###", "!@#$%^&*()(*&^%$#@#$%^&**&^%$#@#$%^*%*&%*%^&^%&^%&%&^%&%&^%");
                try {
                    response = ApiCall.GET(client, dns);
                    //Log.d("Response:LoadContent", response);

                }
                catch (IOException e) {
                    e.printStackTrace();
                    Log.e("ERROR TERROR", e.toString());
                }
                return response;
            }

            @Override
            protected void onPostExecute(String resp) {
                super.onPostExecute(resp);

            }
        }.execute();

    }

    private HashMap<String, String> putData(String name, String dns) {
        HashMap<String, String> item = new HashMap<String, String>();
        item.put(name, dns);
        return item;
    }

}

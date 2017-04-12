package com.lou.auth_okhttp;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import okhttp3.OkHttpClient;

public class ApiCallAct extends AppCompatActivity {

    private MainActivity mainAct;

    private static String response;
    private String dns;

    String[] apiCalls = {"Server Time", "iOS Version", "Android Version", "Windows Version",
                        "Upcoming Events", "Student Stats", "Book Resources", "User Content"};
    private String callStr = null;
    private String finalStr = null;
    HashMap calls = new HashMap();
    AutoCompleteTextView autoApi;
    final Context context = this;
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

        UserAreaAct userArea = new UserAreaAct();

        final TextView txt_api = (TextView) findViewById(R.id.txt_api);
        final Button btn_call = (Button) findViewById(R.id.btn_call);
        /*AutoCompleteTextView */autoApi = (AutoCompleteTextView) findViewById(R.id.autotxt_api);
        txt_api.setText("API Calls");

        /*final String*/ dns = mainAct.getDns();
        final OkHttpClient client = mainAct.getClient();
        final LoginInfo objLogin = mainAct.getObj1();
        final Example[] objBooks = userArea.getObjBooks();

        ArrayList<String> apiList = new ArrayList(Arrays.asList(apiCalls));

        //HashMap calls = new HashMap();
        String serverTime = "/unity/time";
        calls.put("Server Time", serverTime);
        String iOS = "/unity/api/1.0/ios/version";
        calls.put("iOS Version", iOS);
        String androidDns = "/unity/api/1.0/apk/version";
        calls.put("Android Version", androidDns);
        String windows = "/unity/api/1.0/windows/version";
        calls.put("Windows Version", windows);
        String events = "/unity/api/1.0/calendar/since/0";
        calls.put("Upcoming Events", events);
        String studentStats = "/answers/api/student/" + objLogin.getUser().getUsername();
        calls.put("Student Stats", studentStats);
        String bookResc = "/unity/api/1.0/epubs/bulk/content/since/0?noDeletes=1";
        for (int i = 0; i < objBooks.length; i++) {
            bookResc = bookResc + "&id=" + objBooks[i].getId();
        }
        calls.put("Book Resources", bookResc);
        String userContent = "/unity/api/1.0/epubs/bulk/userContent/since/0?noDeletes=1";
        for (int i = 0; i < objBooks.length; i++) {
            bookResc = bookResc + "&id=" + objBooks[i].getId();
        }
        calls.put("User Content", userContent);
        /*Set set = calls.entrySet();

        Iterator i = set.iterator();*/

        autoComplete(apiList);


        btn_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*if (autoApi.getText().toString() == null) {
                    showAlert("Not a valid API Call");
                }*/
                String tmpCall = autoApi.getText().toString();
                String callStr = (String) calls.get(tmpCall);
                finalStr = "https://" + dns + callStr;

                apiCall(finalStr, client);
            }
        });



    }
    private void autoComplete(ArrayList calls) {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(ApiCallAct.this, android.R.layout.simple_dropdown_item_1line, calls);
        AutoCompleteTextView autoTxt = (AutoCompleteTextView) findViewById(R.id.autotxt_api);
        autoTxt.setThreshold(1);
        autoTxt.setAdapter(adapter);
    }


    public void apiCall (final String dns, final OkHttpClient client) {
        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... params) {
                //Log.d("###WIELE###", "!@#$%^&*()(*&^%$#@#$%^&**&^%$#@#$%^*%*&%*%^&^%&^%&%&^%&%&^%");
                try {
                    response = ApiCall.GET(client, dns);
                    Log.d("Response:LoadContent", response);

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
                showAlert(resp);

            }
        }.execute();

    }

    private HashMap<String, String> putData(String name, String dns) {
        HashMap<String, String> item = new HashMap<String, String>();
        item.put(name, dns);
        return item;
    }

    public void showAlert(String msg) {
        AlertDialog.Builder alert = new AlertDialog.Builder(context);
        alert.setTitle("Result").setCancelable(true);
        //Log.wtf("CHECK CHECK", "CHECK_AUTH");
        alert.setMessage(msg);
        alert.create();
        alert.show();
    }

}

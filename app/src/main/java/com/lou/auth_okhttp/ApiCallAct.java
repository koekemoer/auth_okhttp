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
import android.widget.Spinner;
import android.widget.TextView;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import android.app.Activity;
import com.chilkatsoft.*;


import org.json.JSONException;

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
    Spinner spinner;
    final Context context = this;
    private static final String TAG = "Chilkat";

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

        Log.d("WTF WTF WTF WTF", "WHY U NO WORK?");

        final TextView txt_api = (TextView) findViewById(R.id.txt_api);
        final Button btn_call = (Button) findViewById(R.id.btn_call);
        ///*AutoCompleteTextView */autoApi = (AutoCompleteTextView) findViewById(R.id.autotxt_api);
        /*Spinner*/ spinner = (Spinner) findViewById(R.id.spinner);
        txt_api.setText("API Calls");
        //ArrayAdapter<String> adapter = ArrayAdapter.createFromResource(this, null, )

        /*final String*/ dns = mainAct.getDns();
        final OkHttpClient client = mainAct.getClient();
        final LoginInfo objLogin = mainAct.getObj1();
        final Example[] objBooks = userArea.getObjBooks();
        String http = "https://";

        ArrayList<String> apiList = new ArrayList(Arrays.asList(apiCalls));

        //HashMap calls = new HashMap();
        String serverTime = http + dns + "/unity/time";
        calls.put("Server Time", serverTime);
        String iOS = http + dns + "/unity/api/1.0/ios/version";
        calls.put("iOS Version", iOS);
        String androidDns = http + dns + "/unity/api/1.0/apk/version";
        calls.put("Android Version", androidDns);
        String windows = http + dns + "/unity/api/1.0/windows/version";
        calls.put("Windows Version", windows);
        String events = http + dns + "/unity/api/1.0/calendar/since/0";
        calls.put("Upcoming Events", events);
        String studentStats = http + dns + "/answers/api/student/" + objLogin.getUser().getUsername();
        calls.put("Student Stats", studentStats);
        String bookResc = http + dns + "/unity/api/1.0/epubs/bulk/content/since/0?noDeletes=1";
        for (int i = 0; i < objBooks.length; i++) {
            bookResc = bookResc + "&id=" + objBooks[i].getId();
        }
        calls.put("Book Resources", bookResc);
        String userContent = http + dns + "/unity/api/1.0/epubs/bulk/userContent/since/0?noDeletes=1";
        for (int i = 0; i < objBooks.length; i++) {
            userContent = userContent + "&id=" + objBooks[i].getId();
        }
        calls.put("User Content", userContent);

        autoComplete(apiList);

        //apiCall(finalStr, client);


        btn_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*if (autoApi.getText().toString() == null) {
                    showAlert("Not a valid API Call");
                }*/
                String tmpCall = String.valueOf(spinner.getSelectedItem());
                /*String callStr*/ finalStr = (String) calls.get(tmpCall);
                //finalStr = "https://" + dns + callStr;

                apiCall(finalStr, client);
                //showAlert(response);
            }
        });



    }
    private void autoComplete(ArrayList calls) {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(ApiCallAct.this, android.R.layout.select_dialog_item, calls);
        //ArrayAdapter<String> adapter = ArrayAdapter.createFromResource(ApiCallAct.this, R.array.calls, R.layout.api_call_spinner)
        //AutoCompleteTextView autoTxt = (AutoCompleteTextView) findViewById(R.id.autotxt_api);
        spinner = (Spinner) findViewById(R.id.spinner);
        //spinner.setThreshold(1);
        spinner.setAdapter(adapter);
    }


    public void apiCall (final String str, final OkHttpClient client) {
        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... params) {
                //Log.d("###WIELE###", "!@#$%^&*()(*&^%$#@#$%^&**&^%$#@#$%^*%*&%*%^&^%&^%&%&^%&%&^%");
                try {
                    response = ApiCall.GET(client, str);

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

                /*CkJsonObject json = new CkJsonObject();
                boolean success = json.Load(resp);
                json.put_EmitCompact(false);*/

                CkJsonObject json = new CkJsonObject();
                boolean success = json.Load(resp);
                if (success != true) {
                    Log.i(TAG, json.lastErrorText());
                }

                json.put_EmitCompact(false);
                //json.put_EmitCrLf(false);
                if (spinner.getSelectedItem().toString().equals("Server Time")) {
                    showAlert(resp);
                }
                else {
                    showAlert(json.emit());
                }
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

    static {
        System.loadLibrary("chilkat");
    }
}

package com.lou.auth_okhttp;

import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.Scroller;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import android.app.Activity;
import android.widget.Toast;

import com.chilkatsoft.*;


import org.json.JSONException;

import okhttp3.OkHttpClient;

public class ApiCallAct extends AppCompatActivity {

    private static String response;

    String[] apiCalls = {"Server Time", "iOS Version", "Android Version", "Windows Version",
                        "Upcoming Events", "Student Stats", "Book Resources", "User Content"};
    private String callStr = null;
    private String finalStr = null;
    private HashMap<String, String> calls = new HashMap<String, String>();
    AutoCompleteTextView autoApi;
    Spinner spinner;
    final Context context = this;
    private static final String TAG = "Chilkat";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_api_call);

        MainActivity mainAct = null;
        try {
            mainAct = new MainActivity();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        UserAreaAct userArea = new UserAreaAct();

        Log.d("WTF WTF WTF WTF", "WHY U NO WORK?");

        //final TextView txt_api = (TextView) findViewById(R.id.txt_api);
        final Button btn_call = (Button) findViewById(R.id.btn_call);
        ///*AutoCompleteTextView */autoApi = (AutoCompleteTextView) findViewById(R.id.autotxt_api);
        /*Spinner*/ spinner = (Spinner) findViewById(R.id.spinner);
        //txt_api.setText("API Calls");
        //ArrayAdapter<String> adapter = ArrayAdapter.createFromResource(this, null, )

        /*final String*/
        String dns = mainAct.getDns();
        final OkHttpClient client = mainAct.getClient();
        final LoginInfo objLogin = mainAct.getObj1();
        final Example[] objBooks = userArea.getObjBooks();

        getSupportActionBar().setTitle("API Calls");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //getSupportActionBar().setDisplayShowHomeEnabled(true);

        /*@Override
        public boolean onOptionsItemSelected(MenuItem item) {
            switch (item.getItemId()) {
                case android.R.id.home:
                    this.finish();
                    return true;
                default:
                    return super.onOptionsItemSelected(item);
            }
        }*/

        String http = "https://";

        ArrayList<String> apiList = new ArrayList<>(Arrays.asList(apiCalls));

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
            //Log.wtf("BOOK RESOURCE", bookResc);
        }
        calls.put("Book Resources", bookResc);
        String userContent = http + dns + "/unity/api/1.0/epubs/bulk/userContent/since/0?noDeletes=1";
        for (int i = 0; i < objBooks.length; i++) {
            userContent = userContent + "&id=" + objBooks[i].getId();
            //Log.wtf("USER CONTENT", userContent);
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

                CkJsonObject json = new CkJsonObject();
                CkJsonArray jsonArray = new CkJsonArray();

                boolean success = jsonArray.Load(resp);
                if (success != true) {
                    Log.i(TAG, jsonArray.lastErrorText());
                    boolean s = json.Load(resp);
                    if (s) {
                        json.put_EmitCompact(false);
                        if (spinner.getSelectedItem().toString().equals("Server Time")) {
                            showAlert(resp);
                        }
                        else {
                            showAlert(json.emit());
                        }
                    }
                }
                else {
                    jsonArray.put_EmitCompact(false);
                    showAlert(jsonArray.emit());
                }
            }
        }.execute();

    }

    private HashMap<String, String> putData(String name, String dns) {
        HashMap<String, String> item = new HashMap<String, String>();
        item.put(name, dns);
        return item;
    }

    public void showAlert(final String msg) {
        //TextView showText = new TextView(this);

        //ScrollView showText = new ScrollView(this);

        /*showText.setTextColor(Color.BLACK);
        showText.setTextSize(18);
        showText.setTextIsSelectable(true);
        showText.setPadding(5, 5, 5, 5);
        //showText.setVerticalScrollBarEnabled(true);
        showText.setMovementMethod(new ScrollingMovementMethod());
        showText.setText(msg);
        showText.setHighlightColor(Color.BLUE);
        showText.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                ClipboardManager manager = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                TextView showTextParam = (TextView) v;
                manager.setText(showTextParam.getText());
                Toast.makeText(v.getContext(), "Text in Clipboard", Toast.LENGTH_LONG).show();
                return true;
            }
        });*/

        AlertDialog dialog;
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        //alert.setView(showText);
        dialog = alert.create();
        dialog.setTitle("Response");
        dialog.setCancelable(true);
        dialog.setMessage(msg);
        dialog.setButton(DialogInterface.BUTTON_NEUTRAL, "Copy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ClipboardManager manager = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                //AlertDialog showTextParam = (AlertDialog) dialog;
                manager.setText(msg);
                Toast.makeText(((AlertDialog) dialog).getContext(), "Text in Clipboard", Toast.LENGTH_LONG).show();
                //return true;
            }
        });
        dialog.show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    static {
        System.loadLibrary("chilkat");
    }
}

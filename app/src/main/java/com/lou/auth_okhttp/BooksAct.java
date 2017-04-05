package com.lou.auth_okhttp;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;

import okhttp3.OkHttpClient;

public class BooksAct extends AppCompatActivity {

    private ListView listView;
    private static String response;
    private static Example[] objMeta;
    private static Groups[] objGroup;
    private static Authorize objAuth;
    public static boolean ack;
    public MainActivity mainActivity;
    final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books);

        /*getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.miebooks);*/

        final TextView txt_books = (TextView) findViewById(R.id.tview_books);
        txt_books.setText("Books Activity");

        listView = (ListView) findViewById(R.id.list_books);

        mainActivity = null;
        try {
            mainActivity = new MainActivity();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        final OkHttpClient client = mainActivity.getClient();
        final LoginInfo objLogin = mainActivity.getObj1();

        loadContent(client, objLogin.user.username);

        listView.setClickable(true);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("#####CLICKEDY CLICK", "CLICKED");
                Log.d("#####CLICKEDY CLICK", objMeta[position].metadata.bookID);

                String url = "https://app.dev.it.si/alchemy/api/1.0/epubs/" + objMeta[position].metadata.bookID + "/key?device=auth_test";

                Log.d("USER_AREA:AUTH-TEST", url);

                validate(client, objMeta[position].metadata.bookID);
            }
        });
    }

    public void loadContent(final OkHttpClient client, final String username) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                try {
                    response = ApiCall.GET(client, RequestBuilder.buildUrl(username));
                    Log.d("USER_AREA:LoadContent", response);

                    Gson gson = new Gson();
                    objMeta = gson.fromJson(response, Example[].class);

                    updateList(objMeta);
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }
        }.execute();
    }

    private void updateList(final Example[] objMeta1) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < objMeta1.length; i++) {
                    Log.d("####BOOK_ID", objMeta1[i].metadata.bookID);
                    if (objMeta1[i].metadata.title == null || objMeta1[i].metadata.title == "") {
                        Log.d("###BOOK_TITLE", "NULL OU BUL");
                    }
                    Log.d("###BOOK_TITLE", objMeta1[i].metadata.title);
                }

                ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

                for (int j = 0; j < objMeta1.length; j++) {
                    if (objMeta1[j].metadata.title == "" || objMeta1[j].metadata.title == null) {
                        list.add(putData(objMeta1[j].metadata.bookID, "(Title not available)"));
                    }
                    else {
                        list.add(putData(objMeta1[j].metadata.bookID, objMeta1[j].metadata.title));
                    }
                }

                String[] from = {"Title: ", "BookID: "};
                int[] to = {android.R.id.text1, android.R.id.text2};
                SimpleAdapter adapter = new SimpleAdapter(BooksAct.this, list, android.R.layout.simple_list_item_2, from, to);
                listView.setAdapter(adapter);


            }
        });
    }

    private void checkAuth(final OkHttpClient client, final String id) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                if (objAuth.key == null) {
                    showAlert(objAuth.detail);
                }
                else {
                    Log.d("####OBJECT_KEY", objAuth.key);

                    String url = "https://app.dev.it.si/alchemy/api/1.0/epubs/" + id + "/key/confirm?device=auth_test&platform=web&model=na";
                    acknowledgeKey(url, objAuth.key, client);

                    //String ackstr = Boolean.toString(objAuth.ack);
                    //Log.wtf("###ACK_checkAuth", ackstr);
                }

            }
        });
    }

    public void validate(final OkHttpClient client, final String id) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                try {
                    Log.wtf("CHECK CHECK","VALIDATE");
                    String url = "https://app.dev.it.si/alchemy/api/1.0/epubs/" + id + "/key?device=auth_test";
                    response = ApiCall.GET(client, url);
                    Log.d("###CHECK_AUTH", response);

                    Gson gson = new Gson();
                    objAuth = gson.fromJson(response, Authorize.class);

                    checkAuth(client, id);

                }
                catch (IOException e) {
                    e.printStackTrace();
                    Log.e("ERROR TERROR", e.getMessage());
                }
                return null;
            }
        }.execute();
    }

    private void acknowledgeKey(final String url, final String key, final OkHttpClient client) {
        new AsyncTask<String, Void, Void>() {
            protected Void doInBackground(String... params) {
                assert (params[0] != null);
                try {
                    Log.wtf("CHECK CHECK","ACKNOWLEDGE_KEY");
                    response = ApiCall.POST(
                            client,
                            params[0],
                            RequestBuilder.PostKey(key)
                    );
                    Log.wtf("ACKNOWLEDGE_KEY", url);
                    Log.wtf("ACKNOWLEDGE_KEY", response);

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Gson gson = new Gson();
                            objAuth = gson.fromJson(response, Authorize.class);

                            ProgressDialog progress = new ProgressDialog(BooksAct.this);
                            progress.setTitle("Checking");
                            progress.setMessage("Good things come to those who wait");
                            progress.setCancelable(true);
                            progress.show();
                            //progress.dismiss();

                            //String ackstr = Boolean.toString(objAuth.ack);
                            //check = true;
                            //String test = Boolean.toString(ack);
                            //Log.wtf("###ACK_ackFunc", ackstr);
                            //Log.wtf("###ACK_TEST", test);

                            //ack = objAuth.ack;
                            progress.dismiss();
                            showAlert("Book Authenticated\n\nKey: " + key + "\n\n" + (objAuth.ack ? "Acknowledged" : "Not Acknowledged"));
                        }
                    });

                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }
        }.execute(url);
    }

    private HashMap<String, String> putData(String id, String title) {
        HashMap<String, String> item = new HashMap<String, String>();
        item.put("Title: ", title);
        item.put("BookID: ", id);
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

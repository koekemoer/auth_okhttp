package com.lou.auth_okhttp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;

import okhttp3.OkHttpClient;

public class BooksAct extends AppCompatActivity {

    private ListView listView;
    private static String response;
    private static Example[] objBooks;
    private static Groups[] objGroup;
    private static Authorize objAuth;
    private String dns;
    public static boolean ack;
    public MainActivity mainActivity;
    private UserAreaAct userArea;
    final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books);

        listView = (ListView) findViewById(R.id.list_books);

        mainActivity = null;
        try {
            mainActivity = new MainActivity();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        /*UserAreaAct */userArea = new UserAreaAct();

        final OkHttpClient client = mainActivity.getClient();
        final LoginInfo objLogin = mainActivity.getObj1();
        final Example[] books = userArea.getObjBooks();
        dns = mainActivity.getDns();

        Log.wtf("###### DNS", dns);

        getSupportActionBar().setTitle(objLogin.getUser().getFirstname() + "'s Books");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Log.wtf("USERNAME #####", objLogin.getUser().getUsername());
        loadContent(client, objLogin.getUser().getUsername(), dns);

        listView.setClickable(true);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("#####CLICKEDY CLICK", "CLICKED");

                Log.d("TEST BEFORE objBOOKS", "TOETS TOETS");

                if (client != null || objBooks[position].getMetadata().getBookID() != null) {
                    validate(client, objBooks[position].getMetadata().getBookID(), objBooks[position].getMetadata().getTitle());
                }
                else {
                    showAlert("Wiele");
                }
            }
        });
    }

    private void updateList(final Example[] objBooks) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

                for (int j = 0; j < objBooks.length; j++) {
                    if (objBooks[j].getMetadata().getTitle().equals("") || objBooks[j].getMetadata().getTitle() == null) {
                        list.add(putData(objBooks[j].getMetadata().getBookID(), "(Title not available)"));
                    }
                    else {
                        list.add(putData(objBooks[j].getMetadata().getBookID(), objBooks[j].getMetadata().getTitle()));
                    }
                }

                String[] from = {"Title: ", "BookID: "};
                int[] to = {android.R.id.text1, android.R.id.text2};
                SimpleAdapter adapter = new SimpleAdapter(BooksAct.this, list, android.R.layout.simple_list_item_2, from, to);
                listView.setAdapter(adapter);


            }
        });
    }

    public void loadContent(final OkHttpClient client, final String username, final String dns) {
        new AsyncTask<Void, Void, Example[]>() {
            @Override
            protected Example[] doInBackground(Void... params) {
                try {
                    response = ApiCall.GET(client, RequestBuilder.buildUrl(username, dns));
                    Log.d("USER_AREA:LoadContent", response);

                    if (response.equals("Unauthorized") || response == null || response.equals("")) {
                        showAlert("You are not Authorized\nto view this content");
                        //finish();
                        return null;
                    } else {
                        Gson gson = new Gson();
                        objBooks = gson.fromJson(response, Example[].class);
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
                return objBooks;
            }

            @Override
            protected void onPostExecute(Example[] books) {
                super.onPostExecute(books);
                if (books != null) {
                    updateList(books);
                }
            }
        }.execute();
    }

    private void checkAuth(final OkHttpClient client, final String id, final String title) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                if (objAuth.getKey() == null) {
                    debug("1ST IF", "OFFLINE");
                    showAlert(title + ":\n\nCould not be authenticated");
                }
                else if (client == null || id == null) {
                    showAlert(objAuth.getDetail());
                    debug("OFFLINE", objAuth.getKey());
                    debug("OFFLINE", id);
                    debug("OFFLINE", "WHAT TO DO WHEN OFFLINE");
                }
                else {
                    debug("ONLINE", objAuth.getKey());
                    String url = "https://" + dns + "/alchemy/api/1.0/epubs/" + id + "/key/confirm?device=auth_test&platform=web&model=na";
                    acknowledgeKey(url, objAuth.getKey(), client, title);
                }

            }
        });
    }

    public void validate(final OkHttpClient client, final String id, final String title) {
        new AsyncTask<Void, Void, Authorize>() {
            @Override
            protected Authorize doInBackground(Void... params) {
                try {
                    debug("ID", id);
                    String url = "https://" + dns + "/alchemy/api/1.0/epubs/" + id + "/key?device=auth_test";
                    response = ApiCall.GET(client, url);

                    debug("VALIDATE_RESPONSE", response);
                    if (response == null || response.equals("")) {
                        showAlert("Eish bru!");
                    }
                    else {
                        Gson gson = new Gson();
                        objAuth = gson.fromJson(response, Authorize.class);
                    }

                    debug("VALIDATE_KEY", objAuth.getKey());
                    debug("VALIDATE_DETAIL", objAuth.getDetail());
                    String ackstr = Boolean.toString(objAuth.isAck());
                    debug("VALIDATE_IS-ACK", ackstr);

                }
                catch (IOException e) {
                    e.printStackTrace();
                    Log.e("ERROR TERROR", e.getMessage());
                }
                return objAuth;
            }

            @Override
            protected void onPostExecute(Authorize auth) {
                super.onPostExecute(auth);
                checkAuth(client, id, title);
            }
        }.execute();
    }

    public void debug(final String msg, final String str) {
        Log.wtf("##### " + msg + " #####", str != null ? str : "NULL");
    }

    private void acknowledgeKey(final String url, final String key, final OkHttpClient client, final String title) {
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
                    debug("ACKNOWLEDGE_KEY", url);
                    debug("ACKNOWLEDGE_KEY", response);
                    debug("SHOW_KEY", key);

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

                            //ack = objAuth.ack;
                            debug("ACKNOWLEDGE_KEY", objAuth.getDetail());
                            String ackstr = Boolean.toString(objAuth.isAck());
                            debug("ACKNOWLEDGE_KEY", ackstr);

                            progress.dismiss();
                            if (!objAuth.isAck() || key == null || key.equals("")) {
                                showAlert("An error occurred.\n");
                            }
                            else {
                                showAlert(title + ":\n\nIs Authenticated");
                            }

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

    public void showAlert(String msg) {
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);
        AlertDialog alert;

        alert = alertBuilder.create();
        alert.setTitle("Result");
        //Log.wtf("CHECK CHECK", "CHECK_AUTH");
        alert.setMessage(msg);
        alert.setButton(DialogInterface.BUTTON_NEGATIVE, "Dismiss", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface alert, int which) {
                alert.cancel();
            }
        });
        alert.show();
    }

    public boolean isOnline(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if ((cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE) != null && cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).isConnected())
                || (cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI) != null && cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI).isConnected())) {
            return true;
        }
        else {
            return false;
        }
    }
}

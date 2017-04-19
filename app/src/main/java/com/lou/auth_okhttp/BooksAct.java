package com.lou.auth_okhttp;

import android.app.ProgressDialog;
import android.content.Context;
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
    //private static Example[] objMeta = null;
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

        Log.wtf("BOOKS_ACT!@#$%^&*()", "1");

        /*getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.miebooks);*/

        //final TextView txt_books = (TextView) findViewById(R.id.tview_books);
        //txt_books.setText("Books");

        listView = (ListView) findViewById(R.id.list_books);

        Log.wtf("BOOKS_ACT!@#$%^&*()", "2");

        mainActivity = null;
        try {
            mainActivity = new MainActivity();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        /*UserAreaAct */userArea = new UserAreaAct();

        Log.wtf("BOOKS_ACT!@#$%^&*()", "3");

        final OkHttpClient client = mainActivity.getClient();
        final LoginInfo objLogin = mainActivity.getObj1();
        final Example[] books = userArea.getObjBooks();
        dns = mainActivity.getDns();

        getSupportActionBar().setTitle(objLogin.getUser().getFirstname() + "'s Books");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Log.wtf("BOOKS_ACT!@#$%^&*()", "4");

        //loadContent(client, objLogin.user.username);

        updateList(books);

        Log.wtf("BOOKS_ACT!@#$%^&*()", "5");

        listView.setClickable(true);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("#####CLICKEDY CLICK", "CLICKED");
                Log.d("#####CLICKEDY CLICK", books[position].getMetadata().getBookID());

                String url = "https://" + dns + "/alchemy/api/1.0/epubs/" + books[position].getMetadata().getBookID() + "/key?device=auth_test";

                Log.d("USER_AREA:AUTH-TEST", url);

                validate(client, books[position].getMetadata().getBookID());
            }
        });
    }

    private void updateList(final Example[] objBooks) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                /*for (int i = 0; i < objBooks.length; i++) {
                    Log.d("####BOOK_ID", objBooks[i].getMetadata().getBookID());
                    if (objBooks[i].getMetadata().getTitle() == null || objBooks[i].getMetadata().getTitle() == "") {
                        Log.d("###BOOK_TITLE", "NULL OU BUL");
                    }
                    Log.d("###BOOK_TITLE", objBooks[i].getMetadata().getTitle());
                }*/

                ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

                for (int j = 0; j < objBooks.length; j++) {
                    if (objBooks[j].getMetadata().getTitle() == "" || objBooks[j].getMetadata().getTitle() == null) {
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

    private void checkAuth(final OkHttpClient client, final String id) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                if (objAuth.getKey() == null) {
                    showAlert(objAuth.getDetail());
                }
                else {
                    Log.d("####OBJECT_KEY", objAuth.getKey());
                    Log.wtf("SARIE_SLEEP_SEWE_SAKKE_SOUT", "5");

                    String url = "https://" + dns + "/alchemy/api/1.0/epubs/" + id + "/key/confirm?device=auth_test&platform=web&model=na";
                    acknowledgeKey(url, objAuth.getKey(), client);

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
                    Log.wtf("SARIE_SLEEP_SEWE_SAKKE_SOUT", "1");
                    String url = "https://" + dns + "/alchemy/api/1.0/epubs/" + id + "/key?device=auth_test";
                    response = ApiCall.GET(client, url);
                    Log.d("###CHECK_AUTH", response);
                    Log.wtf("SARIE_SLEEP_SEWE_SAKKE_SOUT", "2");


                    Gson gson = new Gson();
                    objAuth = gson.fromJson(response, Authorize.class);

                    Log.wtf("SARIE_SLEEP_SEWE_SAKKE_SOUT", "3");

                    //checkAuth(client, id);

                }
                catch (IOException e) {
                    e.printStackTrace();
                    Log.e("ERROR TERROR", e.getMessage());
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Log.wtf("SARIE_SLEEP_SEWE_SAKKE_SOUT", "4");
                checkAuth(client, id);
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
                            showAlert("Book Authenticated\n\nKey: " + key + "\n\n" + (objAuth.isAck() ? "Acknowledged" : "Not Acknowledged"));
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
        AlertDialog.Builder alert = new AlertDialog.Builder(context);
        alert.setTitle("Result").setCancelable(true);
        //Log.wtf("CHECK CHECK", "CHECK_AUTH");
        alert.setMessage(msg);
        alert.create();
        alert.show();
    }
}

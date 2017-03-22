package com.lou.auth_okhttp;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxStatus;
import com.google.gson.Gson;

import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;

import okhttp3.OkHttpClient;

public class UserAreaAct extends AppCompatActivity {

    private static String response;
    private static Example[] objMeta;
    private ListView listView;
    final Context context = this;
    //private AQuery aq;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_area);

        //aq = new AQuery(this);

        Log.d("UserArea:", "UserArea Entered");

        final TextView txt_welcome = (TextView) findViewById(R.id.textView_welcome);
        //final TextView txt_test = (TextView) findViewById(R.id.textView_test);
        Button btn_show = (Button) findViewById(R.id.btn_show);
        listView = (ListView) findViewById(R.id.list_books);

        MainActivity mainActivity = null;
        try {
            mainActivity = new MainActivity();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        final OkHttpClient client = mainActivity.getClient();
        final LoginInfo objLogin = mainActivity.getObj1();

        txt_welcome.setText("Welcome Mr. " + objLogin.surname);
        Log.d("UserArea:Username", mainActivity.finalUser);

        //btn_show.setOnClickListener(new View.OnClickListener() {
        //    @Override
        //    public void onClick(View v) {
                Log.d("CLICKEDY CLICK", "CLICKED");
                loadContent(client, objLogin.user.username);
        //    }
        //});

        //loadContent(client, objLogin.user.username);

        listView.setClickable(true);
        //listView.setLongClickable(true);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("#####CLICKEDY CLICK", "CLICKED");
                Log.d("#####CLICKEDY CLICK", objMeta[position].metadata.bookID);

                String url = "https://app.dev.it.si/alchemy/api/1.0/epubs/" + objMeta[position].metadata.bookID + "/key?device=auth_test";

                Log.d("USER_AREA:AUTH-TEST", url);

                validate(client, objMeta[position].metadata.bookID);

                //return true;
            }
        });

    }

    private void checkAuth(final Authorize objKey, final OkHttpClient client, final String id) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                AlertDialog.Builder alert = new AlertDialog.Builder(context);
                alert.setTitle("Result").setCancelable(true);

                if (objKey.key == null) {
                    Log.d("####OBJECT_KEY", "FOUND OBJECT_KEY");
                    Log.d("####OBJECT_TEST", objKey.key);
                    //Toast.makeText(UserAreaAct.this, objKey.key, Toast.LENGTH_LONG).show();
                    alert.setMessage("You are not authorized\nto view this material");
                }
                else {
                    Log.d("####OBJECT_KEY", objKey.key);

                    String url = "https://app.dev.it.si/alchemy/api/1.0/epubs/" + id + "/key/confirm?device=auth_test&platform=web&model=na";
                    acknowledgeKey(url, objKey.key, client);
                    alert.setMessage("Book Authenticated\n\nKey: " + objKey.key);
                }
                alert.create();
                alert.show();

            }
        });
    }

    public void validate(final OkHttpClient client, final String id) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                //Toast.makeText()
                try {
                    String url = "https://app.dev.it.si/alchemy/api/1.0/epubs/" + id + "/key?device=auth_test";
                    response = ApiCall.GET(client, url);
                    Log.d("###CHECK_AUTH", response);

                    Gson gson = new Gson();
                    Authorize objAuth = gson.fromJson(response, Authorize.class);

                    checkAuth(objAuth, client, id);

                }
                catch (IOException e) {
                    e.printStackTrace();
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
                            Authorize objAuth = gson.fromJson(response, Authorize.class);
                            //String ackStr = Boolean.toString(objAuth.ack);
                            //Toast.makeText(UserAreaAct.this, objAuth.key, Toast.LENGTH_LONG).show();

                            ProgressDialog progress = new ProgressDialog(UserAreaAct.this);
                            progress.setTitle("Checking");
                            progress.setMessage("Good things come to those who wait");
                            progress.setCancelable(true);
                            progress.show();
                            progress.dismiss();
                        }
                    });

                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }
        }.execute(url);
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

                    //listView = (ListView) findViewById(R.id.list_books);

                ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

                //HashMap<String, String> map = new HashMap<String, String>();
                for (int j = 0; j < objMeta1.length; j++) {
                    if (objMeta1[j].metadata.title == "" || objMeta1[j].metadata.title == null) {
                        list.add(putData(objMeta1[j].metadata.bookID, "(Title not available)"));
                    }
                    else {
                        list.add(putData(objMeta1[j].metadata.bookID, objMeta1[j].metadata.title));
                    }
                }
                //ArrayAdapter adapter = new ArrayAdapter(UserAreaAct.this, android.R.layout.simple_list_item_1, list);
                //listView.setAdapter(adapter);
                String[] from = {"Title: ", "BookID: "};
                int[] to = {android.R.id.text1, android.R.id.text2};
                SimpleAdapter adapter = new SimpleAdapter(UserAreaAct.this, list, android.R.layout.simple_list_item_2, from, to);
                listView.setAdapter(adapter);

            }
        });
    }

    private HashMap<String, String> putData(String id, String title) {
        HashMap<String, String> item = new HashMap<String, String>();
        item.put("Title: ", title);
        item.put("BookID: ", id);
        return item;
    }

    public Example[] getObjMeta() {
        return this.objMeta;
    }

    public void loadContent(final OkHttpClient client, final String username) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                try {
                    response = ApiCall.GET(client, RequestBuilder.buildUrl(username) /*user_url*/);
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
}

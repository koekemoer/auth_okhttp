package com.lou.auth_okhttp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
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
import java.security.acl.Group;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

public class UserAreaAct extends AppCompatActivity {

    private static String response;
    private static Example[] objMeta;
    private static Groups[] objGroup;
    private static Authorize objAuth;
    //private static Ack objAck;
    private ListView listView, listGroup;
    final Context context = this;
    public static boolean ack;
    public static boolean check = false;
    public MainActivity mainActivity;
    //private AQuery aq;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_area);

        Log.d("UserArea:", "UserArea Entered");

        /*getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.miebooks);*/

        final TextView txt_welcome = (TextView) findViewById(R.id.textView_welcome);
        final Button btn_show = (Button) findViewById(R.id.btn_show);
        final Button btn_groups = (Button) findViewById(R.id.btn_groups);
        //listView = (ListView) findViewById(R.id.list_books1);

        mainActivity = null;
        try {
            mainActivity = new MainActivity();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        final OkHttpClient client = mainActivity.getClient();

        Log.wtf("USERAREA!@#$%^&*()", "1");

        final LoginInfo objLogin = mainActivity.getObj1();

        loadContent(client, objLogin.user.username);

        txt_welcome.setText("Welcome " + objLogin.user.firstname);
        //Log.d("UserArea:Username", mainActivity.finalUser);

        Log.d("CLICKEDY CLICK", "CLICKED");

        btn_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.wtf("USERAREA!@#$%^&*()", "2");

                Intent intent = new Intent(UserAreaAct.this, BooksAct.class);
                UserAreaAct.this.startActivity(intent);
                Log.wtf("USERAREA!@#$%^&*()", "3");
            }
        });

        btn_groups.setOnClickListener(new View.OnClickListener(){
            boolean show = false;
            @Override
            public void onClick(View v) {
                Log.wtf("USERAREA!@#$%^&*()", "4");

                Intent intent = new Intent(UserAreaAct.this, GroupsAct.class);
                UserAreaAct.this.startActivity(intent);
                Log.wtf("USERAREA!@#$%^&*()", "5");
            }
        });

        /*listView.setClickable(true);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("#####CLICKEDY CLICK", "CLICKED");
                Log.d("#####CLICKEDY CLICK", objMeta[position].metadata.bookID);

                String url = "https://app.dev.it.si/alchemy/api/1.0/epubs/" + objMeta[position].metadata.bookID + "/key?device=auth_test";

                Log.d("USER_AREA:AUTH-TEST", url);

                validate(client, objMeta[position].metadata.bookID);
            }
        });*/

    }

    /*private void checkAuth(final OkHttpClient client, final String id) {
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
    }*/

    /*public void validate(final OkHttpClient client, final String id) {
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
    }*/

    /*private void acknowledgeKey(final String url, final String key, final OkHttpClient client) {
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

                            ProgressDialog progress = new ProgressDialog(UserAreaAct.this);
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

                            ack = objAuth.ack;
                            progress.dismiss();
                            showAlert("Book Authenticated\n\nKey: " + key + "\n\n" + (ack ? "Acknowledged" : "Not Acknowledged"));
                        }
                    });

                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }
        }.execute(url);
    }*/

    public void loadContent(final OkHttpClient client, final String username) {
        new AsyncTask<Void, Void, Example[]>() {
            @Override
            protected Example[] doInBackground(Void... params) {
                try {

                    Log.wtf("BOOKS_ACT!@#$%^&*()", "6");
                    response = ApiCall.GET(client, RequestBuilder.buildUrl(username));
                    Log.d("USER_AREA:LoadContent", response);

                    Log.wtf("BOOKS_ACT!@#$%^&*()", "7");

                    if (response.equals("Unauthorized")) {
                        showAlert("You are not Authorized\nto view this content");
                    } else {
                        Gson gson = new Gson();
                        objMeta = gson.fromJson(response, Example[].class);

                        Log.wtf("BOOKS_ACT!@#$%^&*()", "8");
                    }

                    //updateList(objMeta);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }

            /*@Override
            protected void onPostExecute(Example[] books) {
                super.onPostExecute(books);
                if (objMeta != null) {
                    Log.wtf("BOOKS_ACT!@#$%^&*()", "9");
                    //updateList(objMeta);
                    Log.wtf("BOOKS_ACT!@#$%^&*()", "10");
                }
            }*/
        }.execute();
    }

    public Example[] getObjmeta() {
        return this.objMeta;
    }

    /*private void updateList(final Example[] objMeta1) {
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
    }*/

    /*private HashMap<String, String> putData(String id, String title) {
        HashMap<String, String> item = new HashMap<String, String>();
        item.put("Title: ", title);
        item.put("BookID: ", id);
        return item;
    }/*

    /*public void showGroups (final OkHttpClient client, final String url) {
        new AsyncTask<Void, Void, Void>() {
            protected Void doInBackground(Void... params) {
                try {
                    response = ApiCall.GET(client, url);
                    Log.wtf("###_SHOW_GROUPS_###", response);

                    Gson gson = new Gson();
                    objGroup = gson.fromJson(response, Groups[].class);

                    updateGroups(objGroup);


                } catch (IOException e) {
                    e.printStackTrace();
                    Log.e("ERROR TERROR", e.getMessage());
                }
                return null;
            }
        }.execute();
    }*/

    /*public void updateGroups (final Groups[] objGrp) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                for (int i = 0; i < objGrp.length; i++) {
                    Log.d("####NAME/GROUP", objGrp[i].name);
                    //if (objGrp[i].name == "" || objGrp[i].name == null) {
                    //    Log.d("###BOOK_TITLE", "NULL OU BUL");
                    //}
                    Log.d("###SUBJECT", objGrp[i].subject);
                }

                ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

                for (int j = 0; j < objGrp.length; j++) {
                    //if (objGrp[j].subject == "" || objGrp[j].subject == null) {
                        //list.add(putData2(objGrp[j].subject, "(Title not available)"));
                    //}
                    //else {
                        list.add(putData2(objGrp[j].subject, objGrp[j].name));
                    //}
                }

                String[] from = {"Subject: ", "Name: "};
                int[] to = {android.R.id.text1, android.R.id.text2};
                SimpleAdapter adapter = new SimpleAdapter(UserAreaAct.this, list, android.R.layout.simple_list_item_2, from, to);
                listView.setAdapter(adapter);
            }
        });
    }*/

    private HashMap<String, String> putData(String id, String title) {
        HashMap<String, String> item = new HashMap<String, String>();
        item.put("Title: ", title);
        item.put("BookID: ", id);
        return item;
    }

    private HashMap<String, String> putData2(String id, String title) {
        HashMap<String, String> item = new HashMap<String, String>();
        item.put("Subject: ", title);
        item.put("Name: ", id);
        return item;
    }

    public Example[] getObjMeta() {
        return this.objMeta;
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

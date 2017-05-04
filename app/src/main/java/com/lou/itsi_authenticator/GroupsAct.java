package com.lou.itsi_authenticator;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.google.gson.Gson;
import com.lou.auth_okhttp.R;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;

import okhttp3.OkHttpClient;

public class GroupsAct extends AppCompatActivity {

    private ListView listView;
    private static String response;
    private static Example[] objMeta;
    private static Groups[] objGroup;
    private static Authorize objAuth;
    private String dns;
    public MainActivity mainActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_groups);

        /*getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.miebooks);*/

        //final TextView txt_books = (TextView) findViewById(R.id.tview_groups);
        //txt_books.setText("Groups");

        listView = (ListView) findViewById(R.id.list_groups);

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
        dns = mainActivity.getDns();

        getSupportActionBar().setTitle(objLogin.getUser().getFirstname() + "'s Groups");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        showGroups(client, "https://" + dns + "/alchemy/api/1.0/users/" + objLogin.getUser().getUsername() + "/enrolment?withPrivate=true");
    }

    public void showGroups (final OkHttpClient client, final String url) {
        new AsyncTask<Void, Void, Void>() {
            protected Void doInBackground(Void... params) {
                try {
                    response = ApiCall.GET(client, url);
                    Log.wtf("###_SHOW_GROUPS_###", response);

                    Gson gson = new Gson();
                    objGroup = gson.fromJson(response, Groups[].class);

                    Log.wtf("###_CHECK CHECK_###", "1");

                    updateGroups(objGroup);


                } catch (IOException e) {
                    e.printStackTrace();
                    Log.e("ERROR TERROR", e.getMessage());
                }
                return null;
            }
        }.execute();
    }

    public void updateGroups (final Groups[] objGrp) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                /*Log.wtf("###_CHECK CHECK_###", "2");

                for (int i = 0; i < objGrp.length; i++) {

                    if (objGrp[i].getName() == "" || objGrp[i].getName() == null) {
                        Log.d("###BOOK_TITLE", "(Name not available)");
                    }
                    else {
                        Log.d("####NAME/GROUP", objGrp[i].getName());
                    }
                    //Log.d("###SUBJECT", objGrp[i].getSubject());
                    if (objGrp[i].getSubject() == "" || objGrp[i].getSubject() == null) {
                        Log.d("###SUBJECT", "(Subject Not available");
                    }
                    else {
                        Log.d("###SUBJECT", objGrp[i].getSubject());
                    }
                }

                Log.wtf("###_CHECK CHECK_###", "3");*/

                ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

                for (int j = 0; j < objGrp.length; j++) {
                    if (objGrp[j].getName() == "" || objGrp[j].getName() == null) {
                        list.add(putData(objGrp[j].getSubject(), "(Name not Available)"));
                    }
                    else {
                        list.add(putData(objGrp[j].getSubject(), objGrp[j].getName()));
                    }
                }

                Log.wtf("###_CHECK CHECK_###", "4");

                String[] from = {"Subject: ", "Name: "};
                int[] to = {android.R.id.text1, android.R.id.text2};
                SimpleAdapter adapter = new SimpleAdapter(GroupsAct.this, list, android.R.layout.simple_list_item_2, from, to);
                listView.setAdapter(adapter);

                Log.wtf("###_CHECK CHECK_###", "5");
            }
        });
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

    private HashMap<String, String> putData(String id, String title) {
        HashMap<String, String> item = new HashMap<String, String>();
        item.put("Subject: ", title);
        item.put("Name: ", id);
        return item;
    }
}

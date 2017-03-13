package com.lou.auth_okhttp;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;

import org.w3c.dom.Text;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import okhttp3.OkHttpClient;

public class UserAreaAct extends AppCompatActivity {

    //private static String user_url = "https://app.dev.it.si/alchemy/api/users/harvey/books";
    private static String response;
    private static Example[] objMeta;
    //private static Example[] objExample;
    //private static Map<String, Object> objMap;
    //private static List<Example> objList = new ArrayList<>();

    //public String test = "Wiele";

    //final TextView txt_test = (TextView) findViewById(R.id.textView_test);

    //private OkHttpClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_area);

        Log.d("UserArea:", "UserArea Entered");

        final TextView txt_welcome = (TextView) findViewById(R.id.textView_welcome);
        final TextView txt_test = (TextView) findViewById(R.id.textView_test);
        final Button btn_show = (Button) findViewById(R.id.btn_show);

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

        btn_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                loadContent(client, objLogin.user.username);

                /*for (int i = 0; i < objMeta.length; i++) {
                    Log.d("###########BOOK_ID", objMeta[i].metadata.bookID);
                    if (objMeta[i].metadata.title == null || objMeta[i].metadata.title == "") {
                        Log.d("#########BOOK_TITLE", "NULL OU BUL");
                    }
                    Log.d("###########BOOK_TITLE", objMeta[i].metadata.title);
                }*/

            }
        });

        //loadContent(client, objLogin.user.username);

        //loadContent(mainActivity.getClient(), objLogin.user.username);

        //String[] textArr = {"One", "Two", "Three", "Four"};

        /*LinearLayout linearLayout = new LinearLayout(this);
        setContentView(linearLayout);
        linearLayout.setOrientation(LinearLayout.VERTICAL);*/
        /*for (int i = 0; i < textArr.length; i++) {
            TextView textView = new TextView(this);
            textView.setText(textArr[i]);
            linearLayout.addView(textView);
        }*/

        /*RelativeLayout relativeLayout = new RelativeLayout(this);
        setContentView(relativeLayout);
        for (int i = 0; i < textArr.length; i++) {
            TextView textView = new TextView(this);
            textView.setText(textArr[i]);
            layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT)
            relativeLayout.addView(textView);
        }*/

        Log.i("####################", "AFTER GSON");
        //Log.i("USER_AREA:BookID", objList.get(0).metadata.bookID);
        //Log.i("USER_AREA:Book_Title", objList.get(0).metadata.title);*/

    }

    public Example[] getObjMeta() {
        return this.objMeta;
    }

    public void loadContent(final OkHttpClient client, final String username) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                //Toast.makeText()
                try {
                    response = ApiCall.GET(client, RequestBuilder.buildUrl(username) /*user_url*/);
                    Log.d("USER_AREA:LoadContent", response);

                    Gson gson = new Gson();
                    objMeta = gson.fromJson(response, Example[].class);
                    //objMap = gson.fromJson(response, new TypeToken<Map<String, Object>>(){}.getType());
                    //objList = gson.fromJson(response, new TypeToken<List<Example>>(){}.getType());

                    for (int i = 0; i < objMeta.length; i++) {
                        Log.d("###########BOOK_ID", objMeta[i].metadata.bookID);
                        if (objMeta[i].metadata.title == null || objMeta[i].metadata.title == "") {
                            Log.d("#########BOOK_TITLE", "NULL OU BUL");
                        }
                        Log.d("###########BOOK_TITLE", objMeta[i].metadata.title);
                    }
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }
        }.execute();
    }

    public void loadContent2(final OkHttpClient client, final String username) {
        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... params) {
                String resp = null;
                try {
                    resp = ApiCall.GET(client, RequestBuilder.buildUrl(username));
                    Log.d("USER_AREA:LoadContent", resp);
                    return resp;
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return resp;
            }
        };
    }
}

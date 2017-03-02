package com.lou.auth_okhttp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class UserAreaAct extends AppCompatActivity {

    private static String user_url = "https://app.dev.it.si/alchemy/api/users/harvey/books";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_area);


    }
}

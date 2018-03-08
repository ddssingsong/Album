package com.trustmobi.basiclib;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class LauncherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);
    }

    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.button:
                intent.setClass(this, UtilActivity.class);
                break;
            case R.id.button1:
                intent.setClass(this, EventActivity.class);
                break;
            default:
                break;
        }

        startActivity(intent);
    }
}

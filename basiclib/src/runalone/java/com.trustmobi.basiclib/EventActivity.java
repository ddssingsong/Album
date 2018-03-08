package com.trustmobi.basiclib;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import com.trustmobi.basiclib.event.BusProvider;
import com.trustmobi.basiclib.event.LoginEvent;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import io.reactivex.functions.Consumer;

public class EventActivity extends AppCompatActivity {


    EditText edit;
    TextView text;
    TextView text1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        edit = (EditText) findViewById(R.id.edit);
        text = (TextView) findViewById(R.id.text);
        text1 = (TextView) findViewById(R.id.text1);

        edit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                BusProvider.getBus().post(new LoginEvent(s.toString()));
                BusProvider.getBus1().post(new LoginEvent(s.toString()));
            }
        });

        BusProvider.getBus1().toFollowable(LoginEvent.class)
                .subscribe(new Consumer<LoginEvent>() {
                    @Override
                    public void accept(LoginEvent loginEvent) throws Exception {
                        text1.setText(loginEvent.getText());
                    }
                });

    }

    @Override
    protected void onStart() {
        super.onStart();
        BusProvider.getBus().register(this);

    }

    @Override
    protected void onStop() {
        super.onStop();
        BusProvider.getBus().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(LoginEvent loginEvent) {
        text.setText(loginEvent.getText());
    }

}

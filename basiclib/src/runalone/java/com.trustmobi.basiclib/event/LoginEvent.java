package com.trustmobi.basiclib.event;

/**
 * Created by wanglei on 2017/1/30.
 */

public class LoginEvent implements IBus.IEvent {
    private String text;

    public LoginEvent(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    @Override
    public int getTag() {
        return 0;
    }
}

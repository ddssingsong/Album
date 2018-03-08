package com.trustmobi.basiclib.event;

import org.greenrobot.eventbus.EventBus;


public class EventBusImpl implements IBus {

    public static EventBusImpl get() {
        return EventBusImpl.Holder.instance;
    }

    @Override
    public void register(Object object) {
        EventBus.getDefault().register(object);
    }

    @Override
    public void unregister(Object object) {
        EventBus.getDefault().unregister(object);
    }

    @Override
    public void post(IEvent event) {
        EventBus.getDefault().post(event);
    }

    @Override
    public void postSticky(IEvent event) {
        EventBus.getDefault().postSticky(event);
    }
    private static class Holder {
        private static final EventBusImpl instance = new EventBusImpl();
    }
}

package com.trustmobi.basiclib.event;


/**
 * impl "EventBus/RxBus"
 */
public interface IBus {

    void register(Object object);

    void unregister(Object object);

    void post(IEvent event);

    void postSticky(IEvent event);


    interface IEvent {
        int getTag();
    }


}

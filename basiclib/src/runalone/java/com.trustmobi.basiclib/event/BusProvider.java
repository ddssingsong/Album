package com.trustmobi.basiclib.event;


public class BusProvider {

    private static EventBusImpl bus;

    public static EventBusImpl getBus() {
        if (bus == null) {
            synchronized (BusProvider.class) {
                if (bus == null) {
                    bus = EventBusImpl.get();
                }
            }
        }
        return bus;
    }


    private static RxBusImpl bus1;

    public static RxBusImpl getBus1() {
        if (bus1 == null) {
            synchronized (BusProvider.class) {
                if (bus1 == null) {
                    bus1 = RxBusImpl.get();
                }
            }
        }
        return bus1;
    }


}

package mx.softux.androidweardemo;

import com.squareup.otto.Bus;

/**
 * Created by juan on 7/7/15.
 */
public class EventBus {
    private static Bus instance;

    private EventBus() {

    }

    public static Bus getInstance() {
        if(instance == null) {
            instance = new Bus();
        }

        return instance;
    }
}

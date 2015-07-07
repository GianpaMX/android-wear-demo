package mx.softux.androidweardemo;

import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.gcm.GcmListenerService;

/**
 * Created by juan on 7/7/15.
 */
public class AndroidWearDemoGcmListenerService extends GcmListenerService {
    private static final String TAG = AndroidWearDemoGcmListenerService.class.getSimpleName();

    @Override
    public void onMessageReceived(String from, Bundle data) {
        String sender = data.getString("sender");
        String message = data.getString("message");
        Log.d(TAG, "From: " + from);
        Log.d(TAG, "Message: " + message);

        EventBus.getInstance().post(new ConversationMessage(sender, message));
    }
}

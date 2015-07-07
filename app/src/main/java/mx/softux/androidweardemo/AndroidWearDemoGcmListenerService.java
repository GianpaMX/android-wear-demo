package mx.softux.androidweardemo;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
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

        sendNotification(message);
    }

    private void sendNotification(String message) {
        int notificationId = 001;

        Intent viewIntent = new Intent(this, ConversationActivity.class);
        PendingIntent viewPendingIntent = PendingIntent.getActivity(this, 0, viewIntent, 0);

        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.ic_chat_black_24dp)
                        .setContentTitle("New message")
                        .setContentText(message)
                        .setContentIntent(viewPendingIntent);

        NotificationManagerCompat notificationManager =
                NotificationManagerCompat.from(this);


        notificationManager.notify(notificationId, notificationBuilder.build());
    }
}

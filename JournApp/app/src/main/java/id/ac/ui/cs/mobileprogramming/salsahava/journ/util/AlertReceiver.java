package id.ac.ui.cs.mobileprogramming.salsahava.journ.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;

public class AlertReceiver extends BroadcastReceiver {
    private static final String NAME_KEY = "name";
    private static final String DESC_KEY = "desc";

    @Override
    public void onReceive(Context context, Intent intent) {
        String name = intent.getStringExtra(NAME_KEY);
        String desc = intent.getStringExtra(DESC_KEY);
        NotificationHelper notificationHelper = new NotificationHelper(context);
        NotificationCompat.Builder builder = notificationHelper.getChannelNotification(name, desc);
        notificationHelper.getNotificationManager().notify(1, builder.build());
    }
}

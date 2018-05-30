package com.android.sudesi.schoolapp;

import android.annotation.SuppressLint;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.provider.Settings;
import android.support.v7.app.NotificationCompat;
import android.util.Log;

public class InternetBroadcast extends BroadcastReceiver {

    @SuppressLint("UnsafeProtectedBroadcastReceiver")
    @Override
    public void onReceive(final Context context, final Intent intent) {
        final ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        assert connMgr != null;
        final android.net.NetworkInfo wifi = connMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        final android.net.NetworkInfo mobile = connMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

        if (wifi.isAvailable()) {
            // Do something
            Log.d("Network Available ", "Flag No 1");
            enableNotification(context,false);
        } else if (!wifi.isAvailable()) {
            enableNotification(context, true);
        }
    }

    public void enableNotification(Context context, boolean enabled) {
        String message1 = "Please Enable the Internet";


        try {
            String message = "Click here to enable Internet setting";
            NotificationCompat.Builder builder =
                    (NotificationCompat.Builder) new NotificationCompat.Builder(context)
                            .setSmallIcon(R.drawable.notify1)
                            .setStyle(new NotificationCompat.BigTextStyle().bigText(message))
                            .setContentTitle(message1).setAutoCancel(true);

            // .setContentText("The current date and time is\n"+time_str).setAutoCancel(true);

            Intent notificationIntent = new Intent();
            notificationIntent.setAction(Settings.ACTION_WIFI_SETTINGS);
            PendingIntent contentIntent = PendingIntent.getActivity(context, 2, notificationIntent,
                    PendingIntent.FLAG_UPDATE_CURRENT);
            builder.setContentIntent(contentIntent);

            // Add as notification
            NotificationManager notificationmanager = (NotificationManager) context
                    .getSystemService(Context.NOTIFICATION_SERVICE);
            // Build Notification with Notification Manager
            notificationmanager.notify(0, builder.build());
            if (enabled) {
                notificationmanager.cancel(0);
            }

            //startForeground(2, builder.build());

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}
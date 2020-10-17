package com.example.beststudy;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class NotificationReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationCompat.Builder timedBuilder = new NotificationCompat.Builder(context, "timedRems")
                .setSmallIcon(R.drawable.ic_baseline_access_alarm_24)
                .setContentTitle("Assignments due")
                .setContentText("Reminder:Assignments due soon!")
                .setPriority(NotificationCompat.PRIORITY_HIGH);

        NotificationManagerCompat notifManage = NotificationManagerCompat.from(context);
        notifManage.notify(442,timedBuilder.build());

    }
}
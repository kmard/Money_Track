package com.example.p0991_servicenotification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.concurrent.TimeUnit;

public class MyService extends Service {

    private static final String TAG = "myLogs";

    NotificationManager nm;

    @Override
    public void onCreate() {
        super.onCreate();
        nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Log.i(TAG, "MyService onCreate: ");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        sendNotif();
        Log.i(TAG, "MyService onStartCommand: ");
        return super.onStartCommand(intent, flags, startId);
    }

    void sendNotif() {
        //part 1
        Notification notif = new Notification(R.drawable.ic_launcher_foreground, "Text in status bar", System.currentTimeMillis());

        //part 3
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(MainActivity.FILE_NAME, "somefile");
        PendingIntent pIntent = PendingIntent.getActivity(this, 0, intent, 0);

        //part 2
        //  notif.setLatestEventInfo(this,"Notification's title","Notification text",pIntent);

        //put flag, that notification lost after putting
        notif.flags |= Notification.FLAG_AUTO_CANCEL;

        //send
        nm.notify(1, notif);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}

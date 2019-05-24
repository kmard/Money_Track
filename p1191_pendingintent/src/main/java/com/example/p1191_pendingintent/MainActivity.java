package com.example.p1191_pendingintent;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "myLogs";

    NotificationManager nm;
    AlarmManager am;
    Intent intent1;
    Intent intent11;
    Intent intent2;
    Intent intent22;
    PendingIntent pIntent1;
    PendingIntent pIntent11;
    PendingIntent pIntent2;
    PendingIntent pIntent22;
    Notification myNotication;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        am = (AlarmManager) getSystemService(ALARM_SERVICE);
    }

    public void onClick1(View view) {
        intent1 = createIntent("action 1", "extra 1");
        pIntent1 = PendingIntent.getBroadcast(this, 0, intent1, 0);

        intent2 = createIntent("action 2 ", "extra 2");
        pIntent2 = PendingIntent.getBroadcast(this, 0, intent2, 0);

        compare();

        sendNotif(1, pIntent1);
        sendNotif(2, pIntent2);
    }

    public void onClick2(View view) {

        intent11 = createIntent("action 11", "extra 1");
        pIntent11 = PendingIntent.getBroadcast(this, 0, intent11, 0);

        intent22 = createIntent("action 2 ", "extra 2");
        pIntent22 = PendingIntent.getBroadcast(this, 0, intent22, 0);

        Log.i(TAG, "onClick2: start");
        am.set(AlarmManager.RTC, System.currentTimeMillis() + 4000, pIntent11);
        am.setRepeating(AlarmManager.ELAPSED_REALTIME, SystemClock.elapsedRealtime() + 3000, 5000, pIntent22);

    }

    Intent createIntent(String action, String extra) {
        Intent intent = new Intent(this, Receiver.class);
        intent.setAction(action);
        intent.putExtra("extra", extra);
        return intent;
    }

    private void compare() {
        Log.i(TAG, "intent1 = intent2 : " + intent1.filterEquals(intent2));
        Log.i(TAG, "pIntent1 = pIntent2 : " + pIntent1.equals(pIntent2));
    }

    void sendNotif(int id, PendingIntent pIntent) {


//    Notification notif = new Notification(R.drawable.ic_launcher_background, "Notif " + id, System.currentTimeMillis());
//    notif.flags |= Notification.FLAG_AUTO_CANCEL;
//    notif.setLatestEventInfo(this,"Title "+id,"Content "+id,pIntent);
//      nm.notify(id, notif);

        Notification.Builder builder = new Notification.Builder(MainActivity.this);
        builder.setAutoCancel(false);
        builder.setTicker("this is ticker text");
        builder.setContentText("You have a new message");
        builder.setSmallIcon(R.drawable.ic_launcher_background);
        builder.setContentIntent(pIntent);
        builder.setOngoing(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            builder.setSubText("This is subtext...");   //API level 16
        }
        builder.setNumber(100);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            builder.build();
        }
        myNotication = builder.getNotification();
        nm.notify(id, myNotication);
    }

}


















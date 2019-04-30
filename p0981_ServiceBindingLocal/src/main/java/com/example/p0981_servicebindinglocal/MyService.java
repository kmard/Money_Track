package com.example.p0981_servicebindinglocal;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;

public class MyService extends Service {

    private static final String TAG = "myLogs";

    MyBinder binder = new MyBinder();

    Timer timer;
    TimerTask tTask;
    long interval = 1000;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "MyService onCreate: ");
        timer = new Timer();
        schedule();
    }

    private void schedule() {
        if (tTask != null) tTask.cancel();
        if (interval > 0) {
            tTask = new TimerTask() {
                @Override
                public void run() {
                    Log.i(TAG, "run: ");
                }
            };
            timer.schedule(tTask, 1000, interval);
        }
    }

    long upInterval(long gap) {
        interval = interval + gap;
        schedule();
        return interval;
    }

    long downInterval(long gap) {
        interval = interval - gap;
        if (interval < 0) interval = 0;
        schedule();
        return interval;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG, "MyService onBind: ");
        return binder;
    }


    class MyBinder extends Binder {
        MyService getService() {
            return MyService.this;
        }
    }
}

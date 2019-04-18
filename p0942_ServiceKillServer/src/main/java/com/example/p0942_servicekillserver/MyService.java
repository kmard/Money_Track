package com.example.p0942_servicekillserver;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import java.util.concurrent.TimeUnit;

public class MyService extends Service {

    private static final String TAG = "myLogs";

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "MyService onCreate: ");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "MyService onDestroy: ");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "MyService onStartCommand: ");
        readFlags(flags);
        MyRun mr = new MyRun(startId);
        new Thread(mr).start();
        return START_NOT_STICKY;
        //return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent arg0t) {
        return null;
    }

    private void readFlags(int flags) {

        if ((flags & START_FLAG_REDELIVERY) == START_FLAG_REDELIVERY) {
            Log.i(TAG, "readFlags: START_FLAG_REDELIVERY ");
        }
        if ((flags & START_FLAG_RETRY) == START_FLAG_RETRY) {
            Log.i(TAG, "readFlags: START_FLAG_RETRY");
        }

    }

    private class MyRun implements Runnable {

        int startId;

        public MyRun(int startId) {
            this.startId = startId;
            Log.i(TAG, "MyRun# " + startId + " create");
        }

        @Override
        public void run() {
            Log.i(TAG, "MyRun#  " + startId + " start");
            try {
                TimeUnit.SECONDS.sleep(15);
            } catch (Exception e) {
                e.printStackTrace();
            }
            stop();
        }

        private void stop() {

            Log.i(TAG, "MyRun# " + startId + "end, stopSelfResult("
                    + startId + ") = " + stopSelfResult(startId));
        }
    }
}

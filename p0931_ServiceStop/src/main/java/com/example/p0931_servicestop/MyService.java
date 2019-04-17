package com.example.p0931_servicestop;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MyService extends Service {

    private static final String TAG = "myLogs";
    ExecutorService es;
    Object someRes;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "MyService onCreate: ");
        es = Executors.newFixedThreadPool(3);
        someRes = new Object();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "myService onDestroy: ");
        someRes = null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "MyService onStartCommand: ");
        int time = intent.getIntExtra("time", 1);
        MyRun mr = new MyRun(time, startId);
        es.execute(mr);
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private class MyRun implements Runnable {

        int time;
        int startId;

        public MyRun(int time, int startId) {
            this.time = time;
            this.startId = startId;
            Log.i(TAG, "MyRun# " + startId + " create");
        }

        @Override
        public void run() {
            Log.i(TAG, "MyRun# " + startId + " start, time = " + time);
            try {
                TimeUnit.SECONDS.sleep(time);
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                Log.i(TAG, "MyRun# " + startId + " someRes " + someRes.getClass());
            } catch (Exception e) {
                Log.i(TAG, "MyRun# " + startId + " error, null pointer");
            }
            stop();
        }

        private void stop() {
            Log.i(TAG, "MyRun# " + startId + " end, stopSelf( " + startId + " )");
            stopSelf(startId);
        }
    }
}

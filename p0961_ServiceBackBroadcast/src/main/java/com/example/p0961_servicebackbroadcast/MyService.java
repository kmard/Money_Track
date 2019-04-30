package com.example.p0961_servicebackbroadcast;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MyService extends Service {

    private static final String TAG = "myLogs";
    ExecutorService es;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "MyService onCreate: ");
        es = Executors.newFixedThreadPool(2);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "MyService onDestroy: ");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "MyService onStartCommand: ");

        int time = intent.getIntExtra(MainActivity.PARAM_TIME, 1);
        int task = intent.getIntExtra(MainActivity.PARAM_TASK, 0);

        MyRun mr = new MyRun(startId, time, task);
        es.execute(mr);

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    private class MyRun implements Runnable {

        int time;
        int startId;
        int task;

        public MyRun(int time, int startId, int task) {
            this.time = time;
            this.startId = startId;
            this.task = task;
            Log.i(TAG, "MyRun#: " + startId + " create");
        }

        @Override
        public void run() {
            Intent intent = new Intent(MainActivity.BROADCAST_ACTION);
            Log.i(TAG, "MyRun#: " + startId + "start, time " + time);
            try {
                //message about start task
                intent.putExtra(MainActivity.PARAM_TASK, task);
                intent.putExtra(MainActivity.PARAM_STATUS, MainActivity.STATUS_START);
                sendBroadcast(intent);

                //begin executing task
                TimeUnit.SECONDS.sleep(time);

                //message about finished task
                intent.putExtra(MainActivity.PARAM_STATUS, MainActivity.STATUS_FINISH);
                intent.putExtra(MainActivity.PARAM_RESULT, time * 100);
                sendBroadcast(intent);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            stop();
        }

        void stop() {
            Log.i(TAG, " MyRun#" + startId + " end, stopSelfResult( "
                    + startId + ") = " + stopSelfResult(startId));
        }
    }
}

package com.example.p0921_servicesimple;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.concurrent.TimeUnit;

public class MyService extends Service {

    private static final String TAG = "myLogs";

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate: ");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "onStartCommand: ");
        someTask();
        return super.onStartCommand(intent, flags, startId);
    }

    void someTask() {
//        for (int i = 0; i<=5; i++){
//            Log.i(TAG, "i = "+i);
//            try{
//                TimeUnit.SECONDS.sleep(1);
//            }catch(Exception e){
//                e.printStackTrace();
//            }
//        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 5; i++) {
                    Log.i(TAG, "i = " + i);
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                //stop service
                stopSelf();
            }
        }).start();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy: ");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG, "onBind: ");
        return null;
    }
}

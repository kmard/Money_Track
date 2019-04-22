package com.example.p0971_servicebindclient;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "myLogs";

    boolean bound = false;
    ServiceConnection sConn;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        intent = new Intent("com.example.p0972_servicebindserver.MyService");

        sConn = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                Log.i(TAG, "MainActivity  onServiceConnected: ");
                bound = true;
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                Log.i(TAG, "mainActivity onServiceDisconnected: ");
                bound = false;
            }
        };

    }

    public void onClickStart(View v) {
        startService(intent);
    }

    public void onClickStop(View v) {
        stopService(intent);
    }

    public void onClickBind(View v) {
        bindService(intent, sConn, BIND_AUTO_CREATE);
    }

    public void onClickUnBind(View v) {
        if (!bound) return;
        unbindService(sConn);
        bound = false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        onClickUnBind(null);
    }
}

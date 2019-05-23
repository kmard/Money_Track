package com.example.p1191_pendingintent;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class Receiver extends BroadcastReceiver {

    private static final String TAG = "myLogs";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(TAG, "onReceive: ");
        Log.i(TAG, "action : " + intent.getAction());
        Log.i(TAG, "extra : " + intent.getStringExtra("extra"));
    }
}

package com.example.p0921_servicesimple;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "myLogs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickStop(View v) {
        Log.i(TAG, "stopService ");
        stopService(new Intent(this, MyService.class));
    }

    public void onClickStart(View v) {
        Log.i(TAG, "startService ");
        startService(new Intent(this, MyService.class));
    }
}

package com.example.p0241_twoactivitystate;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class ActivityTwo extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "State ";
    Button btActMain;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);
        btActMain = (Button) findViewById(R.id.btActMain);
        btActMain.setOnClickListener(this);
        Log.i(TAG, "onCreate: Activity two");
    }

    @Override
    protected void onRestart() {
        Log.i(TAG, "onRestart: Activity two");
        super.onRestart();
    }

    @Override
    protected void onStart() {
        Log.i(TAG, "onStart: Activity two");
        super.onStart();
    }

    @Override
    protected void onResume() {
        Log.i(TAG, "onResume: Activity two");
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.i(TAG, "onPause: Activity two");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.i(TAG, "onStop: Activity two");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.i(TAG, "onDestroy: Activity two");
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}

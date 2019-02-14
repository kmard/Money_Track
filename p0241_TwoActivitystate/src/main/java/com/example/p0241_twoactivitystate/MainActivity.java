package com.example.p0241_twoactivitystate;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "State";
    
    Button btnActTwo;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        btnActTwo = (Button) findViewById(R.id.btnActTwo);
        btnActTwo.setOnClickListener(this);
        Log.i(TAG, "onCreate: Main");
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this,ActivityTwo.class);
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        Log.i(TAG, "onStart: Main");
        super.onStart();
    }
    
    @Override
    protected void onResume() {
        Log.i(TAG, "onResume: Main");
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.i(TAG, "onPause: Main");
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        Log.i(TAG, "onDestroy: Main");
        super.onDestroy();
    }

    @Override
    protected void onStop() {
        Log.i(TAG, "onStop: Main");
        super.onStop();
    }

    @Override
    protected void onRestart() {
        Log.i(TAG, "onRestart: Main");
        super.onRestart();
    }
}

package com.example.p0691_parcelable;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class Second extends AppCompatActivity {

    private static final String TAG = "myLogs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Log.i(TAG, "getParcebleExtra");
        MyObject myObj = (MyObject) getIntent().getParcelableExtra(MyObject.class.getCanonicalName());
        Log.i(TAG, "myObj" + myObj.s + ", " + myObj.i);
    }
}

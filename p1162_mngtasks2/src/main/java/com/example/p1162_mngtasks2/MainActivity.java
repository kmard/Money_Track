package com.example.p1162_mngtasks2;

import android.app.ActivityManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "myLogs";
    List<ActivityManager.RunningTaskInfo> list;
    ActivityManager am;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle(getResources().getString(R.string.app_name) + " : " + getLocalClassName());
        am = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
    }

    public void onClick(View view) {
        startActivity(new Intent("mngtasks1_activity_c"));
    }

    public void onInfoClick(View view) {
        list = am.getRunningTasks(10);
        for (ActivityManager.RunningTaskInfo task : list) {
            if (task.baseActivity.flattenToShortString().startsWith("com.example.p1162_mngtasks2")) {
                Log.d(TAG, "------------------");
                Log.d(TAG, "Count: " + task.numActivities);
                Log.d(TAG, "Root: " + task.baseActivity.flattenToShortString());
                Log.d(TAG, "Top: " + task.topActivity.flattenToShortString());
            }
        }
    }

}

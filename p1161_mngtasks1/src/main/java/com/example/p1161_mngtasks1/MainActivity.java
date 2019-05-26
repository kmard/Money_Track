package com.example.p1161_mngtasks1;

import android.app.ActivityManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import java.util.List;

public abstract class MainActivity extends AppCompatActivity {

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

    public void onInfoClick(View v) {
        list = am.getRunningTasks(10);
        for (ActivityManager.RunningTaskInfo task : list) {
            if (task.baseActivity.flattenToShortString().startsWith("com.example.p1161_mngtasks1")) {
                Log.i(TAG, "------------------- ");
                Log.i(TAG, "Count : " + task.numActivities);
                Log.i(TAG, "Root : " + task.baseActivity.flattenToShortString());
                Log.i(TAG, "Top : " + task.topActivity.flattenToShortString());
            }
        }
    }

    abstract public void onClick(View v);

}

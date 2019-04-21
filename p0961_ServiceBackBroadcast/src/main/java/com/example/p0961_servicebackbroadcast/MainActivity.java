package com.example.p0961_servicebackbroadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public final static int STATUS_START = 100;
    public final static int STATUS_FINISH = 200;
    public final static String PARAM_TIME = "time";
    public final static String PARAM_TASK = "task";
    public final static String PARAM_RESULT = "result";
    public final static String PARAM_STATUS = "status";
    public final static String BROADCAST_ACTION = "com.example.p0961_servicebackbroadcast";
    private static final String TAG = "myLogs";
    final int TASK1_CODE = 1;
    final int TASK2_CODE = 2;
    final int TASK3_CODE = 3;
    TextView tvTask1;
    TextView tvTask2;
    TextView tvTask3;
    BroadcastReceiver br;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvTask1 = findViewById(R.id.tvTask1);
        tvTask1.setText("Task1");
        tvTask2 = findViewById(R.id.tvTask2);
        tvTask1.setText("Task2");
        tvTask3 = findViewById(R.id.tvTask3);
        tvTask1.setText("Task3");

        //create broadcast receiver
        br = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                int task = intent.getIntExtra(PARAM_TASK, 0);
                int status = intent.getIntExtra(PARAM_STATUS, 0);
                Log.i(TAG, "onReceive: task " + task + " , status" + status);

                //catch message about start task
                if (status == STATUS_START) {
                    switch (task) {
                        case TASK1_CODE:
                            tvTask1.setText("Task1 start");
                            break;
                        case TASK2_CODE:
                            tvTask2.setText("Task2 start");
                            break;
                        case TASK3_CODE:
                            tvTask3.setText("Task3 start");
                            break;
                    }
                }

                //catch message about finisjed tasks
                if (status == STATUS_FINISH) {
                    int result = intent.getIntExtra(PARAM_RESULT, 0);
                    switch (task) {
                        case TASK1_CODE:
                            tvTask1.setText("Task1 finish, result = " + result);
                            break;
                        case TASK2_CODE:
                            tvTask2.setText("Task2 finish, result = " + result);
                            break;
                        case TASK3_CODE:
                            tvTask3.setText("Task3 finish, result = " + result);
                            break;
                    }
                }
            }
        };
        //create filtr for Broadcastreceiver
        IntentFilter intFilt = new IntentFilter(BROADCAST_ACTION);
        //register BroadCastReceiver
        registerReceiver(br, intFilt);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //turn off BroadcastReceiver
        unregisterReceiver(br);
    }

    public void onClickStart(View v) {

        Intent intent;

        //create intent for colling service and put them some param
        intent = new Intent(this, MyService.class).putExtra(PARAM_TIME, 7).putExtra(PARAM_TASK, TASK1_CODE);
        //start servise
        startService(intent);

        intent = new Intent(this, MyService.class).putExtra(PARAM_TIME, 4).putExtra(PARAM_TASK, TASK2_CODE);
        startService(intent);

        intent = new Intent(this, MyService.class).putExtra(PARAM_TIME, 6).putExtra(PARAM_TASK, TASK3_CODE);
        startService(intent);

    }
}

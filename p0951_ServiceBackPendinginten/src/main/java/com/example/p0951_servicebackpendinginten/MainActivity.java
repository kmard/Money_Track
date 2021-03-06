package com.example.p0951_servicebackpendinginten;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "myLogs";

    final int TASK1_CODE = 1;
    final int TASK2_CODE = 2;
    final int TASK3_CODE = 3;

    public final static int STATUS_START = 100;
    public final static int STATUS_FINISH = 200;

    public final static String PARAM_TIME = "time";
    public final static String PARAM_PINTENT = "pendingIntent";
    public final static String PARAM_RESULT = "result";

    TextView tvTask1;
    TextView tvTask2;
    TextView tvTask3;

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
    }

    public void onClickStart(View v) {

        PendingIntent pi;
        Intent intent;

        //Create PendingIntent for Task1
        pi = createPendingResult(TASK1_CODE, null, 0);
        //Create intent for colling servise, put there param time and created PendingIntent
        intent = new Intent(this, MyService.class).putExtra(PARAM_TIME, 7).putExtra(PARAM_PINTENT, pi);
        //start service
        startService(intent);

        pi = createPendingResult(TASK2_CODE, null, 0);
        intent = new Intent(this, MyService.class).putExtra(PARAM_TIME, 4).putExtra(PARAM_PINTENT, pi);
        startService(intent);

        pi = createPendingResult(TASK3_CODE, null, 0);
        intent = new Intent(this, MyService.class).putExtra(PARAM_TIME, 5).putExtra(PARAM_PINTENT, pi);
        startService(intent);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Log.i(TAG, "requestCode = " + requestCode + ", resultCode = " + requestCode);

        //catch message about starting task
        if (resultCode == STATUS_START) {
            switch (requestCode) {
                case TASK1_CODE:
                    tvTask1.setText("Task1 start");
                    break;
                case TASK2_CODE:
                    tvTask2.setText("Task2 start");
                    break;
                case TASK3_CODE:
                    tvTask3.setText("Task3 start");
            }
        }

        //catch message when task finished
        if (requestCode == STATUS_FINISH) {
            int result = data.getIntExtra(PARAM_RESULT, 0);
            switch (requestCode) {
                case TASK1_CODE:
                    tvTask1.setText("Task1 finish, result = " + result);
                    break;
                case TASK2_CODE:
                    tvTask1.setText("Task2 finish, result = " + result);
                    break;
                case TASK3_CODE:
                    tvTask1.setText("Task3 finish, result = " + result);
                    break;
            }
        }
    }
}

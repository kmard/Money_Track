package com.example.p0911_asynctaskrotate;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "myLogs";

    MyTask mt;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(TAG, "onCreate: " + this.hashCode());

        tv = findViewById(R.id.tv);

//        mt = new MyTask();
        mt = (MyTask) getLastNonConfigurationInstance();
        if (mt == null) {
            mt = new MyTask();
            mt.execute();
        }
        mt.link(this);
        Log.i(TAG, "Create myTask: " + mt.hashCode());
        //mt.execute();
    }

    @Override
    public Object onRetainCustomNonConfigurationInstance() {

        mt.unLink();
        //return mt;
        return super.onRetainCustomNonConfigurationInstance();
    }


    private class MyTask extends AsyncTask<String, Integer, Void> {

        MainActivity activity;

        //get reference
        void link(MainActivity act) {
            activity = act;
        }

        //update reference
        void unLink() {
            activity = null;
        }

        @Override
        protected Void doInBackground(String... params) {

            try {
                for (int i = 1; i <= 10; i++) {
                    TimeUnit.SECONDS.sleep(1);
                    publishProgress(i);
                    Log.i(TAG, "i =  " + i
                            + " ,MyTask: " + this.hashCode()
                            + " , MainActivity: " + MainActivity.this.hashCode());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            tv.setText(" i = " + values[0]);
        }
    }
}

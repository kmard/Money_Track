package com.example.p0891_asynctaskcancel;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "myLogs";

    MyTask mt;
    TextView tvInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvInfo = findViewById(R.id.tvInfo);

    }

    public void onclick(View v) {
        switch (v.getId()) {
            case R.id.btnStart:
                mt = new MyTask();
                mt.execute();
                break;
            case R.id.btnCancel:
                cancelTask();
                break;
            default:
                break;
        }
    }

    private void cancelTask() {
        if (mt == null) return;
        Log.i(TAG, "cancel result : " + mt.cancel(false));
//        Log.i(TAG, "cancel result : "+mt.cancel(true));
    }


    private class MyTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Log.i(TAG, "Begin");
            tvInfo.setText("Begin");
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                for (int i = 0; i < 5; i++) {
                    TimeUnit.SECONDS.sleep(1);
                    //  if(isCancelled()) return  null;
                    Log.i(TAG, "isCanceled : " + isCancelled());
                }
            } catch (Exception e) {
                Log.i(TAG, "Interrupted");
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            tvInfo.setText("End");
            Log.i(TAG, "End");
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
            tvInfo.setText("Cancel");
            Log.i(TAG, "Cancell ");
        }
    }



}

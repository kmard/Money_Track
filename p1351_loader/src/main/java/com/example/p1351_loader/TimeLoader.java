package com.example.p1351_loader;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.Loader;
import android.text.TextUtils;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class TimeLoader extends Loader<String> {

    public final static String ARGS_TIME_FORMAT = "time_format";
    public final static String TIME_FORMAT_SHORT = "h:mm:ss a";
    public final static String TIME_FORMAT_LONG = "yyyy.MM.dd G 'at' HH:mm:ss";
    private static final String TAG = "myLogs";
    final int PAUSE = 10;
    GetTimeTask getTimeTask;
    String format;

    public TimeLoader(@NonNull Context context, Bundle args) {
        super(context);
        Log.i(TAG, hashCode() + " create TimeLoader");
        if (args != null)
            format = args.getString(ARGS_TIME_FORMAT);
        if (TextUtils.isEmpty(format))
            format = TIME_FORMAT_SHORT;
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        Log.i(TAG, hashCode() + " onStartLoading: ");
    }

    @Override
    protected void onStopLoading() {
        super.onStopLoading();
        Log.i(TAG, hashCode() + " onStopLoading: ");
    }

    @Override
    protected void onForceLoad() {
        super.onForceLoad();
        Log.i(TAG, hashCode() + " onForceLoad: ");
        if (getTimeTask != null)
            getTimeTask.cancel(true);
        getTimeTask = new GetTimeTask();
        getTimeTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, format);
    }

    @Override
    protected void onAbandon() {
        super.onAbandon();
        Log.i(TAG, hashCode() + " onAbandon: ");
    }

    @Override
    protected void onReset() {
        super.onReset();
        Log.i(TAG, hashCode() + " onReset: ");
    }

    void getResultFromTask(String result) {
        deliverResult(result);
    }


    private class GetTimeTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {

            Log.i(TAG, TimeLoader.this.hashCode() + " doInBackground: ");
            try {
                TimeUnit.SECONDS.sleep(PAUSE);
            } catch (InterruptedException e) {
                return null;
            }

            SimpleDateFormat sdf = new SimpleDateFormat(params[0],
                    Locale.getDefault());
            return sdf.format(new Date());
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            Log.i(TAG, TimeLoader.this.hashCode() + " onPostExecute" + result);
            getResultFromTask(result);
        }
    }
}

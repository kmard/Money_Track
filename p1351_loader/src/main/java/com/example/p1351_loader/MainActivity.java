package com.example.p1351_loader;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String> {

    static final int LOADER_TIME_ID = 1;
    private static final String TAG = "myLogs";
    static int lastCheckedId = 0;
    TextView tvTime;
    RadioGroup rgTimeFormat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvTime = (TextView) findViewById(R.id.tvTime);
        rgTimeFormat = (RadioGroup) findViewById(R.id.rgTimeFormat);

        Bundle bndl = new Bundle();
        bndl.putString(TimeLoader.ARGS_TIME_FORMAT, getTimeFormat());
        getLoaderManager().initLoader(LOADER_TIME_ID, bndl, (android.app.LoaderManager.LoaderCallbacks<Object>) this);
        lastCheckedId = rgTimeFormat.getCheckedRadioButtonId();
    }

    @Override
    public Loader<String> onCreateLoader(int id, Bundle args) {
        Loader<String> loader = null;
        if (id == LOADER_TIME_ID) {
            loader = new TimeLoader(this, args);
            Log.d(TAG, "onCreateLoader: " + loader.hashCode());
        }
        return loader;
    }


    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String result) {
        Log.i(TAG, "onLoadFinished: " + loader.hashCode()
                + ", result = " + result);
        tvTime.setText(result);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {

        Log.i(TAG, "onLoaderReset: " + loader.hashCode());
    }



    public void getTimeClick(View view) {
        android.content.Loader<Object> loader;

        int id = rgTimeFormat.getCheckedRadioButtonId();
        if (id == lastCheckedId) {
            loader = getLoaderManager().getLoader(LOADER_TIME_ID);
        } else {
            Bundle bndl = new Bundle();
            bndl.putString(TimeLoader.ARGS_TIME_FORMAT, getTimeFormat());
            loader = getLoaderManager().restartLoader(LOADER_TIME_ID, bndl, (android.app.LoaderManager.LoaderCallbacks<Object>) this);
            lastCheckedId = id;
        }
        loader.forceLoad();
    }

    String getTimeFormat() {
        String result = TimeLoader.TIME_FORMAT_SHORT;
        switch (rgTimeFormat.getCheckedRadioButtonId()) {
            case R.id.rdShort:
                result = TimeLoader.TIME_FORMAT_SHORT;
                break;
            case R.id.rdLong:
                result = TimeLoader.TIME_FORMAT_LONG;
                break;
        }
        return result;
    }

    public void observerClick(View view) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}

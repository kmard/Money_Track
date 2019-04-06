package com.example.p0841_handlerrunnable;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "myLogs";
    final int max = 100;
    ProgressBar pbCount;
    TextView tvInfo;
    CheckBox chbInfo;
    int cnt;
    Handler h;
    //Update Progressbar
    Runnable updateProgress = new Runnable() {
        @Override
        public void run() {
            pbCount.setProgress(cnt);
        }
    };
    //show information
    Runnable showInfo = new Runnable() {
        @Override
        public void run() {
            Log.i(TAG, "showInfo");
            tvInfo.setText("Count =  " + cnt);
            //plane the same after 1000 msec
            h.postDelayed(showInfo, 1000);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        h = new Handler();

        pbCount = (ProgressBar) findViewById(R.id.pbCount);
        pbCount.setMax(max);
        pbCount.setProgress(0);

        tvInfo = (TextView) findViewById(R.id.tvInfo);

        chbInfo = (CheckBox) findViewById(R.id.chbInfo);
        chbInfo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    tvInfo.setVisibility(View.VISIBLE);
                    //show information
                    h.post(showInfo);
                } else {
                    tvInfo.setVisibility(View.GONE);
                    //delay show information
                    h.removeCallbacks(showInfo);
                }
            }
        });

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    for (cnt = 1; cnt < max; cnt++) {
                        TimeUnit.MILLISECONDS.sleep(100);
                        //update progressBar
                        h.post(updateProgress);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();
    }

}

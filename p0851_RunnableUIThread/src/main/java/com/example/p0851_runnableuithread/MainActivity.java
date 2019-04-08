package com.example.p0851_runnableuithread;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "myLogs";
    TextView tvInfo;
    Runnable runn1 = new Runnable() {
        @Override
        public void run() {
            tvInfo.setText("runn1");
        }
    };
    Runnable runn2 = new Runnable() {
        @Override
        public void run() {
            tvInfo.setText("runn2");
        }
    };
    Runnable runn3 = new Runnable() {
        @Override
        public void run() {
            tvInfo.setText("runn3");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvInfo = (TextView) findViewById(R.id.tvInfo);

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    TimeUnit.SECONDS.sleep(2);
                    runOnUiThread(runn1);
                    TimeUnit.SECONDS.sleep(1);
                    tvInfo.postDelayed(runn3, 2000);
                    tvInfo.post(runn2);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();
    }


}

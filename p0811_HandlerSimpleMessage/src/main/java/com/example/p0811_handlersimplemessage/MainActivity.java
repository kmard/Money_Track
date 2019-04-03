package com.example.p0811_handlersimplemessage;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "myLogs";

    final int STATUS_NONE = 0; //not connection
    final int STATUS_CONNECTING = 1;//connecting
    final int STATUS_CONNECTED = 2; //connected

    Handler h;

    TextView tvStatus;
    ProgressBar pbConnector;
    Button btnConnect;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvStatus = (TextView) findViewById(R.id.tvStatus);
        pbConnector = (ProgressBar) findViewById(R.id.pbConnect);
        btnConnect = (Button) findViewById(R.id.btnConnect);

        h = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case STATUS_NONE:
                        btnConnect.setEnabled(true);
                        tvStatus.setText("Not connected");
                        break;
                    case STATUS_CONNECTING:
                        btnConnect.setEnabled(true);
                        pbConnector.setVisibility(View.VISIBLE);
                        tvStatus.setText("Connecting");
                        break;
                    case STATUS_CONNECTED:
                        pbConnector.setVisibility(View.GONE);
                        tvStatus.setText("Connected");
                        break;
                }
            }
        };
        h.sendEmptyMessage(STATUS_NONE);
    }

    public void onclick(View v) {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //install connection
                    h.sendEmptyMessage(STATUS_CONNECTING);
                    TimeUnit.SECONDS.sleep(2);

                    //installed
                    h.sendEmptyMessage(STATUS_CONNECTED);

                    //processing some work
                    TimeUnit.SECONDS.sleep(3);

                    //close connection
                    h.sendEmptyMessage(STATUS_NONE);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();
    }
}

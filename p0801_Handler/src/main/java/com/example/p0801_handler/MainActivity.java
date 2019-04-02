package com.example.p0801_handler;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "myLogs";

    Handler h;
    TextView tvInfo;
    Button btnStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvInfo = (TextView) findViewById(R.id.tvInfo);
        btnStart = (Button) findViewById(R.id.btnStart);
        h = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                //upload TextView
                tvInfo.setText("uploadFiles : " + msg.what);
                if (msg.what == 10) btnStart.setEnabled(true);
                //super.handleMessage(msg);
            }

            ;
        };

    }

    public void onclick(View view) {
//        switch (view.getId()){
//            case R.id.btnStart:
//                for(int i = 0; i <= 10; i++){
//                    //Long process
//                    downLoadFile();
//                    //update textView
//                    tvInfo.setText("upLoad files : "+i);
//                    //write to log
//                    Log.i(TAG, "upLoad files : "+i);
//                }
//                break;
//            case  R.id.btnTest:
//                Log.i(TAG, "btnTest");
//             default:
//                 break;
//        }

//        switch (view.getId()) {
//            case R.id.btnStart:
//                Thread t = new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        for (int i = 0; i <= 10; i++) {
//                            //long process
//                            downLoadFile();
//                            //update textView
//                            tvInfo.setText("upLoad Files : " + i);
//                            //write to log
//                            Log.i(TAG, "i =  " + i);
//                        }
//                    }
//                });
//                t.start();
//                break;
//            case R.id.btnTest:
//                Log.i(TAG, "Test ");
//                break;
//            default:
//                break;
//        }

        switch (view.getId()) {
            case R.id.btnStart:
                btnStart.setEnabled(false);
                Thread t = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i <= 10; i++) {
                            //long process
                            downLoadFile();
                            h.sendEmptyMessage(i);
                            //write to log
                            Log.i(TAG, "I = " + i);
                        }
                    }
                });
                t.start();
                break;
            case R.id.btnTest:
                Log.i(TAG, "Test ");
                break;
            default:
                break;
        }
    }

    private void downLoadFile() {
        //pause on 1 sec
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}

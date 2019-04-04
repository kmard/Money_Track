package com.example.handleradvmessage;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "myLogs";

    final int STATUS_NONE = 0; //not connection
    final int STATUS_CONNECTING = 1;//connecting
    final int STATUS_CONNECTED = 2;//connected
    final int STATUS_DOWNLOAD_START = 3;//upLoad is starting
    final int STATUS_DOWNLOAD_FILE = 4;// file is load
    final int STATUS_DOWNLOAD_END = 5;//upLoad is finish
    final int STATUS_DOWNLOAD_NONE = 6;//There is nothing to upLoad

    Handler h;

    TextView tvStatus;
    ProgressBar pbDownload;
    Button btnConnect;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvStatus = (TextView) findViewById(R.id.tvStatus);
        pbDownload = (ProgressBar) findViewById(R.id.pbDownload);
        btnConnect = (Button) findViewById(R.id.btnConnect);

        h = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                //super.handleMessage(msg);
                switch (msg.what) {
                    case STATUS_NONE:
                        btnConnect.setEnabled(true);
                        tvStatus.setText("Not connected");
                        pbDownload.setVisibility(View.GONE);
                        break;
                    case STATUS_CONNECTING:
                        btnConnect.setEnabled(false);
                        tvStatus.setText("Connecting");
                        break;
                    case STATUS_CONNECTED:
                        tvStatus.setText("Connected");
                        break;
                    case STATUS_DOWNLOAD_START:
                        tvStatus.setText("Start download " + msg.arg1 + "files");
                        pbDownload.setMax(msg.arg1);
                        pbDownload.setProgress(0);
                        pbDownload.setVisibility(View.VISIBLE);
                        break;
                    case STATUS_DOWNLOAD_FILE:
                        tvStatus.setText("Downloading. Left " + msg.arg2 + " files");
                        pbDownload.setProgress(msg.arg1);
                        saveFile((byte[]) msg.obj);
                        break;
                    case STATUS_DOWNLOAD_END:
                        tvStatus.setText("DownLoad complete ! ");
                        break;
                    case STATUS_DOWNLOAD_NONE:
                        tvStatus.setText("No files for download");
                        break;
                }
            }
        };
        h.sendEmptyMessage(STATUS_NONE);
    }


    public void onclick(View v) {

        Thread t = new Thread(new Runnable() {

            Message msg;
            byte[] file;
            Random rand = new Random();

            @Override
            public void run() {
                try {

                    // install connection
                    h.sendEmptyMessage(STATUS_CONNECTING);
                    TimeUnit.SECONDS.sleep(1);

                    // connection is setting
                    h.sendEmptyMessage(STATUS_CONNECTED);

                    // define count files
                    TimeUnit.SECONDS.sleep(1);
                    int filesCount = rand.nextInt(5);

                    if (filesCount == 0) {
                        // asks that files not
                        h.sendEmptyMessage(STATUS_DOWNLOAD_NONE);
                        // and disconect
                        TimeUnit.MILLISECONDS.sleep(1500);
                        h.sendEmptyMessage(STATUS_NONE);
                        return;
                    }

                    // upLoad is begin
                    // create message with information about count of files
                    msg = h.obtainMessage(STATUS_DOWNLOAD_START, filesCount, 0);
                    // send
                    h.sendMessage(msg);

                    for (int i = 1; i <= filesCount; i++) {
                        // uploading file
                        file = downloadFile();
                        //create message with information about order number file's
                        //counts stayed and same file
                        msg = h.obtainMessage(STATUS_DOWNLOAD_FILE, i,
                                filesCount - i, file);
                        // send
                        h.sendMessage(msg);
                    }

                    // upload is end
                    h.sendEmptyMessage(STATUS_DOWNLOAD_END);

                    // disconnect
                    TimeUnit.MILLISECONDS.sleep(1500);
                    h.sendEmptyMessage(STATUS_NONE);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();
    }

    private byte[] downloadFile() throws InterruptedException {

        TimeUnit.SECONDS.sleep(2);
        return new byte[1024];
    }

    void saveFile(byte[] file) {

    }

}

//https://developer.android.com/reference/android/os/Handler.html#pubmethods
package com.example.p0611_alertdialogprepare;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import java.text.SimpleDateFormat;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "myLog";
    final int DIALOG = 1;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd G 'at' HH:mm:ss z");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view) {
        showDialog(DIALOG);
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        Log.i(TAG, "onCreateDialog: ");
        if (id == DIALOG) {
            AlertDialog.Builder adb = new AlertDialog.Builder(this);
            adb.setTitle("Current time");
            adb.setMessage(sdf.format(System.currentTimeMillis()));
            return adb.create();
        }
        return super.onCreateDialog(id);
    }

    @Override
    protected void onPrepareDialog(int id, Dialog dialog) {
        super.onPrepareDialog(id, dialog);
        Log.i(TAG, "onPrepareDialog: ");
        if (id == DIALOG) {
            ((AlertDialog) dialog).setMessage(sdf.format(System.currentTimeMillis()));
        }
    }
}

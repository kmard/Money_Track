package com.example.p0661_alertdialogoperations;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "myLogs";
    final int DIALOG = 1;

    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    void method1() {
        //var1
        //dialog.dismiss();
        //var2
        // dialog.cancel();
        //var3
        //dialog.hide();

        dismissDialog(DIALOG);
    }

    void method2() {
        showDialog(DIALOG);
    }

    public void onClick(View view) {
        showDialog(DIALOG);

        Handler h = new Handler();

        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                method1();
            }
        }, 2000);

        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                method2();
            }
        }, 4000);

    }

    @Override
    protected Dialog onCreateDialog(int id) {
        if (id == DIALOG) {
            Log.i(TAG, "Create");
            AlertDialog.Builder adb = new AlertDialog.Builder(this);
            adb.setTitle("Title");
            adb.setMessage("Message");
            adb.setPositiveButton("OK", null);
            dialog = adb.create();


            //processing display
            dialog.setOnShowListener(new DialogInterface.OnShowListener() {
                @Override
                public void onShow(DialogInterface dialog) {
                    Log.i(TAG, "Show");
                }
            });

            //processing cancel
            dialog.setOnCancelListener(new Dialog.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialog) {
                    Log.i(TAG, "Cancel ");
                }
            });

            //processing  closing
            dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialog) {
                    Log.i(TAG, "Dismiss ");
                }
            });
            return dialog;
        }
        return super.onCreateDialog(id);
    }
}

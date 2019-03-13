package com.example.p0601_alertdialogsimple;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    final int DIALOG_EXIT = 1;
    DialogInterface.OnClickListener myClickListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            switch (which) {
                case Dialog.BUTTON_POSITIVE: {
                    saveData();
                    finish();
                    break;
                }
                case Dialog.BUTTON_NEGATIVE: {
                    finish();
                    break;
                }
                case Dialog.BUTTON_NEUTRAL: {
                    break;
                }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view) {
        //colling dialog
        showDialog(DIALOG_EXIT);
    }

    @Override
    protected Dialog onCreateDialog(int id) {

        if (id == DIALOG_EXIT) {
            AlertDialog.Builder adb = new AlertDialog.Builder(this);
            //title
            adb.setTitle(R.string.exit);
            //message
            adb.setMessage(R.string.save_data);
            //icon
            adb.setIcon(android.R.drawable.ic_dialog_info);
            //button positive answer
            adb.setPositiveButton(R.string.yes, myClickListener);
            //button negative answer
            adb.setNegativeButton(R.string.no, myClickListener);
            //button neutral answer
            adb.setNeutralButton(R.string.cancel, myClickListener);
            //create dialog
            return adb.create();
        }
        return super.onCreateDialog(id);
    }

    void saveData() {
        Toast.makeText(this, R.string.saved, Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onPrepareDialog(int id, Dialog dialog) {
        super.onPrepareDialog(id, dialog);
    }

    public void onBackPressed() {
        showDialog(DIALOG_EXIT);
    }
}











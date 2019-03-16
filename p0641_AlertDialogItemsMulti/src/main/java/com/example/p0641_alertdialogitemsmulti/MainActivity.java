package com.example.p0641_alertdialogitemsmulti;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "myLogs";

    final int DIALOG_ITEMS = 1;
    final int DIALOG_CURSOR = 3;
    DB db;
    Cursor cursor;

    String data[] = {"one", "two", "three", "four"};
    boolean chkd[] = {false, false, false, false};
    //processing massive
    android.content.DialogInterface.OnMultiChoiceClickListener myItemsMultiClickListener = new android.content.DialogInterface.OnMultiChoiceClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which, boolean isChecked) {
            Log.i(TAG, "which = " + which + " , isChecked = " + isChecked);
        }
    };
    //processing cursor
    DialogInterface.OnMultiChoiceClickListener myCursorMultiClickListener = new DialogInterface.OnMultiChoiceClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which, boolean isChecked) {
            ListView lv = ((AlertDialog) dialog).getListView();
            Log.i(TAG, "which " + which + " , isChecked = " + isChecked);
            db.changeRec(which, isChecked);
            cursor.requery();
        }
    };
    //proseccing push the button
    DialogInterface.OnClickListener myBtnClickListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            SparseBooleanArray sbArray = ((AlertDialog) dialog).getListView().getCheckedItemPositions();
            for (int i = 0; i < sbArray.size(); i++) {
                int key = sbArray.keyAt(i);
                if (sbArray.get(key)) {
                    Log.i(TAG, "checked: " + key);
                }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //open connection to DB
        db = new DB(this);
        db.open();
        cursor = db.getAllData();
        startManagingCursor(cursor);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnItems:
                showDialog(DIALOG_ITEMS);
                break;
            case R.id.btnCursor:
                showDialog(DIALOG_CURSOR);
                break;
            default:
                break;
        }
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        AlertDialog.Builder adb = new AlertDialog.Builder(this);
        switch (id) {

            case DIALOG_ITEMS:
                adb.setTitle(R.string.items);
                adb.setMultiChoiceItems(data, chkd, myItemsMultiClickListener);
                break;
            case DIALOG_CURSOR:
                adb.setTitle(R.string.cursor);
                adb.setMultiChoiceItems(cursor, DB.COLUMN_CHK, DB.COLUMN_TXT, myCursorMultiClickListener);
                break;
        }

        adb.setPositiveButton("ok", myBtnClickListener);
        return adb.create();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onPrepareDialog(int id, Dialog dialog, Bundle args) {
//        ((AlertDialog) dialog).getListView().setItemChecked(2,true);
    }
}

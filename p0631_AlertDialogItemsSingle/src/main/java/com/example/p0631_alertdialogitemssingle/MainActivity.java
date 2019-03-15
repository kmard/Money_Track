package com.example.p0631_alertdialogitemssingle;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "myLogs";

    final int DIALOG_ITEMS = 1;
    final int DIALOG_ADAPTER = 2;
    final int DIALOG_CURSOR = 3;
    DB db;
    Cursor cursor;

    String[] data = {"one", "two", "three", "four"};
    DialogInterface.OnClickListener myClickListner = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            ListView lv = ((AlertDialog) dialog).getListView();
            if (which == Dialog.BUTTON_POSITIVE) {
                //input to log position selected element's
                Log.i(TAG, "pos " + lv.getCheckedItemPosition());
            } else {
                //input to log position pushing element
                Log.i(TAG, "which = " + which);
            }

        }
    };

    public MainActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //open connection
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
            case R.id.btnAdapter:
                showDialog(DIALOG_ADAPTER);
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
                adb.setSingleChoiceItems(data, -1, myClickListner);
                break;

            //adapter
            case DIALOG_ADAPTER:
                adb.setTitle(R.string.adapter);
                ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.select_dialog_singlechoice, data);
                adb.setSingleChoiceItems(adapter, -1, myClickListner);
                break;

            //cursor
            case DIALOG_CURSOR:
                adb.setTitle(R.string.cursor);
                adb.setSingleChoiceItems(cursor, -1, DB.COLUMN_TXT, myClickListner);
                break;
        }
        adb.setPositiveButton(getString(R.string.ok), myClickListner);

        return adb.create();
    }

    @Override
    protected void onPrepareDialog(int id, Dialog dialog) {
        //((AlertDialog) dialog).getListView().setItemChecked(2,true);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        db.close();
    }
}

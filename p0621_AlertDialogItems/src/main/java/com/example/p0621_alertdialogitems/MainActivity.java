package com.example.p0621_alertdialogitems;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "myLog";

    final int DIALOG_ITEMS = 1;
    final int DIALOG_ADAPTER = 2;
    final int DIALOG_CURSOR = 3;
    int cnt = 0;
    DB db;
    Cursor cursor;

    String[] data = {"one", "two", "three", "four"};
    DialogInterface.OnClickListener myClickListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            //out put in log position pushed element
            Log.i(TAG, "which " + which);
        }
    };

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
        changeCount();
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
        }
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        AlertDialog.Builder adb = new AlertDialog.Builder(this);
        switch (id) {
            //massive
            case DIALOG_ITEMS:
                adb.setTitle(R.string.items);
                adb.setItems(data, myClickListener);
                break;
            //adapter
            case DIALOG_ADAPTER:
                adb.setTitle(R.string.adapter);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.select_dialog_item, data);
                adb.setAdapter(adapter, myClickListener);
                break;
            //cursor
            case DIALOG_CURSOR:
                adb.setTitle(R.string.cursor);
                adb.setCursor(cursor, myClickListener, DB.COLUMN_TXT);
                break;
        }
        return adb.create();
    }

    ;

    @Override
    protected void onPrepareDialog(int id, Dialog dialog) {
        //receive access to adapter
        AlertDialog aDialog = (AlertDialog) dialog;
        ListAdapter lAdapter = aDialog.getListView().getAdapter();

        switch (id) {
            case DIALOG_ITEMS:

            case DIALOG_ADAPTER:
                //check
                if (lAdapter instanceof BaseAdapter) {
                    //check and calling a new value
                    BaseAdapter bAdapter = (BaseAdapter) lAdapter;
                    bAdapter.notifyDataSetChanged();
                }
                break;
            case DIALOG_CURSOR:
                break;
            default:
                break;
        }
    }

    //change counter
    private void changeCount() {
        cnt++;
        //update massive
        data[3] = String.valueOf(cnt);
        //update BD
        db.changeRec(4, String.valueOf(cnt));
        cursor.requery();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        db.close();
    }
}

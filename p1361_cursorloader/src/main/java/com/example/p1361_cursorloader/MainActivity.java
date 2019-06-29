package com.example.p1361_cursorloader;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import java.util.concurrent.TimeUnit;

public class MainActivity extends FragmentActivity implements LoaderManager.LoaderCallbacks {

    private static final int CM_DELETE_ID = 1;
    ListView lvData;
    DB db;
    SimpleCursorAdapter scAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //open connection to BD
        db = new DB(this);
        db.open();

        //create column conformity
        String[] from = new String[] {DB.COLUMN_IMG, DB.COLUMN_TXT};
        int[] to = new int[] {R.id.ivImg, R.id.tvText};

        //create adapter and tune list
        scAdapter = new SimpleCursorAdapter(this, R.layout.activity_item, null, from, to, 0);
        lvData = (ListView) findViewById(R.id.lvData);
        lvData.setAdapter(scAdapter);

        //add context menu to list
        registerForContextMenu(lvData);

        //create loader for reading
        getSupportLoaderManager().initLoader(0, null, this);
    }

    public void onButtonClick(View view) {
        //add record
        db.addRec("sometext " + (scAdapter.getCount() + 1), R.drawable.ic_launcher_background);
        //get new cursor with data
        getSupportLoaderManager().getLoader(0).forceLoad();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(0, CM_DELETE_ID, 0, R.string.delete_record);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        if (item.getItemId() == CM_DELETE_ID) {
            //get from point context menu data about of point list
            AdapterView.AdapterContextMenuInfo acmi = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
            //extract id record and delete record from DB
            db.delRec(acmi.id);
            //get new cursor with data
            getSupportLoaderManager().getLoader(0).forceLoad();
            return true;
        }
        return super.onContextItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //close connection
        db.close();
    }

    @NonNull
    @Override
    public Loader onCreateLoader(int i, @Nullable Bundle bundle) {
//        return null;
        return new MyCursorLoader(this, db);
    }

    @Override
    public void onLoadFinished(@NonNull Loader loader, Object cursor) {
        scAdapter.swapCursor((Cursor) cursor);
    }

    @Override
    public void onLoaderReset(@NonNull Loader loader) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }


    private class MyCursorLoader extends CursorLoader {

        DB db;

        public MyCursorLoader(Context context, DB db) {
            super(context);
            this.db = db;
        }

        @Override
        public Cursor loadInBackground() {

            Cursor cursor = db.getAllData();
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return cursor;
            //return super.loadInBackground();
        }
    }
}

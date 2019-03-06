package com.example.p0521_simplecursoradapter;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class MainActivity extends AppCompatActivity {

    private static final int CM_DELETE_ID = 1;
    ListView lvData;
    DB db;
    SimpleCursorAdapter scAdapter;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //open connection to the BD
        db = new DB(this);
        db.open();

        //get cursor
        cursor = db.getAllData();
        startManagingCursor(cursor);

        //create column conformity
        String[] from = {DB.COLUMN_IMG, DB.COLUMN_TXT};
        int[] to = {R.id.ivImg, R.id.tvText};

        //create adapter and connect lvData
        scAdapter = new SimpleCursorAdapter(this, R.layout.activity_item, cursor, from, to);
        lvData = (ListView) findViewById(R.id.lvData);
        lvData.setAdapter(scAdapter);

        //add contex menu
        registerForContextMenu(lvData);

    }

    public void onButtonClick(View view) {
        //add record
        db.addRec("sometext" + (cursor.getCount() + 1), R.mipmap.ic_launcher_round);
        //update cursor
        cursor.requery();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(0, CM_DELETE_ID, 0, R.string.delete_record);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if (item.getItemId() == CM_DELETE_ID) {
            //get id of elemet
            AdapterView.AdapterContextMenuInfo acmi = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
            //delete record
            db.delRec(acmi.id);
            //update cursor
            cursor.requery();
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
}

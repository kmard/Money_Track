package com.example.p1131_actionmode;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    ActionMode actionMode;
    private static final String TAG = "myLogs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.context,menu);
        return true;
        //return super.onCreateOptionsMenu(menu);
    }

    public void onClick(View v) {
        if(actionMode == null)
            actionMode = startActionMode(callback);
        else
            actionMode.finish();
    }

      private  ActionMode.Callback callback = new ActionMode.Callback() {
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            mode.getMenuInflater().inflate(R.menu.context,menu);
            return true;
            //return false;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            Log.i(TAG, "item : "+item.getTitle());
            return false;
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            Log.i(TAG, "onDestroyActionMode: ");
            actionMode = null;
        }
    };
}

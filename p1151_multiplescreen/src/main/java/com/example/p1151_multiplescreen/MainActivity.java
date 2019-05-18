package com.example.p1151_multiplescreen;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.FragmentActivity;

public class MainActivity extends FragmentActivity implements TitlesFragment.onItemClickListener {

    int position = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState != null)
            position = savedInstanceState.getInt("position");
        ShowDetails(position);
    }

    private void ShowDetails(int pos) {
        Details details = (Details) getSupportFragmentManager().findFragmentById(R.id.cont);
        if (details == null || details.getPosition() != pos) {
            details = Details.newInstance(pos);
            getSupportFragmentManager().beginTransaction().replace(R.id.cont, details).commit();
        }
    }

    @Override
    public void itemClick(int position) {
        this.position = position;
        ShowDetails(position);
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        outState.putInt("position", position);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }


}

package com.example.p_simple_intention;

import android.app.ListActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class list_view extends ListActivity implements AdapterView.OnItemClickListener {

     static final String EXTRA_SELECTED_STATION = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_list_view);
        final Resources r = getResources();

        String [] stationArray = r.getStringArray(R.array.Stations);

        ArrayAdapter<String> aa = new ArrayAdapter<String>(this,R.layout.activity_list_view,stationArray);

        setListAdapter(aa);

        ListView lv = getListView();

        lv.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        CharSequence stationSelected = ((TextView) view).getText();
        Intent intent = new Intent();
        intent.putExtra(EXTRA_SELECTED_STATION,stationSelected);
        setResult(RESULT_OK,intent);
        finish();
    }
}

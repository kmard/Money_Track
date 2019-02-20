package com.example.p_listviewsample;

import android.app.ListActivity;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class MainActivity extends ListActivity implements AdapterView.OnItemClickListener {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_main);

        final Resources r = getResources();

        String [] stationArray = r.getStringArray(R.array.Stations);

        ArrayAdapter<String> aa = new ArrayAdapter<String>(this,R.layout.activity_main,stationArray);

        setListAdapter(aa);

        ListView lv = getListView();


//        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//                CharSequence text = ((TextView) view).getText();
//                int duration  = Toast.LENGTH_SHORT;
//                Context contex = getApplicationContext();
//                Toast.makeText(contex, text, duration).show();
//            }
//        });

        lv.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//            CharSequence text = ((TextView) view).getText();
//            int duration = Toast.LENGTH_SHORT;
//            Context contex = getApplicationContext();
//            Toast.makeText(contex, text, duration).show();
              Toast.makeText(this,((TextView) view).getText(),(int) 2).show();
    }
}

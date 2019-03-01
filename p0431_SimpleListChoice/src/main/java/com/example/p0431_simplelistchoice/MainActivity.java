package com.example.p0431_simplelistchoice;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "myLogs";

    ListView lvMain;
    String[] names;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvMain = (ListView) findViewById(R.id.lvMain);
        //put the selection mode for the List
       // lvMain.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        //multi choice
         lvMain.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        //create adapter using massiv from file :values\strings sigleChoice
        //ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.names,android.R.layout.simple_list_item_single_choice);

        //create adapter using massiv from file :values\strings multyChoice
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.names,android.R.layout.simple_list_item_multiple_choice);

        lvMain.setAdapter(adapter);

        Button btnChecked = (Button) findViewById(R.id.btnChecked);
        btnChecked.setOnClickListener(this);

        //receive massive from file res
        names = getResources().getStringArray(R.array.names);

    }

    @Override
    public void onClick(View v) {
        //SingleChoise
        //Log.i(TAG, "checked: "+names[lvMain.getCheckedItemPosition()]);

        //write to log selected elements
        Log.i(TAG, "Checked : ");
        SparseBooleanArray sbArray = lvMain.getCheckedItemPositions();
        for(int i = 0; i<sbArray.size();i++){
            int key = sbArray.keyAt(i);

            if(sbArray.get(key)){
                Log.i(TAG, names[key]);
            }
        }
    }
}

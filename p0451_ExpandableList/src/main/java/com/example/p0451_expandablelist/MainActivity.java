package com.example.p0451_expandablelist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ExpandableListView;
import android.widget.SimpleExpandableListAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    //name group
    String[] groups = new String[] {"HTC","Samsung","LG"};

    //name elements
    String[] phonesHTC = {"Sensation","Desire","Wildfire","Hero"};
    String[] phonesSams = {"Galaxy S II","Galaxy Nexus","Wave"};
    String[] phonesLG = {"Optimus","Optimus Link","Optimus Black","Optimus One"};

    //collections for group
    ArrayList<Map<String,String>> groupData;

    //collection for elements of one group
    ArrayList<Map<String,String>> childDataItem;

    //common collection for all items
    ArrayList<ArrayList<Map<String,String>>> childData;

    //List attribute group for one element
    Map<String,String> m;

    ExpandableListView elvMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //fill collectiongroup from massive with names group
        groupData = new ArrayList<Map<String,String>>();
           for(String group:groups){
               //fill list atribute for each group
               m = new HashMap<String, String>();
               m.put("groupName",group);  //name company
               groupData.add(m);
           }

           //list atributes group for reading
        String groupFrom[] = new String[] {"groupName"};
           //list ID view-elements, put in the atribute groups
        int groupTo[] = new int[]  {android.R.id.text1};

        //create collection for elements
        childData = new ArrayList<ArrayList<Map<String, String>>>();

        //create collection elements for first group
        childDataItem = new ArrayList<Map<String,String>>();
        //fill list atribute for each element
        for(String phone:phonesHTC){
            m = new HashMap<String,String>();
            m.put("phoneName",phone);     //name telephone
            childDataItem.add(m);
        }

        //add to collection of collections
        childData.add(childDataItem);

        //create collections elements for second group
        childDataItem = new ArrayList<Map<String,String>>();
        for(String phone: phonesSams){
            m = new HashMap<String, String>();
            m.put("phoneName",phone);
            childDataItem.add(m);
        }
        childData.add(childDataItem);

        //create collection elements for third group
        childDataItem = new ArrayList<Map<String, String>>();
        for(String phone: phonesLG){
            m = new HashMap<String, String>();
            m.put("phoneName",phone);
            childDataItem.add(m);
        }
        childData.add(childDataItem);

        //list atributs elements for reading
        String childFrom[] = new String[] {"phoneName"};
        //list Id view-elements, put in attribute elements
        int childTo[] = new int[] {android.R.id.text1};

        SimpleExpandableListAdapter adapter = new SimpleExpandableListAdapter(
                this,
                groupData,
                android.R.layout.simple_expandable_list_item_1,
                groupFrom,
                groupTo,
                childData,
                android.R.layout.simple_list_item_1,
                childFrom,
                childTo);

        elvMain = (ExpandableListView) findViewById(R.id.elvMain);
        elvMain.setAdapter(adapter);
    }
}

package com.example.p0461_expandablelistevents;

import android.content.Context;
import android.widget.SimpleExpandableListAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AdapterHelper {

    final String ATTR_GROUP_NAME = "groupName";
    final String ATTR_PHONE_NAME = "phoneName";

    //name's group
    String[] groups = {"HTC", "Samsung", "LG"};

    //name's elements
    String[] phonesHTC = {"Sensation", "Desire", "Wildfire", "Hero"};
    String[] phonesSams = {"Galaxy S II", "Galaxy Nexus", "Wave"};
    String[] phonesLG = {"Optimus", "Optimus Link", "Optimus Black", "Optimus One"};

    //collection for groups
    ArrayList<Map<String, String>> groupData;

    //collection elements for group
    ArrayList<Map<String, String>> childDataItem;

    //common collection for collections of elements
    ArrayList<ArrayList<Map<String, String>>> childData;
    //on total  result : childData = ArrayList<childDataItem>

    //List attribute group or elements
    Map<String, String> m;

    Context ctx;
    SimpleExpandableListAdapter adapter;

    AdapterHelper(Context _ctx) {
        ctx = _ctx;
    }

    SimpleExpandableListAdapter getAdapter() {

        //fill collection group from massive with value group
        groupData = new ArrayList<Map<String, String>>();

        for (String group : groups) {
            //fill list attribute for each group
            m = new HashMap<String, String>();
            m.put(ATTR_GROUP_NAME, group);  //name company
            groupData.add(m);
        }

        //list attribute group for reading
        String groupFrom[] = {ATTR_GROUP_NAME};
        //List ID view-elements, that input in groups
        int groupTo[] = {android.R.id.text1};

        //create collection for collection elements
        childData = new ArrayList<ArrayList<Map<String, String>>>();

        //create collection elements for first group
        childDataItem = new ArrayList<Map<String, String>>();
        //fill list attribute
        for (String phone : phonesHTC) {
            m = new HashMap<String, String>();
            m.put(ATTR_PHONE_NAME, phone); //name phone
            childDataItem.add(m);
        }
        //add to collection of collections
        childData.add(childDataItem);

        //create collections for second group
        childDataItem = new ArrayList<Map<String, String>>();
        for (String phone : phonesSams) {
            m = new HashMap<String, String>();
            m.put(ATTR_PHONE_NAME, phone);
            childDataItem.add(m);
        }
        childData.add(childDataItem);

        //create collections for third group
        childDataItem = new ArrayList<Map<String, String>>();
        for (String phone : phonesLG) {
            m = new HashMap<String, String>();
            m.put(ATTR_PHONE_NAME, phone);
            childDataItem.add(m);
        }
        childData.add(childDataItem);

        //list attribute elements for reading
        String childFrom[] = {ATTR_PHONE_NAME};
        //list ID view-element, that inputs elements attribute
        int childTo[] = {android.R.id.text1};

        adapter = new SimpleExpandableListAdapter(
                ctx,
                groupData,
                android.R.layout.simple_expandable_list_item_1,
                groupFrom,
                groupTo,
                childData,
                android.R.layout.simple_list_item_1,
                childFrom,
                childTo
        );

        return adapter;
    }

    String getGroupText(int groupPos) {
        return ((Map<String, String>) (adapter.getGroup(groupPos))).get(ATTR_GROUP_NAME);
    }

    String getChildText(int groupPos, int childPos) {
        return ((Map<String, String>) (adapter.getChild(groupPos, childPos))).get(ATTR_PHONE_NAME);
    }

    String getGroupChildText(int groupPos, int childPos) {
        return getGroupText(groupPos) + " " + getChildText(groupPos, childPos);
    }


}

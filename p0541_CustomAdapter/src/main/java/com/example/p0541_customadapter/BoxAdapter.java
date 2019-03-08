package com.example.p0541_customadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import java.util.ArrayList;

public class BoxAdapter extends BaseAdapter {

    Context ctx;
    LayoutInflater lInflater;
    ArrayList<Product> objects;
    //Processing for CheckBoxes
    CompoundButton.OnCheckedChangeListener myCheckChangeList = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            //change data about goods (in the box, or not in the box)
            getProduct((Integer) buttonView.getTag()).box = isChecked;
        }
    };

    public BoxAdapter(Context ctx, ArrayList<Product> objects) {
        this.ctx = ctx;
        this.objects = objects;
        lInflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    //count elements
    @Override
    public int getCount() {
        return objects.size();
    }

    //return position of element
    @Override
    public Object getItem(int position) {
        return objects.get(position);
    }

    //return ID position of element
    @Override
    public long getItemId(int position) {
        return position;
    }

    //
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //Use data, but don't use view
        View view = convertView;

        if (view == null) {
            view = lInflater.inflate(R.layout.activity_item, parent, false);
        }

        Product p = getProduct(position);

        //fill View of good which consist : name goods, price, paint
        ((TextView) view.findViewById(R.id.tvDescr)).setText(p.name);
        ((TextView) view.findViewById(R.id.tvPrice)).setText(p.price + " ");
        ((ImageView) view.findViewById(R.id.ivImage)).setImageResource(p.image);

        CheckBox cbBuy = (CheckBox) view.findViewById(R.id.cbBox);
        //add to checkBox processing
        cbBuy.setOnCheckedChangeListener(myCheckChangeList);
        //write position
        cbBuy.setTag(position);
        //fiil up data of goods : in box or not
        cbBuy.setChecked(p.box);

        return view;
    }

    //position goods
    private Product getProduct(int position) {
        return ((Product) getItem(position));
    }

    //Contents box
    ArrayList<Product> getBox() {
        ArrayList<Product> box = new ArrayList<Product>();
        for (Product p : objects) {
            //if in box
            if (p.box) {
                box.add(p);
            }
        }
        return box;
    }

}

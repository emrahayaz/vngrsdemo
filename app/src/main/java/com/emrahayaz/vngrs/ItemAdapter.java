package com.emrahayaz.vngrs;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by poseydon on 13.09.2015.
 */
public class ItemAdapter extends ArrayAdapter {
    private List<ItemHolder> items=new ArrayList<>();
    private Context context;
    public ItemAdapter(Context context, int resource, List<ItemHolder> objects) {
        super(context, resource, objects);
        this.context=context;
        this.items=objects;
    }
    public void setItems(ArrayList<ItemHolder> items){
        this.items=items;
        notifyDataSetChanged();

    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ItemHolder item = items.get(position);

        LayoutInflater inflater =
                (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.my_list_item, null);

        //ImageView image = (ImageView) view.findViewById(R.id.ivFlowerImage);
        //image.setImageResource(flower.getImageResource());

        TextView tv1 = (TextView) view.findViewById(R.id.list_item_text);
        TextView tv2 = (TextView) view.findViewById(R.id.list_item_text2);

        tv1.setText(item.name);
        tv2.setText(item.address);

        return view;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    public ItemHolder getItemAt(int id){
        if(id>=items.size()) return null;
        return items.get(id);
    }
}

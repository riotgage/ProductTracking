package com.example.pradnya.producttracking.Info;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import com.example.pradnya.producttracking.R;

import java.util.ArrayList;
import java.util.List;

public class InformationAdapter extends ArrayAdapter<info> {

    private  Context mContext;
    private int mResource;
    public InformationAdapter( Context context, int resource,ArrayList<info> objects) {
        super(context, resource, objects);
        mContext=context;
        mResource=resource;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String box_no=getItem(position).getBox_no();
        String cat=getItem(position).getCategory();

        info i =new info(box_no,cat);
        LayoutInflater inflater=LayoutInflater.from(mContext);
        convertView=inflater.inflate(mResource,parent,false);


        TextView box=convertView.findViewById(R.id.box_no_id);
        TextView category=convertView.findViewById(R.id.category);

        box.setText(box_no);
        category.setText(cat);

        return  convertView;

    }
}

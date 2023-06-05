package com.example.lab6_ph31259;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class SchoolAdapter extends BaseAdapter {

    ArrayList<School> list = new ArrayList<>();
    Context c;

    public SchoolAdapter(ArrayList<School> list, Context c) {
        this.list = list;
        this.c = c;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inf = ((Them) c).getLayoutInflater();
        convertView = inf.inflate(R.layout.item_spinner, null);

        ImageView img = convertView.findViewById(R.id.imageView);
        TextView tv = convertView.findViewById(R.id.textView11);

        img.setImageResource(list.get(position).hinh);
        tv.setText(list.get(position).ten);

        return convertView;
    }
}

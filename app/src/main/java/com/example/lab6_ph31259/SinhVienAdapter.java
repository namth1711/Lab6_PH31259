package com.example.lab6_ph31259;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class SinhVienAdapter extends BaseAdapter implements Filterable {
    ArrayList<SinhVienModel> list;
    ArrayList<SinhVienModel> listTim;
    Context c;

    public SinhVienAdapter(ArrayList<SinhVienModel> list, Context c) {
        this.list = list;
        this.listTim=list;
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
        LayoutInflater inf = ((Home)c).getLayoutInflater();
        convertView = inf.inflate(R.layout.item_listview,null);

        TextView cs = convertView.findViewById(R.id.tvCS);
        TextView name = convertView.findViewById(R.id.tvName);
        TextView dc = convertView.findViewById(R.id.tvDc);
        Button btupdate = convertView.findViewById(R.id.btUpdate);
        Button btdelete = convertView.findViewById(R.id.btDelete);

        cs.setText(list.get(position).coSo);
        name.setText(list.get(position).hoTen);
        dc.setText(list.get(position).diaChi);

        btdelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Home)c).deleteSV(position);
            }
        });

        btupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {((Home)c).updateSV(position);}
        });
        return convertView;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String s = charSequence.toString();
                if (s.isEmpty()){
                    list=listTim;
                }else{
                    ArrayList<SinhVienModel> lists =new ArrayList<>();
                    for (SinhVienModel x : listTim){
                        if (x.hoTen.toLowerCase().contains(s.toLowerCase())){
                            lists.add(x);
                        }
                    }
                    list= lists;
                }
                FilterResults filterResults =new FilterResults();
                filterResults.values=list;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                list=(ArrayList<SinhVienModel>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }
}

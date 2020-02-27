package com.tuvi.tuvi2019.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import com.tuvi.tuvi2019.R;
import com.tuvi.tuvi2019.models.Congiap;

public class NamSinhAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private ArrayList<Congiap> listData;

    public NamSinhAdapter(Context context, int layout, ArrayList<Congiap> listData) {
        this.context = context;
        this.layout = layout;
        this.listData = listData;
    }

    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);
            viewHolder.txtNamSinh = (TextView) view.findViewById(R.id.txt_nam_sinh);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        Congiap namSinh = listData.get(i);
        viewHolder.txtNamSinh.setText(namSinh.getYear()+" - "+namSinh.getTitle());
        return view;
    }

    public class ViewHolder {
        private TextView txtNamSinh;
    }
}
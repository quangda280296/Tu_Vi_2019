package com.tuvi.tuvi2019.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import com.tuvi.tuvi2019.R;

public class CungHoangDaoAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private ArrayList<String> listData;

    public CungHoangDaoAdapter(Context context, int layout, ArrayList<String> listData) {
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
            viewHolder.txtTenCung = (TextView) view.findViewById(R.id.txt_cung);
            viewHolder.imgCung = (ImageView) view.findViewById(R.id.img_cung);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        viewHolder.txtTenCung.setText(listData.get(i));
        if (i == 0) {
            viewHolder.imgCung.setImageResource(R.drawable.cungbachduong);
        } else if (i == 1) {
            viewHolder.imgCung.setImageResource(R.drawable.cungkimnguu);
        } else if (i == 2) {
            viewHolder.imgCung.setImageResource(R.drawable.cungsongtu);
        } else if (i == 3) {
            viewHolder.imgCung.setImageResource(R.drawable.cungcugiai);
        } else if (i == 4) {
            viewHolder.imgCung.setImageResource(R.drawable.cungsutu);
        } else if (i == 5) {
            viewHolder.imgCung.setImageResource(R.drawable.cungxunu);
        } else if (i == 6) {
            viewHolder.imgCung.setImageResource(R.drawable.cungthienbinh);
        } else if (i == 7) {
            viewHolder.imgCung.setImageResource(R.drawable.cunghocap);
        } else if (i == 8) {
            viewHolder.imgCung.setImageResource(R.drawable.cungnhanma);
        } else if (i == 9) {
            viewHolder.imgCung.setImageResource(R.drawable.cungmaket);
        } else if (i == 10) {
            viewHolder.imgCung.setImageResource(R.drawable.cungbaobinh);
        } else {
            viewHolder.imgCung.setImageResource(R.drawable.cungsongngu);
        }
        return view;
    }

    public class ViewHolder {
        private TextView txtTenCung;
        private ImageView imgCung;
    }
}
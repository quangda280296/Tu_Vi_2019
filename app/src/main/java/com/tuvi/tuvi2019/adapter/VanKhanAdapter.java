package com.tuvi.tuvi2019.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.ArrayList;
import com.tuvi.tuvi2019.R;

public class VanKhanAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private ArrayList<String> mData ;

    public VanKhanAdapter(Context context, int layout, ArrayList<String> mData) {
        this.context = context;
        this.layout = layout;
        this.mData = mData;
    }

    @Override
    public int getCount() {
        return mData.size();
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
        if (view==null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout,null);
            viewHolder.txtChuDeKhan = (TextView) view.findViewById(R.id.txt_chu_de_khan);
            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }

        viewHolder.txtChuDeKhan.setText(mData.get(i));
        return view;
    }
    public class ViewHolder{
        private TextView txtChuDeKhan;
    }
}
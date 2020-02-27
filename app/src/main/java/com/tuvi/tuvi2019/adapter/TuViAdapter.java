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

public class TuViAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private ArrayList<String> listData ;

    public TuViAdapter(Context context, int layout, ArrayList<String> listData) {
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
        if (view==null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout,null);
            viewHolder.txtTenConGiap = (TextView) view.findViewById(R.id.txt_con_giap);
            viewHolder.imgConGiap = (ImageView) view.findViewById(R.id.img_con_giap);
            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }

        viewHolder.txtTenConGiap.setText(listData.get(i));
        if(i==0){
            viewHolder.imgConGiap.setImageResource(R.drawable.ti);
        }else if(i==1){
            viewHolder.imgConGiap.setImageResource(R.drawable.suu);
        }else if(i==2){
            viewHolder.imgConGiap.setImageResource(R.drawable.dan);
        }else if(i==3){
            viewHolder.imgConGiap.setImageResource(R.drawable.mao);
        }else if(i==4){
            viewHolder.imgConGiap.setImageResource(R.drawable.thin);
        }else if(i==5){
            viewHolder.imgConGiap.setImageResource(R.drawable.ty);
        }else if(i==6){
            viewHolder.imgConGiap.setImageResource(R.drawable.ngo);
        }else if(i==7){
            viewHolder.imgConGiap.setImageResource(R.drawable.mui);
        }else if(i==8){
            viewHolder.imgConGiap.setImageResource(R.drawable.than);
        }else if(i==9){
            viewHolder.imgConGiap.setImageResource(R.drawable.dau);
        }else if(i==10){
            viewHolder.imgConGiap.setImageResource(R.drawable.tuat);
        }else {
            viewHolder.imgConGiap.setImageResource(R.drawable.hoi);
        }
            return view;
    }
    public class ViewHolder{
        private TextView txtTenConGiap;
        private ImageView imgConGiap;
    }
}
package com.tuvi.tuvi2019.base;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.tuvi.tuvi2019.base.holder.BaseViewHolder;

import java.util.List;

public abstract class BaseAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<T> list;

    public BaseAdapter(Context context, List<T> list) {
        this.context = context;
        this.list = list;
    }

    protected abstract int getResLayout();

    @Override
    public int getItemCount() {
        return list.size();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        if (this.context == null)
            return null;

        LayoutInflater inflater = LayoutInflater.from(this.context);
        return new BaseViewHolder(inflater.inflate(getResLayout(), viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        ((BaseViewHolder) viewHolder).bindData(position);
    }
}

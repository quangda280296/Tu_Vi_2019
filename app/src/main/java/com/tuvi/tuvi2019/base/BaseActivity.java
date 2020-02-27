package com.tuvi.tuvi2019.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

import jack.com.servicekeep.act.BaseVMAppCompatActivity;

public abstract class BaseActivity extends BaseVMAppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getResLayout());
        initView();
        initData();
    }

    protected abstract int getResLayout();

    protected abstract void initView();

    protected abstract void initData();
}
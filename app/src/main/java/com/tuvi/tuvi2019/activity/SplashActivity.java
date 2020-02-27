package com.tuvi.tuvi2019.activity;

import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.util.Log;

import com.tuvi.tuvi2019.Config;
import com.tuvi.tuvi2019.MainApplication;
import com.tuvi.tuvi2019.R;
import com.vmb.ads_in_app.GetConfig;
import com.vmb.ads_in_app.handler.AdsHandler;
import com.wang.avi.AVLoadingIndicatorView;

import jack.com.servicekeep.act.BaseVMAppCompatActivity;

public class SplashActivity extends BaseVMAppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        AVLoadingIndicatorView loading_view = findViewById(R.id.loading_view);
        loading_view.smoothToShow();

        GetConfig.callAPI(getApplicationContext(), Config.CODE_CONTROL_APP, Config.VERSION_APP, Config.PACKAGE_NAME);

        ((MainApplication) getApplication()).registerReceiver();
        WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        if (!wifiManager.isWifiEnabled())
            wifiManager.setWifiEnabled(true);

        AdsHandler.getInstance().displayPopupOpenApp(SplashActivity.this,
                new Intent(SplashActivity.this, MainActivity.class));
    }

    @Override
    public void onBackPressed() {
        Log.i("SplashActivity", "onBackPressed()");
    }
}
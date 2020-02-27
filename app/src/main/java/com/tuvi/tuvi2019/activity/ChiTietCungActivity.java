package com.tuvi.tuvi2019.activity;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;

import com.google.android.gms.ads.AdView;

import com.tuvi.tuvi2019.R;
import com.tuvi.tuvi2019.utils.AdsManager;
import com.tuvi.tuvi2019.utils.Utils;
import com.vmb.ads_in_app.handler.AdsHandler;

public class ChiTietCungActivity extends AppCompatActivity {

    WebView webView;
    Toolbar toolbar;
    AdsManager mAdsManager = null;
    //AdView //mAdView;
    String cung;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(getResources().getColor(android.R.color.black));
        }

        setContentView(R.layout.activity_chi_tiet_cung);
        if (mAdsManager == null) {
            mAdsManager = new AdsManager(ChiTietCungActivity.this);
        }
        getSupportActionBar().hide();
        initToolbar();
        initView();
        initData();

        AdsHandler.getInstance().displayInterstitial(this, false);
        AdsHandler.getInstance().initBanner(this);
    }

    private void initData() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            cung = bundle.getString("cunghoangdao");
            webView.loadUrl("file:///android_asset/hoangdao/h0" + cung + ".html");
        }
    }

    private void initView() {
        webView = findViewById(R.id.webView);
        ////mAdView = findViewById(R.id.adView);
        //Utils.adView(ChiTietCungActivity.this,//mAdView);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setDisplayZoomControls(false);
    }

    private void initToolbar() {
        toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mAdsManager != null && Integer.parseInt(cung) % 2 != 0 ) {
                    mAdsManager.showFullAds(ChiTietCungActivity.this);
                } else {
                    finish();
                    AdsHandler.getInstance().displayInterstitial(ChiTietCungActivity.this, false);
                }
            }
        });
        toolbar.setTitle("Cung hoàng đạo");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        /*if (mAdsManager != null && Integer.parseInt(cung) % 2 == 0) {
            mAdsManager.showFullAds(ChiTietCungActivity.this);
        } else {
            finish();
        }*/
        AdsHandler.getInstance().displayInterstitial(this, false);
        finish();
    }

}
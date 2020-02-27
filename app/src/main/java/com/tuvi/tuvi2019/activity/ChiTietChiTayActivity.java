package com.tuvi.tuvi2019.activity;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;

import com.google.android.gms.ads.AdView;

import com.tuvi.tuvi2019.R;
import com.tuvi.tuvi2019.utils.AdsManager;
import com.tuvi.tuvi2019.utils.Utils;
import com.vmb.ads_in_app.handler.AdsHandler;

public class ChiTietChiTayActivity extends AppCompatActivity {

    WebView webView;
    Toolbar toolbar;
    String duongChiTay;
    AdsManager mAdsManager = null;
    //AdView //mAdView;
    String chitay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(getResources().getColor(android.R.color.black));
        }

        setContentView(R.layout.activity_chi_tiet_chi_tay);
        if (mAdsManager == null) {
            mAdsManager = new AdsManager(ChiTietChiTayActivity.this);
        }
        getSupportActionBar().hide();
        initView();
        initData();
        initToolbar();

        AdsHandler.getInstance().displayInterstitial(this, false);
        AdsHandler.getInstance().initBanner(this);
    }
    private void initData() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if(bundle!=null){
            duongChiTay = bundle.getString("duongchitay");
            chitay = bundle.getString("boichitay");
            webView.loadUrl("file:///android_asset/boichitay/chitay"+chitay+".html");
        }
    }

    private void initView() {
        webView = findViewById(R.id.webView);
        ////mAdView = findViewById(R.id.adView);
        //Utils.adView(ChiTietChiTayActivity.this,//mAdView);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setDisplayZoomControls(false);
    }

    private void initToolbar() {
        toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("datnm","ChiTietChiTay.initToolbar");
                if (mAdsManager != null && Integer.parseInt(chitay)%2==0) {
                    mAdsManager.showFullAds(ChiTietChiTayActivity.this);
                } else {
                    finish();
                    AdsHandler.getInstance().displayInterstitial(ChiTietChiTayActivity.this, false);
                }
            }
        });
        toolbar.setTitle("Chỉ tay - "+duongChiTay);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Log.d("datnm","ChiTietChiTay.onBackPressed");
        /*if (mAdsManager != null && Integer.parseInt(chitay)%2==0 && (Integer.parseInt(chitay)==2) || (Integer.parseInt(chitay)==4)) {
            mAdsManager.showFullAds(ChiTietChiTayActivity.this);
        } else {
            finish();
        }*/
        AdsHandler.getInstance().displayInterstitial(this, false);
        finish();
    }
}

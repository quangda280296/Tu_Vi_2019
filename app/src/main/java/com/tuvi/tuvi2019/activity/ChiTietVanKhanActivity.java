package com.tuvi.tuvi2019.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;

import com.tuvi.tuvi2019.R;
import com.tuvi.tuvi2019.utils.AdsManager;
import com.vmb.ads_in_app.handler.AdsHandler;

public class ChiTietVanKhanActivity extends AppCompatActivity {

    WebView webView;
    Toolbar toolbar;
    //AdView //mAdView;
    AdsManager mAdsManager = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(getResources().getColor(android.R.color.black));
        }

        setContentView(R.layout.activity_chi_tiet_van_khan);
        getSupportActionBar().hide();
        if (mAdsManager == null) {
            mAdsManager = new AdsManager(ChiTietVanKhanActivity.this);
        }
        initToolbar();
        initView();
        initData();

        AdsHandler.getInstance().displayInterstitial(this, false);
        AdsHandler.getInstance().initBanner(this);
    }

    private void initData() {
        Intent intent = getIntent();
        int vanKhan = intent.getIntExtra("vankhan", 000);
        int ndVanKhan = intent.getIntExtra("noidungvankhan", 000);
        switch (vanKhan) {
            case 1:
                if (ndVanKhan < 10) {
                    webView.loadUrl("file:///android_asset/vankhan/" + vanKhan + "0" + ndVanKhan + ".html");
                } else {
                    webView.loadUrl("file:///android_asset/vankhan/" + vanKhan + ndVanKhan + ".html");
                }
                break;
            case 2:
                if (ndVanKhan < 10) {
                    webView.loadUrl("file:///android_asset/vankhan/" + vanKhan + "0" + ndVanKhan + ".html");
                } else {
                    webView.loadUrl("file:///android_asset/vankhan/" + vanKhan + ndVanKhan + ".html");
                }
                break;
            case 3:
                if (ndVanKhan < 10) {
                    webView.loadUrl("file:///android_asset/vankhan/" + vanKhan + "0" + ndVanKhan + ".html");
                } else {
                    webView.loadUrl("file:///android_asset/vankhan/" + vanKhan + ndVanKhan + ".html");
                }
                break;
            case 4:
                if (ndVanKhan < 10) {
                    webView.loadUrl("file:///android_asset/vankhan/" + vanKhan + "0" + ndVanKhan + ".html");
                } else {
                    webView.loadUrl("file:///android_asset/vankhan/" + vanKhan + ndVanKhan + ".html");
                }
                break;
            case 5:
                if (ndVanKhan < 10) {
                    webView.loadUrl("file:///android_asset/vankhan/" + vanKhan + "0" + ndVanKhan + ".html");
                } else {
                    webView.loadUrl("file:///android_asset/vankhan/" + vanKhan + ndVanKhan + ".html");
                }
                break;
            case 6:
                if (ndVanKhan < 10) {
                    webView.loadUrl("file:///android_asset/vankhan/" + vanKhan + "0" + ndVanKhan + ".html");
                } else {
                    webView.loadUrl("file:///android_asset/vankhan/" + vanKhan + ndVanKhan + ".html");
                }
                break;
            case 7:
                if (ndVanKhan < 10) {
                    webView.loadUrl("file:///android_asset/vankhan/" + vanKhan + "0" + ndVanKhan + ".html");
                } else {
                    webView.loadUrl("file:///android_asset/vankhan/" + vanKhan + ndVanKhan + ".html");
                }
                break;
            case 8:
                if (ndVanKhan < 10) {
                    webView.loadUrl("file:///android_asset/vankhan/" + vanKhan + "0" + ndVanKhan + ".html");
                } else {
                    webView.loadUrl("file:///android_asset/vankhan/" + vanKhan + ndVanKhan + ".html");
                }
                break;
            case 9:
                if (ndVanKhan < 10) {
                    webView.loadUrl("file:///android_asset/vankhan/" + vanKhan + "0" + ndVanKhan + ".html");
                } else {
                    webView.loadUrl("file:///android_asset/vankhan/" + vanKhan + ndVanKhan + ".html");
                }
                break;
            case 10:
                if (ndVanKhan < 10) {
                    webView.loadUrl("file:///android_asset/vankhan/" + vanKhan + "0" + ndVanKhan + ".html");
                } else {
                    webView.loadUrl("file:///android_asset/vankhan/" + vanKhan + ndVanKhan + ".html");
                }
                break;
        }
    }

    private void initView() {
        webView = findViewById(R.id.webView);
        ////mAdView = findViewById(R.id.adView);
        //Utils.adView(ChiTietVanKhanActivity.this,//mAdView);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setDisplayZoomControls(false);
    }

    private void initToolbar() {
        toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                AdsHandler.getInstance().displayInterstitial(ChiTietVanKhanActivity.this, false);
            }
        });
        toolbar.setTitle("Nội dung Văn khấn");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
//        if (mAdsManager != null) {
//            mAdsManager.showFullAds(ChiTietVanKhanActivity.this);
//        } else {
        AdsHandler.getInstance().displayInterstitial(this, false);
        finish();
//        }
    }
}
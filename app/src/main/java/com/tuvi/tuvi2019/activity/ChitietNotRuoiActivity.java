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
import android.widget.ImageView;

import com.tuvi.tuvi2019.R;
import com.tuvi.tuvi2019.utils.AdsManager;
import com.vmb.ads_in_app.handler.AdsHandler;

public class ChitietNotRuoiActivity extends AppCompatActivity {

    WebView webView;
    Toolbar toolbar;
    ImageView img;
    String tenNotRuoi;
    String xemnotruoi;
    AdsManager mAdsManager = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(getResources().getColor(android.R.color.black));
        }

        setContentView(R.layout.activity_chitiet_not_ruoi);
        getSupportActionBar().hide();
        if (mAdsManager == null) {
            mAdsManager = new AdsManager(ChitietNotRuoiActivity.this);
        }
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
            tenNotRuoi = bundle.getString("tennotruoi");
            xemnotruoi = bundle.getString("xemnotruoi");
            if(xemnotruoi.equals("thannam")){
               img.setImageResource(R.drawable.notruoicothenamgioi);
            }else if(xemnotruoi.equals("thannu")){
                img.setImageResource(R.drawable.notruoicothenugioi);
            }else if(xemnotruoi.equals("matnam")){
                img.setImageResource(R.drawable.notruoitrenmatnamgioi);
            }else if(xemnotruoi.equals("matnu")){
                img.setImageResource(R.drawable.notruoitrenmatnugioi);
            }else if(xemnotruoi.equals("chan")){
                img.setImageResource(R.drawable.notruoiobanchan);
            }else {
                img.setImageResource(R.drawable.notruoiolongbantay);
            }
            webView.loadUrl("file:///android_asset/notruoi/"+xemnotruoi+".html");
        }
    }

    private void initView() {
        webView = findViewById(R.id.webView);
        img = findViewById(R.id.img_not_ruoi);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setDisplayZoomControls(false);
    }

    private void initToolbar() {
        toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mAdsManager != null && xemnotruoi.equals("thannu")|| xemnotruoi.equals("matnam")|| xemnotruoi.equals("chan")) {
                    mAdsManager.showFullAds(ChitietNotRuoiActivity.this);
                } else {
                    finish();
                    AdsHandler.getInstance().displayInterstitial(ChitietNotRuoiActivity.this, false);
                }
            }
        });
        toolbar.setTitle(tenNotRuoi);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        AdsHandler.getInstance().displayInterstitial(this, false);
        finish();
    }
}

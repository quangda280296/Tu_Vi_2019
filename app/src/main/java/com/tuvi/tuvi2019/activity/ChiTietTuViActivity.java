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
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.google.android.gms.ads.AdView;

import com.tuvi.tuvi2019.Enum.CommonEnum;
import com.tuvi.tuvi2019.R;
import com.tuvi.tuvi2019.utils.AdsManager;
import com.tuvi.tuvi2019.utils.Utils;
import com.vmb.ads_in_app.handler.AdsHandler;

public class ChiTietTuViActivity extends AppCompatActivity {

    WebView webView;
    Toolbar toolbar;
    RadioGroup rdgMang;
    RadioButton rdbNamMang, rdbNuMang;
    String namSinh, thienCan, tuoi;
    int type, position;
    AdsManager mAdsManager = null;
    //AdView //mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(getResources().getColor(android.R.color.black));
        }

        setContentView(R.layout.activity_chi_tiet_tu_vi);
        if (mAdsManager == null) {
            mAdsManager = new AdsManager(ChiTietTuViActivity.this);
        }
        getSupportActionBar().hide();
        initData();
        initToolbar();
        initView();

        AdsHandler.getInstance().displayInterstitial(this, false);
        AdsHandler.getInstance().initBanner(this);
    }

    private void initData() {
        Intent intent = getIntent();
        namSinh = intent.getStringExtra("namsinh");
        thienCan = intent.getStringExtra("thiencan");
        type = intent.getIntExtra("tuvi", 000);
        position = intent.getIntExtra("position", 111);
        tuoi = intent.getStringExtra("tuoi");

    }

    private void initView() {
        webView = findViewById(R.id.webView);
        rdgMang = findViewById(R.id.rdg_mang);
        rdbNamMang = findViewById(R.id.rdb_nam);
        rdbNuMang = findViewById(R.id.rdb_nu);
        ////mAdView = findViewById(R.id.adView);
        //Utils.adView(ChiTietTuViActivity.this, //mAdView);
        rdbNamMang.setChecked(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setDisplayZoomControls(false);
        if (type == CommonEnum.Tuvi.TUVI_2019.getValue()) {
            webView.loadUrl("file:///android_asset/2019/" + namSinh + "m.html");
        } else if (type == CommonEnum.Tuvi.TUVI_2020.getValue()) {
            webView.loadUrl("file:///android_asset/2020/" + namSinh + "m.html");
        } else {
            webView.loadUrl("file:///android_asset/full/" + namSinh + "m.html");
        }
        rdgMang.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.rdb_nam:
                        if (type == CommonEnum.Tuvi.TUVI_2019.getValue()) {
                            webView.loadUrl("file:///android_asset/2019/" + namSinh + "m.html");
                        } else if (type == CommonEnum.Tuvi.TUVI_2020.getValue()) {
                            webView.loadUrl("file:///android_asset/2020/" + namSinh + "m.html");
                        } else {
                            webView.loadUrl("file:///android_asset/full/" + namSinh + "m.html");
                        }
                        break;
                    case R.id.rdb_nu:
                        if (type == CommonEnum.Tuvi.TUVI_2019.getValue()) {
                            webView.loadUrl("file:///android_asset/2019/" + namSinh + "f.html");
                        }
                        if (type == CommonEnum.Tuvi.TUVI_2020.getValue()) {
                            webView.loadUrl("file:///android_asset/2020/" + namSinh + "f.html");
                        } else {
                            webView.loadUrl("file:///android_asset/full/" + namSinh + "f.html");
                        }
                        break;
                    default:
                        break;
                }
            }
        });
    }

    private void initToolbar() {
        toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mAdsManager != null && Integer.parseInt(namSinh) % 2 == 0 && position % 2 == 0 || Integer.parseInt(namSinh) % 2 != 0 & position % 2 == 0) {
                    mAdsManager.showFullAds(ChiTietTuViActivity.this);
                } else {
                    finish();
                    AdsHandler.getInstance().displayInterstitial(ChiTietTuViActivity.this, false);
                }
            }
        });
        if (type == CommonEnum.Tuvi.TUVI_2019.getValue()) {
            toolbar.setTitle(thienCan + " - Kỷ Hợi 2019");
        } else if (type == CommonEnum.Tuvi.TUVI_2020.getValue()) {
            toolbar.setTitle(thienCan + " - Canh Tý 2020");
        } else {
            toolbar.setTitle(thienCan + " - Trọn đời");
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        /*if (mAdsManager != null && Integer.parseInt(namSinh) % 2 == 0 && position % 2 == 0 || Integer.parseInt(namSinh) % 2 != 0 & position % 2 == 0) {
            mAdsManager.showFullAds(ChiTietTuViActivity.this);
        } else {
            finish();
        }*/
        AdsHandler.getInstance().displayInterstitial(this, false);
        finish();
    }
}

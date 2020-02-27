package com.tuvi.tuvi2019.activity;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.android.gms.ads.AdView;

import java.util.ArrayList;

import com.tuvi.tuvi2019.Enum.CommonEnum;
import com.tuvi.tuvi2019.R;
import com.tuvi.tuvi2019.adapter.NamSinhAdapter;
import com.tuvi.tuvi2019.models.Congiap;
import com.tuvi.tuvi2019.utils.AdsManager;
import com.tuvi.tuvi2019.utils.Utils;
import com.vmb.ads_in_app.handler.AdsHandler;

public class NamSinhActivity extends AppCompatActivity {

    ListView lvNamSinh;
    ArrayList<Congiap> listData;
    ArrayList<Congiap> namSinhList;
    Toolbar toolbar;
    String tuoi;
    int type;
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

        setContentView(R.layout.activity_nam_sinh);
        if (mAdsManager == null) {
            mAdsManager = new AdsManager(NamSinhActivity.this);
        }
        getSupportActionBar().hide();
        initData();
        initToolbar();
        initView();

        AdsHandler.getInstance().displayInterstitial(this, false);
        AdsHandler.getInstance().initBanner(this);
    }

    private void initData() {
        listData = new ArrayList<>();
        namSinhList = new ArrayList<>();
        final Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            tuoi = bundle.getString("tuoi");
            type = bundle.getInt("tuvi");
            listData = bundle.getParcelableArrayList("namsinh");
            assert listData != null;
            for (int i = 0; i < listData.size(); i++) {
                if (type == CommonEnum.Tuvi.TUVI_2020.getValue()) {
                    if (!listData.get(i).getYear().equals("1948") && !listData.get(i).getYear().equals("1949")) {
                            namSinhList.add(listData.get(i));
                    }

                } else {
                    namSinhList.add(listData.get(i));
                }
            }


        }
    }

    private void initView() {
        ////mAdView = findViewById(R.id.adView);
        //Utils.adView(NamSinhActivity.this, //mAdView);
        lvNamSinh = findViewById(R.id.lv_nam_sinh);
        NamSinhAdapter namSinhAdapter = new NamSinhAdapter(NamSinhActivity.this, R.layout.custom_item_nam_sinh, namSinhList);
        lvNamSinh.setAdapter(namSinhAdapter);

        lvNamSinh.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent1 = new Intent(NamSinhActivity.this, ChiTietTuViActivity.class);
                intent1.putExtra("namsinh", namSinhList.get(i).getYear());
                intent1.putExtra("thiencan", namSinhList.get(i).getTitle());
                intent1.putExtra("position", i);
                intent1.putExtra("tuoi", tuoi);
                intent1.putExtra("tuvi", type);
                startActivity(intent1);
            }
        });
    }

    private void initToolbar() {
        toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                AdsHandler.getInstance().displayInterstitial(NamSinhActivity.this, false);
            }
        });
        if (type == CommonEnum.Tuvi.TUVI_2019.getValue()) {
            toolbar.setTitle("Tuổi " + tuoi + " Năm Kỷ Hợi 2019");
        } else if (type == CommonEnum.Tuvi.TUVI_2020.getValue()) {
            toolbar.setTitle("Tuổi " + tuoi + " Năm Canh Tý 2020");
        } else {
            toolbar.setTitle("Tử vi trọn đời tuổi " + tuoi);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        AdsHandler.getInstance().displayInterstitial(this, false);
        finish();
    }
}
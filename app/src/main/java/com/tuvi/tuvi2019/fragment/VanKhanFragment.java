package com.tuvi.tuvi2019.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.google.android.gms.ads.AdView;

import java.util.ArrayList;

import com.tuvi.tuvi2019.R;
import com.tuvi.tuvi2019.activity.DanhSachVanKhanActivity;
import com.tuvi.tuvi2019.activity.MainActivity;
import com.tuvi.tuvi2019.adapter.VanKhanAdapter;
import com.tuvi.tuvi2019.utils.AdsManager;
import com.tuvi.tuvi2019.utils.Utils;
import com.vmb.ads_in_app.LibrayData;
import com.vmb.ads_in_app.handler.AdsHandler;

/**
 * A simple {@link Fragment} subclass.
 */
public class VanKhanFragment extends Fragment {

    View view;
    android.support.v7.widget.Toolbar toolbar;

    ArrayList<String> listChuDeKhan;
    VanKhanAdapter adapter;
    GridView grv;
    //AdView //mAdView;
    AdsManager mAdsManager = null;

    public static VanKhanFragment newInstance() {
        VanKhanFragment fragment = new VanKhanFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_van_khan, container, false);
        ((MainActivity) getActivity()).getSupportActionBar().hide();
        ((MainActivity) getActivity()).disableText();
        initToolbar();
        initData();
        iniView();
        return view;
    }

    private void initToolbar() {
        toolbar = view.findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().remove(VanKhanFragment.this).commit();
                ((MainActivity) getActivity()).getSupportActionBar().show();
                ((MainActivity) getActivity()).enableText();
                AdsHandler.getInstance().displayInterstitial(getActivity(), false);
            }
        });
        toolbar.setTitle("Văn khấn");
    }


    private void iniView() {
        //mAdView = view.findViewById(R.id.adView);
        //Utils.adView(getActivity(), //mAdView);
        grv = view.findViewById(R.id.grv);
        adapter = new VanKhanAdapter(getActivity(), R.layout.custom_item_van_khan, listChuDeKhan);
        grv.setAdapter(adapter);
        grv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), DanhSachVanKhanActivity.class);
                intent.putExtra("vankhan", i);
                startActivity(intent);
            }
        });
    }

    private void initData() {
        listChuDeKhan = new ArrayList<>();
        listChuDeKhan.add("Tết nguyên đán");
        listChuDeKhan.add("Các tiết trong năm");
        listChuDeKhan.add("Rằm - Mùng một");
        listChuDeKhan.add("Lễ tục vòng đời");
        listChuDeKhan.add("Giải hạn");
        listChuDeKhan.add("Tang lễ");
        listChuDeKhan.add("Cúng giỗ");
        listChuDeKhan.add("Thần linh tại gia");
        listChuDeKhan.add("Tại chùa");
        listChuDeKhan.add("Tại Đình, Đền, Miếu, Phủ");
        AdsHandler.getInstance().displayInterstitial(getActivity(), false);
        AdsHandler.getInstance().initBanner(getActivity(), LibrayData.AdsSize.BANNER,
                (ViewGroup) view.findViewById(R.id.adView_van_khan));
    }
}
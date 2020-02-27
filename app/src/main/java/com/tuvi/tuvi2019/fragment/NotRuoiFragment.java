package com.tuvi.tuvi2019.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.google.android.gms.ads.AdView;

import java.util.ArrayList;

import com.tuvi.tuvi2019.R;
import com.tuvi.tuvi2019.activity.ChitietNotRuoiActivity;
import com.tuvi.tuvi2019.activity.MainActivity;
import com.tuvi.tuvi2019.adapter.NotRuoiAdapter;
import com.tuvi.tuvi2019.utils.AdsManager;
import com.tuvi.tuvi2019.utils.Utils;
import com.vmb.ads_in_app.LibrayData;
import com.vmb.ads_in_app.handler.AdsHandler;

/**
 * A simple {@link Fragment} subclass.
 */
public class NotRuoiFragment extends Fragment {

    View view;
    Toolbar toolbar;
    GridView grv;
    NotRuoiAdapter adapter;
    ArrayList<String> listNotRuoi;

    //AdView //mAdView;
    AdsManager mAdsManager = null;

    public static NotRuoiFragment newInstance() {
        NotRuoiFragment fragment = new NotRuoiFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_not_ruoi, container, false);
        ((MainActivity) getActivity()).getSupportActionBar().hide();
        ((MainActivity) getActivity()).disableText();
        initToolbar();
        initData();
        initView();
        return view;
    }

    private void initData() {
        listNotRuoi = new ArrayList<>();
        listNotRuoi.add("Nốt ruồi thân nam");
        listNotRuoi.add("Nốt ruồi thân nữ");
        listNotRuoi.add("Nốt ruồi mặt nam");
        listNotRuoi.add("Nốt ruồi mặt nữ");
        listNotRuoi.add("Nốt ruồi chân");
        listNotRuoi.add("Nốt ruồi tay");
        AdsHandler.getInstance().displayInterstitial(getActivity(), false);
        AdsHandler.getInstance().initBanner(getActivity(), LibrayData.AdsSize.BANNER,
                (ViewGroup) view.findViewById(R.id.adView_not_ruoi));
    }

    private void initView() {
        //mAdView = view.findViewById(R.id.adView);
        //Utils.adView(getActivity(), //mAdView);
        grv = view.findViewById(R.id.grv);
        adapter = new NotRuoiAdapter(getActivity(), R.layout.custom_item_hoang_dao_va_chi_tay_va_not_ruoi, listNotRuoi);
        grv.setAdapter(adapter);
        grv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), ChitietNotRuoiActivity.class);
                Bundle bundle = new Bundle();
                if (i == 0) {
                    bundle.putString("xemnotruoi", "thannam");
                    bundle.putString("tennotruoi", listNotRuoi.get(i));
                    intent.putExtras(bundle);
                    startActivity(intent);
                } else if (i == 1) {
                    bundle.putString("xemnotruoi", "thannu");
                    bundle.putString("tennotruoi", listNotRuoi.get(i));
                    intent.putExtras(bundle);
                    startActivity(intent);
                } else if (i == 2) {
                    bundle.putString("xemnotruoi", "matnam");
                    bundle.putString("tennotruoi", listNotRuoi.get(i));
                    intent.putExtras(bundle);
                    startActivity(intent);
                } else if (i == 3) {
                    bundle.putString("xemnotruoi", "matnu");
                    bundle.putString("tennotruoi", listNotRuoi.get(i));
                    intent.putExtras(bundle);
                    startActivity(intent);
                } else if (i == 4) {
                    bundle.putString("xemnotruoi", "chan");
                    bundle.putString("tennotruoi", listNotRuoi.get(i));
                    intent.putExtras(bundle);
                    startActivity(intent);
                } else if (i == 5) {
                    bundle.putString("xemnotruoi", "tay");
                    bundle.putString("tennotruoi", listNotRuoi.get(i));
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            }
        });
    }

    private void initToolbar() {
        toolbar = view.findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().remove(NotRuoiFragment.this).commit();
                ((MainActivity) getActivity()).getSupportActionBar().show();
                ((MainActivity) getActivity()).enableText();
                AdsHandler.getInstance().displayInterstitial(getActivity(), false);
            }
        });
        toolbar.setTitle("Bói nốt ruồi");
    }
}

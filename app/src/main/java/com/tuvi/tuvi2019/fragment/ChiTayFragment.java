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

import com.tuvi.tuvi2019.R;
import com.tuvi.tuvi2019.activity.ChiTietChiTayActivity;
import com.tuvi.tuvi2019.activity.MainActivity;
import com.tuvi.tuvi2019.adapter.ChiTayAdapter;
import com.vmb.ads_in_app.LibrayData;
import com.vmb.ads_in_app.handler.AdsHandler;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChiTayFragment extends Fragment {

    View view;
    Toolbar toolbar;
    GridView grv;
    ChiTayAdapter adapter;
    ArrayList<String> lisChiTay;
    //AdView //mAdView;

    public static ChiTayFragment newInstance() {
        ChiTayFragment fragment = new ChiTayFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_chi_tay, container, false);
        ((MainActivity) getActivity()).getSupportActionBar().hide();
        ((MainActivity) getActivity()).disableText();
        initToolbar();
        initData();
        initView();
        return view;
    }

    private void initData() {
        lisChiTay = new ArrayList<>();
        lisChiTay.add("Đặc điểm chung");
        lisChiTay.add("Cách xem tay");
        lisChiTay.add("Đường sinh đạo");
        lisChiTay.add("Đường tâm đạo");
        lisChiTay.add("Đường định mệnh");
        lisChiTay.add("Đường thái dương");
        lisChiTay.add("Vân tay và tính cách");
        lisChiTay.add("Gò bàn tay");

        AdsHandler.getInstance().displayInterstitial(getActivity(), false);
        AdsHandler.getInstance().initBanner(getActivity(), LibrayData.AdsSize.BANNER,
                (ViewGroup) view.findViewById(R.id.adView_boi_chi_tay));
    }

    private void initView() {
        //mAdView = view.findViewById(R.id.adView);
        //Utils.adView(getActivity(),//mAdView);
        grv = view.findViewById(R.id.grv);
        adapter = new ChiTayAdapter(getActivity(), R.layout.custom_item_hoang_dao_va_chi_tay_va_not_ruoi, lisChiTay);
        grv.setAdapter(adapter);
        grv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), ChiTietChiTayActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("boichitay", String.valueOf(i + 1));
                bundle.putString("duongchitay", lisChiTay.get(i));
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    private void initToolbar() {
        toolbar = view.findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().remove(ChiTayFragment.this).commit();
                ((MainActivity) getActivity()).getSupportActionBar().show();
                ((MainActivity) getActivity()).enableText();
                AdsHandler.getInstance().displayInterstitial(getActivity(), false);
            }
        });
        toolbar.setTitle("Bói chỉ tay");
    }
}

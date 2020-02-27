package com.tuvi.tuvi2019.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.tuvi.tuvi2019.R;
import com.tuvi.tuvi2019.activity.ChiTietCungActivity;
import com.tuvi.tuvi2019.activity.MainActivity;
import com.tuvi.tuvi2019.adapter.CungHoangDaoAdapter;
import com.tuvi.tuvi2019.utils.AdsManager;
import com.vmb.ads_in_app.LibrayData;
import com.vmb.ads_in_app.handler.AdsHandler;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class HoangDaoFragment extends Fragment {

    View view;
    Toolbar toolbar;
    GridView grv;
    CungHoangDaoAdapter adapter;
    ArrayList<String> listCung;

    //AdView //mAdView;
    AdsManager mAdsManager = null;

    public static HoangDaoFragment newInstance() {
        HoangDaoFragment fragment = new HoangDaoFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_hoang_dao, container, false);
        ((MainActivity) getActivity()).getSupportActionBar().hide();
        ((MainActivity) getActivity()).disableText();
        initToolbar();
        initData();
        initView();
        return view;
    }

    private void initData() {
        listCung = new ArrayList<>();
        listCung.add("Cung Bạch Dương\n(21/3 - 19/4)");
        listCung.add("Cung Kim Ngưu\n(20/04 - 20/5)");
        listCung.add("Cung Song Tử\n(21/05 - 20/06)");
        listCung.add("Cung Cự Giải\n(21/06 - 22/07)");
        listCung.add("Cung Sư Tử\n(23/07 - 22/08)");
        listCung.add("Cung Xử Nữ\n(23/08 - 21/09)");
        listCung.add("Cung Thiên Xứng\n(22/09 - 22/10)");
        listCung.add("Cung Bọ Cạp\n(23/10 - 21/11)");
        listCung.add("Cung Nhân Mã\n(22/11 - 21/12)");
        listCung.add("Cung Ma Kết\n(22/12 - 19/01)");
        listCung.add("Cung Bảo Bình\n(20/01 - 19/02)");
        listCung.add("Cung Song Ngư\n(20/02 - 21/03)");
        AdsHandler.getInstance().displayInterstitial(getActivity(), false);
        AdsHandler.getInstance().initBanner(getActivity(), LibrayData.AdsSize.BANNER,
                (ViewGroup) view.findViewById(R.id.adView_hoang_dao));
    }

    private void initView() {
        //mAdView = view.findViewById(R.id.adView);
        //Utils.adView(getActivity(),//mAdView);
        grv = view.findViewById(R.id.grv);
        Log.d("datasize", "" + listCung.size());
        adapter = new CungHoangDaoAdapter(getActivity(), R.layout.custom_item_hoang_dao_va_chi_tay_va_not_ruoi, listCung);
        grv.setAdapter(adapter);
        grv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), ChiTietCungActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("cunghoangdao", String.valueOf(i));
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
                getActivity().getSupportFragmentManager().beginTransaction().remove(HoangDaoFragment.this).commit();
                ((MainActivity) getActivity()).getSupportActionBar().show();
                ((MainActivity) getActivity()).enableText();
                AdsHandler.getInstance().displayInterstitial(getActivity(), false);
            }
        });
        toolbar.setTitle("Cung hoàng đạo");
    }

}

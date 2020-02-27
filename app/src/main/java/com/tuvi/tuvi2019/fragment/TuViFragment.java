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

import com.tuvi.tuvi2019.Enum.CommonEnum;
import com.tuvi.tuvi2019.R;
import com.tuvi.tuvi2019.activity.MainActivity;
import com.tuvi.tuvi2019.activity.NamSinhActivity;
import com.tuvi.tuvi2019.adapter.TuViAdapter;
import com.tuvi.tuvi2019.models.Congiap;
import com.tuvi.tuvi2019.utils.AdsManager;
import com.tuvi.tuvi2019.utils.Utils;
import com.vmb.ads_in_app.LibrayData;
import com.vmb.ads_in_app.handler.AdsHandler;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class TuViFragment extends Fragment {

    public static String KEY_TUVI = "tuvi_key";
    View view;
    GridView grv;
    TuViAdapter adapter;
    ArrayList<Congiap> mData;
    ArrayList<Congiap> namSinhList;
    ArrayList<String> listConGiap;

    Toolbar toolbar;
    //AdView //mAdView;
    AdsManager mAdsManager = null;
    public int type;

    public static TuViFragment newInstance(int type) {
        TuViFragment fragment = new TuViFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(KEY_TUVI, type);
        fragment.setArguments(bundle);
        return fragment;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_tu_vi, container, false);
        ((MainActivity) getActivity()).getSupportActionBar().hide();
        ((MainActivity) getActivity()).disableText();
        initData();
        initToolbar();
        initView();
        return view;
    }

    private void initData() {
        this.type = getArguments().getInt(KEY_TUVI);
        mData = new ArrayList<>();
        listConGiap = new ArrayList<>();
        listConGiap.add("Tý");
        listConGiap.add("Sửu");
        listConGiap.add("Dần");
        listConGiap.add("Mão");
        listConGiap.add("Thìn");
        listConGiap.add("Tỵ");
        listConGiap.add("Ngọ");
        listConGiap.add("Mùi");
        listConGiap.add("Thân");
        listConGiap.add("Dậu");
        listConGiap.add("Tuất");
        listConGiap.add("Hợi");
        mData = Utils.getData(Utils.loadJSONFromAsset(getActivity()));
        AdsHandler.getInstance().displayInterstitial(getActivity(), false);
        AdsHandler.getInstance().initBanner(getActivity(), LibrayData.AdsSize.BANNER,
                (ViewGroup) view.findViewById(R.id.adView_tu_vi));
    }

    private void initView() {
        //mAdView = view.findViewById(R.id.adView);
        //Utils.adView(getActivity(),//mAdView);
        grv = view.findViewById(R.id.grv);
        adapter = new TuViAdapter(getActivity(), R.layout.custom_item_tu_vi_2019, listConGiap);
        grv.setAdapter(adapter);
        grv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), NamSinhActivity.class);
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList("namsinh", getNamsinh(i));
                bundle.putString("tuoi", mData.get(i).getType());
                bundle.putInt("tuvi", type);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    private ArrayList<Congiap> getNamsinh(int viTri) {
        namSinhList = new ArrayList<>();
        ArrayList<Congiap> list = new ArrayList<>();
        list = Utils.getData(Utils.loadJSONFromAsset(getActivity()));
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getType().equals(list.get(viTri).getType())) {
                namSinhList.add(list.get(i));
            }
        }
        return namSinhList;
    }

    private void initToolbar() {
        toolbar = view.findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().remove(TuViFragment.this).commit();
                ((MainActivity) getActivity()).getSupportActionBar().show();
                ((MainActivity) getActivity()).enableText();
                AdsHandler.getInstance().displayInterstitial(getActivity(), false);
            }
        });
        if (type == CommonEnum.Tuvi.TUVI_2019.getValue()) {
            toolbar.setTitle("Tử Vi 2019");

        } else if (type == CommonEnum.Tuvi.TUVI_2020.getValue()) {
            toolbar.setTitle("Tử Vi 2020");

        } else {
            toolbar.setTitle("Tử Vi Trọn Đời");

        }
    }

}

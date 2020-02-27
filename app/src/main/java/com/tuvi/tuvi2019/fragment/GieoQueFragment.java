package com.tuvi.tuvi2019.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;

import com.tuvi.tuvi2019.R;
import com.tuvi.tuvi2019.activity.ChiTietQueActivity;
import com.tuvi.tuvi2019.activity.MainActivity;
import com.tuvi.tuvi2019.utils.AdsManager;
import com.vmb.ads_in_app.LibrayData;
import com.vmb.ads_in_app.handler.AdsHandler;

/**
 * A simple {@link Fragment} subclass.
 */
public class GieoQueFragment extends Fragment {

    Button btnGieoQue;
    ImageView img1;
    View view;
    WebView webView;
    CountDownTimer countDownTimer;
    Toolbar toolbar;
    //AdView //mAdView;
    AdsManager mAdsManager = null;
    int position = 0;
    int[] listQue = {R.drawable.frame_1, R.drawable.frame_2, R.drawable.frame_3, R.drawable.frame_4,
            R.drawable.frame_5, R.drawable.frame_6, R.drawable.frame_7, R.drawable.frame_8, R.drawable.frame_9, R.drawable.frame_10, R.drawable.frame_11};


    public static GieoQueFragment newInstance() {
        GieoQueFragment fragment = new GieoQueFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_gieo_que, container, false);
        ((MainActivity) getActivity()).getSupportActionBar().hide();
        ((MainActivity) getActivity()).disableText();
        initToolbar();
        initView();
        countDownTimer = new CountDownTimer(3300, 300) {
            @Override
            public void onTick(long l) {
                img1.setImageResource(listQue[position]);
                position++;
            }

            @Override
            public void onFinish() {
                if (getActivity().isFinishing())
                    return;

                Intent intent = new Intent(getActivity(), ChiTietQueActivity.class);
                startActivity(intent);
                position = 0;
            }
        };

        AdsHandler.getInstance().displayInterstitial(getActivity(), false);
        AdsHandler.getInstance().initBanner(getActivity(), LibrayData.AdsSize.BANNER,
                (ViewGroup) view.findViewById(R.id.adView_gieo_que));
        return view;
    }

    private void initView() {
        //mAdView = view.findViewById(R.id.adView);
        //Utils.adView(getActivity(),//mAdView);
        btnGieoQue = view.findViewById(R.id.btn_gieo_que);
        img1 = view.findViewById(R.id.frame_1);
        webView = view.findViewById(R.id.webView);
        btnGieoQue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                countDownTimer.start();
            }
        });
    }

    private void initToolbar() {
        toolbar = view.findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_back);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().remove(GieoQueFragment.this).commit();
                ((MainActivity) getActivity()).getSupportActionBar().show();
                ((MainActivity) getActivity()).enableText();
                AdsHandler.getInstance().displayInterstitial(getActivity(), false);
            }
        });
        toolbar.setTitle("Gieo quẻ");
    }
}

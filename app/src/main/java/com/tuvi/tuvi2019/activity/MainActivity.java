package com.tuvi.tuvi2019.activity;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.tuvi.tuvi2019.Config;
import com.tuvi.tuvi2019.Enum.CommonEnum;
import com.tuvi.tuvi2019.R;
import com.tuvi.tuvi2019.fragment.BoiTinhYeuFragment;
import com.tuvi.tuvi2019.fragment.ChiTayFragment;
import com.tuvi.tuvi2019.fragment.GieoQueFragment;
import com.tuvi.tuvi2019.fragment.HoangDaoFragment;
import com.tuvi.tuvi2019.fragment.NotRuoiFragment;
import com.tuvi.tuvi2019.fragment.TuViFragment;
import com.tuvi.tuvi2019.fragment.VanKhanFragment;
import com.vmb.ads_in_app.GetConfig;
import com.vmb.ads_in_app.Interface.IUpdateNewVersion;
import com.vmb.ads_in_app.LibrayData;
import com.vmb.ads_in_app.handler.AdsHandler;
import com.vmb.ads_in_app.model.AdsConfig;
import com.vmb.ads_in_app.util.CountryCodeUtil;
import com.vmb.ads_in_app.util.NetworkUtil;
import com.vmb.ads_in_app.util.OnTouchClickListener;
import com.vmb.ads_in_app.util.PrintKeyHash;
import com.vmb.ads_in_app.util.RefreshToken;
import com.vmb.ads_in_app.util.ShareRateUtil;
import com.vmb.ads_in_app.util.SharedPreferencesUtil;
import com.vmb.ads_in_app.util.ToastUtil;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, IUpdateNewVersion {

    private ViewGroup layout_dialog;

    private TextView lbl_title;
    private TextView lbl_content;

    private ImageView img_close;

    private Button btn_a;
    private Button btn_b;
    private Button btn_ok;

    private boolean show_rate = false;
    private boolean require_update = false;

    LinearLayout txtTV2019, txtTVTronDoi, txtVanKhan, txtHoangDao, txtChitay, txtNotRuoi, txtGieoQue, txtTinhYeu;
    //AdView //mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(getResources().getColor(android.R.color.black));
        }

        setContentView(R.layout.activity_main);

        GetConfig.init(MainActivity.this, MainActivity.this);

        /*MobileAds.initialize(this, getResources().getString(R.string.ADMOB_APP_ID));*/
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initView();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);

        new Thread(new Runnable() {
            @Override
            public void run() {
                CountryCodeUtil.setCountryCode(getApplicationContext(),
                        Config.CODE_CONTROL_APP, Config.VERSION_APP, Config.PACKAGE_NAME);
                PrintKeyHash.print(getApplicationContext());

                int count_play = SharedPreferencesUtil.getPrefferInt(getApplicationContext(),
                        LibrayData.KeySharePrefference.COUNT_PLAY, 0);
                count_play++;
                SharedPreferencesUtil.putPrefferInt(getApplicationContext(),
                        LibrayData.KeySharePrefference.COUNT_PLAY, count_play);
                boolean rate = SharedPreferencesUtil.getPrefferBool(getApplicationContext(),
                        LibrayData.KeySharePrefference.SHOW_RATE, false);
                if (!rate) {
                    if (count_play >= 5)
                        show_rate = true;
                }

                RefreshToken.getInstance().checkSendToken(getApplicationContext(),
                        Config.CODE_CONTROL_APP, Config.VERSION_APP, Config.PACKAGE_NAME);
            }
        }).start();
    }

    private void initView() {
        ////mAdView = findViewById(R.id.adView);
        ////Utils.adView(MainActivity.this,//mAdView);
        txtTV2019 = findViewById(R.id.txt_tv_2019);
        txtVanKhan = findViewById(R.id.txt_van_khan);
        txtHoangDao = findViewById(R.id.txt_tv_hoang_dao);
        txtChitay = findViewById(R.id.txt_boi_chi_tay);
        txtNotRuoi = findViewById(R.id.txt_xem_not_ruoi);
        txtGieoQue = findViewById(R.id.txt_gieo_que);
        txtTVTronDoi = findViewById(R.id.txt_tv_tron_doi);
        txtTinhYeu = findViewById(R.id.txt_boi_tinh_yeu);
    }

    public void showRate() {
        show_rate = false;
        SharedPreferencesUtil.putPrefferBool(getApplicationContext(), LibrayData.KeySharePrefference.SHOW_RATE, true);

        lbl_title.setText(R.string.rate_title);
        lbl_content.setText(R.string.rate_content);

        btn_ok.setVisibility(View.GONE);

        btn_a.setText(R.string.share);
        btn_a.setVisibility(View.VISIBLE);
        btn_a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!NetworkUtil.isNetworkAvailable(getApplicationContext())) {
                    ToastUtil.shortToast(getApplicationContext(), getString(R.string.no_internet));
                    return;
                }
                /*ShareRateUtil.showShareFB(MainActivity.this, callbackManager,
                        Config.CODE_CONTROL_APP, Config.VERSION_APP, Config.PACKAGE_NAME);*/
                ShareRateUtil.shareApp(MainActivity.this);
            }
        });

        btn_b.setText(R.string.rate);
        btn_b.setVisibility(View.VISIBLE);
        btn_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!NetworkUtil.isNetworkAvailable(getApplicationContext())) {
                    ToastUtil.shortToast(getApplicationContext(), getString(R.string.no_internet));
                    return;
                }
                ShareRateUtil.rateApp(MainActivity.this);
            }
        });

        img_close.setOnTouchListener(new OnTouchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout_dialog.setVisibility(View.GONE);
            }
        }, getApplicationContext()));

        layout_dialog.setVisibility(View.VISIBLE);
    }

    public void showUpdate() {
        String title = AdsConfig.getInstance().getUpdate_title();
        if (TextUtils.isEmpty(title))
            title = "Update";

        String content = AdsConfig.getInstance().getUpdate_message();
        if (TextUtils.isEmpty(content))
            content = "There is a new version, please update soon !";

        lbl_title.setText(title);
        lbl_content.setText(content);

        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = AdsConfig.getInstance().getUpdate_url();
                if (TextUtils.isEmpty(url))
                    url = "https://play.google.com/store/apps/developer?id=Fruit+Game+Studio";

                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivityForResult(intent, LibrayData.RequestCode.REQUEST_CODE_UPDATE);
            }
        });

        img_close.setOnTouchListener(new OnTouchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (require_update)
                    return;

                layout_dialog.setVisibility(View.GONE);
            }
        }, getApplicationContext()));

        layout_dialog.setVisibility(View.VISIBLE);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            FragmentManager manager = getSupportFragmentManager();
            if (manager.getBackStackEntryCount() > 0) {
                getSupportActionBar().show();
                enableText();
                AdsHandler.getInstance().displayInterstitial(this, false);
                super.onBackPressed();
            } else {
            /*getSupportActionBar().show();
            enableText();*/
                if (findViewById(R.id.layout_dialog).getVisibility() == View.VISIBLE) {
                    if (require_update)
                        return;

                    findViewById(R.id.layout_dialog).setVisibility(View.GONE);
                    return;
                }

                AdsHandler.getInstance().showCofirmDialog(MainActivity.this);
            }
        }
        //Toast.makeText(MainActivity.this,"Nhấn Back thêm lần nữa để thoát!",Toast.LENGTH_SHORT).show();
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_tv_2019) {
            nextFragment(R.id.content_layout, TuViFragment.newInstance(CommonEnum.Tuvi.TUVI_2019.getValue()));
        } else if (id == R.id.nav_tv_2020) {
            nextFragment(R.id.content_layout, TuViFragment.newInstance(CommonEnum.Tuvi.TUVI_2020.getValue()));
        } else if (id == R.id.nav_tv_tron_doi) {
            nextFragment(R.id.content_layout, TuViFragment.newInstance(CommonEnum.Tuvi.TUVI_TRONDOI.getValue()));
        } else if (id == R.id.nav_van_khan) {
            nextFragment(R.id.content_layout, VanKhanFragment.newInstance());
        } else if (id == R.id.nav_hoang_dao) {
            nextFragment(R.id.content_layout, HoangDaoFragment.newInstance());
        } else if (id == R.id.nav_boi_chi_tay) {
            nextFragment(R.id.content_layout, ChiTayFragment.newInstance());
        } else if (id == R.id.nav_xem_not_ruoi) {
            nextFragment(R.id.content_layout, NotRuoiFragment.newInstance());
        } else if (id == R.id.nav_gieo_que) {
            nextFragment(R.id.content_layout, GieoQueFragment.newInstance());
        } else if (id == R.id.nav_boi_tinh_yeu) {
            nextFragment(R.id.content_layout, BoiTinhYeuFragment.newInstance());
            /*if(//mAdView.getVisibility()==View.VISIBLE){
                //mAdView.setVisibility(View.GONE);
            }*/
        } else if (id == R.id.nav_share) {
            shareApp();
        } else if (id == R.id.nav_rate) {
            rateApp(getApplicationContext());
        } else if (id == R.id.nav_policy) {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://raw.githubusercontent.com/congcau37/TuVi2019/master/Privacy%20Policy"));
            startActivity(browserIntent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void nextFragment(int id, android.support.v4.app.Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(id, fragment).addToBackStack(null).commit();
    }

    public void onChange(View view) {
        switch (view.getId()) {
            case R.id.txt_tv_2019:
                nextFragment(R.id.content_layout, TuViFragment.newInstance(CommonEnum.Tuvi.TUVI_2019.getValue()));
                break;
            case R.id.txt_tv_2020:
                nextFragment(R.id.content_layout, TuViFragment.newInstance(CommonEnum.Tuvi.TUVI_2020.getValue()));
                break;
            case R.id.txt_tv_tron_doi:
                nextFragment(R.id.content_layout, TuViFragment.newInstance(CommonEnum.Tuvi.TUVI_TRONDOI.getValue()));
                break;
            case R.id.txt_van_khan:
                nextFragment(R.id.content_layout, VanKhanFragment.newInstance());
                break;
            case R.id.txt_tv_hoang_dao:
                nextFragment(R.id.content_layout, HoangDaoFragment.newInstance());
                break;
            case R.id.txt_boi_chi_tay:
                nextFragment(R.id.content_layout, ChiTayFragment.newInstance());
                break;
            case R.id.txt_xem_not_ruoi:
                nextFragment(R.id.content_layout, NotRuoiFragment.newInstance());
                break;
            case R.id.txt_gieo_que:
                nextFragment(R.id.content_layout, GieoQueFragment.newInstance());
                break;
            case R.id.txt_boi_tinh_yeu:
                nextFragment(R.id.content_layout, BoiTinhYeuFragment.newInstance());
                /*if(//mAdView.getVisibility()==View.VISIBLE){
                    //mAdView.setVisibility(View.GONE);
                }*/
                break;
        }
    }

    public void disableText() {
        txtGieoQue.setVisibility(View.GONE);
        txtNotRuoi.setVisibility(View.GONE);
        txtChitay.setVisibility(View.GONE);
        txtHoangDao.setVisibility(View.GONE);
        txtVanKhan.setVisibility(View.GONE);
        txtTV2019.setVisibility(View.GONE);
        txtTinhYeu.setVisibility(View.GONE);
        txtTVTronDoi.setVisibility(View.GONE);
    }

    public void enableText() {
        txtGieoQue.setVisibility(View.VISIBLE);
        txtNotRuoi.setVisibility(View.VISIBLE);
        txtChitay.setVisibility(View.VISIBLE);
        txtHoangDao.setVisibility(View.VISIBLE);
        txtVanKhan.setVisibility(View.VISIBLE);
        txtTV2019.setVisibility(View.VISIBLE);
        txtTinhYeu.setVisibility(View.VISIBLE);
        txtTVTronDoi.setVisibility(View.VISIBLE);
    }

    private void shareApp() {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        String shareSubText = "Tử Vi 2019 - Tử Vi Trọn Đời";
        String shareBodyText = "https://play.google.com/store/apps/details?id=" + getPackageName();
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, shareSubText);
        shareIntent.putExtra(Intent.EXTRA_TEXT, shareBodyText);
        startActivity(Intent.createChooser(shareIntent, "Share With"));
    }

    public void checkForUpdate(Context context) {
        try {
            context.startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse(context.getString(R.string.url_market_details)
                            + getPackageName())));
        } catch (android.content.ActivityNotFoundException anfe) {
            try {
                context.startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse(context.getString(R.string.url_playstore_app)
                                + getPackageName())));
            } catch (Exception e) {
                Toast.makeText(context,
                        R.string.install_google_play_store,
                        Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void rateApp(Context context) {
        try {
            Uri uri = Uri.parse(context.getString(R.string.url_market_details)
                    + getPackageName());
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            int flags = Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_ACTIVITY_MULTIPLE_TASK;
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT_WATCH)
                flags |= Intent.FLAG_ACTIVITY_NEW_DOCUMENT;
            else
                flags |= Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET;
            intent.addFlags(flags);
            context.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            checkForUpdate(context);
        }
    }

    @Override
    public void onGetConfig(boolean require_update) {
        this.require_update = require_update;
        showUpdate();
    }
}

package com.tuvi.tuvi2019.activity;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import java.util.Calendar;

import com.tuvi.tuvi2019.R;
import com.tuvi.tuvi2019.models.BanMenhModel;
import com.tuvi.tuvi2019.models.BoiKieuModel;
import com.tuvi.tuvi2019.models.CanChiModel;
import com.tuvi.tuvi2019.models.MangModel;
import com.tuvi.tuvi2019.models.NguHanhModel;
import com.tuvi.tuvi2019.models.QueDichModel;
import com.tuvi.tuvi2019.models.TinhYeuModel;
import com.tuvi.tuvi2019.sqlite.TinhYeuController;
import com.tuvi.tuvi2019.utils.AdsManager;
import com.vmb.ads_in_app.handler.AdsHandler;

public class ChiTietTYActivity extends AppCompatActivity {

    Toolbar toolbar;
    AdsManager mAdsManager = null;
    TinhYeuModel mData;
    TinhYeuController controller;
    String ten1, ten2;

    int namSinh1, namSinh2;
    int tongDiem, diem1, diem2, diem3, diem4, diem5;
    TextView txtTen1, txtTen2, txtMang1, txtMang2, txtNguHanh, txtBanMenh, txtQueDich, txtCanChi, txtHoTen, txtTongDiem, txtKetLuan, txtNamsinh1, txtNamSinh2, txtDiemNguHanh, txtDiemBanMenh, txtDiemCanChi, txtDiemHoTen, txtDiemQueDich;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(getResources().getColor(android.R.color.black));
        }

        setContentView(R.layout.activity_chi_tiet_ty);
        if (mAdsManager == null) {
            mAdsManager = new AdsManager(ChiTietTYActivity.this);
        }
        getSupportActionBar().hide();
        initToolbar();
        initView();
        initData();

        AdsHandler.getInstance().displayInterstitial(this, false);
        AdsHandler.getInstance().initBanner(this);
    }

    private void initData() {
        controller = new TinhYeuController(ChiTietTYActivity.this);
        mData = new TinhYeuModel();
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            mData = (TinhYeuModel) bundle.getSerializable("boitinhyeu");
            namSinh1 = bundle.getInt("namsinh1");
            namSinh2 = bundle.getInt("namsinh2");
            txtNamsinh1.setText((namSinh1 + "(" + layCanChi(namSinh1) + ")") + "");
            txtNamSinh2.setText((namSinh2 + "(" + layCanChi(namSinh2) + ")") + "");
            ten1 = bundle.getString("ten1");
            ten2 = bundle.getString("ten2");
            txtTen1.setText("Tên người nam: "+ten1);
            txtTen2.setText("Tên người nữ: "+ten2);
            setMang();
            setBanMenh();
            setCanChi();
            setNguHanh();
            hoTen();
            setQueDich();
            setTongDiem();
            setKetLuan();
        }
    }

    private void initView() {
        txtTen1 = findViewById(R.id.txt_ten1);
        txtTen2 = findViewById(R.id.txt_ten2);
        txtMang1 = findViewById(R.id.txt_mang);
        txtMang2 = findViewById(R.id.txt_mang_2);
        txtNguHanh = findViewById(R.id.txt_ngu_hanh);
        txtBanMenh = findViewById(R.id.txt_ban_menh);
        txtQueDich = findViewById(R.id.txt_que_dich);
        txtCanChi = findViewById(R.id.txt_can_chi);
        txtHoTen = findViewById(R.id.txt_ho_ten);
        txtTongDiem = findViewById(R.id.txt_tong_diem);
        txtKetLuan = findViewById(R.id.txt_ket_luan);
        txtNamsinh1 = findViewById(R.id.txt_nam_sinh_1);
        txtNamSinh2 = findViewById(R.id.txt_nam_sinh_2);
        txtDiemNguHanh = findViewById(R.id.txt_diem_ngu_hanh);
        txtDiemBanMenh = findViewById(R.id.txt_diem_ban_menh);
        txtDiemCanChi = findViewById(R.id.txt_diem_can_chi);
        txtDiemHoTen = findViewById(R.id.txt_diem_ho_ten);
        txtDiemQueDich = findViewById(R.id.txt_diem_que_dich);

    }

    private void initToolbar() {
        toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mAdsManager != null) {
                    mAdsManager.showFullAds(ChiTietTYActivity.this);
                } else {
                    finish();
                    AdsHandler.getInstance().displayInterstitial(ChiTietTYActivity.this, false);
                }
            }
        });
        toolbar.setTitle("Kết quả");
    }

    private void setMang() {
        MangModel mang1 = controller.getMang(mData.getMang1());
        txtMang1.setText(mang1.getMang());
        MangModel mang2 = controller.getMang(mData.getMang2());
        txtMang2.setText(mang2.getMang());
    }

    private void setNguHanh() {
        NguHanhModel nguHanhModel = controller.getNguHanh(mData.getIdNguHanh());
        txtNguHanh.setText(nguHanhModel.getNguHanh());
        diem1 = nguHanhModel.getDiem();
        txtDiemNguHanh.setText(diem1 + "/2");
    }

    private void setBanMenh() {
        BanMenhModel banMenhModel = controller.getBanMenh(mData.getIdBanMenh());
        txtBanMenh.setText(banMenhModel.getBanMenh());
        diem2 = banMenhModel.getDiem();
        txtDiemBanMenh.setText(diem2 + "/2");
    }

    private void setCanChi() {
        CanChiModel canChiModel = controller.getCanChi(mData.getIdCanChi());
        txtCanChi.setText(canChiModel.getCanChi());
        diem3 = canChiModel.getDiem();
        txtDiemCanChi.setText(diem3 + "/2");
    }

    private void setQueDich() {
        QueDichModel queDichModel = controller.getQueDich(mData.getIdQueDich());
        txtQueDich.setText(queDichModel.getQueDich());
        String name1 = txtQueDich.getText().toString().replace("{name-male}",ten1);
        txtQueDich.setText(name1);
        String name2 = txtQueDich.getText().toString().replace("{name-female}",ten2);
        txtQueDich.setText(name2);
        diem4 = queDichModel.getDiem();
        txtDiemQueDich.setText(diem4 + "/2");
    }

    private void setHoTen() {

    }

    private void setTongDiem() {
        tongDiem = diem1 + diem2 + diem3 + diem4 + diem5;
        txtTongDiem.setText(tongDiem + "");
    }

    private void setKetLuan() {
        if (tongDiem < 5) {
            txtKetLuan.setText("Tình cảm có lẽ ở trên mức tình bạn hai bạn cần vun đắp thêm nhiều nữa nhé!");
        } else {
            txtKetLuan.setText("Hai bạn khá hợp nhau, nếu kiên trì nhất định sẽ đạt được hạnh phúc!");

        }
    }


    public String layCanChi(int currentYear) {
        Calendar calendar = Calendar.getInstance();
        String can = "", chi = "";
        String canChi = "";
        // tìm can bằng cách lấy năm đó chia lấy dư cho 10
        switch (currentYear % 10) {
            case 0:
                can = "Canh";
                break;
            case 1:
                can = "Tân";
                break;
            case 2:
                can = "Nhâm";
                break;
            case 3:
                can = "Quý";
                break;
            case 4:
                can = "Giáp";
                break;
            case 5:
                can = "Ất";
                break;
            case 6:
                can = "Bính";
                break;
            case 7:
                can = "Đinh";
                break;
            case 8:
                can = "Mậu";
                break;
            case 9:
                can = "Kỷ";
                break;
        }

        switch (currentYear % 12) {
            case 0:
                chi = "Thân";
                break;
            case 1:
                chi = "Dậu";
                break;
            case 2:
                chi = "Tuất";
                break;
            case 3:
                chi = "Hợi";
                break;
            case 4:
                chi = "Tý";
                break;
            case 5:
                chi = "Sửu";
                break;
            case 6:
                chi = "Dần";
                break;
            case 7:
                chi = "Mạo";
                break;
            case 8:
                chi = "Thìn";
                break;
            case 9:
                chi = "Tỵ";
                break;
            case 10:
                chi = "Ngọ";
                break;
            case 11:
                chi = "Mùi";
                break;
        }
        canChi = can + " " + chi;
        return canChi;
    }

    private void hoTen() {
        int id = 1;
        String kyTu = ten1.charAt(0) + "";
        Log.d("kytu", kyTu);
        String[] array = new String[]{"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
        for (int i = 0; i < 9; i++) {
            if (kyTu.equals(array[i])) ;
            {
                BoiKieuModel boiKieuModel = controller.getBoiKieu(id);
                txtHoTen.setText(boiKieuModel.getBoiKieu());
                diem5 = boiKieuModel.getDiem();
                txtDiemHoTen.setText(diem5 + "/2");
            }
            id++;
        }
        id = 1;
        for (int i = 9; i < 18; i++) {
            if (kyTu.equals(array[i])) ;
            {
                BoiKieuModel boiKieuModel = controller.getBoiKieu(id);
                txtHoTen.setText(boiKieuModel.getBoiKieu());
                diem5 = boiKieuModel.getDiem();
                txtDiemHoTen.setText(diem5 + "/2");
            }
            id++;
        }
        id = 1;
        for (int i = 19; i <= 25; i++) {
            if (kyTu.equals(array[i])) ;
            {
                BoiKieuModel boiKieuModel = controller.getBoiKieu(id);
                txtHoTen.setText(boiKieuModel.getBoiKieu());
                diem5 = boiKieuModel.getDiem();
                txtDiemHoTen.setText(diem5 + "/2");
            }
            id++;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        /*if (mAdsManager != null) {
            mAdsManager.showFullAds(ChiTietTYActivity.this);
        } else {
            finish();
        }*/
        AdsHandler.getInstance().displayInterstitial(this, false);
        finish();
    }
}

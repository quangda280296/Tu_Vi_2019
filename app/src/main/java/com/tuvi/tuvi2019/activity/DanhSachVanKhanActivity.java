package com.tuvi.tuvi2019.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;

import com.tuvi.tuvi2019.R;
import com.tuvi.tuvi2019.adapter.TextAdapter;
import com.tuvi.tuvi2019.utils.AdsManager;
import com.vmb.ads_in_app.handler.AdsHandler;

import java.util.ArrayList;

public class DanhSachVanKhanActivity extends AppCompatActivity {

    ArrayList<String> nguyenDanData, tietData, ramData, tucVongData, giaiHanData, tangLeData, cungGioData, thanlinhData, chuaData, dinhDenMieuPhuData;
    ListView lv;
    TextAdapter adapter;
    int vanKhan;
    Toolbar toolbar;
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

        setContentView(R.layout.activity_danh_sach_van_khan);
        if (mAdsManager == null) {
            mAdsManager = new AdsManager(DanhSachVanKhanActivity.this);
        }
        getSupportActionBar().hide();
        initData();
        initToolbar();
        initView();

        AdsHandler.getInstance().displayInterstitial(this, false);
        AdsHandler.getInstance().initBanner(this);
    }

    private void initView() {
        ////mAdView = findViewById(R.id.adView);
        //Utils.adView(DanhSachVanKhanActivity.this,//mAdView);
        lv = findViewById(R.id.lv_van_khan);
        Intent intent = getIntent();
        vanKhan = intent.getIntExtra("vankhan", 000);
        switch (vanKhan) {
            case 0:
                adapter = new TextAdapter(DanhSachVanKhanActivity.this, R.layout.custom_item_text_list, nguyenDanData);
                break;
            case 1:
                adapter = new TextAdapter(DanhSachVanKhanActivity.this, R.layout.custom_item_text_list, tietData);
                break;
            case 2:
                adapter = new TextAdapter(DanhSachVanKhanActivity.this, R.layout.custom_item_text_list, ramData);
                break;
            case 3:
                adapter = new TextAdapter(DanhSachVanKhanActivity.this, R.layout.custom_item_text_list, tucVongData);
                break;
            case 4:
                adapter = new TextAdapter(DanhSachVanKhanActivity.this, R.layout.custom_item_text_list, giaiHanData);
                break;
            case 5:
                adapter = new TextAdapter(DanhSachVanKhanActivity.this, R.layout.custom_item_text_list, tangLeData);
                break;
            case 6:
                adapter = new TextAdapter(DanhSachVanKhanActivity.this, R.layout.custom_item_text_list, cungGioData);
                break;
            case 7:
                adapter = new TextAdapter(DanhSachVanKhanActivity.this, R.layout.custom_item_text_list, thanlinhData);
                break;
            case 8:
                adapter = new TextAdapter(DanhSachVanKhanActivity.this, R.layout.custom_item_text_list, chuaData);
                break;
            case 9:
                adapter = new TextAdapter(DanhSachVanKhanActivity.this, R.layout.custom_item_text_list, dinhDenMieuPhuData);
                break;
        }
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent1 = new Intent(DanhSachVanKhanActivity.this, ChiTietVanKhanActivity.class);
                intent1.putExtra("vankhan", vanKhan + 1);
                intent1.putExtra("noidungvankhan", i + 1);
                startActivity(intent1);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        AdsHandler.getInstance().displayInterstitial(this, false);
        finish();
    }

    private void initToolbar() {
        toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                AdsHandler.getInstance().displayInterstitial(DanhSachVanKhanActivity.this, false);
            }
        });
        toolbar.setTitle("Văn khấn");
    }

    private void initData() {
        nguyenDanData = new ArrayList<>();
        nguyenDanData.add("Ông táo chầu trời");
        nguyenDanData.add("Lễ chạp");
        nguyenDanData.add("Tất niên");
        nguyenDanData.add("Giao thừa ngoài trời");
        nguyenDanData.add("Giao thừa trong nhà");
        nguyenDanData.add("Thần linh trong nhà(Ngày mùng 1 Tết)");
        nguyenDanData.add("Tổ tiên(Ngày mùng 1 Tết)");
        nguyenDanData.add("Hóa vàng");

        tietData = new ArrayList<>();
        tietData.add("Tết nguyên tiêu");
        tietData.add("Rằm thánh giêng");
        tietData.add("Thanh minh(Lễ âm phần, Long Mạnh, Sơn Thần Thố phủ nơi mộ)");
        tietData.add("Thành minh(Lễ vong ngoài mộ)");
        tietData.add("Tết Hàn Thực(3/3)");
        tietData.add("Tết Đoan Ngọ(5/5)");
        tietData.add("Rằm tháng 7(Khấn thần linh tại nhà)");
        tietData.add("Rằm tháng 7(Cúng phóng sinh)");
        tietData.add("Tết trung thu(Cúng tổ tiên 15/8 âm lịch)");
        tietData.add("Tết hạ nguyên(Văn khấn tổ tiên - Tết cơm mới)");

        ramData = new ArrayList<>();
        ramData.add("Tổ công và các vị thần");
        ramData.add("Gia tiên");

        tucVongData = new ArrayList<>();
        tucVongData.add("Cúng mụ");
        tucVongData.add("Cúng mụ(Đầy năm)");
        tucVongData.add("Cưới gả yết cáo gia thân, gia tiên");
        tucVongData.add("Động thổ");
        tucVongData.add("Nhập trạch(Văn khấn thần linh)");
        tucVongData.add("Nhập trạch(Văn khấn yết cáo Gia tiên)");
        tucVongData.add("Lễ tân gia(yết cáo Táo quân tổ thần)");
        tucVongData.add("Lễ tân gia(Văn khấn Gia tiên)");
        tucVongData.add("Khai trương cửa hàng");
        tucVongData.add("Bồi hoàng địa mạch");

        giaiHanData = new ArrayList<>();
        giaiHanData.add("Giải hạn sao Thái Dương");
        giaiHanData.add("Giải hạn sao Thái Âm");
        giaiHanData.add("Giải hạn sao Mộc Đức");
        giaiHanData.add("Giải hạn sao Vân Hán");
        giaiHanData.add("Giải hạn sao Thổ Tú");
        giaiHanData.add("Giải hạn sao Thái Bạch");
        giaiHanData.add("Giải hạn sao Thủy Diệu");
        giaiHanData.add("Giải hạn sao La Hầu");
        giaiHanData.add("Giải hạn sao Kế Đô");

        tangLeData = new ArrayList<>();
        tangLeData.add("Ý nghĩa văn khấn trong tang lễ");
        tangLeData.add("Lễ Thiết Linh");
        tangLeData.add("Lễ Thanh Phục");
        tangLeData.add("Lễ Chúc Thực");
        tangLeData.add("Lễ cáo Long Thần Thổ Địa");
        tangLeData.add("Lễ Thành Phần");
        tangLeData.add("Lễ Hồi Linh");
        tangLeData.add("Lễ Chầu Tổ");
        tangLeData.add("Lễ Tế Ngu");
        tangLeData.add("Lễ Chung Thất và Tốt Khốc");
        tangLeData.add("Lễ Triệu Tịch Điện Văn");
        tangLeData.add("Lễ Tiểu Tường, Đại Tường");
        tangLeData.add("Lễ Đàm Tế");
        tangLeData.add("Lễ rước linh vị vào chính điện yết cáo Tiên Tổ");
        tangLeData.add("Lễ Cải Cát");
        tangLeData.add("Cách miền trần thế");
        tangLeData.add("Long Mạch Sơn Thần Thổ Thần");

        cungGioData = new ArrayList<>();
        cungGioData.add("Ý nghĩa khi cúng giỗ");
        cungGioData.add("Thổ Thần, Táo Quân, Long Mạch và các vị Thần Linh trước khi Giỗ đầu");
        cungGioData.add("Giỗ đầu");
        cungGioData.add("Thổ Thần, Táo Quân, Long Mạch, và các vị Thần Linh trước khi Giỗ hết");
        cungGioData.add("Giỗ hết(Đại tường)");
        cungGioData.add("Thổ Thần, Táo Quân, Long Mạch và các vị Thần Linh vào ngày giỗ Tiên Thường(Cát Kỵ)");
        cungGioData.add("Giỗ Tiên Thường(Cát Kỵ)");
        cungGioData.add("Thổ Thần, Táo Quân, Long Mạch và các vị Thần Linh vào các ngày giỗ Thường()Cát Kỵ ");
        cungGioData.add("Giỗ Thường(Cát Kỵ)");

        thanlinhData = new ArrayList<>();
        thanlinhData.add("Thổ Công");
        thanlinhData.add("Thần Tài");
        thanlinhData.add("Thánh Sứ");
        thanlinhData.add("Tiền Chủ");

        chuaData = new ArrayList<>();
        chuaData.add("Tại chùa");
        chuaData.add("Lễ Phật");
        chuaData.add("Lễ Đức Ông");
        chuaData.add("Lễ Đức Thánh Hiền");
        chuaData.add("Cầu tài, cầu lộ, cầu bình an, ở Tam Bảo");
        chuaData.add("Lễ Đức dịa Tạng Vương Bồ Tát(U Minh giáo chủ)");
        chuaData.add("Lễ Đức Quan Thế Âm Bồ Tát(Phật bà quan âm)");
        chuaData.add("Xin giải trừ bệnh tật");

        dinhDenMieuPhuData = new ArrayList<>();
        dinhDenMieuPhuData.add("Lễ Thành Hoàng ở Đình, Đền, Miếu");
        dinhDenMieuPhuData.add("Lễ Tân tòa Thánh mẫu");
        dinhDenMieuPhuData.add("Lễ Mẫu Thượng Ngàn");
        dinhDenMieuPhuData.add("Lễ Mẫu Thượng Thiên");
        dinhDenMieuPhuData.add("Ban Công Đồng");
        dinhDenMieuPhuData.add("Lễ Đức Thánh)");
        dinhDenMieuPhuData.add("Đền Bà Chú Kho");
        dinhDenMieuPhuData.add("Lễ Thánh Mẫu Liễu Hạnh");
    }
}
package com.tuvi.tuvi2019.fragment;


import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.tuvi.tuvi2019.R;
import com.tuvi.tuvi2019.activity.ChiTietTYActivity;
import com.tuvi.tuvi2019.activity.MainActivity;
import com.tuvi.tuvi2019.models.TinhYeuModel;
import com.tuvi.tuvi2019.sqlite.DBHelper;
import com.tuvi.tuvi2019.sqlite.TinhYeuController;
import com.vmb.ads_in_app.handler.AdsHandler;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 */
public class BoiTinhYeuFragment extends Fragment {

    View view;
    Toolbar toolbar;
    TextView txtNgaySinh1, txtNgaySinh2;
    EditText edtTen1, edtTen2;
    Button btnXem;
    int namSinh1, namSinh2;
    TinhYeuModel mData;

    TinhYeuController controller;

    public static BoiTinhYeuFragment newInstance() {
        BoiTinhYeuFragment fragment = new BoiTinhYeuFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_boi_tinh_yeu, container, false);
        ((MainActivity) getActivity()).getSupportActionBar().hide();
        ((MainActivity) getActivity()).disableText();
        initToolbar();
        initData();
        initView();
        return view;
    }

    private void initData() {
        DBHelper sql = new DBHelper(getActivity());
        try {
            sql.createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }
        controller = new TinhYeuController(getActivity());
        mData = new TinhYeuModel();
        AdsHandler.getInstance().displayInterstitial(getActivity(), false);
        /*AdsHandler.getInstance().initBanner(getActivity(), LibrayData.AdsSize.BANNER,
                (ViewGroup) view.findViewById(R.id.adView_boi_tinh_yeu));*/
    }


    private void initView() {
        txtNgaySinh1 = view.findViewById(R.id.txt_nam_sinh_1);
        txtNgaySinh2 = view.findViewById(R.id.txt_nam_sinh_2);
        edtTen1 = view.findViewById(R.id.edt_ten_1);
        edtTen2 = view.findViewById(R.id.edt_ten_2);
        btnXem = view.findViewById(R.id.img_xem);
        txtNgaySinh1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chonNgay(1);
            }
        });
        txtNgaySinh2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chonNgay(2);
            }
        });

        btnXem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (txtNgaySinh1.getText().toString().equals("")) {
                    Toast.makeText(getActivity(), "Bạn chưa nhập ngày sinh người Nam", Toast.LENGTH_SHORT).show();
                } else if (txtNgaySinh2.getText().toString().equals("")) {
                    Toast.makeText(getActivity(), "Bạn chưa nhập ngày sinh người Nữ", Toast.LENGTH_SHORT).show();
                } else if (namSinh1 > 2014) {
                    Toast.makeText(getActivity(), "Năm sinh của người Nam phải trước năm 2014", Toast.LENGTH_SHORT).show();
                } else if (namSinh2 > 2014) {
                    Toast.makeText(getActivity(), "Năm sinh của người Nữ phải trước năm 2014", Toast.LENGTH_SHORT).show();
                } else if (edtTen1.getText().toString().equals("")) {
                    Toast.makeText(getActivity(), "Bạn chưa nhập tên người Nam", Toast.LENGTH_SHORT).show();
                } else if (edtTen2.getText().toString().equals("")) {
                    Toast.makeText(getActivity(), "Bạn chưa nhập tên người Nữ", Toast.LENGTH_SHORT).show();
                } else {
                    mData = controller.getListTinhYeu("", "", namSinh1, namSinh2);
                    Intent intent = new Intent(getActivity(), ChiTietTYActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("boitinhyeu", mData);
                    bundle.putInt("namsinh1", namSinh1);
                    bundle.putInt("namsinh2", namSinh2);
                    bundle.putString("ten1", edtTen1.getText().toString());
                    bundle.putString("ten2", edtTen2.getText().toString());
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
                hideKeyboard(getActivity());
                getActivity().getSupportFragmentManager().beginTransaction().remove(BoiTinhYeuFragment.this).commit();
                ((MainActivity) getActivity()).getSupportActionBar().show();
                ((MainActivity) getActivity()).enableText();
                AdsHandler.getInstance().displayInterstitial(getActivity(), false);
            }
        });
        toolbar.setTitle("Bói tình yêu");
    }

    private void chonNgay(final int check) {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                calendar.set(year, monthOfYear, dayOfMonth);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                if (check == 1) {
                    namSinh1 = year;
                    txtNgaySinh1.setText(simpleDateFormat.format(calendar.getTime()));
                } else {
                    namSinh2 = year;
                    txtNgaySinh2.setText(simpleDateFormat.format(calendar.getTime()));
                }
            }
        }, year, month, day);
        datePickerDialog.show();
    }

    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        View view = activity.getCurrentFocus();
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}

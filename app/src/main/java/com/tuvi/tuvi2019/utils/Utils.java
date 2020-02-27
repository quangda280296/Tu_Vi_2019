package com.tuvi.tuvi2019.utils;

import android.content.Context;

import android.util.Log;
import android.view.View;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import com.tuvi.tuvi2019.models.Congiap;

public class Utils {
    public static String loadJSONFromAsset(Context context) {
        String json = null;
        try {
            InputStream is = context.getAssets().open("2019/data.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        Log.d("json",json);
        return json;
    }

    public static ArrayList<Congiap> getData(String json){
        ArrayList<Congiap> list = new ArrayList<>();
        try {
           JSONArray jsonarray = new JSONArray(json);
           for (int i = 0; i < jsonarray.length(); i++) {
               JSONObject jsonobject = jsonarray.getJSONObject(i);
               Congiap congiap = new Congiap();
               congiap.setTitle(jsonobject.getString("title"));
               congiap.setYear(jsonobject.getString("year"));
               congiap.setType(jsonobject.getString("type"));
               list.add(congiap);
           }
       } catch (JSONException e) {
           e.printStackTrace();
       }
        return list;
    }

    public static void adView(Context context, final AdView adView) {
        /*AdRequest adRequest = new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build();
        adView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                adView.setVisibility(View.VISIBLE);
            }
        });
        adView.loadAd(adRequest);*/
    }

}

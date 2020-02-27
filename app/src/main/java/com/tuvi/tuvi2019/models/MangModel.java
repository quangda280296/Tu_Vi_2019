package com.tuvi.tuvi2019.models;

public class MangModel {
    int id;
    String mang;

    public MangModel(int id, String mang) {
        this.id = id;
        this.mang = mang;
    }

    public MangModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMang() {
        return mang;
    }

    public void setMang(String mang) {
        this.mang = mang;
    }
}

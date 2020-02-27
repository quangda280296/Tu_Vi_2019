package com.tuvi.tuvi2019.models;

public class BanMenhModel {
    int id;
    String banMenh;
    int diem;

    public BanMenhModel(int id, String banMenh, int diem) {
        this.id = id;
        this.banMenh = banMenh;
        this.diem = diem;
    }

    public BanMenhModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBanMenh() {
        return banMenh;
    }

    public void setBanMenh(String banMenh) {
        this.banMenh = banMenh;
    }

    public int getDiem() {
        return diem;
    }

    public void setDiem(int diem) {
        this.diem = diem;
    }
}

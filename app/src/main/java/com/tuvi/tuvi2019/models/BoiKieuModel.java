package com.tuvi.tuvi2019.models;

public class BoiKieuModel {
    int id;
    String boiKieu;
    int diem;

    public BoiKieuModel(int id, String boiKieu, int diem) {
        this.id = id;
        this.boiKieu = boiKieu;
        this.diem = diem;
    }

    public BoiKieuModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBoiKieu() {
        return boiKieu;
    }

    public void setBoiKieu(String boiKieu) {
        this.boiKieu = boiKieu;
    }

    public int getDiem() {
        return diem;
    }

    public void setDiem(int diem) {
        this.diem = diem;
    }
}

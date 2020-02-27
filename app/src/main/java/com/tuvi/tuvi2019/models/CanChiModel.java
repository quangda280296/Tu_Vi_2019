package com.tuvi.tuvi2019.models;

public class CanChiModel {
    int id;
    String canChi;
    int diem;

    public CanChiModel(int id, String canChi, int diem) {
        this.id = id;
        this.canChi = canChi;
        this.diem = diem;
    }

    public CanChiModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCanChi() {
        return canChi;
    }

    public void setCanChi(String canChi) {
        this.canChi = canChi;
    }

    public int getDiem() {
        return diem;
    }

    public void setDiem(int diem) {
        this.diem = diem;
    }
}

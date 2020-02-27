package com.tuvi.tuvi2019.models;

public class NguHanhModel {
    int id;
    String nguHanh;
    int diem;

    public NguHanhModel(int id, String nguHanh, int diem) {
        this.id = id;
        this.nguHanh = nguHanh;
        this.diem = diem;
    }

    public NguHanhModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNguHanh() {
        return nguHanh;
    }

    public void setNguHanh(String nguHanh) {
        this.nguHanh = nguHanh;
    }

    public int getDiem() {
        return diem;
    }

    public void setDiem(int diem) {
        this.diem = diem;
    }
}

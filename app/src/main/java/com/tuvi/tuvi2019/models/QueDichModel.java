package com.tuvi.tuvi2019.models;

public class QueDichModel {
    int id;
    int que;
    String queDich;
    int diem;

    public QueDichModel(int id, int que, String queDich, int diem) {
        this.id = id;
        this.que = que;
        this.queDich = queDich;
        this.diem = diem;
    }

    public QueDichModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQue() {
        return que;
    }

    public void setQue(int que) {
        this.que = que;
    }

    public String getQueDich() {
        return queDich;
    }

    public void setQueDich(String queDich) {
        this.queDich = queDich;
    }

    public int getDiem() {
        return diem;
    }

    public void setDiem(int diem) {
        this.diem = diem;
    }
}

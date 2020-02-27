package com.tuvi.tuvi2019.models;

import java.io.Serializable;

public class TinhYeuModel implements Serializable {

    int id;
    int namSinh1;
    int namSinh2;
    int mang1;
    int mang2;
    int idNguHanh;
    int idBanMenh;
    int idCanChi;
    int idQueDich;

    public TinhYeuModel(int id, int namSinh1, int namSinh2, int mang1, int mang2, int idNguHanh, int idBanMenh, int idCanChi, int idQueDich) {
        this.id = id;
        this.namSinh1 = namSinh1;
        this.namSinh2 = namSinh2;
        this.mang1 = mang1;
        this.mang2 = mang2;
        this.idNguHanh = idNguHanh;
        this.idBanMenh = idBanMenh;
        this.idCanChi = idCanChi;
        this.idQueDich = idQueDich;
    }

    public TinhYeuModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNamSinh1() {
        return namSinh1;
    }

    public void setNamSinh1(int namSinh1) {
        this.namSinh1 = namSinh1;
    }

    public int getNamSinh2() {
        return namSinh2;
    }

    public void setNamSinh2(int namSinh2) {
        this.namSinh2 = namSinh2;
    }

    public int getMang1() {
        return mang1;
    }

    public void setMang1(int mang1) {
        this.mang1 = mang1;
    }

    public int getMang2() {
        return mang2;
    }

    public void setMang2(int mang2) {
        this.mang2 = mang2;
    }

    public int getIdNguHanh() {
        return idNguHanh;
    }

    public void setIdNguHanh(int idNguHanh) {
        this.idNguHanh = idNguHanh;
    }

    public int getIdBanMenh() {
        return idBanMenh;
    }

    public void setIdBanMenh(int idBanMenh) {
        this.idBanMenh = idBanMenh;
    }

    public int getIdCanChi() {
        return idCanChi;
    }

    public void setIdCanChi(int idCanChi) {
        this.idCanChi = idCanChi;
    }

    public int getIdQueDich() {
        return idQueDich;
    }

    public void setIdQueDich(int idQueDich) {
        this.idQueDich = idQueDich;
    }
}
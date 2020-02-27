package com.tuvi.tuvi2019.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Congiap implements Parcelable {
    private String title;
    private String year;
    private String type;

    protected Congiap(Parcel in) {
        title = in.readString();
        year = in.readString();
        type = in.readString();
    }

    public static final Creator<Congiap> CREATOR = new Creator<Congiap>() {
        @Override
        public Congiap createFromParcel(Parcel in) {
            return new Congiap(in);
        }

        @Override
        public Congiap[] newArray(int size) {
            return new Congiap[size];
        }
    };

    public Congiap() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeString(year);
        parcel.writeString(type);
    }
}

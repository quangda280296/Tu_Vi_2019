package com.tuvi.tuvi2019.sqlite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.tuvi.tuvi2019.models.BanMenhModel;
import com.tuvi.tuvi2019.models.BoiKieuModel;
import com.tuvi.tuvi2019.models.CanChiModel;
import com.tuvi.tuvi2019.models.MangModel;
import com.tuvi.tuvi2019.models.NguHanhModel;
import com.tuvi.tuvi2019.models.QueDichModel;
import com.tuvi.tuvi2019.models.TinhYeuModel;

public class TinhYeuController {
    private DBHelper dbHelper;

    public TinhYeuController(Context context) {
        dbHelper = new DBHelper(context);
    }

    public TinhYeuModel getListTinhYeu (String ten1, String ten2, int namSinh1, int namSinh2) {

        TinhYeuModel item = new TinhYeuModel();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM boitinhyeu WHERE nam_sinh_1= '"+namSinh1+"' AND nam_sinh_2='"+namSinh2+"'",null);
        cursor.moveToFirst();
        do {
            item = new TinhYeuModel(cursor.getInt(0),cursor.getInt(1),cursor.getInt(2),cursor.getInt(3),cursor.getInt(4),cursor.getInt(5),cursor.getInt(6),cursor.getInt(7),cursor.getInt(8));
        } while (cursor.moveToNext());
        return item;
    }

    public MangModel getMang(int mang){
        MangModel item ;
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM mang WHERE id= '"+mang+"'",null);
        cursor.moveToFirst();
        do {
            item = new MangModel(cursor.getInt(0),cursor.getString(1));
        } while (cursor.moveToNext());
        return item;
    }

    public CanChiModel getCanChi(int canChi){
        CanChiModel item;
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM canchi WHERE id= '"+canChi+"'",null);
        cursor.moveToFirst();
        do {
            item = new CanChiModel(cursor.getInt(0),cursor.getString(1),cursor.getInt(2));
        } while (cursor.moveToNext());
        return item;
    }

    public BanMenhModel getBanMenh(int banMenh){
        BanMenhModel item;
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM banmenh WHERE id= '"+banMenh+"'",null);
        cursor.moveToFirst();
        do {
            item = new BanMenhModel(cursor.getInt(0),cursor.getString(1),cursor.getInt(2));
        } while (cursor.moveToNext());
        return item;
    }

    public BoiKieuModel getBoiKieu(int boiKieu){
        BoiKieuModel item;
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM boikieu WHERE id= '"+boiKieu+"'",null);
        cursor.moveToFirst();
        do {
            item = new BoiKieuModel(cursor.getInt(0),cursor.getString(1),cursor.getInt(2));
        } while (cursor.moveToNext());
        return item;
    }

    public NguHanhModel getNguHanh(int nguHanh){
        NguHanhModel item;
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM nguhanh WHERE id= '"+nguHanh+"'",null);
        cursor.moveToFirst();
        do {
            item = new NguHanhModel(cursor.getInt(0),cursor.getString(1),cursor.getInt(2));
        } while (cursor.moveToNext());
        return item;
    }

    public QueDichModel getQueDich(int queDich){
        QueDichModel item;
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM quedich WHERE id= '"+queDich+"'",null);
        cursor.moveToFirst();
        do {
            item = new QueDichModel(cursor.getInt(0),cursor.getInt(1),cursor.getString(2),cursor.getInt(3));
        } while (cursor.moveToNext());
        return item;
    }

}

package com.fpoly.nguyenvietanh_ph32739;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.util.ArrayList;

public class LapTopDAO {

    DBHelper dbHelper;
    Context context;

    public LapTopDAO(DBHelper dbHelper, Context context) {
        this.dbHelper = dbHelper;
        this.context = context;
    }

    public boolean themLapTop(LapTop lapTop) {
        SQLiteDatabase sql = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Malt", lapTop.getMaLapTop());
        values.put("tenlt", lapTop.getTenLapTop());
        values.put("gialt", lapTop.getGiaLapTop());
        values.put("khuyenMai", lapTop.getKhuyenMai());
        long result = sql.insert("Laptop", null, values);

        if (result > 0) {
            Toast.makeText(context, "Thêm  thành công", Toast.LENGTH_SHORT).show();
            return true;
        } else {
            Toast.makeText(context, "Thêm thất bại - Mã đã tồn tại", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    public void xoaLapTop(LapTop lapTop) {
        SQLiteDatabase sql = dbHelper.getReadableDatabase();

        long result = sql.delete("Laptop", " Malt   = ?", new String[]{lapTop.getMaLapTop()});

        if (result > 0) {
            Toast.makeText(context, "Xoá  thành công", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Xoá thất bại ", Toast.LENGTH_SHORT).show();
        }
    }

    public ArrayList<LapTop> getList() {
        ArrayList<LapTop> list = new ArrayList<>();
        SQLiteDatabase sql = dbHelper.getWritableDatabase();
        Cursor cursor = sql.rawQuery("SELECT * FROM Laptop", null);
        sql.beginTransaction();
        try {
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                do {
                    String getMalt = cursor.getString(0);
                    String getTenLt = cursor.getString(1);
                    int getGia = cursor.getInt(2);
                    int khuyenMai = cursor.getInt(3);
                    list.add(new LapTop(getMalt, getTenLt, getGia, khuyenMai));

                } while (cursor.moveToNext());
                cursor.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sql.endTransaction();
        }

        return list;
    }

}

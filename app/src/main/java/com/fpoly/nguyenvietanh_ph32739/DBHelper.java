package com.fpoly.nguyenvietanh_ph32739;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(@Nullable Context context) {
        super(context, "name.db", null, 1);
    }

    String createTable = "CREATE TABLE Laptop(Malt TEXT PRIMARY KEY, tenlt TEXT, gialt TEXT, khuyenMai TEXT)";

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

package com.xcy.fzbcity.all.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String USER_TABLE_NAME = "useruser";
    public static final String USER_NAME = "USER_NAME";
    public static final String USER_PASSWORD = "USER_PASSWORD";
    public static final String DATABASE_NAME = "database.db";
    public static final int VERSION = 1;
    public static final String ID = "ID";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + USER_TABLE_NAME + "(" + "ID INTEGER PRIMARY KEY AUTOINCREMENT ," + "USER_NAME varchar(20) not null,"  + "USER_PASSWORD varchar(10) not null" + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

package com.xcy.fzbcity.all.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class InformeDatabaseHelper extends SQLiteOpenHelper {

    public static final String USER_TABLE_NAME = "informeuseruser";
    public static final String TITLE = "TITLE";
    public static final String CONTENT = "CONTENT";
    public static final String TYPE = "TYPE";
    public static final String DATA = "DATA";
    public static final String USERID = "USERID";
    public static final String ISREAD = "ISREAD";
    public static final String INFORMEID = "INFORMEID";
    public static final String SUBTYPE = "SUBTYPE";
    public static final String COMMONID = "COMMONID";
    public static final String STATE = "STATE";
    public static final String ISENABLE = "ISENABLE";
    public static final String DATABASE_NAME = "informedatabase.db";
    public static final int VERSION = 1;
    public static final String ID = "ID";

    public InformeDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + USER_TABLE_NAME + "(" + "ID INTEGER PRIMARY KEY AUTOINCREMENT,"+"INFORMEID varchar(10000) not null ," + "TITLE varchar(10000) not null" +
                    ","  + "CONTENT varchar(10000) not null" + "" + ","+" TYPE varchar(10000) not null, "+" DATA varchar(10000) not null,"+"USERID varchar(10000) not null," +
                    ""+"ISREAD varchar(10000) not null,"+"SUBTYPE varchar(10000) not null,"+"COMMONID varchar(10000) not null,"+"STATE varchar(10000) not null" +
                ","+"ISENABLE varchar(10000) not null)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

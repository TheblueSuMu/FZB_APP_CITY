package com.xcy.fzb.all.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataBaseUtil {


    public static DatabaseHelper mDatabaseHelper;
    public static SQLiteDatabase mSqLiteDatabase;

    //TODO 查
    public static List<DataBase> initSelect(Context context, String selectData) {

        mDatabaseHelper = new DatabaseHelper(context);
        mSqLiteDatabase = mDatabaseHelper.getWritableDatabase();
        List<DataBase> list = new ArrayList<>();
        list.clear();
        Cursor cursor = mSqLiteDatabase.query(mDatabaseHelper.USER_TABLE_NAME, null, null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                if (selectData.equals("")) {
                    String username = cursor.getString(cursor.getColumnIndex("username"));
                    String userpassword = cursor.getString(cursor.getColumnIndex("userpassword"));
                    DataBase dataBase = new DataBase(username, userpassword);
                    list.add(dataBase);
                } else {
                    String username = cursor.getString(cursor.getColumnIndex("username"));
                    String userpassword = cursor.getString(cursor.getColumnIndex("userpassword"));
                    if (username.equals(selectData)) {
                        DataBase dataBase = new DataBase(username, userpassword);
                        list.add(dataBase);
                        break;
                    }
                }
            } while (cursor.moveToNext());
        }
        cursor.close();

        return list;

    }

    //TODO 改
    public static int initUpData(Context context, String userName, String userPassword) {

        mDatabaseHelper = new DatabaseHelper(context);
        mSqLiteDatabase = mDatabaseHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.USER_NAME, userName);

        String conditions = "username=?";
        String[] valueStr = {userPassword};

        int affectNum = mSqLiteDatabase.update(DatabaseHelper.USER_TABLE_NAME, contentValues, conditions, valueStr);

        return affectNum;
    }

    //TODO 删
    public static int initDelete(Context context, String userName) {

        mDatabaseHelper = new DatabaseHelper(context);
        mSqLiteDatabase = mDatabaseHelper.getWritableDatabase();

        String conditions = "username=?";
        String[] args = {userName};
        int numbers = mSqLiteDatabase.delete(DatabaseHelper.USER_TABLE_NAME, conditions, args);
        return numbers;
    }

    //TODO 增
    public static long initAdd(Context context, String userName, String userPassword) {

        mDatabaseHelper = new DatabaseHelper(context);
        mSqLiteDatabase = mDatabaseHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(mDatabaseHelper.USER_NAME, userName);
        values.put(mDatabaseHelper.USER_PASSWORD, userPassword);

        long index = mSqLiteDatabase.insert(mDatabaseHelper.USER_TABLE_NAME, null, values);

        if (index != -1) {
            Toast.makeText(context, "插入成功", Toast.LENGTH_LONG).show();
        }

        return index;

    }

    //TODO 长度
    public static int initSize(Context context) {

        mDatabaseHelper = new DatabaseHelper(context);
        mSqLiteDatabase = mDatabaseHelper.getWritableDatabase();

        Cursor cursor = mSqLiteDatabase.query(mDatabaseHelper.USER_TABLE_NAME, null, null, null, null, null, null, null);
        int count = cursor.getCount();
        return count;
    }

}
package com.xcy.fzbcity.all.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class DataBaseUtil {


    public static DatabaseHelper mDatabaseHelper;
    public static SQLiteDatabase mSqLiteDatabase;
    private static long index;

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
                    String username = cursor.getString(cursor.getColumnIndex("USER_NAME"));
                    String userpassword = cursor.getString(cursor.getColumnIndex("USER_PASSWORD"));
                    DataBase dataBase = new DataBase(username, userpassword);
                    list.add(dataBase);
                } else {
                    String username = cursor.getString(cursor.getColumnIndex("USER_NAME"));
                    String userpassword = cursor.getString(cursor.getColumnIndex("USER_PASSWORD"));
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

        String conditions = "USER_NAME=?";
        String[] valueStr = {userPassword};

        int affectNum = mSqLiteDatabase.update(DatabaseHelper.USER_TABLE_NAME, contentValues, conditions, valueStr);

        return affectNum;
    }

    //TODO 删
    public static int initDelete(Context context, String userName) {

        mDatabaseHelper = new DatabaseHelper(context);
        mSqLiteDatabase = mDatabaseHelper.getWritableDatabase();

        String conditions = "USER_NAME=?";
        String[] args = {userName};
        int numbers = mSqLiteDatabase.delete(DatabaseHelper.USER_TABLE_NAME, conditions, args);
        return numbers;
    }

    //TODO 增
    public static long initAdd(Context context, String userName, String userPassword) {

        mDatabaseHelper = new DatabaseHelper(context);
        mSqLiteDatabase = mDatabaseHelper.getWritableDatabase();

        int indexDelete = 0;
        int ifnum = 0;
        Cursor cursor = mSqLiteDatabase.query(mDatabaseHelper.USER_TABLE_NAME, null, null, null, null, null, null, null);
        int count = cursor.getCount();

        if (cursor.moveToFirst()) {
            do {
                String username = cursor.getString(cursor.getColumnIndex("USER_NAME"));
                String userpassword = cursor.getString(cursor.getColumnIndex("USER_PASSWORD"));
                if (userName.equals(username)) {
                    ifnum = 1;
                    break;
                } else {

                }
            } while (cursor.moveToNext());
        }

        if (ifnum == 1) {
//            if (count == 3) {
//                if (cursor.moveToFirst()) {
//                    do {
//                        String username = cursor.getString(cursor.getColumnIndex("USER_NAME"));
//                        String userpassword = cursor.getString(cursor.getColumnIndex("USER_PASSWORD"));
//                        if (indexDelete == 0) {
//                            String conditions = "USER_NAME=?";
//                            String[] args = {username};
//                            mSqLiteDatabase.delete(DatabaseHelper.USER_TABLE_NAME, conditions, args);
//                            break;
//                        }
//                    } while (cursor.moveToNext());
//                }
//            }
        } else if (ifnum == 0) {
            if (count == 3) {
                if (cursor.moveToFirst()) {
                    do {
                        String username = cursor.getString(cursor.getColumnIndex("USER_NAME"));
                        String userpassword = cursor.getString(cursor.getColumnIndex("USER_PASSWORD"));
                        if (indexDelete == 0) {
                            String conditions = "USER_NAME=?";
                            String[] args = {username};
                            mSqLiteDatabase.delete(DatabaseHelper.USER_TABLE_NAME, conditions, args);
                            break;
                        }
                    } while (cursor.moveToNext());
                }
            }
            ContentValues values = new ContentValues();
            values.put(mDatabaseHelper.USER_NAME, userName);
            values.put(mDatabaseHelper.USER_PASSWORD, userPassword);
            index = mSqLiteDatabase.insert(mDatabaseHelper.USER_TABLE_NAME, null, values);
        }

        if (index != -1) {

        }
        cursor.close();
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

package com.xcy.fzbcity.all.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class InformeDataBaseUtil {


    public static InformeDatabaseHelper mDatabaseHelper;
    public static SQLiteDatabase mSqLiteDatabase;
    private static long index;

    //TODO 查
    public static List<InformeDataBase> initSelect(Context context, String selectData) {

        mDatabaseHelper = new InformeDatabaseHelper(context);
        mSqLiteDatabase = mDatabaseHelper.getWritableDatabase();
        List<InformeDataBase> list = new ArrayList<>();
        list.clear();
        Cursor cursor = mSqLiteDatabase.query(mDatabaseHelper.USER_TABLE_NAME, null, null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                if (selectData.equals("")) {
                    String informeid = cursor.getString(cursor.getColumnIndex("INFORMEID"));
                    String title = cursor.getString(cursor.getColumnIndex("TITLE"));
                    String content = cursor.getString(cursor.getColumnIndex("CONTENT"));
                    String type = cursor.getString(cursor.getColumnIndex("TYPE"));
                    String data = cursor.getString(cursor.getColumnIndex("DATA"));
                    String userid = cursor.getString(cursor.getColumnIndex("USERID"));
                    String isread = cursor.getString(cursor.getColumnIndex("ISREAD"));
                    String subtype = cursor.getString(cursor.getColumnIndex("SUBTYPE"));
                    String commonid = cursor.getString(cursor.getColumnIndex("COMMONID"));
                    String state = cursor.getString(cursor.getColumnIndex("STATE"));
                    String isenable = cursor.getString(cursor.getColumnIndex("ISENABLE"));
                    InformeDataBase dataBase = new InformeDataBase(informeid, title, content, type, data, userid, isread,subtype,commonid,state,isenable);
                    list.add(dataBase);
                } else {
//                    String username = cursor.getString(cursor.getColumnIndex("USER_NAME"));
//                    String userpassword = cursor.getString(cursor.getColumnIndex("USER_PASSWORD"));
//                    if (username.equals(selectData)) {
//                        DataBase dataBase = new DataBase(username, userpassword);
//                        list.add(dataBase);
//                        break;
//                    }
                }
            } while (cursor.moveToNext());
        }
        cursor.close();

        return list;

    }


    //TODO 改
    public static int initUpData(Context context, String informeid, String isRead) {

        mDatabaseHelper = new InformeDatabaseHelper(context);
        mSqLiteDatabase = mDatabaseHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(InformeDatabaseHelper.ISREAD, isRead);
        String conditions = "INFORMEID=?";
        String[] valueStr = {String.valueOf(informeid)};

        Log.i("通知数据库", "mDatabaseHelper.USER_TABLE_NAME:" + mDatabaseHelper.USER_TABLE_NAME);
        Log.i("通知数据库", "contentValues:" + contentValues);
        Log.i("通知数据库", "conditions:" + conditions);
        Log.i("通知数据库", "valueStr:" + valueStr);

        int affectNum = mSqLiteDatabase.update(mDatabaseHelper.USER_TABLE_NAME, contentValues, conditions, valueStr);


//        Log.i("通知数据库", "informeid:" + informeid);
//        Log.i("通知数据库", "isRead:" + isRead);
//
//        mDatabaseHelper = new InformeDatabaseHelper(context);
//        mSqLiteDatabase = mDatabaseHelper.getWritableDatabase();
//        ContentValues values =new ContentValues();
//        values.put("INFORMEID", informeid);
//        String whereClause="ISREAD=?";
//        String[] whereArgs={String.valueOf(isRead)};
//        int affectNum = mSqLiteDatabase.update(mDatabaseHelper.USER_TABLE_NAME, values, whereClause, whereArgs);
//        mSqLiteDatabase.close();
//        Log.i("通知数据库", "affectNum:" + affectNum);
        return affectNum;
    }

    //TODO 删
    public static int initDelete(Context context, String type) {

        mDatabaseHelper = new InformeDatabaseHelper(context);
        mSqLiteDatabase = mDatabaseHelper.getWritableDatabase();

        String conditions = "TYPE=?";
        String[] args = {type};
        int numbers = mSqLiteDatabase.delete(mDatabaseHelper.USER_TABLE_NAME, conditions, args);
        return numbers;

    }

    //TODO 增
    public static long initAdd(Context context, String informeid, String title, String content, String type, String data, String userid, String isread, String subtype, String commonid, String state, String isenable) {

        mDatabaseHelper = new InformeDatabaseHelper(context);
        mSqLiteDatabase = mDatabaseHelper.getWritableDatabase();

        int ifnum = 0;
        Cursor cursor = mSqLiteDatabase.query(mDatabaseHelper.USER_TABLE_NAME, null, null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                String Myinformeid = cursor.getString(cursor.getColumnIndex("INFORMEID"));
                if (informeid.equals(Myinformeid)) {
                    ifnum = 1;
                    break;
                } else {

                }
            } while (cursor.moveToNext());
        }

        if (ifnum == 1) {

        } else {

            ContentValues values = new ContentValues();
            values.put(mDatabaseHelper.TITLE, title);
            values.put(mDatabaseHelper.CONTENT, content);
            values.put(mDatabaseHelper.TYPE, type);
            values.put(mDatabaseHelper.DATA, data);
            values.put(mDatabaseHelper.USERID, userid);
            values.put(mDatabaseHelper.ISREAD, isread);
            values.put(mDatabaseHelper.INFORMEID, informeid);
            values.put(mDatabaseHelper.SUBTYPE, subtype);
            values.put(mDatabaseHelper.COMMONID, commonid);
            values.put(mDatabaseHelper.STATE, state);
            values.put(mDatabaseHelper.ISENABLE, isenable);
            index = mSqLiteDatabase.insert(mDatabaseHelper.USER_TABLE_NAME, null, values);
        }

        if (index != -1) {

        }
        cursor.close();
        return index;

    }

}

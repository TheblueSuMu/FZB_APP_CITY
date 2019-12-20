package com.xcy.fzbcity.all.utils;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.util.Log;

import com.tencent.bugly.crashreport.CrashReport;
import com.xcy.fzbcity.all.api.CityContents;
import com.xcy.fzbcity.all.modle.PhoneDto;

import java.util.ArrayList;
import java.util.List;

public class PhoneUtil {

    // 号码
    public final static String NUM = ContactsContract.CommonDataKinds.Phone.NUMBER;
    // 联系人姓名
    public final static String NAME = ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME;

    //上下文对象
    private Context context;
    //联系人提供者的uri
    private Uri phoneUri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;

    public PhoneUtil(Context context){
        this.context = context;
    }

    //获取所有联系人
    public List<PhoneDto> getPhone(){
        List<PhoneDto> phoneDtos = new ArrayList<>();
        ContentResolver cr = context.getContentResolver();
        Cursor cursor = cr.query(phoneUri,new String[]{NUM,NAME},null,null,null);
        try {
            if (cursor != null) {
                while (cursor.moveToNext()){
                    PhoneDto phoneDto = new PhoneDto(cursor.getString(cursor.getColumnIndex(NAME)),cursor.getString(cursor.getColumnIndex(NUM)));
                    phoneDtos.add(phoneDto);
                }
            }
        }catch (Exception e){
            Log.e("非空判断",Log.getStackTraceString(e));
            Log.i("非空判断","为空："+e.getMessage());
            Log.i("非空判断","为空");
            CityContents.setError(Log.getStackTraceString(e));
            CityContents.setErrorMessage(e.getMessage());
            CrashReport.testJavaCrash();
        }
        return phoneDtos;
    }
}
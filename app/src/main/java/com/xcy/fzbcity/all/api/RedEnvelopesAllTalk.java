package com.xcy.fzbcity.all.api;

import com.xcy.fzbcity.all.modle.RedbagReceiveRecordBean;

import java.util.ArrayList;
import java.util.List;

public class RedEnvelopesAllTalk {
    static String webshopId = "";

    static String AppletImage = "";

    static String UserName = "";

    static String UserPhone = "";

    static int index = 0;

    static List<RedbagReceiveRecordBean.DataBean.RowsBean> RedbagReceiveRecordList = new ArrayList<>();

    public static List<RedbagReceiveRecordBean.DataBean.RowsBean> getRedbagReceiveRecordList() {
        return RedbagReceiveRecordList;
    }

    public static void setRedbagReceiveRecordList(List<RedbagReceiveRecordBean.DataBean.RowsBean> redbagReceiveRecordList) {
        RedbagReceiveRecordList = redbagReceiveRecordList;
    }

    public static int getIndex() {
        return index;
    }

    public static void setIndex(int index) {
        RedEnvelopesAllTalk.index = index;
    }

    public static String getUserName() {
        return UserName;
    }

    public static void setUserName(String userName) {
        UserName = userName;
    }

    public static String getUserPhone() {
        return UserPhone;
    }

    public static void setUserPhone(String userPhone) {
        UserPhone = userPhone;
    }

    public static String getAppletImage() {
        return AppletImage;
    }

    public static void setAppletImage(String appletImage) {
        AppletImage = appletImage;
    }

    public static String getWebshopId() {
        return webshopId;
    }

    public static void setWebshopId(String webshopId) {
        RedEnvelopesAllTalk.webshopId = webshopId;
    }
}

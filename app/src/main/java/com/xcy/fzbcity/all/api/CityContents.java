package com.xcy.fzbcity.all.api;

import com.xcy.fzbcity.all.modle.ProjectDetailsBean;

import java.util.ArrayList;
import java.util.List;

public class CityContents {
    static String familyId = "";

    static String cityType = "";

    static String ReadRecordStatus = "";

    static String OneKey = "";

    static String addClient = "";

    static String WeChatType = "";

    static String WeChatJson = "";

    static String IsRead = "";

    static String IsReport = "";

    static String Error = "";

    static String ErrorMessage = "";

    static String Store = "";

    static boolean Enabled = true;

    static String CommissionFormat = "";

    static String phone = "";

    public static String getPhone() {
        return phone;
    }

    public static void setPhone(String phone) {
        CityContents.phone = phone;
    }

    static List<ProjectDetailsBean.DataBean.ProjectListVoBean.FfAttacheListBean> FfAttacheList = new ArrayList<>();

    public static List<ProjectDetailsBean.DataBean.ProjectListVoBean.FfAttacheListBean> getFfAttacheList() {
        return FfAttacheList;
    }

    public static void setFfAttacheList(List<ProjectDetailsBean.DataBean.ProjectListVoBean.FfAttacheListBean> ffAttacheList) {
        FfAttacheList = ffAttacheList;
    }

    public static String getCommissionFormat() {
        return CommissionFormat;
    }

    public static void setCommissionFormat(String commissionFormat) {
        CommissionFormat = commissionFormat;
    }

    public static boolean isEnabled() {
        return Enabled;
    }

    public static void setEnabled(boolean enabled) {
        Enabled = enabled;
    }

    public static String getStore() {
        return Store;
    }

    public static void setStore(String store) {
        Store = store;
    }

    public static String getError() {
        return Error;
    }

    public static void setError(String error) {
        Error = error;
    }

    public static String getErrorMessage() {
        return ErrorMessage;
    }

    public static void setErrorMessage(String errorMessage) {
        ErrorMessage = errorMessage;
    }

    public static String getIsReport() {
        return IsReport;
    }

    public static void setIsReport(String isReport) {
        IsReport = isReport;
    }

    public static String getIsRead() {
        return IsRead;
    }

    public static void setIsRead(String isRead) {
        IsRead = isRead;
    }

    public static String getWeChatType() {
        return WeChatType;
    }

    public static void setWeChatType(String weChatType) {
        WeChatType = weChatType;
    }

    public static String getWeChatJson() {
        return WeChatJson;
    }

    public static void setWeChatJson(String weChatJson) {
        WeChatJson = weChatJson;
    }

    public static String getAddClient() {
        return addClient;
    }

    public static void setAddClient(String addClient) {
        CityContents.addClient = addClient;
    }

    public static String getOneKey() {
        return OneKey;
    }

    public static void setOneKey(String oneKey) {
        OneKey = oneKey;
    }

    public static String getReadRecordStatus() {
        return ReadRecordStatus;
    }

    public static void setReadRecordStatus(String readRecordStatus) {
        ReadRecordStatus = readRecordStatus;
    }

    public static String getCityType() {
        return cityType;
    }

    public static void setCityType(String cityType) {
        CityContents.cityType = cityType;
    }

    public static String getFamilyId() {
        return familyId;
    }

    public static void setFamilyId(String familyId) {
        CityContents.familyId = familyId;
    }
}

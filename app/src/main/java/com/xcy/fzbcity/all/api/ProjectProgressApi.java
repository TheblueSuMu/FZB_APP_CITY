package com.xcy.fzbcity.all.api;


import com.xcy.fzbcity.all.database.FieldBean;

import java.util.ArrayList;
import java.util.List;

//      TODO    项目进度专用数据提取类
public class ProjectProgressApi {
    static String CustomerName = "";             //      TODO    客户名
    static String CustomerPhone = "";           //      TODO    客户手机号
    static String ProjectName = "";             //      TODO    项目名
    static String complemented = "";             //      TODO    补全信息
    static String amend = "";             //      TODO    修改信息

    static String gender = "";             //      TODO    性别
    static String idNumber = "";             //      TODO    身份证号码
    static String age = "";             //      TODO    年龄
    static String passportNumber = "";             //      TODO    护照号码
    static String passportimg = "";             //      TODO    护照或身份证 照片

    static String ID = "";                //      TODO    补全信息的ID

    static String CustomerCity = "";                //      TODO    客户基本信息描摹 客户城市
    static String CustomerOccupation = "";                //      TODO    客户基本信息描摹 客户职业
    static String CustomerFocus = "";                //      TODO    客户基本信息描摹 客户关注点
    static String CustomerIntentionalBuilding = "";                //      TODO    客户基本信息描摹 意向房源
    static String CustomerPaymentMethod = "";                //      TODO    客户基本信息描摹 付款方式
    static String CustomerHasDecision = "";                //      TODO    客户基本信息描摹 是否具备决策权
    static String CustomerResistance= "";                //      TODO    客户基本信息描摹 可能存在抗性
    static String CustomerObjective = "";                //      TODO    客户基本信息描摹 购房目的
    static String CustomerAuditStatus = "";                //      TODO    客户基本信息描摹 客户理想面积

    static String field = "";             //      TODO    补全信息
    static FieldBean fieldBean = new FieldBean();                //      TODO    同行人界面
    static List<FieldBean> fieldBeanList = new ArrayList<>();                //      TODO    同行人集合
    static String FieldID = "";                //      TODO    同行人查询ID
    static String landingId = "";                //      TODO    同行人登岛ID

    static int index = 999999;

    static String chongfu = "";

    public static String getChongfu() {
        return chongfu;
    }

    public static void setChongfu(String chongfu) {
        ProjectProgressApi.chongfu = chongfu;
    }

    public static int getIndex() {
        return index;
    }

    public static void setIndex(int index) {
        ProjectProgressApi.index = index;
    }

    public static String getLandingId() {
        return landingId;
    }

    public static void setLandingId(String landingId) {
        ProjectProgressApi.landingId = landingId;
    }

    public static String getField() {
        return field;
    }

    public static void setField(String field) {
        ProjectProgressApi.field = field;
    }

    public static String getFieldID() {
        return FieldID;
    }

    public static void setFieldID(String fieldID) {
        FieldID = fieldID;
    }

    public static FieldBean getFieldBean() {
        return fieldBean;
    }

    public static void setFieldBean(FieldBean fieldBean) {
        ProjectProgressApi.fieldBean = fieldBean;
    }

    public static List<FieldBean> getFieldBeanList() {
        return fieldBeanList;
    }

    public static void setFieldBeanList(List<FieldBean> fieldBeanList) {
        ProjectProgressApi.fieldBeanList = fieldBeanList;
    }

    public static String getCustomerCity() {
        return CustomerCity;
    }

    public static void setCustomerCity(String customerCity) {
        CustomerCity = customerCity;
    }

    public static String getCustomerOccupation() {
        return CustomerOccupation;
    }

    public static void setCustomerOccupation(String customerOccupation) {
        CustomerOccupation = customerOccupation;
    }

    public static String getCustomerFocus() {
        return CustomerFocus;
    }

    public static void setCustomerFocus(String customerFocus) {
        CustomerFocus = customerFocus;
    }

    public static String getCustomerIntentionalBuilding() {
        return CustomerIntentionalBuilding;
    }

    public static void setCustomerIntentionalBuilding(String customerIntentionalBuilding) {
        CustomerIntentionalBuilding = customerIntentionalBuilding;
    }

    public static String getCustomerPaymentMethod() {
        return CustomerPaymentMethod;
    }

    public static void setCustomerPaymentMethod(String customerPaymentMethod) {
        CustomerPaymentMethod = customerPaymentMethod;
    }

    public static String getCustomerHasDecision() {
        return CustomerHasDecision;
    }

    public static void setCustomerHasDecision(String customerHasDecision) {
        CustomerHasDecision = customerHasDecision;
    }

    public static String getCustomerResistance() {
        return CustomerResistance;
    }

    public static void setCustomerResistance(String customerResistance) {
        CustomerResistance = customerResistance;
    }

    public static String getCustomerObjective() {
        return CustomerObjective;
    }

    public static void setCustomerObjective(String customerObjective) {
        CustomerObjective = customerObjective;
    }

    public static String getCustomerAuditStatus() {
        return CustomerAuditStatus;
    }

    public static void setCustomerAuditStatus(String customerAuditStatus) {
        CustomerAuditStatus = customerAuditStatus;
    }

    public static String getID() {
        return ID;
    }

    public static void setID(String ID) {
        ProjectProgressApi.ID = ID;
    }

    public static String getGender() {
        return gender;
    }

    public static void setGender(String gender) {
        ProjectProgressApi.gender = gender;
    }

    public static String getIdNumber() {
        return idNumber;
    }

    public static void setIdNumber(String idNumber) {
        ProjectProgressApi.idNumber = idNumber;
    }

    public static String getAge() {
        return age;
    }

    public static void setAge(String age) {
        ProjectProgressApi.age = age;
    }

    public static String getPassportNumber() {
        return passportNumber;
    }

    public static void setPassportNumber(String passportNumber) {
        ProjectProgressApi.passportNumber = passportNumber;
    }

    public static String getPassportimg() {
        return passportimg;
    }

    public static void setPassportimg(String passportimg) {
        ProjectProgressApi.passportimg = passportimg;
    }

    public static String getComplemented() {
        return complemented;
    }

    public static void setComplemented(String complemented) {
        ProjectProgressApi.complemented = complemented;
    }

    public static String getAmend() {
        return amend;
    }

    public static void setAmend(String amend) {
        ProjectProgressApi.amend = amend;
    }

    public static String getProjectName() {
        return ProjectName;
    }

    public static void setProjectName(String projectName) {
        ProjectName = projectName;
    }

    public static String getCustomerName() {
        return CustomerName;
    }

    public static void setCustomerName(String customerName) {
        CustomerName = customerName;
    }

    public static String getCustomerPhone() {
        return CustomerPhone;
    }

    public static void setCustomerPhone(String customerPhone) {
        CustomerPhone = customerPhone;
    }
}
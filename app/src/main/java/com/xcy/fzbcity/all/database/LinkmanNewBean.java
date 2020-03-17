package com.xcy.fzbcity.all.database;

import com.mcxtzhang.indexlib.IndexBar.bean.BaseIndexPinyinBean;

public class LinkmanNewBean extends BaseIndexPinyinBean {

    private String clientname;// 名字
    private String clientId; // ID
    private String clientPhone; // 电话号码

    private String gender;//性别
    private String way;//拓客显示
    private String customerimg;//头像

    private boolean isTop;//是否是最上面的 不需要被转化成拼音的

    public LinkmanNewBean(String clientname, String clientId, String clientPhone, String gender, String way, String customerimg) {
        this.clientname = clientname;
        this.clientId = clientId;
        this.clientPhone = clientPhone;
        this.gender = gender;
        this.way = way;
        this.customerimg = customerimg;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getWay() {
        return way;
    }

    public void setWay(String way) {
        this.way = way;
    }

    public String getCustomerimg() {
        return customerimg;
    }

    public void setCustomerimg(String customerimg) {
        this.customerimg = customerimg;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientPhone() {
        return clientPhone;
    }

    public void setClientPhone(String clientPhone) {
        this.clientPhone = clientPhone;
    }

    public LinkmanNewBean() {
    }

    public LinkmanNewBean(String clientname) {
        this.clientname = clientname;
    }

    public String getCity() {
        return clientname;
    }

    public LinkmanNewBean setCity(String clientname) {
        this.clientname = clientname;
        return this;
    }

    public boolean isTop() {
        return isTop;
    }

    public LinkmanNewBean setTop(boolean top) {
        isTop = top;
        return this;
    }

    @Override
    public String getTarget() {
        return clientname;
    }

    @Override
    public boolean isNeedToPinyin() {
        return !isTop;
    }


    @Override
    public boolean isShowSuspension() {
        return !isTop;
    }
}

package com.xcy.fzbcity.all.database;

import com.mcxtzhang.indexlib.IndexBar.bean.BaseIndexPinyinBean;

public class LinkmanBean extends BaseIndexPinyinBean {

    private String clientname;// 名字
    private String clientId; // ID
    private String clientPhone; // 电话号码
    private boolean isTop;//是否是最上面的 不需要被转化成拼音的

    public LinkmanBean(String clientname, String clientId, String clientPhone) {
        this.clientname = clientname;
        this.clientId = clientId;
        this.clientPhone = clientPhone;
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

    public LinkmanBean() {
    }

    public LinkmanBean(String clientname) {
        this.clientname = clientname;
    }

    public String getCity() {
        return clientname;
    }

    public LinkmanBean setCity(String clientname) {
        this.clientname = clientname;
        return this;
    }

    public boolean isTop() {
        return isTop;
    }

    public LinkmanBean setTop(boolean top) {
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

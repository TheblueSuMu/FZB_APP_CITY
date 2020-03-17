package com.xcy.fzbcity.all.utils;

public class InformeDataNewBase {

    int ID;
    String Informeid;
    String title;
    String content;
    String type;
    String date;
    String userId;
    String isRead;

    String subtype;
    String commonid;
    String state;
    String isenable;

    public InformeDataNewBase() {

    }

    public InformeDataNewBase(String informeid, String title, String content, String type, String date, String userId, String isRead, String subtype, String commonid, String state, String isenable) {
        this.ID = ID;
        Informeid = informeid;
        this.title = title;
        this.content = content;
        this.type = type;
        this.date = date;
        this.userId = userId;
        this.isRead = isRead;
        this.subtype = subtype;
        this.commonid = commonid;
        this.state = state;
        this.isenable = isenable;
    }

    public String getSubtype() {
        return subtype;
    }

    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }

    public String getCommonid() {
        return commonid;
    }

    public void setCommonid(String commonid) {
        this.commonid = commonid;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getIsenable() {
        return isenable;
    }

    public void setIsenable(String isenable) {
        this.isenable = isenable;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getInformeid() {
        return Informeid;
    }

    public void setInformeid(String informeid) {
        Informeid = informeid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getIsRead() {
        return isRead;
    }

    public void setIsRead(String isRead) {
        this.isRead = isRead;
    }
}

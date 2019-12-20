package com.xcy.fzbcity.all.utils;

public class DataBase {

    int ID;
    String userName;
    String userPassword;

    public DataBase() {
    }

    public DataBase(String userName, String userPassword) {
        this.userName = userName;
        this.userPassword = userPassword;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
}

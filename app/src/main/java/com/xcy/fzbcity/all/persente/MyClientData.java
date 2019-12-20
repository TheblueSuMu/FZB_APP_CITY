package com.xcy.fzbcity.all.persente;

public class MyClientData {

    String name;
    String judge;

    public MyClientData() {
    }

    public MyClientData(String name, String judge) {
        this.name = name;
        this.judge = judge;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJudge() {
        return judge;
    }

    public void setJudge(String judge) {
        this.judge = judge;
    }
}
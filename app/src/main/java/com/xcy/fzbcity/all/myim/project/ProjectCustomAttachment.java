package com.xcy.fzbcity.all.myim.project;

import android.text.TextUtils;
import android.util.Log;

import com.alibaba.fastjson.JSONObject;
import com.xcy.fzbcity.all.myim.CustomAttachParser;
import com.xcy.fzbcity.all.myim.CustomAttachment;
import com.xcy.fzbcity.all.myim.CustomAttachmentType;

public class ProjectCustomAttachment extends CustomAttachment {

    private String pid;   //项目id
    private String name;  //项目名称
    private String projectType;  //项目特点
    private String square;//项目面积
    private String price; //项目价格

    private static final String PID = "pid";
    private static final String PROJECT_NAME = "name";
    private static final String PROJECT_TYPE = "type";
    private static final String PROJECT_SQUARE = "square";
    private static final String PROJECT_PRICE = "price";


    public ProjectCustomAttachment() {
        super(CustomAttachmentType.project);
    }

    @Override
    public String toJson(boolean send) {
        JSONObject data = new JSONObject();

        data.put(PID, getPid());
        data.put(PROJECT_NAME, getName());
        data.put(PROJECT_TYPE, getProjectType());
        data.put(PROJECT_SQUARE, getSquare());
        data.put(PROJECT_PRICE, getPrice());



        return CustomAttachParser.packData(CustomAttachmentType.project, data);
    }

    @Override
    protected void parseData(JSONObject data) {
        pid = data.getString(PID);
        name = data.getString(PROJECT_NAME);
        projectType = data.getString(PROJECT_TYPE);
        square = data.getString(PROJECT_SQUARE);
        price = data.getString(PROJECT_PRICE);
    }

    @Override
    protected JSONObject packData() {
        JSONObject data = new JSONObject();
        data.put(PID, getPid());
        data.put(PROJECT_NAME, getName());
        data.put(PROJECT_TYPE, getProjectType());
        data.put(PROJECT_SQUARE, getSquare());
        data.put(PROJECT_PRICE, getPrice());
        return data;
    }

    public String getProjectType() {
        return projectType;
    }

    public void setProjectType(String projectType) {
        this.projectType = projectType;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getSquare() {
        return square;
    }

    public void setSquare(String square) {
        this.square = square;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

}

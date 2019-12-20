package com.xcy.fzbcity.all.utils;

public class DetailsData {

    String buildingName;
    String elementNumber;
    String households;
    String ifnum;

    public DetailsData() {
    }

    public DetailsData(String buildingName, String elementNumber, String households,String ifnum) {
        this.buildingName = buildingName;
        this.elementNumber = elementNumber;
        this.households = households;
        this.ifnum = ifnum;
    }

    public String getIfnum() {
        return ifnum;
    }

    public void setIfnum(String ifnum) {
        this.ifnum = ifnum;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public String getElementNumber() {
        return elementNumber;
    }

    public void setElementNumber(String elementNumber) {
        this.elementNumber = elementNumber;
    }

    public String getHouseholds() {
        return households;
    }

    public void setHouseholds(String households) {
        this.households = households;
    }
}

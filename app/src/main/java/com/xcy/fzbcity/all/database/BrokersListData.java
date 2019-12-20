package com.xcy.fzbcity.all.database;

public class BrokersListData {

    String AgentPhone;
    String CompanyName;
    String StoreName;
    String Status;
    String AgentId;
    String AgentName;

    public String getAgentName() {
        return AgentName;
    }

    public void setAgentName(String agentName) {
        AgentName = agentName;
    }

    public String getAgentId() {
        return AgentId;
    }

    public void setAgentId(String agentId) {
        AgentId = agentId;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public BrokersListData(String agentPhone, String companyName, String storeName,String status,String agentId) {
        AgentPhone = agentPhone;
        CompanyName = companyName;
        StoreName = storeName;
        Status = status;
        AgentId = agentId;
    }

    public BrokersListData() {
    }

    public String getAgentPhone() {
        return AgentPhone;
    }

    public void setAgentPhone(String agentPhone) {
        AgentPhone = agentPhone;
    }

    public String getCompanyName() {
        return CompanyName;
    }

    public void setCompanyName(String companyName) {
        CompanyName = companyName;
    }

    public String getStoreName() {
        return StoreName;
    }

    public void setStoreName(String storeName) {
        StoreName = storeName;
    }
}
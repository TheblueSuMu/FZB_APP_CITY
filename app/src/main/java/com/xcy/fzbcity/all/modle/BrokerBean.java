package com.xcy.fzbcity.all.modle;

import java.util.List;

public class BrokerBean {

    /**
     * code : 1
     * msg : 成功
     * data : [{"id":"9aa7ac2e4755498689b8c21327b5b648","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","datePicker":"","startDate":"","endDate":"","mainTitle":"高层产品","onlineState":"","contractPercent":"","contractUnitPrice":"","contractAmount":"","isSeconds":"0","secondsAmount":"","agentPercent":"","agentUnitPrice":"","agentAmount":"","leaderPercent":"","leaderUnitPrice":"","leaderAmount":"","project":"","isProjectShow":0,"contractFormat":"","agentFormat":"","leaderFormat":"","currentDate":"","commissionFormat":"5万元","secondsFormat":"无秒结","teamLeaders":"","incentiveTeamIds":""},{"id":"c1cafff3322d47838db2dad77ea88d5d","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","datePicker":"","startDate":"","endDate":"","mainTitle":"叠拼产品","onlineState":"","contractPercent":"","contractUnitPrice":"","contractAmount":"","isSeconds":"0","secondsAmount":"","agentPercent":"","agentUnitPrice":"","agentAmount":"","leaderPercent":"","leaderUnitPrice":"","leaderAmount":"","project":"","isProjectShow":0,"contractFormat":"","agentFormat":"","leaderFormat":"","currentDate":"","commissionFormat":"9.5万元","secondsFormat":"无秒结","teamLeaders":"","incentiveTeamIds":""}]
     */

    private String code;
    private String msg;
    private List<DataBean> data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 9aa7ac2e4755498689b8c21327b5b648
         * remarks :
         * createBy :
         * createDate :
         * updateBy :
         * updateDate :
         * datePicker :
         * startDate :
         * endDate :
         * mainTitle : 高层产品
         * onlineState :
         * contractPercent :
         * contractUnitPrice :
         * contractAmount :
         * isSeconds : 0
         * secondsAmount :
         * agentPercent :
         * agentUnitPrice :
         * agentAmount :
         * leaderPercent :
         * leaderUnitPrice :
         * leaderAmount :
         * project :
         * isProjectShow : 0
         * contractFormat :
         * agentFormat :
         * leaderFormat :
         * currentDate :
         * commissionFormat : 5万元
         * secondsFormat : 无秒结
         * teamLeaders :
         * incentiveTeamIds :
         */

        private String id;
        private String remarks;
        private String createBy;
        private String createDate;
        private String updateBy;
        private String updateDate;
        private String datePicker;
        private String startDate;
        private String endDate;
        private String mainTitle;
        private String onlineState;
        private String contractPercent;
        private String contractUnitPrice;
        private String contractAmount;
        private String isSeconds;
        private String secondsAmount;
        private String agentPercent;
        private String agentUnitPrice;
        private String agentAmount;
        private String leaderPercent;
        private String leaderUnitPrice;
        private String leaderAmount;
        private String project;
        private int isProjectShow;
        private String contractFormat;
        private String agentFormat;
        private String leaderFormat;
        private String currentDate;
        private String commissionFormat;
        private String secondsFormat;
        private String teamLeaders;
        private String incentiveTeamIds;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getRemarks() {
            return remarks;
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks;
        }

        public String getCreateBy() {
            return createBy;
        }

        public void setCreateBy(String createBy) {
            this.createBy = createBy;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public String getUpdateBy() {
            return updateBy;
        }

        public void setUpdateBy(String updateBy) {
            this.updateBy = updateBy;
        }

        public String getUpdateDate() {
            return updateDate;
        }

        public void setUpdateDate(String updateDate) {
            this.updateDate = updateDate;
        }

        public String getDatePicker() {
            return datePicker;
        }

        public void setDatePicker(String datePicker) {
            this.datePicker = datePicker;
        }

        public String getStartDate() {
            return startDate;
        }

        public void setStartDate(String startDate) {
            this.startDate = startDate;
        }

        public String getEndDate() {
            return endDate;
        }

        public void setEndDate(String endDate) {
            this.endDate = endDate;
        }

        public String getMainTitle() {
            return mainTitle;
        }

        public void setMainTitle(String mainTitle) {
            this.mainTitle = mainTitle;
        }

        public String getOnlineState() {
            return onlineState;
        }

        public void setOnlineState(String onlineState) {
            this.onlineState = onlineState;
        }

        public String getContractPercent() {
            return contractPercent;
        }

        public void setContractPercent(String contractPercent) {
            this.contractPercent = contractPercent;
        }

        public String getContractUnitPrice() {
            return contractUnitPrice;
        }

        public void setContractUnitPrice(String contractUnitPrice) {
            this.contractUnitPrice = contractUnitPrice;
        }

        public String getContractAmount() {
            return contractAmount;
        }

        public void setContractAmount(String contractAmount) {
            this.contractAmount = contractAmount;
        }

        public String getIsSeconds() {
            return isSeconds;
        }

        public void setIsSeconds(String isSeconds) {
            this.isSeconds = isSeconds;
        }

        public String getSecondsAmount() {
            return secondsAmount;
        }

        public void setSecondsAmount(String secondsAmount) {
            this.secondsAmount = secondsAmount;
        }

        public String getAgentPercent() {
            return agentPercent;
        }

        public void setAgentPercent(String agentPercent) {
            this.agentPercent = agentPercent;
        }

        public String getAgentUnitPrice() {
            return agentUnitPrice;
        }

        public void setAgentUnitPrice(String agentUnitPrice) {
            this.agentUnitPrice = agentUnitPrice;
        }

        public String getAgentAmount() {
            return agentAmount;
        }

        public void setAgentAmount(String agentAmount) {
            this.agentAmount = agentAmount;
        }

        public String getLeaderPercent() {
            return leaderPercent;
        }

        public void setLeaderPercent(String leaderPercent) {
            this.leaderPercent = leaderPercent;
        }

        public String getLeaderUnitPrice() {
            return leaderUnitPrice;
        }

        public void setLeaderUnitPrice(String leaderUnitPrice) {
            this.leaderUnitPrice = leaderUnitPrice;
        }

        public String getLeaderAmount() {
            return leaderAmount;
        }

        public void setLeaderAmount(String leaderAmount) {
            this.leaderAmount = leaderAmount;
        }

        public String getProject() {
            return project;
        }

        public void setProject(String project) {
            this.project = project;
        }

        public int getIsProjectShow() {
            return isProjectShow;
        }

        public void setIsProjectShow(int isProjectShow) {
            this.isProjectShow = isProjectShow;
        }

        public String getContractFormat() {
            return contractFormat;
        }

        public void setContractFormat(String contractFormat) {
            this.contractFormat = contractFormat;
        }

        public String getAgentFormat() {
            return agentFormat;
        }

        public void setAgentFormat(String agentFormat) {
            this.agentFormat = agentFormat;
        }

        public String getLeaderFormat() {
            return leaderFormat;
        }

        public void setLeaderFormat(String leaderFormat) {
            this.leaderFormat = leaderFormat;
        }

        public String getCurrentDate() {
            return currentDate;
        }

        public void setCurrentDate(String currentDate) {
            this.currentDate = currentDate;
        }

        public String getCommissionFormat() {
            return commissionFormat;
        }

        public void setCommissionFormat(String commissionFormat) {
            this.commissionFormat = commissionFormat;
        }

        public String getSecondsFormat() {
            return secondsFormat;
        }

        public void setSecondsFormat(String secondsFormat) {
            this.secondsFormat = secondsFormat;
        }

        public String getTeamLeaders() {
            return teamLeaders;
        }

        public void setTeamLeaders(String teamLeaders) {
            this.teamLeaders = teamLeaders;
        }

        public String getIncentiveTeamIds() {
            return incentiveTeamIds;
        }

        public void setIncentiveTeamIds(String incentiveTeamIds) {
            this.incentiveTeamIds = incentiveTeamIds;
        }
    }
}

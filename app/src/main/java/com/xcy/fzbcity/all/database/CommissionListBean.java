package com.xcy.fzbcity.all.database;

import java.util.List;

public class CommissionListBean {


    /**
     * code : 1
     * msg : 成功
     * data : {"total":1,"rows":[{"id":"","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","customerName":"ff","customerPhone":"15558535538","projectName":"航天首府","roomNumber":"5栋5单元508室","tradeDate":"","totalAmount":"30000.00","alreadyAmount":"","notAmount":"30000.00","invoiceMoney":"","secondsAmount":"","closingTime":"2019-12-10 15:39:47","moneyStatus":0,"returnedMoney":"0.00","moneyStatusImg":"","companyName":"经纪公司B","companyId":"","storeName":"星期一门店","storeId":"","agentName":"ffff","agentId":"","agentPhone":"","status":"0","isMy":"","commission":"","userId":"","type":"","projectType":"","projectId":"","search":"","beforeDate":"","afterDate":"","historyData":""}]}
     */

    private String code;
    private String msg;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * total : 1
         * rows : [{"id":"","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","customerName":"ff","customerPhone":"15558535538","projectName":"航天首府","roomNumber":"5栋5单元508室","tradeDate":"","totalAmount":"30000.00","alreadyAmount":"","notAmount":"30000.00","invoiceMoney":"","secondsAmount":"","closingTime":"2019-12-10 15:39:47","moneyStatus":0,"returnedMoney":"0.00","moneyStatusImg":"","companyName":"经纪公司B","companyId":"","storeName":"星期一门店","storeId":"","agentName":"ffff","agentId":"","agentPhone":"","status":"0","isMy":"","commission":"","userId":"","type":"","projectType":"","projectId":"","search":"","beforeDate":"","afterDate":"","historyData":""}]
         */

        private int total;
        private List<RowsBean> rows;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public List<RowsBean> getRows() {
            return rows;
        }

        public void setRows(List<RowsBean> rows) {
            this.rows = rows;
        }

        public static class RowsBean {
            /**
             * id :
             * remarks :
             * createBy :
             * createDate :
             * updateBy :
             * updateDate :
             * customerName : ff
             * customerPhone : 15558535538
             * projectName : 航天首府
             * roomNumber : 5栋5单元508室
             * tradeDate :
             * totalAmount : 30000.00
             * alreadyAmount :
             * notAmount : 30000.00
             * invoiceMoney :
             * secondsAmount :
             * closingTime : 2019-12-10 15:39:47
             * moneyStatus : 0
             * returnedMoney : 0.00
             * moneyStatusImg :
             * companyName : 经纪公司B
             * companyId :
             * storeName : 星期一门店
             * storeId :
             * agentName : ffff
             * agentId :
             * agentPhone :
             * status : 0
             * isMy :
             * commission :
             * userId :
             * type :
             * projectType :
             * projectId :
             * search :
             * beforeDate :
             * afterDate :
             * historyData :
             */

            private String id;
            private String remarks;
            private String createBy;
            private String createDate;
            private String updateBy;
            private String updateDate;
            private String customerName;
            private String customerPhone;
            private String projectName;
            private String roomNumber;
            private String tradeDate;
            private String totalAmount;
            private String alreadyAmount;
            private String notAmount;
            private String invoiceMoney;
            private String secondsAmount;
            private String closingTime;
            private int moneyStatus;
            private String returnedMoney;
            private String moneyStatusImg;
            private String companyName;
            private String companyId;
            private String storeName;
            private String storeId;
            private String agentName;
            private String agentId;
            private String agentPhone;
            private String status;
            private String isMy;
            private String commission;
            private String userId;
            private String type;
            private String projectType;
            private String projectId;
            private String search;
            private String beforeDate;
            private String afterDate;
            private String historyData;

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

            public String getCustomerName() {
                return customerName;
            }

            public void setCustomerName(String customerName) {
                this.customerName = customerName;
            }

            public String getCustomerPhone() {
                return customerPhone;
            }

            public void setCustomerPhone(String customerPhone) {
                this.customerPhone = customerPhone;
            }

            public String getProjectName() {
                return projectName;
            }

            public void setProjectName(String projectName) {
                this.projectName = projectName;
            }

            public String getRoomNumber() {
                return roomNumber;
            }

            public void setRoomNumber(String roomNumber) {
                this.roomNumber = roomNumber;
            }

            public String getTradeDate() {
                return tradeDate;
            }

            public void setTradeDate(String tradeDate) {
                this.tradeDate = tradeDate;
            }

            public String getTotalAmount() {
                return totalAmount;
            }

            public void setTotalAmount(String totalAmount) {
                this.totalAmount = totalAmount;
            }

            public String getAlreadyAmount() {
                return alreadyAmount;
            }

            public void setAlreadyAmount(String alreadyAmount) {
                this.alreadyAmount = alreadyAmount;
            }

            public String getNotAmount() {
                return notAmount;
            }

            public void setNotAmount(String notAmount) {
                this.notAmount = notAmount;
            }

            public String getInvoiceMoney() {
                return invoiceMoney;
            }

            public void setInvoiceMoney(String invoiceMoney) {
                this.invoiceMoney = invoiceMoney;
            }

            public String getSecondsAmount() {
                return secondsAmount;
            }

            public void setSecondsAmount(String secondsAmount) {
                this.secondsAmount = secondsAmount;
            }

            public String getClosingTime() {
                return closingTime;
            }

            public void setClosingTime(String closingTime) {
                this.closingTime = closingTime;
            }

            public int getMoneyStatus() {
                return moneyStatus;
            }

            public void setMoneyStatus(int moneyStatus) {
                this.moneyStatus = moneyStatus;
            }

            public String getReturnedMoney() {
                return returnedMoney;
            }

            public void setReturnedMoney(String returnedMoney) {
                this.returnedMoney = returnedMoney;
            }

            public String getMoneyStatusImg() {
                return moneyStatusImg;
            }

            public void setMoneyStatusImg(String moneyStatusImg) {
                this.moneyStatusImg = moneyStatusImg;
            }

            public String getCompanyName() {
                return companyName;
            }

            public void setCompanyName(String companyName) {
                this.companyName = companyName;
            }

            public String getCompanyId() {
                return companyId;
            }

            public void setCompanyId(String companyId) {
                this.companyId = companyId;
            }

            public String getStoreName() {
                return storeName;
            }

            public void setStoreName(String storeName) {
                this.storeName = storeName;
            }

            public String getStoreId() {
                return storeId;
            }

            public void setStoreId(String storeId) {
                this.storeId = storeId;
            }

            public String getAgentName() {
                return agentName;
            }

            public void setAgentName(String agentName) {
                this.agentName = agentName;
            }

            public String getAgentId() {
                return agentId;
            }

            public void setAgentId(String agentId) {
                this.agentId = agentId;
            }

            public String getAgentPhone() {
                return agentPhone;
            }

            public void setAgentPhone(String agentPhone) {
                this.agentPhone = agentPhone;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getIsMy() {
                return isMy;
            }

            public void setIsMy(String isMy) {
                this.isMy = isMy;
            }

            public String getCommission() {
                return commission;
            }

            public void setCommission(String commission) {
                this.commission = commission;
            }

            public String getUserId() {
                return userId;
            }

            public void setUserId(String userId) {
                this.userId = userId;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getProjectType() {
                return projectType;
            }

            public void setProjectType(String projectType) {
                this.projectType = projectType;
            }

            public String getProjectId() {
                return projectId;
            }

            public void setProjectId(String projectId) {
                this.projectId = projectId;
            }

            public String getSearch() {
                return search;
            }

            public void setSearch(String search) {
                this.search = search;
            }

            public String getBeforeDate() {
                return beforeDate;
            }

            public void setBeforeDate(String beforeDate) {
                this.beforeDate = beforeDate;
            }

            public String getAfterDate() {
                return afterDate;
            }

            public void setAfterDate(String afterDate) {
                this.afterDate = afterDate;
            }

            public String getHistoryData() {
                return historyData;
            }

            public void setHistoryData(String historyData) {
                this.historyData = historyData;
            }
        }
    }
}
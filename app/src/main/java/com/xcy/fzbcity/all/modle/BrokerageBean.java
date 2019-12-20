package com.xcy.fzbcity.all.modle;

import java.util.List;

public class BrokerageBean {


    /**
     * code : 1
     * msg : 成功
     * data : {"total":16,"rows":[{"id":"","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","customerName":"","customerPhone":"","projectName":"杨进礼测试2","roomNumber":"1栋503单元2室","tradeDate":1559491200000,"totalAmount":1000,"alreadyAmount":100,"notAmount":900,"secondsAmount":"","userId":"","projectType":"","search":"","moneyStatusImg":""}]}
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
         * total : 16
         * rows : [{"id":"","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","customerName":"","customerPhone":"","projectName":"杨进礼测试2","roomNumber":"1栋503单元2室","tradeDate":1559491200000,"totalAmount":1000,"alreadyAmount":100,"notAmount":900,"secondsAmount":"","userId":"","projectType":"","search":"","moneyStatusImg":""}]
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
             * customerName :
             * customerPhone :
             * projectName : 杨进礼测试2
             * roomNumber : 1栋503单元2室
             * tradeDate : 1559491200000
             * totalAmount : 1000
             * alreadyAmount : 100
             * notAmount : 900
             * secondsAmount :
             * userId :
             * projectType :
             * search :
             * moneyStatusImg :
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
            private long tradeDate;
            private int totalAmount;
            private int alreadyAmount;
            private int notAmount;
            private String secondsAmount;
            private String userId;
            private String projectType;
            private String search;
            private String moneyStatusImg;

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

            public long getTradeDate() {
                return tradeDate;
            }

            public void setTradeDate(long tradeDate) {
                this.tradeDate = tradeDate;
            }

            public int getTotalAmount() {
                return totalAmount;
            }

            public void setTotalAmount(int totalAmount) {
                this.totalAmount = totalAmount;
            }

            public int getAlreadyAmount() {
                return alreadyAmount;
            }

            public void setAlreadyAmount(int alreadyAmount) {
                this.alreadyAmount = alreadyAmount;
            }

            public int getNotAmount() {
                return notAmount;
            }

            public void setNotAmount(int notAmount) {
                this.notAmount = notAmount;
            }

            public String getSecondsAmount() {
                return secondsAmount;
            }

            public void setSecondsAmount(String secondsAmount) {
                this.secondsAmount = secondsAmount;
            }

            public String getUserId() {
                return userId;
            }

            public void setUserId(String userId) {
                this.userId = userId;
            }

            public String getProjectType() {
                return projectType;
            }

            public void setProjectType(String projectType) {
                this.projectType = projectType;
            }

            public String getSearch() {
                return search;
            }

            public void setSearch(String search) {
                this.search = search;
            }

            public String getMoneyStatusImg() {
                return moneyStatusImg;
            }

            public void setMoneyStatusImg(String moneyStatusImg) {
                this.moneyStatusImg = moneyStatusImg;
            }
        }
    }
}

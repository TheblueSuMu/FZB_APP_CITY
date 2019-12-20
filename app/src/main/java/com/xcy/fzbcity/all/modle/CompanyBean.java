package com.xcy.fzbcity.all.modle;

public class CompanyBean {


    /**
     * code : 1
     * msg : 成功
     * data : {"companyInfo":{"id":"","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","user":"","companyId":"bf38ccb2df8243cd86c320f309df44e0","companyName":"001","companyAddress":"姑姑呼呼呼不不不阿拉","storeNum":"1","storeName":"","storeId":"","storeIdCode":"","isShopowner":"1","shopownerName":"001","shopownerPhone":"15423546213","loginName":"","status":"1","area":"","address":"","storeRise":"","storeImg":"","location":"","reason":"","agentNum":"","search":""},"companyMoneyData":{"totalAmount":"0","notAmount":"0","alreadyAmount":"0"},"companyDataStatistics":{"agentNum":1,"storeNum":1}}
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
         * companyInfo : {"id":"","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","user":"","companyId":"bf38ccb2df8243cd86c320f309df44e0","companyName":"001","companyAddress":"姑姑呼呼呼不不不阿拉","storeNum":"1","storeName":"","storeId":"","storeIdCode":"","isShopowner":"1","shopownerName":"001","shopownerPhone":"15423546213","loginName":"","status":"1","area":"","address":"","storeRise":"","storeImg":"","location":"","reason":"","agentNum":"","search":""}
         * companyMoneyData : {"totalAmount":"0","notAmount":"0","alreadyAmount":"0"}
         * companyDataStatistics : {"agentNum":1,"storeNum":1}
         */

        private CompanyInfoBean companyInfo;
        private CompanyMoneyDataBean companyMoneyData;
        private CompanyDataStatisticsBean companyDataStatistics;

        public CompanyInfoBean getCompanyInfo() {
            return companyInfo;
        }

        public void setCompanyInfo(CompanyInfoBean companyInfo) {
            this.companyInfo = companyInfo;
        }

        public CompanyMoneyDataBean getCompanyMoneyData() {
            return companyMoneyData;
        }

        public void setCompanyMoneyData(CompanyMoneyDataBean companyMoneyData) {
            this.companyMoneyData = companyMoneyData;
        }

        public CompanyDataStatisticsBean getCompanyDataStatistics() {
            return companyDataStatistics;
        }

        public void setCompanyDataStatistics(CompanyDataStatisticsBean companyDataStatistics) {
            this.companyDataStatistics = companyDataStatistics;
        }

        public static class CompanyInfoBean {
            /**
             * id :
             * remarks :
             * createBy :
             * createDate :
             * updateBy :
             * updateDate :
             * user :
             * companyId : bf38ccb2df8243cd86c320f309df44e0
             * companyName : 001
             * companyAddress : 姑姑呼呼呼不不不阿拉
             * storeNum : 1
             * storeName :
             * storeId :
             * storeIdCode :
             * isShopowner : 1
             * shopownerName : 001
             * shopownerPhone : 15423546213
             * loginName :
             * status : 1
             * area :
             * address :
             * storeRise :
             * storeImg :
             * location :
             * reason :
             * agentNum :
             * search :
             */

            private String id;
            private String remarks;
            private String createBy;
            private String createDate;
            private String updateBy;
            private String updateDate;
            private String user;
            private String companyId;
            private String companyName;
            private String companyAddress;
            private String storeNum;
            private String storeName;
            private String storeId;
            private String storeIdCode;
            private String isShopowner;
            private String shopownerName;
            private String shopownerPhone;
            private String loginName;
            private String status;
            private String area;
            private String address;
            private String storeRise;
            private String storeImg;
            private String location;
            private String reason;
            private String agentNum;
            private String search;

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

            public String getUser() {
                return user;
            }

            public void setUser(String user) {
                this.user = user;
            }

            public String getCompanyId() {
                return companyId;
            }

            public void setCompanyId(String companyId) {
                this.companyId = companyId;
            }

            public String getCompanyName() {
                return companyName;
            }

            public void setCompanyName(String companyName) {
                this.companyName = companyName;
            }

            public String getCompanyAddress() {
                return companyAddress;
            }

            public void setCompanyAddress(String companyAddress) {
                this.companyAddress = companyAddress;
            }

            public String getStoreNum() {
                return storeNum;
            }

            public void setStoreNum(String storeNum) {
                this.storeNum = storeNum;
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

            public String getStoreIdCode() {
                return storeIdCode;
            }

            public void setStoreIdCode(String storeIdCode) {
                this.storeIdCode = storeIdCode;
            }

            public String getIsShopowner() {
                return isShopowner;
            }

            public void setIsShopowner(String isShopowner) {
                this.isShopowner = isShopowner;
            }

            public String getShopownerName() {
                return shopownerName;
            }

            public void setShopownerName(String shopownerName) {
                this.shopownerName = shopownerName;
            }

            public String getShopownerPhone() {
                return shopownerPhone;
            }

            public void setShopownerPhone(String shopownerPhone) {
                this.shopownerPhone = shopownerPhone;
            }

            public String getLoginName() {
                return loginName;
            }

            public void setLoginName(String loginName) {
                this.loginName = loginName;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getArea() {
                return area;
            }

            public void setArea(String area) {
                this.area = area;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public String getStoreRise() {
                return storeRise;
            }

            public void setStoreRise(String storeRise) {
                this.storeRise = storeRise;
            }

            public String getStoreImg() {
                return storeImg;
            }

            public void setStoreImg(String storeImg) {
                this.storeImg = storeImg;
            }

            public String getLocation() {
                return location;
            }

            public void setLocation(String location) {
                this.location = location;
            }

            public String getReason() {
                return reason;
            }

            public void setReason(String reason) {
                this.reason = reason;
            }

            public String getAgentNum() {
                return agentNum;
            }

            public void setAgentNum(String agentNum) {
                this.agentNum = agentNum;
            }

            public String getSearch() {
                return search;
            }

            public void setSearch(String search) {
                this.search = search;
            }
        }

        public static class CompanyMoneyDataBean {
            /**
             * totalAmount : 0
             * notAmount : 0
             * alreadyAmount : 0
             */

            private String totalAmount;
            private String notAmount;
            private String alreadyAmount;

            public String getTotalAmount() {
                return totalAmount;
            }

            public void setTotalAmount(String totalAmount) {
                this.totalAmount = totalAmount;
            }

            public String getNotAmount() {
                return notAmount;
            }

            public void setNotAmount(String notAmount) {
                this.notAmount = notAmount;
            }

            public String getAlreadyAmount() {
                return alreadyAmount;
            }

            public void setAlreadyAmount(String alreadyAmount) {
                this.alreadyAmount = alreadyAmount;
            }
        }

        public static class CompanyDataStatisticsBean {
            /**
             * agentNum : 1
             * storeNum : 1
             */

            private int agentNum;
            private int storeNum;

            public int getAgentNum() {
                return agentNum;
            }

            public void setAgentNum(int agentNum) {
                this.agentNum = agentNum;
            }

            public int getStoreNum() {
                return storeNum;
            }

            public void setStoreNum(int storeNum) {
                this.storeNum = storeNum;
            }
        }
    }
}
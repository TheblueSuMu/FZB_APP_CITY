package com.xcy.fzbcity.all.modle;

import java.util.List;

public class ProcessDataBean {

    /**
     * code : 1
     * msg : 成功
     * data : {"total":0,"rows":[{"id":"","remarks":"","createBy":"","createDate":"2019-11-30 17:44:01","updateBy":"","updateDate":"2019-11-30 17:45:38","user":"","agentUser":"","storeId":"","agentId":"bd50b94b2d7349a9aa69dc08f99fa644","projectId":"129dcdd7a342486386853fa6d3725cba","preparationId":"f446bf8d0d764075b87fdffdad5b214a","customerName":"王健林","customerPhone":"13752071477","customerImg":"/fangfang/static/common/images/flat-avatar.png","projectName":"融创西双版纳国际旅游度假区","agentName":"团队长-王楠|销售-王俊凯|顾问-王源","agentPhone":"","companyName":"","storeName":"","auditStatus":"1","visitStatus":"1","landingStatus":"","earnestStatus":"","tradeStatus":"","protectDay":"25","status":"20","attacheName":"","isMy":"","search":"","dateStr":"最近到访时间 2019-11-30 17:45","date":"最近到访时间 2019-11-30 17:45","statusStr":"到访成功\n保护期剩余25天","relatedData":"到访成功\n保护期剩余25天","beforeDate":"","afterDate":""},{"id":"","remarks":"","createBy":"","createDate":"2019-11-28 14:26:30","updateBy":"","updateDate":"2019-11-28 14:37:47","user":"","agentUser":"","storeId":"","agentId":"bd50b94b2d7349a9aa69dc08f99fa644","projectId":"2616d1191d6a444b94b2a743384bfb6e","preparationId":"a562e997b59d47199a6ba4649e8a8c1b","customerName":"王思聪","customerPhone":"15323258055","customerImg":"/fangfang/static/common/images/flat-avatar.png","projectName":"海映兰屿","agentName":"团队长-王楠|销售-王俊凯|顾问-王源","agentPhone":"","companyName":"","storeName":"","auditStatus":"1","visitStatus":"1","landingStatus":"","earnestStatus":"2","tradeStatus":"","protectDay":"21","status":"20","attacheName":"","isMy":"","search":"","dateStr":"最近到访时间 2019-11-28 14:37","date":"最近到访时间 2019-11-28 14:37","statusStr":"到访成功\n保护期剩余21天","relatedData":"到访成功\n保护期剩余21天","beforeDate":"","afterDate":""},{"id":"","remarks":"","createBy":"","createDate":"2019-11-28 14:01:23","updateBy":"","updateDate":"2019-11-28 14:02:12","user":"","agentUser":"","storeId":"","agentId":"bd50b94b2d7349a9aa69dc08f99fa644","projectId":"1014ea5af90f483e86af89e1ea3e8665","preparationId":"894912fb0e4e43feba990da75d6c32f6","customerName":"王健林","customerPhone":"13752071477","customerImg":"/fangfang/static/common/images/flat-avatar.png","projectName":"温泉新都孔雀城风情园","agentName":"团队长-王楠|销售-王俊凯|顾问-王源","agentPhone":"","companyName":"","storeName":"","auditStatus":"1","visitStatus":"0","landingStatus":"","earnestStatus":"","tradeStatus":"","protectDay":"21","status":"20","attacheName":"","isMy":"","search":"","dateStr":"最近到访时间 2019-11-28 14:02","date":"最近到访时间 2019-11-28 14:02","statusStr":"到访申请","relatedData":"到访申请","beforeDate":"","afterDate":""},{"id":"","remarks":"","createBy":"","createDate":"2019-11-28 13:25:58","updateBy":"","updateDate":"2019-11-28 13:34:10","user":"","agentUser":"","storeId":"","agentId":"bd50b94b2d7349a9aa69dc08f99fa644","projectId":"1014ea5af90f483e86af89e1ea3e8665","preparationId":"c392a5d539ca45a582c827fbf2087735","customerName":"王思聪","customerPhone":"15323258055","customerImg":"/fangfang/static/common/images/flat-avatar.png","projectName":"温泉新都孔雀城风情园","agentName":"团队长-王楠|销售-王俊凯|顾问-王源","agentPhone":"","companyName":"","storeName":"","auditStatus":"1","visitStatus":"1","landingStatus":"","earnestStatus":"","tradeStatus":"","protectDay":"21","status":"20","attacheName":"","isMy":"","search":"","dateStr":"最近到访时间 2019-11-28 13:34","date":"最近到访时间 2019-11-28 13:34","statusStr":"到访成功\n保护期剩余21天","relatedData":"到访成功\n保护期剩余21天","beforeDate":"","afterDate":""},{"id":"","remarks":"","createBy":"","createDate":"2019-11-27 17:43:12","updateBy":"","updateDate":"2019-11-27 17:46:55","user":"","agentUser":"","storeId":"","agentId":"bd50b94b2d7349a9aa69dc08f99fa644","projectId":"371dac37607c41d4b2e7de8581783188","preparationId":"1aa93f93d0dd4416a1d61ce56d717f8d","customerName":"王大哥","customerPhone":"18852369636","customerImg":"/fangfang/static/common/images/flat-avatar.png","projectName":"融创.春风十里","agentName":"团队长-王楠|销售-王俊凯|顾问-王源","agentPhone":"","companyName":"","storeName":"","auditStatus":"1","visitStatus":"1","landingStatus":"","earnestStatus":"","tradeStatus":"","protectDay":"20","status":"20","attacheName":"","isMy":"","search":"","dateStr":"最近到访时间 2019-11-27 17:46","date":"最近到访时间 2019-11-27 17:46","statusStr":"到访成功\n保护期剩余20天","relatedData":"到访成功\n保护期剩余20天","beforeDate":"","afterDate":""}]}
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
         * total : 0
         * rows : [{"id":"","remarks":"","createBy":"","createDate":"2019-11-30 17:44:01","updateBy":"","updateDate":"2019-11-30 17:45:38","user":"","agentUser":"","storeId":"","agentId":"bd50b94b2d7349a9aa69dc08f99fa644","projectId":"129dcdd7a342486386853fa6d3725cba","preparationId":"f446bf8d0d764075b87fdffdad5b214a","customerName":"王健林","customerPhone":"13752071477","customerImg":"/fangfang/static/common/images/flat-avatar.png","projectName":"融创西双版纳国际旅游度假区","agentName":"团队长-王楠|销售-王俊凯|顾问-王源","agentPhone":"","companyName":"","storeName":"","auditStatus":"1","visitStatus":"1","landingStatus":"","earnestStatus":"","tradeStatus":"","protectDay":"25","status":"20","attacheName":"","isMy":"","search":"","dateStr":"最近到访时间 2019-11-30 17:45","date":"最近到访时间 2019-11-30 17:45","statusStr":"到访成功\n保护期剩余25天","relatedData":"到访成功\n保护期剩余25天","beforeDate":"","afterDate":""},{"id":"","remarks":"","createBy":"","createDate":"2019-11-28 14:26:30","updateBy":"","updateDate":"2019-11-28 14:37:47","user":"","agentUser":"","storeId":"","agentId":"bd50b94b2d7349a9aa69dc08f99fa644","projectId":"2616d1191d6a444b94b2a743384bfb6e","preparationId":"a562e997b59d47199a6ba4649e8a8c1b","customerName":"王思聪","customerPhone":"15323258055","customerImg":"/fangfang/static/common/images/flat-avatar.png","projectName":"海映兰屿","agentName":"团队长-王楠|销售-王俊凯|顾问-王源","agentPhone":"","companyName":"","storeName":"","auditStatus":"1","visitStatus":"1","landingStatus":"","earnestStatus":"2","tradeStatus":"","protectDay":"21","status":"20","attacheName":"","isMy":"","search":"","dateStr":"最近到访时间 2019-11-28 14:37","date":"最近到访时间 2019-11-28 14:37","statusStr":"到访成功\n保护期剩余21天","relatedData":"到访成功\n保护期剩余21天","beforeDate":"","afterDate":""},{"id":"","remarks":"","createBy":"","createDate":"2019-11-28 14:01:23","updateBy":"","updateDate":"2019-11-28 14:02:12","user":"","agentUser":"","storeId":"","agentId":"bd50b94b2d7349a9aa69dc08f99fa644","projectId":"1014ea5af90f483e86af89e1ea3e8665","preparationId":"894912fb0e4e43feba990da75d6c32f6","customerName":"王健林","customerPhone":"13752071477","customerImg":"/fangfang/static/common/images/flat-avatar.png","projectName":"温泉新都孔雀城风情园","agentName":"团队长-王楠|销售-王俊凯|顾问-王源","agentPhone":"","companyName":"","storeName":"","auditStatus":"1","visitStatus":"0","landingStatus":"","earnestStatus":"","tradeStatus":"","protectDay":"21","status":"20","attacheName":"","isMy":"","search":"","dateStr":"最近到访时间 2019-11-28 14:02","date":"最近到访时间 2019-11-28 14:02","statusStr":"到访申请","relatedData":"到访申请","beforeDate":"","afterDate":""},{"id":"","remarks":"","createBy":"","createDate":"2019-11-28 13:25:58","updateBy":"","updateDate":"2019-11-28 13:34:10","user":"","agentUser":"","storeId":"","agentId":"bd50b94b2d7349a9aa69dc08f99fa644","projectId":"1014ea5af90f483e86af89e1ea3e8665","preparationId":"c392a5d539ca45a582c827fbf2087735","customerName":"王思聪","customerPhone":"15323258055","customerImg":"/fangfang/static/common/images/flat-avatar.png","projectName":"温泉新都孔雀城风情园","agentName":"团队长-王楠|销售-王俊凯|顾问-王源","agentPhone":"","companyName":"","storeName":"","auditStatus":"1","visitStatus":"1","landingStatus":"","earnestStatus":"","tradeStatus":"","protectDay":"21","status":"20","attacheName":"","isMy":"","search":"","dateStr":"最近到访时间 2019-11-28 13:34","date":"最近到访时间 2019-11-28 13:34","statusStr":"到访成功\n保护期剩余21天","relatedData":"到访成功\n保护期剩余21天","beforeDate":"","afterDate":""},{"id":"","remarks":"","createBy":"","createDate":"2019-11-27 17:43:12","updateBy":"","updateDate":"2019-11-27 17:46:55","user":"","agentUser":"","storeId":"","agentId":"bd50b94b2d7349a9aa69dc08f99fa644","projectId":"371dac37607c41d4b2e7de8581783188","preparationId":"1aa93f93d0dd4416a1d61ce56d717f8d","customerName":"王大哥","customerPhone":"18852369636","customerImg":"/fangfang/static/common/images/flat-avatar.png","projectName":"融创.春风十里","agentName":"团队长-王楠|销售-王俊凯|顾问-王源","agentPhone":"","companyName":"","storeName":"","auditStatus":"1","visitStatus":"1","landingStatus":"","earnestStatus":"","tradeStatus":"","protectDay":"20","status":"20","attacheName":"","isMy":"","search":"","dateStr":"最近到访时间 2019-11-27 17:46","date":"最近到访时间 2019-11-27 17:46","statusStr":"到访成功\n保护期剩余20天","relatedData":"到访成功\n保护期剩余20天","beforeDate":"","afterDate":""}]
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
             * createDate : 2019-11-30 17:44:01
             * updateBy :
             * updateDate : 2019-11-30 17:45:38
             * user :
             * agentUser :
             * storeId :
             * agentId : bd50b94b2d7349a9aa69dc08f99fa644
             * projectId : 129dcdd7a342486386853fa6d3725cba
             * preparationId : f446bf8d0d764075b87fdffdad5b214a
             * customerName : 王健林
             * customerPhone : 13752071477
             * customerImg : /fangfang/static/common/images/flat-avatar.png
             * projectName : 融创西双版纳国际旅游度假区
             * agentName : 团队长-王楠|销售-王俊凯|顾问-王源
             * agentPhone :
             * companyName :
             * storeName :
             * auditStatus : 1
             * visitStatus : 1
             * landingStatus :
             * earnestStatus :
             * tradeStatus :
             * protectDay : 25
             * status : 20
             * attacheName :
             * isMy :
             * search :
             * dateStr : 最近到访时间 2019-11-30 17:45
             * date : 最近到访时间 2019-11-30 17:45
             * statusStr : 到访成功
             保护期剩余25天
             * relatedData : 到访成功
             保护期剩余25天
             * beforeDate :
             * afterDate :
             */

            private String id;
            private String remarks;
            private String createBy;
            private String createDate;
            private String updateBy;
            private String updateDate;
            private String user;
            private String agentUser;
            private String storeId;
            private String agentId;
            private String projectId;
            private String preparationId;
            private String customerName;
            private String customerPhone;
            private String customerImg;
            private String projectName;
            private String agentName;
            private String agentPhone;
            private String companyName;
            private String storeName;
            private String auditStatus;
            private String visitStatus;
            private String landingStatus;
            private String earnestStatus;
            private String tradeStatus;
            private String protectDay;
            private String status;
            private String attacheName;
            private String isMy;
            private String search;
            private String dateStr;
            private String date;
            private String statusStr;
            private String relatedData;
            private String beforeDate;
            private String afterDate;

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

            public String getAgentUser() {
                return agentUser;
            }

            public void setAgentUser(String agentUser) {
                this.agentUser = agentUser;
            }

            public String getStoreId() {
                return storeId;
            }

            public void setStoreId(String storeId) {
                this.storeId = storeId;
            }

            public String getAgentId() {
                return agentId;
            }

            public void setAgentId(String agentId) {
                this.agentId = agentId;
            }

            public String getProjectId() {
                return projectId;
            }

            public void setProjectId(String projectId) {
                this.projectId = projectId;
            }

            public String getPreparationId() {
                return preparationId;
            }

            public void setPreparationId(String preparationId) {
                this.preparationId = preparationId;
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

            public String getCustomerImg() {
                return customerImg;
            }

            public void setCustomerImg(String customerImg) {
                this.customerImg = customerImg;
            }

            public String getProjectName() {
                return projectName;
            }

            public void setProjectName(String projectName) {
                this.projectName = projectName;
            }

            public String getAgentName() {
                return agentName;
            }

            public void setAgentName(String agentName) {
                this.agentName = agentName;
            }

            public String getAgentPhone() {
                return agentPhone;
            }

            public void setAgentPhone(String agentPhone) {
                this.agentPhone = agentPhone;
            }

            public String getCompanyName() {
                return companyName;
            }

            public void setCompanyName(String companyName) {
                this.companyName = companyName;
            }

            public String getStoreName() {
                return storeName;
            }

            public void setStoreName(String storeName) {
                this.storeName = storeName;
            }

            public String getAuditStatus() {
                return auditStatus;
            }

            public void setAuditStatus(String auditStatus) {
                this.auditStatus = auditStatus;
            }

            public String getVisitStatus() {
                return visitStatus;
            }

            public void setVisitStatus(String visitStatus) {
                this.visitStatus = visitStatus;
            }

            public String getLandingStatus() {
                return landingStatus;
            }

            public void setLandingStatus(String landingStatus) {
                this.landingStatus = landingStatus;
            }

            public String getEarnestStatus() {
                return earnestStatus;
            }

            public void setEarnestStatus(String earnestStatus) {
                this.earnestStatus = earnestStatus;
            }

            public String getTradeStatus() {
                return tradeStatus;
            }

            public void setTradeStatus(String tradeStatus) {
                this.tradeStatus = tradeStatus;
            }

            public String getProtectDay() {
                return protectDay;
            }

            public void setProtectDay(String protectDay) {
                this.protectDay = protectDay;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getAttacheName() {
                return attacheName;
            }

            public void setAttacheName(String attacheName) {
                this.attacheName = attacheName;
            }

            public String getIsMy() {
                return isMy;
            }

            public void setIsMy(String isMy) {
                this.isMy = isMy;
            }

            public String getSearch() {
                return search;
            }

            public void setSearch(String search) {
                this.search = search;
            }

            public String getDateStr() {
                return dateStr;
            }

            public void setDateStr(String dateStr) {
                this.dateStr = dateStr;
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getStatusStr() {
                return statusStr;
            }

            public void setStatusStr(String statusStr) {
                this.statusStr = statusStr;
            }

            public String getRelatedData() {
                return relatedData;
            }

            public void setRelatedData(String relatedData) {
                this.relatedData = relatedData;
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
        }
    }
}
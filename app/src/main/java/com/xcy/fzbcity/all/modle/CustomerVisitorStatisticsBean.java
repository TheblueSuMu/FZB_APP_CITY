package com.xcy.fzbcity.all.modle;

import java.util.List;

public class CustomerVisitorStatisticsBean {

    /**
     * code : 1
     * msg : 成功
     * data : {"total":4,"rows":[{"id":"9e896eb108a34a7595b45146ac1ee4ff","remarks":"","createBy":"","createDate":"2020-03-13 12:07:23","updateBy":"","updateDate":"","filterField":"","webshopId":"284ab23018ad4fe5816adf66d2eefbb0","projectId":"","openId":"222","gender":"男","visitorPosition":"1","visitorStart":1584072443000,"visitorEnd":"","visitorDuration":"","networkType":"wifi","visitorAddress":"天津市南开区鞍山西道201号","visitorCoordinate":"","browseMode":"1","visitorName":"刘一天","visitTimes":1,"companyId":"","projectName":"","webshopName":"经纪人","token":"5f2ea3f31e984d61a7bf6e17a450a81e","customerImg":"/fangfang/static/common/images/flat-avatar.png","customerId":"944aed0d12f84d90bf0725dccbd9b04b","estateImg":"","searchName":""},{"id":"96e41462d28c44a59964bb8660fdd503","remarks":"","createBy":"","createDate":"2020-03-13 12:04:59","updateBy":"","updateDate":"","filterField":"","webshopId":"284ab23018ad4fe5816adf66d2eefbb0","projectId":"","openId":"444","gender":"男","visitorPosition":"1","visitorStart":1584072299000,"visitorEnd":1584072313000,"visitorDuration":"13秒","networkType":"wifi","visitorAddress":"天津市南开区鞍山西道201号","visitorCoordinate":"","browseMode":"1","visitorName":"Allen","visitTimes":2,"companyId":"","projectName":"","webshopName":"经纪人","token":"955c3618da134552aa8db46d1407dcdb","customerImg":"/fangfang/static/common/images/flat-avatar.png","customerId":"2d8d29593f384ec686c48cb2869b3492","estateImg":"","searchName":""},{"id":"f50e8ff5b20245249cb52d9c488a2549","remarks":"","createBy":"","createDate":"2020-03-13 10:50:05","updateBy":"","updateDate":"","filterField":"","webshopId":"284ab23018ad4fe5816adf66d2eefbb0","projectId":"353","openId":"444","gender":"男","visitorPosition":"1","visitorStart":1584067805000,"visitorEnd":1584067893000,"visitorDuration":"1分27秒","networkType":"wifi","visitorAddress":"天津市南开区鞍山西道201号","visitorCoordinate":"","browseMode":"1","visitorName":"Allen","visitTimes":1,"companyId":"","projectName":"紫光·逸水湾","webshopName":"经纪人","token":"955c3618da134552aa8db46d1407dcdb","customerImg":"/fangfang/static/common/images/flat-avatar.png","customerId":"2d8d29593f384ec686c48cb2869b3492","estateImg":"/fangfang/userfiles/642237ae64c542f2a257da961dadd9f8/attachment//ff/server/ffServerHousesPhoto/2019/12/1576229956656.jpg","searchName":""},{"id":"f651229f147642d3862214213229d6f8","remarks":"","createBy":"","createDate":"2020-03-12 13:42:00","updateBy":"","updateDate":"","filterField":"","webshopId":"284ab23018ad4fe5816adf66d2eefbb0","projectId":"","openId":"123","gender":"","visitorPosition":"1","visitorStart":1583991698000,"visitorEnd":"","visitorDuration":"","networkType":"","visitorAddress":"","visitorCoordinate":"","browseMode":"3","visitorName":"","visitTimes":1,"companyId":"","projectName":"","webshopName":"经纪人","token":"bef13c4d33414132bb7158e56c6b6ef7","customerImg":"/fangfang/static/common/images/flat-avatar.png","customerId":"30b1fac43913451b80e1ffefb090082f","estateImg":"","searchName":""}]}
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
         * total : 4
         * rows : [{"id":"9e896eb108a34a7595b45146ac1ee4ff","remarks":"","createBy":"","createDate":"2020-03-13 12:07:23","updateBy":"","updateDate":"","filterField":"","webshopId":"284ab23018ad4fe5816adf66d2eefbb0","projectId":"","openId":"222","gender":"男","visitorPosition":"1","visitorStart":1584072443000,"visitorEnd":"","visitorDuration":"","networkType":"wifi","visitorAddress":"天津市南开区鞍山西道201号","visitorCoordinate":"","browseMode":"1","visitorName":"刘一天","visitTimes":1,"companyId":"","projectName":"","webshopName":"经纪人","token":"5f2ea3f31e984d61a7bf6e17a450a81e","customerImg":"/fangfang/static/common/images/flat-avatar.png","customerId":"944aed0d12f84d90bf0725dccbd9b04b","estateImg":"","searchName":""},{"id":"96e41462d28c44a59964bb8660fdd503","remarks":"","createBy":"","createDate":"2020-03-13 12:04:59","updateBy":"","updateDate":"","filterField":"","webshopId":"284ab23018ad4fe5816adf66d2eefbb0","projectId":"","openId":"444","gender":"男","visitorPosition":"1","visitorStart":1584072299000,"visitorEnd":1584072313000,"visitorDuration":"13秒","networkType":"wifi","visitorAddress":"天津市南开区鞍山西道201号","visitorCoordinate":"","browseMode":"1","visitorName":"Allen","visitTimes":2,"companyId":"","projectName":"","webshopName":"经纪人","token":"955c3618da134552aa8db46d1407dcdb","customerImg":"/fangfang/static/common/images/flat-avatar.png","customerId":"2d8d29593f384ec686c48cb2869b3492","estateImg":"","searchName":""},{"id":"f50e8ff5b20245249cb52d9c488a2549","remarks":"","createBy":"","createDate":"2020-03-13 10:50:05","updateBy":"","updateDate":"","filterField":"","webshopId":"284ab23018ad4fe5816adf66d2eefbb0","projectId":"353","openId":"444","gender":"男","visitorPosition":"1","visitorStart":1584067805000,"visitorEnd":1584067893000,"visitorDuration":"1分27秒","networkType":"wifi","visitorAddress":"天津市南开区鞍山西道201号","visitorCoordinate":"","browseMode":"1","visitorName":"Allen","visitTimes":1,"companyId":"","projectName":"紫光·逸水湾","webshopName":"经纪人","token":"955c3618da134552aa8db46d1407dcdb","customerImg":"/fangfang/static/common/images/flat-avatar.png","customerId":"2d8d29593f384ec686c48cb2869b3492","estateImg":"/fangfang/userfiles/642237ae64c542f2a257da961dadd9f8/attachment//ff/server/ffServerHousesPhoto/2019/12/1576229956656.jpg","searchName":""},{"id":"f651229f147642d3862214213229d6f8","remarks":"","createBy":"","createDate":"2020-03-12 13:42:00","updateBy":"","updateDate":"","filterField":"","webshopId":"284ab23018ad4fe5816adf66d2eefbb0","projectId":"","openId":"123","gender":"","visitorPosition":"1","visitorStart":1583991698000,"visitorEnd":"","visitorDuration":"","networkType":"","visitorAddress":"","visitorCoordinate":"","browseMode":"3","visitorName":"","visitTimes":1,"companyId":"","projectName":"","webshopName":"经纪人","token":"bef13c4d33414132bb7158e56c6b6ef7","customerImg":"/fangfang/static/common/images/flat-avatar.png","customerId":"30b1fac43913451b80e1ffefb090082f","estateImg":"","searchName":""}]
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
             * id : 9e896eb108a34a7595b45146ac1ee4ff
             * remarks :
             * createBy :
             * createDate : 2020-03-13 12:07:23
             * updateBy :
             * updateDate :
             * filterField :
             * webshopId : 284ab23018ad4fe5816adf66d2eefbb0
             * projectId :
             * openId : 222
             * gender : 男
             * visitorPosition : 1
             * visitorStart : 1584072443000
             * visitorEnd :
             * visitorDuration :
             * networkType : wifi
             * visitorAddress : 天津市南开区鞍山西道201号
             * visitorCoordinate :
             * browseMode : 1
             * visitorName : 刘一天
             * visitTimes : 1
             * companyId :
             * projectName :
             * webshopName : 经纪人
             * token : 5f2ea3f31e984d61a7bf6e17a450a81e
             * customerImg : /fangfang/static/common/images/flat-avatar.png
             * customerId : 944aed0d12f84d90bf0725dccbd9b04b
             * estateImg :
             * searchName :
             */

            private String id;
            private String remarks;
            private String createBy;
            private String createDate;
            private String updateBy;
            private String updateDate;
            private String filterField;
            private String webshopId;
            private String projectId;
            private String openId;
            private String gender;
            private String visitorPosition;
            private long visitorStart;
            private String visitorEnd;
            private String visitorDuration;
            private String networkType;
            private String visitorAddress;
            private String visitorCoordinate;
            private String browseMode;
            private String visitorName;
            private int visitTimes;
            private String companyId;
            private String projectName;
            private String webshopName;
            private String token;
            private String customerImg;
            private String customerId;
            private String estateImg;
            private String searchName;

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

            public String getFilterField() {
                return filterField;
            }

            public void setFilterField(String filterField) {
                this.filterField = filterField;
            }

            public String getWebshopId() {
                return webshopId;
            }

            public void setWebshopId(String webshopId) {
                this.webshopId = webshopId;
            }

            public String getProjectId() {
                return projectId;
            }

            public void setProjectId(String projectId) {
                this.projectId = projectId;
            }

            public String getOpenId() {
                return openId;
            }

            public void setOpenId(String openId) {
                this.openId = openId;
            }

            public String getGender() {
                return gender;
            }

            public void setGender(String gender) {
                this.gender = gender;
            }

            public String getVisitorPosition() {
                return visitorPosition;
            }

            public void setVisitorPosition(String visitorPosition) {
                this.visitorPosition = visitorPosition;
            }

            public long getVisitorStart() {
                return visitorStart;
            }

            public void setVisitorStart(long visitorStart) {
                this.visitorStart = visitorStart;
            }

            public String getVisitorEnd() {
                return visitorEnd;
            }

            public void setVisitorEnd(String visitorEnd) {
                this.visitorEnd = visitorEnd;
            }

            public String getVisitorDuration() {
                return visitorDuration;
            }

            public void setVisitorDuration(String visitorDuration) {
                this.visitorDuration = visitorDuration;
            }

            public String getNetworkType() {
                return networkType;
            }

            public void setNetworkType(String networkType) {
                this.networkType = networkType;
            }

            public String getVisitorAddress() {
                return visitorAddress;
            }

            public void setVisitorAddress(String visitorAddress) {
                this.visitorAddress = visitorAddress;
            }

            public String getVisitorCoordinate() {
                return visitorCoordinate;
            }

            public void setVisitorCoordinate(String visitorCoordinate) {
                this.visitorCoordinate = visitorCoordinate;
            }

            public String getBrowseMode() {
                return browseMode;
            }

            public void setBrowseMode(String browseMode) {
                this.browseMode = browseMode;
            }

            public String getVisitorName() {
                return visitorName;
            }

            public void setVisitorName(String visitorName) {
                this.visitorName = visitorName;
            }

            public int getVisitTimes() {
                return visitTimes;
            }

            public void setVisitTimes(int visitTimes) {
                this.visitTimes = visitTimes;
            }

            public String getCompanyId() {
                return companyId;
            }

            public void setCompanyId(String companyId) {
                this.companyId = companyId;
            }

            public String getProjectName() {
                return projectName;
            }

            public void setProjectName(String projectName) {
                this.projectName = projectName;
            }

            public String getWebshopName() {
                return webshopName;
            }

            public void setWebshopName(String webshopName) {
                this.webshopName = webshopName;
            }

            public String getToken() {
                return token;
            }

            public void setToken(String token) {
                this.token = token;
            }

            public String getCustomerImg() {
                return customerImg;
            }

            public void setCustomerImg(String customerImg) {
                this.customerImg = customerImg;
            }

            public String getCustomerId() {
                return customerId;
            }

            public void setCustomerId(String customerId) {
                this.customerId = customerId;
            }

            public String getEstateImg() {
                return estateImg;
            }

            public void setEstateImg(String estateImg) {
                this.estateImg = estateImg;
            }

            public String getSearchName() {
                return searchName;
            }

            public void setSearchName(String searchName) {
                this.searchName = searchName;
            }
        }
    }
}

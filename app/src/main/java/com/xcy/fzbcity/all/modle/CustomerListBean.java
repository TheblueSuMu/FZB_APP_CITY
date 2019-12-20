package com.xcy.fzbcity.all.modle;

import java.util.List;

public class CustomerListBean {

    /**
     * code : 1
     * msg : 成功
     * data : {"total":1,"rows":[{"id":"","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","customerId":"9027257e80904372b4aef62f29327eba","customerName":"客户2","customerPhone":"15506094074","customerImg":"/fangfang/static/common/images/flat-avatar.png","status":"0","statusStr":"","stateName":"","preparationId":"944479b7d877410a8a23a6568857365c","colleagueNum":1,"project":{"id":"5eae2f4d635f449d9ce699ef3e15fda7","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","projectName":"恒大冰泉","developer":"","houseCompany":"","cityCompany":"","projectType":"","attache":"","cooperationState":"","onlineState":"","belongsArea":"","address":"","location":"","totalBuildings":"","hot":"","sort":"","remark":"","province":"","city":"","region":"","forwardingAmount":"","reportAmount":"","nation":"","hotSort":"","projectImg":"","projectListImg":"","buildingImg":"","browseNum":"","awardRules":"","commissionRules":"","searchName":"","collectionNum":"","productTypeId":"","productTypeSize":"","amountIncentiveId":"","cityName":"","haveInformation":"","projectTypeName":"","isgroup":"","groupNum":""},"landingId":"1cb43f91d1e243879431294337a8143e","noTrade":"","routeTimeId":"","searchName":""}]}
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
         * rows : [{"id":"","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","customerId":"9027257e80904372b4aef62f29327eba","customerName":"客户2","customerPhone":"15506094074","customerImg":"/fangfang/static/common/images/flat-avatar.png","status":"0","statusStr":"","stateName":"","preparationId":"944479b7d877410a8a23a6568857365c","colleagueNum":1,"project":{"id":"5eae2f4d635f449d9ce699ef3e15fda7","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","projectName":"恒大冰泉","developer":"","houseCompany":"","cityCompany":"","projectType":"","attache":"","cooperationState":"","onlineState":"","belongsArea":"","address":"","location":"","totalBuildings":"","hot":"","sort":"","remark":"","province":"","city":"","region":"","forwardingAmount":"","reportAmount":"","nation":"","hotSort":"","projectImg":"","projectListImg":"","buildingImg":"","browseNum":"","awardRules":"","commissionRules":"","searchName":"","collectionNum":"","productTypeId":"","productTypeSize":"","amountIncentiveId":"","cityName":"","haveInformation":"","projectTypeName":"","isgroup":"","groupNum":""},"landingId":"1cb43f91d1e243879431294337a8143e","noTrade":"","routeTimeId":"","searchName":""}]
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
             * customerId : 9027257e80904372b4aef62f29327eba
             * customerName : 客户2
             * customerPhone : 15506094074
             * customerImg : /fangfang/static/common/images/flat-avatar.png
             * status : 0
             * statusStr :
             * stateName :
             * preparationId : 944479b7d877410a8a23a6568857365c
             * colleagueNum : 1
             * project : {"id":"5eae2f4d635f449d9ce699ef3e15fda7","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","projectName":"恒大冰泉","developer":"","houseCompany":"","cityCompany":"","projectType":"","attache":"","cooperationState":"","onlineState":"","belongsArea":"","address":"","location":"","totalBuildings":"","hot":"","sort":"","remark":"","province":"","city":"","region":"","forwardingAmount":"","reportAmount":"","nation":"","hotSort":"","projectImg":"","projectListImg":"","buildingImg":"","browseNum":"","awardRules":"","commissionRules":"","searchName":"","collectionNum":"","productTypeId":"","productTypeSize":"","amountIncentiveId":"","cityName":"","haveInformation":"","projectTypeName":"","isgroup":"","groupNum":""}
             * landingId : 1cb43f91d1e243879431294337a8143e
             * noTrade :
             * routeTimeId :
             * searchName :
             */

            private String id;
            private String remarks;
            private String createBy;
            private String createDate;
            private String updateBy;
            private String updateDate;
            private String customerId;
            private String customerName;
            private String customerPhone;
            private String customerImg;
            private String status;
            private String statusStr;
            private String stateName;
            private String preparationId;
            private int colleagueNum;
            private ProjectBean project;
            private String landingId;
            private String noTrade;
            private String routeTimeId;
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

            public String getCustomerId() {
                return customerId;
            }

            public void setCustomerId(String customerId) {
                this.customerId = customerId;
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

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getStatusStr() {
                return statusStr;
            }

            public void setStatusStr(String statusStr) {
                this.statusStr = statusStr;
            }

            public String getStateName() {
                return stateName;
            }

            public void setStateName(String stateName) {
                this.stateName = stateName;
            }

            public String getPreparationId() {
                return preparationId;
            }

            public void setPreparationId(String preparationId) {
                this.preparationId = preparationId;
            }

            public int getColleagueNum() {
                return colleagueNum;
            }

            public void setColleagueNum(int colleagueNum) {
                this.colleagueNum = colleagueNum;
            }

            public ProjectBean getProject() {
                return project;
            }

            public void setProject(ProjectBean project) {
                this.project = project;
            }

            public String getLandingId() {
                return landingId;
            }

            public void setLandingId(String landingId) {
                this.landingId = landingId;
            }

            public String getNoTrade() {
                return noTrade;
            }

            public void setNoTrade(String noTrade) {
                this.noTrade = noTrade;
            }

            public String getRouteTimeId() {
                return routeTimeId;
            }

            public void setRouteTimeId(String routeTimeId) {
                this.routeTimeId = routeTimeId;
            }

            public String getSearchName() {
                return searchName;
            }

            public void setSearchName(String searchName) {
                this.searchName = searchName;
            }

            public static class ProjectBean {
                /**
                 * id : 5eae2f4d635f449d9ce699ef3e15fda7
                 * remarks :
                 * createBy :
                 * createDate :
                 * updateBy :
                 * updateDate :
                 * projectName : 恒大冰泉
                 * developer :
                 * houseCompany :
                 * cityCompany :
                 * projectType :
                 * attache :
                 * cooperationState :
                 * onlineState :
                 * belongsArea :
                 * address :
                 * location :
                 * totalBuildings :
                 * hot :
                 * sort :
                 * remark :
                 * province :
                 * city :
                 * region :
                 * forwardingAmount :
                 * reportAmount :
                 * nation :
                 * hotSort :
                 * projectImg :
                 * projectListImg :
                 * buildingImg :
                 * browseNum :
                 * awardRules :
                 * commissionRules :
                 * searchName :
                 * collectionNum :
                 * productTypeId :
                 * productTypeSize :
                 * amountIncentiveId :
                 * cityName :
                 * haveInformation :
                 * projectTypeName :
                 * isgroup :
                 * groupNum :
                 */

                private String id;
                private String remarks;
                private String createBy;
                private String createDate;
                private String updateBy;
                private String updateDate;
                private String projectName;
                private String developer;
                private String houseCompany;
                private String cityCompany;
                private String projectType;
                private String attache;
                private String cooperationState;
                private String onlineState;
                private String belongsArea;
                private String address;
                private String location;
                private String totalBuildings;
                private String hot;
                private String sort;
                private String remark;
                private String province;
                private String city;
                private String region;
                private String forwardingAmount;
                private String reportAmount;
                private String nation;
                private String hotSort;
                private String projectImg;
                private String projectListImg;
                private String buildingImg;
                private String browseNum;
                private String awardRules;
                private String commissionRules;
                private String searchName;
                private String collectionNum;
                private String productTypeId;
                private String productTypeSize;
                private String amountIncentiveId;
                private String cityName;
                private String haveInformation;
                private String projectTypeName;
                private String isgroup;
                private String groupNum;

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

                public String getProjectName() {
                    return projectName;
                }

                public void setProjectName(String projectName) {
                    this.projectName = projectName;
                }

                public String getDeveloper() {
                    return developer;
                }

                public void setDeveloper(String developer) {
                    this.developer = developer;
                }

                public String getHouseCompany() {
                    return houseCompany;
                }

                public void setHouseCompany(String houseCompany) {
                    this.houseCompany = houseCompany;
                }

                public String getCityCompany() {
                    return cityCompany;
                }

                public void setCityCompany(String cityCompany) {
                    this.cityCompany = cityCompany;
                }

                public String getProjectType() {
                    return projectType;
                }

                public void setProjectType(String projectType) {
                    this.projectType = projectType;
                }

                public String getAttache() {
                    return attache;
                }

                public void setAttache(String attache) {
                    this.attache = attache;
                }

                public String getCooperationState() {
                    return cooperationState;
                }

                public void setCooperationState(String cooperationState) {
                    this.cooperationState = cooperationState;
                }

                public String getOnlineState() {
                    return onlineState;
                }

                public void setOnlineState(String onlineState) {
                    this.onlineState = onlineState;
                }

                public String getBelongsArea() {
                    return belongsArea;
                }

                public void setBelongsArea(String belongsArea) {
                    this.belongsArea = belongsArea;
                }

                public String getAddress() {
                    return address;
                }

                public void setAddress(String address) {
                    this.address = address;
                }

                public String getLocation() {
                    return location;
                }

                public void setLocation(String location) {
                    this.location = location;
                }

                public String getTotalBuildings() {
                    return totalBuildings;
                }

                public void setTotalBuildings(String totalBuildings) {
                    this.totalBuildings = totalBuildings;
                }

                public String getHot() {
                    return hot;
                }

                public void setHot(String hot) {
                    this.hot = hot;
                }

                public String getSort() {
                    return sort;
                }

                public void setSort(String sort) {
                    this.sort = sort;
                }

                public String getRemark() {
                    return remark;
                }

                public void setRemark(String remark) {
                    this.remark = remark;
                }

                public String getProvince() {
                    return province;
                }

                public void setProvince(String province) {
                    this.province = province;
                }

                public String getCity() {
                    return city;
                }

                public void setCity(String city) {
                    this.city = city;
                }

                public String getRegion() {
                    return region;
                }

                public void setRegion(String region) {
                    this.region = region;
                }

                public String getForwardingAmount() {
                    return forwardingAmount;
                }

                public void setForwardingAmount(String forwardingAmount) {
                    this.forwardingAmount = forwardingAmount;
                }

                public String getReportAmount() {
                    return reportAmount;
                }

                public void setReportAmount(String reportAmount) {
                    this.reportAmount = reportAmount;
                }

                public String getNation() {
                    return nation;
                }

                public void setNation(String nation) {
                    this.nation = nation;
                }

                public String getHotSort() {
                    return hotSort;
                }

                public void setHotSort(String hotSort) {
                    this.hotSort = hotSort;
                }

                public String getProjectImg() {
                    return projectImg;
                }

                public void setProjectImg(String projectImg) {
                    this.projectImg = projectImg;
                }

                public String getProjectListImg() {
                    return projectListImg;
                }

                public void setProjectListImg(String projectListImg) {
                    this.projectListImg = projectListImg;
                }

                public String getBuildingImg() {
                    return buildingImg;
                }

                public void setBuildingImg(String buildingImg) {
                    this.buildingImg = buildingImg;
                }

                public String getBrowseNum() {
                    return browseNum;
                }

                public void setBrowseNum(String browseNum) {
                    this.browseNum = browseNum;
                }

                public String getAwardRules() {
                    return awardRules;
                }

                public void setAwardRules(String awardRules) {
                    this.awardRules = awardRules;
                }

                public String getCommissionRules() {
                    return commissionRules;
                }

                public void setCommissionRules(String commissionRules) {
                    this.commissionRules = commissionRules;
                }

                public String getSearchName() {
                    return searchName;
                }

                public void setSearchName(String searchName) {
                    this.searchName = searchName;
                }

                public String getCollectionNum() {
                    return collectionNum;
                }

                public void setCollectionNum(String collectionNum) {
                    this.collectionNum = collectionNum;
                }

                public String getProductTypeId() {
                    return productTypeId;
                }

                public void setProductTypeId(String productTypeId) {
                    this.productTypeId = productTypeId;
                }

                public String getProductTypeSize() {
                    return productTypeSize;
                }

                public void setProductTypeSize(String productTypeSize) {
                    this.productTypeSize = productTypeSize;
                }

                public String getAmountIncentiveId() {
                    return amountIncentiveId;
                }

                public void setAmountIncentiveId(String amountIncentiveId) {
                    this.amountIncentiveId = amountIncentiveId;
                }

                public String getCityName() {
                    return cityName;
                }

                public void setCityName(String cityName) {
                    this.cityName = cityName;
                }

                public String getHaveInformation() {
                    return haveInformation;
                }

                public void setHaveInformation(String haveInformation) {
                    this.haveInformation = haveInformation;
                }

                public String getProjectTypeName() {
                    return projectTypeName;
                }

                public void setProjectTypeName(String projectTypeName) {
                    this.projectTypeName = projectTypeName;
                }

                public String getIsgroup() {
                    return isgroup;
                }

                public void setIsgroup(String isgroup) {
                    this.isgroup = isgroup;
                }

                public String getGroupNum() {
                    return groupNum;
                }

                public void setGroupNum(String groupNum) {
                    this.groupNum = groupNum;
                }
            }
        }
    }
}
package com.xcy.fzbcity.all.modle;

public class FamilyInfoBean {
    /**
     * code : 1
     * msg : 成功
     * data : {"id":"b56125f47155474dbe1feaa9366d9ee7","remarks":"","createBy":"","createDate":"2019-06-16 11:04:03","updateBy":"","updateDate":"2019-12-07 15:55:46","project":{"id":"ef3f647c7e3643bc882c9fc365634a1a","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","projectName":"丽江时光","developer":"","houseCompany":"","cityCompany":"","projectType":"","cityType":"","showroom":"1","attache":"","cooperationState":"","onlineState":"","belongsArea":"","address":"云南省丽江市古城区金山北路","location":"100.281021,26.884087","totalBuildings":"","hot":"","sort":"","remark":"","province":"","city":"","region":"","forwardingAmount":"","reportAmount":"","nation":"","hotSort":"","projectImg":"","projectListImg":"","buildingImg":"","browseNum":"","awardRules":"","commissionRules":"","searchName":"","collectionNum":"","productTypeId":"","productTypeSize":"","amountIncentiveId":"","cityName":"","haveInformation":"","projectTypeName":"","isgroup":"","groupNum":"","developerId":"","onlineAuditState":"","applyBy":"","applyDate":"","auditBy":"","auditDate":"","reason":""},"familyName":"A","productType":"1","room":"1","hall":"1","toilet":"1","apartment":"","familyArea":"50","familyOrientation":"南","getHouseRate":"","modelHouse":"1","floorPlan":"/fangfang/userfiles/3c37d25396a940f9b784b4c180f7db37/attachment//ff/server/ffServerFamilyInfomation/2019/6/1573524426482.jpg","modelHouseChart":"","familySlideImgList":"","average":"10000","total":"100.00","downpayment":"40.00","percentage":"40","loan":"60.00","monthly":"3000","years":"20","interest":"1.23","title":"啊手动阀手动阀","text":"啊手动阀手动阀手动阀手动阀","isText":"1","isShow":"1","build":"17栋,19栋","saleStatus":"在售"}
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
         * id : b56125f47155474dbe1feaa9366d9ee7
         * remarks :
         * createBy :
         * createDate : 2019-06-16 11:04:03
         * updateBy :
         * updateDate : 2019-12-07 15:55:46
         * project : {"id":"ef3f647c7e3643bc882c9fc365634a1a","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","projectName":"丽江时光","developer":"","houseCompany":"","cityCompany":"","projectType":"","cityType":"","showroom":"1","attache":"","cooperationState":"","onlineState":"","belongsArea":"","address":"云南省丽江市古城区金山北路","location":"100.281021,26.884087","totalBuildings":"","hot":"","sort":"","remark":"","province":"","city":"","region":"","forwardingAmount":"","reportAmount":"","nation":"","hotSort":"","projectImg":"","projectListImg":"","buildingImg":"","browseNum":"","awardRules":"","commissionRules":"","searchName":"","collectionNum":"","productTypeId":"","productTypeSize":"","amountIncentiveId":"","cityName":"","haveInformation":"","projectTypeName":"","isgroup":"","groupNum":"","developerId":"","onlineAuditState":"","applyBy":"","applyDate":"","auditBy":"","auditDate":"","reason":""}
         * familyName : A
         * productType : 1
         * room : 1
         * hall : 1
         * toilet : 1
         * apartment :
         * familyArea : 50
         * familyOrientation : 南
         * getHouseRate :
         * modelHouse : 1
         * floorPlan : /fangfang/userfiles/3c37d25396a940f9b784b4c180f7db37/attachment//ff/server/ffServerFamilyInfomation/2019/6/1573524426482.jpg
         * modelHouseChart :
         * familySlideImgList :
         * average : 10000
         * total : 100.00
         * downpayment : 40.00
         * percentage : 40
         * loan : 60.00
         * monthly : 3000
         * years : 20
         * interest : 1.23
         * title : 啊手动阀手动阀
         * text : 啊手动阀手动阀手动阀手动阀
         * isText : 1
         * isShow : 1
         * build : 17栋,19栋
         * saleStatus : 在售
         */

        private String id;
        private String remarks;
        private String createBy;
        private String createDate;
        private String updateBy;
        private String updateDate;
        private ProjectBean project;
        private String familyName;
        private String productType;
        private String room;
        private String hall;
        private String toilet;
        private String apartment;
        private String familyArea;
        private String familyOrientation;
        private String getHouseRate;
        private String modelHouse;
        private String floorPlan;
        private String modelHouseChart;
        private String familySlideImgList;
        private String average;
        private String total;
        private String downpayment;
        private String percentage;
        private String loan;
        private String monthly;
        private String years;
        private String interest;
        private String title;
        private String text;
        private String isText;
        private String isShow;
        private String build;
        private String saleStatus;

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

        public ProjectBean getProject() {
            return project;
        }

        public void setProject(ProjectBean project) {
            this.project = project;
        }

        public String getFamilyName() {
            return familyName;
        }

        public void setFamilyName(String familyName) {
            this.familyName = familyName;
        }

        public String getProductType() {
            return productType;
        }

        public void setProductType(String productType) {
            this.productType = productType;
        }

        public String getRoom() {
            return room;
        }

        public void setRoom(String room) {
            this.room = room;
        }

        public String getHall() {
            return hall;
        }

        public void setHall(String hall) {
            this.hall = hall;
        }

        public String getToilet() {
            return toilet;
        }

        public void setToilet(String toilet) {
            this.toilet = toilet;
        }

        public String getApartment() {
            return apartment;
        }

        public void setApartment(String apartment) {
            this.apartment = apartment;
        }

        public String getFamilyArea() {
            return familyArea;
        }

        public void setFamilyArea(String familyArea) {
            this.familyArea = familyArea;
        }

        public String getFamilyOrientation() {
            return familyOrientation;
        }

        public void setFamilyOrientation(String familyOrientation) {
            this.familyOrientation = familyOrientation;
        }

        public String getGetHouseRate() {
            return getHouseRate;
        }

        public void setGetHouseRate(String getHouseRate) {
            this.getHouseRate = getHouseRate;
        }

        public String getModelHouse() {
            return modelHouse;
        }

        public void setModelHouse(String modelHouse) {
            this.modelHouse = modelHouse;
        }

        public String getFloorPlan() {
            return floorPlan;
        }

        public void setFloorPlan(String floorPlan) {
            this.floorPlan = floorPlan;
        }

        public String getModelHouseChart() {
            return modelHouseChart;
        }

        public void setModelHouseChart(String modelHouseChart) {
            this.modelHouseChart = modelHouseChart;
        }

        public String getFamilySlideImgList() {
            return familySlideImgList;
        }

        public void setFamilySlideImgList(String familySlideImgList) {
            this.familySlideImgList = familySlideImgList;
        }

        public String getAverage() {
            return average;
        }

        public void setAverage(String average) {
            this.average = average;
        }

        public String getTotal() {
            return total;
        }

        public void setTotal(String total) {
            this.total = total;
        }

        public String getDownpayment() {
            return downpayment;
        }

        public void setDownpayment(String downpayment) {
            this.downpayment = downpayment;
        }

        public String getPercentage() {
            return percentage;
        }

        public void setPercentage(String percentage) {
            this.percentage = percentage;
        }

        public String getLoan() {
            return loan;
        }

        public void setLoan(String loan) {
            this.loan = loan;
        }

        public String getMonthly() {
            return monthly;
        }

        public void setMonthly(String monthly) {
            this.monthly = monthly;
        }

        public String getYears() {
            return years;
        }

        public void setYears(String years) {
            this.years = years;
        }

        public String getInterest() {
            return interest;
        }

        public void setInterest(String interest) {
            this.interest = interest;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getIsText() {
            return isText;
        }

        public void setIsText(String isText) {
            this.isText = isText;
        }

        public String getIsShow() {
            return isShow;
        }

        public void setIsShow(String isShow) {
            this.isShow = isShow;
        }

        public String getBuild() {
            return build;
        }

        public void setBuild(String build) {
            this.build = build;
        }

        public String getSaleStatus() {
            return saleStatus;
        }

        public void setSaleStatus(String saleStatus) {
            this.saleStatus = saleStatus;
        }

        public static class ProjectBean {
            /**
             * id : ef3f647c7e3643bc882c9fc365634a1a
             * remarks :
             * createBy :
             * createDate :
             * updateBy :
             * updateDate :
             * projectName : 丽江时光
             * developer :
             * houseCompany :
             * cityCompany :
             * projectType :
             * cityType :
             * showroom : 1
             * attache :
             * cooperationState :
             * onlineState :
             * belongsArea :
             * address : 云南省丽江市古城区金山北路
             * location : 100.281021,26.884087
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
             * developerId :
             * onlineAuditState :
             * applyBy :
             * applyDate :
             * auditBy :
             * auditDate :
             * reason :
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
            private String cityType;
            private String showroom;
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
            private String developerId;
            private String onlineAuditState;
            private String applyBy;
            private String applyDate;
            private String auditBy;
            private String auditDate;
            private String reason;

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

            public String getCityType() {
                return cityType;
            }

            public void setCityType(String cityType) {
                this.cityType = cityType;
            }

            public String getShowroom() {
                return showroom;
            }

            public void setShowroom(String showroom) {
                this.showroom = showroom;
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

            public String getDeveloperId() {
                return developerId;
            }

            public void setDeveloperId(String developerId) {
                this.developerId = developerId;
            }

            public String getOnlineAuditState() {
                return onlineAuditState;
            }

            public void setOnlineAuditState(String onlineAuditState) {
                this.onlineAuditState = onlineAuditState;
            }

            public String getApplyBy() {
                return applyBy;
            }

            public void setApplyBy(String applyBy) {
                this.applyBy = applyBy;
            }

            public String getApplyDate() {
                return applyDate;
            }

            public void setApplyDate(String applyDate) {
                this.applyDate = applyDate;
            }

            public String getAuditBy() {
                return auditBy;
            }

            public void setAuditBy(String auditBy) {
                this.auditBy = auditBy;
            }

            public String getAuditDate() {
                return auditDate;
            }

            public void setAuditDate(String auditDate) {
                this.auditDate = auditDate;
            }

            public String getReason() {
                return reason;
            }

            public void setReason(String reason) {
                this.reason = reason;
            }
        }
    }
}

package com.xcy.fzbcity.all.modle;

import java.util.List;

public class HomeListBean {


    /**
     * code : 1
     * msg : 成功
     * data : {"total":3,"rows":[{"id":"","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","projectId":"73c35fda713946de8cd0d411716ff070","projectName":"丽江时光","listPageCover":"/fangfang/userfiles/3c37d25396a940f9b784b4c180f7db37/attachment//ff/server/ffServerHousesPhoto/2019/6/5.jpg","projectImg":"/fangfang/userfiles/3c37d25396a940f9b784b4c180f7db37/attachment//ff/server/ffServerHousesPhoto/2019/6/2.jpg","detailAddress":"云南省丽江市丽江市古城区金山北路","browseNum":"247","forwardingAmount":"12","reportAmount":"19","collectionNum":"5","awardRules":"","commissionRules":"","productFeature":"核心地段,0首付,品牌房企,ts1","buildingArea":"570000","saleStatus":"3","location":"100.278929,26.890137","amountIncentiveId":"","guideRuleId":"3148a14b6864456e92878886730b179b","isPhone":"0","digit":"","isPapers":"0","projectType":"3","type":"1","percent":"6","amount":"","contractAmount":"","contractPercent":"","secondsAmount":"","isSeconds":"0","area":"丽江","isgroup":"1","groupNum":"1","salesOfficeLocation":"","productTypeSize":1,"productUnitPrice":"11500","productTotalPrice":"40","referenceToatlPrice":"","areaIntervalStart":"30","areaIntervalEnd":"40","areaInterval":"30-40㎡","monetaryUnit":"元/㎡","commission":"总房款的6%","secondPay":"无秒结","hot":"","city":"","nation":"","searchContent":"","searchName":"","attacheId":"","businessType":"","collectionId":"","isCollection":"","ffAttacheList":"","brokerUserPhone":"","projectLabel":"","projectLabelStr":"","comprehensiveSorting":"","comprehensiveSortingStr":"pm.create_date DESC","apartment":"","projectPriceStart":"","projectPriceEnd":"","areaSection":"","ffProjectTrait":"","procuctType":"","fitmentState":""},{"id":"","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","projectId":"93ab096079604c3cb1ccb5a977099cde","projectName":"融创东海湾","listPageCover":"/fangfang/userfiles/3c37d25396a940f9b784b4c180f7db37/attachment//ff/server/ffServerHousesPhoto/2019/6/01.jpg","projectImg":"/fangfang/userfiles/3c37d25396a940f9b784b4c180f7db37/attachment//ff/server/ffServerHousesPhoto/2019/6/01.jpg","detailAddress":"山东省烟台市烟台市龙口市融创东海湾","browseNum":"64","forwardingAmount":"3","reportAmount":"6","collectionNum":"1","awardRules":"","commissionRules":"","productFeature":"住宅,别墅","buildingArea":"988471","saleStatus":"3","location":"120.493936,37.74289","amountIncentiveId":"","guideRuleId":"5f1e840c172a4f6a80ed6170b49a5f2f","isPhone":"0","digit":"","isPapers":"1","projectType":"3","type":"0","percent":"","amount":"10000","contractAmount":"","contractPercent":"","secondsAmount":"1000","isSeconds":"1","area":"烟台","isgroup":"1","groupNum":"2","salesOfficeLocation":"","productTypeSize":1,"productUnitPrice":"8500","productTotalPrice":"","referenceToatlPrice":"","areaIntervalStart":"95","areaIntervalEnd":"115","areaInterval":"95-115㎡","monetaryUnit":"元/㎡","commission":"10000元/套","secondPay":"1000元","hot":"","city":"","nation":"","searchContent":"","searchName":"","attacheId":"","businessType":"","collectionId":"","isCollection":"","ffAttacheList":"","brokerUserPhone":"","projectLabel":"","projectLabelStr":"","comprehensiveSorting":"","comprehensiveSortingStr":"pm.create_date DESC","apartment":"","projectPriceStart":"","projectPriceEnd":"","areaSection":"","ffProjectTrait":"","procuctType":"","fitmentState":""},{"id":"","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","projectId":"5eae2f4d635f449d9ce699ef3e15fda7","projectName":"恒大冰泉","listPageCover":"/fangfang/userfiles/3c37d25396a940f9b784b4c180f7db37/attachment//ff/server/ffServerHousesPhoto/2019/6/test1.4c424f53.png","projectImg":"/fangfang/userfiles/3c37d25396a940f9b784b4c180f7db37/attachment//ff/server/ffServerHousesPhoto/2019/6/test1.4c424f53.png","detailAddress":"广东省广州市北京市北京市朝阳区西大望路12号14号楼","browseNum":"122","forwardingAmount":"0","reportAmount":"6","collectionNum":"1","awardRules":"","commissionRules":"","productFeature":"核心地段","buildingArea":"18000","saleStatus":"3","location":"116.4865,39.911015","amountIncentiveId":"","guideRuleId":"fb3c6de54ea145af8f3d54efde51c492","isPhone":"1","digit":"4,5,6,7","isPapers":"1","projectType":"3","type":"1","percent":"3","amount":"","contractAmount":"","contractPercent":"","secondsAmount":"1000","isSeconds":"1","area":"广州","isgroup":"0","groupNum":"0","salesOfficeLocation":"","productTypeSize":1,"productUnitPrice":"60000","productTotalPrice":"","referenceToatlPrice":"","areaIntervalStart":"70","areaIntervalEnd":"180","areaInterval":"70-180㎡","monetaryUnit":"元/㎡","commission":"总房款的3%","secondPay":"1000元","hot":"","city":"","nation":"","searchContent":"","searchName":"","attacheId":"","businessType":"","collectionId":"","isCollection":"","ffAttacheList":"","brokerUserPhone":"","projectLabel":"","projectLabelStr":"","comprehensiveSorting":"","comprehensiveSortingStr":"pm.create_date DESC","apartment":"","projectPriceStart":"","projectPriceEnd":"","areaSection":"","ffProjectTrait":"","procuctType":"","fitmentState":""}]}
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
         * total : 3
         * rows : [{"id":"","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","projectId":"73c35fda713946de8cd0d411716ff070","projectName":"丽江时光","listPageCover":"/fangfang/userfiles/3c37d25396a940f9b784b4c180f7db37/attachment//ff/server/ffServerHousesPhoto/2019/6/5.jpg","projectImg":"/fangfang/userfiles/3c37d25396a940f9b784b4c180f7db37/attachment//ff/server/ffServerHousesPhoto/2019/6/2.jpg","detailAddress":"云南省丽江市丽江市古城区金山北路","browseNum":"247","forwardingAmount":"12","reportAmount":"19","collectionNum":"5","awardRules":"","commissionRules":"","productFeature":"核心地段,0首付,品牌房企,ts1","buildingArea":"570000","saleStatus":"3","location":"100.278929,26.890137","amountIncentiveId":"","guideRuleId":"3148a14b6864456e92878886730b179b","isPhone":"0","digit":"","isPapers":"0","projectType":"3","type":"1","percent":"6","amount":"","contractAmount":"","contractPercent":"","secondsAmount":"","isSeconds":"0","area":"丽江","isgroup":"1","groupNum":"1","salesOfficeLocation":"","productTypeSize":1,"productUnitPrice":"11500","productTotalPrice":"40","referenceToatlPrice":"","areaIntervalStart":"30","areaIntervalEnd":"40","areaInterval":"30-40㎡","monetaryUnit":"元/㎡","commission":"总房款的6%","secondPay":"无秒结","hot":"","city":"","nation":"","searchContent":"","searchName":"","attacheId":"","businessType":"","collectionId":"","isCollection":"","ffAttacheList":"","brokerUserPhone":"","projectLabel":"","projectLabelStr":"","comprehensiveSorting":"","comprehensiveSortingStr":"pm.create_date DESC","apartment":"","projectPriceStart":"","projectPriceEnd":"","areaSection":"","ffProjectTrait":"","procuctType":"","fitmentState":""},{"id":"","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","projectId":"93ab096079604c3cb1ccb5a977099cde","projectName":"融创东海湾","listPageCover":"/fangfang/userfiles/3c37d25396a940f9b784b4c180f7db37/attachment//ff/server/ffServerHousesPhoto/2019/6/01.jpg","projectImg":"/fangfang/userfiles/3c37d25396a940f9b784b4c180f7db37/attachment//ff/server/ffServerHousesPhoto/2019/6/01.jpg","detailAddress":"山东省烟台市烟台市龙口市融创东海湾","browseNum":"64","forwardingAmount":"3","reportAmount":"6","collectionNum":"1","awardRules":"","commissionRules":"","productFeature":"住宅,别墅","buildingArea":"988471","saleStatus":"3","location":"120.493936,37.74289","amountIncentiveId":"","guideRuleId":"5f1e840c172a4f6a80ed6170b49a5f2f","isPhone":"0","digit":"","isPapers":"1","projectType":"3","type":"0","percent":"","amount":"10000","contractAmount":"","contractPercent":"","secondsAmount":"1000","isSeconds":"1","area":"烟台","isgroup":"1","groupNum":"2","salesOfficeLocation":"","productTypeSize":1,"productUnitPrice":"8500","productTotalPrice":"","referenceToatlPrice":"","areaIntervalStart":"95","areaIntervalEnd":"115","areaInterval":"95-115㎡","monetaryUnit":"元/㎡","commission":"10000元/套","secondPay":"1000元","hot":"","city":"","nation":"","searchContent":"","searchName":"","attacheId":"","businessType":"","collectionId":"","isCollection":"","ffAttacheList":"","brokerUserPhone":"","projectLabel":"","projectLabelStr":"","comprehensiveSorting":"","comprehensiveSortingStr":"pm.create_date DESC","apartment":"","projectPriceStart":"","projectPriceEnd":"","areaSection":"","ffProjectTrait":"","procuctType":"","fitmentState":""},{"id":"","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","projectId":"5eae2f4d635f449d9ce699ef3e15fda7","projectName":"恒大冰泉","listPageCover":"/fangfang/userfiles/3c37d25396a940f9b784b4c180f7db37/attachment//ff/server/ffServerHousesPhoto/2019/6/test1.4c424f53.png","projectImg":"/fangfang/userfiles/3c37d25396a940f9b784b4c180f7db37/attachment//ff/server/ffServerHousesPhoto/2019/6/test1.4c424f53.png","detailAddress":"广东省广州市北京市北京市朝阳区西大望路12号14号楼","browseNum":"122","forwardingAmount":"0","reportAmount":"6","collectionNum":"1","awardRules":"","commissionRules":"","productFeature":"核心地段","buildingArea":"18000","saleStatus":"3","location":"116.4865,39.911015","amountIncentiveId":"","guideRuleId":"fb3c6de54ea145af8f3d54efde51c492","isPhone":"1","digit":"4,5,6,7","isPapers":"1","projectType":"3","type":"1","percent":"3","amount":"","contractAmount":"","contractPercent":"","secondsAmount":"1000","isSeconds":"1","area":"广州","isgroup":"0","groupNum":"0","salesOfficeLocation":"","productTypeSize":1,"productUnitPrice":"60000","productTotalPrice":"","referenceToatlPrice":"","areaIntervalStart":"70","areaIntervalEnd":"180","areaInterval":"70-180㎡","monetaryUnit":"元/㎡","commission":"总房款的3%","secondPay":"1000元","hot":"","city":"","nation":"","searchContent":"","searchName":"","attacheId":"","businessType":"","collectionId":"","isCollection":"","ffAttacheList":"","brokerUserPhone":"","projectLabel":"","projectLabelStr":"","comprehensiveSorting":"","comprehensiveSortingStr":"pm.create_date DESC","apartment":"","projectPriceStart":"","projectPriceEnd":"","areaSection":"","ffProjectTrait":"","procuctType":"","fitmentState":""}]
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
             * projectId : 73c35fda713946de8cd0d411716ff070
             * projectName : 丽江时光
             * listPageCover : /fangfang/userfiles/3c37d25396a940f9b784b4c180f7db37/attachment//ff/server/ffServerHousesPhoto/2019/6/5.jpg
             * projectImg : /fangfang/userfiles/3c37d25396a940f9b784b4c180f7db37/attachment//ff/server/ffServerHousesPhoto/2019/6/2.jpg
             * detailAddress : 云南省丽江市丽江市古城区金山北路
             * browseNum : 247
             * forwardingAmount : 12
             * reportAmount : 19
             * collectionNum : 5
             * awardRules :
             * commissionRules :
             * productFeature : 核心地段,0首付,品牌房企,ts1
             * buildingArea : 570000
             * saleStatus : 3
             * location : 100.278929,26.890137
             * amountIncentiveId :
             * guideRuleId : 3148a14b6864456e92878886730b179b
             * isPhone : 0
             * digit :
             * isPapers : 0
             * projectType : 3
             * type : 1
             * percent : 6
             * amount :
             * contractAmount :
             * contractPercent :
             * secondsAmount :
             * isSeconds : 0
             * area : 丽江
             * isgroup : 1
             * groupNum : 1
             * salesOfficeLocation :
             * productTypeSize : 1
             * productUnitPrice : 11500
             * productTotalPrice : 40
             * referenceToatlPrice :
             * areaIntervalStart : 30
             * areaIntervalEnd : 40
             * areaInterval : 30-40㎡
             * monetaryUnit : 元/㎡
             * commission : 总房款的6%
             * secondPay : 无秒结
             * hot :
             * city :
             * nation :
             * searchContent :
             * searchName :
             * attacheId :
             * businessType :
             * collectionId :
             * isCollection :
             * ffAttacheList :
             * brokerUserPhone :
             * projectLabel :
             * projectLabelStr :
             * comprehensiveSorting :
             * comprehensiveSortingStr : pm.create_date DESC
             * apartment :
             * projectPriceStart :
             * projectPriceEnd :
             * areaSection :
             * ffProjectTrait :
             * procuctType :
             * fitmentState :
             */

            private String id;
            private String remarks;
            private String createBy;
            private String createDate;
            private String updateBy;
            private String updateDate;
            private String projectId;
            private String projectName;
            private String listPageCover;
            private String projectImg;
            private String detailAddress;
            private String browseNum;
            private String forwardingAmount;
            private String reportAmount;
            private String collectionNum;
            private String awardRules;
            private String commissionRules;
            private String productFeature;
            private String buildingArea;
            private String saleStatus;
            private String location;
            private String amountIncentiveId;
            private String guideRuleId;
            private String isPhone;
            private String digit;
            private String isPapers;
            private String projectType;
            private String type;
            private String percent;
            private String amount;
            private String contractAmount;
            private String contractPercent;
            private String secondsAmount;
            private String isSeconds;
            private String area;
            private String isgroup;
            private String groupNum;
            private String salesOfficeLocation;
            private int productTypeSize;
            private String productUnitPrice;
            private String productTotalPrice;
            private String referenceToatlPrice;
            private String areaIntervalStart;
            private String areaIntervalEnd;
            private String areaInterval;
            private String monetaryUnit;
            private String commission;
            private String secondPay;
            private String hot;
            private String city;
            private String nation;
            private String searchContent;
            private String searchName;
            private String attacheId;
            private String businessType;
            private String collectionId;
            private String isCollection;
            private String ffAttacheList;
            private String brokerUserPhone;
            private String projectLabel;
            private String projectLabelStr;
            private String comprehensiveSorting;
            private String comprehensiveSortingStr;
            private String apartment;
            private String projectPriceStart;
            private String projectPriceEnd;
            private String areaSection;
            private String ffProjectTrait;
            private String procuctType;
            private String fitmentState;

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

            public String getProjectId() {
                return projectId;
            }

            public void setProjectId(String projectId) {
                this.projectId = projectId;
            }

            public String getProjectName() {
                return projectName;
            }

            public void setProjectName(String projectName) {
                this.projectName = projectName;
            }

            public String getListPageCover() {
                return listPageCover;
            }

            public void setListPageCover(String listPageCover) {
                this.listPageCover = listPageCover;
            }

            public String getProjectImg() {
                return projectImg;
            }

            public void setProjectImg(String projectImg) {
                this.projectImg = projectImg;
            }

            public String getDetailAddress() {
                return detailAddress;
            }

            public void setDetailAddress(String detailAddress) {
                this.detailAddress = detailAddress;
            }

            public String getBrowseNum() {
                return browseNum;
            }

            public void setBrowseNum(String browseNum) {
                this.browseNum = browseNum;
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

            public String getCollectionNum() {
                return collectionNum;
            }

            public void setCollectionNum(String collectionNum) {
                this.collectionNum = collectionNum;
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

            public String getProductFeature() {
                return productFeature;
            }

            public void setProductFeature(String productFeature) {
                this.productFeature = productFeature;
            }

            public String getBuildingArea() {
                return buildingArea;
            }

            public void setBuildingArea(String buildingArea) {
                this.buildingArea = buildingArea;
            }

            public String getSaleStatus() {
                return saleStatus;
            }

            public void setSaleStatus(String saleStatus) {
                this.saleStatus = saleStatus;
            }

            public String getLocation() {
                return location;
            }

            public void setLocation(String location) {
                this.location = location;
            }

            public String getAmountIncentiveId() {
                return amountIncentiveId;
            }

            public void setAmountIncentiveId(String amountIncentiveId) {
                this.amountIncentiveId = amountIncentiveId;
            }

            public String getGuideRuleId() {
                return guideRuleId;
            }

            public void setGuideRuleId(String guideRuleId) {
                this.guideRuleId = guideRuleId;
            }

            public String getIsPhone() {
                return isPhone;
            }

            public void setIsPhone(String isPhone) {
                this.isPhone = isPhone;
            }

            public String getDigit() {
                return digit;
            }

            public void setDigit(String digit) {
                this.digit = digit;
            }

            public String getIsPapers() {
                return isPapers;
            }

            public void setIsPapers(String isPapers) {
                this.isPapers = isPapers;
            }

            public String getProjectType() {
                return projectType;
            }

            public void setProjectType(String projectType) {
                this.projectType = projectType;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getPercent() {
                return percent;
            }

            public void setPercent(String percent) {
                this.percent = percent;
            }

            public String getAmount() {
                return amount;
            }

            public void setAmount(String amount) {
                this.amount = amount;
            }

            public String getContractAmount() {
                return contractAmount;
            }

            public void setContractAmount(String contractAmount) {
                this.contractAmount = contractAmount;
            }

            public String getContractPercent() {
                return contractPercent;
            }

            public void setContractPercent(String contractPercent) {
                this.contractPercent = contractPercent;
            }

            public String getSecondsAmount() {
                return secondsAmount;
            }

            public void setSecondsAmount(String secondsAmount) {
                this.secondsAmount = secondsAmount;
            }

            public String getIsSeconds() {
                return isSeconds;
            }

            public void setIsSeconds(String isSeconds) {
                this.isSeconds = isSeconds;
            }

            public String getArea() {
                return area;
            }

            public void setArea(String area) {
                this.area = area;
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

            public String getSalesOfficeLocation() {
                return salesOfficeLocation;
            }

            public void setSalesOfficeLocation(String salesOfficeLocation) {
                this.salesOfficeLocation = salesOfficeLocation;
            }

            public int getProductTypeSize() {
                return productTypeSize;
            }

            public void setProductTypeSize(int productTypeSize) {
                this.productTypeSize = productTypeSize;
            }

            public String getProductUnitPrice() {
                return productUnitPrice;
            }

            public void setProductUnitPrice(String productUnitPrice) {
                this.productUnitPrice = productUnitPrice;
            }

            public String getProductTotalPrice() {
                return productTotalPrice;
            }

            public void setProductTotalPrice(String productTotalPrice) {
                this.productTotalPrice = productTotalPrice;
            }

            public String getReferenceToatlPrice() {
                return referenceToatlPrice;
            }

            public void setReferenceToatlPrice(String referenceToatlPrice) {
                this.referenceToatlPrice = referenceToatlPrice;
            }

            public String getAreaIntervalStart() {
                return areaIntervalStart;
            }

            public void setAreaIntervalStart(String areaIntervalStart) {
                this.areaIntervalStart = areaIntervalStart;
            }

            public String getAreaIntervalEnd() {
                return areaIntervalEnd;
            }

            public void setAreaIntervalEnd(String areaIntervalEnd) {
                this.areaIntervalEnd = areaIntervalEnd;
            }

            public String getAreaInterval() {
                return areaInterval;
            }

            public void setAreaInterval(String areaInterval) {
                this.areaInterval = areaInterval;
            }

            public String getMonetaryUnit() {
                return monetaryUnit;
            }

            public void setMonetaryUnit(String monetaryUnit) {
                this.monetaryUnit = monetaryUnit;
            }

            public String getCommission() {
                return commission;
            }

            public void setCommission(String commission) {
                this.commission = commission;
            }

            public String getSecondPay() {
                return secondPay;
            }

            public void setSecondPay(String secondPay) {
                this.secondPay = secondPay;
            }

            public String getHot() {
                return hot;
            }

            public void setHot(String hot) {
                this.hot = hot;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getNation() {
                return nation;
            }

            public void setNation(String nation) {
                this.nation = nation;
            }

            public String getSearchContent() {
                return searchContent;
            }

            public void setSearchContent(String searchContent) {
                this.searchContent = searchContent;
            }

            public String getSearchName() {
                return searchName;
            }

            public void setSearchName(String searchName) {
                this.searchName = searchName;
            }

            public String getAttacheId() {
                return attacheId;
            }

            public void setAttacheId(String attacheId) {
                this.attacheId = attacheId;
            }

            public String getBusinessType() {
                return businessType;
            }

            public void setBusinessType(String businessType) {
                this.businessType = businessType;
            }

            public String getCollectionId() {
                return collectionId;
            }

            public void setCollectionId(String collectionId) {
                this.collectionId = collectionId;
            }

            public String getIsCollection() {
                return isCollection;
            }

            public void setIsCollection(String isCollection) {
                this.isCollection = isCollection;
            }

            public String getFfAttacheList() {
                return ffAttacheList;
            }

            public void setFfAttacheList(String ffAttacheList) {
                this.ffAttacheList = ffAttacheList;
            }

            public String getBrokerUserPhone() {
                return brokerUserPhone;
            }

            public void setBrokerUserPhone(String brokerUserPhone) {
                this.brokerUserPhone = brokerUserPhone;
            }

            public String getProjectLabel() {
                return projectLabel;
            }

            public void setProjectLabel(String projectLabel) {
                this.projectLabel = projectLabel;
            }

            public String getProjectLabelStr() {
                return projectLabelStr;
            }

            public void setProjectLabelStr(String projectLabelStr) {
                this.projectLabelStr = projectLabelStr;
            }

            public String getComprehensiveSorting() {
                return comprehensiveSorting;
            }

            public void setComprehensiveSorting(String comprehensiveSorting) {
                this.comprehensiveSorting = comprehensiveSorting;
            }

            public String getComprehensiveSortingStr() {
                return comprehensiveSortingStr;
            }

            public void setComprehensiveSortingStr(String comprehensiveSortingStr) {
                this.comprehensiveSortingStr = comprehensiveSortingStr;
            }

            public String getApartment() {
                return apartment;
            }

            public void setApartment(String apartment) {
                this.apartment = apartment;
            }

            public String getProjectPriceStart() {
                return projectPriceStart;
            }

            public void setProjectPriceStart(String projectPriceStart) {
                this.projectPriceStart = projectPriceStart;
            }

            public String getProjectPriceEnd() {
                return projectPriceEnd;
            }

            public void setProjectPriceEnd(String projectPriceEnd) {
                this.projectPriceEnd = projectPriceEnd;
            }

            public String getAreaSection() {
                return areaSection;
            }

            public void setAreaSection(String areaSection) {
                this.areaSection = areaSection;
            }

            public String getFfProjectTrait() {
                return ffProjectTrait;
            }

            public void setFfProjectTrait(String ffProjectTrait) {
                this.ffProjectTrait = ffProjectTrait;
            }

            public String getProcuctType() {
                return procuctType;
            }

            public void setProcuctType(String procuctType) {
                this.procuctType = procuctType;
            }

            public String getFitmentState() {
                return fitmentState;
            }

            public void setFitmentState(String fitmentState) {
                this.fitmentState = fitmentState;
            }
        }
    }
}

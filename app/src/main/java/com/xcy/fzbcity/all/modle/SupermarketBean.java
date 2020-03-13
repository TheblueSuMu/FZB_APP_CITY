package com.xcy.fzbcity.all.modle;

import java.util.List;

public class SupermarketBean {

    /**
     * code : 1
     * msg : 成功
     * data : {"total":1,"rows":[{"id":"f38d4848969c4152b7b4faf59f03cb7a","remarks":"","createBy":"","createDate":"2020-03-11 16:08:46","updateBy":"","updateDate":"","filterField":"","webshopId":"1ba5c65a224e42c38188f0cc82bf93e5","projectId":"2f18d49c562844d799e7c8370e61bba5","publicPrivate":"1","hotPush":"0","serialNum":13,"hotNum":"","companyId":"c241db93cbd247f5a8aadf501806b56a","project":"","projectListVo":{"id":"","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","filterField":"","projectId":"2f18d49c562844d799e7c8370e61bba5","projectName":"KYD测试7.1","listPageCover":"/fangfang/userfiles/3c37d25396a940f9b784b4c180f7db37/attachment//ff/server/ffServerEstateManage/2020/1/1578276644134.jpg","projectImg":"/fangfang/userfiles/3c37d25396a940f9b784b4c180f7db37/attachment//ff/server/ffServerEstateManage/2020/1/1578300735693.jpg","detailAddress":"北京市北京市北京市朝阳区阜安西路","province":"","citys":"","region":"","browseNum":"16","forwardingAmount":"0","reportAmount":"0","collectionNum":"0","awardRules":"","commissionRules":"","productFeature":"0首付,核心地段","buildingArea":"33","saleStatus":"2","location":"116.479314,39.997297","amountIncentiveId":"","endDate":"","guideRuleId":"840cdc164ff44a3582b9b989bc48ccdf","isPhone":"0","digit":"","isPapers":"","projectType":"1","cityType":"2","agentPercent":"","agentUnitPrice":"","agentAmount":"","leaderPercent":"","leaderUnitPrice":"","leaderAmount":"","isSeconds":"","secondsAmount":"","area":"山东","isgroup":"0","groupNum":"0","salesOfficeLocation":"116.343059,39.922747","onlineState":"","saleDiscounts":"3","isAccessing":"","isAccessShow":"","accessDenomination":"","isTrade":"","isTradeShow":"","tradeDenomination":"","isPriceTrend":"","remindstatus1":"","remindstatus2":"","productTypeSize":6,"productUnitPrice":"2","productTotalPrice":"2","referenceToatlPrice":"0.00","referenceToatlUnit":"万元起/套","areaIntervalStart":"2","areaIntervalEnd":"2","areaInterval":"2-2㎡","monetaryUnit":"元起/㎡","commission":"","secondPay":"无秒结","hot":"","city":"","nation":"","searchContent":"","searchName":"","attacheId":"","businessType":"","collectionId":"","isCollection":"","ffAttacheList":"","brokerUserPhone":"","projectLabel":"","projectLabelStr":"","comprehensiveSorting":"","comprehensiveSortingStr":"pm.sort ASC,pm.update_date DESC","apartment":"","projectPriceStart":"","projectPriceEnd":"","areaSection":"","ffProjectTrait":"","procuctType":"","fitmentState":"","webShopProject":""},"company":"","userInfoVo":{"id":"111bdbee1723490ab081d314c8e6ba43","name":"","phone":"","identity":"","photo":"/fangfang/static/common/images/flat-avatar.png"},"userId":""}]}
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
         * rows : [{"id":"f38d4848969c4152b7b4faf59f03cb7a","remarks":"","createBy":"","createDate":"2020-03-11 16:08:46","updateBy":"","updateDate":"","filterField":"","webshopId":"1ba5c65a224e42c38188f0cc82bf93e5","projectId":"2f18d49c562844d799e7c8370e61bba5","publicPrivate":"1","hotPush":"0","serialNum":13,"hotNum":"","companyId":"c241db93cbd247f5a8aadf501806b56a","project":"","projectListVo":{"id":"","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","filterField":"","projectId":"2f18d49c562844d799e7c8370e61bba5","projectName":"KYD测试7.1","listPageCover":"/fangfang/userfiles/3c37d25396a940f9b784b4c180f7db37/attachment//ff/server/ffServerEstateManage/2020/1/1578276644134.jpg","projectImg":"/fangfang/userfiles/3c37d25396a940f9b784b4c180f7db37/attachment//ff/server/ffServerEstateManage/2020/1/1578300735693.jpg","detailAddress":"北京市北京市北京市朝阳区阜安西路","province":"","citys":"","region":"","browseNum":"16","forwardingAmount":"0","reportAmount":"0","collectionNum":"0","awardRules":"","commissionRules":"","productFeature":"0首付,核心地段","buildingArea":"33","saleStatus":"2","location":"116.479314,39.997297","amountIncentiveId":"","endDate":"","guideRuleId":"840cdc164ff44a3582b9b989bc48ccdf","isPhone":"0","digit":"","isPapers":"","projectType":"1","cityType":"2","agentPercent":"","agentUnitPrice":"","agentAmount":"","leaderPercent":"","leaderUnitPrice":"","leaderAmount":"","isSeconds":"","secondsAmount":"","area":"山东","isgroup":"0","groupNum":"0","salesOfficeLocation":"116.343059,39.922747","onlineState":"","saleDiscounts":"3","isAccessing":"","isAccessShow":"","accessDenomination":"","isTrade":"","isTradeShow":"","tradeDenomination":"","isPriceTrend":"","remindstatus1":"","remindstatus2":"","productTypeSize":6,"productUnitPrice":"2","productTotalPrice":"2","referenceToatlPrice":"0.00","referenceToatlUnit":"万元起/套","areaIntervalStart":"2","areaIntervalEnd":"2","areaInterval":"2-2㎡","monetaryUnit":"元起/㎡","commission":"","secondPay":"无秒结","hot":"","city":"","nation":"","searchContent":"","searchName":"","attacheId":"","businessType":"","collectionId":"","isCollection":"","ffAttacheList":"","brokerUserPhone":"","projectLabel":"","projectLabelStr":"","comprehensiveSorting":"","comprehensiveSortingStr":"pm.sort ASC,pm.update_date DESC","apartment":"","projectPriceStart":"","projectPriceEnd":"","areaSection":"","ffProjectTrait":"","procuctType":"","fitmentState":"","webShopProject":""},"company":"","userInfoVo":{"id":"111bdbee1723490ab081d314c8e6ba43","name":"","phone":"","identity":"","photo":"/fangfang/static/common/images/flat-avatar.png"},"userId":""}]
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
             * id : f38d4848969c4152b7b4faf59f03cb7a
             * remarks :
             * createBy :
             * createDate : 2020-03-11 16:08:46
             * updateBy :
             * updateDate :
             * filterField :
             * webshopId : 1ba5c65a224e42c38188f0cc82bf93e5
             * projectId : 2f18d49c562844d799e7c8370e61bba5
             * publicPrivate : 1
             * hotPush : 0
             * serialNum : 13
             * hotNum :
             * companyId : c241db93cbd247f5a8aadf501806b56a
             * project :
             * projectListVo : {"id":"","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","filterField":"","projectId":"2f18d49c562844d799e7c8370e61bba5","projectName":"KYD测试7.1","listPageCover":"/fangfang/userfiles/3c37d25396a940f9b784b4c180f7db37/attachment//ff/server/ffServerEstateManage/2020/1/1578276644134.jpg","projectImg":"/fangfang/userfiles/3c37d25396a940f9b784b4c180f7db37/attachment//ff/server/ffServerEstateManage/2020/1/1578300735693.jpg","detailAddress":"北京市北京市北京市朝阳区阜安西路","province":"","citys":"","region":"","browseNum":"16","forwardingAmount":"0","reportAmount":"0","collectionNum":"0","awardRules":"","commissionRules":"","productFeature":"0首付,核心地段","buildingArea":"33","saleStatus":"2","location":"116.479314,39.997297","amountIncentiveId":"","endDate":"","guideRuleId":"840cdc164ff44a3582b9b989bc48ccdf","isPhone":"0","digit":"","isPapers":"","projectType":"1","cityType":"2","agentPercent":"","agentUnitPrice":"","agentAmount":"","leaderPercent":"","leaderUnitPrice":"","leaderAmount":"","isSeconds":"","secondsAmount":"","area":"山东","isgroup":"0","groupNum":"0","salesOfficeLocation":"116.343059,39.922747","onlineState":"","saleDiscounts":"3","isAccessing":"","isAccessShow":"","accessDenomination":"","isTrade":"","isTradeShow":"","tradeDenomination":"","isPriceTrend":"","remindstatus1":"","remindstatus2":"","productTypeSize":6,"productUnitPrice":"2","productTotalPrice":"2","referenceToatlPrice":"0.00","referenceToatlUnit":"万元起/套","areaIntervalStart":"2","areaIntervalEnd":"2","areaInterval":"2-2㎡","monetaryUnit":"元起/㎡","commission":"","secondPay":"无秒结","hot":"","city":"","nation":"","searchContent":"","searchName":"","attacheId":"","businessType":"","collectionId":"","isCollection":"","ffAttacheList":"","brokerUserPhone":"","projectLabel":"","projectLabelStr":"","comprehensiveSorting":"","comprehensiveSortingStr":"pm.sort ASC,pm.update_date DESC","apartment":"","projectPriceStart":"","projectPriceEnd":"","areaSection":"","ffProjectTrait":"","procuctType":"","fitmentState":"","webShopProject":""}
             * company :
             * userInfoVo : {"id":"111bdbee1723490ab081d314c8e6ba43","name":"","phone":"","identity":"","photo":"/fangfang/static/common/images/flat-avatar.png"}
             * userId :
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
            private String publicPrivate;
            private String hotPush;
            private int serialNum;
            private String hotNum;
            private String companyId;
            private String project;
            private ProjectListVoBean projectListVo;
            private String company;
            private UserInfoVoBean userInfoVo;
            private String userId;

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

            public String getPublicPrivate() {
                return publicPrivate;
            }

            public void setPublicPrivate(String publicPrivate) {
                this.publicPrivate = publicPrivate;
            }

            public String getHotPush() {
                return hotPush;
            }

            public void setHotPush(String hotPush) {
                this.hotPush = hotPush;
            }

            public int getSerialNum() {
                return serialNum;
            }

            public void setSerialNum(int serialNum) {
                this.serialNum = serialNum;
            }

            public String getHotNum() {
                return hotNum;
            }

            public void setHotNum(String hotNum) {
                this.hotNum = hotNum;
            }

            public String getCompanyId() {
                return companyId;
            }

            public void setCompanyId(String companyId) {
                this.companyId = companyId;
            }

            public String getProject() {
                return project;
            }

            public void setProject(String project) {
                this.project = project;
            }

            public ProjectListVoBean getProjectListVo() {
                return projectListVo;
            }

            public void setProjectListVo(ProjectListVoBean projectListVo) {
                this.projectListVo = projectListVo;
            }

            public String getCompany() {
                return company;
            }

            public void setCompany(String company) {
                this.company = company;
            }

            public UserInfoVoBean getUserInfoVo() {
                return userInfoVo;
            }

            public void setUserInfoVo(UserInfoVoBean userInfoVo) {
                this.userInfoVo = userInfoVo;
            }

            public String getUserId() {
                return userId;
            }

            public void setUserId(String userId) {
                this.userId = userId;
            }

            public static class ProjectListVoBean {
                /**
                 * id :
                 * remarks :
                 * createBy :
                 * createDate :
                 * updateBy :
                 * updateDate :
                 * filterField :
                 * projectId : 2f18d49c562844d799e7c8370e61bba5
                 * projectName : KYD测试7.1
                 * listPageCover : /fangfang/userfiles/3c37d25396a940f9b784b4c180f7db37/attachment//ff/server/ffServerEstateManage/2020/1/1578276644134.jpg
                 * projectImg : /fangfang/userfiles/3c37d25396a940f9b784b4c180f7db37/attachment//ff/server/ffServerEstateManage/2020/1/1578300735693.jpg
                 * detailAddress : 北京市北京市北京市朝阳区阜安西路
                 * province :
                 * citys :
                 * region :
                 * browseNum : 16
                 * forwardingAmount : 0
                 * reportAmount : 0
                 * collectionNum : 0
                 * awardRules :
                 * commissionRules :
                 * productFeature : 0首付,核心地段
                 * buildingArea : 33
                 * saleStatus : 2
                 * location : 116.479314,39.997297
                 * amountIncentiveId :
                 * endDate :
                 * guideRuleId : 840cdc164ff44a3582b9b989bc48ccdf
                 * isPhone : 0
                 * digit :
                 * isPapers :
                 * projectType : 1
                 * cityType : 2
                 * agentPercent :
                 * agentUnitPrice :
                 * agentAmount :
                 * leaderPercent :
                 * leaderUnitPrice :
                 * leaderAmount :
                 * isSeconds :
                 * secondsAmount :
                 * area : 山东
                 * isgroup : 0
                 * groupNum : 0
                 * salesOfficeLocation : 116.343059,39.922747
                 * onlineState :
                 * saleDiscounts : 3
                 * isAccessing :
                 * isAccessShow :
                 * accessDenomination :
                 * isTrade :
                 * isTradeShow :
                 * tradeDenomination :
                 * isPriceTrend :
                 * remindstatus1 :
                 * remindstatus2 :
                 * productTypeSize : 6
                 * productUnitPrice : 2
                 * productTotalPrice : 2
                 * referenceToatlPrice : 0.00
                 * referenceToatlUnit : 万元起/套
                 * areaIntervalStart : 2
                 * areaIntervalEnd : 2
                 * areaInterval : 2-2㎡
                 * monetaryUnit : 元起/㎡
                 * commission :
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
                 * comprehensiveSortingStr : pm.sort ASC,pm.update_date DESC
                 * apartment :
                 * projectPriceStart :
                 * projectPriceEnd :
                 * areaSection :
                 * ffProjectTrait :
                 * procuctType :
                 * fitmentState :
                 * webShopProject :
                 */

                private String id;
                private String remarks;
                private String createBy;
                private String createDate;
                private String updateBy;
                private String updateDate;
                private String filterField;
                private String projectId;
                private String projectName;
                private String listPageCover;
                private String projectImg;
                private String detailAddress;
                private String province;
                private String citys;
                private String region;
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
                private String endDate;
                private String guideRuleId;
                private String isPhone;
                private String digit;
                private String isPapers;
                private String projectType;
                private String cityType;
                private String agentPercent;
                private String agentUnitPrice;
                private String agentAmount;
                private String leaderPercent;
                private String leaderUnitPrice;
                private String leaderAmount;
                private String isSeconds;
                private String secondsAmount;
                private String area;
                private String isgroup;
                private String groupNum;
                private String salesOfficeLocation;
                private String onlineState;
                private String saleDiscounts;
                private String isAccessing;
                private String isAccessShow;
                private String accessDenomination;
                private String isTrade;
                private String isTradeShow;
                private String tradeDenomination;
                private String isPriceTrend;
                private String remindstatus1;
                private String remindstatus2;
                private int productTypeSize;
                private String productUnitPrice;
                private String productTotalPrice;
                private String referenceToatlPrice;
                private String referenceToatlUnit;
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
                private String webShopProject;

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

                public String getProvince() {
                    return province;
                }

                public void setProvince(String province) {
                    this.province = province;
                }

                public String getCitys() {
                    return citys;
                }

                public void setCitys(String citys) {
                    this.citys = citys;
                }

                public String getRegion() {
                    return region;
                }

                public void setRegion(String region) {
                    this.region = region;
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

                public String getEndDate() {
                    return endDate;
                }

                public void setEndDate(String endDate) {
                    this.endDate = endDate;
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

                public String getCityType() {
                    return cityType;
                }

                public void setCityType(String cityType) {
                    this.cityType = cityType;
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

                public String getOnlineState() {
                    return onlineState;
                }

                public void setOnlineState(String onlineState) {
                    this.onlineState = onlineState;
                }

                public String getSaleDiscounts() {
                    return saleDiscounts;
                }

                public void setSaleDiscounts(String saleDiscounts) {
                    this.saleDiscounts = saleDiscounts;
                }

                public String getIsAccessing() {
                    return isAccessing;
                }

                public void setIsAccessing(String isAccessing) {
                    this.isAccessing = isAccessing;
                }

                public String getIsAccessShow() {
                    return isAccessShow;
                }

                public void setIsAccessShow(String isAccessShow) {
                    this.isAccessShow = isAccessShow;
                }

                public String getAccessDenomination() {
                    return accessDenomination;
                }

                public void setAccessDenomination(String accessDenomination) {
                    this.accessDenomination = accessDenomination;
                }

                public String getIsTrade() {
                    return isTrade;
                }

                public void setIsTrade(String isTrade) {
                    this.isTrade = isTrade;
                }

                public String getIsTradeShow() {
                    return isTradeShow;
                }

                public void setIsTradeShow(String isTradeShow) {
                    this.isTradeShow = isTradeShow;
                }

                public String getTradeDenomination() {
                    return tradeDenomination;
                }

                public void setTradeDenomination(String tradeDenomination) {
                    this.tradeDenomination = tradeDenomination;
                }

                public String getIsPriceTrend() {
                    return isPriceTrend;
                }

                public void setIsPriceTrend(String isPriceTrend) {
                    this.isPriceTrend = isPriceTrend;
                }

                public String getRemindstatus1() {
                    return remindstatus1;
                }

                public void setRemindstatus1(String remindstatus1) {
                    this.remindstatus1 = remindstatus1;
                }

                public String getRemindstatus2() {
                    return remindstatus2;
                }

                public void setRemindstatus2(String remindstatus2) {
                    this.remindstatus2 = remindstatus2;
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

                public String getReferenceToatlUnit() {
                    return referenceToatlUnit;
                }

                public void setReferenceToatlUnit(String referenceToatlUnit) {
                    this.referenceToatlUnit = referenceToatlUnit;
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

                public String getWebShopProject() {
                    return webShopProject;
                }

                public void setWebShopProject(String webShopProject) {
                    this.webShopProject = webShopProject;
                }
            }

            public static class UserInfoVoBean {
                /**
                 * id : 111bdbee1723490ab081d314c8e6ba43
                 * name :
                 * phone :
                 * identity :
                 * photo : /fangfang/static/common/images/flat-avatar.png
                 */

                private String id;
                private String name;
                private String phone;
                private String identity;
                private String photo;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getPhone() {
                    return phone;
                }

                public void setPhone(String phone) {
                    this.phone = phone;
                }

                public String getIdentity() {
                    return identity;
                }

                public void setIdentity(String identity) {
                    this.identity = identity;
                }

                public String getPhoto() {
                    return photo;
                }

                public void setPhoto(String photo) {
                    this.photo = photo;
                }
            }
        }
    }
}

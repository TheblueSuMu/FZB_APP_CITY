package com.xcy.fzbcity.all.modle;

import java.util.List;

public class DetailsBean {

    /**
     * code : 1
     * msg : 成功
     * data : {"project":{"id":"a2a23fc831c844629a600d2b781570dd","remarks":"","createBy":{"id":"3c37d25396a940f9b784b4c180f7db37","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","company":"","office":"","post":"","loginName":"","no":"","name":"","email":"","phone":"","oldPhone":"","mobile":"","loginIp":"","loginDate":"","loginFlag":"1","photo":"/fangfang/static/common/images/flat-avatar.png","qrCode":"","oldLoginName":"","newPassword":"","sign":"","oldLoginIp":"","oldLoginDate":"","wechatOpenid":"","wechatData":"","role":"","sex":"","province":"","city":"","county":"","identity":"","brokerId":"","storeId":"","companyManage":"","storeManage":"","classics":0,"team":"","admin":false,"roleNames":""},"createDate":"2019-08-10 14:08:10","updateBy":{"id":"3c37d25396a940f9b784b4c180f7db37","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","company":"","office":"","post":"","loginName":"","no":"","name":"","email":"","phone":"","oldPhone":"","mobile":"","loginIp":"","loginDate":"","loginFlag":"1","photo":"/fangfang/static/common/images/flat-avatar.png","qrCode":"","oldLoginName":"","newPassword":"","sign":"","oldLoginIp":"","oldLoginDate":"","wechatOpenid":"","wechatData":"","role":"","sex":"","province":"","city":"","county":"","identity":"","brokerId":"","storeId":"","companyManage":"","storeManage":"","classics":0,"team":"","admin":false,"roleNames":""},"updateDate":"2019-08-10 15:28:48","projectName":"丽江项目测试1","developer":{"id":"0c2413ac130b43ff98a51ab72b6a172c","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","name":"丽江时光置业有限公司","type":"","area":"","province":"","city":"","brand":"","cityCompany":"","typeName":"国外"},"houseCompany":"丽江时光置业有限公司","cityCompany":{"id":"c241db93cbd247f5a8aadf501806b56a","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","parentIds":"","name":"长春房坐标（吉海优房）","sort":30,"hasChildren":false,"area":"","code":"","type":"2","grade":"","address":"","zipCode":"","master":"","phone":"","fax":"","email":"","useable":"","primaryPerson":"","deputyPerson":"","childDeptList":"","province":"","city":"","county":"","areas":"","parentId":"0"},"projectType":"3","attache":{"id":"3a0ee28065a84d548c96ee51849b5222","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","company":"","office":"","post":"","loginName":"","no":"","name":"王珊珊","email":"","phone":"13944098039","oldPhone":"","mobile":"","loginIp":"","loginDate":"","loginFlag":"1","photo":"/fangfang/static/common/images/flat-avatar.png","qrCode":"","oldLoginName":"","newPassword":"","sign":"","oldLoginIp":"","oldLoginDate":"","wechatOpenid":"","wechatData":"","role":"","sex":"","province":"","city":"","county":"","identity":"","brokerId":"","storeId":"","companyManage":"","storeManage":"","classics":0,"team":"","admin":false,"roleNames":""},"cooperationState":"1","onlineState":"1","belongsArea":"云南省/丽江市/古城区","address":"云南省丽江市古城区","location":"100.232157,26.861463","totalBuildings":100,"hot":"1","sort":"","remark":"测试数据","province":"云南省","city":"丽江市","region":"古城区","forwardingAmount":"","reportAmount":2,"nation":"云南","hotSort":"","projectImg":"","projectListImg":"","buildingImg":"","browseNum":132,"awardRules":"","commissionRules":"","searchName":"丽江项目测试1|云南省丽江市古城区|住宅|贴心物业,位置佳,前佣,核心地段,配套丰富,学区房,首付分|","collectionNum":0,"productTypeId":"0009f92a90084965a08b7a69515f4a73","productTypeSize":1,"amountIncentiveId":"15416ce8131048da8926ab6d44f2b25b","cityName":"丽江测试","haveInformation":"","projectTypeName":"旅居","isgroup":0,"groupNum":0},"gsonOption":{"yAxis":{"axisLabel":{"show":true,"textStyle":{"color":"#a8b6cc"}},"splitLine":{"lineStyle":{"color":"#eeeeee"},"show":true},"type":"value"},"xAxis":{"axisLabel":{"textStyle":{"color":"#a8b6cc"}},"data":["00","01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24"],"type":"category","boundaryGap":false},"series":[{"areaStyle":{"color":"#e6eefe"},"data":[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],"itemStyle":{"color":"#3b82d2"},"type":"line"}]},"receivableMoneyMap":{"invoiceMoney":"0","surplusMoney":"0","backMoney":"0","receivableMoney":"0"},"operation":{"accessingNumber":0,"tradeNumber":0,"reportNumber":1,"isIslandNumber":0,"earnestMoneyNumber":0,"reportOk":0,"landingNumber":0,"InvalidNum":1}}
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
         * project : {"id":"a2a23fc831c844629a600d2b781570dd","remarks":"","createBy":{"id":"3c37d25396a940f9b784b4c180f7db37","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","company":"","office":"","post":"","loginName":"","no":"","name":"","email":"","phone":"","oldPhone":"","mobile":"","loginIp":"","loginDate":"","loginFlag":"1","photo":"/fangfang/static/common/images/flat-avatar.png","qrCode":"","oldLoginName":"","newPassword":"","sign":"","oldLoginIp":"","oldLoginDate":"","wechatOpenid":"","wechatData":"","role":"","sex":"","province":"","city":"","county":"","identity":"","brokerId":"","storeId":"","companyManage":"","storeManage":"","classics":0,"team":"","admin":false,"roleNames":""},"createDate":"2019-08-10 14:08:10","updateBy":{"id":"3c37d25396a940f9b784b4c180f7db37","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","company":"","office":"","post":"","loginName":"","no":"","name":"","email":"","phone":"","oldPhone":"","mobile":"","loginIp":"","loginDate":"","loginFlag":"1","photo":"/fangfang/static/common/images/flat-avatar.png","qrCode":"","oldLoginName":"","newPassword":"","sign":"","oldLoginIp":"","oldLoginDate":"","wechatOpenid":"","wechatData":"","role":"","sex":"","province":"","city":"","county":"","identity":"","brokerId":"","storeId":"","companyManage":"","storeManage":"","classics":0,"team":"","admin":false,"roleNames":""},"updateDate":"2019-08-10 15:28:48","projectName":"丽江项目测试1","developer":{"id":"0c2413ac130b43ff98a51ab72b6a172c","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","name":"丽江时光置业有限公司","type":"","area":"","province":"","city":"","brand":"","cityCompany":"","typeName":"国外"},"houseCompany":"丽江时光置业有限公司","cityCompany":{"id":"c241db93cbd247f5a8aadf501806b56a","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","parentIds":"","name":"长春房坐标（吉海优房）","sort":30,"hasChildren":false,"area":"","code":"","type":"2","grade":"","address":"","zipCode":"","master":"","phone":"","fax":"","email":"","useable":"","primaryPerson":"","deputyPerson":"","childDeptList":"","province":"","city":"","county":"","areas":"","parentId":"0"},"projectType":"3","attache":{"id":"3a0ee28065a84d548c96ee51849b5222","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","company":"","office":"","post":"","loginName":"","no":"","name":"王珊珊","email":"","phone":"13944098039","oldPhone":"","mobile":"","loginIp":"","loginDate":"","loginFlag":"1","photo":"/fangfang/static/common/images/flat-avatar.png","qrCode":"","oldLoginName":"","newPassword":"","sign":"","oldLoginIp":"","oldLoginDate":"","wechatOpenid":"","wechatData":"","role":"","sex":"","province":"","city":"","county":"","identity":"","brokerId":"","storeId":"","companyManage":"","storeManage":"","classics":0,"team":"","admin":false,"roleNames":""},"cooperationState":"1","onlineState":"1","belongsArea":"云南省/丽江市/古城区","address":"云南省丽江市古城区","location":"100.232157,26.861463","totalBuildings":100,"hot":"1","sort":"","remark":"测试数据","province":"云南省","city":"丽江市","region":"古城区","forwardingAmount":"","reportAmount":2,"nation":"云南","hotSort":"","projectImg":"","projectListImg":"","buildingImg":"","browseNum":132,"awardRules":"","commissionRules":"","searchName":"丽江项目测试1|云南省丽江市古城区|住宅|贴心物业,位置佳,前佣,核心地段,配套丰富,学区房,首付分|","collectionNum":0,"productTypeId":"0009f92a90084965a08b7a69515f4a73","productTypeSize":1,"amountIncentiveId":"15416ce8131048da8926ab6d44f2b25b","cityName":"丽江测试","haveInformation":"","projectTypeName":"旅居","isgroup":0,"groupNum":0}
         * gsonOption : {"yAxis":{"axisLabel":{"show":true,"textStyle":{"color":"#a8b6cc"}},"splitLine":{"lineStyle":{"color":"#eeeeee"},"show":true},"type":"value"},"xAxis":{"axisLabel":{"textStyle":{"color":"#a8b6cc"}},"data":["00","01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24"],"type":"category","boundaryGap":false},"series":[{"areaStyle":{"color":"#e6eefe"},"data":[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],"itemStyle":{"color":"#3b82d2"},"type":"line"}]}
         * receivableMoneyMap : {"invoiceMoney":"0","surplusMoney":"0","backMoney":"0","receivableMoney":"0"}
         * operation : {"accessingNumber":0,"tradeNumber":0,"reportNumber":1,"isIslandNumber":0,"earnestMoneyNumber":0,"reportOk":0,"landingNumber":0,"InvalidNum":1}
         */

        private ProjectBean project;
        private GsonOptionBean gsonOption;
        private ReceivableMoneyMapBean receivableMoneyMap;
        private OperationBean operation;

        public ProjectBean getProject() {
            return project;
        }

        public void setProject(ProjectBean project) {
            this.project = project;
        }

        public GsonOptionBean getGsonOption() {
            return gsonOption;
        }

        public void setGsonOption(GsonOptionBean gsonOption) {
            this.gsonOption = gsonOption;
        }

        public ReceivableMoneyMapBean getReceivableMoneyMap() {
            return receivableMoneyMap;
        }

        public void setReceivableMoneyMap(ReceivableMoneyMapBean receivableMoneyMap) {
            this.receivableMoneyMap = receivableMoneyMap;
        }

        public OperationBean getOperation() {
            return operation;
        }

        public void setOperation(OperationBean operation) {
            this.operation = operation;
        }

        public static class ProjectBean {
            /**
             * id : a2a23fc831c844629a600d2b781570dd
             * remarks :
             * createBy : {"id":"3c37d25396a940f9b784b4c180f7db37","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","company":"","office":"","post":"","loginName":"","no":"","name":"","email":"","phone":"","oldPhone":"","mobile":"","loginIp":"","loginDate":"","loginFlag":"1","photo":"/fangfang/static/common/images/flat-avatar.png","qrCode":"","oldLoginName":"","newPassword":"","sign":"","oldLoginIp":"","oldLoginDate":"","wechatOpenid":"","wechatData":"","role":"","sex":"","province":"","city":"","county":"","identity":"","brokerId":"","storeId":"","companyManage":"","storeManage":"","classics":0,"team":"","admin":false,"roleNames":""}
             * createDate : 2019-08-10 14:08:10
             * updateBy : {"id":"3c37d25396a940f9b784b4c180f7db37","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","company":"","office":"","post":"","loginName":"","no":"","name":"","email":"","phone":"","oldPhone":"","mobile":"","loginIp":"","loginDate":"","loginFlag":"1","photo":"/fangfang/static/common/images/flat-avatar.png","qrCode":"","oldLoginName":"","newPassword":"","sign":"","oldLoginIp":"","oldLoginDate":"","wechatOpenid":"","wechatData":"","role":"","sex":"","province":"","city":"","county":"","identity":"","brokerId":"","storeId":"","companyManage":"","storeManage":"","classics":0,"team":"","admin":false,"roleNames":""}
             * updateDate : 2019-08-10 15:28:48
             * projectName : 丽江项目测试1
             * developer : {"id":"0c2413ac130b43ff98a51ab72b6a172c","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","name":"丽江时光置业有限公司","type":"","area":"","province":"","city":"","brand":"","cityCompany":"","typeName":"国外"}
             * houseCompany : 丽江时光置业有限公司
             * cityCompany : {"id":"c241db93cbd247f5a8aadf501806b56a","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","parentIds":"","name":"长春房坐标（吉海优房）","sort":30,"hasChildren":false,"area":"","code":"","type":"2","grade":"","address":"","zipCode":"","master":"","phone":"","fax":"","email":"","useable":"","primaryPerson":"","deputyPerson":"","childDeptList":"","province":"","city":"","county":"","areas":"","parentId":"0"}
             * projectType : 3
             * attache : {"id":"3a0ee28065a84d548c96ee51849b5222","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","company":"","office":"","post":"","loginName":"","no":"","name":"王珊珊","email":"","phone":"13944098039","oldPhone":"","mobile":"","loginIp":"","loginDate":"","loginFlag":"1","photo":"/fangfang/static/common/images/flat-avatar.png","qrCode":"","oldLoginName":"","newPassword":"","sign":"","oldLoginIp":"","oldLoginDate":"","wechatOpenid":"","wechatData":"","role":"","sex":"","province":"","city":"","county":"","identity":"","brokerId":"","storeId":"","companyManage":"","storeManage":"","classics":0,"team":"","admin":false,"roleNames":""}
             * cooperationState : 1
             * onlineState : 1
             * belongsArea : 云南省/丽江市/古城区
             * address : 云南省丽江市古城区
             * location : 100.232157,26.861463
             * totalBuildings : 100
             * hot : 1
             * sort :
             * remark : 测试数据
             * province : 云南省
             * city : 丽江市
             * region : 古城区
             * forwardingAmount :
             * reportAmount : 2
             * nation : 云南
             * hotSort :
             * projectImg :
             * projectListImg :
             * buildingImg :
             * browseNum : 132
             * awardRules :
             * commissionRules :
             * searchName : 丽江项目测试1|云南省丽江市古城区|住宅|贴心物业,位置佳,前佣,核心地段,配套丰富,学区房,首付分|
             * collectionNum : 0
             * productTypeId : 0009f92a90084965a08b7a69515f4a73
             * productTypeSize : 1
             * amountIncentiveId : 15416ce8131048da8926ab6d44f2b25b
             * cityName : 丽江测试
             * haveInformation :
             * projectTypeName : 旅居
             * isgroup : 0
             * groupNum : 0
             */

            private String id;
            private String remarks;
            private CreateByBean createBy;
            private String createDate;
            private UpdateByBean updateBy;
            private String updateDate;
            private String projectName;
            private DeveloperBean developer;
            private String houseCompany;
            private CityCompanyBean cityCompany;
            private String projectType;
            private AttacheBean attache;
            private String cooperationState;
            private String onlineState;
            private String belongsArea;
            private String address;
            private String location;
            private int totalBuildings;
            private String hot;
            private String sort;
            private String remark;
            private String province;
            private String city;
            private String region;
            private String forwardingAmount;
            private int reportAmount;
            private String nation;
            private String hotSort;
            private String projectImg;
            private String projectListImg;
            private String buildingImg;
            private int browseNum;
            private String awardRules;
            private String commissionRules;
            private String searchName;
            private int collectionNum;
            private String productTypeId;
            private int productTypeSize;
            private String amountIncentiveId;
            private String cityName;
            private String haveInformation;
            private String projectTypeName;
            private int isgroup;
            private int groupNum;

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

            public CreateByBean getCreateBy() {
                return createBy;
            }

            public void setCreateBy(CreateByBean createBy) {
                this.createBy = createBy;
            }

            public String getCreateDate() {
                return createDate;
            }

            public void setCreateDate(String createDate) {
                this.createDate = createDate;
            }

            public UpdateByBean getUpdateBy() {
                return updateBy;
            }

            public void setUpdateBy(UpdateByBean updateBy) {
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

            public DeveloperBean getDeveloper() {
                return developer;
            }

            public void setDeveloper(DeveloperBean developer) {
                this.developer = developer;
            }

            public String getHouseCompany() {
                return houseCompany;
            }

            public void setHouseCompany(String houseCompany) {
                this.houseCompany = houseCompany;
            }

            public CityCompanyBean getCityCompany() {
                return cityCompany;
            }

            public void setCityCompany(CityCompanyBean cityCompany) {
                this.cityCompany = cityCompany;
            }

            public String getProjectType() {
                return projectType;
            }

            public void setProjectType(String projectType) {
                this.projectType = projectType;
            }

            public AttacheBean getAttache() {
                return attache;
            }

            public void setAttache(AttacheBean attache) {
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

            public int getTotalBuildings() {
                return totalBuildings;
            }

            public void setTotalBuildings(int totalBuildings) {
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

            public int getReportAmount() {
                return reportAmount;
            }

            public void setReportAmount(int reportAmount) {
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

            public int getBrowseNum() {
                return browseNum;
            }

            public void setBrowseNum(int browseNum) {
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

            public int getCollectionNum() {
                return collectionNum;
            }

            public void setCollectionNum(int collectionNum) {
                this.collectionNum = collectionNum;
            }

            public String getProductTypeId() {
                return productTypeId;
            }

            public void setProductTypeId(String productTypeId) {
                this.productTypeId = productTypeId;
            }

            public int getProductTypeSize() {
                return productTypeSize;
            }

            public void setProductTypeSize(int productTypeSize) {
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

            public int getIsgroup() {
                return isgroup;
            }

            public void setIsgroup(int isgroup) {
                this.isgroup = isgroup;
            }

            public int getGroupNum() {
                return groupNum;
            }

            public void setGroupNum(int groupNum) {
                this.groupNum = groupNum;
            }

            public static class CreateByBean {
                /**
                 * id : 3c37d25396a940f9b784b4c180f7db37
                 * remarks :
                 * createBy :
                 * createDate :
                 * updateBy :
                 * updateDate :
                 * company :
                 * office :
                 * post :
                 * loginName :
                 * no :
                 * name :
                 * email :
                 * phone :
                 * oldPhone :
                 * mobile :
                 * loginIp :
                 * loginDate :
                 * loginFlag : 1
                 * photo : /fangfang/static/common/images/flat-avatar.png
                 * qrCode :
                 * oldLoginName :
                 * newPassword :
                 * sign :
                 * oldLoginIp :
                 * oldLoginDate :
                 * wechatOpenid :
                 * wechatData :
                 * role :
                 * sex :
                 * province :
                 * city :
                 * county :
                 * identity :
                 * brokerId :
                 * storeId :
                 * companyManage :
                 * storeManage :
                 * classics : 0
                 * team :
                 * admin : false
                 * roleNames :
                 */

                private String id;
                private String remarks;
                private String createBy;
                private String createDate;
                private String updateBy;
                private String updateDate;
                private String company;
                private String office;
                private String post;
                private String loginName;
                private String no;
                private String name;
                private String email;
                private String phone;
                private String oldPhone;
                private String mobile;
                private String loginIp;
                private String loginDate;
                private String loginFlag;
                private String photo;
                private String qrCode;
                private String oldLoginName;
                private String newPassword;
                private String sign;
                private String oldLoginIp;
                private String oldLoginDate;
                private String wechatOpenid;
                private String wechatData;
                private String role;
                private String sex;
                private String province;
                private String city;
                private String county;
                private String identity;
                private String brokerId;
                private String storeId;
                private String companyManage;
                private String storeManage;
                private int classics;
                private String team;
                private boolean admin;
                private String roleNames;

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

                public String getCompany() {
                    return company;
                }

                public void setCompany(String company) {
                    this.company = company;
                }

                public String getOffice() {
                    return office;
                }

                public void setOffice(String office) {
                    this.office = office;
                }

                public String getPost() {
                    return post;
                }

                public void setPost(String post) {
                    this.post = post;
                }

                public String getLoginName() {
                    return loginName;
                }

                public void setLoginName(String loginName) {
                    this.loginName = loginName;
                }

                public String getNo() {
                    return no;
                }

                public void setNo(String no) {
                    this.no = no;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getEmail() {
                    return email;
                }

                public void setEmail(String email) {
                    this.email = email;
                }

                public String getPhone() {
                    return phone;
                }

                public void setPhone(String phone) {
                    this.phone = phone;
                }

                public String getOldPhone() {
                    return oldPhone;
                }

                public void setOldPhone(String oldPhone) {
                    this.oldPhone = oldPhone;
                }

                public String getMobile() {
                    return mobile;
                }

                public void setMobile(String mobile) {
                    this.mobile = mobile;
                }

                public String getLoginIp() {
                    return loginIp;
                }

                public void setLoginIp(String loginIp) {
                    this.loginIp = loginIp;
                }

                public String getLoginDate() {
                    return loginDate;
                }

                public void setLoginDate(String loginDate) {
                    this.loginDate = loginDate;
                }

                public String getLoginFlag() {
                    return loginFlag;
                }

                public void setLoginFlag(String loginFlag) {
                    this.loginFlag = loginFlag;
                }

                public String getPhoto() {
                    return photo;
                }

                public void setPhoto(String photo) {
                    this.photo = photo;
                }

                public String getQrCode() {
                    return qrCode;
                }

                public void setQrCode(String qrCode) {
                    this.qrCode = qrCode;
                }

                public String getOldLoginName() {
                    return oldLoginName;
                }

                public void setOldLoginName(String oldLoginName) {
                    this.oldLoginName = oldLoginName;
                }

                public String getNewPassword() {
                    return newPassword;
                }

                public void setNewPassword(String newPassword) {
                    this.newPassword = newPassword;
                }

                public String getSign() {
                    return sign;
                }

                public void setSign(String sign) {
                    this.sign = sign;
                }

                public String getOldLoginIp() {
                    return oldLoginIp;
                }

                public void setOldLoginIp(String oldLoginIp) {
                    this.oldLoginIp = oldLoginIp;
                }

                public String getOldLoginDate() {
                    return oldLoginDate;
                }

                public void setOldLoginDate(String oldLoginDate) {
                    this.oldLoginDate = oldLoginDate;
                }

                public String getWechatOpenid() {
                    return wechatOpenid;
                }

                public void setWechatOpenid(String wechatOpenid) {
                    this.wechatOpenid = wechatOpenid;
                }

                public String getWechatData() {
                    return wechatData;
                }

                public void setWechatData(String wechatData) {
                    this.wechatData = wechatData;
                }

                public String getRole() {
                    return role;
                }

                public void setRole(String role) {
                    this.role = role;
                }

                public String getSex() {
                    return sex;
                }

                public void setSex(String sex) {
                    this.sex = sex;
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

                public String getCounty() {
                    return county;
                }

                public void setCounty(String county) {
                    this.county = county;
                }

                public String getIdentity() {
                    return identity;
                }

                public void setIdentity(String identity) {
                    this.identity = identity;
                }

                public String getBrokerId() {
                    return brokerId;
                }

                public void setBrokerId(String brokerId) {
                    this.brokerId = brokerId;
                }

                public String getStoreId() {
                    return storeId;
                }

                public void setStoreId(String storeId) {
                    this.storeId = storeId;
                }

                public String getCompanyManage() {
                    return companyManage;
                }

                public void setCompanyManage(String companyManage) {
                    this.companyManage = companyManage;
                }

                public String getStoreManage() {
                    return storeManage;
                }

                public void setStoreManage(String storeManage) {
                    this.storeManage = storeManage;
                }

                public int getClassics() {
                    return classics;
                }

                public void setClassics(int classics) {
                    this.classics = classics;
                }

                public String getTeam() {
                    return team;
                }

                public void setTeam(String team) {
                    this.team = team;
                }

                public boolean isAdmin() {
                    return admin;
                }

                public void setAdmin(boolean admin) {
                    this.admin = admin;
                }

                public String getRoleNames() {
                    return roleNames;
                }

                public void setRoleNames(String roleNames) {
                    this.roleNames = roleNames;
                }
            }

            public static class UpdateByBean {
                /**
                 * id : 3c37d25396a940f9b784b4c180f7db37
                 * remarks :
                 * createBy :
                 * createDate :
                 * updateBy :
                 * updateDate :
                 * company :
                 * office :
                 * post :
                 * loginName :
                 * no :
                 * name :
                 * email :
                 * phone :
                 * oldPhone :
                 * mobile :
                 * loginIp :
                 * loginDate :
                 * loginFlag : 1
                 * photo : /fangfang/static/common/images/flat-avatar.png
                 * qrCode :
                 * oldLoginName :
                 * newPassword :
                 * sign :
                 * oldLoginIp :
                 * oldLoginDate :
                 * wechatOpenid :
                 * wechatData :
                 * role :
                 * sex :
                 * province :
                 * city :
                 * county :
                 * identity :
                 * brokerId :
                 * storeId :
                 * companyManage :
                 * storeManage :
                 * classics : 0
                 * team :
                 * admin : false
                 * roleNames :
                 */

                private String id;
                private String remarks;
                private String createBy;
                private String createDate;
                private String updateBy;
                private String updateDate;
                private String company;
                private String office;
                private String post;
                private String loginName;
                private String no;
                private String name;
                private String email;
                private String phone;
                private String oldPhone;
                private String mobile;
                private String loginIp;
                private String loginDate;
                private String loginFlag;
                private String photo;
                private String qrCode;
                private String oldLoginName;
                private String newPassword;
                private String sign;
                private String oldLoginIp;
                private String oldLoginDate;
                private String wechatOpenid;
                private String wechatData;
                private String role;
                private String sex;
                private String province;
                private String city;
                private String county;
                private String identity;
                private String brokerId;
                private String storeId;
                private String companyManage;
                private String storeManage;
                private int classics;
                private String team;
                private boolean admin;
                private String roleNames;

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

                public String getCompany() {
                    return company;
                }

                public void setCompany(String company) {
                    this.company = company;
                }

                public String getOffice() {
                    return office;
                }

                public void setOffice(String office) {
                    this.office = office;
                }

                public String getPost() {
                    return post;
                }

                public void setPost(String post) {
                    this.post = post;
                }

                public String getLoginName() {
                    return loginName;
                }

                public void setLoginName(String loginName) {
                    this.loginName = loginName;
                }

                public String getNo() {
                    return no;
                }

                public void setNo(String no) {
                    this.no = no;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getEmail() {
                    return email;
                }

                public void setEmail(String email) {
                    this.email = email;
                }

                public String getPhone() {
                    return phone;
                }

                public void setPhone(String phone) {
                    this.phone = phone;
                }

                public String getOldPhone() {
                    return oldPhone;
                }

                public void setOldPhone(String oldPhone) {
                    this.oldPhone = oldPhone;
                }

                public String getMobile() {
                    return mobile;
                }

                public void setMobile(String mobile) {
                    this.mobile = mobile;
                }

                public String getLoginIp() {
                    return loginIp;
                }

                public void setLoginIp(String loginIp) {
                    this.loginIp = loginIp;
                }

                public String getLoginDate() {
                    return loginDate;
                }

                public void setLoginDate(String loginDate) {
                    this.loginDate = loginDate;
                }

                public String getLoginFlag() {
                    return loginFlag;
                }

                public void setLoginFlag(String loginFlag) {
                    this.loginFlag = loginFlag;
                }

                public String getPhoto() {
                    return photo;
                }

                public void setPhoto(String photo) {
                    this.photo = photo;
                }

                public String getQrCode() {
                    return qrCode;
                }

                public void setQrCode(String qrCode) {
                    this.qrCode = qrCode;
                }

                public String getOldLoginName() {
                    return oldLoginName;
                }

                public void setOldLoginName(String oldLoginName) {
                    this.oldLoginName = oldLoginName;
                }

                public String getNewPassword() {
                    return newPassword;
                }

                public void setNewPassword(String newPassword) {
                    this.newPassword = newPassword;
                }

                public String getSign() {
                    return sign;
                }

                public void setSign(String sign) {
                    this.sign = sign;
                }

                public String getOldLoginIp() {
                    return oldLoginIp;
                }

                public void setOldLoginIp(String oldLoginIp) {
                    this.oldLoginIp = oldLoginIp;
                }

                public String getOldLoginDate() {
                    return oldLoginDate;
                }

                public void setOldLoginDate(String oldLoginDate) {
                    this.oldLoginDate = oldLoginDate;
                }

                public String getWechatOpenid() {
                    return wechatOpenid;
                }

                public void setWechatOpenid(String wechatOpenid) {
                    this.wechatOpenid = wechatOpenid;
                }

                public String getWechatData() {
                    return wechatData;
                }

                public void setWechatData(String wechatData) {
                    this.wechatData = wechatData;
                }

                public String getRole() {
                    return role;
                }

                public void setRole(String role) {
                    this.role = role;
                }

                public String getSex() {
                    return sex;
                }

                public void setSex(String sex) {
                    this.sex = sex;
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

                public String getCounty() {
                    return county;
                }

                public void setCounty(String county) {
                    this.county = county;
                }

                public String getIdentity() {
                    return identity;
                }

                public void setIdentity(String identity) {
                    this.identity = identity;
                }

                public String getBrokerId() {
                    return brokerId;
                }

                public void setBrokerId(String brokerId) {
                    this.brokerId = brokerId;
                }

                public String getStoreId() {
                    return storeId;
                }

                public void setStoreId(String storeId) {
                    this.storeId = storeId;
                }

                public String getCompanyManage() {
                    return companyManage;
                }

                public void setCompanyManage(String companyManage) {
                    this.companyManage = companyManage;
                }

                public String getStoreManage() {
                    return storeManage;
                }

                public void setStoreManage(String storeManage) {
                    this.storeManage = storeManage;
                }

                public int getClassics() {
                    return classics;
                }

                public void setClassics(int classics) {
                    this.classics = classics;
                }

                public String getTeam() {
                    return team;
                }

                public void setTeam(String team) {
                    this.team = team;
                }

                public boolean isAdmin() {
                    return admin;
                }

                public void setAdmin(boolean admin) {
                    this.admin = admin;
                }

                public String getRoleNames() {
                    return roleNames;
                }

                public void setRoleNames(String roleNames) {
                    this.roleNames = roleNames;
                }
            }

            public static class DeveloperBean {
                /**
                 * id : 0c2413ac130b43ff98a51ab72b6a172c
                 * remarks :
                 * createBy :
                 * createDate :
                 * updateBy :
                 * updateDate :
                 * name : 丽江时光置业有限公司
                 * type :
                 * area :
                 * province :
                 * city :
                 * brand :
                 * cityCompany :
                 * typeName : 国外
                 */

                private String id;
                private String remarks;
                private String createBy;
                private String createDate;
                private String updateBy;
                private String updateDate;
                private String name;
                private String type;
                private String area;
                private String province;
                private String city;
                private String brand;
                private String cityCompany;
                private String typeName;

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

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getType() {
                    return type;
                }

                public void setType(String type) {
                    this.type = type;
                }

                public String getArea() {
                    return area;
                }

                public void setArea(String area) {
                    this.area = area;
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

                public String getBrand() {
                    return brand;
                }

                public void setBrand(String brand) {
                    this.brand = brand;
                }

                public String getCityCompany() {
                    return cityCompany;
                }

                public void setCityCompany(String cityCompany) {
                    this.cityCompany = cityCompany;
                }

                public String getTypeName() {
                    return typeName;
                }

                public void setTypeName(String typeName) {
                    this.typeName = typeName;
                }
            }

            public static class CityCompanyBean {
                /**
                 * id : c241db93cbd247f5a8aadf501806b56a
                 * remarks :
                 * createBy :
                 * createDate :
                 * updateBy :
                 * updateDate :
                 * parentIds :
                 * name : 长春房坐标（吉海优房）
                 * sort : 30
                 * hasChildren : false
                 * area :
                 * code :
                 * type : 2
                 * grade :
                 * address :
                 * zipCode :
                 * master :
                 * phone :
                 * fax :
                 * email :
                 * useable :
                 * primaryPerson :
                 * deputyPerson :
                 * childDeptList :
                 * province :
                 * city :
                 * county :
                 * areas :
                 * parentId : 0
                 */

                private String id;
                private String remarks;
                private String createBy;
                private String createDate;
                private String updateBy;
                private String updateDate;
                private String parentIds;
                private String name;
                private int sort;
                private boolean hasChildren;
                private String area;
                private String code;
                private String type;
                private String grade;
                private String address;
                private String zipCode;
                private String master;
                private String phone;
                private String fax;
                private String email;
                private String useable;
                private String primaryPerson;
                private String deputyPerson;
                private String childDeptList;
                private String province;
                private String city;
                private String county;
                private String areas;
                private String parentId;

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

                public String getParentIds() {
                    return parentIds;
                }

                public void setParentIds(String parentIds) {
                    this.parentIds = parentIds;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public int getSort() {
                    return sort;
                }

                public void setSort(int sort) {
                    this.sort = sort;
                }

                public boolean isHasChildren() {
                    return hasChildren;
                }

                public void setHasChildren(boolean hasChildren) {
                    this.hasChildren = hasChildren;
                }

                public String getArea() {
                    return area;
                }

                public void setArea(String area) {
                    this.area = area;
                }

                public String getCode() {
                    return code;
                }

                public void setCode(String code) {
                    this.code = code;
                }

                public String getType() {
                    return type;
                }

                public void setType(String type) {
                    this.type = type;
                }

                public String getGrade() {
                    return grade;
                }

                public void setGrade(String grade) {
                    this.grade = grade;
                }

                public String getAddress() {
                    return address;
                }

                public void setAddress(String address) {
                    this.address = address;
                }

                public String getZipCode() {
                    return zipCode;
                }

                public void setZipCode(String zipCode) {
                    this.zipCode = zipCode;
                }

                public String getMaster() {
                    return master;
                }

                public void setMaster(String master) {
                    this.master = master;
                }

                public String getPhone() {
                    return phone;
                }

                public void setPhone(String phone) {
                    this.phone = phone;
                }

                public String getFax() {
                    return fax;
                }

                public void setFax(String fax) {
                    this.fax = fax;
                }

                public String getEmail() {
                    return email;
                }

                public void setEmail(String email) {
                    this.email = email;
                }

                public String getUseable() {
                    return useable;
                }

                public void setUseable(String useable) {
                    this.useable = useable;
                }

                public String getPrimaryPerson() {
                    return primaryPerson;
                }

                public void setPrimaryPerson(String primaryPerson) {
                    this.primaryPerson = primaryPerson;
                }

                public String getDeputyPerson() {
                    return deputyPerson;
                }

                public void setDeputyPerson(String deputyPerson) {
                    this.deputyPerson = deputyPerson;
                }

                public String getChildDeptList() {
                    return childDeptList;
                }

                public void setChildDeptList(String childDeptList) {
                    this.childDeptList = childDeptList;
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

                public String getCounty() {
                    return county;
                }

                public void setCounty(String county) {
                    this.county = county;
                }

                public String getAreas() {
                    return areas;
                }

                public void setAreas(String areas) {
                    this.areas = areas;
                }

                public String getParentId() {
                    return parentId;
                }

                public void setParentId(String parentId) {
                    this.parentId = parentId;
                }
            }

            public static class AttacheBean {
                /**
                 * id : 3a0ee28065a84d548c96ee51849b5222
                 * remarks :
                 * createBy :
                 * createDate :
                 * updateBy :
                 * updateDate :
                 * company :
                 * office :
                 * post :
                 * loginName :
                 * no :
                 * name : 王珊珊
                 * email :
                 * phone : 13944098039
                 * oldPhone :
                 * mobile :
                 * loginIp :
                 * loginDate :
                 * loginFlag : 1
                 * photo : /fangfang/static/common/images/flat-avatar.png
                 * qrCode :
                 * oldLoginName :
                 * newPassword :
                 * sign :
                 * oldLoginIp :
                 * oldLoginDate :
                 * wechatOpenid :
                 * wechatData :
                 * role :
                 * sex :
                 * province :
                 * city :
                 * county :
                 * identity :
                 * brokerId :
                 * storeId :
                 * companyManage :
                 * storeManage :
                 * classics : 0
                 * team :
                 * admin : false
                 * roleNames :
                 */

                private String id;
                private String remarks;
                private String createBy;
                private String createDate;
                private String updateBy;
                private String updateDate;
                private String company;
                private String office;
                private String post;
                private String loginName;
                private String no;
                private String name;
                private String email;
                private String phone;
                private String oldPhone;
                private String mobile;
                private String loginIp;
                private String loginDate;
                private String loginFlag;
                private String photo;
                private String qrCode;
                private String oldLoginName;
                private String newPassword;
                private String sign;
                private String oldLoginIp;
                private String oldLoginDate;
                private String wechatOpenid;
                private String wechatData;
                private String role;
                private String sex;
                private String province;
                private String city;
                private String county;
                private String identity;
                private String brokerId;
                private String storeId;
                private String companyManage;
                private String storeManage;
                private int classics;
                private String team;
                private boolean admin;
                private String roleNames;

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

                public String getCompany() {
                    return company;
                }

                public void setCompany(String company) {
                    this.company = company;
                }

                public String getOffice() {
                    return office;
                }

                public void setOffice(String office) {
                    this.office = office;
                }

                public String getPost() {
                    return post;
                }

                public void setPost(String post) {
                    this.post = post;
                }

                public String getLoginName() {
                    return loginName;
                }

                public void setLoginName(String loginName) {
                    this.loginName = loginName;
                }

                public String getNo() {
                    return no;
                }

                public void setNo(String no) {
                    this.no = no;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getEmail() {
                    return email;
                }

                public void setEmail(String email) {
                    this.email = email;
                }

                public String getPhone() {
                    return phone;
                }

                public void setPhone(String phone) {
                    this.phone = phone;
                }

                public String getOldPhone() {
                    return oldPhone;
                }

                public void setOldPhone(String oldPhone) {
                    this.oldPhone = oldPhone;
                }

                public String getMobile() {
                    return mobile;
                }

                public void setMobile(String mobile) {
                    this.mobile = mobile;
                }

                public String getLoginIp() {
                    return loginIp;
                }

                public void setLoginIp(String loginIp) {
                    this.loginIp = loginIp;
                }

                public String getLoginDate() {
                    return loginDate;
                }

                public void setLoginDate(String loginDate) {
                    this.loginDate = loginDate;
                }

                public String getLoginFlag() {
                    return loginFlag;
                }

                public void setLoginFlag(String loginFlag) {
                    this.loginFlag = loginFlag;
                }

                public String getPhoto() {
                    return photo;
                }

                public void setPhoto(String photo) {
                    this.photo = photo;
                }

                public String getQrCode() {
                    return qrCode;
                }

                public void setQrCode(String qrCode) {
                    this.qrCode = qrCode;
                }

                public String getOldLoginName() {
                    return oldLoginName;
                }

                public void setOldLoginName(String oldLoginName) {
                    this.oldLoginName = oldLoginName;
                }

                public String getNewPassword() {
                    return newPassword;
                }

                public void setNewPassword(String newPassword) {
                    this.newPassword = newPassword;
                }

                public String getSign() {
                    return sign;
                }

                public void setSign(String sign) {
                    this.sign = sign;
                }

                public String getOldLoginIp() {
                    return oldLoginIp;
                }

                public void setOldLoginIp(String oldLoginIp) {
                    this.oldLoginIp = oldLoginIp;
                }

                public String getOldLoginDate() {
                    return oldLoginDate;
                }

                public void setOldLoginDate(String oldLoginDate) {
                    this.oldLoginDate = oldLoginDate;
                }

                public String getWechatOpenid() {
                    return wechatOpenid;
                }

                public void setWechatOpenid(String wechatOpenid) {
                    this.wechatOpenid = wechatOpenid;
                }

                public String getWechatData() {
                    return wechatData;
                }

                public void setWechatData(String wechatData) {
                    this.wechatData = wechatData;
                }

                public String getRole() {
                    return role;
                }

                public void setRole(String role) {
                    this.role = role;
                }

                public String getSex() {
                    return sex;
                }

                public void setSex(String sex) {
                    this.sex = sex;
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

                public String getCounty() {
                    return county;
                }

                public void setCounty(String county) {
                    this.county = county;
                }

                public String getIdentity() {
                    return identity;
                }

                public void setIdentity(String identity) {
                    this.identity = identity;
                }

                public String getBrokerId() {
                    return brokerId;
                }

                public void setBrokerId(String brokerId) {
                    this.brokerId = brokerId;
                }

                public String getStoreId() {
                    return storeId;
                }

                public void setStoreId(String storeId) {
                    this.storeId = storeId;
                }

                public String getCompanyManage() {
                    return companyManage;
                }

                public void setCompanyManage(String companyManage) {
                    this.companyManage = companyManage;
                }

                public String getStoreManage() {
                    return storeManage;
                }

                public void setStoreManage(String storeManage) {
                    this.storeManage = storeManage;
                }

                public int getClassics() {
                    return classics;
                }

                public void setClassics(int classics) {
                    this.classics = classics;
                }

                public String getTeam() {
                    return team;
                }

                public void setTeam(String team) {
                    this.team = team;
                }

                public boolean isAdmin() {
                    return admin;
                }

                public void setAdmin(boolean admin) {
                    this.admin = admin;
                }

                public String getRoleNames() {
                    return roleNames;
                }

                public void setRoleNames(String roleNames) {
                    this.roleNames = roleNames;
                }
            }
        }

        public static class GsonOptionBean {
            /**
             * yAxis : {"axisLabel":{"show":true,"textStyle":{"color":"#a8b6cc"}},"splitLine":{"lineStyle":{"color":"#eeeeee"},"show":true},"type":"value"}
             * xAxis : {"axisLabel":{"textStyle":{"color":"#a8b6cc"}},"data":["00","01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24"],"type":"category","boundaryGap":false}
             * series : [{"areaStyle":{"color":"#e6eefe"},"data":[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],"itemStyle":{"color":"#3b82d2"},"type":"line"}]
             */

            private YAxisBean yAxis;
            private XAxisBean xAxis;
            private List<SeriesBean> series;

            public YAxisBean getYAxis() {
                return yAxis;
            }

            public void setYAxis(YAxisBean yAxis) {
                this.yAxis = yAxis;
            }

            public XAxisBean getXAxis() {
                return xAxis;
            }

            public void setXAxis(XAxisBean xAxis) {
                this.xAxis = xAxis;
            }

            public List<SeriesBean> getSeries() {
                return series;
            }

            public void setSeries(List<SeriesBean> series) {
                this.series = series;
            }

            public static class YAxisBean {
                /**
                 * axisLabel : {"show":true,"textStyle":{"color":"#a8b6cc"}}
                 * splitLine : {"lineStyle":{"color":"#eeeeee"},"show":true}
                 * type : value
                 */

                private AxisLabelBean axisLabel;
                private SplitLineBean splitLine;
                private String type;

                public AxisLabelBean getAxisLabel() {
                    return axisLabel;
                }

                public void setAxisLabel(AxisLabelBean axisLabel) {
                    this.axisLabel = axisLabel;
                }

                public SplitLineBean getSplitLine() {
                    return splitLine;
                }

                public void setSplitLine(SplitLineBean splitLine) {
                    this.splitLine = splitLine;
                }

                public String getType() {
                    return type;
                }

                public void setType(String type) {
                    this.type = type;
                }

                public static class AxisLabelBean {
                    /**
                     * show : true
                     * textStyle : {"color":"#a8b6cc"}
                     */

                    private boolean show;
                    private TextStyleBean textStyle;

                    public boolean isShow() {
                        return show;
                    }

                    public void setShow(boolean show) {
                        this.show = show;
                    }

                    public TextStyleBean getTextStyle() {
                        return textStyle;
                    }

                    public void setTextStyle(TextStyleBean textStyle) {
                        this.textStyle = textStyle;
                    }

                    public static class TextStyleBean {
                        /**
                         * color : #a8b6cc
                         */

                        private String color;

                        public String getColor() {
                            return color;
                        }

                        public void setColor(String color) {
                            this.color = color;
                        }
                    }
                }

                public static class SplitLineBean {
                    /**
                     * lineStyle : {"color":"#eeeeee"}
                     * show : true
                     */

                    private LineStyleBean lineStyle;
                    private boolean show;

                    public LineStyleBean getLineStyle() {
                        return lineStyle;
                    }

                    public void setLineStyle(LineStyleBean lineStyle) {
                        this.lineStyle = lineStyle;
                    }

                    public boolean isShow() {
                        return show;
                    }

                    public void setShow(boolean show) {
                        this.show = show;
                    }

                    public static class LineStyleBean {
                        /**
                         * color : #eeeeee
                         */

                        private String color;

                        public String getColor() {
                            return color;
                        }

                        public void setColor(String color) {
                            this.color = color;
                        }
                    }
                }
            }

            public static class XAxisBean {
                /**
                 * axisLabel : {"textStyle":{"color":"#a8b6cc"}}
                 * data : ["00","01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24"]
                 * type : category
                 * boundaryGap : false
                 */

                private AxisLabelBeanX axisLabel;
                private String type;
                private boolean boundaryGap;
                private List<String> data;

                public AxisLabelBeanX getAxisLabel() {
                    return axisLabel;
                }

                public void setAxisLabel(AxisLabelBeanX axisLabel) {
                    this.axisLabel = axisLabel;
                }

                public String getType() {
                    return type;
                }

                public void setType(String type) {
                    this.type = type;
                }

                public boolean isBoundaryGap() {
                    return boundaryGap;
                }

                public void setBoundaryGap(boolean boundaryGap) {
                    this.boundaryGap = boundaryGap;
                }

                public List<String> getData() {
                    return data;
                }

                public void setData(List<String> data) {
                    this.data = data;
                }

                public static class AxisLabelBeanX {
                    /**
                     * textStyle : {"color":"#a8b6cc"}
                     */

                    private TextStyleBeanX textStyle;

                    public TextStyleBeanX getTextStyle() {
                        return textStyle;
                    }

                    public void setTextStyle(TextStyleBeanX textStyle) {
                        this.textStyle = textStyle;
                    }

                    public static class TextStyleBeanX {
                        /**
                         * color : #a8b6cc
                         */

                        private String color;

                        public String getColor() {
                            return color;
                        }

                        public void setColor(String color) {
                            this.color = color;
                        }
                    }
                }
            }

            public static class SeriesBean {
                /**
                 * areaStyle : {"color":"#e6eefe"}
                 * data : [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]
                 * itemStyle : {"color":"#3b82d2"}
                 * type : line
                 */

                private AreaStyleBean areaStyle;
                private ItemStyleBean itemStyle;
                private String type;
                private List<Integer> data;

                public AreaStyleBean getAreaStyle() {
                    return areaStyle;
                }

                public void setAreaStyle(AreaStyleBean areaStyle) {
                    this.areaStyle = areaStyle;
                }

                public ItemStyleBean getItemStyle() {
                    return itemStyle;
                }

                public void setItemStyle(ItemStyleBean itemStyle) {
                    this.itemStyle = itemStyle;
                }

                public String getType() {
                    return type;
                }

                public void setType(String type) {
                    this.type = type;
                }

                public List<Integer> getData() {
                    return data;
                }

                public void setData(List<Integer> data) {
                    this.data = data;
                }

                public static class AreaStyleBean {
                    /**
                     * color : #e6eefe
                     */

                    private String color;

                    public String getColor() {
                        return color;
                    }

                    public void setColor(String color) {
                        this.color = color;
                    }
                }

                public static class ItemStyleBean {
                    /**
                     * color : #3b82d2
                     */

                    private String color;

                    public String getColor() {
                        return color;
                    }

                    public void setColor(String color) {
                        this.color = color;
                    }
                }
            }
        }

        public static class ReceivableMoneyMapBean {
            /**
             * invoiceMoney : 0
             * surplusMoney : 0
             * backMoney : 0
             * receivableMoney : 0
             */

            private String invoiceMoney;
            private String surplusMoney;
            private String backMoney;
            private String receivableMoney;

            public String getInvoiceMoney() {
                return invoiceMoney;
            }

            public void setInvoiceMoney(String invoiceMoney) {
                this.invoiceMoney = invoiceMoney;
            }

            public String getSurplusMoney() {
                return surplusMoney;
            }

            public void setSurplusMoney(String surplusMoney) {
                this.surplusMoney = surplusMoney;
            }

            public String getBackMoney() {
                return backMoney;
            }

            public void setBackMoney(String backMoney) {
                this.backMoney = backMoney;
            }

            public String getReceivableMoney() {
                return receivableMoney;
            }

            public void setReceivableMoney(String receivableMoney) {
                this.receivableMoney = receivableMoney;
            }
        }

        public static class OperationBean {
            /**
             * accessingNumber : 0
             * tradeNumber : 0
             * reportNumber : 1
             * isIslandNumber : 0
             * earnestMoneyNumber : 0
             * reportOk : 0
             * landingNumber : 0
             * InvalidNum : 1
             */

            private int accessingNumber;
            private int tradeNumber;
            private int reportNumber;
            private int isIslandNumber;
            private int earnestMoneyNumber;
            private int reportOk;
            private int landingNumber;
            private int InvalidNum;

            public int getAccessingNumber() {
                return accessingNumber;
            }

            public void setAccessingNumber(int accessingNumber) {
                this.accessingNumber = accessingNumber;
            }

            public int getTradeNumber() {
                return tradeNumber;
            }

            public void setTradeNumber(int tradeNumber) {
                this.tradeNumber = tradeNumber;
            }

            public int getReportNumber() {
                return reportNumber;
            }

            public void setReportNumber(int reportNumber) {
                this.reportNumber = reportNumber;
            }

            public int getIsIslandNumber() {
                return isIslandNumber;
            }

            public void setIsIslandNumber(int isIslandNumber) {
                this.isIslandNumber = isIslandNumber;
            }

            public int getEarnestMoneyNumber() {
                return earnestMoneyNumber;
            }

            public void setEarnestMoneyNumber(int earnestMoneyNumber) {
                this.earnestMoneyNumber = earnestMoneyNumber;
            }

            public int getReportOk() {
                return reportOk;
            }

            public void setReportOk(int reportOk) {
                this.reportOk = reportOk;
            }

            public int getLandingNumber() {
                return landingNumber;
            }

            public void setLandingNumber(int landingNumber) {
                this.landingNumber = landingNumber;
            }

            public int getInvalidNum() {
                return InvalidNum;
            }

            public void setInvalidNum(int InvalidNum) {
                this.InvalidNum = InvalidNum;
            }
        }
    }
}
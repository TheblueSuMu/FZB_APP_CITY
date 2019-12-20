package com.xcy.fzbcity.all.modle;

import java.util.List;

public class MyClientFragmentBean {


    /**
     * code : 1
     * msg : 成功
     * data : {"menuData":[{"menuname":"失效","meunkey":"0"},{"menuname":"申请登岛","meunkey":"40"},{"menuname":"到访","meunkey":"20"},{"menuname":"认筹","meunkey":"50"},{"menuname":"成交","meunkey":"60"}],"processData":[{"id":"","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","preparationId":"","recordType":"","recordType2":"","recordName":"经纪人资料","jsonData":"","businessId":"","businessTable":"","auditId":"","jsonDatas":[{"value":"cs5","key":"经纪人"},{"valueType":"list","value":[{"phone":"18235195941","name":""}],"key":"联系电话"},{"value":"鸭店001","key":"门店名称"},{"value":"唐老鸭","key":"经纪公司"}],"icon":"/fangfang/static/common/images/broker2x.png","tradeStatus":"","identity":""},{"id":"57ec16320a9641f7bda13b02efb055b1","remarks":"","createBy":"","createDate":"2019-09-09 15:55:43","updateBy":"","updateDate":"","preparationId":"e9a061d9dda546938baad16a47534ea2","recordType":"10","recordType2":"","recordName":"无效原因","jsonData":"","businessId":"e9a061d9dda546938baad16a47534ea2","businessTable":"ff_server_newspaper_preparation","auditId":"","jsonDatas":[{"value":"该组客户已被其他经纪人报备，客户失效","key":"无效原因"},{"value":"2019-09-09 15:55:43","key":"无效时间"},{"value":"王珊珊","key":"操作专案"}],"icon":"/fangfang/static/common/images/failure2x.png","tradeStatus":"","identity":""},{"id":"8bee724b036540b594272ef753c8290c","remarks":"","createBy":"","createDate":"2019-09-09 15:55:43","updateBy":"","updateDate":"","preparationId":"e9a061d9dda546938baad16a47534ea2","recordType":"10","recordType2":"","recordName":"无效原因","jsonData":"","businessId":"e9a061d9dda546938baad16a47534ea2","businessTable":"ff_server_newspaper_preparation","auditId":"","jsonDatas":[{"value":"该组客户已被其他经纪人报备，客户失效","key":"无效原因"},{"value":"2019-09-09 15:55:43","key":"无效时间"},{"value":"王珊珊","key":"操作专案"}],"icon":"/fangfang/static/common/images/failure2x.png","tradeStatus":"","identity":""},{"id":"c717bb549bcd45fc8430f839b5800e42","remarks":"","createBy":"","createDate":"2019-09-09 15:55:43","updateBy":"","updateDate":"","preparationId":"e9a061d9dda546938baad16a47534ea2","recordType":"10","recordType2":"","recordName":"无效原因","jsonData":"","businessId":"e9a061d9dda546938baad16a47534ea2","businessTable":"ff_server_newspaper_preparation","auditId":"","jsonDatas":[{"value":"该组客户已被其他经纪人报备，客户失效","key":"无效原因"},{"value":"2019-09-09 15:55:43","key":"无效时间"},{"value":"王珊珊","key":"操作专案"}],"icon":"/fangfang/static/common/images/failure2x.png","tradeStatus":"","identity":""},{"id":"f1d8f72e3a3c49e19b6410f0cff75cca","remarks":"","createBy":"","createDate":"2019-09-09 15:55:43","updateBy":"","updateDate":"","preparationId":"e9a061d9dda546938baad16a47534ea2","recordType":"10","recordType2":"1","recordName":"报备审核","jsonData":"","businessId":"e9a061d9dda546938baad16a47534ea2","businessTable":"ff_server_newspaper_preparation","auditId":"","jsonDatas":[{"value":"2019-09-09 15:55:43","key":"审核时间"},{"value":"30天","key":"报备保护期"},{"value":"王珊珊","key":"审核专案"}],"icon":"/fangfang/static/common/images/audit2x.png","tradeStatus":"","identity":""},{"id":"82227dc56af9481b94685156e5209dc1","remarks":"","createBy":"","createDate":"2019-09-09 10:39:21","updateBy":"","updateDate":"","preparationId":"e9a061d9dda546938baad16a47534ea2","recordType":"10","recordType2":"0","recordName":"报备材料","jsonData":"","businessId":"e9a061d9dda546938baad16a47534ea2","businessTable":"ff_server_newspaper_preparation","auditId":"","jsonDatas":[{"value":"566665","key":"客户姓名"},{"value":"36777","key":"客户电话"},{"value":"泰禾厦门湾","key":"报备项目"},{"value":"","key":"意向面积"},{"value":"2019-09-09 10:39:21","key":"报备时间"}],"icon":"/fangfang/static/common/images/material2x.png","tradeStatus":"","identity":""}],"infoData":{"date":"报备时间2019-09-09 10:39:21","customerImg":"/fangfang/userfiles/43dea5335a1b4cb6bf15782a3be87c6a/attachment/null/2019/9/20190902174654.png","projectType":"3","userId":"3a0ee28065a84d548c96ee51849b5222","customerName":"566665","customerPhone":"367****","identity":"4","customerId":"d0b839eb848e4d86b172aa94d4cdc90d","preparationId":"e9a061d9dda546938baad16a47534ea2","relatedData":"保护期剩余30天","intentionArea":"","projectName":"泰禾厦门湾","projectId":"823cadd2058b43ba8dba1fc7d0a5de17","status":"10"}}
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
         * menuData : [{"menuname":"失效","meunkey":"0"},{"menuname":"申请登岛","meunkey":"40"},{"menuname":"到访","meunkey":"20"},{"menuname":"认筹","meunkey":"50"},{"menuname":"成交","meunkey":"60"}]
         * processData : [{"id":"","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","preparationId":"","recordType":"","recordType2":"","recordName":"经纪人资料","jsonData":"","businessId":"","businessTable":"","auditId":"","jsonDatas":[{"value":"cs5","key":"经纪人"},{"valueType":"list","value":[{"phone":"18235195941","name":""}],"key":"联系电话"},{"value":"鸭店001","key":"门店名称"},{"value":"唐老鸭","key":"经纪公司"}],"icon":"/fangfang/static/common/images/broker2x.png","tradeStatus":"","identity":""},{"id":"57ec16320a9641f7bda13b02efb055b1","remarks":"","createBy":"","createDate":"2019-09-09 15:55:43","updateBy":"","updateDate":"","preparationId":"e9a061d9dda546938baad16a47534ea2","recordType":"10","recordType2":"","recordName":"无效原因","jsonData":"","businessId":"e9a061d9dda546938baad16a47534ea2","businessTable":"ff_server_newspaper_preparation","auditId":"","jsonDatas":[{"value":"该组客户已被其他经纪人报备，客户失效","key":"无效原因"},{"value":"2019-09-09 15:55:43","key":"无效时间"},{"value":"王珊珊","key":"操作专案"}],"icon":"/fangfang/static/common/images/failure2x.png","tradeStatus":"","identity":""},{"id":"8bee724b036540b594272ef753c8290c","remarks":"","createBy":"","createDate":"2019-09-09 15:55:43","updateBy":"","updateDate":"","preparationId":"e9a061d9dda546938baad16a47534ea2","recordType":"10","recordType2":"","recordName":"无效原因","jsonData":"","businessId":"e9a061d9dda546938baad16a47534ea2","businessTable":"ff_server_newspaper_preparation","auditId":"","jsonDatas":[{"value":"该组客户已被其他经纪人报备，客户失效","key":"无效原因"},{"value":"2019-09-09 15:55:43","key":"无效时间"},{"value":"王珊珊","key":"操作专案"}],"icon":"/fangfang/static/common/images/failure2x.png","tradeStatus":"","identity":""},{"id":"c717bb549bcd45fc8430f839b5800e42","remarks":"","createBy":"","createDate":"2019-09-09 15:55:43","updateBy":"","updateDate":"","preparationId":"e9a061d9dda546938baad16a47534ea2","recordType":"10","recordType2":"","recordName":"无效原因","jsonData":"","businessId":"e9a061d9dda546938baad16a47534ea2","businessTable":"ff_server_newspaper_preparation","auditId":"","jsonDatas":[{"value":"该组客户已被其他经纪人报备，客户失效","key":"无效原因"},{"value":"2019-09-09 15:55:43","key":"无效时间"},{"value":"王珊珊","key":"操作专案"}],"icon":"/fangfang/static/common/images/failure2x.png","tradeStatus":"","identity":""},{"id":"f1d8f72e3a3c49e19b6410f0cff75cca","remarks":"","createBy":"","createDate":"2019-09-09 15:55:43","updateBy":"","updateDate":"","preparationId":"e9a061d9dda546938baad16a47534ea2","recordType":"10","recordType2":"1","recordName":"报备审核","jsonData":"","businessId":"e9a061d9dda546938baad16a47534ea2","businessTable":"ff_server_newspaper_preparation","auditId":"","jsonDatas":[{"value":"2019-09-09 15:55:43","key":"审核时间"},{"value":"30天","key":"报备保护期"},{"value":"王珊珊","key":"审核专案"}],"icon":"/fangfang/static/common/images/audit2x.png","tradeStatus":"","identity":""},{"id":"82227dc56af9481b94685156e5209dc1","remarks":"","createBy":"","createDate":"2019-09-09 10:39:21","updateBy":"","updateDate":"","preparationId":"e9a061d9dda546938baad16a47534ea2","recordType":"10","recordType2":"0","recordName":"报备材料","jsonData":"","businessId":"e9a061d9dda546938baad16a47534ea2","businessTable":"ff_server_newspaper_preparation","auditId":"","jsonDatas":[{"value":"566665","key":"客户姓名"},{"value":"36777","key":"客户电话"},{"value":"泰禾厦门湾","key":"报备项目"},{"value":"","key":"意向面积"},{"value":"2019-09-09 10:39:21","key":"报备时间"}],"icon":"/fangfang/static/common/images/material2x.png","tradeStatus":"","identity":""}]
         * infoData : {"date":"报备时间2019-09-09 10:39:21","customerImg":"/fangfang/userfiles/43dea5335a1b4cb6bf15782a3be87c6a/attachment/null/2019/9/20190902174654.png","projectType":"3","userId":"3a0ee28065a84d548c96ee51849b5222","customerName":"566665","customerPhone":"367****","identity":"4","customerId":"d0b839eb848e4d86b172aa94d4cdc90d","preparationId":"e9a061d9dda546938baad16a47534ea2","relatedData":"保护期剩余30天","intentionArea":"","projectName":"泰禾厦门湾","projectId":"823cadd2058b43ba8dba1fc7d0a5de17","status":"10"}
         */

        private InfoDataBean infoData;
        private List<MenuDataBean> menuData;
        private List<ProcessDataBean> processData;

        public InfoDataBean getInfoData() {
            return infoData;
        }

        public void setInfoData(InfoDataBean infoData) {
            this.infoData = infoData;
        }

        public List<MenuDataBean> getMenuData() {
            return menuData;
        }

        public void setMenuData(List<MenuDataBean> menuData) {
            this.menuData = menuData;
        }

        public List<ProcessDataBean> getProcessData() {
            return processData;
        }

        public void setProcessData(List<ProcessDataBean> processData) {
            this.processData = processData;
        }

        public static class InfoDataBean {
            /**
             * date : 报备时间2019-09-09 10:39:21
             * customerImg : /fangfang/userfiles/43dea5335a1b4cb6bf15782a3be87c6a/attachment/null/2019/9/20190902174654.png
             * projectType : 3
             * userId : 3a0ee28065a84d548c96ee51849b5222
             * customerName : 566665
             * customerPhone : 367****
             * identity : 4
             * customerId : d0b839eb848e4d86b172aa94d4cdc90d
             * preparationId : e9a061d9dda546938baad16a47534ea2
             * relatedData : 保护期剩余30天
             * intentionArea :
             * projectName : 泰禾厦门湾
             * projectId : 823cadd2058b43ba8dba1fc7d0a5de17
             * status : 10
             */

            private String date;
            private String customerImg;
            private String projectType;
            private String userId;
            private String customerName;
            private String customerPhone;
            private String identity;
            private String customerId;
            private String preparationId;
            private String relatedData;
            private String intentionArea;
            private String projectName;
            private String projectId;
            private String status;

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getCustomerImg() {
                return customerImg;
            }

            public void setCustomerImg(String customerImg) {
                this.customerImg = customerImg;
            }

            public String getProjectType() {
                return projectType;
            }

            public void setProjectType(String projectType) {
                this.projectType = projectType;
            }

            public String getUserId() {
                return userId;
            }

            public void setUserId(String userId) {
                this.userId = userId;
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

            public String getIdentity() {
                return identity;
            }

            public void setIdentity(String identity) {
                this.identity = identity;
            }

            public String getCustomerId() {
                return customerId;
            }

            public void setCustomerId(String customerId) {
                this.customerId = customerId;
            }

            public String getPreparationId() {
                return preparationId;
            }

            public void setPreparationId(String preparationId) {
                this.preparationId = preparationId;
            }

            public String getRelatedData() {
                return relatedData;
            }

            public void setRelatedData(String relatedData) {
                this.relatedData = relatedData;
            }

            public String getIntentionArea() {
                return intentionArea;
            }

            public void setIntentionArea(String intentionArea) {
                this.intentionArea = intentionArea;
            }

            public String getProjectName() {
                return projectName;
            }

            public void setProjectName(String projectName) {
                this.projectName = projectName;
            }

            public String getProjectId() {
                return projectId;
            }

            public void setProjectId(String projectId) {
                this.projectId = projectId;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }
        }

        public static class MenuDataBean {
            /**
             * menuname : 失效
             * meunkey : 0
             */

            private String menuname;
            private String meunkey;

            public String getMenuname() {
                return menuname;
            }

            public void setMenuname(String menuname) {
                this.menuname = menuname;
            }

            public String getMeunkey() {
                return meunkey;
            }

            public void setMeunkey(String meunkey) {
                this.meunkey = meunkey;
            }
        }

        public static class ProcessDataBean {
            /**
             * id :
             * remarks :
             * createBy :
             * createDate :
             * updateBy :
             * updateDate :
             * preparationId :
             * recordType :
             * recordType2 :
             * recordName : 经纪人资料
             * jsonData :
             * businessId :
             * businessTable :
             * auditId :
             * jsonDatas : [{"value":"cs5","key":"经纪人"},{"valueType":"list","value":[{"phone":"18235195941","name":""}],"key":"联系电话"},{"value":"鸭店001","key":"门店名称"},{"value":"唐老鸭","key":"经纪公司"}]
             * icon : /fangfang/static/common/images/broker2x.png
             * tradeStatus :
             * identity :
             */

            private String id;
            private String remarks;
            private String createBy;
            private String createDate;
            private String updateBy;
            private String updateDate;
            private String preparationId;
            private String recordType;
            private String recordType2;
            private String recordName;
            private String jsonData;
            private String businessId;
            private String businessTable;
            private String auditId;
            private String icon;
            private String tradeStatus;
            private String identity;
            private List<JsonDatasBean> jsonDatas;

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

            public String getPreparationId() {
                return preparationId;
            }

            public void setPreparationId(String preparationId) {
                this.preparationId = preparationId;
            }

            public String getRecordType() {
                return recordType;
            }

            public void setRecordType(String recordType) {
                this.recordType = recordType;
            }

            public String getRecordType2() {
                return recordType2;
            }

            public void setRecordType2(String recordType2) {
                this.recordType2 = recordType2;
            }

            public String getRecordName() {
                return recordName;
            }

            public void setRecordName(String recordName) {
                this.recordName = recordName;
            }

            public String getJsonData() {
                return jsonData;
            }

            public void setJsonData(String jsonData) {
                this.jsonData = jsonData;
            }

            public String getBusinessId() {
                return businessId;
            }

            public void setBusinessId(String businessId) {
                this.businessId = businessId;
            }

            public String getBusinessTable() {
                return businessTable;
            }

            public void setBusinessTable(String businessTable) {
                this.businessTable = businessTable;
            }

            public String getAuditId() {
                return auditId;
            }

            public void setAuditId(String auditId) {
                this.auditId = auditId;
            }

            public String getIcon() {
                return icon;
            }

            public void setIcon(String icon) {
                this.icon = icon;
            }

            public String getTradeStatus() {
                return tradeStatus;
            }

            public void setTradeStatus(String tradeStatus) {
                this.tradeStatus = tradeStatus;
            }

            public String getIdentity() {
                return identity;
            }

            public void setIdentity(String identity) {
                this.identity = identity;
            }

            public List<JsonDatasBean> getJsonDatas() {
                return jsonDatas;
            }

            public void setJsonDatas(List<JsonDatasBean> jsonDatas) {
                this.jsonDatas = jsonDatas;
            }

            public static class JsonDatasBean {
                /**
                 * value :
                 * key : 经纪人 cs5
                 * valueType : list
                 */

                private String value;
                private String key;
                private String valueType;

                public String getValue() {
                    return value;
                }

                public void setValue(String value) {
                    this.value = value;
                }

                public String getKey() {
                    return key;
                }

                public void setKey(String key) {
                    this.key = key;
                }

                public String getValueType() {
                    return valueType;
                }

                public void setValueType(String valueType) {
                    this.valueType = valueType;
                }
            }
        }
    }
}
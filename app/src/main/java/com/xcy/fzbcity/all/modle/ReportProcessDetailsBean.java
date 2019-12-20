package com.xcy.fzbcity.all.modle;

import java.util.List;

public class ReportProcessDetailsBean {

    /**
     * code : 1
     * msg : 成功
     * data : {"menuData":[{"menuname":"失效","meunkey":"0"},{"menuname":"申请登岛","meunkey":"40"},{"menuname":"到访","meunkey":"20"},{"menuname":"认筹","meunkey":"50"},{"menuname":"成交","meunkey":"60"}],"processData":[{"id":"","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","preparationId":"","recordType":"","recordType2":"","recordName":"经纪人资料","jsonData":"","businessId":"","businessTable":"","auditId":"","jsonDatas":[{"valueType":"","value":"圈层经纪人","key":"经纪人类型"},{"valueType":"","value":"顾问","key":"经纪人身份"},{"valueType":"","value":"gw001","key":"经纪人姓名"},{"valueType":"","value":"1542664525","key":"经纪人电话"},{"valueType":"","value":"mjx","key":"团队长"},{"valueType":"","value":"xs001","key":"销售"}],"icon":"/fangfang/static/common/images/broker2x.png","tradeStatus":"","identity":""},{"id":"3cba039ef98a4c8baba6dd2e88694b38","remarks":"","createBy":"","createDate":"2019-10-19 13:04:27","updateBy":"","updateDate":"","preparationId":"ef11a4c283f54d2da85fa2d3a8d0ee21","recordType":"10","recordType2":"1","recordName":"报备材料","jsonData":"","businessId":"ef11a4c283f54d2da85fa2d3a8d0ee21","businessTable":"ff_server_newspaper_preparation","auditId":"","jsonDatas":[{"valueType":"","value":"小米","key":"客户姓名"},{"valueType":"","value":"1352141414","key":"客户电话"},{"valueType":"","value":"146789199008087644","key":"身份证号"},{"valueType":"","value":"泰禾厦门湾","key":"报备项目"},{"valueType":"","value":"写字间","key":"意向类型"},{"valueType":"","value":"50-70m²","key":"意向面积"},{"valueType":"","value":"改善","key":"置业目的"},{"valueType":"","value":"2019-10-19 13:04:27","key":"报备时间"}],"icon":"/fangfang/static/common/images/material2x.png","tradeStatus":"","identity":""}],"attacheList":[{"phone":"15204414455","name":"李洋"},{"phone":"19856581879","name":"王珊珊"}],"customerList":["本人手机:1352141414"],"infoData":{"date":"报备时间2019-10-19 13:04:27","agentId":"59f45207e7a84c57bd31e9e5955506df","customerImg":"/fangfang/static/common/images/flat-avatar.png","projectType":"3","agentName":"团队长：mjx--销售：xs001--顾问：gw001","userId":"3a0ee28065a84d548c96ee51849b5222","customerName":"小米","customerPhone":"135****414","identity":"4","customerId":"888d6a7e0bfb4eedba154d44311bf2b1","preparationId":"ef11a4c283f54d2da85fa2d3a8d0ee21","relatedData":"","intentionArea":"","projectName":"泰禾厦门湾","projectId":"823cadd2058b43ba8dba1fc7d0a5de17","status":"10"}}
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
         * processData : [{"id":"","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","preparationId":"","recordType":"","recordType2":"","recordName":"经纪人资料","jsonData":"","businessId":"","businessTable":"","auditId":"","jsonDatas":[{"valueType":"","value":"圈层经纪人","key":"经纪人类型"},{"valueType":"","value":"顾问","key":"经纪人身份"},{"valueType":"","value":"gw001","key":"经纪人姓名"},{"valueType":"","value":"1542664525","key":"经纪人电话"},{"valueType":"","value":"mjx","key":"团队长"},{"valueType":"","value":"xs001","key":"销售"}],"icon":"/fangfang/static/common/images/broker2x.png","tradeStatus":"","identity":""},{"id":"3cba039ef98a4c8baba6dd2e88694b38","remarks":"","createBy":"","createDate":"2019-10-19 13:04:27","updateBy":"","updateDate":"","preparationId":"ef11a4c283f54d2da85fa2d3a8d0ee21","recordType":"10","recordType2":"1","recordName":"报备材料","jsonData":"","businessId":"ef11a4c283f54d2da85fa2d3a8d0ee21","businessTable":"ff_server_newspaper_preparation","auditId":"","jsonDatas":[{"valueType":"","value":"小米","key":"客户姓名"},{"valueType":"","value":"1352141414","key":"客户电话"},{"valueType":"","value":"146789199008087644","key":"身份证号"},{"valueType":"","value":"泰禾厦门湾","key":"报备项目"},{"valueType":"","value":"写字间","key":"意向类型"},{"valueType":"","value":"50-70m²","key":"意向面积"},{"valueType":"","value":"改善","key":"置业目的"},{"valueType":"","value":"2019-10-19 13:04:27","key":"报备时间"}],"icon":"/fangfang/static/common/images/material2x.png","tradeStatus":"","identity":""}]
         * attacheList : [{"phone":"15204414455","name":"李洋"},{"phone":"19856581879","name":"王珊珊"}]
         * customerList : ["本人手机:1352141414"]
         * infoData : {"date":"报备时间2019-10-19 13:04:27","agentId":"59f45207e7a84c57bd31e9e5955506df","customerImg":"/fangfang/static/common/images/flat-avatar.png","projectType":"3","agentName":"团队长：mjx--销售：xs001--顾问：gw001","userId":"3a0ee28065a84d548c96ee51849b5222","customerName":"小米","customerPhone":"135****414","identity":"4","customerId":"888d6a7e0bfb4eedba154d44311bf2b1","preparationId":"ef11a4c283f54d2da85fa2d3a8d0ee21","relatedData":"","intentionArea":"","projectName":"泰禾厦门湾","projectId":"823cadd2058b43ba8dba1fc7d0a5de17","status":"10"}
         */

        private InfoDataBean infoData;
        private List<MenuDataBean> menuData;
        private List<ProcessDataBean> processData;
        private List<AttacheListBean> attacheList;
        private List<String> customerList;

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

        public List<AttacheListBean> getAttacheList() {
            return attacheList;
        }

        public void setAttacheList(List<AttacheListBean> attacheList) {
            this.attacheList = attacheList;
        }

        public List<String> getCustomerList() {
            return customerList;
        }

        public void setCustomerList(List<String> customerList) {
            this.customerList = customerList;
        }

        public static class InfoDataBean {
            /**
             * date : 报备时间2019-10-19 13:04:27
             * agentId : 59f45207e7a84c57bd31e9e5955506df
             * customerImg : /fangfang/static/common/images/flat-avatar.png
             * projectType : 3
             * agentName : 团队长：mjx--销售：xs001--顾问：gw001
             * userId : 3a0ee28065a84d548c96ee51849b5222
             * customerName : 小米
             * customerPhone : 135****414
             * identity : 4
             * customerId : 888d6a7e0bfb4eedba154d44311bf2b1
             * preparationId : ef11a4c283f54d2da85fa2d3a8d0ee21
             * relatedData :
             * intentionArea :
             * projectName : 泰禾厦门湾
             * projectId : 823cadd2058b43ba8dba1fc7d0a5de17
             * status : 10
             */

            private String date;
            private String agentId;
            private String customerImg;
            private String projectType;
            private String agentName;
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

            public String getAgentId() {
                return agentId;
            }

            public void setAgentId(String agentId) {
                this.agentId = agentId;
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

            public String getAgentName() {
                return agentName;
            }

            public void setAgentName(String agentName) {
                this.agentName = agentName;
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
             * jsonDatas : [{"valueType":"","value":"圈层经纪人","key":"经纪人类型"},{"valueType":"","value":"顾问","key":"经纪人身份"},{"valueType":"","value":"gw001","key":"经纪人姓名"},{"valueType":"","value":"1542664525","key":"经纪人电话"},{"valueType":"","value":"mjx","key":"团队长"},{"valueType":"","value":"xs001","key":"销售"}]
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
                 * valueType :
                 * value : 圈层经纪人
                 * key : 经纪人类型
                 */

                private String valueType;
                private String value;
                private String key;

                public String getValueType() {
                    return valueType;
                }

                public void setValueType(String valueType) {
                    this.valueType = valueType;
                }

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
            }
        }

        public static class AttacheListBean {
            /**
             * phone : 15204414455
             * name : 李洋
             */

            private String phone;
            private String name;

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
    }
}

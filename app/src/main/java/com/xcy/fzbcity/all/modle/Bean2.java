package com.xcy.fzbcity.all.modle;

import java.util.List;

public class Bean2 {

    /**
     * code : 1
     * msg : 成功
     * data : {"menuData":"","processData":[{"id":"a1c97684a4de4b8b9e89351769cfe395","remarks":"","createBy":"","createDate":"2019-08-10 16:03:55","updateBy":"","updateDate":"","preparationId":"9fbc438e1b234e33b18deff1c307e5ea","recordType":"40","recordType2":"0","recordName":"申请登岛","jsonData":"","businessId":"4ceed6b693014aab929a70ff467982d8","businessTable":"ff_server_landing","auditId":"","jsonDatas":[{"value":"123456789123456789","key":"身份证号"},{"value":"70","key":"客户年龄"},{"valueType":"img","value":"/fangfang/userfiles/3a0ee28065a84d548c96ee51849b5222/attachment//ff/server/land/2019/8/1565424199500.jpeg","key":"身份证"},{"value":"长春","key":"所在城市"},{"value":"地产","key":"所在行业"},{"value":"10000000000","key":"家庭收入"},{"value":"1000000","key":"意向价格"},{"value":"10","key":"意向户型"},{"value":"长春-丽江","key":"登岛路线"},{"value":"2019-08-13 - 2019-08-15","key":"登岛时间"},{"value":"10000元","key":"团费金额"},{"value":"无","key":"有无同行人"},{"value":"10000元","key":"合计总费用"},{"valueType":"img","value":"/fangfang/userfiles/3a0ee28065a84d548c96ee51849b5222/attachment//ff/server/land/2019/8/1565424232905.jpeg","key":"团费凭证"},{"value":"已交","key":"是否交纳团费"},{"value":"2019-08-10 16:03:55","key":"操作时间"},{"value":"王珊珊","key":"操作专案"}],"icon":"/fangfang/static/common/images/applyIsland2x.png","tradeStatus":"","identity":""},{"id":"37888d6b4aaa4242acf37310a16d832f","remarks":"","createBy":"","createDate":"2019-08-10 15:53:58","updateBy":"","updateDate":"","preparationId":"9fbc438e1b234e33b18deff1c307e5ea","recordType":"20","recordType2":"","recordName":"客户到访","jsonData":"","businessId":"570526e4a7414dac81f7cd92cdb70122","businessTable":"ff_server_accessing","auditId":"","jsonDatas":[{"value":"2019-08-10 15:53:58","key":"到访时间"},{"valueType":"img","value":"/fangfang/userfiles/3a0ee28065a84d548c96ee51849b5222/attachment//ff/server/visit/2019/8/1565423634666.jpeg","key":"到访照片"},{"value":"7天","key":"到访保护期"},{"value":"王珊珊","key":"操作专案"}],"icon":"/fangfang/static/common/images/visit2x.png","tradeStatus":"","identity":""},{"id":"e04b781a3e6b4187be6b455d5056c05b","remarks":"","createBy":"","createDate":"2019-08-10 15:51:09","updateBy":"","updateDate":"","preparationId":"9fbc438e1b234e33b18deff1c307e5ea","recordType":"10","recordType2":"","recordName":"报备审核","jsonData":"","businessId":"9fbc438e1b234e33b18deff1c307e5ea","businessTable":"ff_server_newspaper_preparation","auditId":"","jsonDatas":[{"value":"2019-08-10 15:51:09","key":"审核时间"},{"value":"王珊珊","key":"审核专案"}],"icon":"/fangfang/static/common/images/audit2x.png","tradeStatus":"","identity":""},{"id":"3fa66aaf443c4dbf871da69bc4e7fc39","remarks":"","createBy":"","createDate":"2019-08-10 15:49:17","updateBy":"","updateDate":"","preparationId":"9fbc438e1b234e33b18deff1c307e5ea","recordType":"10","recordType2":"","recordName":"报备材料","jsonData":"","businessId":"9fbc438e1b234e33b18deff1c307e5ea","businessTable":"ff_server_newspaper_preparation","auditId":"","jsonDatas":[{"value":"妲己","key":"客户姓名"},{"value":"15647893625","key":"客户电话"},{"value":"西港云上","key":"报备项目"},{"value":"住宅","key":"意向类型"},{"value":"40㎡-100㎡","key":"意向面积"},{"value":"1000元/㎡-2000元/㎡","key":"意向价格"},{"value":"abc","key":"备注"},{"value":"2019-08-10 15:49:17","key":"报备时间"}],"icon":"/fangfang/static/common/images/material2x.png","tradeStatus":"","identity":""}],"attacheList":[{"phone":"15204414455","name":"李洋"}],"customerList":["本人手机:15647893625"],"infoData":{"date":"登岛时间2019-08-10 16:03:55","customerImg":"/fangfang/static/common/images/flat-avatar.png","projectType":"2","userId":"43dea5335a1b4cb6bf15782a3be87c6a","customerName":"妲己","customerPhone":"15647893625","identity":"1","customerId":"84f2d339d8584be4a8f989cb4cfe3cca","preparationId":"9fbc438e1b234e33b18deff1c307e5ea","relatedData":"申请登岛","intentionArea":"40-100㎡","projectName":"西港云上","projectId":"603e127528f747b68bee14d37b3f6239","status":"40"}}
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
         * menuData :
         * processData : [{"id":"a1c97684a4de4b8b9e89351769cfe395","remarks":"","createBy":"","createDate":"2019-08-10 16:03:55","updateBy":"","updateDate":"","preparationId":"9fbc438e1b234e33b18deff1c307e5ea","recordType":"40","recordType2":"0","recordName":"申请登岛","jsonData":"","businessId":"4ceed6b693014aab929a70ff467982d8","businessTable":"ff_server_landing","auditId":"","jsonDatas":[{"value":"123456789123456789","key":"身份证号"},{"value":"70","key":"客户年龄"},{"valueType":"img","value":"/fangfang/userfiles/3a0ee28065a84d548c96ee51849b5222/attachment//ff/server/land/2019/8/1565424199500.jpeg","key":"身份证"},{"value":"长春","key":"所在城市"},{"value":"地产","key":"所在行业"},{"value":"10000000000","key":"家庭收入"},{"value":"1000000","key":"意向价格"},{"value":"10","key":"意向户型"},{"value":"长春-丽江","key":"登岛路线"},{"value":"2019-08-13 - 2019-08-15","key":"登岛时间"},{"value":"10000元","key":"团费金额"},{"value":"无","key":"有无同行人"},{"value":"10000元","key":"合计总费用"},{"valueType":"img","value":"/fangfang/userfiles/3a0ee28065a84d548c96ee51849b5222/attachment//ff/server/land/2019/8/1565424232905.jpeg","key":"团费凭证"},{"value":"已交","key":"是否交纳团费"},{"value":"2019-08-10 16:03:55","key":"操作时间"},{"value":"王珊珊","key":"操作专案"}],"icon":"/fangfang/static/common/images/applyIsland2x.png","tradeStatus":"","identity":""},{"id":"37888d6b4aaa4242acf37310a16d832f","remarks":"","createBy":"","createDate":"2019-08-10 15:53:58","updateBy":"","updateDate":"","preparationId":"9fbc438e1b234e33b18deff1c307e5ea","recordType":"20","recordType2":"","recordName":"客户到访","jsonData":"","businessId":"570526e4a7414dac81f7cd92cdb70122","businessTable":"ff_server_accessing","auditId":"","jsonDatas":[{"value":"2019-08-10 15:53:58","key":"到访时间"},{"valueType":"img","value":"/fangfang/userfiles/3a0ee28065a84d548c96ee51849b5222/attachment//ff/server/visit/2019/8/1565423634666.jpeg","key":"到访照片"},{"value":"7天","key":"到访保护期"},{"value":"王珊珊","key":"操作专案"}],"icon":"/fangfang/static/common/images/visit2x.png","tradeStatus":"","identity":""},{"id":"e04b781a3e6b4187be6b455d5056c05b","remarks":"","createBy":"","createDate":"2019-08-10 15:51:09","updateBy":"","updateDate":"","preparationId":"9fbc438e1b234e33b18deff1c307e5ea","recordType":"10","recordType2":"","recordName":"报备审核","jsonData":"","businessId":"9fbc438e1b234e33b18deff1c307e5ea","businessTable":"ff_server_newspaper_preparation","auditId":"","jsonDatas":[{"value":"2019-08-10 15:51:09","key":"审核时间"},{"value":"王珊珊","key":"审核专案"}],"icon":"/fangfang/static/common/images/audit2x.png","tradeStatus":"","identity":""},{"id":"3fa66aaf443c4dbf871da69bc4e7fc39","remarks":"","createBy":"","createDate":"2019-08-10 15:49:17","updateBy":"","updateDate":"","preparationId":"9fbc438e1b234e33b18deff1c307e5ea","recordType":"10","recordType2":"","recordName":"报备材料","jsonData":"","businessId":"9fbc438e1b234e33b18deff1c307e5ea","businessTable":"ff_server_newspaper_preparation","auditId":"","jsonDatas":[{"value":"妲己","key":"客户姓名"},{"value":"15647893625","key":"客户电话"},{"value":"西港云上","key":"报备项目"},{"value":"住宅","key":"意向类型"},{"value":"40㎡-100㎡","key":"意向面积"},{"value":"1000元/㎡-2000元/㎡","key":"意向价格"},{"value":"abc","key":"备注"},{"value":"2019-08-10 15:49:17","key":"报备时间"}],"icon":"/fangfang/static/common/images/material2x.png","tradeStatus":"","identity":""}]
         * attacheList : [{"phone":"15204414455","name":"李洋"}]
         * customerList : ["本人手机:15647893625"]
         * infoData : {"date":"登岛时间2019-08-10 16:03:55","customerImg":"/fangfang/static/common/images/flat-avatar.png","projectType":"2","userId":"43dea5335a1b4cb6bf15782a3be87c6a","customerName":"妲己","customerPhone":"15647893625","identity":"1","customerId":"84f2d339d8584be4a8f989cb4cfe3cca","preparationId":"9fbc438e1b234e33b18deff1c307e5ea","relatedData":"申请登岛","intentionArea":"40-100㎡","projectName":"西港云上","projectId":"603e127528f747b68bee14d37b3f6239","status":"40"}
         */

        private String menuData;
        private InfoDataBean infoData;
        private List<ProcessDataBean> processData;
        private List<AttacheListBean> attacheList;
        private List<String> customerList;

        public String getMenuData() {
            return menuData;
        }

        public void setMenuData(String menuData) {
            this.menuData = menuData;
        }

        public InfoDataBean getInfoData() {
            return infoData;
        }

        public void setInfoData(InfoDataBean infoData) {
            this.infoData = infoData;
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
             * date : 登岛时间2019-08-10 16:03:55
             * customerImg : /fangfang/static/common/images/flat-avatar.png
             * projectType : 2
             * userId : 43dea5335a1b4cb6bf15782a3be87c6a
             * customerName : 妲己
             * customerPhone : 15647893625
             * identity : 1
             * customerId : 84f2d339d8584be4a8f989cb4cfe3cca
             * preparationId : 9fbc438e1b234e33b18deff1c307e5ea
             * relatedData : 申请登岛
             * intentionArea : 40-100㎡
             * projectName : 西港云上
             * projectId : 603e127528f747b68bee14d37b3f6239
             * status : 40
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

        public static class ProcessDataBean {
            /**
             * id : a1c97684a4de4b8b9e89351769cfe395
             * remarks :
             * createBy :
             * createDate : 2019-08-10 16:03:55
             * updateBy :
             * updateDate :
             * preparationId : 9fbc438e1b234e33b18deff1c307e5ea
             * recordType : 40
             * recordType2 : 0
             * recordName : 申请登岛
             * jsonData :
             * businessId : 4ceed6b693014aab929a70ff467982d8
             * businessTable : ff_server_landing
             * auditId :
             * jsonDatas : [{"value":"123456789123456789","key":"身份证号"},{"value":"70","key":"客户年龄"},{"valueType":"img","value":"/fangfang/userfiles/3a0ee28065a84d548c96ee51849b5222/attachment//ff/server/land/2019/8/1565424199500.jpeg","key":"身份证"},{"value":"长春","key":"所在城市"},{"value":"地产","key":"所在行业"},{"value":"10000000000","key":"家庭收入"},{"value":"1000000","key":"意向价格"},{"value":"10","key":"意向户型"},{"value":"长春-丽江","key":"登岛路线"},{"value":"2019-08-13 - 2019-08-15","key":"登岛时间"},{"value":"10000元","key":"团费金额"},{"value":"无","key":"有无同行人"},{"value":"10000元","key":"合计总费用"},{"valueType":"img","value":"/fangfang/userfiles/3a0ee28065a84d548c96ee51849b5222/attachment//ff/server/land/2019/8/1565424232905.jpeg","key":"团费凭证"},{"value":"已交","key":"是否交纳团费"},{"value":"2019-08-10 16:03:55","key":"操作时间"},{"value":"王珊珊","key":"操作专案"}]
             * icon : /fangfang/static/common/images/applyIsland2x.png
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
                 * value : 123456789123456789
                 * key : 身份证号
                 * valueType : img
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

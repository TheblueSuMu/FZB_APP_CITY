package com.xcy.fzbcity.all.modle;

import java.util.List;

public class FindTradeBean {

    /**
     * code : 1
     * msg : 成功
     * data : {"showData":[{"value":"GI奥","key":"客户名称"},{"value":"12345678910","key":"客户电话"},{"value":"天泰·大理十畝","key":"报备项目"},{"value":"2019-09-30 14:26:46","key":"报备时间"}],"procuctTypeData":[{"id":"747bc38da3bb40b28ec955cb9b1f91d0","remarks":"","createBy":"","createDate":"2019-03-10 15:36:16","updateBy":"","updateDate":"2019-03-10 15:36:16","label":"住宅","value":"1","sort":"1","dictType":{"id":"d506d59af7ab4063a802650f3aba366d","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","type":"","description":"","dictValueList":[]}},{"id":"f53d7f3765284dc893d4dab2b6ba29b8","remarks":"","createBy":"","createDate":"2019-03-10 15:44:01","updateBy":"","updateDate":"2019-03-10 15:44:01","label":"公寓","value":"2","sort":"2","dictType":{"id":"d506d59af7ab4063a802650f3aba366d","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","type":"","description":"","dictValueList":[]}},{"id":"a4686a460bbc492aadcf2984cfffa125","remarks":"","createBy":"","createDate":"2019-03-10 15:44:14","updateBy":"","updateDate":"2019-03-10 15:44:14","label":"写字间","value":"3","sort":"3","dictType":{"id":"d506d59af7ab4063a802650f3aba366d","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","type":"","description":"","dictValueList":[]}},{"id":"68e8f43b25b148bea5926d686f997a0e","remarks":"","createBy":"","createDate":"2019-03-10 15:49:12","updateBy":"","updateDate":"2019-03-10 15:49:12","label":"商铺","value":"4","sort":"4","dictType":{"id":"d506d59af7ab4063a802650f3aba366d","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","type":"","description":"","dictValueList":[]}},{"id":"0c1d91e7e9af42a29c7ea4101d27bb38","remarks":"","createBy":"","createDate":"2019-03-10 15:51:21","updateBy":"","updateDate":"2019-03-10 15:51:21","label":"别墅","value":"5","sort":"5","dictType":{"id":"d506d59af7ab4063a802650f3aba366d","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","type":"","description":"","dictValueList":[]}}],"isEarnestMoney":"0","comesBackData":{"customerId":"30b294bcffb244cb9c1f5cb933f1b29e","preparationId":"18dbdae66029485abcfd287d539a6cd7","economicId":"43dea5335a1b4cb6bf15782a3be87c6a","projectId":"5a9b45bf1d5d41afb93efc670e11115d"},"ffServerEarnestMoney":{"id":"","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","preparationId":"18dbdae66029485abcfd287d539a6cd7","customerId":"30b294bcffb244cb9c1f5cb933f1b29e","fullName":"","phone":"","idNumber":"","intentionPier":"","apartment":"","intentionalArea":"","recognizeTime":"","projectId":"5a9b45bf1d5d41afb93efc670e11115d","gender":"","relation":"","earnestStatus":"","earnestComment":"","applyBy":"","applyDate":"","auditBy":"","auditDate":"","earnestAuthComment":"","submitBy":"","submitDate":"","auditSubmitBy":"","auditSubmitDate":"","earnestCondition":""},"isTrade":"0"}
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
         * showData : [{"value":"GI奥","key":"客户名称"},{"value":"12345678910","key":"客户电话"},{"value":"天泰·大理十畝","key":"报备项目"},{"value":"2019-09-30 14:26:46","key":"报备时间"}]
         * procuctTypeData : [{"id":"747bc38da3bb40b28ec955cb9b1f91d0","remarks":"","createBy":"","createDate":"2019-03-10 15:36:16","updateBy":"","updateDate":"2019-03-10 15:36:16","label":"住宅","value":"1","sort":"1","dictType":{"id":"d506d59af7ab4063a802650f3aba366d","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","type":"","description":"","dictValueList":[]}},{"id":"f53d7f3765284dc893d4dab2b6ba29b8","remarks":"","createBy":"","createDate":"2019-03-10 15:44:01","updateBy":"","updateDate":"2019-03-10 15:44:01","label":"公寓","value":"2","sort":"2","dictType":{"id":"d506d59af7ab4063a802650f3aba366d","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","type":"","description":"","dictValueList":[]}},{"id":"a4686a460bbc492aadcf2984cfffa125","remarks":"","createBy":"","createDate":"2019-03-10 15:44:14","updateBy":"","updateDate":"2019-03-10 15:44:14","label":"写字间","value":"3","sort":"3","dictType":{"id":"d506d59af7ab4063a802650f3aba366d","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","type":"","description":"","dictValueList":[]}},{"id":"68e8f43b25b148bea5926d686f997a0e","remarks":"","createBy":"","createDate":"2019-03-10 15:49:12","updateBy":"","updateDate":"2019-03-10 15:49:12","label":"商铺","value":"4","sort":"4","dictType":{"id":"d506d59af7ab4063a802650f3aba366d","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","type":"","description":"","dictValueList":[]}},{"id":"0c1d91e7e9af42a29c7ea4101d27bb38","remarks":"","createBy":"","createDate":"2019-03-10 15:51:21","updateBy":"","updateDate":"2019-03-10 15:51:21","label":"别墅","value":"5","sort":"5","dictType":{"id":"d506d59af7ab4063a802650f3aba366d","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","type":"","description":"","dictValueList":[]}}]
         * isEarnestMoney : 0
         * comesBackData : {"customerId":"30b294bcffb244cb9c1f5cb933f1b29e","preparationId":"18dbdae66029485abcfd287d539a6cd7","economicId":"43dea5335a1b4cb6bf15782a3be87c6a","projectId":"5a9b45bf1d5d41afb93efc670e11115d"}
         * ffServerEarnestMoney : {"id":"","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","preparationId":"18dbdae66029485abcfd287d539a6cd7","customerId":"30b294bcffb244cb9c1f5cb933f1b29e","fullName":"","phone":"","idNumber":"","intentionPier":"","apartment":"","intentionalArea":"","recognizeTime":"","projectId":"5a9b45bf1d5d41afb93efc670e11115d","gender":"","relation":"","earnestStatus":"","earnestComment":"","applyBy":"","applyDate":"","auditBy":"","auditDate":"","earnestAuthComment":"","submitBy":"","submitDate":"","auditSubmitBy":"","auditSubmitDate":"","earnestCondition":""}
         * isTrade : 0
         */

        private String isEarnestMoney;
        private ComesBackDataBean comesBackData;
        private FfServerEarnestMoneyBean ffServerEarnestMoney;
        private String isTrade;
        private List<ShowDataBean> showData;
        private List<ProcuctTypeDataBean> procuctTypeData;

        public String getIsEarnestMoney() {
            return isEarnestMoney;
        }

        public void setIsEarnestMoney(String isEarnestMoney) {
            this.isEarnestMoney = isEarnestMoney;
        }

        public ComesBackDataBean getComesBackData() {
            return comesBackData;
        }

        public void setComesBackData(ComesBackDataBean comesBackData) {
            this.comesBackData = comesBackData;
        }

        public FfServerEarnestMoneyBean getFfServerEarnestMoney() {
            return ffServerEarnestMoney;
        }

        public void setFfServerEarnestMoney(FfServerEarnestMoneyBean ffServerEarnestMoney) {
            this.ffServerEarnestMoney = ffServerEarnestMoney;
        }

        public String getIsTrade() {
            return isTrade;
        }

        public void setIsTrade(String isTrade) {
            this.isTrade = isTrade;
        }

        public List<ShowDataBean> getShowData() {
            return showData;
        }

        public void setShowData(List<ShowDataBean> showData) {
            this.showData = showData;
        }

        public List<ProcuctTypeDataBean> getProcuctTypeData() {
            return procuctTypeData;
        }

        public void setProcuctTypeData(List<ProcuctTypeDataBean> procuctTypeData) {
            this.procuctTypeData = procuctTypeData;
        }

        public static class ComesBackDataBean {
            /**
             * customerId : 30b294bcffb244cb9c1f5cb933f1b29e
             * preparationId : 18dbdae66029485abcfd287d539a6cd7
             * economicId : 43dea5335a1b4cb6bf15782a3be87c6a
             * projectId : 5a9b45bf1d5d41afb93efc670e11115d
             */

            private String customerId;
            private String preparationId;
            private String economicId;
            private String projectId;

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

            public String getEconomicId() {
                return economicId;
            }

            public void setEconomicId(String economicId) {
                this.economicId = economicId;
            }

            public String getProjectId() {
                return projectId;
            }

            public void setProjectId(String projectId) {
                this.projectId = projectId;
            }
        }

        public static class FfServerEarnestMoneyBean {
            /**
             * id :
             * remarks :
             * createBy :
             * createDate :
             * updateBy :
             * updateDate :
             * preparationId : 18dbdae66029485abcfd287d539a6cd7
             * customerId : 30b294bcffb244cb9c1f5cb933f1b29e
             * fullName :
             * phone :
             * idNumber :
             * intentionPier :
             * apartment :
             * intentionalArea :
             * recognizeTime :
             * projectId : 5a9b45bf1d5d41afb93efc670e11115d
             * gender :
             * relation :
             * earnestStatus :
             * earnestComment :
             * applyBy :
             * applyDate :
             * auditBy :
             * auditDate :
             * earnestAuthComment :
             * submitBy :
             * submitDate :
             * auditSubmitBy :
             * auditSubmitDate :
             * earnestCondition :
             */

            private String id;
            private String remarks;
            private String createBy;
            private String createDate;
            private String updateBy;
            private String updateDate;
            private String preparationId;
            private String customerId;
            private String fullName;
            private String phone;
            private String idNumber;
            private String intentionPier;
            private String apartment;
            private String intentionalArea;
            private String recognizeTime;
            private String projectId;
            private String gender;
            private String relation;
            private String earnestStatus;
            private String earnestComment;
            private String applyBy;
            private String applyDate;
            private String auditBy;
            private String auditDate;
            private String earnestAuthComment;
            private String submitBy;
            private String submitDate;
            private String auditSubmitBy;
            private String auditSubmitDate;
            private String earnestCondition;

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

            public String getCustomerId() {
                return customerId;
            }

            public void setCustomerId(String customerId) {
                this.customerId = customerId;
            }

            public String getFullName() {
                return fullName;
            }

            public void setFullName(String fullName) {
                this.fullName = fullName;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public String getIdNumber() {
                return idNumber;
            }

            public void setIdNumber(String idNumber) {
                this.idNumber = idNumber;
            }

            public String getIntentionPier() {
                return intentionPier;
            }

            public void setIntentionPier(String intentionPier) {
                this.intentionPier = intentionPier;
            }

            public String getApartment() {
                return apartment;
            }

            public void setApartment(String apartment) {
                this.apartment = apartment;
            }

            public String getIntentionalArea() {
                return intentionalArea;
            }

            public void setIntentionalArea(String intentionalArea) {
                this.intentionalArea = intentionalArea;
            }

            public String getRecognizeTime() {
                return recognizeTime;
            }

            public void setRecognizeTime(String recognizeTime) {
                this.recognizeTime = recognizeTime;
            }

            public String getProjectId() {
                return projectId;
            }

            public void setProjectId(String projectId) {
                this.projectId = projectId;
            }

            public String getGender() {
                return gender;
            }

            public void setGender(String gender) {
                this.gender = gender;
            }

            public String getRelation() {
                return relation;
            }

            public void setRelation(String relation) {
                this.relation = relation;
            }

            public String getEarnestStatus() {
                return earnestStatus;
            }

            public void setEarnestStatus(String earnestStatus) {
                this.earnestStatus = earnestStatus;
            }

            public String getEarnestComment() {
                return earnestComment;
            }

            public void setEarnestComment(String earnestComment) {
                this.earnestComment = earnestComment;
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

            public String getEarnestAuthComment() {
                return earnestAuthComment;
            }

            public void setEarnestAuthComment(String earnestAuthComment) {
                this.earnestAuthComment = earnestAuthComment;
            }

            public String getSubmitBy() {
                return submitBy;
            }

            public void setSubmitBy(String submitBy) {
                this.submitBy = submitBy;
            }

            public String getSubmitDate() {
                return submitDate;
            }

            public void setSubmitDate(String submitDate) {
                this.submitDate = submitDate;
            }

            public String getAuditSubmitBy() {
                return auditSubmitBy;
            }

            public void setAuditSubmitBy(String auditSubmitBy) {
                this.auditSubmitBy = auditSubmitBy;
            }

            public String getAuditSubmitDate() {
                return auditSubmitDate;
            }

            public void setAuditSubmitDate(String auditSubmitDate) {
                this.auditSubmitDate = auditSubmitDate;
            }

            public String getEarnestCondition() {
                return earnestCondition;
            }

            public void setEarnestCondition(String earnestCondition) {
                this.earnestCondition = earnestCondition;
            }
        }

        public static class ShowDataBean {
            /**
             * value : GI奥
             * key : 客户名称
             */

            private String value;
            private String key;

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

        public static class ProcuctTypeDataBean {
            /**
             * id : 747bc38da3bb40b28ec955cb9b1f91d0
             * remarks :
             * createBy :
             * createDate : 2019-03-10 15:36:16
             * updateBy :
             * updateDate : 2019-03-10 15:36:16
             * label : 住宅
             * value : 1
             * sort : 1
             * dictType : {"id":"d506d59af7ab4063a802650f3aba366d","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","type":"","description":"","dictValueList":[]}
             */

            private String id;
            private String remarks;
            private String createBy;
            private String createDate;
            private String updateBy;
            private String updateDate;
            private String label;
            private String value;
            private String sort;
            private DictTypeBean dictType;

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

            public String getLabel() {
                return label;
            }

            public void setLabel(String label) {
                this.label = label;
            }

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }

            public String getSort() {
                return sort;
            }

            public void setSort(String sort) {
                this.sort = sort;
            }

            public DictTypeBean getDictType() {
                return dictType;
            }

            public void setDictType(DictTypeBean dictType) {
                this.dictType = dictType;
            }

            public static class DictTypeBean {
                /**
                 * id : d506d59af7ab4063a802650f3aba366d
                 * remarks :
                 * createBy :
                 * createDate :
                 * updateBy :
                 * updateDate :
                 * type :
                 * description :
                 * dictValueList : []
                 */

                private String id;
                private String remarks;
                private String createBy;
                private String createDate;
                private String updateBy;
                private String updateDate;
                private String type;
                private String description;
                private List<?> dictValueList;

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

                public String getType() {
                    return type;
                }

                public void setType(String type) {
                    this.type = type;
                }

                public String getDescription() {
                    return description;
                }

                public void setDescription(String description) {
                    this.description = description;
                }

                public List<?> getDictValueList() {
                    return dictValueList;
                }

                public void setDictValueList(List<?> dictValueList) {
                    this.dictValueList = dictValueList;
                }
            }
        }
    }
}
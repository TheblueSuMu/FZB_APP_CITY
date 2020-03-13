package com.xcy.fzbcity.all.modle;

import java.util.List;

public class RedbagReceiveRecordBean {

    /**
     * code : 1
     * msg : 成功
     * data : {"total":3,"rows":[{"id":"","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","filterField":"","userId":"","redbagType":"","timeType":"","redbagUseId":"223","customerImg":"/fangfang/static/common/images/flat-avatar.png","webshopId":"1ba5c65a224e42c38188f0cc82bf93e5","projectId":"","projectName":"","customerName":"王晶","denomination":"2.95","claimDate":"2020-03-10 02:46:18.0","assistList":[{"id":"4234","remarks":"","createBy":"","createDate":"2020-03-10 02:47:52","updateBy":"","updateDate":"","filterField":"","redbagUseId":"223","assistCustomerId":"55555","cityCompany":"","assistName":"张鹏","customerImg":"/fangfang/static/common/images/flat-avatar.png"}]}]}
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
         * rows : [{"id":"","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","filterField":"","userId":"","redbagType":"","timeType":"","redbagUseId":"223","customerImg":"/fangfang/static/common/images/flat-avatar.png","webshopId":"1ba5c65a224e42c38188f0cc82bf93e5","projectId":"","projectName":"","customerName":"王晶","denomination":"2.95","claimDate":"2020-03-10 02:46:18.0","assistList":[{"id":"4234","remarks":"","createBy":"","createDate":"2020-03-10 02:47:52","updateBy":"","updateDate":"","filterField":"","redbagUseId":"223","assistCustomerId":"55555","cityCompany":"","assistName":"张鹏","customerImg":"/fangfang/static/common/images/flat-avatar.png"}]}]
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
             * filterField :
             * userId :
             * redbagType :
             * timeType :
             * redbagUseId : 223
             * customerImg : /fangfang/static/common/images/flat-avatar.png
             * webshopId : 1ba5c65a224e42c38188f0cc82bf93e5
             * projectId :
             * projectName :
             * customerName : 王晶
             * denomination : 2.95
             * claimDate : 2020-03-10 02:46:18.0
             * assistList : [{"id":"4234","remarks":"","createBy":"","createDate":"2020-03-10 02:47:52","updateBy":"","updateDate":"","filterField":"","redbagUseId":"223","assistCustomerId":"55555","cityCompany":"","assistName":"张鹏","customerImg":"/fangfang/static/common/images/flat-avatar.png"}]
             */

            private String id;
            private String remarks;
            private String createBy;
            private String createDate;
            private String updateBy;
            private String updateDate;
            private String filterField;
            private String userId;
            private String redbagType;
            private String timeType;
            private String redbagUseId;
            private String customerImg;
            private String webshopId;
            private String projectId;
            private String projectName;
            private String customerName;
            private String denomination;
            private String claimDate;
            private List<AssistListBean> assistList;

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

            public String getUserId() {
                return userId;
            }

            public void setUserId(String userId) {
                this.userId = userId;
            }

            public String getRedbagType() {
                return redbagType;
            }

            public void setRedbagType(String redbagType) {
                this.redbagType = redbagType;
            }

            public String getTimeType() {
                return timeType;
            }

            public void setTimeType(String timeType) {
                this.timeType = timeType;
            }

            public String getRedbagUseId() {
                return redbagUseId;
            }

            public void setRedbagUseId(String redbagUseId) {
                this.redbagUseId = redbagUseId;
            }

            public String getCustomerImg() {
                return customerImg;
            }

            public void setCustomerImg(String customerImg) {
                this.customerImg = customerImg;
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

            public String getProjectName() {
                return projectName;
            }

            public void setProjectName(String projectName) {
                this.projectName = projectName;
            }

            public String getCustomerName() {
                return customerName;
            }

            public void setCustomerName(String customerName) {
                this.customerName = customerName;
            }

            public String getDenomination() {
                return denomination;
            }

            public void setDenomination(String denomination) {
                this.denomination = denomination;
            }

            public String getClaimDate() {
                return claimDate;
            }

            public void setClaimDate(String claimDate) {
                this.claimDate = claimDate;
            }

            public List<AssistListBean> getAssistList() {
                return assistList;
            }

            public void setAssistList(List<AssistListBean> assistList) {
                this.assistList = assistList;
            }

            public static class AssistListBean {
                /**
                 * id : 4234
                 * remarks :
                 * createBy :
                 * createDate : 2020-03-10 02:47:52
                 * updateBy :
                 * updateDate :
                 * filterField :
                 * redbagUseId : 223
                 * assistCustomerId : 55555
                 * cityCompany :
                 * assistName : 张鹏
                 * customerImg : /fangfang/static/common/images/flat-avatar.png
                 */

                private String id;
                private String remarks;
                private String createBy;
                private String createDate;
                private String updateBy;
                private String updateDate;
                private String filterField;
                private String redbagUseId;
                private String assistCustomerId;
                private String cityCompany;
                private String assistName;
                private String customerImg;

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

                public String getRedbagUseId() {
                    return redbagUseId;
                }

                public void setRedbagUseId(String redbagUseId) {
                    this.redbagUseId = redbagUseId;
                }

                public String getAssistCustomerId() {
                    return assistCustomerId;
                }

                public void setAssistCustomerId(String assistCustomerId) {
                    this.assistCustomerId = assistCustomerId;
                }

                public String getCityCompany() {
                    return cityCompany;
                }

                public void setCityCompany(String cityCompany) {
                    this.cityCompany = cityCompany;
                }

                public String getAssistName() {
                    return assistName;
                }

                public void setAssistName(String assistName) {
                    this.assistName = assistName;
                }

                public String getCustomerImg() {
                    return customerImg;
                }

                public void setCustomerImg(String customerImg) {
                    this.customerImg = customerImg;
                }
            }
        }
    }
}

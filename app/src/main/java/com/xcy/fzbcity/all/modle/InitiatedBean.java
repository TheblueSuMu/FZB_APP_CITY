package com.xcy.fzbcity.all.modle;

import java.util.List;

public class InitiatedBean {

    /**
     * code : 1
     * msg : 成功
     * data : {"total":1,"rows":[{"date":"认筹时间2019-12-10 13:28:45","customerImg":"/fangfang/static/common/images/flat-avatar.png","isRead":"0","projectType":"1","customerName":"刘一一","customerPhone":"13558588888","customerId":"d04c16fd59924515a1500226933d0a5d","preparationId":"7a3218ee05fb4faea2171ef85dc30fac","relatedData":"退筹审核","intentionArea":"","projectName":"鼎龙湾国际海洋王国度假区","projectId":"7c809d817257415fbded8d52879b4288","status":"50"}]}
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
         * rows : [{"date":"认筹时间2019-12-10 13:28:45","customerImg":"/fangfang/static/common/images/flat-avatar.png","isRead":"0","projectType":"1","customerName":"刘一一","customerPhone":"13558588888","customerId":"d04c16fd59924515a1500226933d0a5d","preparationId":"7a3218ee05fb4faea2171ef85dc30fac","relatedData":"退筹审核","intentionArea":"","projectName":"鼎龙湾国际海洋王国度假区","projectId":"7c809d817257415fbded8d52879b4288","status":"50"}]
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
             * date : 认筹时间2019-12-10 13:28:45
             * customerImg : /fangfang/static/common/images/flat-avatar.png
             * isRead : 0
             * projectType : 1
             * customerName : 刘一一
             * customerPhone : 13558588888
             * customerId : d04c16fd59924515a1500226933d0a5d
             * preparationId : 7a3218ee05fb4faea2171ef85dc30fac
             * relatedData : 退筹审核
             * intentionArea :
             * projectName : 鼎龙湾国际海洋王国度假区
             * projectId : 7c809d817257415fbded8d52879b4288
             * status : 50
             */

            private String date;
            private String customerImg;
            private String isRead;
            private String projectType;
            private String customerName;
            private String customerPhone;
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

            public String getIsRead() {
                return isRead;
            }

            public void setIsRead(String isRead) {
                this.isRead = isRead;
            }

            public String getProjectType() {
                return projectType;
            }

            public void setProjectType(String projectType) {
                this.projectType = projectType;
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
    }
}
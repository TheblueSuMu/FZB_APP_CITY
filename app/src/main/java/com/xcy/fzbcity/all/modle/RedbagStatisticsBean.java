package com.xcy.fzbcity.all.modle;

import java.util.List;

public class RedbagStatisticsBean {

    /**
     * code : 1
     * msg : 成功
     * data : {"total":16,"rows":[{"id":"","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","filterField":"","projectId":"1798a06ce075466aabf5811847556f77","converted":"0","quantity":"1","convertedAmount":"0.00","quantityAmount":"2.95","startDate":"","endDate":"","state":"0"},{"id":"","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","filterField":"","projectId":"1798a06ce075466aabf5811847556f77","converted":"0","quantity":"1","convertedAmount":"0.00","quantityAmount":"2.95","startDate":"","endDate":"","state":"0"},{"id":"","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","filterField":"","projectId":"rewrewrewr111","converted":"0","quantity":"1","convertedAmount":"0.00","quantityAmount":"2.95","startDate":"","endDate":"","state":"0"}]}
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
         * total : 16
         * rows : [{"id":"","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","filterField":"","projectId":"1798a06ce075466aabf5811847556f77","converted":"0","quantity":"1","convertedAmount":"0.00","quantityAmount":"2.95","startDate":"","endDate":"","state":"0"},{"id":"","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","filterField":"","projectId":"1798a06ce075466aabf5811847556f77","converted":"0","quantity":"1","convertedAmount":"0.00","quantityAmount":"2.95","startDate":"","endDate":"","state":"0"},{"id":"","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","filterField":"","projectId":"rewrewrewr111","converted":"0","quantity":"1","convertedAmount":"0.00","quantityAmount":"2.95","startDate":"","endDate":"","state":"0"}]
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
             * projectId : 1798a06ce075466aabf5811847556f77
             * converted : 0
             * quantity : 1
             * convertedAmount : 0.00
             * quantityAmount : 2.95
             * startDate :
             * endDate :
             * state : 0
             */

            private String id;
            private String remarks;
            private String createBy;
            private String createDate;
            private String updateBy;
            private String updateDate;
            private String filterField;
            private String projectId;
            private String converted;
            private String quantity;
            private String convertedAmount;
            private String quantityAmount;
            private String startDate;
            private String endDate;
            private String state;

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

            public String getConverted() {
                return converted;
            }

            public void setConverted(String converted) {
                this.converted = converted;
            }

            public String getQuantity() {
                return quantity;
            }

            public void setQuantity(String quantity) {
                this.quantity = quantity;
            }

            public String getConvertedAmount() {
                return convertedAmount;
            }

            public void setConvertedAmount(String convertedAmount) {
                this.convertedAmount = convertedAmount;
            }

            public String getQuantityAmount() {
                return quantityAmount;
            }

            public void setQuantityAmount(String quantityAmount) {
                this.quantityAmount = quantityAmount;
            }

            public String getStartDate() {
                return startDate;
            }

            public void setStartDate(String startDate) {
                this.startDate = startDate;
            }

            public String getEndDate() {
                return endDate;
            }

            public void setEndDate(String endDate) {
                this.endDate = endDate;
            }

            public String getState() {
                return state;
            }

            public void setState(String state) {
                this.state = state;
            }
        }
    }
}

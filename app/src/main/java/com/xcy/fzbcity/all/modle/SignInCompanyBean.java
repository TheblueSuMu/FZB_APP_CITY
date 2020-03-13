package com.xcy.fzbcity.all.modle;

import java.util.List;

public class SignInCompanyBean {


    /**
     * code : 1
     * msg : 成功
     * data : {"total":0,"rows":[{"id":"09139b36ca5a484f80e5a51fae817771","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","filterField":"","name":"测试的","address":"白堤路222号招商银行(天津新技术产业园区支行)","parentId":""}]}
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
         * total : 0
         * rows : [{"id":"09139b36ca5a484f80e5a51fae817771","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","filterField":"","name":"测试的","address":"白堤路222号招商银行(天津新技术产业园区支行)","parentId":""}]
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
             * id : 09139b36ca5a484f80e5a51fae817771
             * remarks :
             * createBy :
             * createDate :
             * updateBy :
             * updateDate :
             * filterField :
             * name : 测试的
             * address : 白堤路222号招商银行(天津新技术产业园区支行)
             * parentId :
             */

            private String id;
            private String remarks;
            private String createBy;
            private String createDate;
            private String updateBy;
            private String updateDate;
            private String filterField;
            private String name;
            private String address;
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

            public String getFilterField() {
                return filterField;
            }

            public void setFilterField(String filterField) {
                this.filterField = filterField;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public String getParentId() {
                return parentId;
            }

            public void setParentId(String parentId) {
                this.parentId = parentId;
            }
        }
    }
}

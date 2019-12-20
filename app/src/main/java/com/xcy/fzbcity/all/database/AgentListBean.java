package com.xcy.fzbcity.all.database;

import java.util.List;

public class AgentListBean {

    /**
     * code : 1
     * msg : 成功
     * data : {"total":659,"rows":[{"id":"ca77d0b221844266a07dd60fb4ffd36f","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","name":"二","phone":"2","photo":"/fangfang/static/common/images/flat-avatar.png","identity":"61","loginDate":"","type":"2","searcName":"","loginFlag":"1","parentId":"","user":""},{"id":"5b116bf96f8a4e0e893b7911ea539ab4","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","name":"www","phone":"21432","photo":"/fangfang/static/common/images/flat-avatar.png","identity":"61","loginDate":"","type":"2","searcName":"","loginFlag":"1","parentId":"","user":""},{"id":"514a738f08234721b02cd4ac2fc808e2","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","name":"123","phone":"111111","photo":"/fangfang/static/common/images/flat-avatar.png","identity":"61","loginDate":"","type":"2","searcName":"","loginFlag":"1","parentId":"","user":""},{"id":"9d0cdbcb566e45c99e22ad3f8fae6289","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","name":"css","phone":"10086","photo":"/fangfang/static/common/images/flat-avatar.png","identity":"60","loginDate":"","type":"1","searcName":"","loginFlag":"1","parentId":"","user":""},{"id":"d33c9df6df5f4e0883b7edf4d7e04e70","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","name":"dg2","phone":"18235195943","photo":"/fangfang/static/common/images/flat-avatar.png","identity":"7","loginDate":"2019-09-12 14:36:58","type":"","searcName":"","loginFlag":"1","parentId":"","user":""},{"id":"79670902bd784edea5311a71ec68aaca","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","name":"十点37","phone":"18235193527","photo":"/fangfang/static/common/images/flat-avatar.png","identity":"1","loginDate":"","type":"","searcName":"","loginFlag":"1","parentId":"","user":""},{"id":"cc740550d1f54db2a225a8b4ad0865f2","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","name":"user499","phone":"","photo":"/fangfang/static/common/images/flat-avatar.png","identity":"3","loginDate":"2019-08-15 10:08:47","type":"","searcName":"","loginFlag":"1","parentId":"","user":""},{"id":"7d38b696cce34a9a990e1c9f8afc53ab","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","name":"user498","phone":"","photo":"/fangfang/static/common/images/flat-avatar.png","identity":"3","loginDate":"","type":"","searcName":"","loginFlag":"1","parentId":"","user":""},{"id":"9da76e69d375489a88b30ffed8a73777","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","name":"user497","phone":"","photo":"/fangfang/static/common/images/flat-avatar.png","identity":"3","loginDate":"","type":"","searcName":"","loginFlag":"1","parentId":"","user":""},{"id":"c56b0fd6afff48aa90efa79dd234687d","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","name":"user496","phone":"","photo":"/fangfang/static/common/images/flat-avatar.png","identity":"3","loginDate":"","type":"","searcName":"","loginFlag":"1","parentId":"","user":""}]}
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
         * total : 659
         * rows : [{"id":"ca77d0b221844266a07dd60fb4ffd36f","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","name":"二","phone":"2","photo":"/fangfang/static/common/images/flat-avatar.png","identity":"61","loginDate":"","type":"2","searcName":"","loginFlag":"1","parentId":"","user":""},{"id":"5b116bf96f8a4e0e893b7911ea539ab4","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","name":"www","phone":"21432","photo":"/fangfang/static/common/images/flat-avatar.png","identity":"61","loginDate":"","type":"2","searcName":"","loginFlag":"1","parentId":"","user":""},{"id":"514a738f08234721b02cd4ac2fc808e2","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","name":"123","phone":"111111","photo":"/fangfang/static/common/images/flat-avatar.png","identity":"61","loginDate":"","type":"2","searcName":"","loginFlag":"1","parentId":"","user":""},{"id":"9d0cdbcb566e45c99e22ad3f8fae6289","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","name":"css","phone":"10086","photo":"/fangfang/static/common/images/flat-avatar.png","identity":"60","loginDate":"","type":"1","searcName":"","loginFlag":"1","parentId":"","user":""},{"id":"d33c9df6df5f4e0883b7edf4d7e04e70","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","name":"dg2","phone":"18235195943","photo":"/fangfang/static/common/images/flat-avatar.png","identity":"7","loginDate":"2019-09-12 14:36:58","type":"","searcName":"","loginFlag":"1","parentId":"","user":""},{"id":"79670902bd784edea5311a71ec68aaca","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","name":"十点37","phone":"18235193527","photo":"/fangfang/static/common/images/flat-avatar.png","identity":"1","loginDate":"","type":"","searcName":"","loginFlag":"1","parentId":"","user":""},{"id":"cc740550d1f54db2a225a8b4ad0865f2","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","name":"user499","phone":"","photo":"/fangfang/static/common/images/flat-avatar.png","identity":"3","loginDate":"2019-08-15 10:08:47","type":"","searcName":"","loginFlag":"1","parentId":"","user":""},{"id":"7d38b696cce34a9a990e1c9f8afc53ab","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","name":"user498","phone":"","photo":"/fangfang/static/common/images/flat-avatar.png","identity":"3","loginDate":"","type":"","searcName":"","loginFlag":"1","parentId":"","user":""},{"id":"9da76e69d375489a88b30ffed8a73777","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","name":"user497","phone":"","photo":"/fangfang/static/common/images/flat-avatar.png","identity":"3","loginDate":"","type":"","searcName":"","loginFlag":"1","parentId":"","user":""},{"id":"c56b0fd6afff48aa90efa79dd234687d","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","name":"user496","phone":"","photo":"/fangfang/static/common/images/flat-avatar.png","identity":"3","loginDate":"","type":"","searcName":"","loginFlag":"1","parentId":"","user":""}]
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
             * id : ca77d0b221844266a07dd60fb4ffd36f
             * remarks :
             * createBy :
             * createDate :
             * updateBy :
             * updateDate :
             * name : 二
             * phone : 2
             * photo : /fangfang/static/common/images/flat-avatar.png
             * identity : 61
             * loginDate :
             * type : 2
             * searcName :
             * loginFlag : 1
             * parentId :
             * user :
             */

            private String id;
            private String remarks;
            private String createBy;
            private String createDate;
            private String updateBy;
            private String updateDate;
            private String name;
            private String phone;
            private String photo;
            private String identity;
            private String loginDate;
            private String type;
            private String searcName;
            private String loginFlag;
            private String parentId;
            private String user;

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

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public String getPhoto() {
                return photo;
            }

            public void setPhoto(String photo) {
                this.photo = photo;
            }

            public String getIdentity() {
                return identity;
            }

            public void setIdentity(String identity) {
                this.identity = identity;
            }

            public String getLoginDate() {
                return loginDate;
            }

            public void setLoginDate(String loginDate) {
                this.loginDate = loginDate;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getSearcName() {
                return searcName;
            }

            public void setSearcName(String searcName) {
                this.searcName = searcName;
            }

            public String getLoginFlag() {
                return loginFlag;
            }

            public void setLoginFlag(String loginFlag) {
                this.loginFlag = loginFlag;
            }

            public String getParentId() {
                return parentId;
            }

            public void setParentId(String parentId) {
                this.parentId = parentId;
            }

            public String getUser() {
                return user;
            }

            public void setUser(String user) {
                this.user = user;
            }
        }
    }
}
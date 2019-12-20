package com.xcy.fzbcity.all.database;

import java.util.List;

public class TeamMemberBean {


    /**
     * code : 1
     * msg : 成功
     * data : {"total":4,"rows":[{"id":"1821c475de6442dba0df19a9961eba1d","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","name":"陈思宇","phone":"13910983649","photo":"/fangfang/static/common/images/flat-avatar.png","identity":"60","loginDate":"","loginFlag":"1","type":"1","searcName":"","parentId":"","parentIds":"","user":"","leaderName":"","saleName":"","saleNum":"0","counselorNum":"0","ratioName":"","levelName":"旅长"},{"id":"a2afae9c0c2b47f6aeb82bdfed1d1eca","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","name":"马佳","phone":"15004079651","photo":"/fangfang/static/common/images/flat-avatar.png","identity":"60","loginDate":"2019-10-30 11:16:50","loginFlag":"1","type":"1","searcName":"","parentId":"","parentIds":"","user":"","leaderName":"","saleName":"","saleNum":"0","counselorNum":"0","ratioName":"","levelName":"师长"},{"id":"c07585a03f0a437e8188418c3a087f47","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","name":"王楠","phone":"15633336699","photo":"/fangfang/static/common/images/flat-avatar.png","identity":"60","loginDate":"2019-10-30 09:45:42","loginFlag":"1","type":"1","searcName":"","parentId":"","parentIds":"","user":"","leaderName":"","saleName":"","saleNum":"2","counselorNum":"2","ratioName":"","levelName":"营长"},{"id":"f8f5c64032784e5d914e83ef345750f5","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","name":"刘喆","phone":"15623235588","photo":"/fangfang/static/common/images/flat-avatar.png","identity":"60","loginDate":"2019-10-30 09:20:30","loginFlag":"1","type":"1","searcName":"","parentId":"","parentIds":"","user":"","leaderName":"","saleName":"","saleNum":"0","counselorNum":"0","ratioName":"","levelName":"排长"}]}
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
         * total : 4
         * rows : [{"id":"1821c475de6442dba0df19a9961eba1d","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","name":"陈思宇","phone":"13910983649","photo":"/fangfang/static/common/images/flat-avatar.png","identity":"60","loginDate":"","loginFlag":"1","type":"1","searcName":"","parentId":"","parentIds":"","user":"","leaderName":"","saleName":"","saleNum":"0","counselorNum":"0","ratioName":"","levelName":"旅长"},{"id":"a2afae9c0c2b47f6aeb82bdfed1d1eca","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","name":"马佳","phone":"15004079651","photo":"/fangfang/static/common/images/flat-avatar.png","identity":"60","loginDate":"2019-10-30 11:16:50","loginFlag":"1","type":"1","searcName":"","parentId":"","parentIds":"","user":"","leaderName":"","saleName":"","saleNum":"0","counselorNum":"0","ratioName":"","levelName":"师长"},{"id":"c07585a03f0a437e8188418c3a087f47","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","name":"王楠","phone":"15633336699","photo":"/fangfang/static/common/images/flat-avatar.png","identity":"60","loginDate":"2019-10-30 09:45:42","loginFlag":"1","type":"1","searcName":"","parentId":"","parentIds":"","user":"","leaderName":"","saleName":"","saleNum":"2","counselorNum":"2","ratioName":"","levelName":"营长"},{"id":"f8f5c64032784e5d914e83ef345750f5","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","name":"刘喆","phone":"15623235588","photo":"/fangfang/static/common/images/flat-avatar.png","identity":"60","loginDate":"2019-10-30 09:20:30","loginFlag":"1","type":"1","searcName":"","parentId":"","parentIds":"","user":"","leaderName":"","saleName":"","saleNum":"0","counselorNum":"0","ratioName":"","levelName":"排长"}]
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
             * id : 1821c475de6442dba0df19a9961eba1d
             * remarks :
             * createBy :
             * createDate :
             * updateBy :
             * updateDate :
             * name : 陈思宇
             * phone : 13910983649
             * photo : /fangfang/static/common/images/flat-avatar.png
             * identity : 60
             * loginDate :
             * loginFlag : 1
             * type : 1
             * searcName :
             * parentId :
             * parentIds :
             * user :
             * leaderName :
             * saleName :
             * saleNum : 0
             * counselorNum : 0
             * ratioName :
             * levelName : 旅长
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
            private String loginFlag;
            private String type;
            private String searcName;
            private String parentId;
            private String parentIds;
            private String user;
            private String leaderName;
            private String saleName;
            private String saleNum;
            private String counselorNum;
            private String ratioName;
            private String levelName;

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

            public String getLoginFlag() {
                return loginFlag;
            }

            public void setLoginFlag(String loginFlag) {
                this.loginFlag = loginFlag;
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

            public String getParentId() {
                return parentId;
            }

            public void setParentId(String parentId) {
                this.parentId = parentId;
            }

            public String getParentIds() {
                return parentIds;
            }

            public void setParentIds(String parentIds) {
                this.parentIds = parentIds;
            }

            public String getUser() {
                return user;
            }

            public void setUser(String user) {
                this.user = user;
            }

            public String getLeaderName() {
                return leaderName;
            }

            public void setLeaderName(String leaderName) {
                this.leaderName = leaderName;
            }

            public String getSaleName() {
                return saleName;
            }

            public void setSaleName(String saleName) {
                this.saleName = saleName;
            }

            public String getSaleNum() {
                return saleNum;
            }

            public void setSaleNum(String saleNum) {
                this.saleNum = saleNum;
            }

            public String getCounselorNum() {
                return counselorNum;
            }

            public void setCounselorNum(String counselorNum) {
                this.counselorNum = counselorNum;
            }

            public String getRatioName() {
                return ratioName;
            }

            public void setRatioName(String ratioName) {
                this.ratioName = ratioName;
            }

            public String getLevelName() {
                return levelName;
            }

            public void setLevelName(String levelName) {
                this.levelName = levelName;
            }
        }
    }
}

package com.xcy.fzbcity.all.database;

public class AppPackageBean {

    /**
     * code : 1
     * msg : 成功
     * data : {"comment":"请联系管理员","isUpgrade":"0","appurl":""}
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
         * comment : 请联系管理员
         * isUpgrade : 0
         * appurl :
         */

        private String comment;
        private String isUpgrade;
        private String appurl;

        public String getComment() {
            return comment;
        }

        public void setComment(String comment) {
            this.comment = comment;
        }

        public String getIsUpgrade() {
            return isUpgrade;
        }

        public void setIsUpgrade(String isUpgrade) {
            this.isUpgrade = isUpgrade;
        }

        public String getAppurl() {
            return appurl;
        }

        public void setAppurl(String appurl) {
            this.appurl = appurl;
        }
    }
}

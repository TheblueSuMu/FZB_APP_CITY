package com.xcy.fzbcity.all.database;

public class AppPackageBean {

    /**
     * code : 1
     * msg : 成功
     * data : {"img":"","comment":"房坐标城市版本开通啦，请您更新体验！","isUpgrade":"1","appurl":"http://test.fangzuobiao.com:88/fangfang/static/down/fangzuobiao.apk"}
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
         * img :
         * comment : 房坐标城市版本开通啦，请您更新体验！
         * isUpgrade : 1
         * appurl : http://test.fangzuobiao.com:88/fangfang/static/down/fangzuobiao.apk
         */

        private String img;
        private String comment;
        private String isUpgrade;
        private String appurl;

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

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

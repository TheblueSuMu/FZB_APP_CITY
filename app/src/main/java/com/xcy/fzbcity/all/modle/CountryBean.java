package com.xcy.fzbcity.all.modle;

import java.util.List;

public class CountryBean {

    /**
     * code : 1
     * msg : 成功
     * data : [{"id":"c08469e8170d4520b4c889306dc2a6e8","remarks":"","createBy":"","createDate":"2019-07-19 08:55:44","updateBy":"","updateDate":"","nationName":"云南","nationImg":"/fangfang/userfiles/1/attachment//ff/server/ffServerNewsManage/2019/7/云南_03.png","delFlage":"","isOpen":"1","projectType":"3"},{"id":"19ee378663054f9e8353da4965832c89","remarks":"","createBy":"","createDate":"2019-07-16 15:15:30","updateBy":"","updateDate":"","nationName":"厦门","nationImg":"/fangfang/userfiles/1/attachment//ff/server/ffServerNewsManage/2019/7/厦门_03.png","delFlage":"","isOpen":"1","projectType":"3"},{"id":"d65fd5946399493f89e988715a7bda07","remarks":"山东烟台龙口","createBy":"","createDate":"2019-06-16 09:53:11","updateBy":"","updateDate":"","nationName":"烟台龙口","nationImg":"/fangfang/userfiles/1/attachment//ff/server/ffServerNewsManage/2019/6/longkou.png","delFlage":"","isOpen":"1","projectType":"3"},{"id":"563f449f010349ea8d493f57e842b570","remarks":"","createBy":"","createDate":"2019-04-21 11:23:21","updateBy":"","updateDate":"","nationName":"柬埔寨","nationImg":"/fangfang/userfiles/3c37d25396a940f9b784b4c180f7db37/attachment//ff/server/ffServerNewsManage/2019/4/button_4@2x.png","delFlage":"","isOpen":"1","projectType":"2"}]
     */

    private String code;
    private String msg;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : c08469e8170d4520b4c889306dc2a6e8
         * remarks :
         * createBy :
         * createDate : 2019-07-19 08:55:44
         * updateBy :
         * updateDate :
         * nationName : 云南
         * nationImg : /fangfang/userfiles/1/attachment//ff/server/ffServerNewsManage/2019/7/云南_03.png
         * delFlage :
         * isOpen : 1
         * projectType : 3
         */

        private String id;
        private String remarks;
        private String createBy;
        private String createDate;
        private String updateBy;
        private String updateDate;
        private String nationName;
        private String nationImg;
        private String delFlage;
        private String isOpen;
        private String projectType;

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

        public String getNationName() {
            return nationName;
        }

        public void setNationName(String nationName) {
            this.nationName = nationName;
        }

        public String getNationImg() {
            return nationImg;
        }

        public void setNationImg(String nationImg) {
            this.nationImg = nationImg;
        }

        public String getDelFlage() {
            return delFlage;
        }

        public void setDelFlage(String delFlage) {
            this.delFlage = delFlage;
        }

        public String getIsOpen() {
            return isOpen;
        }

        public void setIsOpen(String isOpen) {
            this.isOpen = isOpen;
        }

        public String getProjectType() {
            return projectType;
        }

        public void setProjectType(String projectType) {
            this.projectType = projectType;
        }
    }
}
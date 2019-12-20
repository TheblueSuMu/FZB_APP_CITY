package com.xcy.fzbcity.all.modle;

import java.util.List;

public class CityBean {


    /**
     * code : 1
     * msg : 成功
     * data : [{"id":"0ebce5bc3cc24ee493570e5512e8505d","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","province":"","city":"松原市","county":"","areas":""},{"id":"7aed337438af4620984daa64ca1bf130","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","province":"","city":"吉林市","county":"","areas":""},{"id":"b499b9b93a1345cdbba48b8493da0850","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","province":"","city":"天津市","county":"","areas":""},{"id":"c241db93cbd247f5a8aadf501806b56a","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","province":"","city":"长春市","county":"","areas":""}]
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
         * id : 0ebce5bc3cc24ee493570e5512e8505d
         * remarks :
         * createBy :
         * createDate :
         * updateBy :
         * updateDate :
         * province :
         * city : 松原市
         * county :
         * areas :
         */

        private String id;
        private String remarks;
        private String createBy;
        private String createDate;
        private String updateBy;
        private String updateDate;
        private String province;
        private String city;
        private String county;
        private String areas;

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

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getCounty() {
            return county;
        }

        public void setCounty(String county) {
            this.county = county;
        }

        public String getAreas() {
            return areas;
        }

        public void setAreas(String areas) {
            this.areas = areas;
        }
    }
}
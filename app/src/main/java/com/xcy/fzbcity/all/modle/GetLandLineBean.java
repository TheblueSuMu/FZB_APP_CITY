package com.xcy.fzbcity.all.modle;

import java.util.List;

public class GetLandLineBean {

    /**
     * code : 1
     * msg : 成功
     * data : [{"id":"052e77a9a4f94a209ad0cdbbfd6ad373","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","routeName":"人间\u2014\u2014天堂","company":"","projectId":"","endCloseTimeStr":"","startTime":""}]
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
         * id : 052e77a9a4f94a209ad0cdbbfd6ad373
         * remarks :
         * createBy :
         * createDate :
         * updateBy :
         * updateDate :
         * routeName : 人间——天堂
         * company :
         * projectId :
         * endCloseTimeStr :
         * startTime :
         */

        private String id;
        private String remarks;
        private String createBy;
        private String createDate;
        private String updateBy;
        private String updateDate;
        private String routeName;
        private String company;
        private String projectId;
        private String endCloseTimeStr;
        private String startTime;

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

        public String getRouteName() {
            return routeName;
        }

        public void setRouteName(String routeName) {
            this.routeName = routeName;
        }

        public String getCompany() {
            return company;
        }

        public void setCompany(String company) {
            this.company = company;
        }

        public String getProjectId() {
            return projectId;
        }

        public void setProjectId(String projectId) {
            this.projectId = projectId;
        }

        public String getEndCloseTimeStr() {
            return endCloseTimeStr;
        }

        public void setEndCloseTimeStr(String endCloseTimeStr) {
            this.endCloseTimeStr = endCloseTimeStr;
        }

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }
    }
}
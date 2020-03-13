package com.xcy.fzbcity.all.modle;

import java.util.List;

public class PreferentialActListBean {

    /**
     * code : 1
     * msg : 成功
     * data : {"rows":[{"id":"c3d8cceddcad4b4c909d71be92a23585","projectId":"85","projectName":"苏宁天润城","lotteryId":"1b029ad77e274ff182a7e298af237ac4","lotteryState":"2","type":"1","denomination":2.95,"startDate":"2020-03-13 09:24:38","endDate":"2020-03-15 09:00:00"}]}
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
        private List<RowsBean> rows;

        public List<RowsBean> getRows() {
            return rows;
        }

        public void setRows(List<RowsBean> rows) {
            this.rows = rows;
        }

        public static class RowsBean {
            /**
             * id : c3d8cceddcad4b4c909d71be92a23585
             * projectId : 85
             * projectName : 苏宁天润城
             * lotteryId : 1b029ad77e274ff182a7e298af237ac4
             * lotteryState : 2
             * type : 1
             * denomination : 2.95
             * startDate : 2020-03-13 09:24:38
             * endDate : 2020-03-15 09:00:00
             */

            private String id;
            private String projectId;
            private String projectName;
            private String lotteryId;
            private String lotteryState;
            private String type;
            private double denomination;
            private String startDate;
            private String endDate;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getProjectId() {
                return projectId;
            }

            public void setProjectId(String projectId) {
                this.projectId = projectId;
            }

            public String getProjectName() {
                return projectName;
            }

            public void setProjectName(String projectName) {
                this.projectName = projectName;
            }

            public String getLotteryId() {
                return lotteryId;
            }

            public void setLotteryId(String lotteryId) {
                this.lotteryId = lotteryId;
            }

            public String getLotteryState() {
                return lotteryState;
            }

            public void setLotteryState(String lotteryState) {
                this.lotteryState = lotteryState;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public double getDenomination() {
                return denomination;
            }

            public void setDenomination(double denomination) {
                this.denomination = denomination;
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
        }
    }
}

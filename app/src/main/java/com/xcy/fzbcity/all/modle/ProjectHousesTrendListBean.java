package com.xcy.fzbcity.all.modle;

import java.util.List;

public class ProjectHousesTrendListBean {

    /**
     * code : 1
     * msg : 成功
     * data : {"houseTrendResult":{"projectId":"5a9b45bf1d5d41afb93efc670e11115d","projectName":"天泰·大理十畝","houseTrendVoList":[{"id":"2afa9823e19640eb93e25767f7871e45","procuctType":"3","monthList":["201911"],"monthPriceList":[8000]},{"id":"9abbefbdbb9c485f91e5dba11d5d35e4","procuctType":"1","monthList":["201911","201910"],"monthPriceList":[11500,15000]}],"houseTrendRatioVoList":[{"id":"9abbefbdbb9c485f91e5dba11d5d35e4","procuctType":"1","upLow":"low","averagePrice":10000,"compareLastMonth":"23.08"}]}}
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
         * houseTrendResult : {"projectId":"5a9b45bf1d5d41afb93efc670e11115d","projectName":"天泰·大理十畝","houseTrendVoList":[{"id":"2afa9823e19640eb93e25767f7871e45","procuctType":"3","monthList":["201911"],"monthPriceList":[8000]},{"id":"9abbefbdbb9c485f91e5dba11d5d35e4","procuctType":"1","monthList":["201911","201910"],"monthPriceList":[11500,15000]}],"houseTrendRatioVoList":[{"id":"9abbefbdbb9c485f91e5dba11d5d35e4","procuctType":"1","upLow":"low","averagePrice":10000,"compareLastMonth":"23.08"}]}
         */

        private HouseTrendResultBean houseTrendResult;

        public HouseTrendResultBean getHouseTrendResult() {
            return houseTrendResult;
        }

        public void setHouseTrendResult(HouseTrendResultBean houseTrendResult) {
            this.houseTrendResult = houseTrendResult;
        }

        public static class HouseTrendResultBean {
            /**
             * projectId : 5a9b45bf1d5d41afb93efc670e11115d
             * projectName : 天泰·大理十畝
             * houseTrendVoList : [{"id":"2afa9823e19640eb93e25767f7871e45","procuctType":"3","monthList":["201911"],"monthPriceList":[8000]},{"id":"9abbefbdbb9c485f91e5dba11d5d35e4","procuctType":"1","monthList":["201911","201910"],"monthPriceList":[11500,15000]}]
             * houseTrendRatioVoList : [{"id":"9abbefbdbb9c485f91e5dba11d5d35e4","procuctType":"1","upLow":"low","averagePrice":10000,"compareLastMonth":"23.08"}]
             */

            private String projectId;
            private String projectName;
            private List<HouseTrendVoListBean> houseTrendVoList;
            private List<HouseTrendRatioVoListBean> houseTrendRatioVoList;

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

            public List<HouseTrendVoListBean> getHouseTrendVoList() {
                return houseTrendVoList;
            }

            public void setHouseTrendVoList(List<HouseTrendVoListBean> houseTrendVoList) {
                this.houseTrendVoList = houseTrendVoList;
            }

            public List<HouseTrendRatioVoListBean> getHouseTrendRatioVoList() {
                return houseTrendRatioVoList;
            }

            public void setHouseTrendRatioVoList(List<HouseTrendRatioVoListBean> houseTrendRatioVoList) {
                this.houseTrendRatioVoList = houseTrendRatioVoList;
            }

            public static class HouseTrendVoListBean {
                /**
                 * id : 2afa9823e19640eb93e25767f7871e45
                 * procuctType : 3
                 * monthList : ["201911"]
                 * monthPriceList : [8000]
                 */

                private String id;
                private String procuctType;
                private List<String> monthList;
                private List<Integer> monthPriceList;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getProcuctType() {
                    return procuctType;
                }

                public void setProcuctType(String procuctType) {
                    this.procuctType = procuctType;
                }

                public List<String> getMonthList() {
                    return monthList;
                }

                public void setMonthList(List<String> monthList) {
                    this.monthList = monthList;
                }

                public List<Integer> getMonthPriceList() {
                    return monthPriceList;
                }

                public void setMonthPriceList(List<Integer> monthPriceList) {
                    this.monthPriceList = monthPriceList;
                }
            }

            public static class HouseTrendRatioVoListBean {
                /**
                 * id : 9abbefbdbb9c485f91e5dba11d5d35e4
                 * procuctType : 1
                 * upLow : low
                 * averagePrice : 10000
                 * compareLastMonth : 23.08
                 */

                private String id;
                private String procuctType;
                private String upLow;
                private int averagePrice;
                private String compareLastMonth;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getProcuctType() {
                    return procuctType;
                }

                public void setProcuctType(String procuctType) {
                    this.procuctType = procuctType;
                }

                public String getUpLow() {
                    return upLow;
                }

                public void setUpLow(String upLow) {
                    this.upLow = upLow;
                }

                public int getAveragePrice() {
                    return averagePrice;
                }

                public void setAveragePrice(int averagePrice) {
                    this.averagePrice = averagePrice;
                }

                public String getCompareLastMonth() {
                    return compareLastMonth;
                }

                public void setCompareLastMonth(String compareLastMonth) {
                    this.compareLastMonth = compareLastMonth;
                }
            }
        }
    }
}

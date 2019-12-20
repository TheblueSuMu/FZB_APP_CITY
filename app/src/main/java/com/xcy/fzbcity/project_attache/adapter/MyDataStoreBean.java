package com.xcy.fzbcity.project_attache.adapter;

public class MyDataStoreBean {


    /**
     * code : 1
     * msg : 成功
     * data : {"totalAmountStr":"","recordNum":""}
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
         * totalAmountStr :
         * recordNum :
         */

        private String totalAmountStr;
        private String recordNum;

        public String getTotalAmountStr() {
            return totalAmountStr;
        }

        public void setTotalAmountStr(String totalAmountStr) {
            this.totalAmountStr = totalAmountStr;
        }

        public String getRecordNum() {
            return recordNum;
        }

        public void setRecordNum(String recordNum) {
            this.recordNum = recordNum;
        }
    }
}

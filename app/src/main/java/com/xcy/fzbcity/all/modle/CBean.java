package com.xcy.fzbcity.all.modle;

public class CBean {


    /**
     * code : 1
     * msg : 成功
     * data : {"maxStatus":"10","minStatus":"1","message":"审核成功"}
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
         * maxStatus : 10
         * minStatus : 1
         * message : 审核成功
         */

        private String maxStatus;
        private String minStatus;
        private String message;

        public String getMaxStatus() {
            return maxStatus;
        }

        public void setMaxStatus(String maxStatus) {
            this.maxStatus = maxStatus;
        }

        public String getMinStatus() {
            return minStatus;
        }

        public void setMinStatus(String minStatus) {
            this.minStatus = minStatus;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
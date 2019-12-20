package com.xcy.fzbcity.all.modle;

public class ChangePhoneBean {

    /**
     * code : 1
     * msg : 成功
     * data : {"business":"","message":"该客户已报备成功，请等待审核！","status":"1"}
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
         * business :
         * message : 该客户已报备成功，请等待审核！
         * status : 1
         */

        private String business;
        private String message;
        private String status;

        public String getBusiness() {
            return business;
        }

        public void setBusiness(String business) {
            this.business = business;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}
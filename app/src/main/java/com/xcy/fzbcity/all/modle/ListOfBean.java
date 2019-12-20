package com.xcy.fzbcity.all.modle;

public class ListOfBean {


    /**
     * code : 1
     * msg : 成功
     * data : {"flag":"1","message":"该客户已调单，您的操作无效！","status":"60"}
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
         * flag : 1
         * message : 该客户已调单，您的操作无效！
         * status : 60
         */

        private String flag;
        private String message;
        private String status;

        public String getFlag() {
            return flag;
        }

        public void setFlag(String flag) {
            this.flag = flag;
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
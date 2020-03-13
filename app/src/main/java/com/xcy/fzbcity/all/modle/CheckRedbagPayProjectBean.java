package com.xcy.fzbcity.all.modle;

public class CheckRedbagPayProjectBean {

    /**
     * code : 1
     * msg : 成功
     * data : {"messageCode":"0","message":"未检测到该网店上发放过红包!"}
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
         * messageCode : 0
         * message : 未检测到该网店上发放过红包!
         */

        private String messageCode;
        private String message;

        public String getMessageCode() {
            return messageCode;
        }

        public void setMessageCode(String messageCode) {
            this.messageCode = messageCode;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}

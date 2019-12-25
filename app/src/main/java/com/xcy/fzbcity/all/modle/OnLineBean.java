package com.xcy.fzbcity.all.modle;

public class OnLineBean {

    /**
     * code : 1
     * msg : 成功
     * data : {"online":"1"}
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
         * online : 1
         */

        private String online;

        public String getOnline() {
            return online;
        }

        public void setOnline(String online) {
            this.online = online;
        }
    }
}

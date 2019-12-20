package com.xcy.fzbcity.all.modle;

public class IdNumberBean {

    /**
     * code : 1
     * msg : 成功
     * data : {"isPapers":0}
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
         * isPapers : 0
         */

        private int isPapers;

        public int getIsPapers() {
            return isPapers;
        }

        public void setIsPapers(int isPapers) {
            this.isPapers = isPapers;
        }
    }
}

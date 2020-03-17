package com.xcy.fzbcity.all.modle;

public class AppletWechatImageBean {

    /**
     * code : 1
     * msg : 成功
     * data : {"qrFilePath":"http://localhost:8080/fangfang/userfiles/qrcode/1582860644210.jpg"}
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
         * qrFilePath : http://localhost:8080/fangfang/userfiles/qrcode/1582860644210.jpg
         */

        private String qrFilePath;

        public String getQrFilePath() {
            return qrFilePath;
        }

        public void setQrFilePath(String qrFilePath) {
            this.qrFilePath = qrFilePath;
        }
    }
}

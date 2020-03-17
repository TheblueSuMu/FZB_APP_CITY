package com.xcy.fzbcity.all.modle;

import com.google.gson.annotations.SerializedName;

public class RechargeRedbagBean {

    /**
     * code : 1
     * msg : 成功
     * data : {"nonce_str":"2505f625d5e7450fb68534825056ba86","package":"Sign=WXPay","sign":"60D2C692CAC505A6C8184B9C61833C67","err_code":"SUCCESS","err_code_des":"ok","return_msg":"OK","mch_id":"1574904901","prepay_id":"wx20200307150703437406","device_info":"sandbox","appid":"wxf9a42b48a61cfd62","trade_type":"APP","result_code":"SUCCESS","return_code":"SUCCESS","timestamp":"1583564860"}
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
         * nonce_str : 2505f625d5e7450fb68534825056ba86
         * package : Sign=WXPay
         * sign : 60D2C692CAC505A6C8184B9C61833C67
         * err_code : SUCCESS
         * err_code_des : ok
         * return_msg : OK
         * mch_id : 1574904901
         * prepay_id : wx20200307150703437406
         * device_info : sandbox
         * appid : wxf9a42b48a61cfd62
         * trade_type : APP
         * result_code : SUCCESS
         * return_code : SUCCESS
         * timestamp : 1583564860
         */

        private String nonce_str;
        @SerializedName("package")
        private String packageX;
        private String sign;
        private String err_code;
        private String err_code_des;
        private String return_msg;
        private String mch_id;
        private String prepay_id;
        private String device_info;
        private String appid;
        private String trade_type;
        private String result_code;
        private String return_code;
        private String timestamp;

        public String getNonce_str() {
            return nonce_str;
        }

        public void setNonce_str(String nonce_str) {
            this.nonce_str = nonce_str;
        }

        public String getPackageX() {
            return packageX;
        }

        public void setPackageX(String packageX) {
            this.packageX = packageX;
        }

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }

        public String getErr_code() {
            return err_code;
        }

        public void setErr_code(String err_code) {
            this.err_code = err_code;
        }

        public String getErr_code_des() {
            return err_code_des;
        }

        public void setErr_code_des(String err_code_des) {
            this.err_code_des = err_code_des;
        }

        public String getReturn_msg() {
            return return_msg;
        }

        public void setReturn_msg(String return_msg) {
            this.return_msg = return_msg;
        }

        public String getMch_id() {
            return mch_id;
        }

        public void setMch_id(String mch_id) {
            this.mch_id = mch_id;
        }

        public String getPrepay_id() {
            return prepay_id;
        }

        public void setPrepay_id(String prepay_id) {
            this.prepay_id = prepay_id;
        }

        public String getDevice_info() {
            return device_info;
        }

        public void setDevice_info(String device_info) {
            this.device_info = device_info;
        }

        public String getAppid() {
            return appid;
        }

        public void setAppid(String appid) {
            this.appid = appid;
        }

        public String getTrade_type() {
            return trade_type;
        }

        public void setTrade_type(String trade_type) {
            this.trade_type = trade_type;
        }

        public String getResult_code() {
            return result_code;
        }

        public void setResult_code(String result_code) {
            this.result_code = result_code;
        }

        public String getReturn_code() {
            return return_code;
        }

        public void setReturn_code(String return_code) {
            this.return_code = return_code;
        }

        public String getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(String timestamp) {
            this.timestamp = timestamp;
        }
    }
}

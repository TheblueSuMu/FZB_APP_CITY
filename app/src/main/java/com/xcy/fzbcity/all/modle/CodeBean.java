package com.xcy.fzbcity.all.modle;

import java.io.Serializable;

/**
 * 创建：Sun
 * 时间：2019/7/23
 */
public class CodeBean  {

    /**
     * code : 1
     * msg : 成功
     * data : {"phone":"18600503212","message":"短信验证码已发送，请填写验证码"}
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

    public static class DataBean implements Serializable {
        /**
         * phone : 18600503212
         * message : 短信验证码已发送，请填写验证码
         */

        private String phone;
        private String message;
        private String status;

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
package com.xcy.fzbcity.all.modle;

import java.io.Serializable;

/**
 * 创建：Sun
 * 时间：2019/7/24
 */
public class ResetPasswordBean {

    /**
     * code : 1
     * msg : 成功
     * data : {"message":"验证码验证通过","status":"1"}
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
         * message : 验证码验证通过
         * status : 1
         */

        private String message;
        private String status;

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

package com.xcy.fzbcity.all.database;

import java.util.List;

public class DailyTurnoverBean {

    /**
     * code : 1
     * msg : 成功
     * data : [{"num":1,"name":"圈层测试2","photo":"/fangfang/static/common/images/flat-avatar.png","id":"fa6b11bb9bcc4bbfb2f396a7b4396ac5"},{"num":1,"name":"圈层测试3","photo":"/fangfang/static/common/images/flat-avatar.png","id":"c8d66eab81504f9ca73256818dabfd44"}]
     */

    private String code;
    private String msg;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * num : 1
         * name : 圈层测试2
         * photo : /fangfang/static/common/images/flat-avatar.png
         * id : fa6b11bb9bcc4bbfb2f396a7b4396ac5
         */

        private int num;
        private String name;
        private String photo;
        private String id;

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }
}
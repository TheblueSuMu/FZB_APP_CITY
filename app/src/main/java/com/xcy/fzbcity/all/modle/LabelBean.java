package com.xcy.fzbcity.all.modle;

import java.util.List;

public class LabelBean {

    /**
     * code : 1
     * msg : 成功
     * data : [{"lable":"高端精品房","id":"c0d5d71991bf432aac4ea258a442eb1f"},{"lable":"独家","id":"f0462889fca6428c9fc444cd165b3101"},{"lable":"高佣","id":"c94ab520323e43c9a440de60d82b14e0"},{"lable":"前佣","id":"e3ffcf97314549fe9ab0634ced1dfe7f"},{"lable":"带看奖","id":"e5797a9fec6c48718ad8f6e18c0d4341"},{"lable":"成交奖","id":"f4221e4fbb454316a28d6f4e65c799a2"},{"lable":"跳点","id":"e3943314e90d45b8967384ed93af28a4"},{"lable":"包销","id":"eed62b47db0342959b7b1399799db796"}]
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
         * lable : 高端精品房
         * id : c0d5d71991bf432aac4ea258a442eb1f
         */

        private String lable;
        private String id;

        public String getLable() {
            return lable;
        }

        public void setLable(String lable) {
            this.lable = lable;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }
}

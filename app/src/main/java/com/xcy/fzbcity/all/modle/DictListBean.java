package com.xcy.fzbcity.all.modle;

import java.util.List;

public class DictListBean {

    /**
     * code : 1
     * msg : 成功
     * data : [{"name":"住宅","value":"1"},{"name":"公寓","value":"2"},{"name":"写字间","value":"3"},{"name":"商铺","value":"4"},{"name":"别墅","value":"5"}]
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
         * name : 住宅
         * value : 1
         */

        private String name;
        private String value;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
}
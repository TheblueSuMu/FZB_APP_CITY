package com.xcy.fzbcity.all.modle;

public class AddPhotoBean {


    /**
     * code : 1
     * msg : 成功
     * data : {"id":"/var/share/userfiles/null/attachment/null/2019/8/fzb1.png","url":"/fangfang/userfiles/null/attachment/null/2019/8/fzb1.png"}
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
         * id : /var/share/userfiles/null/attachment/null/2019/8/fzb1.png
         * url : /fangfang/userfiles/null/attachment/null/2019/8/fzb1.png
         */

        private String id;
        private String url;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
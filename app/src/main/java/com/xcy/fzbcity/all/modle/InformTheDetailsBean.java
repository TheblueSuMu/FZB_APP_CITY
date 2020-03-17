package com.xcy.fzbcity.all.modle;

import java.util.List;

public class InformTheDetailsBean {


    /**
     * code : 1
     * msg : 成功
     * data : {"list":[{"id":"1","type":"3","title":"云南","content":"大理","date":"1583392365000","subType":"","commonId":"","state":"","isEnable":""},{"id":"2","type":"1","title":"红色预警","content":"大雾","date":"1583392443000","subType":"","commonId":"","state":"","isEnable":""},{"id":"3","type":"2","title":"领劵","content":"100元","date":"1583306557000","subType":"","commonId":"","state":"","isEnable":""},{"id":"4","type":"2","title":"领奖","content":"200元","date":"1583392992000","subType":"","commonId":"","state":"","isEnable":""},{"id":"5","type":"3","title":"西藏","content":"拉萨","date":"1583393020000","subType":"","commonId":"","state":"","isEnable":""},{"id":"ewrewrwerwe","type":"2","title":"是发送到发","content":"而亲仁翁","date":"1583385086000","subType":"","commonId":"","state":"","isEnable":""},{"id":"rewrew","type":"1","title":"进球啦","content":"球球凄凄切切","date":"1583385033000","subType":"","commonId":"","state":"","isEnable":""},{"id":"sdfsvv","type":"3","title":"服务费二十多","content":"热未发生大V速度","date":"1583385131000","subType":"","commonId":"","state":"","isEnable":""}]}
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
        private List<ListBean> list;

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * id : 1
             * type : 3
             * title : 云南
             * content : 大理
             * date : 1583392365000
             * subType :
             * commonId :
             * state :
             * isEnable :
             */

            private String id;
            private String type;
            private String title;
            private String content;
            private String date;
            private String subType;
            private String commonId;
            private String state;
            private String isEnable;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getSubType() {
                return subType;
            }

            public void setSubType(String subType) {
                this.subType = subType;
            }

            public String getCommonId() {
                return commonId;
            }

            public void setCommonId(String commonId) {
                this.commonId = commonId;
            }

            public String getState() {
                return state;
            }

            public void setState(String state) {
                this.state = state;
            }

            public String getIsEnable() {
                return isEnable;
            }

            public void setIsEnable(String isEnable) {
                this.isEnable = isEnable;
            }
        }
    }
}

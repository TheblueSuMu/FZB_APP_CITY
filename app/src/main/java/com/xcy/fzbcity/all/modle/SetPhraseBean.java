package com.xcy.fzbcity.all.modle;

import java.io.Serializable;
import java.util.List;

public class SetPhraseBean {


    /**
     * code : 1
     * msg : 成功
     * data : {"commonLanguageList":[{"id":"243e0269ce0e41108c22c416beeaab3e","content":"亢亚栋测试","state":"1"},{"id":"5b28574d81664101b342971ea9c037bf","content":"亢亚栋测试2","state":"1"},{"id":"60e2a923ab5d4891a9a20838e8df40f0","content":"测试4","state":"1"}]}
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
        private List<CommonLanguageListBean> commonLanguageList;

        public List<CommonLanguageListBean> getCommonLanguageList() {
            return commonLanguageList;
        }

        public void setCommonLanguageList(List<CommonLanguageListBean> commonLanguageList) {
            this.commonLanguageList = commonLanguageList;
        }

        public static class CommonLanguageListBean implements Serializable{
            /**
             * id : 243e0269ce0e41108c22c416beeaab3e
             * content : 亢亚栋测试
             * state : 1
             */

            private String id;
            private String content;
            private String state;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getState() {
                return state;
            }

            public void setState(String state) {
                this.state = state;
            }
        }
    }
}

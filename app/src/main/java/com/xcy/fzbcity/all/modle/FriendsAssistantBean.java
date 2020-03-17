package com.xcy.fzbcity.all.modle;

import java.util.List;

public class FriendsAssistantBean {

    /**
     * code : 1
     * msg : 成功
     * data : [{"listDate":[{"id":"26127c9febcd42a78f5b5f8591348906","title":"测试2","content":"测试2","imgPath":"","height":"","className":"贩卖焦虑"}],"classId":"5e27fd36ec814b0bb68cacbc164d82af","className":"贩卖焦虑"},{"listDate":[{"id":"26127c9febcd42a78f5b5f8591348906","title":"励志","content":"励志","imgPath":"","height":"","className":"励志"}],"classId":"b27e336e6a22459ea9c10070e1790b38","className":"励志"}]
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
         * listDate : [{"id":"26127c9febcd42a78f5b5f8591348906","title":"测试2","content":"测试2","imgPath":"","height":"","className":"贩卖焦虑"}]
         * classId : 5e27fd36ec814b0bb68cacbc164d82af
         * className : 贩卖焦虑
         */

        private String classId;
        private String className;
        private List<ListDateBean> listDate;

        public String getClassId() {
            return classId;
        }

        public void setClassId(String classId) {
            this.classId = classId;
        }

        public String getClassName() {
            return className;
        }

        public void setClassName(String className) {
            this.className = className;
        }

        public List<ListDateBean> getListDate() {
            return listDate;
        }

        public void setListDate(List<ListDateBean> listDate) {
            this.listDate = listDate;
        }

        public static class ListDateBean {
            /**
             * id : 26127c9febcd42a78f5b5f8591348906
             * title : 测试2
             * content : 测试2
             * imgPath :
             * height :
             * className : 贩卖焦虑
             */

            private String id;
            private String title;
            private String content;
            private String imgPath;
            private String height;
            private String className;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
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

            public String getImgPath() {
                return imgPath;
            }

            public void setImgPath(String imgPath) {
                this.imgPath = imgPath;
            }

            public String getHeight() {
                return height;
            }

            public void setHeight(String height) {
                this.height = height;
            }

            public String getClassName() {
                return className;
            }

            public void setClassName(String className) {
                this.className = className;
            }
        }
    }
}

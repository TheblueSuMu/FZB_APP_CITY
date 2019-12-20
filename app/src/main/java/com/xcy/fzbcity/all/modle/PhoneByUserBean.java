package com.xcy.fzbcity.all.modle;

public class PhoneByUserBean {

    /**
     * code : 1
     * msg : 成功
     * data : {"msg":"","code":"1","data":{"userPhone":"15805563580","userName":"小心","userId":"7618a711cfd64a988f2e908eceb016f6"}}
     */

    private String code;
    private String msg;
    private DataBeanX data;

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

    public DataBeanX getData() {
        return data;
    }

    public void setData(DataBeanX data) {
        this.data = data;
    }

    public static class DataBeanX {
        /**
         * msg :
         * code : 1
         * data : {"userPhone":"15805563580","userName":"小心","userId":"7618a711cfd64a988f2e908eceb016f6"}
         */

        private String msg;
        private String code;
        private DataBean data;

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public DataBean getData() {
            return data;
        }

        public void setData(DataBean data) {
            this.data = data;
        }

        public static class DataBean {
            /**
             * userPhone : 15805563580
             * userName : 小心
             * userId : 7618a711cfd64a988f2e908eceb016f6
             */

            private String userPhone;
            private String userName;
            private String userId;

            public String getUserPhone() {
                return userPhone;
            }

            public void setUserPhone(String userPhone) {
                this.userPhone = userPhone;
            }

            public String getUserName() {
                return userName;
            }

            public void setUserName(String userName) {
                this.userName = userName;
            }

            public String getUserId() {
                return userId;
            }

            public void setUserId(String userId) {
                this.userId = userId;
            }
        }
    }
}

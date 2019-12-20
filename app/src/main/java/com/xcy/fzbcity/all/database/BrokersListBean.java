package com.xcy.fzbcity.all.database;

import java.util.List;

public class BrokersListBean {


    /**
     * code : 1
     * msg : 成功
     * data : {"total":12,"rows":[{"id":"","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","user":"","agentId":"1bc96d8b974841a7901a7e066015aa51","loginName":"zhazong","agentName":"査总","agentPhone":"15043439666","identity":"3","identityName":"","companyId":"8e381e93aad04945b96046ad6410230f","companyName":"苹果","storeId":"d90a9b327ce04e2689b09d39716015eb","storeName":"苹果2店","status":"1","reason":"","search":""},{"id":"","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","user":"","agentId":"56bde823caaf42a18137dba54f2713c8","loginName":"jizong","agentName":"吉总","agentPhone":"18704449999","identity":"3","identityName":"","companyId":"8e381e93aad04945b96046ad6410230f","companyName":"苹果","storeId":"b707b62da103450294002d1f0e505de7","storeName":"苹果1店","status":"1","reason":"","search":""},{"id":"","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","user":"","agentId":"4bb2884cbc774d7a940b49f7e8558eae","loginName":"cs","agentName":"测试","agentPhone":"15506024074","identity":"3","identityName":"","companyId":"bab6ae2572ec4c25bad6b3875be36c28","companyName":"测试公司","storeId":"025d9946751d45228454cd1d2458b941","storeName":"测试一下","status":"1","reason":"","search":""},{"id":"","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","user":"","agentId":"a651f86d360842ab96d16c2f495e469a","loginName":"xj01","agentName":"雪茄01","agentPhone":"15506014085","identity":"1","identityName":"","companyId":"4f3bbbb8643f43b7816f5b32e4fde2b2","companyName":"雪茄","storeId":"e1cf35c4501244d994adf3567d3a51a0","storeName":"雪茄1店","status":"2","reason":"门店已取消合作","search":""},{"id":"","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","user":"","agentId":"96be204281df43d6ba76c7e658e5095e","loginName":"pg21","agentName":"苹果21","agentPhone":"16694467463","identity":"2","identityName":"","companyId":"8e381e93aad04945b96046ad6410230f","companyName":"苹果","storeId":"d90a9b327ce04e2689b09d39716015eb","storeName":"苹果2店","status":"1","reason":"","search":""},{"id":"","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","user":"","agentId":"49a4f194f5864e77abdfb6ea38acc11b","loginName":"qaee","agentName":"哈哈","agentPhone":"13657302120","identity":"2","identityName":"","companyId":"c56d4aea06454af0a77d435160710214","companyName":"杨进礼测试","storeId":"409847846376464aa15fc4a811c59d6c","storeName":"好的","status":"2","reason":"门店已取消合作","search":""},{"id":"","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","user":"","agentId":"1776d67e9cb3481fa0894346423e88f1","loginName":"qazwsx","agentName":"qazxc","agentPhone":"12345678901","identity":"1","identityName":"","companyId":"c56d4aea06454af0a77d435160710214","companyName":"杨进礼测试","storeId":"409847846376464aa15fc4a811c59d6c","storeName":"好的","status":"2","reason":"门店已取消合作","search":""},{"id":"","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","user":"","agentId":"5bb30535b9ba44cba5cf9ecb175a613d","loginName":"pg12","agentName":"苹果1店02","agentPhone":"15867543214","identity":"2","identityName":"","companyId":"8e381e93aad04945b96046ad6410230f","companyName":"苹果","storeId":"b707b62da103450294002d1f0e505de7","storeName":"苹果1店","status":"1","reason":"","search":""},{"id":"","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","user":"","agentId":"75219e2d084d47cfaa26b6efb79a3b6c","loginName":"pg11","agentName":"苹果1店01","agentPhone":"16948732565","identity":"1","identityName":"","companyId":"8e381e93aad04945b96046ad6410230f","companyName":"苹果","storeId":"b707b62da103450294002d1f0e505de7","storeName":"苹果1店","status":"1","reason":"","search":""},{"id":"","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","user":"","agentId":"652990c888874b758ba20a259dc4859d","loginName":"cs7","agentName":"经纪人7","agentPhone":"19904308819","identity":"3","identityName":"","companyId":"004c70f2438249039728411b50d996a7","companyName":"唐老鸭","storeId":"f294af6abbfc4a3ea2a1c7b999c7deff","storeName":"鸭店001","status":"1","reason":"","search":""}]}
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
         * total : 12
         * rows : [{"id":"","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","user":"","agentId":"1bc96d8b974841a7901a7e066015aa51","loginName":"zhazong","agentName":"査总","agentPhone":"15043439666","identity":"3","identityName":"","companyId":"8e381e93aad04945b96046ad6410230f","companyName":"苹果","storeId":"d90a9b327ce04e2689b09d39716015eb","storeName":"苹果2店","status":"1","reason":"","search":""},{"id":"","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","user":"","agentId":"56bde823caaf42a18137dba54f2713c8","loginName":"jizong","agentName":"吉总","agentPhone":"18704449999","identity":"3","identityName":"","companyId":"8e381e93aad04945b96046ad6410230f","companyName":"苹果","storeId":"b707b62da103450294002d1f0e505de7","storeName":"苹果1店","status":"1","reason":"","search":""},{"id":"","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","user":"","agentId":"4bb2884cbc774d7a940b49f7e8558eae","loginName":"cs","agentName":"测试","agentPhone":"15506024074","identity":"3","identityName":"","companyId":"bab6ae2572ec4c25bad6b3875be36c28","companyName":"测试公司","storeId":"025d9946751d45228454cd1d2458b941","storeName":"测试一下","status":"1","reason":"","search":""},{"id":"","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","user":"","agentId":"a651f86d360842ab96d16c2f495e469a","loginName":"xj01","agentName":"雪茄01","agentPhone":"15506014085","identity":"1","identityName":"","companyId":"4f3bbbb8643f43b7816f5b32e4fde2b2","companyName":"雪茄","storeId":"e1cf35c4501244d994adf3567d3a51a0","storeName":"雪茄1店","status":"2","reason":"门店已取消合作","search":""},{"id":"","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","user":"","agentId":"96be204281df43d6ba76c7e658e5095e","loginName":"pg21","agentName":"苹果21","agentPhone":"16694467463","identity":"2","identityName":"","companyId":"8e381e93aad04945b96046ad6410230f","companyName":"苹果","storeId":"d90a9b327ce04e2689b09d39716015eb","storeName":"苹果2店","status":"1","reason":"","search":""},{"id":"","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","user":"","agentId":"49a4f194f5864e77abdfb6ea38acc11b","loginName":"qaee","agentName":"哈哈","agentPhone":"13657302120","identity":"2","identityName":"","companyId":"c56d4aea06454af0a77d435160710214","companyName":"杨进礼测试","storeId":"409847846376464aa15fc4a811c59d6c","storeName":"好的","status":"2","reason":"门店已取消合作","search":""},{"id":"","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","user":"","agentId":"1776d67e9cb3481fa0894346423e88f1","loginName":"qazwsx","agentName":"qazxc","agentPhone":"12345678901","identity":"1","identityName":"","companyId":"c56d4aea06454af0a77d435160710214","companyName":"杨进礼测试","storeId":"409847846376464aa15fc4a811c59d6c","storeName":"好的","status":"2","reason":"门店已取消合作","search":""},{"id":"","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","user":"","agentId":"5bb30535b9ba44cba5cf9ecb175a613d","loginName":"pg12","agentName":"苹果1店02","agentPhone":"15867543214","identity":"2","identityName":"","companyId":"8e381e93aad04945b96046ad6410230f","companyName":"苹果","storeId":"b707b62da103450294002d1f0e505de7","storeName":"苹果1店","status":"1","reason":"","search":""},{"id":"","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","user":"","agentId":"75219e2d084d47cfaa26b6efb79a3b6c","loginName":"pg11","agentName":"苹果1店01","agentPhone":"16948732565","identity":"1","identityName":"","companyId":"8e381e93aad04945b96046ad6410230f","companyName":"苹果","storeId":"b707b62da103450294002d1f0e505de7","storeName":"苹果1店","status":"1","reason":"","search":""},{"id":"","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","user":"","agentId":"652990c888874b758ba20a259dc4859d","loginName":"cs7","agentName":"经纪人7","agentPhone":"19904308819","identity":"3","identityName":"","companyId":"004c70f2438249039728411b50d996a7","companyName":"唐老鸭","storeId":"f294af6abbfc4a3ea2a1c7b999c7deff","storeName":"鸭店001","status":"1","reason":"","search":""}]
         */

        private int total;
        private List<RowsBean> rows;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public List<RowsBean> getRows() {
            return rows;
        }

        public void setRows(List<RowsBean> rows) {
            this.rows = rows;
        }

        public static class RowsBean {
            /**
             * id :
             * remarks :
             * createBy :
             * createDate :
             * updateBy :
             * updateDate :
             * user :
             * agentId : 1bc96d8b974841a7901a7e066015aa51
             * loginName : zhazong
             * agentName : 査总
             * agentPhone : 15043439666
             * identity : 3
             * identityName :
             * companyId : 8e381e93aad04945b96046ad6410230f
             * companyName : 苹果
             * storeId : d90a9b327ce04e2689b09d39716015eb
             * storeName : 苹果2店
             * status : 1
             * reason :
             * search :
             */

            private String id;
            private String remarks;
            private String createBy;
            private String createDate;
            private String updateBy;
            private String updateDate;
            private String user;
            private String agentId;
            private String loginName;
            private String agentName;
            private String agentPhone;
            private String identity;
            private String identityName;
            private String companyId;
            private String companyName;
            private String storeId;
            private String storeName;
            private String status;
            private String reason;
            private String search;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getRemarks() {
                return remarks;
            }

            public void setRemarks(String remarks) {
                this.remarks = remarks;
            }

            public String getCreateBy() {
                return createBy;
            }

            public void setCreateBy(String createBy) {
                this.createBy = createBy;
            }

            public String getCreateDate() {
                return createDate;
            }

            public void setCreateDate(String createDate) {
                this.createDate = createDate;
            }

            public String getUpdateBy() {
                return updateBy;
            }

            public void setUpdateBy(String updateBy) {
                this.updateBy = updateBy;
            }

            public String getUpdateDate() {
                return updateDate;
            }

            public void setUpdateDate(String updateDate) {
                this.updateDate = updateDate;
            }

            public String getUser() {
                return user;
            }

            public void setUser(String user) {
                this.user = user;
            }

            public String getAgentId() {
                return agentId;
            }

            public void setAgentId(String agentId) {
                this.agentId = agentId;
            }

            public String getLoginName() {
                return loginName;
            }

            public void setLoginName(String loginName) {
                this.loginName = loginName;
            }

            public String getAgentName() {
                return agentName;
            }

            public void setAgentName(String agentName) {
                this.agentName = agentName;
            }

            public String getAgentPhone() {
                return agentPhone;
            }

            public void setAgentPhone(String agentPhone) {
                this.agentPhone = agentPhone;
            }

            public String getIdentity() {
                return identity;
            }

            public void setIdentity(String identity) {
                this.identity = identity;
            }

            public String getIdentityName() {
                return identityName;
            }

            public void setIdentityName(String identityName) {
                this.identityName = identityName;
            }

            public String getCompanyId() {
                return companyId;
            }

            public void setCompanyId(String companyId) {
                this.companyId = companyId;
            }

            public String getCompanyName() {
                return companyName;
            }

            public void setCompanyName(String companyName) {
                this.companyName = companyName;
            }

            public String getStoreId() {
                return storeId;
            }

            public void setStoreId(String storeId) {
                this.storeId = storeId;
            }

            public String getStoreName() {
                return storeName;
            }

            public void setStoreName(String storeName) {
                this.storeName = storeName;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getReason() {
                return reason;
            }

            public void setReason(String reason) {
                this.reason = reason;
            }

            public String getSearch() {
                return search;
            }

            public void setSearch(String search) {
                this.search = search;
            }
        }
    }
}
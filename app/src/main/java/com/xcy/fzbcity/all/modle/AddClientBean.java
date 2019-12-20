package com.xcy.fzbcity.all.modle;

public class AddClientBean {


    /**
     * code : 1
     * msg : 成功
     * data : {"id":"7cc46d28e46d42a096078db3e60758e1","remarks":"","createBy":"","createDate":"2019-08-14 15:16:18","updateBy":"","updateDate":"2019-08-14 15:16:18","customerName":"张三","letter":"Z","user":{"id":"43dea5335a1b4cb6bf15782a3be87c6a","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","company":"","office":"","post":"","loginName":"","no":"","name":"","email":"","phone":"","oldPhone":"","mobile":"","loginIp":"","loginDate":"","loginFlag":"1","photo":"/fangfang/static/common/images/flat-avatar.png","qrCode":"","oldLoginName":"","newPassword":"","sign":"","oldLoginIp":"","oldLoginDate":"","wechatOpenid":"","wechatData":"","role":"","sex":"","province":"","city":"","county":"","identity":"","brokerId":"","storeId":"","companyManage":"","storeManage":"","classics":0,"team":"","admin":false,"roleNames":""},"contacts1":"本人手机","contactsPhone1":"13800000000","contacts2":"","contactsPhone2":"","contacts3":"","contactsPhone3":"","customerImg":"/fangfang/static/common/images/flat-avatar.png","gender":"","name":"张三","searchName":""}
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
         * id : 7cc46d28e46d42a096078db3e60758e1
         * remarks :
         * createBy :
         * createDate : 2019-08-14 15:16:18
         * updateBy :
         * updateDate : 2019-08-14 15:16:18
         * customerName : 张三
         * letter : Z
         * user : {"id":"43dea5335a1b4cb6bf15782a3be87c6a","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","company":"","office":"","post":"","loginName":"","no":"","name":"","email":"","phone":"","oldPhone":"","mobile":"","loginIp":"","loginDate":"","loginFlag":"1","photo":"/fangfang/static/common/images/flat-avatar.png","qrCode":"","oldLoginName":"","newPassword":"","sign":"","oldLoginIp":"","oldLoginDate":"","wechatOpenid":"","wechatData":"","role":"","sex":"","province":"","city":"","county":"","identity":"","brokerId":"","storeId":"","companyManage":"","storeManage":"","classics":0,"team":"","admin":false,"roleNames":""}
         * contacts1 : 本人手机
         * contactsPhone1 : 13800000000
         * contacts2 :
         * contactsPhone2 :
         * contacts3 :
         * contactsPhone3 :
         * customerImg : /fangfang/static/common/images/flat-avatar.png
         * gender :
         * name : 张三
         * searchName :
         */

        private String id;
        private String remarks;
        private String createBy;
        private String createDate;
        private String updateBy;
        private String updateDate;
        private String customerName;
        private String letter;
        private UserBean user;
        private String contacts1;
        private String contactsPhone1;
        private String contacts2;
        private String contactsPhone2;
        private String contacts3;
        private String contactsPhone3;
        private String customerImg;
        private String gender;
        private String name;
        private String searchName;

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

        public String getCustomerName() {
            return customerName;
        }

        public void setCustomerName(String customerName) {
            this.customerName = customerName;
        }

        public String getLetter() {
            return letter;
        }

        public void setLetter(String letter) {
            this.letter = letter;
        }

        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
            this.user = user;
        }

        public String getContacts1() {
            return contacts1;
        }

        public void setContacts1(String contacts1) {
            this.contacts1 = contacts1;
        }

        public String getContactsPhone1() {
            return contactsPhone1;
        }

        public void setContactsPhone1(String contactsPhone1) {
            this.contactsPhone1 = contactsPhone1;
        }

        public String getContacts2() {
            return contacts2;
        }

        public void setContacts2(String contacts2) {
            this.contacts2 = contacts2;
        }

        public String getContactsPhone2() {
            return contactsPhone2;
        }

        public void setContactsPhone2(String contactsPhone2) {
            this.contactsPhone2 = contactsPhone2;
        }

        public String getContacts3() {
            return contacts3;
        }

        public void setContacts3(String contacts3) {
            this.contacts3 = contacts3;
        }

        public String getContactsPhone3() {
            return contactsPhone3;
        }

        public void setContactsPhone3(String contactsPhone3) {
            this.contactsPhone3 = contactsPhone3;
        }

        public String getCustomerImg() {
            return customerImg;
        }

        public void setCustomerImg(String customerImg) {
            this.customerImg = customerImg;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSearchName() {
            return searchName;
        }

        public void setSearchName(String searchName) {
            this.searchName = searchName;
        }

        public static class UserBean {
            /**
             * id : 43dea5335a1b4cb6bf15782a3be87c6a
             * remarks :
             * createBy :
             * createDate :
             * updateBy :
             * updateDate :
             * company :
             * office :
             * post :
             * loginName :
             * no :
             * name :
             * email :
             * phone :
             * oldPhone :
             * mobile :
             * loginIp :
             * loginDate :
             * loginFlag : 1
             * photo : /fangfang/static/common/images/flat-avatar.png
             * qrCode :
             * oldLoginName :
             * newPassword :
             * sign :
             * oldLoginIp :
             * oldLoginDate :
             * wechatOpenid :
             * wechatData :
             * role :
             * sex :
             * province :
             * city :
             * county :
             * identity :
             * brokerId :
             * storeId :
             * companyManage :
             * storeManage :
             * classics : 0
             * team :
             * admin : false
             * roleNames :
             */

            private String id;
            private String remarks;
            private String createBy;
            private String createDate;
            private String updateBy;
            private String updateDate;
            private String company;
            private String office;
            private String post;
            private String loginName;
            private String no;
            private String name;
            private String email;
            private String phone;
            private String oldPhone;
            private String mobile;
            private String loginIp;
            private String loginDate;
            private String loginFlag;
            private String photo;
            private String qrCode;
            private String oldLoginName;
            private String newPassword;
            private String sign;
            private String oldLoginIp;
            private String oldLoginDate;
            private String wechatOpenid;
            private String wechatData;
            private String role;
            private String sex;
            private String province;
            private String city;
            private String county;
            private String identity;
            private String brokerId;
            private String storeId;
            private String companyManage;
            private String storeManage;
            private int classics;
            private String team;
            private boolean admin;
            private String roleNames;

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

            public String getCompany() {
                return company;
            }

            public void setCompany(String company) {
                this.company = company;
            }

            public String getOffice() {
                return office;
            }

            public void setOffice(String office) {
                this.office = office;
            }

            public String getPost() {
                return post;
            }

            public void setPost(String post) {
                this.post = post;
            }

            public String getLoginName() {
                return loginName;
            }

            public void setLoginName(String loginName) {
                this.loginName = loginName;
            }

            public String getNo() {
                return no;
            }

            public void setNo(String no) {
                this.no = no;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getEmail() {
                return email;
            }

            public void setEmail(String email) {
                this.email = email;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public String getOldPhone() {
                return oldPhone;
            }

            public void setOldPhone(String oldPhone) {
                this.oldPhone = oldPhone;
            }

            public String getMobile() {
                return mobile;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }

            public String getLoginIp() {
                return loginIp;
            }

            public void setLoginIp(String loginIp) {
                this.loginIp = loginIp;
            }

            public String getLoginDate() {
                return loginDate;
            }

            public void setLoginDate(String loginDate) {
                this.loginDate = loginDate;
            }

            public String getLoginFlag() {
                return loginFlag;
            }

            public void setLoginFlag(String loginFlag) {
                this.loginFlag = loginFlag;
            }

            public String getPhoto() {
                return photo;
            }

            public void setPhoto(String photo) {
                this.photo = photo;
            }

            public String getQrCode() {
                return qrCode;
            }

            public void setQrCode(String qrCode) {
                this.qrCode = qrCode;
            }

            public String getOldLoginName() {
                return oldLoginName;
            }

            public void setOldLoginName(String oldLoginName) {
                this.oldLoginName = oldLoginName;
            }

            public String getNewPassword() {
                return newPassword;
            }

            public void setNewPassword(String newPassword) {
                this.newPassword = newPassword;
            }

            public String getSign() {
                return sign;
            }

            public void setSign(String sign) {
                this.sign = sign;
            }

            public String getOldLoginIp() {
                return oldLoginIp;
            }

            public void setOldLoginIp(String oldLoginIp) {
                this.oldLoginIp = oldLoginIp;
            }

            public String getOldLoginDate() {
                return oldLoginDate;
            }

            public void setOldLoginDate(String oldLoginDate) {
                this.oldLoginDate = oldLoginDate;
            }

            public String getWechatOpenid() {
                return wechatOpenid;
            }

            public void setWechatOpenid(String wechatOpenid) {
                this.wechatOpenid = wechatOpenid;
            }

            public String getWechatData() {
                return wechatData;
            }

            public void setWechatData(String wechatData) {
                this.wechatData = wechatData;
            }

            public String getRole() {
                return role;
            }

            public void setRole(String role) {
                this.role = role;
            }

            public String getSex() {
                return sex;
            }

            public void setSex(String sex) {
                this.sex = sex;
            }

            public String getProvince() {
                return province;
            }

            public void setProvince(String province) {
                this.province = province;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getCounty() {
                return county;
            }

            public void setCounty(String county) {
                this.county = county;
            }

            public String getIdentity() {
                return identity;
            }

            public void setIdentity(String identity) {
                this.identity = identity;
            }

            public String getBrokerId() {
                return brokerId;
            }

            public void setBrokerId(String brokerId) {
                this.brokerId = brokerId;
            }

            public String getStoreId() {
                return storeId;
            }

            public void setStoreId(String storeId) {
                this.storeId = storeId;
            }

            public String getCompanyManage() {
                return companyManage;
            }

            public void setCompanyManage(String companyManage) {
                this.companyManage = companyManage;
            }

            public String getStoreManage() {
                return storeManage;
            }

            public void setStoreManage(String storeManage) {
                this.storeManage = storeManage;
            }

            public int getClassics() {
                return classics;
            }

            public void setClassics(int classics) {
                this.classics = classics;
            }

            public String getTeam() {
                return team;
            }

            public void setTeam(String team) {
                this.team = team;
            }

            public boolean isAdmin() {
                return admin;
            }

            public void setAdmin(boolean admin) {
                this.admin = admin;
            }

            public String getRoleNames() {
                return roleNames;
            }

            public void setRoleNames(String roleNames) {
                this.roleNames = roleNames;
            }
        }
    }
}
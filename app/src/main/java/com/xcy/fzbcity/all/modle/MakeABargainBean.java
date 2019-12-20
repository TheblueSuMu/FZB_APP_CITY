package com.xcy.fzbcity.all.modle;

import java.util.List;

public class MakeABargainBean {


    /**
     * code : 1
     * msg : 成功
     * data : {"listData":[{"id":"","remarks":"","createBy":"","createDate":"2019-04-30 10:44:11","updateBy":"","updateDate":"2019-09-02 17:34:33","projectId":"603e127528f747b68bee14d37b3f6239","projectName":"西港云上","projectImg":"/fangfang/userfiles/3c37d25396a940f9b784b4c180f7db37/attachment//ff/server/ffServerHousesPhoto/2019/4/1(1).png","preparationId":"e1fc484ccf1642db8bb9d6901f053080","auditStatus":"1","status":"60","protectDay":"30","amount":"","landingStatus":"3","refuseReason":"","attacheList":[{"id":"82283a8b480545ac9729e24e5b1897c4","remarks":"","createBy":"","createDate":"2019-07-03 10:27:31","updateBy":"","updateDate":"","project":{"id":"603e127528f747b68bee14d37b3f6239","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","projectName":"","developer":"","houseCompany":"","cityCompany":"","projectType":"","attache":"","cooperationState":"","onlineState":"","belongsArea":"","address":"","location":"","totalBuildings":"","hot":"","sort":"","remark":"","province":"","city":"","region":"","forwardingAmount":"","reportAmount":"","nation":"","hotSort":"","projectImg":"","projectListImg":"","buildingImg":"","browseNum":"","awardRules":"","commissionRules":"","searchName":"","collectionNum":"","productTypeId":"","productTypeSize":"","amountIncentiveId":"","cityName":"","haveInformation":"","projectTypeName":"","isgroup":"","groupNum":""},"attacheId":"3a0ee28065a84d548c96ee51849b5222","name":"王珊珊","phone":"13944098039"}],"statusName":"成交","statusColor":"#3B82D2","listMap":[{"value":"2019-09-02 17:34:33","key":"成交时间"}]},{"id":"","remarks":"","createBy":"","createDate":"2019-04-29 20:21:20","updateBy":"","updateDate":"2019-06-10 10:22:40","projectId":"fc17eeeb177f4e5e85625264234bc5fe","projectName":"Chelsea Creek | 切尔西水畔 泰晤士河北岸伦敦西二区","projectImg":"/fangfang/userfiles/3c37d25396a940f9b784b4c180f7db37/attachment//ff/server/ffServerHousesPhoto/2019/4/17(4).jpg","preparationId":"fefdfcaca9c54600a76f2986a0974917","auditStatus":"1","status":"40","protectDay":"6","amount":"","landingStatus":"1","refuseReason":"","attacheList":[{"id":"ab85c6c85a724d42a1b6383fc3004922","remarks":"","createBy":"","createDate":"2019-04-22 18:08:52","updateBy":"","updateDate":"","project":{"id":"fc17eeeb177f4e5e85625264234bc5fe","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","projectName":"","developer":"","houseCompany":"","cityCompany":"","projectType":"","attache":"","cooperationState":"","onlineState":"","belongsArea":"","address":"","location":"","totalBuildings":"","hot":"","sort":"","remark":"","province":"","city":"","region":"","forwardingAmount":"","reportAmount":"","nation":"","hotSort":"","projectImg":"","projectListImg":"","buildingImg":"","browseNum":"","awardRules":"","commissionRules":"","searchName":"","collectionNum":"","productTypeId":"","productTypeSize":"","amountIncentiveId":"","cityName":"","haveInformation":"","projectTypeName":"","isgroup":"","groupNum":""},"attacheId":"3a0ee28065a84d548c96ee51849b5222","name":"王珊珊","phone":"13944098039"}],"statusName":"登岛","statusColor":"#3B82D2","listMap":[{"value":"2019-06-10 10:22:40","key":"登岛时间"}]}],"infoData":{"id":"e6626e1104f244cdafc24287eb6f8b44","remarks":"","createBy":"","createDate":"2019-04-29 20:09:57","updateBy":"","updateDate":"2019-04-29 20:09:57","customerName":"王换手机","letter":"W","user":{"id":"43dea5335a1b4cb6bf15782a3be87c6a","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","company":"","office":"","post":"","loginName":"","no":"","name":"虎虎","email":"","phone":"","oldPhone":"","mobile":"","loginIp":"","loginDate":"","loginFlag":"1","photo":"/fangfang/static/common/images/flat-avatar.png","qrCode":"","oldLoginName":"","newPassword":"","sign":"","oldLoginIp":"","oldLoginDate":"","wechatOpenid":"","wechatData":"","role":"","sex":"","province":"","city":"","county":"","identity":"","brokerId":"","storeId":"","companyManage":"","storeManage":"","classics":0,"team":"","admin":false,"roleNames":""},"contacts1":"本人手机","contactsPhone1":"1760104811","contacts2":"本人手机","contactsPhone2":"45546466","contacts3":"","contactsPhone3":"","customerImg":"/fangfang/userfiles/43dea5335a1b4cb6bf15782a3be87c6a/attachment//ff/server/customer/2019/4/1556539794759.jpeg","gender":"","name":"王换手机","searchName":""}}
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
         * listData : [{"id":"","remarks":"","createBy":"","createDate":"2019-04-30 10:44:11","updateBy":"","updateDate":"2019-09-02 17:34:33","projectId":"603e127528f747b68bee14d37b3f6239","projectName":"西港云上","projectImg":"/fangfang/userfiles/3c37d25396a940f9b784b4c180f7db37/attachment//ff/server/ffServerHousesPhoto/2019/4/1(1).png","preparationId":"e1fc484ccf1642db8bb9d6901f053080","auditStatus":"1","status":"60","protectDay":"30","amount":"","landingStatus":"3","refuseReason":"","attacheList":[{"id":"82283a8b480545ac9729e24e5b1897c4","remarks":"","createBy":"","createDate":"2019-07-03 10:27:31","updateBy":"","updateDate":"","project":{"id":"603e127528f747b68bee14d37b3f6239","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","projectName":"","developer":"","houseCompany":"","cityCompany":"","projectType":"","attache":"","cooperationState":"","onlineState":"","belongsArea":"","address":"","location":"","totalBuildings":"","hot":"","sort":"","remark":"","province":"","city":"","region":"","forwardingAmount":"","reportAmount":"","nation":"","hotSort":"","projectImg":"","projectListImg":"","buildingImg":"","browseNum":"","awardRules":"","commissionRules":"","searchName":"","collectionNum":"","productTypeId":"","productTypeSize":"","amountIncentiveId":"","cityName":"","haveInformation":"","projectTypeName":"","isgroup":"","groupNum":""},"attacheId":"3a0ee28065a84d548c96ee51849b5222","name":"王珊珊","phone":"13944098039"}],"statusName":"成交","statusColor":"#3B82D2","listMap":[{"value":"2019-09-02 17:34:33","key":"成交时间"}]},{"id":"","remarks":"","createBy":"","createDate":"2019-04-29 20:21:20","updateBy":"","updateDate":"2019-06-10 10:22:40","projectId":"fc17eeeb177f4e5e85625264234bc5fe","projectName":"Chelsea Creek | 切尔西水畔 泰晤士河北岸伦敦西二区","projectImg":"/fangfang/userfiles/3c37d25396a940f9b784b4c180f7db37/attachment//ff/server/ffServerHousesPhoto/2019/4/17(4).jpg","preparationId":"fefdfcaca9c54600a76f2986a0974917","auditStatus":"1","status":"40","protectDay":"6","amount":"","landingStatus":"1","refuseReason":"","attacheList":[{"id":"ab85c6c85a724d42a1b6383fc3004922","remarks":"","createBy":"","createDate":"2019-04-22 18:08:52","updateBy":"","updateDate":"","project":{"id":"fc17eeeb177f4e5e85625264234bc5fe","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","projectName":"","developer":"","houseCompany":"","cityCompany":"","projectType":"","attache":"","cooperationState":"","onlineState":"","belongsArea":"","address":"","location":"","totalBuildings":"","hot":"","sort":"","remark":"","province":"","city":"","region":"","forwardingAmount":"","reportAmount":"","nation":"","hotSort":"","projectImg":"","projectListImg":"","buildingImg":"","browseNum":"","awardRules":"","commissionRules":"","searchName":"","collectionNum":"","productTypeId":"","productTypeSize":"","amountIncentiveId":"","cityName":"","haveInformation":"","projectTypeName":"","isgroup":"","groupNum":""},"attacheId":"3a0ee28065a84d548c96ee51849b5222","name":"王珊珊","phone":"13944098039"}],"statusName":"登岛","statusColor":"#3B82D2","listMap":[{"value":"2019-06-10 10:22:40","key":"登岛时间"}]}]
         * infoData : {"id":"e6626e1104f244cdafc24287eb6f8b44","remarks":"","createBy":"","createDate":"2019-04-29 20:09:57","updateBy":"","updateDate":"2019-04-29 20:09:57","customerName":"王换手机","letter":"W","user":{"id":"43dea5335a1b4cb6bf15782a3be87c6a","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","company":"","office":"","post":"","loginName":"","no":"","name":"虎虎","email":"","phone":"","oldPhone":"","mobile":"","loginIp":"","loginDate":"","loginFlag":"1","photo":"/fangfang/static/common/images/flat-avatar.png","qrCode":"","oldLoginName":"","newPassword":"","sign":"","oldLoginIp":"","oldLoginDate":"","wechatOpenid":"","wechatData":"","role":"","sex":"","province":"","city":"","county":"","identity":"","brokerId":"","storeId":"","companyManage":"","storeManage":"","classics":0,"team":"","admin":false,"roleNames":""},"contacts1":"本人手机","contactsPhone1":"1760104811","contacts2":"本人手机","contactsPhone2":"45546466","contacts3":"","contactsPhone3":"","customerImg":"/fangfang/userfiles/43dea5335a1b4cb6bf15782a3be87c6a/attachment//ff/server/customer/2019/4/1556539794759.jpeg","gender":"","name":"王换手机","searchName":""}
         */

        private InfoDataBean infoData;
        private List<ListDataBean> listData;

        public InfoDataBean getInfoData() {
            return infoData;
        }

        public void setInfoData(InfoDataBean infoData) {
            this.infoData = infoData;
        }

        public List<ListDataBean> getListData() {
            return listData;
        }

        public void setListData(List<ListDataBean> listData) {
            this.listData = listData;
        }

        public static class InfoDataBean {
            /**
             * id : e6626e1104f244cdafc24287eb6f8b44
             * remarks :
             * createBy :
             * createDate : 2019-04-29 20:09:57
             * updateBy :
             * updateDate : 2019-04-29 20:09:57
             * customerName : 王换手机
             * letter : W
             * user : {"id":"43dea5335a1b4cb6bf15782a3be87c6a","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","company":"","office":"","post":"","loginName":"","no":"","name":"虎虎","email":"","phone":"","oldPhone":"","mobile":"","loginIp":"","loginDate":"","loginFlag":"1","photo":"/fangfang/static/common/images/flat-avatar.png","qrCode":"","oldLoginName":"","newPassword":"","sign":"","oldLoginIp":"","oldLoginDate":"","wechatOpenid":"","wechatData":"","role":"","sex":"","province":"","city":"","county":"","identity":"","brokerId":"","storeId":"","companyManage":"","storeManage":"","classics":0,"team":"","admin":false,"roleNames":""}
             * contacts1 : 本人手机
             * contactsPhone1 : 1760104811
             * contacts2 : 本人手机
             * contactsPhone2 : 45546466
             * contacts3 :
             * contactsPhone3 :
             * customerImg : /fangfang/userfiles/43dea5335a1b4cb6bf15782a3be87c6a/attachment//ff/server/customer/2019/4/1556539794759.jpeg
             * gender :
             * name : 王换手机
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
                 * name : 虎虎
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

        public static class ListDataBean {
            /**
             * id :
             * remarks :
             * createBy :
             * createDate : 2019-04-30 10:44:11
             * updateBy :
             * updateDate : 2019-09-02 17:34:33
             * projectId : 603e127528f747b68bee14d37b3f6239
             * projectName : 西港云上
             * projectImg : /fangfang/userfiles/3c37d25396a940f9b784b4c180f7db37/attachment//ff/server/ffServerHousesPhoto/2019/4/1(1).png
             * preparationId : e1fc484ccf1642db8bb9d6901f053080
             * auditStatus : 1
             * status : 60
             * protectDay : 30
             * amount :
             * landingStatus : 3
             * refuseReason :
             * attacheList : [{"id":"82283a8b480545ac9729e24e5b1897c4","remarks":"","createBy":"","createDate":"2019-07-03 10:27:31","updateBy":"","updateDate":"","project":{"id":"603e127528f747b68bee14d37b3f6239","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","projectName":"","developer":"","houseCompany":"","cityCompany":"","projectType":"","attache":"","cooperationState":"","onlineState":"","belongsArea":"","address":"","location":"","totalBuildings":"","hot":"","sort":"","remark":"","province":"","city":"","region":"","forwardingAmount":"","reportAmount":"","nation":"","hotSort":"","projectImg":"","projectListImg":"","buildingImg":"","browseNum":"","awardRules":"","commissionRules":"","searchName":"","collectionNum":"","productTypeId":"","productTypeSize":"","amountIncentiveId":"","cityName":"","haveInformation":"","projectTypeName":"","isgroup":"","groupNum":""},"attacheId":"3a0ee28065a84d548c96ee51849b5222","name":"王珊珊","phone":"13944098039"}]
             * statusName : 成交
             * statusColor : #3B82D2
             * listMap : [{"value":"2019-09-02 17:34:33","key":"成交时间"}]
             */

            private String id;
            private String remarks;
            private String createBy;
            private String createDate;
            private String updateBy;
            private String updateDate;
            private String projectId;
            private String projectName;
            private String projectImg;
            private String preparationId;
            private String auditStatus;
            private String status;
            private String protectDay;
            private String amount;
            private String landingStatus;
            private String refuseReason;
            private String statusName;
            private String statusColor;
            private List<AttacheListBean> attacheList;
            private List<ListMapBean> listMap;

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

            public String getProjectId() {
                return projectId;
            }

            public void setProjectId(String projectId) {
                this.projectId = projectId;
            }

            public String getProjectName() {
                return projectName;
            }

            public void setProjectName(String projectName) {
                this.projectName = projectName;
            }

            public String getProjectImg() {
                return projectImg;
            }

            public void setProjectImg(String projectImg) {
                this.projectImg = projectImg;
            }

            public String getPreparationId() {
                return preparationId;
            }

            public void setPreparationId(String preparationId) {
                this.preparationId = preparationId;
            }

            public String getAuditStatus() {
                return auditStatus;
            }

            public void setAuditStatus(String auditStatus) {
                this.auditStatus = auditStatus;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getProtectDay() {
                return protectDay;
            }

            public void setProtectDay(String protectDay) {
                this.protectDay = protectDay;
            }

            public String getAmount() {
                return amount;
            }

            public void setAmount(String amount) {
                this.amount = amount;
            }

            public String getLandingStatus() {
                return landingStatus;
            }

            public void setLandingStatus(String landingStatus) {
                this.landingStatus = landingStatus;
            }

            public String getRefuseReason() {
                return refuseReason;
            }

            public void setRefuseReason(String refuseReason) {
                this.refuseReason = refuseReason;
            }

            public String getStatusName() {
                return statusName;
            }

            public void setStatusName(String statusName) {
                this.statusName = statusName;
            }

            public String getStatusColor() {
                return statusColor;
            }

            public void setStatusColor(String statusColor) {
                this.statusColor = statusColor;
            }

            public List<AttacheListBean> getAttacheList() {
                return attacheList;
            }

            public void setAttacheList(List<AttacheListBean> attacheList) {
                this.attacheList = attacheList;
            }

            public List<ListMapBean> getListMap() {
                return listMap;
            }

            public void setListMap(List<ListMapBean> listMap) {
                this.listMap = listMap;
            }

            public static class AttacheListBean {
                /**
                 * id : 82283a8b480545ac9729e24e5b1897c4
                 * remarks :
                 * createBy :
                 * createDate : 2019-07-03 10:27:31
                 * updateBy :
                 * updateDate :
                 * project : {"id":"603e127528f747b68bee14d37b3f6239","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","projectName":"","developer":"","houseCompany":"","cityCompany":"","projectType":"","attache":"","cooperationState":"","onlineState":"","belongsArea":"","address":"","location":"","totalBuildings":"","hot":"","sort":"","remark":"","province":"","city":"","region":"","forwardingAmount":"","reportAmount":"","nation":"","hotSort":"","projectImg":"","projectListImg":"","buildingImg":"","browseNum":"","awardRules":"","commissionRules":"","searchName":"","collectionNum":"","productTypeId":"","productTypeSize":"","amountIncentiveId":"","cityName":"","haveInformation":"","projectTypeName":"","isgroup":"","groupNum":""}
                 * attacheId : 3a0ee28065a84d548c96ee51849b5222
                 * name : 王珊珊
                 * phone : 13944098039
                 */

                private String id;
                private String remarks;
                private String createBy;
                private String createDate;
                private String updateBy;
                private String updateDate;
                private ProjectBean project;
                private String attacheId;
                private String name;
                private String phone;

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

                public ProjectBean getProject() {
                    return project;
                }

                public void setProject(ProjectBean project) {
                    this.project = project;
                }

                public String getAttacheId() {
                    return attacheId;
                }

                public void setAttacheId(String attacheId) {
                    this.attacheId = attacheId;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getPhone() {
                    return phone;
                }

                public void setPhone(String phone) {
                    this.phone = phone;
                }

                public static class ProjectBean {
                    /**
                     * id : 603e127528f747b68bee14d37b3f6239
                     * remarks :
                     * createBy :
                     * createDate :
                     * updateBy :
                     * updateDate :
                     * projectName :
                     * developer :
                     * houseCompany :
                     * cityCompany :
                     * projectType :
                     * attache :
                     * cooperationState :
                     * onlineState :
                     * belongsArea :
                     * address :
                     * location :
                     * totalBuildings :
                     * hot :
                     * sort :
                     * remark :
                     * province :
                     * city :
                     * region :
                     * forwardingAmount :
                     * reportAmount :
                     * nation :
                     * hotSort :
                     * projectImg :
                     * projectListImg :
                     * buildingImg :
                     * browseNum :
                     * awardRules :
                     * commissionRules :
                     * searchName :
                     * collectionNum :
                     * productTypeId :
                     * productTypeSize :
                     * amountIncentiveId :
                     * cityName :
                     * haveInformation :
                     * projectTypeName :
                     * isgroup :
                     * groupNum :
                     */

                    private String id;
                    private String remarks;
                    private String createBy;
                    private String createDate;
                    private String updateBy;
                    private String updateDate;
                    private String projectName;
                    private String developer;
                    private String houseCompany;
                    private String cityCompany;
                    private String projectType;
                    private String attache;
                    private String cooperationState;
                    private String onlineState;
                    private String belongsArea;
                    private String address;
                    private String location;
                    private String totalBuildings;
                    private String hot;
                    private String sort;
                    private String remark;
                    private String province;
                    private String city;
                    private String region;
                    private String forwardingAmount;
                    private String reportAmount;
                    private String nation;
                    private String hotSort;
                    private String projectImg;
                    private String projectListImg;
                    private String buildingImg;
                    private String browseNum;
                    private String awardRules;
                    private String commissionRules;
                    private String searchName;
                    private String collectionNum;
                    private String productTypeId;
                    private String productTypeSize;
                    private String amountIncentiveId;
                    private String cityName;
                    private String haveInformation;
                    private String projectTypeName;
                    private String isgroup;
                    private String groupNum;

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

                    public String getProjectName() {
                        return projectName;
                    }

                    public void setProjectName(String projectName) {
                        this.projectName = projectName;
                    }

                    public String getDeveloper() {
                        return developer;
                    }

                    public void setDeveloper(String developer) {
                        this.developer = developer;
                    }

                    public String getHouseCompany() {
                        return houseCompany;
                    }

                    public void setHouseCompany(String houseCompany) {
                        this.houseCompany = houseCompany;
                    }

                    public String getCityCompany() {
                        return cityCompany;
                    }

                    public void setCityCompany(String cityCompany) {
                        this.cityCompany = cityCompany;
                    }

                    public String getProjectType() {
                        return projectType;
                    }

                    public void setProjectType(String projectType) {
                        this.projectType = projectType;
                    }

                    public String getAttache() {
                        return attache;
                    }

                    public void setAttache(String attache) {
                        this.attache = attache;
                    }

                    public String getCooperationState() {
                        return cooperationState;
                    }

                    public void setCooperationState(String cooperationState) {
                        this.cooperationState = cooperationState;
                    }

                    public String getOnlineState() {
                        return onlineState;
                    }

                    public void setOnlineState(String onlineState) {
                        this.onlineState = onlineState;
                    }

                    public String getBelongsArea() {
                        return belongsArea;
                    }

                    public void setBelongsArea(String belongsArea) {
                        this.belongsArea = belongsArea;
                    }

                    public String getAddress() {
                        return address;
                    }

                    public void setAddress(String address) {
                        this.address = address;
                    }

                    public String getLocation() {
                        return location;
                    }

                    public void setLocation(String location) {
                        this.location = location;
                    }

                    public String getTotalBuildings() {
                        return totalBuildings;
                    }

                    public void setTotalBuildings(String totalBuildings) {
                        this.totalBuildings = totalBuildings;
                    }

                    public String getHot() {
                        return hot;
                    }

                    public void setHot(String hot) {
                        this.hot = hot;
                    }

                    public String getSort() {
                        return sort;
                    }

                    public void setSort(String sort) {
                        this.sort = sort;
                    }

                    public String getRemark() {
                        return remark;
                    }

                    public void setRemark(String remark) {
                        this.remark = remark;
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

                    public String getRegion() {
                        return region;
                    }

                    public void setRegion(String region) {
                        this.region = region;
                    }

                    public String getForwardingAmount() {
                        return forwardingAmount;
                    }

                    public void setForwardingAmount(String forwardingAmount) {
                        this.forwardingAmount = forwardingAmount;
                    }

                    public String getReportAmount() {
                        return reportAmount;
                    }

                    public void setReportAmount(String reportAmount) {
                        this.reportAmount = reportAmount;
                    }

                    public String getNation() {
                        return nation;
                    }

                    public void setNation(String nation) {
                        this.nation = nation;
                    }

                    public String getHotSort() {
                        return hotSort;
                    }

                    public void setHotSort(String hotSort) {
                        this.hotSort = hotSort;
                    }

                    public String getProjectImg() {
                        return projectImg;
                    }

                    public void setProjectImg(String projectImg) {
                        this.projectImg = projectImg;
                    }

                    public String getProjectListImg() {
                        return projectListImg;
                    }

                    public void setProjectListImg(String projectListImg) {
                        this.projectListImg = projectListImg;
                    }

                    public String getBuildingImg() {
                        return buildingImg;
                    }

                    public void setBuildingImg(String buildingImg) {
                        this.buildingImg = buildingImg;
                    }

                    public String getBrowseNum() {
                        return browseNum;
                    }

                    public void setBrowseNum(String browseNum) {
                        this.browseNum = browseNum;
                    }

                    public String getAwardRules() {
                        return awardRules;
                    }

                    public void setAwardRules(String awardRules) {
                        this.awardRules = awardRules;
                    }

                    public String getCommissionRules() {
                        return commissionRules;
                    }

                    public void setCommissionRules(String commissionRules) {
                        this.commissionRules = commissionRules;
                    }

                    public String getSearchName() {
                        return searchName;
                    }

                    public void setSearchName(String searchName) {
                        this.searchName = searchName;
                    }

                    public String getCollectionNum() {
                        return collectionNum;
                    }

                    public void setCollectionNum(String collectionNum) {
                        this.collectionNum = collectionNum;
                    }

                    public String getProductTypeId() {
                        return productTypeId;
                    }

                    public void setProductTypeId(String productTypeId) {
                        this.productTypeId = productTypeId;
                    }

                    public String getProductTypeSize() {
                        return productTypeSize;
                    }

                    public void setProductTypeSize(String productTypeSize) {
                        this.productTypeSize = productTypeSize;
                    }

                    public String getAmountIncentiveId() {
                        return amountIncentiveId;
                    }

                    public void setAmountIncentiveId(String amountIncentiveId) {
                        this.amountIncentiveId = amountIncentiveId;
                    }

                    public String getCityName() {
                        return cityName;
                    }

                    public void setCityName(String cityName) {
                        this.cityName = cityName;
                    }

                    public String getHaveInformation() {
                        return haveInformation;
                    }

                    public void setHaveInformation(String haveInformation) {
                        this.haveInformation = haveInformation;
                    }

                    public String getProjectTypeName() {
                        return projectTypeName;
                    }

                    public void setProjectTypeName(String projectTypeName) {
                        this.projectTypeName = projectTypeName;
                    }

                    public String getIsgroup() {
                        return isgroup;
                    }

                    public void setIsgroup(String isgroup) {
                        this.isgroup = isgroup;
                    }

                    public String getGroupNum() {
                        return groupNum;
                    }

                    public void setGroupNum(String groupNum) {
                        this.groupNum = groupNum;
                    }
                }
            }

            public static class ListMapBean {
                /**
                 * value : 2019-09-02 17:34:33
                 * key : 成交时间
                 */

                private String value;
                private String key;

                public String getValue() {
                    return value;
                }

                public void setValue(String value) {
                    this.value = value;
                }

                public String getKey() {
                    return key;
                }

                public void setKey(String key) {
                    this.key = key;
                }
            }
        }
    }
}
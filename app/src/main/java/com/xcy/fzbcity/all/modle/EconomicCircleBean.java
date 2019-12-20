package com.xcy.fzbcity.all.modle;

import java.util.List;

public class EconomicCircleBean {


    /**
     * code : 1
     * msg : 成功
     * data : {"commentList":[{"id":"d04936ef4e7e4083b18f3ad8b71c9a88","remarks":"","createBy":"","createDate":"2019-09-26 09:44:55","updateBy":"","updateDate":"","targetId":"90a2ebe69e824e7ab8a2f1a8565d0892","user":{"id":"43dea5335a1b4cb6bf15782a3be87c6a","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","company":"","office":"","post":"","loginName":"","no":"","name":"钱包","email":"","phone":"","oldPhone":"","mobile":"","loginIp":"","loginDate":"","loginFlag":"1","photo":"/fangfang/userfiles/43dea5335a1b4cb6bf15782a3be87c6a/attachment/null/2019/9/20190925093938.png","qrCode":"","oldLoginName":"","newPassword":"","sign":"","oldLoginIp":"","oldLoginDate":"","wechatOpenid":"","wechatData":"","role":"","sex":"","province":"","city":"","county":"","identity":"","brokerId":"","storeId":"","companyManage":"","storeManage":"","classics":0,"team":"","layerTeamVo":"","admin":false,"roleNames":""},"type":"2","comment":"哈哈哈","imgUrl":"","status":"","pid":""}],"giveList":[{"id":"4c51ffeb10d44a77ae9577f83876591c","remarks":"","createBy":"","createDate":"2019-09-26 09:41:19","updateBy":"","updateDate":"","targetId":"90a2ebe69e824e7ab8a2f1a8565d0892","user":{"id":"43dea5335a1b4cb6bf15782a3be87c6a","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","company":"","office":"","post":"","loginName":"","no":"","name":"钱包","email":"","phone":"","oldPhone":"","mobile":"","loginIp":"","loginDate":"","loginFlag":"1","photo":"/fangfang/userfiles/43dea5335a1b4cb6bf15782a3be87c6a/attachment/null/2019/9/20190925093938.png","qrCode":"","oldLoginName":"","newPassword":"","sign":"","oldLoginIp":"","oldLoginDate":"","wechatOpenid":"","wechatData":"","role":"","sex":"","province":"","city":"","county":"","identity":"","brokerId":"","storeId":"","companyManage":"","storeManage":"","classics":0,"team":"","layerTeamVo":"","admin":false,"roleNames":""},"type":"1","comment":"","imgUrl":"","status":"","pid":""},{"id":"f8c32d92f1ce4f7eaa1d9716a48159d3","remarks":"","createBy":"","createDate":"2019-09-25 11:17:49","updateBy":"","updateDate":"","targetId":"90a2ebe69e824e7ab8a2f1a8565d0892","user":"","type":"1","comment":"","imgUrl":"","status":"","pid":""}],"circle":{"id":"90a2ebe69e824e7ab8a2f1a8565d0892","remarks":"","createBy":"","createDate":"2019-09-25 11:09:29","updateBy":"","updateDate":"","content":"一","imgUrl":"/fangfang/userfiles/43dea5335a1b4cb6bf15782a3be87c6a/attachment/null/2019/9/20190925110928.png|/fangfang/userfiles/43dea5335a1b4cb6bf15782a3be87c6a/attachment/null/2019/9/20190925110928(1).png|/fangfang/userfiles/43dea5335a1b4cb6bf15782a3be87c6a/attachment/null/2019/9/20190925110929.png","likeNum":"2","commentNum":"","city":"长春市","createByName":"钱包","createByPhoto":"/fangfang/userfiles/43dea5335a1b4cb6bf15782a3be87c6a/attachment/null/2019/9/20190925093938.png","isLike":"","cityCompany":"c241db93cbd247f5a8aadf501806b56a"}}
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
         * commentList : [{"id":"d04936ef4e7e4083b18f3ad8b71c9a88","remarks":"","createBy":"","createDate":"2019-09-26 09:44:55","updateBy":"","updateDate":"","targetId":"90a2ebe69e824e7ab8a2f1a8565d0892","user":{"id":"43dea5335a1b4cb6bf15782a3be87c6a","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","company":"","office":"","post":"","loginName":"","no":"","name":"钱包","email":"","phone":"","oldPhone":"","mobile":"","loginIp":"","loginDate":"","loginFlag":"1","photo":"/fangfang/userfiles/43dea5335a1b4cb6bf15782a3be87c6a/attachment/null/2019/9/20190925093938.png","qrCode":"","oldLoginName":"","newPassword":"","sign":"","oldLoginIp":"","oldLoginDate":"","wechatOpenid":"","wechatData":"","role":"","sex":"","province":"","city":"","county":"","identity":"","brokerId":"","storeId":"","companyManage":"","storeManage":"","classics":0,"team":"","layerTeamVo":"","admin":false,"roleNames":""},"type":"2","comment":"哈哈哈","imgUrl":"","status":"","pid":""}]
         * giveList : [{"id":"4c51ffeb10d44a77ae9577f83876591c","remarks":"","createBy":"","createDate":"2019-09-26 09:41:19","updateBy":"","updateDate":"","targetId":"90a2ebe69e824e7ab8a2f1a8565d0892","user":{"id":"43dea5335a1b4cb6bf15782a3be87c6a","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","company":"","office":"","post":"","loginName":"","no":"","name":"钱包","email":"","phone":"","oldPhone":"","mobile":"","loginIp":"","loginDate":"","loginFlag":"1","photo":"/fangfang/userfiles/43dea5335a1b4cb6bf15782a3be87c6a/attachment/null/2019/9/20190925093938.png","qrCode":"","oldLoginName":"","newPassword":"","sign":"","oldLoginIp":"","oldLoginDate":"","wechatOpenid":"","wechatData":"","role":"","sex":"","province":"","city":"","county":"","identity":"","brokerId":"","storeId":"","companyManage":"","storeManage":"","classics":0,"team":"","layerTeamVo":"","admin":false,"roleNames":""},"type":"1","comment":"","imgUrl":"","status":"","pid":""},{"id":"f8c32d92f1ce4f7eaa1d9716a48159d3","remarks":"","createBy":"","createDate":"2019-09-25 11:17:49","updateBy":"","updateDate":"","targetId":"90a2ebe69e824e7ab8a2f1a8565d0892","user":"","type":"1","comment":"","imgUrl":"","status":"","pid":""}]
         * circle : {"id":"90a2ebe69e824e7ab8a2f1a8565d0892","remarks":"","createBy":"","createDate":"2019-09-25 11:09:29","updateBy":"","updateDate":"","content":"一","imgUrl":"/fangfang/userfiles/43dea5335a1b4cb6bf15782a3be87c6a/attachment/null/2019/9/20190925110928.png|/fangfang/userfiles/43dea5335a1b4cb6bf15782a3be87c6a/attachment/null/2019/9/20190925110928(1).png|/fangfang/userfiles/43dea5335a1b4cb6bf15782a3be87c6a/attachment/null/2019/9/20190925110929.png","likeNum":"2","commentNum":"","city":"长春市","createByName":"钱包","createByPhoto":"/fangfang/userfiles/43dea5335a1b4cb6bf15782a3be87c6a/attachment/null/2019/9/20190925093938.png","isLike":"","cityCompany":"c241db93cbd247f5a8aadf501806b56a"}
         */

        private CircleBean circle;
        private List<CommentListBean> commentList;
        private List<GiveListBean> giveList;

        public CircleBean getCircle() {
            return circle;
        }

        public void setCircle(CircleBean circle) {
            this.circle = circle;
        }

        public List<CommentListBean> getCommentList() {
            return commentList;
        }

        public void setCommentList(List<CommentListBean> commentList) {
            this.commentList = commentList;
        }

        public List<GiveListBean> getGiveList() {
            return giveList;
        }

        public void setGiveList(List<GiveListBean> giveList) {
            this.giveList = giveList;
        }

        public static class CircleBean {
            /**
             * id : 90a2ebe69e824e7ab8a2f1a8565d0892
             * remarks :
             * createBy :
             * createDate : 2019-09-25 11:09:29
             * updateBy :
             * updateDate :
             * content : 一
             * imgUrl : /fangfang/userfiles/43dea5335a1b4cb6bf15782a3be87c6a/attachment/null/2019/9/20190925110928.png|/fangfang/userfiles/43dea5335a1b4cb6bf15782a3be87c6a/attachment/null/2019/9/20190925110928(1).png|/fangfang/userfiles/43dea5335a1b4cb6bf15782a3be87c6a/attachment/null/2019/9/20190925110929.png
             * likeNum : 2
             * commentNum :
             * city : 长春市
             * createByName : 钱包
             * createByPhoto : /fangfang/userfiles/43dea5335a1b4cb6bf15782a3be87c6a/attachment/null/2019/9/20190925093938.png
             * isLike :
             * cityCompany : c241db93cbd247f5a8aadf501806b56a
             */

            private String id;
            private String remarks;
            private String createBy;
            private String createDate;
            private String updateBy;
            private String updateDate;
            private String content;
            private String imgUrl;
            private String likeNum;
            private String commentNum;
            private String city;
            private String createByName;
            private String createByPhoto;
            private String isLike;
            private String cityCompany;

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

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getImgUrl() {
                return imgUrl;
            }

            public void setImgUrl(String imgUrl) {
                this.imgUrl = imgUrl;
            }

            public String getLikeNum() {
                return likeNum;
            }

            public void setLikeNum(String likeNum) {
                this.likeNum = likeNum;
            }

            public String getCommentNum() {
                return commentNum;
            }

            public void setCommentNum(String commentNum) {
                this.commentNum = commentNum;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getCreateByName() {
                return createByName;
            }

            public void setCreateByName(String createByName) {
                this.createByName = createByName;
            }

            public String getCreateByPhoto() {
                return createByPhoto;
            }

            public void setCreateByPhoto(String createByPhoto) {
                this.createByPhoto = createByPhoto;
            }

            public String getIsLike() {
                return isLike;
            }

            public void setIsLike(String isLike) {
                this.isLike = isLike;
            }

            public String getCityCompany() {
                return cityCompany;
            }

            public void setCityCompany(String cityCompany) {
                this.cityCompany = cityCompany;
            }
        }

        public static class CommentListBean {
            /**
             * id : d04936ef4e7e4083b18f3ad8b71c9a88
             * remarks :
             * createBy :
             * createDate : 2019-09-26 09:44:55
             * updateBy :
             * updateDate :
             * targetId : 90a2ebe69e824e7ab8a2f1a8565d0892
             * user : {"id":"43dea5335a1b4cb6bf15782a3be87c6a","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","company":"","office":"","post":"","loginName":"","no":"","name":"钱包","email":"","phone":"","oldPhone":"","mobile":"","loginIp":"","loginDate":"","loginFlag":"1","photo":"/fangfang/userfiles/43dea5335a1b4cb6bf15782a3be87c6a/attachment/null/2019/9/20190925093938.png","qrCode":"","oldLoginName":"","newPassword":"","sign":"","oldLoginIp":"","oldLoginDate":"","wechatOpenid":"","wechatData":"","role":"","sex":"","province":"","city":"","county":"","identity":"","brokerId":"","storeId":"","companyManage":"","storeManage":"","classics":0,"team":"","layerTeamVo":"","admin":false,"roleNames":""}
             * type : 2
             * comment : 哈哈哈
             * imgUrl :
             * status :
             * pid :
             */

            private String id;
            private String remarks;
            private String createBy;
            private String createDate;
            private String updateBy;
            private String updateDate;
            private String targetId;
            private UserBean user;
            private String type;
            private String comment;
            private String imgUrl;
            private String status;
            private String pid;

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

            public String getTargetId() {
                return targetId;
            }

            public void setTargetId(String targetId) {
                this.targetId = targetId;
            }

            public UserBean getUser() {
                return user;
            }

            public void setUser(UserBean user) {
                this.user = user;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getComment() {
                return comment;
            }

            public void setComment(String comment) {
                this.comment = comment;
            }

            public String getImgUrl() {
                return imgUrl;
            }

            public void setImgUrl(String imgUrl) {
                this.imgUrl = imgUrl;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getPid() {
                return pid;
            }

            public void setPid(String pid) {
                this.pid = pid;
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
                 * name : 钱包
                 * email :
                 * phone :
                 * oldPhone :
                 * mobile :
                 * loginIp :
                 * loginDate :
                 * loginFlag : 1
                 * photo : /fangfang/userfiles/43dea5335a1b4cb6bf15782a3be87c6a/attachment/null/2019/9/20190925093938.png
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
                 * layerTeamVo :
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
                private String layerTeamVo;
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

                public String getLayerTeamVo() {
                    return layerTeamVo;
                }

                public void setLayerTeamVo(String layerTeamVo) {
                    this.layerTeamVo = layerTeamVo;
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

        public static class GiveListBean {
            /**
             * id : 4c51ffeb10d44a77ae9577f83876591c
             * remarks :
             * createBy :
             * createDate : 2019-09-26 09:41:19
             * updateBy :
             * updateDate :
             * targetId : 90a2ebe69e824e7ab8a2f1a8565d0892
             * user : {"id":"43dea5335a1b4cb6bf15782a3be87c6a","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","company":"","office":"","post":"","loginName":"","no":"","name":"钱包","email":"","phone":"","oldPhone":"","mobile":"","loginIp":"","loginDate":"","loginFlag":"1","photo":"/fangfang/userfiles/43dea5335a1b4cb6bf15782a3be87c6a/attachment/null/2019/9/20190925093938.png","qrCode":"","oldLoginName":"","newPassword":"","sign":"","oldLoginIp":"","oldLoginDate":"","wechatOpenid":"","wechatData":"","role":"","sex":"","province":"","city":"","county":"","identity":"","brokerId":"","storeId":"","companyManage":"","storeManage":"","classics":0,"team":"","layerTeamVo":"","admin":false,"roleNames":""}
             * type : 1
             * comment :
             * imgUrl :
             * status :
             * pid :
             */

            private String id;
            private String remarks;
            private String createBy;
            private String createDate;
            private String updateBy;
            private String updateDate;
            private String targetId;
            private UserBeanX user;
            private String type;
            private String comment;
            private String imgUrl;
            private String status;
            private String pid;

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

            public String getTargetId() {
                return targetId;
            }

            public void setTargetId(String targetId) {
                this.targetId = targetId;
            }

            public UserBeanX getUser() {
                return user;
            }

            public void setUser(UserBeanX user) {
                this.user = user;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getComment() {
                return comment;
            }

            public void setComment(String comment) {
                this.comment = comment;
            }

            public String getImgUrl() {
                return imgUrl;
            }

            public void setImgUrl(String imgUrl) {
                this.imgUrl = imgUrl;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getPid() {
                return pid;
            }

            public void setPid(String pid) {
                this.pid = pid;
            }

            public static class UserBeanX {
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
                 * name : 钱包
                 * email :
                 * phone :
                 * oldPhone :
                 * mobile :
                 * loginIp :
                 * loginDate :
                 * loginFlag : 1
                 * photo : /fangfang/userfiles/43dea5335a1b4cb6bf15782a3be87c6a/attachment/null/2019/9/20190925093938.png
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
                 * layerTeamVo :
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
                private String layerTeamVo;
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

                public String getLayerTeamVo() {
                    return layerTeamVo;
                }

                public void setLayerTeamVo(String layerTeamVo) {
                    this.layerTeamVo = layerTeamVo;
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
}
package com.xcy.fzbcity.all.modle;

public class EarnestMoneyAuditBean {

    /**
     * code : 1
     * msg : 成功
     * data : {"id":"0c5c98f96e744d23abf00c441946e552","remarks":"","createBy":"","createDate":"2019-10-25 13:17:46","updateBy":"","updateDate":"","preparationId":"bd84eaa6e6864413a78475441e297134","customerId":"60f010d718e442c881d734cb9a5a6065","fullName":"1","phone":"123887999","idNumber":"2","intentionPier":"1","apartment":"1","intentionalArea":"2","recognizeTime":"","projectId":"603e127528f747b68bee14d37b3f6239","gender":"","relation":"本人","earnestStatus":"0","earnestComment":"","applyBy":"","applyDate":"","auditBy":"","auditDate":"","earnestAuthComment":"","submitBy":{"id":"e56ae1ca8957463aa709df49fed0f015","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","company":"","office":"","post":"","loginName":"","no":"","name":"","email":"","phone":"","oldPhone":"","mobile":"","loginIp":"","loginDate":"","loginFlag":"1","photo":"/fangfang/static/common/images/flat-avatar.png","qrCode":"","oldLoginName":"","newPassword":"","sign":"","oldLoginIp":"","oldLoginDate":"","wechatOpenid":"","wechatData":"","industry":"","manageFlag":"","role":"","sex":"","province":"","city":"","county":"","identity":"","brokerId":"","storeId":"","companyManage":"","storeManage":"","classics":0,"team":"","layerTeamVo":"","teamParentId":"","commissionLevelId":"","identityStr":"","admin":false,"roleNames":""},"submitDate":"2019-10-25 13:17:46","auditSubmitBy":{"id":"3a0ee28065a84d548c96ee51849b5222","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","company":"","office":"","post":"","loginName":"","no":"","name":"","email":"","phone":"","oldPhone":"","mobile":"","loginIp":"","loginDate":"","loginFlag":"1","photo":"/fangfang/static/common/images/flat-avatar.png","qrCode":"","oldLoginName":"","newPassword":"","sign":"","oldLoginIp":"","oldLoginDate":"","wechatOpenid":"","wechatData":"","industry":"","manageFlag":"","role":"","sex":"","province":"","city":"","county":"","identity":"","brokerId":"","storeId":"","companyManage":"","storeManage":"","classics":0,"team":"","layerTeamVo":"","teamParentId":"","commissionLevelId":"","identityStr":"","admin":false,"roleNames":""},"auditSubmitDate":"2019-10-25 13:32:46","earnestCondition":""}
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
         * id : 0c5c98f96e744d23abf00c441946e552
         * remarks :
         * createBy :
         * createDate : 2019-10-25 13:17:46
         * updateBy :
         * updateDate :
         * preparationId : bd84eaa6e6864413a78475441e297134
         * customerId : 60f010d718e442c881d734cb9a5a6065
         * fullName : 1
         * phone : 123887999
         * idNumber : 2
         * intentionPier : 1
         * apartment : 1
         * intentionalArea : 2
         * recognizeTime :
         * projectId : 603e127528f747b68bee14d37b3f6239
         * gender :
         * relation : 本人
         * earnestStatus : 0
         * earnestComment :
         * applyBy :
         * applyDate :
         * auditBy :
         * auditDate :
         * earnestAuthComment :
         * submitBy : {"id":"e56ae1ca8957463aa709df49fed0f015","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","company":"","office":"","post":"","loginName":"","no":"","name":"","email":"","phone":"","oldPhone":"","mobile":"","loginIp":"","loginDate":"","loginFlag":"1","photo":"/fangfang/static/common/images/flat-avatar.png","qrCode":"","oldLoginName":"","newPassword":"","sign":"","oldLoginIp":"","oldLoginDate":"","wechatOpenid":"","wechatData":"","industry":"","manageFlag":"","role":"","sex":"","province":"","city":"","county":"","identity":"","brokerId":"","storeId":"","companyManage":"","storeManage":"","classics":0,"team":"","layerTeamVo":"","teamParentId":"","commissionLevelId":"","identityStr":"","admin":false,"roleNames":""}
         * submitDate : 2019-10-25 13:17:46
         * auditSubmitBy : {"id":"3a0ee28065a84d548c96ee51849b5222","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","company":"","office":"","post":"","loginName":"","no":"","name":"","email":"","phone":"","oldPhone":"","mobile":"","loginIp":"","loginDate":"","loginFlag":"1","photo":"/fangfang/static/common/images/flat-avatar.png","qrCode":"","oldLoginName":"","newPassword":"","sign":"","oldLoginIp":"","oldLoginDate":"","wechatOpenid":"","wechatData":"","industry":"","manageFlag":"","role":"","sex":"","province":"","city":"","county":"","identity":"","brokerId":"","storeId":"","companyManage":"","storeManage":"","classics":0,"team":"","layerTeamVo":"","teamParentId":"","commissionLevelId":"","identityStr":"","admin":false,"roleNames":""}
         * auditSubmitDate : 2019-10-25 13:32:46
         * earnestCondition :
         */

        private String id;
        private String remarks;
        private String createBy;
        private String createDate;
        private String updateBy;
        private String updateDate;
        private String preparationId;
        private String customerId;
        private String fullName;
        private String phone;
        private String idNumber;
        private String intentionPier;
        private String apartment;
        private String intentionalArea;
        private String recognizeTime;
        private String projectId;
        private String gender;
        private String relation;
        private String earnestStatus;
        private String earnestComment;
        private String applyBy;
        private String applyDate;
        private String auditBy;
        private String auditDate;
        private String earnestAuthComment;
        private SubmitByBean submitBy;
        private String submitDate;
        private AuditSubmitByBean auditSubmitBy;
        private String auditSubmitDate;
        private String earnestCondition;

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

        public String getPreparationId() {
            return preparationId;
        }

        public void setPreparationId(String preparationId) {
            this.preparationId = preparationId;
        }

        public String getCustomerId() {
            return customerId;
        }

        public void setCustomerId(String customerId) {
            this.customerId = customerId;
        }

        public String getFullName() {
            return fullName;
        }

        public void setFullName(String fullName) {
            this.fullName = fullName;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getIdNumber() {
            return idNumber;
        }

        public void setIdNumber(String idNumber) {
            this.idNumber = idNumber;
        }

        public String getIntentionPier() {
            return intentionPier;
        }

        public void setIntentionPier(String intentionPier) {
            this.intentionPier = intentionPier;
        }

        public String getApartment() {
            return apartment;
        }

        public void setApartment(String apartment) {
            this.apartment = apartment;
        }

        public String getIntentionalArea() {
            return intentionalArea;
        }

        public void setIntentionalArea(String intentionalArea) {
            this.intentionalArea = intentionalArea;
        }

        public String getRecognizeTime() {
            return recognizeTime;
        }

        public void setRecognizeTime(String recognizeTime) {
            this.recognizeTime = recognizeTime;
        }

        public String getProjectId() {
            return projectId;
        }

        public void setProjectId(String projectId) {
            this.projectId = projectId;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getRelation() {
            return relation;
        }

        public void setRelation(String relation) {
            this.relation = relation;
        }

        public String getEarnestStatus() {
            return earnestStatus;
        }

        public void setEarnestStatus(String earnestStatus) {
            this.earnestStatus = earnestStatus;
        }

        public String getEarnestComment() {
            return earnestComment;
        }

        public void setEarnestComment(String earnestComment) {
            this.earnestComment = earnestComment;
        }

        public String getApplyBy() {
            return applyBy;
        }

        public void setApplyBy(String applyBy) {
            this.applyBy = applyBy;
        }

        public String getApplyDate() {
            return applyDate;
        }

        public void setApplyDate(String applyDate) {
            this.applyDate = applyDate;
        }

        public String getAuditBy() {
            return auditBy;
        }

        public void setAuditBy(String auditBy) {
            this.auditBy = auditBy;
        }

        public String getAuditDate() {
            return auditDate;
        }

        public void setAuditDate(String auditDate) {
            this.auditDate = auditDate;
        }

        public String getEarnestAuthComment() {
            return earnestAuthComment;
        }

        public void setEarnestAuthComment(String earnestAuthComment) {
            this.earnestAuthComment = earnestAuthComment;
        }

        public SubmitByBean getSubmitBy() {
            return submitBy;
        }

        public void setSubmitBy(SubmitByBean submitBy) {
            this.submitBy = submitBy;
        }

        public String getSubmitDate() {
            return submitDate;
        }

        public void setSubmitDate(String submitDate) {
            this.submitDate = submitDate;
        }

        public AuditSubmitByBean getAuditSubmitBy() {
            return auditSubmitBy;
        }

        public void setAuditSubmitBy(AuditSubmitByBean auditSubmitBy) {
            this.auditSubmitBy = auditSubmitBy;
        }

        public String getAuditSubmitDate() {
            return auditSubmitDate;
        }

        public void setAuditSubmitDate(String auditSubmitDate) {
            this.auditSubmitDate = auditSubmitDate;
        }

        public String getEarnestCondition() {
            return earnestCondition;
        }

        public void setEarnestCondition(String earnestCondition) {
            this.earnestCondition = earnestCondition;
        }

        public static class SubmitByBean {
            /**
             * id : e56ae1ca8957463aa709df49fed0f015
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
             * industry :
             * manageFlag :
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
             * teamParentId :
             * commissionLevelId :
             * identityStr :
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
            private String industry;
            private String manageFlag;
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
            private String teamParentId;
            private String commissionLevelId;
            private String identityStr;
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

            public String getIndustry() {
                return industry;
            }

            public void setIndustry(String industry) {
                this.industry = industry;
            }

            public String getManageFlag() {
                return manageFlag;
            }

            public void setManageFlag(String manageFlag) {
                this.manageFlag = manageFlag;
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

            public String getTeamParentId() {
                return teamParentId;
            }

            public void setTeamParentId(String teamParentId) {
                this.teamParentId = teamParentId;
            }

            public String getCommissionLevelId() {
                return commissionLevelId;
            }

            public void setCommissionLevelId(String commissionLevelId) {
                this.commissionLevelId = commissionLevelId;
            }

            public String getIdentityStr() {
                return identityStr;
            }

            public void setIdentityStr(String identityStr) {
                this.identityStr = identityStr;
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

        public static class AuditSubmitByBean {
            /**
             * id : 3a0ee28065a84d548c96ee51849b5222
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
             * industry :
             * manageFlag :
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
             * teamParentId :
             * commissionLevelId :
             * identityStr :
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
            private String industry;
            private String manageFlag;
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
            private String teamParentId;
            private String commissionLevelId;
            private String identityStr;
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

            public String getIndustry() {
                return industry;
            }

            public void setIndustry(String industry) {
                this.industry = industry;
            }

            public String getManageFlag() {
                return manageFlag;
            }

            public void setManageFlag(String manageFlag) {
                this.manageFlag = manageFlag;
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

            public String getTeamParentId() {
                return teamParentId;
            }

            public void setTeamParentId(String teamParentId) {
                this.teamParentId = teamParentId;
            }

            public String getCommissionLevelId() {
                return commissionLevelId;
            }

            public void setCommissionLevelId(String commissionLevelId) {
                this.commissionLevelId = commissionLevelId;
            }

            public String getIdentityStr() {
                return identityStr;
            }

            public void setIdentityStr(String identityStr) {
                this.identityStr = identityStr;
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

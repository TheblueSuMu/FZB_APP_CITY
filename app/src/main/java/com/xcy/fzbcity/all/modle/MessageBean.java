
package com.xcy.fzbcity.all.modle;

import java.util.List;

public class MessageBean {


    /**
     * code : 1
     * msg : 成功
     * data : {"total":1,"rows":[{"id":"5ef0977f8597483ca4a66984423d5121","remarks":"","createBy":{"id":"3c37d25396a940f9b784b4c180f7db37","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","company":"","office":"","post":"","loginName":"fzbchangchun","no":"","name":"房坐标长春公司总经理","email":"","phone":"","oldPhone":"","mobile":"","loginIp":"","loginDate":"","loginFlag":"1","photo":"/fangfang/static/common/images/flat-avatar.png","qrCode":"","oldLoginName":"","newPassword":"","sign":"","oldLoginIp":"","oldLoginDate":"","wechatOpenid":"","wechatData":"","role":"","sex":"","province":"","city":"","county":"","identity":"","brokerId":"","storeId":"","companyManage":"","storeManage":"","classics":0,"team":"","layerTeamVo":"","admin":false,"roleNames":""},"createDate":"2019-04-22 22:20:29","updateBy":{"id":"3c37d25396a940f9b784b4c180f7db37","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","company":"","office":"","post":"","loginName":"fzbchangchun","no":"","name":"房坐标长春公司总经理","email":"","phone":"","oldPhone":"","mobile":"","loginIp":"","loginDate":"","loginFlag":"1","photo":"/fangfang/static/common/images/flat-avatar.png","qrCode":"","oldLoginName":"","newPassword":"","sign":"","oldLoginIp":"","oldLoginDate":"","wechatOpenid":"","wechatData":"","role":"","sex":"","province":"","city":"","county":"","identity":"","brokerId":"","storeId":"","companyManage":"","storeManage":"","classics":0,"team":"","layerTeamVo":"","admin":false,"roleNames":""},"updateDate":"2019-09-04 13:19:33","type":"2","projectType":"2","projectTypeName":"海外","title":"求购！宽城区80平米学区房","content":"因工作需要本人现急需求购80平米学区房，最好是现房，带装修","user":{"id":"","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","company":"","office":"","post":"","loginName":"","no":"","name":"","email":"","phone":"","oldPhone":"","mobile":"","loginIp":"","loginDate":"","loginFlag":"1","photo":"/fangfang/static/common/images/flat-avatar.png","qrCode":"","oldLoginName":"","newPassword":"","sign":"","oldLoginIp":"","oldLoginDate":"","wechatOpenid":"","wechatData":"","role":"","sex":"","province":"","city":"","county":"","identity":"","brokerId":"","storeId":"","companyManage":"","storeManage":"","classics":0,"team":"","layerTeamVo":"","admin":false,"roleNames":""},"sort":"1","imgPath":"","isAll":"0","province":"吉林省","city":"长春市","cityCompany":"c241db93cbd247f5a8aadf501806b56a","area":"吉林省/长春市/南关区","company":{"id":"","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","parentIds":"","name":"长春房坐标（吉海优房）","sort":30,"hasChildren":false,"area":"","code":"","type":"2","grade":"","address":"","zipCode":"","master":"","phone":"","fax":"","email":"","useable":"","primaryPerson":"","deputyPerson":"","childDeptList":"","province":"","city":"","county":"","areas":"吉林省/长春市/南关区","parentId":"0"},"onlineState":"1","projectId":"","phone":"","userName":"","brokerId":"","storeId":"","auditState":"","auditBy":{"id":"","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","company":"","office":"","post":"","loginName":"","no":"","name":"","email":"","phone":"","oldPhone":"","mobile":"","loginIp":"","loginDate":"","loginFlag":"1","photo":"/fangfang/static/common/images/flat-avatar.png","qrCode":"","oldLoginName":"","newPassword":"","sign":"","oldLoginIp":"","oldLoginDate":"","wechatOpenid":"","wechatData":"","role":"","sex":"","province":"","city":"","county":"","identity":"","brokerId":"","storeId":"","companyManage":"","storeManage":"","classics":0,"team":"","layerTeamVo":"","admin":false,"roleNames":""},"auditDate":"","reason":"","isAudit":"","preparationId":"","type2":"5","startDate":"","endDate":"","flag":"","brokerCompanyUser":""}]}
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
         * total : 1
         * rows : [{"id":"5ef0977f8597483ca4a66984423d5121","remarks":"","createBy":{"id":"3c37d25396a940f9b784b4c180f7db37","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","company":"","office":"","post":"","loginName":"fzbchangchun","no":"","name":"房坐标长春公司总经理","email":"","phone":"","oldPhone":"","mobile":"","loginIp":"","loginDate":"","loginFlag":"1","photo":"/fangfang/static/common/images/flat-avatar.png","qrCode":"","oldLoginName":"","newPassword":"","sign":"","oldLoginIp":"","oldLoginDate":"","wechatOpenid":"","wechatData":"","role":"","sex":"","province":"","city":"","county":"","identity":"","brokerId":"","storeId":"","companyManage":"","storeManage":"","classics":0,"team":"","layerTeamVo":"","admin":false,"roleNames":""},"createDate":"2019-04-22 22:20:29","updateBy":{"id":"3c37d25396a940f9b784b4c180f7db37","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","company":"","office":"","post":"","loginName":"fzbchangchun","no":"","name":"房坐标长春公司总经理","email":"","phone":"","oldPhone":"","mobile":"","loginIp":"","loginDate":"","loginFlag":"1","photo":"/fangfang/static/common/images/flat-avatar.png","qrCode":"","oldLoginName":"","newPassword":"","sign":"","oldLoginIp":"","oldLoginDate":"","wechatOpenid":"","wechatData":"","role":"","sex":"","province":"","city":"","county":"","identity":"","brokerId":"","storeId":"","companyManage":"","storeManage":"","classics":0,"team":"","layerTeamVo":"","admin":false,"roleNames":""},"updateDate":"2019-09-04 13:19:33","type":"2","projectType":"2","projectTypeName":"海外","title":"求购！宽城区80平米学区房","content":"因工作需要本人现急需求购80平米学区房，最好是现房，带装修","user":{"id":"","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","company":"","office":"","post":"","loginName":"","no":"","name":"","email":"","phone":"","oldPhone":"","mobile":"","loginIp":"","loginDate":"","loginFlag":"1","photo":"/fangfang/static/common/images/flat-avatar.png","qrCode":"","oldLoginName":"","newPassword":"","sign":"","oldLoginIp":"","oldLoginDate":"","wechatOpenid":"","wechatData":"","role":"","sex":"","province":"","city":"","county":"","identity":"","brokerId":"","storeId":"","companyManage":"","storeManage":"","classics":0,"team":"","layerTeamVo":"","admin":false,"roleNames":""},"sort":"1","imgPath":"","isAll":"0","province":"吉林省","city":"长春市","cityCompany":"c241db93cbd247f5a8aadf501806b56a","area":"吉林省/长春市/南关区","company":{"id":"","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","parentIds":"","name":"长春房坐标（吉海优房）","sort":30,"hasChildren":false,"area":"","code":"","type":"2","grade":"","address":"","zipCode":"","master":"","phone":"","fax":"","email":"","useable":"","primaryPerson":"","deputyPerson":"","childDeptList":"","province":"","city":"","county":"","areas":"吉林省/长春市/南关区","parentId":"0"},"onlineState":"1","projectId":"","phone":"","userName":"","brokerId":"","storeId":"","auditState":"","auditBy":{"id":"","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","company":"","office":"","post":"","loginName":"","no":"","name":"","email":"","phone":"","oldPhone":"","mobile":"","loginIp":"","loginDate":"","loginFlag":"1","photo":"/fangfang/static/common/images/flat-avatar.png","qrCode":"","oldLoginName":"","newPassword":"","sign":"","oldLoginIp":"","oldLoginDate":"","wechatOpenid":"","wechatData":"","role":"","sex":"","province":"","city":"","county":"","identity":"","brokerId":"","storeId":"","companyManage":"","storeManage":"","classics":0,"team":"","layerTeamVo":"","admin":false,"roleNames":""},"auditDate":"","reason":"","isAudit":"","preparationId":"","type2":"5","startDate":"","endDate":"","flag":"","brokerCompanyUser":""}]
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
             * id : 5ef0977f8597483ca4a66984423d5121
             * remarks :
             * createBy : {"id":"3c37d25396a940f9b784b4c180f7db37","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","company":"","office":"","post":"","loginName":"fzbchangchun","no":"","name":"房坐标长春公司总经理","email":"","phone":"","oldPhone":"","mobile":"","loginIp":"","loginDate":"","loginFlag":"1","photo":"/fangfang/static/common/images/flat-avatar.png","qrCode":"","oldLoginName":"","newPassword":"","sign":"","oldLoginIp":"","oldLoginDate":"","wechatOpenid":"","wechatData":"","role":"","sex":"","province":"","city":"","county":"","identity":"","brokerId":"","storeId":"","companyManage":"","storeManage":"","classics":0,"team":"","layerTeamVo":"","admin":false,"roleNames":""}
             * createDate : 2019-04-22 22:20:29
             * updateBy : {"id":"3c37d25396a940f9b784b4c180f7db37","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","company":"","office":"","post":"","loginName":"fzbchangchun","no":"","name":"房坐标长春公司总经理","email":"","phone":"","oldPhone":"","mobile":"","loginIp":"","loginDate":"","loginFlag":"1","photo":"/fangfang/static/common/images/flat-avatar.png","qrCode":"","oldLoginName":"","newPassword":"","sign":"","oldLoginIp":"","oldLoginDate":"","wechatOpenid":"","wechatData":"","role":"","sex":"","province":"","city":"","county":"","identity":"","brokerId":"","storeId":"","companyManage":"","storeManage":"","classics":0,"team":"","layerTeamVo":"","admin":false,"roleNames":""}
             * updateDate : 2019-09-04 13:19:33
             * type : 2
             * projectType : 2
             * projectTypeName : 海外
             * title : 求购！宽城区80平米学区房
             * content : 因工作需要本人现急需求购80平米学区房，最好是现房，带装修
             * user : {"id":"","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","company":"","office":"","post":"","loginName":"","no":"","name":"","email":"","phone":"","oldPhone":"","mobile":"","loginIp":"","loginDate":"","loginFlag":"1","photo":"/fangfang/static/common/images/flat-avatar.png","qrCode":"","oldLoginName":"","newPassword":"","sign":"","oldLoginIp":"","oldLoginDate":"","wechatOpenid":"","wechatData":"","role":"","sex":"","province":"","city":"","county":"","identity":"","brokerId":"","storeId":"","companyManage":"","storeManage":"","classics":0,"team":"","layerTeamVo":"","admin":false,"roleNames":""}
             * sort : 1
             * imgPath :
             * isAll : 0
             * province : 吉林省
             * city : 长春市
             * cityCompany : c241db93cbd247f5a8aadf501806b56a
             * area : 吉林省/长春市/南关区
             * company : {"id":"","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","parentIds":"","name":"长春房坐标（吉海优房）","sort":30,"hasChildren":false,"area":"","code":"","type":"2","grade":"","address":"","zipCode":"","master":"","phone":"","fax":"","email":"","useable":"","primaryPerson":"","deputyPerson":"","childDeptList":"","province":"","city":"","county":"","areas":"吉林省/长春市/南关区","parentId":"0"}
             * onlineState : 1
             * projectId :
             * phone :
             * userName :
             * brokerId :
             * storeId :
             * auditState :
             * auditBy : {"id":"","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","company":"","office":"","post":"","loginName":"","no":"","name":"","email":"","phone":"","oldPhone":"","mobile":"","loginIp":"","loginDate":"","loginFlag":"1","photo":"/fangfang/static/common/images/flat-avatar.png","qrCode":"","oldLoginName":"","newPassword":"","sign":"","oldLoginIp":"","oldLoginDate":"","wechatOpenid":"","wechatData":"","role":"","sex":"","province":"","city":"","county":"","identity":"","brokerId":"","storeId":"","companyManage":"","storeManage":"","classics":0,"team":"","layerTeamVo":"","admin":false,"roleNames":""}
             * auditDate :
             * reason :
             * isAudit :
             * preparationId :
             * type2 : 5
             * startDate :
             * endDate :
             * flag :
             * brokerCompanyUser :
             */

            private String id;
            private String remarks;
            private CreateByBean createBy;
            private String createDate;
            private UpdateByBean updateBy;
            private String updateDate;
            private String type;
            private String projectType;
            private String projectTypeName;
            private String title;
            private String content;
            private UserBean user;
            private String sort;
            private String imgPath;
            private String isAll;
            private String province;
            private String city;
            private String cityCompany;
            private String area;
            private CompanyBean company;
            private String onlineState;
            private String projectId;
            private String phone;
            private String userName;
            private String brokerId;
            private String storeId;
            private String auditState;
            private AuditByBean auditBy;
            private String auditDate;
            private String reason;
            private String isAudit;
            private String preparationId;
            private String type2;
            private String startDate;
            private String endDate;
            private String flag;
            private String brokerCompanyUser;

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

            public CreateByBean getCreateBy() {
                return createBy;
            }

            public void setCreateBy(CreateByBean createBy) {
                this.createBy = createBy;
            }

            public String getCreateDate() {
                return createDate;
            }

            public void setCreateDate(String createDate) {
                this.createDate = createDate;
            }

            public UpdateByBean getUpdateBy() {
                return updateBy;
            }

            public void setUpdateBy(UpdateByBean updateBy) {
                this.updateBy = updateBy;
            }

            public String getUpdateDate() {
                return updateDate;
            }

            public void setUpdateDate(String updateDate) {
                this.updateDate = updateDate;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getProjectType() {
                return projectType;
            }

            public void setProjectType(String projectType) {
                this.projectType = projectType;
            }

            public String getProjectTypeName() {
                return projectTypeName;
            }

            public void setProjectTypeName(String projectTypeName) {
                this.projectTypeName = projectTypeName;
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

            public UserBean getUser() {
                return user;
            }

            public void setUser(UserBean user) {
                this.user = user;
            }

            public String getSort() {
                return sort;
            }

            public void setSort(String sort) {
                this.sort = sort;
            }

            public String getImgPath() {
                return imgPath;
            }

            public void setImgPath(String imgPath) {
                this.imgPath = imgPath;
            }

            public String getIsAll() {
                return isAll;
            }

            public void setIsAll(String isAll) {
                this.isAll = isAll;
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

            public String getCityCompany() {
                return cityCompany;
            }

            public void setCityCompany(String cityCompany) {
                this.cityCompany = cityCompany;
            }

            public String getArea() {
                return area;
            }

            public void setArea(String area) {
                this.area = area;
            }

            public CompanyBean getCompany() {
                return company;
            }

            public void setCompany(CompanyBean company) {
                this.company = company;
            }

            public String getOnlineState() {
                return onlineState;
            }

            public void setOnlineState(String onlineState) {
                this.onlineState = onlineState;
            }

            public String getProjectId() {
                return projectId;
            }

            public void setProjectId(String projectId) {
                this.projectId = projectId;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public String getUserName() {
                return userName;
            }

            public void setUserName(String userName) {
                this.userName = userName;
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

            public String getAuditState() {
                return auditState;
            }

            public void setAuditState(String auditState) {
                this.auditState = auditState;
            }

            public AuditByBean getAuditBy() {
                return auditBy;
            }

            public void setAuditBy(AuditByBean auditBy) {
                this.auditBy = auditBy;
            }

            public String getAuditDate() {
                return auditDate;
            }

            public void setAuditDate(String auditDate) {
                this.auditDate = auditDate;
            }

            public String getReason() {
                return reason;
            }

            public void setReason(String reason) {
                this.reason = reason;
            }

            public String getIsAudit() {
                return isAudit;
            }

            public void setIsAudit(String isAudit) {
                this.isAudit = isAudit;
            }

            public String getPreparationId() {
                return preparationId;
            }

            public void setPreparationId(String preparationId) {
                this.preparationId = preparationId;
            }

            public String getType2() {
                return type2;
            }

            public void setType2(String type2) {
                this.type2 = type2;
            }

            public String getStartDate() {
                return startDate;
            }

            public void setStartDate(String startDate) {
                this.startDate = startDate;
            }

            public String getEndDate() {
                return endDate;
            }

            public void setEndDate(String endDate) {
                this.endDate = endDate;
            }

            public String getFlag() {
                return flag;
            }

            public void setFlag(String flag) {
                this.flag = flag;
            }

            public String getBrokerCompanyUser() {
                return brokerCompanyUser;
            }

            public void setBrokerCompanyUser(String brokerCompanyUser) {
                this.brokerCompanyUser = brokerCompanyUser;
            }

            public static class CreateByBean {
                /**
                 * id : 3c37d25396a940f9b784b4c180f7db37
                 * remarks :
                 * createBy :
                 * createDate :
                 * updateBy :
                 * updateDate :
                 * company :
                 * office :
                 * post :
                 * loginName : fzbchangchun
                 * no :
                 * name : 房坐标长春公司总经理
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

            public static class UpdateByBean {
                /**
                 * id : 3c37d25396a940f9b784b4c180f7db37
                 * remarks :
                 * createBy :
                 * createDate :
                 * updateBy :
                 * updateDate :
                 * company :
                 * office :
                 * post :
                 * loginName : fzbchangchun
                 * no :
                 * name : 房坐标长春公司总经理
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

            public static class UserBean {
                /**
                 * id :
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

            public static class CompanyBean {
                /**
                 * id :
                 * remarks :
                 * createBy :
                 * createDate :
                 * updateBy :
                 * updateDate :
                 * parentIds :
                 * name : 长春房坐标（吉海优房）
                 * sort : 30
                 * hasChildren : false
                 * area :
                 * code :
                 * type : 2
                 * grade :
                 * address :
                 * zipCode :
                 * master :
                 * phone :
                 * fax :
                 * email :
                 * useable :
                 * primaryPerson :
                 * deputyPerson :
                 * childDeptList :
                 * province :
                 * city :
                 * county :
                 * areas : 吉林省/长春市/南关区
                 * parentId : 0
                 */

                private String id;
                private String remarks;
                private String createBy;
                private String createDate;
                private String updateBy;
                private String updateDate;
                private String parentIds;
                private String name;
                private int sort;
                private boolean hasChildren;
                private String area;
                private String code;
                private String type;
                private String grade;
                private String address;
                private String zipCode;
                private String master;
                private String phone;
                private String fax;
                private String email;
                private String useable;
                private String primaryPerson;
                private String deputyPerson;
                private String childDeptList;
                private String province;
                private String city;
                private String county;
                private String areas;
                private String parentId;

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

                public String getParentIds() {
                    return parentIds;
                }

                public void setParentIds(String parentIds) {
                    this.parentIds = parentIds;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public int getSort() {
                    return sort;
                }

                public void setSort(int sort) {
                    this.sort = sort;
                }

                public boolean isHasChildren() {
                    return hasChildren;
                }

                public void setHasChildren(boolean hasChildren) {
                    this.hasChildren = hasChildren;
                }

                public String getArea() {
                    return area;
                }

                public void setArea(String area) {
                    this.area = area;
                }

                public String getCode() {
                    return code;
                }

                public void setCode(String code) {
                    this.code = code;
                }

                public String getType() {
                    return type;
                }

                public void setType(String type) {
                    this.type = type;
                }

                public String getGrade() {
                    return grade;
                }

                public void setGrade(String grade) {
                    this.grade = grade;
                }

                public String getAddress() {
                    return address;
                }

                public void setAddress(String address) {
                    this.address = address;
                }

                public String getZipCode() {
                    return zipCode;
                }

                public void setZipCode(String zipCode) {
                    this.zipCode = zipCode;
                }

                public String getMaster() {
                    return master;
                }

                public void setMaster(String master) {
                    this.master = master;
                }

                public String getPhone() {
                    return phone;
                }

                public void setPhone(String phone) {
                    this.phone = phone;
                }

                public String getFax() {
                    return fax;
                }

                public void setFax(String fax) {
                    this.fax = fax;
                }

                public String getEmail() {
                    return email;
                }

                public void setEmail(String email) {
                    this.email = email;
                }

                public String getUseable() {
                    return useable;
                }

                public void setUseable(String useable) {
                    this.useable = useable;
                }

                public String getPrimaryPerson() {
                    return primaryPerson;
                }

                public void setPrimaryPerson(String primaryPerson) {
                    this.primaryPerson = primaryPerson;
                }

                public String getDeputyPerson() {
                    return deputyPerson;
                }

                public void setDeputyPerson(String deputyPerson) {
                    this.deputyPerson = deputyPerson;
                }

                public String getChildDeptList() {
                    return childDeptList;
                }

                public void setChildDeptList(String childDeptList) {
                    this.childDeptList = childDeptList;
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

                public String getAreas() {
                    return areas;
                }

                public void setAreas(String areas) {
                    this.areas = areas;
                }

                public String getParentId() {
                    return parentId;
                }

                public void setParentId(String parentId) {
                    this.parentId = parentId;
                }
            }

            public static class AuditByBean {
                /**
                 * id :
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
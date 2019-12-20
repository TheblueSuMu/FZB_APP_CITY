package com.xcy.fzbcity.all.modle;

import java.io.Serializable;

/**
 * 创建：Sun
 * 时间：2019/7/23
 */
public class LoginUserBean {


    /**
     * code : 1
     * msg : 成功
     * data : {"id":"43dea5335a1b4cb6bf15782a3be87c6a","loginName":"cs5","name":"cs5","email":"","phone":"19904308150","mobile":"","loginIp":"127.0.0.1","loginDate":1563854002177,"loginFlag":"1","photo":"/fangfang/userfiles/3c37d25396a940f9b784b4c180f7db37/attachment//ff/server/sysUser/2019/4/1.png","qrCode":"","oldLoginName":"","newPassword":"","sign":"","oldLoginIp":"127.0.0.1","oldLoginDate":1563853881170,"sex":"男","province":"吉林省","city":"长春市","county":"宽城区","identity":"1","companyManage":{"id":"004c70f2438249039728411b50d996a7","remarks":"","createBy":"","createDate":"2019-06-16 14:30:41","updateBy":"","updateDate":"2019-06-16 22:10:28","companyCityName":"长春房坐标（吉海优房）","companyName":"唐老鸭","area":"吉林省/长春市/南关区","zoneId":"","location":"125.333415,43.822148","address":"吉林省长春市南关区人民大街辅路","companyInfo":"测试","attacheId":"","managerId":"","directorId":"","flag":"1","officeId":"c241db93cbd247f5a8aadf501806b56a","userId":"43dea5335a1b4cb6bf15782a3be87c6a","userName":"cs5","phone":"19904308150","loginName":"cs5","password":"","startTime":"","endTime":"","lprovince":"吉林省","lcity":"长春市","lregion":"南关区"},"storeManage":{"id":"f294af6abbfc4a3ea2a1c7b999c7deff","remarks":"","createBy":"","createDate":"2019-06-16 14:32:08","updateBy":"","updateDate":"2019-06-16 21:52:31","company":"","storeName":"鸭店001","area":"吉林省/长春市/宽城区","zoneId":"","location":"125.330372,43.945159","address":"吉林省长春市宽城区北人民大街辅路","storeInfo":"测试不许删","attache":"","managerId":"","directorId":"","flag":"1","lProvince":"吉林省","lCity":"长春市","lRegion":"宽城区","officeId":"c241db93cbd247f5a8aadf501806b56a","storeRise":"/fangfang/userfiles/3c37d25396a940f9b784b4c180f7db37/attachment//ff/server/ffServerStoreManage/2019/6/3.jpg","storeImg":"/fangfang/userfiles/3c37d25396a940f9b784b4c180f7db37/attachment//ff/server/ffServerStoreManage/2019/6/2.jpg","reason":"","userId":"43dea5335a1b4cb6bf15782a3be87c6a","storeNum":"0000004","userName":"cs5","phone":"19904308150","loginName":"cs5","password":"","attacheName":"专员","attachePhone":"17610596662","startTime":"","endTime":"","lprovince":"吉林省","lcity":"长春市","lregion":"宽城区"},"storeManagePhone":"17610596662","storeManageName":"专员","cityId":"c241db93cbd247f5a8aadf501806b56a","cityName":"长春房坐标（吉海优房）","team":"","codeImg":"/fangfang/static/down/appTwoCode.png","codeHref":"/fangfang/static/down/index.html","uId":"8df1397fdf4eeb2f582d416272b1e275c58abbe18559e5ac94073c452f81c5de8d85d48153555e494b79cfa0d9ae3cf1","status":"1","message":""}
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

    public static class DataBean implements Serializable {
        /**
         * id : 43dea5335a1b4cb6bf15782a3be87c6a
         * loginName : cs5
         * name : cs5
         * email :
         * phone : 19904308150
         * mobile :
         * loginIp : 127.0.0.1
         * loginDate : 1563854002177
         * loginFlag : 1
         * photo : /fangfang/userfiles/3c37d25396a940f9b784b4c180f7db37/attachment//ff/server/sysUser/2019/4/1.png
         * qrCode :
         * oldLoginName :
         * newPassword :
         * sign :
         * oldLoginIp : 127.0.0.1
         * oldLoginDate : 1563853881170
         * sex : 男
         * province : 吉林省
         * city : 长春市
         * county : 宽城区
         * identity : 1
         * companyManage : {"id":"004c70f2438249039728411b50d996a7","remarks":"","createBy":"","createDate":"2019-06-16 14:30:41","updateBy":"","updateDate":"2019-06-16 22:10:28","companyCityName":"长春房坐标（吉海优房）","companyName":"唐老鸭","area":"吉林省/长春市/南关区","zoneId":"","location":"125.333415,43.822148","address":"吉林省长春市南关区人民大街辅路","companyInfo":"测试","attacheId":"","managerId":"","directorId":"","flag":"1","officeId":"c241db93cbd247f5a8aadf501806b56a","userId":"43dea5335a1b4cb6bf15782a3be87c6a","userName":"cs5","phone":"19904308150","loginName":"cs5","password":"","startTime":"","endTime":"","lprovince":"吉林省","lcity":"长春市","lregion":"南关区"}
         * storeManage : {"id":"f294af6abbfc4a3ea2a1c7b999c7deff","remarks":"","createBy":"","createDate":"2019-06-16 14:32:08","updateBy":"","updateDate":"2019-06-16 21:52:31","company":"","storeName":"鸭店001","area":"吉林省/长春市/宽城区","zoneId":"","location":"125.330372,43.945159","address":"吉林省长春市宽城区北人民大街辅路","storeInfo":"测试不许删","attache":"","managerId":"","directorId":"","flag":"1","lProvince":"吉林省","lCity":"长春市","lRegion":"宽城区","officeId":"c241db93cbd247f5a8aadf501806b56a","storeRise":"/fangfang/userfiles/3c37d25396a940f9b784b4c180f7db37/attachment//ff/server/ffServerStoreManage/2019/6/3.jpg","storeImg":"/fangfang/userfiles/3c37d25396a940f9b784b4c180f7db37/attachment//ff/server/ffServerStoreManage/2019/6/2.jpg","reason":"","userId":"43dea5335a1b4cb6bf15782a3be87c6a","storeNum":"0000004","userName":"cs5","phone":"19904308150","loginName":"cs5","password":"","attacheName":"专员","attachePhone":"17610596662","startTime":"","endTime":"","lprovince":"吉林省","lcity":"长春市","lregion":"宽城区"}
         * storeManagePhone : 17610596662
         * storeManageName : 专员
         * cityId : c241db93cbd247f5a8aadf501806b56a
         * cityName : 长春房坐标（吉海优房）
         * team :
         * codeImg : /fangfang/static/down/appTwoCode.png
         * codeHref : /fangfang/static/down/index.html
         * uId : 8df1397fdf4eeb2f582d416272b1e275c58abbe18559e5ac94073c452f81c5de8d85d48153555e494b79cfa0d9ae3cf1
         * status : 1
         * message :
         */

        private String id;
        private String loginName;
        private String name;
        private String email;
        private String phone;
        private String mobile;
        private String loginIp;
        private long loginDate;
        private String loginFlag;
        private String photo;
        private String qrCode;
        private String oldLoginName;
        private String newPassword;
        private String sign;
        private String oldLoginIp;
        private long oldLoginDate;
        private String sex;
        private String province;
        private String city;
        private String county;
        private String identity;
        private CompanyManageBean companyManage;
        private StoreManageBean storeManage;
        private String storeManagePhone;
        private String storeManageName;
        private String cityId;
        private String cityName;
        private String team;
        private String codeImg;
        private String codeHref;
        private String uId;
        private String status;
        private String message;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getLoginName() {
            return loginName;
        }

        public void setLoginName(String loginName) {
            this.loginName = loginName;
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

        public long getLoginDate() {
            return loginDate;
        }

        public void setLoginDate(long loginDate) {
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

        public long getOldLoginDate() {
            return oldLoginDate;
        }

        public void setOldLoginDate(long oldLoginDate) {
            this.oldLoginDate = oldLoginDate;
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

        public CompanyManageBean getCompanyManage() {
            return companyManage;
        }

        public void setCompanyManage(CompanyManageBean companyManage) {
            this.companyManage = companyManage;
        }

        public StoreManageBean getStoreManage() {
            return storeManage;
        }

        public void setStoreManage(StoreManageBean storeManage) {
            this.storeManage = storeManage;
        }

        public String getStoreManagePhone() {
            return storeManagePhone;
        }

        public void setStoreManagePhone(String storeManagePhone) {
            this.storeManagePhone = storeManagePhone;
        }

        public String getStoreManageName() {
            return storeManageName;
        }

        public void setStoreManageName(String storeManageName) {
            this.storeManageName = storeManageName;
        }

        public String getCityId() {
            return cityId;
        }

        public void setCityId(String cityId) {
            this.cityId = cityId;
        }

        public String getCityName() {
            return cityName;
        }

        public void setCityName(String cityName) {
            this.cityName = cityName;
        }

        public String getTeam() {
            return team;
        }

        public void setTeam(String team) {
            this.team = team;
        }

        public String getCodeImg() {
            return codeImg;
        }

        public void setCodeImg(String codeImg) {
            this.codeImg = codeImg;
        }

        public String getCodeHref() {
            return codeHref;
        }

        public void setCodeHref(String codeHref) {
            this.codeHref = codeHref;
        }

        public String getUId() {
            return uId;
        }

        public void setUId(String uId) {
            this.uId = uId;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public static class CompanyManageBean implements Serializable {
            /**
             * id : 004c70f2438249039728411b50d996a7
             * remarks :
             * createBy :
             * createDate : 2019-06-16 14:30:41
             * updateBy :
             * updateDate : 2019-06-16 22:10:28
             * companyCityName : 长春房坐标（吉海优房）
             * companyName : 唐老鸭
             * area : 吉林省/长春市/南关区
             * zoneId :
             * location : 125.333415,43.822148
             * address : 吉林省长春市南关区人民大街辅路
             * companyInfo : 测试
             * attacheId :
             * managerId :
             * directorId :
             * flag : 1
             * officeId : c241db93cbd247f5a8aadf501806b56a
             * userId : 43dea5335a1b4cb6bf15782a3be87c6a
             * userName : cs5
             * phone : 19904308150
             * loginName : cs5
             * password :
             * startTime :
             * endTime :
             * lprovince : 吉林省
             * lcity : 长春市
             * lregion : 南关区
             */

            private String id;
            private String remarks;
            private String createBy;
            private String createDate;
            private String updateBy;
            private String updateDate;
            private String companyCityName;
            private String companyName;
            private String area;
            private String zoneId;
            private String location;
            private String address;
            private String companyInfo;
            private String attacheId;
            private String managerId;
            private String directorId;
            private String flag;
            private String officeId;
            private String userId;
            private String userName;
            private String phone;
            private String loginName;
            private String password;
            private String startTime;
            private String endTime;
            private String lprovince;
            private String lcity;
            private String lregion;

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

            public String getCompanyCityName() {
                return companyCityName;
            }

            public void setCompanyCityName(String companyCityName) {
                this.companyCityName = companyCityName;
            }

            public String getCompanyName() {
                return companyName;
            }

            public void setCompanyName(String companyName) {
                this.companyName = companyName;
            }

            public String getArea() {
                return area;
            }

            public void setArea(String area) {
                this.area = area;
            }

            public String getZoneId() {
                return zoneId;
            }

            public void setZoneId(String zoneId) {
                this.zoneId = zoneId;
            }

            public String getLocation() {
                return location;
            }

            public void setLocation(String location) {
                this.location = location;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public String getCompanyInfo() {
                return companyInfo;
            }

            public void setCompanyInfo(String companyInfo) {
                this.companyInfo = companyInfo;
            }

            public String getAttacheId() {
                return attacheId;
            }

            public void setAttacheId(String attacheId) {
                this.attacheId = attacheId;
            }

            public String getManagerId() {
                return managerId;
            }

            public void setManagerId(String managerId) {
                this.managerId = managerId;
            }

            public String getDirectorId() {
                return directorId;
            }

            public void setDirectorId(String directorId) {
                this.directorId = directorId;
            }

            public String getFlag() {
                return flag;
            }

            public void setFlag(String flag) {
                this.flag = flag;
            }

            public String getOfficeId() {
                return officeId;
            }

            public void setOfficeId(String officeId) {
                this.officeId = officeId;
            }

            public String getUserId() {
                return userId;
            }

            public void setUserId(String userId) {
                this.userId = userId;
            }

            public String getUserName() {
                return userName;
            }

            public void setUserName(String userName) {
                this.userName = userName;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public String getLoginName() {
                return loginName;
            }

            public void setLoginName(String loginName) {
                this.loginName = loginName;
            }

            public String getPassword() {
                return password;
            }

            public void setPassword(String password) {
                this.password = password;
            }

            public String getStartTime() {
                return startTime;
            }

            public void setStartTime(String startTime) {
                this.startTime = startTime;
            }

            public String getEndTime() {
                return endTime;
            }

            public void setEndTime(String endTime) {
                this.endTime = endTime;
            }

            public String getLprovince() {
                return lprovince;
            }

            public void setLprovince(String lprovince) {
                this.lprovince = lprovince;
            }

            public String getLcity() {
                return lcity;
            }

            public void setLcity(String lcity) {
                this.lcity = lcity;
            }

            public String getLregion() {
                return lregion;
            }

            public void setLregion(String lregion) {
                this.lregion = lregion;
            }
        }

        public static class StoreManageBean implements Serializable {
            /**
             * id : f294af6abbfc4a3ea2a1c7b999c7deff
             * remarks :
             * createBy :
             * createDate : 2019-06-16 14:32:08
             * updateBy :
             * updateDate : 2019-06-16 21:52:31
             * company :
             * storeName : 鸭店001
             * area : 吉林省/长春市/宽城区
             * zoneId :
             * location : 125.330372,43.945159
             * address : 吉林省长春市宽城区北人民大街辅路
             * storeInfo : 测试不许删
             * attache :
             * managerId :
             * directorId :
             * flag : 1
             * lProvince : 吉林省
             * lCity : 长春市
             * lRegion : 宽城区
             * officeId : c241db93cbd247f5a8aadf501806b56a
             * storeRise : /fangfang/userfiles/3c37d25396a940f9b784b4c180f7db37/attachment//ff/server/ffServerStoreManage/2019/6/3.jpg
             * storeImg : /fangfang/userfiles/3c37d25396a940f9b784b4c180f7db37/attachment//ff/server/ffServerStoreManage/2019/6/2.jpg
             * reason :
             * userId : 43dea5335a1b4cb6bf15782a3be87c6a
             * storeNum : 0000004
             * userName : cs5
             * phone : 19904308150
             * loginName : cs5
             * password :
             * attacheName : 专员
             * attachePhone : 17610596662
             * startTime :
             * endTime :
             * lprovince : 吉林省
             * lcity : 长春市
             * lregion : 宽城区
             */

            private String id;
            private String remarks;
            private String createBy;
            private String createDate;
            private String updateBy;
            private String updateDate;
            private String company;
            private String storeName;
            private String area;
            private String zoneId;
            private String location;
            private String address;
            private String storeInfo;
            private String attache;
            private String managerId;
            private String directorId;
            private String flag;
            private String lProvince;
            private String lCity;
            private String lRegion;
            private String officeId;
            private String storeRise;
            private String storeImg;
            private String reason;
            private String userId;
            private String storeNum;
            private String userName;
            private String phone;
            private String loginName;
            private String password;
            private String attacheName;
            private String attachePhone;
            private String startTime;
            private String endTime;
            private String lprovince;
            private String lcity;
            private String lregion;

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

            public String getStoreName() {
                return storeName;
            }

            public void setStoreName(String storeName) {
                this.storeName = storeName;
            }

            public String getArea() {
                return area;
            }

            public void setArea(String area) {
                this.area = area;
            }

            public String getZoneId() {
                return zoneId;
            }

            public void setZoneId(String zoneId) {
                this.zoneId = zoneId;
            }

            public String getLocation() {
                return location;
            }

            public void setLocation(String location) {
                this.location = location;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public String getStoreInfo() {
                return storeInfo;
            }

            public void setStoreInfo(String storeInfo) {
                this.storeInfo = storeInfo;
            }

            public String getAttache() {
                return attache;
            }

            public void setAttache(String attache) {
                this.attache = attache;
            }

            public String getManagerId() {
                return managerId;
            }

            public void setManagerId(String managerId) {
                this.managerId = managerId;
            }

            public String getDirectorId() {
                return directorId;
            }

            public void setDirectorId(String directorId) {
                this.directorId = directorId;
            }

            public String getFlag() {
                return flag;
            }

            public void setFlag(String flag) {
                this.flag = flag;
            }

            public String getLProvince() {
                return lProvince;
            }

            public void setLProvince(String lProvince) {
                this.lProvince = lProvince;
            }

            public String getLCity() {
                return lCity;
            }

            public void setLCity(String lCity) {
                this.lCity = lCity;
            }

            public String getLRegion() {
                return lRegion;
            }

            public void setLRegion(String lRegion) {
                this.lRegion = lRegion;
            }

            public String getOfficeId() {
                return officeId;
            }

            public void setOfficeId(String officeId) {
                this.officeId = officeId;
            }

            public String getStoreRise() {
                return storeRise;
            }

            public void setStoreRise(String storeRise) {
                this.storeRise = storeRise;
            }

            public String getStoreImg() {
                return storeImg;
            }

            public void setStoreImg(String storeImg) {
                this.storeImg = storeImg;
            }

            public String getReason() {
                return reason;
            }

            public void setReason(String reason) {
                this.reason = reason;
            }

            public String getUserId() {
                return userId;
            }

            public void setUserId(String userId) {
                this.userId = userId;
            }

            public String getStoreNum() {
                return storeNum;
            }

            public void setStoreNum(String storeNum) {
                this.storeNum = storeNum;
            }

            public String getUserName() {
                return userName;
            }

            public void setUserName(String userName) {
                this.userName = userName;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public String getLoginName() {
                return loginName;
            }

            public void setLoginName(String loginName) {
                this.loginName = loginName;
            }

            public String getPassword() {
                return password;
            }

            public void setPassword(String password) {
                this.password = password;
            }

            public String getAttacheName() {
                return attacheName;
            }

            public void setAttacheName(String attacheName) {
                this.attacheName = attacheName;
            }

            public String getAttachePhone() {
                return attachePhone;
            }

            public void setAttachePhone(String attachePhone) {
                this.attachePhone = attachePhone;
            }

            public String getStartTime() {
                return startTime;
            }

            public void setStartTime(String startTime) {
                this.startTime = startTime;
            }

            public String getEndTime() {
                return endTime;
            }

            public void setEndTime(String endTime) {
                this.endTime = endTime;
            }

            public String getLprovince() {
                return lprovince;
            }

            public void setLprovince(String lprovince) {
                this.lprovince = lprovince;
            }

            public String getLcity() {
                return lcity;
            }

            public void setLcity(String lcity) {
                this.lcity = lcity;
            }

            public String getLregion() {
                return lregion;
            }

            public void setLregion(String lregion) {
                this.lregion = lregion;
            }
        }
    }
}
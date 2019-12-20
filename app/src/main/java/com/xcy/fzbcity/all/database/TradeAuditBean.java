package com.xcy.fzbcity.all.database;

public class TradeAuditBean {

    /**
     * code : 1
     * msg : 成功
     * data : {"id":"239b74f2524e4581a966e0394b1b010c","remarks":"","createBy":"","createDate":"2019-10-28 20:43:45","updateBy":"","updateDate":"","preparationId":"a7ab023ac5a84eb584fc05e10868cd22","customerId":"cda0a7e9b8db4b9c95ae645867d9a1a1","roomNumber":"222栋123单元456室","apartment":"2","area":"89.63","price":"9835.67","totalPrice":"881571.1","paymentMethod":"按揭","tradeDate":"2019年10月28日","commissionId":"b69623de99874c2b9c5708d65b1eb426","procuctType":"1","gender":"女","relation":"本人","fullName":"阿狸","phone":"18708095632","idNumber":"220107198008085632","tradeStatus":"4","tradeComment":"16511","tradeAutnComment":"","flag":"0","applyBy":"","applyDate":"2019-10-29 10:35:38","auditBy":"","auditDate":"","submitBy":"","submitDate":"2019-10-28 20:43:45","auditSubmitBy":"","auditSubmitDate":"2019-10-29 10:21:04","tung":"222","several":"123","room":"456","commission":{"id":"b69623de99874c2b9c5708d65b1eb426","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","datePicker":"","startDate":"","endDate":"","mainTitle":"公寓佣金","onlineState":"","contractPercent":"","contractUnitPrice":"","contractAmount":"","isSeconds":"","secondsAmount":"","agentPercent":"","agentUnitPrice":"","agentAmount":"","leaderPercent":"","leaderUnitPrice":"","leaderAmount":"","project":"","isProjectShow":"","contractFormat":"","agentFormat":"","leaderFormat":"","currentDate":"","commissionFormat":"0.64万元/㎡+0.40万元","secondsFormat":"无秒结","teamLeaders":"","incentiveTeamIds":""},"projectId":"5a9b45bf1d5d41afb93efc670e11115d"}
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
         * id : 239b74f2524e4581a966e0394b1b010c
         * remarks :
         * createBy :
         * createDate : 2019-10-28 20:43:45
         * updateBy :
         * updateDate :
         * preparationId : a7ab023ac5a84eb584fc05e10868cd22
         * customerId : cda0a7e9b8db4b9c95ae645867d9a1a1
         * roomNumber : 222栋123单元456室
         * apartment : 2
         * area : 89.63
         * price : 9835.67
         * totalPrice : 881571.1
         * paymentMethod : 按揭
         * tradeDate : 2019年10月28日
         * commissionId : b69623de99874c2b9c5708d65b1eb426
         * procuctType : 1
         * gender : 女
         * relation : 本人
         * fullName : 阿狸
         * phone : 18708095632
         * idNumber : 220107198008085632
         * tradeStatus : 4
         * tradeComment : 16511
         * tradeAutnComment :
         * flag : 0
         * applyBy :
         * applyDate : 2019-10-29 10:35:38
         * auditBy :
         * auditDate :
         * submitBy :
         * submitDate : 2019-10-28 20:43:45
         * auditSubmitBy :
         * auditSubmitDate : 2019-10-29 10:21:04
         * tung : 222
         * several : 123
         * room : 456
         * commission : {"id":"b69623de99874c2b9c5708d65b1eb426","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","datePicker":"","startDate":"","endDate":"","mainTitle":"公寓佣金","onlineState":"","contractPercent":"","contractUnitPrice":"","contractAmount":"","isSeconds":"","secondsAmount":"","agentPercent":"","agentUnitPrice":"","agentAmount":"","leaderPercent":"","leaderUnitPrice":"","leaderAmount":"","project":"","isProjectShow":"","contractFormat":"","agentFormat":"","leaderFormat":"","currentDate":"","commissionFormat":"0.64万元/㎡+0.40万元","secondsFormat":"无秒结","teamLeaders":"","incentiveTeamIds":""}
         * projectId : 5a9b45bf1d5d41afb93efc670e11115d
         */

        private String id;
        private String remarks;
        private String createBy;
        private String createDate;
        private String updateBy;
        private String updateDate;
        private String preparationId;
        private String customerId;
        private String roomNumber;
        private String apartment;
        private String area;
        private String price;
        private String totalPrice;
        private String paymentMethod;
        private String tradeDate;
        private String commissionId;
        private String procuctType;
        private String gender;
        private String relation;
        private String fullName;
        private String phone;
        private String idNumber;
        private String tradeStatus;
        private String tradeComment;
        private String tradeAutnComment;
        private String flag;
        private String applyBy;
        private String applyDate;
        private String auditBy;
        private String auditDate;
        private String submitBy;
        private String submitDate;
        private String auditSubmitBy;
        private String auditSubmitDate;
        private String tung;
        private String several;
        private String room;
        private CommissionBean commission;
        private String projectId;

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

        public String getRoomNumber() {
            return roomNumber;
        }

        public void setRoomNumber(String roomNumber) {
            this.roomNumber = roomNumber;
        }

        public String getApartment() {
            return apartment;
        }

        public void setApartment(String apartment) {
            this.apartment = apartment;
        }

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getTotalPrice() {
            return totalPrice;
        }

        public void setTotalPrice(String totalPrice) {
            this.totalPrice = totalPrice;
        }

        public String getPaymentMethod() {
            return paymentMethod;
        }

        public void setPaymentMethod(String paymentMethod) {
            this.paymentMethod = paymentMethod;
        }

        public String getTradeDate() {
            return tradeDate;
        }

        public void setTradeDate(String tradeDate) {
            this.tradeDate = tradeDate;
        }

        public String getCommissionId() {
            return commissionId;
        }

        public void setCommissionId(String commissionId) {
            this.commissionId = commissionId;
        }

        public String getProcuctType() {
            return procuctType;
        }

        public void setProcuctType(String procuctType) {
            this.procuctType = procuctType;
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

        public String getTradeStatus() {
            return tradeStatus;
        }

        public void setTradeStatus(String tradeStatus) {
            this.tradeStatus = tradeStatus;
        }

        public String getTradeComment() {
            return tradeComment;
        }

        public void setTradeComment(String tradeComment) {
            this.tradeComment = tradeComment;
        }

        public String getTradeAutnComment() {
            return tradeAutnComment;
        }

        public void setTradeAutnComment(String tradeAutnComment) {
            this.tradeAutnComment = tradeAutnComment;
        }

        public String getFlag() {
            return flag;
        }

        public void setFlag(String flag) {
            this.flag = flag;
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

        public String getSubmitBy() {
            return submitBy;
        }

        public void setSubmitBy(String submitBy) {
            this.submitBy = submitBy;
        }

        public String getSubmitDate() {
            return submitDate;
        }

        public void setSubmitDate(String submitDate) {
            this.submitDate = submitDate;
        }

        public String getAuditSubmitBy() {
            return auditSubmitBy;
        }

        public void setAuditSubmitBy(String auditSubmitBy) {
            this.auditSubmitBy = auditSubmitBy;
        }

        public String getAuditSubmitDate() {
            return auditSubmitDate;
        }

        public void setAuditSubmitDate(String auditSubmitDate) {
            this.auditSubmitDate = auditSubmitDate;
        }

        public String getTung() {
            return tung;
        }

        public void setTung(String tung) {
            this.tung = tung;
        }

        public String getSeveral() {
            return several;
        }

        public void setSeveral(String several) {
            this.several = several;
        }

        public String getRoom() {
            return room;
        }

        public void setRoom(String room) {
            this.room = room;
        }

        public CommissionBean getCommission() {
            return commission;
        }

        public void setCommission(CommissionBean commission) {
            this.commission = commission;
        }

        public String getProjectId() {
            return projectId;
        }

        public void setProjectId(String projectId) {
            this.projectId = projectId;
        }

        public static class CommissionBean {
            /**
             * id : b69623de99874c2b9c5708d65b1eb426
             * remarks :
             * createBy :
             * createDate :
             * updateBy :
             * updateDate :
             * datePicker :
             * startDate :
             * endDate :
             * mainTitle : 公寓佣金
             * onlineState :
             * contractPercent :
             * contractUnitPrice :
             * contractAmount :
             * isSeconds :
             * secondsAmount :
             * agentPercent :
             * agentUnitPrice :
             * agentAmount :
             * leaderPercent :
             * leaderUnitPrice :
             * leaderAmount :
             * project :
             * isProjectShow :
             * contractFormat :
             * agentFormat :
             * leaderFormat :
             * currentDate :
             * commissionFormat : 0.64万元/㎡+0.40万元
             * secondsFormat : 无秒结
             * teamLeaders :
             * incentiveTeamIds :
             */

            private String id;
            private String remarks;
            private String createBy;
            private String createDate;
            private String updateBy;
            private String updateDate;
            private String datePicker;
            private String startDate;
            private String endDate;
            private String mainTitle;
            private String onlineState;
            private String contractPercent;
            private String contractUnitPrice;
            private String contractAmount;
            private String isSeconds;
            private String secondsAmount;
            private String agentPercent;
            private String agentUnitPrice;
            private String agentAmount;
            private String leaderPercent;
            private String leaderUnitPrice;
            private String leaderAmount;
            private String project;
            private String isProjectShow;
            private String contractFormat;
            private String agentFormat;
            private String leaderFormat;
            private String currentDate;
            private String commissionFormat;
            private String secondsFormat;
            private String teamLeaders;
            private String incentiveTeamIds;

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

            public String getDatePicker() {
                return datePicker;
            }

            public void setDatePicker(String datePicker) {
                this.datePicker = datePicker;
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

            public String getMainTitle() {
                return mainTitle;
            }

            public void setMainTitle(String mainTitle) {
                this.mainTitle = mainTitle;
            }

            public String getOnlineState() {
                return onlineState;
            }

            public void setOnlineState(String onlineState) {
                this.onlineState = onlineState;
            }

            public String getContractPercent() {
                return contractPercent;
            }

            public void setContractPercent(String contractPercent) {
                this.contractPercent = contractPercent;
            }

            public String getContractUnitPrice() {
                return contractUnitPrice;
            }

            public void setContractUnitPrice(String contractUnitPrice) {
                this.contractUnitPrice = contractUnitPrice;
            }

            public String getContractAmount() {
                return contractAmount;
            }

            public void setContractAmount(String contractAmount) {
                this.contractAmount = contractAmount;
            }

            public String getIsSeconds() {
                return isSeconds;
            }

            public void setIsSeconds(String isSeconds) {
                this.isSeconds = isSeconds;
            }

            public String getSecondsAmount() {
                return secondsAmount;
            }

            public void setSecondsAmount(String secondsAmount) {
                this.secondsAmount = secondsAmount;
            }

            public String getAgentPercent() {
                return agentPercent;
            }

            public void setAgentPercent(String agentPercent) {
                this.agentPercent = agentPercent;
            }

            public String getAgentUnitPrice() {
                return agentUnitPrice;
            }

            public void setAgentUnitPrice(String agentUnitPrice) {
                this.agentUnitPrice = agentUnitPrice;
            }

            public String getAgentAmount() {
                return agentAmount;
            }

            public void setAgentAmount(String agentAmount) {
                this.agentAmount = agentAmount;
            }

            public String getLeaderPercent() {
                return leaderPercent;
            }

            public void setLeaderPercent(String leaderPercent) {
                this.leaderPercent = leaderPercent;
            }

            public String getLeaderUnitPrice() {
                return leaderUnitPrice;
            }

            public void setLeaderUnitPrice(String leaderUnitPrice) {
                this.leaderUnitPrice = leaderUnitPrice;
            }

            public String getLeaderAmount() {
                return leaderAmount;
            }

            public void setLeaderAmount(String leaderAmount) {
                this.leaderAmount = leaderAmount;
            }

            public String getProject() {
                return project;
            }

            public void setProject(String project) {
                this.project = project;
            }

            public String getIsProjectShow() {
                return isProjectShow;
            }

            public void setIsProjectShow(String isProjectShow) {
                this.isProjectShow = isProjectShow;
            }

            public String getContractFormat() {
                return contractFormat;
            }

            public void setContractFormat(String contractFormat) {
                this.contractFormat = contractFormat;
            }

            public String getAgentFormat() {
                return agentFormat;
            }

            public void setAgentFormat(String agentFormat) {
                this.agentFormat = agentFormat;
            }

            public String getLeaderFormat() {
                return leaderFormat;
            }

            public void setLeaderFormat(String leaderFormat) {
                this.leaderFormat = leaderFormat;
            }

            public String getCurrentDate() {
                return currentDate;
            }

            public void setCurrentDate(String currentDate) {
                this.currentDate = currentDate;
            }

            public String getCommissionFormat() {
                return commissionFormat;
            }

            public void setCommissionFormat(String commissionFormat) {
                this.commissionFormat = commissionFormat;
            }

            public String getSecondsFormat() {
                return secondsFormat;
            }

            public void setSecondsFormat(String secondsFormat) {
                this.secondsFormat = secondsFormat;
            }

            public String getTeamLeaders() {
                return teamLeaders;
            }

            public void setTeamLeaders(String teamLeaders) {
                this.teamLeaders = teamLeaders;
            }

            public String getIncentiveTeamIds() {
                return incentiveTeamIds;
            }

            public void setIncentiveTeamIds(String incentiveTeamIds) {
                this.incentiveTeamIds = incentiveTeamIds;
            }
        }
    }
}

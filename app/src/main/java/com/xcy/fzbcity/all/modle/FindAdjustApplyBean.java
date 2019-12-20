package com.xcy.fzbcity.all.modle;

import java.util.List;

public class FindAdjustApplyBean {

    /**
     * code : 1
     * msg : 成功
     * data : {"showData":[{"value":"密码","key":"客户名称"},{"value":"12345678910","key":"客户电话"},{"value":"西港云上","key":"报备项目"}],"procuctTypeData":[{"id":"747bc38da3bb40b28ec955cb9b1f91d0","remarks":"","createBy":"","createDate":"2019-03-10 15:36:16","updateBy":"","updateDate":"2019-03-10 15:36:16","label":"住宅","value":"1","sort":"1","dictType":{"id":"d506d59af7ab4063a802650f3aba366d","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","type":"","description":"","dictValueList":[]}},{"id":"f53d7f3765284dc893d4dab2b6ba29b8","remarks":"","createBy":"","createDate":"2019-03-10 15:44:01","updateBy":"","updateDate":"2019-03-10 15:44:01","label":"公寓","value":"2","sort":"2","dictType":{"id":"d506d59af7ab4063a802650f3aba366d","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","type":"","description":"","dictValueList":[]}},{"id":"a4686a460bbc492aadcf2984cfffa125","remarks":"","createBy":"","createDate":"2019-03-10 15:44:14","updateBy":"","updateDate":"2019-03-10 15:44:14","label":"写字间","value":"3","sort":"3","dictType":{"id":"d506d59af7ab4063a802650f3aba366d","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","type":"","description":"","dictValueList":[]}},{"id":"68e8f43b25b148bea5926d686f997a0e","remarks":"","createBy":"","createDate":"2019-03-10 15:49:12","updateBy":"","updateDate":"2019-03-10 15:49:12","label":"商铺","value":"4","sort":"4","dictType":{"id":"d506d59af7ab4063a802650f3aba366d","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","type":"","description":"","dictValueList":[]}},{"id":"0c1d91e7e9af42a29c7ea4101d27bb38","remarks":"","createBy":"","createDate":"2019-03-10 15:51:21","updateBy":"","updateDate":"2019-03-10 15:51:21","label":"别墅","value":"5","sort":"5","dictType":{"id":"d506d59af7ab4063a802650f3aba366d","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","type":"","description":"","dictValueList":[]}}],"ffServerTrade":{"id":"381b806bc24b4923a2cd58a49f71d9f2","remarks":"","createBy":"","createDate":"2019-10-25 09:33:25","updateBy":"","updateDate":"","preparationId":"98247db535de498999b27a46dedd947e","customerId":"e5c5e9fc37484f079e8fe7d878ad729f","roomNumber":"1栋1单元1室","apartment":"0","area":"2","price":"3","totalPrice":"6","paymentMethod":"一次性","tradeDate":"2019年10月25日","commissionId":"b69623de99874c2b9c5708d65b1eb426","procuctType":"1","gender":"男","relation":"本人","fullName":"1","phone":"1","idNumber":"2","tradeStatus":"0","tradeComment":"","tradeAutnComment":"","flag":"0","applyBy":"","applyDate":"","auditBy":"","auditDate":"","submitBy":"","submitDate":"","auditSubmitBy":"","auditSubmitDate":"2019-10-25 09:33:25","tung":"1","several":"1","room":"1","commission":{"id":"381b806bc24b4923a2cd58a49f71d9f2","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","datePicker":"","startDate":"","endDate":"","mainTitle":"","onlineState":"","contractPercent":"","contractUnitPrice":"","contractAmount":"","isSeconds":"","secondsAmount":"","agentPercent":"","agentUnitPrice":"","agentAmount":"","leaderPercent":"","leaderUnitPrice":"","leaderAmount":"","project":"","isProjectShow":"","contractFormat":"","agentFormat":"","leaderFormat":"","currentDate":"","commissionFormat":"","secondsFormat":"无秒结","teamLeaders":"","incentiveTeamIds":""}},"isEarnestMoney":"0","comesBackData":{"customerId":"e5c5e9fc37484f079e8fe7d878ad729f","preparationId":"98247db535de498999b27a46dedd947e","economicId":"7b585b28383e485bbbfe1fd35f83d94e","projectId":"603e127528f747b68bee14d37b3f6239"},"isTrade":"1"}
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
         * showData : [{"value":"密码","key":"客户名称"},{"value":"12345678910","key":"客户电话"},{"value":"西港云上","key":"报备项目"}]
         * procuctTypeData : [{"id":"747bc38da3bb40b28ec955cb9b1f91d0","remarks":"","createBy":"","createDate":"2019-03-10 15:36:16","updateBy":"","updateDate":"2019-03-10 15:36:16","label":"住宅","value":"1","sort":"1","dictType":{"id":"d506d59af7ab4063a802650f3aba366d","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","type":"","description":"","dictValueList":[]}},{"id":"f53d7f3765284dc893d4dab2b6ba29b8","remarks":"","createBy":"","createDate":"2019-03-10 15:44:01","updateBy":"","updateDate":"2019-03-10 15:44:01","label":"公寓","value":"2","sort":"2","dictType":{"id":"d506d59af7ab4063a802650f3aba366d","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","type":"","description":"","dictValueList":[]}},{"id":"a4686a460bbc492aadcf2984cfffa125","remarks":"","createBy":"","createDate":"2019-03-10 15:44:14","updateBy":"","updateDate":"2019-03-10 15:44:14","label":"写字间","value":"3","sort":"3","dictType":{"id":"d506d59af7ab4063a802650f3aba366d","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","type":"","description":"","dictValueList":[]}},{"id":"68e8f43b25b148bea5926d686f997a0e","remarks":"","createBy":"","createDate":"2019-03-10 15:49:12","updateBy":"","updateDate":"2019-03-10 15:49:12","label":"商铺","value":"4","sort":"4","dictType":{"id":"d506d59af7ab4063a802650f3aba366d","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","type":"","description":"","dictValueList":[]}},{"id":"0c1d91e7e9af42a29c7ea4101d27bb38","remarks":"","createBy":"","createDate":"2019-03-10 15:51:21","updateBy":"","updateDate":"2019-03-10 15:51:21","label":"别墅","value":"5","sort":"5","dictType":{"id":"d506d59af7ab4063a802650f3aba366d","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","type":"","description":"","dictValueList":[]}}]
         * ffServerTrade : {"id":"381b806bc24b4923a2cd58a49f71d9f2","remarks":"","createBy":"","createDate":"2019-10-25 09:33:25","updateBy":"","updateDate":"","preparationId":"98247db535de498999b27a46dedd947e","customerId":"e5c5e9fc37484f079e8fe7d878ad729f","roomNumber":"1栋1单元1室","apartment":"0","area":"2","price":"3","totalPrice":"6","paymentMethod":"一次性","tradeDate":"2019年10月25日","commissionId":"b69623de99874c2b9c5708d65b1eb426","procuctType":"1","gender":"男","relation":"本人","fullName":"1","phone":"1","idNumber":"2","tradeStatus":"0","tradeComment":"","tradeAutnComment":"","flag":"0","applyBy":"","applyDate":"","auditBy":"","auditDate":"","submitBy":"","submitDate":"","auditSubmitBy":"","auditSubmitDate":"2019-10-25 09:33:25","tung":"1","several":"1","room":"1","commission":{"id":"381b806bc24b4923a2cd58a49f71d9f2","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","datePicker":"","startDate":"","endDate":"","mainTitle":"","onlineState":"","contractPercent":"","contractUnitPrice":"","contractAmount":"","isSeconds":"","secondsAmount":"","agentPercent":"","agentUnitPrice":"","agentAmount":"","leaderPercent":"","leaderUnitPrice":"","leaderAmount":"","project":"","isProjectShow":"","contractFormat":"","agentFormat":"","leaderFormat":"","currentDate":"","commissionFormat":"","secondsFormat":"无秒结","teamLeaders":"","incentiveTeamIds":""}}
         * isEarnestMoney : 0
         * comesBackData : {"customerId":"e5c5e9fc37484f079e8fe7d878ad729f","preparationId":"98247db535de498999b27a46dedd947e","economicId":"7b585b28383e485bbbfe1fd35f83d94e","projectId":"603e127528f747b68bee14d37b3f6239"}
         * isTrade : 1
         */

        private FfServerTradeBean ffServerTrade;
        private String isEarnestMoney;
        private ComesBackDataBean comesBackData;
        private String isTrade;
        private List<ShowDataBean> showData;
        private List<ProcuctTypeDataBean> procuctTypeData;

        public FfServerTradeBean getFfServerTrade() {
            return ffServerTrade;
        }

        public void setFfServerTrade(FfServerTradeBean ffServerTrade) {
            this.ffServerTrade = ffServerTrade;
        }

        public String getIsEarnestMoney() {
            return isEarnestMoney;
        }

        public void setIsEarnestMoney(String isEarnestMoney) {
            this.isEarnestMoney = isEarnestMoney;
        }

        public ComesBackDataBean getComesBackData() {
            return comesBackData;
        }

        public void setComesBackData(ComesBackDataBean comesBackData) {
            this.comesBackData = comesBackData;
        }

        public String getIsTrade() {
            return isTrade;
        }

        public void setIsTrade(String isTrade) {
            this.isTrade = isTrade;
        }

        public List<ShowDataBean> getShowData() {
            return showData;
        }

        public void setShowData(List<ShowDataBean> showData) {
            this.showData = showData;
        }

        public List<ProcuctTypeDataBean> getProcuctTypeData() {
            return procuctTypeData;
        }

        public void setProcuctTypeData(List<ProcuctTypeDataBean> procuctTypeData) {
            this.procuctTypeData = procuctTypeData;
        }

        public static class FfServerTradeBean {
            /**
             * id : 381b806bc24b4923a2cd58a49f71d9f2
             * remarks :
             * createBy :
             * createDate : 2019-10-25 09:33:25
             * updateBy :
             * updateDate :
             * preparationId : 98247db535de498999b27a46dedd947e
             * customerId : e5c5e9fc37484f079e8fe7d878ad729f
             * roomNumber : 1栋1单元1室
             * apartment : 0
             * area : 2
             * price : 3
             * totalPrice : 6
             * paymentMethod : 一次性
             * tradeDate : 2019年10月25日
             * commissionId : b69623de99874c2b9c5708d65b1eb426
             * procuctType : 1
             * gender : 男
             * relation : 本人
             * fullName : 1
             * phone : 1
             * idNumber : 2
             * tradeStatus : 0
             * tradeComment :
             * tradeAutnComment :
             * flag : 0
             * applyBy :
             * applyDate :
             * auditBy :
             * auditDate :
             * submitBy :
             * submitDate :
             * auditSubmitBy :
             * auditSubmitDate : 2019-10-25 09:33:25
             * tung : 1
             * several : 1
             * room : 1
             * commission : {"id":"381b806bc24b4923a2cd58a49f71d9f2","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","datePicker":"","startDate":"","endDate":"","mainTitle":"","onlineState":"","contractPercent":"","contractUnitPrice":"","contractAmount":"","isSeconds":"","secondsAmount":"","agentPercent":"","agentUnitPrice":"","agentAmount":"","leaderPercent":"","leaderUnitPrice":"","leaderAmount":"","project":"","isProjectShow":"","contractFormat":"","agentFormat":"","leaderFormat":"","currentDate":"","commissionFormat":"","secondsFormat":"无秒结","teamLeaders":"","incentiveTeamIds":""}
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

            public static class CommissionBean {
                /**
                 * id : 381b806bc24b4923a2cd58a49f71d9f2
                 * remarks :
                 * createBy :
                 * createDate :
                 * updateBy :
                 * updateDate :
                 * datePicker :
                 * startDate :
                 * endDate :
                 * mainTitle :
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
                 * commissionFormat :
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

        public static class ComesBackDataBean {
            /**
             * customerId : e5c5e9fc37484f079e8fe7d878ad729f
             * preparationId : 98247db535de498999b27a46dedd947e
             * economicId : 7b585b28383e485bbbfe1fd35f83d94e
             * projectId : 603e127528f747b68bee14d37b3f6239
             */

            private String customerId;
            private String preparationId;
            private String economicId;
            private String projectId;

            public String getCustomerId() {
                return customerId;
            }

            public void setCustomerId(String customerId) {
                this.customerId = customerId;
            }

            public String getPreparationId() {
                return preparationId;
            }

            public void setPreparationId(String preparationId) {
                this.preparationId = preparationId;
            }

            public String getEconomicId() {
                return economicId;
            }

            public void setEconomicId(String economicId) {
                this.economicId = economicId;
            }

            public String getProjectId() {
                return projectId;
            }

            public void setProjectId(String projectId) {
                this.projectId = projectId;
            }
        }

        public static class ShowDataBean {
            /**
             * value : 密码
             * key : 客户名称
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

        public static class ProcuctTypeDataBean {
            /**
             * id : 747bc38da3bb40b28ec955cb9b1f91d0
             * remarks :
             * createBy :
             * createDate : 2019-03-10 15:36:16
             * updateBy :
             * updateDate : 2019-03-10 15:36:16
             * label : 住宅
             * value : 1
             * sort : 1
             * dictType : {"id":"d506d59af7ab4063a802650f3aba366d","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","type":"","description":"","dictValueList":[]}
             */

            private String id;
            private String remarks;
            private String createBy;
            private String createDate;
            private String updateBy;
            private String updateDate;
            private String label;
            private String value;
            private String sort;
            private DictTypeBean dictType;

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

            public String getLabel() {
                return label;
            }

            public void setLabel(String label) {
                this.label = label;
            }

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }

            public String getSort() {
                return sort;
            }

            public void setSort(String sort) {
                this.sort = sort;
            }

            public DictTypeBean getDictType() {
                return dictType;
            }

            public void setDictType(DictTypeBean dictType) {
                this.dictType = dictType;
            }

            public static class DictTypeBean {
                /**
                 * id : d506d59af7ab4063a802650f3aba366d
                 * remarks :
                 * createBy :
                 * createDate :
                 * updateBy :
                 * updateDate :
                 * type :
                 * description :
                 * dictValueList : []
                 */

                private String id;
                private String remarks;
                private String createBy;
                private String createDate;
                private String updateBy;
                private String updateDate;
                private String type;
                private String description;
                private List<?> dictValueList;

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

                public String getType() {
                    return type;
                }

                public void setType(String type) {
                    this.type = type;
                }

                public String getDescription() {
                    return description;
                }

                public void setDescription(String description) {
                    this.description = description;
                }

                public List<?> getDictValueList() {
                    return dictValueList;
                }

                public void setDictValueList(List<?> dictValueList) {
                    this.dictValueList = dictValueList;
                }
            }
        }
    }
}

package com.xcy.fzbcity.all.modle;

import com.mcxtzhang.indexlib.IndexBar.bean.BaseIndexPinyinBean;

import java.util.List;

public class ClientBean extends BaseIndexPinyinBean {
    /**
     * code : 1
     * msg : 成功
     * data : [{"id":"2d8d29593f384ec686c48cb2869b3492","remarks":"","createBy":"","createDate":"2020-03-06 14:25:40","updateBy":"","updateDate":"2020-03-06 14:25:40","filterField":"","customerName":"Allen","letter":"A","user":"","contacts1":"本人手机","contactsPhone1":"18302210990","contacts2":"","contactsPhone2":"","contacts3":"","contactsPhone3":"","customerImg":"/fangfang/static/common/images/flat-avatar.png","gender":"","name":"Allen","searchName":"","way":"0","companyId":"c241db93cbd247f5a8aadf501806b56a","companyName":"吉海旅居（长春）","reportNum":"0","tradeNum":"0","customerClass":"","buyingCity":"","intentionType":"","intentionArea":"","priceMin":"","priceMax":"","intentionApartment":"","intentionFitment":"","customerCity":"","customerOccupation":"","customerFocus":"","intentionalBuilding":"","paymentMethod":"","hasDecision":"","resistance":"","objective":"","token":"","openId":"","selectedBroker":""}]
     */

    private String code;
    private String msg;
    private List<DataBean> data;

    @Override
    public String getTarget() {
        return null;
    }

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }


    public static class DataBean {
        /**
         * id : 2d8d29593f384ec686c48cb2869b3492
         * remarks :
         * createBy :
         * createDate : 2020-03-06 14:25:40
         * updateBy :
         * updateDate : 2020-03-06 14:25:40
         * filterField :
         * customerName : Allen
         * letter : A
         * user :
         * contacts1 : 本人手机
         * contactsPhone1 : 18302210990
         * contacts2 :
         * contactsPhone2 :
         * contacts3 :
         * contactsPhone3 :
         * customerImg : /fangfang/static/common/images/flat-avatar.png
         * gender :
         * name : Allen
         * searchName :
         * way : 0
         * companyId : c241db93cbd247f5a8aadf501806b56a
         * companyName : 吉海旅居（长春）
         * reportNum : 0
         * tradeNum : 0
         * customerClass :
         * buyingCity :
         * intentionType :
         * intentionArea :
         * priceMin :
         * priceMax :
         * intentionApartment :
         * intentionFitment :
         * customerCity :
         * customerOccupation :
         * customerFocus :
         * intentionalBuilding :
         * paymentMethod :
         * hasDecision :
         * resistance :
         * objective :
         * token :
         * openId :
         * selectedBroker :
         */

        private String id;
        private String remarks;
        private String createBy;
        private String createDate;
        private String updateBy;
        private String updateDate;
        private String filterField;
        private String customerName;
        private String letter;
        private String user;
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
        private String way;
        private String companyId;
        private String companyName;
        private String reportNum;
        private String tradeNum;
        private String customerClass;
        private String buyingCity;
        private String intentionType;
        private String intentionArea;
        private String priceMin;
        private String priceMax;
        private String intentionApartment;
        private String intentionFitment;
        private String customerCity;
        private String customerOccupation;
        private String customerFocus;
        private String intentionalBuilding;
        private String paymentMethod;
        private String hasDecision;
        private String resistance;
        private String objective;
        private String token;
        private String openId;
        private String selectedBroker;

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

        public String getFilterField() {
            return filterField;
        }

        public void setFilterField(String filterField) {
            this.filterField = filterField;
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

        public String getUser() {
            return user;
        }

        public void setUser(String user) {
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

        public String getWay() {
            return way;
        }

        public void setWay(String way) {
            this.way = way;
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

        public String getReportNum() {
            return reportNum;
        }

        public void setReportNum(String reportNum) {
            this.reportNum = reportNum;
        }

        public String getTradeNum() {
            return tradeNum;
        }

        public void setTradeNum(String tradeNum) {
            this.tradeNum = tradeNum;
        }

        public String getCustomerClass() {
            return customerClass;
        }

        public void setCustomerClass(String customerClass) {
            this.customerClass = customerClass;
        }

        public String getBuyingCity() {
            return buyingCity;
        }

        public void setBuyingCity(String buyingCity) {
            this.buyingCity = buyingCity;
        }

        public String getIntentionType() {
            return intentionType;
        }

        public void setIntentionType(String intentionType) {
            this.intentionType = intentionType;
        }

        public String getIntentionArea() {
            return intentionArea;
        }

        public void setIntentionArea(String intentionArea) {
            this.intentionArea = intentionArea;
        }

        public String getPriceMin() {
            return priceMin;
        }

        public void setPriceMin(String priceMin) {
            this.priceMin = priceMin;
        }

        public String getPriceMax() {
            return priceMax;
        }

        public void setPriceMax(String priceMax) {
            this.priceMax = priceMax;
        }

        public String getIntentionApartment() {
            return intentionApartment;
        }

        public void setIntentionApartment(String intentionApartment) {
            this.intentionApartment = intentionApartment;
        }

        public String getIntentionFitment() {
            return intentionFitment;
        }

        public void setIntentionFitment(String intentionFitment) {
            this.intentionFitment = intentionFitment;
        }

        public String getCustomerCity() {
            return customerCity;
        }

        public void setCustomerCity(String customerCity) {
            this.customerCity = customerCity;
        }

        public String getCustomerOccupation() {
            return customerOccupation;
        }

        public void setCustomerOccupation(String customerOccupation) {
            this.customerOccupation = customerOccupation;
        }

        public String getCustomerFocus() {
            return customerFocus;
        }

        public void setCustomerFocus(String customerFocus) {
            this.customerFocus = customerFocus;
        }

        public String getIntentionalBuilding() {
            return intentionalBuilding;
        }

        public void setIntentionalBuilding(String intentionalBuilding) {
            this.intentionalBuilding = intentionalBuilding;
        }

        public String getPaymentMethod() {
            return paymentMethod;
        }

        public void setPaymentMethod(String paymentMethod) {
            this.paymentMethod = paymentMethod;
        }

        public String getHasDecision() {
            return hasDecision;
        }

        public void setHasDecision(String hasDecision) {
            this.hasDecision = hasDecision;
        }

        public String getResistance() {
            return resistance;
        }

        public void setResistance(String resistance) {
            this.resistance = resistance;
        }

        public String getObjective() {
            return objective;
        }

        public void setObjective(String objective) {
            this.objective = objective;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getOpenId() {
            return openId;
        }

        public void setOpenId(String openId) {
            this.openId = openId;
        }

        public String getSelectedBroker() {
            return selectedBroker;
        }

        public void setSelectedBroker(String selectedBroker) {
            this.selectedBroker = selectedBroker;
        }
    }
}
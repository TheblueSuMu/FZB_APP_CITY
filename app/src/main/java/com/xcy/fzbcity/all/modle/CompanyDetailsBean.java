
package com.xcy.fzbcity.all.modle;

import java.util.List;

public class CompanyDetailsBean {


    /**
     * code : 1
     * msg : 成功
     * data : {"storeMoneyData":{"totalAmount":"0","notAmount":"0","alreadyAmount":"0"},"gsonOption":{"yAxis":{"axisLabel":{"show":true,"textStyle":{"color":"#a8b6cc"}},"splitLine":{"lineStyle":{"color":"#eeeeee"},"show":true},"type":"value"},"xAxis":{"axisLabel":{"textStyle":{"color":"#a8b6cc"}},"data":["26","27","28","29","30","01","02"],"type":"category","boundaryGap":false},"series":[{"areaStyle":{"color":"#e6eefe"},"data":[0,0,0,0,0,0,0],"itemStyle":{"color":"#3b82d2"},"type":"line"}]},"storeInfo":{"id":"","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","user":"","companyId":"3db11016e35c4af796f34057cee3d29c","companyName":"测试公司","companyAddress":"","storeNum":"","storeName":"互联网金融","storeId":"efd95b4f26b64f1883cc4f96643eb873","storeIdCode":"0000889","isShopowner":"0","shopownerName":"","shopownerPhone":"","loginName":"","status":"1","state":"","cmState":"","area":"江苏省/常州市/天宁区","address":"江南皮革厂","storeRise":"","storeImg":"","location":"","comLocation":"117.386421,39.170700","attacheId":"badb8e2d394d4841a8376437471970a5","attacheName":"文章","attachePhone":"13241693578","attacheIdentity":"5","isMy":"1","reason":"","agentNum":"0","search":"","beforeDate":"","afterDate":""},"storeDataStatistics":{"agentNum":0,"accessingNumber":0,"tradeNumber":0,"reportNumber":0,"isIslandNumber":0,"earnestMoneyNumber":0,"landingNumber":0,"InvalidNum":0}}
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
         * storeMoneyData : {"totalAmount":"0","notAmount":"0","alreadyAmount":"0"}
         * gsonOption : {"yAxis":{"axisLabel":{"show":true,"textStyle":{"color":"#a8b6cc"}},"splitLine":{"lineStyle":{"color":"#eeeeee"},"show":true},"type":"value"},"xAxis":{"axisLabel":{"textStyle":{"color":"#a8b6cc"}},"data":["26","27","28","29","30","01","02"],"type":"category","boundaryGap":false},"series":[{"areaStyle":{"color":"#e6eefe"},"data":[0,0,0,0,0,0,0],"itemStyle":{"color":"#3b82d2"},"type":"line"}]}
         * storeInfo : {"id":"","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","user":"","companyId":"3db11016e35c4af796f34057cee3d29c","companyName":"测试公司","companyAddress":"","storeNum":"","storeName":"互联网金融","storeId":"efd95b4f26b64f1883cc4f96643eb873","storeIdCode":"0000889","isShopowner":"0","shopownerName":"","shopownerPhone":"","loginName":"","status":"1","state":"","cmState":"","area":"江苏省/常州市/天宁区","address":"江南皮革厂","storeRise":"","storeImg":"","location":"","comLocation":"117.386421,39.170700","attacheId":"badb8e2d394d4841a8376437471970a5","attacheName":"文章","attachePhone":"13241693578","attacheIdentity":"5","isMy":"1","reason":"","agentNum":"0","search":"","beforeDate":"","afterDate":""}
         * storeDataStatistics : {"agentNum":0,"accessingNumber":0,"tradeNumber":0,"reportNumber":0,"isIslandNumber":0,"earnestMoneyNumber":0,"landingNumber":0,"InvalidNum":0}
         */

        private StoreMoneyDataBean storeMoneyData;
        private GsonOptionBean gsonOption;
        private StoreInfoBean storeInfo;
        private StoreDataStatisticsBean storeDataStatistics;

        public StoreMoneyDataBean getStoreMoneyData() {
            return storeMoneyData;
        }

        public void setStoreMoneyData(StoreMoneyDataBean storeMoneyData) {
            this.storeMoneyData = storeMoneyData;
        }

        public GsonOptionBean getGsonOption() {
            return gsonOption;
        }

        public void setGsonOption(GsonOptionBean gsonOption) {
            this.gsonOption = gsonOption;
        }

        public StoreInfoBean getStoreInfo() {
            return storeInfo;
        }

        public void setStoreInfo(StoreInfoBean storeInfo) {
            this.storeInfo = storeInfo;
        }

        public StoreDataStatisticsBean getStoreDataStatistics() {
            return storeDataStatistics;
        }

        public void setStoreDataStatistics(StoreDataStatisticsBean storeDataStatistics) {
            this.storeDataStatistics = storeDataStatistics;
        }

        public static class StoreMoneyDataBean {
            /**
             * totalAmount : 0
             * notAmount : 0
             * alreadyAmount : 0
             */

            private String totalAmount;
            private String notAmount;
            private String alreadyAmount;

            public String getTotalAmount() {
                return totalAmount;
            }

            public void setTotalAmount(String totalAmount) {
                this.totalAmount = totalAmount;
            }

            public String getNotAmount() {
                return notAmount;
            }

            public void setNotAmount(String notAmount) {
                this.notAmount = notAmount;
            }

            public String getAlreadyAmount() {
                return alreadyAmount;
            }

            public void setAlreadyAmount(String alreadyAmount) {
                this.alreadyAmount = alreadyAmount;
            }
        }

        public static class GsonOptionBean {
            /**
             * yAxis : {"axisLabel":{"show":true,"textStyle":{"color":"#a8b6cc"}},"splitLine":{"lineStyle":{"color":"#eeeeee"},"show":true},"type":"value"}
             * xAxis : {"axisLabel":{"textStyle":{"color":"#a8b6cc"}},"data":["26","27","28","29","30","01","02"],"type":"category","boundaryGap":false}
             * series : [{"areaStyle":{"color":"#e6eefe"},"data":[0,0,0,0,0,0,0],"itemStyle":{"color":"#3b82d2"},"type":"line"}]
             */

            private YAxisBean yAxis;
            private XAxisBean xAxis;
            private List<SeriesBean> series;

            public YAxisBean getYAxis() {
                return yAxis;
            }

            public void setYAxis(YAxisBean yAxis) {
                this.yAxis = yAxis;
            }

            public XAxisBean getXAxis() {
                return xAxis;
            }

            public void setXAxis(XAxisBean xAxis) {
                this.xAxis = xAxis;
            }

            public List<SeriesBean> getSeries() {
                return series;
            }

            public void setSeries(List<SeriesBean> series) {
                this.series = series;
            }

            public static class YAxisBean {
                /**
                 * axisLabel : {"show":true,"textStyle":{"color":"#a8b6cc"}}
                 * splitLine : {"lineStyle":{"color":"#eeeeee"},"show":true}
                 * type : value
                 */

                private AxisLabelBean axisLabel;
                private SplitLineBean splitLine;
                private String type;

                public AxisLabelBean getAxisLabel() {
                    return axisLabel;
                }

                public void setAxisLabel(AxisLabelBean axisLabel) {
                    this.axisLabel = axisLabel;
                }

                public SplitLineBean getSplitLine() {
                    return splitLine;
                }

                public void setSplitLine(SplitLineBean splitLine) {
                    this.splitLine = splitLine;
                }

                public String getType() {
                    return type;
                }

                public void setType(String type) {
                    this.type = type;
                }

                public static class AxisLabelBean {
                    /**
                     * show : true
                     * textStyle : {"color":"#a8b6cc"}
                     */

                    private boolean show;
                    private TextStyleBean textStyle;

                    public boolean isShow() {
                        return show;
                    }

                    public void setShow(boolean show) {
                        this.show = show;
                    }

                    public TextStyleBean getTextStyle() {
                        return textStyle;
                    }

                    public void setTextStyle(TextStyleBean textStyle) {
                        this.textStyle = textStyle;
                    }

                    public static class TextStyleBean {
                        /**
                         * color : #a8b6cc
                         */

                        private String color;

                        public String getColor() {
                            return color;
                        }

                        public void setColor(String color) {
                            this.color = color;
                        }
                    }
                }

                public static class SplitLineBean {
                    /**
                     * lineStyle : {"color":"#eeeeee"}
                     * show : true
                     */

                    private LineStyleBean lineStyle;
                    private boolean show;

                    public LineStyleBean getLineStyle() {
                        return lineStyle;
                    }

                    public void setLineStyle(LineStyleBean lineStyle) {
                        this.lineStyle = lineStyle;
                    }

                    public boolean isShow() {
                        return show;
                    }

                    public void setShow(boolean show) {
                        this.show = show;
                    }

                    public static class LineStyleBean {
                        /**
                         * color : #eeeeee
                         */

                        private String color;

                        public String getColor() {
                            return color;
                        }

                        public void setColor(String color) {
                            this.color = color;
                        }
                    }
                }
            }

            public static class XAxisBean {
                /**
                 * axisLabel : {"textStyle":{"color":"#a8b6cc"}}
                 * data : ["26","27","28","29","30","01","02"]
                 * type : category
                 * boundaryGap : false
                 */

                private AxisLabelBeanX axisLabel;
                private String type;
                private boolean boundaryGap;
                private List<String> data;

                public AxisLabelBeanX getAxisLabel() {
                    return axisLabel;
                }

                public void setAxisLabel(AxisLabelBeanX axisLabel) {
                    this.axisLabel = axisLabel;
                }

                public String getType() {
                    return type;
                }

                public void setType(String type) {
                    this.type = type;
                }

                public boolean isBoundaryGap() {
                    return boundaryGap;
                }

                public void setBoundaryGap(boolean boundaryGap) {
                    this.boundaryGap = boundaryGap;
                }

                public List<String> getData() {
                    return data;
                }

                public void setData(List<String> data) {
                    this.data = data;
                }

                public static class AxisLabelBeanX {
                    /**
                     * textStyle : {"color":"#a8b6cc"}
                     */

                    private TextStyleBeanX textStyle;

                    public TextStyleBeanX getTextStyle() {
                        return textStyle;
                    }

                    public void setTextStyle(TextStyleBeanX textStyle) {
                        this.textStyle = textStyle;
                    }

                    public static class TextStyleBeanX {
                        /**
                         * color : #a8b6cc
                         */

                        private String color;

                        public String getColor() {
                            return color;
                        }

                        public void setColor(String color) {
                            this.color = color;
                        }
                    }
                }
            }

            public static class SeriesBean {
                /**
                 * areaStyle : {"color":"#e6eefe"}
                 * data : [0,0,0,0,0,0,0]
                 * itemStyle : {"color":"#3b82d2"}
                 * type : line
                 */

                private AreaStyleBean areaStyle;
                private ItemStyleBean itemStyle;
                private String type;
                private List<Integer> data;

                public AreaStyleBean getAreaStyle() {
                    return areaStyle;
                }

                public void setAreaStyle(AreaStyleBean areaStyle) {
                    this.areaStyle = areaStyle;
                }

                public ItemStyleBean getItemStyle() {
                    return itemStyle;
                }

                public void setItemStyle(ItemStyleBean itemStyle) {
                    this.itemStyle = itemStyle;
                }

                public String getType() {
                    return type;
                }

                public void setType(String type) {
                    this.type = type;
                }

                public List<Integer> getData() {
                    return data;
                }

                public void setData(List<Integer> data) {
                    this.data = data;
                }

                public static class AreaStyleBean {
                    /**
                     * color : #e6eefe
                     */

                    private String color;

                    public String getColor() {
                        return color;
                    }

                    public void setColor(String color) {
                        this.color = color;
                    }
                }

                public static class ItemStyleBean {
                    /**
                     * color : #3b82d2
                     */

                    private String color;

                    public String getColor() {
                        return color;
                    }

                    public void setColor(String color) {
                        this.color = color;
                    }
                }
            }
        }

        public static class StoreInfoBean {
            /**
             * id :
             * remarks :
             * createBy :
             * createDate :
             * updateBy :
             * updateDate :
             * user :
             * companyId : 3db11016e35c4af796f34057cee3d29c
             * companyName : 测试公司
             * companyAddress :
             * storeNum :
             * storeName : 互联网金融
             * storeId : efd95b4f26b64f1883cc4f96643eb873
             * storeIdCode : 0000889
             * isShopowner : 0
             * shopownerName :
             * shopownerPhone :
             * loginName :
             * status : 1
             * state :
             * cmState :
             * area : 江苏省/常州市/天宁区
             * address : 江南皮革厂
             * storeRise :
             * storeImg :
             * location :
             * comLocation : 117.386421,39.170700
             * attacheId : badb8e2d394d4841a8376437471970a5
             * attacheName : 文章
             * attachePhone : 13241693578
             * attacheIdentity : 5
             * isMy : 1
             * reason :
             * agentNum : 0
             * search :
             * beforeDate :
             * afterDate :
             */

            private String id;
            private String remarks;
            private String createBy;
            private String createDate;
            private String updateBy;
            private String updateDate;
            private String user;
            private String companyId;
            private String companyName;
            private String companyAddress;
            private String storeNum;
            private String storeName;
            private String storeId;
            private String storeIdCode;
            private String isShopowner;
            private String shopownerName;
            private String shopownerPhone;
            private String loginName;
            private String status;
            private String state;
            private String cmState;
            private String area;
            private String address;
            private String storeRise;
            private String storeImg;
            private String location;
            private String comLocation;
            private String attacheId;
            private String attacheName;
            private String attachePhone;
            private String attacheIdentity;
            private String isMy;
            private String reason;
            private String agentNum;
            private String search;
            private String beforeDate;
            private String afterDate;

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

            public String getCompanyAddress() {
                return companyAddress;
            }

            public void setCompanyAddress(String companyAddress) {
                this.companyAddress = companyAddress;
            }

            public String getStoreNum() {
                return storeNum;
            }

            public void setStoreNum(String storeNum) {
                this.storeNum = storeNum;
            }

            public String getStoreName() {
                return storeName;
            }

            public void setStoreName(String storeName) {
                this.storeName = storeName;
            }

            public String getStoreId() {
                return storeId;
            }

            public void setStoreId(String storeId) {
                this.storeId = storeId;
            }

            public String getStoreIdCode() {
                return storeIdCode;
            }

            public void setStoreIdCode(String storeIdCode) {
                this.storeIdCode = storeIdCode;
            }

            public String getIsShopowner() {
                return isShopowner;
            }

            public void setIsShopowner(String isShopowner) {
                this.isShopowner = isShopowner;
            }

            public String getShopownerName() {
                return shopownerName;
            }

            public void setShopownerName(String shopownerName) {
                this.shopownerName = shopownerName;
            }

            public String getShopownerPhone() {
                return shopownerPhone;
            }

            public void setShopownerPhone(String shopownerPhone) {
                this.shopownerPhone = shopownerPhone;
            }

            public String getLoginName() {
                return loginName;
            }

            public void setLoginName(String loginName) {
                this.loginName = loginName;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getState() {
                return state;
            }

            public void setState(String state) {
                this.state = state;
            }

            public String getCmState() {
                return cmState;
            }

            public void setCmState(String cmState) {
                this.cmState = cmState;
            }

            public String getArea() {
                return area;
            }

            public void setArea(String area) {
                this.area = area;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
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

            public String getLocation() {
                return location;
            }

            public void setLocation(String location) {
                this.location = location;
            }

            public String getComLocation() {
                return comLocation;
            }

            public void setComLocation(String comLocation) {
                this.comLocation = comLocation;
            }

            public String getAttacheId() {
                return attacheId;
            }

            public void setAttacheId(String attacheId) {
                this.attacheId = attacheId;
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

            public String getAttacheIdentity() {
                return attacheIdentity;
            }

            public void setAttacheIdentity(String attacheIdentity) {
                this.attacheIdentity = attacheIdentity;
            }

            public String getIsMy() {
                return isMy;
            }

            public void setIsMy(String isMy) {
                this.isMy = isMy;
            }

            public String getReason() {
                return reason;
            }

            public void setReason(String reason) {
                this.reason = reason;
            }

            public String getAgentNum() {
                return agentNum;
            }

            public void setAgentNum(String agentNum) {
                this.agentNum = agentNum;
            }

            public String getSearch() {
                return search;
            }

            public void setSearch(String search) {
                this.search = search;
            }

            public String getBeforeDate() {
                return beforeDate;
            }

            public void setBeforeDate(String beforeDate) {
                this.beforeDate = beforeDate;
            }

            public String getAfterDate() {
                return afterDate;
            }

            public void setAfterDate(String afterDate) {
                this.afterDate = afterDate;
            }
        }

        public static class StoreDataStatisticsBean {
            /**
             * agentNum : 0
             * accessingNumber : 0
             * tradeNumber : 0
             * reportNumber : 0
             * isIslandNumber : 0
             * earnestMoneyNumber : 0
             * landingNumber : 0
             * InvalidNum : 0
             */

            private int agentNum;
            private int accessingNumber;
            private int tradeNumber;
            private int reportNumber;
            private int isIslandNumber;
            private int earnestMoneyNumber;
            private int landingNumber;
            private int InvalidNum;

            public int getAgentNum() {
                return agentNum;
            }

            public void setAgentNum(int agentNum) {
                this.agentNum = agentNum;
            }

            public int getAccessingNumber() {
                return accessingNumber;
            }

            public void setAccessingNumber(int accessingNumber) {
                this.accessingNumber = accessingNumber;
            }

            public int getTradeNumber() {
                return tradeNumber;
            }

            public void setTradeNumber(int tradeNumber) {
                this.tradeNumber = tradeNumber;
            }

            public int getReportNumber() {
                return reportNumber;
            }

            public void setReportNumber(int reportNumber) {
                this.reportNumber = reportNumber;
            }

            public int getIsIslandNumber() {
                return isIslandNumber;
            }

            public void setIsIslandNumber(int isIslandNumber) {
                this.isIslandNumber = isIslandNumber;
            }

            public int getEarnestMoneyNumber() {
                return earnestMoneyNumber;
            }

            public void setEarnestMoneyNumber(int earnestMoneyNumber) {
                this.earnestMoneyNumber = earnestMoneyNumber;
            }

            public int getLandingNumber() {
                return landingNumber;
            }

            public void setLandingNumber(int landingNumber) {
                this.landingNumber = landingNumber;
            }

            public int getInvalidNum() {
                return InvalidNum;
            }

            public void setInvalidNum(int InvalidNum) {
                this.InvalidNum = InvalidNum;
            }
        }
    }
}
package com.xcy.fzbcity.all.database;

import java.util.List;

public class BrokerBean {


    /**
     * code : 1
     * msg : 成功
     * data : {"agentDataStatistics":{"accessingNumber":0,"tradeNumber":0,"reportNumber":0,"isIslandNumber":0,"earnestMoneyNumber":0,"landingNumber":0,"InvalidNum":0},"agentInfo":{"id":"","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","user":"","agentId":"1bc96d8b974841a7901a7e066015aa51","loginName":"zhazong","agentName":"査总","agentPhone":"15043439666","identity":"3","identityName":"","companyId":"8e381e93aad04945b96046ad6410230f","companyName":"苹果","storeId":"d90a9b327ce04e2689b09d39716015eb","storeName":"苹果2店","status":"1","reason":"","search":""},"gsonOption":{"yAxis":{"axisLabel":{"show":true,"textStyle":{"color":"#a8b6cc"}},"splitLine":{"lineStyle":{"color":"#eeeeee"},"show":true},"type":"value"},"xAxis":{"axisLabel":{"textStyle":{"color":"#a8b6cc"}},"data":["05","06","07","08","09","10"],"type":"category","boundaryGap":false},"series":[{"areaStyle":{"color":"#e6eefe"},"data":[0,0,0,0,0,0],"itemStyle":{"color":"#3b82d2"},"type":"line"}]},"agentMoneyData":{"totalAmount":"0","notAmount":"0","alreadyAmount":"0"}}
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
         * agentDataStatistics : {"accessingNumber":0,"tradeNumber":0,"reportNumber":0,"isIslandNumber":0,"earnestMoneyNumber":0,"landingNumber":0,"InvalidNum":0}
         * agentInfo : {"id":"","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","user":"","agentId":"1bc96d8b974841a7901a7e066015aa51","loginName":"zhazong","agentName":"査总","agentPhone":"15043439666","identity":"3","identityName":"","companyId":"8e381e93aad04945b96046ad6410230f","companyName":"苹果","storeId":"d90a9b327ce04e2689b09d39716015eb","storeName":"苹果2店","status":"1","reason":"","search":""}
         * gsonOption : {"yAxis":{"axisLabel":{"show":true,"textStyle":{"color":"#a8b6cc"}},"splitLine":{"lineStyle":{"color":"#eeeeee"},"show":true},"type":"value"},"xAxis":{"axisLabel":{"textStyle":{"color":"#a8b6cc"}},"data":["05","06","07","08","09","10"],"type":"category","boundaryGap":false},"series":[{"areaStyle":{"color":"#e6eefe"},"data":[0,0,0,0,0,0],"itemStyle":{"color":"#3b82d2"},"type":"line"}]}
         * agentMoneyData : {"totalAmount":"0","notAmount":"0","alreadyAmount":"0"}
         */

        private AgentDataStatisticsBean agentDataStatistics;
        private AgentInfoBean agentInfo;
        private GsonOptionBean gsonOption;
        private AgentMoneyDataBean agentMoneyData;

        public AgentDataStatisticsBean getAgentDataStatistics() {
            return agentDataStatistics;
        }

        public void setAgentDataStatistics(AgentDataStatisticsBean agentDataStatistics) {
            this.agentDataStatistics = agentDataStatistics;
        }

        public AgentInfoBean getAgentInfo() {
            return agentInfo;
        }

        public void setAgentInfo(AgentInfoBean agentInfo) {
            this.agentInfo = agentInfo;
        }

        public GsonOptionBean getGsonOption() {
            return gsonOption;
        }

        public void setGsonOption(GsonOptionBean gsonOption) {
            this.gsonOption = gsonOption;
        }

        public AgentMoneyDataBean getAgentMoneyData() {
            return agentMoneyData;
        }

        public void setAgentMoneyData(AgentMoneyDataBean agentMoneyData) {
            this.agentMoneyData = agentMoneyData;
        }

        public static class AgentDataStatisticsBean {
            /**
             * accessingNumber : 0
             * tradeNumber : 0
             * reportNumber : 0
             * isIslandNumber : 0
             * earnestMoneyNumber : 0
             * landingNumber : 0
             * InvalidNum : 0
             */

            private int accessingNumber;
            private int tradeNumber;
            private int reportNumber;
            private int isIslandNumber;
            private int earnestMoneyNumber;
            private int landingNumber;
            private int InvalidNum;

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

        public static class AgentInfoBean {
            /**
             * id :
             * remarks :
             * createBy :
             * createDate :
             * updateBy :
             * updateDate :
             * user :
             * agentId : 1bc96d8b974841a7901a7e066015aa51
             * loginName : zhazong
             * agentName : 査总
             * agentPhone : 15043439666
             * identity : 3
             * identityName :
             * companyId : 8e381e93aad04945b96046ad6410230f
             * companyName : 苹果
             * storeId : d90a9b327ce04e2689b09d39716015eb
             * storeName : 苹果2店
             * status : 1
             * reason :
             * search :
             */

            private String id;
            private String remarks;
            private String createBy;
            private String createDate;
            private String updateBy;
            private String updateDate;
            private String user;
            private String agentId;
            private String loginName;
            private String agentName;
            private String agentPhone;
            private String identity;
            private String identityName;
            private String companyId;
            private String companyName;
            private String storeId;
            private String storeName;
            private String status;
            private String reason;
            private String search;

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

            public String getAgentId() {
                return agentId;
            }

            public void setAgentId(String agentId) {
                this.agentId = agentId;
            }

            public String getLoginName() {
                return loginName;
            }

            public void setLoginName(String loginName) {
                this.loginName = loginName;
            }

            public String getAgentName() {
                return agentName;
            }

            public void setAgentName(String agentName) {
                this.agentName = agentName;
            }

            public String getAgentPhone() {
                return agentPhone;
            }

            public void setAgentPhone(String agentPhone) {
                this.agentPhone = agentPhone;
            }

            public String getIdentity() {
                return identity;
            }

            public void setIdentity(String identity) {
                this.identity = identity;
            }

            public String getIdentityName() {
                return identityName;
            }

            public void setIdentityName(String identityName) {
                this.identityName = identityName;
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

            public String getStoreId() {
                return storeId;
            }

            public void setStoreId(String storeId) {
                this.storeId = storeId;
            }

            public String getStoreName() {
                return storeName;
            }

            public void setStoreName(String storeName) {
                this.storeName = storeName;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getReason() {
                return reason;
            }

            public void setReason(String reason) {
                this.reason = reason;
            }

            public String getSearch() {
                return search;
            }

            public void setSearch(String search) {
                this.search = search;
            }
        }

        public static class GsonOptionBean {
            /**
             * yAxis : {"axisLabel":{"show":true,"textStyle":{"color":"#a8b6cc"}},"splitLine":{"lineStyle":{"color":"#eeeeee"},"show":true},"type":"value"}
             * xAxis : {"axisLabel":{"textStyle":{"color":"#a8b6cc"}},"data":["05","06","07","08","09","10"],"type":"category","boundaryGap":false}
             * series : [{"areaStyle":{"color":"#e6eefe"},"data":[0,0,0,0,0,0],"itemStyle":{"color":"#3b82d2"},"type":"line"}]
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
                 * data : ["05","06","07","08","09","10"]
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
                 * data : [0,0,0,0,0,0]
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

        public static class AgentMoneyDataBean {
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
    }
}
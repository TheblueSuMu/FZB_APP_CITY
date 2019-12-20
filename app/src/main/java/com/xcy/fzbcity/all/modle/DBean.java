package com.xcy.fzbcity.all.modle;

import java.util.List;

public class DBean {

    /**
     * code : 1
     * msg : 成功
     * data : {"isAmount":1,"peopleCount":9,"dataMap":{"accessingNumber":0,"tradeNumber":1,"reportNumber":15,"isIslandNumber":1,"earnestMoneyNumber":1,"landingNumber":0,"InvalidNum":6},"storeCount":4,"gsonOption":{"yAxis":{"axisLabel":{"show":true,"textStyle":{"color":"#a8b6cc"}},"splitLine":{"lineStyle":{"color":"#eeeeee"},"show":true},"type":"value"},"xAxis":{"axisLabel":{"textStyle":{"color":"#a8b6cc"}},"data":["04","05","06","07","08","09"],"type":"category","boundaryGap":false},"series":[{"areaStyle":{"color":"#e6eefe"},"data":[2,1,1,1,0,1],"itemStyle":{"color":"#3b82d2"},"type":"line"}]}}
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
         * isAmount : 1
         * peopleCount : 9
         * dataMap : {"accessingNumber":0,"tradeNumber":1,"reportNumber":15,"isIslandNumber":1,"earnestMoneyNumber":1,"landingNumber":0,"InvalidNum":6}
         * storeCount : 4
         * gsonOption : {"yAxis":{"axisLabel":{"show":true,"textStyle":{"color":"#a8b6cc"}},"splitLine":{"lineStyle":{"color":"#eeeeee"},"show":true},"type":"value"},"xAxis":{"axisLabel":{"textStyle":{"color":"#a8b6cc"}},"data":["04","05","06","07","08","09"],"type":"category","boundaryGap":false},"series":[{"areaStyle":{"color":"#e6eefe"},"data":[2,1,1,1,0,1],"itemStyle":{"color":"#3b82d2"},"type":"line"}]}
         */

        private int isAmount;
        private int peopleCount;
        private DataMapBean dataMap;
        private int storeCount;
        private GsonOptionBean gsonOption;

        public int getIsAmount() {
            return isAmount;
        }

        public void setIsAmount(int isAmount) {
            this.isAmount = isAmount;
        }

        public int getPeopleCount() {
            return peopleCount;
        }

        public void setPeopleCount(int peopleCount) {
            this.peopleCount = peopleCount;
        }

        public DataMapBean getDataMap() {
            return dataMap;
        }

        public void setDataMap(DataMapBean dataMap) {
            this.dataMap = dataMap;
        }

        public int getStoreCount() {
            return storeCount;
        }

        public void setStoreCount(int storeCount) {
            this.storeCount = storeCount;
        }

        public GsonOptionBean getGsonOption() {
            return gsonOption;
        }

        public void setGsonOption(GsonOptionBean gsonOption) {
            this.gsonOption = gsonOption;
        }

        public static class DataMapBean {
            /**
             * accessingNumber : 0
             * tradeNumber : 1
             * reportNumber : 15
             * isIslandNumber : 1
             * earnestMoneyNumber : 1
             * landingNumber : 0
             * InvalidNum : 6
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

        public static class GsonOptionBean {
            /**
             * yAxis : {"axisLabel":{"show":true,"textStyle":{"color":"#a8b6cc"}},"splitLine":{"lineStyle":{"color":"#eeeeee"},"show":true},"type":"value"}
             * xAxis : {"axisLabel":{"textStyle":{"color":"#a8b6cc"}},"data":["04","05","06","07","08","09"],"type":"category","boundaryGap":false}
             * series : [{"areaStyle":{"color":"#e6eefe"},"data":[2,1,1,1,0,1],"itemStyle":{"color":"#3b82d2"},"type":"line"}]
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
                 * data : ["04","05","06","07","08","09"]
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
                 * data : [2,1,1,1,0,1]
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
    }
}
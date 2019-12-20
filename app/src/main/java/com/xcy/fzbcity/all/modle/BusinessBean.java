package com.xcy.fzbcity.all.modle;

import java.util.List;

public class BusinessBean {

    /**
     * code : 1
     * msg : 成功
     * data : {"yAxis":{"axisLabel":{"show":true,"textStyle":{"color":"#a8b6cc"}},"splitLine":{"lineStyle":{"color":"#eeeeee"},"show":true},"type":"value"},"xAxis":{"axisLabel":{"textStyle":{"color":"#a8b6cc"}},"data":["00","01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24"],"type":"category","boundaryGap":false},"series":[{"areaStyle":{"color":"#e6eefe"},"data":[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],"itemStyle":{"color":"#3b82d2"},"type":"line"}]}
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
         * yAxis : {"axisLabel":{"show":true,"textStyle":{"color":"#a8b6cc"}},"splitLine":{"lineStyle":{"color":"#eeeeee"},"show":true},"type":"value"}
         * xAxis : {"axisLabel":{"textStyle":{"color":"#a8b6cc"}},"data":["00","01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24"],"type":"category","boundaryGap":false}
         * series : [{"areaStyle":{"color":"#e6eefe"},"data":[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],"itemStyle":{"color":"#3b82d2"},"type":"line"}]
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
             * data : ["00","01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24"]
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
             * data : [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]
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
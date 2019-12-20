package com.xcy.fzbcity.all.modle;

import java.util.List;

public class MoreBean {

    /**
     * code : 1
     * msg : 成功
     * data : [{"isbar":0,"value":[{"isshow":"0","value":[{"value":"西港云上","key":"项目名称"},{"value":"永久产权,托管,公寓,海景房","key":"产品特色"},{"value":"柬埔寨西哈努克友谊三区（索卡海滩旁）","key":"项目地址"},{"value":"长春市南关区南部新城华康街与锦湖大路交汇保合大厦","key":"售楼处地址"},{"value":"兰天使鸿业投资集团有限公司","key":"开发商名称"},{"value":"兰天使鸿业投资集团有限公司","key":"开发商品牌"},{"value":"无","key":"销售状态"}],"key":"基本信息"}],"key":"基本信息"},{"isbar":1,"value":[{"isshow":"1","value":[{"value":"40-90m²","key":"面积区间"},{"value":"40万/套","key":"参考总价"},{"value":"永久","key":"产品年限"},{"value":"酒店式公寓","key":"建筑类型"}],"key":"公寓"}],"key":"产品类型"},{"isbar":0,"value":[{"isshow":"0","value":[{"value":"17,700㎡","key":"总占地面积"},{"value":"200,000㎡","key":"总建筑面积"},{"value":"10.0","key":"容积率"},{"value":"30%","key":"绿化率"},{"value":"2栋","key":"规划总栋数"},{"value":"2,200户","key":"规划总户数"},{"value":"个","key":"规划停车位"},{"value":"斯维登物管公司","key":"物业公司"}],"key":"基本信息"}],"key":"项目规划"}]
     */

    private String code;
    private String msg;
    private List<DataBean> data;

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
         * isbar : 0
         * value : [{"isshow":"0","value":[{"value":"西港云上","key":"项目名称"},{"value":"永久产权,托管,公寓,海景房","key":"产品特色"},{"value":"柬埔寨西哈努克友谊三区（索卡海滩旁）","key":"项目地址"},{"value":"长春市南关区南部新城华康街与锦湖大路交汇保合大厦","key":"售楼处地址"},{"value":"兰天使鸿业投资集团有限公司","key":"开发商名称"},{"value":"兰天使鸿业投资集团有限公司","key":"开发商品牌"},{"value":"无","key":"销售状态"}],"key":"基本信息"}]
         * key : 基本信息
         */

        private int isbar;
        private String key;
        private List<ValueBeanX> value;

        public int getIsbar() {
            return isbar;
        }

        public void setIsbar(int isbar) {
            this.isbar = isbar;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public List<ValueBeanX> getValue() {
            return value;
        }

        public void setValue(List<ValueBeanX> value) {
            this.value = value;
        }

        public static class ValueBeanX {
            /**
             * isshow : 0
             * value : [{"value":"西港云上","key":"项目名称"},{"value":"永久产权,托管,公寓,海景房","key":"产品特色"},{"value":"柬埔寨西哈努克友谊三区（索卡海滩旁）","key":"项目地址"},{"value":"长春市南关区南部新城华康街与锦湖大路交汇保合大厦","key":"售楼处地址"},{"value":"兰天使鸿业投资集团有限公司","key":"开发商名称"},{"value":"兰天使鸿业投资集团有限公司","key":"开发商品牌"},{"value":"无","key":"销售状态"}]
             * key : 基本信息
             */

            private String isshow;
            private String key;
            private List<ValueBean> value;

            public String getIsshow() {
                return isshow;
            }

            public void setIsshow(String isshow) {
                this.isshow = isshow;
            }

            public String getKey() {
                return key;
            }

            public void setKey(String key) {
                this.key = key;
            }

            public List<ValueBean> getValue() {
                return value;
            }

            public void setValue(List<ValueBean> value) {
                this.value = value;
            }

            public static class ValueBean {
                /**
                 * value : 西港云上
                 * key : 项目名称
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
        }
    }
}
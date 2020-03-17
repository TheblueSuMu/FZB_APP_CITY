package com.xcy.fzbcity.all.adapter;

import java.util.List;

public class ProjectsBean {


    /**
     * code : 1
     * msg : 成功
     * data : [{"name":"吉林省","latLon":"125.356612,43.869874","num":3,"list":[{"name":"长春市","latLon":"125.356612,43.869874","num":3,"list":[{"name":"朝阳区","latLon":"125.323842,43.844289","num":1,"list":[{"name":"碧桂","latLon":"125.323842,43.844289","num":1,"list":[],"id":"","productUnitPrice":"18000","monetaryUnit":"元/㎡","projectType":"1","projectImg":"/fangfang/userfiles/3c37d25396a940f9b784b4c180f7db37/attachment//ff/server/ffServerProjectManage/2020/1/1578042685327.jpg","area":"长春","productFeature":"民宿,0首付,核心地段,非得,第一个,测试,jjjjj,精装,地铁沿线,配套丰富,河景房,现房,户型方正,首付分期,别墅,海景房","isgroup":"0","groupNum":"0","browseNum":"178","forwardingAmount":"0","reportAmount":"7","collectionNum":"1","areaInterval":"无秒结","shopownerName":"","shopownerPhone":"","status":"","address":""}],"id":"","productUnitPrice":"","monetaryUnit":"","projectType":"","projectImg":"","area":"","productFeature":"","isgroup":"","groupNum":"","browseNum":"","forwardingAmount":"","reportAmount":"","collectionNum":"","areaInterval":"","shopownerName":"","shopownerPhone":"","status":"","address":""},{"name":"二道区","latLon":"125.366612,43.879874","num":1,"list":[{"name":"佳源金月湾","latLon":"125.366612,43.879874","num":1,"list":[],"id":"","productUnitPrice":"14000","monetaryUnit":"元起/㎡","projectType":"1","projectImg":"/fangfang/userfiles/3c37d25396a940f9b784b4c180f7db37/attachment//ff/server/ffServerHousesPhoto/2019/9/1573524426722.jpg","area":"海南","productFeature":"配套丰富,拎包入住,海景房","isgroup":"0","groupNum":"0","browseNum":"600","forwardingAmount":"1","reportAmount":"80","collectionNum":"3","areaInterval":"1000元","shopownerName":"","shopownerPhone":"","status":"","address":""}],"id":"","productUnitPrice":"","monetaryUnit":"","projectType":"","projectImg":"","area":"","productFeature":"","isgroup":"","groupNum":"","browseNum":"","forwardingAmount":"","reportAmount":"","collectionNum":"","areaInterval":"","shopownerName":"","shopownerPhone":"","status":"","address":""},{"name":"南关区","latLon":"125.356612,43.869874","num":1,"list":[{"name":"航天首府","latLon":"125.356612,43.869874","num":1,"list":[],"id":"","productUnitPrice":"19000","monetaryUnit":"元起/㎡","projectType":"1","projectImg":"/fangfang/userfiles/3c37d25396a940f9b784b4c180f7db37/attachment//ff/server/ffServerHousesPhoto/2019/9/1573524426714.jpg","area":"固安","productFeature":"","isgroup":"0","groupNum":"0","browseNum":"1492","forwardingAmount":"10","reportAmount":"79","collectionNum":"9","areaInterval":"无秒结","shopownerName":"","shopownerPhone":"","status":"","address":""}],"id":"","productUnitPrice":"","monetaryUnit":"","projectType":"","projectImg":"","area":"","productFeature":"","isgroup":"","groupNum":"","browseNum":"","forwardingAmount":"","reportAmount":"","collectionNum":"","areaInterval":"","shopownerName":"","shopownerPhone":"","status":"","address":""}],"id":"","productUnitPrice":"","monetaryUnit":"","projectType":"","projectImg":"","area":"","productFeature":"","isgroup":"","groupNum":"","browseNum":"","forwardingAmount":"","reportAmount":"","collectionNum":"","areaInterval":"","shopownerName":"","shopownerPhone":"","status":"","address":""}],"id":"","productUnitPrice":"","monetaryUnit":"","projectType":"","projectImg":"","area":"","productFeature":"","isgroup":"","groupNum":"","browseNum":"","forwardingAmount":"","reportAmount":"","collectionNum":"","areaInterval":"","shopownerName":"","shopownerPhone":"","status":"","address":""}]
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
         * name : 吉林省
         * latLon : 125.356612,43.869874
         * num : 3
         * list : [{"name":"长春市","latLon":"125.356612,43.869874","num":3,"list":[{"name":"朝阳区","latLon":"125.323842,43.844289","num":1,"list":[{"name":"碧桂","latLon":"125.323842,43.844289","num":1,"list":[],"id":"","productUnitPrice":"18000","monetaryUnit":"元/㎡","projectType":"1","projectImg":"/fangfang/userfiles/3c37d25396a940f9b784b4c180f7db37/attachment//ff/server/ffServerProjectManage/2020/1/1578042685327.jpg","area":"长春","productFeature":"民宿,0首付,核心地段,非得,第一个,测试,jjjjj,精装,地铁沿线,配套丰富,河景房,现房,户型方正,首付分期,别墅,海景房","isgroup":"0","groupNum":"0","browseNum":"178","forwardingAmount":"0","reportAmount":"7","collectionNum":"1","areaInterval":"无秒结","shopownerName":"","shopownerPhone":"","status":"","address":""}],"id":"","productUnitPrice":"","monetaryUnit":"","projectType":"","projectImg":"","area":"","productFeature":"","isgroup":"","groupNum":"","browseNum":"","forwardingAmount":"","reportAmount":"","collectionNum":"","areaInterval":"","shopownerName":"","shopownerPhone":"","status":"","address":""},{"name":"二道区","latLon":"125.366612,43.879874","num":1,"list":[{"name":"佳源金月湾","latLon":"125.366612,43.879874","num":1,"list":[],"id":"","productUnitPrice":"14000","monetaryUnit":"元起/㎡","projectType":"1","projectImg":"/fangfang/userfiles/3c37d25396a940f9b784b4c180f7db37/attachment//ff/server/ffServerHousesPhoto/2019/9/1573524426722.jpg","area":"海南","productFeature":"配套丰富,拎包入住,海景房","isgroup":"0","groupNum":"0","browseNum":"600","forwardingAmount":"1","reportAmount":"80","collectionNum":"3","areaInterval":"1000元","shopownerName":"","shopownerPhone":"","status":"","address":""}],"id":"","productUnitPrice":"","monetaryUnit":"","projectType":"","projectImg":"","area":"","productFeature":"","isgroup":"","groupNum":"","browseNum":"","forwardingAmount":"","reportAmount":"","collectionNum":"","areaInterval":"","shopownerName":"","shopownerPhone":"","status":"","address":""},{"name":"南关区","latLon":"125.356612,43.869874","num":1,"list":[{"name":"航天首府","latLon":"125.356612,43.869874","num":1,"list":[],"id":"","productUnitPrice":"19000","monetaryUnit":"元起/㎡","projectType":"1","projectImg":"/fangfang/userfiles/3c37d25396a940f9b784b4c180f7db37/attachment//ff/server/ffServerHousesPhoto/2019/9/1573524426714.jpg","area":"固安","productFeature":"","isgroup":"0","groupNum":"0","browseNum":"1492","forwardingAmount":"10","reportAmount":"79","collectionNum":"9","areaInterval":"无秒结","shopownerName":"","shopownerPhone":"","status":"","address":""}],"id":"","productUnitPrice":"","monetaryUnit":"","projectType":"","projectImg":"","area":"","productFeature":"","isgroup":"","groupNum":"","browseNum":"","forwardingAmount":"","reportAmount":"","collectionNum":"","areaInterval":"","shopownerName":"","shopownerPhone":"","status":"","address":""}],"id":"","productUnitPrice":"","monetaryUnit":"","projectType":"","projectImg":"","area":"","productFeature":"","isgroup":"","groupNum":"","browseNum":"","forwardingAmount":"","reportAmount":"","collectionNum":"","areaInterval":"","shopownerName":"","shopownerPhone":"","status":"","address":""}]
         * id :
         * productUnitPrice :
         * monetaryUnit :
         * projectType :
         * projectImg :
         * area :
         * productFeature :
         * isgroup :
         * groupNum :
         * browseNum :
         * forwardingAmount :
         * reportAmount :
         * collectionNum :
         * areaInterval :
         * shopownerName :
         * shopownerPhone :
         * status :
         * address :
         */

        private String name;
        private String latLon;
        private int num;
        private String id;
        private String productUnitPrice;
        private String monetaryUnit;
        private String projectType;
        private String projectImg;
        private String area;
        private String productFeature;
        private String isgroup;
        private String groupNum;
        private String browseNum;
        private String forwardingAmount;
        private String reportAmount;
        private String collectionNum;
        private String areaInterval;
        private String shopownerName;
        private String shopownerPhone;
        private String status;
        private String address;
        private List<ListBeanXX> list;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getLatLon() {
            return latLon;
        }

        public void setLatLon(String latLon) {
            this.latLon = latLon;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getProductUnitPrice() {
            return productUnitPrice;
        }

        public void setProductUnitPrice(String productUnitPrice) {
            this.productUnitPrice = productUnitPrice;
        }

        public String getMonetaryUnit() {
            return monetaryUnit;
        }

        public void setMonetaryUnit(String monetaryUnit) {
            this.monetaryUnit = monetaryUnit;
        }

        public String getProjectType() {
            return projectType;
        }

        public void setProjectType(String projectType) {
            this.projectType = projectType;
        }

        public String getProjectImg() {
            return projectImg;
        }

        public void setProjectImg(String projectImg) {
            this.projectImg = projectImg;
        }

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public String getProductFeature() {
            return productFeature;
        }

        public void setProductFeature(String productFeature) {
            this.productFeature = productFeature;
        }

        public String getIsgroup() {
            return isgroup;
        }

        public void setIsgroup(String isgroup) {
            this.isgroup = isgroup;
        }

        public String getGroupNum() {
            return groupNum;
        }

        public void setGroupNum(String groupNum) {
            this.groupNum = groupNum;
        }

        public String getBrowseNum() {
            return browseNum;
        }

        public void setBrowseNum(String browseNum) {
            this.browseNum = browseNum;
        }

        public String getForwardingAmount() {
            return forwardingAmount;
        }

        public void setForwardingAmount(String forwardingAmount) {
            this.forwardingAmount = forwardingAmount;
        }

        public String getReportAmount() {
            return reportAmount;
        }

        public void setReportAmount(String reportAmount) {
            this.reportAmount = reportAmount;
        }

        public String getCollectionNum() {
            return collectionNum;
        }

        public void setCollectionNum(String collectionNum) {
            this.collectionNum = collectionNum;
        }

        public String getAreaInterval() {
            return areaInterval;
        }

        public void setAreaInterval(String areaInterval) {
            this.areaInterval = areaInterval;
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

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public List<ListBeanXX> getList() {
            return list;
        }

        public void setList(List<ListBeanXX> list) {
            this.list = list;
        }

        public static class ListBeanXX {
            /**
             * name : 长春市
             * latLon : 125.356612,43.869874
             * num : 3
             * list : [{"name":"朝阳区","latLon":"125.323842,43.844289","num":1,"list":[{"name":"碧桂","latLon":"125.323842,43.844289","num":1,"list":[],"id":"","productUnitPrice":"18000","monetaryUnit":"元/㎡","projectType":"1","projectImg":"/fangfang/userfiles/3c37d25396a940f9b784b4c180f7db37/attachment//ff/server/ffServerProjectManage/2020/1/1578042685327.jpg","area":"长春","productFeature":"民宿,0首付,核心地段,非得,第一个,测试,jjjjj,精装,地铁沿线,配套丰富,河景房,现房,户型方正,首付分期,别墅,海景房","isgroup":"0","groupNum":"0","browseNum":"178","forwardingAmount":"0","reportAmount":"7","collectionNum":"1","areaInterval":"无秒结","shopownerName":"","shopownerPhone":"","status":"","address":""}],"id":"","productUnitPrice":"","monetaryUnit":"","projectType":"","projectImg":"","area":"","productFeature":"","isgroup":"","groupNum":"","browseNum":"","forwardingAmount":"","reportAmount":"","collectionNum":"","areaInterval":"","shopownerName":"","shopownerPhone":"","status":"","address":""},{"name":"二道区","latLon":"125.366612,43.879874","num":1,"list":[{"name":"佳源金月湾","latLon":"125.366612,43.879874","num":1,"list":[],"id":"","productUnitPrice":"14000","monetaryUnit":"元起/㎡","projectType":"1","projectImg":"/fangfang/userfiles/3c37d25396a940f9b784b4c180f7db37/attachment//ff/server/ffServerHousesPhoto/2019/9/1573524426722.jpg","area":"海南","productFeature":"配套丰富,拎包入住,海景房","isgroup":"0","groupNum":"0","browseNum":"600","forwardingAmount":"1","reportAmount":"80","collectionNum":"3","areaInterval":"1000元","shopownerName":"","shopownerPhone":"","status":"","address":""}],"id":"","productUnitPrice":"","monetaryUnit":"","projectType":"","projectImg":"","area":"","productFeature":"","isgroup":"","groupNum":"","browseNum":"","forwardingAmount":"","reportAmount":"","collectionNum":"","areaInterval":"","shopownerName":"","shopownerPhone":"","status":"","address":""},{"name":"南关区","latLon":"125.356612,43.869874","num":1,"list":[{"name":"航天首府","latLon":"125.356612,43.869874","num":1,"list":[],"id":"","productUnitPrice":"19000","monetaryUnit":"元起/㎡","projectType":"1","projectImg":"/fangfang/userfiles/3c37d25396a940f9b784b4c180f7db37/attachment//ff/server/ffServerHousesPhoto/2019/9/1573524426714.jpg","area":"固安","productFeature":"","isgroup":"0","groupNum":"0","browseNum":"1492","forwardingAmount":"10","reportAmount":"79","collectionNum":"9","areaInterval":"无秒结","shopownerName":"","shopownerPhone":"","status":"","address":""}],"id":"","productUnitPrice":"","monetaryUnit":"","projectType":"","projectImg":"","area":"","productFeature":"","isgroup":"","groupNum":"","browseNum":"","forwardingAmount":"","reportAmount":"","collectionNum":"","areaInterval":"","shopownerName":"","shopownerPhone":"","status":"","address":""}]
             * id :
             * productUnitPrice :
             * monetaryUnit :
             * projectType :
             * projectImg :
             * area :
             * productFeature :
             * isgroup :
             * groupNum :
             * browseNum :
             * forwardingAmount :
             * reportAmount :
             * collectionNum :
             * areaInterval :
             * shopownerName :
             * shopownerPhone :
             * status :
             * address :
             */

            private String name;
            private String latLon;
            private int num;
            private String id;
            private String productUnitPrice;
            private String monetaryUnit;
            private String projectType;
            private String projectImg;
            private String area;
            private String productFeature;
            private String isgroup;
            private String groupNum;
            private String browseNum;
            private String forwardingAmount;
            private String reportAmount;
            private String collectionNum;
            private String areaInterval;
            private String shopownerName;
            private String shopownerPhone;
            private String status;
            private String address;
            private List<ListBeanX> list;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getLatLon() {
                return latLon;
            }

            public void setLatLon(String latLon) {
                this.latLon = latLon;
            }

            public int getNum() {
                return num;
            }

            public void setNum(int num) {
                this.num = num;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getProductUnitPrice() {
                return productUnitPrice;
            }

            public void setProductUnitPrice(String productUnitPrice) {
                this.productUnitPrice = productUnitPrice;
            }

            public String getMonetaryUnit() {
                return monetaryUnit;
            }

            public void setMonetaryUnit(String monetaryUnit) {
                this.monetaryUnit = monetaryUnit;
            }

            public String getProjectType() {
                return projectType;
            }

            public void setProjectType(String projectType) {
                this.projectType = projectType;
            }

            public String getProjectImg() {
                return projectImg;
            }

            public void setProjectImg(String projectImg) {
                this.projectImg = projectImg;
            }

            public String getArea() {
                return area;
            }

            public void setArea(String area) {
                this.area = area;
            }

            public String getProductFeature() {
                return productFeature;
            }

            public void setProductFeature(String productFeature) {
                this.productFeature = productFeature;
            }

            public String getIsgroup() {
                return isgroup;
            }

            public void setIsgroup(String isgroup) {
                this.isgroup = isgroup;
            }

            public String getGroupNum() {
                return groupNum;
            }

            public void setGroupNum(String groupNum) {
                this.groupNum = groupNum;
            }

            public String getBrowseNum() {
                return browseNum;
            }

            public void setBrowseNum(String browseNum) {
                this.browseNum = browseNum;
            }

            public String getForwardingAmount() {
                return forwardingAmount;
            }

            public void setForwardingAmount(String forwardingAmount) {
                this.forwardingAmount = forwardingAmount;
            }

            public String getReportAmount() {
                return reportAmount;
            }

            public void setReportAmount(String reportAmount) {
                this.reportAmount = reportAmount;
            }

            public String getCollectionNum() {
                return collectionNum;
            }

            public void setCollectionNum(String collectionNum) {
                this.collectionNum = collectionNum;
            }

            public String getAreaInterval() {
                return areaInterval;
            }

            public void setAreaInterval(String areaInterval) {
                this.areaInterval = areaInterval;
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

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public List<ListBeanX> getList() {
                return list;
            }

            public void setList(List<ListBeanX> list) {
                this.list = list;
            }

            public static class ListBeanX {
                /**
                 * name : 朝阳区
                 * latLon : 125.323842,43.844289
                 * num : 1
                 * list : [{"name":"碧桂","latLon":"125.323842,43.844289","num":1,"list":[],"id":"","productUnitPrice":"18000","monetaryUnit":"元/㎡","projectType":"1","projectImg":"/fangfang/userfiles/3c37d25396a940f9b784b4c180f7db37/attachment//ff/server/ffServerProjectManage/2020/1/1578042685327.jpg","area":"长春","productFeature":"民宿,0首付,核心地段,非得,第一个,测试,jjjjj,精装,地铁沿线,配套丰富,河景房,现房,户型方正,首付分期,别墅,海景房","isgroup":"0","groupNum":"0","browseNum":"178","forwardingAmount":"0","reportAmount":"7","collectionNum":"1","areaInterval":"无秒结","shopownerName":"","shopownerPhone":"","status":"","address":""}]
                 * id :
                 * productUnitPrice :
                 * monetaryUnit :
                 * projectType :
                 * projectImg :
                 * area :
                 * productFeature :
                 * isgroup :
                 * groupNum :
                 * browseNum :
                 * forwardingAmount :
                 * reportAmount :
                 * collectionNum :
                 * areaInterval :
                 * shopownerName :
                 * shopownerPhone :
                 * status :
                 * address :
                 */

                private String name;
                private String latLon;
                private int num;
                private String id;
                private String productUnitPrice;
                private String monetaryUnit;
                private String projectType;
                private String projectImg;
                private String area;
                private String productFeature;
                private String isgroup;
                private String groupNum;
                private String browseNum;
                private String forwardingAmount;
                private String reportAmount;
                private String collectionNum;
                private String areaInterval;
                private String shopownerName;
                private String shopownerPhone;
                private String status;
                private String address;
                private List<ListBean> list;

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getLatLon() {
                    return latLon;
                }

                public void setLatLon(String latLon) {
                    this.latLon = latLon;
                }

                public int getNum() {
                    return num;
                }

                public void setNum(int num) {
                    this.num = num;
                }

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getProductUnitPrice() {
                    return productUnitPrice;
                }

                public void setProductUnitPrice(String productUnitPrice) {
                    this.productUnitPrice = productUnitPrice;
                }

                public String getMonetaryUnit() {
                    return monetaryUnit;
                }

                public void setMonetaryUnit(String monetaryUnit) {
                    this.monetaryUnit = monetaryUnit;
                }

                public String getProjectType() {
                    return projectType;
                }

                public void setProjectType(String projectType) {
                    this.projectType = projectType;
                }

                public String getProjectImg() {
                    return projectImg;
                }

                public void setProjectImg(String projectImg) {
                    this.projectImg = projectImg;
                }

                public String getArea() {
                    return area;
                }

                public void setArea(String area) {
                    this.area = area;
                }

                public String getProductFeature() {
                    return productFeature;
                }

                public void setProductFeature(String productFeature) {
                    this.productFeature = productFeature;
                }

                public String getIsgroup() {
                    return isgroup;
                }

                public void setIsgroup(String isgroup) {
                    this.isgroup = isgroup;
                }

                public String getGroupNum() {
                    return groupNum;
                }

                public void setGroupNum(String groupNum) {
                    this.groupNum = groupNum;
                }

                public String getBrowseNum() {
                    return browseNum;
                }

                public void setBrowseNum(String browseNum) {
                    this.browseNum = browseNum;
                }

                public String getForwardingAmount() {
                    return forwardingAmount;
                }

                public void setForwardingAmount(String forwardingAmount) {
                    this.forwardingAmount = forwardingAmount;
                }

                public String getReportAmount() {
                    return reportAmount;
                }

                public void setReportAmount(String reportAmount) {
                    this.reportAmount = reportAmount;
                }

                public String getCollectionNum() {
                    return collectionNum;
                }

                public void setCollectionNum(String collectionNum) {
                    this.collectionNum = collectionNum;
                }

                public String getAreaInterval() {
                    return areaInterval;
                }

                public void setAreaInterval(String areaInterval) {
                    this.areaInterval = areaInterval;
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

                public String getStatus() {
                    return status;
                }

                public void setStatus(String status) {
                    this.status = status;
                }

                public String getAddress() {
                    return address;
                }

                public void setAddress(String address) {
                    this.address = address;
                }

                public List<ListBean> getList() {
                    return list;
                }

                public void setList(List<ListBean> list) {
                    this.list = list;
                }

                public static class ListBean {
                    /**
                     * name : 碧桂
                     * latLon : 125.323842,43.844289
                     * num : 1
                     * list : []
                     * id :
                     * productUnitPrice : 18000
                     * monetaryUnit : 元/㎡
                     * projectType : 1
                     * projectImg : /fangfang/userfiles/3c37d25396a940f9b784b4c180f7db37/attachment//ff/server/ffServerProjectManage/2020/1/1578042685327.jpg
                     * area : 长春
                     * productFeature : 民宿,0首付,核心地段,非得,第一个,测试,jjjjj,精装,地铁沿线,配套丰富,河景房,现房,户型方正,首付分期,别墅,海景房
                     * isgroup : 0
                     * groupNum : 0
                     * browseNum : 178
                     * forwardingAmount : 0
                     * reportAmount : 7
                     * collectionNum : 1
                     * areaInterval : 无秒结
                     * shopownerName :
                     * shopownerPhone :
                     * status :
                     * address :
                     */

                    private String name;
                    private String latLon;
                    private int num;
                    private String id;
                    private String productUnitPrice;
                    private String monetaryUnit;
                    private String projectType;
                    private String projectImg;
                    private String area;
                    private String productFeature;
                    private String isgroup;
                    private String groupNum;
                    private String browseNum;
                    private String forwardingAmount;
                    private String reportAmount;
                    private String collectionNum;
                    private String areaInterval;
                    private String shopownerName;
                    private String shopownerPhone;
                    private String status;
                    private String address;
                    private List<?> list;

                    public String getName() {
                        return name;
                    }

                    public void setName(String name) {
                        this.name = name;
                    }

                    public String getLatLon() {
                        return latLon;
                    }

                    public void setLatLon(String latLon) {
                        this.latLon = latLon;
                    }

                    public int getNum() {
                        return num;
                    }

                    public void setNum(int num) {
                        this.num = num;
                    }

                    public String getId() {
                        return id;
                    }

                    public void setId(String id) {
                        this.id = id;
                    }

                    public String getProductUnitPrice() {
                        return productUnitPrice;
                    }

                    public void setProductUnitPrice(String productUnitPrice) {
                        this.productUnitPrice = productUnitPrice;
                    }

                    public String getMonetaryUnit() {
                        return monetaryUnit;
                    }

                    public void setMonetaryUnit(String monetaryUnit) {
                        this.monetaryUnit = monetaryUnit;
                    }

                    public String getProjectType() {
                        return projectType;
                    }

                    public void setProjectType(String projectType) {
                        this.projectType = projectType;
                    }

                    public String getProjectImg() {
                        return projectImg;
                    }

                    public void setProjectImg(String projectImg) {
                        this.projectImg = projectImg;
                    }

                    public String getArea() {
                        return area;
                    }

                    public void setArea(String area) {
                        this.area = area;
                    }

                    public String getProductFeature() {
                        return productFeature;
                    }

                    public void setProductFeature(String productFeature) {
                        this.productFeature = productFeature;
                    }

                    public String getIsgroup() {
                        return isgroup;
                    }

                    public void setIsgroup(String isgroup) {
                        this.isgroup = isgroup;
                    }

                    public String getGroupNum() {
                        return groupNum;
                    }

                    public void setGroupNum(String groupNum) {
                        this.groupNum = groupNum;
                    }

                    public String getBrowseNum() {
                        return browseNum;
                    }

                    public void setBrowseNum(String browseNum) {
                        this.browseNum = browseNum;
                    }

                    public String getForwardingAmount() {
                        return forwardingAmount;
                    }

                    public void setForwardingAmount(String forwardingAmount) {
                        this.forwardingAmount = forwardingAmount;
                    }

                    public String getReportAmount() {
                        return reportAmount;
                    }

                    public void setReportAmount(String reportAmount) {
                        this.reportAmount = reportAmount;
                    }

                    public String getCollectionNum() {
                        return collectionNum;
                    }

                    public void setCollectionNum(String collectionNum) {
                        this.collectionNum = collectionNum;
                    }

                    public String getAreaInterval() {
                        return areaInterval;
                    }

                    public void setAreaInterval(String areaInterval) {
                        this.areaInterval = areaInterval;
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

                    public String getStatus() {
                        return status;
                    }

                    public void setStatus(String status) {
                        this.status = status;
                    }

                    public String getAddress() {
                        return address;
                    }

                    public void setAddress(String address) {
                        this.address = address;
                    }

                    public List<?> getList() {
                        return list;
                    }

                    public void setList(List<?> list) {
                        this.list = list;
                    }
                }
            }
        }
    }
}

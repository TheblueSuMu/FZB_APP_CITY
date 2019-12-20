package com.xcy.fzbcity.all.modle;

import java.util.List;

public class CheckPendingBean {
    /**
     * code : 1
     * msg : 成功
     * data : {"total":17,"rows":[{"date":"报备时间2019-12-11 14:55:24","customerImg":"/fangfang/static/common/images/flat-avatar.png","isRead":"0","projectType":"","customerName":"不错","customerPhone":"15243111617","customerId":"85547b9dfbf64621a3e7587aec0a1195","preparationId":"94325e66877f43b195d1bc481fb71ebb","relatedData":"待审核","intentionArea":"","projectName":"鼎龙湾国际海洋王国度假区","projectId":"","status":"10"},{"date":"报备时间2019-12-11 13:28:18","customerImg":"/fangfang/static/common/images/flat-avatar.png","isRead":"0","projectType":"","customerName":"不错","customerPhone":"15243111617","customerId":"85547b9dfbf64621a3e7587aec0a1195","preparationId":"e8942da95d614b9186d77b639b4966a2","relatedData":"待审核","intentionArea":"","projectName":"航天首府","projectId":"","status":"10"},{"date":"报备时间2019-12-11 13:18:02","customerImg":"/fangfang/static/common/images/flat-avatar.png","isRead":"0","projectType":"","customerName":"宠一","customerPhone":"15566666335","customerId":"aacc72239e724ea2a6229f290ff1a6da","preparationId":"64998e04f01949b1bd7cc6c623ab61d8","relatedData":"待审核","intentionArea":"","projectName":"航天首府","projectId":"","status":"10"},{"date":"报备时间2019-12-10 22:30:16","customerImg":"/fangfang/static/common/images/flat-avatar.png","isRead":"0","projectType":"","customerName":"嘿嘿","customerPhone":"13850306686","customerId":"3b02b4e89564471b90e63ede0062d805","preparationId":"443dec288bb94b94ba53b4a7cef51226","relatedData":"待审核","intentionArea":"","projectName":"航天首府","projectId":"","status":"10"},{"date":"报备时间2019-12-10 16:22:01","customerImg":"/fangfang/static/common/images/flat-avatar.png","isRead":"0","projectType":"","customerName":"姣姣","customerPhone":"15752452580","customerId":"0de805a44df9439da591ab046a51f31a","preparationId":"ac574bf9cf8c4fc1b3a85025aa2fefd9","relatedData":"待审核","intentionArea":"","projectName":"鼎龙湾国际海洋王国度假区","projectId":"","status":"10"},{"date":"报备时间2019-12-10 16:16:06","customerImg":"/fangfang/static/common/images/flat-avatar.png","isRead":"0","projectType":"","customerName":"米兰","customerPhone":"15566368989","customerId":"93325a8b9e4f49ce85c00d29f9183489","preparationId":"4987fc3fceb64fb29c46236cd71a94b5","relatedData":"待审核","intentionArea":"","projectName":"航天首府","projectId":"","status":"10"},{"date":"报备时间2019-12-10 14:35:30","customerImg":"/fangfang/static/common/images/flat-avatar.png","isRead":"0","projectType":"","customerName":"米咋","customerPhone":"15855565555","customerId":"7016d0009d614f8584edaa356d8f89a2","preparationId":"b6f8ddee0b5241a59744a287245f659b","relatedData":"待审核","intentionArea":"","projectName":"温泉新都孔雀城冠军园","projectId":"","status":"10"},{"date":"报备时间2019-12-10 14:33:37","customerImg":"/fangfang/static/common/images/flat-avatar.png","isRead":"0","projectType":"","customerName":"你丫","customerPhone":"15808880008","customerId":"b254afa4fb67447a9d0ad8ea1b59d32a","preparationId":"9ca69ff2d0df4680819f389dcf675d66","relatedData":"待审核","intentionArea":"","projectName":"温泉新都孔雀城冠军园","projectId":"","status":"10"},{"date":"报备时间2019-12-10 11:25:26","customerImg":"/fangfang/static/common/images/flat-avatar.png","isRead":"0","projectType":"","customerName":"赵娜娜","customerPhone":"15508445563","customerId":"484672b2cd5a4e78a2d4c5ad4e7885fb","preparationId":"e3888fffcd054fc7b9c2aeed105aa974","relatedData":"待审核","intentionArea":"","projectName":"温泉新都孔雀城风情园","projectId":"","status":"10"},{"date":"报备时间2019-12-10 11:02:41","customerImg":"/fangfang/static/common/images/flat-avatar.png","isRead":"0","projectType":"","customerName":"米兰","customerPhone":"15566368989","customerId":"93325a8b9e4f49ce85c00d29f9183489","preparationId":"09af972d202f4ed0bee4f39338fdc302","relatedData":"待审核","intentionArea":"","projectName":"温泉新都孔雀城风情园","projectId":"","status":"10"}]}
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
         * total : 17
         * rows : [{"date":"报备时间2019-12-11 14:55:24","customerImg":"/fangfang/static/common/images/flat-avatar.png","isRead":"0","projectType":"","customerName":"不错","customerPhone":"15243111617","customerId":"85547b9dfbf64621a3e7587aec0a1195","preparationId":"94325e66877f43b195d1bc481fb71ebb","relatedData":"待审核","intentionArea":"","projectName":"鼎龙湾国际海洋王国度假区","projectId":"","status":"10"},{"date":"报备时间2019-12-11 13:28:18","customerImg":"/fangfang/static/common/images/flat-avatar.png","isRead":"0","projectType":"","customerName":"不错","customerPhone":"15243111617","customerId":"85547b9dfbf64621a3e7587aec0a1195","preparationId":"e8942da95d614b9186d77b639b4966a2","relatedData":"待审核","intentionArea":"","projectName":"航天首府","projectId":"","status":"10"},{"date":"报备时间2019-12-11 13:18:02","customerImg":"/fangfang/static/common/images/flat-avatar.png","isRead":"0","projectType":"","customerName":"宠一","customerPhone":"15566666335","customerId":"aacc72239e724ea2a6229f290ff1a6da","preparationId":"64998e04f01949b1bd7cc6c623ab61d8","relatedData":"待审核","intentionArea":"","projectName":"航天首府","projectId":"","status":"10"},{"date":"报备时间2019-12-10 22:30:16","customerImg":"/fangfang/static/common/images/flat-avatar.png","isRead":"0","projectType":"","customerName":"嘿嘿","customerPhone":"13850306686","customerId":"3b02b4e89564471b90e63ede0062d805","preparationId":"443dec288bb94b94ba53b4a7cef51226","relatedData":"待审核","intentionArea":"","projectName":"航天首府","projectId":"","status":"10"},{"date":"报备时间2019-12-10 16:22:01","customerImg":"/fangfang/static/common/images/flat-avatar.png","isRead":"0","projectType":"","customerName":"姣姣","customerPhone":"15752452580","customerId":"0de805a44df9439da591ab046a51f31a","preparationId":"ac574bf9cf8c4fc1b3a85025aa2fefd9","relatedData":"待审核","intentionArea":"","projectName":"鼎龙湾国际海洋王国度假区","projectId":"","status":"10"},{"date":"报备时间2019-12-10 16:16:06","customerImg":"/fangfang/static/common/images/flat-avatar.png","isRead":"0","projectType":"","customerName":"米兰","customerPhone":"15566368989","customerId":"93325a8b9e4f49ce85c00d29f9183489","preparationId":"4987fc3fceb64fb29c46236cd71a94b5","relatedData":"待审核","intentionArea":"","projectName":"航天首府","projectId":"","status":"10"},{"date":"报备时间2019-12-10 14:35:30","customerImg":"/fangfang/static/common/images/flat-avatar.png","isRead":"0","projectType":"","customerName":"米咋","customerPhone":"15855565555","customerId":"7016d0009d614f8584edaa356d8f89a2","preparationId":"b6f8ddee0b5241a59744a287245f659b","relatedData":"待审核","intentionArea":"","projectName":"温泉新都孔雀城冠军园","projectId":"","status":"10"},{"date":"报备时间2019-12-10 14:33:37","customerImg":"/fangfang/static/common/images/flat-avatar.png","isRead":"0","projectType":"","customerName":"你丫","customerPhone":"15808880008","customerId":"b254afa4fb67447a9d0ad8ea1b59d32a","preparationId":"9ca69ff2d0df4680819f389dcf675d66","relatedData":"待审核","intentionArea":"","projectName":"温泉新都孔雀城冠军园","projectId":"","status":"10"},{"date":"报备时间2019-12-10 11:25:26","customerImg":"/fangfang/static/common/images/flat-avatar.png","isRead":"0","projectType":"","customerName":"赵娜娜","customerPhone":"15508445563","customerId":"484672b2cd5a4e78a2d4c5ad4e7885fb","preparationId":"e3888fffcd054fc7b9c2aeed105aa974","relatedData":"待审核","intentionArea":"","projectName":"温泉新都孔雀城风情园","projectId":"","status":"10"},{"date":"报备时间2019-12-10 11:02:41","customerImg":"/fangfang/static/common/images/flat-avatar.png","isRead":"0","projectType":"","customerName":"米兰","customerPhone":"15566368989","customerId":"93325a8b9e4f49ce85c00d29f9183489","preparationId":"09af972d202f4ed0bee4f39338fdc302","relatedData":"待审核","intentionArea":"","projectName":"温泉新都孔雀城风情园","projectId":"","status":"10"}]
         */

        private int total;
        private List<RowsBean> rows;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public List<RowsBean> getRows() {
            return rows;
        }

        public void setRows(List<RowsBean> rows) {
            this.rows = rows;
        }

        public static class RowsBean {
            /**
             * date : 报备时间2019-12-11 14:55:24
             * customerImg : /fangfang/static/common/images/flat-avatar.png
             * isRead : 0
             * projectType :
             * customerName : 不错
             * customerPhone : 15243111617
             * customerId : 85547b9dfbf64621a3e7587aec0a1195
             * preparationId : 94325e66877f43b195d1bc481fb71ebb
             * relatedData : 待审核
             * intentionArea :
             * projectName : 鼎龙湾国际海洋王国度假区
             * projectId :
             * status : 10
             */

            private String date;
            private String customerImg;
            private String isRead;
            private String projectType;
            private String customerName;
            private String customerPhone;
            private String customerId;
            private String preparationId;
            private String relatedData;
            private String intentionArea;
            private String projectName;
            private String projectId;
            private String status;

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getCustomerImg() {
                return customerImg;
            }

            public void setCustomerImg(String customerImg) {
                this.customerImg = customerImg;
            }

            public String getIsRead() {
                return isRead;
            }

            public void setIsRead(String isRead) {
                this.isRead = isRead;
            }

            public String getProjectType() {
                return projectType;
            }

            public void setProjectType(String projectType) {
                this.projectType = projectType;
            }

            public String getCustomerName() {
                return customerName;
            }

            public void setCustomerName(String customerName) {
                this.customerName = customerName;
            }

            public String getCustomerPhone() {
                return customerPhone;
            }

            public void setCustomerPhone(String customerPhone) {
                this.customerPhone = customerPhone;
            }

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

            public String getRelatedData() {
                return relatedData;
            }

            public void setRelatedData(String relatedData) {
                this.relatedData = relatedData;
            }

            public String getIntentionArea() {
                return intentionArea;
            }

            public void setIntentionArea(String intentionArea) {
                this.intentionArea = intentionArea;
            }

            public String getProjectName() {
                return projectName;
            }

            public void setProjectName(String projectName) {
                this.projectName = projectName;
            }

            public String getProjectId() {
                return projectId;
            }

            public void setProjectId(String projectId) {
                this.projectId = projectId;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }
        }
    }
}
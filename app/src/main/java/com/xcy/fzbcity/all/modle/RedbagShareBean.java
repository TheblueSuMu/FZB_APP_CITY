package com.xcy.fzbcity.all.modle;

import java.util.List;

public class RedbagShareBean {

    /**
     * code : 1
     * msg : 成功
     * data : {"projectShareImagePaths":["http://localhost:8080/fangfang//userfiles//temp//posterTemplate//1582373795124.jpg"]}
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
        private List<String> projectShareImagePaths;

        public List<String> getProjectShareImagePaths() {
            return projectShareImagePaths;
        }

        public void setProjectShareImagePaths(List<String> projectShareImagePaths) {
            this.projectShareImagePaths = projectShareImagePaths;
        }
    }
}

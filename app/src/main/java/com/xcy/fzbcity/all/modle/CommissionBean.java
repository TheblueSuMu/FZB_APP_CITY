package com.xcy.fzbcity.all.modle;

public class CommissionBean {
    private String title;
    private String price;
    private String time;

    public CommissionBean(String title, String price, String time) {
        this.title = title;
        this.price = price;
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "CommissionBean{" +
                "title='" + title + '\'' +
                ", price='" + price + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}

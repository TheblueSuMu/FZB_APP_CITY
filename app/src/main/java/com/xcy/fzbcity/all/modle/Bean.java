package com.xcy.fzbcity.all.modle;

public class Bean {

    public Integer pic;
    public String name;

    public Bean(Integer pic, String name) {
        this.pic = pic;
        this.name = name;
    }

    public Integer getPic() {
        return pic;
    }

    public void setPic(Integer pic) {
        this.pic = pic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Bean{" +
                "pic=" + pic +
                ", name='" + name + '\'' +
                '}';
    }
}
package com.xcy.fzbcity.all.modle;

public class Bean_S {

    public Integer pic;
    public String name;

    public Bean_S(String name) {
        this.name = name;
    }

    public Bean_S(Integer pic, String name) {
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
package com.xcy.fzbcity.all.adapter;

import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class MyFragmentPagerAdapter extends FragmentPagerAdapter {
    private FragmentManager fragmetnmanager;  //创建FragmentManager
    private List<Fragment> listfragment; //创建一个List<Fragment>

    public MyFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public List<Fragment> getListfragment() {
        return listfragment;
    }

    public void setListfragment(List<Fragment> listfragment) {
        this.listfragment = listfragment;
    }

    @Override
    public Fragment getItem(int arg0) {
        return listfragment.get(arg0); //返回第几个fragment
    }

    @Override
    public int getCount() {
        return listfragment.size(); //总共有多少个fragment
    }
}

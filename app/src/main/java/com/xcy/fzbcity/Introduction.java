package com.xcy.fzbcity;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.fragment.Introduction_Fragment1;
import com.xcy.fzbcity.all.fragment.Introduction_Fragment2;
import com.xcy.fzbcity.all.fragment.Introduction_Fragment3;
import com.xcy.fzbcity.all.view.AllActivity;
import com.xcy.fzbcity.shopping_guide.adapter.BaseFragmentAdapter;

import java.util.ArrayList;
import java.util.List;

public class Introduction extends AllActivity {

    private List<Fragment> mFragments;
    private String[] mTitles = new String[]{};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduction);
        init();
    }

    private void init(){
        ViewPager introduction_APP = findViewById(R.id.introduction_APP);
        setupViewPager(introduction_APP);
    }

    private void setupViewPager(ViewPager viewPager) {
        mFragments = new ArrayList<>();
        Introduction_Fragment1 introduction_fragment1 = new Introduction_Fragment1();
        Introduction_Fragment2 introduction_fragment2 = new Introduction_Fragment2();
        Introduction_Fragment3 introduction_fragment3 = new Introduction_Fragment3();

        mFragments.add(introduction_fragment1);
        mFragments.add(introduction_fragment2);
        mFragments.add(introduction_fragment3);
        BaseFragmentAdapter adapter = new BaseFragmentAdapter(getSupportFragmentManager(), mFragments, mTitles);
        viewPager.setAdapter(adapter);
    }

}

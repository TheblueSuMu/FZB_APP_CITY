package com.xcy.fzbcity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.fragment.Introduction_Fragment1;
import com.xcy.fzbcity.all.fragment.Introduction_Fragment2;
import com.xcy.fzbcity.all.fragment.Introduction_Fragment3;
import com.xcy.fzbcity.all.view.AllActivity;
import com.xcy.fzbcity.all.view.DisclaimerActivity;
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

        AlertDialog.Builder builder2 = new AlertDialog.Builder(Introduction.this);
        View inflate2 = LayoutInflater.from(Introduction.this).inflate(R.layout.login_yingxi, null, false);
        builder2.setView(inflate2);
        builder2.setCancelable(false);
        final AlertDialog show2 = builder2.show();
        show2.getWindow().setBackgroundDrawableResource(R.drawable.report_shape);
        WindowManager m2 = Introduction.this.getWindowManager();
        Display d2 = m2.getDefaultDisplay();
        WindowManager.LayoutParams attributes2 = show2.getWindow().getAttributes();
        attributes2.width = (int)(d2.getWidth() - 200);
        show2.getWindow().setAttributes(attributes2);
        show2.getWindow().setBackgroundDrawableResource(R.drawable.report_shape);
        RelativeLayout login_yingxi_cancel2 = inflate2.findViewById(R.id.login_yingxi_cancel);
        RelativeLayout login_yingxi_confirm2 = inflate2.findViewById(R.id.login_yingxi_confirm);
        TextView login_yingxi_chick = inflate2.findViewById(R.id.login_yingxi_chick);
        login_yingxi_chick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Introduction.this, DisclaimerActivity.class);
                startActivity(intent);
            }
        });
        login_yingxi_cancel2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AllActivity.exit = true;
                finish();
                show2.dismiss();
            }
        });
        login_yingxi_confirm2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show2.dismiss();
            }
        });

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

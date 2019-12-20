package com.xcy.fzbcity.all.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.persente.SingleClick;
import com.xcy.fzbcity.all.persente.StatusBar;
import com.xcy.fzbcity.all.utils.CommonUtil;
import com.xcy.fzbcity.all.utils.ToastUtil;

import butterknife.ButterKnife;

public class DynamicConditionActivity extends AppCompatActivity implements View.OnClickListener {

    RelativeLayout personalReturnImg;
    RelativeLayout dynamic_condition_rl_1;
    RelativeLayout dynamic_condition_rl_2;
    RelativeLayout dynamic_condition_rl_3;
    RelativeLayout dynamic_condition_rl_4;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamic_condition);

        init_No_Network();
    }

    private void init_No_Network() {
        StatusBar.makeStatusBarTransparent(this);
        boolean networkAvailable = CommonUtil.isNetworkAvailable(this);
        if (networkAvailable) {
            ButterKnife.bind(this);

            initView();
        } else {
            RelativeLayout all_no_network = findViewById(R.id.dynamic_condition_no_network);
            Button all_no_reload = findViewById(R.id.dynamic_condition_reload);

            all_no_network.setVisibility(View.VISIBLE);
            all_no_reload.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                    startActivity(getIntent());
                }
            });
            ToastUtil.showToast(this,"当前无网络，请检查网络后再进行登录");
        }

    }

    private void initView() {

        personalReturnImg = findViewById(R.id.dynamic_condition_return_img);
        dynamic_condition_rl_1 = findViewById(R.id.dynamic_condition_rl_1);
        dynamic_condition_rl_2 = findViewById(R.id.dynamic_condition_rl_2);
        dynamic_condition_rl_3 = findViewById(R.id.dynamic_condition_rl_3);
        dynamic_condition_rl_4 = findViewById(R.id.dynamic_condition_rl_4);


        personalReturnImg.setOnClickListener(this);
        dynamic_condition_rl_1.setOnClickListener(this);
        dynamic_condition_rl_2.setOnClickListener(this);
        dynamic_condition_rl_3.setOnClickListener(this);
        dynamic_condition_rl_4.setOnClickListener(this);

    }

    @SingleClick(1000)
    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.dynamic_condition_return_img:
                finish();
                break;
            case R.id.dynamic_condition_rl_1:
                intent = new Intent(DynamicConditionActivity.this, DynamicDetailsActivity.class);//我的发布
                intent.putExtra("DynamicName","我的发布");
                startActivity(intent);
                break;
            case R.id.dynamic_condition_rl_2:
                intent = new Intent(DynamicConditionActivity.this, DynamicDetailsActivity.class);//我的留言
                intent.putExtra("DynamicName","我的留言");
                startActivity(intent);
                break;
            case R.id.dynamic_condition_rl_3:
                intent = new Intent(DynamicConditionActivity.this, DynamicDetailsActivity.class);//我的点赞
                intent.putExtra("DynamicName","我的点赞");
                startActivity(intent);
                break;
            case R.id.dynamic_condition_rl_4:
                intent = new Intent(DynamicConditionActivity.this, CollectActivity.class);//我的收藏
                startActivity(intent);
                break;
        }

    }
}

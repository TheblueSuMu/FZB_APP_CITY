package com.xcy.fzbcity.all.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.xcy.fzbcity.all.utils.ToastUtil;

import com.google.gson.Gson;
import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.api.FinalContents;
import com.xcy.fzbcity.all.modle.NickNameBean;
import com.xcy.fzbcity.all.persente.OkHttpPost;
import com.xcy.fzbcity.all.persente.SingleClick;
import com.xcy.fzbcity.all.persente.StatusBar;
import com.xcy.fzbcity.all.utils.CommonUtil;
import com.xcy.fzbcity.all.utils.ToastUtil;


//TODO 性别
public class SexActivity extends AllActivity implements View.OnClickListener {

    private RelativeLayout sex_return;
    private TextView sex_wc;
    private ImageView sex_nan;
    private ImageView sex_nv;
    private RelativeLayout sex_rl_1;
    private RelativeLayout sex_rl_2;

    String sex = "男";
    private String nc;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sex);
        init_No_Network();
    }

    private void init_No_Network(){
        boolean networkAvailable = CommonUtil.isNetworkAvailable(this);
        if (networkAvailable) {
            initView();
        } else {
            RelativeLayout all_no_network = findViewById(R.id.all_no_network);
            Button all_no_reload = findViewById(R.id.all_no_reload);

            all_no_network.setVisibility(View.VISIBLE);
            all_no_reload.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                    startActivity(getIntent());
                }
            });
            ToastUtil.showToast(this, "当前无网络，请检查网络后再进行登录");
        }
    }

    private void initView() {

        StatusBar.makeStatusBarTransparent(this);

        sex_return = findViewById(R.id.sex_return);
        sex_wc = findViewById(R.id.sex_wc);
        sex_nan = findViewById(R.id.sex_nan);
        sex_nv = findViewById(R.id.sex_nv);

        sex_rl_1 = findViewById(R.id.sex_rl_1);
        sex_rl_2 = findViewById(R.id.sex_rl_2);

        sex_return.setOnClickListener(this);
        sex_wc.setOnClickListener(this);
        sex_rl_1.setOnClickListener(this);
        sex_rl_2.setOnClickListener(this);
    }

    @SingleClick(1000)
    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.sex_return:
                intent = new Intent(SexActivity.this, PersonalInformationActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.sex_wc:
                nc = getIntent().getStringExtra("nc");
                Log.i("MyCL", "接收参数：" + nc);
                String url = FinalContents.getImageUrl()+"/fangfang/app/v1/commonUpdate/updateUser?" + "userId=" + FinalContents.getUserID() + "&name=" + nc + "&sex=" + sex + "&photo=" + "&industry=";

                OkHttpPost okHttpPost = new OkHttpPost(url);
                String data = okHttpPost.post();
                Log.i("MyCL", "参数：" + data);
                Gson gson = new Gson();
                NickNameBean nickNameBean = gson.fromJson(data, NickNameBean.class);
                NickNameBean.DataBean data1 = nickNameBean.getData();
                ToastUtil.showToast(SexActivity.this, data1.getMessage());

                intent = new Intent(SexActivity.this, PersonalInformationActivity.class);
                startActivity(intent);
                finish();

                break;
            case R.id.sex_rl_1:
                sex_nan.setVisibility(View.VISIBLE);
                sex_nv.setVisibility(View.INVISIBLE);

                sex = "男";

                break;
            case R.id.sex_rl_2:
                sex_nan.setVisibility(View.INVISIBLE);
                sex_nv.setVisibility(View.VISIBLE);

                sex = "女";

                break;

        }

    }
}

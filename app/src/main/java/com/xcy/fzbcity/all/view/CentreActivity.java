package com.xcy.fzbcity.all.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.persente.SingleClick;
import com.xcy.fzbcity.all.persente.StatusBar;
import com.xcy.fzbcity.all.utils.CommonUtil;
import com.xcy.fzbcity.all.utils.ToastUtil;


//TODO 医疗中心
public class CentreActivity extends AllActivity implements View.OnClickListener {


    private RelativeLayout center_img;
    private TextView center_headline;
    private TextView center_title;
    private TextView center_time;
    private TextView center_content;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_centre);
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
            ToastUtil.showToast(this,"当前无网络，请检查网络后再进行登录");
        }
    }

    public void initView() {

        StatusBar.makeStatusBarTransparent(this);

        center_img = findViewById(R.id.center_img);
        center_headline = findViewById(R.id.center_headline);
        center_title = findViewById(R.id.center_title);
        center_time = findViewById(R.id.center_time);
        center_content = findViewById(R.id.center_content);

        center_img.setOnClickListener(this);
    }

    @SingleClick(1000)
    @Override
    public void onClick(View view) {

        int id = view.getId();

        if (id == R.id.center_img) {
            finish();
        }

    }


}

package com.xcy.fzbcity.all.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.adapter.InformTheDetailsAdapter;
import com.xcy.fzbcity.all.api.FinalContents;
import com.xcy.fzbcity.all.utils.InformeDataBase;
import com.xcy.fzbcity.all.utils.InformeDataBaseUtil;
import com.xcy.fzbcity.all.utils.InformeDataNewBase;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class InformTheDetailsActivity extends AllActivity implements InformTheDetailsAdapter.OnItemInformClick, View.OnClickListener {

    RelativeLayout inform_the_details_return;
    TextView inform_the_details_title;
    RecyclerView inform_the_details_rv;
    InformTheDetailsAdapter adapter;
    ImageView inform_the_details_eliminate;

    private String type;
    private List<InformeDataBase> informeDataBases = new ArrayList<>();
    private List<InformeDataNewBase> informeDataNewBases = new ArrayList<>();
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inform_the_details);

        initViewData();

    }

    private void initViewData() {

        type = getIntent().getStringExtra("type");

        inform_the_details_return = findViewById(R.id.inform_the_details_return);
        inform_the_details_title = findViewById(R.id.inform_the_details_title);
        inform_the_details_rv = findViewById(R.id.inform_the_details_rv);
        inform_the_details_eliminate = findViewById(R.id.inform_the_details_eliminate);

        inform_the_details_eliminate.setOnClickListener(this);
        inform_the_details_return.setOnClickListener(this);


        if (type.equals("1")) {
            inform_the_details_title.setText("通知消息");
        } else if (type.equals("2")) {
            inform_the_details_title.setText("红包消息");
        } else if (type.equals("3")) {
            inform_the_details_title.setText("足迹信息");
        }


        initData();

    }

    private void initData() {

        informeDataBases = InformeDataBaseUtil.initSelect(InformTheDetailsActivity.this, "");//数据库查找到的数据集合
        for (int i = 0; i < informeDataBases.size(); ++i) {
            if (informeDataBases.get(i).getType().equals(type)) {
                if (informeDataBases.get(i).getUserId().equals(FinalContents.getUserID())) {
                    informeDataNewBases.add(new InformeDataNewBase(informeDataBases.get(i).getInformeid(), informeDataBases.get(i).getTitle(), informeDataBases.get(i).getContent()
                            , informeDataBases.get(i).getType(), informeDataBases.get(i).getDate(), informeDataBases.get(i).getUserId(), informeDataBases.get(i).getIsRead()
                            , informeDataBases.get(i).getSubtype(), informeDataBases.get(i).getCommonid(), informeDataBases.get(i).getState(), informeDataBases.get(i).getIsenable()));
                }
            }
            if ((informeDataBases.size() - 1) == i) {
                adapter = new InformTheDetailsAdapter();
                adapter.setOnItemInformClick(this);
                LinearLayoutManager manager = new LinearLayoutManager(this);
                manager.setOrientation(LinearLayoutManager.VERTICAL);
                inform_the_details_rv.setLayoutManager(manager);
                adapter.setInformeDataBases(informeDataNewBases);
                inform_the_details_rv.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
        }

    }

    //item点击事件
    @Override
    public void ItemInformClick(int position) {

        Log.i("通知数据库", "informeDataNewBases.get(position).getInformeid()：" + informeDataNewBases.get(position).getInformeid());
        InformeDataBaseUtil.initUpData(InformTheDetailsActivity.this, informeDataNewBases.get(position).getInformeid(), "0");
        informeDataNewBases.clear();
        initData();
        if (informeDataNewBases.get(position).getSubtype().equals("1")) {//开盘和变价
            Log.i("状态","开盘和变价");
//            intent = new Intent(InformTheDetailsActivity.this,);
        } else if (informeDataNewBases.get(position).getSubtype().equals("2")) {//楼盘动态
            Log.i("状态","楼盘动态");
//            intent = new Intent(InformTheDetailsActivity.this,);
        } else if (informeDataNewBases.get(position).getSubtype().equals("3")) {//消息通知
            Log.i("状态","消息通知");
//            intent = new Intent(InformTheDetailsActivity.this,);
        } else if (informeDataNewBases.get(position).getSubtype().equals("4")) {//流程通知
            Log.i("状态","流程通知");
//            intent = new Intent(InformTheDetailsActivity.this,);
        } else if (informeDataNewBases.get(position).getSubtype().equals("5")) {//红包领取详情
            Log.i("状态","红包领取详情");
//            intent = new Intent(InformTheDetailsActivity.this,);
        } else if (informeDataNewBases.get(position).getSubtype().equals("6")) {//优惠活动
            Log.i("状态","优惠活动");
//            intent = new Intent(InformTheDetailsActivity.this,);
        }else {//状态为空
            Log.i("状态","状态为空");
        }
//        startActivity(intent);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.inform_the_details_return:
                finish();
                break;
            case R.id.inform_the_details_eliminate:
                for (int i = 0; i < informeDataNewBases.size(); ++i) {
                    InformeDataBaseUtil.initUpData(InformTheDetailsActivity.this, informeDataNewBases.get(i).getInformeid(), "0");
                }
                informeDataNewBases.clear();
                initData();
                break;
        }

    }
}

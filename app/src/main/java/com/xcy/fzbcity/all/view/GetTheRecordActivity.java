package com.xcy.fzbcity.all.view;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bumptech.glide.Glide;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.adapter.GetTheRecordAdapter;
import com.xcy.fzbcity.all.adapter.HousingSupermarketAdapter;
import com.xcy.fzbcity.all.api.FinalContents;
import com.xcy.fzbcity.all.modle.AppletWechatImageBean;
import com.xcy.fzbcity.all.modle.RedbagReceiveRecordBean;
import com.xcy.fzbcity.all.persente.SharItOff;
import com.xcy.fzbcity.all.service.MyService;
import com.xcy.fzbcity.all.utils.CommonUtil;
import com.xcy.fzbcity.all.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GetTheRecordActivity extends AllActivity implements View.OnClickListener {

    private RecyclerView get_the_record_recyclerview;
    private RelativeLayout get_the_record_return;
    private LinearLayout get_the_record_linear;
    private LinearLayout circle_of_friends_assistant_caption_linear;
    private View circle_of_friends_assistant_illustration_view;
    private View circle_of_friends_assistant_caption_view;
    private String redbagType = "1";    //  公司和经纪人切换
    private String timeType = "7";       //  时间选择
    private String searchName = "";     //  搜索条件
    private LinearLayout get_the_record_select_linear;
    private TextView get_the_record_select;
    private EditText get_the_record_title;
    private ImageView get_the_record_all_no_information;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_the_record);
        init_No_Network();
    }

    private void init_No_Network(){
        boolean networkAvailable = CommonUtil.isNetworkAvailable(this);
        if (networkAvailable) {
            initfvb();
        } else {
            RelativeLayout all_no_network = findViewById(R.id.get_the_record_all_no_network);
            Button all_no_reload = findViewById(R.id.get_the_record_all_no_reload);

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

    private void initfvb(){
        get_the_record_title = findViewById(R.id.get_the_record_title);
        get_the_record_select = findViewById(R.id.get_the_record_select);
        get_the_record_select_linear = findViewById(R.id.get_the_record_select_linear);
        get_the_record_linear = findViewById(R.id.get_the_record_linear);
        circle_of_friends_assistant_caption_linear = findViewById(R.id.circle_of_friends_assistant_caption_linear);
        circle_of_friends_assistant_illustration_view = findViewById(R.id.circle_of_friends_assistant_illustration_view);
        circle_of_friends_assistant_caption_view = findViewById(R.id.circle_of_friends_assistant_caption_view);
        get_the_record_recyclerview = findViewById(R.id.get_the_record_recyclerview);
        get_the_record_return = findViewById(R.id.get_the_record_return);

        get_the_record_all_no_information = findViewById(R.id.get_the_record_all_no_information);

        get_the_record_return.setOnClickListener(this);
        get_the_record_linear.setOnClickListener(this);
        circle_of_friends_assistant_caption_linear.setOnClickListener(this);
        get_the_record_select_linear.setOnClickListener(this);
        initData();

        get_the_record_title.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (KeyEvent.KEYCODE_ENTER == i && KeyEvent.ACTION_DOWN == keyEvent.getAction()) {
                    searchName = get_the_record_title.getText().toString();
                    initData();
                    return true;
                }
                return false;
            }

        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.get_the_record_return :
                //  TODO    返回
                finish();
                break;
            case R.id.get_the_record_linear :
                //  TODO    公司紅包
                circle_of_friends_assistant_illustration_view.setVisibility(View.VISIBLE);
                circle_of_friends_assistant_caption_view.setVisibility(View.INVISIBLE);
                redbagType = "1";
                initData();
                break;
            case R.id.circle_of_friends_assistant_caption_linear :
                //  TODO    经纪人红包
                circle_of_friends_assistant_illustration_view.setVisibility(View.INVISIBLE);
                circle_of_friends_assistant_caption_view.setVisibility(View.VISIBLE);
                redbagType = "2";
                initData();
                break;
            case R.id.get_the_record_select_linear :
                //  TODO   选择
                List<String> list = new ArrayList<>();
                list.add("今日");
                list.add("七日");
                list.add("全部");

                //      监听选中
                OptionsPickerView pvOptions = new OptionsPickerBuilder(GetTheRecordActivity.this, new OnOptionsSelectListener() {
                    @Override
                    public void onOptionsSelect(int options1, int option2, int options3, View v) {
                        //               返回的分别是三个级别的选中位置
                        //              展示选中数据
                        if (list.get(options1).equals("今日")) {
                            timeType = "0";
                        } else if (list.get(options1).equals("七日")){
                            timeType = "6";
                        } else if (list.get(options1).equals("全部")){
                            timeType = "7";
                        }
                        get_the_record_select.setText(list.get(options1));
                        initData();
                    }
                })
                        .setSelectOptions(0)//设置选择第一个
                        .setOutSideCancelable(false)//点击背的地方不消失
                        .build();//创建
                //      把数据绑定到控件上面
                pvOptions.setPicker(list);
                //      展示
                pvOptions.show();
                break;
        }
    }

    //      TODO    领取记录列表
    private void initData(){
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        final Observable<RedbagReceiveRecordBean> code = fzbInterface.getRedbagReceiveRecord(FinalContents.getUserID(),redbagType,timeType,searchName,"10","1");
        code.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RedbagReceiveRecordBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(RedbagReceiveRecordBean redbagReceiveRecordBean) {
                        if (redbagReceiveRecordBean.getData().getRows().size() != 0) {
                            get_the_record_all_no_information.setVisibility(View.GONE);
                            get_the_record_recyclerview.setVisibility(View.VISIBLE);
                            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(GetTheRecordActivity.this);
                            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                            get_the_record_recyclerview.setLayoutManager(linearLayoutManager);
                            GetTheRecordAdapter getTheRecordAdapter = new GetTheRecordAdapter(redbagReceiveRecordBean.getData().getRows());
                            get_the_record_recyclerview.setAdapter(getTheRecordAdapter);
                            getTheRecordAdapter.notifyDataSetChanged();
                        }else {
                            get_the_record_all_no_information.setVisibility(View.VISIBLE);
                            get_the_record_recyclerview.setVisibility(View.GONE);
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        get_the_record_all_no_information.setVisibility(View.VISIBLE);
                        get_the_record_recyclerview.setVisibility(View.GONE);
                        Log.i("领取记录列表", "领取记录列表错误信息:" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}

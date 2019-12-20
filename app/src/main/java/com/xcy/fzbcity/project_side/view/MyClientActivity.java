package com.xcy.fzbcity.project_side.view;

import android.content.Intent;
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

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.api.CityContents;
import com.xcy.fzbcity.all.api.FinalContents;
import com.xcy.fzbcity.all.api.NewlyIncreased;
import com.xcy.fzbcity.all.modle.ReportNoReadListBean;
import com.xcy.fzbcity.all.persente.MyClientData;
import com.xcy.fzbcity.all.persente.SingleClick;
import com.xcy.fzbcity.all.persente.StatusBar;
import com.xcy.fzbcity.all.service.MyService;
import com.xcy.fzbcity.all.utils.CommonUtil;
import com.xcy.fzbcity.all.utils.ToastUtil;
import com.xcy.fzbcity.all.view.AllActivity;
import com.xcy.fzbcity.all.view.MyClientAddActivity;
import com.xcy.fzbcity.project_side.fragment.MyClientFragment2;
import com.xcy.fzbcity.project_side.fragment.MyClientFragment3;
import com.xcy.fzbcity.project_side.fragment.MyClientFragment4;
import com.xcy.fzbcity.project_side.fragment.MyClientFragment5;
import com.xcy.fzbcity.project_side.fragment.MyClientFragment6;
import com.xcy.fzbcity.project_side.fragment.MyClientFragment7;

import org.greenrobot.eventbus.EventBus;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyClientActivity extends AllActivity implements View.OnClickListener {

    LinearLayout my_client_return;
    ImageView client_add;

    EditText my_client_ss;

    TextView my_client_tv;

    RelativeLayout my_client_11_2;
    RelativeLayout my_client_11_3;
    RelativeLayout my_client_11_4;
    RelativeLayout my_client_11_5;
    RelativeLayout my_client_11_6;
    RelativeLayout my_client_11_7;
    LinearLayout my_client_11_9;
    LinearLayout my_client_11_10;
    LinearLayout my_client_11_11;
    LinearLayout my_client_11_12;
    LinearLayout my_client_11_13;
    LinearLayout my_client_11_14;

    FragmentManager manager;
    FragmentTransaction transaction;

    MyClientFragment2 myClientFragment2 = new MyClientFragment2();
    MyClientFragment3 myClientFragment3 = new MyClientFragment3();
    MyClientFragment4 myClientFragment4 = new MyClientFragment4();
    MyClientFragment5 myClientFragment5 = new MyClientFragment5();
    MyClientFragment6 myClientFragment6 = new MyClientFragment6();
    MyClientFragment7 myClientFragment7 = new MyClientFragment7();
    private String client = "1";
    private ImageView client_one_key;
    private TextView my_client_unread_1;
    private TextView my_client_unread_2;
    private TextView my_client_unread_3;
    private TextView my_client_unread_4;
    private TextView my_client_unread_5;
    private TextView my_client_unread_6;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.project_side_activity_my_client);
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

        client = getIntent().getStringExtra("client");

        my_client_return = findViewById(R.id.my_client_return);
        client_add = findViewById(R.id.client_add);
        my_client_ss = findViewById(R.id.my_client_ss);
        my_client_tv = findViewById(R.id.my_client_tv);
        my_client_11_2 = findViewById(R.id.my_client_ll_2);
        my_client_11_3 = findViewById(R.id.my_client_ll_3);
        my_client_11_4 = findViewById(R.id.my_client_ll_4);
        my_client_11_5 = findViewById(R.id.my_client_ll_5);
        my_client_11_6 = findViewById(R.id.my_client_ll_6);
        my_client_11_7 = findViewById(R.id.my_client_ll_7);
        my_client_11_9 = findViewById(R.id.my_client_ll_9);
        my_client_11_10 = findViewById(R.id.my_client_ll_10);
        my_client_11_11 = findViewById(R.id.my_client_ll_11);
        my_client_11_12 = findViewById(R.id.my_client_ll_12);
        my_client_11_13 = findViewById(R.id.my_client_ll_13);
        my_client_11_14 = findViewById(R.id.my_client_ll_14);

        client_one_key = findViewById(R.id.client_one_key);

        //  TODO    客户未读
        my_client_unread_1 = findViewById(R.id.my_client_unread_1);
        my_client_unread_2 = findViewById(R.id.my_client_unread_2);
        my_client_unread_3 = findViewById(R.id.my_client_unread_3);
        my_client_unread_4 = findViewById(R.id.my_client_unread_4);
        my_client_unread_5 = findViewById(R.id.my_client_unread_5);
        my_client_unread_6 = findViewById(R.id.my_client_unread_6);

        my_client_unread_1.bringToFront();
        my_client_unread_2.bringToFront();
        my_client_unread_3.bringToFront();
        my_client_unread_4.bringToFront();
        my_client_unread_5.bringToFront();
        my_client_unread_6.bringToFront();



        if (FinalContents.getIdentity().equals("4")) {
            my_client_ss.setVisibility(View.VISIBLE);
            my_client_tv.setVisibility(View.GONE);
            client_one_key.setVisibility(View.VISIBLE);
        }else {
            my_client_ss.setVisibility(View.GONE);
            my_client_tv.setVisibility(View.VISIBLE);
            client_one_key.setVisibility(View.GONE);
        }


        myClientFragment2 = new MyClientFragment2();
        initClient();

        client_one_key.setOnClickListener(this);
        my_client_return.setOnClickListener(this);
        client_add.setOnClickListener(this);
        my_client_11_2.setOnClickListener(this);
        my_client_11_3.setOnClickListener(this);
        my_client_11_4.setOnClickListener(this);
        my_client_11_5.setOnClickListener(this);
        my_client_11_6.setOnClickListener(this);
        my_client_11_7.setOnClickListener(this);


        my_client_ss.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (KeyEvent.KEYCODE_ENTER == i && KeyEvent.ACTION_DOWN == keyEvent.getAction()) {
                    //处理事件
                    String s = my_client_ss.getText().toString();
                    if(my_client_11_9.getVisibility() == View.VISIBLE){
                        EventBus.getDefault().post(new MyClientData(s,"报备"));
                    }else if(my_client_11_10.getVisibility() == View.VISIBLE){
                        EventBus.getDefault().post(new MyClientData(s,"到访"));
                    }else if(my_client_11_11.getVisibility() == View.VISIBLE){
                        EventBus.getDefault().post(new MyClientData(s,"登岛"));
                    }else if(my_client_11_12.getVisibility() == View.VISIBLE){
                        EventBus.getDefault().post(new MyClientData(s,"认筹"));
                    }else if(my_client_11_13.getVisibility() == View.VISIBLE){
                        EventBus.getDefault().post(new MyClientData(s,"成交"));
                    }else if(my_client_11_14.getVisibility() == View.VISIBLE){
                        EventBus.getDefault().post(new MyClientData(s,"失效"));
                    }
                    return true;
                }
                return false;
            }
        });

    }

    private void initClient(){
        if (client.equals("1")) {
            my_client_11_9.setVisibility(View.VISIBLE);
            my_client_11_10.setVisibility(View.INVISIBLE);
            my_client_11_11.setVisibility(View.INVISIBLE);
            my_client_11_12.setVisibility(View.INVISIBLE);
            my_client_11_13.setVisibility(View.INVISIBLE);
            my_client_11_14.setVisibility(View.INVISIBLE);
            manager = getSupportFragmentManager();
            transaction = manager.beginTransaction();
            transaction.replace(R.id.my_client_fl, myClientFragment2);
            transaction.commit();
        }else if (client.equals("2")) {
            my_client_11_9.setVisibility(View.INVISIBLE);
            my_client_11_10.setVisibility(View.VISIBLE);
            my_client_11_11.setVisibility(View.INVISIBLE);
            my_client_11_12.setVisibility(View.INVISIBLE);
            my_client_11_13.setVisibility(View.INVISIBLE);
            my_client_11_14.setVisibility(View.INVISIBLE);
            myClientFragment3 = new MyClientFragment3();
            manager = getSupportFragmentManager();
            transaction = manager.beginTransaction();
            transaction.replace(R.id.my_client_fl, myClientFragment3);
            transaction.commit();
        }else if (client.equals("3")) {
            my_client_11_9.setVisibility(View.INVISIBLE);
            my_client_11_10.setVisibility(View.INVISIBLE);
            my_client_11_11.setVisibility(View.VISIBLE);
            my_client_11_12.setVisibility(View.INVISIBLE);
            my_client_11_13.setVisibility(View.INVISIBLE);
            my_client_11_14.setVisibility(View.INVISIBLE);
            myClientFragment4 = new MyClientFragment4();
            manager = getSupportFragmentManager();
            transaction = manager.beginTransaction();
            transaction.replace(R.id.my_client_fl, myClientFragment4);
            transaction.commit();
        }else if (client.equals("4")) {
            my_client_11_9.setVisibility(View.INVISIBLE);
            my_client_11_10.setVisibility(View.INVISIBLE);
            my_client_11_11.setVisibility(View.INVISIBLE);
            my_client_11_12.setVisibility(View.VISIBLE);
            my_client_11_13.setVisibility(View.INVISIBLE);
            my_client_11_14.setVisibility(View.INVISIBLE);
            myClientFragment5 = new MyClientFragment5();
            manager = getSupportFragmentManager();
            transaction = manager.beginTransaction();
            transaction.replace(R.id.my_client_fl, myClientFragment5);
            transaction.commit();
        }else if (client.equals("5")) {
            my_client_11_9.setVisibility(View.INVISIBLE);
            my_client_11_10.setVisibility(View.INVISIBLE);
            my_client_11_11.setVisibility(View.INVISIBLE);
            my_client_11_12.setVisibility(View.INVISIBLE);
            my_client_11_13.setVisibility(View.VISIBLE);
            my_client_11_14.setVisibility(View.INVISIBLE);
            myClientFragment6 = new MyClientFragment6();
            manager = getSupportFragmentManager();
            transaction = manager.beginTransaction();
            transaction.replace(R.id.my_client_fl, myClientFragment6);
            transaction.commit();
        }else if (client.equals("6")) {
            my_client_11_9.setVisibility(View.INVISIBLE);
            my_client_11_10.setVisibility(View.INVISIBLE);
            my_client_11_11.setVisibility(View.INVISIBLE);
            my_client_11_12.setVisibility(View.INVISIBLE);
            my_client_11_13.setVisibility(View.INVISIBLE);
            my_client_11_14.setVisibility(View.VISIBLE);
            myClientFragment7 = new MyClientFragment7();
            manager = getSupportFragmentManager();
            transaction = manager.beginTransaction();
            transaction.replace(R.id.my_client_fl, myClientFragment7);
            transaction.commit();
        }
    }

    private void initData(){
        String ProjectID = "";
        if (!CityContents.getIsRead().equals("1")) {
            ProjectID = FinalContents.getProjectID();
        }

        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<ReportNoReadListBean> clientFragment = fzbInterface.getReportNoReadList(FinalContents.getUserID(),ProjectID, NewlyIncreased.getTag(), NewlyIncreased.getStartDate(), NewlyIncreased.getEndDate(),"","");
        clientFragment.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ReportNoReadListBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ReportNoReadListBean reportNoReadListBean) {

                        if (reportNoReadListBean.getData().getReports().equals("0") || reportNoReadListBean.getData().getReports().equals("")) {//  TODO 报备
                            my_client_unread_1.setVisibility(View.INVISIBLE);
                        }else {
                            my_client_unread_1.setVisibility(View.VISIBLE);
                            my_client_unread_1.setText(reportNoReadListBean.getData().getReports());
                        }

                        if (reportNoReadListBean.getData().getAccessing().equals("0") || reportNoReadListBean.getData().getAccessing().equals("")) {//  TODO 到访
                            my_client_unread_2.setVisibility(View.INVISIBLE);
                        }else {
                            my_client_unread_2.setVisibility(View.VISIBLE);
                            my_client_unread_2.setText(reportNoReadListBean.getData().getAccessing());
                        }

                        if (reportNoReadListBean.getData().getTrade().equals("0") || reportNoReadListBean.getData().getTrade().equals("")) {//  TODO 成交
                            my_client_unread_5.setVisibility(View.INVISIBLE);
                        }else {
                            my_client_unread_5.setVisibility(View.VISIBLE);
                            my_client_unread_5.setText(reportNoReadListBean.getData().getTrade());
                        }

                        if (reportNoReadListBean.getData().getLose().equals("0") || reportNoReadListBean.getData().getLose().equals("")) {//  TODO 失效
                            my_client_unread_6.setVisibility(View.INVISIBLE);
                        }else {
                            my_client_unread_6.setVisibility(View.VISIBLE);
                            my_client_unread_6.setText(reportNoReadListBean.getData().getLose());
                        }

                        if (reportNoReadListBean.getData().getEarnest().equals("0") || reportNoReadListBean.getData().getEarnest().equals("")) {//  TODO 认筹
                            my_client_unread_4.setVisibility(View.INVISIBLE);
                        }else {
                            my_client_unread_4.setVisibility(View.VISIBLE);
                            my_client_unread_4.setText(reportNoReadListBean.getData().getEarnest());
                        }

                        if (reportNoReadListBean.getData().getIsIsland().equals("0") || reportNoReadListBean.getData().getIsIsland().equals("")) {//  TODO 登岛
                            my_client_unread_3.setVisibility(View.INVISIBLE);
                        }else {
                            my_client_unread_3.setVisibility(View.VISIBLE);
                            my_client_unread_3.setText(reportNoReadListBean.getData().getIsIsland());
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("MyCL", "未浏览错误信息" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @SingleClick(1000)
    @Override
    public void onClick(View view) {
        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();
        switch (view.getId()) {
            case R.id.my_client_return:
                FinalContents.setTiaozhuang("");
                finish();
                break;
            case R.id.client_add:
                Intent intent = new Intent(MyClientActivity.this, MyClientAddActivity.class);
                startActivity(intent);
                break;
            case R.id.my_client_ll_2:

                my_client_11_9.setVisibility(View.VISIBLE);
                my_client_11_10.setVisibility(View.INVISIBLE);
                my_client_11_11.setVisibility(View.INVISIBLE);
                my_client_11_12.setVisibility(View.INVISIBLE);
                my_client_11_13.setVisibility(View.INVISIBLE);
                my_client_11_14.setVisibility(View.INVISIBLE);
                transaction.replace(R.id.my_client_fl, myClientFragment2);
                break;
            case R.id.my_client_ll_3:

                my_client_11_9.setVisibility(View.INVISIBLE);
                my_client_11_10.setVisibility(View.VISIBLE);
                my_client_11_11.setVisibility(View.INVISIBLE);
                my_client_11_12.setVisibility(View.INVISIBLE);
                my_client_11_13.setVisibility(View.INVISIBLE);
                my_client_11_14.setVisibility(View.INVISIBLE);
                transaction.replace(R.id.my_client_fl, myClientFragment3);
                break;
            case R.id.my_client_ll_4:

                my_client_11_9.setVisibility(View.INVISIBLE);
                my_client_11_10.setVisibility(View.INVISIBLE);
                my_client_11_11.setVisibility(View.VISIBLE);
                my_client_11_12.setVisibility(View.INVISIBLE);
                my_client_11_13.setVisibility(View.INVISIBLE);
                my_client_11_14.setVisibility(View.INVISIBLE);
                transaction.replace(R.id.my_client_fl, myClientFragment4);
                break;
            case R.id.my_client_ll_5:

                my_client_11_9.setVisibility(View.INVISIBLE);
                my_client_11_10.setVisibility(View.INVISIBLE);
                my_client_11_11.setVisibility(View.INVISIBLE);
                my_client_11_12.setVisibility(View.VISIBLE);
                my_client_11_13.setVisibility(View.INVISIBLE);
                my_client_11_14.setVisibility(View.INVISIBLE);
                transaction.replace(R.id.my_client_fl, myClientFragment5);
                break;
            case R.id.my_client_ll_6:

                my_client_11_9.setVisibility(View.INVISIBLE);
                my_client_11_10.setVisibility(View.INVISIBLE);
                my_client_11_11.setVisibility(View.INVISIBLE);
                my_client_11_12.setVisibility(View.INVISIBLE);
                my_client_11_13.setVisibility(View.VISIBLE);
                my_client_11_14.setVisibility(View.INVISIBLE);
                transaction.replace(R.id.my_client_fl, myClientFragment6);
                break;
            case R.id.my_client_ll_7:

                my_client_11_9.setVisibility(View.INVISIBLE);
                my_client_11_10.setVisibility(View.INVISIBLE);
                my_client_11_11.setVisibility(View.INVISIBLE);
                my_client_11_12.setVisibility(View.INVISIBLE);
                my_client_11_13.setVisibility(View.INVISIBLE);
                my_client_11_14.setVisibility(View.VISIBLE);
                transaction.replace(R.id.my_client_fl, myClientFragment7);
                break;
            case R.id.client_one_key:
                Intent one_key_intent = new Intent(MyClientActivity.this,OneKeyActivity.class);
                startActivity(one_key_intent);
                break;
        }
        transaction.commit();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        FinalContents.setTiaozhuang("");
    }

    @Override
    protected void onResume() {
        super.onResume();


        if (FinalContents.getTiaozhuang().equals("登岛成功")) {
            myClientFragment4 = new MyClientFragment4();
            manager = getSupportFragmentManager();
            transaction = manager.beginTransaction();
            transaction.replace(R.id.my_client_fl, myClientFragment4);
            transaction.commit();

            my_client_11_9.setVisibility(View.INVISIBLE);
            my_client_11_10.setVisibility(View.INVISIBLE);
            my_client_11_11.setVisibility(View.VISIBLE);
            my_client_11_12.setVisibility(View.INVISIBLE);
            my_client_11_13.setVisibility(View.INVISIBLE);
            my_client_11_14.setVisibility(View.INVISIBLE);
        } else if (FinalContents.getTiaozhuang().equals("到访成功")) {
            myClientFragment3 = new MyClientFragment3();
            manager = getSupportFragmentManager();
            transaction = manager.beginTransaction();
            transaction.replace(R.id.my_client_fl, myClientFragment3);
            transaction.commit();

            my_client_11_9.setVisibility(View.INVISIBLE);
            my_client_11_10.setVisibility(View.VISIBLE);
            my_client_11_11.setVisibility(View.INVISIBLE);
            my_client_11_12.setVisibility(View.INVISIBLE);
            my_client_11_13.setVisibility(View.INVISIBLE);
            my_client_11_14.setVisibility(View.INVISIBLE);
        } else if (FinalContents.getTiaozhuang().equals("认筹成功")) {
            myClientFragment5 = new MyClientFragment5();
            manager = getSupportFragmentManager();
            transaction = manager.beginTransaction();
            transaction.replace(R.id.my_client_fl, myClientFragment5);
            transaction.commit();

            my_client_11_9.setVisibility(View.INVISIBLE);
            my_client_11_10.setVisibility(View.INVISIBLE);
            my_client_11_11.setVisibility(View.INVISIBLE);
            my_client_11_12.setVisibility(View.VISIBLE);
            my_client_11_13.setVisibility(View.INVISIBLE);
            my_client_11_14.setVisibility(View.INVISIBLE);
        } else if (FinalContents.getTiaozhuang().equals("失效成功")) {
            myClientFragment7 = new MyClientFragment7();
            manager = getSupportFragmentManager();
            transaction = manager.beginTransaction();
            transaction.replace(R.id.my_client_fl, myClientFragment7);
            transaction.commit();

            my_client_11_9.setVisibility(View.INVISIBLE);
            my_client_11_10.setVisibility(View.INVISIBLE);
            my_client_11_11.setVisibility(View.INVISIBLE);
            my_client_11_12.setVisibility(View.INVISIBLE);
            my_client_11_13.setVisibility(View.INVISIBLE);
            my_client_11_14.setVisibility(View.VISIBLE);
        } else if (FinalContents.getTiaozhuang().equals("成交")){
            myClientFragment6 = new MyClientFragment6();
            manager = getSupportFragmentManager();
            transaction = manager.beginTransaction();
            transaction.replace(R.id.my_client_fl, myClientFragment6);
            transaction.commit();

            my_client_11_9.setVisibility(View.INVISIBLE);
            my_client_11_10.setVisibility(View.INVISIBLE);
            my_client_11_11.setVisibility(View.INVISIBLE);
            my_client_11_12.setVisibility(View.INVISIBLE);
            my_client_11_13.setVisibility(View.VISIBLE);
            my_client_11_14.setVisibility(View.INVISIBLE);
        } else if (FinalContents.getTiaozhuang().equals("调单")){
            myClientFragment6 = new MyClientFragment6();
            manager = getSupportFragmentManager();
            transaction = manager.beginTransaction();
            transaction.replace(R.id.my_client_fl, myClientFragment6);
            transaction.commit();

            my_client_11_9.setVisibility(View.INVISIBLE);
            my_client_11_10.setVisibility(View.INVISIBLE);
            my_client_11_11.setVisibility(View.INVISIBLE);
            my_client_11_12.setVisibility(View.INVISIBLE);
            my_client_11_13.setVisibility(View.VISIBLE);
            my_client_11_14.setVisibility(View.INVISIBLE);
        }

        initData();
    }


}

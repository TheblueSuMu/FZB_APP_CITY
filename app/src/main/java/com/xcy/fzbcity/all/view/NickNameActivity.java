package com.xcy.fzbcity.all.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.api.Connector;
import com.xcy.fzbcity.all.api.FinalContents;
import com.xcy.fzbcity.all.api.NewlyIncreased;
import com.xcy.fzbcity.all.modle.ChangeNameBean;
import com.xcy.fzbcity.all.modle.GWDataBean;
import com.xcy.fzbcity.all.modle.UserBean;
import com.xcy.fzbcity.all.modle.UserMessageBean;
import com.xcy.fzbcity.all.modle.ZYDataBean;
import com.xcy.fzbcity.all.modle.ZhangBingDataBean;
import com.xcy.fzbcity.all.persente.SingleClick;
import com.xcy.fzbcity.all.persente.StatusBar;
import com.xcy.fzbcity.all.service.MyService;
import com.xcy.fzbcity.all.utils.CommonUtil;
import com.xcy.fzbcity.all.utils.ToastUtil;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NickNameActivity extends AllActivity implements View.OnClickListener {

    private RelativeLayout nick_return;
    private TextView nick_wc;
    private EditText nick_change_name;
    private Intent intent;
    private RelativeLayout nick_ensure;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nick_name);
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
        nick_return = findViewById(R.id.nick_return);
        nick_wc = findViewById(R.id.nick_wc);
        nick_ensure = findViewById(R.id.nick_ensure);
        nick_change_name = findViewById(R.id.nick_change_name);
        nick_return.setOnClickListener(this);
        nick_ensure.setOnClickListener(this);
    }

    @SingleClick(1000)
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.nick_return:
                intent = new Intent(NickNameActivity.this,PersonalInformationActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.nick_ensure:
                if (nick_change_name.getText().toString().equals("")){
                    ToastUtil.showLongToast(NickNameActivity.this,"请输入您要修改的姓名");
                    return;
                }
            {
                //TODO 处理事件
                Retrofit.Builder builder = new Retrofit.Builder();
                builder.baseUrl(FinalContents.getBaseUrl());
                builder.addConverterFactory(GsonConverterFactory.create());
                builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
                Retrofit build = builder.build();
                MyService fzbInterface = build.create(MyService.class);
                Observable<ChangeNameBean> userMessage = fzbInterface.getUpdateName(FinalContents.getUserID(),nick_change_name.getText().toString());
                userMessage.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<ChangeNameBean>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @SuppressLint("WrongConstant")
                            @Override
                            public void onNext(ChangeNameBean changeNameBean) {
                                if (changeNameBean.getData().getMessage().equals("修改昵称成功")) {
                                    ToastUtil.showToast(NickNameActivity.this, changeNameBean.getData().getMessage());
                                    if (FinalContents.getIdentity().equals("1") || FinalContents.getIdentity().equals("2") || FinalContents.getIdentity().equals("3")) {
                                        UserMessageBean userMessageBean = Connector.getUserMessageBean();
                                        UserMessageBean.DataBean dataBean = userMessageBean.getData();
                                        dataBean.setName(nick_change_name.getText().toString());
                                        userMessageBean.setData(dataBean);
                                        Connector.setUserMessageBean(userMessageBean);
                                        NewlyIncreased.setUserMessage("123");
                                    } else if (FinalContents.getIdentity().equals("4")  || FinalContents.getIdentity().equals("7")) {
                                        UserBean userMessageBean = Connector.getUserBean();
                                        UserBean.DataBean dataBean = userMessageBean.getData();
                                        dataBean.setName(nick_change_name.getText().toString());
                                        userMessageBean.setData(dataBean);
                                        Connector.setUserBean(userMessageBean);
                                        NewlyIncreased.setUserMessage("7");
                                    } else if (FinalContents.getIdentity().equals("5")) {
                                        ZYDataBean userMessageBean = Connector.getZyDataBean();
                                        ZYDataBean.DataBean dataBean = userMessageBean.getData();
                                        dataBean.setName(nick_change_name.getText().toString());
                                        userMessageBean.setData(dataBean);
                                        Connector.setZyDataBean(userMessageBean);
                                        NewlyIncreased.setUserMessage("5");
                                    }  else if (FinalContents.getIdentity().equals("8")) {
                                        ZYDataBean userMessageBean = Connector.getZyDataBean();
                                        ZYDataBean.DataBean dataBean = userMessageBean.getData();
                                        dataBean.setName(nick_change_name.getText().toString());
                                        userMessageBean.setData(dataBean);
                                        Connector.setZyDataBean(userMessageBean);
                                        NewlyIncreased.setUserMessage("8");
                                    }  else if (FinalContents.getIdentity().equals("9")) {
                                        ZYDataBean userMessageBean = Connector.getZyDataBean();
                                        ZYDataBean.DataBean dataBean = userMessageBean.getData();
                                        dataBean.setName(nick_change_name.getText().toString());
                                        userMessageBean.setData(dataBean);
                                        Connector.setZyDataBean(userMessageBean);
                                        NewlyIncreased.setUserMessage("9");
                                    } else if (FinalContents.getIdentity().equals("60")) {
                                        ZhangBingDataBean userMessageBean = Connector.getZhangBingDataBean();
                                        ZhangBingDataBean.DataBean dataBean = userMessageBean.getData();
                                        ZhangBingDataBean.DataBean.SysUserBean sysUserBean = dataBean.getSysUser();
                                        sysUserBean.setName(nick_change_name.getText().toString());
                                        dataBean.setSysUser(sysUserBean);
                                        userMessageBean.setData(dataBean);
                                        Connector.setZhangBingDataBean(userMessageBean);
                                        NewlyIncreased.setUserMessage("60");
                                    } else if (FinalContents.getIdentity().equals("61")) {
                                        GWDataBean userMessageBean = Connector.getGwDataBean();
                                        GWDataBean.DataBean dataBean = userMessageBean.getData();
                                        GWDataBean.DataBean.SysUserBean sysUserBean = dataBean.getSysUser();
                                        sysUserBean.setName(nick_change_name.getText().toString());
                                        dataBean.setSysUser(sysUserBean);
                                        userMessageBean.setData(dataBean);
                                        Connector.setGwDataBean(userMessageBean);
                                        NewlyIncreased.setUserMessage("61");
                                    } else if (FinalContents.getIdentity().equals("62")) {
                                        GWDataBean userMessageBean = Connector.getGwDataBean();
                                        GWDataBean.DataBean dataBean = userMessageBean.getData();
                                        GWDataBean.DataBean.SysUserBean sysUserBean = dataBean.getSysUser();
                                        sysUserBean.setName(nick_change_name.getText().toString());
                                        dataBean.setSysUser(sysUserBean);
                                        userMessageBean.setData(dataBean);
                                        Connector.setGwDataBean(userMessageBean);
                                        NewlyIncreased.setUserMessage("61");
                                    } else if (FinalContents.getIdentity().equals("63")) {
                                        UserBean userMessageBean = Connector.getUserBean();
                                        UserBean.DataBean dataBean = userMessageBean.getData();
                                        dataBean.setName(nick_change_name.getText().toString());
                                        userMessageBean.setData(dataBean);
                                        Connector.setUserBean(userMessageBean);
                                        NewlyIncreased.setUserMessage("63");
                                    }
                                    finish();
                                } else {
                                    ToastUtil.showToast(NickNameActivity.this, "修改昵称失败");
                                }
                            }

                            @Override
                            public void onError(Throwable e) {
                                ToastUtil.showToast(NickNameActivity.this, "修改昵称失败");
                                Log.i("修改昵称失败", "错误" + e);
                            }

                            @Override
                            public void onComplete() {

                            }
                        });
            }
            break;
        }

    }
}

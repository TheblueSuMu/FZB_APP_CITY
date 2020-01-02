package com.xcy.fzbcity.all.view;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.api.CityContents;
import com.xcy.fzbcity.all.api.FinalContents;
import com.xcy.fzbcity.all.modle.CodeBean;
import com.xcy.fzbcity.all.modle.WechatBindingBean;
import com.xcy.fzbcity.all.service.MyService;
import com.xcy.fzbcity.all.utils.CommonUtil;
import com.xcy.fzbcity.all.utils.CountDownTimerUtils;
import com.xcy.fzbcity.all.utils.ToastUtil;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WeChatBindingActivity extends AllActivity{


    private Button change_wechat_yanzhengma_1;
    private EditText change_wechat_et;
    private RelativeLayout change_wechat_return;
    private EditText change_wechat_yanzhengma;
    private Button change_wechat_ensure;

    TextView change_wechat_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_binding_wechat);
        init_No_Network();
    }

    private void init_No_Network(){
        boolean networkAvailable = CommonUtil.isNetworkAvailable(this);
        if (networkAvailable) {
            initfvb();
        } else {
            RelativeLayout all_no_network = findViewById(R.id.all_wechat_no_network);
            Button all_no_reload = findViewById(R.id.all_wechat_no_reload);

            all_no_network.setVisibility(View.VISIBLE);
            all_no_reload.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                    startActivity(getIntent());
                }
            });
            ToastUtil.showLongToast(this, "当前无网络，请检查网络后再进行登录");
        }
    }

    private void initfvb(){

        change_wechat_return = findViewById(R.id.change_wechat_return);
        change_wechat_title = findViewById(R.id.change_wechat_title);
        change_wechat_et = findViewById(R.id.change_wechat_et);
        change_wechat_yanzhengma_1 = findViewById(R.id.change_wechat_yanzhengma_1);
        change_wechat_yanzhengma = findViewById(R.id.change_wechat_yanzhengma);
        change_wechat_ensure = findViewById(R.id.change_wechat_ensure);

        if(CityContents.getWeChatType().equals("1")){
            change_wechat_title.setText("绑定微信");
        }else {
            change_wechat_title.setText("解绑微信");
        }

        change_wechat_yanzhengma_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initView();
            }
        });

        change_wechat_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        change_wechat_ensure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (change_wechat_et.getText().toString().equals("")) {
                    ToastUtil.showLongToast(WeChatBindingActivity.this, "手机号不能为空");
                    return;
                }
                if (change_wechat_yanzhengma.getText().toString().equals("")) {
                    ToastUtil.showLongToast(WeChatBindingActivity.this, "验证码不能为空");
                    return;
                }
                initData(change_wechat_et.getText().toString(),change_wechat_yanzhengma.getText().toString(), CityContents.getWeChatType(),CityContents.getWeChatJson());
            }
        });
    }

    private void initView(){
        if (change_wechat_et.getText().toString().equals("")) {
            ToastUtil.showLongToast(WeChatBindingActivity.this, "手机号不能为空");
            return;
        } else {
            Retrofit.Builder builder = new Retrofit.Builder();
            builder.baseUrl(FinalContents.getBaseUrl());
            builder.addConverterFactory(GsonConverterFactory.create());
            builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
            Retrofit build = builder.build();
            MyService fzbInterface = build.create(MyService.class);
            Observable<CodeBean> userMessage = fzbInterface.getSendCode(change_wechat_et.getText().toString());
            userMessage.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<CodeBean>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(CodeBean codeBean) {
                            if(codeBean.getData().getStatus().equals("0")){
                                ToastUtil.showLongToast(WeChatBindingActivity.this, codeBean.getData().getMessage());
                            }else if(codeBean.getData().getStatus().equals("1")){
                                CountDownTimerUtils mCountDownTimerUtils = new CountDownTimerUtils(change_wechat_yanzhengma_1, 60000, 1000);
                                mCountDownTimerUtils.start();
                                ToastUtil.showLongToast(WeChatBindingActivity.this, codeBean.getData().getMessage());
                            }

                        }

                        @Override
                        public void onError(Throwable e) {
                            ToastUtil.showLongToast(WeChatBindingActivity.this, "您输入的手机号有误");
                            Log.i("wsw", "返回的数据" + e.getMessage());
                        }

                        @Override
                        public void onComplete() {

                        }
                    });
        }
    }


    private void initData(String userphone, String captcha, String type,String json) {
        Retrofit.Builder builder1 = new Retrofit.Builder();
        builder1.baseUrl(FinalContents.getBaseUrl());
        builder1.addConverterFactory(GsonConverterFactory.create());
        builder1.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder1.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<WechatBindingBean> userMessage = fzbInterface.getWechatBinding(FinalContents.getUserID(),userphone+"",captcha+"",type+"",json);
        userMessage.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<WechatBindingBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(WechatBindingBean wechatBindingBean) {
                        if (wechatBindingBean.getData().getMessage().equals("绑定微信成功")) {
                            ToastUtil.showLongToast(WeChatBindingActivity.this,wechatBindingBean.getData().getMessage());
                            finish();
                        } else if (wechatBindingBean.getData().getMessage().equals("解绑微信成功")) {
                            ToastUtil.showLongToast(WeChatBindingActivity.this,wechatBindingBean.getData().getMessage());
                            finish();
                        }else {
                            ToastUtil.showLongToast(WeChatBindingActivity.this,wechatBindingBean.getData().getMessage());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        ToastUtil.showLongToast(WeChatBindingActivity.this, "您输入的手机号或验证码有误");
                        Log.i("wsw", "返回的数据" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}

package com.xcy.fzbcity.all.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.api.CityContents;
import com.xcy.fzbcity.all.api.FinalContents;
import com.xcy.fzbcity.all.modle.WechatBindingBean;
import com.xcy.fzbcity.all.persente.SingleClick;
import com.xcy.fzbcity.all.persente.StatusBar;
import com.xcy.fzbcity.all.service.MyService;
import com.xcy.fzbcity.all.utils.CommonUtil;
import com.xcy.fzbcity.all.utils.ToastUtil;

import org.json.JSONObject;

import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.wechat.friends.Wechat;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ToLoginActivity extends AllActivity implements View.OnClickListener{

    private RelativeLayout to_return;
    RelativeLayout to_login_rl;
    ImageView to_login_img_1;
    ImageView to_login_img_2;
    ImageView to_login_img_3;
    ImageView to_login_img_4;
    private String s;
    private boolean login = false;
    private JSONObject json = new JSONObject();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_login);
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
        StatusBar.makeStatusBarTransparent(ToLoginActivity.this);

        to_return = findViewById(R.id.to_return);
        to_login_rl = findViewById(R.id.to_login_rl);
        to_login_img_1 = findViewById(R.id.to_login_img_1);
        to_login_img_2 = findViewById(R.id.to_login_img_2);
        to_login_img_3 = findViewById(R.id.to_login_img_3);
        to_login_img_4 = findViewById(R.id.to_login_img_4);



        to_return.setOnClickListener(this);
        to_login_rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (to_login_img_1.getVisibility() == View.GONE) {
                    //TODO 绑定
                    Platform plat = ShareSDK.getPlatform(Wechat.NAME);
                    plat.removeAccount(true); //移除授权状态和本地缓存，下次授权会重新授权
                    plat.SSOSetting(false); //SSO授权，传false默认是客户端授权，没有客户端授权或者不支持客户端授权会跳web授权
                    plat.setPlatformActionListener(new PlatformActionListener() {
                        @Override
                        public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                            login = true;
                            Log.i("json","授权成功");
                            json = new JSONObject(hashMap);
                            Log.i("json","授权成功"+ json.toString());
                            CityContents.setWeChatType("1");
                            CityContents.setWeChatJson(json.toString());
                            Intent intent = new Intent(ToLoginActivity.this,WeChatBindingActivity.class);
                            startActivity(intent);
                        }

                        @Override
                        public void onError(Platform platform, int i, Throwable throwable) {
                            ToastUtil.showToast(ToLoginActivity.this, "授权失败");
                            Log.i("json","授权失败"+throwable.getMessage());
                        }

                        @Override
                        public void onCancel(Platform platform, int i) {
                            Log.i("json","授权取消");
                            ToastUtil.showToast(ToLoginActivity.this, "授权取消");
                        }
                    });//授权回调监听，监听oncomplete，onerror，oncancel三种状态


                    if (plat.isClientValid()) {
                        //判断是否存在授权凭条的客户端，true是有客户端，false是无
                    }
                    if (plat.isAuthValid()) {
                        //判断是否已经存在授权状态，可以根据自己的登录逻辑设置
                        ToastUtil.showToast(ToLoginActivity.this, "已经授权过了");
                        return;
                    } else {
                        ToastUtil.showToast(ToLoginActivity.this, "正在授权");
                    }
                    ShareSDK.setActivity(ToLoginActivity.this);//抖音登录适配安卓9.0
                    plat.showUser(null);    //要数据不要功能，主要体现在不会重复出现授权界面
                } else {
//TODO 解除绑定
                    CityContents.setWeChatType("0");
                    CityContents.setWeChatJson("");
                    Intent intent = new Intent(ToLoginActivity.this,WeChatBindingActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    private void init(){
        Retrofit.Builder builder1 = new Retrofit.Builder();
        builder1.baseUrl(FinalContents.getBaseUrl());
        builder1.addConverterFactory(GsonConverterFactory.create());
        builder1.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder1.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<WechatBindingBean> userMessage = fzbInterface.getIsWeChat(FinalContents.getUserID());
        userMessage.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<WechatBindingBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(WechatBindingBean wechatBindingBean) {
                        if (wechatBindingBean.getData().getMessage().equals("1")) {
                            to_login_img_1.setVisibility(View.VISIBLE);
                            to_login_img_2.setVisibility(View.GONE);
                            to_login_img_3.setVisibility(View.VISIBLE);
                            to_login_img_4.setVisibility(View.GONE);
                        }else if (wechatBindingBean.getData().getMessage().equals("0")){
                            to_login_img_1.setVisibility(View.GONE);
                            to_login_img_2.setVisibility(View.VISIBLE);
                            to_login_img_3.setVisibility(View.GONE);
                            to_login_img_4.setVisibility(View.VISIBLE);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("wsw", "查询是否绑定微信返回的数据" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @SuppressLint("InflateParams")
    @SingleClick(1000)
    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.to_return:
                finish();
                break;
        }

    }
    @Override
    protected void onResume() {
        super.onResume();
        init();
    }
}

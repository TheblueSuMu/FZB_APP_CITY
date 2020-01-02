package com.xcy.fzbcity.all.view;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.api.FinalContents;
import com.xcy.fzbcity.all.modle.CodeBean;
import com.xcy.fzbcity.all.modle.ForgetBean;
import com.xcy.fzbcity.all.persente.SingleClick;
import com.xcy.fzbcity.all.persente.StatusBar;
import com.xcy.fzbcity.all.service.MyService;
import com.xcy.fzbcity.all.utils.CommonUtil;
import com.xcy.fzbcity.all.utils.CountDownTimerUtils;
import com.xcy.fzbcity.all.utils.MatcherUtils;
import com.xcy.fzbcity.all.utils.ToastUtil;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ForgetActivity extends AllActivity implements View.OnClickListener {
    private EditText phone;
    private EditText code;
    private EditText password;
    private Button getCode;
    private TextView ensure;
    private RelativeLayout binding_return;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget);
        init_No_Network();
    }

    private void init_No_Network(){
        boolean networkAvailable = CommonUtil.isNetworkAvailable(this);
        if (networkAvailable) {
            initfvb();
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

    private void initfvb(){
        StatusBar.makeStatusBarTransparent(this);
        binding_return = findViewById(R.id.binding_return);
        phone = findViewById(R.id.forget_phone);
        password = findViewById(R.id.forget_password);
        code = findViewById(R.id.forget_code);
        getCode = findViewById(R.id.forget_code_get);
        ensure = findViewById(R.id.forget_ensure);
        binding_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        getCode.setOnClickListener(this);
        ensure.setOnClickListener(this);
    }

    private void initEnsure(){
        if (code.getText().toString().equals("")) {
            ToastUtil.showToast(this, "请输入验证码");
            return;
        }
        if (password.getText().toString().equals("")) {
            ToastUtil.showToast(this, "请输入新密码");
            return;
        }
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<ForgetBean> userMessage = fzbInterface.getFindBackPassword(code.getText().toString(),phone.getText().toString(),password.getText().toString());
        userMessage.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ForgetBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ForgetBean forgetBean) {
                        ToastUtil.showToast(ForgetActivity.this, forgetBean.getData().getMessage());
                        if (forgetBean.getData().getMessage().equals("密码修改成功，请牢记！")) {
                            finish();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        ToastUtil.showToast(ForgetActivity.this, "您输入的信息有误");
                        Log.i("wsw", "返回的数据" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    /**
     * 发送验证码
     */
    @SuppressLint("ShowToast")
    private void sendcode(){

        if (phone.getText().toString().equals("")) {
            ToastUtil.showToast(this, "请输入手机号");
            return;
        }
        if (!MatcherUtils.isMobile(phone.getText().toString())) {
            ToastUtil.showToast(this, "请输入正确的手机号");
            return;
        }else {
        }
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<CodeBean> userMessage = fzbInterface.getSendCode(phone.getText().toString());
        userMessage.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CodeBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(CodeBean codeBean) {
                        if(codeBean.getData().getStatus().equals("0")){
                            ToastUtil.showToast(ForgetActivity.this, codeBean.getData().getMessage());
                        }else if(codeBean.getData().getStatus().equals("1")){
                            CountDownTimerUtils mCountDownTimerUtils = new CountDownTimerUtils(getCode, 60000, 1000);
                            mCountDownTimerUtils.start();
                            ToastUtil.showToast(ForgetActivity.this, codeBean.getData().getMessage());
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        ToastUtil.showToast(ForgetActivity.this, "您输入的手机号有误");
                        Log.i("wsw", "返回的数据" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @SingleClick(1000)
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.forget_code_get:
                if (!MatcherUtils.isMobile(phone.getText().toString())) {
                    ToastUtil.showToast(this, "请输入正确的手机号");
                    return;
                } else {
                    sendcode();
                }

                break;
            case R.id.forget_ensure:
                initEnsure();
                break;
        }
    }
}

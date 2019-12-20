package com.xcy.fzbcity.project_side.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.api.FinalContents;
import com.xcy.fzbcity.all.modle.CBean;
import com.xcy.fzbcity.all.persente.StatusBar;
import com.xcy.fzbcity.all.service.MyService;
import com.xcy.fzbcity.all.utils.CommonUtil;
import com.xcy.fzbcity.all.utils.ToastUtil;
import com.xcy.fzbcity.all.view.AllActivity;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//TODO 拒绝原因
public class TheReasonForRefusalActivity extends AllActivity {

    RelativeLayout the_reason_for_refusal_return;

    EditText the_reason_for_refusal_et;

    Button the_reason_for_refusal_btn;
    private String message;
    private String url;

    int isnum = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.project_side_activity_the_reason_for_refusal);
        init_No_Network();
    }

    private void init_No_Network() {
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

        the_reason_for_refusal_return = findViewById(R.id.the_reason_for_refusal_return);
        the_reason_for_refusal_et = findViewById(R.id.the_reason_for_refusal_et);
        the_reason_for_refusal_btn = findViewById(R.id.the_reason_for_refusal_btn);

        the_reason_for_refusal_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        the_reason_for_refusal_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isnum == 0){
                    isnum = 1;
                    message = the_reason_for_refusal_et.getText().toString();
                    if (message.equals("")) {
                        ToastUtil.showToast(TheReasonForRefusalActivity.this, "请填写拒绝原因");
                    } else {

                        Retrofit.Builder builder = new Retrofit.Builder();
                        builder.baseUrl(FinalContents.getBaseUrl());
                        builder.addConverterFactory(GsonConverterFactory.create());
                        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
                        Retrofit build = builder.build();
                        MyService fzbInterface = build.create(MyService.class);
                        Observable<CBean> clientFragmentBean = fzbInterface.getReportAndVisitAudit(FinalContents.getUserID(), FinalContents.getPreparationId(), FinalContents.getStatus(), "2",message);
                        clientFragmentBean.subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(new Observer<CBean>() {
                                    @Override
                                    public void onSubscribe(Disposable d) {

                                    }

                                    @Override
                                    public void onNext(CBean cBean) {
                                        if (cBean.getMsg().equals("成功")) {
                                            ToastUtil.showToast(TheReasonForRefusalActivity.this, cBean.getData().getMessage());
                                            FinalContents.setEndStart("成功");
                                            the_reason_for_refusal_et.setText("");
                                            Intent intent = new Intent(TheReasonForRefusalActivity.this, CheckPendingTheProjectActivity.class);
                                            startActivity(intent);
                                            finish();
                                        } else {
                                            ToastUtil.showToast(TheReasonForRefusalActivity.this, cBean.getData().getMessage());
                                        }
                                    }

                                    @Override
                                    public void onError(Throwable e) {
                                        Log.i("MyCL", "错误信息：" + e.getMessage());
                                    }

                                    @Override
                                    public void onComplete() {

                                    }
                                });

                    }
                    isnum = 0;
                }


            }
        });

    }
}

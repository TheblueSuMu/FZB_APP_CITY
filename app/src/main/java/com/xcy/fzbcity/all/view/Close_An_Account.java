package com.xcy.fzbcity.all.view;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.xcy.fzbcity.Login.LoginActivity;
import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.api.FinalContents;
import com.xcy.fzbcity.all.database.TeamMemberBean;
import com.xcy.fzbcity.all.modle.UpdateLoginFlag;
import com.xcy.fzbcity.all.persente.CleanDataUtils;
import com.xcy.fzbcity.all.persente.ContactModel;
import com.xcy.fzbcity.all.service.MyService;
import com.xcy.fzbcity.all.utils.ToastUtil;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Close_An_Account extends AllActivity{

    private RelativeLayout close_an_account_return;
    private Button close_an_account_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.close_an_account);
        init();
    }

    private void init(){
        close_an_account_return = findViewById(R.id.close_an_account_return);
        close_an_account_button = findViewById(R.id.close_an_account_button);
        close_an_account_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        close_an_account_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder2 = new AlertDialog.Builder(Close_An_Account.this);
                View inflate2 = LayoutInflater.from(Close_An_Account.this).inflate(R.layout.binding_report, null, false);
                builder2.setView(inflate2);
                final AlertDialog show2 = builder2.show();
                show2.getWindow().setBackgroundDrawableResource(R.drawable.report_shape);
                WindowManager m2 = Close_An_Account.this.getWindowManager();
                Display d2 = m2.getDefaultDisplay();
                WindowManager.LayoutParams attributes2 = show2.getWindow().getAttributes();
                attributes2.width = (int)(d2.getWidth() - 200);
                show2.getWindow().setAttributes(attributes2);
                show2.getWindow().setBackgroundDrawableResource(R.drawable.report_shape);
                TextView report_binding_title2 = inflate2.findViewById(R.id.report_binding_title);
                TextView report_binding_confirm_tv2 = inflate2.findViewById(R.id.report_binding_confirm_tv);
                TextView report_binding_cancel_tv2 = inflate2.findViewById(R.id.report_binding_cancel_tv);
                RelativeLayout report_binding_cancel2 = inflate2.findViewById(R.id.report_binding_cancel);
                RelativeLayout report_binding_confirm2 = inflate2.findViewById(R.id.report_binding_confirm);
                report_binding_title2.setText("确认注销账号吗?");//内容
                report_binding_confirm_tv2.setText("确定");
                report_binding_cancel_tv2.setText("取消");
                report_binding_title2.setTextColor(Color.parseColor("#111111"));
                report_binding_cancel_tv2.setTextColor(Color.parseColor("#334485"));
                report_binding_confirm_tv2.setTextColor(Color.parseColor("#334485"));
                report_binding_cancel2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        show2.dismiss();
                    }
                });
                report_binding_confirm2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        initData();
                    }
                });

            }
        });
    }


    private void initData(){
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<UpdateLoginFlag> teamMemberBeane = fzbInterface.getUpdateLoginFlag(FinalContents.getUserID(),FinalContents.getUserID());
        teamMemberBeane.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UpdateLoginFlag>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(UpdateLoginFlag updateLoginFlag) {
                        FinalContents.setIFSP("1");
                        FinalContents.setDengLu("0");
                        ToastUtil.showLongToast(Close_An_Account.this,updateLoginFlag.getData().getMessage());
                        Intent intent = new Intent(Close_An_Account.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("MyCL", "UpdateLoginFlag错误信息：" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}

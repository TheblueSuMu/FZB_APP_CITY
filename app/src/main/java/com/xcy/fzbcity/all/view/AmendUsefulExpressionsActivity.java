package com.xcy.fzbcity.all.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.api.FinalContents;
import com.xcy.fzbcity.all.modle.AddLanguageBean;
import com.xcy.fzbcity.all.modle.AmendUsefulExpressionsBean;
import com.xcy.fzbcity.all.persente.StatusBar;
import com.xcy.fzbcity.all.service.MyService;
import com.xcy.fzbcity.all.utils.ToastUtil;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AmendUsefulExpressionsActivity extends AllActivity implements View.OnClickListener {

    RelativeLayout amend_useful_expressions_return;
    TextView amend_useful_expressions_et_title;
    TextView amend_useful_expressions_bc;
    TextView amend_useful_expressions_tv;
    EditText amend_useful_expressions_et;
    private String languageId;
    private String languageContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_amend_useful_expressions);
        StatusBar.makeStatusBarTransparent(this);

        initDataView();

    }

    private void initDataView() {

        languageId = getIntent().getStringExtra("languageId");
        languageContent = getIntent().getStringExtra("languageContent");

        amend_useful_expressions_return = findViewById(R.id.amend_useful_expressions_return);
        amend_useful_expressions_et_title = findViewById(R.id.amend_useful_expressions_et_title);
        amend_useful_expressions_bc = findViewById(R.id.amend_useful_expressions_bc);
        amend_useful_expressions_tv = findViewById(R.id.amend_useful_expressions_tv);
        amend_useful_expressions_et = findViewById(R.id.amend_useful_expressions_et);

        amend_useful_expressions_et.setText(languageContent);

        if(languageId.equals("")){
            amend_useful_expressions_et_title.setText("添加常用语");
        }else {
            amend_useful_expressions_et_title.setText("修改常用语");
        }

        amend_useful_expressions_return.setOnClickListener(this);
        amend_useful_expressions_bc.setOnClickListener(this);

        amend_useful_expressions_et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.i("常用语","afterTextChanged：" + s.toString());
                amend_useful_expressions_tv.setText(s.length() + "/100");
            }
        });

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.amend_useful_expressions_return:
                finish();
                break;
            case R.id.amend_useful_expressions_bc:
                if(languageId.equals("")){
                    initAddBC();
                }else {
                    initChangeBC();
                }
                break;
        }

    }

    private void initAddBC() {

        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Log.i("修改常用语","languageId:" + languageId);
        Log.i("修改常用语","amend_useful_expressions_et.getText().toString():" + amend_useful_expressions_et.getText().toString());
        Observable<AddLanguageBean> AddLanguageBean = fzbInterface.getAddLanguageBean(FinalContents.getUserID(),amend_useful_expressions_et.getText().toString());
        AddLanguageBean.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<AddLanguageBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(AddLanguageBean addLanguageBean) {
                        if(addLanguageBean.getData().getMessageCode().equals("1")){
                            ToastUtil.showLongToast(com.xcy.fzbcity.all.view.AmendUsefulExpressionsActivity.this,addLanguageBean.getData().getMessage());
                            finish();
                        }else{
                            ToastUtil.showLongToast(com.xcy.fzbcity.all.view.AmendUsefulExpressionsActivity.this,addLanguageBean.getData().getMessage());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    private void initChangeBC() {

        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Log.i("修改常用语","languageId:" + languageId);
        Log.i("修改常用语","amend_useful_expressions_et.getText().toString():" + amend_useful_expressions_et.getText().toString());
        Observable<AmendUsefulExpressionsBean> AmendUsefulExpressionsActivity = fzbInterface.getAmendUsefulExpressionsBean(languageId,amend_useful_expressions_et.getText().toString());
        AmendUsefulExpressionsActivity.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<AmendUsefulExpressionsBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(AmendUsefulExpressionsBean amendUsefulExpressionsBean) {
                        if(amendUsefulExpressionsBean.getData().getMessageCode().equals("1")){
                            ToastUtil.showLongToast(com.xcy.fzbcity.all.view.AmendUsefulExpressionsActivity.this,amendUsefulExpressionsBean.getData().getMessage());
                            finish();
                        }else{
                            ToastUtil.showLongToast(com.xcy.fzbcity.all.view.AmendUsefulExpressionsActivity.this,amendUsefulExpressionsBean.getData().getMessage());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("修改常用语","错误信息:" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
}

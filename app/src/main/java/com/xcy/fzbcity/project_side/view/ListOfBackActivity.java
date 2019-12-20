package com.xcy.fzbcity.project_side.view;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.api.FinalContents;
import com.xcy.fzbcity.all.modle.ListOfBean;
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

//TODO 填写退单说明
public class ListOfBackActivity extends AllActivity {

    RelativeLayout list_of_back_return;

    EditText list_of_back_et;

    Button list_of_back_btn;
    private String message;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.project_side_activity_list_of_back);
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

        list_of_back_return = findViewById(R.id.list_of_back_return);
        list_of_back_et = findViewById(R.id.list_of_back_et);
        list_of_back_btn = findViewById(R.id.list_of_back_btn);

        list_of_back_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        list_of_back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                message = list_of_back_et.getText().toString();
                if (message.equals("")) {
                    ToastUtil.showToast(ListOfBackActivity.this, "请填写退单说明内容");
                    return;
                }else {
                    Retrofit.Builder builder = new Retrofit.Builder();
                    builder.baseUrl(FinalContents.getBaseUrl());
                    builder.addConverterFactory(GsonConverterFactory.create());
                    builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
                    Retrofit build = builder.build();
                    MyService fzbInterface = build.create(MyService.class);
                    Observable<ListOfBean> userMessage = fzbInterface.getChargebackApply(FinalContents.getUserID(),FinalContents.getPreparationId(),message);
                    userMessage.subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Observer<ListOfBean>() {
                                @Override
                                public void onSubscribe(Disposable d) {

                                }

                                @SuppressLint("WrongConstant")
                                @Override
                                public void onNext(ListOfBean listOfBean) {
                                    if(listOfBean.getMsg().equals("成功")){
                                        ToastUtil.showToast(ListOfBackActivity.this,listOfBean.getData().getMessage());
                                        finish();
                                    }else {
                                        ToastUtil.showToast(ListOfBackActivity.this,listOfBean.getData().getMessage());
                                    }
                                }

                                @Override
                                public void onError(Throwable e) {
                                    Log.i("填写退单说明","错误"+e);
                                }

                                @Override
                                public void onComplete() {

                                }
                            });
                }



            }
        });


    }
}

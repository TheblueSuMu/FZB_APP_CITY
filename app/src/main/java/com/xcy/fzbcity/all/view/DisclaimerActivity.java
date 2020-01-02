package com.xcy.fzbcity.all.view;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.api.FinalContents;
import com.xcy.fzbcity.all.modle.DisclaimerBean;
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

//TODO 免责声明
public class DisclaimerActivity extends AllActivity implements View.OnClickListener {

    RelativeLayout disclaimer_return;
    TextView disclaimer_title;
    TextView disclaimer_message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disclaimer);
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
            ToastUtil.showToast(this,"当前无网络，请检查网络后再进行登录");
        }
    }

    private void initView() {

        StatusBar.makeStatusBarTransparent(this);

        disclaimer_return = findViewById(R.id.disclaimer_return);
        disclaimer_title = findViewById(R.id.disclaimer_title);
        disclaimer_message = findViewById(R.id.disclaimer_message);

        disclaimer_return.setOnClickListener(this);


        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<DisclaimerBean> disclaimer = fzbInterface.getDisclaimer("1", FinalContents.getUserID());
        disclaimer.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DisclaimerBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(DisclaimerBean disclaimerBean) {
                        DisclaimerBean.DataBean data = disclaimerBean.getData();
                        disclaimer_title.setText(data.getTilte());
                        String content = data.getContent();
                        StringBuffer stringBuffer = new StringBuffer();
                        stringBuffer.append(content);
                        for (int i = 0; i < stringBuffer.length(); ++i) {
                            if (stringBuffer.substring(i, i + 1).equals("<")) {
                                stringBuffer.replace(i, i + 1, " ");
                            } else if (stringBuffer.substring(i, i + 1).equals(">")) {
                                stringBuffer.replace(i, i + 1, " ");
                            } else if (stringBuffer.substring(i, i + 1).equals("/")) {
                                stringBuffer.replace(i, i + 1, " ");
                            }else if (stringBuffer.substring(i, i + 1).equals("p")) {
                                stringBuffer.replace(i, i + 1, " ");
                            } else if (stringBuffer.substring(i, i + 1).equals("d")) {
                                stringBuffer.replace(i, i + 1, " ");
                            } else if (stringBuffer.substring(i, i + 1).equals("i")) {
                                stringBuffer.replace(i, i + 1, " ");
                            } else if (stringBuffer.substring(i, i + 1).equals("v")) {
                                stringBuffer.replace(i, i + 1, " ");
                            } else if (stringBuffer.substring(i, i + 1).equals("b")) {
                                stringBuffer.replace(i, i + 1, " ");
                            } else if (stringBuffer.substring(i, i + 1).equals("r")) {
                                stringBuffer.replace(i, i + 1, "\n");
                            }else if (stringBuffer.substring(i, i + 1).equals("免")) {
                                stringBuffer.replace(i-4, i, "\n");
                            }
                        }
                        disclaimer_message.setText("\t\t\t\t" + stringBuffer.toString());

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("MyCL", "免责声明错误信息" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @SingleClick(1000)
    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.disclaimer_return:
                finish();
                break;
        }

    }
}
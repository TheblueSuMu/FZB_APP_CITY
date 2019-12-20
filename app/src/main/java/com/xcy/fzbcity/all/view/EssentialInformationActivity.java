package com.xcy.fzbcity.all.view;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.api.FinalContents;
import com.xcy.fzbcity.all.api.ProjectProgressApi;
import com.xcy.fzbcity.all.database.ColleagueBean;
import com.xcy.fzbcity.all.database.FieldBean;
import com.xcy.fzbcity.all.modle.LandBean;
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

//TODO 用戶基本信息描摹
public class EssentialInformationActivity extends AllActivity {

    RelativeLayout essential_information_return;

    EditText essential_information_et1;
    EditText essential_information_et2;
    EditText essential_information_et3;
    EditText essential_information_et4;
    EditText essential_information_et5;
    EditText essential_information_et6;
    EditText essential_information_et7;
    EditText essential_information_et8;

    Button essential_information_tbn;

    RadioButton essential_information_rb1;
    RadioButton essential_information_rb2;

    RadioGroup essential_information_rg;
    private String et1;
    private String et3;
    private String et4;
    private String et5;
    private String et6;
    private String et2;
    private String et7;
    private String et8;
    private String authority = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_essential_information);
        init_No_Network();
    }

    private void init_No_Network(){
        boolean networkAvailable = CommonUtil.isNetworkAvailable(this);
        if (networkAvailable) {
            initView();
            if (ProjectProgressApi.getComplemented().equals("0")) {
                if (ProjectProgressApi.getField().equals("1")) {
                    initSQDDTXR();
                }else if (ProjectProgressApi.getField().equals("0")){
                    initSQDD();
                }
            } else if (ProjectProgressApi.getComplemented().equals("1")) {
                if (ProjectProgressApi.getField().equals("0")) {
                    initComplemented();
                }else if (ProjectProgressApi.getField().equals("1")){
                    initData();
                }
            }
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

        essential_information_return = findViewById(R.id.essential_information_return);

        essential_information_et1 = findViewById(R.id.essential_information_et1);
        essential_information_et2 = findViewById(R.id.essential_information_et2);
        essential_information_et3 = findViewById(R.id.essential_information_et3);
        essential_information_et4 = findViewById(R.id.essential_information_et4);
        essential_information_et5 = findViewById(R.id.essential_information_et5);
        essential_information_et6 = findViewById(R.id.essential_information_et6);
        essential_information_et7 = findViewById(R.id.essential_information_et7);
        essential_information_et8 = findViewById(R.id.essential_information_et8);

        essential_information_tbn = findViewById(R.id.essential_information_btn);

        essential_information_rb1 = findViewById(R.id.essential_information_rb1);
        essential_information_rb2 = findViewById(R.id.essential_information_rb2);

        essential_information_rg = findViewById(R.id.essential_information_rg);

        essential_information_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        essential_information_tbn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                et1 = essential_information_et1.getText().toString();
                et2 = essential_information_et2.getText().toString();
                et3 = essential_information_et3.getText().toString();
                et4 = essential_information_et4.getText().toString();
                et5 = essential_information_et5.getText().toString();
                et6 = essential_information_et6.getText().toString();
                et7 = essential_information_et7.getText().toString();
                et8 = essential_information_et8.getText().toString();

                if(essential_information_rb1.isChecked() == true){
                    authority = "是";
                }else if(essential_information_rb2.isChecked() == true){
                    authority = "否";
                }

                if (ProjectProgressApi.getComplemented().equals("0")){
                    if (ProjectProgressApi.getField().equals("1")) {
                        FieldBean fieldBean = ProjectProgressApi.getFieldBean();
                        fieldBean.setCity(essential_information_et1.getText().toString());
                        fieldBean.setOccupation(essential_information_et2.getText().toString());
                        fieldBean.setFocus(essential_information_et3.getText().toString());
                        fieldBean.setIntentionalBuilding(essential_information_et4.getText().toString());
                        fieldBean.setPaymentMethod(essential_information_et5.getText().toString());
                        fieldBean.setHasDecision(authority);
                        fieldBean.setResistance(essential_information_et6.getText().toString());
                        fieldBean.setObjective(essential_information_et7.getText().toString());
                        fieldBean.setIdealArea(essential_information_et8.getText().toString());
                        ProjectProgressApi.setFieldBean(fieldBean);
                    }else if (ProjectProgressApi.getField().equals("0")){
                        ProjectProgressApi.setCustomerCity(essential_information_et1.getText().toString());
                        ProjectProgressApi.setCustomerOccupation(essential_information_et2.getText().toString());
                        ProjectProgressApi.setCustomerFocus(essential_information_et3.getText().toString());
                        ProjectProgressApi.setCustomerIntentionalBuilding(essential_information_et4.getText().toString());
                        ProjectProgressApi.setCustomerPaymentMethod(essential_information_et5.getText().toString());
                        ProjectProgressApi.setCustomerHasDecision(authority);
                        ProjectProgressApi.setCustomerResistance(essential_information_et6.getText().toString());
                        ProjectProgressApi.setCustomerObjective(essential_information_et7.getText().toString());
                        ProjectProgressApi.setCustomerAuditStatus(essential_information_et8.getText().toString());
                    }
                }else if (ProjectProgressApi.getComplemented().equals("1")){
                    if (ProjectProgressApi.getField().equals("1")) {
                        FieldBean fieldBean = ProjectProgressApi.getFieldBean();
                        fieldBean.setCity(essential_information_et1.getText().toString());
                        fieldBean.setOccupation(essential_information_et2.getText().toString());
                        fieldBean.setFocus(essential_information_et3.getText().toString());
                        fieldBean.setIntentionalBuilding(essential_information_et4.getText().toString());
                        fieldBean.setPaymentMethod(essential_information_et5.getText().toString());
                        fieldBean.setHasDecision(authority);
                        fieldBean.setResistance(essential_information_et6.getText().toString());
                        fieldBean.setObjective(essential_information_et7.getText().toString());
                        fieldBean.setIdealArea(essential_information_et8.getText().toString());
                        ProjectProgressApi.setFieldBean(fieldBean);
                    }else if (ProjectProgressApi.getField().equals("0")){
                        ProjectProgressApi.setCustomerCity(essential_information_et1.getText().toString());
                        ProjectProgressApi.setCustomerOccupation(essential_information_et2.getText().toString());
                        ProjectProgressApi.setCustomerFocus(essential_information_et3.getText().toString());
                        ProjectProgressApi.setCustomerIntentionalBuilding(essential_information_et4.getText().toString());
                        ProjectProgressApi.setCustomerPaymentMethod(essential_information_et5.getText().toString());
                        ProjectProgressApi.setCustomerHasDecision(authority);
                        ProjectProgressApi.setCustomerResistance(essential_information_et6.getText().toString());
                        ProjectProgressApi.setCustomerObjective(essential_information_et7.getText().toString());
                        ProjectProgressApi.setCustomerAuditStatus(essential_information_et8.getText().toString());
                    }
                }
                finish();
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
        Observable<ColleagueBean> clientFragmentBean = fzbInterface.getColleague(FinalContents.getUserID(), ProjectProgressApi.getFieldID());
        clientFragmentBean.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ColleagueBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ColleagueBean colleagueBean) {
                        essential_information_et1.setText(colleagueBean.getData().getCity());
                        essential_information_et2.setText(colleagueBean.getData().getOccupation());
                        essential_information_et3.setText(colleagueBean.getData().getFocus());
                        essential_information_et4.setText(colleagueBean.getData().getIntentionalBuilding());
                        essential_information_et5.setText(colleagueBean.getData().getPaymentMethod());
                        authority = colleagueBean.getData().getHasDecision();

                        if(authority.equals("是")){
                            essential_information_rb1.setChecked(true);
                        }else if(authority.equals("否")){
                            essential_information_rb2.setChecked(true);
                        }

                        essential_information_et6.setText(colleagueBean.getData().getResistance());
                        essential_information_et7.setText(colleagueBean.getData().getObjective());
                        essential_information_et8.setText(colleagueBean.getData().getIdealArea());

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

    private void initComplemented(){
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<LandBean> clientFragmentBean = fzbInterface.getLand(FinalContents.getUserID(), FinalContents.getPreparationId());
        clientFragmentBean.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LandBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(LandBean landBean) {
                        essential_information_et1.setText(landBean.getData().getCity());
                        essential_information_et2.setText(landBean.getData().getOccupation());
                        essential_information_et3.setText(landBean.getData().getFocus());
                        essential_information_et4.setText(landBean.getData().getIntentionalBuilding());
                        essential_information_et5.setText(landBean.getData().getPaymentMethod());
                        authority = landBean.getData().getHasDecision();

                        if(authority.equals("是")){
                            essential_information_rb1.setChecked(true);
                        }else if(authority.equals("否")){
                            essential_information_rb2.setChecked(true);
                        }

                        essential_information_et6.setText(landBean.getData().getResistance());
                        essential_information_et7.setText(landBean.getData().getObjective());
                        essential_information_et8.setText(landBean.getData().getIdealArea());
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

    private void initSQDD(){
        essential_information_et1.setText(ProjectProgressApi.getCustomerCity());
        essential_information_et2.setText(ProjectProgressApi.getCustomerOccupation());
        essential_information_et3.setText(ProjectProgressApi.getCustomerFocus());
        essential_information_et4.setText(ProjectProgressApi.getCustomerIntentionalBuilding());
        essential_information_et5.setText(ProjectProgressApi.getCustomerPaymentMethod());
        authority = ProjectProgressApi.getCustomerHasDecision();

        if(authority.equals("是")){
            essential_information_rb1.setChecked(true);
        }else if(authority.equals("否")){
            essential_information_rb2.setChecked(true);
        }

        essential_information_et6.setText(ProjectProgressApi.getCustomerResistance());
        essential_information_et7.setText(ProjectProgressApi.getCustomerObjective());
        essential_information_et8.setText(ProjectProgressApi.getCustomerAuditStatus());
    }

    private void initSQDDTXR(){
        essential_information_et1.setText(ProjectProgressApi.getFieldBean().getCity());
        essential_information_et2.setText(ProjectProgressApi.getFieldBean().getOccupation());
        essential_information_et3.setText(ProjectProgressApi.getFieldBean().getFocus());
        essential_information_et4.setText(ProjectProgressApi.getFieldBean().getIntentionalBuilding());
        essential_information_et5.setText(ProjectProgressApi.getFieldBean().getPaymentMethod());
        authority = ProjectProgressApi.getFieldBean().getHasDecision();

        if (ProjectProgressApi.getFieldBean().getHasDecision() != null) {
            if(authority.equals("是")){
                essential_information_rb1.setChecked(true);
            }else if(authority.equals("否")){
                essential_information_rb2.setChecked(true);
            }
        }

        essential_information_et6.setText(ProjectProgressApi.getFieldBean().getResistance());
        essential_information_et7.setText(ProjectProgressApi.getFieldBean().getObjective());
        essential_information_et8.setText(ProjectProgressApi.getFieldBean().getIdealArea());


    }

}

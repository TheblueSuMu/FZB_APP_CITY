package com.xcy.fzbcity.captain_team.view;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.api.FinalContents;
import com.xcy.fzbcity.all.database.BrokerSaveBean;
import com.xcy.fzbcity.all.database.RatioByOwnerIdBean;
import com.xcy.fzbcity.all.database.TeamMemberBean;
import com.xcy.fzbcity.all.modle.SysUser2Bean;
import com.xcy.fzbcity.all.persente.SingleClick;
import com.xcy.fzbcity.all.persente.SlideSwitch;
import com.xcy.fzbcity.all.persente.StatusBar;
import com.xcy.fzbcity.all.service.MyService;
import com.xcy.fzbcity.all.utils.CommonUtil;
import com.xcy.fzbcity.all.utils.MatcherUtils;
import com.xcy.fzbcity.all.utils.ToastUtil;
import com.xcy.fzbcity.all.view.AllActivity;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//TODO 圈层5-2添加顾问
public class Captain_Team_AddAConsultantActivity extends AllActivity implements View.OnClickListener, SlideSwitch.SlideListener {

    RelativeLayout add_aconsultant_img;

    TextView add_aconsultant_tv1;
    TextView add_aconsultant_tv2;
    TextView add_aconsultant_tv3;

    EditText add_aconsultant_et1;
    EditText add_aconsultant_et2;
    EditText add_aconsultant_et3;
    EditText add_aconsultant_et4;
    EditText add_aconsultant_et5;

    Button add_aconsultant_btn;

    RelativeLayout add_aconsultant_rl1;
    RelativeLayout add_aconsultant_rl2;
    RelativeLayout add_acon_rl_s;

    private String industry = "";
    private String name = "";
    private String phone = "";
    private String loginName = "";
    private String password = "";
    private String loginFlag = "1";
    private String manageFlag = "";
    private String type = "3";

    //TODO 新增
    SlideSwitch slide;
    TextView txt;
    TextView txt1;
    private TextView add_aconsultant_title;
    private String id = "";
    private Observable<BrokerSaveBean> userMessage;
    private Observable<RatioByOwnerIdBean> userMessage1;

    int ifnum1 = 0;
    int ifnum2 = 0;
    int ifnum3 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.captain_team_activity_add_aconsultant);
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

        add_aconsultant_img = findViewById(R.id.add_aconsultant_img1);

        add_aconsultant_tv1 = findViewById(R.id.add_aconsultant_tv1);
        add_aconsultant_tv2 = findViewById(R.id.add_aconsultant_tv2);
        add_aconsultant_tv3 = findViewById(R.id.add_aconsultant_tv3);

        add_aconsultant_et1 = findViewById(R.id.add_aconsultant_et1);
        add_aconsultant_et2 = findViewById(R.id.add_aconsultant_et2);
        add_aconsultant_et3 = findViewById(R.id.add_aconsultant_et3);
        add_aconsultant_et4 = findViewById(R.id.add_aconsultant_et4);
        add_aconsultant_et5 = findViewById(R.id.add_aconsultant_et5);

        add_aconsultant_btn = findViewById(R.id.add_aconsultant_btn);

        add_aconsultant_rl1 = findViewById(R.id.add_aconsultant_rl1);
        add_aconsultant_rl2 = findViewById(R.id.add_aconsultant_rl2);
        add_acon_rl_s = findViewById(R.id.add_acon_rl_s);

        add_aconsultant_title = findViewById(R.id.add_aconsultant_title);

        add_aconsultant_img.setOnClickListener(this);
        add_aconsultant_rl1.setOnClickListener(this);
        add_aconsultant_rl2.setOnClickListener(this);
        add_acon_rl_s.setOnClickListener(this);
        add_aconsultant_btn.setOnClickListener(this);

//        add_aconsultant_tv1.setText(FinalContents.getXSName());
//        add_aconsultant_tv2.setText(FinalContents.getXSTeamName());

        //        TODO 新增s
        slide = (SlideSwitch) findViewById(R.id.ssswit);

        slide.setState(false);
        txt = (TextView) findViewById(R.id.sstxt);
        txt1 = (TextView) findViewById(R.id.sstxt1);
        slide.setSlideListener(this);

        if (FinalContents.getXiuGai().equals("修改顾问")) {
            add_aconsultant_title.setText("修改顾问");
            initSysUser();
        } else if (FinalContents.getXiuGai().equals("添加顾问")) {
            add_aconsultant_title.setText("添加顾问");
            FinalContents.setRatioId("");
            if (FinalContents.getIdentity().equals("63")) {
                Log.i("顾问", "63");
            } else {
                if (FinalContents.getIdentity().equals("60")) {
                    add_aconsultant_tv1.setText(FinalContents.getUserName());
                    FinalContents.setOwnerId002(FinalContents.getUserID());
                } else {
                    add_aconsultant_tv2.setText(FinalContents.getUserName());
                    add_aconsultant_tv1.setText(FinalContents.getParentName());
                }
            }
        }
    }

    @SingleClick(1000)
    @Override
    public void onClick(View view) {

        switch (view.getId()) {
//            TODO 返回上一层
            case R.id.add_aconsultant_img1:
                FinalContents.setOwnerId001("");
                FinalContents.setOwnerId002("");
                finish();
                break;
//            TODO 选择团队长
            case R.id.add_acon_rl_s:
                ifnum1 = 0;
                if (FinalContents.getIdentity().equals("63")) {
                    if (ifnum1 == 0) {
                        ifnum1 = 1;
                        initAssistant();
                    }
                }
                break;
//            TODO 选择销售
            case R.id.add_aconsultant_rl1:
                ifnum2 = 0;
                if (FinalContents.getIdentity().equals("63") || (FinalContents.getXiuGai().equals("添加顾问") && FinalContents.getIdentity().equals("60"))) {
                    if (ifnum2 == 0) {
                        ifnum2 = 1;
                        initmarket();
                    }
                }
                break;
//                TODO 级别
            case R.id.add_aconsultant_rl2:
                ifnum3 = 0;
                if(ifnum3 == 0){
                    ifnum3 = 1;
                    if(add_aconsultant_tv2.getText().toString().equals("")){

                    }else {
                        initRatioByOwnerId();
                    }
                }
                break;
//                TODO 确定
            case R.id.add_aconsultant_btn:
                if (!MatcherUtils.isMobile(add_aconsultant_et3.getText().toString())) {
                    Toast.makeText(this, "请输入正确的手机号", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    initData();
                }

                break;
        }

    }

    //            TODO 选择团队长
    private void initAssistant() {

        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Log.i("销售", FinalContents.getUserID());
        Observable<TeamMemberBean> teamMemberBeane = fzbInterface.getTeamMemberBeane("", "1", loginFlag, FinalContents.getUserID(), FinalContents.getUserID(), "1000");
        teamMemberBeane.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<TeamMemberBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(final TeamMemberBean teamMemberBean) {
                        final List<String> list1 = new ArrayList<>();
                        for (int i = 0; i < teamMemberBean.getData().getRows().size(); i++) {
                            list1.add(teamMemberBean.getData().getRows().get(i).getName());
                        }

                        if (list1.size() != 0) {
                            //      监听选中
                            OptionsPickerView pvOptions = new OptionsPickerBuilder(Captain_Team_AddAConsultantActivity.this, new OnOptionsSelectListener() {
                                @Override
                                public void onOptionsSelect(int options1, int option2, int options3, View v) {
                                    //               返回的分别是三个级别的选中位置
                                    //              展示选中数据
                                    add_aconsultant_tv1.setText(teamMemberBean.getData().getRows().get(options1).getName() + "");
                                    FinalContents.setOwnerId002(teamMemberBean.getData().getRows().get(options1).getId());
                                    add_aconsultant_tv2.setText("");
                                    add_aconsultant_tv3.setText("");
                                }
                            })
                                    .setSelectOptions(0)//设置选择第一个
                                    .setOutSideCancelable(false)//点击背的地方不消失
                                    .build();//创建
                            //      把数据绑定到控件上面
                            pvOptions.setPicker(list1);
                            //      展示
                            pvOptions.show();
                        }else {
                            Toast.makeText(Captain_Team_AddAConsultantActivity.this, "暂无团队长", Toast.LENGTH_SHORT).show();
                        }
                        ifnum1 = 0;
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("MyCL", "TeamMemberActivity错误信息：" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    //            TODO 选择销售
    private void initmarket() {

        if (FinalContents.getOwnerId002().equals("")) {

        } else {

            Retrofit.Builder builder = new Retrofit.Builder();
            builder.baseUrl(FinalContents.getBaseUrl());
            builder.addConverterFactory(GsonConverterFactory.create());
            builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
            Retrofit build = builder.build();
            MyService fzbInterface = build.create(MyService.class);
            Log.i("销售","FinalContents.getOwnerId002()：" + FinalContents.getOwnerId002());
            Observable<TeamMemberBean> teamMemberBeane = fzbInterface.getTeamMemberBeane("", "2", "", FinalContents.getOwnerId002(), FinalContents.getOwnerId002(), "1000");
            teamMemberBeane.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<TeamMemberBean>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(final TeamMemberBean teamMemberBean) {

                            final List<String> list1 = new ArrayList<>();
                            for (int i = 0; i < teamMemberBean.getData().getRows().size(); i++) {
                                list1.add(teamMemberBean.getData().getRows().get(i).getName());
                            }
                            if (list1.size() != 0) {
                                //      监听选中
                                OptionsPickerView pvOptions = new OptionsPickerBuilder(Captain_Team_AddAConsultantActivity.this, new OnOptionsSelectListener() {
                                    @Override
                                    public void onOptionsSelect(int options1, int option2, int options3, View v) {
                                        //               返回的分别是三个级别的选中位置
                                        //              展示选中数据
                                        add_aconsultant_tv2.setText(teamMemberBean.getData().getRows().get(options1).getName() + "");
                                        FinalContents.setOwnerId001(teamMemberBean.getData().getRows().get(options1).getId());
                                        add_aconsultant_tv3.setText("");
                                    }
                                })
                                        .setSelectOptions(0)//设置选择第一个
                                        .setOutSideCancelable(false)//点击背的地方不消失
                                        .build();//创建
                                //      把数据绑定到控件上面
                                pvOptions.setPicker(list1);
                                //      展示
                                pvOptions.show();
                            }else {
                                Toast.makeText(Captain_Team_AddAConsultantActivity.this, "当前团队长暂无销售", Toast.LENGTH_SHORT).show();
                            }

                            ifnum2 = 0;
                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.i("MyCL", "TeamMemberActivity错误信息：" + e.getMessage());
                        }

                        @Override
                        public void onComplete() {

                        }
                    });
        }

    }

    private void initRatioByOwnerId() {

        if (FinalContents.getIdentity().equals("63")) {
            if (FinalContents.getOwnerId001().equals("")) {

            } else {
                Retrofit.Builder builder = new Retrofit.Builder();
                builder.baseUrl(FinalContents.getBaseUrl());
                builder.addConverterFactory(GsonConverterFactory.create());
                builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
                Retrofit build = builder.build();
                MyService fzbInterface = build.create(MyService.class);
                Observable<RatioByOwnerIdBean> userMessage = fzbInterface.getRatioByOwnerId(FinalContents.getUserID(), FinalContents.getOwnerId001());
                userMessage.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<RatioByOwnerIdBean>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @SuppressLint("WrongConstant")
                            @Override
                            public void onNext(final RatioByOwnerIdBean ratioByOwnerIdBean) {
                                final List<String> list1 = new ArrayList<>();
                                for (int i = 0; i < ratioByOwnerIdBean.getData().size(); i++) {
                                    list1.add(ratioByOwnerIdBean.getData().get(i).getName()+ "("  + ratioByOwnerIdBean.getData().get(i).getPercent() + "%)");
                                }
                                //      监听选中
                                OptionsPickerView pvOptions = new OptionsPickerBuilder(Captain_Team_AddAConsultantActivity.this, new OnOptionsSelectListener() {
                                    @Override
                                    public void onOptionsSelect(int options1, int option2, int options3, View v) {
                                        //               返回的分别是三个级别的选中位置
                                        //              展示选中数据
                                        add_aconsultant_tv3.setText(ratioByOwnerIdBean.getData().get(options1).getName()+ "("  + ratioByOwnerIdBean.getData().get(options1).getPercent() + "%)");
                                        FinalContents.setRatioId(ratioByOwnerIdBean.getData().get(options1).getId());
                                    }
                                })
                                        .setSelectOptions(0)//设置选择第一个
                                        .setOutSideCancelable(false)//点击背的地方不消失
                                        .build();//创建
                                //      把数据绑定到控件上面
                                pvOptions.setPicker(list1);
                                //      展示
                                pvOptions.show();
                                ifnum3 = 0;
                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.i("列表数据获取错误", "错误" + e);
                            }

                            @Override
                            public void onComplete() {

                            }
                        });
            }
        } else {
            Log.i("顾问", "2");
            Retrofit.Builder builder = new Retrofit.Builder();
            builder.baseUrl(FinalContents.getBaseUrl());
            builder.addConverterFactory(GsonConverterFactory.create());
            builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
            Retrofit build = builder.build();
            MyService fzbInterface = build.create(MyService.class);
            if (FinalContents.getOwnerId001().equals("")) {
                userMessage1 = fzbInterface.getRatioByOwnerId(FinalContents.getUserID(), FinalContents.getUserID());
            } else {
                userMessage1 = fzbInterface.getRatioByOwnerId(FinalContents.getUserID(), FinalContents.getOwnerId001());
            }
            userMessage1.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<RatioByOwnerIdBean>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @SuppressLint("WrongConstant")
                        @Override
                        public void onNext(final RatioByOwnerIdBean ratioByOwnerIdBean) {
                            final List<String> list2 = new ArrayList<>();
                            for (int i = 0; i < ratioByOwnerIdBean.getData().size(); i++) {
                                list2.add(ratioByOwnerIdBean.getData().get(i).getName()+ "("  + ratioByOwnerIdBean.getData().get(i).getPercent() + "%)");
                            }
                            //      监听选中
                            OptionsPickerView pvOptions = new OptionsPickerBuilder(Captain_Team_AddAConsultantActivity.this, new OnOptionsSelectListener() {
                                @Override
                                public void onOptionsSelect(int options1, int option2, int options3, View v) {
                                    //               返回的分别是三个级别的选中位置
                                    //              展示选中数据
                                    add_aconsultant_tv3.setText(ratioByOwnerIdBean.getData().get(options1).getName() + "("  + ratioByOwnerIdBean.getData().get(options1).getPercent() + "%)");
                                    FinalContents.setRatioId(ratioByOwnerIdBean.getData().get(options1).getId());
                                }
                            })
                                    .setSelectOptions(0)//设置选择第一个
                                    .setOutSideCancelable(false)//点击背的地方不消失
                                    .build();//创建
                            //      把数据绑定到控件上面
                            pvOptions.setPicker(list2);
                            //      展示
                            pvOptions.show();
                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.i("列表数据获取错误", "错误" + e);
                        }

                        @Override
                        public void onComplete() {

                        }
                    });
        }


    }

    private void initData() {


        name = add_aconsultant_et1.getText().toString();
        industry = add_aconsultant_et2.getText().toString();
        phone = add_aconsultant_et3.getText().toString();
        loginName = add_aconsultant_et4.getText().toString();
        password = add_aconsultant_et5.getText().toString();


        Log.i("修改顾问", "ID++:" + FinalContents.getRatioId());
        Log.i("修改顾问", "ID++:" + FinalContents.getOwnerId002());

        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);

        Log.i("顾问数据", FinalContents.getUserID());
        Log.i("顾问数据", FinalContents.getRatioId());
        Log.i("顾问数据", FinalContents.getOwnerId002());
        Log.i("顾问数据", id);

        if (FinalContents.getIdentity().equals("63")) {
            if (FinalContents.getOwnerId001().equals("")) {
                ToastUtil.showToast(Captain_Team_AddAConsultantActivity.this, "请把数据填充完整再提交");
            } else {
                if (add_aconsultant_et1.getText().toString().equals("")  || add_aconsultant_et3.getText().toString().equals("") || add_aconsultant_et4.getText().toString().equals("") || add_aconsultant_tv1.getText().toString().equals("") || add_aconsultant_tv2.getText().toString().equals("") || add_aconsultant_tv3.getText().toString().equals("")) {
                    ToastUtil.showToast(Captain_Team_AddAConsultantActivity.this, "请把数据填充完整再提交");
                } else {
                    if (txt.getVisibility() == View.VISIBLE) {
                        loginFlag = "0";
                    } else {
                        loginFlag = "1";
                    }
                    userMessage = fzbInterface.getBrokerSave(id, industry, name, phone, loginName, password, loginFlag, manageFlag, FinalContents.getUserID(), FinalContents.getRatioId(), FinalContents.getOwnerId001(), type, "");
                    userMessage.subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Observer<BrokerSaveBean>() {
                                @Override
                                public void onSubscribe(Disposable d) {

                                }

                                @SuppressLint("WrongConstant")
                                @Override
                                public void onNext(BrokerSaveBean brokerSaveBean) {
                                    ToastUtil.showToast(Captain_Team_AddAConsultantActivity.this, brokerSaveBean.getData().getMessage());
                                    if (brokerSaveBean.getData().getMessage().equals("保存成功")) {
                                        FinalContents.setOwnerId("");
                                        FinalContents.setOwnerId001("");
                                        FinalContents.setOwnerId002("");
                                        finish();
                                    } else {

                                    }
                                }

                                @Override
                                public void onError(Throwable e) {
                                    Log.i("添加销售获取错误", "错误" + e);
                                }

                                @Override
                                public void onComplete() {

                                }
                            });
                }
            }
        } else {
            if (add_aconsultant_et1.getText().toString().equals("") || add_aconsultant_et3.getText().toString().equals("") || add_aconsultant_et4.getText().toString().equals("") || add_aconsultant_tv1.getText().toString().equals("") || add_aconsultant_tv2.getText().toString().equals("") || add_aconsultant_tv3.getText().toString().equals("")) {
                Toast.makeText(Captain_Team_AddAConsultantActivity.this, "请把数据填充完整再提交", Toast.LENGTH_SHORT).show();
            } else {
                if (txt.getVisibility() == View.VISIBLE) {
                    loginFlag = "0";
                } else {
                    loginFlag = "1";
                }
                if (FinalContents.getIdentity().equals("60")) {
                    userMessage = fzbInterface.getBrokerSave(id, industry, name, phone, loginName, password, loginFlag, manageFlag, FinalContents.getUserID(), FinalContents.getRatioId(), FinalContents.getOwnerId001(), type, "");
                } else {
                    userMessage = fzbInterface.getBrokerSave(id, industry, name, phone, loginName, password, loginFlag, manageFlag, FinalContents.getUserID(), FinalContents.getRatioId(), FinalContents.getUserID(), type, "");
                }
                userMessage.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<BrokerSaveBean>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @SuppressLint("WrongConstant")
                            @Override
                            public void onNext(BrokerSaveBean brokerSaveBean) {
                                ToastUtil.showToast(Captain_Team_AddAConsultantActivity.this, brokerSaveBean.getData().getMessage());
                                if (brokerSaveBean.getData().getMessage().equals("保存成功")) {
                                    FinalContents.setOwnerId("");
                                    FinalContents.setOwnerId001("");
                                    FinalContents.setOwnerId002("");
                                    finish();
                                } else {

                                }
                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.i("添加销售获取错误", "错误" + e);
                            }

                            @Override
                            public void onComplete() {

                            }
                        });
            }
        }

    }


    private void initSysUser() {
        Log.i("顾问", "ID2：" + FinalContents.getAgentId());
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<SysUser2Bean> userMessage = fzbInterface.getSysUser(FinalContents.getUserID(), FinalContents.getAgentId());
        userMessage.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SysUser2Bean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @SuppressLint("WrongConstant")
                    @Override
                    public void onNext(final SysUser2Bean sysUser2Bean) {
                        Log.i("名字", "销售名字：" + FinalContents.getUserName());
                        Log.i("名字", "团队长名字：" + FinalContents.getParentName());
//                        add_aconsultant_tv1.setText(FinalContents.getParentName());

                        Retrofit.Builder builder = new Retrofit.Builder();
                        builder.baseUrl(FinalContents.getBaseUrl());
                        builder.addConverterFactory(GsonConverterFactory.create());
                        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
                        Retrofit build = builder.build();
                        MyService fzbInterface = build.create(MyService.class);
                        Observable<SysUser2Bean> userMessage = fzbInterface.getSysUser(FinalContents.getUserID(), sysUser2Bean.getData().getSysUser().getParent().getId());
                        userMessage.subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(new Observer<SysUser2Bean>() {
                                    @Override
                                    public void onSubscribe(Disposable d) {

                                    }

                                    @SuppressLint("WrongConstant")
                                    @Override
                                    public void onNext(final SysUser2Bean sysUser2Bean) {
                                        add_aconsultant_tv1.setText(sysUser2Bean.getData().getSysUser().getParent().getName());
                                        if (sysUser2Bean.getData().getSysUser().getLoginFlag().equals("0")) {
                                            slide.setState(true);
                                        } else if (sysUser2Bean.getData().getSysUser().getLoginFlag().equals("1")) {
                                            slide.setState(false);
                                        }

                                        FinalContents.setOwnerId002(sysUser2Bean.getData().getSysUser().getParent().getId());
                                    }

                                    @Override
                                    public void onError(Throwable e) {
                                        Log.i("列表数据获取错误", "错误" + e);
                                    }

                                    @Override
                                    public void onComplete() {

                                    }
                                });


                        add_aconsultant_tv2.setText(sysUser2Bean.getData().getSysUser().getParent().getName());
                        add_aconsultant_tv3.setText(sysUser2Bean.getData().getRatioName());
                        add_aconsultant_et1.setText(sysUser2Bean.getData().getSysUser().getName());
                        add_aconsultant_et2.setText(sysUser2Bean.getData().getSysUser().getIndustry());
                        add_aconsultant_et3.setText(sysUser2Bean.getData().getSysUser().getPhone());
                        add_aconsultant_et4.setText(sysUser2Bean.getData().getSysUser().getLoginName());

                        if (sysUser2Bean.getData().getSysUser().getLoginFlag().equals("1")) {
                            slide.setState(true);
                        } else if (sysUser2Bean.getData().getSysUser().getLoginFlag().equals("0")) {
                            slide.setState(false);
                        }
                        FinalContents.setOwnerId001(sysUser2Bean.getData().getSysUser().getParent().getId());
                        FinalContents.setRatioId(sysUser2Bean.getData().getRatioId());
                        id = sysUser2Bean.getData().getSysUser().getId();
                        Log.i("修改顾问", "ID:" + FinalContents.getRatioId());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("列表数据获取错误", "错误" + e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    //    TODO 新增
    @Override
    public void open() {
        txt.setVisibility(View.VISIBLE);
        txt1.setVisibility(View.GONE);
    }

    @Override
    public void close() {
        txt.setVisibility(View.GONE);
        txt1.setVisibility(View.VISIBLE);
    }
}

package com.xcy.fzbcity.captain_team.view;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

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

//TODO 圈层5-2添加销售
public class Captain_Team_AddSalesActivity extends AllActivity implements View.OnClickListener, SlideSwitch.SlideListener {

    RelativeLayout add_sales_img;

    TextView add_sales_tv1;
    TextView add_sales_tv2;

    EditText add_sales_et1;
    EditText add_sales_et2;
    EditText add_sales_et3;
    EditText add_sales_et4;
    EditText add_sales_et5;

    Button add_sales_btn;

    RelativeLayout add_sales_rl1;
    RelativeLayout add_sales_rl2;
    private String industry = "";
    private String name = "";
    private String phone = "";
    private String loginName = "";
    private String password = "";
    private String loginFlag = "1";
    private String manageFlag = "";
    private String type = "2";

    //TODO 新增
    SlideSwitch slide;
    TextView txt;
    TextView txt1;
    private TextView add_sales_title;
    private Observable<BrokerSaveBean> userMessage;
    private Observable<TeamMemberBean> teamMemberBeane;
    private Observable<RatioByOwnerIdBean> userMessage1;

    int ifnum1 = 0;
    int ifnum2 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.captain_team_activity_add_sales);
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

        add_sales_img = findViewById(R.id.add_sales_img1);

        add_sales_title = findViewById(R.id.add_sales_title);

        add_sales_tv1 = findViewById(R.id.add_sales_tv1);
        add_sales_tv2 = findViewById(R.id.add_sales_tv2);

        add_sales_et1 = findViewById(R.id.add_sales_et1);
        add_sales_et2 = findViewById(R.id.add_sales_et2);
        add_sales_et3 = findViewById(R.id.add_sales_et3);
        add_sales_et4 = findViewById(R.id.add_sales_et4);
        add_sales_et5 = findViewById(R.id.add_sales_et5);

        add_sales_btn = findViewById(R.id.add_sales_btn);

        add_sales_rl1 = findViewById(R.id.add_sales_rl1);
        add_sales_rl2 = findViewById(R.id.add_sales_rl2);

        Log.i("销售啊啊啊", "修改销售：" + FinalContents.getXiuGai());
        if (FinalContents.getXiuGai().equals("修改销售")) {
            add_sales_title.setText("修改销售");
            initSysUser();
        } else if (FinalContents.getXiuGai().equals("添加销售")) {
            add_sales_title.setText("添加销售");
            FinalContents.setAgentId("");
            FinalContents.setRatioId("");
        }

        add_sales_img.setOnClickListener(this);
        add_sales_rl1.setOnClickListener(this);
        add_sales_rl2.setOnClickListener(this);
        add_sales_btn.setOnClickListener(this);

//        TODO 新增s
        slide = (SlideSwitch) findViewById(R.id.sswit);

        slide.setState(false);
        txt = (TextView) findViewById(R.id.stxt);
        txt1 = (TextView) findViewById(R.id.stxt1);
        slide.setSlideListener(this);
        if (FinalContents.getIdentity().equals("63")) {

        } else {
            add_sales_tv1.setText(FinalContents.getUserName());
        }
    }

    @SingleClick(1000)
    @Override
    public void onClick(View view) {

        switch (view.getId()) {
//            TODO 返回上一层
            case R.id.add_sales_img1:
                FinalContents.setOwnerId001("");
                finish();
                break;
//            TODO 选择团队长
            case R.id.add_sales_rl1:
                ifnum1 = 0;
                if (FinalContents.getIdentity().equals("63")) {
                    if (ifnum1 == 0) {
                        ifnum1 = 1;
                        initTeamLeaderLevel();
                        ifnum1 = 0;
                    }
                } else {

                }
                break;
//                TODO 级别
            case R.id.add_sales_rl2:
                ifnum2 = 0;
                if (ifnum2 == 0) {
                    ifnum2 = 1;
                    initRatioByOwnerId();
                    ifnum2 = 0;
                }

                break;
//                TODO 确定
            case R.id.add_sales_btn:
                if (!MatcherUtils.isMobile(add_sales_et3.getText().toString())) {
                    ToastUtil.showToast(this, "请输入正确的手机号");
                    return;
                } else {
                    initData();
                }
                break;
        }
    }

    private void initSysUser() {
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
                        add_sales_tv1.setText(sysUser2Bean.getData().getSysUser().getParent().getName());
                        add_sales_tv2.setText(sysUser2Bean.getData().getRatioName());
                        add_sales_et1.setText(sysUser2Bean.getData().getSysUser().getName());
                        add_sales_et2.setText(sysUser2Bean.getData().getSysUser().getIndustry());
                        add_sales_et3.setText(sysUser2Bean.getData().getSysUser().getPhone());
                        add_sales_et4.setText(sysUser2Bean.getData().getSysUser().getLoginName());

                        if (sysUser2Bean.getData().getSysUser().getLoginFlag().equals("0")) {
                            slide.setState(true);
                        } else if (sysUser2Bean.getData().getSysUser().getLoginFlag().equals("1")) {
                            slide.setState(false);
                        }

                        FinalContents.setOwnerId001(sysUser2Bean.getData().getSysUser().getParent().getId());
                        FinalContents.setRatioId(sysUser2Bean.getData().getRatioId());
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

    private void initTeamLeaderLevel() {

        Log.i("MyCL", "loginFlag：" + loginFlag);

        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
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
                        //      监听选中
                        OptionsPickerView pvOptions = new OptionsPickerBuilder(Captain_Team_AddSalesActivity.this, new OnOptionsSelectListener() {
                            @Override
                            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                                //               返回的分别是三个级别的选中位置
                                //              展示选中数据
                                add_sales_tv1.setText(teamMemberBean.getData().getRows().get(options1).getName() + "");
                                FinalContents.setOwnerId001(teamMemberBean.getData().getRows().get(options1).getId());
                                add_sales_tv2.setText("");
                            }
                        })
                                .setSelectOptions(0)//设置选择第一个
                                .setOutSideCancelable(false)//点击背的地方不消失
                                .build();//创建
                        //      把数据绑定到控件上面
                        pvOptions.setPicker(list1);
                        //      展示
                        pvOptions.show();
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

    private void initRatioByOwnerId() {
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        if (FinalContents.getIdentity().equals("63")) {
            if (FinalContents.getOwnerId001().equals("")) {

            } else {
                userMessage1 = fzbInterface.getRatioByOwnerId(FinalContents.getUserID(), FinalContents.getOwnerId001());
                userMessage1.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<RatioByOwnerIdBean>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @SuppressLint("WrongConstant")
                            @Override
                            public void onNext(final RatioByOwnerIdBean ratioByOwnerIdBean) {
                                Log.i("销售数据", "销售数据");
                                final List<String> lists = new ArrayList<>();
                                for (int i = 0; i < ratioByOwnerIdBean.getData().size(); i++) {
                                    lists.add(ratioByOwnerIdBean.getData().get(i).getName() + "("  + ratioByOwnerIdBean.getData().get(i).getPercent() + "%)");
                                }
                                //      监听选中
                                OptionsPickerView pvOptions = new OptionsPickerBuilder(Captain_Team_AddSalesActivity.this, new OnOptionsSelectListener() {
                                    @Override
                                    public void onOptionsSelect(int options1, int option2, int options3, View v) {
                                        //               返回的分别是三个级别的选中位置
                                        //              展示选中数据
                                        add_sales_tv2.setText(ratioByOwnerIdBean.getData().get(options1).getName() + "("  + ratioByOwnerIdBean.getData().get(options1).getPercent() + "%)");
                                        FinalContents.setRatioId(ratioByOwnerIdBean.getData().get(options1).getId());
                                    }
                                })
                                        .setSelectOptions(0)//设置选择第一个
                                        .setOutSideCancelable(false)//点击背的地方不消失
                                        .build();//创建
                                //      把数据绑定到控件上面
                                pvOptions.setPicker(lists);
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
        } else {
            userMessage1 = fzbInterface.getRatioByOwnerId(FinalContents.getUserID(), FinalContents.getUserID());
            userMessage1.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<RatioByOwnerIdBean>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @SuppressLint("WrongConstant")
                        @Override
                        public void onNext(final RatioByOwnerIdBean ratioByOwnerIdBean) {
                            Log.i("销售数据", "销售数据");
                            final List<String> lists = new ArrayList<>();
                            for (int i = 0; i < ratioByOwnerIdBean.getData().size(); i++) {
                                lists.add(ratioByOwnerIdBean.getData().get(i).getName()+ "("  + ratioByOwnerIdBean.getData().get(i).getPercent() + "%)");
                            }
                            //      监听选中
                            OptionsPickerView pvOptions = new OptionsPickerBuilder(Captain_Team_AddSalesActivity.this, new OnOptionsSelectListener() {
                                @Override
                                public void onOptionsSelect(int options1, int option2, int options3, View v) {
                                    //               返回的分别是三个级别的选中位置
                                    //              展示选中数据
                                    add_sales_tv2.setText(ratioByOwnerIdBean.getData().get(options1).getName()+ "("  + ratioByOwnerIdBean.getData().get(options1).getPercent() + "%)");
                                    FinalContents.setRatioId(ratioByOwnerIdBean.getData().get(options1).getId());
                                }
                            })
                                    .setSelectOptions(0)//设置选择第一个
                                    .setOutSideCancelable(false)//点击背的地方不消失
                                    .build();//创建
                            //      把数据绑定到控件上面
                            pvOptions.setPicker(lists);
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

        if (add_sales_et1.getText().toString().equals("") || add_sales_et3.getText().toString().equals("") || add_sales_et4.getText().toString().equals("") || add_sales_tv1.getText().toString().equals("") || add_sales_tv2.getText().toString().equals("")) {
            ToastUtil.showToast(Captain_Team_AddSalesActivity.this, "请把数据填充完整再提交");
        } else {

            name = add_sales_et1.getText().toString();
            industry = add_sales_et2.getText().toString();
            phone = add_sales_et3.getText().toString();
            loginName = add_sales_et4.getText().toString();
            password = add_sales_et5.getText().toString();

            if (txt.getVisibility() == View.VISIBLE) {
                loginFlag = "0";
            } else {
                loginFlag = "1";
            }

            Retrofit.Builder builder = new Retrofit.Builder();
            builder.baseUrl(FinalContents.getBaseUrl());
            builder.addConverterFactory(GsonConverterFactory.create());
            builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
            Retrofit build = builder.build();
            MyService fzbInterface = build.create(MyService.class);
            if (FinalContents.getIdentity().equals("63")) {
                userMessage = fzbInterface.getBrokerSave(FinalContents.getAgentId(), industry, name, phone, loginName, password, loginFlag, manageFlag, FinalContents.getUserID(), FinalContents.getRatioId(), FinalContents.getOwnerId001(), type, FinalContents.getLevelId());
            } else {
                userMessage = fzbInterface.getBrokerSave(FinalContents.getAgentId(), industry, name, phone, loginName, password, loginFlag, manageFlag, FinalContents.getUserID(), FinalContents.getRatioId(), FinalContents.getUserID(), type, FinalContents.getLevelId());
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
                            ToastUtil.showToast(Captain_Team_AddSalesActivity.this, brokerSaveBean.getData().getMessage());
                            if (brokerSaveBean.getData().getMessage().equals("保存成功")) {
                                FinalContents.setOwnerId001("");
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

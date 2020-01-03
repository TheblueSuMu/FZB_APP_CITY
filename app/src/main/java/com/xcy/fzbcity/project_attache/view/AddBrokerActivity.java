package com.xcy.fzbcity.project_attache.view;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.api.FinalContents;
import com.xcy.fzbcity.all.database.AddBrokerBean;
import com.xcy.fzbcity.all.database.BrokerChangeBean;
import com.xcy.fzbcity.all.persente.SingleClick;
import com.xcy.fzbcity.all.persente.StatusBar;
import com.xcy.fzbcity.all.service.MyService;
import com.xcy.fzbcity.all.utils.CommonUtil;
import com.xcy.fzbcity.all.utils.MatcherUtils;
import com.xcy.fzbcity.all.utils.ToastUtil;
import com.xcy.fzbcity.all.view.AllActivity;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//TODO 添加经纪人
public class AddBrokerActivity extends AppCompatActivity implements View.OnClickListener {

    RelativeLayout add_store_rl1;
    RelativeLayout add_store_rl2;
    RelativeLayout add_store_rl3;

    Button add_store_btn;

    TextView add_store_tv1;
    TextView add_store_tv2;
    TextView add_store_tv3;
    TextView broker_tv_id;

    EditText add_store_et1;
    EditText add_store_et2;
    EditText add_store_et3;
    EditText add_store_et4;

    RelativeLayout add_store_return;

    RelativeLayout add_rl4;
    LinearLayout add_rl4_s;

    RadioGroup add_rg;
    RadioButton add_rb1;
    RadioButton add_rb2;


    private String s;
    private String s2;
    private String s1;
    private String s11;
    private String s3;
    private String s4;
    private String s5;
    private String s6;
    private Intent intent;

    private List<String> list;
    private OptionsPickerView pvOptions;
    private String num;
    private BrokerChangeBean.DataBean data;
    private TextView add_title;

    int ifnum = 0;
    private String isCover;
    private String ifCompanyManageId;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.project_attache_activity_add_broker);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);      //  TODO    始终竖屏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        getWindow().setStatusBarColor(Color.parseColor("#ff546da6"));
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
            ToastUtil.showLongToast(this, "当前无网络，请检查网络后再进行登录");
        }
    }

    private void initView() {
        add_title = findViewById(R.id.add_title);
        add_store_rl1 = findViewById(R.id.add_rl1);
        add_store_rl2 = findViewById(R.id.add_rl2);
        add_store_rl3 = findViewById(R.id.add_rl3);
        add_store_btn = findViewById(R.id.add_btn);
        add_store_tv1 = findViewById(R.id.add_tv1);
        add_store_tv2 = findViewById(R.id.add_tv2);
        add_store_tv3 = findViewById(R.id.add_tv3);
        add_store_et1 = findViewById(R.id.add_et1);
        add_store_et2 = findViewById(R.id.add_et2);
        add_store_et2.setInputType(EditorInfo.TYPE_CLASS_PHONE);
        add_store_et2 = findViewById(R.id.add_et2);
        add_store_et3 = findViewById(R.id.add_et3);
        add_store_et4 = findViewById(R.id.add_et4);
        add_store_return = findViewById(R.id.add_return);
        broker_tv_id = findViewById(R.id.broker_tv_id);

        add_rl4 = findViewById(R.id.add_rl4);
        add_rl4_s = findViewById(R.id.add_ll4_s);
        add_rg = findViewById(R.id.add_rg);
        add_rb1 = findViewById(R.id.add_rb1);
        add_rb2 = findViewById(R.id.add_rb2);

        broker_tv_id.setVisibility(View.GONE);

        add_rl4.setVisibility(View.GONE);
        add_rl4_s.setVisibility(View.GONE);

        add_store_return.setOnClickListener(this);
        add_store_rl1.setOnClickListener(this);
        add_store_rl2.setOnClickListener(this);
        add_store_rl3.setOnClickListener(this);
        add_store_btn.setOnClickListener(this);

        if (FinalContents.getBorkerChange().equals("修改")) {
            initChangeData();
            broker_tv_id.setVisibility(View.GONE);
            add_title.setText("修改经纪人");
            add_rl4.setVisibility(View.VISIBLE);
            add_rl4_s.setVisibility(View.VISIBLE);
        }

    }

    private void initChangeData() {

        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<BrokerChangeBean> brokerChangeBean = fzbInterface.getBrokerChangeBean(FinalContents.getUserID(), FinalContents.getAgentId());
        brokerChangeBean.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BrokerChangeBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BrokerChangeBean brokerChangeBean) {
                        data = brokerChangeBean.getData();
                        add_store_tv1.setText(brokerChangeBean.getData().getCompanyManage().getCompanyName());
                        add_store_tv2.setText(brokerChangeBean.getData().getStoreManage().getStoreName());
                        FinalContents.setAddtype1(brokerChangeBean.getData().getStoreManage().getStoreName());
                        FinalContents.setAddtype2(brokerChangeBean.getData().getCompanyManage().getCompanyName());
                        String identity = brokerChangeBean.getData().getIdentity();

                        if (identity.equals("1")) {
                            add_store_tv3.setText("经纪公司管理者");
                        } else if (identity.equals("2")) {
                            add_store_tv3.setText("经纪门店管理者");
                        } else if (identity.equals("3")) {
                            add_store_tv3.setText("普通经纪人");
                        }
                        add_store_et1.setText(brokerChangeBean.getData().getName());
                        add_store_et2.setText(brokerChangeBean.getData().getPhone());
                        add_store_et3.setText(brokerChangeBean.getData().getLoginName());
                        FinalContents.setCompanyManageId(brokerChangeBean.getData().getCompanyManage().getId());
                        FinalContents.setStoreManageId(brokerChangeBean.getData().getStoreManage().getId());

//                        if(brokerChangeBean.getData().get){
//
//                        }

                        ifCompanyManageId = brokerChangeBean.getData().getCompanyManage().getId();

                        add_store_et4.setHint("");

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("专员经纪人回显", "专员经纪人回显错误信息" + e.getMessage());
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
            case R.id.add_return:
                FinalContents.setAddtype1("");
                FinalContents.setAddtype2("");
                FinalContents.setBorkerChange("");
                finish();
                break;
            case R.id.add_rl1:
                add_store_tv2.setText("");
                FinalContents.setAddtype1("");
                intent = new Intent(AddBrokerActivity.this, StoreListActivity.class);
                FinalContents.setMyAddType("公司");
                startActivity(intent);
                break;
            case R.id.add_rl2:
                if (add_store_tv1.getText().equals("")) {
                    ToastUtil.showLongToast(this, "请先选择公司");
                } else {
                    intent = new Intent(AddBrokerActivity.this, StoreListActivity.class);
                    FinalContents.setMyAddType("门店");
                    startActivity(intent);
                }

                break;
            case R.id.add_rl3:
                if (ifnum == 0) {
                    ifnum = 1;
                    list = new ArrayList<>();
                    list.add("经纪公司管理者");
                    list.add("经纪门店管理者");
                    list.add("普通经纪人");
                    pvOptions = new OptionsPickerBuilder(view.getContext(), new OnOptionsSelectListener() {
                        @Override
                        public void onOptionsSelect(int options1, int option2, int options3, View v) {
                            //               返回的分别是三个级别的选中位置
                            //              展示选中数据
                            add_store_tv3.setText(list.get(options1));
                        }
                    })
                            .setSelectOptions(0)//设置选择第一个
                            .setOutSideCancelable(false)//点击背的地方不消失
                            .build();//创建
                    //      把数据绑定到控件上面
                    pvOptions.setPicker(list);
                    //      展示
                    pvOptions.show();
                    ifnum = 0;
                }

                break;
            case R.id.add_btn:
                if (!MatcherUtils.isMobile(add_store_et2.getText().toString())) {
                    ToastUtil.showLongToast(this, "请输入正确的手机号");
                    return;
                }
                s = add_store_tv1.getText().toString();
                s2 = add_store_tv2.getText().toString();
                s11 = add_store_tv3.getText().toString();

                s3 = add_store_et1.getText().toString();
                s4 = add_store_et2.getText().toString();
                s5 = add_store_et3.getText().toString();
                s6 = add_store_et4.getText().toString();

//                if (s6.equals("")) {
//                    s6 = 123456 + "";
//                }

                initData();

                break;
        }

    }

    private void initData() {
        if (FinalContents.getBorkerChange().equals("")) {
            if (s11.equals("经纪公司管理者")) {
                num = "1";
            } else if (s11.equals("经纪门店管理者")) {
                num = "2";
            } else if (s11.equals("普通经纪人")) {
                num = "3";
            }

            if(add_store_et4.getHint().equals("")){

            }else {

            }

            if (s.equals("") || s2.equals("") || s11.equals("") || s3.equals("") || s4.equals("") || s5.equals("")) {
                ToastUtil.showLongToast(this, "带*数据请填写完整");
            } else {
                Retrofit.Builder builder = new Retrofit.Builder();
                builder.baseUrl(FinalContents.getBaseUrl());
                builder.addConverterFactory(GsonConverterFactory.create());
                builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
                Retrofit build = builder.build();
                MyService fzbInterface = build.create(MyService.class);
                Observable<AddBrokerBean> addBrokerBean = fzbInterface.getAddBrokerBean("", num, s3, s4, s5, s6, FinalContents.getUserID(), FinalContents.getCompanyManageId(), FinalContents.getStoreManageId(), "");
                addBrokerBean.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<AddBrokerBean>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                            }

                            @Override
                            public void onNext(AddBrokerBean addBrokerBean) {
                                if (addBrokerBean.getData().getStatus() == 1) {
                                    ToastUtil.showLongToast(AddBrokerActivity.this, addBrokerBean.getData().getMessage());
                                    finish();
                                } else {
                                    ToastUtil.showLongToast(AddBrokerActivity.this, "" + addBrokerBean.getData().getMessage());
                                }
                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.i("经纪人", "添加经纪人错误信息：" + e.getMessage());
                            }

                            @Override
                            public void onComplete() {
                            }
                        });
            }
        } else if (FinalContents.getBorkerChange().equals("修改")) {
            if (add_rl4.getVisibility() == View.GONE) {
                isCover = "0";
            } else {
                if (add_rb1.isChecked() == true) {
                    isCover = "1";
                } else if (add_rb2.isChecked() == true) {
                    isCover = "0";
                }
            }


            if (s11.equals("经纪公司管理者")) {
                num = "1";
            } else if (s11.equals("经纪门店管理者")) {
                num = "2";
            } else if (s11.equals("普通经纪人")) {
                num = "3";
            }
            if (s.equals("") || s2.equals("") || s11.equals("") || s3.equals("") || s4.equals("") || s5.equals("")) {
                ToastUtil.showLongToast(AddBrokerActivity.this, "带*数据请填写完整");
            } else {
                Retrofit.Builder builder = new Retrofit.Builder();
                builder.baseUrl(FinalContents.getBaseUrl());
                builder.addConverterFactory(GsonConverterFactory.create());
                builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
                Retrofit build = builder.build();
                MyService fzbInterface = build.create(MyService.class);
                Log.i("经纪人", "data.getId()：" + data.getId());
                Log.i("经纪人", "num：" + num);
                Log.i("经纪人", "s3：" + s3);
                Log.i("经纪人", "s4：" + s4);
                Log.i("经纪人", "s5：" + s5);
                Log.i("经纪人", "s6：" + s6);
                Log.i("经纪人", "FinalContents.getUserID()：" + FinalContents.getUserID());
                Log.i("经纪人", "FinalContents.getCompanyManageId()：" + FinalContents.getCompanyManageId());
                Log.i("经纪人", "FinalContents.getStoreManageId()：" + FinalContents.getStoreManageId());
                Log.i("经纪人", "isCover：" + isCover);

                Observable<AddBrokerBean> addBrokerBean = fzbInterface.getAddBrokerBean(data.getId(), num, s3, s4, s5, s6, FinalContents.getUserID(), FinalContents.getCompanyManageId(), FinalContents.getStoreManageId(), isCover);
                addBrokerBean.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<AddBrokerBean>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                            }

                            @Override
                            public void onNext(AddBrokerBean addBrokerBean) {
                                if (addBrokerBean.getData().getStatus() == 1) {
                                    ToastUtil.showLongToast(AddBrokerActivity.this, addBrokerBean.getData().getMessage());
                                    FinalContents.setAddtype1("");
                                    FinalContents.setAddtype2("");
                                    BrokerActivity.brokerActivity.finish();
                                    finish();
                                } else {
                                    ToastUtil.showLongToast(AddBrokerActivity.this, addBrokerBean.getData().getMessage());
                                    FinalContents.setBorkerChange("修改");
                                }
                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.i("经纪人", "修改经纪人错误信息：" + e.getMessage());
                            }

                            @Override
                            public void onComplete() {
                            }
                        });
            }
            FinalContents.setBorkerChange("");
        }


    }

    @Override
    protected void onRestart() {
        super.onRestart();

        add_store_tv1.setText(FinalContents.getAddtype2());
        add_store_tv2.setText(FinalContents.getAddtype1());

        if (FinalContents.getCompanyManageId().equals(ifCompanyManageId)) {
            add_rb1.setVisibility(View.VISIBLE);
        } else {
            add_rb1.setVisibility(View.GONE);
            add_rb2.setChecked(true);
        }

    }
}

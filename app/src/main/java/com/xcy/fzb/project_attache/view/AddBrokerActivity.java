package com.xcy.fzb.project_attache.view;

import android.content.Intent;
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
import com.xcy.fzb.R;
import com.xcy.fzb.all.api.FinalContents;
import com.xcy.fzb.all.database.AddBrokerBean;
import com.xcy.fzb.all.database.BrokerChangeBean;
import com.xcy.fzb.all.persente.StatusBar;
import com.xcy.fzb.all.service.MyService;
import com.xcy.fzb.all.view.AllActivity;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//TODO 添加经纪人
public class AddBrokerActivity extends AllActivity implements View.OnClickListener {

    RelativeLayout add_store_rl1;
    RelativeLayout add_store_rl2;
    RelativeLayout add_store_rl3;

    Button add_store_btn;

    TextView add_store_tv1;
    TextView add_store_tv2;
    TextView add_store_tv3;

    EditText add_store_et1;
    EditText add_store_et2;
    EditText add_store_et3;
    EditText add_store_et4;

    RelativeLayout add_store_return;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.project_attache_activity_add_broker);

        initView();

    }

    private void initView() {
        StatusBar.makeStatusBarTransparent(this);
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
        add_store_et2 = findViewById(R.id.add_et2);
        add_store_et3 = findViewById(R.id.add_et3);
        add_store_et4 = findViewById(R.id.add_et4);
        add_store_return = findViewById(R.id.add_return);

        add_store_return.setOnClickListener(this);
        add_store_rl1.setOnClickListener(this);
        add_store_rl2.setOnClickListener(this);
        add_store_rl3.setOnClickListener(this);
        add_store_btn.setOnClickListener(this);

        if (FinalContents.getBorkerChange().equals("修改")) {
            initChangeData();
            add_title.setText("修改经纪人");
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
                Log.i("MyCL", "点击了");
                add_store_tv2.setText("");
                FinalContents.setAddtype1("");
                intent = new Intent(AddBrokerActivity.this, StoreListActivity.class);
                FinalContents.setMyAddType("公司");
                startActivity(intent);
                break;
            case R.id.add_rl2:
                Log.i("MyCL", "点击了");
                if (add_store_tv1.getText().equals("")) {
                    Toast.makeText(AddBrokerActivity.this, "请先选择公司", Toast.LENGTH_SHORT).show();
                } else {
                    intent = new Intent(AddBrokerActivity.this, StoreListActivity.class);
                    FinalContents.setMyAddType("门店");
                    startActivity(intent);
                }

                break;
            case R.id.add_rl3:
                Log.i("MyCL", "点击了");
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
                break;
            case R.id.add_btn:
                s = add_store_tv1.getText().toString();
                s2 = add_store_tv2.getText().toString();
                s11 = add_store_tv3.getText().toString();

                s3 = add_store_et1.getText().toString();
                s4 = add_store_et2.getText().toString();
                s5 = add_store_et3.getText().toString();
                s6 = add_store_et4.getText().toString();

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
                            if (addBrokerBean.getData().getMessage().equals("保存成功")) {
                                Toast.makeText(AddBrokerActivity.this, addBrokerBean.getData().getMessage(), Toast.LENGTH_SHORT).show();
                                finish();
                            } else {
                                Toast.makeText(AddBrokerActivity.this, addBrokerBean.getData().getMessage(), Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        }

                        @Override
                        public void onError(Throwable e) {
                        }

                        @Override
                        public void onComplete() {
                        }
                    });
        } else if (FinalContents.getBorkerChange().equals("修改")) {
            if (s11.equals("经纪公司管理者")) {
                num = "1";
            } else if (s11.equals("经纪门店管理者")) {
                num = "2";
            } else if (s11.equals("普通经纪人")) {
                num = "3";
            }
            Retrofit.Builder builder = new Retrofit.Builder();
            builder.baseUrl(FinalContents.getBaseUrl());
            builder.addConverterFactory(GsonConverterFactory.create());
            builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
            Retrofit build = builder.build();
            MyService fzbInterface = build.create(MyService.class);
            Observable<AddBrokerBean> addBrokerBean = fzbInterface.getAddBrokerBean(data.getId(), num, s3, s4, s5, s6, FinalContents.getUserID(), FinalContents.getCompanyManageId(), FinalContents.getStoreManageId(), "1");
            addBrokerBean.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<AddBrokerBean>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                        }

                        @Override
                        public void onNext(AddBrokerBean addBrokerBean) {
                            if (addBrokerBean.getData().getMessage().equals("保存成功")) {
                                Toast.makeText(AddBrokerActivity.this, addBrokerBean.getData().getMessage(), Toast.LENGTH_SHORT).show();
                                FinalContents.setAddtype1("");
                                FinalContents.setAddtype2("");
                                finish();
                            } else {
                                Toast.makeText(AddBrokerActivity.this, addBrokerBean.getData().getMessage(), Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        }

                        @Override
                        public void onError(Throwable e) {
                        }

                        @Override
                        public void onComplete() {
                        }
                    });
            FinalContents.setBorkerChange("");
        }


    }

    @Override
    protected void onRestart() {
        super.onRestart();
        add_store_tv1.setText(FinalContents.getAddtype2());
        add_store_tv2.setText(FinalContents.getAddtype1());

    }
}

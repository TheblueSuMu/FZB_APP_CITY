package com.xcy.fzbcity.all.view;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.xcy.fzbcity.all.utils.ToastUtil;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bumptech.glide.Glide;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.adapter.ClientParticularsAdapter;
import com.xcy.fzbcity.all.api.FinalContents;
import com.xcy.fzbcity.all.modle.ClientParticularsBean;
import com.xcy.fzbcity.all.persente.SingleClick;
import com.xcy.fzbcity.all.persente.StatusBar;
import com.xcy.fzbcity.all.service.MyService;
import com.xcy.fzbcity.all.utils.CommonUtil;
import com.xcy.fzbcity.all.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ClientParticularsActivity extends AllActivity implements View.OnClickListener {

    ImageView client_particulars_img;
    RelativeLayout client_particulars_return;
    ImageView client_particulars_call;
    TextView client_particulars_name;
    TextView client_particulars_phone;
    RecyclerView client_particulars_rv;

    ClientParticularsAdapter clientParticularsAdapter;
    private ClientParticularsBean.DataBean.InfoDataBean infoData;
    private ImageView client_particulars_report;
    private ImageView all_no_information;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_particulars);
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
        client_particulars_report = findViewById(R.id.client_particulars_report);
        client_particulars_img = findViewById(R.id.client_particulars_img);
        client_particulars_return = findViewById(R.id.client_particulars_return);
        client_particulars_call = findViewById(R.id.client_particulars_call);
        client_particulars_name = findViewById(R.id.client_particulars_name);
        client_particulars_phone = findViewById(R.id.client_particulars_phone);
        client_particulars_rv = findViewById(R.id.client_particulars_rv);
        all_no_information = findViewById(R.id.all_no_information);

        client_particulars_img.setOnClickListener(this);
        client_particulars_return.setOnClickListener(this);



        if (!FinalContents.getCityID().equals(FinalContents.getOldCityId())) {
            client_particulars_report.setVisibility(View.GONE);
        }else {
            client_particulars_report.setVisibility(View.VISIBLE);
            if (FinalContents.getIdentity().equals("63") || FinalContents.getIdentity().equals("4") || FinalContents.getIdentity().equals("5") || FinalContents.getIdentity().equals("7") || FinalContents.getIdentity().equals("8") || FinalContents.getIdentity().equals("9") ) {
                client_particulars_report.setVisibility(View.GONE);
            }else {
                client_particulars_report.setVisibility(View.VISIBLE);
            }
        }

        initData();

    }

    @SuppressLint("WrongConstant")
    private void initData() {

        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        client_particulars_rv.setLayoutManager(manager);

        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<ClientParticularsBean> clientParticularsBean = fzbInterface.getClientParticularsBean(FinalContents.getUserID(), FinalContents.getCustomerID());
        clientParticularsBean.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ClientParticularsBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(final ClientParticularsBean clientParticularsBean) {
                        infoData = clientParticularsBean.getData().getInfoData();
//
                        Glide.with(ClientParticularsActivity.this).load(FinalContents.getImageUrl() + infoData.getCustomerImg()).into(client_particulars_img);
                        client_particulars_name.setText(infoData.getCustomerName() + "");
                        client_particulars_phone.setText(infoData.getContactsPhone1() + "");

                        client_particulars_phone.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                final List<String> list = new ArrayList<>();
                                list.add(infoData.getContactsPhone1());
                                list.add(infoData.getContactsPhone2());
                                list.add(infoData.getContactsPhone3());
                                //      监听选中
                                OptionsPickerView pvOptions = new OptionsPickerBuilder(view.getContext(), new OnOptionsSelectListener() {
                                    @Override
                                    public void onOptionsSelect(final int options1, int option2, int options3, View v) {
                                        //               返回的分别是三个级别的选中位置
                                        //              展示选中数据
                                        AlertDialog.Builder builder = new AlertDialog.Builder(ClientParticularsActivity.this);
                                        builder.setMessage("确认拨打号码："+list.get(options1));
                                        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                //            TODO 拨打手机号
                                                Intent dialIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" +list.get(options1)));//跳转到拨号界面，同时传递电话号码
                                                startActivity(dialIntent);
                                            }
                                        });
                                        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                ToastUtil.showToast(ClientParticularsActivity.this, "取消拨打电话");
                                            }
                                        });
                                        builder.show();
                                    }
                                })
                                        .setSelectOptions(0)//设置选择第一个
                                        .setOutSideCancelable(false)//点击背的地方不消失
                                        .build();//创建
                                //      把数据绑定到控件上面
                                pvOptions.setPicker(list);
                                //      展示
                                pvOptions.show();
                            }
                        });

                        client_particulars_call.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                final List<String> list = new ArrayList<>();
                                list.add(infoData.getContactsPhone1());
                                list.add(infoData.getContactsPhone2());
                                list.add(infoData.getContactsPhone3());
                                //      监听选中
                                OptionsPickerView pvOptions = new OptionsPickerBuilder(view.getContext(), new OnOptionsSelectListener() {
                                    @Override
                                    public void onOptionsSelect(final int options1, int option2, int options3, View v) {
                                        //               返回的分别是三个级别的选中位置
                                        //              展示选中数据
                                        AlertDialog.Builder builder = new AlertDialog.Builder(ClientParticularsActivity.this);
                                        builder.setMessage("确认拨打号码："+list.get(options1));
                                        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                //            TODO 拨打手机号
                                                Intent dialIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" +list.get(options1)));//跳转到拨号界面，同时传递电话号码
                                                startActivity(dialIntent);
                                            }
                                        });
                                        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                ToastUtil.showToast(ClientParticularsActivity.this,"取消拨打电话");
                                            }
                                        });
                                        builder.show();
                                    }
                                })
                                        .setSelectOptions(0)//设置选择第一个
                                        .setOutSideCancelable(false)//点击背的地方不消失
                                        .build();//创建
                                //      把数据绑定到控件上面
                                pvOptions.setPicker(list);
                                //      展示
                                pvOptions.show();
                            }
                        });
                        client_particulars_report.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                finish();
                                FinalContents.setClientPhone(clientParticularsBean.getData().getInfoData().getContactsPhone1());
                                FinalContents.setClientName(clientParticularsBean.getData().getInfoData().getCustomerName());
                                FinalContents.setCustomerID(clientParticularsBean.getData().getInfoData().getId());
                                FinalContents.setChecked(true);
                                Intent intent = new Intent(ClientParticularsActivity.this, ReportActivity.class);
                                startActivity(intent);
                            }
                        });

                        if (clientParticularsBean.getData().getListData().size() != 0) {
                            //        TODO RecyclerView
                            List<ClientParticularsBean.DataBean.ListDataBean> listData = clientParticularsBean.getData().getListData();
                            clientParticularsAdapter = new ClientParticularsAdapter();
                            clientParticularsAdapter.setListData(listData);
                            client_particulars_rv.setAdapter(clientParticularsAdapter);
                            client_particulars_rv.setVisibility(View.VISIBLE);
                            all_no_information.setVisibility(View.GONE);
                        }else {
                            client_particulars_rv.setVisibility(View.GONE);
                            all_no_information.setVisibility(View.VISIBLE);
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        client_particulars_rv.setVisibility(View.GONE);
                        all_no_information.setVisibility(View.VISIBLE);
                        Log.i("MyCL", "ClientParticularsActivity错误信息：" + e.getMessage());
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
            case R.id.client_particulars_img:
                Intent dialIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + infoData.getContacts1()));//跳转到拨号界面，同时传递电话号码
                startActivity(dialIntent);
                break;
            case R.id.client_particulars_return:
                finish();
                break;
        }

    }

}

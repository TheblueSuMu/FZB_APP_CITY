package com.xcy.fzb.all.view;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bumptech.glide.Glide;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.xcy.fzb.R;
import com.xcy.fzb.all.adapter.ClientParticularsAdapter;
import com.xcy.fzb.all.api.FinalContents;
import com.xcy.fzb.all.modle.ClientParticularsBean;
import com.xcy.fzb.all.persente.StatusBar;
import com.xcy.fzb.all.service.MyService;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_particulars);


        initView();

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

        client_particulars_img.setOnClickListener(this);
        client_particulars_return.setOnClickListener(this);


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
                        Glide.with(ClientParticularsActivity.this).load("http://39.98.173.250:8080" + infoData.getCustomerImg()).into(client_particulars_img);
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
                                                Toast.makeText(ClientParticularsActivity.this, "取消拨打电话", Toast.LENGTH_SHORT).show();
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
                                                Toast.makeText(ClientParticularsActivity.this, "取消拨打电话", Toast.LENGTH_SHORT).show();
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
                                FinalContents.setClientName(clientParticularsBean.getData().getInfoData().getCustomerName());
                                FinalContents.setCustomerID(clientParticularsBean.getData().getInfoData().getId());
                                FinalContents.setChecked(true);
                                Log.i("报备跳转","数据1："+FinalContents.getClientName());
                                Log.i("报备跳转","数据2："+FinalContents.getCustomerID());
                                Intent intent = new Intent(ClientParticularsActivity.this, ReportActivity.class);
                                startActivity(intent);
                            }
                        });

//        TODO RecyclerView
                        List<ClientParticularsBean.DataBean.ListDataBean> listData = clientParticularsBean.getData().getListData();
                        clientParticularsAdapter = new ClientParticularsAdapter();
                        clientParticularsAdapter.setListData(listData);
                        client_particulars_rv.setAdapter(clientParticularsAdapter);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("MyCL", "ClientParticularsActivity错误信息：" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

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

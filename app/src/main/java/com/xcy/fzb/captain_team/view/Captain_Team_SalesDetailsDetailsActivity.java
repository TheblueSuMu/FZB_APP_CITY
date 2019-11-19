package com.xcy.fzb.captain_team.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IFillFormatter;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.interfaces.dataprovider.LineDataProvider;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.xcy.fzb.R;
import com.xcy.fzb.all.api.FinalContents;
import com.xcy.fzb.all.api.NewlyIncreased;
import com.xcy.fzb.all.database.AgentDetailsBean;
import com.xcy.fzb.all.database.DataStatisticsBean;
import com.xcy.fzb.all.persente.StatusBar;
import com.xcy.fzb.all.service.MyService;
import com.xcy.fzb.all.utils.CommonUtil;
import com.xcy.fzb.all.view.AllActivity;
import com.xcy.fzb.captain_assistant.view.Assistant_Addteam_Activity;
import com.xcy.fzb.captain_team.adapter.Captain_Team_PopAdapter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import top.defaults.view.DateTimePickerView;

public class Captain_Team_SalesDetailsDetailsActivity extends AllActivity implements View.OnClickListener {
    private LinearLayout sales_details_details_ll1;
    private LinearLayout sales_details_details_ll2;
    private LinearLayout sales_details_details_ll3;
    private LinearLayout sales_details_details_ll4;
    private LinearLayout sales_details_details_ll5;
    private LinearLayout sales_details_details_ll6;
    private LinearLayout sales_details_details_ll7;
    private LinearLayout sales_details_details_picker;

    private TextView sales_details_details_tv1;
    private TextView sales_details_details_tv2;
    private TextView sales_details_details_tv3;
    private TextView sales_details_details_tv4;
    private TextView sales_details_details_tv5;
    private TextView sales_details_details_tv6;
    private TextView sales_details_details_tv7;
    private TextView sales_details_details_tv8;
    private TextView sales_details_details_tv9;
    private TextView sales_details_details_type;
    private TextView sales_details_details_time1;
    private TextView sales_details_details_time2;
    private TextView sales_details_details_cancel;
    private TextView sales_details_details_ensure;
    private TextView sales_details_details_name;
    private TextView sales_details_details_tv10;
    private TextView sales_details_details_tv11;

    private RadioButton sales_details_details_rb1;
    private RadioButton sales_details_details_rb2;
    private RadioButton sales_details_details_rb3;
    private RadioButton sales_details_details_rb4;

    private ImageView sales_details_details_img;

    private LineChart sales_details_details_lc;
    private List<String> names = new ArrayList<>(); //折线名字集合
    private List<Integer> colour = new ArrayList<>();//折线颜色集合
    private List<String> indexList;

    private DateTimePickerView dateTimePickerView;


    private RelativeLayout sales_details_details_return;
    private RelativeLayout sales_details_details_amend;
    private Intent intent;
    private String startTime = "";
    private String endTime = "";
    private String type = "";
    private ImageView sales_details_details_xiugai;
    private AgentDetailsBean agentDetails;
    private PopupWindow p;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.captain_team_activity_sales_details_details);
        init_No_Network();
    }

    private void init_No_Network() {
        boolean networkAvailable = CommonUtil.isNetworkAvailable(this);
        if (networkAvailable) {
            NewlyIncreased.setTag("");
            NewlyIncreased.setStartDate("");
            NewlyIncreased.setEndDate("");
            initfvb();
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
            Toast.makeText(this, "当前无网络，请检查网络后再进行登录", Toast.LENGTH_SHORT).show();
        }
    }


    private void initfvb() {

        StatusBar.makeStatusBarTransparent(this);

        sales_details_details_ll1 = findViewById(R.id.sales_details_details_ll1);
        sales_details_details_ll2 = findViewById(R.id.sales_details_details_ll2);
        sales_details_details_ll3 = findViewById(R.id.sales_details_details_ll3);
        sales_details_details_ll4 = findViewById(R.id.sales_details_details_ll4);
        sales_details_details_ll5 = findViewById(R.id.sales_details_details_ll5);
        sales_details_details_ll6 = findViewById(R.id.sales_details_details_ll6);
        sales_details_details_ll7 = findViewById(R.id.sales_details_details_ll7);
        sales_details_details_picker = findViewById(R.id.sales_details_details_picker);
        sales_details_details_return = findViewById(R.id.sales_details_details_return);
        sales_details_details_amend = findViewById(R.id.sales_details_details_amend);

        sales_details_details_xiugai = findViewById(R.id.sales_details_details_xiugai);

        sales_details_details_tv1 = findViewById(R.id.sales_details_details_tv1);
        sales_details_details_tv2 = findViewById(R.id.sales_details_details_tv2);
        sales_details_details_tv3 = findViewById(R.id.sales_details_details_tv3);
        sales_details_details_tv4 = findViewById(R.id.sales_details_details_tv4);
        sales_details_details_tv5 = findViewById(R.id.sales_details_details_tv5);
        sales_details_details_tv6 = findViewById(R.id.sales_details_details_tv6);
        sales_details_details_tv7 = findViewById(R.id.sales_details_details_tv7);
        sales_details_details_tv8 = findViewById(R.id.sales_details_details_tv8);
        sales_details_details_tv9 = findViewById(R.id.sales_details_details_tv9);
        sales_details_details_type = findViewById(R.id.sales_details_details_type);
        sales_details_details_time1 = findViewById(R.id.sales_details_details_time1);
        sales_details_details_time2 = findViewById(R.id.sales_details_details_time2);
        sales_details_details_cancel = findViewById(R.id.sales_details_details_cancel);
        sales_details_details_ensure = findViewById(R.id.sales_details_details_ensure);
        sales_details_details_name = findViewById(R.id.sales_details_details_name);
        sales_details_details_tv10 = findViewById(R.id.sales_details_details_tv10);
        sales_details_details_tv11 = findViewById(R.id.sales_details_details_tv11);

        sales_details_details_rb1 = findViewById(R.id.sales_details_details_rb1);
        sales_details_details_rb2 = findViewById(R.id.sales_details_details_rb2);
        sales_details_details_rb3 = findViewById(R.id.sales_details_details_rb3);
        sales_details_details_rb4 = findViewById(R.id.sales_details_details_rb4);

        sales_details_details_img = findViewById(R.id.sales_details_details_img);

        sales_details_details_lc = findViewById(R.id.sales_details_details_lc);

        dateTimePickerView = findViewById(R.id.sales_details_details_pickerView);

        sales_details_details_ll2.setOnClickListener(this);
        sales_details_details_ll3.setOnClickListener(this);
        sales_details_details_ll4.setOnClickListener(this);
        sales_details_details_ll5.setOnClickListener(this);
        sales_details_details_ll6.setOnClickListener(this);
        sales_details_details_ll7.setOnClickListener(this);

        sales_details_details_rb1.setOnClickListener(this);
        sales_details_details_rb2.setOnClickListener(this);
        sales_details_details_rb3.setOnClickListener(this);
        sales_details_details_rb4.setOnClickListener(this);

        sales_details_details_time1.setOnClickListener(this);
        sales_details_details_time2.setOnClickListener(this);

        sales_details_details_tv10.setOnClickListener(this);
        sales_details_details_tv11.setOnClickListener(this);

        sales_details_details_return.setOnClickListener(this);
        sales_details_details_amend.setOnClickListener(this);
        sales_details_details_xiugai.setOnClickListener(this);


        if (FinalContents.getIdentity().equals("60")) {
            if (FinalContents.getManageFlag().equals("0")) {
                sales_details_details_xiugai.setVisibility(View.GONE);
            } else {
                if (FinalContents.getXiaoShou().equals("不")) {
                    sales_details_details_xiugai.setVisibility(View.GONE);
                    sales_details_details_amend.setVisibility(View.GONE);
                } else if (FinalContents.getXiaoShou().equals("是")) {
                    sales_details_details_xiugai.setVisibility(View.VISIBLE);
                    sales_details_details_amend.setVisibility(View.VISIBLE);
                }
            }

        }

        sales_details_details_tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dialIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + agentDetails.getData().getAgentInfo().getPhone()));//跳转到拨号界面，同时传递电话号码
                startActivity(dialIntent);
            }
        });

        initData();
    }


    private void initData() {
        Log.i("顾问", "从详情页获取的ID：" + FinalContents.getInforId());
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<AgentDetailsBean> clientCommissions = fzbInterface.getAgentDetails(FinalContents.getUserID(), FinalContents.getInforId());
        clientCommissions.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<AgentDetailsBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(AgentDetailsBean agentDetailsBean) {
                        agentDetails = agentDetailsBean;
                        sales_details_details_tv4.setText(agentDetailsBean.getData().getAgentDataStatistics().getReportNumber() + "");
                        sales_details_details_tv5.setText(agentDetailsBean.getData().getAgentDataStatistics().getAccessingNumber() + "");
                        sales_details_details_tv6.setText(agentDetailsBean.getData().getAgentDataStatistics().getIsIslandNumber() + "");
                        sales_details_details_tv7.setText(agentDetailsBean.getData().getAgentDataStatistics().getEarnestMoneyNumber() + "");
                        sales_details_details_tv8.setText(agentDetailsBean.getData().getAgentDataStatistics().getTradeNumber() + "");
                        sales_details_details_tv9.setText(agentDetailsBean.getData().getAgentDataStatistics().getInvalidNum() + "");

                        sales_details_details_name.setText(agentDetailsBean.getData().getAgentInfo().getName());
                        sales_details_details_tv2.setText(agentDetailsBean.getData().getAgentInfo().getPhone());
                        Glide.with(Captain_Team_SalesDetailsDetailsActivity.this).load(FinalContents.getImageUrl() + agentDetailsBean.getData().getAgentInfo().getPhoto()).into(sales_details_details_img);

                        FinalContents.setAgentId(agentDetailsBean.getData().getAgentInfo().getId());
                        if (FinalContents.getIdentity().equals("60")) {
                            Log.i("测试", "第一个显示");
                            if (FinalContents.getManageFlag().equals("0")) {
                                Log.i("测试", "第二个显示");
                                sales_details_details_xiugai.setVisibility(View.GONE);
                            } else {

                                Log.i("测试", "第二个不显示");
                                if (FinalContents.getXiaoShou().equals("不")) {
                                    Log.i("测试", "第三个显示");
                                    sales_details_details_xiugai.setVisibility(View.GONE);
                                    sales_details_details_amend.setVisibility(View.GONE);
                                } else if (FinalContents.getXiaoShou().equals("是")) {
                                    if (sales_details_details_tv10.getText().toString().equals("他的销售")) {
                                        sales_details_details_xiugai.setVisibility(View.GONE);
                                        sales_details_details_amend.setVisibility(View.GONE);
                                    }else if(sales_details_details_tv10.getText().toString().equals("他的顾问")){
                                        sales_details_details_xiugai.setVisibility(View.VISIBLE);
                                        sales_details_details_amend.setVisibility(View.VISIBLE);
                                    }
                                } else {
                                    Log.i("测试", "第三个不显示");
                                    if (agentDetailsBean.getData().getAgentInfo().getIdentity().equals("61")) {
                                        Log.i("测试", "显示");
                                        sales_details_details_xiugai.setVisibility(View.VISIBLE);
                                        sales_details_details_amend.setVisibility(View.VISIBLE);
                                    } else {
                                        Log.i("测试", "不显示");
                                        sales_details_details_xiugai.setVisibility(View.GONE);
                                        sales_details_details_amend.setVisibility(View.GONE);
                                    }
                                }
                            }
                        }

                        else if (FinalContents.getIdentity().equals("61")) {
                            Log.i("顾问", "第一个不显示");
                            if (FinalContents.getManageFlag().equals("0")) {
                                Log.i("顾问", "第二个显示");
                                sales_details_details_xiugai.setVisibility(View.GONE);
                            } else {
                                Log.i("顾问", "第二个不显示");
                                if (agentDetailsBean.getData().getAgentInfo().getIdentity().equals("62")) {
                                    Log.i("顾问", "显示");
                                    sales_details_details_xiugai.setVisibility(View.VISIBLE);
                                    sales_details_details_amend.setVisibility(View.VISIBLE);
                                } else {
                                    Log.i("顾问", "不显示");
                                    sales_details_details_xiugai.setVisibility(View.GONE);
                                    sales_details_details_amend.setVisibility(View.GONE);
                                }

                            }
                        }

                        /**
                         * 20191104 团助 修改
                         */

                        else if (FinalContents.getIdentity().equals("63")){
                            Log.i("顾问","第一个不显示");
                            if(FinalContents.getManageFlag().equals("0")){
                                Log.i("顾问","第二个显示");
                                sales_details_details_xiugai.setVisibility(View.VISIBLE);
                            }else {
                                Log.i("顾问","第二个不显示");
                                if (agentDetailsBean.getData().getAgentInfo().getIdentity().equals("60")) {
                                    Log.i("顾问","显示");
                                    sales_details_details_xiugai.setVisibility(View.VISIBLE);
                                    sales_details_details_amend.setVisibility(View.VISIBLE);
                                }else {
                                    Log.i("顾问","不显示");
                                    sales_details_details_xiugai.setVisibility(View.VISIBLE);
                                    sales_details_details_amend.setVisibility(View.VISIBLE);
                                }

                            }
                        }

                        if (agentDetailsBean.getData().getAgentInfo().getType().equals("2")) {
                            sales_details_details_tv1.setText(agentDetailsBean.getData().getAgentInfo().getName() + "(" + agentDetailsBean.getData().getAgentInfo().getRatioName() + ")");
                            if (agentDetailsBean.getData().getAgentInfo().getCounselorNum().equals("")) {
                                if (agentDetailsBean.getData().getAgentInfo().getLeaderName().equals("")) {
                                    sales_details_details_tv3.setText("团队长：暂无  顾问：0人");
                                } else {
                                    sales_details_details_tv3.setText("团队长：" + agentDetailsBean.getData().getAgentInfo().getLeaderName() + "  顾问：0人");
                                }
                            } else {
                                if (agentDetailsBean.getData().getAgentInfo().getLeaderName().equals("")) {
                                    sales_details_details_tv3.setText("团队长：暂无  顾问：" + agentDetailsBean.getData().getAgentInfo().getCounselorNum() + "人");
                                } else {
                                    sales_details_details_tv3.setText("团队长：" + agentDetailsBean.getData().getAgentInfo().getLeaderName() + "  顾问：" + agentDetailsBean.getData().getAgentInfo().getCounselorNum() + "人");
                                }
                            }
                            sales_details_details_tv10.setText("他的顾问");
                        } else if (agentDetailsBean.getData().getAgentInfo().getType().equals("3")) {
                            sales_details_details_tv1.setText(agentDetailsBean.getData().getAgentInfo().getName() + "(" + agentDetailsBean.getData().getAgentInfo().getRatioName() + ")");
                            if (agentDetailsBean.getData().getAgentInfo().getSaleName().equals("")) {
                                if (agentDetailsBean.getData().getAgentInfo().getLeaderName().equals("")) {
                                    sales_details_details_tv3.setText("团队长：暂无  暂无销售");
                                } else {
                                    sales_details_details_tv3.setText("团队长：" + agentDetailsBean.getData().getAgentInfo().getLeaderName() + "  暂无销售");
                                }
                            } else {
                                if (agentDetailsBean.getData().getAgentInfo().getLeaderName().equals("")) {
                                    sales_details_details_tv3.setText("团队长：暂无  销售：" + agentDetailsBean.getData().getAgentInfo().getSaleName());
                                } else {
                                    sales_details_details_tv3.setText("团队长：" + agentDetailsBean.getData().getAgentInfo().getLeaderName() + "  销售：" + agentDetailsBean.getData().getAgentInfo().getSaleName());
                                }
                            }
                            sales_details_details_tv10.setText("他的销售");
                        } else if (agentDetailsBean.getData().getAgentInfo().getType().equals("1")) {
                            sales_details_details_tv1.setText(agentDetailsBean.getData().getAgentInfo().getName());
                            if (agentDetailsBean.getData().getAgentInfo().getCounselorNum().equals("")) {
                                if (agentDetailsBean.getData().getAgentInfo().getSaleNum().equals("")) {
                                    sales_details_details_tv3.setText("销售：0 人  顾问：0人");
                                } else {
                                    sales_details_details_tv3.setText("销售：" + agentDetailsBean.getData().getAgentInfo().getSaleNum() + "人  顾问：0人");
                                }
                            } else {
                                if (agentDetailsBean.getData().getAgentInfo().getSaleNum().equals("")) {
                                    sales_details_details_tv3.setText("销售：0 人  顾问：" + agentDetailsBean.getData().getAgentInfo().getCounselorNum() + "人");
                                } else {
                                    sales_details_details_tv3.setText("销售：" + agentDetailsBean.getData().getAgentInfo().getSaleNum() + "人  顾问：" + agentDetailsBean.getData().getAgentInfo().getCounselorNum() + "人");
                                }
                            }
                            sales_details_details_tv10.setText("他的销售");
                        }

                        List<Integer> integers = agentDetailsBean.getData().getGsonOption().getSeries().get(0).getData();
                        indexList = agentDetailsBean.getData().getGsonOption().getXAxis().getData();
                        init(integers);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("MyCL", "销售详情页第一次:" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            //      TODO 进入报备
            case R.id.sales_details_details_ll2:
                FinalContents.setQuanceng("1");
                FinalContents.setMySelf("0");
                intent = new Intent(Captain_Team_SalesDetailsDetailsActivity.this, Captain_Team_MyClientActivity.class);
                intent.putExtra("client", "1");
                startActivity(intent);
                break;
            //      TODO 进入到访
            case R.id.sales_details_details_ll3:
                FinalContents.setQuanceng("1");
                FinalContents.setMySelf("0");
                intent = new Intent(Captain_Team_SalesDetailsDetailsActivity.this, Captain_Team_MyClientActivity.class);
                intent.putExtra("client", "2");
                startActivity(intent);
                break;
            //      TODO 进入登岛
            case R.id.sales_details_details_ll4:
                FinalContents.setQuanceng("1");
                FinalContents.setMySelf("0");
                intent = new Intent(Captain_Team_SalesDetailsDetailsActivity.this, Captain_Team_MyClientActivity.class);
                intent.putExtra("client", "3");
                startActivity(intent);
                break;
            //      TODO 进入认筹
            case R.id.sales_details_details_ll5:
                FinalContents.setQuanceng("1");
                FinalContents.setMySelf("0");
                intent = new Intent(Captain_Team_SalesDetailsDetailsActivity.this, Captain_Team_MyClientActivity.class);
                intent.putExtra("client", "4");
                startActivity(intent);
                break;
            //      TODO 进入成交
            case R.id.sales_details_details_ll6:
                FinalContents.setQuanceng("1");
                FinalContents.setMySelf("0");
                intent = new Intent(Captain_Team_SalesDetailsDetailsActivity.this, Captain_Team_MyClientActivity.class);
                intent.putExtra("client", "5");
                startActivity(intent);
                break;
            //      TODO 进入失效
            case R.id.sales_details_details_ll7:
                FinalContents.setQuanceng("1");
                FinalContents.setMySelf("0");
                intent = new Intent(Captain_Team_SalesDetailsDetailsActivity.this, Captain_Team_MyClientActivity.class);
                intent.putExtra("client", "6");
                startActivity(intent);
                break;
            //      TODO 数据统计 全部
            case R.id.sales_details_details_rb1:
                sales_details_details_ll1.setVisibility(View.GONE);
                type = "0";
                startTime = "";
                endTime = "";
                NewlyIncreased.setTag("0");
                initDataStatistics();
                break;
            //      TODO 数据统计 昨天
            case R.id.sales_details_details_rb2:
                sales_details_details_ll1.setVisibility(View.GONE);
                type = "1";
                startTime = "";
                endTime = "";
                NewlyIncreased.setTag("1");
                initDataStatistics();
                break;
            //      TODO 数据统计 七天
            case R.id.sales_details_details_rb3:
                sales_details_details_ll1.setVisibility(View.GONE);
                type = "2";
                startTime = "";
                endTime = "";
                NewlyIncreased.setTag("2");
                initDataStatistics();
                break;
            //      TODO 数据统计 自定义
            case R.id.sales_details_details_rb4:
                sales_details_details_ll1.setVisibility(View.VISIBLE);
                initDate();
                type = "3";
                NewlyIncreased.setTag("3");
                break;
            //      TODO 选择开始时间
            case R.id.sales_details_details_time1:
                initDate();
                break;
            //      TODO 选择结束时间
            case R.id.sales_details_details_time2:
                initDate();
                break;
            //      TODO 退出
            case R.id.sales_details_details_return:
                finish();
                break;
            //      TODO 进入修改编辑界面
            case R.id.sales_details_details_amend:
                if (FinalContents.getIdentity().equals("60")) {
                    finish();
                    FinalContents.setXiuGai("修改销售");
                    FinalContents.setOwnerId("");
                    Log.i("销售啊啊啊", "修改销售：" + FinalContents.getXiuGai());
                    Intent intent = new Intent(Captain_Team_SalesDetailsDetailsActivity.this, Captain_Team_AddSalesActivity.class);
                    startActivity(intent);
                    finish();
                } else if (FinalContents.getIdentity().equals("61")) {
                    finish();
                    FinalContents.setXiuGai("修改顾问");
                    FinalContents.setOwnerId("");
                    Log.i("销售啊啊啊", "修改gw：" + FinalContents.getXiuGai());
                    Intent intent = new Intent(Captain_Team_SalesDetailsDetailsActivity.this, Captain_Team_AddAConsultantActivity.class);
                    startActivity(intent);
                    finish();
                } else if (FinalContents.getIdentity().equals("63")) {
                    Log.i("团助端修改", "63");
                    if (agentDetails.getData().getAgentInfo().getType().equals("1")) {
                        Log.i("团助端修改", "1");
                        PopWindow1();
//                        FinalContents.setOwnerId("");
//                        intent = new Intent(Captain_Team_SalesDetailsDetailsActivity.this, Assistant_Addteam_Activity.class);
//                        FinalContents.setXiuGai("修改团队长");
//                        startActivity(intent);
                    } else if (agentDetails.getData().getAgentInfo().getType().equals("2")) {
                        Log.i("团助端修改", "2");
                        PopWindow1();
//                        FinalContents.setOwnerId("");
//                        intent = new Intent(Captain_Team_SalesDetailsDetailsActivity.this, Captain_Team_AddSalesActivity.class);
//                        FinalContents.setXiuGai("修改销售");
//                        startActivity(intent);
                    } else if (agentDetails.getData().getAgentInfo().getType().equals("3")) {
                        Log.i("团助端修改", "3");
                        FinalContents.setOwnerId("");
                        intent = new Intent(Captain_Team_SalesDetailsDetailsActivity.this, Captain_Team_AddAConsultantActivity.class);
                        FinalContents.setXiuGai("修改顾问");
                        startActivity(intent);
                        finish();
                    }
                }
                break;
            //      TODO 他的顾问
            case R.id.sales_details_details_tv10:
                if (agentDetails.getData().getAgentInfo().getType().equals("2")) {
                    finish();
                    FinalContents.setInforId(agentDetails.getData().getAgentInfo().getId());
                    Log.i("顾问", "销售从详情页获取的顾问ID：" + FinalContents.getInforId());
                    intent = new Intent(Captain_Team_SalesDetailsDetailsActivity.this, Captain_Team_SalesDetails.class);
                    intent.putExtra("searchName", "顾问");
                    startActivity(intent);
                } else if (agentDetails.getData().getAgentInfo().getType().equals("3")) {
                    finish();
                    FinalContents.setInforId(agentDetails.getData().getAgentInfo().getParentId());
                    Log.i("顾问", "从详情页获取的销售ID：" + FinalContents.getInforId());
                    intent = new Intent(Captain_Team_SalesDetailsDetailsActivity.this, Captain_Team_SalesDetailsDetailsActivity.class);
                    startActivity(intent);
                } else if (agentDetails.getData().getAgentInfo().getType().equals("1")) {
                    finish();
                    FinalContents.setInforId(agentDetails.getData().getAgentInfo().getId());
                    Log.i("顾问", "团队长从详情页获取的销售ID：" + FinalContents.getInforId());
                    intent = new Intent(Captain_Team_SalesDetailsDetailsActivity.this, Captain_Team_SalesDetails.class);
                    intent.putExtra("searchName", "销售");
                    startActivity(intent);
                }
                break;
            //      TODO 打电话
            case R.id.sales_details_details_tv11:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("确定拨打：" + sales_details_details_tv2.getText().toString());
                builder.setPositiveButton("呼叫", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent dialIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + sales_details_details_tv2.getText().toString()));//跳转到拨号界面，同时传递电话号码
                        startActivity(dialIntent);
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builder.show();
                break;
            //      TODO 修改
            case R.id.sales_details_details_xiugai:
                if (FinalContents.getIdentity().equals("60")) {
                    finish();
                    FinalContents.setXiuGai("修改销售");
                    FinalContents.setOwnerId("");
                    Log.i("销售啊啊啊", "修改销售：" + FinalContents.getXiuGai());
                    Intent intent = new Intent(Captain_Team_SalesDetailsDetailsActivity.this, Captain_Team_AddSalesActivity.class);
                    startActivity(intent);
                    finish();
                } else if (FinalContents.getIdentity().equals("61")) {
                    finish();
                    FinalContents.setXiuGai("修改顾问");
                    FinalContents.setOwnerId("");
                    Log.i("销售啊啊啊", "修改gw：" + FinalContents.getXiuGai());
                    Intent intent = new Intent(Captain_Team_SalesDetailsDetailsActivity.this, Captain_Team_AddAConsultantActivity.class);
                    startActivity(intent);
                    finish();
                } else if (FinalContents.getIdentity().equals("63")) {
                    /**
                     * 修改
                     */
                    Log.i("团助端修改", "63");
                    if (agentDetails.getData().getAgentInfo().getType().equals("1")) {
                        Log.i("团助端修改", "1");
                        PopWindow1();
//                        FinalContents.setOwnerId("");
//                        intent = new Intent(Captain_Team_SalesDetailsDetailsActivity.this, Assistant_Addteam_Activity.class);
//                        FinalContents.setXiuGai("修改团队长");
//                        startActivity(intent);
                    } else if (agentDetails.getData().getAgentInfo().getType().equals("2")) {
                        Log.i("团助端修改", "2");
                        PopWindow1();
//                        FinalContents.setOwnerId("");
//                        intent = new Intent(Captain_Team_SalesDetailsDetailsActivity.this, Captain_Team_AddSalesActivity.class);
//                        FinalContents.setXiuGai("修改销售");
//                        startActivity(intent);
                    } else if (agentDetails.getData().getAgentInfo().getType().equals("3")) {
                        Log.i("团助端修改", "3");
                        FinalContents.setOwnerId("");
                        intent = new Intent(Captain_Team_SalesDetailsDetailsActivity.this, Captain_Team_AddAConsultantActivity.class);
                        FinalContents.setXiuGai("修改顾问");
                        startActivity(intent);
                        finish();
                    }
                } else {

                }
                break;
        }
    }

    // TODO 数据统计
    private void initDataStatistics() {
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<DataStatisticsBean> clientCommissions = fzbInterface.getDataStatistics(FinalContents.getUserID(), FinalContents.getAgentId(), type, startTime, endTime);
        clientCommissions.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DataStatisticsBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(DataStatisticsBean dataStatisticsBean) {
                        sales_details_details_tv4.setText(dataStatisticsBean.getData().getReportNumber() + "");
                        sales_details_details_tv5.setText(dataStatisticsBean.getData().getAccessingNumber() + "");
                        sales_details_details_tv6.setText(dataStatisticsBean.getData().getIsIslandNumber() + "");
                        sales_details_details_tv7.setText(dataStatisticsBean.getData().getEarnestMoneyNumber() + "");
                        sales_details_details_tv8.setText(dataStatisticsBean.getData().getTradeNumber() + "");
                        sales_details_details_tv9.setText(dataStatisticsBean.getData().getInvalidNum() + "");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("MyCL", "销售详情页数据统计:" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    //TODO 首页时间赋值
    private void initDate() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        String string = String.format(Locale.getDefault(), "%d.%02d.%02d", year, month, dayOfMonth);
        sales_details_details_time1.setText("<" + string);
        sales_details_details_time2.setText("-" + string + " >");
        dateTimePickerView.setStartDate(new GregorianCalendar(year, month - 1, dayOfMonth-15));
        // 注意：月份是从0开始计数的
        dateTimePickerView.setSelectedDate(new GregorianCalendar(year, month - 1, dayOfMonth));
        dateTimePickerView.setEndDate(new GregorianCalendar(year, month - 1, dayOfMonth+15));

        sales_details_details_ensure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sales_details_details_picker.setVisibility(View.GONE);
            }
        });

        sales_details_details_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sales_details_details_picker.setVisibility(View.GONE);
            }
        });

        //            TODO 开始时间
        sales_details_details_time1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sales_details_details_picker.setVisibility(View.VISIBLE);
                dateTimePickerView.setOnSelectedDateChangedListener(new DateTimePickerView.OnSelectedDateChangedListener() {
                    @Override
                    public void onSelectedDateChanged(Calendar date) {
                        int year = date.get(Calendar.YEAR);
                        int month = date.get(Calendar.MONTH);
                        int dayOfMonth = date.get(Calendar.DAY_OF_MONTH);
                        String dateString = String.format(Locale.getDefault(), "%d.%02d.%02d", year, month + 1, dayOfMonth);
                        startTime = dateString;
                        sales_details_details_time1.setText("<" + dateString);
                        NewlyIncreased.setStartDate(dateString);
                    }
                });
            }
        });

        //                TODO 结束时间
        sales_details_details_time2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sales_details_details_picker.setVisibility(View.VISIBLE);
                dateTimePickerView.setOnSelectedDateChangedListener(new DateTimePickerView.OnSelectedDateChangedListener() {
                    @Override
                    public void onSelectedDateChanged(Calendar date) {
                        int year = date.get(Calendar.YEAR);
                        int month = date.get(Calendar.MONTH);
                        int dayOfMonth = date.get(Calendar.DAY_OF_MONTH);
                        String dateString = String.format(Locale.getDefault(), "%d.%02d.%02d", year, month + 1, dayOfMonth);
                        endTime = dateString;
                        sales_details_details_time2.setText("-" + dateString + " >");
                        NewlyIncreased.setEndDate(dateString);
                        initDataStatistics();
                    }
                });

            }
        });
    }

    //TODO 详情页趋势图绘制
    private void init(final List<Integer> list) {

        //显示边界
        sales_details_details_lc.setDrawBorders(false);
        //无数据时显示的文字
        sales_details_details_lc.setNoDataText("暂无数据");
        //折线图不显示数值
//        data.setDrawValues(false);
        //得到X轴
        XAxis xAxis = sales_details_details_lc.getXAxis();
        //设置X轴的位置（默认在上方)
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        //设置X轴坐标之间的最小间隔
        xAxis.setGranularity(0f);
        //设置X轴的刻度数量，第二个参数为true,将会画出明确数量（带有小数点），但是可能值导致不均匀，默认（6，false）
        xAxis.setLabelCount(indexList.size(), false);
        //设置X轴的值（最小值、最大值、然后会根据设置的刻度数量自动分配刻度显示）
        xAxis.setAxisMinimum(0f);
        xAxis.setAxisMaximum((float) list.size());
        //不显示网格线
        xAxis.setDrawGridLines(false);
        // 标签倾斜
        xAxis.setLabelRotationAngle(45);
        //设置X轴值为字符串
        xAxis.setValueFormatter(new IndexAxisValueFormatter(indexList));
        //得到Y轴
        YAxis yAxis = sales_details_details_lc.getAxisLeft();
        YAxis rightYAxis = sales_details_details_lc.getAxisRight();
        //设置Y轴是否显示
        rightYAxis.setEnabled(false); //右侧Y轴不显示
//        yAxis.setEnabled(false);
        //设置y轴坐标之间的最小间隔
        //不显示网格线
        yAxis.setDrawGridLines(false);
//        //设置Y轴坐标之间的最小间隔
        yAxis.setGranularity(3);
        //设置y轴的刻度数量
        //+2：最大值n就有n+1个刻度，在加上y轴多一个单位长度，为了好看，so+2
        yAxis.setLabelCount(5, false);
        //设置从Y轴值
        yAxis.setAxisMinimum(0f);
        //+1:y轴多一个单位长度，为了好看
        yAxis.setAxisMaximum(Collections.max(list) + 1);
        //图例：得到Lengend
        Legend legend = sales_details_details_lc.getLegend();
        //隐藏Lengend
        legend.setEnabled(false);
        //隐藏描述
        Description description = new Description();
        description.setEnabled(false);
        sales_details_details_lc.setDescription(description);
        //图标刷新
        sales_details_details_lc.invalidate();
        sales_details_details_lc.animateXY(2000, 2000);
        setData(list);

        // don't forget to refresh the drawing
        sales_details_details_lc.invalidate();
    }

    //TODO 详情页趋势图数据填充
    private void setData(final List<Integer> list) {

        ArrayList<Entry> values = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            values.add(new Entry(i, list.get(i)));
        }

        LineDataSet set1;

        if (sales_details_details_lc.getData() != null &&
                sales_details_details_lc.getData().getDataSetCount() > 0) {
            set1 = (LineDataSet) sales_details_details_lc.getData().getDataSetByIndex(0);
            set1.setValues(values);
            sales_details_details_lc.getData().notifyDataChanged();
            sales_details_details_lc.notifyDataSetChanged();
        } else {
            // create a dataset and give it a type
            set1 = new LineDataSet(values, "DataSet");

            set1.setMode(LineDataSet.Mode.CUBIC_BEZIER);
            set1.setCubicIntensity(0.2f);
            set1.setDrawFilled(true);
            set1.setDrawCircles(true);
            set1.setLineWidth(1.8f);
            set1.setCircleRadius(3f);
            set1.setValueTextSize(9f);
            set1.setHighlightEnabled(!set1.isHighlightEnabled());
            set1.setCircleColor(Color.parseColor("#FFFFFF"));
            set1.setCircleHoleColor(Color.parseColor("#5484FF"));
            set1.setHighLightColor(Color.BLACK);
            set1.setColor(Color.parseColor("#5484FF"));
//            set1.setFillColor(R.color.mian);
            set1.setFillAlpha(20);
            Drawable drawable = getResources().getDrawable(R.drawable.line_back);
            set1.setFillDrawable(drawable);
            set1.setDrawValues(!set1.isDrawValuesEnabled());
            set1.setDrawHorizontalHighlightIndicator(false);
            set1.setFillFormatter(new IFillFormatter() {
                @Override
                public float getFillLinePosition(ILineDataSet dataSet, LineDataProvider dataProvider) {
                    return sales_details_details_lc.getAxisLeft().getAxisMinimum();
                }
            });


            // create a data object with the data sets
            LineData data = new LineData(set1);
            data.setValueTextSize(9f);
            data.setDrawValues(false);

            // set data
            sales_details_details_lc.setData(data);
            // 设置放大限制
            sales_details_details_lc.getViewPortHandler().setMaximumScaleX(1.0f); // 限制X轴放大限制
            sales_details_details_lc.getViewPortHandler().setMaximumScaleY(1.0f); // 限制Y轴放大限制
        }
        sales_details_details_lc.animateXY(2000, 2000);

    }

    /**
     * 新增
     */
    //TODO 弹窗
    private void PopWindow1() {
        View contentView = LayoutInflater.from(this).inflate(R.layout.captain_team_item_popup, null);
        //处理popWindow 显示内容
        handleListView1(contentView);
        //创建并显示popWindow
        p = new PopupWindow(contentView, WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
        p.setTouchable(true);
        p.setFocusable(true);
        p.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.color)));
        p.getContentView().measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        int xOff;
        int buttonWidth = sales_details_details_xiugai.getWidth();
        int popupwindowWidth = p.getContentView().getMeasuredWidth();
        xOff = buttonWidth - popupwindowWidth;
        p.showAsDropDown(sales_details_details_xiugai, xOff, 0);

        p.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {

            }
        });
    }

    // TODO 填写数据
    private void handleListView1(View contentView) {

        RecyclerView recyclerView = contentView.findViewById(R.id.rv_user);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        final List<String> list = new ArrayList<>();
        list.add("修改信息");
        list.add("修改佣金");

        Captain_Team_PopAdapter popAdapter = new Captain_Team_PopAdapter(list);
        recyclerView.setAdapter(popAdapter);

        popAdapter.setOnItemClickListener(new Captain_Team_PopAdapter.OnItemClickLisenter() {
            @Override
            public void onItemClick(int postion) {
                if (postion == 0) {
                    if (agentDetails.getData().getAgentInfo().getType().equals("1")) {
                        FinalContents.setOwnerId("");
                        intent = new Intent(Captain_Team_SalesDetailsDetailsActivity.this, Assistant_Addteam_Activity.class);
                        FinalContents.setXiuGai("修改团队长");
                        startActivity(intent);
                        finish();
                    } else if (agentDetails.getData().getAgentInfo().getType().equals("2")) {
                        FinalContents.setOwnerId("");
                        intent = new Intent(Captain_Team_SalesDetailsDetailsActivity.this, Captain_Team_AddSalesActivity.class);
                        FinalContents.setXiuGai("修改销售");
                        startActivity(intent);
                        finish();
                    }
                } else if (postion == 1) {
                    if (agentDetails.getData().getAgentInfo().getType().equals("1")) {
                        FinalContents.setOwnerId("");
                        intent = new Intent(Captain_Team_SalesDetailsDetailsActivity.this, Captain_Team_CommissionLevelActivity.class);
                        intent.putExtra("SearchIf", "销售");
                        intent.putExtra("SearchID", agentDetails.getData().getAgentInfo().getId());
                        startActivity(intent);
                        finish();
                    } else if (agentDetails.getData().getAgentInfo().getType().equals("2")) {
                        FinalContents.setOwnerId("");
                        intent = new Intent(Captain_Team_SalesDetailsDetailsActivity.this, Captain_Team_CommissionLevelActivity.class);
                        intent.putExtra("SearchIf", "顾问");
                        intent.putExtra("SearchID", agentDetails.getData().getAgentInfo().getId());
                        startActivity(intent);
                        finish();
                    }
                }
                p.dismiss();
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        NewlyIncreased.setTag("");
        NewlyIncreased.setStartDate("");
        NewlyIncreased.setEndDate("");
    }
}

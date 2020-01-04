package com.xcy.fzbcity.captain_team.view;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.xcy.fzbcity.all.utils.ToastUtil;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.api.FinalContents;
import com.xcy.fzbcity.all.database.CommissionLevelSelectBean;
import com.xcy.fzbcity.all.database.TeamMemberBean;
import com.xcy.fzbcity.all.modle.RatioByOwnerId2Bean;
import com.xcy.fzbcity.all.persente.LevelBean;
import com.xcy.fzbcity.all.persente.StatusBar;
import com.xcy.fzbcity.all.service.MyService;
import com.xcy.fzbcity.all.utils.CommonUtil;
import com.xcy.fzbcity.all.utils.ToastUtil;
import com.xcy.fzbcity.all.view.AllActivity;
import com.xcy.fzbcity.captain_team.adapter.Captain_Team_BatchModifyingAdapter;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//TODO 圈层5-1团队人员-批量修改级别psd
public class Captain_Team_BatchModifyingActivity extends AllActivity {

    RelativeLayout batch_modifying_img;
    RecyclerView batch_modifying_rv;
    Captain_Team_BatchModifyingAdapter adapter;

    Button batch_modifying_btn;
    String IDS = "";
    private OptionsPickerView pvOptions;
    private String type = "";
    private ImageView all_no_information;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.captain_team_activity_batch_modifying);
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

//        if (FinalContents.getIdentity().equals("60")) {
//            type = "2";
//        }else if (FinalContents.getIdentity().equals("61")){
//            type = "3";
//        }

        if (FinalContents.getIdentity().equals("60") || FinalContents.getXiuGai().equals("批量修改销售级别")) {
            type = "2";
        } else if (FinalContents.getIdentity().equals("61") || FinalContents.getXiuGai().equals("批量修改顾问级别")) {
            type = "3";
        } else if (FinalContents.getIdentity().equals("63") || FinalContents.getXiuGai().equals("批量修改团队长级别")) {
            type = "1";
        }

        StatusBar.makeStatusBarTransparent(this);
        all_no_information = findViewById(R.id.all_no_information);
        batch_modifying_img = findViewById(R.id.batch_modifying_img);

        batch_modifying_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        batch_modifying_rv = findViewById(R.id.batch_modifying_rv);

        batch_modifying_btn = findViewById(R.id.batch_modifying_btn);

        batch_modifying_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initDataView();
            }
        });
        initData();
    }

    private void initDataView() {
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);

        if (FinalContents.getXiuGai().equals("批量修改团队长级别")) {

            Observable<RatioByOwnerId2Bean> userMessage = fzbInterface.getRatioByOwnerId2(FinalContents.getUserID());
            userMessage.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<RatioByOwnerId2Bean>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @SuppressLint("WrongConstant")
                        @Override
                        public void onNext(final RatioByOwnerId2Bean ratioByOwnerIdBean) {
                            final List<String> list = new ArrayList<>();
                            for (int i = 0; i < ratioByOwnerIdBean.getData().size(); i++) {
                                list.add(ratioByOwnerIdBean.getData().get(i).getName()+ "(" + ratioByOwnerIdBean.getData().get(i).getPercent() + "%)");
                            }
                            //      监听选中
                            OptionsPickerView pvOptions = new OptionsPickerBuilder(Captain_Team_BatchModifyingActivity.this, new OnOptionsSelectListener() {
                                @Override
                                public void onOptionsSelect(int options1, int option2, int options3, View v) {
                                    //               返回的分别是三个级别的选中位置
                                    //              展示选中数据
                                    batch_modifying_btn.setText(ratioByOwnerIdBean.getData().get(options1).getName() + "(" + ratioByOwnerIdBean.getData().get(options1).getPercent() + "%)");
                                    FinalContents.setRatioId(ratioByOwnerIdBean.getData().get(options1).getId());
                                    initViewData();
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

                        @Override
                        public void onError(Throwable e) {
                            Log.i("列表数据获取错误", "错误" + e);
                        }

                        @Override
                        public void onComplete() {

                        }
                    });

        } else {
            final Observable<CommissionLevelSelectBean> commissionLevelSelece = fzbInterface.getCommissionLevelSelece(FinalContents.getUserID(), FinalContents.getUserID());
            commissionLevelSelece.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<CommissionLevelSelectBean>() {

                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(final CommissionLevelSelectBean commissionLevelSelectBean) {

                            final List<String> list = new ArrayList<>();
                            for (int i = 0; i < commissionLevelSelectBean.getData().size(); i++) {
                                list.add(commissionLevelSelectBean.getData().get(i).getName()+ "(" + commissionLevelSelectBean.getData().get(i).getPercent() + "%)");
                            }
                            //      监听选中
                            //创建
                            pvOptions = new OptionsPickerBuilder(Captain_Team_BatchModifyingActivity.this, new OnOptionsSelectListener() {
                                @Override
                                public void onOptionsSelect(int options1, int option2, int options3, View v) {
                                    //               返回的分别是三个级别的选中位置
                                    //              展示选中数据
                                    batch_modifying_btn.setText(list.get(options1));
                                    FinalContents.setRatioId(commissionLevelSelectBean.getData().get(options1).getId());
                                    Log.i("MyCL", "initViewData()：");
                                    initViewData();
                                }
                            })
                                    .setSelectOptions(0)//设置选择第一个
                                    .setOutSideCancelable(false)//点击背的地方不消失
                                    .build();
                            //      把数据绑定到控件上面
                            pvOptions.setPicker(list);
                            //      展示
                            pvOptions.show();

                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.i("MyCL", "查询佣金级别错误信息：" + e.getMessage());
                        }

                        @Override
                        public void onComplete() {

                        }
                    });
        }
    }

    @SuppressLint("WrongConstant")
    private void initData() {
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<TeamMemberBean> teamMemberBeane = fzbInterface.getTeamMemberBeane("", type, "", FinalContents.getUserID(), FinalContents.getUserID(), "1000");
        teamMemberBeane.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<TeamMemberBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(final TeamMemberBean teamMemberBean) {

                        if (teamMemberBean.getData().getRows().size() != 0) {
                            all_no_information.setVisibility(View.GONE);
                            batch_modifying_rv.setVisibility(View.VISIBLE);
                            LinearLayoutManager manager = new LinearLayoutManager(Captain_Team_BatchModifyingActivity.this);
                            manager.setOrientation(LinearLayoutManager.VERTICAL);
                            batch_modifying_rv.setLayoutManager(manager);
                            adapter = new Captain_Team_BatchModifyingAdapter(teamMemberBean.getData().getRows());
                            batch_modifying_rv.setAdapter(adapter);
                            adapter.notifyDataSetChanged();
                            adapter.setOnItemClickListener(new Captain_Team_BatchModifyingAdapter.OnItemClickLisenter() {
                                @Override
                                public void onItemClick(int postion) {
                                    if (IDS.equals("")) {
                                        IDS = teamMemberBean.getData().getRows().get(postion).getId();
                                    } else {
                                        IDS = IDS + "," + teamMemberBean.getData().getRows().get(postion).getId();
                                    }
                                }
                            });
                        } else {
                            all_no_information.setVisibility(View.VISIBLE);
                            batch_modifying_rv.setVisibility(View.GONE);
                        }


                    }

                    @Override
                    public void onError(Throwable e) {
                        all_no_information.setVisibility(View.VISIBLE);
                        batch_modifying_rv.setVisibility(View.GONE);
                        Log.i("MyCL", "批量修改错误信息：" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    private void initViewData() {
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Log.i("MyCL", "FinalContents.getUserID()：" + FinalContents.getUserID());
        Log.i("MyCL", "FinalContents.getRatioId()：" + FinalContents.getRatioId());
        Log.i("MyCL", "IDS：" + IDS);
        Observable<LevelBean> teamMemberBeane = fzbInterface.getLevelBean(FinalContents.getUserID(), FinalContents.getRatioId(), IDS);
        teamMemberBeane.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LevelBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(final LevelBean levelBean) {
                        ToastUtil.showToast(Captain_Team_BatchModifyingActivity.this, levelBean.getData().getMessage());
                        finish();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("MyCL", "批量修改错误信息：" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}

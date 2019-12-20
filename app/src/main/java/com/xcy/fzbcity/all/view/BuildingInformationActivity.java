package com.xcy.fzbcity.all.view;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.tabs.TabLayout;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.adapter.BuildingInformationAdapter;
import com.xcy.fzbcity.all.api.FinalContents;
import com.xcy.fzbcity.all.modle.BuildingBean;
import com.xcy.fzbcity.all.persente.StatusBar;
import com.xcy.fzbcity.all.service.MyService;
import com.xcy.fzbcity.all.utils.CommonUtil;
import com.xcy.fzbcity.all.utils.ToastUtil;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BuildingInformationActivity extends AllActivity {

    private LinearLayout building_img;
    private TextView name;
    private TextView ladder;
    private TextView tier;
    private TextView cell;
    private TextView open;
    private TextView occupancy;
    private TextView buildingCase;
    private TextView standard;
    private TextView building_standard_S;
    private RecyclerView recyclerView;
    private ImageView pic;
    private TabLayout tabLayout;
    private TabLayout project_details_family_tablayout;
    private BuildingInformationAdapter buildingInformationAdapter;
    private String picUrl;
    private int num = 0;
    private List<BuildingBean.DataBean> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_building_information);
        init_No_Network();
    }

    private void init_No_Network() {
        boolean networkAvailable = CommonUtil.isNetworkAvailable(this);
        if (networkAvailable) {
            picUrl = getIntent().getStringExtra("pic");
            initView();
            initData();
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
        name = findViewById(R.id.building_name);
        building_standard_S = findViewById(R.id.building_standard_S);
        ladder = findViewById(R.id.building_ladder);
        project_details_family_tablayout = findViewById(R.id.project_details_family_tablayout_S);
        tier = findViewById(R.id.building_tier);
        cell = findViewById(R.id.building_cell);
        open = findViewById(R.id.building_open);
        occupancy = findViewById(R.id.building_occupancy);
        buildingCase = findViewById(R.id.building_case);
        standard = findViewById(R.id.building_standard);
        recyclerView = findViewById(R.id.building_house_type);
        pic = findViewById(R.id.building_pic);
        building_img = findViewById(R.id.building_img);
        tabLayout = findViewById(R.id.building_tab_layout);
        Glide.with(this).load(picUrl).into(pic);

        building_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @SuppressLint("ResourceAsColor")
    private void initData() {

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);

        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        final Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<BuildingBean> buildingBean = fzbInterface.getBuildingBean(FinalContents.getUserID(), FinalContents.getProjectID());
        buildingBean.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BuildingBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BuildingBean buildingBean) {
                        list = buildingBean.getData();

                        if (list.size() != 0) {
                            tabLayout.setSelectedTabIndicatorColor(R.color.colorIndicator);
                            tabLayout.setSelectedTabIndicator(R.drawable.tab_indicator);
                            name.setText(list.get(0).getProject().getProjectName());
                            if (list.get(0).getElevator().equals("") || list.get(0).getFamily().equals("")) {
                                ladder.setText("暂无");
                            } else {
                                ladder.setText(list.get(0).getElevator() + "梯" + list.get(0).getFamily() + "户");
                            }
                            if (list.get(0).getElementNumber().equals("")) {
                                cell.setText("暂无");
                            } else {
                                cell.setText(list.get(0).getElementNumber() + "");
                            }

                            if (list.get(0).getPliesNumber().equals("")) {
                                tier.setText("暂无");
                            } else {
                                tier.setText(list.get(0).getPliesNumber() + "层");
                            }

                            if (list.get(0).getBuildTime().equals("")) {
                                open.setText("暂无");
                            } else {
                                open.setText(list.get(0).getBuildTime());
                            }

                            if (list.get(0).getCheckInTime().equals("")) {
                                occupancy.setText("暂无");
                            } else {
                                occupancy.setText(list.get(0).getCheckInTime());
                            }

                            if (list.get(0).getFitmentState().equals("")) {
                                buildingCase.setText("暂无");
                            } else {
                                buildingCase.setText(list.get(0).getFitmentState());
                            }

                            if (list.get(0).getFitmentStandardStr().equals("")) {
                                standard.setText("暂无");
                            } else {
                                standard.setText(list.get(0).getFitmentStandardStr());
                            }


                            if (list.get(0).getHouseInfoList().size() == 0) {

                            } else {
                                if (list.get(0).getHouseInfoList().get(0).getValue() != null) {
                                    if (list.get(0).getHouseInfoList().get(0).getValue().size() != 0) {
                                        buildingInformationAdapter = new BuildingInformationAdapter(list.get(0).getHouseInfoList().get(0).getValue());
                                        buildingInformationAdapter.setImageUrl(list.get(0).getHouseInfoList().get(0).getValue().get(0).getFloorPlan());

                                        recyclerView.setAdapter(buildingInformationAdapter);
                                        buildingInformationAdapter.notifyDataSetChanged();
                                        for (int i = 0; i < list.size(); i++) {
                                            if (i == 0) {
                                                tabLayout.addTab(tabLayout.newTab().setText(list.get(i).getBuildingName()), true);
                                            } else {
                                                tabLayout.addTab(tabLayout.newTab().setText(list.get(i).getBuildingName()));
                                            }
                                        }
                                        project_details_family_tablayout.removeAllTabs();
                                        for (int s = 0; s < list.get(0).getHouseInfoList().size(); ++s) {
                                            project_details_family_tablayout.addTab(project_details_family_tablayout.newTab().setText(list.get(0).getHouseInfoList().get(s).getKey()));
                                            num = num + list.get(0).getHouseInfoList().get(s).getSize();
                                        }
                                        building_standard_S.setText("包含户型：(" + num + ")");
                                    }

                                    final int index = 0;
                                    project_details_family_tablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                                        @Override
                                        public void onTabSelected(TabLayout.Tab tab) {

                                            for (int i = 0; i < list.get(index).getHouseInfoList().size(); i++) {
                                                if (list.get(index).getHouseInfoList().get(i).getKey().equals(tab.getText().toString())) {
                                                    LinearLayoutManager layoutManager_family = new LinearLayoutManager(BuildingInformationActivity.this);
                                                    layoutManager_family.setOrientation(LinearLayoutManager.HORIZONTAL);
                                                    recyclerView.setLayoutManager(layoutManager_family);
                                                    buildingInformationAdapter = new BuildingInformationAdapter(list.get(index).getHouseInfoList().get(i).getValue());
                                                    Log.i("户型信息", "户型信息走向1");
                                                    recyclerView.setAdapter(buildingInformationAdapter);
                                                    buildingInformationAdapter.notifyDataSetChanged();
                                                    Log.i("户型信息", "户型信息走向1");
                                                    return;
                                                }
                                            }
                                        }

                                        @Override
                                        public void onTabUnselected(TabLayout.Tab tab) {
                                            //                添加未选中Tab的逻辑
                                        }

                                        @Override
                                        public void onTabReselected(TabLayout.Tab tab) {
                                            for (int i = 0; i < list.get(index).getHouseInfoList().size(); i++) {
                                                if (list.get(index).getHouseInfoList().get(i).getKey().equals(tab.getText().toString())) {
                                                    LinearLayoutManager layoutManager_family = new LinearLayoutManager(BuildingInformationActivity.this);
                                                    layoutManager_family.setOrientation(LinearLayoutManager.HORIZONTAL);
                                                    recyclerView.setLayoutManager(layoutManager_family);
                                                    buildingInformationAdapter = new BuildingInformationAdapter(list.get(index).getHouseInfoList().get(i).getValue());
                                                    Log.i("户型信息", "户型信息走向1");
                                                    recyclerView.setAdapter(buildingInformationAdapter);
                                                    buildingInformationAdapter.notifyDataSetChanged();
                                                    Log.i("户型信息", "户型信息走向1");
                                                    return;
                                                }
                                            }
                                        }
                                    });

                                    tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                                        @Override
                                        public void onTabSelected(TabLayout.Tab tab) {
                                            num = 0;
//                                    project_details_family_tablayout
                                            //                添加选中Tab的逻辑
                                            for (int j = 0; j < list.size(); j++) {
                                                if (tab.getText().toString().equals(list.get(j).getBuildingName())) {
                                                    name.setText(list.get(j).getProject().getProjectName());
                                                    if (list.get(j).getElevator().equals("") || list.get(0).getFamily().equals("")) {
                                                        ladder.setText("暂无");
                                                    } else {
                                                        ladder.setText(list.get(j).getElevator() + "梯" + list.get(j).getFamily() + "户");
                                                    }
                                                    if (list.get(j).getElementNumber().equals("")) {
                                                        cell.setText("暂无");
                                                    } else {
                                                        cell.setText(list.get(j).getElementNumber() + "");
                                                    }

                                                    if (list.get(j).getPliesNumber().equals("")) {
                                                        tier.setText("暂无");
                                                    } else {
                                                        tier.setText(list.get(j).getPliesNumber() + "层");
                                                    }

                                                    if (list.get(j).getBuildTime().equals("")) {
                                                        open.setText("暂无");
                                                    } else {
                                                        open.setText(list.get(j).getBuildTime());
                                                    }

                                                    if (list.get(j).getCheckInTime().equals("")) {
                                                        occupancy.setText("暂无");
                                                    } else {
                                                        occupancy.setText(list.get(j).getCheckInTime());
                                                    }

                                                    if (list.get(j).getFitmentState().equals("")) {
                                                        buildingCase.setText("暂无");
                                                    } else {
                                                        buildingCase.setText(list.get(j).getFitmentState());
                                                    }

                                                    if (list.get(j).getFitmentStandardStr().equals("")) {
                                                        standard.setText("暂无");
                                                    } else {
                                                        standard.setText(list.get(j).getFitmentStandardStr());
                                                    }

                                                    project_details_family_tablayout.removeAllTabs();
                                                    for (int s = 0; s < list.get(j).getHouseInfoList().size(); ++s) {
                                                        project_details_family_tablayout.addTab(project_details_family_tablayout.newTab().setText(list.get(j).getHouseInfoList().get(s).getKey()));
                                                        num = num + list.get(j).getHouseInfoList().get(s).getSize();
                                                    }
                                                    building_standard_S.setText("包含户型：(" + num + ")");

                                                    final int index = j;
                                                    project_details_family_tablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                                                        @Override
                                                        public void onTabSelected(TabLayout.Tab tab) {
                                                            for (int i = 0; i < list.get(index).getHouseInfoList().size(); i++) {
                                                                if (list.get(index).getHouseInfoList().get(i).getKey().equals(tab.getText().toString())) {
                                                                    LinearLayoutManager layoutManager_family = new LinearLayoutManager(BuildingInformationActivity.this);
                                                                    layoutManager_family.setOrientation(LinearLayoutManager.HORIZONTAL);
                                                                    recyclerView.setLayoutManager(layoutManager_family);
                                                                    buildingInformationAdapter = new BuildingInformationAdapter(list.get(index).getHouseInfoList().get(i).getValue());
                                                                    Log.i("户型信息", "户型信息走向1");
                                                                    recyclerView.setAdapter(buildingInformationAdapter);
                                                                    buildingInformationAdapter.notifyDataSetChanged();
                                                                    Log.i("户型信息", "户型信息走向1");
                                                                    return;
                                                                }
                                                            }
                                                        }

                                                        @Override
                                                        public void onTabUnselected(TabLayout.Tab tab) {
                                                            //                添加未选中Tab的逻辑
                                                        }

                                                        @Override
                                                        public void onTabReselected(TabLayout.Tab tab) {
                                                            for (int i = 0; i < list.get(index).getHouseInfoList().size(); i++) {
                                                                if (list.get(index).getHouseInfoList().get(i).getKey().equals(tab.getText().toString())) {
                                                                    LinearLayoutManager layoutManager_family = new LinearLayoutManager(BuildingInformationActivity.this);
                                                                    layoutManager_family.setOrientation(LinearLayoutManager.HORIZONTAL);
                                                                    recyclerView.setLayoutManager(layoutManager_family);
                                                                    buildingInformationAdapter = new BuildingInformationAdapter(list.get(index).getHouseInfoList().get(i).getValue());
                                                                    Log.i("户型信息", "户型信息走向1");
                                                                    recyclerView.setAdapter(buildingInformationAdapter);
                                                                    buildingInformationAdapter.notifyDataSetChanged();
                                                                    Log.i("户型信息", "户型信息走向1");
                                                                    return;
                                                                }
                                                            }
                                                        }
                                                    });
                                                }
                                            }
                                        }

                                        @Override
                                        public void onTabUnselected(TabLayout.Tab tab) {
                                            //                添加未选中Tab的逻辑
                                        }

                                        @Override
                                        public void onTabReselected(TabLayout.Tab tab) {
                                            num = 0;
                                            //                添加选中Tab的逻辑
                                            for (int j = 0; j < list.size(); j++) {
                                                if (tab.getText().toString().equals(list.get(j).getBuildingName())) {
                                                    name.setText(list.get(j).getProject().getProjectName());
                                                    if (list.get(j).getElevator().equals("") || list.get(0).getFamily().equals("")) {
                                                        ladder.setText("暂无");
                                                    } else {
                                                        ladder.setText(list.get(j).getElevator() + "梯" + list.get(j).getFamily() + "户");
                                                    }
                                                    if (list.get(j).getElementNumber().equals("")) {
                                                        cell.setText("暂无");
                                                    } else {
                                                        cell.setText(list.get(j).getElementNumber() + "");
                                                    }

                                                    if (list.get(j).getPliesNumber().equals("")) {
                                                        tier.setText("暂无");
                                                    } else {
                                                        tier.setText(list.get(j).getPliesNumber() + "层");
                                                    }

                                                    if (list.get(j).getBuildTime().equals("")) {
                                                        open.setText("暂无");
                                                    } else {
                                                        open.setText(list.get(j).getBuildTime());
                                                    }

                                                    if (list.get(j).getCheckInTime().equals("")) {
                                                        occupancy.setText("暂无");
                                                    } else {
                                                        occupancy.setText(list.get(j).getCheckInTime());
                                                    }

                                                    if (list.get(j).getFitmentState().equals("")) {
                                                        buildingCase.setText("暂无");
                                                    } else {
                                                        buildingCase.setText(list.get(j).getFitmentState());
                                                    }

                                                    if (list.get(j).getFitmentStandardStr().equals("")) {
                                                        standard.setText("暂无");
                                                    } else {
                                                        standard.setText(list.get(j).getFitmentStandardStr());
                                                    }

                                                    project_details_family_tablayout.removeAllTabs();
                                                    for (int s = 0; s < list.get(j).getHouseInfoList().size(); ++s) {
                                                        project_details_family_tablayout.addTab(project_details_family_tablayout.newTab().setText(list.get(j).getHouseInfoList().get(s).getKey()));
                                                        num = num + list.get(j).getHouseInfoList().get(s).getSize();
                                                    }
                                                    building_standard_S.setText("包含户型：(" + num + ")");

                                                    final int index = j;
                                                    project_details_family_tablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                                                        @Override
                                                        public void onTabSelected(TabLayout.Tab tab) {
                                                            for (int i = 0; i < list.get(index).getHouseInfoList().size(); i++) {
                                                                if (list.get(index).getHouseInfoList().get(i).getKey().equals(tab.getText().toString())) {
                                                                    LinearLayoutManager layoutManager_family = new LinearLayoutManager(BuildingInformationActivity.this);
                                                                    layoutManager_family.setOrientation(LinearLayoutManager.HORIZONTAL);
                                                                    recyclerView.setLayoutManager(layoutManager_family);
                                                                    buildingInformationAdapter = new BuildingInformationAdapter(list.get(index).getHouseInfoList().get(i).getValue());
                                                                    Log.i("户型信息", "户型信息走向1");
                                                                    recyclerView.setAdapter(buildingInformationAdapter);
                                                                    buildingInformationAdapter.notifyDataSetChanged();
                                                                    Log.i("户型信息", "户型信息走向1");
                                                                    return;
                                                                }
                                                            }
                                                        }

                                                        @Override
                                                        public void onTabUnselected(TabLayout.Tab tab) {
                                                            //                添加未选中Tab的逻辑
                                                        }

                                                        @Override
                                                        public void onTabReselected(TabLayout.Tab tab) {
                                                            for (int i = 0; i < list.get(index).getHouseInfoList().size(); i++) {
                                                                if (list.get(index).getHouseInfoList().get(i).getKey().equals(tab.getText().toString())) {
                                                                    LinearLayoutManager layoutManager_family = new LinearLayoutManager(BuildingInformationActivity.this);
                                                                    layoutManager_family.setOrientation(LinearLayoutManager.HORIZONTAL);
                                                                    recyclerView.setLayoutManager(layoutManager_family);
                                                                    buildingInformationAdapter = new BuildingInformationAdapter(list.get(index).getHouseInfoList().get(i).getValue());
                                                                    Log.i("户型信息", "户型信息走向1");
                                                                    recyclerView.setAdapter(buildingInformationAdapter);
                                                                    buildingInformationAdapter.notifyDataSetChanged();
                                                                    Log.i("户型信息", "户型信息走向1");
                                                                    return;
                                                                }
                                                            }
                                                        }
                                                    });
                                                }
                                            }
                                        }
                                    });
                                }
                            }

                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("MyCL", "BuildingInformationActivity错误信息：" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
}

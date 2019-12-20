package com.xcy.fzbcity.captain_team.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.api.FinalContents;
import com.xcy.fzbcity.all.database.TeamMemberBean;
import com.xcy.fzbcity.all.persente.StatusBar;
import com.xcy.fzbcity.all.service.MyService;
import com.xcy.fzbcity.all.utils.CommonUtil;
import com.xcy.fzbcity.all.utils.ToastUtil;
import com.xcy.fzbcity.all.view.AllActivity;
import com.xcy.fzbcity.captain_team.adapter.Captain_Team_SalesDetailsAdapter;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//TODO 圈层5-销售详情-他的顾问
public class Captain_Team_SalesDetails extends AllActivity {

    RelativeLayout sales_details_img;
    RecyclerView sales_details_rv;
    Captain_Team_SalesDetailsAdapter adapter;
    TextView sales_tv;
    private String searchName = "";
    private ImageView all_sales_details_no_information;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.captain_team_activity_sales_details);
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
            ToastUtil.showToast(this, "当前无网络，请检查网络后再进行登录");
        }
    }

    private void initView() {

        StatusBar.makeStatusBarTransparent(this);

        sales_details_img = findViewById(R.id.sales_details_img);
        sales_details_rv = findViewById(R.id.sales_details_rv);
        sales_tv = findViewById(R.id.sales_tv);
        all_sales_details_no_information = findViewById(R.id.all_sales_details_no_information);

        sales_details_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        searchName = getIntent().getStringExtra("searchName");
        if(searchName.equals("顾问")){
            sales_tv.setText("选择顾问");
            initData();
        }else if(searchName.equals("销售")){
            sales_tv.setText("选择销售");
            initDataS();
        }

    }

    private void initDataS() {

        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<TeamMemberBean> teamMemberBeane = fzbInterface.getTeamMemberBeane("", "2", "", FinalContents.getInforId(), FinalContents.getInforId(),"1000");
        teamMemberBeane.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<TeamMemberBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(final TeamMemberBean teamMemberBean) {
                        Log.i("顾问","从团队长详情页："+FinalContents.getInforId());
                        if (teamMemberBean.getData().getRows().size() != 0) {
                            all_sales_details_no_information.setVisibility(View.GONE);
                            sales_details_rv.setVisibility(View.VISIBLE);
                            LinearLayoutManager manager = new LinearLayoutManager(Captain_Team_SalesDetails.this);
                            manager.setOrientation(LinearLayoutManager.VERTICAL);
                            sales_details_rv.setLayoutManager(manager);
                            adapter = new Captain_Team_SalesDetailsAdapter(teamMemberBean.getData().getRows());
                            sales_details_rv.setAdapter(adapter);
                            adapter.setIdenty(searchName);
                            adapter.notifyDataSetChanged();

                            adapter.setOnItemClickListener(new Captain_Team_SalesDetailsAdapter.OnItemClickLisenter() {
                                @Override
                                public void onItemClick(int postion) {
                                    finish();
                                    FinalContents.setInforId(teamMemberBean.getData().getRows().get(postion).getId());
                                    Log.i("顾问","从团队长详情页获取的ID："+FinalContents.getInforId());
                                    Intent intent = new Intent( Captain_Team_SalesDetails.this, Captain_Team_SalesDetailsDetailsActivity.class);
                                    startActivity(intent);
                                }
                            });
                        }else {
                            all_sales_details_no_information.setVisibility(View.VISIBLE);
                            sales_details_rv.setVisibility(View.GONE);
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        all_sales_details_no_information.setVisibility(View.VISIBLE);
                        sales_details_rv.setVisibility(View.GONE);
                        Log.i("MyCL", "TeamMemberActivity错误信息：" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    @SuppressLint("WrongConstant")
    private void initData() {
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<TeamMemberBean> teamMemberBeane = fzbInterface.getTeamMemberBeane("", "3", "", FinalContents.getInforId(), FinalContents.getInforId(),"1000");
        teamMemberBeane.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<TeamMemberBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(final TeamMemberBean teamMemberBean) {
                        if (teamMemberBean.getData().getRows().size() != 0) {
                            all_sales_details_no_information.setVisibility(View.GONE);
                            sales_details_rv.setVisibility(View.VISIBLE);
                            LinearLayoutManager manager = new LinearLayoutManager(Captain_Team_SalesDetails.this);
                            manager.setOrientation(LinearLayoutManager.VERTICAL);
                            sales_details_rv.setLayoutManager(manager);
                            adapter = new Captain_Team_SalesDetailsAdapter(teamMemberBean.getData().getRows());
                            sales_details_rv.setAdapter(adapter);
                            adapter.setIdenty(searchName);
                            adapter.notifyDataSetChanged();

                            adapter.setOnItemClickListener(new Captain_Team_SalesDetailsAdapter.OnItemClickLisenter() {
                                @Override
                                public void onItemClick(int postion) {
                                    finish();
                                    FinalContents.setInforId(teamMemberBean.getData().getRows().get(postion).getId());
                                    Log.i("顾问", "从顾问详情页获取的ID：" + FinalContents.getInforId());
                                    Intent intent = new Intent(Captain_Team_SalesDetails.this, Captain_Team_SalesDetailsDetailsActivity.class);
                                    startActivity(intent);
                                }
                            });
                        }else {
                            all_sales_details_no_information.setVisibility(View.VISIBLE);
                            sales_details_rv.setVisibility(View.GONE);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        all_sales_details_no_information.setVisibility(View.VISIBLE);
                        sales_details_rv.setVisibility(View.GONE);
                        Log.i("MyCL", "TeamMemberActivity错误信息：" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}

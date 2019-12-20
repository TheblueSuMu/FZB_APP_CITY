package com.xcy.fzbcity.shopping_guide.view;

import android.annotation.SuppressLint;
import android.content.Intent;
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
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.api.FinalContents;
import com.xcy.fzbcity.all.api.ProjectProgressApi;
import com.xcy.fzbcity.all.persente.SingleClick;
import com.xcy.fzbcity.all.persente.StatusBar;
import com.xcy.fzbcity.all.service.MyService;
import com.xcy.fzbcity.all.utils.CommonUtil;
import com.xcy.fzbcity.all.utils.ToastUtil;
import com.xcy.fzbcity.all.view.AllActivity;
import com.xcy.fzbcity.all.view.ToApplyForAnIslandActivity;
import com.xcy.fzbcity.project_side.view.ConfessToRaiseInformationActivity;
import com.xcy.fzbcity.project_side.view.FillInTransactionInformationActivity;
import com.xcy.fzbcity.shopping_guide.MyClientFragmentBean;
import com.xcy.fzbcity.shopping_guide.adapter.ReviewTheSuccessAdapter;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//TODO 报备—审核成功
public class ReviewTheSuccessActivity extends AllActivity implements View.OnClickListener {

    RelativeLayout review_the_success_return;
    ImageView review_the_success_img1;

    TextView review_the_success_tv1;
    TextView review_the_success_tv2;
    TextView review_the_success_tv3;

    RecyclerView review_the_success_rv;

    TextView review_the_success_bt1;
    TextView review_the_success_bt3;
    TextView review_the_success_bt4;
    TextView review_the_success_bt5;


    private Intent intent;

    ReviewTheSuccessAdapter adapter;

    private MyClientFragmentBean.DataBean.InfoDataBean infoData;
    private List<MyClientFragmentBean.DataBean.ListDataBean> processData;

    int isnum1 = 0;
    int isnum2 = 0;
    int isnum3 = 0;
    int isnum4 = 0;
    private LinearLayout success_layout1;
    private LinearLayout success_layout3;
    private LinearLayout success_layout4;
    private LinearLayout success_layout5;
    private Button review_the_success_b1;
    private Button review_the_success_b3;
    private Button review_the_success_b4;
    private Button review_the_success_b5;
    private LinearLayout linearlayout_l;
    private LinearLayout linearlayout_ll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shopping_guide_activity_review_the_success);
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

        review_the_success_return = findViewById(R.id.review_the_success_return);
        review_the_success_img1 = findViewById(R.id.review_the_success_img1);
        review_the_success_tv1 = findViewById(R.id.review_the_success_tv1);
        review_the_success_tv2 = findViewById(R.id.review_the_success_tv2);
        review_the_success_tv3 = findViewById(R.id.review_the_success_tv3);
        review_the_success_rv = findViewById(R.id.review_the_success_rv);
        review_the_success_bt1 = findViewById(R.id.review_the_success_bt1);
        review_the_success_bt3 = findViewById(R.id.review_the_success_bt3);
        review_the_success_bt4 = findViewById(R.id.review_the_success_bt4);
        review_the_success_bt5 = findViewById(R.id.review_the_success_bt5);

        review_the_success_b1 = findViewById(R.id.review_the_success_b1);
        review_the_success_b3 = findViewById(R.id.review_the_success_b3);
        review_the_success_b4 = findViewById(R.id.review_the_success_b4);
        review_the_success_b5 = findViewById(R.id.review_the_success_b5);

        success_layout1 = findViewById(R.id.success_layout1);
        success_layout3 = findViewById(R.id.success_layout3);
        success_layout4 = findViewById(R.id.success_layout4);
        success_layout5 = findViewById(R.id.success_layout5);

        linearlayout_l = findViewById(R.id.linearlayout_l);
        linearlayout_ll = findViewById(R.id.linearlayout_ll);


        review_the_success_bt1.setOnClickListener(this);
        review_the_success_bt3.setOnClickListener(this);
        review_the_success_bt4.setOnClickListener(this);
        review_the_success_bt5.setOnClickListener(this);

        review_the_success_b1.setOnClickListener(this);
        review_the_success_b3.setOnClickListener(this);
        review_the_success_b4.setOnClickListener(this);
        review_the_success_b5.setOnClickListener(this);

        review_the_success_bt5.setVisibility(View.GONE);
        review_the_success_bt4.setVisibility(View.GONE);
        review_the_success_bt1.setVisibility(View.GONE);
        review_the_success_bt3.setVisibility(View.GONE);

    }

    private void initData() {

        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<MyClientFragmentBean> clientFragmentBean = fzbInterface.getClientFragmentBeanDao(FinalContents.getUserID(), FinalContents.getCustomerID(), FinalContents.getLandingId(), FinalContents.getPreparationId());
        clientFragmentBean.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MyClientFragmentBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(MyClientFragmentBean myClientFragmentBean) {

                        if (myClientFragmentBean.getData().getMenuData().size() > 1) {
                            linearlayout_ll.setVisibility(View.GONE);
                            linearlayout_l.setVisibility(View.VISIBLE);
                            for (int i = 0; i < myClientFragmentBean.getData().getMenuData().size(); i++) {
                                if (myClientFragmentBean.getData().getMenuData().get(i).getMeunkey().equals("60")) {
                                    review_the_success_bt5.setVisibility(View.VISIBLE);//申请成交
                                    success_layout5.setVisibility(View.VISIBLE);
                                } else if (myClientFragmentBean.getData().getMenuData().get(i).getMeunkey().equals("50")) {
                                    review_the_success_bt4.setVisibility(View.VISIBLE);//申请认筹
                                    success_layout4.setVisibility(View.VISIBLE);
                                } else if (myClientFragmentBean.getData().getMenuData().get(i).getMeunkey().equals("400")) {
                                    review_the_success_bt1.setVisibility(View.VISIBLE);//补全信息
                                    success_layout1.setVisibility(View.VISIBLE);
                                } else if (myClientFragmentBean.getData().getMenuData().get(i).getMeunkey().equals("402")) {
                                    review_the_success_bt3.setVisibility(View.VISIBLE);//未成交
                                    success_layout3.setVisibility(View.VISIBLE);
                                }
                            }
                        } else if (myClientFragmentBean.getData().getMenuData().size() == 1) {
                            linearlayout_ll.setVisibility(View.VISIBLE);
                            linearlayout_l.setVisibility(View.GONE);
                            for (int i = 0; i < myClientFragmentBean.getData().getMenuData().size(); i++) {
                                if (myClientFragmentBean.getData().getMenuData().get(i).getMeunkey().equals("60")) {
                                    review_the_success_b5.setVisibility(View.VISIBLE);//申请成交
                                } else if (myClientFragmentBean.getData().getMenuData().get(i).getMeunkey().equals("50")) {
                                    review_the_success_b4.setVisibility(View.VISIBLE);//申请认筹
                                } else if (myClientFragmentBean.getData().getMenuData().get(i).getMeunkey().equals("400")) {
                                    review_the_success_b1.setVisibility(View.VISIBLE);//补全信息
                                } else if (myClientFragmentBean.getData().getMenuData().get(i).getMeunkey().equals("402")) {
                                    review_the_success_b3.setVisibility(View.VISIBLE);//未成交
                                }
                            }
                        }



                        infoData = myClientFragmentBean.getData().getInfoData();
                        if (infoData.getCustomerImg() != null) {
                            if (!infoData.getCustomerImg().equals("")) {
                                Glide.with(ReviewTheSuccessActivity.this).load(FinalContents.getImageUrl() + infoData.getCustomerImg()).into(review_the_success_img1);
                            }
                        }
                        review_the_success_tv1.setText(infoData.getCustomerName());
                        processData = myClientFragmentBean.getData().getListData();
                        FinalContents.setJJrID(FinalContents.getUserID());
                        FinalContents.setProjectID(infoData.getProjectId());
                        review_the_success_tv2.setText(infoData.getProjectName());
                        review_the_success_tv3.setText(infoData.getCustomerName() + "[" + infoData.getCustomerPhone() + "]");
                        review_the_success_return.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                finish();
                            }
                        });
                        FinalContents.setProjectType(infoData.getProjectType());
                        initRV();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("MyCL", "错误信息：" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @SuppressLint("WrongConstant")
    private void initRV() {

        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        review_the_success_rv.setLayoutManager(manager);
        adapter = new ReviewTheSuccessAdapter(processData);

        review_the_success_rv.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @SingleClick(1000)
    @Override
    public void onClick(View view) {


        switch (view.getId()) {
            //            TODO 补全信息
            case R.id.review_the_success_bt1:
                if (isnum1 == 0) {
                    isnum1 = 1;
                    finish();
                    ProjectProgressApi.setComplemented("1");
                    intent = new Intent(ReviewTheSuccessActivity.this, ToApplyForAnIslandActivity.class);
                    intent.putExtra("name", infoData.getCustomerName());
                    startActivity(intent);
                    isnum1 = 0;
                }

                break;
            //            TODO 未成交
            case R.id.review_the_success_bt3:
                if (isnum2 == 0) {
                    isnum2 = 1;
                    finish();
                    intent = new Intent(ReviewTheSuccessActivity.this, WCJActivity.class);
                    startActivity(intent);
                    isnum2 = 0;
                }

                break;
            //            TODO 申请认筹
            case R.id.review_the_success_bt4:
                if (isnum3 == 0) {
                    isnum3 = 1;
                    finish();
                    intent = new Intent(ReviewTheSuccessActivity.this, ConfessToRaiseInformationActivity.class);
                    ProjectProgressApi.setCustomerName(infoData.getCustomerName());
                    ProjectProgressApi.setProjectName(infoData.getProjectName());
                    ProjectProgressApi.setCustomerPhone(infoData.getCustomerPhone());
                    startActivity(intent);
                    isnum3 = 0;
                }

                break;
            //            TODO 申请成交
            case R.id.review_the_success_bt5:
                if (isnum4 == 0) {
                    isnum4 = 1;
                    finish();
                    intent = new Intent(ReviewTheSuccessActivity.this, FillInTransactionInformationActivity.class);
                    FinalContents.setPreparationId(infoData.getPreparationId());
                    FinalContents.setTiaodan("成交");
                    startActivity(intent);
                    isnum4 = 0;
                }

                break;
            //            TODO 补全信息
            case R.id.review_the_success_b1:
                if (isnum1 == 0) {
                    isnum1 = 1;
                    finish();
                    ProjectProgressApi.setComplemented("1");
                    intent = new Intent(ReviewTheSuccessActivity.this, ToApplyForAnIslandActivity.class);
                    intent.putExtra("name", infoData.getCustomerName());
                    startActivity(intent);
                    isnum1 = 0;
                }

                break;
            //            TODO 未成交
            case R.id.review_the_success_b3:
                if (isnum2 == 0) {
                    isnum2 = 1;
                    finish();
                    intent = new Intent(ReviewTheSuccessActivity.this, WCJActivity.class);
                    startActivity(intent);
                    isnum2 = 0;
                }

                break;
            //            TODO 申请认筹
            case R.id.review_the_success_b4:
                if (isnum3 == 0) {
                    isnum3 = 1;
                    finish();
                    intent = new Intent(ReviewTheSuccessActivity.this, ConfessToRaiseInformationActivity.class);
                    ProjectProgressApi.setCustomerName(infoData.getCustomerName());
                    ProjectProgressApi.setProjectName(infoData.getProjectName());
                    ProjectProgressApi.setCustomerPhone(infoData.getCustomerPhone());
                    startActivity(intent);
                    isnum3 = 0;
                }

                break;
            //            TODO 申请成交
            case R.id.review_the_success_b5:
                if (isnum4 == 0) {
                    isnum4 = 1;
                    finish();
                    intent = new Intent(ReviewTheSuccessActivity.this, FillInTransactionInformationActivity.class);
                    FinalContents.setPreparationId(infoData.getPreparationId());
                    FinalContents.setTiaodan("成交");
                    startActivity(intent);
                    isnum4 = 0;
                }

                break;
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        initData();
    }


}

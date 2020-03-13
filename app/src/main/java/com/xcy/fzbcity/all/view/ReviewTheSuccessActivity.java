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
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.adapter.ReviewTheSuccessAdapter;
import com.xcy.fzbcity.all.adapter.ReviewTheSuccessPhoneAdapter;
import com.xcy.fzbcity.all.api.CityContents;
import com.xcy.fzbcity.all.api.FinalContents;
import com.xcy.fzbcity.all.api.ProjectProgressApi;
import com.xcy.fzbcity.all.database.FieldBean;
import com.xcy.fzbcity.all.modle.CBean;
import com.xcy.fzbcity.all.modle.CheckBean;
import com.xcy.fzbcity.all.modle.FailureAlertBean;
import com.xcy.fzbcity.all.modle.ReadRecordBean;
import com.xcy.fzbcity.all.modle.ReportProcessDetailsBean;
import com.xcy.fzbcity.all.persente.MyLinearLayoutManager;
import com.xcy.fzbcity.all.persente.OkHttpPost;
import com.xcy.fzbcity.all.persente.SingleClick;
import com.xcy.fzbcity.all.persente.StatusBar;
import com.xcy.fzbcity.all.service.MyService;
import com.xcy.fzbcity.all.utils.CommonUtil;
import com.xcy.fzbcity.all.utils.ToastUtil;
import com.xcy.fzbcity.project_side.view.BackToRaiseThatActivity;
import com.xcy.fzbcity.project_side.view.ConfessToRaiseInformationActivity;
import com.xcy.fzbcity.project_side.view.FillInTransactionInformationActivity;
import com.xcy.fzbcity.project_side.view.ListOfBackActivity;
import com.xcy.fzbcity.project_side.view.ModifyTheRecognitionToRaiseActivity;
import com.xcy.fzbcity.project_side.view.TheReasonForRefusalActivity;

import java.util.ArrayList;
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
    ImageView review_the_success_img3;

    TextView review_the_success_tv1;
    TextView review_the_success_tv2;
    TextView review_the_success_tv3;

    RecyclerView review_the_success_rv;

    TextView review_the_success_bt1;
    TextView review_the_success_bt2;
    TextView review_the_success_bt3;
    TextView review_the_success_bt4;
    TextView review_the_success_bt5;
    TextView review_the_success_bt6;
    TextView review_the_success_bt7;
    TextView review_the_success_bt8;
    TextView review_the_success_bt9;
    TextView review_the_success_bt10;
    TextView review_the_success_bt11;
    TextView review_the_success_bt12;
    TextView review_the_success_bt13;

    Button review_the_success_b1;
    Button review_the_success_b2;
    Button review_the_success_b3;
    Button review_the_success_b4;
    Button review_the_success_b5;

    Button review_the_success_b6;
    Button review_the_success_b7;
    Button review_the_success_b8;
    Button review_the_success_b9;
    Button review_the_success_b10;
    Button review_the_success_b11;
    Button review_the_success_b12;
    Button review_the_success_b13;

    private Intent intent;
    private String url;
    ReviewTheSuccessAdapter adapter;

    private ReportProcessDetailsBean.DataBean.InfoDataBean infoData;
    private List<ReportProcessDetailsBean.DataBean.ProcessDataBean> processData;
    private String name = "";
    private RecyclerView review_the_success_nameRv;
    private ImageView all_no_information;
    private RelativeLayout lien;
    private LinearLayout success_linear1;
    private LinearLayout success_linear2;
    private LinearLayout success_linear3;
    private LinearLayout success_linear4;
    private LinearLayout success_linear5;
    private LinearLayout success_linear6;
    private LinearLayout success_linear7;
    private LinearLayout success_linear8;
    private LinearLayout success_linear9;
    private LinearLayout success_linear10;
    private LinearLayout success_linear11;
    private LinearLayout success_linear12;
    private LinearLayout success_linear13;
    private LinearLayout linearlayout_ll;
    private LinearLayout linearlayout_l;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_the_success);
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

        initReadRecord();

        FieldBean fieldBean = new FieldBean();
        ProjectProgressApi.setFieldBean(fieldBean);
        List<FieldBean> list = new ArrayList<>();
        ProjectProgressApi.setFieldBeanList(list);

        ProjectProgressApi.setCustomerCity("");
        ProjectProgressApi.setCustomerOccupation("");
        ProjectProgressApi.setCustomerFocus("");
        ProjectProgressApi.setCustomerIntentionalBuilding("");
        ProjectProgressApi.setCustomerPaymentMethod("");
        ProjectProgressApi.setCustomerHasDecision("");
        ProjectProgressApi.setCustomerResistance("");
        ProjectProgressApi.setCustomerObjective("");
        ProjectProgressApi.setCustomerAuditStatus("");

        lien = findViewById(R.id.lien);
        all_no_information = findViewById(R.id.all_no_information);
        review_the_success_return = findViewById(R.id.review_the_success_return);
        review_the_success_img1 = findViewById(R.id.review_the_success_img1);
        review_the_success_tv1 = findViewById(R.id.review_the_success_tv1);
        review_the_success_tv2 = findViewById(R.id.review_the_success_tv2);
        review_the_success_tv3 = findViewById(R.id.review_the_success_tv3);
        review_the_success_img3 = findViewById(R.id.review_the_success_img3);
        review_the_success_rv = findViewById(R.id.review_the_success_rv);

        linearlayout_ll = findViewById(R.id.linearlayout_ll);
        review_the_success_bt1 = findViewById(R.id.review_the_success_bt1);
        review_the_success_bt2 = findViewById(R.id.review_the_success_bt2);
        review_the_success_bt3 = findViewById(R.id.review_the_success_bt3);
        review_the_success_bt4 = findViewById(R.id.review_the_success_bt4);
        review_the_success_bt5 = findViewById(R.id.review_the_success_bt5);
        review_the_success_bt6 = findViewById(R.id.review_the_success_bt6);
        review_the_success_bt7 = findViewById(R.id.review_the_success_bt7);
        review_the_success_bt8 = findViewById(R.id.review_the_success_bt8);
        review_the_success_bt9 = findViewById(R.id.review_the_success_bt9);
        review_the_success_bt10 = findViewById(R.id.review_the_success_bt10);
        review_the_success_bt11 = findViewById(R.id.review_the_success_bt11);
        review_the_success_bt12 = findViewById(R.id.review_the_success_bt12);
        review_the_success_bt13 = findViewById(R.id.review_the_success_bt13);

        linearlayout_l = findViewById(R.id.linearlayout_l);
        review_the_success_b1 = findViewById(R.id.review_the_success_b1);
        review_the_success_b2 = findViewById(R.id.review_the_success_b2);
        review_the_success_b3 = findViewById(R.id.review_the_success_b3);
        review_the_success_b4 = findViewById(R.id.review_the_success_b4);
        review_the_success_b5 = findViewById(R.id.review_the_success_b5);
        review_the_success_b6 = findViewById(R.id.review_the_success_b6);
        review_the_success_b7 = findViewById(R.id.review_the_success_b7);
        review_the_success_b8 = findViewById(R.id.review_the_success_b8);
        review_the_success_b9 = findViewById(R.id.review_the_success_b9);
        review_the_success_b10 = findViewById(R.id.review_the_success_b10);
        review_the_success_b11 = findViewById(R.id.review_the_success_b11);
        review_the_success_b12 = findViewById(R.id.review_the_success_b12);
        review_the_success_b13 = findViewById(R.id.review_the_success_b13);
        review_the_success_nameRv = findViewById(R.id.review_the_success_NameRv);

        success_linear1 = findViewById(R.id.success_linear1);
        success_linear2 = findViewById(R.id.success_linear2);
        success_linear3 = findViewById(R.id.success_linear3);
        success_linear4 = findViewById(R.id.success_linear4);
        success_linear5 = findViewById(R.id.success_linear5);
        success_linear6 = findViewById(R.id.success_linear6);
        success_linear7 = findViewById(R.id.success_linear7);
        success_linear8 = findViewById(R.id.success_linear8);
        success_linear9 = findViewById(R.id.success_linear9);
        success_linear10 = findViewById(R.id.success_linear10);
        success_linear11 = findViewById(R.id.success_linear11);
        success_linear12 = findViewById(R.id.success_linear12);
        success_linear13 = findViewById(R.id.success_linear13);


        review_the_success_bt1.setOnClickListener(this);
        review_the_success_bt2.setOnClickListener(this);
        review_the_success_bt3.setOnClickListener(this);
        review_the_success_bt4.setOnClickListener(this);
        review_the_success_bt5.setOnClickListener(this);

        review_the_success_bt6.setOnClickListener(this);
        review_the_success_bt7.setOnClickListener(this);
        review_the_success_bt8.setOnClickListener(this);
        review_the_success_bt9.setOnClickListener(this);
        review_the_success_bt10.setOnClickListener(this);

        review_the_success_bt11.setOnClickListener(this);
        review_the_success_bt12.setOnClickListener(this);
        review_the_success_bt13.setOnClickListener(this);


        review_the_success_b1.setOnClickListener(this);
        review_the_success_b2.setOnClickListener(this);
        review_the_success_b3.setOnClickListener(this);
        review_the_success_b4.setOnClickListener(this);
        review_the_success_b5.setOnClickListener(this);

        review_the_success_b6.setOnClickListener(this);
        review_the_success_b7.setOnClickListener(this);
        review_the_success_b8.setOnClickListener(this);
        review_the_success_b9.setOnClickListener(this);
        review_the_success_b10.setOnClickListener(this);

        review_the_success_b11.setOnClickListener(this);
        review_the_success_b12.setOnClickListener(this);
        review_the_success_b13.setOnClickListener(this);

        review_the_success_bt1.setVisibility(View.GONE);
        review_the_success_bt2.setVisibility(View.GONE);
        review_the_success_bt3.setVisibility(View.GONE);
        review_the_success_bt4.setVisibility(View.GONE);
        review_the_success_bt5.setVisibility(View.GONE);
        review_the_success_bt6.setVisibility(View.GONE);
        review_the_success_bt7.setVisibility(View.GONE);
        review_the_success_bt8.setVisibility(View.GONE);
        review_the_success_bt9.setVisibility(View.GONE);
        review_the_success_bt10.setVisibility(View.GONE);
        review_the_success_bt11.setVisibility(View.GONE);
        review_the_success_bt12.setVisibility(View.GONE);
        review_the_success_bt13.setVisibility(View.GONE);


        initData();

    }

    private void initData() {

        Log.i("项目进度页", "getCustomerId：" + FinalContents.getCustomerID());
        Log.i("项目进度页", "userID：" + FinalContents.getUserID());

        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<ReportProcessDetailsBean> clientFragmentBean = fzbInterface.getReportProcessDetails(FinalContents.getUserID(), FinalContents.getPreparationId());
        clientFragmentBean.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ReportProcessDetailsBean>() {

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ReportProcessDetailsBean reportProcessDetailsBean) {

                        lien.setVisibility(View.VISIBLE);
                        all_no_information.setVisibility(View.GONE);
                        if (reportProcessDetailsBean.getData().getMenuData().size() > 1) {
                            linearlayout_ll.setVisibility(View.VISIBLE);
                            linearlayout_l.setVisibility(View.GONE);
                            for (int i = 0;i < reportProcessDetailsBean.getData().getMenuData().size();i++){
                                if (reportProcessDetailsBean.getData().getMenuData().get(i).getMeunkey().equals("0")) {
                                    //  TODO    失效
                                    review_the_success_bt1.setVisibility(View.VISIBLE);
                                    success_linear1.setVisibility(View.VISIBLE);
                                    review_the_success_bt1.setText(reportProcessDetailsBean.getData().getMenuData().get(i).getMenuname());
                                } else if (reportProcessDetailsBean.getData().getMenuData().get(i).getMeunkey().equals("40")) {
                                    //  TODO    申请登岛
                                    review_the_success_bt2.setVisibility(View.VISIBLE);
                                    success_linear2.setVisibility(View.VISIBLE);
                                    review_the_success_bt2.setText(reportProcessDetailsBean.getData().getMenuData().get(i).getMenuname());
                                } else if (reportProcessDetailsBean.getData().getMenuData().get(i).getMeunkey().equals("20")) {
                                    //  TODO    申请到访
                                    review_the_success_bt3.setVisibility(View.VISIBLE);
                                    success_linear3.setVisibility(View.VISIBLE);
                                    review_the_success_bt3.setText(reportProcessDetailsBean.getData().getMenuData().get(i).getMenuname());
                                } else if (reportProcessDetailsBean.getData().getMenuData().get(i).getMeunkey().equals("50")) {
                                    //  TODO    认筹
                                    review_the_success_bt4.setVisibility(View.VISIBLE);
                                    success_linear4.setVisibility(View.VISIBLE);
                                    review_the_success_bt4.setText(reportProcessDetailsBean.getData().getMenuData().get(i).getMenuname());
                                } else if (reportProcessDetailsBean.getData().getMenuData().get(i).getMeunkey().equals("60")) {
                                    //  TODO    成交
                                    review_the_success_bt5.setVisibility(View.VISIBLE);
                                    success_linear5.setVisibility(View.VISIBLE);
                                    review_the_success_bt5.setText(reportProcessDetailsBean.getData().getMenuData().get(i).getMenuname());
                                } else if (reportProcessDetailsBean.getData().getMenuData().get(i).getMeunkey().equals("402")) {
                                    //  TODO    未成交
                                    review_the_success_bt6.setVisibility(View.VISIBLE);
                                    success_linear6.setVisibility(View.VISIBLE);
                                    review_the_success_bt6.setText(reportProcessDetailsBean.getData().getMenuData().get(i).getMenuname());
                                } else if (reportProcessDetailsBean.getData().getMenuData().get(i).getMeunkey().equals("500")) {
                                    //  TODO    修改信息
                                    review_the_success_bt7.setVisibility(View.VISIBLE);
                                    success_linear7.setVisibility(View.VISIBLE);
                                    review_the_success_bt7.setText(reportProcessDetailsBean.getData().getMenuData().get(i).getMenuname());
                                } else if (reportProcessDetailsBean.getData().getMenuData().get(i).getMeunkey().equals("501")) {
                                    //  TODO    退筹
                                    review_the_success_bt8.setVisibility(View.VISIBLE);
                                    success_linear8.setVisibility(View.VISIBLE);
                                    review_the_success_bt8.setText(reportProcessDetailsBean.getData().getMenuData().get(i).getMenuname());
                                } else if (reportProcessDetailsBean.getData().getMenuData().get(i).getMeunkey().equals("601")) {
                                    //  TODO    调单
                                    review_the_success_bt9.setVisibility(View.VISIBLE);
                                    success_linear9.setVisibility(View.VISIBLE);
                                    review_the_success_bt9.setText(reportProcessDetailsBean.getData().getMenuData().get(i).getMenuname());
                                } else if (reportProcessDetailsBean.getData().getMenuData().get(i).getMeunkey().equals("602")) {
                                    //  TODO    退单
                                    review_the_success_bt10.setVisibility(View.VISIBLE);
                                    success_linear10.setVisibility(View.VISIBLE);
                                    review_the_success_bt10.setText(reportProcessDetailsBean.getData().getMenuData().get(i).getMenuname());
                                } else if (reportProcessDetailsBean.getData().getMenuData().get(i).getMeunkey().equals("5")) {
                                    //  TODO    通过
                                    review_the_success_bt11.setVisibility(View.VISIBLE);
                                    success_linear11.setVisibility(View.VISIBLE);
                                    review_the_success_bt11.setText(reportProcessDetailsBean.getData().getMenuData().get(i).getMenuname());
                                } else if (reportProcessDetailsBean.getData().getMenuData().get(i).getMeunkey().equals("6")) {
                                    //  TODO    拒绝
                                    review_the_success_bt12.setVisibility(View.VISIBLE);
                                    success_linear12.setVisibility(View.VISIBLE);
                                    review_the_success_bt12.setText(reportProcessDetailsBean.getData().getMenuData().get(i).getMenuname());
                                } else if (reportProcessDetailsBean.getData().getMenuData().get(i).getMeunkey().equals("400")) {
                                    //  TODO    补全信息
                                    review_the_success_bt13.setVisibility(View.VISIBLE);
                                    success_linear13.setVisibility(View.VISIBLE);
                                    review_the_success_bt13.setText(reportProcessDetailsBean.getData().getMenuData().get(i).getMenuname());
                                }
                            }
                        }
                        else if (reportProcessDetailsBean.getData().getMenuData().size() == 1){
                            linearlayout_ll.setVisibility(View.GONE);
                            linearlayout_l.setVisibility(View.VISIBLE);
                            for (int i = 0;i < reportProcessDetailsBean.getData().getMenuData().size();i++){
                                if (reportProcessDetailsBean.getData().getMenuData().get(i).getMeunkey().equals("0")) {
                                    //  TODO    失效
                                    review_the_success_b1.setVisibility(View.VISIBLE);
                                    review_the_success_b1.setText(reportProcessDetailsBean.getData().getMenuData().get(i).getMenuname());
                                } else if (reportProcessDetailsBean.getData().getMenuData().get(i).getMeunkey().equals("40")) {
                                    //  TODO    申请登岛
                                    review_the_success_b2.setVisibility(View.VISIBLE);
                                    review_the_success_b2.setText(reportProcessDetailsBean.getData().getMenuData().get(i).getMenuname());
                                } else if (reportProcessDetailsBean.getData().getMenuData().get(i).getMeunkey().equals("20")) {
                                    //  TODO    申请到访
                                    review_the_success_b3.setVisibility(View.VISIBLE);
                                    review_the_success_b3.setText(reportProcessDetailsBean.getData().getMenuData().get(i).getMenuname());
                                } else if (reportProcessDetailsBean.getData().getMenuData().get(i).getMeunkey().equals("50")) {
                                    //  TODO    认筹
                                    review_the_success_b4.setVisibility(View.VISIBLE);
                                    review_the_success_b4.setText(reportProcessDetailsBean.getData().getMenuData().get(i).getMenuname());
                                } else if (reportProcessDetailsBean.getData().getMenuData().get(i).getMeunkey().equals("60")) {
                                    //  TODO    成交
                                    review_the_success_b5.setVisibility(View.VISIBLE);
                                    review_the_success_b5.setText(reportProcessDetailsBean.getData().getMenuData().get(i).getMenuname());
                                } else if (reportProcessDetailsBean.getData().getMenuData().get(i).getMeunkey().equals("402")) {
                                    //  TODO    未成交
                                    review_the_success_b6.setVisibility(View.VISIBLE);
                                    review_the_success_b6.setText(reportProcessDetailsBean.getData().getMenuData().get(i).getMenuname());
                                } else if (reportProcessDetailsBean.getData().getMenuData().get(i).getMeunkey().equals("500")) {
                                    //  TODO    修改信息
                                    review_the_success_b7.setVisibility(View.VISIBLE);
                                    review_the_success_b7.setText(reportProcessDetailsBean.getData().getMenuData().get(i).getMenuname());
                                } else if (reportProcessDetailsBean.getData().getMenuData().get(i).getMeunkey().equals("501")) {
                                    //  TODO    退筹
                                    review_the_success_b8.setVisibility(View.VISIBLE);
                                    review_the_success_b8.setText(reportProcessDetailsBean.getData().getMenuData().get(i).getMenuname());
                                } else if (reportProcessDetailsBean.getData().getMenuData().get(i).getMeunkey().equals("601")) {
                                    //  TODO    调单
                                    review_the_success_b9.setVisibility(View.VISIBLE);
                                    review_the_success_b9.setText(reportProcessDetailsBean.getData().getMenuData().get(i).getMenuname());
                                } else if (reportProcessDetailsBean.getData().getMenuData().get(i).getMeunkey().equals("602")) {
                                    //  TODO    退单
                                    review_the_success_b10.setVisibility(View.VISIBLE);
                                    review_the_success_b10.setText(reportProcessDetailsBean.getData().getMenuData().get(i).getMenuname());
                                } else if (reportProcessDetailsBean.getData().getMenuData().get(i).getMeunkey().equals("5")) {
                                    //  TODO    通过
                                    review_the_success_b11.setVisibility(View.VISIBLE);
                                    review_the_success_b11.setText(reportProcessDetailsBean.getData().getMenuData().get(i).getMenuname());
                                } else if (reportProcessDetailsBean.getData().getMenuData().get(i).getMeunkey().equals("6")) {
                                    //  TODO    拒绝
                                    review_the_success_b12.setVisibility(View.VISIBLE);
                                    review_the_success_b12.setText(reportProcessDetailsBean.getData().getMenuData().get(i).getMenuname());
                                } else if (reportProcessDetailsBean.getData().getMenuData().get(i).getMeunkey().equals("400")) {
                                    //  TODO    补全信息
                                    review_the_success_b13.setVisibility(View.VISIBLE);
                                    review_the_success_b13.setText(reportProcessDetailsBean.getData().getMenuData().get(i).getMenuname());
                                }
                            }
                        }


                        infoData = reportProcessDetailsBean.getData().getInfoData();
                        if (infoData.getCustomerImg() != null) {
                            if (!infoData.getCustomerImg().equals("")) {
                                Glide.with(ReviewTheSuccessActivity.this).load(FinalContents.getImageUrl() + infoData.getCustomerImg()).into(review_the_success_img1);
                            }
                        }
                        if(FinalContents.getIdentity().equals("4")){
                            review_the_success_tv1.setText(infoData.getCustomerName());
                        }else {
                            review_the_success_tv1.setText(infoData.getCustomerName()+"("+infoData.getCustomerPhone()+")");
                        }

                        processData = reportProcessDetailsBean.getData().getProcessData();

                        FinalContents.setProjectType(infoData.getProjectType());
                        review_the_success_tv2.setText(infoData.getProjectName());


                        if (FinalContents.getIdentity().equals("4")) {
                            review_the_success_tv3.setVisibility(View.VISIBLE);
                            review_the_success_img3.setVisibility(View.VISIBLE);
                            review_the_success_tv3.setText("电话："+infoData.getCustomerPhone());
                            review_the_success_tv3.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    Intent dialIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + infoData.getCustomerPhone()));//跳转到拨号界面，同时传递电话号码
                                    startActivity(dialIntent);
                                }
                            });
                            review_the_success_nameRv.setVisibility(View.GONE);
                        }else {
                            review_the_success_img3.setVisibility(View.GONE);
                            if (reportProcessDetailsBean.getData().getAttacheList().size() == 0) {
                                review_the_success_tv3.setVisibility(View.GONE);
                            }else {
                                GridLayoutManager gridLayoutManager = new GridLayoutManager(ReviewTheSuccessActivity.this,2);
                                gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
                                review_the_success_nameRv.setLayoutManager(gridLayoutManager);
                                ReviewTheSuccessPhoneAdapter reviewTheSuccessPhoneAdapter = new ReviewTheSuccessPhoneAdapter(reportProcessDetailsBean.getData().getAttacheList());
                                review_the_success_nameRv.setAdapter(reviewTheSuccessPhoneAdapter);
                                reviewTheSuccessPhoneAdapter.notifyDataSetChanged();
                                review_the_success_tv3.setText("项目负责人：");
                            }
                        }


                        FinalContents.setJJrID(infoData.getAgentId());
                        ProjectProgressApi.setCustomerName(infoData.getCustomerName());        //      TODO    客户名
                        FinalContents.setProjectID(infoData.getProjectId());
                        Log.i("项目路线","项目ID："+ FinalContents.getProjectID());
                        initRV();

                        review_the_success_return.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                FinalContents.setTiaozhuang("");
                                finish();
                            }
                        });
                    }

                    @Override
                    public void onError(Throwable e) {
                        all_no_information.setVisibility(View.VISIBLE);
                        lien.setVisibility(View.GONE);
                        Log.i("项目进度页", "错误信息：" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @SuppressLint("WrongConstant")
    private void initRV() {
        MyLinearLayoutManager layoutManager = new MyLinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager.setScrollEnabled(false);
        review_the_success_rv.setLayoutManager(layoutManager);
        adapter = new ReviewTheSuccessAdapter(processData);
        review_the_success_rv.setNestedScrollingEnabled(false);
        review_the_success_rv.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @SingleClick(1000)
    @Override
    public void onClick(View view) {


        switch (view.getId()) {
            //            TODO 失效
            case R.id.review_the_success_bt1:
                initShiXiao();
                break;
            //            TODO 申请登岛
            case R.id.review_the_success_bt2:
                intent = new Intent(ReviewTheSuccessActivity.this, ToApplyForAnIslandActivity.class);
                ProjectProgressApi.setCustomerName(infoData.getCustomerName());
                ProjectProgressApi.setComplemented("0");
                startActivity(intent);
                finish();
                break;
            //            TODO 申请到访
            case R.id.review_the_success_bt3:
                intent = new Intent(ReviewTheSuccessActivity.this, ConfirmTheVisitActivity.class);
                ProjectProgressApi.setCustomerName(infoData.getCustomerName());
                ProjectProgressApi.setProjectName(infoData.getProjectName());
                startActivity(intent);
                finish();

                break;
            //            TODO 认筹
            case R.id.review_the_success_bt4:
                FinalContents.setPreparationId(infoData.getPreparationId());
                FinalContents.setProjectID(infoData.getProjectId());
                FinalContents.setCustomerID(infoData.getCustomerId());
                intent = new Intent(ReviewTheSuccessActivity.this, ConfessToRaiseInformationActivity.class);
                ProjectProgressApi.setCustomerName(infoData.getCustomerName());
                ProjectProgressApi.setProjectName(infoData.getProjectName());
                ProjectProgressApi.setCustomerPhone(infoData.getCustomerPhone());
                startActivity(intent);
                finish();

                break;
            //            TODO 成交
            case R.id.review_the_success_bt5:
                FinalContents.setProjectID(infoData.getProjectId());
                intent = new Intent(ReviewTheSuccessActivity.this, FillInTransactionInformationActivity.class);
                FinalContents.setTiaodan("成交");
                startActivity(intent);
                finish();

                break;
            //            TODO 未成交
            case R.id.review_the_success_bt6:
                finish();
                break;
            //            TODO 修改信息
            case R.id.review_the_success_bt7:
                intent = new Intent(ReviewTheSuccessActivity.this, ModifyTheRecognitionToRaiseActivity.class);
                startActivity(intent);
                finish();
                break;
            //            TODO 退筹
            case R.id.review_the_success_bt8:
                intent = new Intent(ReviewTheSuccessActivity.this, BackToRaiseThatActivity.class);
                startActivity(intent);
                finish();

                break;
            //            TODO 调单
            case R.id.review_the_success_bt9:
                intent = new Intent(ReviewTheSuccessActivity.this, FillInTransactionInformationActivity.class);
                FinalContents.setTiaodan("调单");
                startActivity(intent);
                finish();
                break;
            //            TODO 退单
            case R.id.review_the_success_bt10:
                intent = new Intent(ReviewTheSuccessActivity.this, ListOfBackActivity.class);
                startActivity(intent);
                finish();

                break;
            //            TODO 通过
            case R.id.review_the_success_bt11:
                initBTN();
                finish();

                break;
            //            TODO 拒绝
            case R.id.review_the_success_bt12:
                intent = new Intent(ReviewTheSuccessActivity.this, TheReasonForRefusalActivity.class);
                startActivity(intent);
                finish();

                break;
            //            TODO 补全信息
            case R.id.review_the_success_bt13:
                intent = new Intent(ReviewTheSuccessActivity.this, ToApplyForAnIslandActivity.class);
                ProjectProgressApi.setCustomerName(infoData.getCustomerName());
                ProjectProgressApi.setComplemented("1");
                startActivity(intent);
                finish();
                break;


            //            TODO 失效
            case R.id.review_the_success_b1:
                initShiXiao();
                break;
            //            TODO 申请登岛
            case R.id.review_the_success_b2:
                intent = new Intent(ReviewTheSuccessActivity.this, ToApplyForAnIslandActivity.class);
                ProjectProgressApi.setCustomerName(infoData.getCustomerName());
                ProjectProgressApi.setComplemented("0");
                startActivity(intent);
                finish();
                break;
            //            TODO 申请到访
            case R.id.review_the_success_b3:
                intent = new Intent(ReviewTheSuccessActivity.this, ConfirmTheVisitActivity.class);
                ProjectProgressApi.setCustomerName(infoData.getCustomerName());
                ProjectProgressApi.setProjectName(infoData.getProjectName());
                startActivity(intent);
                finish();

                break;
            //            TODO 认筹
            case R.id.review_the_success_b4:
                FinalContents.setPreparationId(infoData.getPreparationId());
                FinalContents.setProjectID(infoData.getProjectId());
                FinalContents.setCustomerID(infoData.getCustomerId());
                intent = new Intent(ReviewTheSuccessActivity.this, ConfessToRaiseInformationActivity.class);
                ProjectProgressApi.setCustomerName(infoData.getCustomerName());
                ProjectProgressApi.setProjectName(infoData.getProjectName());
                ProjectProgressApi.setCustomerPhone(infoData.getCustomerPhone());
                startActivity(intent);
                finish();

                break;
            //            TODO 成交
            case R.id.review_the_success_b5:
                FinalContents.setProjectID(infoData.getProjectId());
                intent = new Intent(ReviewTheSuccessActivity.this, FillInTransactionInformationActivity.class);
                FinalContents.setTiaodan("成交");
                startActivity(intent);
                finish();

                break;
            //            TODO 未成交
            case R.id.review_the_success_b6:
                finish();
                break;
            //            TODO 修改信息
            case R.id.review_the_success_b7:
                intent = new Intent(ReviewTheSuccessActivity.this, ModifyTheRecognitionToRaiseActivity.class);
                startActivity(intent);
                finish();
                break;
            //            TODO 退筹
            case R.id.review_the_success_b8:
                intent = new Intent(ReviewTheSuccessActivity.this, BackToRaiseThatActivity.class);
                startActivity(intent);
                finish();

                break;
            //            TODO 调单
            case R.id.review_the_success_b9:
                intent = new Intent(ReviewTheSuccessActivity.this, FillInTransactionInformationActivity.class);
                FinalContents.setTiaodan("调单");
                startActivity(intent);
                finish();
                break;
            //            TODO 退单
            case R.id.review_the_success_b10:
                intent = new Intent(ReviewTheSuccessActivity.this, ListOfBackActivity.class);
                startActivity(intent);
                finish();

                break;
            //            TODO 通过
            case R.id.review_the_success_b11:
                initBTN();
                finish();

                break;
            //            TODO 拒绝
            case R.id.review_the_success_b12:
                intent = new Intent(ReviewTheSuccessActivity.this, TheReasonForRefusalActivity.class);
                startActivity(intent);
                finish();

                break;
            //            TODO 补全信息
            case R.id.review_the_success_b13:
                intent = new Intent(ReviewTheSuccessActivity.this, ToApplyForAnIslandActivity.class);
                ProjectProgressApi.setCustomerName(infoData.getCustomerName());
                ProjectProgressApi.setComplemented("1");
                startActivity(intent);
                finish();

                break;
        }

    }

    @SuppressLint("WrongConstant")
    private void initBTN() {
        Log.i("MyCL", "getUserID：" + FinalContents.getUserID());
        Log.i("MyCL", "getPreparationId：" + FinalContents.getPreparationId());
        Log.i("MyCL","Name：" + name);
        if (name.equals("报备") || name.equals("到访")) {
            url = FinalContents.getBaseUrl() + "specialUpdate/reportAndVisitAudit?preparationId=" + FinalContents.getPreparationId() + "&maxStatus=" + FinalContents.getStatus() + "&minStatus=1&userId=" + FinalContents.getUserID();
            OkHttpPost okHttpPost = new OkHttpPost(url);
            String data = okHttpPost.post();
            Gson gson = new Gson();
            CBean cBean = gson.fromJson(data, CBean.class);
            if (cBean.getMsg().equals("成功")) {
                ToastUtil.showToast(ReviewTheSuccessActivity.this, cBean.getData().getMessage());
                finish();
            } else {
                ToastUtil.showToast(ReviewTheSuccessActivity.this, cBean.getData().getMessage());
                finish();
            }
        } else if (name.equals("认筹")) {
            url = FinalContents.getBaseUrl() + "specialUpdate/earnestMoneyAudit?userId=" + FinalContents.getUserID() + "&preparationId=" + FinalContents.getPreparationId() + "&isUpdate=0";
            OkHttpPost okHttpPost = new OkHttpPost(url);
            String data = okHttpPost.post();
            Gson gson = new Gson();
            CheckBean checkBean = gson.fromJson(data, CheckBean.class);
            if (checkBean.getMsg().equals("成功")) {
                ToastUtil.showToast(ReviewTheSuccessActivity.this, checkBean.getMsg());
                finish();
            } else {
                ToastUtil.showToast(ReviewTheSuccessActivity.this, checkBean.getMsg());
                finish();
            }
        } else if (name.equals("成功")) {
            url = FinalContents.getBaseUrl() + "specialUpdate/tradeAudit?userId=" + FinalContents.getUserID() + "&preparationId=" + FinalContents.getPreparationId() + "&isUpdate=0";
            OkHttpPost okHttpPost = new OkHttpPost(url);
            String data = okHttpPost.post();
            Gson gson = new Gson();
            CheckBean checkBean = gson.fromJson(data, CheckBean.class);
            if (checkBean.getMsg().equals("成功")) {
                ToastUtil.showToast(ReviewTheSuccessActivity.this, checkBean.getMsg());
                finish();
            } else {
                ToastUtil.showToast(ReviewTheSuccessActivity.this, checkBean.getMsg());
                finish();
            }
        }

    }

    private void initShiXiao(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("确定失效操作？");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String s = FinalContents.getImageUrl()+"/fangfang/app/v1/specialUpdate/failureSave?userId=" + FinalContents.getUserID() + "&preparationId=" + infoData.getPreparationId();
                OkHttpPost okHttpPost = new OkHttpPost(s);
                String post = okHttpPost.post();
                Gson gson = new Gson();
                FailureAlertBean failureAlertBean = gson.fromJson(post, FailureAlertBean.class);
                if(failureAlertBean.getMsg().equals("成功")){
                    ToastUtil.showToast(ReviewTheSuccessActivity.this,failureAlertBean.getData().getMessage());
                    FinalContents.setTiaozhuang("失效成功");
                    finish();
                }else{
                    ToastUtil.showToast(ReviewTheSuccessActivity.this,failureAlertBean.getData().getMessage());
                }
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.show();
    }

    private void initReadRecord(){
        Log.i("状态", "项目进度中状态"+CityContents.getReadRecordStatus());
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<ReadRecordBean> clientFragment = fzbInterface.getReadRecord(FinalContents.getUserID(),FinalContents.getPreparationId(), CityContents.getReadRecordStatus());
        clientFragment.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ReadRecordBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ReadRecordBean readRecordBean) {
                        Log.i("MyCL", "项目进度中未浏览信息"+readRecordBean.getData().getMessage());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("MyCL", "项目进度中未浏览错误信息" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        FinalContents.setTiaozhuang("");
    }
}


package com.xcy.fzbcity.project_attache.fragment;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.bumptech.glide.Glide;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.xcy.fzbcity.Login.LoginActivity;
import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.api.Connector;
import com.xcy.fzbcity.all.api.FinalContents;
import com.xcy.fzbcity.all.api.NewlyIncreased;
import com.xcy.fzbcity.all.modle.ZYDataBean;
import com.xcy.fzbcity.all.persente.CleanDataUtils;
import com.xcy.fzbcity.all.persente.SingleClick;
import com.xcy.fzbcity.all.persente.StatusBar;
import com.xcy.fzbcity.all.service.MyService;
import com.xcy.fzbcity.all.utils.ToastUtil;
import com.xcy.fzbcity.all.view.AboutFZBActivity;
import com.xcy.fzbcity.all.view.CollectActivity;
import com.xcy.fzbcity.all.view.FeedbackActivity;
import com.xcy.fzbcity.all.view.PersonalInformationActivity;
import com.xcy.fzbcity.project_attache.adapter.MyDataStoreBean;
import com.xcy.fzbcity.project_attache.view.CommissionActivity;
import com.xcy.fzbcity.project_attache.view.PunchingCardRecordActivity;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EFragment extends Fragment implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener {

    RelativeLayout my_collect;
    RelativeLayout my_comment;
    RelativeLayout my_about;
    RelativeLayout my_empty;
    RelativeLayout my_exit;
    RelativeLayout me_gr;
    RelativeLayout my_rl_1;

    ImageView me_photo;
    TextView me_name;
    TextView me_identity;
    TextView me_city;
    TextView me_store;
    private TextView my_tv_huancun;
    private Intent intent;
    private ZYDataBean.DataBean data;

    LinearLayout attache_ll_1;
    LinearLayout attache_ll_2;
    TextView attache_tv_1;
    TextView attache_tv_2;
    private SwipeRefreshLayout layout;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.project_attache_modulebroker_fragment_me, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//TODO 设置导航栏、标题栏透明
        StatusBar.makeStatusBarTransparent(getActivity());

        me_gr = getActivity().findViewById(R.id.me_gr);//个人中心
        my_collect = getActivity().findViewById(R.id.my_collect);//我的收藏
        my_comment = getActivity().findViewById(R.id.my_comment);//意见反馈
        my_about = getActivity().findViewById(R.id.my_about);//关于房坐标
        my_empty = getActivity().findViewById(R.id.my_empty);//清空缓存
        my_exit = getActivity().findViewById(R.id.my_exit);//退出登录

        attache_ll_1 = getActivity().findViewById(R.id.attache_ll_1);
        attache_ll_2 = getActivity().findViewById(R.id.attache_ll_2);
        attache_tv_1 = getActivity().findViewById(R.id.attache_tv_1);
        attache_tv_2 = getActivity().findViewById(R.id.attache_tv_2);
        layout = getActivity().findViewById(R.id.e_ssrfl_4);
        my_rl_1 = getActivity().findViewById(R.id.my_rl_1);
        me_photo = getActivity().findViewById(R.id.me_photo);
        me_name = getActivity().findViewById(R.id.me_name);
        me_identity = getActivity().findViewById(R.id.me_identity);
        me_city = getActivity().findViewById(R.id.me_city);
        me_store = getActivity().findViewById(R.id.me_store);

        my_tv_huancun = getActivity().findViewById(R.id.my_tv_huancun);
        try {
            my_tv_huancun.setText(CleanDataUtils.getTotalCacheSize(getActivity()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        layout.setOnRefreshListener(this);
        my_rl_1.setOnClickListener(this);
        my_collect.setOnClickListener(this);
        my_comment.setOnClickListener(this);
        my_about.setOnClickListener(this);
        my_empty.setOnClickListener(this);
        my_exit.setOnClickListener(this);
        attache_ll_1.setOnClickListener(this);
        attache_ll_2.setOnClickListener(this);
        initUserMessage();
        initMyDataStore();
    }

    private void initMyDataStore() {

        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<MyDataStoreBean> userMessage = fzbInterface.getMyDataStore(FinalContents.getUserID());
        userMessage.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MyDataStoreBean>() {
                    @Override
                    public void onSubscribe(Disposable disposable) {

                    }

                    @Override
                    public void onNext(MyDataStoreBean myDataStoreBean) {
                        attache_tv_1.setText(myDataStoreBean.getData().getRecordNum());
                        attache_tv_2.setText(myDataStoreBean.getData().getTotalAmountStr());
                    }

                    @Override
                    public void onError(Throwable throwable) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    private void initUserMessage() {

        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<ZYDataBean> userMessage = fzbInterface.getZYDataBean(FinalContents.getUserID());
        userMessage.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ZYDataBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ZYDataBean userMessageBean) {
                        data = userMessageBean.getData();

                        String s = data.getPhoto().toString();
                        StringBuffer stringBuffer = new StringBuffer();
                        stringBuffer.append(s);

                        Glide.with(getActivity()).load(FinalContents.getImageUrl() + data.getPhoto()).into(me_photo);

                        me_name.setText(data.getName());

                        if (userMessageBean.getData().getIdentity().equals("5")) {
                            me_identity.setText("专员");
                        }else if (userMessageBean.getData().getIdentity().equals("8")) {
                            me_identity.setText("经理");
                        }else if (userMessageBean.getData().getIdentity().equals("9")) {
                            me_identity.setText("总监");
                        }
                        me_city.setText(data.getCity());
//                        me_store.setText(data.getStoreManage() + "");
                        me_store.setText(data.getCityName());

                        Connector.setZyDataBean(userMessageBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("MyCL", "根据用户id获取用户信息错误:" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });




    }

    //点击事件
    @SingleClick(1000)
    @Override
    public void onClick(View view) {
        Log.i("MyCL","onClick");
        int id = view.getId();
        if (id == R.id.my_collect) {
//            TODO 我的收藏
            intent = new Intent(getContext(), CollectActivity.class);
            startActivity(intent);
        } else if(id == R.id.attache_ll_1){
            //TODO 打卡历史记录
            Log.i("MyCL","attache_ll_1");
            intent = new Intent(getContext(), PunchingCardRecordActivity.class);
            startActivity(intent);
        } else if(id == R.id.attache_ll_2){
            //TODO 我的佣金
            Log.i("MyCL","attache_ll_1");
            intent = new Intent(getContext(), CommissionActivity.class);
            FinalContents.setCompanyId("");
            FinalContents.setStoreId("");
            FinalContents.setAgentId("");
            startActivity(intent);
        }else if (id == R.id.my_rl_1) {
//            TODO 个人信息
            Log.i("MyCL","my_rl_1");
            intent = new Intent(getContext(), PersonalInformationActivity.class);

            startActivity(intent);
        } else if (id == R.id.my_comment) {
//            TODO 意见反馈
            intent = new Intent(getContext(), FeedbackActivity.class);
            startActivity(intent);
        } else if (id == R.id.my_about) {
//            TODO 关于房坐标
            intent = new Intent(getContext(), AboutFZBActivity.class);
            startActivity(intent);
        } else if (id == R.id.my_empty) {
//            TODO 清空缓存
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setMessage("确认清除缓存吗?");
            builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    try {
                        String totalCacheSize = CleanDataUtils.getTotalCacheSize(getActivity());
                        CleanDataUtils.clearAllCache(getActivity());
                        ToastUtil.showLongToast(getContext(),"清理缓存成功,共清理了" + totalCacheSize + "内存");
                        my_tv_huancun.setText("0 M");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
//                    Toast.makeText(getActivity(), "取消清理", Toast.LENGTH_SHORT).show();
                }
            });
            AlertDialog show = builder.show();
            show.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.parseColor("#334485"));
            show.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.parseColor("#334485"));
        } else if (id == R.id.my_exit) {
//            TODO 退出登录
            AlertDialog.Builder builder1 = new AlertDialog.Builder(getActivity());
            View inflate = LayoutInflater.from(getActivity()).inflate(R.layout.binding_report, null, false);
            builder1.setView(inflate);
            final AlertDialog show = builder1.show();
            show.getWindow().setBackgroundDrawableResource(R.drawable.report_shape);
            TextView report_binding_title = inflate.findViewById(R.id.report_binding_title);
            TextView report_binding_confirm_tv = inflate.findViewById(R.id.report_binding_confirm_tv);
            TextView report_binding_cancel_tv = inflate.findViewById(R.id.report_binding_cancel_tv);
            RelativeLayout report_binding_cancel = inflate.findViewById(R.id.report_binding_cancel);
            RelativeLayout report_binding_confirm = inflate.findViewById(R.id.report_binding_confirm);
            report_binding_title.setText("确定要退出程序吗?");//内容
            report_binding_confirm_tv.setText("确定");
            report_binding_cancel_tv.setText("取消");
            report_binding_title.setTextColor(Color.parseColor("#111111"));
            report_binding_cancel_tv.setTextColor(Color.parseColor("#334485"));
            report_binding_confirm_tv.setTextColor(Color.parseColor("#334485"));
            report_binding_cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    show.dismiss();
                }
            });
            report_binding_confirm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FinalContents.setIFSP("1");
                    intent = new Intent(getContext(), LoginActivity.class);
                    startActivity(intent);
                    getActivity().finish();
                    FinalContents.setDengLu("0");
                    show.dismiss();
                }
            });

        }

    }


    private void init(){
        ZYDataBean userMessageBean = Connector.getZyDataBean();

        data = userMessageBean.getData();

        Glide.with(getActivity()).load(FinalContents.getImageUrl() + data.getPhoto()).into(me_photo);

        me_name.setText(data.getName());

        if (userMessageBean.getData().getIdentity().equals("5")) {
            me_identity.setText("专员");
        }else if(userMessageBean.getData().getIdentity().equals("8")){
            me_identity.setText("经理");
        }else if(userMessageBean.getData().getIdentity().equals("9")){
            me_identity.setText("总监");
        }
        me_city.setText(data.getCity());
//        me_store.setText(data.getStoreManage() + "");
        me_store.setText(data.getCityName());
    }

    @Override
    public void onResume() {
        super.onResume();
        initMyDataStore();
        if (NewlyIncreased.getUserMessage().equals("5") || NewlyIncreased.getUserMessage().equals("8") || NewlyIncreased.getUserMessage().equals("9")){
            init();
            NewlyIncreased.setUserMessage("");
        }

    }

    @Override
    public void onRefresh() {

        if (layout.isRefreshing()) {//如果正在刷新
//            initView();
//            initHotList();
            initUserMessage();
            initMyDataStore();
            layout.setRefreshing(false);//取消刷新
        }

    }
}
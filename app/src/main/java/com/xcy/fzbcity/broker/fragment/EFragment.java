package com.xcy.fzbcity.broker.fragment;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.bumptech.glide.Glide;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.xcy.fzbcity.Login.LoginActivity;
import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.api.Connector;
import com.xcy.fzbcity.all.api.FinalContents;
import com.xcy.fzbcity.all.api.NewlyIncreased;
import com.xcy.fzbcity.all.database.ClientCommissionsBean;
import com.xcy.fzbcity.all.fragment.AllFragment;
import com.xcy.fzbcity.all.modle.UserMessageBean;
import com.xcy.fzbcity.all.persente.CleanDataUtils;
import com.xcy.fzbcity.all.persente.StatusBar;
import com.xcy.fzbcity.all.service.MyService;
import com.xcy.fzbcity.all.view.AboutFZBActivity;
import com.xcy.fzbcity.all.view.CollectActivity;
import com.xcy.fzbcity.all.view.FeedbackActivity;
import com.xcy.fzbcity.all.view.MyBrokerageActivity;
import com.xcy.fzbcity.all.view.MyClientActivity;
import com.xcy.fzbcity.all.view.PersonalInformationActivity;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EFragment extends AllFragment implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener {

    RelativeLayout my_collect;
    RelativeLayout my_comment;
    RelativeLayout my_about;
    RelativeLayout my_empty;
    RelativeLayout my_exit;
    RelativeLayout me_gr;
    LinearLayout me_brokerage;
    LinearLayout me_Client;
    TextView e_client_tv;
    TextView e_commissions_tv;

    ImageView me_photo;
    TextView me_name;
    TextView me_identity;
    TextView me_city;
    TextView me_store;
    TextView me_tv_name;
    TextView me_tv_phone;

    ImageView me_img_phone;

    private Intent intent;
    private TextView my_tv_huancun;

    private SwipeRefreshLayout layout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.broker_modulebroker_fragment_me, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//TODO 设置导航栏、标题栏透明
        StatusBar.makeStatusBarTransparent(getActivity());
        my_tv_huancun = getActivity().findViewById(R.id.my_tv_huancun);
        me_gr = getActivity().findViewById(R.id.me_gr);//个人中心
        me_Client = getActivity().findViewById(R.id.me_Client);//我的客户
        me_brokerage = getActivity().findViewById(R.id.me_Brokerage);//我的佣金
        my_collect = getActivity().findViewById(R.id.my_collect);//我的收藏
        my_comment = getActivity().findViewById(R.id.my_comment);//意见反馈
        my_about = getActivity().findViewById(R.id.my_about);//关于房坐标
        my_empty = getActivity().findViewById(R.id.my_empty);//清空缓存
        my_exit = getActivity().findViewById(R.id.my_exit);//退出登录
        me_img_phone = getActivity().findViewById(R.id.me_img_phone);//拨打手机号

        me_tv_name = getActivity().findViewById(R.id.me_tv_name);//个人中心
        me_tv_phone = getActivity().findViewById(R.id.me_tv_phone);//个人中心

        layout = getActivity().findViewById(R.id.e_ssrfl_1);

        e_client_tv = getActivity().findViewById(R.id.e_client_tv);
        e_commissions_tv = getActivity().findViewById(R.id.e_commissions_tv);

        me_photo = getActivity().findViewById(R.id.me_photo);
        me_name = getActivity().findViewById(R.id.me_name);
        me_identity = getActivity().findViewById(R.id.me_identity);
        me_city = getActivity().findViewById(R.id.me_city);
        me_store = getActivity().findViewById(R.id.me_store);


        try {
            my_tv_huancun.setText(CleanDataUtils.getTotalCacheSize(getActivity()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        me_img_phone.setOnClickListener(this);
        me_gr.setOnClickListener(this);
        me_Client.setOnClickListener(this);
        me_brokerage.setOnClickListener(this);
        my_collect.setOnClickListener(this);
        my_comment.setOnClickListener(this);
        my_about.setOnClickListener(this);
        my_empty.setOnClickListener(this);
        my_exit.setOnClickListener(this);
        layout.setOnRefreshListener(this);
        //        根据用户Id获取用户信息
        initUserMessage();
//        我的佣金和客户数量
        initClientCommissions();
    }

    private void initUserMessage() {

        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<UserMessageBean> userMessage = fzbInterface.getUserMessage(FinalContents.getUserID());
        userMessage.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UserMessageBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(UserMessageBean userMessageBean) {
                        UserMessageBean.DataBean data = userMessageBean.getData();

                        String s = data.getPhoto().toString();
                        StringBuffer stringBuffer = new StringBuffer();
                        stringBuffer.append(s);

                        Glide.with(getActivity()).load(FinalContents.getImageUrl() + data.getPhoto()).into(me_photo);

                        me_name.setText(data.getName());
                        if (data.getIdentity().equals("1") || data.getIdentity().equals("2") || data.getIdentity().equals("3")) {
                            me_identity.setText("经纪人");
                        }
                        me_city.setText(data.getCity());
                        UserMessageBean.DataBean.StoreManageBean storeManage = data.getStoreManage();
//                        me_store.setText(storeManage.getStoreName());
                        me_store.setText(data.getCity() + "  " + data.getCompanyManage().getCompanyName() + "  " + data.getStoreManage().getStoreName());
                        me_tv_name.setText(data.getStoreManageName());
                        me_tv_phone.setText(data.getStoreManagePhone());

                        Connector.setUserMessageBean(userMessageBean);
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

    @Override
    public void onClick(View view) {

        int id = view.getId();
        if (id == R.id.me_gr) {
//            TODO 个人信息
            intent = new Intent(getContext(), PersonalInformationActivity.class);
            startActivity(intent);
        } else if (id == R.id.me_Client) {
//            TODO 我的客户
            intent = new Intent(getContext(), MyClientActivity.class);
            startActivity(intent);
        } else if (id == R.id.me_Brokerage) {
//            TODO 我的佣金
            intent = new Intent(getContext(), MyBrokerageActivity.class);
            startActivity(intent);
        } else if (id == R.id.me_img_phone) {
//            TODO 拨打手机号
            Intent dialIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + me_tv_phone.getText().toString()));//跳转到拨号界面，同时传递电话号码
            startActivity(dialIntent);
        } else if (id == R.id.my_collect) {
//            TODO 我的收藏
//            Log.i("MyCL", "我的收藏");
            intent = new Intent(getContext(), CollectActivity.class);
            startActivity(intent);
        } else if (id == R.id.my_comment) {
//            TODO 意见反馈
//            Log.i("MyCL", "意见反馈");
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
                        Toast.makeText(getActivity(), "清理缓存成功,共清理了" + totalCacheSize + "内存", Toast.LENGTH_SHORT).show();
                        my_tv_huancun.setText("0 M");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Toast.makeText(getActivity(), "取消清理", Toast.LENGTH_SHORT).show();
                }
            });
            AlertDialog show = builder.show();
            show.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.parseColor("#334485"));
            show.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.parseColor("#334485"));
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

//            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
//            builder.setMessage("确定要退出程序吗?");
////            builder.setTitle("退出完成");
//            builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialogInterface, int i) {
//                    FinalContents.setIFSP("1");
//                    intent = new Intent(getContext(), LoginActivity.class);
//                    startActivity(intent);
//                    getActivity().finish();
//                    FinalContents.setDengLu("0");
//                }
//            });
//            AlertDialog show = builder.show();
//            show.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.parseColor("#334485"));

        }

    }

    private void initClientCommissions() {

        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<ClientCommissionsBean> clientCommissions = fzbInterface.getClientCommissions(FinalContents.getUserID() + "");
        clientCommissions.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ClientCommissionsBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ClientCommissionsBean clientCommissionsBean) {
                        ClientCommissionsBean.DataBean data = clientCommissionsBean.getData();
                        e_client_tv.setText(data.getMyCustomerCount() + "");
                        e_commissions_tv.setText(data.getTotalAmount() + "");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("MyCL", "我的佣金、用户数量:" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    private void init() {
        UserMessageBean.DataBean data = Connector.getUserMessageBean().getData();

        Glide.with(getActivity()).load(FinalContents.getImageUrl() + data.getPhoto()).into(me_photo);

        me_name.setText(data.getName());
        if (data.getIdentity().equals("1") || data.getIdentity().equals("2") || data.getIdentity().equals("3")) {
            me_identity.setText("经纪人");
        }
        me_city.setText(data.getCity());
        UserMessageBean.DataBean.StoreManageBean storeManage = data.getStoreManage();
//        me_store.setText(storeManage.getStoreName());
        me_store.setText(data.getCity() + "  " + data.getCompanyManage().getCompanyName() + "  " + data.getStoreManage().getStoreName());
        me_tv_name.setText(data.getStoreManageName());
        me_tv_phone.setText(data.getStoreManagePhone());
    }

    @Override
    public void onResume() {
        super.onResume();
        if (NewlyIncreased.getUserMessage().equals("123")) {
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
            initClientCommissions();
            layout.setRefreshing(false);//取消刷新
        }

    }
}
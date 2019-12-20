package com.xcy.fzbcity.captain_market.fragment;


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

import androidx.annotation.Nullable;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.bumptech.glide.Glide;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.xcy.fzbcity.Login.LoginActivity;
import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.api.Connector;
import com.xcy.fzbcity.all.api.FinalContents;
import com.xcy.fzbcity.all.api.NewlyIncreased;
import com.xcy.fzbcity.all.database.MyDataBean;
import com.xcy.fzbcity.all.fragment.AllFragment;
import com.xcy.fzbcity.all.modle.GWDataBean;
import com.xcy.fzbcity.all.persente.CleanDataUtils;
import com.xcy.fzbcity.all.persente.SingleClick;
import com.xcy.fzbcity.all.persente.StatusBar;
import com.xcy.fzbcity.all.service.MyService;
import com.xcy.fzbcity.all.utils.ToastUtil;
import com.xcy.fzbcity.all.view.AboutFZBActivity;
import com.xcy.fzbcity.all.view.CollectActivity;
import com.xcy.fzbcity.all.view.FeedbackActivity;
import com.xcy.fzbcity.all.view.PersonalInformationActivity;
import com.xcy.fzbcity.captain_market.view.Captain_Market_MyTeamActivity;
import com.xcy.fzbcity.captain_team.view.Captain_Team_CommissionTheProjectEndActivity;
import com.xcy.fzbcity.captain_team.view.Captain_Team_MyClientActivity;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MeFragment extends AllFragment implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener {

    RelativeLayout my_collect;
    RelativeLayout my_comment;
    RelativeLayout my_about;
    RelativeLayout my_empty;
    RelativeLayout my_exit;
    RelativeLayout me_gr;
    LinearLayout me_brokerage;
    LinearLayout me_team;
    LinearLayout me_Client;
    TextView e_commissions_team;
    TextView e_client_tv;
    TextView e_commissions_tv;

    TextView my_tv_1;
    TextView my_tv_name;
    TextView my_tv_phone;

    ImageView me_photo;
    TextView me_name;
    TextView me_identity;
    TextView me_city;
    TextView me_store;

    ImageView me_img_phone;

    private Intent intent;
    private GWDataBean.DataBean data;
    private TextView me_tv_name;
    private TextView me_tv_phone;
    private TextView my_tv_huancun;
    private SwipeRefreshLayout layout;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.captain_team_modulebroker_fragment_me, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//TODO 设置导航栏、标题栏透明
        StatusBar.makeStatusBarTransparent(getActivity());

        me_gr = getActivity().findViewById(R.id.me_gr);//个人中心
        me_Client = getActivity().findViewById(R.id.me_Client);//我的客户
        me_brokerage = getActivity().findViewById(R.id.me_Brokerage);//我的佣金
        me_team = getActivity().findViewById(R.id.me_team);//我的团队
        my_collect = getActivity().findViewById(R.id.my_collect);//我的收藏
        my_comment = getActivity().findViewById(R.id.my_comment);//意见反馈
        my_about = getActivity().findViewById(R.id.my_about);//关于房坐标
        my_empty = getActivity().findViewById(R.id.my_empty);//清空缓存
        my_exit = getActivity().findViewById(R.id.my_exit);//退出登录
        me_img_phone = getActivity().findViewById(R.id.me_img_phone);//拨打手机号
        me_tv_name = getActivity().findViewById(R.id.me_tv_name);
        me_tv_phone = getActivity().findViewById(R.id.me_tv_phone);


        my_tv_1 = getActivity().findViewById(R.id.my_tv_1);
        my_tv_name = getActivity().findViewById(R.id.me_tv_name);
        my_tv_phone = getActivity().findViewById(R.id.me_tv_phone);
        my_tv_1.setText("我的团队长");

        e_client_tv = getActivity().findViewById(R.id.e_client_tv);
        e_commissions_team = getActivity().findViewById(R.id.e_commissions_team);
        e_commissions_tv = getActivity().findViewById(R.id.e_commissions_tv);

        me_photo = getActivity().findViewById(R.id.me_photo);
        me_name = getActivity().findViewById(R.id.me_name);
        me_identity = getActivity().findViewById(R.id.me_identity);
        me_city = getActivity().findViewById(R.id.me_city);
        me_store = getActivity().findViewById(R.id.me_store);
        layout = getActivity().findViewById(R.id.e_ssrfl_5);
        my_tv_huancun = getActivity().findViewById(R.id.my_tv_huancun);
        try {
            my_tv_huancun.setText(CleanDataUtils.getTotalCacheSize(getActivity()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        layout.setOnRefreshListener(this);
        me_img_phone.setOnClickListener(this);
        me_gr.setOnClickListener(this);
        me_Client.setOnClickListener(this);
        me_brokerage.setOnClickListener(this);
        me_team.setOnClickListener(this);
        my_collect.setOnClickListener(this);
        my_comment.setOnClickListener(this);
        my_about.setOnClickListener(this);
        my_empty.setOnClickListener(this);
        my_exit.setOnClickListener(this);

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
        Observable<GWDataBean> userMessage = fzbInterface.getGWDataBean(FinalContents.getUserID(), FinalContents.getUserID());
        userMessage.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GWDataBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(GWDataBean userMessageBean) {
                        data = userMessageBean.getData();

                        String s = data.getSysUser().getPhoto().toString();
                        StringBuffer stringBuffer = new StringBuffer();
                        stringBuffer.append(s);

                        if(data.getSysUser().getPhoto().equals("")){
                            Glide.with(getActivity()).load(FinalContents.getImageUrl() + data.getSysUser().getManager().getPhoto()).into(me_photo);
                        }else {
                            Glide.with(getActivity()).load(FinalContents.getImageUrl() + data.getSysUser().getPhoto()).into(me_photo);
                        }


                        me_name.setText(data.getSysUser().getName());
                        if (data.getSysUser().getIdentity().equals("61")) {
                            me_identity.setText("销售");
                        } else if (data.getSysUser().getIdentity().equals("62")) {
                            me_identity.setText("顾问");
                        } else if (data.getSysUser().getIdentity().equals("63")) {
                            me_identity.setText("团助");
                        }
                        me_city.setText(data.getSysUser().getCity());
                        me_store.setText(data.getSysUser().getCounty());
//                        FinalContents.setUserName(data.getSysUser().getName());
//                        FinalContents.setParentName(data.getLayerTeamVo().getTeamLeader().getParent().getName());
                        Log.i("名字","销售名字："+FinalContents.getUserName());
                        Log.i("名字","团队长名字："+FinalContents.getParentName());
//                        me_tv_name.setText(data.getSysUser().getStoreManage());
//                        me_tv_phone.setText(data.getSysUser().getPhone());

                        my_tv_name.setText(data.getSysUser().getManager().getName());
                        my_tv_phone.setText(data.getSysUser().getManager().getPhone());

                        Connector.setGwDataBean(userMessageBean);

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

        int id = view.getId();
        if (id == R.id.me_gr) {
            intent = new Intent(getContext(), PersonalInformationActivity.class);
            startActivity(intent);
        } else if (id == R.id.me_Client) {
//            TODO 成交量
            FinalContents.setMySelf("1");
            intent = new Intent(getContext(), Captain_Team_MyClientActivity.class);
            intent.putExtra("client","5");
            FinalContents.setQuanceng("1");
            FinalContents.setAgentId(FinalContents.getUserID());
            startActivity(intent);
        }else if (id == R.id.me_team) {
//            TODO 我的团队
            intent = new Intent(getContext(), Captain_Market_MyTeamActivity.class);
            startActivity(intent);
        } else if (id == R.id.me_Brokerage) {
//            TODO 团队佣金
            intent = new Intent(getContext(), Captain_Team_CommissionTheProjectEndActivity.class);
            startActivity(intent);
        } else if (id == R.id.me_img_phone) {
//            TODO 拨打手机号
            Intent dialIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + data.getSysUser().getManager().getPhone()));//跳转到拨号界面，同时传递电话号码
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
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setTitle("确定要退出程序吗?");
            builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    FinalContents.setIFSP("1");
                    FinalContents.setFragmentSS("0");
                    FinalContents.setFragmentSSS("0");
                    intent = new Intent(getContext(), LoginActivity.class);
                    startActivity(intent);
                    getActivity().finish();
                    FinalContents.setDengLu("0");
                }
            });
            AlertDialog show = builder.show();
            show.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.parseColor("#334485"));

        }

    }

    private void initClientCommissions() {

        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<MyDataBean> clientCommissions = fzbInterface.getMyData(FinalContents.getUserID());
        clientCommissions.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MyDataBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(MyDataBean myDataBean) {
                        e_client_tv.setText(myDataBean.getData().getTradeNum());
                        e_commissions_tv.setText(myDataBean.getData().getMyAmount());
                        e_commissions_team.setText(myDataBean.getData().getMyTeamNum()+"");

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

    private void init(){
        GWDataBean userMessageBean = Connector.getGwDataBean();
        data = userMessageBean.getData();

        String s = data.getSysUser().getPhoto().toString();
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(s);

        if(data.getSysUser().getPhoto().equals("")){
            Glide.with(getActivity()).load(FinalContents.getImageUrl() + data.getSysUser().getManager().getPhoto()).into(me_photo);
        }else {
            Glide.with(getActivity()).load(FinalContents.getImageUrl() + data.getSysUser().getPhoto()).into(me_photo);
        }


        me_name.setText(data.getSysUser().getName());
        if (data.getSysUser().getIdentity().equals("61")) {
            me_identity.setText("销售");
        } else if (data.getSysUser().getIdentity().equals("62")) {
            me_identity.setText("顾问");
        } else if (data.getSysUser().getIdentity().equals("63")) {
            me_identity.setText("团助");
        }
        me_city.setText(data.getSysUser().getCity());
        me_store.setText(data.getSysUser().getCounty());
//                        FinalContents.setUserName(data.getSysUser().getName());
//                        FinalContents.setParentName(data.getLayerTeamVo().getTeamLeader().getParent().getName());
        Log.i("名字","销售名字："+FinalContents.getUserName());
        Log.i("名字","团队长名字："+FinalContents.getParentName());
//                        me_tv_name.setText(data.getSysUser().getStoreManage());
//                        me_tv_phone.setText(data.getSysUser().getPhone());

        my_tv_name.setText(data.getSysUser().getManager().getName());
        my_tv_phone.setText(data.getSysUser().getManager().getPhone());
    }

    @Override
    public void onResume() {
        super.onResume();
        if (NewlyIncreased.getUserMessage().equals("61")){
            init();
            NewlyIncreased.setUserMessage("");
        }
    }

    @Override
    public void onRefresh() {


        if (layout.isRefreshing()) {//如果正在刷新
//            initView();
//            initHotList();
//        根据用户Id获取用户信息
            initUserMessage();
//        我的佣金和客户数量
            initClientCommissions();
            layout.setRefreshing(false);//取消刷新
        }


    }
}
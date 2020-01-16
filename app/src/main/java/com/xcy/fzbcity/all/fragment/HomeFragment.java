package com.xcy.fzbcity.all.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.SpannedString;
import android.text.style.AbsoluteSizeSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bumptech.glide.Glide;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.stx.xmarqueeview.XMarqueeView;
import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.adapter.RecyclerAdapter;
import com.xcy.fzbcity.all.adapter.TextBannerAdapter;
import com.xcy.fzbcity.all.adapter.TextBannerAdapter_S;
import com.xcy.fzbcity.all.api.CityContents;
import com.xcy.fzbcity.all.api.FinalContents;
import com.xcy.fzbcity.all.application.DemoApplication;
import com.xcy.fzbcity.all.modle.Bean;
import com.xcy.fzbcity.all.modle.Bean_S;
import com.xcy.fzbcity.all.modle.CityBean;
import com.xcy.fzbcity.all.modle.HotBean;
import com.xcy.fzbcity.all.modle.ImgData;
import com.xcy.fzbcity.all.modle.MessageBean2;
import com.xcy.fzbcity.all.persente.MyLinearLayoutManager;
import com.xcy.fzbcity.all.persente.SharItOff;
import com.xcy.fzbcity.all.persente.SingleClick;
import com.xcy.fzbcity.all.persente.StatusBar;
import com.xcy.fzbcity.all.service.MyService;
import com.xcy.fzbcity.all.utils.ToastUtil;
import com.xcy.fzbcity.all.view.CityWideActivity;
import com.xcy.fzbcity.all.view.MapHouseActivity;
import com.xcy.fzbcity.all.view.MyBrokerageActivity;
import com.xcy.fzbcity.all.view.OverSeaActivity;
import com.xcy.fzbcity.all.view.WebViewActivity;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import top.defaults.view.DateTimePickerView;


@SuppressLint("NewApi")
public class HomeFragment extends AllFragment implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener, SensorEventListener {
    private View view;
    private Banner banner;
    private ArrayList<String> list_path;
    private ArrayList<String> list_title;
    private RecyclerView recyclerView;
    private TextView city;
    //文字轮播数据
    List<Bean> messagelist2 = new ArrayList<>();
    List<Bean_S> messagelist2_S = new ArrayList<>();
    private XMarqueeView tvBanner2_S;

    private XMarqueeView tvBanner2;
    private SwipeRefreshLayout layout;
    private LinearLayout textView1;
    private LinearLayout textView2;
    private LinearLayout textView3;
    private LinearLayout textView4;
    //搜索
    private EditText search;

    private DateTimePickerView dateTimePickerView;


    private String projectType = "1";//房产类型
    private String arrposid = "1"; //新闻类型
    private List<ImgData.DataBean> imglist = new ArrayList<>();
    private List<MessageBean2.DataBean.RowsBean> messagelist = new ArrayList<>();
    private List<CityBean.DataBean> citylist = new ArrayList<>();
    private List<HotBean.DataBean.RowsBean> hotlist = new ArrayList<>();
    private PopupWindow p;
    private SwipeRefreshLayout.OnRefreshListener listener;

    //TODO     摇一摇
    private SensorManager mSensorManager;
    private Vibrator vibrator;
    private DemoApplication application;
    private ImageView all_no_information;
    private ImageView home_banner_no;

    TextView side_message_no;
    private TextBannerAdapter textBannerAdapter;
    private TextBannerAdapter_S textBannerAdapter_s;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.modulebroker_fragment_home, null);
        StatusBar.makeStatusBarTransparent(getActivity());

        fvbId(view);
        mSensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
        vibrator = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);

        initView();

        initHotList();
        tvBanner();
        return view;
    }

    //    TODO 摇一摇start
    @Override
    public void onStop() {
        super.onStop();
        mSensorManager.unregisterListener(this);
    }

    //  TODO 摇一摇
    @SuppressLint("MissingPermission")
    @Override
    public void onSensorChanged(SensorEvent event) {
        if (FinalContents.getCityID().equals(FinalContents.getOldCityId())) {
            if (FinalContents.getCityIs().equals("")) {
                int sensortype = event.sensor.getType();
                float[] values = event.values;
                if (sensortype == Sensor.TYPE_ACCELEROMETER) {
                    /*因为一般正常情况下，任意轴数值最大就在9.8~10之间，只有在你突然摇动手机
                     *的时候，瞬时加速度才会突然增大或减少。
                     *所以，经过实际测试，只需监听任一轴的加速度大于14的时候，改变你需要的设置
                     *就OK了~~~
                     */
                    if (Math.abs(values[0]) > 20 || Math.abs(values[1]) > 20 || Math.abs(values[2]) > 20) {

                        if (SharItOff.getShar().equals("隐")) {
                            SharItOff.setShar("显");
                            ToastUtil.showLongToast(getContext(), "佣金已显示，如需隐藏请摇动");
                        } else if (SharItOff.getShar().equals("显")) {
                            SharItOff.setShar("隐");
                            ToastUtil.showLongToast(getContext(), "佣金已隐藏，如需显示请摇动");
                        }
                        Log.i("MyCL", "摇一摇");

                        initHotList();
                        vibrator.vibrate(100);
                    }
                }
            }
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
//    TODO 摇一摇end

    @Override
    public void onResume() {
        super.onResume();

        tvBanner2.startFlipping();
        city.setText(FinalContents.getCityName());
        //TODO 获取加速传感器
        mSensorManager.registerListener(this, mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onPause() {
        super.onPause();
        tvBanner2.stopFlipping();
        //        TODO 摇一摇
        mSensorManager.unregisterListener(this);
    }

    //命名区域
    private void fvbId(View view) {
        application = (DemoApplication) getActivity().getApplication();


        recyclerView = view.findViewById(R.id.home_recycler_vertical);
        all_no_information = view.findViewById(R.id.all_no_information_h);
        home_banner_no = view.findViewById(R.id.home_banner_no);
        banner = view.findViewById(R.id.home_banner);

        layout = view.findViewById(R.id.home_srl);
        tvBanner2 = view.findViewById(R.id.tv_banner2);
        tvBanner2_S = view.findViewById(R.id.tv_banner2_S);

        textView1 = view.findViewById(R.id.home_item_sojourn);
        textView2 = view.findViewById(R.id.home_item_overseas);
        textView3 = view.findViewById(R.id.home_item_client);
        textView4 = view.findViewById(R.id.home_item_brokerage);
        city = view.findViewById(R.id.home_city_selector);
        side_message_no = view.findViewById(R.id.side_message_no);

        city.setText(FinalContents.getCityName());

        layout.setOnRefreshListener(this);
        search = view.findViewById(R.id.home_search);
        search.setFocusable(false);
        search.setOnClickListener(this);
        city.setOnClickListener(this);

        textView1.setOnClickListener(this);
        textView2.setOnClickListener(this);
        textView3.setOnClickListener(this);
        textView4.setOnClickListener(this);

        // 新建一个可以添加属性的文本对象
        SpannableString ss = new SpannableString("搜索");
        // 新建一个属性对象,设置文字的大小
        AbsoluteSizeSpan ass = new AbsoluteSizeSpan(15, true);
        // 附加属性到文本
        ss.setSpan(ass, 0, ss.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        search.setHint(new SpannedString(ss));
    }

    //点击事件
    @SingleClick(1000)
    @Override
    public void onClick(View view) {
        if (hotlist.size() != 0) {
            if (view.getId() == R.id.home_city_selector) {
                showPickerView();
            } else if (view.getId() == R.id.home_search) {
//                Intent intent = new Intent(view.getContext(), SearchInterfaceActivity.class);
//                startActivity(intent);
                Intent intent = new Intent(view.getContext(), OverSeaActivity.class);
                startActivity(intent);
            } else if (view.getId() == R.id.home_item_sojourn) {

//                FinalContents.setProjectType("3");
//                Intent intent_sojourn = new Intent(view.getContext(), OverSeaActivity.class);
//                startActivity(intent_sojourn);

                CityContents.setCityType("2");
                FinalContents.setIfCityType("2");
                Intent intent = new Intent(view.getContext(), CityWideActivity.class);
                startActivity(intent);

            } else if (view.getId() == R.id.home_item_overseas) {
//                FinalContents.setProjectType("2");
//                Intent intent_overseas = new Intent(view.getContext(), OverSeaActivity.class);
//                startActivity(intent_overseas);

                FinalContents.setIfCity(FinalContents.getCityID());
                FinalContents.setIfCityType("");
                Intent intent_overseas = new Intent(view.getContext(), MapHouseActivity.class);
                startActivity(intent_overseas);

            } else if (view.getId() == R.id.home_item_client) {
//                FinalContents.setProjectType("1");
//                Intent intent_overseas = new Intent(view.getContext(), OverSeaActivity.class);
//                startActivity(intent_overseas);

                CityContents.setCityType("1");
                FinalContents.setIfCityType("1");
                Intent intent = new Intent(view.getContext(), CityWideActivity.class);
                startActivity(intent);


            } else if (view.getId() == R.id.home_item_brokerage) {
//                Intent intent_overseas = new Intent(view.getContext(), MyClientActivity.class);
//                startActivity(intent_overseas);

                Intent intent = new Intent(getContext(), MyBrokerageActivity.class);
                startActivity(intent);
            }
        } else {
            if (view.getId() == R.id.home_city_selector) {
                showPickerView();
            }
        }
    }

    /**
     * 展示选择器
     * 核心代码
     */
    private void showPickerView() {
//      要展示的数据
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<CityBean> userMessage = fzbInterface.getCityList();
        userMessage.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CityBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(CityBean cityBean) {
                        citylist = cityBean.getData();
                        final List<String> list = new ArrayList<>();
                        for (int i = 0; i < citylist.size(); i++) {
                            list.add(citylist.get(i).getCity());
                        }
                        //      监听选中
                        OptionsPickerView pvOptions = new OptionsPickerBuilder(view.getContext(), new OnOptionsSelectListener() {
                            @Override
                            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                                //               返回的分别是三个级别的选中位置
                                //              展示选中数据
                                if (!citylist.get(options1).getId().equals(FinalContents.getOldCityId())) {
                                    FinalContents.setCityIs("不是当前城市");
                                    SharItOff.setShar("隐");
                                } else {
                                    FinalContents.setCityIs("");
                                }
                                city.setText(list.get(options1));
                                FinalContents.setCityName(list.get(options1));
                                FinalContents.setCityID(citylist.get(options1).getId());
                                Log.i("city", FinalContents.getCityID());

                                initHotList();
                            }
                        })
                                .setSelectOptions(2)//设置选择第一个
                                .setOutSideCancelable(false)//点击背的地方不消失
                                .build();//创建
                        //      把数据绑定到控件上面
                        pvOptions.setPicker(list);
                        //      展示
                        pvOptions.show();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("城市列表", "获取：" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    private void initHotList() {
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<HotBean> userMessage = fzbInterface.getHotList(FinalContents.getUserID(), FinalContents.getCityID(), "1", "1000");
        userMessage.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HotBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @SuppressLint("WrongConstant")
                    @Override
                    public void onNext(HotBean hotBean) {
                        Log.i("hotBean", hotBean.getMsg());
                        if (hotBean.getCode().equals("1")) {
                            HotBean.DataBean hotBeanData = hotBean.getData();
                            hotlist = hotBeanData.getRows();
                            if (hotlist.size() != 0) {
                                all_no_information.setVisibility(View.GONE);
                                //在此处修改布局排列方向
                                recyclerView.setVisibility(View.VISIBLE);
                                MyLinearLayoutManager layoutManager = new MyLinearLayoutManager(view.getContext());
                                layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                                layoutManager.setScrollEnabled(false);
                                recyclerView.setLayoutManager(layoutManager);
                                FinalContents.setZyHome("");
                                RecyclerAdapter recyclerAdapter = new RecyclerAdapter(hotlist);
                                recyclerView.setNestedScrollingEnabled(false);
                                recyclerView.setAdapter(recyclerAdapter);
                                recyclerAdapter.notifyDataSetChanged();
                            } else {
                                all_no_information.setVisibility(View.VISIBLE);
                                recyclerView.setVisibility(View.GONE);
                            }
                        } else {
                            recyclerView.setVisibility(View.GONE);
                            all_no_information.setVisibility(View.VISIBLE);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        recyclerView.setVisibility(View.GONE);
                        all_no_information.setVisibility(View.VISIBLE);
                        Log.i("列表数据获取错误", "错误" + e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    //文字轮播
    private void tvBanner() {
        messagelist2.clear();
        messagelist2_S.clear();
        Log.i("文字轮播", "文字轮播1");
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<MessageBean2> userMessage = fzbInterface.getMessageTextList(FinalContents.getUserID(), FinalContents.getCityID(), "");
        userMessage.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MessageBean2>() {

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @SuppressLint("WrongConstant")
                    @Override
                    public void onNext(MessageBean2 messageBean) {
                        MessageBean2.DataBean dataBean = messageBean.getData();
                        messagelist = dataBean.getRows();
                        if (messagelist.size() == 0) {
                            tvBanner2.setVisibility(View.VISIBLE);
                            tvBanner2_S.setVisibility(View.VISIBLE);
                            side_message_no.setVisibility(View.GONE);

                            messagelist2.add(new Bean(R.mipmap.no_information, "暂无数据"));
                            messagelist2.add(new Bean(R.mipmap.no_information, "暂无数据"));
                            messagelist2.add(new Bean(R.mipmap.no_information, "暂无数据"));

                            textBannerAdapter = new TextBannerAdapter(messagelist2, view.getContext());
                            tvBanner2.setAdapter(textBannerAdapter);

                            //TODO 第二行

                            messagelist2_S.add(new Bean_S(R.mipmap.no_information, "暂无数据"));
                            messagelist2_S.add(new Bean_S(R.mipmap.no_information, "暂无数据"));
                            messagelist2_S.add(new Bean_S(R.mipmap.no_information, "暂无数据"));

                            textBannerAdapter_s = new TextBannerAdapter_S(messagelist2_S, view.getContext());
                            tvBanner2_S.setAdapter(textBannerAdapter_s);


                        } else if (messagelist.size() == 1) {

                            Log.i("文字轮播","messagelist.size() == 1");

                            tvBanner2.setVisibility(View.VISIBLE);
                            tvBanner2_S.setVisibility(View.INVISIBLE);
                            side_message_no.setVisibility(View.GONE);
                            tvBanner2.setFlipInterval(500000000);
                            for (int i = 0; i < messagelist.size(); i++) {
                                if (messagelist.get(i).getType().equals("0")) {
                                    messagelist2.add(new Bean(R.mipmap.give, messagelist.get(i).getTitle()));
                                } else if (messagelist.get(i).getType().equals("2")) {
                                    messagelist2.add(new Bean(R.mipmap.lodger, messagelist.get(i).getTitle()));
                                } else if (messagelist.get(i).getType().equals("5")) {
                                    messagelist2.add(new Bean(R.mipmap.goodnews, messagelist.get(i).getTitle()));
                                }
                            }
                            textBannerAdapter = new TextBannerAdapter(messagelist2, view.getContext());
                            tvBanner2.setAdapter(textBannerAdapter);
                            textBannerAdapter.setOnItemClickListener(new TextBannerAdapter.OnItemClickLisenter() {
                                @Override
                                public void onItemClick(int postion) {
                                    Log.i("文字轮播","点击执行回调textBannerAdapter：" + messagelist.get(postion).getType());
                                    if (messagelist.get(postion).getType().equals("0")) {
                                        listterner.process("0"); // 3.1 执行回调
                                    } else if (messagelist.get(postion).getType().equals("2")) {
                                        listterner.process("2"); // 3.1 执行回调
                                    } else if (messagelist.get(postion).getType().equals("5")) {
                                        listterner.process("5"); // 3.1 执行回调
                                    }
                                }
                            });
                            //TODO 第二行
                            int numSize = 0;
                            for (int i = 1; i < messagelist.size(); ++i) {
                                if (messagelist.get(i).getType().equals("0")) {
                                    messagelist2_S.add(new Bean_S(R.mipmap.give, messagelist.get(i).getTitle()));
                                } else if (messagelist.get(i).getType().equals("2")) {
                                    messagelist2_S.add(new Bean_S(R.mipmap.lodger, messagelist.get(i).getTitle()));
                                } else if (messagelist.get(i).getType().equals("5")) {
                                    messagelist2_S.add(new Bean_S(R.mipmap.goodnews, messagelist.get(i).getTitle()));
                                }
                                numSize = i;
                            }
//                            if(numSize == messagelist.size()){
                            if (messagelist.get(0).getType().equals("0")) {
                                messagelist2_S.add(new Bean_S(R.mipmap.give, messagelist.get(0).getTitle()));
                            } else if (messagelist.get(0).getType().equals("2")) {
                                messagelist2_S.add(new Bean_S(R.mipmap.lodger, messagelist.get(0).getTitle()));
                            } else if (messagelist.get(0).getType().equals("5")) {
                                messagelist2_S.add(new Bean_S(R.mipmap.goodnews, messagelist.get(0).getTitle()));
                            }
//                            }
                            textBannerAdapter_s = new TextBannerAdapter_S(messagelist2_S, view.getContext());
                            tvBanner2_S.setAdapter(textBannerAdapter_s);
                            tvBanner2_S.setVisibility(View.INVISIBLE);
                            textBannerAdapter_s.setOnItemClickListener(new TextBannerAdapter_S.OnItemClickLisenter() {
                                @Override
                                public void onItemClick(int postion) {
                                    Log.i("文字轮播","点击执行回调textBannerAdapter_s：" + messagelist.get(postion).getType());
                                    if (messagelist.get(postion).getType().equals("0")) {
                                        listterner.process("0"); // 3.1 执行回调
                                    } else if (messagelist.get(postion).getType().equals("2")) {
                                        listterner.process("2"); // 3.1 执行回调
                                    } else if (messagelist.get(postion).getType().equals("5")) {
                                        listterner.process("5"); // 3.1 执行回调
                                    }
                                }
                            });
                        } else {
                            Log.i("文字轮播","else");

                            tvBanner2.setVisibility(View.VISIBLE);
                            tvBanner2_S.setVisibility(View.VISIBLE);
                            side_message_no.setVisibility(View.GONE);
                            if(messagelist.size() == 2){
                                tvBanner2.setFlipInterval(500000000);tvBanner2_S.setFlipInterval(500000000);
                            }else {

                            }
                            //TODO 第一行
                            for (int i = 0; i < messagelist.size(); i++) {
                                if (messagelist.get(i).getType().equals("0")) {
                                    messagelist2.add(new Bean(R.mipmap.give, messagelist.get(i).getTitle()));
                                } else if (messagelist.get(i).getType().equals("2")) {
                                    messagelist2.add(new Bean(R.mipmap.lodger, messagelist.get(i).getTitle()));
                                } else if (messagelist.get(i).getType().equals("5")) {
                                    messagelist2.add(new Bean(R.mipmap.goodnews, messagelist.get(i).getTitle()));
                                }
                            }
                            textBannerAdapter = new TextBannerAdapter(messagelist2, view.getContext());
                            tvBanner2.setAdapter(textBannerAdapter);
                            textBannerAdapter.setOnItemClickListener(new TextBannerAdapter.OnItemClickLisenter() {
                                @Override
                                public void onItemClick(int postion) {
                                    Log.i("文字轮播","点击执行回调textBannerAdapter：" + messagelist.get(postion).getType());
                                    if (messagelist.get(postion).getType().equals("0")) {
                                        listterner.process("0"); // 3.1 执行回调
                                    } else if (messagelist.get(postion).getType().equals("2")) {
                                        listterner.process("2"); // 3.1 执行回调
                                    } else if (messagelist.get(postion).getType().equals("5")) {
                                        listterner.process("5"); // 3.1 执行回调
                                    }
                                }
                            });

                            for (int i = 0; i < messagelist2.size(); ++i){
                                Log.i("文字轮播","第一行：" + messagelist2.get(i).getName());
                            }

                            //TODO 第二行
                            int numSize = 0;
                            for (int i = 1; i < messagelist.size(); ++i) {
                                if (messagelist.get(i).getType().equals("0")) {
                                    messagelist2_S.add(new Bean_S(R.mipmap.give, messagelist.get(i).getTitle()));
                                } else if (messagelist.get(i).getType().equals("2")) {
                                    messagelist2_S.add(new Bean_S(R.mipmap.lodger, messagelist.get(i).getTitle()));
                                } else if (messagelist.get(i).getType().equals("5")) {
                                    messagelist2_S.add(new Bean_S(R.mipmap.goodnews, messagelist.get(i).getTitle()));
                                }
                                numSize = i;
                            }
//                            if(numSize == messagelist.size()){
                            if (messagelist.get(0).getType().equals("0")) {
                                messagelist2_S.add(new Bean_S(R.mipmap.give, messagelist.get(0).getTitle()));
                            } else if (messagelist.get(0).getType().equals("2")) {
                                messagelist2_S.add(new Bean_S(R.mipmap.lodger, messagelist.get(0).getTitle()));
                            } else if (messagelist.get(0).getType().equals("5")) {
                                messagelist2_S.add(new Bean_S(R.mipmap.goodnews, messagelist.get(0).getTitle()));
                            }
//                            }
                            textBannerAdapter_s = new TextBannerAdapter_S(messagelist2_S, view.getContext());
                            tvBanner2_S.setAdapter(textBannerAdapter_s);
                            textBannerAdapter_s.setOnItemClickListener(new TextBannerAdapter_S.OnItemClickLisenter() {
                                @Override
                                public void onItemClick(int postion) {
                                    Log.i("文字轮播","点击执行回调textBannerAdapter_s：" + messagelist.get(postion).getType());
                                    if (messagelist.get(postion).getType().equals("0")) {
                                        listterner.process("0"); // 3.1 执行回调
                                    } else if (messagelist.get(postion).getType().equals("2")) {
                                        listterner.process("2"); // 3.1 执行回调
                                    } else if (messagelist.get(postion).getType().equals("5")) {
                                        listterner.process("5"); // 3.1 执行回调
                                    }
                                }
                            });
                            Log.i("文字轮播","*********************************************************************");
                            for (int i = 0; i < messagelist2_S.size(); ++i){
                                Log.i("文字轮播","第二行：" + messagelist2_S.get(i).getName());
                            }
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        tvBanner2.setVisibility(View.GONE);
                        tvBanner2_S.setVisibility(View.GONE);
                        side_message_no.setVisibility(View.VISIBLE);
                        Log.i("列表数据获取错误", "错误" + e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    //文字轮播
    private void tvBanner2() {
        tvBanner2.stopFlipping();
        tvBanner2_S.stopFlipping();
        messagelist2 = new ArrayList<>();
        messagelist2_S = new ArrayList<>();
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<MessageBean2> userMessage = fzbInterface.getMessageTextList(FinalContents.getUserID(), FinalContents.getCityID(), "");
        userMessage.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MessageBean2>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @SuppressLint("WrongConstant")
                    @Override
                    public void onNext(MessageBean2 messageBean) {
                        MessageBean2.DataBean dataBean = messageBean.getData();
                        messagelist = dataBean.getRows();
                        if (messagelist.size() == 0) {
                            messagelist2.add(new Bean(R.mipmap.no_information, "暂无数据"));
                            messagelist2.add(new Bean(R.mipmap.no_information, "暂无数据"));
                            messagelist2.add(new Bean(R.mipmap.no_information, "暂无数据"));
                            messagelist2_S.add(new Bean_S(R.mipmap.no_information, "暂无数据"));
                            messagelist2_S.add(new Bean_S(R.mipmap.no_information, "暂无数据"));
                            messagelist2_S.add(new Bean_S(R.mipmap.no_information, "暂无数据"));
                        } else if (messagelist.size() == 1) {
                            //TODO 第一行
                            for (int i = 0; i < messagelist.size(); i++) {
                                if (messagelist.get(i).getType().equals("0")) {
                                    messagelist2.add(new Bean(R.mipmap.give, messagelist.get(i).getTitle()));
                                } else if (messagelist.get(i).getType().equals("2")) {
                                    messagelist2.add(new Bean(R.mipmap.lodger, messagelist.get(i).getTitle()));
                                } else if (messagelist.get(i).getType().equals("5")) {
                                    messagelist2.add(new Bean(R.mipmap.goodnews, messagelist.get(i).getTitle()));
                                }
                            }
                            int numSize = 0;
                            for (int i = 1; i < messagelist.size(); ++i) {
                                if (messagelist.get(i).getType().equals("0")) {
                                    messagelist2_S.add(new Bean_S(R.mipmap.give, messagelist.get(i).getTitle()));
                                } else if (messagelist.get(i).getType().equals("2")) {
                                    messagelist2_S.add(new Bean_S(R.mipmap.lodger, messagelist.get(i).getTitle()));
                                } else if (messagelist.get(i).getType().equals("5")) {
                                    messagelist2_S.add(new Bean_S(R.mipmap.goodnews, messagelist.get(i).getTitle()));
                                }
                                numSize = i;
                            }
                            tvBanner2.setFlipInterval(500000000);
                            tvBanner2_S.setFlipInterval(500000000);
                        } else {
                            //TODO 第一行
                            for (int i = 0; i < messagelist.size(); i++) {
                                if (messagelist.get(i).getType().equals("0")) {
                                    messagelist2.add(new Bean(R.mipmap.give, messagelist.get(i).getTitle()));
                                } else if (messagelist.get(i).getType().equals("2")) {
                                    messagelist2.add(new Bean(R.mipmap.lodger, messagelist.get(i).getTitle()));
                                } else if (messagelist.get(i).getType().equals("5")) {
                                    messagelist2.add(new Bean(R.mipmap.goodnews, messagelist.get(i).getTitle()));
                                }
                            }
                            int numSize = 0;
                            for (int i = 1; i < messagelist.size(); ++i) {
                                if (messagelist.get(i).getType().equals("0")) {
                                    messagelist2_S.add(new Bean_S(R.mipmap.give, messagelist.get(i).getTitle()));
                                } else if (messagelist.get(i).getType().equals("2")) {
                                    messagelist2_S.add(new Bean_S(R.mipmap.lodger, messagelist.get(i).getTitle()));
                                } else if (messagelist.get(i).getType().equals("5")) {
                                    messagelist2_S.add(new Bean_S(R.mipmap.goodnews, messagelist.get(i).getTitle()));
                                }
                                numSize = i;
                            }
                        }

                        if (messagelist.size() > 1){
                            tvBanner2_S.setVisibility(View.VISIBLE);
                        }
                        textBannerAdapter.setData(messagelist2);
                        textBannerAdapter_s.setData(messagelist2_S);
                        textBannerAdapter.setOnItemClickListener(new TextBannerAdapter.OnItemClickLisenter() {
                            @Override
                            public void onItemClick(int postion) {
                                if (messagelist.get(postion).getType().equals("0")) {
                                    listterner.process("0"); // 3.1 执行回调
                                } else if (messagelist.get(postion).getType().equals("2")) {
                                    listterner.process("2"); // 3.1 执行回调
                                } else if (messagelist.get(postion).getType().equals("5")) {
                                    listterner.process("5"); // 3.1 执行回调
                                }
                            }
                        });
                        textBannerAdapter_s.setOnItemClickListener(new TextBannerAdapter_S.OnItemClickLisenter() {
                            @Override
                            public void onItemClick(int postion) {
                                if (messagelist.get(postion+1).getType().equals("0")) {
                                    listterner.process("0"); // 3.1 执行回调
                                } else if (messagelist.get(postion+1).getType().equals("2")) {
                                    listterner.process("2"); // 3.1 执行回调
                                } else if (messagelist.get(postion+1).getType().equals("5")) {
                                    listterner.process("5"); // 3.1 执行回调
                                }
                            }
                        });
                    }

                    @Override
                    public void onError(Throwable e) {
                        tvBanner2.setVisibility(View.GONE);
                        tvBanner2_S.setVisibility(View.GONE);
                        side_message_no.setVisibility(View.VISIBLE);
                        Log.i("列表数据获取错误", "错误" + e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    //首页轮播图
    private void initView() {
        list_path = new ArrayList<>();
        list_title = new ArrayList<>();
        list_path.clear();
        list_title.clear();

        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<ImgData> userMessage = fzbInterface.getBannerList(FinalContents.getUserID(), FinalContents.getCityID(), "1", arrposid);
        userMessage.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ImgData>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @SuppressLint("WrongConstant")
                    @Override
                    public void onNext(ImgData imgData) {
                        imglist = imgData.getData();
                        if (imglist.size() != 0) {
                            banner.setVisibility(View.VISIBLE);
                            home_banner_no.setVisibility(View.GONE);
                            for (int i = 0; i < imglist.size(); i++) {
                                list_path.add(FinalContents.getImageUrl() + imglist.get(i).getCoverImg());
                                list_title.add(imglist.get(i).getTitle());
                            }

                            //设置banner样式
                            banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
                            //设置图片加载器
                            banner.setImageLoader(new MyLoader());
                            //设置图片集合
                            banner.setImages(list_path);
                            //设置banner动画效果
                            banner.setBannerAnimation(Transformer.Default);
                            //设置标题集合（当banner样式有显示title时）
                            banner.setBannerTitles(list_title);
                            //设置自动轮播，默认为true
                            banner.isAutoPlay(true);
                            //设置轮播时间
                            banner.setDelayTime(3000);
                            //设置指示器位置（当banner模式中有指示器时）
                            banner.setIndicatorGravity(BannerConfig.CENTER);
                            //banner设置方法全部调用完毕时最后调用
                            banner.setOnBannerListener(new OnBannerListener() {
                                @Override
                                public void OnBannerClick(int position) {
                                    FinalContents.setProjectID(imglist.get(position).getProject().getId());
                                    FinalContents.setNewID(imglist.get(position).getId());
                                    Log.i("详情", "项目ID" + FinalContents.getProjectID());
                                    Log.i("详情", "用户ID" + FinalContents.getUserID());
                                    Log.i("详情", "用户ID" + FinalContents.getNewID());
                                    Intent intent = new Intent(view.getContext(), WebViewActivity.class);
                                    intent.putExtra("title", "新闻详情");
                                    intent.putExtra("webview", imglist.get(position).getContent());
                                    startActivity(intent);
                                }
                            });
                            banner.start();


                        } else {
                            banner.setVisibility(View.GONE);
                            home_banner_no.setVisibility(View.VISIBLE);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        banner.setVisibility(View.GONE);
                        home_banner_no.setVisibility(View.VISIBLE);
                        Log.i("列表数据获取错误", "错误" + e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void onRefresh() {
        if (layout.isRefreshing()) {//如果正在刷新
            initView();
            initHotList();
            tvBanner2();
            layout.setRefreshing(false);//取消刷新
        }
    }

    /**
     * 网络加载图片
     * 使用了Glide图片加载框架
     */
    private class MyLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context.getApplicationContext())
                    .load(path)
                    .into(imageView);
        }


    }

    // 2.1 定义用来与外部activity交互，获取到宿主activity
    private FragmentInteraction listterner;

    // 1 定义了所有activity必须实现的接口方法
    public interface FragmentInteraction {
        void process(String str);
    }

    // 当FRagmen被加载到activity的时候会被回调
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        if (activity instanceof FragmentInteraction) {
            listterner = (FragmentInteraction) activity; // 2.2 获取到宿主activity并赋值
        } else {
            throw new IllegalArgumentException("activity must implements FragmentInteraction");
        }
    }

    //把传递进来的activity对象释放掉
    @Override
    public void onDetach() {
        super.onDetach();
        listterner = null;
    }
}

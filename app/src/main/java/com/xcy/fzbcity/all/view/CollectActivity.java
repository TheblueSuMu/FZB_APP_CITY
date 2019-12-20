package com.xcy.fzbcity.all.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.adapter.RecyclerSAdapter;
import com.xcy.fzbcity.all.api.FinalContents;
import com.xcy.fzbcity.all.application.DemoApplication;
import com.xcy.fzbcity.all.modle.HotBean;
import com.xcy.fzbcity.all.persente.SharItOff;
import com.xcy.fzbcity.all.persente.SingleClick;
import com.xcy.fzbcity.all.persente.StatusBar;
import com.xcy.fzbcity.all.service.MyService;
import com.xcy.fzbcity.all.utils.CommonUtil;
import com.xcy.fzbcity.all.utils.ToastUtil;

import java.util.List;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CollectActivity extends AllActivity implements View.OnClickListener , SensorEventListener {

    PtrClassicFrameLayout ptrClassicFrameLayout;

    private RelativeLayout collect_img;
    private LinearLayout collect_l1;
    private LinearLayout collect_l2;
    private LinearLayout collect_l3;
    private LinearLayout collect_ll3;
    private LinearLayout collect_ll1;
    private LinearLayout collect_ll2;


    private RecyclerView recyclerView;
    private ImageView all_no_information;

    //TODO     摇一摇
    private SensorManager mSensorManager;
    private Vibrator vibrator;
    private DemoApplication application;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collect);
        init_No_Network();
    }

    private void init_No_Network(){
        boolean networkAvailable = CommonUtil.isNetworkAvailable(this);
        if (networkAvailable) {
            mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
            vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);


            application = (DemoApplication) getApplication();
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
            ToastUtil.showToast(this,"当前无网络，请检查网络后再进行登录");
        }
    }

    private void initView() {

        StatusBar.makeStatusBarTransparent(this);



        collect_img = findViewById(R.id.collect_img);
        collect_l1 = findViewById(R.id.collect_l1);
        collect_l2 = findViewById(R.id.collect_l2);
        collect_l3 = findViewById(R.id.collect_l3);
        collect_ll3 = findViewById(R.id.collect_ll3);
        collect_ll1 = findViewById(R.id.collect_ll1);
        collect_ll2 = findViewById(R.id.collect_ll2);

        recyclerView = findViewById(R.id.collect_recyler);
        all_no_information = findViewById(R.id.all_no_information);

        collect_img.setOnClickListener(this);
        collect_l1.setOnClickListener(this);
        collect_l2.setOnClickListener(this);
        collect_l3.setOnClickListener(this);


        ptrClassicFrameLayout = findViewById(R.id.store_house_ptr_frame_9);
        ptrClassicFrameLayout.setPtrHandler(new PtrHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                frame.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ptrClassicFrameLayout.refreshComplete();
                        ptrClassicFrameLayout.setLastUpdateTimeKey("2017-2-10");
                        if(collect_ll1.getVisibility() == View.VISIBLE){
                            recyclerViewData("3");
                        }else if(collect_ll2.getVisibility() == View.VISIBLE){
                            recyclerViewData("2");
                        }else if(collect_ll3.getVisibility() == View.VISIBLE){
                            recyclerViewData("1");
                        }
                    }
                }, 1000);
            }

            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
            }
        });

        recyclerViewData("1");

    }

    @SuppressLint("WrongConstant")
    private void recyclerViewData(String projectType ) {

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<HotBean> hotBean1 = fzbInterface.getMyCollection(FinalContents.getUserID(), projectType);
        hotBean1.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HotBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(HotBean hotBean) {

                        HotBean.DataBean hotBeanData = hotBean.getData();
                        List<HotBean.DataBean.RowsBean> hotlist = hotBeanData.getRows();
                        if (hotlist.size() != 0){
                            all_no_information.setVisibility(View.GONE);
                            recyclerView.setVisibility(View.VISIBLE);
                            RecyclerSAdapter recyclerAdapter = new RecyclerSAdapter(hotlist);
                            recyclerView.setAdapter(recyclerAdapter);
                            recyclerAdapter.notifyDataSetChanged();
                        }else {
                            all_no_information.setVisibility(View.VISIBLE);
                            recyclerView.setVisibility(View.GONE);
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        all_no_information.setVisibility(View.VISIBLE);
                        recyclerView.setVisibility(View.GONE);
                        Log.i("MyCL", "CollectActivity错误信息：" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @SingleClick(1000)
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.collect_img:
                finish();
                break;
            case R.id.collect_l1:
                collect_ll1.setVisibility(View.VISIBLE);
                collect_ll2.setVisibility(View.INVISIBLE);
                collect_ll3.setVisibility(View.INVISIBLE);
                recyclerViewData("3");
                break;
            case R.id.collect_l2:
                collect_ll1.setVisibility(View.INVISIBLE);
                collect_ll2.setVisibility(View.VISIBLE);
                collect_ll3.setVisibility(View.INVISIBLE);
                recyclerViewData("2");
                break;
            case R.id.collect_l3:
                collect_ll1.setVisibility(View.INVISIBLE);
                collect_ll2.setVisibility(View.INVISIBLE);
                collect_ll3.setVisibility(View.VISIBLE);
                recyclerViewData("1");
                break;
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if(collect_ll1.getVisibility() == View.VISIBLE){
            recyclerViewData("3");
        }else if(collect_ll2.getVisibility() == View.VISIBLE){
            recyclerViewData("2");
        }else if(collect_ll3.getVisibility() == View.VISIBLE){
            recyclerViewData("1");
        }
    }



    @Override
    public void onSensorChanged(SensorEvent event) {
        if (FinalContents.getCityID().equals(FinalContents.getOldCityId())) {
            if(FinalContents.getIdentity().equals("63")){

            }else if (FinalContents.getIdentity().equals("7")) {

            }else {
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
                            ToastUtil.showToast(this,"佣金已显示，如需隐藏请摇动");
                        } else if (SharItOff.getShar().equals("显")) {
                            SharItOff.setShar("隐");
                            ToastUtil.showToast(this,"佣金已隐藏，如需显示请摇动");
                        }
                        if(collect_ll1.getVisibility() == View.VISIBLE){
                            recyclerViewData("3");
                        }else if(collect_ll2.getVisibility() == View.VISIBLE){
                            recyclerViewData("2");
                        }else if(collect_ll3.getVisibility() == View.VISIBLE){
                            recyclerViewData("1");
                        }


                        vibrator.vibrate(100);
                    }
                }
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mSensorManager.unregisterListener(this);
    }
}

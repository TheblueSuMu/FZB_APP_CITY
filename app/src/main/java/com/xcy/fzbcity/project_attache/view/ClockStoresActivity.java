package com.xcy.fzbcity.project_attache.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.utils.DistanceUtil;
import com.bumptech.glide.Glide;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.adapter.GridViewAdapter;
import com.xcy.fzbcity.all.api.FinalContents;
import com.xcy.fzbcity.all.modle.AddPhotoBean;
import com.xcy.fzbcity.all.modle.ClockInBean;
import com.xcy.fzbcity.all.modle.RecordBean;
import com.xcy.fzbcity.all.persente.SingleClick;
import com.xcy.fzbcity.all.persente.StatusBar;
import com.xcy.fzbcity.all.service.MyService;
import com.xcy.fzbcity.all.utils.SourcePanel;
import com.xcy.fzbcity.all.utils.ToastUtil;
import com.xcy.fzbcity.all.view.BigPhotoActivity;
import com.xcy.fzbcity.project_attache.adapter.GridViewSAdapter;
import com.xcy.fzbcity.project_attache.adapter.RecordAdapter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

//TODO 门店打卡
public class ClockStoresActivity extends AppCompatActivity implements View.OnClickListener {

    RelativeLayout store_details_return;
    ImageView store_details_img;
    ImageView store_details_img_btn;
    TextView store_details_title;
    TextView store_details_check;
    RecyclerView store_details_rv;
    LinearLayout store_details_linear1;
    LinearLayout store_details_linear2;
    ImageView store_details_img_rf;
    TextView store_details_tv_start;
    TextView store_details_check_S;
    /**
     * 地图
     */
    // 定位相关
    LocationClient mLocClient;
    //定位监听
    public MyLocationListenner myListener = new MyLocationListenner();
    boolean isFirstLoc = true; // 是否首次定位
    BDLocation mlocation;
    private LatLng ll;
    /**
     * num判断按钮状态
     * 0 初始状态
     * 1 未开启定位权限
     * 2 已开启定位权限
     * 3 在范围内
     * 4 不在范围内
     */
    int num = 0;
    int ifnum = 0;
    /**
     * GridView + 相机拍照
     */
    SourcePanel confirm_the_visit_gv;
    private GridViewSAdapter adapter;
    private List<Object> mDatas;
    int isPhoto = 0;
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            "android.permission.READ_EXTERNAL_STORAGE",
            "android.permission.WRITE_EXTERNAL_STORAGE"};
    private File file;
    StringBuffer stringBuffer = new StringBuffer();
    private View inflate;
    private PopupWindow popupWindow;
    private String myStoreRise;
    private String myStoreName;
    private String myLocation;
    private String mylatitude;
    private String mylongitude;
    private String myStoreId;
    private RecordBean.DataBean data;
    private int ifClock = 0;
    private String ifType = "";
    private RecordAdapter adapter1;

    private int IfNum = 0;

    private int isOne = 0;

    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {

            // 回传的是此item的bean,对当前bean中的数据能够进行操作,位置传过来之后还能将数据进行操作
            int position = msg.getData().getInt(GridViewAdapter.POSITION);

            switch (msg.what) {
                case GridViewAdapter.DELETE:
                    Log.i("MyCL", "下标：" + position);
                    ArrayList<String> listImage = new ArrayList<>();
                    final String[] a = stringBuffer.toString().split("[|]");
                    for (int i = 0; i < a.length; i++) {
                        listImage.add(a[i]);
                    }
                    listImage.remove(position);
                    stringBuffer.delete(0, stringBuffer.length());
                    mDatas.remove(position);
                    adapter.notifyDataSetChanged();
                    for (int i = 0; i < mDatas.size(); ++i) {
                        if (i == 0) {
                            stringBuffer.append(mDatas.get(i));
                        } else {
                            stringBuffer.append("|" + mDatas.get(i));
                        }
                    }
                    Log.i("MyCL", "stringBuffer：" + stringBuffer.toString());
                    Log.i("MyCL", "mDatas：" + mDatas.toString());
                    break;

                default:
                    break;
            }
        }

        ;
    };
    private String addrStr;
    private String s;
    private Timer timer1;
    private TimerTask timerTask;

    TextView store_details_img_btn_tv1;
    TextView store_details_img_btn_tv2;
    private int min;
    private int hour;
    private int second;
    private long l;
    private long timeStamp;
    private long createTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clock_stores);

        initView();
    }

    private void initView() {

        StatusBar.makeStatusBarTransparent(this);

        store_details_linear1 = findViewById(R.id.store_details_linear1);
        store_details_linear2 = findViewById(R.id.store_details_linear2);
        store_details_img_rf = findViewById(R.id.store_details_img_rf);
        store_details_tv_start = findViewById(R.id.store_details_tv_start);
        store_details_img_btn_tv1 = findViewById(R.id.store_details_img_btn_tv1);
        store_details_img_btn_tv2 = findViewById(R.id.store_details_img_btn_tv2);

        store_details_return = findViewById(R.id.store_details_return_s);
        store_details_img = findViewById(R.id.store_details_img);
        store_details_img_btn = findViewById(R.id.store_details_img_btn);
        store_details_title = findViewById(R.id.store_details_title);
        store_details_check = findViewById(R.id.store_details_check);
        store_details_check_S = findViewById(R.id.store_details_check_S);
        store_details_rv = findViewById(R.id.store_details_rv);
        confirm_the_visit_gv = findViewById(R.id.clock_stores_gv);

        store_details_return.setOnClickListener(this);
        store_details_img_rf.setOnClickListener(this);
        store_details_tv_start.setOnClickListener(this);
        store_details_img_btn.setOnClickListener(this);

        inflate = View.inflate(ClockStoresActivity.this, R.layout.item_flowlayout, null);
        mDatas = new ArrayList<>();
        adapter = new GridViewSAdapter(ClockStoresActivity.this, mDatas, handler);
        confirm_the_visit_gv.setAdapter(adapter);

        myStoreRise = getIntent().getStringExtra("MyStoreRise");
        myStoreName = getIntent().getStringExtra("MyStoreName");
        myLocation = getIntent().getStringExtra("MyLocation");
        myStoreId = getIntent().getStringExtra("MyStoreId");

        //门店抬头照片
        if (myStoreRise.equals("")) {

        } else {
            Glide.with(ClockStoresActivity.this).load(FinalContents.getImageUrl() + myStoreRise).into(store_details_img);
        }
        //门店名称
        store_details_title.setText(myStoreName);

        //TODO 照片添加事件
        confirm_the_visit_gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
//                if (mDatas.size() == 3) {
//                    ToastUtil.showLongToast(ClockStoresActivity.this, "图片最多三张");
//                } else {
                if (position == parent.getChildCount() - 1) {

                    if (mDatas.size() == 3) {
                        Intent intent = new Intent(ClockStoresActivity.this, BigPhotoActivity.class);
                        intent.putExtra("index", position);
                        intent.putExtra("bigPhotoimg", stringBuffer.toString());// -1  -1  -1
                        startActivity(intent);
//                            ToastUtil.showLongToast(ClockStoresActivity.this, "图片最多三张");
                    } else {
//                        AlertDialog.Builder builder = new AlertDialog.Builder(ConfirmTheVisitActivity.this);
//                        builder.setTitle("请选择图片来源");
//                        builder.setItems(new String[]{"相机", "相册"}, new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialogInterface, int i) {
//                                if (i == 0) {

                        try {
                            //检测是否有写的权限
                            int permission = ActivityCompat.checkSelfPermission(ClockStoresActivity.this,
                                    "android.permission.WRITE_EXTERNAL_STORAGE");
                            if (permission != PackageManager.PERMISSION_GRANTED) {
                                // 没有写的权限，去申请写的权限，
                                ActivityCompat.requestPermissions(ClockStoresActivity.this, PERMISSIONS_STORAGE, REQUEST_EXTERNAL_STORAGE);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        String SDState = Environment.getExternalStorageState();
                        if (SDState.equals(Environment.MEDIA_MOUNTED)) {
                            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);// "android.media.action.IMAGE_CAPTURE"
                            /***
                             * 需要说明一下，以下操作使用照相机拍照，拍照后的图片会存放在相册中的 这里使用的这种方式有一个好处就是获取的图片是拍照后的原图
                             * 如果不实用ContentValues存放照片路径的话，拍照后获取的图片为缩略图不清晰
                             */
                            ContentValues values = new ContentValues();
                            Uri photoUri = ClockStoresActivity.this.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);


//TODO Uri图片转换成file类型的 start
                            file = uri2File(photoUri);
                            Log.i("MyCL", "Uri图片转换成file类型的：" + file);

                            isPhoto = 1;

//TODO Uri图片转换成file类型的 end
                            Log.i("MyCL", "图片路径：" + photoUri);
                            intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                            startActivityForResult(intent, 1);
                        } else {

                        }
                    }
                } else {
                    Intent intent = new Intent(ClockStoresActivity.this, BigPhotoActivity.class);
                    intent.putExtra("index", position);
                    intent.putExtra("bigPhotoimg", stringBuffer.toString());// -1  -1  -1
                    startActivity(intent);
//                        ArrayList<String> listImage = new ArrayList<>();
//                        final String[] a = stringBuffer.toString().split("[|]");
//                        for (int i = 0; i < a.length; i++) {
//                            listImage.add(a[i]);
//                        }
//                        listImage.remove(position);
//                        stringBuffer.append(listImage);
//                        adapter.notifyDataSetChanged();

                }
//                }
            }
        });

        initQX();
    }

    private void initQX() {
        Log.i("MyCL", "initQX");
        //TODO 判断是否开启定位权限
        if (ContextCompat.checkSelfPermission(ClockStoresActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            //TODO 未开启定位权限 按钮状态：无法打卡
            //开启定位权限,200是标识码
//            ActivityCompat.requestPermissions(ClockStoresActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 200);
            Log.i("MyCL", "未开启定位权限");
            store_details_img_btn_tv1.setVisibility(View.GONE);
            store_details_img_btn_tv2.setVisibility(View.GONE);
            Glide.with(ClockStoresActivity.this).load(R.mipmap.wufadaka).into(store_details_img_btn);//无法打卡
            num = 1;
            store_details_linear1.setVisibility(View.GONE);
            store_details_linear2.setVisibility(View.VISIBLE);
        } else {
            //TODO 已开启定位权限
            Log.i("MyCL", "已开启定位权限");
//            initMap();
//            ToastUtil.showToast(ClockStoresActivity.this, "已开启定位权限", Toast.LENGTH_LONG).show();
            ifnum = 1;
            mLocClient = new LocationClient(this);
            mLocClient.registerLocationListener(myListener);
            LocationClientOption option = new LocationClientOption();
            option.setOpenGps(true); // 打开gps
            option.setCoorType("bd09ll"); // 设置坐标类型
            option.setScanSpan(1000);
            option.setAddrType("all");
            mLocClient.setLocOption(option);
            mLocClient.start();

        }
        if (isFirstLoc) {

        } else {
            initData();
        }
    }

    private void initData() {

        Log.i("MyCL", "进入initData");

        adapter1 = new RecordAdapter();
        LinearLayoutManager manager = new LinearLayoutManager(ClockStoresActivity.this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        store_details_rv.setLayoutManager(manager);

        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        builder.addConverterFactory(GsonConverterFactory.create());
        Retrofit build = builder.build();
        MyService myService = build.create(MyService.class);
        Log.i("MyCL", "FinalContents.getUserID():" + FinalContents.getUserID());
        Log.i("MyCL", "myStoreId:" + myStoreId);
        Observable<RecordBean> financeBean = myService.getRecord(FinalContents.getUserID(), myStoreId, "", "1");
        financeBean.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RecordBean>() {
                    @Override
                    public void onSubscribe(Disposable disposable) {

                    }

                    @Override
                    public void onNext(RecordBean recordBean) {
                        data = recordBean.getData();
                        Log.i("MyCL", "门店打卡记录数据：" + data.getTotal());
                        Log.i("MyCL", "门店打卡记录数据：" + data.getRows().toString());
                        adapter1.setRows(data.getRows());
                        store_details_rv.setAdapter(adapter1);
//                        if (data.getRows().size() >= 2) {
//                            store_details_check.setVisibility(View.GONE);
//                            store_details_check_S.setVisibility(View.GONE);
//                            confirm_the_visit_gv.setVisibility(View.GONE);
//                            store_details_img_btn.setVisibility(View.GONE);
//                        } else {
                        initDataS();
//                        }

                    }

                    @Override
                    public void onError(Throwable throwable) {
                        Log.i("MyCL", "获取打卡记录错误信息：" + throwable.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });


//        Log.i("MyCL", "initData");
        //TODO 后台给的数据
//        String mylatitude = "38.117131";
//        String mylongitude = "118.162608";

    }

    private void StartTime() {
        timer1 = new Timer();
        final int count = new Long(l).intValue();
        timerTask = new TimerTask() {
            int cnt = count / 1000;

            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
//                        Log.i("计时器", "时间：" + getStringTime(cnt++));
                        store_details_img_btn_tv2.setText(getStringTime(cnt++));
                    }
                });
            }
        };
        timer1.schedule(timerTask, 0, 1000);
    }

    private String getStringTime(int cnt) {

        hour = cnt / 3600;
        min = cnt % 3600 / 60;
        second = cnt % 60;

        return String.format(Locale.CHINA, "%02d:%02d:%02d", hour, min, second);
    }


    private void initDataS() {

        Log.i("MyCL", "initDataS");

        StringBuffer stringBuffer = new StringBuffer();
        StringBuffer append = stringBuffer.append(myLocation);
        for (int j = 0; j < append.length(); ++j) {
            if (append.substring(j, j + 1).equals(",")) {
                mylatitude = append.substring(j + 1);
                mylongitude = append.substring(0, j);
                break;
            }
        }

        LatLng start = new LatLng(Double.valueOf(mylatitude), Double.valueOf(mylongitude));
        double distance = DistanceUtil.getDistance(ll, start);
        Log.i("MyCL", "ll：" + ll);
        Log.i("MyCL", "data.getTotal()：" + data.getTotal());
        Log.i("MyCL", "data.getTotal()：" + data.getTotal());

        if (distance < 50) {
            Log.i("MyCL", "范围小于50");
            store_details_linear1.setVisibility(View.GONE);
            store_details_linear2.setVisibility(View.GONE);
            //TODO 做判断是到店打卡还是出店打卡
            //TODO 在范围内 按钮状态：到店打卡/出店打卡
            num = 3;
            if (data.getTotal() == 0) {
                Glide.with(ClockStoresActivity.this).load(R.mipmap.daodiandaka).into(store_details_img_btn);//到店打卡
                store_details_img_btn_tv1.setVisibility(View.GONE);
                store_details_img_btn_tv2.setVisibility(View.GONE);
                store_details_img_btn_tv1.setText("进店打卡");
            } else if (data.getTotal() == 1) {
                store_details_check.setText("出店打卡：");
//                store_details_img_btn_tv1.setVisibility(View.GONE);
//                store_details_img_btn_tv2.setVisibility(View.GONE);
                store_details_img_btn_tv1.setVisibility(View.VISIBLE);
                store_details_img_btn_tv2.setVisibility(View.VISIBLE);
                store_details_img_btn_tv1.setText("出店打卡");
                //获取时间戳
                timeStamp = System.currentTimeMillis();
                //最后一个出店打卡时间戳
                createTime = data.getRows().get(data.getRows().size() - 1).getCreateTime();
                //时间戳差
                l = timeStamp - createTime;
                StartTime();
                Glide.with(ClockStoresActivity.this).load(R.mipmap.dakabeijing).into(store_details_img_btn);//出店打卡
            }
//            else if (data.getTotal() >= 2) {
//                store_details_img_btn.setVisibility(View.GONE);
//                store_details_check.setVisibility(View.GONE);
//                store_details_check_S.setVisibility(View.GONE);
//                confirm_the_visit_gv.setVisibility(View.GONE);
//                store_details_img_btn_tv1.setVisibility(View.GONE);
//                store_details_img_btn_tv2.setVisibility(View.GONE);
//
//            }

        } else if (distance > 50) {
            //TODO 不在范围内 按钮状态：未到指定区域
            Log.i("MyCL", "范围大于50");
            num = 4;
            store_details_linear1.setVisibility(View.VISIBLE);
            store_details_linear2.setVisibility(View.GONE);
            store_details_img_btn_tv1.setVisibility(View.GONE);
            store_details_img_btn_tv2.setVisibility(View.GONE);
            Glide.with(ClockStoresActivity.this).load(R.mipmap.weidaozhidingquyu).into(store_details_img_btn);//未到指定区域
            ToastUtil.showLongToast(ClockStoresActivity.this, "未到指定区域");
        }

    }


    @SingleClick(1000)
    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.store_details_return_s:
                //返回按钮
                if (data.getTotal() == 1 && store_details_linear1.getVisibility() == View.GONE && store_details_linear2.getVisibility() == View.GONE) {
                    if (!timerTask.cancel()) {
                        timerTask.cancel();
                        timer1.cancel();
                    }
                }
                finish();
                break;
            case R.id.store_details_img_btn:
                //打卡
                Log.i("MyCL", "点击按钮");
                if (num == 1) {
                    //未开启定位权限
                    Log.i("MyCL", "未开启定位权限");
                    ToastUtil.showLongToast(ClockStoresActivity.this, "未开启定位权限，无法打卡");
                } else if (num == 2) {
                    //暂无状态
                    Log.i("MyCL", "暂无状态");
                } else if (num == 3) {
                    //到店打卡
                    Log.i("MyCL", "到店打卡/出店打卡");
                    if (stringBuffer.length() == 0) {
                        Log.i("MyCL", "打卡失败 图片至少一张");
                        ToastUtil.showLongToast(ClockStoresActivity.this, "照片不能为空");
                        //打卡失败 图片至少一张
                    } else {
                        if (data.getTotal() == 0) {
                            initClockIn();
                        } else if (data.getTotal() == 1) {
                            if (min < 30 && hour == 0) {
                                AlertDialog.Builder builder1 = new AlertDialog.Builder(ClockStoresActivity.this);
                                View inflate = LayoutInflater.from(ClockStoresActivity.this).inflate(R.layout.binding_report, null, false);
                                builder1.setView(inflate);
                                TextView report_binding_title = inflate.findViewById(R.id.report_binding_title);
                                TextView report_binding_confirm_tv = inflate.findViewById(R.id.report_binding_confirm_tv);
                                TextView report_binding_cancel_tv = inflate.findViewById(R.id.report_binding_cancel_tv);
                                RelativeLayout report_binding_cancel = inflate.findViewById(R.id.report_binding_cancel);
                                RelativeLayout report_binding_confirm = inflate.findViewById(R.id.report_binding_confirm);
                                report_binding_title.setText("未到指定时间,无效打卡");//内容
                                report_binding_confirm_tv.setText("取消打卡");
                                report_binding_cancel_tv.setText("继续打卡");
                                final AlertDialog show = builder1.show();
                                report_binding_cancel.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        show.dismiss();
                                        initClockIn();
                                    }
                                });
                                report_binding_confirm.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        show.dismiss();
                                    }
                                });
                            } else if (min >= 30 || hour > 0) {
                                initClockIn();
                            }
                        }
                        //打卡成功 刷新
//                        if (data.getTotal() == 0) {
//                            //进店打卡成功
//                            store_details_check.setText("出店打卡");
//                            stringBuffer.setLength(0);
//                        } else if (data.getTotal() == 1) {
//                            //出店打卡成功
//                            store_details_check.setVisibility(View.GONE);
//                            store_details_check_S.setVisibility(View.GONE);
//                            confirm_the_visit_gv.setVisibility(View.GONE);
//                            stringBuffer.setLength(0);
//                        }
                    }
                } else if (num == 4) {
                    //未到指定区域
                    Log.i("MyCL", "未到指定区域");
                    ToastUtil.showLongToast(ClockStoresActivity.this, "未到指定区域");
//                    Intent intent = new Intent(ClockStoresActivity.this, MapHouseActivity.class);
//                    startActivity(intent);
                }

                break;
            case R.id.store_details_img_rf:
                //未到指定地点 刷新
                initQX();
                break;
            case R.id.store_details_tv_start:
                //未开启定位权限 开启权限
                ActivityCompat.requestPermissions(ClockStoresActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 200);
                break;
        }

    }

    //门店打卡接口
    private void initClockIn() {
        //从百度地图获取地址
        if (IfNum == 0) {
            s = addrStr;
            IfNum = 1;
        } else {
            s = mlocation.getAddrStr();
        }
        if (data.getTotal() == 0) {
            ifType = "1";
        } else if (data.getTotal() == 1) {
            ifType = "2";
        }

        double longitude = ll.longitude;
        double latitude = ll.latitude;
        String locations = longitude + "," + latitude;
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        builder.addConverterFactory(GsonConverterFactory.create());
        Retrofit build = builder.build();
        MyService myService = build.create(MyService.class);
        Log.i("MyCL", "myStoreId：" + myStoreId);
        Log.i("MyCL", "s：" + s);
        Log.i("MyCL", "locations：" + locations);
        Log.i("MyCL", "stringBuffer.toString()：" + stringBuffer.toString());
        Log.i("MyCL", "ifType：" + ifType);
        Log.i("MyCL", "FinalContents.getUserID()：" + FinalContents.getUserID());
        Observable<ClockInBean> financeBean = myService.getClockIn(myStoreId, s, locations, stringBuffer.toString(), ifType, FinalContents.getUserID());
        financeBean.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ClockInBean>() {
                    @Override
                    public void onSubscribe(Disposable disposable) {

                    }

                    @Override
                    public void onNext(ClockInBean clockInBean) {
                        if (clockInBean.getData().getMessage().equals("保存成功")) {
                            Log.i("MyCL", "打卡成功：");
                            if (data.getTotal() == 0) {
                                store_details_check.setText("出店打卡");
                                stringBuffer.setLength(0);
                                mDatas.clear();
                                adapter.notifyDataSetChanged();
                            } else if (data.getTotal() == 1) {
                                store_details_check.setText("到店打卡");
//                                store_details_check.setVisibility(View.GONE);
//                                store_details_check_S.setVisibility(View.GONE);
//                                confirm_the_visit_gv.setVisibility(View.GONE);
//                                store_details_img_btn.setVisibility(View.GONE);
                                stringBuffer.setLength(0);
                                mDatas.clear();
                                adapter.notifyDataSetChanged();
                                if (!timerTask.cancel()) {
                                    timerTask.cancel();
                                    timer1.cancel();
                                }
                            }
                            initData();

                        }

                    }

                    @Override
                    public void onError(Throwable throwable) {
                        ToastUtil.showLongToast(ClockStoresActivity.this, "数据异常请联系管理员");
                        Log.i("MyCL", "门店打卡错误信息：" + throwable.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }


    @Override
    protected void onRestart() {
        super.onRestart();

        if (isPhoto == 1) {
            new Thread() {
                @Override
                public void run() {

                    Retrofit.Builder builder = new Retrofit.Builder();
                    builder.baseUrl(FinalContents.getBaseUrl());
                    builder.addConverterFactory(GsonConverterFactory.create());
                    builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
                    Retrofit build = builder.build();
                    MyService fzbInterface = build.create(MyService.class);

                    RequestBody requestBody = RequestBody.create(MediaType.parse("image/png"), file);

                    MultipartBody.Part part = MultipartBody.Part.createFormData("file", file.getName(), requestBody);

                    Observable<AddPhotoBean> addPhoto = fzbInterface.getAddPhoto(FinalContents.getUserID(), "feed", part);
                    addPhoto.subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Observer<AddPhotoBean>() {
                                @Override
                                public void onSubscribe(Disposable d) {

                                }

                                @Override
                                public void onNext(AddPhotoBean addPhotoBean) {
                                    if (stringBuffer.length() == 0) {
                                        stringBuffer.append(addPhotoBean.getData().getUrl());
                                    } else {
                                        stringBuffer.append("|" + addPhotoBean.getData().getUrl());
                                    }
                                    Log.i("MyCL", "获取图片信息：" + addPhotoBean.getData().getUrl());
                                    mDatas.add(addPhotoBean.getData().getUrl());
                                    adapter.notifyDataSetChanged();
                                    isPhoto = 0;
                                }

                                @Override
                                public void onError(Throwable e) {
                                    Log.i("MyCL", "经济圈发布图片上传错误信息：" + e.getMessage());
                                }

                                @Override
                                public void onComplete() {

                                }
                            });
                }
            }.start();
        }

    }

    //TODO 将Uri转换成File
    private File uri2File(Uri uri) {
        String img_path;
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor actualimagecursor = managedQuery(uri, proj, null,
                null, null);
        if (actualimagecursor == null) {
            img_path = uri.getPath();
        } else {
            int actual_image_column_index = actualimagecursor
                    .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            actualimagecursor.moveToFirst();
            img_path = actualimagecursor
                    .getString(actual_image_column_index);
        }
        File file = new File(img_path);
        return file;
    }

    /**
     * 定位SDK监听函数
     * num = 2;
     * initData();
     */
    public class MyLocationListenner implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
            // map view 销毁后不在处理新接收的位置  || mMapView == null
            if (location == null) {
                return;
            }
            mlocation = location;
            MyLocationData locData = new MyLocationData.Builder()
                    .accuracy(mlocation.getRadius())
                    // 此处设置开发者获取到的方向信息，顺时针0-360
                    .direction(100).latitude(mlocation.getLatitude())
                    .longitude(mlocation.getLongitude()).build();
//            mBaiduMap.setMyLocationData(locData);
            if (isFirstLoc) {
                isFirstLoc = false;
                addrStr = mlocation.getAddrStr();
                ll = new LatLng(location.getLatitude(),
                        location.getLongitude());
                MapStatus.Builder builder = new MapStatus.Builder();
                builder.target(ll).zoom(18.0f);
                Log.i("MyCL", "MyLocationListenner");
                initData();
//                mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
            }
        }

        public void onReceivePoi(BDLocation poiLocation) {
        }
    }

    @Override
    protected void onPause() {
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
//        mMapView.onPause();
        super.onPause();
    }

    @Override
    protected void onResume() {
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
//        mMapView.onResume();
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        // 退出时销毁定位
        mLocClient.stop();
        // 关闭定位图层
//        mBaiduMap.setMyLocationEnabled(false);
//        mMapView.onDestroy();
//        mMapView = null;
        super.onDestroy();
    }

    //    TODO 动态打开gps
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 200://刚才的识别码
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {//用户同意权限,执行我们的操作
//                    initMap();//开始定位
                } else {//用户拒绝之后,当然我们也可以弹出一个窗口,直接跳转到系统设置页面
                    ToastUtil.showLongToast(ClockStoresActivity.this, "未开启定位权限,请手动到设置去开启权限");
                }
                Log.i("MyCL", "动态打开gps");
                mLocClient = new LocationClient(this);
                mLocClient.registerLocationListener(myListener);
                LocationClientOption option = new LocationClientOption();
                option.setOpenGps(true); // 打开gps
                option.setCoorType("bd09ll"); // 设置坐标类型
                option.setScanSpan(1000);
                option.setAddrType("all");
                mLocClient.setLocOption(option);
                mLocClient.start();
                initQX();
                break;
            default:
                break;
        }
    }
}
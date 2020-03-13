package com.xcy.fzbcity.all.view;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.MapView;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.geocoder.GeocodeResult;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.amap.api.services.geocoder.RegeocodeQuery;
import com.amap.api.services.geocoder.RegeocodeResult;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.adapter.GridViewAdapter;
import com.xcy.fzbcity.all.api.FinalContents;
import com.xcy.fzbcity.all.api.ProjectProgressApi;
import com.xcy.fzbcity.all.modle.AddPhotoBean;
import com.xcy.fzbcity.all.modle.ChangeAddress;
import com.xcy.fzbcity.all.modle.VisitSaveBean;
import com.xcy.fzbcity.all.persente.StatusBar;
import com.xcy.fzbcity.all.service.MyService;
import com.xcy.fzbcity.all.utils.CommonUtil;
import com.xcy.fzbcity.all.utils.ToastUtil;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

//TODO 确认到访
public class ConfirmTheVisitActivity extends AllActivity implements LocationSource, AMapLocationListener, GeocodeSearch.OnGeocodeSearchListener {
    RelativeLayout confirm_the_visit_return;
    private TextView comfirm_client_name;
    private TextView comfirm_project_name;
    private TextView comfirm_location;
    private Button confirm_the_visit_btn;

    GridView confirm_the_visit_gv;
    private GridViewAdapter adapter;
    private List<Object> mDatas;

    private final int IMAGE_CODE = 0;

    RelativeLayout visit_rl;

    private Bitmap bm;
    StringBuffer stringBuffer = new StringBuffer();
    int sum = 0;
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            "android.permission.READ_EXTERNAL_STORAGE",
            "android.permission.WRITE_EXTERNAL_STORAGE"};
    int isPhoto = 0;
    private File file;

    int isIF = 0;
    private String getLongitude = "";
    private String getLatitude = "";

    int ifnum1 = 0;

    private double latitude = 0.0;
    private double longitude = 0.0;
    LocationManager locationManager = null;
    private LocationListener locationListener;

    private int GPS_REQUEST_CODE = 10;

    int ismap = 0;


    boolean isFirstLoc = true; // 是否首次定位

    MapView mMapView = null;
    AMap aMap = null;
    OnLocationChangedListener mListener;
    AMapLocationClient mlocationClient;
    AMapLocationClientOption mLocationOption;
    private double latitude1;
    private double longitude1;
    private GeocodeSearch geocoderSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_the_visit);

        StatusBar.makeStatusBarTransparent(this);

        mMapView = (MapView) findViewById(R.id.map_s_d);
        mMapView.onCreate(savedInstanceState);// 此方法须覆写，虚拟机需要在很多情况下保存地图绘制的当前状态。
//初始化地图控制器对象

        if (aMap == null) {
            aMap = mMapView.getMap();
        }

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
            ToastUtil.showToast(this,"当前无网络，请检查网络后再进行登录");
        }
    }

    @SuppressLint("MissingPermission")
    private void initView() {

        comfirm_client_name = findViewById(R.id.comfirm_client_name);
        confirm_the_visit_return = findViewById(R.id.confirm_the_visit_return);
        comfirm_project_name = findViewById(R.id.comfirm_project_name);
        comfirm_location = findViewById(R.id.comfirm_location);
        confirm_the_visit_btn = findViewById(R.id.confirm_the_visit_btn);
        confirm_the_visit_gv = findViewById(R.id.confirm_the_visit_gv);
        visit_rl = findViewById(R.id.visit_rl);
        confirm_the_visit_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        comfirm_client_name.setText(ProjectProgressApi.getCustomerName());
        comfirm_project_name.setText(ProjectProgressApi.getProjectName());
        confirm_the_visit_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (ifnum1 == 0) {
                    ifnum1 = 1;
                    initVisitSave();
                    ifnum1 = 0;
                }
            }
        });

        boolean locServiceEnable = isLocServiceEnable(ConfirmTheVisitActivity.this);
        if (locServiceEnable == true) {
            Log.i("MyCL", "定位服务已开启");
            initMapData();
        } else {
            Log.i("MyCL", "定位服务未开启");
            ismap = 1;
            Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            startActivityForResult(intent, GPS_REQUEST_CODE);
        }

        visit_rl.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("MissingPermission")
            @Override
            public void onClick(View view) {
                isFirstLoc = true;
                initMapData();
//                Log.e("Map", "setOnClickListener ");
                //取上一次定位的位置
//       Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
//       if(location != null){
//          latitude = location.getLatitude(); //经度
//          longitude = location.getLongitude(); //纬度
//
//          Log.e("Map", "NETWORK_PROVIDER changed : Lat: "
//          + location.getLatitude() + " Lng: "
//          + location.getLongitude());
//       }


//                isIF = 1;
//                Intent intent = new Intent(ConfirmTheVisitActivity.this, TestMapActivity.class);
//                intent.putExtra("La", getLatitude);
//                intent.putExtra("Lo", getLongitude);
//                startActivityForResult(intent, 1);
            }
        });


        initData();
    }

    public static boolean isLocServiceEnable(Context context) {
        LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        boolean gps = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        boolean network = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        return gps || network;
    }


    @SuppressLint("MissingPermission")
    private void initMapData() {

        // 设置定位监听
        aMap.setLocationSource(this);
// 设置为true表示显示定位层并可触发定位，false表示隐藏定位层并不可触发定位，默认是false
        aMap.setMyLocationEnabled(true);
// 设置定位的类型为定位模式，有定位、跟随或地图根据面向方向旋转几种
        aMap.setMyLocationType(AMap.LOCATION_TYPE_LOCATE);

    }


    private void initData() {

        View inflate = View.inflate(ConfirmTheVisitActivity.this, R.layout.item_flowlayout, null);
        mDatas = new ArrayList<>();
        adapter = new GridViewAdapter(ConfirmTheVisitActivity.this, mDatas);
        confirm_the_visit_gv.setAdapter(adapter);
        confirm_the_visit_gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                if (position == parent.getChildCount() - 1) {
                    if (mDatas.size() == 9) {
                        ToastUtil.showToast(ConfirmTheVisitActivity.this,"图片最多九张");
                    } else {
//                        AlertDialog.Builder builder = new AlertDialog.Builder(ConfirmTheVisitActivity.this);
//                        builder.setTitle("请选择图片来源");
//                        builder.setItems(new String[]{"相机", "相册"}, new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialogInterface, int i) {
//                                if (i == 0) {

                        try {
                            //检测是否有写的权限
                            int permission = ActivityCompat.checkSelfPermission(ConfirmTheVisitActivity.this,
                                    "android.permission.WRITE_EXTERNAL_STORAGE");
                            if (permission != PackageManager.PERMISSION_GRANTED) {
                                // 没有写的权限，去申请写的权限，
                                ActivityCompat.requestPermissions(ConfirmTheVisitActivity.this, PERMISSIONS_STORAGE, REQUEST_EXTERNAL_STORAGE);
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
                            Uri photoUri = ConfirmTheVisitActivity.this.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);


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
                }
//                                else if (i == 1) {
//                                    Intent getAlbum = new Intent(Intent.ACTION_PICK);
//                                    getAlbum.setType(IMAGE_TYPE);
//                                    startActivityForResult(getAlbum, IMAGE_CODE);
//                                }
//                        });
//                        builder.setPositiveButton("取消", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialogInterface, int i) {
//
//                            }
//                        });
//                        builder.show();
                adapter = new GridViewAdapter(ConfirmTheVisitActivity.this, mDatas);
                confirm_the_visit_gv.setAdapter(adapter);

//                    }
//                }

            }
        });

    }


    //TODO 添加到访信息数据
    private void initVisitSave() {

        String string = comfirm_location.getText().toString();

        String locationS = longitude1 + "," + latitude1;

        if (comfirm_location.getText().toString().equals("")) {
            ToastUtil.showToast(this,"请进入地图选择地址");
            return;
        }

        if (stringBuffer.toString().equals("")) {
            ToastUtil.showToast(this,"请选择照片");
            return;
        }

        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<VisitSaveBean> userMessage = fzbInterface.getVisitSave(FinalContents.getPreparationId(), locationS, string, stringBuffer.toString(), FinalContents.getUserID());
        userMessage.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<VisitSaveBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @SuppressLint("WrongConstant")
                    @Override
                    public void onNext(VisitSaveBean visitSaveBean) {
                        ToastUtil.showToast(ConfirmTheVisitActivity.this,visitSaveBean.getData().getMessage());
                        FinalContents.setTiaozhuang("到访成功");
                        finish();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("列表数据获取错误", "错误" + e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    //TODO 从相册获取图片
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        //TODO  获取相册图片地址
        sum++;
        if (resultCode != RESULT_OK) {        //此处的 RESULT_OK 是系统自定义得一个常量
            return;
        }
        bm = null;
        //外界的程序访问ContentProvider所提供数据 可以通过ContentResolver接口

        ContentResolver resolver = getContentResolver();


        //此处的用于判断接收的Activity是不是你想要的那个

        if (requestCode == IMAGE_CODE) {

            try {

                Uri originalUri = data.getData();        //获得图片的uri

                bm = MediaStore.Images.Media.getBitmap(resolver, originalUri);        //显得到bitmap图片
                long l = System.currentTimeMillis();
//TODO bitmap图片转换成file类型的 start
                final File san = saveFile(bm, "" + l + ".png");

                new Thread() {
                    @Override
                    public void run() {

                        Retrofit.Builder builder = new Retrofit.Builder();
                        builder.baseUrl(FinalContents.getBaseUrl());
                        builder.addConverterFactory(GsonConverterFactory.create());
                        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
                        Retrofit build = builder.build();
                        MyService fzbInterface = build.create(MyService.class);

                        RequestBody requestBody = RequestBody.create(MediaType.parse("image/png"), san);

                        MultipartBody.Part part = MultipartBody.Part.createFormData("file", san.getName(), requestBody);

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
                                        mDatas.add(addPhotoBean.getData().getUrl());

                                        adapter.notifyDataSetChanged();
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
////TODO bitmap图片转换成file类型的 end

                String[] proj = {MediaStore.Images.Media.DATA};


                //好像是android多媒体数据库的封装接口，具体的看Android文档

                Cursor cursor = managedQuery(originalUri, proj, null, null, null);

                //按我个人理解 这个是获得用户选择的图片的索引值

                int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);

                //将光标移至开头 ，这个很重要，不小心很容易引起越界

                cursor.moveToFirst();

                //最后根据索引值获取图片路径

                String path = cursor.getString(column_index);
            } catch (IOException e) {
            }

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

    //TODO 将bitmap转换成File
    public File saveFile(Bitmap bm, String fileName) throws IOException {//将Bitmap类型的图片转化成file类型，便于上传到服务器
        String path = Environment.getExternalStorageDirectory() + "/Ask";
        File dirFile = new File(path);
        if (!dirFile.exists()) {
            dirFile.mkdir();
        }
        File myCaptureFile = new File(path + fileName);
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(myCaptureFile));
        bm.compress(Bitmap.CompressFormat.JPEG, 80, bos);
        bos.flush();
        bos.close();
        return myCaptureFile;

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
                                    mDatas.add(addPhotoBean.getData().getUrl());
                                    adapter.notifyDataSetChanged();
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
        isPhoto = 0;
        if (ismap == 1) {
            initMapData();
            ismap = 0;
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，保存地图当前的状态
        mMapView.onSaveInstanceState(outState);
    }

    @Override
    public void activate(OnLocationChangedListener onLocationChangedListener) {
        mListener = onLocationChangedListener;
        if (mlocationClient == null) {
            //初始化定位
            mlocationClient = new AMapLocationClient(this);
            //初始化定位参数
            mLocationOption = new AMapLocationClientOption();
            //设置定位回调监听
            mlocationClient.setLocationListener(this);
            //设置为高精度定位模式
            mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
            //设置定位参数
            mlocationClient.setLocationOption(mLocationOption);
            // 此方法为每隔固定时间会发起一次定位请求，为了减少电量消耗或网络流量消耗，
            // 注意设置合适的定位时间的间隔（最小间隔支持为2000ms），并且在合适时间调用stopLocation()方法来取消定位请求
            // 在定位结束后，在合适的生命周期调用onDestroy()方法
            // 在单次定位情况下，定位无论成功与否，都无需调用stopLocation()方法移除请求，定位sdk内部会移除
            mlocationClient.startLocation();//启动定位
        }
    }

    @Override
    public void deactivate() {
        mListener = null;
        if (mlocationClient != null) {
            mlocationClient.stopLocation();
            mlocationClient.onDestroy();
        }
        mlocationClient = null;
    }

    @Override
    public void onLocationChanged(AMapLocation amapLocation) {
        if (mListener != null&&amapLocation != null) {
            if (amapLocation != null
                    &&amapLocation.getErrorCode() == 0) {
                mListener.onLocationChanged(amapLocation);// 显示系统小蓝点
                if(isFirstLoc == true){
                    //39.16951
                    latitude1 = amapLocation.getLatitude();
                    //117.252287
                    longitude1 = amapLocation.getLongitude();
                    isFirstLoc = false;
                    geocoderSearch = new GeocodeSearch(this);
                    geocoderSearch.setOnGeocodeSearchListener(this);
                    // 第一个参数表示一个Latlng，第二参数表示范围多少米，第三个参数表示是火系坐标系还是GPS原生坐标系
                    RegeocodeQuery query = new RegeocodeQuery(new LatLonPoint(latitude1,longitude1), 200,GeocodeSearch.AMAP);
                    geocoderSearch.getFromLocationAsyn(query);
                }
            } else {
                String errText = "定位失败," + amapLocation.getErrorCode()+ ": " + amapLocation.getErrorInfo();
                Log.e("AmapErr",errText);
            }
        }
    }

    @Override
    public void onRegeocodeSearched(RegeocodeResult regeocodeResult, int i) {
        comfirm_location.setText(regeocodeResult.getRegeocodeAddress().getFormatAddress());
    }

    @Override
    public void onGeocodeSearched(GeocodeResult geocodeResult, int i) {

    }
}

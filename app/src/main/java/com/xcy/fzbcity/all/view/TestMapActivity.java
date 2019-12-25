package com.xcy.fzbcity.all.view;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeOption;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.baidu.mapapi.search.route.RoutePlanSearch;
import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.persente.StatusBar;
import com.xcy.fzbcity.all.utils.ToastUtil;

public class TestMapActivity extends AppCompatActivity {

    // 定位相关
    LocationClient mLocClient;
    //定位监听
    public MyLocationListenner myListener = new MyLocationListenner();
    MapView mMapView = null;
    BaiduMap mBaiduMap;
    boolean isFirstLoc = true; // 是否首次定位
    BDLocation mlocation;
    private double latitude = 0;
    private double longitude = 0;
    private GeoCoder mCoder;
    private LatLng latLng1;
    private RoutePlanSearch mSearch;
    GeoCoder geoCoder = GeoCoder.newInstance();
    private String la = "";
    private String lo = "";
    private LatLng ll;
    private MyLocationData locData;

    Button map_sendmap_btn;

    RelativeLayout text_map_rl;
    ImageView text_map_img;
    private String address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_map);

        la = getIntent().getStringExtra("La");
        lo = getIntent().getStringExtra("Lo");


        if (ContextCompat.checkSelfPermission(TestMapActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {//未开启定位权限
            //开启定位权限,200是标识码
            ActivityCompat.requestPermissions(TestMapActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 200);
        } else {
//            initMap();
//            Toast.makeText(TestMapActivity.this, "已开启定位权限", Toast.LENGTH_LONG).show();
        }


        text_map_rl = findViewById(R.id.text_map_rl);
        text_map_img = findViewById(R.id.text_map_img);

        if (la.equals("") || lo.equals("")) {
            text_map_rl.setVisibility(View.VISIBLE);
            text_map_img.setVisibility(View.VISIBLE);
        } else {
            text_map_rl.setVisibility(View.GONE);
            text_map_img.setVisibility(View.GONE);
        }

        text_map_rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text_map_rl.setVisibility(View.GONE);
                text_map_img.setVisibility(View.GONE);
            }
        });
        text_map_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text_map_rl.setVisibility(View.GONE);
                text_map_img.setVisibility(View.GONE);
            }
        });

        map_sendmap_btn = findViewById(R.id.map_sendmap_btn);

        map_sendmap_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage();
            }
        });

        initView();


    }

    private void initView() {

        StatusBar.makeStatusBarTransparent(this);

        mMapView = (MapView) findViewById(R.id.bmapView);

        //获取地图控件引用
        mMapView = (MapView) findViewById(R.id.bmapView);
        mBaiduMap = mMapView.getMap();
        // 开启定位图层
        mBaiduMap.setMyLocationEnabled(true);
        // 定位初始化
        mLocClient = new LocationClient(this);
        mLocClient.registerLocationListener(myListener);
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true); // 打开gps
        option.setCoorType("bd09ll"); // 设置坐标类型
        option.setScanSpan(1000);
        option.setAddrType("all");
        mLocClient.setLocOption(option);
        //经纬度转地址
        mCoder = GeoCoder.newInstance();
        mSearch = RoutePlanSearch.newInstance();
        mCoder.setOnGetGeoCodeResultListener(onGetGeoCoderResultListener);
//        mSearch.setOnGetRoutePlanResultListener((OnGetRoutePlanResultListener) this);

        BaiduMap.OnMapClickListener listener = new BaiduMap.OnMapClickListener() {

            @Override
            public void onMapClick(LatLng latLng) {
                String s = latLng.toString();
                Log.i("地图", "latLng：" + s);
                mBaiduMap.clear();
                mBaiduMap = mMapView.getMap();
                //定义Maker坐标点
                LatLng point = new LatLng(latLng.latitude, latLng.longitude);
                //构建Marker图标
                BitmapDescriptor bitmap = BitmapDescriptorFactory
                        .fromResource(R.drawable.icon_marka);
                //构建MarkerOption，用于在地图上添加Marker
                OverlayOptions option = new MarkerOptions()
                        .position(point)
                        .icon(bitmap);
                //在地图上添加Marker，并显示
                mBaiduMap.addOverlay(option);
                latitude = latLng.latitude;
                longitude = latLng.longitude;
                //经纬度转地址
                mCoder.reverseGeoCode(new ReverseGeoCodeOption()
                        .location(point)
                        // POI召回半径，允许设置区间为0-1000米，超过1000米按1000米召回。默认值为1000
                        .radius(500));

                latLng1 = latLng;
            }

            @Override
            public boolean onMapPoiClick(MapPoi mapPoi) {
                return false;
            }
        };
        mBaiduMap.setOnMapClickListener(listener);

        mBaiduMap.setMyLocationEnabled(true);

        mLocClient.start();

    }

    OnGetGeoCoderResultListener onGetGeoCoderResultListener = new OnGetGeoCoderResultListener() {
        @Override
        public void onGetGeoCodeResult(GeoCodeResult geoCodeResult) {

        }

        @Override
        public void onGetReverseGeoCodeResult(ReverseGeoCodeResult reverseGeoCodeResult) {
            if (reverseGeoCodeResult == null || reverseGeoCodeResult.error != SearchResult.ERRORNO.NO_ERROR) {
                //没有找到检索结果
                Log.i("经纬度", "没有找到检索结果");
                return;
            } else {
                //详细地址
                address = reverseGeoCodeResult.getAddress();
                //行政区号
                int adCode = reverseGeoCodeResult.getCityCode();
                Log.i("经纬度", "address：" + address);
            }
        }
    };

//    //经纬度转地址
//    OnGetGeoCoderResultListener listener = new OnGetGeoCoderResultListener() {
//        @Override
//        public void onGetGeoCodeResult(GeoCodeResult geoCodeResult) {
//
//        }
//
//        @Override
//        public void onGetReverseGeoCodeResult(ReverseGeoCodeResult reverseGeoCodeResult) {
//            if (reverseGeoCodeResult == null || reverseGeoCodeResult.error != SearchResult.ERRORNO.NO_ERROR) {
//                //没有找到检索结果
//                return;
//            } else {
//                //详细地址
//                String address = reverseGeoCodeResult.getAddress();
////                //行政区号
////                int adCode = reverseGeoCodeResult. getCityCode();
//            }
//        }
//    };

    /**
     * 定位SDK监听函数
     */
    public class MyLocationListenner implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
            // map view 销毁后不在处理新接收的位置
            if (location == null || mMapView == null) {
                return;
            }
            mlocation = location;

            if (lo.equals("") || la.equals("")) {
                locData = new MyLocationData.Builder()
                        .accuracy(mlocation.getRadius())
                        // 此处设置开发者获取到的方向信息，顺时针0-360
                        .direction(100).latitude(mlocation.getLatitude())
                        .longitude(mlocation.getLongitude()).build();
            } else {
                locData = new MyLocationData.Builder()
                        .accuracy(mlocation.getRadius())
                        // 此处设置开发者获取到的方向信息，顺时针0-360
                        .direction(100).latitude(Double.parseDouble(la))
                        .longitude(Double.parseDouble(lo)).build();
            }

            mBaiduMap.setMyLocationData(locData);

            if (isFirstLoc) {
                isFirstLoc = false;
                if (lo.equals("") || la.equals("")) {
                    ll = new LatLng(location.getLatitude(),
                            location.getLongitude());
                } else {
                    ll = new LatLng(Double.parseDouble(la),
                            Double.parseDouble(lo));
                }

                MapStatus.Builder builder = new MapStatus.Builder();
                builder.target(ll).zoom(20.0f);
                mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
            }
        }

        public void onReceivePoi(BDLocation poiLocation) {

            Log.i("地图", poiLocation + "");

        }
    }

    @Override
    protected void onPause() {
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        mMapView.onPause();
        super.onPause();
    }

    @Override
    protected void onResume() {
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        mMapView.onResume();
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        // 退出时销毁定位
        mLocClient.stop();
        // 关闭定位图层
        mBaiduMap.setMyLocationEnabled(false);
        mMapView.onDestroy();
        mMapView = null;
//        mCoder.destroy();

        super.onDestroy();
    }

    /**
     * 发送按钮的点击事件
     */
    public void sendMessage() {

        if (mlocation == null || mMapView == null) {
            ToastUtil.showToast(TestMapActivity.this, "点击了发送按钮");
            return;
        }

//        Log.i("地图", "latitude：" + latitude);
//        Log.i("地图", "longitude：" + longitude);

        if (latitude == 0 || longitude == 0) {
            ToastUtil.showToast(TestMapActivity.this, "请选择定位");
        } else {
            Intent intent = new Intent();
            //纬度
            intent.putExtra("getLatitude", latitude + "");
            //经度
            intent.putExtra("getLongitude", longitude + "");
            //地址
            intent.putExtra("address", address + "");

            setResult(RESULT_OK, intent);
            finish();
        }


        //        Intent intent = new Intent();
        //        //纬度
        //        intent.putExtra("getLatitude", mlocation.getLatitude() + "");
        //        //经度
        //        intent.putExtra("getLongitude", mlocation.getLongitude() + "");
        //        //地址
        //        intent.putExtra("getAddress", mlocation.getAddrStr());
        //        setResult(RESULT_OK, intent);
        //        finish();
    }


    //    TODO 动态打开gps
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 200://刚才的识别码
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {//用户同意权限,执行我们的操作
//                    initMap();//开始定位
                } else {//用户拒绝之后,当然我们也可以弹出一个窗口,直接跳转到系统设置页面
                    ToastUtil.showToast(TestMapActivity.this, "未开启定位权限,请手动到设置去开启权限");
                    initView();
                }
                break;
            default:
                break;
        }
    }


    /**
     * 监听Back键按下事件
     */
    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        ToastUtil.showToast(TestMapActivity.this, "请选择坐标并且发送");
        Log.i("键", "点击了回退键");

    }

}

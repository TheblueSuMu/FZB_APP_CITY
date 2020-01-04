package com.xcy.fzbcity.all.view;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.Polyline;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiDetailSearchOption;
import com.baidu.mapapi.search.poi.PoiDetailSearchResult;
import com.baidu.mapapi.search.poi.PoiIndoorResult;
import com.baidu.mapapi.search.poi.PoiNearbySearchOption;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;
import com.baidu.mapapi.utils.route.BaiduMapRoutePlan;
import com.baidu.mapapi.utils.route.RouteParaOption;
import com.google.android.material.tabs.TabLayout;
import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.api.FinalContents;
import com.xcy.fzbcity.all.persente.SingleClick;
import com.xcy.fzbcity.all.persente.StatusBar;
import com.xcy.fzbcity.all.utils.CommonUtil;
import com.xcy.fzbcity.all.utils.ToastUtil;
import com.xcy.fzbcity.overlayutil.OverlayManager;

import java.util.ArrayList;
import java.util.List;

public class MapActivity extends AllActivity implements View.OnClickListener {

    ImageView map_navigation;
    TabLayout map_tab_layout;
    LinearLayout map_return;
    //    TODO Map
    MapView mMapView;
    BaiduMap mBaiduMap;
    LocationClient mLocationClient;
    MyLocationConfiguration.LocationMode mCurrentMode;
    private LocationClientOption option;
    private double latitude;
    private double longitude;
    private String addr;
    private String country;
    private String province;
    private String city;
    private String district;
    private String street;
    private PoiSearch mPoiSearch;
    private String location;
    private double d = 0;
    private double o = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        init_No_Network();
    }



    private void init_No_Network(){
        boolean networkAvailable = CommonUtil.isNetworkAvailable(this);
        if (networkAvailable) {
            Intent intent = getIntent();
            String office = ""+intent.getStringExtra("office");
            if (office.equals("1")) {
                d = FinalContents.getD();
                o = FinalContents.getO();
            }else if (office.equals("0")){
                d = FinalContents.getD1();
                o = FinalContents.getO1();
            }else {
                d = FinalContents.getD();
                o = FinalContents.getO();
            }


            initView();
            initMap();
        } else {
            RelativeLayout all_no_network = findViewById(R.id.all_no_network);
            Button all_no_reload = findViewById(R.id.all_no_reload);

            all_no_network.setVisibility(View.VISIBLE);
            all_no_reload.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    init_No_Network();
                }
            });
            ToastUtil.showToast(this, "当前无网络，请检查网络后再进行登录");
        }
    }

    private void initView() {
        StatusBar.makeStatusBarTransparent(this);

        map_navigation = findViewById(R.id.map_navigation);
        map_return = findViewById(R.id.map_return);
        map_tab_layout = findViewById(R.id.map_tab_layout);

        map_tab_layout.addTab(map_tab_layout.newTab().setText("交通出行"));
        map_tab_layout.addTab(map_tab_layout.newTab().setText("教育教学"));
        map_tab_layout.addTab(map_tab_layout.newTab().setText("医疗健康"));
        map_tab_layout.addTab(map_tab_layout.newTab().setText("商场购物"));
        map_tab_layout.addTab(map_tab_layout.newTab().setText("生活娱乐"));

        map_tab_layout.getTabAt(0).select();

        map_tab_layout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getText().equals("交通出行")) {
                    initTestBtn("交通出行");
                } else if (tab.getText().equals("教育教学")) {
                    initTestBtn("教育教学");
                } else if (tab.getText().equals("医疗健康")) {
                    initTestBtn("医疗健康");
                } else if (tab.getText().equals("商场购物")) {
                    initTestBtn("商场购物");
                } else if (tab.getText().equals("生活娱乐")) {
                    initTestBtn("生活娱乐");
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        map_navigation.setOnClickListener(this);
        map_return.setOnClickListener(this);

        mMapView = (MapView) findViewById(R.id.bmapView);


        if (ContextCompat.checkSelfPermission(MapActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {//未开启定位权限
            //开启定位权限,200是标识码
            ActivityCompat.requestPermissions(MapActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 200);
        } else {
//            initMap();
            ToastUtil.showToast(MapActivity.this, "已开启定位权限");
        }
    }

    @SingleClick(1000)
    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.map_return:
                finish();
                break;
            case R.id.map_navigation:
                initgh();
//                initTestBtn("餐厅");
                break;
        }

    }

    private void initTestBtn(final String RetrieveName) {
//TODO 创建POI检索实例
        mPoiSearch = PoiSearch.newInstance();
//TODO 设置检索监听器
        mPoiSearch.setOnGetPoiSearchResultListener(listener);
//TODO 发起检索请求

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
                mPoiSearch.searchNearby(new PoiNearbySearchOption()
                        .location(new LatLng(o, d))
                        .radius(10000)
                        .keyword(RetrieveName)
                        .pageNum(10));
//            }
//        }).start();



    }

    private void initMap() {

        mBaiduMap = mMapView.getMap();
//TODO 开启地图的定位图层
        mBaiduMap.setMyLocationEnabled(true);

//TODO 定位初始化
        mLocationClient = new LocationClient(this);

//TODO 通过LocationClientOption设置LocationClient相关参数
        option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        option.setOpenGps(true); // 打开gps
        option.setCoorType("bd09ll"); // 设置坐标类型
        option.setIsNeedAddress(true);

//TODO 设置locationClientOption
        mLocationClient.setLocOption(option);

//TODO 注册LocationListener监听器
        MyLocationListener myLocationListener = new MyLocationListener();
        mLocationClient.registerLocationListener(myLocationListener);

        mCurrentMode = MyLocationConfiguration.LocationMode.FOLLOWING;
//TODO 检索出的marker点设置点击事件
//        mBaiduMap.setOnMarkerClickListener(new BaiduMap.OnMarkerClickListener() {
//            @Override
//            public boolean onMarkerClick(Marker marker) {
//                //从marker中获取info信息
//                Bundle bundle = marker.getExtraInfo();
//                PoiInfo infoUtil = (PoiInfo) bundle.getParcelable("娱乐");
//                // mSpeechSynthesizer.speak(infoUtil.name);
//                ImageView tv = new ImageView(MapActivity.this);
//                BitmapDescriptor bitmapDescriptor;
//                bitmapDescriptor = BitmapDescriptorFactory.fromView(tv);
////infowindow位置
//                LatLng latLng = new LatLng(marker.getPosition().latitude, marker.getPosition().longitude);
//                Log.i("MyCL","latLng：" + latLng);
////infowindow点击事件
//                InfoWindow.OnInfoWindowClickListener listener = new InfoWindow.OnInfoWindowClickListener() {
//                    @Override
//                    public void onInfoWindowClick() {
//                        //隐藏infowindow
//                        mBaiduMap.hideInfoWindow();
//                    }
//                };
////显示infowindow，-47是偏移量，使infowindow向上偏移，不会挡住marker
//                InfoWindow infoWindow = new InfoWindow(bitmapDescriptor, latLng, -107, listener);
//                Log.i("MyCL","infoWindow：" + infoWindow);
//                mBaiduMap.showInfoWindow(infoWindow);
//                return true;
//            }
//        });

//TODO 开启地图定位图层
        mLocationClient.start();

    }

    //TODO POI周边检索
    OnGetPoiSearchResultListener listener = new OnGetPoiSearchResultListener() {
        @Override
        public void onGetPoiResult(PoiResult poiResult) {
            //获取POI检索结果
            Log.i("MyCL", "进入了这个方法------1");
            Log.i("MyCL", "进入了这个方法------poiResult" + poiResult.error.name());
            if (poiResult.error == SearchResult.ERRORNO.NO_ERROR) {
                mBaiduMap.clear();
                //创建PoiOverlay对象
                Log.i("MyCL", "进入了这个方法------2");
                MyPoiOverlay overlay = new MyPoiOverlay(mBaiduMap);
                mBaiduMap.setOnMarkerClickListener(overlay);
                overlay.setResult(poiResult);
                overlay.addToMap();
                overlay.zoomToSpan();
            } else {
//                ToastUtil.showToast(MapActivity.this, "没有找到相应的建筑物");
            }
            mPoiSearch.destroy();
        }

        @Override
        public void onGetPoiDetailResult(PoiDetailSearchResult poiDetailSearchResult) {
            Log.i("MyCL", "进入了这个方法------3");

        }

        @Override
        public void onGetPoiIndoorResult(PoiIndoorResult poiIndoorResult) {
            Log.i("MyCL", "进入了这个方法------4");
        }

        //废弃
        @Override
        public void onGetPoiDetailResult(PoiDetailResult poiDetailResult) {
            Log.i("MyCL", "进入了这个方法------6");
        }
    };

    //TODO 自定义poi检索覆盖物
    private class MyPoiOverlay extends OverlayManager {
        private PoiResult result;
        private boolean flag = false;

        public void setResult(PoiResult result) {
            this.result = result;
        }

        public MyPoiOverlay(BaiduMap baiduMap) {
            super(baiduMap);
        }

        @Override
        public boolean onMarkerClick(Marker marker) {
            onClick(marker.getZIndex());
            return true;
        }

        public boolean onClick(int index) {
            PoiInfo poi = result.getAllPoi().get(index);
            //if(poi.hasCaterDetails){
            mPoiSearch.searchPoiDetail((new PoiDetailSearchOption()).poiUid(poi.uid));
            // }
            return true;
        }

        @Override
        public boolean onPolylineClick(Polyline arg0) {
            return false;
        }

        @Override
        public List<OverlayOptions> getOverlayOptions() {
            List<OverlayOptions> ops = new ArrayList<OverlayOptions>();
            List<PoiInfo> pois = result.getAllPoi();
            OverlayOptions op = null;
            BitmapDescriptor bitmap = BitmapDescriptorFactory.fromResource(R.drawable.icon_gcoding);
            BitmapDescriptor bitmap2 = BitmapDescriptorFactory.fromResource(R.drawable.icon_gcoding);
            for (int i = 0; i < pois.size(); i++) {
                if (flag) {
                    op = new MarkerOptions().position(pois.get(i).location).icon(bitmap);
                } else {
                    op = new MarkerOptions().position(pois.get(i).location).icon(bitmap2);
                }
                ops.add(op);
                mBaiduMap.addOverlay(op).setZIndex(i);
            }
            return ops;
        }
    }

    //TODO 构造地图数据
    public class MyLocationListener extends BDAbstractLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
            //mapView 销毁后不在处理新接收的位置
            if (location == null || mMapView == null) {
                return;
            }
            MyLocationData locData = new MyLocationData.Builder()
                    .accuracy(location.getRadius())
                    // 此处设置开发者获取到的方向信息，顺时针0-360
                    .direction(location.getDirection()).latitude(o)
                    .longitude(d).build();
            mBaiduMap.setMyLocationData(locData);

            //纬度
            latitude = location.getLatitude();
            //经度
            longitude = location.getLongitude();
            Log.i("MyCL", "O：" + o);
            Log.i("MyCL", "D：" + d);
            Log.i("MyCL", location.getLatitude() + "");
            Log.i("MyCL", location.getLongitude() + "");

            //获取详细地址信息
            addr = location.getAddrStr();
            //获取国家
            country = location.getCountry();
            //获取省份
            province = location.getProvince();
            //获取城市
            city = location.getCity();
            //获取区县
            district = location.getDistrict();
            //获取街道信息
            street = location.getStreet();

//            mPoiSearch.searchNearby(new PoiNearbySearchOption()
//                    .location(new LatLng(location.getLatitude(), location.getLongitude()))
//                    .radius(100)
//                    .keyword("公交")
//                    .pageNum(10));

//            if (isFirstLoc) {
//                isFirstLoc = false;
            LatLng ll = new LatLng(o,
                    d);
            MapStatus.Builder builder = new MapStatus.Builder();
            builder.target(ll).zoom(17.0f);
            mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
//            }


        }
    }

    //TODO 调起百度地图路线规划
    private void initgh() {
//定义起终点坐标（天安门和百度大厦）
        LatLng startPoint = new LatLng(latitude, longitude);
        LatLng endPoint = new LatLng(o, d);
//构建RouteParaOption参数以及策略
//也可以通过startName和endName来构造
        RouteParaOption paraOption = new RouteParaOption()
                .startPoint(startPoint)
                .endPoint(endPoint)
                .busStrategyType(RouteParaOption.EBusStrategyType.bus_recommend_way);
//调起百度地图
        try {
            BaiduMapRoutePlan.openBaiduMapTransitRoute(paraOption, this);
        } catch (Exception e) {
            e.printStackTrace();
        }
//调起结束时及时调用finish方法以释放相关资源
        BaiduMapRoutePlan.finish(this);


    }

    //TODO 正确管理各部分的生命周期
    @Override
    protected void onResume() {
        mMapView.onResume();
        super.onResume();
    }

    @Override
    protected void onPause() {
        mMapView.onPause();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        mLocationClient.stop();
        mBaiduMap.setMyLocationEnabled(false);
        mMapView.onDestroy();
        mMapView = null;
        super.onDestroy();
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
                    ToastUtil.showToast(MapActivity.this, "未开启定位权限,请手动到设置去开启权限");
                    initMap();
                }
                break;
            default:
                break;
        }
    }
}
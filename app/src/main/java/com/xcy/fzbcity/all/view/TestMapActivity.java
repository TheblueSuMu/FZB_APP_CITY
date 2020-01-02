package com.xcy.fzbcity.all.view;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeOption;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiCitySearchOption;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiDetailSearchResult;
import com.baidu.mapapi.search.poi.PoiIndoorResult;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;
import com.baidu.mapapi.search.route.RoutePlanSearch;
import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.adapter.TestMapPopwindowAdapter;
import com.xcy.fzbcity.all.persente.StatusBar;
import com.xcy.fzbcity.all.utils.ToastUtil;

import java.util.List;

public class TestMapActivity extends AppCompatActivity implements TestMapPopwindowAdapter.OnTestMap {

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

    TextView map_sendmap_btn;

    RelativeLayout text_map_rl;
    RelativeLayout test_map_back;
    RelativeLayout test_map_rl_2;
    ImageView text_map_img;
    private String address;
    private String pcd;

    EditText test_map_search;
    int num = 0;
    private MapStatus mMapStatus;
    //    private SuggestionSearch mSuggestionSearch;
    private String province;
    private PoiSearch mPoiSearch;
    private List<PoiInfo> allPoi;
    private RecyclerView recyclerView;
    private TestMapPopwindowAdapter adapter;
    private double ssv;
    private double ssvs;
    int ifKeyListener = 0;
    private LatLng ll1;

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

        test_map_back = findViewById(R.id.test_map_back);
        map_sendmap_btn = findViewById(R.id.test_map_success);
        test_map_search = findViewById(R.id.test_map_search);
        test_map_rl_2 = findViewById(R.id.test_map_rl_2);
//        mSuggestionSearch = SuggestionSearch.newInstance();
        mPoiSearch = PoiSearch.newInstance();
        adapter = new TestMapPopwindowAdapter();
        adapter.setOnTestMap(this);

        test_map_rl_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                test_map_rl_2.setVisibility(View.GONE);
                recyclerView.setVisibility(View.GONE);
            }
        });

        recyclerView = findViewById(R.id.test_map_pop_rv_S);


        test_map_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Log.i("经纬度", "beforeTextChanged-s:" + s);
                Log.i("经纬度", "beforeTextChanged-start:" + start);
                Log.i("经纬度", "beforeTextChanged-count:" + count);
                Log.i("经纬度", "beforeTextChanged-after:" + after);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String s1 = s + "";
                if (s1.equals("")) {
                    test_map_rl_2.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.GONE);
                } else {
                    test_map_rl_2.setVisibility(View.VISIBLE);
                    recyclerView.setVisibility(View.VISIBLE);
                    mPoiSearch.searchInCity(new PoiCitySearchOption()
                            .city(province) //必填
                            .keyword(s1 + "") //必填
                            .pageNum(20));
                }

                Log.i("经纬度", "onTextChanged-s:" + s);
                Log.i("经纬度", "onTextChanged-start:" + start);
                Log.i("经纬度", "onTextChanged-before:" + before);
                Log.i("经纬度", "onTextChanged-count:" + count);
            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.i("经纬度", "afterTextChanged-s:" + s);
            }
        });

        map_sendmap_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage();
            }
        });

        test_map_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                //纬度
                intent.putExtra("getLatitude", "");
                //经度
                intent.putExtra("getLongitude", "");
                //地址
                intent.putExtra("address", "");
                //省市区
                intent.putExtra("pcd", "");

                setResult(RESULT_OK, intent);
                finish();
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
//        mSuggestionSearch.setOnGetSuggestionResultListener(onGetSuggestionResultListener);
        mPoiSearch.setOnGetPoiSearchResultListener(poiSearchResultListener);
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
            //地址转经纬度
            if (null != geoCodeResult && null != geoCodeResult.getLocation()) {
//                Log.i("地图", "geoCodeResult：" + geoCodeResult);
//                Log.i("地图", "geoCodeResult.error：" + geoCodeResult.error);
                if (geoCodeResult == null || geoCodeResult.error != SearchResult.ERRORNO.NO_ERROR) {
                    //没有检索到结果
                    Log.i("地图", "没有检索到结果");
                    return;
                } else {
                    latitude = geoCodeResult.getLocation().latitude;
                    longitude = geoCodeResult.getLocation().longitude;
                    la = latitude + "";
                    lo = longitude + "";
                    Log.i("经纬度", "la：" + la);
                    Log.i("经纬度", "lo：" + lo);
                }
            }
        }

        @Override
        public void onGetReverseGeoCodeResult(ReverseGeoCodeResult reverseGeoCodeResult) {
            //经纬度转地址
            if (reverseGeoCodeResult == null || reverseGeoCodeResult.error != SearchResult.ERRORNO.NO_ERROR) {
                //没有找到检索结果
                Log.i("经纬度", "没有找到检索结果");
                return;
            } else {
                //详细地址
                address = reverseGeoCodeResult.getAddress();
                //行政区号
                int adCode = reverseGeoCodeResult.getCityCode();

                pcd = reverseGeoCodeResult.getAddressDetail().province + "/" + reverseGeoCodeResult.getAddressDetail().city + "/" + reverseGeoCodeResult.getAddressDetail().district;

                Log.i("经纬度", "address：" + address);
                Log.i("经纬度", "getBusinessCircle：" + reverseGeoCodeResult.getBusinessCircle());
                Log.i("经纬度", "getSematicDescription：" + reverseGeoCodeResult.getSematicDescription());
                Log.i("经纬度", "getLocation：" + reverseGeoCodeResult.getLocation());
                Log.i("经纬度", "getAdcode：" + reverseGeoCodeResult.getAdcode());
                Log.i("经纬度", "city：" + reverseGeoCodeResult.getAddressDetail().city);
                Log.i("经纬度", "countryName：" + reverseGeoCodeResult.getAddressDetail().countryName);
                Log.i("经纬度", "direction：" + reverseGeoCodeResult.getAddressDetail().direction);
                Log.i("经纬度", "distance：" + reverseGeoCodeResult.getAddressDetail().distance);
                Log.i("经纬度", "district：" + reverseGeoCodeResult.getAddressDetail().district);
                Log.i("经纬度", "province：" + reverseGeoCodeResult.getAddressDetail().province);
                Log.i("经纬度", "street：" + reverseGeoCodeResult.getAddressDetail().street);
                Log.i("经纬度", "streetNumber：" + reverseGeoCodeResult.getAddressDetail().streetNumber);
                Log.i("经纬度", "town：" + reverseGeoCodeResult.getAddressDetail().town);
            }
        }
    };

    OnGetPoiSearchResultListener poiSearchResultListener = new OnGetPoiSearchResultListener() {

        @Override
        public void onGetPoiResult(PoiResult poiResult) {
            Log.i("经纬度", "进入onGetPoiResult:");
            allPoi = poiResult.getAllPoi();

            if (poiResult.error == SearchResult.ERRORNO.NO_ERROR) {
                for (int i = 0; i < poiResult.getAllPoi().size(); ++i) {
                    Log.i("经纬度", "getAddress:" + poiResult.getAllPoi().get(i).getAddress());
                    Log.i("经纬度", "getArea:" + poiResult.getAllPoi().get(i).getArea());
                    Log.i("经纬度", "getName:" + poiResult.getAllPoi().get(i).getName());
                    Log.i("经纬度", "getLocation:" + poiResult.getAllPoi().get(i).getLocation());

                    //name(area)
                    //address
                    Log.i("经纬度", "++++++++++++++++++++++++++++++++++++++++++++++++" + i + "+++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                }

                LinearLayoutManager manager = new LinearLayoutManager(TestMapActivity.this);
                manager.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerView.setLayoutManager(manager);
                adapter.setAllPoi(allPoi);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            } else {
                test_map_rl_2.setVisibility(View.GONE);
                recyclerView.setVisibility(View.GONE);
            }

        }

        @Override
        public void onGetPoiDetailResult(PoiDetailResult poiDetailResult) {
            Log.i("经纬度", "进入onGetPoiDetailResult");
        }

        @Override
        public void onGetPoiDetailResult(PoiDetailSearchResult poiDetailSearchResult) {
            Log.i("经纬度", "进入onGetPoiDetailResult");

        }

        @Override
        public void onGetPoiIndoorResult(PoiIndoorResult poiIndoorResult) {
            Log.i("经纬度", "进入onGetPoiIndoorResult");
        }
    };

    @Override
    public void TestMap(int position) {

        Log.i("经纬度","坐标+名称：" + allPoi.get(position).getAddress() + "+" + allPoi.get(position).getLocation());
        ifKeyListener = 1;
        ssvs = allPoi.get(position).getLocation().latitude;
        ssv = allPoi.get(position).getLocation().longitude;
        test_map_rl_2.setVisibility(View.GONE);
        recyclerView.setVisibility(View.GONE);
    }

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
                province = mlocation.getProvince();
                Log.i("经纬度", " mlocation.getProvince():" + mlocation.getProvince());
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
            if (ifKeyListener == 1) {
                ifKeyListener = 0;
                ll1 = new LatLng(ssvs,
                        ssv);
                MapStatus.Builder builder = new MapStatus.Builder();
                builder.target(ll1).zoom(18.0f);
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
        mPoiSearch.destroy();
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
            //省市区
            intent.putExtra("pcd", pcd + "");

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
        ToastUtil.showToast(TestMapActivity.this, "请选择坐标");
        Log.i("键", "点击了回退键");

    }

}

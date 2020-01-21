package com.xcy.fzbcity.all.view;


import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.AMapOptions;
import com.amap.api.maps.CameraUpdate;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.MapView;
import com.amap.api.maps.UiSettings;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MyLocationStyle;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.geocoder.GeocodeResult;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.amap.api.services.geocoder.RegeocodeQuery;
import com.amap.api.services.geocoder.RegeocodeResult;
import com.amap.api.services.poisearch.PoiResult;
import com.amap.api.services.poisearch.PoiSearch;
import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.adapter.TestMapPopwindowAdapter;
import com.xcy.fzbcity.all.adapter.TestMapPopwindowAdapter_S;
import com.xcy.fzbcity.all.api.FinalContents;
import com.xcy.fzbcity.all.persente.StatusBar;

import java.util.ArrayList;

public class TestMapActivity extends AppCompatActivity implements LocationSource, AMapLocationListener, PoiSearch.OnPoiSearchListener, TestMapPopwindowAdapter.OnTestMap, View.OnClickListener, GeocodeSearch.OnGeocodeSearchListener, TestMapPopwindowAdapter_S.OnTestMap {

    MapView mapView = null;
    AMap aMap = null;
    private UiSettings mUiSettings;//定义一个UiSettings对象
    OnLocationChangedListener mListener;
    AMapLocationClient mlocationClient;
    AMapLocationClientOption mLocationOption;
    MyLocationStyle myLocationStyle;
    private PoiSearch.Query query;
    private PoiSearch poiSearch;
    ImageView test_map_pop_rv_img;
    ImageView test_map_pop_rv_img_s;
    RecyclerView recyclerView;
    RecyclerView test_map_pop_rv_S_s;
    private TestMapPopwindowAdapter adapter;
    private TestMapPopwindowAdapter_S adapter_s;
    private ArrayList<PoiItem> pois;
    private String pcd;
    private Marker marker;
    private String la;
    private String lo;
    private String qy;
    private String xq;

    RelativeLayout test_map_back;
    RelativeLayout test_map_rl_2;
    TextView test_map_city;
    TextView test_map_search_tv;
    EditText test_map_search;

    int isNum = 0;
    private GeocodeSearch geocoderSearch;
    private Intent intent;
    private InputMethodManager imm;
    private AMapLocation aMapLocation1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_map);

        StatusBar.makeStatusBarTransparent(this);

        la = getIntent().getStringExtra("La");
        lo = getIntent().getStringExtra("Lo");
        qy = getIntent().getStringExtra("qy");
        xq = getIntent().getStringExtra("xq");

        Log.i("高德地图", "la:" + la);
        Log.i("高德地图", "lo:" + lo);

        adapter = new TestMapPopwindowAdapter();
        adapter.setOnTestMap(this);

        adapter_s = new TestMapPopwindowAdapter_S();
        adapter_s.setOnTestMap(this);

        recyclerView = findViewById(R.id.test_map_pop_rv_S);
        test_map_back = findViewById(R.id.test_map_back);
        test_map_city = findViewById(R.id.test_map_city);
        test_map_search_tv = findViewById(R.id.test_map_search_tv);
        test_map_search = findViewById(R.id.test_map_search);
        test_map_rl_2 = findViewById(R.id.test_map_rl_2);
        test_map_pop_rv_img_s = findViewById(R.id.test_map_pop_rv_img_s);
        test_map_pop_rv_S_s = findViewById(R.id.test_map_pop_rv_S_s);


        test_map_pop_rv_img = findViewById(R.id.test_map_pop_rv_img);

        //获取地图控件引用
        mapView = findViewById(R.id.map);
        //在activity执行onCreate时执行mMapView.onCreate(savedInstanceState)，创建地图
        mapView.onCreate(savedInstanceState);

        //定义了一个地图view
        mapView = (MapView) findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);// 此方法须覆写，虚拟机需要在很多情况下保存地图绘制的当前状态。
//初始化地图控制器对象

        if (aMap == null) {
            aMap = mapView.getMap();
        }

        test_map_back.setOnClickListener(this);
        test_map_city.setOnClickListener(this);
        test_map_search_tv.setOnClickListener(this);
        test_map_search.setOnClickListener(this);

        initView();

    }

    private void initView() {

        //去掉放大缩小按钮
        mUiSettings = aMap.getUiSettings();//实例化UiSettings类对象
        mUiSettings.setZoomControlsEnabled(false);


        // 设置定位监听
        aMap.setLocationSource(this);
// 设置为true表示显示定位层并可触发定位，false表示隐藏定位层并不可触发定位，默认是false
        aMap.setMyLocationEnabled(true);

        mUiSettings.setMyLocationButtonEnabled(true); //显示默认的定位按钮
//        mUiSettings.setLogoPosition(AMapOptions.LOGO_POSITION_BOTTOM_RIGHT);

// 设置定位的类型为定位模式，有定位、跟随或地图根据面向方向旋转几种
        aMap.setMyLocationType(AMap.LOCATION_TYPE_LOCATE);
        aMap.setOnMyLocationChangeListener(new AMap.OnMyLocationChangeListener() {
            @Override
            public void onMyLocationChange(Location location) {
                if (isNum == 0) {
                    Log.i("高德地图", "provider:" + location.getLatitude());
                    Log.i("高德地图", "provider:" + location.getLongitude());
                    geocoderSearch = new GeocodeSearch(TestMapActivity.this);
                    geocoderSearch.setOnGeocodeSearchListener(TestMapActivity.this);

                    RegeocodeQuery query = new RegeocodeQuery(new LatLonPoint(location.getLatitude(), location.getLongitude()), 200, GeocodeSearch.AMAP);
                    geocoderSearch.getFromLocationAsyn(query);

                    isNum = 1;
                }

            }
        });
        myLocationStyle = new MyLocationStyle();//初始化定位蓝点样式类myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE);//连续定位、且将视角移动到地图中心点，定位点依照设备方向旋转，并且会跟随设备移动。（1秒1次定位）如果不设置myLocationType，默认也会执行此种模式。
        ////定位一次，且将视角移动到地图中心点。
        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATE);

        //定位图标
        myLocationStyle.myLocationIcon(com.amap.api.maps.model.BitmapDescriptorFactory.fromBitmap(BitmapFactory
                .decodeResource(getResources(), R.mipmap.ios_location)));
        //精度圆圈颜色
        myLocationStyle.strokeColor(Color.parseColor("#00000000"));
        myLocationStyle.radiusFillColor(Color.parseColor("#00000000"));

        aMap.setMyLocationStyle(myLocationStyle);
        aMap.setMyLocationEnabled(true);


        /**
         * 高德地图拖拽事件
         */
        AMap.OnCameraChangeListener onCameraChangeListener = new AMap.OnCameraChangeListener() {
            @Override
            public void onCameraChange(CameraPosition cameraPosition) {
                //实时监听
            }

            @Override
            public void onCameraChangeFinish(CameraPosition cameraPosition) {
                //拖拽结束监听
                Log.i("高德地图", "onCameraChangeFinish：" + cameraPosition.target);

                RegeocodeQuery query = new RegeocodeQuery(new LatLonPoint(cameraPosition.target.latitude, cameraPosition.target.longitude), 200, GeocodeSearch.AMAP);
                geocoderSearch.getFromLocationAsyn(query);

                poiSearch.setBound(new PoiSearch.SearchBound(new LatLonPoint(cameraPosition.target.latitude,
                        cameraPosition.target.longitude), 10000));//设置周边搜索的中心点以及半径
                poiSearch.searchPOIAsyn();
                if (la.equals("") || la.equals("")) {

                } else {
                    CameraUpdate mCameraUpdate = CameraUpdateFactory.newCameraPosition(new CameraPosition(new LatLng(Double.parseDouble(la), Double.parseDouble(lo)), 10, 0, 0));
                    aMap.moveCamera(mCameraUpdate);
                }

                FinalContents.setMylatLng(cameraPosition.target);

            }
        };

        aMap.setOnCameraChangeListener(onCameraChangeListener);

    }

    /**
     * 激活定位
     */
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

    /**
     * 停止定位
     */
    @Override
    public void deactivate() {
        mListener = null;
        if (mlocationClient != null) {
            mlocationClient.stopLocation();
            mlocationClient.onDestroy();
        }
        mlocationClient = null;
    }

    /**
     * 定位成功后回调函数
     */
    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        if (mListener != null && aMapLocation != null) {
            if (aMapLocation != null
                    && aMapLocation.getErrorCode() == 0) {
                aMapLocation1 = aMapLocation;
                mListener.onLocationChanged(aMapLocation);// 显示系统小蓝点
            } else {
                String errText = "定位失败," + aMapLocation.getErrorCode() + ": " + aMapLocation.getErrorInfo();
                Log.e("AmapErr", errText);
            }
        }
    }

    /**
     * poi检索1
     */
    @Override
    public void onPoiSearched(PoiResult poiResult, int i) {
//        for (int j = 0; j < poiResult.getPois().size(); ++j) {
//            Log.i("高德地图", "getTitle:" + poiResult.getPois().get(j).getTitle());//名字
//            Log.i("高德地图", "getSnippet:" + poiResult.getPois().get(j).getSnippet());//地址
//            Log.i("高德地图", "getAdName:" + poiResult.getPois().get(j).getAdName());//区
//            Log.i("高德地图", "getProvinceName:" + poiResult.getPois().get(j).getProvinceName());//省
//            Log.i("高德地图", "getCityName:" + poiResult.getPois().get(j).getCityName());//市
//            Log.i("高德地图", "------------------------------------------------------------------");
//
//        }
        pois = poiResult.getPois();
        if (test_map_rl_2.getVisibility() == View.VISIBLE) {//搜索框poi检索
            Log.i("高德地图", "VISIBLE");
            if (pois.size() == 0) {
                Log.i("高德地图", "size0");
                test_map_pop_rv_img_s.setVisibility(View.VISIBLE);
                test_map_pop_rv_S_s.setVisibility(View.GONE);
            } else {
                Log.i("高德地图", "size：" + pois.size());
                test_map_pop_rv_img_s.setVisibility(View.GONE);
                test_map_pop_rv_S_s.setVisibility(View.VISIBLE);
                LinearLayoutManager manager = new LinearLayoutManager(TestMapActivity.this);
                manager.setOrientation(LinearLayoutManager.VERTICAL);
                test_map_pop_rv_S_s.setLayoutManager(manager);
                adapter_s.setAllPoi(pois);
                test_map_pop_rv_S_s.setAdapter(adapter_s);
                adapter_s.notifyDataSetChanged();
            }
        } else {//拖拽poi检索
            Log.i("高德地图", "GONE");
            if (pois.size() == 0) {

                test_map_pop_rv_img.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.GONE);

            } else {

                test_map_pop_rv_img.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);

                LinearLayoutManager manager = new LinearLayoutManager(TestMapActivity.this);
                manager.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerView.setLayoutManager(manager);
                adapter.setAllPoi(pois);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
        }


    }

    /**
     * poi检索2
     */
    @Override
    public void onPoiItemSearched(PoiItem poiItem, int i) {
        Log.i("高德地图", "onPoiItemSearched:");
    }

    /**
     * 检索点击事件
     */
    @Override
    public void TestMap(int position) {

        pcd = pois.get(position).getProvinceName() + "/" + pois.get(position).getCityName() + "/" + pois.get(position).getAdName();

        Intent intent = new Intent();
        //纬度
        intent.putExtra("getLatitude", pois.get(position).getLatLonPoint().getLatitude() + "");
        //经度
        intent.putExtra("getLongitude", pois.get(position).getLatLonPoint().getLongitude() + "");
        //地址
        intent.putExtra("address", pois.get(position).getSnippet() + "");
        //省市区
        intent.putExtra("pcd", pcd + "");

        setResult(RESULT_OK, intent);
        finish();

    }

    /**
     * 所有点击事件
     */
    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.test_map_back://返回上一层
                intent = new Intent();
                //纬度
                intent.putExtra("getLatitude", la);
                //经度
                intent.putExtra("getLongitude", lo);
                //地址
                intent.putExtra("address", xq);
                //省市区
                intent.putExtra("pcd", qy);

                setResult(RESULT_OK, intent);
                finish();
                imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
                break;
            case R.id.test_map_city://城市选择
                FinalContents.setNationalCityName(test_map_city.getText().toString());
//                intent = new Intent(TestMapActivity.this, TheNationalCityActivity.class);
//                startActivity(intent);
                intent = new Intent(TestMapActivity.this, TheNationalCityActivity.class);
                startActivityForResult(intent,TheNationalCityActivity.CITY_SELECT_RESULT_FRAG);
                break;
            case R.id.test_map_search_tv://取消
                test_map_search_tv.setVisibility(View.GONE);
                test_map_rl_2.setVisibility(View.GONE);
                imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
                break;
            case R.id.test_map_search://搜索
                test_map_search_tv.setVisibility(View.VISIBLE);
                test_map_rl_2.setVisibility(View.VISIBLE);

                initSearch();

                break;
        }

    }

    /**
     * 搜索框搜索
     */
    private void initSearch() {
        Log.i("高德地图", "initSearch");
        test_map_search.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.i("高德地图", "onTextChanged");
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Log.i("高德地图", "beforeTextChanged");
            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.i("高德地图", "afterTextChanged");

                String s1 = s + "";
                String s2 = test_map_city.getText().toString();
                if (s1.equals("")) {
                    test_map_pop_rv_img_s.setVisibility(View.VISIBLE);
                    test_map_pop_rv_S_s.setVisibility(View.GONE);
                    Log.i("高德地图", "空");

                } else {
                    test_map_pop_rv_img_s.setVisibility(View.GONE);
                    test_map_pop_rv_S_s.setVisibility(View.VISIBLE);
                    Log.i("高德地图", "s1:" + s1);
                    query = new PoiSearch.Query(s1, "", s2);
                    poiSearch = new PoiSearch(TestMapActivity.this, query);
                    poiSearch.setOnPoiSearchListener(TestMapActivity.this);
                    Log.i("高德地图","高德地图:" + aMapLocation1.getLatitude());
                    Log.i("高德地图","高德地图:" + aMapLocation1.getLongitude());
                    query.setLocation(new LatLonPoint(aMapLocation1.getLatitude(),aMapLocation1.getLongitude()));
                    query.setDistanceSort(true);
                    query.setCityLimit(true);
                    query.setPageSize(10);// 设置每页最多返回多少条poiitem
                    query.setPageNum(0);//设置查询页码
                    poiSearch.searchPOIAsyn();
                }
            }
        });

    }

    /**
     * 搜索框检索点击事件
     */
    @Override
    public void TestMapS(int position) {
        test_map_rl_2.setVisibility(View.GONE);

        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);

        pcd = pois.get(position).getProvinceName() + "/" + pois.get(position).getCityName() + "/" + pois.get(position).getAdName();

        Intent intent = new Intent();
        //纬度
        intent.putExtra("getLatitude", pois.get(position).getLatLonPoint().getLatitude() + "");
        //经度
        intent.putExtra("getLongitude", pois.get(position).getLatLonPoint().getLongitude() + "");
        //地址
        intent.putExtra("address", pois.get(position).getSnippet() + "");
        //省市区
        intent.putExtra("pcd", pcd + "");

        setResult(RESULT_OK, intent);
        finish();

        //跳转指定位置
//        CameraUpdate mCameraUpdate = CameraUpdateFactory.newCameraPosition(new CameraPosition(new LatLng(pois.get(position).getLatLonPoint().getLatitude(),pois.get(position).getLatLonPoint().getLongitude()), 10, 0, 0));
//        aMap.moveCamera(mCameraUpdate);
    }

    /**
     * 坐标转地址
     */
    @Override
    public void onRegeocodeSearched(RegeocodeResult regeocodeResult, int i) {

        Log.i("高德地图", " getCity():" + regeocodeResult.getRegeocodeAddress().getCity());
        test_map_city.setText(regeocodeResult.getRegeocodeAddress().getCity());
        query = new PoiSearch.Query("写字楼|小区|学校|医院|公交车站", "", "");
        poiSearch = new PoiSearch(TestMapActivity.this, query);
        poiSearch.setOnPoiSearchListener(TestMapActivity.this);

    }

    /**
     * 地址转坐标
     */
    @Override
    public void onGeocodeSearched(GeocodeResult geocodeResult, int i) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，销毁地图
        mapView.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView.onResume ()，重新绘制加载地图
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，暂停地图的绘制
        mapView.onPause();
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        test_map_city.setText(FinalContents.getNationalCityName());

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，保存地图当前的状态
        mapView.onSaveInstanceState(outState);
    }

    /**
     * 监听Back键按下事件
     */
    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        intent = new Intent();
        //纬度
        intent.putExtra("getLatitude", la);
        //经度
        intent.putExtra("getLongitude", lo);
        //地址
        intent.putExtra("address", xq);
        //省市区
        intent.putExtra("pcd", qy);

        setResult(RESULT_OK, intent);
        finish();
        Log.i("键", "点击了回退键");

    }

}

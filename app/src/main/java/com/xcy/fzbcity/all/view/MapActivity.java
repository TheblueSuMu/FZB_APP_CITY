package com.xcy.fzbcity.all.view;

import android.Manifest;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.AMapUtils;
import com.amap.api.maps.CameraUpdate;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.MapView;
import com.amap.api.maps.UiSettings;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;
import com.amap.api.maps.model.NaviPara;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.geocoder.GeocodeSearch;

import com.amap.api.services.poisearch.PoiResult;
import com.amap.api.services.poisearch.PoiSearch;
import com.google.android.material.tabs.TabLayout;
import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.api.FinalContents;
import com.xcy.fzbcity.all.persente.SingleClick;
import com.xcy.fzbcity.all.persente.StatusBar;
import com.xcy.fzbcity.all.utils.CommonUtil;
import com.xcy.fzbcity.all.utils.ToastUtil;

import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.util.List;

public class MapActivity extends AllActivity implements View.OnClickListener, PoiSearch.OnPoiSearchListener, LocationSource, AMapLocationListener {

    public static String GAODE_MAP = "com.autonavi.minimap";

    ImageView map_navigation;
    TabLayout map_tab_layout;
    LinearLayout map_return;
    //    TODO Map
    private double d = 0;
    private double o = 0;

    MapView mMapView = null;
    AMap aMap = null;
    private PoiSearch.Query query;
    private PoiSearch poiSearch;
    MyLocationStyle myLocationStyle;

    private GeocodeSearch geocoderSearch;
    private UiSettings mUiSettings;//定义一个UiSettings对象
    OnLocationChangedListener mListener;
    AMapLocationClient mlocationClient;
    AMapLocationClientOption mLocationOption;
    private AMapLocation aMapLocation1;
    private MarkerOptions markerOption;

    private LocationManager locationManager;
    private String locationProvider;
    private double longitude;
    private double latitude;
    private PoiResult poiResult1;
    private String title;
    private String selectLL;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        init_No_Network(savedInstanceState);
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    private void init_No_Network(Bundle savedInstanceState) {
        boolean networkAvailable = CommonUtil.isNetworkAvailable(this);
        if (networkAvailable) {
            Intent intent = getIntent();
            String office = "" + intent.getStringExtra("office");
            selectLL = "" + intent.getStringExtra("selectLL");
            if (office.equals("1")) {
                d = FinalContents.getD();
                o = FinalContents.getO();
            } else if (office.equals("0")) {
                d = FinalContents.getD1();
                o = FinalContents.getO1();
            } else {
                d = FinalContents.getD();
                o = FinalContents.getO();
            }

            Log.i("数据", "d:" + d);
            Log.i("数据", "o:" + o);

            initView(savedInstanceState);
            initMap();
        } else {
            RelativeLayout all_no_network = findViewById(R.id.all_no_network);
            Button all_no_reload = findViewById(R.id.all_no_reload);

            all_no_network.setVisibility(View.VISIBLE);
            all_no_reload.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    init_No_Network(savedInstanceState);
                }
            });
            ToastUtil.showToast(this, "当前无网络，请检查网络后再进行登录");
        }
    }

    private void initView(Bundle savedInstanceState) {
        StatusBar.makeStatusBarTransparent(this);

        map_navigation = findViewById(R.id.map_navigation);
        map_return = findViewById(R.id.map_return);
        map_tab_layout = findViewById(R.id.map_tab_layout);

        map_tab_layout.addTab(map_tab_layout.newTab().setText("交通出行"));
        map_tab_layout.addTab(map_tab_layout.newTab().setText("教育教学"));
        map_tab_layout.addTab(map_tab_layout.newTab().setText("医疗健康"));
        map_tab_layout.addTab(map_tab_layout.newTab().setText("商场购物"));
        map_tab_layout.addTab(map_tab_layout.newTab().setText("生活娱乐"));
        map_tab_layout.addTab(map_tab_layout.newTab().setText("著名景点"));
        Log.i("数据", "selectLL:" + selectLL);
        if(selectLL.equals("0")){
            map_tab_layout.getTabAt(0).select();
            title = "交通出行";
            initTestBtn("交通服务相关|公交站|地铁站|摆渡车站|机场出发/到达|飞机场|火车站|客运站");
        }else if(selectLL.equals("1")){
            map_tab_layout.getTabAt(1).select();
            title = "教育教学";
            initTestBtn("学校|高等院校|中学|小学|幼儿园|成人教育|职业技术学校");
        }else if(selectLL.equals("2")){
            map_tab_layout.getTabAt(2).select();
            title = "医疗健康";
            initTestBtn("医院|专科医院|综合医院|诊所|急救中心");
        }else if(selectLL.equals("3")){
            map_tab_layout.getTabAt(3).select();
            title = "商场购物";
            initTestBtn("商场|超级市场|购物中心|普通商场|便利店|便民商店|综合市场");
        }else if(selectLL.equals("4")){
            map_tab_layout.getTabAt(4).select();
            title = "生活娱乐";
            initTestBtn("生活娱乐|娱乐场所|KTV|迪厅|酒吧|电影院|休闲场所|音乐厅|剧场");
        }else if(selectLL.equals("5")){
            map_tab_layout.getTabAt(5).select();
            title = "著名景点";
            initTestBtn("著名景点|公园广场|风景名胜");
        }


        map_tab_layout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                title = tab.getText().toString();
                if (tab.getText().equals("交通出行")) {
                    CameraUpdate mCameraUpdate = CameraUpdateFactory.newCameraPosition(new CameraPosition(new LatLng(o, d), 16, 0, 0));
                    aMap.moveCamera(mCameraUpdate);
                    initTestBtn("交通服务相关|公交站|地铁站|摆渡车站|机场出发/到达|飞机场|火车站|客运站");
                } else if (tab.getText().equals("教育教学")) {
                    CameraUpdate mCameraUpdate = CameraUpdateFactory.newCameraPosition(new CameraPosition(new LatLng(o, d), 16, 0, 0));
                    aMap.moveCamera(mCameraUpdate);
                    initTestBtn("学校|高等院校|中学|小学|幼儿园|成人教育|职业技术学校");
                } else if (tab.getText().equals("医疗健康")) {
                    CameraUpdate mCameraUpdate = CameraUpdateFactory.newCameraPosition(new CameraPosition(new LatLng(o, d), 16, 0, 0));
                    aMap.moveCamera(mCameraUpdate);
                    initTestBtn("医院|专科医院|综合医院|诊所|急救中心");
                } else if (tab.getText().equals("商场购物")) {
                    CameraUpdate mCameraUpdate = CameraUpdateFactory.newCameraPosition(new CameraPosition(new LatLng(o, d), 16, 0, 0));
                    aMap.moveCamera(mCameraUpdate);
                    initTestBtn("商场|超级市场|购物中心|普通商场|便利店|便民商店|综合市场");
                } else if (tab.getText().equals("生活娱乐")) {
                    CameraUpdate mCameraUpdate = CameraUpdateFactory.newCameraPosition(new CameraPosition(new LatLng(o, d), 16, 0, 0));
                    aMap.moveCamera(mCameraUpdate);
                    initTestBtn("生活娱乐|娱乐场所|KTV|迪厅|酒吧|电影院|休闲场所|音乐厅|剧场");
                } else if (tab.getText().equals("著名景点")) {
                    CameraUpdate mCameraUpdate = CameraUpdateFactory.newCameraPosition(new CameraPosition(new LatLng(o, d), 16, 0, 0));
                    aMap.moveCamera(mCameraUpdate);
                    initTestBtn("著名景点|公园广场|风景名胜");
//                    message.setText(projectDetailsBeanData.getMatingInformation().getFamousScene());
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

        mMapView = (MapView) findViewById(R.id.map);
        //在activity执行onCreate时执行mMapView.onCreate(savedInstanceState)，创建地图
        mMapView.onCreate(savedInstanceState);

        if (aMap == null) {
            aMap = mMapView.getMap();
        }
        if (ContextCompat.checkSelfPermission(MapActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {//未开启定位权限
            //开启定位权限,200是标识码
            ActivityCompat.requestPermissions(MapActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 200);
        } else {
//            initMap();
            ToastUtil.showToast(MapActivity.this, "已开启定位权限");
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void initMap() {

        getLocation();


        CameraUpdate mCameraUpdate = CameraUpdateFactory.newCameraPosition(new CameraPosition(new LatLng(o, d), 16, 0, 0));
        aMap.moveCamera(mCameraUpdate);

        markerOption = new MarkerOptions();
        markerOption.position(new LatLng(o, d));

        markerOption.draggable(true);//设置Marker可拖动
        markerOption.icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory
                .decodeResource(getResources(), R.mipmap.zhen1)));
        // 将Marker设置为贴地显示，可以双指下拉地图查看效果
        markerOption.setFlat(true);//设置marker平贴地图效果
        aMap.addMarker(markerOption);

        Log.i("当前缩放比例","当前缩放比例:" + aMap.getCameraPosition().zoom);

    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    public void getLocation() {
        //获取地理位置管理器
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        //获取所有可用的位置提供器
        List<String> providers = locationManager.getProviders(true);
        if (providers.contains(LocationManager.GPS_PROVIDER)) {
            //如果是GPS
            locationProvider = LocationManager.GPS_PROVIDER;
        } else if (providers.contains(LocationManager.NETWORK_PROVIDER)) {
            //如果是Network
            locationProvider = LocationManager.NETWORK_PROVIDER;
        } else {
            Toast.makeText(this, "没有可用的位置提供器", Toast.LENGTH_SHORT).show();
            return;
        }
        //获取Location
        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    Activity#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for Activity#requestPermissions for more details.
            return;
        }
        Location location = locationManager.getLastKnownLocation(locationProvider);
        if (location != null) {
            //不为空,显示地理位置经纬度
            showLocation(location);
        }
        //监视地理位置变化
        locationManager.requestLocationUpdates(locationProvider, 3000, 1, locationListener);

    }

    //显示坐标
    private void showLocation(Location location) {
        String locationStr = "维度：" + location.getLatitude() + "\n"
                + "经度：" + location.getLongitude();
        latitude = location.getLatitude();
        longitude = location.getLongitude();
        Log.i("当前定位", "location.getLatitude():" + location.getLatitude());//39.168646
        Log.i("当前定位", "location.getLatitude():" + location.getLongitude());//117.24614
    }

    /**
     * LocationListern监听器
     * 参数：地理位置提供器、监听位置变化的时间间隔、位置变化的距离间隔、LocationListener监听器
     */

    LocationListener locationListener = new LocationListener() {

        @Override
        public void onStatusChanged(String provider, int status, Bundle arg2) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }

        @Override
        public void onLocationChanged(Location location) {
            //如果位置发生变化,重新显示
            showLocation(location);

        }
    };


    @SingleClick(1000)
    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.map_return:
                finish();
                break;
            case R.id.map_navigation://周边检索

//
//
//                markerOption.position(new LatLng(o,d));
//                markerOption.title("西安市").snippet("西安市：34.341568, 108.940174");
//
//                markerOption.draggable(true);//设置Marker可拖动
//                markerOption.icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory
//                        .decodeResource(getResources(),R.mipmap.ios_location)));
//                // 将Marker设置为贴地显示，可以双指下拉地图查看效果
//                markerOption.setFlat(true);//设置marker平贴地图效果
//                aMap.addMarker(markerOption);

                initgh();
//                initTestBtn("餐厅");
                break;
        }

    }

    //TODO 周边检索
    private void initTestBtn(final String RetrieveName) {

        query = new PoiSearch.Query(RetrieveName, "", "");
        query.setPageSize(20);// 设置每页最多返回多少条poiitem
        query.setPageNum(0);//设置查询页码
        poiSearch = new PoiSearch(this, query);
        poiSearch.setOnPoiSearchListener(this);
        poiSearch.setBound(new PoiSearch.SearchBound(new LatLonPoint(d,
                o), 5000));
        poiSearch.searchPOIAsyn();
    }

    //TODO 路线规划
    private void initgh() {

        boolean installed = isInstalled(MapActivity.this, GAODE_MAP);
        if (installed == true) {
            Log.i("路线规划", "有");
            toGaoDeRoute(MapActivity.this, GAODE_MAP, "", latitude + "", longitude + "", "", "", o + "", d + "", "", "0", "0");
        } else {
            ToastUtil.showLongToast(MapActivity.this, "未安装高德地图,下载安装后重试");
        }


    }


    /**
     * 启动高德App进行路线规划导航 http://lbs.amap.com/api/amap-mobile/guide/android/route
     *
     * @param context
     * @param sourceApplication 必填 第三方调用应用名称。如 "appName"
     * @param sid
     * @param sla
     * @param slon
     * @param sname
     * @param did
     * @param dlat
     * @param dlon
     * @param dName
     * @param dev               起终点是否偏移(0:lat 和 lon 是已经加密后的,不需要国测加密; 1:需要国测加密)
     * @param t                 t = 0（驾车）= 1（公交）= 2（步行）= 3（骑行）= 4（火车）= 5（长途客车）
     *                          （骑行仅在V788以上版本支持）
     */
    public static void toGaoDeRoute(Context context, String sourceApplication
            , String sid, String sla, String slon, String sname
            , String did, String dlat, String dlon, String dName
            , String dev, String t) {

        Log.i("路线规划", "sla:" + sla);
        Log.i("路线规划", "slon:" + slon);
        Log.i("路线规划", "dlat:" + dlat);
        Log.i("路线规划", "dlon:" + dlon);

        StringBuffer stringBuffer = new StringBuffer("androidamap://route/plan?sourceApplication=").append(sourceApplication);
        if (!TextUtils.isEmpty(sid)) {
            stringBuffer.append("&sid=").append(sid);
        }
        if (!TextUtils.isEmpty(sla)) {
            stringBuffer.append("&sla=").append(sla);
        }
        if (!TextUtils.isEmpty(sla)) {
            stringBuffer.append("&sla=").append(sla);
        }
        if (!TextUtils.isEmpty(slon)) {
            stringBuffer.append("&slon=").append(slon);
        }
        if (!TextUtils.isEmpty(sname)) {
            stringBuffer.append("&sname=").append(sname);
        }
        if (!TextUtils.isEmpty(did)) {
            stringBuffer.append("&did=").append(did);
        }
        stringBuffer.append("&dlat=").append(dlat);
        stringBuffer.append("&dlon=").append(dlon);
        stringBuffer.append("&dName=").append(dName);
        stringBuffer.append("&dev=").append(dev);
        stringBuffer.append("&t=").append(t);


        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_DEFAULT);

        //将功能Scheme以URI的方式传入data
        Uri uri = Uri.parse(stringBuffer.toString());
        intent.setData(uri);
        context.startActivity(intent);
    }

    public static boolean isInstalled(Context context, String packageName) {
        boolean installed = false;
        if (TextUtils.isEmpty(packageName)) {
            return false;
        }
        List<ApplicationInfo> installedApplications = context.getPackageManager().getInstalledApplications(0);
        for (ApplicationInfo in : installedApplications) {
            if (packageName.equals(in.packageName)) {
                installed = true;
                break;
            } else {
                installed = false;
            }
        }
        return installed;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，销毁地图
        mMapView.onDestroy();

        if (locationManager != null) {
            //移除监听器
            locationManager.removeUpdates(locationListener);
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView.onResume ()，重新绘制加载地图
        mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，暂停地图的绘制
        mMapView.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，保存地图当前的状态
        mMapView.onSaveInstanceState(outState);
    }

    //    TODO 动态打开gps
    @RequiresApi(api = Build.VERSION_CODES.M)
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

    //解析result获取POI信息
    @Override
    public void onPoiSearched(PoiResult poiResult, int i) {

        poiResult1 = poiResult;

        aMap.clear(true);

        markerOption = new MarkerOptions();
        markerOption.position(new LatLng(o, d));

        markerOption.draggable(true);//设置Marker可拖动
        markerOption.icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory
                .decodeResource(getResources(), R.mipmap.zhen1)));
        // 将Marker设置为贴地显示，可以双指下拉地图查看效果
        markerOption.setFlat(true);//设置marker平贴地图效果
        aMap.addMarker(markerOption);

        for (int g = 0; g < poiResult.getPois().size(); ++g) {
            Log.i("poi检索", "数据：" + poiResult.getPois().get(g).getLatLonPoint().getLongitude());
            Log.i("poi检索", "数据：" + poiResult.getPois().get(g).getLatLonPoint().getLatitude());

            markerOption.position(new LatLng(poiResult.getPois().get(g).getLatLonPoint().getLatitude(), poiResult.getPois().get(g).getLatLonPoint().getLongitude()));

            markerOption.draggable(true);//设置Marker可拖动
//        map_tab_layout.addTab(map_tab_layout.newTab().setText("交通出行"));
//        map_tab_layout.addTab(map_tab_layout.newTab().setText("教育教学"));
//        map_tab_layout.addTab(map_tab_layout.newTab().setText("医疗健康"));
//        map_tab_layout.addTab(map_tab_layout.newTab().setText("商场购物"));
//        map_tab_layout.addTab(map_tab_layout.newTab().setText("生活娱乐"));
//        map_tab_layout.addTab(map_tab_layout.newTab().setText("著名景点"));
//TODO 切换marker图标
            if(title.equals("交通出行")){
                markerOption.icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory
                        .decodeResource(getResources(), R.mipmap.traffic_img)));
            }else  if(title.equals("教育教学")){
                markerOption.icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory
                        .decodeResource(getResources(), R.mipmap.education_img)));
            }else  if(title.equals("医疗健康")){
                markerOption.icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory
                        .decodeResource(getResources(), R.mipmap.medical_treatme_imgnt)));
            }else  if(title.equals("商场购物")){
                markerOption.icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory
                        .decodeResource(getResources(), R.mipmap.shopping_img)));
            }else  if(title.equals("生活娱乐")){
                markerOption.icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory
                        .decodeResource(getResources(), R.mipmap.live_img)));
            }else  if(title.equals("著名景点")){
                markerOption.icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory
                        .decodeResource(getResources(), R.mipmap.scenic_spot_img)));
            }


            // 将Marker设置为贴地显示，可以双指下拉地图查看效果
            markerOption.setFlat(true);//设置marker平贴地图效果
            aMap.addMarker(markerOption);
        }
        aMap.setOnMarkerClickListener(new AMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                for (int i = 0; i < poiResult.getPois().size(); ++i) {
                    if (poiResult.getPois().get(i).getLatLonPoint().getLatitude() == marker.getPosition().latitude && poiResult.getPois().get(i).getLatLonPoint().getLongitude() == marker.getPosition().longitude) {
                        marker.setTitle(poiResult.getPois().get(i).getTitle());
//                        View inflate = LayoutInflater.from(MapActivity.this).inflate(R.layout.map_infowindow, null);
//                        TextView map_infowindow_tv = inflate.findViewById(R.id.map_infowindow_tv);
//                        map_infowindow_tv.setText(poiResult.getPois().get(i).getTitle());
//                        marker.setIcon(BitmapDescriptorFactory.fromView(inflate));
//                        marker.setIcon(BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.mapbs)));
                        break;
                    }
                }
                return false;
            }
        });
        aMap.setInfoWindowAdapter(new AMap.ImageInfoWindowAdapter() {
            @Override
            public long getInfoWindowUpdateTime() {
                return 0;
            }

            View infoWindow = null;

            @Override
            public View getInfoWindow(Marker marker) {
                if (infoWindow == null) {
                    infoWindow = LayoutInflater.from(MapActivity.this).inflate(
                            R.layout.map_infowindow, null);
                }
                render(marker, infoWindow);
                return infoWindow;
            }

            @Override
            public View getInfoContents(Marker marker) {
                return null;
            }
        });
//        aMap.setOnInfoWindowClickListener(new AMap.OnInfoWindowClickListener() {
//            @Override
//            public void onInfoWindowClick(Marker marker) {
//
//            }
//        });
    }

    private void render(Marker marker, View infoWindow) {

        for (int i = 0; i < poiResult1.getPois().size(); ++i) {
            if (poiResult1.getPois().get(i).getLatLonPoint().getLatitude() == marker.getPosition().latitude && poiResult1.getPois().get(i).getLatLonPoint().getLongitude() == marker.getPosition().longitude) {
                TextView map_infowindow_tv = infoWindow.findViewById(R.id.map_infowindow_tv);

                float distance = AMapUtils.calculateLineDistance(new LatLng(poiResult1.getPois().get(i).getLatLonPoint().getLatitude(), poiResult1.getPois().get(i).getLatLonPoint().getLongitude()),
                        new LatLng(FinalContents.getO(), FinalContents.getD()));

                if (distance < 1000) {
                    int scale = 0;//设置尾数
                    int roundingMode = 4;//表示四舍五入，可以选择其他的舍值方式，例如去位等等
                    BigDecimal b = new BigDecimal(distance);
                    b = b.setScale(scale, roundingMode);
                    map_infowindow_tv.setText(poiResult1.getPois().get(i).getTitle() + b + "m");
                } else {
                    float v = distance / 1000;
                    int scale = 2;//设置尾数
                    int roundingMode = 4;//表示四舍五入，可以选择其他的舍值方式，例如去位等等
                    BigDecimal b = new BigDecimal(v);
                    b = b.setScale(scale, roundingMode);
                    map_infowindow_tv.setText(poiResult1.getPois().get(i).getTitle() + b + "km");
                }


//                        marker.setIcon(BitmapDescriptorFactory.fromView(inflate));
//                        marker.setIcon(BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.mapbs)));
                break;
            }
        }

    }

    @Override
    public void onPoiItemSearched(PoiItem poiItem, int i) {

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
                Log.i("当前定位", "getLongitude" + aMapLocation.getLongitude());
                Log.i("当前定位", "getLatitude" + aMapLocation.getLatitude());
            } else {
                String errText = "定位失败," + aMapLocation.getErrorCode() + ": " + aMapLocation.getErrorInfo();
                Log.e("AmapErr", errText);
            }
        }
    }


}
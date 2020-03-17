package com.xcy.fzbcity.all.view;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.SyncStateContract;
import android.text.Html;
import android.text.InputType;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdate;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.UiSettings;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.services.geocoder.GeocodeQuery;
import com.amap.api.services.geocoder.GeocodeResult;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.amap.api.services.geocoder.RegeocodeResult;
import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bumptech.glide.Glide;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.adapter.CompaniesBean;
import com.xcy.fzbcity.all.adapter.ProjectsBean;
import com.xcy.fzbcity.all.adapter.StoresBean;
import com.xcy.fzbcity.all.api.FinalContents;
import com.xcy.fzbcity.all.modle.CityBean;
import com.xcy.fzbcity.all.persente.StatusBar;
import com.xcy.fzbcity.all.service.MyService;
import com.xcy.fzbcity.all.utils.ToastUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import co.lujun.androidtagview.ColorFactory;
import co.lujun.androidtagview.TagContainerLayout;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MapHouseActivity extends AppCompatActivity implements View.OnClickListener, GeocodeSearch.OnGeocodeSearchListener {

    public static String GAODE_MAP = "com.autonavi.minimap";

    private int height;// 屏幕高度(px)
    private int width;// 屏幕宽度(px)
    MapView mMapView = null;
    private AMap aMap = null;
    private UiSettings mUiSettings;
    private List<CityBean.DataBean> data;
    LinearLayout map_house_return;
    EditText map_house_search;
    TextView map_house_check;
    private String mapCityId = "";
    private List<ProjectsBean.DataBean> projectsData1;
    private MarkerOptions markerOption;
    private List<StoresBean.DataBean> storesData;

    int ifStores = 0;
    private List<CompaniesBean.DataBean> companysData;
    private float zoom;
    private String ifMarkerClick = "";
    private View view;

    LinearLayout map_house_ll1;
    LinearLayout map_house_ll2;
    LinearLayout map_house_ll3;
    private TextView pop_title;
    private TextView pop_address;
    private TextView pop_name;
    private LinearLayout pop_ll_1;
    private Button pop_btn1;

    private LocationManager locationManager;
    private String locationProvider;
    private double longitude;
    private double latitude;
    private String vs2;
    private String vs1;
    private ImageView imageAvatar;
    private TextView nameText;
    private TagContainerLayout tagView;
    private TextView chick;
    private TextView attention;
    private TextView collect;
    private TextView transmit;
    private TextView price_money;
    private TextView price;
    private TextView square;
    private Button item_pop_btn;
    private TextView group_booking;
    private String projectType;
    private String projectId = "";
    private String ifClick = "0";
    private String title;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_house);

        StatusBar.makeStatusBarTransparent(this);
        map_house_check = findViewById(R.id.map_house_check);

        //获取手机屏幕的宽和高
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        width = dm.widthPixels;
        height = dm.heightPixels;
        mMapView = findViewById(R.id.map_s);
        //在activity执行onCreate时执行mMapView.onCreate(savedInstanceState)，创建地图
        mMapView.onCreate(savedInstanceState);
        if (aMap == null) {
            aMap = mMapView.getMap();
//            setUpMap();
            mUiSettings = aMap.getUiSettings();
            mUiSettings.setTiltGesturesEnabled(false);// 禁用倾斜手势。
            mUiSettings.setRotateGesturesEnabled(false);// 禁用旋转手势。
        }


        if (ContextCompat.checkSelfPermission(MapHouseActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {//未开启定位权限
            //开启定位权限,200是标识码
            ActivityCompat.requestPermissions(MapHouseActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 200);
        } else {
            if (FinalContents.getIfCity().equals("")) {
                Log.i("城市名","FinalContents.getOldCityName():" + FinalContents.getOldCityName());
                initLocation(FinalContents.getOldCityName());
                map_house_check.setText("门店");
                initView();
            } else {
                initCityData();//城市名字 + ID
            }
//            ToastUtil.showToast(TestMapActivity.this, "已开启定位权限", Toast.LENGTH_LONG).show();
        }

    }

    //城市名字 + ID
    private void initCityData() {
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Log.i("归根到底", "mapCityId:" + mapCityId);
        final Observable<ProjectsBean> ProjectsBean = fzbInterface.getProjectsBean(FinalContents.getCityID(), "", FinalContents.getUserID(), FinalContents.getIfCityType(), "1000");
        ProjectsBean.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ProjectsBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ProjectsBean projectsBean) {
                        Log.i("归根到底", "initProjects:");
                        int isCityName = 0;
                        int isCityNameNum = 0;
//                        map_house_check.setText(data.get(i).getCity());
//                        mapCityId = data.get(i).getId();
//                        initLocation(data.get(i).getCity());
//                        initView();
                        if (projectsBean.getData().size() == 0) {
                            map_house_check.setText(FinalContents.getCityName());
                            initLocation(FinalContents.getCityName());
                            mapCityId = FinalContents.getCityID();
                        } else {
                            for (int i = 0; i < projectsBean.getData().size(); ++i) {
                                if (FinalContents.getOldCityName().equals(projectsBean.getData().get(i).getList().get(0).getName())) {
                                    isCityName = 1;
                                    isCityNameNum = i;
                                    break;
                                }
                            }
                            if (isCityName == 0) {
                                map_house_check.setText(projectsBean.getData().get(0).getList().get(0).getName());
                                mapCityId = FinalContents.getCityID();
                                initLocation(projectsBean.getData().get(0).getList().get(0).getName());
                            } else {
                                map_house_check.setText(projectsBean.getData().get(isCityNameNum).getList().get(0).getName());
                                mapCityId = FinalContents.getCityID();
                                initLocation(projectsBean.getData().get(isCityNameNum).getList().get(0).getName());
                            }

                        }
                        initView();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    //初始化数据
    private void initView() {

        map_house_return = findViewById(R.id.map_house_return);
        map_house_search = findViewById(R.id.map_house_search_S1);

        map_house_ll1 = findViewById(R.id.map_house_ll1);
        map_house_ll2 = findViewById(R.id.map_house_ll2);
        map_house_ll3 = findViewById(R.id.map_house_ll3);

        pop_title = findViewById(R.id.pop_title1);
        pop_address = findViewById(R.id.pop_address1);
        pop_name = findViewById(R.id.pop_name1);
        pop_ll_1 = findViewById(R.id.pop_ll_11);
        pop_btn1 = findViewById(R.id.pop_btn1);

        imageAvatar = findViewById(R.id.ImageViewss1);
        nameText = (TextView) findViewById(R.id.TextViewNamess1);
        tagView = findViewById(R.id.tagViewss1);
        chick = (TextView) findViewById(R.id.chickss1);
        attention = (TextView) findViewById(R.id.attentionss1);
        collect = (TextView) findViewById(R.id.collectss1);
        transmit = (TextView) findViewById(R.id.transmitss1);
        price_money = (TextView) findViewById(R.id.price_moneyss1);
        price = (TextView) findViewById(R.id.pricess1);
        square = (TextView) findViewById(R.id.squaress1);
        item_pop_btn = (Button) findViewById(R.id.item_pop_btn1);
        group_booking = findViewById(R.id.group_booking_itemss1);
        item_pop_btn.setVisibility(View.VISIBLE);

//        map_house_search.setInputType(InputType.TYPE_NULL);

        if (mapCityId.equals("")) {
            initStores();
        } else {
            initProjects();
        }

        item_pop_btn.setOnClickListener(this);
        map_house_return.setOnClickListener(this);
        map_house_check.setOnClickListener(this);
        map_house_ll1.setOnClickListener(this);
        pop_btn1.setOnClickListener(this);
        map_house_ll3.setOnClickListener(this);

        //输入框回车事件监听
//        map_house_search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
//            @Override
//            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
//                if ((keyEvent != null && KeyEvent.KEYCODE_ENTER == keyEvent.getKeyCode() && KeyEvent.ACTION_DOWN == keyEvent.getAction())) {
//                    title = map_house_search.getText().toString();
//                    if(title.equals("")){
//
//                    }else {
//                        if (ifClick.equals("")) {
//                            ifClick = "1";
//                            if (mapCityId.equals("")) {
//                                if (ifStores == 0) {
//                                    initStoresMarkerClick1(title);
//                                } else if (ifStores == 1) {
//                                    initCompaniesMarkerClick1(title);
//                                }
//                            } else {
//                                initProjectsMarkerClick1(title);
//                            }
//                        }
//                    }
//
//
//                }
//                return false;
//            }
//        });
        //输入框搜索事件监听
        map_house_search.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId,
                                          KeyEvent event) {
                if ((actionId == 0 || actionId == 3) && event != null) {
                    title = map_house_search.getText().toString();
                    if (title.equals("")) {
                        ToastUtil.showToast(MapHouseActivity.this, "输入框为空，请输入内容再进行查找");
                    } else {
                        if (ifClick.equals("0")) {
                            ifClick = "1";
                            if (mapCityId.equals("")) {
                                if (ifStores == 0) {
                                    initStoresMarkerClick1(title);
                                } else if (ifStores == 1) {
                                    initCompaniesMarkerClick1(title);
                                }
                            } else {
                                initProjectsMarkerClick1(title);
                            }
                        }
                    }
                }
                return false;
            }
        });

    }

    //定位地址
    private void initLocation(String name) {
        Log.i("MyCL", "name: " + name);
        GeocodeSearch geocoderSearch = new GeocodeSearch(this);
        geocoderSearch.setOnGeocodeSearchListener(this);

        GeocodeQuery query = new GeocodeQuery(name, name);
        geocoderSearch.getFromLocationNameAsyn(query);


    }

    //点击事件
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.map_house_return:
                finish();
                break;
            case R.id.map_house_check:
                initCheck();
                break;
            case R.id.map_house_ll1:
                if (mapCityId.equals("")) {
                    if (ifStores == 0) {
                        map_house_ll1.setVisibility(View.GONE);
                        map_house_ll2.setVisibility(View.GONE);
                        ifMarkerClick = "";
                        initStoresMarker4();
                    } else if (ifStores == 1) {
                        map_house_ll1.setVisibility(View.GONE);
                        map_house_ll2.setVisibility(View.GONE);
                        ifMarkerClick = "";
                        initCompanyMarker4();
                    }
                } else {
                    map_house_ll1.setVisibility(View.GONE);
                    map_house_ll3.setVisibility(View.GONE);
                    ifMarkerClick = "";
                    initProjectsMarker4();
                }
                break;
            case R.id.pop_btn1:
                //路线
                initPath();
                break;
            case R.id.item_pop_btn1:
                //路线
                initPath();
                break;
            case R.id.map_house_ll3:
                //路线
                FinalContents.setProjectID(projectId);
                FinalContents.setProjectType(projectType);
                Log.i("阿士大夫", "projectId:" + projectId);
                Log.i("阿士大夫", "projectType:" + projectType);
                Intent intent = new Intent(MapHouseActivity.this, ProjectDetails.class);
                startActivity(intent);
                break;
        }
    }

    //路线规划
    private void initPath() {

        boolean installed = isInstalled(MapHouseActivity.this, GAODE_MAP);
        if (installed == true) {
            Log.i("路线规划", "vs2:" + vs2);
            Log.i("路线规划", "vs1:" + vs1);
            toGaoDeRoute(MapHouseActivity.this, GAODE_MAP, "", latitude + "", longitude + "", "", "", vs2 + "", vs1 + "", "", "0", "0");
        } else {
            ToastUtil.showLongToast(MapHouseActivity.this, "未安装高德地图,下载安装后重试");
        }

    }

    //门店/城市选择
    private void initCheck() {

        final List<String> list = new ArrayList<>();

        if (mapCityId.equals("")) {
            list.add("公司");
            list.add("门店");
        } else {
            if (projectsData1.size() == 0) {
                list.add(FinalContents.getCityName());
            } else {
                for (int i = 0; i < projectsData1.size(); ++i) {
                    for (int j = 0; j < projectsData1.get(i).getList().size(); ++j) {
                        list.add(projectsData1.get(i).getList().get(j).getName());
                    }
                }
            }
        }


//        list.add("经纪人");


        OptionsPickerView pvOptions = new OptionsPickerBuilder(MapHouseActivity.this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                //              展示选中数据
                map_house_check.setText(list.get(options1));
//                if (FinalContents.getIfCity().equals("")) {
//                    if (options1 == 1) {//门店
//                        ifMG = 0;
////                    initMy();
//                        ifStart = 0;
//                        aMap.clear(true);
//                        markerOptionsList.clear();
//                        initView();
//                    } else if (options1 == 0) {//公司
//                        ifStart = 0;
//                        ifMG = 1;
////                    initMy();
//                        aMap.clear(true);
//                        markerOptionsList.clear();
//                        initView();
//                    }
//                } else {
//                    for (int i = 0; i < data.size(); ++i) {
//                        if (list.get(options1).equals(data.get(i).getCity())) {
//                            Log.i("", "");
//                            FinalContents.setIfCity(data.get(i).getId());
//                            geocoderSearch = new GeocodeSearch(MapHousesActivity.this);
//                            GeocodeQuery query = new GeocodeQuery(data.get(i).getCity(), data.get(i).getCity());
//                            geocoderSearch.getFromLocationNameAsyn(query);
//                            geocoderSearch.setOnGeocodeSearchListener(new GeocodeSearch.OnGeocodeSearchListener() {
//                                @Override
//                                public void onRegeocodeSearched(RegeocodeResult regeocodeResult, int i) {
//
//                                }
//
//                                @Override
//                                public void onGeocodeSearched(GeocodeResult geocodeResult, int i) {
////                                    geocodeResult.getGeocodeAddressList().get(0).getLatLonPoint();
//                                    CameraUpdate mCameraUpdate = CameraUpdateFactory.newCameraPosition(new CameraPosition(new LatLng(geocodeResult.getGeocodeAddressList().get(0).getLatLonPoint().getLatitude(), geocodeResult.getGeocodeAddressList().get(0).getLatLonPoint().getLongitude()), 12, 0, 0));
//                                    aMap.moveCamera(mCameraUpdate);
//                                }
//                            });
//                            ifStart = 0;
//                            aMap.clear(true);
//                            markerOptionsList.clear();
//                            ifMapStart = 1;
//                            initView();
//                        }
//                    }
//                }

                if (mapCityId.equals("")) {
                    aMap.clear();
                    if (options1 == 1) {//门店
                        ifStores = 0;
                        initStores();
                    } else if (options1 == 0) {//公司
                        ifStores = 1;
                        initCompanies();
                    }
                    initLocation(FinalContents.getOldCityName());
                } else {
//                    initProjectsData(list.get(options1));
//                    initLocation(list.get(options1));
                    if (projectsData1.size() == 0) {
                        initLocation(FinalContents.getCityName());
                    } else {
                        int i1 = projectsData1.get(options1).getList().get(0).getLatLon().indexOf(",");
                        CameraUpdate mCameraUpdate = CameraUpdateFactory.newCameraPosition(new CameraPosition(new LatLng(Double.parseDouble(projectsData1.get(options1).getList().get(0).getLatLon().substring(i1 + 1)), Double.parseDouble(projectsData1.get(options1).getList().get(0).getLatLon().substring(0, i1))), 11, 0, 0));
                        aMap.moveCamera(mCameraUpdate);
                    }

                }

            }
        })
//                .setSelectOptions(1)
                .setOutSideCancelable(false)//点击背的地方不消失
                .build();//创建

        if (mapCityId.equals("")) {
            if (map_house_check.getText().equals("门店")) {
                pvOptions.setSelectOptions(1);
            } else if (map_house_check.getText().equals("公司")) {
                pvOptions.setSelectOptions(0);
            }

        } else {
            if (projectsData1.size() == 0) {
                pvOptions.setSelectOptions(0);
            } else {
                for (int i = 0; i < projectsData1.size(); ++i) {
                    for (int j = 0; j < projectsData1.get(i).getList().size(); ++j) {
                        if (map_house_check.getText().equals(projectsData1.get(i).getList().get(j).getName())) {
                            pvOptions.setSelectOptions(i);
                            break;
                        }
                    }
                }
            }
        }

        //      把数据绑定到控件上面
        pvOptions.setPicker(list);
        //      展示
        pvOptions.show();

    }

    //城市切换数据
    private void initProjectsData(String title) {

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
                    public void onSubscribe(Disposable disposable) {

                    }

                    @Override
                    public void onNext(CityBean cityBean) {
                        Log.i("归根到底", "initProjectsData:");
                        data = cityBean.getData();
                        for (int i = 0; i < data.size(); ++i) {
                            if (data.get(i).getId().equals(title)) {
                                mapCityId = data.get(i).getId();
//                                initLocation(data.get(i).getCity());
                                initView();
                                break;
                            }
                        }

                    }

                    @Override
                    public void onError(Throwable throwable) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    //项目数据
    private void initProjects() {
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Log.i("归根到底", "mapCityId:" + mapCityId);
        final Observable<ProjectsBean> ProjectsBean = fzbInterface.getProjectsBean(mapCityId, "", FinalContents.getUserID(), FinalContents.getIfCityType(), "1000");
        ProjectsBean.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ProjectsBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ProjectsBean projectsBean) {
                        Log.i("归根到底", "initProjects:");
                        projectsData1 = projectsBean.getData();
                        if (projectsData1.size() == 0) {

                        } else {
                            initAmp();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    //缩放比例
    private void initAmp() {

        if(mapCityId.equals("")){
            if (ifStores == 0) {
                initStoresMarker2();
            } else if (ifStores == 1) {
                initCompanyMarker2();
            }
        }else {
            initProjectsMarker2();
        }

        aMap.setOnCameraChangeListener(new AMap.OnCameraChangeListener() {
            @Override
            public void onCameraChange(CameraPosition cameraPosition) {

            }

            @Override
            public void onCameraChangeFinish(CameraPosition cameraPosition) {
                float zoom = cameraPosition.zoom;
                Log.i("归根到底彻底", "zoom:" + zoom);
                if (mapCityId.equals("")) {
//                    if (zoom < 11) {
//                        Log.i("归根到底", "zoom < 10:");
//                        if (ifStores == 0) {
//                            initStoresMarker1();
//                        } else if (ifStores == 1) {
//                            initCompanyMarker1();
//                        }
//                    } else
                    if (zoom <= 11) {
                        Log.i("归根到底", "zoom >= 10 && zoom < 14:");
                        if (ifStores == 0) {
                            initStoresMarker2();
                        } else if (ifStores == 1) {
                            initCompanyMarker2();
                        }
                    } else if (zoom > 11 && zoom < 13) {
                        Log.i("归根到底", "zoom >= 14 && zoom < 18:");
                        if (ifStores == 0) {
                            initStoresMarker3();
                        } else if (ifStores == 1) {
                            initCompanyMarker3();
                        }
                    } else if (zoom >= 13) {
                        Log.i("归根到底", "zoom >= 18:");
                        if (ifStores == 0) {
                            initStoresMarker4();
                        } else if (ifStores == 1) {
                            initCompanyMarker4();
                        }
                    }

                } else {
//                    if (zoom < 11) {
//                        initProjectsMarker1();
//                    } else
                    if (zoom < 11) {
                        initProjectsMarker2();
                    } else if (zoom >= 11 && zoom < 13) {
                        initProjectsMarker3();
                    } else if (zoom >= 13) {
                        initProjectsMarker4();
                    }

                }
            }
        });

        aMap.setOnMarkerClickListener(new AMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                zoom = aMap.getCameraPosition().zoom;
                if (mapCityId.equals("")) {
                    if (ifStores == 0) {
                        if (zoom >= 13) {
                            initStoresMarkerClick(marker.getPosition());
                        } else {
                            if (zoom < 11) {
                                CameraUpdate cameraUpdate = CameraUpdateFactory.zoomTo(12);
                                aMap.moveCamera(cameraUpdate);
                            } else if (zoom >= 11 && zoom < 13) {
                                CameraUpdate cameraUpdate = CameraUpdateFactory.zoomTo(14);
                                aMap.moveCamera(cameraUpdate);
                            }

                        }
                    } else if (ifStores == 1) {
                        if (zoom >= 13) {
                            initCompaniesMarkerClick(marker.getPosition());
                        } else {
                            if (zoom < 11) {
                                CameraUpdate cameraUpdate = CameraUpdateFactory.zoomTo(12);
                                aMap.moveCamera(cameraUpdate);
                            } else if (zoom >= 11 && zoom < 13) {
                                CameraUpdate cameraUpdate = CameraUpdateFactory.zoomTo(14);
                                aMap.moveCamera(cameraUpdate);
                            }

                        }
                    }
                } else {
                    if (zoom >= 13) {
                        initProjectsMarkerClick(marker.getPosition());
                    } else {
                        if (zoom < 11) {
                            CameraUpdate cameraUpdate = CameraUpdateFactory.zoomTo(12);
                            aMap.moveCamera(cameraUpdate);
                        } else if (zoom >= 11 && zoom < 13) {
                            CameraUpdate cameraUpdate = CameraUpdateFactory.zoomTo(14);
                            aMap.moveCamera(cameraUpdate);
                        }

                    }
                }
                return false;
            }
        });

    }

    //项目marker点1
    private void initProjectsMarker1() {
        Log.i("归根到底", "initProjectsMarker:");
        aMap.clear();
        for (int i = 0; i < projectsData1.size(); ++i) {

            int i1 = projectsData1.get(i).getLatLon().indexOf(",");
            Log.i("归根到底", "0,i1:" + projectsData1.get(i).getLatLon().substring(0, i1));
            Log.i("归根到底", "i1:" + projectsData1.get(i).getLatLon().substring(i1 + 1));
            markerOption = new MarkerOptions();
            markerOption.position(new LatLng(Double.parseDouble(projectsData1.get(i).getLatLon().substring(i1 + 1)), Double.parseDouble(projectsData1.get(i).getLatLon().substring(0, i1))));
            markerOption.draggable(true);//设置Marker可拖动
            markerOption.icon(BitmapDescriptorFactory.fromView(getView(projectsData1.get(i).getName() + "\n" + projectsData1.get(i).getNum())));
            // 将Marker设置为贴地显示，可以双指下拉地图查看效果
            markerOption.setFlat(true);//设置marker平贴地图效果
            aMap.addMarker(markerOption);

        }

    }

    //项目marker点2
    private void initProjectsMarker2() {
        Log.i("归根到底", "initProjectsMarker:");
        aMap.clear();
        for (int i = 0; i < projectsData1.size(); ++i) {
            for (int j = 0; j < projectsData1.get(i).getList().size(); ++j) {
                int i1 = projectsData1.get(i).getList().get(j).getLatLon().indexOf(",");
                markerOption = new MarkerOptions();
                markerOption.position(new LatLng(Double.parseDouble(projectsData1.get(i).getList().get(j).getLatLon().substring(i1 + 1)), Double.parseDouble(projectsData1.get(i).getList().get(j).getLatLon().substring(0, i1))));
                markerOption.draggable(true);//设置Marker可拖动
                markerOption.icon(BitmapDescriptorFactory.fromView(getView(projectsData1.get(i).getList().get(j).getName() + "\n" + projectsData1.get(i).getList().get(j).getNum())));
                // 将Marker设置为贴地显示，可以双指下拉地图查看效果
                markerOption.setFlat(true);//设置marker平贴地图效果
                aMap.addMarker(markerOption);
            }
        }

    }

    //项目marker点3
    private void initProjectsMarker3() {
        Log.i("归根到底", "initProjectsMarker:");
        aMap.clear();
        for (int i = 0; i < projectsData1.size(); ++i) {
            for (int j = 0; j < projectsData1.get(i).getList().size(); ++j) {
                for (int q = 0; q < projectsData1.get(i).getList().get(j).getList().size(); ++q) {
                    int i1 = projectsData1.get(i).getList().get(j).getList().get(q).getLatLon().indexOf(",");
                    markerOption = new MarkerOptions();
                    markerOption.position(new LatLng(Double.parseDouble(projectsData1.get(i).getList().get(j).getList().get(q).getLatLon().substring(i1 + 1)), Double.parseDouble(projectsData1.get(i).getList().get(j).getList().get(q).getLatLon().substring(0, i1))));
                    markerOption.draggable(true);//设置Marker可拖动
                    markerOption.icon(BitmapDescriptorFactory.fromView(getView(projectsData1.get(i).getList().get(j).getList().get(q).getName() + "\n" + projectsData1.get(i).getList().get(j).getList().get(q).getNum())));
                    // 将Marker设置为贴地显示，可以双指下拉地图查看效果
                    markerOption.setFlat(true);//设置marker平贴地图效果
                    aMap.addMarker(markerOption);
                }
            }

        }

    }

    //项目marker点4
    private void initProjectsMarker4() {
        Log.i("归根到底", "initProjectsMarker:");
        aMap.clear();
        for (int i = 0; i < projectsData1.size(); ++i) {

            for (int j = 0; j < projectsData1.get(i).getList().size(); ++j) {
                for (int q = 0; q < projectsData1.get(i).getList().get(j).getList().size(); ++q) {
                    for (int w = 0; w < projectsData1.get(i).getList().get(j).getList().get(q).getList().size(); ++w) {
                        int i1 = projectsData1.get(i).getList().get(j).getList().get(q).getList().get(w).getLatLon().indexOf(",");
                        markerOption = new MarkerOptions();
                        markerOption.position(new LatLng(Double.parseDouble(projectsData1.get(i).getList().get(j).getList().get(q).getList().get(w).getLatLon().substring(i1 + 1)), Double.parseDouble(projectsData1.get(i).getList().get(j).getList().get(q).getList().get(w).getLatLon().substring(0, i1))));
                        markerOption.draggable(true);//设置Marker可拖动
//                        sb = new StringBuffer(rows2.get(i).getProjectName() + rows2.get(i).getProductUnitPrice() + rows2.get(i).getMonetaryUnit());
//                        if (projectsData1.get(i).getList().get(j).getList().get(q).getList().get(w).getStatus().equals("0")) {
                        markerOption.icon(BitmapDescriptorFactory.fromView(getView1(projectsData1.get(i).getList().get(j).getList().get(q).getList().get(w).getName() + projectsData1.get(i).getList().get(j).getList().get(q).getList().get(w).getProductUnitPrice() + projectsData1.get(i).getList().get(j).getList().get(q).getList().get(w).getMonetaryUnit())));
//                        } else if (projectsData1.get(i).getList().get(j).getList().get(q).getList().get(w).getStatus().equals("1")) {
//                            markerOption.icon(BitmapDescriptorFactory.fromView(getView1(projectsData1.get(i).getList().get(j).getList().get(q).getList().get(w).getName() + "(合作)")));
//                        } else if (projectsData1.get(i).getList().get(j).getList().get(q).getList().get(w).getStatus().equals("2")) {
//                            markerOption.icon(BitmapDescriptorFactory.fromView(getView1(projectsData1.get(i).getList().get(j).getList().get(q).getList().get(w).getName() + "(取消合作)")));
//                        } else if (projectsData1.get(i).getList().get(j).getList().get(q).getList().get(w).getStatus().equals("3")) {
//                            markerOption.icon(BitmapDescriptorFactory.fromView(getView1(projectsData1.get(i).getList().get(j).getList().get(q).getList().get(w).getName() + "(倒闭)")));
//                        }
                        // 将Marker设置为贴地显示，可以双指下拉地图查看效果
                        markerOption.setFlat(true);//设置marker平贴地图效果
                        aMap.addMarker(markerOption);
                    }
                }
            }

        }

    }

    //门店marker点1
    private void initStoresMarker1() {
        Log.i("归根到底", "initStoresMarker:");
        aMap.clear();
        for (int i = 0; i < storesData.size(); ++i) {
            int i1 = storesData.get(i).getLatLon().indexOf(",");
            markerOption = new MarkerOptions();
            markerOption.position(new LatLng(Double.parseDouble(storesData.get(i).getLatLon().substring(i1 + 1)), Double.parseDouble(storesData.get(i).getLatLon().substring(0, i1))));
            markerOption.draggable(true);//设置Marker可拖动
            markerOption.icon(BitmapDescriptorFactory.fromView(getView(storesData.get(i).getName() + "\n" + storesData.get(i).getNum())));
            // 将Marker设置为贴地显示，可以双指下拉地图查看效果
            markerOption.setFlat(true);//设置marker平贴地图效果
            aMap.addMarker(markerOption);
        }

    }

    //门店marker点2
    private void initStoresMarker2() {
        Log.i("归根到底", "initStoresMarker:");
        aMap.clear();
        for (int i = 0; i < storesData.size(); ++i) {
            for (int j = 0; j < storesData.get(i).getList().size(); ++j) {
                int i1 = storesData.get(i).getList().get(j).getLatLon().indexOf(",");
                markerOption = new MarkerOptions();
                markerOption.position(new LatLng(Double.parseDouble(storesData.get(i).getList().get(j).getLatLon().substring(i1 + 1)), Double.parseDouble(storesData.get(i).getList().get(j).getLatLon().substring(0, i1))));
                markerOption.draggable(true);//设置Marker可拖动
                markerOption.icon(BitmapDescriptorFactory.fromView(getView(storesData.get(i).getList().get(j).getName() + "\n" + storesData.get(i).getList().get(j).getNum())));
                // 将Marker设置为贴地显示，可以双指下拉地图查看效果
                markerOption.setFlat(true);//设置marker平贴地图效果
                aMap.addMarker(markerOption);
            }
        }

    }

    //门店marker点3
    private void initStoresMarker3() {
        Log.i("归根到底", "initStoresMarker:");
        aMap.clear();
        for (int i = 0; i < storesData.size(); ++i) {
            for (int j = 0; j < storesData.get(i).getList().size(); ++j) {
                for (int q = 0; q < storesData.get(i).getList().get(j).getList().size(); ++q) {
                    int i1 = storesData.get(i).getList().get(j).getList().get(q).getLatLon().indexOf(",");
                    markerOption = new MarkerOptions();
                    markerOption.position(new LatLng(Double.parseDouble(storesData.get(i).getList().get(j).getList().get(q).getLatLon().substring(i1 + 1)), Double.parseDouble(storesData.get(i).getList().get(j).getList().get(q).getLatLon().substring(0, i1))));
                    markerOption.draggable(true);//设置Marker可拖动
                    markerOption.icon(BitmapDescriptorFactory.fromView(getView(storesData.get(i).getList().get(j).getList().get(q).getName() + "\n" + storesData.get(i).getList().get(j).getList().get(q).getNum())));
                    // 将Marker设置为贴地显示，可以双指下拉地图查看效果
                    markerOption.setFlat(true);//设置marker平贴地图效果
                    aMap.addMarker(markerOption);
                }
            }
        }

    }

    //门店marker点4
    private void initStoresMarker4() {
        Log.i("归根到底", "initStoresMarker:");
        aMap.clear();
        for (int i = 0; i < storesData.size(); ++i) {
            for (int j = 0; j < storesData.get(i).getList().size(); ++j) {
                for (int q = 0; q < storesData.get(i).getList().get(j).getList().size(); ++q) {
                    for (int w = 0; w < storesData.get(i).getList().get(j).getList().get(q).getList().size(); ++w) {
                        int i1 = storesData.get(i).getList().get(j).getList().get(q).getList().get(w).getLatLon().indexOf(",");
                        markerOption = new MarkerOptions();
                        markerOption.position(new LatLng(Double.parseDouble(storesData.get(i).getList().get(j).getList().get(q).getList().get(w).getLatLon().substring(i1 + 1)), Double.parseDouble(storesData.get(i).getList().get(j).getList().get(q).getList().get(w).getLatLon().substring(0, i1))));
                        markerOption.draggable(true);//设置Marker可拖动
//                        sb = new StringBuffer(rows2.get(i).getProjectName() + rows2.get(i).getProductUnitPrice() + rows2.get(i).getMonetaryUnit());
                        if (storesData.get(i).getList().get(j).getList().get(q).getList().get(w).getStatus().equals("0")) {
                            markerOption.icon(BitmapDescriptorFactory.fromView(getView1(storesData.get(i).getList().get(j).getList().get(q).getList().get(w).getName() + "(未合作)")));
                        } else if (storesData.get(i).getList().get(j).getList().get(q).getList().get(w).getStatus().equals("1")) {
                            if (storesData.get(i).getList().get(j).getList().get(q).getList().get(w).getState().equals("1")) {
                                markerOption.icon(BitmapDescriptorFactory.fromView(getView1(storesData.get(i).getList().get(j).getList().get(q).getList().get(w).getName() + "(签约)")));
                            } else if (storesData.get(i).getList().get(j).getList().get(q).getList().get(w).getState().equals("2")) {
                                markerOption.icon(BitmapDescriptorFactory.fromView(getView1(storesData.get(i).getList().get(j).getList().get(q).getList().get(w).getName() + "(装机)")));
                            } else if (storesData.get(i).getList().get(j).getList().get(q).getList().get(w).getState().equals("3")) {
                                markerOption.icon(BitmapDescriptorFactory.fromView(getView1(storesData.get(i).getList().get(j).getList().get(q).getList().get(w).getName() + "(培训)")));
                            } else if (storesData.get(i).getList().get(j).getList().get(q).getList().get(w).getState().equals("4")) {
                                markerOption.icon(BitmapDescriptorFactory.fromView(getView1(storesData.get(i).getList().get(j).getList().get(q).getList().get(w).getName() + "(维护)")));
                            } else if (storesData.get(i).getList().get(j).getList().get(q).getList().get(w).getState().equals("")) {
                                markerOption.icon(BitmapDescriptorFactory.fromView(getView1(storesData.get(i).getList().get(j).getList().get(q).getList().get(w).getName() + "(合作)")));
                            }
                        } else if (storesData.get(i).getList().get(j).getList().get(q).getList().get(w).getStatus().equals("2")) {
                            markerOption.icon(BitmapDescriptorFactory.fromView(getView1(storesData.get(i).getList().get(j).getList().get(q).getList().get(w).getName() + "(取消合作)")));
                        } else if (storesData.get(i).getList().get(j).getList().get(q).getList().get(w).getStatus().equals("3")) {
                            markerOption.icon(BitmapDescriptorFactory.fromView(getView1(storesData.get(i).getList().get(j).getList().get(q).getList().get(w).getName() + "(倒闭)")));
                        }
                        // 将Marker设置为贴地显示，可以双指下拉地图查看效果
                        markerOption.setFlat(true);//设置marker平贴地图效果
                        aMap.addMarker(markerOption);
                    }
                }
            }
        }

    }

    //公司marker点1
    private void initCompanyMarker1() {
        Log.i("归根到底", "initCompanyMarker:");
        aMap.clear();
        for (int i = 0; i < companysData.size(); ++i) {
            int i1 = companysData.get(i).getLatLon().indexOf(",");
            markerOption = new MarkerOptions();
            markerOption.position(new LatLng(Double.parseDouble(companysData.get(i).getLatLon().substring(i1 + 1)), Double.parseDouble(companysData.get(i).getLatLon().substring(0, i1))));
            markerOption.draggable(true);//设置Marker可拖动
            markerOption.icon(BitmapDescriptorFactory.fromView(getView(companysData.get(i).getName() + "\n" + companysData.get(i).getNum())));
            // 将Marker设置为贴地显示，可以双指下拉地图查看效果
            markerOption.setFlat(true);//设置marker平贴地图效果
            aMap.addMarker(markerOption);
        }

    }

    //公司marker点2
    private void initCompanyMarker2() {
        Log.i("归根到底", "initCompanyMarker:");
        aMap.clear();
        for (int i = 0; i < companysData.size(); ++i) {
            for (int j = 0; j < companysData.get(i).getList().size(); ++j) {
                int i1 = companysData.get(i).getList().get(j).getLatLon().indexOf(",");
                markerOption = new MarkerOptions();
                markerOption.position(new LatLng(Double.parseDouble(companysData.get(i).getList().get(j).getLatLon().substring(i1 + 1)), Double.parseDouble(companysData.get(i).getList().get(j).getLatLon().substring(0, i1))));
                markerOption.draggable(true);//设置Marker可拖动
                markerOption.icon(BitmapDescriptorFactory.fromView(getView(companysData.get(i).getList().get(j).getName() + "\n" + companysData.get(i).getList().get(j).getNum())));
                // 将Marker设置为贴地显示，可以双指下拉地图查看效果
                markerOption.setFlat(true);//设置marker平贴地图效果
                aMap.addMarker(markerOption);
            }
        }

    }

    //公司marker点3
    private void initCompanyMarker3() {
        Log.i("归根到底", "initCompanyMarker:");
        aMap.clear();
        for (int i = 0; i < companysData.size(); ++i) {
            for (int j = 0; j < companysData.get(i).getList().size(); ++j) {
                for (int q = 0; q < companysData.get(i).getList().get(j).getList().size(); ++q) {
                    int i1 = companysData.get(i).getList().get(j).getList().get(q).getLatLon().indexOf(",");
                    markerOption = new MarkerOptions();
                    markerOption.position(new LatLng(Double.parseDouble(companysData.get(i).getList().get(j).getList().get(q).getLatLon().substring(i1 + 1)), Double.parseDouble(companysData.get(i).getList().get(j).getList().get(q).getLatLon().substring(0, i1))));
                    markerOption.draggable(true);//设置Marker可拖动
                    markerOption.icon(BitmapDescriptorFactory.fromView(getView(companysData.get(i).getList().get(j).getList().get(q).getName() + "\n" + companysData.get(i).getList().get(j).getList().get(q).getNum())));
                    // 将Marker设置为贴地显示，可以双指下拉地图查看效果
                    markerOption.setFlat(true);//设置marker平贴地图效果
                    aMap.addMarker(markerOption);
                }
            }
        }

    }

    //公司marker点4
    private void initCompanyMarker4() {
        Log.i("归根到底", "initCompanyMarker:");
        aMap.clear();
        for (int i = 0; i < companysData.size(); ++i) {
            for (int j = 0; j < companysData.get(i).getList().size(); ++j) {
                for (int q = 0; q < companysData.get(i).getList().get(j).getList().size(); ++q) {
                    for (int w = 0; w < companysData.get(i).getList().get(j).getList().get(q).getList().size(); ++w) {
                        Log.i("归根到底测试", "getLatLon():" + companysData.get(i).getList().get(j).getList().get(q).getList().get(w).getLatLon());
                        Log.i("归根到底测试", "getName():" + companysData.get(i).getList().get(j).getList().get(q).getList().get(w).getName());
                        int i1 = companysData.get(i).getList().get(j).getList().get(q).getList().get(w).getLatLon().indexOf(",");
                        markerOption = new MarkerOptions();
                        markerOption.position(new LatLng(Double.parseDouble(companysData.get(i).getList().get(j).getList().get(q).getList().get(w).getLatLon().substring(i1 + 1)), Double.parseDouble(companysData.get(i).getList().get(j).getList().get(q).getList().get(w).getLatLon().substring(0, i1))));
                        markerOption.draggable(true);//设置Marker可拖动
//                        sb = new StringBuffer(rows2.get(i).getProjectName() + rows2.get(i).getProductUnitPrice() + rows2.get(i).getMonetaryUnit());
                        if (companysData.get(i).getList().get(j).getList().get(q).getList().get(w).getStatus().equals("0")) {
                            markerOption.icon(BitmapDescriptorFactory.fromView(getView1(companysData.get(i).getList().get(j).getList().get(q).getList().get(w).getName() + "(未合作)")));
                        } else if (companysData.get(i).getList().get(j).getList().get(q).getList().get(w).getStatus().equals("1")) {
                            markerOption.icon(BitmapDescriptorFactory.fromView(getView1(companysData.get(i).getList().get(j).getList().get(q).getList().get(w).getName() + "(合作)")));
                        } else if (companysData.get(i).getList().get(j).getList().get(q).getList().get(w).getStatus().equals("2")) {
                            markerOption.icon(BitmapDescriptorFactory.fromView(getView1(companysData.get(i).getList().get(j).getList().get(q).getList().get(w).getName() + "(取消合作)")));
                        } else if (companysData.get(i).getList().get(j).getList().get(q).getList().get(w).getStatus().equals("3")) {
                            markerOption.icon(BitmapDescriptorFactory.fromView(getView1(companysData.get(i).getList().get(j).getList().get(q).getList().get(w).getName() + "(倒闭)")));
                        }
                        // 将Marker设置为贴地显示，可以双指下拉地图查看效果
                        markerOption.setFlat(true);//设置marker平贴地图效果
                        aMap.addMarker(markerOption);
                    }
                }
            }
        }

    }

    //门店marker点选择
    private void initStoresMarkerClick(LatLng position) {
        aMap.clear();

        for (int i = 0; i < storesData.size(); ++i) {
            for (int j = 0; j < storesData.get(i).getList().size(); ++j) {
                for (int q = 0; q < storesData.get(i).getList().get(j).getList().size(); ++q) {
                    for (int w = 0; w < storesData.get(i).getList().get(j).getList().get(q).getList().size(); ++w) {
                        int i1 = storesData.get(i).getList().get(j).getList().get(q).getList().get(w).getLatLon().indexOf(",");

                        if (storesData.get(i).getList().get(j).getList().get(q).getList().get(w).getLatLon().substring(i1 + 1).equals("" + position.latitude)) {
//
                            vs2 = storesData.get(i).getList().get(j).getList().get(q).getList().get(w).getLatLon().substring(i1 + 1);
                            vs1 = storesData.get(i).getList().get(j).getList().get(q).getList().get(w).getLatLon().substring(0, i1);

                            map_house_ll1.setVisibility(View.VISIBLE);
                            map_house_ll2.setVisibility(View.VISIBLE);

                            if (storesData.get(i).getList().get(j).getList().get(q).getList().get(w).getStatus().equals("0")) {
                                ifMarkerClick = storesData.get(i).getList().get(j).getList().get(q).getList().get(w).getName() + "(未合作)";
                                pop_title.setText(storesData.get(i).getList().get(j).getList().get(q).getList().get(w).getName() + "(未合作)");
                            } else if (storesData.get(i).getList().get(j).getList().get(q).getList().get(w).getStatus().equals("1")) {
                                if (storesData.get(i).getList().get(j).getList().get(q).getList().get(w).getState().equals("1")) {
                                    ifMarkerClick = storesData.get(i).getList().get(j).getList().get(q).getList().get(w).getName() + "(签约)";
                                    pop_title.setText(storesData.get(i).getList().get(j).getList().get(q).getList().get(w).getName() + "(签约)");
                                } else if (storesData.get(i).getList().get(j).getList().get(q).getList().get(w).getState().equals("2")) {
                                    ifMarkerClick = storesData.get(i).getList().get(j).getList().get(q).getList().get(w).getName() + "(装机)";
                                    pop_title.setText(storesData.get(i).getList().get(j).getList().get(q).getList().get(w).getName() + "(装机)");
                                } else if (storesData.get(i).getList().get(j).getList().get(q).getList().get(w).getState().equals("3")) {
                                    ifMarkerClick = storesData.get(i).getList().get(j).getList().get(q).getList().get(w).getName() + "(培训)";
                                    pop_title.setText(storesData.get(i).getList().get(j).getList().get(q).getList().get(w).getName() + "(培训)");
                                } else if (storesData.get(i).getList().get(j).getList().get(q).getList().get(w).getState().equals("4")) {
                                    ifMarkerClick = storesData.get(i).getList().get(j).getList().get(q).getList().get(w).getName() + "(维护)";
                                    pop_title.setText(storesData.get(i).getList().get(j).getList().get(q).getList().get(w).getName() + "(维护)");
                                } else if (storesData.get(i).getList().get(j).getList().get(q).getList().get(w).getState().equals("")) {
                                    ifMarkerClick = storesData.get(i).getList().get(j).getList().get(q).getList().get(w).getName() + "(合作)";
                                    pop_title.setText(storesData.get(i).getList().get(j).getList().get(q).getList().get(w).getName() + "(合作)");
                                }
                            } else if (storesData.get(i).getList().get(j).getList().get(q).getList().get(w).getStatus().equals("2")) {
                                ifMarkerClick = storesData.get(i).getList().get(j).getList().get(q).getList().get(w).getName() + "(取消合作)";
                                pop_title.setText(storesData.get(i).getList().get(j).getList().get(q).getList().get(w).getName() + "(取消合作)");
                            } else if (storesData.get(i).getList().get(j).getList().get(q).getList().get(w).getStatus().equals("3")) {
                                ifMarkerClick = storesData.get(i).getList().get(j).getList().get(q).getList().get(w).getName() + "(倒闭)";
                                pop_title.setText(storesData.get(i).getList().get(j).getList().get(q).getList().get(w).getName() + "(倒闭)");
                            }
                            pop_address.setText(storesData.get(i).getList().get(j).getList().get(q).getList().get(w).getAddress());
                            if (storesData.get(i).getList().get(j).getList().get(q).getList().get(w).getShopownerName().equals("")) {
                                pop_ll_1.setVisibility(View.GONE);
                            } else {
                                pop_ll_1.setVisibility(View.VISIBLE);
                                pop_name.setText(storesData.get(i).getList().get(j).getList().get(q).getList().get(w).getShopownerName());
                            }
                            initStoresMarker4();
                        }
                    }
                }
            }
        }


    }

    //公司marker点选择
    private void initCompaniesMarkerClick(LatLng position) {

        aMap.clear();

        for (int i = 0; i < companysData.size(); ++i) {
            for (int j = 0; j < companysData.get(i).getList().size(); ++j) {
                for (int q = 0; q < companysData.get(i).getList().get(j).getList().size(); ++q) {
                    for (int w = 0; w < companysData.get(i).getList().get(j).getList().get(q).getList().size(); ++w) {
                        int i1 = companysData.get(i).getList().get(j).getList().get(q).getList().get(w).getLatLon().indexOf(",");
                        if (companysData.get(i).getList().get(j).getList().get(q).getList().get(w).getLatLon().substring(i1 + 1).equals("" + position.latitude)) {

                            vs2 = companysData.get(i).getList().get(j).getList().get(q).getList().get(w).getLatLon().substring(i1 + 1);
                            vs1 = companysData.get(i).getList().get(j).getList().get(q).getList().get(w).getLatLon().substring(0, i1);

                            map_house_ll1.setVisibility(View.VISIBLE);
                            map_house_ll2.setVisibility(View.VISIBLE);

                            if (companysData.get(i).getList().get(j).getList().get(q).getList().get(w).getStatus().equals("0")) {
                                ifMarkerClick = companysData.get(i).getList().get(j).getList().get(q).getList().get(w).getName() + "(未合作)";
                                pop_title.setText(companysData.get(i).getList().get(j).getList().get(q).getList().get(w).getName() + "(未合作)");
                            } else if (companysData.get(i).getList().get(j).getList().get(q).getList().get(w).getStatus().equals("1")) {
                                ifMarkerClick = companysData.get(i).getList().get(j).getList().get(q).getList().get(w).getName() + "(合作)";
                                pop_title.setText(companysData.get(i).getList().get(j).getList().get(q).getList().get(w).getName() + "(合作)");
                            } else if (companysData.get(i).getList().get(j).getList().get(q).getList().get(w).getStatus().equals("2")) {
                                ifMarkerClick = companysData.get(i).getList().get(j).getList().get(q).getList().get(w).getName() + "(取消合作)";
                                pop_title.setText(companysData.get(i).getList().get(j).getList().get(q).getList().get(w).getName() + "(取消合作)");
                            } else if (companysData.get(i).getList().get(j).getList().get(q).getList().get(w).getStatus().equals("3")) {
                                ifMarkerClick = companysData.get(i).getList().get(j).getList().get(q).getList().get(w).getName() + "(倒闭)";
                                pop_title.setText(companysData.get(i).getList().get(j).getList().get(q).getList().get(w).getName() + "(倒闭)");
                            }
                            pop_address.setText(companysData.get(i).getList().get(j).getList().get(q).getList().get(w).getAddress());
                            if (companysData.get(i).getList().get(j).getList().get(q).getList().get(w).getShopownerName().equals("")) {
                                pop_ll_1.setVisibility(View.GONE);
                            } else {
                                pop_ll_1.setVisibility(View.VISIBLE);
                                pop_name.setText(companysData.get(i).getList().get(j).getList().get(q).getList().get(w).getShopownerName());
                            }
                            initCompanyMarker4();
                        }
                    }
                }
            }
        }

    }

    //项目marker点选择
    private void initProjectsMarkerClick(LatLng position) {

        aMap.clear();

        for (int i = 0; i < projectsData1.size(); ++i) {
            for (int j = 0; j < projectsData1.get(i).getList().size(); ++j) {
                for (int q = 0; q < projectsData1.get(i).getList().get(j).getList().size(); ++q) {
                    for (int w = 0; w < projectsData1.get(i).getList().get(j).getList().get(q).getList().size(); ++w) {
                        int i1 = projectsData1.get(i).getList().get(j).getList().get(q).getList().get(w).getLatLon().indexOf(",");
                        if (projectsData1.get(i).getList().get(j).getList().get(q).getList().get(w).getLatLon().substring(i1 + 1).equals("" + position.latitude)) {

                            vs2 = projectsData1.get(i).getList().get(j).getList().get(q).getList().get(w).getLatLon().substring(i1 + 1);
                            vs1 = projectsData1.get(i).getList().get(j).getList().get(q).getList().get(w).getLatLon().substring(0, i1);

                            map_house_ll1.setVisibility(View.VISIBLE);
                            map_house_ll3.setVisibility(View.VISIBLE);

                            ifMarkerClick = projectsData1.get(i).getList().get(j).getList().get(q).getList().get(w).getName() + projectsData1.get(i).getList().get(j).getList().get(q).getList().get(w).getProductUnitPrice() + projectsData1.get(i).getList().get(j).getList().get(q).getList().get(w).getMonetaryUnit();

                            markerOption.icon(BitmapDescriptorFactory.fromView(getView1(projectsData1.get(i).getList().get(j).getList().get(q).getList().get(w).getName() + projectsData1.get(i).getList().get(j).getList().get(q).getList().get(w).getProductUnitPrice() + projectsData1.get(i).getList().get(j).getList().get(q).getList().get(w).getMonetaryUnit())));

                            projectId = projectsData1.get(i).getList().get(j).getList().get(q).getList().get(w).getId();
                            projectType = projectsData1.get(i).getList().get(j).getList().get(q).getList().get(w).getProjectType();
                            Glide.with(MapHouseActivity.this).load(FinalContents.getImageUrl() + projectsData1.get(i).getList().get(j).getList().get(q).getList().get(w).getProjectImg()).into(imageAvatar);
                            nameText.setText("[" + projectsData1.get(i).getList().get(j).getList().get(q).getList().get(w).getArea() + "]" + projectsData1.get(i).getList().get(j).getList().get(q).getList().get(w).getName());

                            String ids = projectsData1.get(i).getList().get(j).getList().get(q).getList().get(w).getProductFeature();//从pd里取出字符串
                            List tags = Arrays.asList(ids.split(","));//根据逗号分隔转化为list
                            List tag = new ArrayList();
                            if (tags.size() > 4) {
                                for (int s = 0; s < 4; s++) {
                                    tag.add(tags.get(s));
                                }
                            } else {
                                for (int s = 0; s < tags.size(); s++) {
                                    tag.add(tags.get(s));
                                }
                            }
                            if (projectsData1.get(i).getList().get(j).getList().get(q).getList().get(w).getProductFeature().equals("")) {
                                tagView.setVisibility(View.GONE);
                            } else {
                                tagView.setVisibility(View.VISIBLE);
                                tagView.setTheme(ColorFactory.NONE);
                                tagView.setTags(tags);
                            }

//        if(FinalContents.getZyHome().equals("1")){
//            holder.group_booking.setVisibility(View.GONE);
//        }else {
                            if (projectsData1.get(i).getList().get(j).getList().get(q).getList().get(w).getIsgroup().equals("1")) {
                                group_booking.setVisibility(View.VISIBLE);
                                group_booking.setText(projectsData1.get(i).getList().get(j).getList().get(q).getList().get(w).getGroupNum() + "个团火热报名中...");
                            } else {
                                group_booking.setVisibility(View.GONE);
                            }
//        }

                            chick.setText(Html.fromHtml("报备(" + "<font color='#A52A2A'>" + projectsData1.get(i).getList().get(j).getList().get(q).getList().get(w).getReportAmount() + "</font>" + ")"));
                            attention.setText(Html.fromHtml("关注(" + "<font color='#A52A2A'>" + projectsData1.get(i).getList().get(j).getList().get(q).getList().get(w).getBrowseNum() + "</font>" + ")"));
                            collect.setText(Html.fromHtml("收藏(" + "<font color='#A52A2A'>" + projectsData1.get(i).getList().get(j).getList().get(q).getList().get(w).getCollectionNum() + "</font>" + ")"));
                            transmit.setText(Html.fromHtml("转发(" + "<font color='#A52A2A'>" + projectsData1.get(i).getList().get(j).getList().get(q).getList().get(w).getForwardingAmount() + "</font>" + ")"));


//                                    if(rows2.get(i).getProjectType().equals("2")){
//                                        price.setText(rows2.get(i).getReferenceToatlPrice());
//                                        price_money.setText(rows2.get(i).getReferenceToatlUnit());
//                                    }else if(rows2.get(i).getProjectType().equals("3")){
                            price.setText(projectsData1.get(i).getList().get(j).getList().get(q).getList().get(w).getProductUnitPrice());
                            price_money.setText(projectsData1.get(i).getList().get(j).getList().get(q).getList().get(w).getMonetaryUnit());
//                                    }else if(rows2.get(i).getProjectType().equals("1")){
//                                       price.setText(rows2.get(i).getProductUnitPrice());
//                                       price_money.setText(rows2.get(i).getMonetaryUnit());
//                                    }

                            square.setText(projectsData1.get(i).getList().get(j).getList().get(q).getList().get(w).getAreaInterval());
//                                    commission.setText("佣金："+rows2.get(i).getCommission());
//                                    second.setText("秒结："+rows2.get(i).getSecondPay());
//                                    FinalContents.setProjectID(rows2.get(i).getProjectId());

                            initProjectsMarker4();
                        }
                    }
                }
            }
        }

    }

    //门店marker点选择1
    private void initStoresMarkerClick1(String title) {

        int isstore = 0;
        for (int i = 0; i < storesData.size(); ++i) {
            for (int j = 0; j < storesData.get(i).getList().size(); ++j) {
                for (int q = 0; q < storesData.get(i).getList().get(j).getList().size(); ++q) {
                    for (int w = 0; w < storesData.get(i).getList().get(j).getList().get(q).getList().size(); ++w) {

                        if (storesData.get(i).getList().get(j).getList().get(q).getList().get(w).getName().equals("" + title)) {
                            aMap.clear();
                            isstore = 1;
                            int i1 = storesData.get(i).getList().get(j).getList().get(q).getList().get(w).getLatLon().indexOf(",");
                            CameraUpdate mCameraUpdate = CameraUpdateFactory.newCameraPosition(new CameraPosition(new LatLng(Double.parseDouble(storesData.get(i).getList().get(j).getList().get(q).getList().get(w).getLatLon().substring(i1 + 1)), Double.parseDouble(storesData.get(i).getList().get(j).getList().get(q).getList().get(w).getLatLon().substring(0, i1))), 16, 0, 0));
                            aMap.moveCamera(mCameraUpdate);

                            vs2 = storesData.get(i).getList().get(j).getList().get(q).getList().get(w).getLatLon().substring(i1 + 1);
                            vs1 = storesData.get(i).getList().get(j).getList().get(q).getList().get(w).getLatLon().substring(0, i1);

                            map_house_ll1.setVisibility(View.VISIBLE);
                            map_house_ll2.setVisibility(View.VISIBLE);

                            if (storesData.get(i).getList().get(j).getList().get(q).getList().get(w).getStatus().equals("0")) {
                                ifMarkerClick = storesData.get(i).getList().get(j).getList().get(q).getList().get(w).getName() + "(未合作)";
                                pop_title.setText(storesData.get(i).getList().get(j).getList().get(q).getList().get(w).getName() + "(未合作)");
                            } else if (storesData.get(i).getList().get(j).getList().get(q).getList().get(w).getStatus().equals("1")) {
                                if (storesData.get(i).getList().get(j).getList().get(q).getList().get(w).getState().equals("1")) {
                                    ifMarkerClick = storesData.get(i).getList().get(j).getList().get(q).getList().get(w).getName() + "(签约)";
                                    pop_title.setText(storesData.get(i).getList().get(j).getList().get(q).getList().get(w).getName() + "(签约)");
                                }else if (storesData.get(i).getList().get(j).getList().get(q).getList().get(w).getState().equals("2")) {
                                    ifMarkerClick = storesData.get(i).getList().get(j).getList().get(q).getList().get(w).getName() + "(装机)";
                                    pop_title.setText(storesData.get(i).getList().get(j).getList().get(q).getList().get(w).getName() + "(装机)");
                                }else if (storesData.get(i).getList().get(j).getList().get(q).getList().get(w).getState().equals("3")) {
                                    ifMarkerClick = storesData.get(i).getList().get(j).getList().get(q).getList().get(w).getName() + "(培训)";
                                    pop_title.setText(storesData.get(i).getList().get(j).getList().get(q).getList().get(w).getName() + "(培训)");
                                }else if (storesData.get(i).getList().get(j).getList().get(q).getList().get(w).getState().equals("4")) {
                                    ifMarkerClick = storesData.get(i).getList().get(j).getList().get(q).getList().get(w).getName() + "(维护)";
                                    pop_title.setText(storesData.get(i).getList().get(j).getList().get(q).getList().get(w).getName() + "(维护)");
                                }else if (storesData.get(i).getList().get(j).getList().get(q).getList().get(w).getState().equals("")) {
                                    ifMarkerClick = storesData.get(i).getList().get(j).getList().get(q).getList().get(w).getName() + "(合作)";
                                    pop_title.setText(storesData.get(i).getList().get(j).getList().get(q).getList().get(w).getName() + "(合作)");
                                }
                            } else if (storesData.get(i).getList().get(j).getList().get(q).getList().get(w).getStatus().equals("2")) {
                                ifMarkerClick = storesData.get(i).getList().get(j).getList().get(q).getList().get(w).getName() + "(取消合作)";
                                pop_title.setText(storesData.get(i).getList().get(j).getList().get(q).getList().get(w).getName() + "(取消合作)");
                            } else if (storesData.get(i).getList().get(j).getList().get(q).getList().get(w).getStatus().equals("3")) {
                                ifMarkerClick = storesData.get(i).getList().get(j).getList().get(q).getList().get(w).getName() + "(倒闭)";
                                pop_title.setText(storesData.get(i).getList().get(j).getList().get(q).getList().get(w).getName() + "(倒闭)");
                            }
                            pop_address.setText(storesData.get(i).getList().get(j).getList().get(q).getList().get(w).getAddress());
                            if (storesData.get(i).getList().get(j).getList().get(q).getList().get(w).getShopownerName().equals("")) {
                                pop_ll_1.setVisibility(View.GONE);
                            } else {
                                pop_ll_1.setVisibility(View.VISIBLE);
                                pop_name.setText(storesData.get(i).getList().get(j).getList().get(q).getList().get(w).getShopownerName());
                            }
                            initStoresMarker4();
                        }
                    }
                }
            }
        }
        if (isstore == 0) {
            ToastUtil.showLongToast(MapHouseActivity.this, "未找到该门店");
        } else {

        }
        ifClick = "0";
    }

    //公司marker点选择1
    private void initCompaniesMarkerClick1(String title) {

        Log.i("归根到底测试", "title:" + title);
        int iscompany = 0;
        for (int i = 0; i < companysData.size(); ++i) {
            for (int j = 0; j < companysData.get(i).getList().size(); ++j) {
                for (int q = 0; q < companysData.get(i).getList().get(j).getList().size(); ++q) {
                    for (int w = 0; w < companysData.get(i).getList().get(j).getList().get(q).getList().size(); ++w) {
                        if (companysData.get(i).getList().get(j).getList().get(q).getList().get(w).getName().equals("" + title)) {
                            Log.i("归根到底测试", "getLatLon()搜索:" + companysData.get(i).getList().get(j).getList().get(q).getList().get(w).getLatLon());
                            Log.i("归根到底测试", "getName()搜索:" + companysData.get(i).getList().get(j).getList().get(q).getList().get(w).getName());
                            aMap.clear();
                            iscompany = 1;
                            int i1 = companysData.get(i).getList().get(j).getList().get(q).getList().get(w).getLatLon().indexOf(",");
                            CameraUpdate mCameraUpdate = CameraUpdateFactory.newCameraPosition(new CameraPosition(new LatLng(Double.parseDouble(companysData.get(i).getList().get(j).getList().get(q).getList().get(w).getLatLon().substring(i1 + 1)), Double.parseDouble(companysData.get(i).getList().get(j).getList().get(q).getList().get(w).getLatLon().substring(0, i1))), 16, 0, 0));
                            aMap.moveCamera(mCameraUpdate);
                            vs2 = companysData.get(i).getList().get(j).getList().get(q).getList().get(w).getLatLon().substring(i1 + 1);
                            vs1 = companysData.get(i).getList().get(j).getList().get(q).getList().get(w).getLatLon().substring(0, i1);

                            map_house_ll1.setVisibility(View.VISIBLE);
                            map_house_ll2.setVisibility(View.VISIBLE);

                            if (companysData.get(i).getList().get(j).getList().get(q).getList().get(w).getStatus().equals("0")) {
                                ifMarkerClick = companysData.get(i).getList().get(j).getList().get(q).getList().get(w).getName() + "(未合作)";
                                pop_title.setText(companysData.get(i).getList().get(j).getList().get(q).getList().get(w).getName() + "(未合作)");
                            } else if (companysData.get(i).getList().get(j).getList().get(q).getList().get(w).getStatus().equals("1")) {
                                ifMarkerClick = companysData.get(i).getList().get(j).getList().get(q).getList().get(w).getName() + "(合作)";
                                pop_title.setText(companysData.get(i).getList().get(j).getList().get(q).getList().get(w).getName() + "(合作)");
                            } else if (companysData.get(i).getList().get(j).getList().get(q).getList().get(w).getStatus().equals("2")) {
                                ifMarkerClick = companysData.get(i).getList().get(j).getList().get(q).getList().get(w).getName() + "(取消合作)";
                                pop_title.setText(companysData.get(i).getList().get(j).getList().get(q).getList().get(w).getName() + "(取消合作)");
                            } else if (companysData.get(i).getList().get(j).getList().get(q).getList().get(w).getStatus().equals("3")) {
                                ifMarkerClick = companysData.get(i).getList().get(j).getList().get(q).getList().get(w).getName() + "(倒闭)";
                                pop_title.setText(companysData.get(i).getList().get(j).getList().get(q).getList().get(w).getName() + "(倒闭)");
                            }
                            pop_address.setText(companysData.get(i).getList().get(j).getList().get(q).getList().get(w).getAddress());
                            if (companysData.get(i).getList().get(j).getList().get(q).getList().get(w).getShopownerName().equals("")) {
                                pop_ll_1.setVisibility(View.GONE);
                            } else {
                                pop_ll_1.setVisibility(View.VISIBLE);
                                pop_name.setText(companysData.get(i).getList().get(j).getList().get(q).getList().get(w).getShopownerName());
                            }
                            initCompanyMarker4();
                            break;
                        }
                    }
                }
            }
        }
        if (iscompany == 0) {
            ToastUtil.showLongToast(MapHouseActivity.this, "未找到该公司");
        } else {

        }
        ifClick = "0";
    }

    //项目marker点选择1
    private void initProjectsMarkerClick1(String title) {


        int isproject = 0;
        for (int i = 0; i < projectsData1.size(); ++i) {
            for (int j = 0; j < projectsData1.get(i).getList().size(); ++j) {
                for (int q = 0; q < projectsData1.get(i).getList().get(j).getList().size(); ++q) {
                    for (int w = 0; w < projectsData1.get(i).getList().get(j).getList().get(q).getList().size(); ++w) {
                        Log.i("搜索", "外initProjectsMarkerClick1");
                        if (projectsData1.get(i).getList().get(j).getList().get(q).getList().get(w).getName().equals("" + title)) {
                            aMap.clear();
                            isproject = 1;
                            Log.i("搜索", "内initProjectsMarkerClick1");
                            int i1 = projectsData1.get(i).getList().get(j).getList().get(q).getList().get(w).getLatLon().indexOf(",");
                            CameraUpdate mCameraUpdate = CameraUpdateFactory.newCameraPosition(new CameraPosition(new LatLng(Double.parseDouble(projectsData1.get(i).getList().get(j).getList().get(q).getList().get(w).getLatLon().substring(i1 + 1)), Double.parseDouble(projectsData1.get(i).getList().get(j).getList().get(q).getList().get(w).getLatLon().substring(0, i1))), 16, 0, 0));
                            aMap.moveCamera(mCameraUpdate);
                            vs2 = projectsData1.get(i).getList().get(j).getList().get(q).getList().get(w).getLatLon().substring(i1 + 1);
                            vs1 = projectsData1.get(i).getList().get(j).getList().get(q).getList().get(w).getLatLon().substring(0, i1);

                            map_house_ll1.setVisibility(View.VISIBLE);
                            map_house_ll3.setVisibility(View.VISIBLE);

                            ifMarkerClick = projectsData1.get(i).getList().get(j).getList().get(q).getList().get(w).getName() + projectsData1.get(i).getList().get(j).getList().get(q).getList().get(w).getProductUnitPrice() + projectsData1.get(i).getList().get(j).getList().get(q).getList().get(w).getMonetaryUnit();

                            markerOption.icon(BitmapDescriptorFactory.fromView(getView1(projectsData1.get(i).getList().get(j).getList().get(q).getList().get(w).getName() + projectsData1.get(i).getList().get(j).getList().get(q).getList().get(w).getProductUnitPrice() + projectsData1.get(i).getList().get(j).getList().get(q).getList().get(w).getMonetaryUnit())));

                            projectId = projectsData1.get(i).getList().get(j).getList().get(q).getList().get(w).getId();
                            projectType = projectsData1.get(i).getList().get(j).getList().get(q).getList().get(w).getProjectType();
                            Glide.with(MapHouseActivity.this).load(FinalContents.getImageUrl() + projectsData1.get(i).getList().get(j).getList().get(q).getList().get(w).getProjectImg()).into(imageAvatar);
                            nameText.setText("[" + projectsData1.get(i).getList().get(j).getList().get(q).getList().get(w).getArea() + "]" + projectsData1.get(i).getList().get(j).getList().get(q).getList().get(w).getName());

                            String ids = projectsData1.get(i).getList().get(j).getList().get(q).getList().get(w).getProductFeature();//从pd里取出字符串
                            List tags = Arrays.asList(ids.split(","));//根据逗号分隔转化为list
                            List tag = new ArrayList();
                            if (tags.size() > 4) {
                                for (int s = 0; s < 4; s++) {
                                    tag.add(tags.get(s));
                                }
                            } else {
                                for (int s = 0; s < tags.size(); s++) {
                                    tag.add(tags.get(s));
                                }
                            }
                            if (projectsData1.get(i).getList().get(j).getList().get(q).getList().get(w).getProductFeature().equals("")) {
                                tagView.setVisibility(View.GONE);
                            } else {
                                tagView.setVisibility(View.VISIBLE);
                                tagView.setTheme(ColorFactory.NONE);
                                tagView.setTags(tags);
                            }

//        if(FinalContents.getZyHome().equals("1")){
//            holder.group_booking.setVisibility(View.GONE);
//        }else {
                            if (projectsData1.get(i).getList().get(j).getList().get(q).getList().get(w).getIsgroup().equals("1")) {
                                group_booking.setVisibility(View.VISIBLE);
                                group_booking.setText(projectsData1.get(i).getList().get(j).getList().get(q).getList().get(w).getGroupNum() + "个团火热报名中...");
                            } else {
                                group_booking.setVisibility(View.GONE);
                            }
//        }

                            chick.setText(Html.fromHtml("报备(" + "<font color='#A52A2A'>" + projectsData1.get(i).getList().get(j).getList().get(q).getList().get(w).getReportAmount() + "</font>" + ")"));
                            attention.setText(Html.fromHtml("关注(" + "<font color='#A52A2A'>" + projectsData1.get(i).getList().get(j).getList().get(q).getList().get(w).getBrowseNum() + "</font>" + ")"));
                            collect.setText(Html.fromHtml("收藏(" + "<font color='#A52A2A'>" + projectsData1.get(i).getList().get(j).getList().get(q).getList().get(w).getCollectionNum() + "</font>" + ")"));
                            transmit.setText(Html.fromHtml("转发(" + "<font color='#A52A2A'>" + projectsData1.get(i).getList().get(j).getList().get(q).getList().get(w).getForwardingAmount() + "</font>" + ")"));


//                                    if(rows2.get(i).getProjectType().equals("2")){
//                                        price.setText(rows2.get(i).getReferenceToatlPrice());
//                                        price_money.setText(rows2.get(i).getReferenceToatlUnit());
//                                    }else if(rows2.get(i).getProjectType().equals("3")){
                            price.setText(projectsData1.get(i).getList().get(j).getList().get(q).getList().get(w).getProductUnitPrice());
                            price_money.setText(projectsData1.get(i).getList().get(j).getList().get(q).getList().get(w).getMonetaryUnit());
//                                    }else if(rows2.get(i).getProjectType().equals("1")){
//                                       price.setText(rows2.get(i).getProductUnitPrice());
//                                       price_money.setText(rows2.get(i).getMonetaryUnit());
//                                    }

                            square.setText(projectsData1.get(i).getList().get(j).getList().get(q).getList().get(w).getAreaInterval());
//                                    commission.setText("佣金："+rows2.get(i).getCommission());
//                                    second.setText("秒结："+rows2.get(i).getSecondPay());
//                                    FinalContents.setProjectID(rows2.get(i).getProjectId());

                            initProjectsMarker4();
                        }
                    }
                }
            }
        }
        if (isproject == 0) {
            ToastUtil.showLongToast(MapHouseActivity.this, "未找到该项目");
        } else {

        }
        ifClick = "0";
    }

    //绘图一
    private View getView(String str) {
        Log.i("数据", "str:" + str);
//        if (view == null) {
        //获取布局
        View view = LayoutInflater.from(MapHouseActivity.this).inflate(R.layout.marker_big, null);
        TextView marker_big_tv = view.findViewById(R.id.marker_big_tv);

        marker_big_tv.setText(str);
        return view;//返回

    }

    //绘图二
    private View getView1(String str) {
        Log.i("数据", "str:" + str);
//        if (view == null) {
        //获取布局
        View view = LayoutInflater.from(MapHouseActivity.this).inflate(R.layout.marker_item, null);
        TextView tv_marker_text = view.findViewById(R.id.tv_marker_text);//文本
        tv_marker_text.setText(str);

        if (ifMarkerClick.equals(str)) {
            tv_marker_text.setTextColor(Color.parseColor("#FFFFFF"));
            tv_marker_text.setBackground(this.getResources().getDrawable(R.mipmap.mapb));
        } else {
            tv_marker_text.setTextColor(Color.parseColor("#111111"));
            tv_marker_text.setBackground(this.getResources().getDrawable(R.mipmap.mapbss));
        }

        return view;//返回

    }

    //公司数据
    private void initCompanies() {
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        final Observable<CompaniesBean> CompaniesBean = fzbInterface.getCompaniesBean(FinalContents.getUserID(), "", "1", "1000");
        CompaniesBean.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CompaniesBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(CompaniesBean companiesBean) {
                        Log.i("归根到底", "companiesBean.getData().size():" + companiesBean.getData().size());
                        companysData = companiesBean.getData();
                        if (companysData.size() == 0) {

                        } else {
                            initAmp();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    //门店数据
    private void initStores() {
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        final Observable<StoresBean> StoresBean = fzbInterface.getStoresBean(FinalContents.getUserID(), "", "", "", "1000");
        StoresBean.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<StoresBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(StoresBean storesBean) {
                        Log.i("归根到底", "storesBean.getData().size():" + storesBean.getData().size());
                        storesData = storesBean.getData();
                        if (storesData.size() == 0) {

                        } else {
                            initAmp();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    //经纬度转地址
    @Override
    public void onRegeocodeSearched(RegeocodeResult regeocodeResult, int i) {

    }

    //地址转经纬度
    @Override
    public void onGeocodeSearched(GeocodeResult geocodeResult, int i) {
        CameraUpdate mCameraUpdate = CameraUpdateFactory.newCameraPosition(new CameraPosition(new LatLng(geocodeResult.getGeocodeAddressList().get(0).getLatLonPoint().getLatitude(), geocodeResult.getGeocodeAddressList().get(0).getLatLonPoint().getLongitude()), 8, 0, 0));
        aMap.moveCamera(mCameraUpdate);

        Log.i("归根到底", "geocodeResult.getGeocodeAddressList().get(0).getLatLonPoint().getLatitude():" + geocodeResult.getGeocodeAddressList().get(0).getLatLonPoint().getLatitude());
        Log.i("归根到底", "geocodeResult.getGeocodeAddressList().get(0).getLatLonPoint().getLongitude():" + geocodeResult.getGeocodeAddressList().get(0).getLatLonPoint().getLongitude());


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

    //显示坐标
    private void showLocation(Location location) {
        String locationStr = "维度：" + location.getLatitude() + "\n"
                + "经度：" + location.getLongitude();
        latitude = location.getLatitude();
        longitude = location.getLongitude();
        Log.i("当前定位", "location.getLatitude():" + location.getLatitude());//39.168646
        Log.i("当前定位", "location.getLatitude():" + location.getLongitude());//117.24614
    }

    //判断是否有高德地图
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

//        Log.i("路线规划","sla:" + sla);
//        Log.i("路线规划","slon:" + slon);
//        Log.i("路线规划","dlat:" + dlat);
//        Log.i("路线规划","dlon:" + dlon);

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
}

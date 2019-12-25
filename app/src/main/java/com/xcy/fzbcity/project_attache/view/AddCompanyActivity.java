package com.xcy.fzbcity.project_attache.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.Settings;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.lljjcoder.citypickerview.widget.CityPicker;
import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.api.FinalContents;
import com.xcy.fzbcity.all.database.AddCompanyBean;
import com.xcy.fzbcity.all.modle.ChangeAddress;
import com.xcy.fzbcity.all.modle.ComapnyManage;
import com.xcy.fzbcity.all.persente.SingleClick;
import com.xcy.fzbcity.all.persente.StatusBar;
import com.xcy.fzbcity.all.service.MyService;
import com.xcy.fzbcity.all.utils.CommonUtil;
import com.xcy.fzbcity.all.utils.MatcherUtils;
import com.xcy.fzbcity.all.utils.ToastUtil;
import com.xcy.fzbcity.all.view.AllActivity;
import com.xcy.fzbcity.all.view.TestMapActivity;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//TODO 添加公司
public class AddCompanyActivity extends AllActivity implements View.OnClickListener {

    RelativeLayout add_company_return;

    TextView add_company_tv1;
    TextView add_company_tv2;
    TextView add_company_tv3;

    EditText add_company_et1;
    EditText add_company_et2;
    EditText add_company_et3;
    EditText add_company_et4;
//    EditText add_company_et5;
//    EditText add_company_et6;
    TextView add_company_tvs;

    Button add_company_btn;

    RelativeLayout add_company_rl1;
    RelativeLayout add_company_rl2;
//    RelativeLayout rl1;
//    RelativeLayout rl2;
//    RelativeLayout rl3;
//    RelativeLayout rl4;

    RadioButton add_company_rb1;
    RadioButton add_company_rb2;
    RadioButton add_company_rb3;
    RadioButton add_company_rb4;


    private String num;
    private String s;
    private String s1;
    private String s2;
    private String s3;
    private String s4;
    private String s5;
    private String s6;
//    private String s7;
//    private String s8;
    private String getLatitude = "";
    private String getLongitude = "";
    private ComapnyManage.DataBean.CompanyManageBean companyManage;
    private Observable<AddCompanyBean> addCompanyBean;

    private int GPS_REQUEST_CODE = 10;

    int isnum = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.project_attache_activity_add_company);
        init_No_Network();

    }

    private void init_No_Network(){
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
            ToastUtil.showLongToast(this,"当前无网络，请检查网络后再进行登录");
        }
    }

    private void initView() {
        StatusBar.makeStatusBarTransparent(this);


        add_company_return = findViewById(R.id.add_company_return);
        add_company_tv1 = findViewById(R.id.add_company_tv1);
        add_company_tv2 = findViewById(R.id.add_company_tv2);
        add_company_tv3 = findViewById(R.id.add_company_tv3);
        add_company_et1 = findViewById(R.id.add_company_et1);
        add_company_et2 = findViewById(R.id.add_company_et2);
        add_company_et3 = findViewById(R.id.add_company_et3);
        add_company_et4 = findViewById(R.id.add_company_et4);
        add_company_et4.setInputType(EditorInfo.TYPE_CLASS_PHONE);
//        add_company_et5 = findViewById(R.id.add_company_et5);
//        add_company_et6 = findViewById(R.id.add_company_et6);
        add_company_btn = findViewById(R.id.add_company_btn);
        add_company_rl1 = findViewById(R.id.add_company_rl1);
        add_company_rl2 = findViewById(R.id.add_company_rl2);
        add_company_rb1 = findViewById(R.id.add_company_rb1);
        add_company_rb2 = findViewById(R.id.add_company_rb2);
        add_company_rb3 = findViewById(R.id.add_company_rb3);
        add_company_rb4 = findViewById(R.id.add_company_rb4);
        add_company_tvs = findViewById(R.id.tvs);
//        rl1 = findViewById(R.id.rl1);
//        rl2 = findViewById(R.id.rl2);
//        rl3 = findViewById(R.id.rl3);
//        rl4 = findViewById(R.id.rl4);

        add_company_return.setOnClickListener(this);
        add_company_rl1.setOnClickListener(this);
        add_company_rl2.setOnClickListener(this);
        add_company_btn.setOnClickListener(this);

        initData();

    }

    private void initData() {

        if (FinalContents.getStoreChange().equals("修改")) {

//            rl1.setVisibility(View.GONE);
//            rl2.setVisibility(View.GONE);
//            rl3.setVisibility(View.GONE);
//            rl4.setVisibility(View.GONE);

            add_company_rb4.setVisibility(View.VISIBLE);

            add_company_tvs.setText("修改公司");
            Retrofit.Builder builder = new Retrofit.Builder();
            builder.baseUrl(FinalContents.getBaseUrl());
            builder.addConverterFactory(GsonConverterFactory.create());
            builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
            Retrofit build = builder.build();
            MyService fzbInterface = build.create(MyService.class);
            Log.i("公司修改", FinalContents.getUserID());
            Log.i("公司修改", FinalContents.getCompanyId());
            Observable<ComapnyManage> comapnyManage = fzbInterface.getComapnyManage(FinalContents.getUserID(), FinalContents.getCompanyId());
            comapnyManage.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<ComapnyManage>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(ComapnyManage comapnyManage) {

                            companyManage = comapnyManage.getData().getCompanyManage();

                            add_company_tv1.setText(companyManage.getCompanyCityName());
                            add_company_tv2.setText(companyManage.getArea());
                            add_company_tv3.setText(companyManage.getLocation());

                            add_company_et1.setText(companyManage.getCompanyName());
                            add_company_et2.setText(companyManage.getAddress());
                            add_company_et3.setText(companyManage.getUserName());
                            add_company_et4.setText(companyManage.getPhone());
//                            add_company_et5.setText(companyManage.getLoginName());

                            if (companyManage.getFlag().equals("0")) {
                                add_company_rb1.setChecked(true);
                            } else if (companyManage.getFlag().equals("1")) {
                                add_company_rb2.setChecked(true);
                            } else if (companyManage.getFlag().equals("2")) {
                                add_company_rb3.setChecked(true);
                            } else if (companyManage.getFlag().equals("3")) {
                                add_company_rb4.setChecked(true);
                            }

                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.i("MyCL", "修改公司回显数据错误信息：" + e.getMessage());
                        }

                        @Override
                        public void onComplete() {

                        }
                    });

        } else {

//            rl1.setVisibility(View.VISIBLE);
//            rl2.setVisibility(View.VISIBLE);
//            rl3.setVisibility(View.VISIBLE);
//            rl4.setVisibility(View.VISIBLE);
            add_company_rb4.setVisibility(View.GONE);
            add_company_tvs.setText("添加公司");
            add_company_tv1.setText(FinalContents.getCityName());
        }


    }

    @SingleClick(1000)
    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.add_company_return:
                FinalContents.setStoreChange("");
                /**
                 *
                 */
                FinalContents.setMyAddType("公司");
                finish();
                break;
            case R.id.add_company_rl1:
                getAddress();
                break;
            case R.id.add_company_rl2:
                boolean locServiceEnable = isLocServiceEnable(AddCompanyActivity.this);
                if (locServiceEnable == true) {
                    Log.i("MyCL", "定位服务已开启");
                    isnum = 1;
                    Intent intent = new Intent(AddCompanyActivity.this, TestMapActivity.class);
                    intent.putExtra("La",getLatitude);
                    intent.putExtra("Lo",getLongitude);
                    startActivityForResult(intent, 1);
                } else {
                    Log.i("MyCL", "定位服务未开启");
                    Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                    startActivityForResult(intent, GPS_REQUEST_CODE);
                }
//                add_company_tv3.setText("117.155243,39.112389");
                break;
            case R.id.add_company_btn:
                s = add_company_tv1.getText().toString();
                s1 = add_company_tv2.getText().toString();
                s2 = add_company_tv3.getText().toString();
                s3 = add_company_et1.getText().toString();
                s4 = add_company_et2.getText().toString();
//                if (FinalContents.getStoreChange().equals("修改")) {
//
//                } else {
//
//
////                    s7 = add_company_et5.getText().toString();
////                    s8 = add_company_et6.getText().toString();
//                }

                s5 = add_company_et3.getText().toString();
                s6 = add_company_et4.getText().toString();

                if(s6.equals("")){

                }else {
                    if (!MatcherUtils.isMobile(add_company_et4.getText().toString())) {
                        ToastUtil.showLongToast(AddCompanyActivity.this,"请输入正确的手机号");
                        return;
                    }
                }

                if (add_company_rb1.isChecked()) {
                    num = "0";
                } else if (add_company_rb2.isChecked()) {
                    num = "1";
                } else if (add_company_rb3.isChecked()) {
                    num = "2";
                }else if (add_company_rb4.isChecked()) {
                    num = "3";
                }
//                if (add_company_et6.getText().toString().equals("")) {
//                    s8 = "123456";
//                }
                if (FinalContents.getStoreChange().equals("修改")) {
                    s2 = add_company_tv3.getText().toString();
                    initDatas1();
                } else {
                    s2 = getLongitude + "," + getLatitude;
                    initdatas2();

                }
                break;
        }

    }

    public static boolean isLocServiceEnable(Context context) {
        LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        boolean gps = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        boolean network = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        return gps || network;
    }

    private void initdatas2() {

        if (s.equals("") || s1.equals("") || s2.equals("") || s3.equals("") || s4.equals("")) {
            ToastUtil.showLongToast(AddCompanyActivity.this,"带*号的数据不能为空，请完成填写再提交");
        } else {

//经度117.155243  纬度39.112389
            Retrofit.Builder builder = new Retrofit.Builder();
            builder.baseUrl(FinalContents.getBaseUrl());
            builder.addConverterFactory(GsonConverterFactory.create());
            builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
            Retrofit build = builder.build();
            MyService fzbInterface = build.create(MyService.class);
            Log.i("公司数据","s3:" + s3);
            Log.i("公司数据","s1:" + s1);
            Log.i("公司数据","s4:" + s4);
            Log.i("公司数据","s2:" + s2);
            Log.i("公司数据","s5:" + s5);
            Log.i("公司数据","s6:" + s6);
//            Log.i("公司数据","s7:" + s7);
//            Log.i("公司数据","s8:" + s8);
            Log.i("公司数据","num:" + num);
            Log.i("公司数据","FinalContents.getUserID():" + FinalContents.getUserID());
            addCompanyBean = fzbInterface.getAddCompanyBean("", s3, s1, s4, s2, s5, s6, num, FinalContents.getUserID());

            addCompanyBean.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<AddCompanyBean>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                        }

                        @Override
                        public void onNext(AddCompanyBean addCompanyBean) {
                            if (addCompanyBean.getData().getMessage().equals("保存成功")) {
                                FinalContents.setStoreChange("");
                                FinalContents.setMyAddType("公司");
                                ToastUtil.showLongToast(AddCompanyActivity.this,addCompanyBean.getData().getMessage());
                                finish();
                            } else {
                                ToastUtil.showLongToast(AddCompanyActivity.this,addCompanyBean.getData().getMessage());
                            }
                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.i("专员", "添加公司报错信息：" + e.getMessage());
                        }

                        @Override
                        public void onComplete() {

                        }
                    });
        }

    }

    private void initDatas1() {

        if (s.equals("") || s1.equals("") || s2.equals("") || s3.equals("") || s4.equals("")) {
            ToastUtil.showLongToast(AddCompanyActivity.this,"带*号的数据不能为空，请完成填写再提交");
        } else {

//经度117.155243  纬度39.112389
            Retrofit.Builder builder = new Retrofit.Builder();
            builder.baseUrl(FinalContents.getBaseUrl());
            builder.addConverterFactory(GsonConverterFactory.create());
            builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
            Retrofit build = builder.build();
            MyService fzbInterface = build.create(MyService.class);

            addCompanyBean = fzbInterface.getAddCompanyBean(companyManage.getId(), s3, s1, s4, s2, s5, s6, num, FinalContents.getUserID());
            addCompanyBean.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<AddCompanyBean>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(AddCompanyBean addCompanyBean) {
                            if (addCompanyBean.getData().getMessage().equals("保存成功")) {
                                FinalContents.setStoreChange("");
                                FinalContents.setMyAddType("公司");
                                ToastUtil.showLongToast(AddCompanyActivity.this, addCompanyBean.getData().getMessage());
                                StoreDetailsActivity.storeDetailsActivity.finish();
                                finish();
                            } else {
                                ToastUtil.showLongToast(AddCompanyActivity.this, addCompanyBean.getData().getMessage());
                            }
                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.i("专员", "添加公司报错信息：" + e.getMessage());
                        }

                        @Override
                        public void onComplete() {

                        }
                    });
        }

    }

    private void getAddress() {

        CityPicker cityPicker = new CityPicker.Builder(AddCompanyActivity.this)
                .textSize(14)
                .title("地址选择")
                .titleBackgroundColor("#FFFFFF")
                .confirTextColor("#696969")
                .cancelTextColor("#696969")
                .province("江苏省")
                .city("常州市")
                .district("天宁区")
                .textColor(Color.parseColor("#000000"))
                .provinceCyclic(true)
                .cityCyclic(false)
                .districtCyclic(false)
                .visibleItemsCount(7)
                .itemPadding(10)
                .onlyShowProvinceAndCity(false)
                .build();
        cityPicker.show();

        //监听事件，获取结果
        cityPicker.setOnCityItemClickListener(new CityPicker.OnCityItemClickListener() {
            @Override
            public void onSelected(String... citySelected) {
                //省份
                String province = citySelected[0];
                //城市
                String city = citySelected[1];
                //区县（如果设定了两级联动，那么该项返回空）
                String district = citySelected[2];
                //邮编
                String code = citySelected[3];
                //为展示区赋值
                add_company_tv2.setText(province + "/" + city.trim() + "/" + district.trim());
            }
        });

    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(isnum == 1){
            if (requestCode == 1 && resultCode == RESULT_OK) {
                add_company_tv3.setText(data.getStringExtra("getLatitude") + "\n" + data.getStringExtra("getLongitude"));
            }

            getLatitude = data.getStringExtra("getLatitude");
            getLongitude = data.getStringExtra("getLongitude");


            StringBuffer stringBuffer1 = new StringBuffer();
            StringBuffer stringBuffer2 = new StringBuffer();

            StringBuffer append1 = stringBuffer1.append(getLatitude);
            StringBuffer append2 = stringBuffer2.append(getLongitude);

            Retrofit.Builder builder = new Retrofit.Builder();
            builder.baseUrl(FinalContents.getBaseUrl());
            builder.addConverterFactory(GsonConverterFactory.create());
            builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
            Retrofit build = builder.build();
            MyService fzbInterface = build.create(MyService.class);

            Observable<ChangeAddress> changeAddress = fzbInterface.getChangeAddress(getLongitude, getLatitude);
            changeAddress.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<ChangeAddress>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(ChangeAddress changeAddress) {

                            add_company_et2.setText(changeAddress.getData().getValue());

                        }

                        @Override
                        public void onError(Throwable e) {
//                            ToastUtil.showToast(AddCompanyActivity.this,"请重新定位详情地址");
                            Log.i("经纬度转坐标","经纬度转坐标错误信息：" + e.getMessage());

                        }

                        @Override
                        public void onComplete() {

                        }
                    });
        }

        isnum = 0;

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (isShouldHideKeyboard(v, ev)) {
                boolean res=hideKeyboard(v.getWindowToken());
                if(res){
                    //隐藏了输入法，则不再分发事件
                    return true;
                }
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    /**
     * 根据EditText所在坐标和用户点击的坐标相对比，来判断是否隐藏键盘，因为当用户点击EditText时则不能隐藏
     *
     * @param v
     * @param event
     * @return
     */
    private boolean isShouldHideKeyboard(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] l = {0, 0};
            v.getLocationInWindow(l);
            int left = l[0],
                    top = l[1],
                    bottom = top + v.getHeight(),
                    right = left + v.getWidth();
            if (event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom) {
                // 点击EditText的事件，忽略它。
                return false;
            } else {
                return true;
            }
        }
        // 如果焦点不是EditText则忽略，这个发生在视图刚绘制完，第一个焦点不在EditText上，和用户用轨迹球选择其他的焦点
        return false;
    }

    /**
     * 获取InputMethodManager，隐藏软键盘
     * @param token
     */
    private boolean hideKeyboard(IBinder token) {
        if (token != null) {
            InputMethodManager im = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            return im.hideSoftInputFromWindow(token, InputMethodManager.HIDE_NOT_ALWAYS);
        }
        return false;
    }
}

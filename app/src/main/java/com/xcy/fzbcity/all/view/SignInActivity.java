package com.xcy.fzbcity.all.view;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.google.android.material.tabs.TabLayout;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.adapter.SignInCityAdapter;
import com.xcy.fzbcity.all.adapter.SignInCompanyAdapter;
import com.xcy.fzbcity.all.adapter.SignInStoreAdapter;
import com.xcy.fzbcity.all.api.FinalContents;
import com.xcy.fzbcity.all.modle.SignInBean;
import com.xcy.fzbcity.all.modle.SignInCityBean;
import com.xcy.fzbcity.all.modle.SignInCompanyBean;
import com.xcy.fzbcity.all.modle.SignInStoreBean;
import com.xcy.fzbcity.all.persente.SingleClick;
import com.xcy.fzbcity.all.persente.StatusBar;
import com.xcy.fzbcity.all.service.MyService;
import com.xcy.fzbcity.all.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SignInActivity extends AllActivity implements View.OnClickListener, SignInCityAdapter.OnCityClick, SignInCompanyAdapter.OnCompanyClick, SignInStoreAdapter.OnStoreClick {

    EditText sign_in_et1;
    EditText sign_in_et2;
    EditText sign_in_et3;
    EditText sign_in_edit;

    TextView sign_in_tv1;
    TextView sign_in_tv2;
    TextView sign_in_login;
    TextView sign_in_fuwu;
//    TextView up;
//    TextView down;

    Button sign_in_btn;

    RelativeLayout sign_in_rl1;
    RelativeLayout sign_in_rl2;

    private List<String> list;
    private OptionsPickerView pvOptions;

    private CheckBox checkBoxed;

    LinearLayout sign_in_ll1;
    LinearLayout sign_in_ll2;

    //    TabLayout map_tab_layout;
    RecyclerView sign_in_rv;

    SignInCityAdapter signInCityAdapter;
    SignInCompanyAdapter signInCompanyAdapter;
    SignInStoreAdapter signInStoreAdapter;
    private List<SignInCityBean.DataBean> data;
    private List<SignInCompanyBean.DataBean.RowsBean> rows;
    private List<SignInStoreBean.DataBean.RowsBean> rows1;

    String CityId = "";
    String CompanyId = "";
    String StoreId = "";
    private String cityName = "";
    private String companyName = "";
    private String storeName = "";
    private String cityName1 = "";
    private String companyName1 = "";
    private String storeName1 = "";

    int cityCount = 0;
    int companyCount = 0;
    int storeCount = 0;

    private Observable<SignInCompanyBean> signInCompany;
    private Observable<SignInBean> signInBean;

    private int position;
    int signEdit = 0;

    int numCount = 0;


    LinearLayout sign_in_ll_1;
    LinearLayout sign_in_ll_2;
    LinearLayout sign_in_ll_3;
    LinearLayout sign_in_ll_4;
    LinearLayout sign_in_ll_5;
    LinearLayout sign_in_ll_6;

    TextView sign_in_tv_1;
    TextView sign_in_tv_2;
    TextView sign_in_tv_3;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
//        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
//        StatusBar.makeStatusBarTransparent(this);
//        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);      //  TODO    始终竖屏
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//        getWindow().setStatusBarColor(Color.parseColor("#FFFFFF"));
        initView();

    }

    private void initView() {

//        StatusBar.makeStatusBarTransparent(this);

//        up = findViewById(R.id.up);
//        down = findViewById(R.id.down);

        signInCityAdapter = new SignInCityAdapter();
        signInCompanyAdapter = new SignInCompanyAdapter();
        signInStoreAdapter = new SignInStoreAdapter();


        sign_in_ll_1 = findViewById(R.id.sign_in_ll_1);
        sign_in_ll_2 = findViewById(R.id.sign_in_ll_2);
        sign_in_ll_3 = findViewById(R.id.sign_in_ll_3);
        sign_in_ll_4 = findViewById(R.id.sign_in_ll_4);
        sign_in_ll_5 = findViewById(R.id.sign_in_ll_5);
        sign_in_ll_6 = findViewById(R.id.sign_in_ll_6);
        sign_in_tv_1 = findViewById(R.id.sign_in_tv_1);
        sign_in_tv_2 = findViewById(R.id.sign_in_tv_2);
        sign_in_tv_3 = findViewById(R.id.sign_in_tv_3);


        sign_in_edit = findViewById(R.id.sign_in_edit);
        sign_in_et1 = findViewById(R.id.sign_in_et1);
        sign_in_et2 = findViewById(R.id.sign_in_et2);
        sign_in_et3 = findViewById(R.id.sign_in_et3);
        sign_in_tv1 = findViewById(R.id.sign_in_tv1);
        sign_in_tv2 = findViewById(R.id.sign_in_tv2);
        sign_in_btn = findViewById(R.id.sign_in_btn);
        sign_in_rl1 = findViewById(R.id.sign_in_rl1);
        sign_in_rl2 = findViewById(R.id.sign_in_rl2);
        sign_in_login = findViewById(R.id.sign_in_login);
        checkBoxed = findViewById(R.id.sign_in_checkboxed);
        sign_in_fuwu = findViewById(R.id.sign_in_fuwu);
        sign_in_ll1 = findViewById(R.id.sign_in_ll1);
        sign_in_ll2 = findViewById(R.id.sign_in_ll2);

        sign_in_rv = findViewById(R.id.sign_in_rv);
//        map_tab_layout = findViewById(R.id.sign_in_tab_layout);

//        map_tab_layout.addTab(map_tab_layout.newTab().setText("请选择"));
//        map_tab_layout.getTabAt(0).select();

        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        sign_in_rv.setLayoutManager(manager);
        signInCityAdapter.setOnCityClick(this);
        signInCompanyAdapter.setOnCompanyClick(this);
        signInStoreAdapter.setOnStoreClick(this);



        sign_in_btn.setOnClickListener(this);
        sign_in_ll1.setOnClickListener(this);
        sign_in_fuwu.setOnClickListener(this);
        sign_in_rl1.setOnClickListener(this);
        sign_in_rl2.setOnClickListener(this);
        sign_in_login.setOnClickListener(this);


        sign_in_ll_1.setOnClickListener(this);
        sign_in_ll_3.setOnClickListener(this);
        sign_in_ll_5.setOnClickListener(this);

//        map_tab_layout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
////                Log.i("数据显示", "位置：" + tab.getPosition());
////                Log.i("数据显示", "位置cityCount：" + cityCount);
//                position = tab.getPosition();
////                if(numCount == 0){
////                    initCity("");
////                    numCount = 1;
////                } else if(position == 0){
////                    initCity("");
////                }
//                if (position == 0) {
//                    if (tab.getText().toString().equals("请选择") || !storeName1.equals("")) {
//                        initCity("");
//                    }
////                    else {
////                        signInCityAdapter.setListCity(data);
////                        signInCityAdapter.setCityName(cityName1);
////                        sign_in_rv.setAdapter(signInCityAdapter);
////                        sign_in_rv.setVisibility(View.VISIBLE);
////                    }
////                    if(numCount == 0){
////                        if(tab.getText().toString().equals("请选择")){
////                            cityName1 = "";
////                        }
////                        initCity("");
////                        numCount = 1;
////                    }else {
////                        if(tab.getText().toString().equals("请选择")){
////                            cityName1 = "";
////                        }
////                        initCity("");
////                    }
//                }
//                if (tab.getPosition() == 1) {
//                    if (companyCount != 0) {
//                        sign_in_edit.setText("");
//                        if (tab.getText().toString().equals("请选择")) {
//                            companyName1 = "";
//                        }
//                        initCompany("");
//                    }
//                } else if (tab.getPosition() == 2) {
//                    if (storeCount != 0) {
//                        sign_in_edit.setText("");
//                        if (tab.getText().toString().equals("请选择")) {
//                            storeName1 = "";
//                        }
//                        initStore("");
//                    }
//                }
//
//            }
//
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {
//                Log.i("数据显示onTabUnselected", "onTabUnselected：" + tab.getPosition());
//
//            }
//
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {
//                Log.i("数据显示onTabUnselected", "onTabReselected：" + tab.getPosition());
//            }
//        });

        sign_in_edit.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent keyEvent) {
                if ((keyEvent != null && KeyEvent.KEYCODE_ENTER == keyEvent.getKeyCode() && KeyEvent.ACTION_DOWN == keyEvent.getAction())) {
                    Log.i("tab数据", "回车事件1:" + sign_in_edit.getText().toString());
                    if (signEdit == 0) {
                        signEdit = 1;
                        if (position == 0) {
                            initCity(sign_in_edit.getText().toString());
                        } else if (position == 1) {
                            initCompany(sign_in_edit.getText().toString());
                        } else if (position == 2) {
                            initStore(sign_in_edit.getText().toString());
                        }
                        signEdit = 0;
//                        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
//                        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
                    }
                }
                return false;
            }
        });
        sign_in_edit.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if ((actionId == 0 || actionId == 3) && event != null) {
                    Log.i("tab数据", "回车事件2:" + sign_in_edit.getText().toString());
                    Log.i("tab数据", "回车事件position2:" + position);
                    if (signEdit == 0) {
                        signEdit = 1;
                        if (position == 0) {
                            Log.i("tab数据", "initCity:");
                            initCity(sign_in_edit.getText().toString());
                        } else if (position == 1) {
                            Log.i("tab数据", "initCompany:");
                            initCompany(sign_in_edit.getText().toString());
                        } else if (position == 2) {
                            Log.i("tab数据", "initStore:");
                            initStore(sign_in_edit.getText().toString());
                        }
                        signEdit = 0;
//                        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
//                        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
                    }
                }
                return (event.getKeyCode() == KeyEvent.KEYCODE_ENTER);
            }
        });
        sign_in_edit.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER) {

                    if (signEdit == 0) {
                        signEdit = 1;
                        if (position == 0) {
                            Log.i("tab数据", "initCity:");
                            initCity(sign_in_edit.getText().toString());
                        } else if (position == 1) {
                            Log.i("tab数据", "initCompany:");
                            initCompany(sign_in_edit.getText().toString());
                        } else if (position == 2) {
                            Log.i("tab数据", "initStore:");
                            initStore(sign_in_edit.getText().toString());
                        }
                        signEdit = 0;
//                        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
//                        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
                    }

                    InputMethodManager imm = (InputMethodManager) v
                            .getContext().getSystemService(
                                    Context.INPUT_METHOD_SERVICE);
                    if (imm.isActive()) {
                        imm.hideSoftInputFromWindow(
                                v.getApplicationWindowToken(), 0);
                    }
                    return true;
                }

                return false;
            }
        });
    }

    @SingleClick(1000)
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sign_in_login://登录
                finish();
                break;
            case R.id.sign_in_rl1://经纪人类型
//                map_tab_layout.removeAllTabs();
//                map_tab_layout.addTab(map_tab_layout.newTab().setText("请选择"));
//                map_tab_layout.getTabAt(0).select();
                initType();
                break;
            case R.id.sign_in_rl2://经纪门店
                if (sign_in_tv1.getText().toString().equals("")) {
                    ToastUtil.showLongToast(SignInActivity.this, "点击输入经纪人类型");
                }else {
                    if(sign_in_tv2.getText().toString().equals("")){
                        sign_in_rv.setVisibility(View.GONE);
                        sign_in_edit.setText("");
                        sign_in_ll1.setVisibility(View.VISIBLE);
                        sign_in_ll2.setVisibility(View.VISIBLE);

                        sign_in_ll_1.setVisibility(View.VISIBLE);
                        sign_in_ll_3.setVisibility(View.GONE);
                        sign_in_ll_5.setVisibility(View.GONE);

                        sign_in_ll_2.setVisibility(View.VISIBLE);
                        sign_in_ll_4.setVisibility(View.INVISIBLE);
                        sign_in_ll_6.setVisibility(View.INVISIBLE);

                        sign_in_tv_1.setText("请选择");

                        initCity("");
                    }else {

                        sign_in_rv.setVisibility(View.GONE);
                        sign_in_edit.setText("");
                        sign_in_ll1.setVisibility(View.VISIBLE);
                        sign_in_ll2.setVisibility(View.VISIBLE);

                        sign_in_ll_1.setVisibility(View.VISIBLE);
                        sign_in_ll_3.setVisibility(View.VISIBLE);
                        sign_in_ll_5.setVisibility(View.VISIBLE);

                        sign_in_ll_2.setVisibility(View.INVISIBLE);
                        sign_in_ll_4.setVisibility(View.INVISIBLE);
                        sign_in_ll_6.setVisibility(View.VISIBLE);

                        initStore("");
                    }
                }
                break;
            case R.id.sign_in_fuwu:
                Intent intent = new Intent(SignInActivity.this, DisclaimerActivity.class);
                startActivity(intent);
                break;
            case R.id.sign_in_ll1:
                if (sign_in_tv2.getText().equals("")) {
                    cityName = "";
                    companyName = "";
                    storeName = "";
                    cityName1 = "";
                    companyName1 = "";
                    storeName1 = "";
                } else {

                }
                sign_in_ll1.setVisibility(View.GONE);
                sign_in_ll2.setVisibility(View.GONE);
                break;
            case R.id.sign_in_btn:
                Log.i("注册", "" + sign_in_et1.getText().toString());
                Log.i("注册", "" + sign_in_et2.getText().toString());
                Log.i("注册", "" + sign_in_et3.getText().toString());
                Log.i("注册", "" + sign_in_tv1.getText().toString());
                Log.i("注册", "" + sign_in_tv2.getText().toString());
                Log.i("注册", "" + CityId);
                Log.i("注册", "" + CompanyId);
                Log.i("注册", "" + StoreId);
                if (checkBoxed.isChecked() == true) {
                    if (sign_in_et1.getText().toString().equals("")) {
                        ToastUtil.showLongToast(SignInActivity.this, "请输入姓名");
                    } else {
                        if (sign_in_et2.getText().toString().equals("")) {
                            ToastUtil.showLongToast(SignInActivity.this, "请输入手机号");
                        } else {
                            if (sign_in_et3.getText().toString().equals("")) {
                                ToastUtil.showLongToast(SignInActivity.this, "登录名为5-12位数字与字母组合");
                            } else {
                                if (sign_in_tv1.getText().equals("")) {
                                    ToastUtil.showLongToast(SignInActivity.this, "点击输入经纪人类型");
                                } else {
                                    if (sign_in_tv2.getText().toString().equals("")) {
                                        ToastUtil.showLongToast(SignInActivity.this, "点击输入经纪门店");
                                    } else {
                                        if (sign_in_et2.getText().toString().length() == 11) {
                                            if (sign_in_et3.getText().toString().length() >= 5 && sign_in_et3.getText().toString().length() <= 12) {
                                                initSignIn();
                                            } else {
                                                ToastUtil.showLongToast(SignInActivity.this, "登录名为5-12位数字与字母组合");
                                            }
                                        } else {
                                            ToastUtil.showLongToast(SignInActivity.this, "手机号输入格式错误");
                                        }
                                    }
                                }
                            }
                        }
                    }
                } else {
                    ToastUtil.showLongToast(SignInActivity.this, "请阅读并同意隐私条款");
                }

                break;
            case R.id.sign_in_ll_1://城市
                sign_in_ll_2.setVisibility(View.VISIBLE);
                sign_in_ll_4.setVisibility(View.INVISIBLE);
                sign_in_ll_6.setVisibility(View.INVISIBLE);
                initCity("");
                break;
            case R.id.sign_in_ll_3://公司
                sign_in_ll_2.setVisibility(View.INVISIBLE);
                sign_in_ll_4.setVisibility(View.VISIBLE);
                sign_in_ll_6.setVisibility(View.INVISIBLE);
                initCompany("");
                break;
            case R.id.sign_in_ll_5://门店
                sign_in_ll_2.setVisibility(View.INVISIBLE);
                sign_in_ll_4.setVisibility(View.INVISIBLE);
                sign_in_ll_6.setVisibility(View.VISIBLE);
                initStore("");
                break;

        }
    }

    private void initSignIn() {

        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Log.i("注册", "" + sign_in_et1.getText().toString());
        Log.i("注册", "" + sign_in_et2.getText().toString());
        Log.i("注册", "" + sign_in_et3.getText().toString());
        Log.i("注册", "" + sign_in_tv1.getText().toString());
        Log.i("注册", "" + sign_in_tv2.getText().toString());
        Log.i("注册", "" + CityId);
        Log.i("注册", "" + CompanyId);
        Log.i("注册", "" + StoreId);
        if (sign_in_tv1.getText().toString().equals("普通经纪人")) {
            signInBean = fzbInterface.getSignInBean(StoreId, "0", sign_in_et1.getText().toString(), sign_in_et2.getText().toString(), sign_in_et3.getText().toString());
        } else if (sign_in_tv1.getText().toString().equals("老业主")) {
            signInBean = fzbInterface.getSignInBean(StoreId, "1", sign_in_et1.getText().toString(), sign_in_et2.getText().toString(), sign_in_et3.getText().toString());
        }
        signInBean.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SignInBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(SignInBean signInBean) {
                        Log.i("注册", "成功信息:" + signInBean.getData().getStatus());
                        if (signInBean.getData().getStatus().equals("0")) {
                            initStatus(signInBean.getData().getMessage(), signInBean.getData().getPhone());
                        } else if (signInBean.getData().getStatus().equals("1")) {
                            ToastUtil.showLongToast(SignInActivity.this, "注册成功");
                            FinalContents.setIfSignIn("注册");
                            FinalContents.setSignInName(sign_in_et3.getText().toString());
                            finish();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        ToastUtil.showLongToast(SignInActivity.this, "请联系管理员");
                        Log.i("注册", "错误信息:" + e.getMessage().toString());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    //注册失败提示框
    private void initStatus(String message, String phone) {

        AlertDialog.Builder builder1 = new AlertDialog.Builder(SignInActivity.this);
        View inflate = LayoutInflater.from(SignInActivity.this).inflate(R.layout.binding_report, null, false);
        builder1.setView(inflate);
        final AlertDialog show = builder1.show();
        show.getWindow().setBackgroundDrawableResource(R.drawable.report_shape);

        WindowManager m = getWindowManager();
        Display d = m.getDefaultDisplay();
        WindowManager.LayoutParams attributes = show.getWindow().getAttributes();
        attributes.width = (int) (d.getWidth() - 200);
        show.getWindow().setAttributes(attributes);
        TextView report_binding_title = inflate.findViewById(R.id.report_binding_title);
        TextView report_binding_confirm_tv = inflate.findViewById(R.id.report_binding_confirm_tv);
        TextView report_binding_cancel_tv = inflate.findViewById(R.id.report_binding_cancel_tv);
        RelativeLayout report_binding_cancel = inflate.findViewById(R.id.report_binding_cancel);
        RelativeLayout report_binding_confirm = inflate.findViewById(R.id.report_binding_confirm);
        report_binding_title.setText(message);//内容
        if (phone.equals("")) {
            report_binding_confirm.setVisibility(View.GONE);
        } else {
            report_binding_confirm.setVisibility(View.VISIBLE);
        }
        report_binding_confirm_tv.setText("立刻联系");
        report_binding_cancel_tv.setText("重新编辑");
        report_binding_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show.dismiss();
            }
        });
        report_binding_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dialIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phone));//跳转到拨号界面，同时传递电话号码
                startActivity(dialIntent);
                show.dismiss();
            }
        });

    }

    //经纪人类型
    private void initType() {

        list = new ArrayList<>();
        list.add("普通经纪人");
        list.add("老业主");
        pvOptions = new OptionsPickerBuilder(SignInActivity.this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                //               返回的分别是三个级别的选中位置
                //              展示选中数据
//                map_tab_layout.removeAllTabs();
//                map_tab_layout.addTab(map_tab_layout.newTab().setText("请选择"));
//                map_tab_layout.getTabAt(0).select();
                sign_in_ll_1.setVisibility(View.VISIBLE);
                sign_in_ll_3.setVisibility(View.GONE);
                sign_in_ll_5.setVisibility(View.GONE);
                sign_in_ll_2.setVisibility(View.VISIBLE);
                cityName1 = "";
                companyName1 = "";
                storeName1 = "";
                cityName = "";
                companyName = "";
                storeName = "";
                sign_in_tv1.setText(list.get(options1));
                sign_in_tv2.setText("");

            }
        })
                .setSelectOptions(0)//设置选择第一个
                .setOutSideCancelable(false)//点击背的地方不消失
                .build();//创建
        //      把数据绑定到控件上面
        pvOptions.setPicker(list);
        //      展示
        pvOptions.show();

    }

    //城市数据
    private void initCity(String name) {

        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Log.i("tab数据", "name:" + name);
        Observable<SignInCityBean> CityBean = fzbInterface.getSignInCityBean("1000", name);
        CityBean.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SignInCityBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(SignInCityBean signInCityBean) {
                        cityCount = 1;
                        for (int i = 0; i < signInCityBean.getData().size(); ++i) {
                            Log.i("数据显示", "城市数据显示：" + signInCityBean.getData().get(i).getName());
                        }
                        data = signInCityBean.getData();

                        if (data.size() == 0) {
                            sign_in_rv.setVisibility(View.GONE);
                        } else {
                            signInCityAdapter.setListCity(data);
                            signInCityAdapter.setCityName(cityName1);
                            sign_in_rv.setAdapter(signInCityAdapter);
                            sign_in_rv.setVisibility(View.VISIBLE);
                            if (cityName1.equals("")) {

                            } else {
                                for (int i = 0; i < signInCityBean.getData().size(); ++i) {
                                    if (signInCityBean.getData().get(i).getName().equals(cityName1)) {
                                        sign_in_rv.scrollToPosition(i);
                                    }
                                }
                            }
                        }
                        signEdit = 0;
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("注册", "城市数据错误信息：" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });


    }

    //公司数据
    private void initCompany(String name) {

        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Log.i("tab数据", "CityId:" + CityId);
        Log.i("tab数据", "name:" + name);
        if (sign_in_tv1.getText().toString().equals("普通经纪人")) {
            signInCompany = fzbInterface.getSignInCompanyBean(CityId, "0", name, "1000");
        } else if (sign_in_tv1.getText().toString().equals("老业主")) {
            signInCompany = fzbInterface.getSignInCompanyBean(CityId, "1", name, "1000");
        }
        signInCompany.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SignInCompanyBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(SignInCompanyBean signInCompanyBean) {
                        companyCount = 1;
                        for (int i = 0; i < signInCompanyBean.getData().getRows().size(); ++i) {
                            Log.i("数据显示", "公司数据显示：" + signInCompanyBean.getData().getRows().get(i).getName());
                        }
                        rows = signInCompanyBean.getData().getRows();
                        if (rows.size() == 0) {
                            sign_in_rv.setVisibility(View.GONE);
                        } else {
                            signInCompanyAdapter.setRows(rows);
                            sign_in_rv.setAdapter(signInCompanyAdapter);
                            signInCompanyAdapter.notifyDataSetChanged();
                            signInCompanyAdapter.setCompanyName(companyName1);
                            sign_in_rv.setVisibility(View.VISIBLE);
                            cityCount = 1;
                            if (companyName1.equals("")) {

                            } else {
                                for (int i = 0; i < signInCompanyBean.getData().getRows().size(); ++i) {
                                    if (signInCompanyBean.getData().getRows().get(i).getName().equals(companyName1)) {
                                        sign_in_rv.scrollToPosition(i);
                                    }
                                }
                            }
                        }
                        signEdit = 0;

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("注册", "公司数据错误信息：" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    //门店数据
    private void initStore(String name) {

        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<SignInStoreBean> SignInStoreBean = fzbInterface.getSignInStoreBean(CompanyId, name, "1000");
        SignInStoreBean.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<com.xcy.fzbcity.all.modle.SignInStoreBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(com.xcy.fzbcity.all.modle.SignInStoreBean signInStoreBean) {
                        storeCount = 1;
                        for (int i = 0; i < signInStoreBean.getData().getRows().size(); ++i) {
                            Log.i("数据显示", "门店数据显示：" + signInStoreBean.getData().getRows().get(i).getName());
                        }
                        rows1 = signInStoreBean.getData().getRows();
                        if (rows1.size() == 0) {
                            sign_in_rv.setVisibility(View.GONE);
                        } else {
                            signInStoreAdapter.setRows1(rows1);
                            signInStoreAdapter.setStoreName(storeName1);
                            sign_in_rv.setAdapter(signInStoreAdapter);
                            sign_in_rv.setVisibility(View.VISIBLE);
                            cityCount = 1;
                            if (storeName1.equals("")) {

                            } else {
                                for (int i = 0; i < signInStoreBean.getData().getRows().size(); ++i) {
                                    if (signInStoreBean.getData().getRows().get(i).getName().equals(storeName1)) {
                                        sign_in_rv.scrollToPosition(i);
                                    }
                                }
                            }
                        }
                        signEdit = 0;
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("注册", "门店数据错误信息：" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    //城市点击
    @Override
    public void CityClick(int cityPosition) {
        sign_in_rv.setVisibility(View.GONE);

        cityName = data.get(cityPosition).getName();
        cityName1 = data.get(cityPosition).getName();
//        map_tab_layout.removeAllTabs();
        int length = cityName.length();
        if (length >= 7) {
            cityName = cityName.substring(0, 5) + "...";
            sign_in_tv_1.setText(cityName);
//            map_tab_layout.addTab(map_tab_layout.newTab().setText(cityName));
        } else {
            sign_in_tv_1.setText(cityName);
//            map_tab_layout.addTab(map_tab_layout.newTab().setText(cityName));
        }
        sign_in_ll_3.setVisibility(View.VISIBLE);
        sign_in_ll_2.setVisibility(View.INVISIBLE);
        sign_in_ll_4.setVisibility(View.VISIBLE);
        sign_in_ll_6.setVisibility(View.INVISIBLE);
        cityCount = 0;
//        if (companyName.equals("")) {
        sign_in_tv_2.setText("请选择");
//            map_tab_layout.addTab(map_tab_layout.newTab().setText("请选择"));
//        } else {
//            sign_in_tv_2.setText(companyName);
////            map_tab_layout.addTab(map_tab_layout.newTab().setText(companyName));
//        }

//        map_tab_layout.getTabAt(1).select();
        CityId = data.get(cityPosition).getId();
        Log.i("tab数据", "tab数据CityId:" + CityId);

        initCompany("");
    }

    //公司点击
    @Override
    public void CompanyClick(int CompanyPosition) {
        sign_in_rv.setVisibility(View.GONE);

        companyName = rows.get(CompanyPosition).getName();
        companyName1 = rows.get(CompanyPosition).getName();
//        map_tab_layout.removeAllTabs();
//        map_tab_layout.addTab(map_tab_layout.newTab().setText(cityName));
        int length = companyName.length();
        Log.i("tab数据", "length：" + length);
        if (length >= 7) {
            companyName = companyName.substring(0, 5) + "...";
            sign_in_tv_2.setText(companyName);
//            map_tab_layout.addTab(map_tab_layout.newTab().setText(companyName));
        } else {
            sign_in_tv_2.setText(companyName);
//            map_tab_layout.addTab(map_tab_layout.newTab().setText(companyName));
        }
        cityCount = 0;
        sign_in_ll_5.setVisibility(View.VISIBLE);
        sign_in_ll_2.setVisibility(View.INVISIBLE);
        sign_in_ll_4.setVisibility(View.INVISIBLE);
        sign_in_ll_6.setVisibility(View.VISIBLE);
//        if (storeName.equals("")) {
        sign_in_tv_3.setText("请选择");
//            map_tab_layout.addTab(map_tab_layout.newTab().setText("请选择"));
//        } else {
//            sign_in_tv_3.setText(storeName);
////            map_tab_layout.addTab(map_tab_layout.newTab().setText(storeName));
//        }

//        map_tab_layout.getTabAt(2).select();
        CompanyId = rows.get(CompanyPosition).getId();
        initStore("");
    }

    //门店点击
    @Override
    public void StoreClick(int StorePosition) {
        sign_in_rv.setVisibility(View.GONE);

        sign_in_ll1.setVisibility(View.GONE);
        sign_in_ll2.setVisibility(View.GONE);
//        map_tab_layout.removeAllTabs();
        storeName = rows1.get(StorePosition).getName();
        storeName1 = rows1.get(StorePosition).getName();
//        map_tab_layout.addTab(map_tab_layout.newTab().setText(cityName));
//        map_tab_layout.addTab(map_tab_layout.newTab().setText(companyName));
        int length = storeName.length();
        if (length >= 7) {
            storeName = storeName.substring(0, 5) + "...";
            sign_in_tv_3.setText(storeName);
//            map_tab_layout.addTab(map_tab_layout.newTab().setText(storeName));
        } else {
            sign_in_tv_3.setText(storeName);
//            map_tab_layout.addTab(map_tab_layout.newTab().setText(storeName));
        }
//        map_tab_layout.getTabAt(2).select();
        sign_in_tv2.setText(cityName1 + companyName1 + storeName1);
        StoreId = rows1.get(StorePosition).getId();

    }

}

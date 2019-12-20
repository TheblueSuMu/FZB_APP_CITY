package com.xcy.fzbcity.Login;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.adapter.PopAdapter;
import com.xcy.fzbcity.all.api.APKVersionCodeUtils;
import com.xcy.fzbcity.all.api.FinalContents;
import com.xcy.fzbcity.all.api.NewlyIncreased;
import com.xcy.fzbcity.all.database.AppPackageBean;
import com.xcy.fzbcity.all.database.CaptainBean;
import com.xcy.fzbcity.all.database.ExemplaryUserBean;
import com.xcy.fzbcity.all.modle.CodeBean;
import com.xcy.fzbcity.all.modle.LoginUserBean;
import com.xcy.fzbcity.all.modle.UserIdentity;
import com.xcy.fzbcity.all.modle.UserSaveBean;
import com.xcy.fzbcity.all.persente.SharItOff;
import com.xcy.fzbcity.all.persente.SingleClick;
import com.xcy.fzbcity.all.service.MyService;
import com.xcy.fzbcity.all.utils.CommonUtil;
import com.xcy.fzbcity.all.utils.CountDownTimerUtils;
import com.xcy.fzbcity.all.utils.DataBase;
import com.xcy.fzbcity.all.utils.DataBaseUtil;
import com.xcy.fzbcity.all.utils.MatcherUtils;
import com.xcy.fzbcity.all.utils.ToastUtil;
import com.xcy.fzbcity.all.view.AllActivity;
import com.xcy.fzbcity.all.view.DisclaimerActivity;
import com.xcy.fzbcity.all.view.ForgetActivity;
import com.xcy.fzbcity.broker.view.Broker_MainActivity;
import com.xcy.fzbcity.captain_assistant.view.Captain_Assistant_MainActivity;
import com.xcy.fzbcity.captain_counselor.view.Captain_Counselor_MainActivity;
import com.xcy.fzbcity.captain_market.view.Captain_Market_MainActivity;
import com.xcy.fzbcity.captain_team.view.Captain_Team_MainActivity;
import com.xcy.fzbcity.project_attache.view.Project_Attache_MainActivity;
import com.xcy.fzbcity.project_side.view.Project_Side_MainActivity;
import com.xcy.fzbcity.shopping_guide.view.Shopping_Guide_MainActivity;

import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.wechat.friends.Wechat;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AllActivity implements View.OnClickListener {
    private TextView login_tv_username;
    private TextView login_tv_password;
    private TextView login_tv_get_code;
    private TextView login_tv_select_type;
    private TextView login_tv_login;
    private TextView login_tv_forget_password;
    private LinearLayout login_tv_wechat;
    private EditText login_et_username;
    private EditText login_et_password;
    private CheckBox login_save_user;
    private ImageView login_iv_user_state;
    private ImageView login_iv_login_type;
    private SharedPreferences.Editor editor;
    private SharedPreferences pref;
    private List<UserSaveBean> list = new ArrayList<>();

    private String LoginUrl = FinalContents.getBaseUrl() + "commonSelect/login?";
    private String sendUrl = FinalContents.getBaseUrl() + "commonSelect/sendCode?";

    private String type = "";

    private CheckBox checkBoxed;

    private String userName;
    private String passWord;

    public static LoginUserBean loginUserBean;//登陆接口数据
    private PopupWindow p;
    private String string;
    private String title;
    private int index = -1;
    private int size = 0;
    private boolean login = false;
    private JSONObject json = new JSONObject();

    /**
     * 版本下载数据
     */
    //  上下文
//    private Context mContext;
    //  进度条
    private ProgressBar mProgressBar;
    //  对话框
    private Dialog mDownloadDialog;
    //  判断是否停止
    private boolean mIsCancel = false;
    //  进度
    private int mProgress;
    //  文件保存路径
    private String mSavePath;
    //  版本名称
    private String mVersion_name= FinalContents.getVersionNumBer();
    //  请求链接
    private String url ="";
    private TextView fuwu;
    private PopAdapter popAdapter;
    private List<UserSaveBean> xlist;
    private List<DataBase> list1;
    private boolean dial = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        editor = getSharedPreferences("data", MODE_PRIVATE).edit();
        pref = getSharedPreferences("data", MODE_PRIVATE);
        index = pref.getInt("index",0);

        if (FinalContents.getClean().equals("")) {

        }else {
            editor.clear();
        }

        boolean networkAvailable = CommonUtil.isNetworkAvailable(this);
        if (networkAvailable) {
            if (FinalContents.getDengLu().equals("")) {
                initDaown();
            }else {
                initfvb();
            }
        } else {
            ToastUtil.showLongToast(LoginActivity.this,"当前无网络，请检查网络后再进行登录");
        }
        initClear();
    }


    private void initClear(){
        NewlyIncreased.setTag("");
        NewlyIncreased.setStartDate("");
        NewlyIncreased.setEndDate("");
        NewlyIncreased.setYJType("");
        NewlyIncreased.setYJstartDate("");
        NewlyIncreased.setYJendDate("");

        FinalContents.setQuanceng("");
        FinalContents.setCityIs("");
        FinalContents.setOldCityId("");
        FinalContents.setZhuanyuan("");
        FinalContents.setZhuanAn("1");
        FinalContents.setCityName("长春市");
        FinalContents.setLuo(true);
        SharItOff.setShar("显");
    }

    //命名区域
    private void initfvb() {
        list = new ArrayList<>();
        login_tv_username = findViewById(R.id.tv_tip);
        login_tv_password = findViewById(R.id.tv_password);
        login_tv_get_code = findViewById(R.id.ctv_get_code);
        login_tv_select_type = findViewById(R.id.tv_select_login_type);
        login_tv_login = findViewById(R.id.tv_user_login);
        fuwu = findViewById(R.id.fuwu);
        login_tv_forget_password = findViewById(R.id.tv_forget_password);
        login_tv_wechat = findViewById(R.id.tv_wechat);

        checkBoxed = findViewById(R.id.checkboxed);

        login_et_username = findViewById(R.id.et_user_name);
        login_et_password = findViewById(R.id.et_password);
        login_save_user = findViewById(R.id.cb_is_save_user);

        login_iv_user_state = findViewById(R.id.iv_user_state);
        login_iv_login_type = findViewById(R.id.iv_login_type);

        fuwu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, DisclaimerActivity.class);
                startActivity(intent);
            }
        });

        FinalContents.setIFSP("1");


        if (index > 0) {
            if (!pref.getString("user_name"+(index-1), "").equals("")) {
                for (int i = 0;i < index;i++){
                    list.add(new UserSaveBean(pref.getString("user_name"+i, ""), pref.getString("user_password"+i, "")));
//                    Log.i("正在储存","index："+list.get(index-1).getUserName());
                }

            }
        }


        if (pref.getString("forget", "").equals("")) {
            if (FinalContents.getDengLu().equals("")) {
                initDengLu();
                Log.i("登录", "加载1：" + pref.getString("forget", ""));
            } else {
                editor.putString("forget", "1");
                editor.commit();
                Log.i("登录", "加载2：" + pref.getString("forget", ""));
            }
        }

        Log.i("登录", "加载4：" + pref.getString("forget", ""));



        click();
    }

    private void initDengLu() {
        Log.i("登录", "数据：" + pref.getString("DengLu", ""));
        if (pref.getString("denglu", "").equals("经纪人")) {
            if (pref.getString("user_name"+(index-1), "").equals("")) {

            } else {
                if (pref.getString("login", "").equals("1")) {
                    Log.i("mmm", "用户成功登录" + pref.getString("userID", ""));
                    Intent intent = new Intent(this, Broker_MainActivity.class);
                    FinalContents.setUserID(pref.getString("userID", ""));
                    FinalContents.setCityID(pref.getString("cityID", ""));
                    FinalContents.setCityName(pref.getString("cityname", ""));
                    FinalContents.setOldCityId(pref.getString("cityID", ""));
                    FinalContents.setIdentity(pref.getString("identity", ""));
                    startActivity(intent);
                    finish();
                } else {
                    editor.putString("login", "1");
                    editor.commit();
                }
            }
        } else if (pref.getString("denglu", "").equals("销售")) {
            if (pref.getString("user_name"+(index-1), "").equals("")) {

            } else {
                if (pref.getString("login", "").equals("1")) {
                    Log.i("mmm", "用户成功登录" + pref.getString("userID", ""));
                    Intent intent = new Intent(this, Captain_Market_MainActivity.class);
                    FinalContents.setUserID(pref.getString("userID", ""));
                    FinalContents.setCityID(pref.getString("cityID", ""));
                    FinalContents.setCityName(pref.getString("cityname", ""));
                    FinalContents.setOldCityId(pref.getString("cityID", ""));
                    FinalContents.setIdentity(pref.getString("identity", ""));
                    startActivity(intent);
                    finish();
                } else {
                    editor.putString("login", "1");
                    editor.commit();
                }
            }
        } else if (pref.getString("denglu", "").equals("顾问")) {
            if (pref.getString("user_name"+(index-1), "").equals("")) {

            } else {
                if (pref.getString("login", "").equals("1")) {
                    Log.i("mmm", "用户成功登录" + pref.getString("userID", ""));
                    Intent intent = new Intent(this, Captain_Counselor_MainActivity.class);
                    FinalContents.setUserID(pref.getString("userID", ""));
                    FinalContents.setCityID(pref.getString("cityID", ""));
                    FinalContents.setCityName(pref.getString("cityname", ""));
                    FinalContents.setOldCityId(pref.getString("cityID", ""));
                    FinalContents.setIdentity(pref.getString("identity", ""));
                    startActivity(intent);
                    finish();
                } else {
                    editor.putString("login", "1");
                    editor.commit();
                }
            }
        } else if (pref.getString("denglu", "").equals("团队长")) {
            if (pref.getString("user_name"+(index-1), "").equals("")) {

            } else {
                if (pref.getString("login", "").equals("1")) {
                    Log.i("mmm", "用户成功登录" + pref.getString("userID", ""));
                    Intent intent = new Intent(this, Captain_Team_MainActivity.class);
                    FinalContents.setUserID(pref.getString("userID", ""));
                    FinalContents.setCityID(pref.getString("cityID", ""));
                    FinalContents.setCityName(pref.getString("cityname", ""));
                    FinalContents.setOldCityId(pref.getString("cityID", ""));
                    FinalContents.setIdentity(pref.getString("identity", ""));
                    startActivity(intent);
                    finish();
                } else {
                    editor.putString("login", "1");
                    editor.commit();
                }
            }
        } else if (pref.getString("denglu", "").equals("团助")) {
            if (pref.getString("user_name"+(index-1), "").equals("")) {

            } else {
                if (pref.getString("login", "").equals("1")) {
                    Log.i("mmm", "用户成功登录" + pref.getString("userID", ""));
                    Intent intent = new Intent(this, Captain_Assistant_MainActivity.class);
                    FinalContents.setUserID(pref.getString("userID", ""));
                    FinalContents.setCityID(pref.getString("cityID", ""));
                    FinalContents.setCityName(pref.getString("cityname", ""));
                    FinalContents.setOldCityId(pref.getString("cityID", ""));
                    FinalContents.setIdentity(pref.getString("identity", ""));
                    startActivity(intent);
                    finish();
                } else {
                    editor.putString("login", "1");
                    editor.commit();
                }
            }
        } else if (pref.getString("denglu", "").equals("专员")) {
            if (pref.getString("user_name"+(index-1), "").equals("")) {

            } else {
                if (pref.getString("login", "").equals("1")) {
                    Log.i("mmm", "用户成功登录" + pref.getString("userID", ""));
                    Intent intent = new Intent(this, Project_Attache_MainActivity.class);
                    FinalContents.setUserID(pref.getString("userID", ""));
                    FinalContents.setCityID(pref.getString("cityID", ""));
                    FinalContents.setOldCityId(pref.getString("cityID", ""));
                    FinalContents.setCityName(pref.getString("cityname", ""));
                    FinalContents.setIdentity(pref.getString("identity", ""));
                    startActivity(intent);
                    finish();
                } else {
                    editor.putString("login", "1");
                    editor.commit();
                }
            }
        } else if (pref.getString("denglu", "").equals("专案")) {
            if (pref.getString("user_name"+(index-1), "").equals("")) {

            } else {
                if (pref.getString("login", "").equals("1")) {
                    Log.i("mmm", "用户成功登录" + pref.getString("userID", ""));
                    Intent intent = new Intent(this, Project_Side_MainActivity.class);
                    FinalContents.setUserID(pref.getString("userID", ""));
                    FinalContents.setCityID(pref.getString("cityID", ""));
                    FinalContents.setCityName(pref.getString("cityname", ""));
                    FinalContents.setOldCityId(pref.getString("cityID", ""));
                    FinalContents.setIdentity(pref.getString("identity", ""));
                    startActivity(intent);
                    finish();
                } else {
                    editor.putString("login", "1");
                    editor.commit();
                }
            }
        } else if (pref.getString("denglu", "").equals("导购")) {
            if (pref.getString("user_name"+(index-1), "").equals("")) {

            } else {
                if (pref.getString("login", "").equals("1")) {
                    Log.i("mmm", "用户成功登录" + pref.getString("userID", ""));
                    Intent intent = new Intent(this, Shopping_Guide_MainActivity.class);
                    FinalContents.setUserID(pref.getString("userID", ""));
                    FinalContents.setCityID(pref.getString("cityID", ""));
                    FinalContents.setCityName(pref.getString("cityname", ""));
                    FinalContents.setOldCityId(pref.getString("cityID", ""));
                    FinalContents.setIdentity(pref.getString("identity", ""));
                    startActivity(intent);
                    finish();
                } else {
                    editor.putString("login", "1");
                    editor.commit();
                }
            }
        }
    }

    private void click() {
        login_tv_username.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
        login_tv_password.setOnClickListener(this);
        login_tv_get_code.setOnClickListener(this);
        login_tv_select_type.setOnClickListener(this);
        login_tv_login.setOnClickListener(this);
        login_tv_forget_password.setOnClickListener(this);
        login_tv_wechat.setOnClickListener(this);
        login_et_username.setOnClickListener(this);
        login_et_password.setOnClickListener(this);
        login_save_user.setOnClickListener(this);
        login_iv_user_state.setOnClickListener(this);
        login_iv_login_type.setOnClickListener(this);
    }


    @SuppressLint("WrongConstant")
    @SingleClick(1000)
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_select_login_type:
                login_et_password.setText("");
                login_et_username.setText("");
                if (login_tv_select_type.getText().equals("短信登录")) {
                    iii();
                    type = "2";
                } else {
                    ccc();
                    type = "1";
                }
                Log.i("数据", "登录：" + type);
                break;
            case R.id.cb_is_save_user:

                break;
            case R.id.tv_forget_password:
                Intent intent = new Intent(this, ForgetActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_user_login:
                boolean networkAvailable = CommonUtil.isNetworkAvailable(this);
                if (networkAvailable) {
                    if (type.equals("1")) {
                        userLoginWithAccount();
                    } else if (type.equals("2")) {
                        userLoginWithCode();
                    } else if (type.equals("")) {
                        userLoginWithAccount();
                    }
                } else {
                    ToastUtil.showLongToast(this, "当前无网络，请检查网络后再进行登录");
                }

                break;
            case R.id.tv_wechat:
                Platform plat = ShareSDK.getPlatform(Wechat.NAME);
                plat.removeAccount(true); //移除授权状态和本地缓存，下次授权会重新授权
                plat.SSOSetting(false); //SSO授权，传false默认是客户端授权，没有客户端授权或者不支持客户端授权会跳web授权
                plat.setPlatformActionListener(new PlatformActionListener() {
                    @Override
                    public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                        login = true;
                        Log.i("json","授权成功");
                        json = new JSONObject(hashMap);
                        Log.i("json","授权成功"+ json.toString());
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                //此时已在主线程中，更新UI
                                onResume();
                            }
                        });
                    }

                    @Override
                    public void onError(Platform platform, int i, Throwable throwable) {
                        ToastUtil.showLongToast(LoginActivity.this, "授权失败");
                        Log.i("json","授权失败"+throwable.getMessage());
                    }

                    @Override
                    public void onCancel(Platform platform, int i) {
                        Log.i("json","授权取消");
                        ToastUtil.showLongToast(LoginActivity.this, "授权取消");
                    }
                });//授权回调监听，监听oncomplete，onerror，oncancel三种状态
                if (plat.isClientValid()) {
                    //判断是否存在授权凭条的客户端，true是有客户端，false是无
                }
                if (plat.isAuthValid()) {
                    //判断是否已经存在授权状态，可以根据自己的登录逻辑设置
                    return;
                }
                ShareSDK.setActivity(this);//抖音登录适配安卓9.0
                plat.showUser(null);    //要数据不要功能，主要体现在不会重复出现授权界面
                break;
            case R.id.iv_user_state:
                PopWindow();
                break;
            case R.id.ctv_get_code:
                //点击发送验证码按钮
                sendcode();
                break;
            case R.id.iv_login_type:
                login_et_password.setText("");
                login_et_username.setText("");
                if (login_tv_select_type.getText().equals("短信登录")) {
                    iii();
                    type = "2";
                } else {
                    ccc();
                    type = "1";
                }
                Log.i("数据", "登录：" + type);
                break;
        }
    }

    /**
     * 发送验证码
     */
    @SuppressLint("ShowToast")
    private void sendcode() {
        userName = login_et_username.getText().toString();

        if (userName.equals("")) {
            ToastUtil.showLongToast(this, "请输入手机号");
            return;
        }
        if (!MatcherUtils.isMobile(userName)) {
            Log.i("aaa", "走一下");
            ToastUtil.showLongToast(this, "请输入正确的手机号");
            return;
        } else {
            Log.i("aaa", "不走");
        }
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<CodeBean> userMessage = fzbInterface.getSendCode(userName);
        userMessage.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CodeBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(CodeBean codeBean) {
                        CountDownTimerUtils mCountDownTimerUtils = new CountDownTimerUtils(login_tv_get_code, 60000, 1000);
                        mCountDownTimerUtils.start();
                        ToastUtil.showLongToast(LoginActivity.this, codeBean.getData().getMessage());
                    }

                    @Override
                    public void onError(Throwable e) {
                        ToastUtil.showLongToast(LoginActivity.this, "您输入的手机号有误");
                        Log.i("wsw", "返回的数据" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    /**
     * 验证码登录
     */
    private void userLoginWithCode() {
        userName = login_et_username.getText().toString();
        passWord = login_et_password.getText().toString();
        if (userName.equals("")) {
            ToastUtil.showLongToast(this, "请输入手机号");
            return;
        }
        if (passWord.equals("")) {
            ToastUtil.showLongToast(this, "请输入验证码");
            return;
        }
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<UserIdentity> userMessage = fzbInterface.getUserIdentity(userName, passWord, "2");
        userMessage.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UserIdentity>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(UserIdentity userIdentity) {
                        if (userIdentity.getData().getIdentity().equals("1") || userIdentity.getData().getIdentity().equals("2") || userIdentity.getData().getIdentity().equals("3")) {
                            initBroker();
                        } else if (userIdentity.getData().getIdentity().equals("4") || userIdentity.getData().getIdentity().equals("5") || userIdentity.getData().getIdentity().equals("7")|| userIdentity.getData().getIdentity().equals("63") || userIdentity.getData().getIdentity().equals("8") || userIdentity.getData().getIdentity().equals("9")) {
                            initExemplary();
                        } else if (userIdentity.getData().getIdentity().equals("60") || userIdentity.getData().getIdentity().equals("61") || userIdentity.getData().getIdentity().equals("62") ) {
                            initCaptain();
                        }
                        type = "2";
                    }

                    @Override
                    public void onError(Throwable e) {
                        ToastUtil.showLongToast(LoginActivity.this, "请确认您输入的验证码或手机号是否正确");
                        Log.i("wsw", "返回的数据" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    /**
     * 账号登录
     */
    private void userLoginWithAccount() {
        userName = login_et_username.getText().toString();
        passWord = login_et_password.getText().toString();
        if (userName.equals("")) {
            ToastUtil.showLongToast(this, "请输入账号");
            return;
        }
        if (passWord.equals("")) {
            ToastUtil.showLongToast(this, "请输入密码");
            return;
        }
        if (checkBoxed.isChecked()) {
            initlogin();
        } else {
            ToastUtil.showLongToast(this, "请同意服务条款后进行登录");
        }
    }

    /**
     * 账号数据请求
     */
    private void initlogin() {
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<UserIdentity> userMessage = fzbInterface.getUserIdentity(userName, passWord, "1");
        userMessage.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UserIdentity>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(UserIdentity userIdentity) {
                        if (userIdentity.getData().getIdentity().equals("1") || userIdentity.getData().getIdentity().equals("2") || userIdentity.getData().getIdentity().equals("3")) {
                            initBroker();
                        } else if (userIdentity.getData().getIdentity().equals("4") || userIdentity.getData().getIdentity().equals("5") || userIdentity.getData().getIdentity().equals("7") || userIdentity.getData().getIdentity().equals("63") || userIdentity.getData().getIdentity().equals("8") || userIdentity.getData().getIdentity().equals("9")) {
                            initExemplary();
                        } else if (userIdentity.getData().getIdentity().equals("60") || userIdentity.getData().getIdentity().equals("61") || userIdentity.getData().getIdentity().equals("62")) {
                            initCaptain();
                        }
                        type = "1";
                    }

                    @Override
                    public void onError(Throwable e) {
                        ToastUtil.showLongToast(LoginActivity.this, "请输入正确的账户或用户名");
                        Log.i("wsw", "返回的数据" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    private void initBroker() {
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<LoginUserBean> userMessage = fzbInterface.getLogin(userName, passWord, type);
        userMessage.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LoginUserBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(LoginUserBean loginUserBean1) {
                        loginUserBean = loginUserBean1;
                        boolean add = true;

                        if (type.equals("1")) {
                            for (int i = index-4;i < index;i++){
                                if (pref.getString("user_name"+i, "").equals(userName)) {
                                    add = false;
                                }
                            }
                            DataBaseUtil.initAdd(LoginActivity.this,userName,passWord);
                            List<DataBase> list = DataBaseUtil.initSelect(LoginActivity.this, "");
                            for (int i = 0; i < list.size(); ++i){
                                Log.i("MyCL","数据库数据：" + list.get(i).getUserName());
                            }
                            if (add) {
                                editor.putString("user_name"+index, userName);
                                editor.putString("user_password"+index, passWord);
                                editor.putInt("index", pref.getInt("index",0)+1);
                                editor.commit();
                                Log.i("正在储存","数据："+pref.getString("user_name"+index, ""));
                                if (index > 5) {
                                    for (int i = 0;i < index-4;i++){
                                        editor.remove("user_name"+i);
                                    }
                                    editor.commit();
                                }
                            }
                        }


                        boolean networkAvailable = CommonUtil.isNetworkAvailable(LoginActivity.this);
                        if (networkAvailable) {
                            Intent intent = new Intent(LoginActivity.this, Broker_MainActivity.class);
                            FinalContents.setUserID(loginUserBean.getData().getId());
                            FinalContents.setCityID(loginUserBean.getData().getCityId());
                            FinalContents.setOldCityId(loginUserBean.getData().getCityId());
                            FinalContents.setCityName(loginUserBean.getData().getCity());
                            FinalContents.setIdentity(loginUserBean.getData().getIdentity());
                            editor.putString("denglu", "经纪人");
                            editor.commit();
                            Log.i("登录", "数据中：" + pref.getString("denglu", ""));
                            editor.putString("userID", FinalContents.getUserID());
                            editor.putString("cityID", FinalContents.getCityID());
                            editor.putString("cityname", FinalContents.getCityName());
                            editor.putString("identity", FinalContents.getIdentity());
                            editor.putString("forget", "");
                            editor.commit();
                            startActivity(intent);
                            finish();
                        } else {
                            ToastUtil.showLongToast(LoginActivity.this, "当前无网络，请检查网络后再重新登录APP");
                            finish();
                        }


                    }

                    @Override
                    public void onError(Throwable e) {
                        ToastUtil.showLongToast(LoginActivity.this, "您输入的账号或密码有误");
                        Log.i("wsw", "返回的数据" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void initExemplary() {
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<ExemplaryUserBean> userMessage = fzbInterface.getExemplaryLogin(userName, passWord, type);
        userMessage.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ExemplaryUserBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ExemplaryUserBean exemplaryUserBean) {
                        ExemplaryUserBean userBean = exemplaryUserBean;

                        if (type.equals("1")) {
                            boolean add = true;

                            for (int i = index-4;i < index;i++){
                                if (pref.getString("user_name"+i, "").equals(userName)) {
                                    add = false;
                                }
                            }
                            DataBaseUtil.initAdd(LoginActivity.this,userName,passWord);
                            List<DataBase> list = DataBaseUtil.initSelect(LoginActivity.this, "");
                            for (int i = 0; i < list.size(); ++i){
                                Log.i("MyCL","数据库数据：" + list.get(i).getUserName());
                            }
                            if (add) {

                                editor.putString("user_name"+index, userName);
                                editor.putString("user_password"+index, passWord);
                                editor.putInt("index", pref.getInt("index",0)+1);
                                editor.commit();
                                Log.i("正在储存","数据："+pref.getString("user_name"+index, ""));
                                if (index > 5) {
                                    for (int i = 0;i < index-4;i++){
                                        editor.remove("user_name"+i);
                                    }
                                    editor.commit();
                                }
                            }
                        }


                        boolean networkAvailable = CommonUtil.isNetworkAvailable(LoginActivity.this);
                        if (networkAvailable) {
                            if (userBean.getData().getIdentity().equals("5") || userBean.getData().getIdentity().equals("8") || userBean.getData().getIdentity().equals("9")) {
                                //  TODO 专员端登录
                                Intent intent = new Intent(LoginActivity.this, Project_Attache_MainActivity.class);
                                FinalContents.setUserID(userBean.getData().getId());
                                FinalContents.setCityID(userBean.getData().getCityId());
                                FinalContents.setOldCityId(userBean.getData().getCityId());
                                FinalContents.setCityName(userBean.getData().getCity());
                                FinalContents.setIdentity(userBean.getData().getIdentity());
                                editor.putString("denglu", "专员");
                                editor.commit();
                                Log.i("登录", "数据中：" + pref.getString("denglu", ""));
                                editor.putString("userID", FinalContents.getUserID());
                                editor.putString("cityID", FinalContents.getCityID());
                                editor.putString("cityname", FinalContents.getCityName());
                                editor.putString("identity", FinalContents.getIdentity());
                                editor.putString("forget", "");
                                editor.commit();
                                startActivity(intent);
                                finish();
                            } else if (userBean.getData().getIdentity().equals("4")) {

                                //  TODO 专案端登录
                                Intent intent = new Intent(LoginActivity.this, Project_Side_MainActivity.class);
                                FinalContents.setUserID(userBean.getData().getId());
                                FinalContents.setCityID(userBean.getData().getCityId());
                                FinalContents.setOldCityId(userBean.getData().getCityId());
                                FinalContents.setCityName(userBean.getData().getCity());
                                FinalContents.setIdentity(userBean.getData().getIdentity());
                                editor.putString("denglu", "专案");
                                editor.commit();
                                Log.i("登录", "数据中：" + pref.getString("denglu", ""));
                                editor.putString("userID", FinalContents.getUserID());
                                editor.putString("cityID", FinalContents.getCityID());
                                editor.putString("cityname", FinalContents.getCityName());
                                editor.putString("identity", FinalContents.getIdentity());
                                editor.putString("forget", "");
                                editor.commit();
                                startActivity(intent);
                                finish();
                            } else if (userBean.getData().getIdentity().equals("7")) {
                                //  TODO 导购端登录
                                Intent intent = new Intent(LoginActivity.this, Shopping_Guide_MainActivity.class);
                                FinalContents.setUserID(userBean.getData().getId());
                                FinalContents.setCityID(userBean.getData().getCityId());
                                FinalContents.setOldCityId(userBean.getData().getCityId());
                                FinalContents.setCityName(userBean.getData().getCity());
                                FinalContents.setIdentity(userBean.getData().getIdentity());
                                editor.putString("denglu", "导购");
                                editor.commit();
                                Log.i("登录", "数据中：" + pref.getString("denglu", ""));
                                editor.putString("userID", FinalContents.getUserID());
                                editor.putString("cityID", FinalContents.getCityID());
                                editor.putString("cityname", FinalContents.getCityName());
                                editor.putString("identity", FinalContents.getIdentity());
                                editor.putString("forget", "");
                                editor.commit();
                                startActivity(intent);
                                finish();
                            } else if (userBean.getData().getIdentity().equals("63")) {
                                //  TODO 圈层端登录 团助
                                Intent intent = new Intent(LoginActivity.this, Captain_Assistant_MainActivity.class);
                                FinalContents.setUserID(userBean.getData().getId());
                                FinalContents.setCityID(userBean.getData().getCityId());
                                FinalContents.setOldCityId(userBean.getData().getCityId());
                                FinalContents.setCityName(userBean.getData().getCity());
                                FinalContents.setIdentity(userBean.getData().getIdentity());
                                FinalContents.setXSName(userBean.getData().getName());
                                editor.putString("denglu", "团助");
                                editor.commit();
                                Log.i("登录", "数据中：" + pref.getString("denglu", ""));
                                editor.putString("userID", FinalContents.getUserID());
                                editor.putString("cityID", FinalContents.getCityID());
                                editor.putString("cityname", FinalContents.getCityName());
                                editor.putString("identity", FinalContents.getIdentity());
                                editor.putString("forget", "");
                                editor.commit();
                                startActivity(intent);
                                finish();
                            }
                        } else {
                            ToastUtil.showLongToast(LoginActivity.this, "当前无网络，请检查网络后再重新登录APP");
                            finish();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        ToastUtil.showLongToast(LoginActivity.this, "您输入的账号或密码有误");
                        Log.i("wsw", "返回的数据" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void initCaptain() {
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<CaptainBean> userMessage = fzbInterface.getCaptainLogin(userName, passWord, type);
        userMessage.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CaptainBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(CaptainBean captainBean) {
                        CaptainBean userBean = captainBean;
                        if (type.equals("1")) {
                            boolean add = true;

                            for (int i = index-4;i < index;i++){
                                if (pref.getString("user_name"+i, "").equals(userName)) {
                                    add = false;
                                }
                            }
                            DataBaseUtil.initAdd(LoginActivity.this,userName,passWord);
                            List<DataBase> list = DataBaseUtil.initSelect(LoginActivity.this, "");
                            for (int i = 0; i < list.size(); ++i){
                                Log.i("MyCL","数据库数据：" + list.get(i).getUserName());
                            }
                            if (add) {

                                editor.putString("user_name"+index, userName);
                                editor.putString("user_password"+index, passWord);
                                editor.putInt("index", pref.getInt("index",0)+1);
                                editor.commit();
                                Log.i("正在储存","数据："+pref.getString("user_name"+index, ""));
                                if (index > 5) {
                                    for (int i = 0;i < index-4;i++){
                                        editor.remove("user_name"+i);
                                    }
                                    editor.commit();
                                }
                            }
                        }


                        boolean networkAvailable = CommonUtil.isNetworkAvailable(LoginActivity.this);
                        if (networkAvailable) {
                            if (userBean.getData().getIdentity().equals("60")) {
                                //  TODO 圈层端登录 团队长
                                Intent intent = new Intent(LoginActivity.this, Captain_Team_MainActivity.class);
                                FinalContents.setUserID(userBean.getData().getId());
                                FinalContents.setCityID(userBean.getData().getCityId());
                                FinalContents.setOldCityId(userBean.getData().getCityId());
                                FinalContents.setCityName(userBean.getData().getCity());
                                FinalContents.setIdentity(userBean.getData().getIdentity());
                                FinalContents.setUserName(userBean.getData().getName());
                                editor.putString("denglu", "团队长");
                                editor.commit();
                                Log.i("登录", "数据中：" + pref.getString("denglu", ""));
                                editor.putString("userID", FinalContents.getUserID());
                                editor.putString("cityID", FinalContents.getCityID());
                                editor.putString("cityname", FinalContents.getCityName());
                                editor.putString("identity", FinalContents.getIdentity());
                                editor.putString("forget", "");
                                editor.commit();
                                startActivity(intent);
                                finish();
                            } else if (userBean.getData().getIdentity().equals("62")) {
                                //  TODO 圈层端登录 顾问
                                Intent intent = new Intent(LoginActivity.this, Captain_Counselor_MainActivity.class);
                                FinalContents.setUserID(userBean.getData().getId());
                                FinalContents.setCityID(userBean.getData().getCityId());
                                FinalContents.setOldCityId(userBean.getData().getCityId());
                                FinalContents.setCityName(userBean.getData().getCity());
                                FinalContents.setIdentity(userBean.getData().getIdentity());
                                editor.putString("denglu", "顾问");
                                editor.commit();
                                Log.i("登录", "数据中：" + pref.getString("denglu", ""));
                                editor.putString("userID", FinalContents.getUserID());
                                editor.putString("cityID", FinalContents.getCityID());
                                editor.putString("cityname", FinalContents.getCityName());
                                editor.putString("identity", FinalContents.getIdentity());
                                editor.putString("forget", "");
                                editor.commit();
                                startActivity(intent);
                                finish();
                            } else if (userBean.getData().getIdentity().equals("61")) {
                                //  TODO 圈层端登录 销售
                                Intent intent = new Intent(LoginActivity.this, Captain_Market_MainActivity.class);
                                FinalContents.setUserID(userBean.getData().getId());
                                FinalContents.setCityID(userBean.getData().getCityId());
                                FinalContents.setOldCityId(userBean.getData().getCityId());
                                FinalContents.setCityName(userBean.getData().getCity());
                                FinalContents.setIdentity(userBean.getData().getIdentity());
                                FinalContents.setXSName(userBean.getData().getName());
                                FinalContents.setXSTeamName(userBean.getData().getLayerTeamVo().getTeamLeader().getParent().getName());
                                FinalContents.setUserName(userBean.getData().getName());
                                FinalContents.setParentName(userBean.getData().getLayerTeamVo().getTeamLeader().getParent().getName());
                                editor.putString("denglu", "销售");
                                editor.commit();
                                Log.i("登录", "数据中：" + pref.getString("denglu", ""));
                                editor.putString("userID", FinalContents.getUserID());
                                editor.putString("cityID", FinalContents.getCityID());
                                editor.putString("cityname", FinalContents.getCityName());
                                editor.putString("identity", FinalContents.getIdentity());
                                editor.putString("forget", "");
                                editor.commit();
                                startActivity(intent);
                                finish();
                            }
                        } else {
                            ToastUtil.showLongToast(LoginActivity.this, "当前无网络，请检查网络后再重新登录APP");
                            finish();
                        }


                    }

                    @Override
                    public void onError(Throwable e) {
                        ToastUtil.showLongToast(LoginActivity.this, "您输入的账号或密码有误");
                        Log.i("wsw", "返回的数据" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void iii() {
        login_et_password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        login_tv_username.setText("手机号");
        login_tv_password.setText("验证码");

        login_et_password.setHint("请输入验证码");
        login_et_username.setHint("请输入手机号");

        login_tv_select_type.setText("账号登录");
        login_tv_get_code.setVisibility(View.VISIBLE);
        login_iv_login_type.setImageResource(R.mipmap.ic_login_account);
    }

    private void ccc() {
        login_et_password.setTransformationMethod(PasswordTransformationMethod.getInstance());
        login_tv_username.setText("账号");
        login_tv_password.setText("密码");

        login_et_password.setHint("请输入密码");
        login_et_username.setHint("请输入账号");

        login_tv_select_type.setText("短信登录");
        login_tv_get_code.setVisibility(View.GONE);
        login_iv_login_type.setImageResource(R.mipmap.ic_login_iphone);
    }

    private void PopWindow() {
        login_iv_user_state.setImageResource(R.mipmap.ic_login_up);
        View contentView = LayoutInflater.from(this).inflate(R.layout.pop_login_user_list, null);
        //处理popWindow 显示内容
        handleListView(contentView);
        //创建并显示popWindow
        p = new PopupWindow(contentView, WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
        p.setTouchable(true);
        p.setFocusable(true);
        p.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.color)));
        p.getContentView().measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        int xOff;
        int buttonWidth = login_iv_user_state.getWidth();
        int popupwindowWidth = p.getContentView().getMeasuredWidth();
        xOff = buttonWidth - popupwindowWidth;
        p.showAsDropDown(login_iv_user_state, xOff, 0);

        p.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                login_iv_user_state.setImageResource(R.mipmap.ic_login_down);
            }
        });
        Log.i("aaa", "用户名" + pref.getString("name", ""));
    }

    //获取历史账号
    private void handleListView(View contentView) {
        RecyclerView recyclerView = contentView.findViewById(R.id.rv_user);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        linearLayoutManager.setStackFromEnd(true);
        linearLayoutManager.setReverseLayout(true);
        recyclerView.setLayoutManager(linearLayoutManager);

        list1 = DataBaseUtil.initSelect(LoginActivity.this, "");
//        xlist = new ArrayList<>();
//
//        for (int i = 0;i < list.size();i++){
//            xlist.add(list.get(i));
//        }
//
//
//        if (list.size() >= 4) {
//            xlist = new ArrayList<>();
//            for (int i = list.size()-3; i < list.size(); i++){
//                xlist.add(list.get(i));
////                Log.i("size","数据："+arrayList.get(i));
//            }
//        }

        popAdapter = new PopAdapter(list1);
        recyclerView.setAdapter(popAdapter);

        popAdapter.setOnItemClickListener(new PopAdapter.OnItemClickLisenter() {
            @Override
            public void onItemClick(int postion) {
                if (FinalContents.getDelete().equals("填充")) {
                    login_et_username.setText(list1.get(postion).getUserName());
                    login_et_password.setText(list1.get(postion).getUserPassword());
                    p.dismiss();
                } else if (FinalContents.getDelete().equals("删除")) {

                    List<DataBase> list = DataBaseUtil.initSelect(LoginActivity.this, "");

                    DataBaseUtil.initDelete(LoginActivity.this,list.get(postion).getUserName());
                    p.dismiss();
//                    Toast.makeText(LoginActivity.this, "相关功能维护中", Toast.LENGTH_SHORT).show();
//                    if (xlist.size() > 1) {
//                        for (int i = 0;i < index;i++){
//                            if (xlist.get(postion).getUserName().equals(pref.getString("user_name"+i, ""))) {
//                                editor.remove("user_name"+i);
//                                editor.remove("user_password"+i);
//                                editor.commit();
//                                xlist.remove(postion);
//                                popAdapter.notifyItemRemoved(postion);
//                                popAdapter.notifyDataSetChanged();
//                                Toast.makeText(LoginActivity.this, "删除成功", Toast.LENGTH_SHORT).show();
//                                return;
//                            }
//                        }
//                    }
                }
            }
        });

    }

    private void initDaown(){
        Log.i("查询次数","次数：" +size++);

        String versionName = APKVersionCodeUtils.getVerName(this);
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        final Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        final Observable<AppPackageBean> appPackage = fzbInterface.getAppPackage("android","com.xcy.fzb", versionName);
        Log.i("提示更新","数据："+FinalContents.getBaseUrl()+"commonSelect/appPackage&appType=android&appPackage=com.xcy.fzb&appVeriosn="+versionName);
        appPackage.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<AppPackageBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(final AppPackageBean appPackageBean) {
                        if(appPackageBean.getData().getIsUpgrade().equals("0")){
                            initfvb();
                        }else if(appPackageBean.getData().getIsUpgrade().equals("1")){
                            AlertDialog.Builder builder1 = new AlertDialog.Builder(LoginActivity.this);
                            builder1.setTitle("提示");
                            builder1.setMessage("是否更新当前版本");
                            builder1.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    initfvb();
                                }
                            });
                            builder1.setPositiveButton("更新", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dial = false;
                                    url = appPackageBean.getData().getAppurl();
                                    showDownloadDialog();
                                }
                            });
                            builder1.setOnDismissListener(new DialogInterface.OnDismissListener() {
                                @Override
                                public void onDismiss(DialogInterface dialogInterface) {
                                    if (dial) {
                                        initfvb();
                                        Log.i("消失","1事件触发");
                                    }
                                    Log.i("消失","事件触发");
//                                    initfvb();
                                }
                            });
                            builder1.show();
                            Log.i("消失","2事件触发");


                        }else if(appPackageBean.getData().getIsUpgrade().equals("2")){
                            AlertDialog.Builder builder1 = new AlertDialog.Builder(LoginActivity.this);
                            builder1.setTitle("提示");
                            builder1.setMessage("是否更新当前版本");
                            builder1.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    AllActivity.exit = true;
                                    finish();
                                }
                            });
                            builder1.setPositiveButton("更新", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    url = appPackageBean.getData().getAppurl();
                                    showDownloadDialog();
                                }
                            });
                            builder1.setOnDismissListener(new DialogInterface.OnDismissListener() {
                                @Override
                                public void onDismiss(DialogInterface dialogInterface) {
                                    AllActivity.exit = true;
                                    finish();
                                }
                            });
                            builder1.show();
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("版本更新","错误信息：" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    /**
     * 显示正在下载对话框
     */
    protected void showDownloadDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
        builder.setTitle("下载中");
        View view = LayoutInflater.from(LoginActivity.this).inflate(R.layout.dialog_progress, null);
        mProgressBar = (ProgressBar) view.findViewById(R.id.id_progress);
        builder.setView(view);
        builder.setCancelable(false);
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // 隐藏当前对话框
                dialog.dismiss();
                // 设置下载状态为取消
                mIsCancel = true;
                initfvb();
            }
        });

        mDownloadDialog = builder.create();
        mDownloadDialog.show();

        // 下载文件
        downloadAPK();
    }
    /**
     * 开启新线程下载apk文件
     */
    private void downloadAPK() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
                        String sdPath = Environment.getExternalStorageDirectory() + "/";
//                      文件保存路径
                        mSavePath = sdPath + "fzbdownload";

                        File dir = new File(mSavePath);
                        if (!dir.exists()){
                            dir.mkdir();
                        }
                        // 下载文件
                        HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
                        conn.connect();
                        InputStream is = conn.getInputStream();
                        int length = conn.getContentLength();

                        File apkFile = new File(mSavePath, mVersion_name);
                        FileOutputStream fos = new FileOutputStream(apkFile);

                        int count = 0;
                        byte[] buffer = new byte[1024];
                        while (!mIsCancel){
                            int numread = is.read(buffer);
                            count += numread;
                            // 计算进度条的当前位置
                            mProgress = (int) (((float)count/length) * 100);
                            // 更新进度条
                            mUpdateProgressHandler.sendEmptyMessage(1);

                            // 下载完成
                            if (numread < 0){
                                mUpdateProgressHandler.sendEmptyMessage(2);
                                break;
                            }
                            fos.write(buffer, 0, numread);
                        }
                        fos.close();
                        is.close();
                    }
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }

    /**
     * 接收消息
     */
    private Handler mUpdateProgressHandler = new Handler(){
        @RequiresApi(api = Build.VERSION_CODES.O)
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    // 设置进度条
                    mProgressBar.setProgress(mProgress);
                    break;
                case 2:
                    // 隐藏当前下载对话框
                    mDownloadDialog.dismiss();
                    // 安装 APK 文件
                    installAPK();
            }
        };
    };


    /**
     * 下载到本地后执行安装
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    protected void installAPK() {

        File apkFile = new File(mSavePath, mVersion_name);
        Intent intent = new Intent();
//跳转下载完成和打开页面
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//执行动作
        intent.setAction(Intent.ACTION_VIEW);
//执行的数据类型
        if (Build.VERSION.SDK_INT >= 24) { //判读版本是否在7.0以上
            //参数1 上下文, 参数2 Provider主机地址 和配置文件中保持一致   参数3  共享的文件
            Uri apkUri =
                    FileProvider.getUriForFile(LoginActivity.this, "com.xcy.fzb.fileprovider", apkFile);
            //添加这一句表示对目标应用临时授权该Uri所代表的文件
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.setDataAndType(apkUri, "application/vnd.android.package-archive");
        } else {
            intent.setDataAndType(Uri.fromFile(apkFile), "application/vnd.android.package-archive");
        }
        Log.e("TAG", "安装apk");
        LoginActivity.this.startActivity(intent);

//        File apkFile = new File(mSavePath, mVersion_name);
//        if (!apkFile.exists()){
//            return;
//        }
////        Uri apkUri = FileProvider.getUriForFile(AboutFZBActivity.this, "com.zidian.qingframe.fileprovider", apkPath);
//        Intent intent = new Intent(Intent.ACTION_VIEW);
////      安装完成后，启动app（源码中少了这句话）
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        Uri uri = Uri.parse("file://" + apkFile.toString());
//        intent.setDataAndType(uri, "application/vnd.android.package-archive");
//        AboutFZBActivity.this.startActivity(intent);
//
//        startInstallPermissionSettingActivity();
//        String fileName = mSavePath;
//        Intent i = new Intent();
//        i.setAction(Intent.ACTION_VIEW);
//        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        i.setDataAndType(Uri.fromFile(new File(fileName) ), "application/vnd.android.package-archive");
//        startActivity(i);
    }

    /**
     *      * 跳转到设置-允许安装未知来源-页面
     *      
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void startInstallPermissionSettingActivity() {
        //注意这个是8.0新API
        Intent intent = new Intent(Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        LoginActivity.this.startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (login) {
            Log.i("json","进入数据加载");
            userLoginWithWX();
        }else {
            Log.i("json","不进入数据加载");
        }
    }


    /**
     * 验证码登录
     */
    private void userLoginWithWX() {
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<UserIdentity> userMessage = fzbInterface.getUserIdentity(json.toString(), "", "3");
        userMessage.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UserIdentity>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(UserIdentity userIdentity) {
                        type = "3";
                        userName = json.toString();
                        passWord = "";
                        if (userIdentity.getData().getIdentity().equals("1") || userIdentity.getData().getIdentity().equals("2") || userIdentity.getData().getIdentity().equals("3")) {
                            initBroker();
                        } else if (userIdentity.getData().getIdentity().equals("4") || userIdentity.getData().getIdentity().equals("5") || userIdentity.getData().getIdentity().equals("7")|| userIdentity.getData().getIdentity().equals("63") || userIdentity.getData().getIdentity().equals("8") || userIdentity.getData().getIdentity().equals("9")) {
                            initExemplary();
                        } else if (userIdentity.getData().getIdentity().equals("60") || userIdentity.getData().getIdentity().equals("61") || userIdentity.getData().getIdentity().equals("62") ) {
                            initCaptain();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        ToastUtil.showLongToast(LoginActivity.this, "当前微信未绑定");
                        Log.i("wsw", "返回的数据" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}

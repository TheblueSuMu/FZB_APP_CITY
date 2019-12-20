package com.xcy.fzbcity.captain_assistant.view;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.api.FinalContents;
import com.xcy.fzbcity.all.database.BrokerSaveBean;
import com.xcy.fzbcity.all.modle.RatioByOwnerId2Bean;
import com.xcy.fzbcity.all.modle.SysUser3Bean;
import com.xcy.fzbcity.all.persente.SingleClick;
import com.xcy.fzbcity.all.persente.SlideSwitch;
import com.xcy.fzbcity.all.persente.StatusBar;
import com.xcy.fzbcity.all.service.MyService;
import com.xcy.fzbcity.all.utils.CommonUtil;
import com.xcy.fzbcity.all.utils.MatcherUtils;
import com.xcy.fzbcity.all.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Assistant_Addteam_Activity extends AppCompatActivity implements View.OnClickListener, SlideSwitch.SlideListener {

    RelativeLayout add_aconsultant_img;

    TextView add_aconsultant_tv3;

    EditText add_aconsultant_et1;
    EditText add_aconsultant_et2;
    EditText add_aconsultant_et3;
    EditText add_aconsultant_et4;
    EditText add_aconsultant_et5;

    Button add_aconsultant_btn;

    RelativeLayout add_aconsultant_rl2;

    RadioButton add_team_rb1;
    RadioButton add_team_rb2;

    private String industry = "";
    private String name = "";
    private String phone = "";
    private String loginName = "";
    private String password = "";
    private String loginFlag = "1";
    private String manageFlag = "";
    private String type = "1";

    //TODO 新增
    SlideSwitch slide;
    TextView txt;
    TextView txt1;
    private TextView add_aconsultant_title;
    private String id = "";

    int ifnum = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assistant__addteam_);
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
            ToastUtil.showToast(this, "当前无网络，请检查网络后再进行登录");
        }
    }

    private void initView() {

        StatusBar.makeStatusBarTransparent(this);

        add_aconsultant_img = findViewById(R.id.add_team_img1);

        add_aconsultant_tv3 = findViewById(R.id.add_team_tv3);

        add_aconsultant_et1 = findViewById(R.id.add_team_et1);
        add_aconsultant_et2 = findViewById(R.id.add_team_et2);
        add_aconsultant_et3 = findViewById(R.id.add_team_et3);
        add_aconsultant_et4 = findViewById(R.id.add_team_et4);
        add_aconsultant_et5 = findViewById(R.id.add_team_et5);

        add_aconsultant_et3.setInputType(EditorInfo.TYPE_CLASS_PHONE);

        add_aconsultant_btn = findViewById(R.id.add_team_btn);
        add_team_rb1 = findViewById(R.id.add_team_rb1);
        add_team_rb2 = findViewById(R.id.add_team_rb2);

        add_aconsultant_rl2 = findViewById(R.id.add_team_rl2);

        add_aconsultant_title = findViewById(R.id.add_team_title);

        add_aconsultant_img.setOnClickListener(this);
        add_aconsultant_rl2.setOnClickListener(this);
        add_aconsultant_btn.setOnClickListener(this);

//        add_aconsultant_tv1.setText(FinalContents.getXSName());
//        add_aconsultant_tv2.setText(FinalContents.getXSTeamName());

        //        TODO 新增s
        slide = (SlideSwitch) findViewById(R.id.ssswit);

        slide.setState(false);
        txt = (TextView) findViewById(R.id.sstxt);
        txt1 = (TextView) findViewById(R.id.sstxt1);
        slide.setSlideListener(this);

        if (FinalContents.getXiuGai().equals("修改团队长")) {
            add_aconsultant_title.setText("修改团队长");
            initSysUser();
        } else if (FinalContents.getXiuGai().equals("添加团队长")) {
            add_aconsultant_title.setText("添加团队长");
            FinalContents.setRatioId("");
//            add_aconsultant_tv2.setText(FinalContents.getUserName());
//            add_aconsultant_tv1.setText(FinalContents.getParentName());
        }

    }

    @SingleClick(1000)
    @Override
    public void onClick(View view) {

        switch (view.getId()) {
//            TODO 返回上一层
            case R.id.add_team_img1:
                finish();
                break;
//                TODO 级别
            case R.id.add_team_rl2:
                ifnum = 0;
                if(ifnum == 0){
                    initRatioByOwnerId();
                    ifnum = 1;
                }
                break;
//                TODO 确定
            case R.id.add_team_btn:
                if (!MatcherUtils.isMobile(add_aconsultant_et3.getText().toString())) {
                    Toast.makeText(this, "请输入正确的手机号", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    initData();
                }
                break;
        }

    }

    private void initRatioByOwnerId() {
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<RatioByOwnerId2Bean> userMessage = fzbInterface.getRatioByOwnerId2(FinalContents.getUserID());
        userMessage.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RatioByOwnerId2Bean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @SuppressLint("WrongConstant")
                    @Override
                    public void onNext(final RatioByOwnerId2Bean ratioByOwnerIdBean) {
                        final List<String> list = new ArrayList<>();
                        for (int i = 0; i < ratioByOwnerIdBean.getData().size(); i++) {
                            list.add(ratioByOwnerIdBean.getData().get(i).getName() + "(" + ratioByOwnerIdBean.getData().get(i).getPercent()+"%)");
                        }
                        //      监听选中
                        OptionsPickerView pvOptions = new OptionsPickerBuilder(Assistant_Addteam_Activity.this, new OnOptionsSelectListener() {
                            @Override
                            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                                //               返回的分别是三个级别的选中位置
                                //              展示选中数据
                                add_aconsultant_tv3.setText(ratioByOwnerIdBean.getData().get(options1).getName()+ "(" + ratioByOwnerIdBean.getData().get(options1).getPercent()+"%)");
                                FinalContents.setRatioId(ratioByOwnerIdBean.getData().get(options1).getId());
                            }
                        })
                                .setSelectOptions(0)//设置选择第一个
                                .setOutSideCancelable(false)//点击背的地方不消失
                                .build();//创建
                        //      把数据绑定到控件上面
                        pvOptions.setPicker(list);
                        //      展示
                        pvOptions.show();
                        ifnum = 0;
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

    private void initData() {
        name = add_aconsultant_et1.getText().toString();
        industry = add_aconsultant_et2.getText().toString();
        phone = add_aconsultant_et3.getText().toString();
        loginName = add_aconsultant_et4.getText().toString();
        password = add_aconsultant_et5.getText().toString();


        Log.i("修改团队长", "ID++:" + FinalContents.getRatioId());

        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);

        Log.i("添加团队长", id);
        Log.i("添加团队长", industry);
        Log.i("添加团队长", name);
        Log.i("添加团队长", phone);
        Log.i("添加团队长", loginName);
        Log.i("添加团队长", password);
        Log.i("添加团队长", loginFlag);
        Log.i("添加团队长", manageFlag);
        Log.i("添加团队长", FinalContents.getUserID());
        Log.i("添加团队长", FinalContents.getRatioId());
        Log.i("添加团队长", FinalContents.getUserID());
        Log.i("添加团队长", type);

        if (add_aconsultant_et1.getText().equals("")  || add_aconsultant_et3.getText().equals("") || loginName.equals("") || add_aconsultant_tv3.getText().equals("")) {
            ToastUtil.showToast(Assistant_Addteam_Activity.this, "请把数据填充完整再提交");
            return;
        } else {

            if (add_team_rb1.isChecked()) {
                manageFlag = "1";
            } else if (add_team_rb2.isChecked()) {
                manageFlag = "0";
            }

            if (txt.getVisibility() == View.VISIBLE) {
                loginFlag = "0";
            } else {
                loginFlag = "1";
            }

            Observable<BrokerSaveBean> userMessage = fzbInterface.getBrokerSave(id, industry, name, phone, loginName, password, loginFlag, manageFlag, FinalContents.getUserID(), "", FinalContents.getUserID(), type, FinalContents.getRatioId());
            userMessage.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<BrokerSaveBean>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @SuppressLint("WrongConstant")
                        @Override
                        public void onNext(BrokerSaveBean brokerSaveBean) {
                            ToastUtil.showToast(Assistant_Addteam_Activity.this, brokerSaveBean.getData().getMessage());
                            if(brokerSaveBean.getData().getMessage().equals("保存成功")){
                                finish();
                            }else {

                            }
                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.i("添加销售获取错误", "错误" + e);
                        }

                        @Override
                        public void onComplete() {

                        }
                    });
        }
    }

    private void initSysUser() {
        Log.i("团队长", "ID2：" + FinalContents.getAgentId());
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Log.i("修改团队长","修改团队长：" + FinalContents.getUserID());
        Log.i("修改团队长","修改团队长：" + FinalContents.getAgentId());
        Observable<SysUser3Bean> userMessage = fzbInterface.getSysUser3(FinalContents.getUserID(), FinalContents.getAgentId());
        userMessage.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SysUser3Bean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @SuppressLint("WrongConstant")
                    @Override
                    public void onNext(final SysUser3Bean sysUser2Bean) {

                        add_aconsultant_tv3.setText(sysUser2Bean.getData().getSysUser().getLevel().getName());
                        add_aconsultant_et1.setText(sysUser2Bean.getData().getSysUser().getName());
                        add_aconsultant_et2.setText(sysUser2Bean.getData().getSysUser().getIndustry());
                        add_aconsultant_et3.setText(sysUser2Bean.getData().getSysUser().getPhone());
                        add_aconsultant_et4.setText(sysUser2Bean.getData().getSysUser().getLoginName());

                        if (sysUser2Bean.getData().getSysUser().getLoginFlag().equals("0")) {
                            slide.setState(true);
                        } else if (sysUser2Bean.getData().getSysUser().getLoginFlag().equals("1")) {
                            slide.setState(false);
                        }
                        if (sysUser2Bean.getData().getSysUser().getManageFlag().equals("1")) {
                            add_team_rb1.setChecked(true);
                        } else {
                            add_team_rb2.setChecked(true);
                        }
                        FinalContents.setRatioId(sysUser2Bean.getData().getSysUser().getLevel().getId());
                        id = sysUser2Bean.getData().getSysUser().getId();
                        Log.i("修改团队长", "ID:" + FinalContents.getRatioId());
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

    //    TODO 新增
    @Override
    public void open() {
        txt.setVisibility(View.VISIBLE);
        txt1.setVisibility(View.GONE);
    }

    @Override
    public void close() {
        txt.setVisibility(View.GONE);
        txt1.setVisibility(View.VISIBLE);
    }


}

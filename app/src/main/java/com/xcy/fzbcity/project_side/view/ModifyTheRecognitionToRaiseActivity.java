package com.xcy.fzbcity.project_side.view;

import android.annotation.SuppressLint;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.api.FinalContents;
import com.xcy.fzbcity.all.modle.ConfessBean;
import com.xcy.fzbcity.all.modle.DictListBean;
import com.xcy.fzbcity.all.modle.EarnestMoneyAuditBean;
import com.xcy.fzbcity.all.service.MyService;
import com.xcy.fzbcity.all.utils.CommonUtil;
import com.xcy.fzbcity.all.utils.MatcherUtils;
import com.xcy.fzbcity.all.utils.ToastUtil;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import top.defaults.view.DateTimePickerView;

//TODO 修改认筹信息
public class ModifyTheRecognitionToRaiseActivity extends AppCompatActivity {

    RelativeLayout modify_the_recognition_to_raise_return;

    RadioGroup modify_the_recognition_to_raise_rg1;

    RadioButton modify_the_recognition_to_raise_rb1;
    RadioButton modify_the_recognition_to_raise_rb2;

    EditText modify_the_recognition_to_raise_tv1;
    EditText modify_the_recognition_to_raise_tv2;
    EditText modify_the_recognition_to_raise_tv3;
    TextView modify_the_recognition_to_raise_tv4;
    EditText modify_the_recognition_to_raise_tv5;
    TextView modify_the_recognition_to_raise_tv6;
    EditText modify_the_recognition_to_raise_tv7;
    TextView modify_the_recognition_to_raise_tv8;
    private Button modify_the_recognition_to_raise_btn;
    private RelativeLayout modify_the_recognition_to_raise_rl1;
    private RelativeLayout modify_the_recognition_to_raise_rl2;
    private RelativeLayout modify_the_recognition_to_raise_rl3;
    private TextView picker_cancel;
    private TextView picker_ensure;
    private DateTimePickerView pickerView;
    private LinearLayout picker;
    private String sex;
    private String id = "";

    int ifnum1 = 0;
    int ifnum2 = 0;
    int ifnum3 = 0;

    int isnum1 = 0;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.project_side_activity_modify_the_recognition_to_raise);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);      //  TODO    始终竖屏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        getWindow().setStatusBarColor(Color.parseColor("#ff546da6"));
        init_No_Network();
    }

    private void init_No_Network() {
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

        modify_the_recognition_to_raise_return = findViewById(R.id.modify_the_recognition_to_raise_return);
        modify_the_recognition_to_raise_rg1 = findViewById(R.id.modify_the_recognition_to_raise_rg1);
        modify_the_recognition_to_raise_rb1 = findViewById(R.id.modify_the_recognition_to_raise_rb1);
        modify_the_recognition_to_raise_rb2 = findViewById(R.id.modify_the_recognition_to_raise_rb2);
        modify_the_recognition_to_raise_tv1 = findViewById(R.id.modify_the_recognition_to_raise_tv1);
        modify_the_recognition_to_raise_tv2 = findViewById(R.id.modify_the_recognition_to_raise_tv2);
        modify_the_recognition_to_raise_tv3 = findViewById(R.id.modify_the_recognition_to_raise_tv3);
        modify_the_recognition_to_raise_tv4 = findViewById(R.id.modify_the_recognition_to_raise_tv4);
        modify_the_recognition_to_raise_tv5 = findViewById(R.id.modify_the_recognition_to_raise_tv5);
        modify_the_recognition_to_raise_tv6 = findViewById(R.id.modify_the_recognition_to_raise_tv6);
        modify_the_recognition_to_raise_tv7 = findViewById(R.id.modify_the_recognition_to_raise_tv7);
        modify_the_recognition_to_raise_tv8 = findViewById(R.id.modify_the_recognition_to_raise_tv8);
        modify_the_recognition_to_raise_btn = findViewById(R.id.modify_the_recognition_to_raise_btn);
        modify_the_recognition_to_raise_rl1 = findViewById(R.id.modify_the_recognition_to_raise_rl1);
        modify_the_recognition_to_raise_rl2 = findViewById(R.id.modify_the_recognition_to_raise_rl2);
        modify_the_recognition_to_raise_rl3 = findViewById(R.id.modify_the_recognition_to_raise_rl3);

        picker_cancel = findViewById(R.id.picker_cancel);
        picker_ensure = findViewById(R.id.picker_ensure);
        pickerView = findViewById(R.id.pickerView);
        picker = findViewById(R.id.picker);

        modify_the_recognition_to_raise_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        modify_the_recognition_to_raise_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isnum1 == 0){
                    isnum1 = 1;
                    init();
                    isnum1 = 0;
                }
            }
        });

        modify_the_recognition_to_raise_rl1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (ifnum1 == 0) {
                    ifnum1 = 1;
                    List<String> relation = new ArrayList<>();
                    relation.add("本人");
                    relation.add("父母");
                    relation.add("子女");
                    relation.add("配偶");
                    relation.add("其他");
                    initSelect(relation, modify_the_recognition_to_raise_tv4);
                    ifnum1 = 0;
                }


            }
        });

        modify_the_recognition_to_raise_rl2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ifnum2 == 0) {
                    ifnum2 = 1;
                    initSelect(modify_the_recognition_to_raise_tv6);
                    ifnum2 = 0;
                }
            }
        });

        modify_the_recognition_to_raise_rl3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ifnum3 == 0) {
                    ifnum3 = 1;
                    initDate();
                    ifnum3 = 0;
                }
            }
        });

        initData();

    }

    private void init() {
        if (!MatcherUtils.isMobile(modify_the_recognition_to_raise_tv2.getText().toString())) {
            ToastUtil.showToast(this, "请输入正确的手机号");
            return;
        }

        if (modify_the_recognition_to_raise_tv6.getText().toString().equals("一室")) {
            modify_the_recognition_to_raise_tv6.setText("1");
        } else if (modify_the_recognition_to_raise_tv6.getText().toString().equals("二室")) {
            modify_the_recognition_to_raise_tv6.setText("2");
        } else if (modify_the_recognition_to_raise_tv6.getText().toString().equals("三室")) {
            modify_the_recognition_to_raise_tv6.setText("3");
        } else if (modify_the_recognition_to_raise_tv6.getText().toString().equals("四室")) {
            modify_the_recognition_to_raise_tv6.setText("4");
        } else if (modify_the_recognition_to_raise_tv6.getText().toString().equals("五室")) {
            modify_the_recognition_to_raise_tv6.setText("5");
        }

        if (modify_the_recognition_to_raise_rb1.isChecked()) {
            sex = "男";
        } else if (modify_the_recognition_to_raise_rb2.isChecked()) {
            sex = "女";
        }

        if (modify_the_recognition_to_raise_tv1.getText().toString().equals("")) {
            ToastUtil.showToast(this, "请输入认筹客户姓名");
            return;
        }

        if (sex.equals("")) {
            ToastUtil.showToast(this, "请选择性别");
            return;
        }

        if (modify_the_recognition_to_raise_tv2.getText().toString().equals("")) {
            ToastUtil.showToast(this, "请输入认筹客户电话");
            return;
        }

        if (modify_the_recognition_to_raise_tv3.getText().toString().equals("")) {
            ToastUtil.showToast(this, "请输入认筹客户身份证");
            return;
        }

        if (modify_the_recognition_to_raise_tv4.getText().toString().equals("")) {
            ToastUtil.showToast(this, "请选择报备客户与认筹客户关系");
            return;
        }

        if (modify_the_recognition_to_raise_tv5.getText().toString().equals("")) {
            ToastUtil.showToast(this, "请输入意向楼栋");
            return;
        }

        if (modify_the_recognition_to_raise_tv6.getText().toString().equals("")) {
            ToastUtil.showToast(this, "请选择意向户型");
            return;
        }

        if (modify_the_recognition_to_raise_tv7.getText().toString().equals("")) {
            ToastUtil.showToast(this, "请输入意向面积");
            return;
        }

        if (modify_the_recognition_to_raise_tv8.getText().toString().equals("")) {
            ToastUtil.showToast(this, "请选择认筹时间");
            return;
        }

        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<ConfessBean> userMessage = fzbInterface.getEarnestMoneySave(id, FinalContents.getPreparationId(), FinalContents.getCustomerID(), FinalContents.getProjectID(),
                modify_the_recognition_to_raise_tv1.getText().toString()+"",modify_the_recognition_to_raise_tv2.getText().toString()+"",modify_the_recognition_to_raise_tv3.getText().toString()+"",
                modify_the_recognition_to_raise_tv5.getText().toString()+"",modify_the_recognition_to_raise_tv6.getText().toString()+"",modify_the_recognition_to_raise_tv7.getText().toString()+"",
                modify_the_recognition_to_raise_tv8.getText().toString()+"",modify_the_recognition_to_raise_tv4.getText().toString()+"",sex, FinalContents.getUserID());
        userMessage.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ConfessBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @SuppressLint("WrongConstant")
                    @Override
                    public void onNext(ConfessBean confessBean) {
                        if (confessBean.getMsg().equals("成功")) {
                            ToastUtil.showToast(ModifyTheRecognitionToRaiseActivity.this, confessBean.getData().getMessage());
                            FinalContents.setTiaozhuang("认筹成功");
                            finish();
                        } else {
                            ToastUtil.showToast(ModifyTheRecognitionToRaiseActivity.this, confessBean.getData().getMessage());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("认筹信息", "错误" + e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void initData() {
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);

        Observable<EarnestMoneyAuditBean> userMessage = fzbInterface.getEarnestMoneyAuditBean(FinalContents.getUserID(), FinalContents.getPreparationId(), "1");
        userMessage.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<EarnestMoneyAuditBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(EarnestMoneyAuditBean earnestMoneyAuditBean) {
                        modify_the_recognition_to_raise_tv1.setText(earnestMoneyAuditBean.getData().getFullName());
                        if (earnestMoneyAuditBean.getData().getGender().equals("男")) {
                            modify_the_recognition_to_raise_rb1.setChecked(true);
                        } else if (earnestMoneyAuditBean.getData().getGender().equals("女")) {
                            modify_the_recognition_to_raise_rb2.setChecked(true);
                        }
                        modify_the_recognition_to_raise_tv2.setText(earnestMoneyAuditBean.getData().getPhone());
                        modify_the_recognition_to_raise_tv3.setText(earnestMoneyAuditBean.getData().getIdNumber());
                        modify_the_recognition_to_raise_tv4.setText(earnestMoneyAuditBean.getData().getRelation());
                        modify_the_recognition_to_raise_tv5.setText(earnestMoneyAuditBean.getData().getIntentionPier());
                        if (earnestMoneyAuditBean.getData().getApartment().equals("1")) {
                            modify_the_recognition_to_raise_tv6.setText("一室");
                        } else if (earnestMoneyAuditBean.getData().getApartment().equals("2")) {
                            modify_the_recognition_to_raise_tv6.setText("二室");
                        } else if (earnestMoneyAuditBean.getData().getApartment().equals("3")) {
                            modify_the_recognition_to_raise_tv6.setText("三室");
                        } else if (earnestMoneyAuditBean.getData().getApartment().equals("4")) {
                            modify_the_recognition_to_raise_tv6.setText("四室");
                        } else if (earnestMoneyAuditBean.getData().getApartment().equals("5")) {
                            modify_the_recognition_to_raise_tv6.setText("五室");
                        } else if (earnestMoneyAuditBean.getData().getApartment().equals("6")) {
                            modify_the_recognition_to_raise_tv6.setText("五室以上");
                        } else if (earnestMoneyAuditBean.getData().getApartment().equals("0")) {
                            modify_the_recognition_to_raise_tv6.setText("不限");
                        }
                        modify_the_recognition_to_raise_tv7.setText(earnestMoneyAuditBean.getData().getIntentionalArea());
                        modify_the_recognition_to_raise_tv8.setText(earnestMoneyAuditBean.getData().getRecognizeTime());
                        id = earnestMoneyAuditBean.getData().getId();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("wsw", "返回的数据" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    //选择器
    private void initSelect(final List<String> list, final TextView textView) {
        //      监听选中
        OptionsPickerView pvOptions = new OptionsPickerBuilder(ModifyTheRecognitionToRaiseActivity.this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                //               返回的分别是三个级别的选中位置
                //              展示选中数据
                textView.setText(list.get(options1));
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

    private void initSelect(final TextView textView) {
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<DictListBean> userMessage = fzbInterface.getDictList(FinalContents.getUserID(), "apartment");
        userMessage.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DictListBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @SuppressLint("WrongConstant")
                    @Override
                    public void onNext(DictListBean dictListBean) {
                        List<String> list = new ArrayList<>();
                        List<DictListBean.DataBean> dictListBeanList = dictListBean.getData();
                        for (int i = 0; i < dictListBeanList.size(); i++) {
                            list.add(dictListBeanList.get(i).getName());
                        }
                        initSelect(list, textView);
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

    //TODO 认筹时间赋值
    private void initDate() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        String dateString = String.format(Locale.getDefault(), "%d年%02d月%02d日", year, month, dayOfMonth);
        pickerView.setStartDate(new GregorianCalendar(year - 2, month - 1, dayOfMonth));
        // 注意：月份是从0开始计数的
        pickerView.setSelectedDate(new GregorianCalendar(year, month - 1, dayOfMonth));
        pickerView.setEndDate(new GregorianCalendar(year, month - 1, dayOfMonth));

        picker_ensure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                picker.setVisibility(View.GONE);
            }
        });

        picker_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                picker.setVisibility(View.GONE);
            }
        });

        pickerView.setOnSelectedDateChangedListener(new DateTimePickerView.OnSelectedDateChangedListener() {
            @Override
            public void onSelectedDateChanged(Calendar date) {
                int year = date.get(Calendar.YEAR);
                int month = date.get(Calendar.MONTH);
                int dayOfMonth = date.get(Calendar.DAY_OF_MONTH);
                String dateString = String.format(Locale.getDefault(), "%d年%02d月%02d日", year, month + 1, dayOfMonth);
                modify_the_recognition_to_raise_tv8.setText(dateString);
            }
        });

        //            TODO 时间
        modify_the_recognition_to_raise_tv8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                picker.setVisibility(View.VISIBLE);
                pickerView.setOnSelectedDateChangedListener(new DateTimePickerView.OnSelectedDateChangedListener() {
                    @Override
                    public void onSelectedDateChanged(Calendar date) {
                        int year = date.get(Calendar.YEAR);
                        int month = date.get(Calendar.MONTH);
                        int dayOfMonth = date.get(Calendar.DAY_OF_MONTH);
                        String dateString = String.format(Locale.getDefault(), "%d年%02d月%02d日", year, month + 1, dayOfMonth);
                        modify_the_recognition_to_raise_tv8.setText(dateString);
                    }
                });
            }
        });

    }

}

package com.xcy.fzbcity.captain_team.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.persente.SingleClick;
import com.xcy.fzbcity.all.persente.StatusBar;
import com.xcy.fzbcity.all.utils.CommonUtil;
import com.xcy.fzbcity.all.utils.ToastUtil;
import com.xcy.fzbcity.all.view.AllActivity;
import com.xcy.fzbcity.captain_team.adapter.Captain_Team_MarketAdapter;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

import top.defaults.view.DateTimePickerView;

public class Captain_Team_MarketActivity extends AllActivity implements View.OnClickListener {

    RelativeLayout market_img;

    LinearLayout market_time_ll1;
    LinearLayout market_time_ll2;
    LinearLayout market_time_ll3;
    LinearLayout market_time_ll4;
    LinearLayout market_time_ll5;
    LinearLayout market_time_ll6;
    LinearLayout market_time_ll7;
    LinearLayout market_time_ll8;
    LinearLayout market_time_ll9;
    LinearLayout market_time_ll10;
    LinearLayout market_time_ll11;
    LinearLayout market_time_ll12;

    TextView market_time_tv1;
    TextView market_time_tv2;
    TextView market_time_tv3;
    TextView market_time_tv4;
    TextView market_time_tv5;
    TextView market_time_tv6;
    TextView market_time_tv7;
    TextView market_time_tv8;
    TextView market_time_tv9;
    TextView market_time_tv10;

    TextView market_time_time_tv1;
    TextView market_time_time_tv2;
    TextView market_time_time_tv3;
    TextView market_time_time_tv4;
    TextView market_time_time_tv5;
    TextView market_time_time_tv6;

    RadioGroup market_time_rg1;
    RadioGroup market_time_rg2;
    RadioGroup market_time_rg3;

    RadioButton market_time_rb1;
    RadioButton market_time_rb2;
    RadioButton market_time_rb3;
    RadioButton market_time_rb4;
    RadioButton market_time_rb5;
    RadioButton market_time_rb6;
    RadioButton market_time_rb7;
    RadioButton market_time_rb8;
    RadioButton market_time_rb9;
    RadioButton market_time_rb10;
    RadioButton market_time_rb11;
    RadioButton market_time_rb12;

    RelativeLayout market_time_rl;

    RecyclerView market_time_rv;

    Captain_Team_MarketAdapter adapter;


    DateTimePickerView dateTimePickerView;
    LinearLayout report_picker;
    TextView report_cancel;
    TextView report_ensure;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.captain_team_activity_market);
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

        market_img = findViewById(R.id.market_img);

        market_time_ll1 = findViewById(R.id.market_time_ll1);
        market_time_ll2 = findViewById(R.id.market_time_ll2);
        market_time_ll3 = findViewById(R.id.market_time_ll3);
        market_time_ll4 = findViewById(R.id.market_time_ll4);
        market_time_ll5 = findViewById(R.id.market_time_ll5);
        market_time_ll6 = findViewById(R.id.market_time_ll6);
        market_time_ll7 = findViewById(R.id.market_time_ll7);
        market_time_ll8 = findViewById(R.id.market_time_ll8);
        market_time_ll9 = findViewById(R.id.market_time_ll9);
        market_time_ll10 = findViewById(R.id.market_time_ll10);
        market_time_ll11 = findViewById(R.id.market_time_ll11);
        market_time_ll12 = findViewById(R.id.market_time_ll12);

        market_time_tv1 = findViewById(R.id.market_tv1);
        market_time_tv2 = findViewById(R.id.market_tv2);
        market_time_tv3 = findViewById(R.id.market_tv3);
        market_time_tv4 = findViewById(R.id.market_tv4);
        market_time_tv5 = findViewById(R.id.market_tv5);
        market_time_tv6 = findViewById(R.id.market_tv6);
        market_time_tv7 = findViewById(R.id.market_tv7);
        market_time_tv8 = findViewById(R.id.market_tv8);
        market_time_tv9 = findViewById(R.id.market_tv9);
        market_time_tv10 = findViewById(R.id.market_tv10);

        market_time_time_tv1 = findViewById(R.id.market_time_tv1);
        market_time_time_tv2 = findViewById(R.id.market_time_tv2);
        market_time_time_tv3 = findViewById(R.id.market_time_tv3);
        market_time_time_tv4 = findViewById(R.id.market_time_tv4);
        market_time_time_tv5 = findViewById(R.id.market_time_tv5);
        market_time_time_tv6 = findViewById(R.id.market_time_tv6);

        market_time_rg1 = findViewById(R.id.market_time_rg1);
        market_time_rg2 = findViewById(R.id.market_time_rg2);
        market_time_rg3 = findViewById(R.id.market_time_rg3);

        market_time_rb1 = findViewById(R.id.market_time_rb1);
        market_time_rb2 = findViewById(R.id.market_time_rb2);
        market_time_rb3 = findViewById(R.id.market_time_rb3);
        market_time_rb4 = findViewById(R.id.market_time_rb4);
        market_time_rb5 = findViewById(R.id.market_time_rb5);
        market_time_rb6 = findViewById(R.id.market_time_rb6);
        market_time_rb7 = findViewById(R.id.market_time_rb7);
        market_time_rb8 = findViewById(R.id.market_time_rb8);
        market_time_rb9 = findViewById(R.id.market_time_rb9);
        market_time_rb10 = findViewById(R.id.market_time_rb10);
        market_time_rb11 = findViewById(R.id.market_time_rb11);
        market_time_rb12 = findViewById(R.id.market_time_rb12);

        market_time_rl = findViewById(R.id.market_time_rl);

        market_time_rv = findViewById(R.id.market_time_rv);


        dateTimePickerView = findViewById(R.id.market_pickerView);
        report_picker = findViewById(R.id.market_picker);
        report_cancel = findViewById(R.id.market_cancel);
        report_ensure = findViewById(R.id.market_ensure);


        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        String string = String.format(Locale.getDefault(), "%d.%02d.%02d", year, month, dayOfMonth);
        market_time_time_tv1.setText(string);
        market_time_time_tv2.setText(string);
        market_time_time_tv3.setText(string);
        market_time_time_tv4.setText(string);
        market_time_time_tv5.setText(string);
        market_time_time_tv6.setText(string);

        dateTimePickerView.setStartDate(Calendar.getInstance());
        // 注意：月份是从0开始计数的
        dateTimePickerView.setSelectedDate(new GregorianCalendar(year, month, dayOfMonth));

        market_img.setOnClickListener(this);
        market_time_rl.setOnClickListener(this);
        market_time_ll1.setOnClickListener(this);
        market_time_ll2.setOnClickListener(this);
        market_time_ll3.setOnClickListener(this);
        market_time_ll4.setOnClickListener(this);
        market_time_ll5.setOnClickListener(this);
        market_time_ll6.setOnClickListener(this);
        market_time_ll7.setOnClickListener(this);
        market_time_ll8.setOnClickListener(this);
        market_time_ll9.setOnClickListener(this);
        market_time_time_tv1.setOnClickListener(this);
        market_time_time_tv2.setOnClickListener(this);
        market_time_time_tv3.setOnClickListener(this);
        market_time_time_tv4.setOnClickListener(this);
        market_time_time_tv5.setOnClickListener(this);
        market_time_time_tv6.setOnClickListener(this);

        market_time_rg1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (R.id.market_time_rb1 == i) {
                    market_time_ll10.setVisibility(View.GONE);
                } else if (R.id.market_time_rb2 == i) {
                    market_time_ll10.setVisibility(View.GONE);
                } else if (R.id.market_time_rb3 == i) {
                    market_time_ll10.setVisibility(View.GONE);
                } else if (R.id.market_time_rb4 == i) {
                    market_time_ll10.setVisibility(View.VISIBLE);
                }
            }
        });
        market_time_rg2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (R.id.market_time_rb5 == i) {
                    market_time_ll11.setVisibility(View.GONE);
                } else if (R.id.market_time_rb6 == i) {
                    market_time_ll11.setVisibility(View.GONE);
                } else if (R.id.market_time_rb7 == i) {
                    market_time_ll11.setVisibility(View.GONE);
                } else if (R.id.market_time_rb8 == i) {
                    market_time_ll11.setVisibility(View.VISIBLE);
                }
            }
        });
        market_time_rg3.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (R.id.market_time_rb9 == i) {
                    market_time_ll12.setVisibility(View.GONE);
                } else if (R.id.market_time_rb10 == i) {
                    market_time_ll12.setVisibility(View.GONE);
                } else if (R.id.market_time_rb11 == i) {
                    market_time_ll12.setVisibility(View.GONE);
                } else if (R.id.market_time_rb12 == i) {
                    market_time_ll12.setVisibility(View.VISIBLE);
                }
            }
        });
        report_ensure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                report_picker.setVisibility(View.GONE);
            }
        });

        report_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                report_picker.setVisibility(View.GONE);
            }
        });
        initData();

    }

    @SingleClick(1000)
    @Override
    public void onClick(View view) {

        switch (view.getId()) {
//            TODO 返回上一层
            case R.id.market_img:
                finish();
                break;
//            TODO 顾问
            case R.id.market_time_rl:
//                intent = new Intent(MarketActivity.this,);
//                startActivity(intent);
                break;
//            TODO 报备
            case R.id.market_time_ll1:
//                intent = new Intent(MarketActivity.this,);
//                startActivity(intent);
                break;
//            TODO 到访
            case R.id.market_time_ll2:
//                intent = new Intent(MarketActivity.this,);
//                startActivity(intent);
                break;
//            TODO 登岛
            case R.id.market_time_ll3:
//                intent = new Intent(MarketActivity.this,);
//                startActivity(intent);
                break;
//            TODO 认筹
            case R.id.market_time_ll4:
//                intent = new Intent(MarketActivity.this,);
//                startActivity(intent);
                break;
//            TODO 成交
            case R.id.market_time_ll5:
//                intent = new Intent(MarketActivity.this,);
//                startActivity(intent);
                break;
//            TODO 失效
            case R.id.market_time_ll6:
//                intent = new Intent(MarketActivity.this,);
//                startActivity(intent);
                break;
//            TODO 总佣金
            case R.id.market_time_ll7:
//                intent = new Intent(MarketActivity.this,);
//                startActivity(intent);
                break;
//            TODO 已结
            case R.id.market_time_ll8:
//                intent = new Intent(MarketActivity.this,);
//                startActivity(intent);
                break;
//            TODO 未结
            case R.id.market_time_ll9:
//                intent = new Intent(MarketActivity.this,);
//                startActivity(intent);
                break;
//            TODO 时间选择1
            case R.id.market_time_tv1:

                report_picker.setVisibility(View.VISIBLE);
                dateTimePickerView.setOnSelectedDateChangedListener(new DateTimePickerView.OnSelectedDateChangedListener() {
                    @Override
                    public void onSelectedDateChanged(Calendar date) {
                        int year = date.get(Calendar.YEAR);
                        int month = date.get(Calendar.MONTH);
                        int dayOfMonth = date.get(Calendar.DAY_OF_MONTH);
                        String dateString = String.format(Locale.getDefault(), "%d.%02d.%02d", year, month + 1, dayOfMonth);
                        market_time_time_tv1.setText(dateString);

                    }
                });

                break;
//            TODO 时间选择2
            case R.id.market_time_tv2:
                report_picker.setVisibility(View.VISIBLE);
                dateTimePickerView.setOnSelectedDateChangedListener(new DateTimePickerView.OnSelectedDateChangedListener() {
                    @Override
                    public void onSelectedDateChanged(Calendar date) {
                        int year = date.get(Calendar.YEAR);
                        int month = date.get(Calendar.MONTH);
                        int dayOfMonth = date.get(Calendar.DAY_OF_MONTH);
                        String dateString = String.format(Locale.getDefault(), "%d.%02d.%02d", year, month + 1, dayOfMonth);
                        market_time_time_tv2.setText(dateString);

                    }
                });
                break;
//            TODO 时间选择3
            case R.id.market_time_tv3:
                report_picker.setVisibility(View.VISIBLE);
                dateTimePickerView.setOnSelectedDateChangedListener(new DateTimePickerView.OnSelectedDateChangedListener() {
                    @Override
                    public void onSelectedDateChanged(Calendar date) {
                        int year = date.get(Calendar.YEAR);
                        int month = date.get(Calendar.MONTH);
                        int dayOfMonth = date.get(Calendar.DAY_OF_MONTH);
                        String dateString = String.format(Locale.getDefault(), "%d.%02d.%02d", year, month + 1, dayOfMonth);
                        market_time_time_tv3.setText(dateString);

                    }
                });
                break;
//            TODO 时间选择4
            case R.id.market_time_tv4:
                report_picker.setVisibility(View.VISIBLE);
                dateTimePickerView.setOnSelectedDateChangedListener(new DateTimePickerView.OnSelectedDateChangedListener() {
                    @Override
                    public void onSelectedDateChanged(Calendar date) {
                        int year = date.get(Calendar.YEAR);
                        int month = date.get(Calendar.MONTH);
                        int dayOfMonth = date.get(Calendar.DAY_OF_MONTH);
                        String dateString = String.format(Locale.getDefault(), "%d.%02d.%02d", year, month + 1, dayOfMonth);
                        market_time_time_tv4.setText(dateString);

                    }
                });
                break;
//            TODO 时间选择5
            case R.id.market_time_tv5:
                report_picker.setVisibility(View.VISIBLE);
                dateTimePickerView.setOnSelectedDateChangedListener(new DateTimePickerView.OnSelectedDateChangedListener() {
                    @Override
                    public void onSelectedDateChanged(Calendar date) {
                        int year = date.get(Calendar.YEAR);
                        int month = date.get(Calendar.MONTH);
                        int dayOfMonth = date.get(Calendar.DAY_OF_MONTH);
                        String dateString = String.format(Locale.getDefault(), "%d.%02d.%02d", year, month + 1, dayOfMonth);
                        market_time_time_tv5.setText(dateString);

                    }
                });
                break;
//            TODO 时间选择6
            case R.id.market_time_tv6:
                report_picker.setVisibility(View.VISIBLE);
                dateTimePickerView.setOnSelectedDateChangedListener(new DateTimePickerView.OnSelectedDateChangedListener() {
                    @Override
                    public void onSelectedDateChanged(Calendar date) {
                        int year = date.get(Calendar.YEAR);
                        int month = date.get(Calendar.MONTH);
                        int dayOfMonth = date.get(Calendar.DAY_OF_MONTH);
                        String dateString = String.format(Locale.getDefault(), "%d.%02d.%02d", year, month + 1, dayOfMonth);
                        market_time_time_tv6.setText(dateString);

                    }
                });
                break;
        }

    }

    @SuppressLint("WrongConstant")
    private void initData() {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        market_time_rv.setLayoutManager(manager);
        adapter = new Captain_Team_MarketAdapter();


        market_time_rv.setAdapter(adapter);

    }
}

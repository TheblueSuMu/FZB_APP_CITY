package com.xcy.fzbcity.captain_team.view;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.adapter.MyFragmentPagerAdapter;
import com.xcy.fzbcity.all.api.FinalContents;
import com.xcy.fzbcity.all.api.NewlyIncreased;
import com.xcy.fzbcity.all.database.DailyTurnoverBean;
import com.xcy.fzbcity.all.database.DataStatisticsBean;
import com.xcy.fzbcity.all.database.MyTeamBean;
import com.xcy.fzbcity.all.database.TeamCommissionsBean;
import com.xcy.fzbcity.all.fragment.MyFragment5;
import com.xcy.fzbcity.all.fragment.MyFragment6;
import com.xcy.fzbcity.all.persente.Fragnemt_SS;
import com.xcy.fzbcity.all.persente.MyLinearLayoutManager;
import com.xcy.fzbcity.all.persente.SingleClick;
import com.xcy.fzbcity.all.persente.StatusBar;
import com.xcy.fzbcity.all.service.MyService;
import com.xcy.fzbcity.all.utils.CommonUtil;
import com.xcy.fzbcity.all.utils.MyViewPager;
import com.xcy.fzbcity.all.utils.ToastUtil;
import com.xcy.fzbcity.all.view.AllActivity;
import com.xcy.fzbcity.captain_team.adapter.DailyTurnoverAdapter;
import com.xcy.fzbcity.captain_team.adapter.MyTeamAdapter;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//TODO 圈层4-1我的团队
public class Captain_Team_MyTeamActivity extends AllActivity implements View.OnClickListener, MyViewPager.OnSingleTouchListener {

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
    LinearLayout market_time_ll13;
    LinearLayout market_time_ll14;
    LinearLayout market_time_ll15;
    LinearLayout market_time_ll16;

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
    TextView market_time_tv11;
    TextView market_time_tv12;
    TextView market_time_tv13;

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

    RelativeLayout market_time_rl1;
    RelativeLayout market_time_rl2;

    RecyclerView market_time_rv;

    private Intent intent;

    String type1 = "";
    String startDate1 = "";
    String endDate1 = "";

    String type2 = "";
    String startDate2 = "";
    String endDate2 = "";

    String state = "2";

    String type3 = "";
    String startDate3 = "";
    String endDate3 = "";
    private PtrClassicFrameLayout ptrClassicFrameLayout;

    ImageView all_no_information_S_S;


    private MyViewPager vpager_one;
    private ArrayList<Fragment> aList = new ArrayList<>();
    private MyFragmentPagerAdapter mAdapter;

    LinearLayout fragment_ll_1;
    LinearLayout fragment_ll_2;

    String type = "";
    LinearLayout project_attache_ll1;
    LinearLayout project_attache_ll2;
    LinearLayout project_attache_ll3;
    LinearLayout project_attache_ll4;

    String tag = "1";
    private String string;
    private int year;
    private int month;
    private int dayOfMonth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.captain_team_activity_my_team);
        init_No_Network();
    }

    private void init_No_Network() {
        boolean networkAvailable = CommonUtil.isNetworkAvailable(this);
        if (networkAvailable) {
            NewlyIncreased.setTag("");
            NewlyIncreased.setStartDate("");
            NewlyIncreased.setEndDate("");
            NewlyIncreased.setYJType("0");
            NewlyIncreased.setYJstartDate("");
            NewlyIncreased.setYJendDate("");
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
        FinalContents.setMySelf("0");

        StatusBar.makeStatusBarTransparent(this);
        ptrClassicFrameLayout = findViewById(R.id.my_team_PtrClassic);

        fragment_ll_2 = findViewById(R.id.fragment_llsss_2);
        fragment_ll_1 = findViewById(R.id.fragment_llsss_1);
        vpager_one = findViewById(R.id.vpager_one_s4);
        vpager_one.setOnSingleTouchListener(this);
        if (FinalContents.getFragmentSS().equals("0")) {
            mAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager());
            FinalContents.setFragmentSS("1");
            aList.add(new MyFragment5());
            aList.add(new MyFragment6());

            mAdapter.setListfragment(aList);
            vpager_one.setAdapter(mAdapter);
            vpager_one.setCurrentItem(0);
        }

        vpager_one.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
//                    fragment_ll_1.setBackgroundColor(Color.parseColor("#334485"));
//                    fragment_ll_2.setBackgroundColor(Color.parseColor("#EEEEEE"));

                    fragment_ll_1.setBackgroundResource(R.drawable.checkbox_underline_shape_s);
                    fragment_ll_2.setBackgroundResource(R.drawable.checkbox_underline_shape_s_s);

                } else if (position == 1) {
//                    fragment_ll_2.setBackgroundColor(Color.parseColor("#334485"));
//                    fragment_ll_1.setBackgroundColor(Color.parseColor("#EEEEEE"));

                    fragment_ll_1.setBackgroundResource(R.drawable.checkbox_underline_shape_s_s);
                    fragment_ll_2.setBackgroundResource(R.drawable.checkbox_underline_shape_s);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        project_attache_ll1 = findViewById(R.id.project_attache_ll1);
        project_attache_ll2 = findViewById(R.id.project_attache_ll2);
        project_attache_ll3 = findViewById(R.id.project_attache_ll3);
        project_attache_ll4 = findViewById(R.id.project_attache_ll4);

        project_attache_ll1.setOnClickListener(this);
        project_attache_ll3.setOnClickListener(this);

        market_time_ll1 = findViewById(R.id.my_team_ll1);
        all_no_information_S_S = findViewById(R.id.all_no_information_S_S);
        market_time_ll2 = findViewById(R.id.my_team_ll2);
        market_time_ll3 = findViewById(R.id.my_team_ll3);
        market_time_ll4 = findViewById(R.id.my_team_ll4);
        market_time_ll5 = findViewById(R.id.my_team_ll5);
        market_time_ll6 = findViewById(R.id.my_team_ll6);
        market_time_ll7 = findViewById(R.id.my_team_ll7);
        market_time_ll8 = findViewById(R.id.my_team_ll8);
        market_time_ll9 = findViewById(R.id.my_team_ll9);
        market_time_ll10 = findViewById(R.id.my_team_ll10);
        market_time_ll11 = findViewById(R.id.my_team_ll11);
        market_time_ll12 = findViewById(R.id.my_team_ll12);
        market_time_ll13 = findViewById(R.id.my_team_ll13);
        market_time_ll14 = findViewById(R.id.my_team_ll14);
        market_time_ll15 = findViewById(R.id.my_team_ll15);
        market_time_ll16 = findViewById(R.id.my_team_ll16);

//        market_time_tv1 = findViewById(R.id.my_team_tv1);
        market_time_tv2 = findViewById(R.id.my_team_tv2);
        market_time_tv3 = findViewById(R.id.my_team_tv3);
        market_time_tv4 = findViewById(R.id.my_team_tv4);
        market_time_tv5 = findViewById(R.id.my_team_tv5);
        market_time_tv6 = findViewById(R.id.my_team_tv6);
        market_time_tv7 = findViewById(R.id.my_team_tv7);
        market_time_tv8 = findViewById(R.id.my_team_tv8);
        market_time_tv9 = findViewById(R.id.my_team_tv9);
        market_time_tv10 = findViewById(R.id.my_team_tv10);
//        market_time_tv11 = findViewById(R.id.my_team_tv11);
        market_time_tv12 = findViewById(R.id.my_team_tv12);
        market_time_tv13 = findViewById(R.id.my_team_tv13);

        market_time_time_tv1 = findViewById(R.id.my_team_time_tv1);
        market_time_time_tv2 = findViewById(R.id.my_team_time_tv2);
        market_time_time_tv3 = findViewById(R.id.my_team_time_tv3);
        market_time_time_tv4 = findViewById(R.id.my_team_time_tv4);
        market_time_time_tv5 = findViewById(R.id.my_team_time_tv5);
        market_time_time_tv6 = findViewById(R.id.my_team_time_tv6);

        market_time_rg1 = findViewById(R.id.my_team_rg1);
        market_time_rg2 = findViewById(R.id.my_team_rg2);
        market_time_rg3 = findViewById(R.id.my_team_rg3);

        market_time_rb1 = findViewById(R.id.my_team_rb1);
        market_time_rb2 = findViewById(R.id.my_team_rb2);
        market_time_rb3 = findViewById(R.id.my_team_rb3);
        market_time_rb4 = findViewById(R.id.my_team_rb4);
        market_time_rb5 = findViewById(R.id.my_team_rb5);
        market_time_rb6 = findViewById(R.id.my_team_rb6);
        market_time_rb7 = findViewById(R.id.my_team_rb7);
        market_time_rb8 = findViewById(R.id.my_team_rb8);
        market_time_rb9 = findViewById(R.id.my_team_rb9);
        market_time_rb10 = findViewById(R.id.my_team_rb10);
        market_time_rb11 = findViewById(R.id.my_team_rb11);
        market_time_rb12 = findViewById(R.id.my_team_rb12);

//        market_time_rl1 = findViewById(R.id.my_team_rl1);
//        market_time_rl2 = findViewById(R.id.my_team_rl2);

        market_time_rv = findViewById(R.id.my_team_rv);
        market_img = findViewById(R.id.my_team_img);


        ptrClassicFrameLayout.setPtrHandler(new PtrHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                frame.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ptrClassicFrameLayout.refreshComplete();
                        ptrClassicFrameLayout.setLastUpdateTimeKey("2017-2-10");
                        initMyTeamData();
                    }
                }, 1000);
            }

            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
            }
        });

        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        string = String.format(Locale.getDefault(), "%d.%02d.%02d", year, month + 1, dayOfMonth);
        market_time_time_tv1.setText(string);
        market_time_time_tv2.setText(string);
        market_time_time_tv3.setText(string);
        market_time_time_tv4.setText(string);
        market_time_time_tv5.setText(string);
        market_time_time_tv6.setText(string);

        startDate1 = string;
        endDate1 = string;
        startDate2 = string;
        endDate2 = string;
        startDate3 = string;
        endDate3 = string;


        market_img.setOnClickListener(this);
//        market_time_rl1.setOnClickListener(this);
//        market_time_rl2.setOnClickListener(this);
        market_time_ll1.setOnClickListener(this);
        market_time_ll2.setOnClickListener(this);
        market_time_ll3.setOnClickListener(this);
        market_time_ll4.setOnClickListener(this);
        market_time_ll5.setOnClickListener(this);
        market_time_ll6.setOnClickListener(this);
        market_time_ll7.setOnClickListener(this);
        market_time_ll8.setOnClickListener(this);
        market_time_ll9.setOnClickListener(this);
        market_time_ll13.setOnClickListener(this);
        market_time_ll15.setOnClickListener(this);
        market_time_time_tv1.setOnClickListener(this);
        market_time_time_tv2.setOnClickListener(this);
        market_time_time_tv3.setOnClickListener(this);
        market_time_time_tv4.setOnClickListener(this);
        market_time_time_tv5.setOnClickListener(this);
        market_time_time_tv6.setOnClickListener(this);

        market_time_rb1.setOnClickListener(this);
        market_time_rb2.setOnClickListener(this);
        market_time_rb3.setOnClickListener(this);
        market_time_rb4.setOnClickListener(this);
        market_time_rb5.setOnClickListener(this);
        market_time_rb6.setOnClickListener(this);
        market_time_rb7.setOnClickListener(this);
        market_time_rb8.setOnClickListener(this);
        market_time_rb9.setOnClickListener(this);
        market_time_rb10.setOnClickListener(this);
        market_time_rb11.setOnClickListener(this);
        market_time_rb12.setOnClickListener(this);

        initMyTeamData();
        initDailyTurnover();
    }

    @SingleClick(1000)
    @Override
    public void onClick(View view) {
        if (NewlyIncreased.getTag().equals("3")) {
            NewlyIncreased.setStartDate(startDate1);
            NewlyIncreased.setEndDate(endDate1);
        }else {
            NewlyIncreased.setStartDate("");
            NewlyIncreased.setEndDate("");
        }

        if (NewlyIncreased.getYJType().equals("3")) {
            NewlyIncreased.setYJstartDate(startDate2);
            NewlyIncreased.setYJendDate(endDate2);
        }else {
            NewlyIncreased.setYJstartDate("");
            NewlyIncreased.setYJendDate("");
        }
        switch (view.getId()) {
//            TODO 返回上一层
            case R.id.my_team_img:
                finish();
                break;
////            TODO 销售（上）
//            case R.id.my_team_rl1:
//                intent = new Intent(Captain_Team_MyTeamActivity.this,Captain_Team_TeamMemberActivity.class);
//                intent.putExtra("tdz","1");
//                startActivity(intent);
//                break;
////            TODO 顾问（上）
//            case R.id.my_team_rl2:
//                intent = new Intent(Captain_Team_MyTeamActivity.this,Captain_Team_TeamMemberActivity.class);
//                intent.putExtra("tdz","2");
//                startActivity(intent);
//                break;
//            TODO 报备
            case R.id.my_team_ll1:
                intent = new Intent(Captain_Team_MyTeamActivity.this, Captain_Team_MyClientActivity.class);
                intent.putExtra("client", "1");
                /**
                 * 修改
                 */
                FinalContents.setQuanceng("1");
                FinalContents.setMySelf("0");
                FinalContents.setAgentId(FinalContents.getUserID());
                startActivity(intent);
                break;
//            TODO 到访
            case R.id.my_team_ll2:
                intent = new Intent(Captain_Team_MyTeamActivity.this, Captain_Team_MyClientActivity.class);
                intent.putExtra("client", "2");
                /**
                 * 修改
                 */
                FinalContents.setQuanceng("1");
                FinalContents.setMySelf("0");
                FinalContents.setAgentId(FinalContents.getUserID());
                startActivity(intent);
                break;
//            TODO 登岛
            case R.id.my_team_ll3:
                intent = new Intent(Captain_Team_MyTeamActivity.this, Captain_Team_MyClientActivity.class);
                intent.putExtra("client", "3");
                /**
                 * 修改
                 */
                FinalContents.setQuanceng("1");
                FinalContents.setMySelf("0");
                FinalContents.setAgentId(FinalContents.getUserID());
                startActivity(intent);
                break;
//            TODO 认筹
            case R.id.my_team_ll4:
                intent = new Intent(Captain_Team_MyTeamActivity.this, Captain_Team_MyClientActivity.class);
                intent.putExtra("client", "4");
                /**
                 * 修改
                 */
                FinalContents.setQuanceng("1");
                FinalContents.setMySelf("0");
                FinalContents.setAgentId(FinalContents.getUserID());
                startActivity(intent);
                break;
//            TODO 成交
            case R.id.my_team_ll5:
                intent = new Intent(Captain_Team_MyTeamActivity.this, Captain_Team_MyClientActivity.class);
                intent.putExtra("client", "5");
                /**
                 * 修改
                 */
                FinalContents.setQuanceng("1");
                FinalContents.setMySelf("0");
                FinalContents.setAgentId(FinalContents.getUserID());
                startActivity(intent);
                break;
//            TODO 失效
            case R.id.my_team_ll6:
                intent = new Intent(Captain_Team_MyTeamActivity.this, Captain_Team_MyClientActivity.class);
                intent.putExtra("client", "6");
                /**
                 * 修改
                 */
                FinalContents.setQuanceng("1");
                FinalContents.setMySelf("0");
                FinalContents.setAgentId(FinalContents.getUserID());
                startActivity(intent);
                break;
//            TODO 总佣金
            case R.id.my_team_ll7:
                intent = new Intent(Captain_Team_MyTeamActivity.this, Captain_Team_CommissionTheProjectEndActivity.class);
                startActivity(intent);
                break;
//            TODO 已结
            case R.id.my_team_ll8:
                intent = new Intent(Captain_Team_MyTeamActivity.this, Captain_Team_CommissionTheProjectEndActivity.class);
                startActivity(intent);
                break;
//            TODO 未结
            case R.id.my_team_ll9:
                intent = new Intent(Captain_Team_MyTeamActivity.this, Captain_Team_CommissionTheProjectEndActivity.class);
                startActivity(intent);
                break;
//            TODO 销售（下）
            case R.id.my_team_ll13:
                market_time_ll14.setVisibility(View.VISIBLE);
                market_time_ll16.setVisibility(View.GONE);
                market_time_tv12.setTextColor(Color.parseColor("#334485"));
                market_time_tv13.setTextColor(Color.parseColor("#333333"));
                state = "2";
                initDailyTurnover();
                break;
//            TODO 顾问（下）
            case R.id.my_team_ll15:
                market_time_ll14.setVisibility(View.GONE);
                market_time_ll16.setVisibility(View.VISIBLE);
                market_time_tv13.setTextColor(Color.parseColor("#334485"));
                market_time_tv12.setTextColor(Color.parseColor("#333333"));
                state = "3";
                initDailyTurnover();
                break;
//            TODO  数据统计   开始时间 时间选择1
            case R.id.my_team_time_tv1:
                initTime1_Date1();
                break;
//            TODO 数据统计   结束时间 时间选择2
            case R.id.my_team_time_tv2:
                initTime1_Date2();
                break;
//            TODO 财务数据   开始时间 时间选择3
            case R.id.my_team_time_tv3:
                initTime2_Date1();
                break;
//            TODO 财务数据   结束时间 时间选择4
            case R.id.my_team_time_tv4:
                initTime2_Date2();
                break;
//            TODO 成交TOP5   开始时间 时间选择5
            case R.id.my_team_time_tv5:
                initTime3_Date1();
                break;
//            TODO 成交TOP5   结束时间 时间选择6
            case R.id.my_team_time_tv6:
                initTime3_Date2();
                break;
            //            TODO 数据统计 时间选择 全部
            case R.id.my_team_rb1:
                market_time_ll10.setVisibility(View.GONE);
                type1 = "0";
                startDate1 = "";
                endDate1 = "";
                NewlyIncreased.setTag("0");
                initDataStatistics();
                break;

            //            TODO 数据统计 时间选择 昨天
            case R.id.my_team_rb2:
                market_time_ll10.setVisibility(View.GONE);
                type1 = "1";
                startDate1 = "";
                endDate1 = "";
                NewlyIncreased.setTag("1");
                initDataStatistics();
                break;

            //            TODO 数据统计 时间选择 七天
            case R.id.my_team_rb3:
                market_time_ll10.setVisibility(View.GONE);
                type1 = "2";
                startDate1 = "";
                endDate1 = "";
                NewlyIncreased.setTag("2");
                initDataStatistics();
                break;

            //            TODO 数据统计 时间选择 自定义
            case R.id.my_team_rb4:
                market_time_ll10.setVisibility(View.VISIBLE);
                type1 = "3";
                startDate1 = string;
                endDate1 = string;
                market_time_time_tv1.setText(string);
                market_time_time_tv2.setText(string);
                NewlyIncreased.setTag("3");
                initDataStatistics();
                break;

            //            TODO 财务数据 时间选择 全部
            case R.id.my_team_rb5:
                market_time_ll11.setVisibility(View.GONE);
                type2 = "0";
                startDate2 = "";
                endDate2 = "";
                NewlyIncreased.setYJType("0");
                initTeamCommissions();
                break;

            //            TODO 财务数据 时间选择 昨天
            case R.id.my_team_rb6:
                market_time_ll11.setVisibility(View.GONE);
                type2 = "1";
                startDate2 = "";
                endDate2 = "";
                NewlyIncreased.setYJType("1");
                initTeamCommissions();
                break;

            //            TODO 财务数据 时间选择 七天
            case R.id.my_team_rb7:
                market_time_ll11.setVisibility(View.GONE);
                type2 = "2";
                startDate2 = "";
                endDate2 = "";
                NewlyIncreased.setYJType("2");
                initTeamCommissions();
                break;

            //            TODO 财务数据 时间选择 自定义
            case R.id.my_team_rb8:
                market_time_ll11.setVisibility(View.VISIBLE);
                type2 = "3";
                startDate2 = string;
                endDate2 = string;
                market_time_time_tv3.setText(string);
                market_time_time_tv4.setText(string);
                initTeamCommissions();
                NewlyIncreased.setYJType("3");
                break;

            //            TODO 成交TOP5 时间选择 全部
            case R.id.my_team_rb9:
                market_time_ll12.setVisibility(View.GONE);
                type3 = "0";
                startDate3 = "";
                endDate3 = "";
                initDailyTurnover();
                break;

            //            TODO 成交TOP5 时间选择 昨天
            case R.id.my_team_rb10:
                market_time_ll12.setVisibility(View.GONE);
                type3 = "1";
                startDate3 = "";
                endDate3 = "";
                initDailyTurnover();
                break;

            //            TODO 成交TOP5 时间选择 七天
            case R.id.my_team_rb11:
                market_time_ll12.setVisibility(View.GONE);
                type3 = "2";
                startDate3 = "";
                endDate3 = "";
                initDailyTurnover();
                break;

            //            TODO 成交TOP5 时间选择 自定义
            case R.id.my_team_rb12:
                market_time_ll12.setVisibility(View.VISIBLE);
                type3 = "3";
                startDate3 = string;
                endDate3 = string;
                market_time_time_tv5.setText(string);
                market_time_time_tv6.setText(string);
                initDailyTurnover();
                break;
            case R.id.project_attache_ll1://实时
                tag = "1";
                project_attache_ll2.setVisibility(View.VISIBLE);
                project_attache_ll4.setVisibility(View.INVISIBLE);
                market_time_ll1.setClickable(true);
                market_time_ll2.setClickable(true);
                market_time_ll3.setClickable(true);
                market_time_ll4.setClickable(true);
                market_time_ll5.setClickable(true);
                market_time_ll6.setClickable(true);
                if (market_time_rb1.isChecked() == true) {
                    NewlyIncreased.setTag("0");
                    type1 = "0";
                    startDate1 = "";
                    endDate1 = "";
                    initDataStatistics();
                    market_time_ll10.setVisibility(View.GONE);
                } else if (market_time_rb2.isChecked() == true) {
                    NewlyIncreased.setTag("1");
                    type1 = "1";
                    startDate1 = "";
                    endDate1 = "";
                    initDataStatistics();
                    market_time_ll10.setVisibility(View.GONE);
                } else if (market_time_rb3.isChecked() == true) {
                    NewlyIncreased.setTag("2");
                    type1 = "2";
                    startDate1 = "";
                    endDate1 = "";
                    initDataStatistics();
                    market_time_ll10.setVisibility(View.GONE);
                } else if (market_time_rb4.isChecked() == true) {
                    type1 = "3";
                    startDate1 = string;
                    endDate1 = string;
                    NewlyIncreased.setTag("3");
                    initDataStatistics();
                    market_time_ll10.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.project_attache_ll3://总体
                tag = "2";
                project_attache_ll2.setVisibility(View.INVISIBLE);
                project_attache_ll4.setVisibility(View.VISIBLE);
                market_time_ll1.setClickable(false);
                market_time_ll2.setClickable(false);
                market_time_ll3.setClickable(false);
                market_time_ll4.setClickable(false);
                market_time_ll5.setClickable(false);
                market_time_ll6.setClickable(false);
                if (market_time_rb1.isChecked() == true) {
                    NewlyIncreased.setTag("0");
                    type1 = "0";
                    startDate1 = "";
                    endDate1 = "";
                    initDataStatistics();
                    market_time_ll10.setVisibility(View.GONE);
                } else if (market_time_rb2.isChecked() == true) {
                    NewlyIncreased.setTag("1");
                    type1 = "1";
                    startDate1 = "";
                    endDate1 = "";
                    initDataStatistics();
                    market_time_ll10.setVisibility(View.GONE);
                } else if (market_time_rb3.isChecked() == true) {
                    NewlyIncreased.setTag("2");
                    type1 = "2";
                    startDate1 = "";
                    endDate1 = "";
                    initDataStatistics();
                    market_time_ll10.setVisibility(View.GONE);
                } else if (market_time_rb4.isChecked() == true) {
                    type1 = "3";
                    startDate1 = string;
                    endDate1 = string;
                    NewlyIncreased.setTag("3");
                    initDataStatistics();
                    market_time_ll10.setVisibility(View.VISIBLE);
                }
                break;
        }

    }


    //            TODO  我的团队    数据统计   开始时间
    private void initTime1_Date1(){
        Calendar selectedDate = Calendar.getInstance();//系统当前时间
        Calendar startDate = Calendar.getInstance();
        startDate.set(year, month, dayOfMonth-15);
        final Calendar endDate = Calendar.getInstance();
        endDate.set(year, month, dayOfMonth+15);
        TimePickerView pvTime = new TimePickerBuilder(Captain_Team_MyTeamActivity.this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                market_time_time_tv1.setText(getTime2(date));
                startDate1 = getTime2(date);
                NewlyIncreased.setStartDate(getTime2(date));
                initDataStatistics();
            }
        })
                .setType(new boolean[]{true, true, true, false, false, false}) //年月日时分秒 的显示与否，不设置则默认全部显示
                .setLabel("年", "月", "日", "", "", "")//默认设置为年月日时分秒
                .isCenterLabel(false)
                .setDate(selectedDate)
                .setLineSpacingMultiplier(1.2f)
                .setTextXOffset(-10, 0,10, 0, 0, 0)//设置X轴倾斜角度[ -90 , 90°]
                .setRangDate(startDate, endDate)
                .build();
        pvTime.show();
    }

    //            TODO  我的团队    数据统计   结束时间
    private void initTime1_Date2(){
        Calendar selectedDate = Calendar.getInstance();//系统当前时间
        Calendar startDate = Calendar.getInstance();
        startDate.set(year, month, dayOfMonth-15);
        final Calendar endDate = Calendar.getInstance();
        endDate.set(year, month, dayOfMonth+15);
        TimePickerView pvTime = new TimePickerBuilder(Captain_Team_MyTeamActivity.this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                market_time_time_tv2.setText(getTime2(date));
                endDate1 = getTime2(date);
                NewlyIncreased.setEndDate(getTime2(date));
                initDataStatistics();
            }
        })
                .setType(new boolean[]{true, true, true, false, false, false}) //年月日时分秒 的显示与否，不设置则默认全部显示
                .setLabel("年", "月", "日", "", "", "")//默认设置为年月日时分秒
                .isCenterLabel(false)
                .setDate(selectedDate)
                .setLineSpacingMultiplier(1.2f)
                .setTextXOffset(-10, 0,10, 0, 0, 0)//设置X轴倾斜角度[ -90 , 90°]
                .setRangDate(startDate, endDate)
                .build();
        pvTime.show();
    }

    //            TODO  我的团队    财务数据   开始时间
    private void initTime2_Date1(){
        Calendar selectedDate = Calendar.getInstance();//系统当前时间
        Calendar startDate = Calendar.getInstance();
        startDate.set(year, month, dayOfMonth-15);
        final Calendar endDate = Calendar.getInstance();
        endDate.set(year, month, dayOfMonth+15);
        TimePickerView pvTime = new TimePickerBuilder(Captain_Team_MyTeamActivity.this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                market_time_time_tv3.setText(getTime2(date));
                startDate2 = getTime2(date);
                NewlyIncreased.setYJstartDate(getTime2(date));
                initTeamCommissions();
            }
        })
                .setType(new boolean[]{true, true, true, false, false, false}) //年月日时分秒 的显示与否，不设置则默认全部显示
                .setLabel("年", "月", "日", "", "", "")//默认设置为年月日时分秒
                .isCenterLabel(false)
                .setDate(selectedDate)
                .setLineSpacingMultiplier(1.2f)
                .setTextXOffset(-10, 0,10, 0, 0, 0)//设置X轴倾斜角度[ -90 , 90°]
                .setRangDate(startDate, endDate)
                .build();
        pvTime.show();
    }

    //            TODO  我的团队    财务数据   结束时间
    private void initTime2_Date2(){
        Calendar selectedDate = Calendar.getInstance();//系统当前时间
        Calendar startDate = Calendar.getInstance();
        startDate.set(year, month, dayOfMonth-15);
        final Calendar endDate = Calendar.getInstance();
        endDate.set(year, month, dayOfMonth+15);
        TimePickerView pvTime = new TimePickerBuilder(Captain_Team_MyTeamActivity.this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                market_time_time_tv4.setText(getTime2(date));
                endDate2 = getTime2(date);
                NewlyIncreased.setYJendDate(getTime2(date));
                initTeamCommissions();
            }
        })
                .setType(new boolean[]{true, true, true, false, false, false}) //年月日时分秒 的显示与否，不设置则默认全部显示
                .setLabel("年", "月", "日", "", "", "")//默认设置为年月日时分秒
                .isCenterLabel(false)
                .setDate(selectedDate)
                .setLineSpacingMultiplier(1.2f)
                .setTextXOffset(-10, 0,10, 0, 0, 0)//设置X轴倾斜角度[ -90 , 90°]
                .setRangDate(startDate, endDate)
                .build();
        pvTime.show();
    }

    //            TODO  我的团队    成交TOP5单   开始时间
    private void initTime3_Date1(){
        Calendar selectedDate = Calendar.getInstance();//系统当前时间
        Calendar startDate = Calendar.getInstance();
        startDate.set(year, month, dayOfMonth-15);
        final Calendar endDate = Calendar.getInstance();
        endDate.set(year, month, dayOfMonth+15);
        TimePickerView pvTime = new TimePickerBuilder(Captain_Team_MyTeamActivity.this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                market_time_time_tv5.setText(getTime2(date));
                startDate3 = getTime2(date);
                initDailyTurnover();
            }
        })
                .setType(new boolean[]{true, true, true, false, false, false}) //年月日时分秒 的显示与否，不设置则默认全部显示
                .setLabel("年", "月", "日", "", "", "")//默认设置为年月日时分秒
                .isCenterLabel(false)
                .setDate(selectedDate)
                .setLineSpacingMultiplier(1.2f)
                .setTextXOffset(-10, 0,10, 0, 0, 0)//设置X轴倾斜角度[ -90 , 90°]
                .setRangDate(startDate, endDate)
                .build();
        pvTime.show();
    }

    //            TODO  我的团队    成交TOP5单   结束时间
    private void initTime3_Date2(){
        Calendar selectedDate = Calendar.getInstance();//系统当前时间
        Calendar startDate = Calendar.getInstance();
        startDate.set(year, month, dayOfMonth-15);
        final Calendar endDate = Calendar.getInstance();
        endDate.set(year, month, dayOfMonth+15);
        TimePickerView pvTime = new TimePickerBuilder(Captain_Team_MyTeamActivity.this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                market_time_time_tv6.setText(getTime2(date));
                endDate3 = getTime2(date);
                initDailyTurnover();
            }
        })
                .setType(new boolean[]{true, true, true, false, false, false}) //年月日时分秒 的显示与否，不设置则默认全部显示
                .setLabel("年", "月", "日", "", "", "")//默认设置为年月日时分秒
                .isCenterLabel(false)
                .setDate(selectedDate)
                .setLineSpacingMultiplier(1.2f)
                .setTextXOffset(-10, 0,10, 0, 0, 0)//设置X轴倾斜角度[ -90 , 90°]
                .setRangDate(startDate, endDate)
                .build();
        pvTime.show();
    }


    // TODO 数据统计
    private void initDataStatistics() {
        Log.i("自定义","实时或总体"+tag);
        Log.i("自定义","选择类型"+type1);
        Log.i("自定义","开始时间"+startDate1);
        Log.i("自定义","结束时间"+endDate1);
        NewlyIncreased.setStartDate(startDate1);
        NewlyIncreased.setEndDate(endDate1);

        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<DataStatisticsBean> clientCommissions = fzbInterface.getDataStatistics(FinalContents.getUserID(), FinalContents.getUserID(), type1, startDate1, endDate1,tag);
        clientCommissions.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DataStatisticsBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(DataStatisticsBean dataStatisticsBean) {

                        market_time_tv2.setText(dataStatisticsBean.getData().getReportNumber() + "");
                        market_time_tv3.setText(dataStatisticsBean.getData().getAccessingNumber() + "");
                        market_time_tv4.setText(dataStatisticsBean.getData().getIsIslandNumber() + "");
                        market_time_tv5.setText(dataStatisticsBean.getData().getEarnestMoneyNumber() + "");
                        market_time_tv6.setText(dataStatisticsBean.getData().getTradeNumber() + "");
                        market_time_tv7.setText(dataStatisticsBean.getData().getInvalidNum() + "");

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("MyCL", "我的佣金、用户数量:" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    // TODO 成交TOP5单
    private void initDailyTurnover() {
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<DailyTurnoverBean> clientCommissions = fzbInterface.getDailyTurnover(FinalContents.getUserID(), FinalContents.getUserID(), state, type3, startDate3, endDate3);
        clientCommissions.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DailyTurnoverBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(DailyTurnoverBean dailyTurnoverBean) {
                        if (dailyTurnoverBean.getData().size() == 0) {
                            all_no_information_S_S.setVisibility(View.VISIBLE);
                        } else {
                            all_no_information_S_S.setVisibility(View.GONE);
                            MyLinearLayoutManager layoutManager = new MyLinearLayoutManager(Captain_Team_MyTeamActivity.this);
                            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                            layoutManager.setScrollEnabled(false);
                            market_time_rv.setLayoutManager(layoutManager);
                            DailyTurnoverAdapter adapter = new DailyTurnoverAdapter(dailyTurnoverBean.getData());
                            market_time_rv.setNestedScrollingEnabled(false);
                            market_time_rv.setAdapter(adapter);
                            adapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("MyCL", "我的佣金、用户数量:" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    // TODO 团队佣金
    private void initTeamCommissions() {
        NewlyIncreased.setYJstartDate(startDate2);
        NewlyIncreased.setYJendDate(endDate2);

        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<TeamCommissionsBean> clientCommissions = fzbInterface.getTeamCommissions(FinalContents.getUserID(), FinalContents.getUserID(), type2, startDate2, endDate2);
        clientCommissions.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<TeamCommissionsBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(TeamCommissionsBean teamCommissionsBean) {
                        market_time_tv8.setText(teamCommissionsBean.getData().getTotalAmount() + "");
                        market_time_tv9.setText(teamCommissionsBean.getData().getAlreadyAmount() + "");
                        market_time_tv10.setText(teamCommissionsBean.getData().getNotAmount() + "");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("MyCL", "我的佣金、用户数量:" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    // TODO 我的团队显示数据
    private void initMyTeamData() {
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<MyTeamBean> clientCommissions = fzbInterface.getMyTeam(FinalContents.getUserID());
        clientCommissions.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MyTeamBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(MyTeamBean myTeamBean) {

//                        market_time_tv11.setText(myTeamBean.getData().getSalesNum()+"");
//                        market_time_tv1.setText(myTeamBean.getData().getCounselorNum()+"");

                        EventBus.getDefault().post(new Fragnemt_SS("", "", "", myTeamBean.getData().getSalesNum() + "", myTeamBean.getData().getCounselorNum() + ""));

                        market_time_tv2.setText(myTeamBean.getData().getDataStatistics().getReportNumber() + "");
                        market_time_tv3.setText(myTeamBean.getData().getDataStatistics().getAccessingNumber() + "");
                        market_time_tv4.setText(myTeamBean.getData().getDataStatistics().getIsIslandNumber() + "");
                        market_time_tv5.setText(myTeamBean.getData().getDataStatistics().getEarnestMoneyNumber() + "");
                        market_time_tv6.setText(myTeamBean.getData().getDataStatistics().getTradeNumber() + "");
                        market_time_tv7.setText(myTeamBean.getData().getDataStatistics().getInvalidNum() + "");

                        market_time_tv8.setText(myTeamBean.getData().getMoneyData().getTotalAmount() + "");
                        market_time_tv9.setText(myTeamBean.getData().getMoneyData().getAlreadyAmount() + "");
                        market_time_tv10.setText(myTeamBean.getData().getMoneyData().getNotAmount() + "");

                        MyLinearLayoutManager layoutManager = new MyLinearLayoutManager(Captain_Team_MyTeamActivity.this);
                        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                        layoutManager.setScrollEnabled(false);
                        market_time_rv.setLayoutManager(layoutManager);
                        MyTeamAdapter adapter = new MyTeamAdapter(myTeamBean.getData().getDailyTurnover());
                        market_time_rv.setNestedScrollingEnabled(false);
                        market_time_rv.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("MyCL", "我的佣金、用户数量:" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void onSingleTouch() {

        int currentItem = vpager_one.getCurrentItem();
        if (currentItem == 0) {
            intent = new Intent(Captain_Team_MyTeamActivity.this, Captain_Team_TeamMemberActivity.class);
            intent.putExtra("tdz", "1");
            startActivity(intent);
        } else if (currentItem == 1) {
            intent = new Intent(Captain_Team_MyTeamActivity.this, Captain_Team_TeamMemberActivity.class);
            intent.putExtra("tdz", "2");
            startActivity(intent);
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        FinalContents.setFragmentSS("0");
        NewlyIncreased.setTag("");
        NewlyIncreased.setStartDate("");
        NewlyIncreased.setEndDate("");
        NewlyIncreased.setYJType("0");
        NewlyIncreased.setYJstartDate("");
        NewlyIncreased.setYJendDate("");
        FinalContents.setFragmentSS("0");
    }
}

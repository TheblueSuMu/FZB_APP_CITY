package com.xcy.fzbcity.captain_assistant.fragment;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
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
import com.xcy.fzbcity.all.database.TeamCommissionsBean;
import com.xcy.fzbcity.all.fragment.MyFragment4;
import com.xcy.fzbcity.all.fragment.MyFragment5;
import com.xcy.fzbcity.all.fragment.MyFragment6;
import com.xcy.fzbcity.all.modle.MyTeam2Bean;
import com.xcy.fzbcity.all.persente.Fragnemt_SS;
import com.xcy.fzbcity.all.persente.MyLinearLayoutManager;
import com.xcy.fzbcity.all.persente.SingleClick;
import com.xcy.fzbcity.all.persente.StatusBar;
import com.xcy.fzbcity.all.service.MyService;
import com.xcy.fzbcity.all.utils.MyViewPager;
import com.xcy.fzbcity.captain_assistant.view.Assistant_Teams_Activity;
import com.xcy.fzbcity.captain_team.adapter.DailyTurnoverAdapter;
import com.xcy.fzbcity.captain_team.view.Captain_Team_CommissionTheProjectEndActivity;
import com.xcy.fzbcity.captain_team.view.Captain_Team_MyClientActivity;

import org.greenrobot.eventbus.EventBus;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class TeamFragment extends Fragment implements View.OnClickListener, MyViewPager.OnSingleTouchListener, SwipeRefreshLayout.OnRefreshListener{

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

    LinearLayout team_ll13_1;
    LinearLayout team_ll14_1;

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
    TextView market_time_tv14;

    TextView team_tv12_1;

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
    RelativeLayout market_time_rl3;

    RecyclerView market_time_rv;

    private Intent intent;

    String type1 = "";
    String startDate1 = "";
    String endDate1 = "";

    String type2 = "";
    String startDate2 = "";
    String endDate2 = "";

    String state = "1";

    String type3 = "0";
    String startDate3 = "";
    String endDate3 = "";

    ImageView all_no_information_S_S_S;

    LinearLayout fragment_lls_1;
    LinearLayout fragment_lls_2;
    LinearLayout fragment_lls_3;

    private SwipeRefreshLayout team_srl;
    private MyViewPager vpager_one;
    private ArrayList<Fragment> aList = new ArrayList<>();
    private MyFragmentPagerAdapter mAdapter;
    private PtrClassicFrameLayout ptrClassicFrameLayout;
    private MyTeam2Bean.DataBean data;
    private ScrollView team_scroll;
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

    public TeamFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_team, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
    }

    //  TODO 声明
    private void initView() {

        StatusBar.makeStatusBarTransparent(getActivity());

        project_attache_ll1 = getActivity().findViewById(R.id.project_attache_ll1);
        project_attache_ll2 = getActivity().findViewById(R.id.project_attache_ll2);
        project_attache_ll3 = getActivity().findViewById(R.id.project_attache_ll3);
        project_attache_ll4 = getActivity().findViewById(R.id.project_attache_ll4);

        project_attache_ll1.setOnClickListener(this);
        project_attache_ll3.setOnClickListener(this);

        team_scroll = getActivity().findViewById(R.id.team_scroll);
        team_scroll.smoothScrollTo(0,20);
        team_srl = getActivity().findViewById(R.id.team_srl);
        ptrClassicFrameLayout = getActivity().findViewById(R.id.PtrClassic_modulebroke);
        fragment_lls_3 = getActivity().findViewById(R.id.fragment_lls_3);
        fragment_lls_2 = getActivity().findViewById(R.id.fragment_lls_2);
        fragment_lls_1 = getActivity().findViewById(R.id.fragment_lls_1);
        vpager_one = getActivity().findViewById(R.id.vpager_one_s1);
        vpager_one.setOnSingleTouchListener(this);
        if (FinalContents.getFragmentSS().equals("0")) {
            mAdapter = new MyFragmentPagerAdapter(getActivity().getSupportFragmentManager());
            aList.add(new MyFragment4());
            aList.add(new MyFragment5());
            aList.add(new MyFragment6());

            mAdapter.setListfragment(aList);
            vpager_one.setAdapter(mAdapter);
            vpager_one.setCurrentItem(0);
            FinalContents.setFragmentSS("1");
        }

        team_srl.setOnRefreshListener(this);

        vpager_one.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }
            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    fragment_lls_1.setBackgroundResource(R.drawable.checkbox_underline_shape_s);
                    fragment_lls_2.setBackgroundResource(R.drawable.checkbox_underline_shape_s_s);
                    fragment_lls_3.setBackgroundResource(R.drawable.checkbox_underline_shape_s_s);

                    EventBus.getDefault().post(new Fragnemt_SS("", "", data.getLeaderNum() + "",data.getSalesNum() + "",""));
                } else if (position == 1) {
                    fragment_lls_2.setBackgroundResource(R.drawable.checkbox_underline_shape_s);
                    fragment_lls_1.setBackgroundResource(R.drawable.checkbox_underline_shape_s_s);
                    fragment_lls_3.setBackgroundResource(R.drawable.checkbox_underline_shape_s_s);

                    EventBus.getDefault().post(new Fragnemt_SS("", "", "",data.getSalesNum() + "",data.getCounselorNum() + ""));
                } else if (position == 2) {
                    fragment_lls_3.setBackgroundResource(R.drawable.checkbox_underline_shape_s);
                    fragment_lls_2.setBackgroundResource(R.drawable.checkbox_underline_shape_s_s);
                    fragment_lls_1.setBackgroundResource(R.drawable.checkbox_underline_shape_s_s);

                    EventBus.getDefault().post(new Fragnemt_SS("", "", "",data.getSalesNum() + "",data.getCounselorNum() + ""));
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        all_no_information_S_S_S = getActivity().findViewById(R.id.all_no_information_S_S_S);
        market_time_ll1 = getActivity().findViewById(R.id.team_ll1);
        market_time_ll2 = getActivity().findViewById(R.id.team_ll2);
        market_time_ll3 = getActivity().findViewById(R.id.team_ll3);
        market_time_ll4 = getActivity().findViewById(R.id.team_ll4);
        market_time_ll5 = getActivity().findViewById(R.id.team_ll5);
        market_time_ll6 = getActivity().findViewById(R.id.team_ll6);
        market_time_ll7 = getActivity().findViewById(R.id.team_ll7);
        market_time_ll8 = getActivity().findViewById(R.id.team_ll8);
        market_time_ll9 = getActivity().findViewById(R.id.team_ll9);
        market_time_ll10 = getActivity().findViewById(R.id.team_ll10);
        market_time_ll11 = getActivity().findViewById(R.id.team_ll11);
        market_time_ll12 = getActivity().findViewById(R.id.team_ll12);
        market_time_ll13 = getActivity().findViewById(R.id.team_ll13);
        market_time_ll14 = getActivity().findViewById(R.id.team_ll14);
        market_time_ll15 = getActivity().findViewById(R.id.team_ll15);
        market_time_ll16 = getActivity().findViewById(R.id.team_ll16);

        team_ll13_1 = getActivity().findViewById(R.id.team_ll13_1);
        team_ll14_1 = getActivity().findViewById(R.id.team_ll14_1);

//        market_time_tv1 = getActivity().findViewById(R.id.team_tv1);
        market_time_tv2 = getActivity().findViewById(R.id.team_tv2);
        market_time_tv3 = getActivity().findViewById(R.id.team_tv3);
        market_time_tv4 = getActivity().findViewById(R.id.team_tv4);
        market_time_tv5 = getActivity().findViewById(R.id.team_tv5);
        market_time_tv6 = getActivity().findViewById(R.id.team_tv6);
        market_time_tv7 = getActivity().findViewById(R.id.team_tv7);
        market_time_tv8 = getActivity().findViewById(R.id.team_tv8);
        market_time_tv9 = getActivity().findViewById(R.id.team_tv9);
        market_time_tv10 = getActivity().findViewById(R.id.team_tv10);
//        market_time_tv11 = getActivity().findViewById(R.id.team_tv11);
        market_time_tv12 = getActivity().findViewById(R.id.team_tv12);
        market_time_tv13 = getActivity().findViewById(R.id.team_tv13);
//        market_time_tv14 = getActivity().findViewById(R.id.team_tv14);

        team_tv12_1 = getActivity().findViewById(R.id.team_tv12_1);

        market_time_time_tv1 = getActivity().findViewById(R.id.team_time_tv1);
        market_time_time_tv2 = getActivity().findViewById(R.id.team_time_tv2);
        market_time_time_tv3 = getActivity().findViewById(R.id.team_time_tv3);
        market_time_time_tv4 = getActivity().findViewById(R.id.team_time_tv4);
        market_time_time_tv5 = getActivity().findViewById(R.id.team_time_tv5);
        market_time_time_tv6 = getActivity().findViewById(R.id.team_time_tv6);

        market_time_rg1 = getActivity().findViewById(R.id.team_rg1);
        market_time_rg2 = getActivity().findViewById(R.id.team_rg2);
        market_time_rg3 = getActivity().findViewById(R.id.team_rg3);

        market_time_rb1 = getActivity().findViewById(R.id.team_rb1);
        market_time_rb2 = getActivity().findViewById(R.id.team_rb2);
        market_time_rb3 = getActivity().findViewById(R.id.team_rb3);
        market_time_rb4 = getActivity().findViewById(R.id.team_rb4);
        market_time_rb5 = getActivity().findViewById(R.id.team_rb5);
        market_time_rb6 = getActivity().findViewById(R.id.team_rb6);
        market_time_rb7 = getActivity().findViewById(R.id.team_rb7);
        market_time_rb8 = getActivity().findViewById(R.id.team_rb8);
        market_time_rb9 = getActivity().findViewById(R.id.team_rb9);
        market_time_rb10 = getActivity().findViewById(R.id.team_rb10);
        market_time_rb11 = getActivity().findViewById(R.id.team_rb11);
        market_time_rb12 = getActivity().findViewById(R.id.team_rb12);

//        market_time_rl1 = getActivity().findViewById(R.id.team_rl1);
//        market_time_rl2 = getActivity().findViewById(R.id.team_rl2);
//        market_time_rl3 = getActivity().findViewById(R.id.team_rl3);

        market_time_rv = getActivity().findViewById(R.id.team_rv);
        market_time_rv.setFocusable(false);


        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        string = String.format(Locale.getDefault(), "%d.%02d.%02d", year, month +1, dayOfMonth);
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

        team_ll13_1.setOnClickListener(this);

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
//        market_time_rl3.setOnClickListener(this);

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
////            TODO 团队长（上）
//            case R.id.team_rl3:
//                intent = new Intent(getContext(), Assistant_Teams_Activity.class);
//                intent.putExtra("Iftz","1");
//                startActivity(intent);
//                break;
////            TODO 销售（上）
//            case R.id.team_rl1:
//                intent = new Intent(getContext(), Assistant_Teams_Activity.class);
//                intent.putExtra("Iftz","2");
//                startActivity(intent);
//                break;
////            TODO 顾问（上）
//            case R.id.team_rl2:
//                intent = new Intent(getContext(),Assistant_Teams_Activity.class);
//                intent.putExtra("Iftz","3");
//                startActivity(intent);
//                break;
//            TODO 报备
            case R.id.team_ll1:
                intent = new Intent(getContext(), Captain_Team_MyClientActivity.class);
                FinalContents.setQuanceng("1");
                FinalContents.setMySelf("0");
                intent.putExtra("client", "1");
                startActivity(intent);
                break;
//            TODO 到访
            case R.id.team_ll2:
                intent = new Intent(getContext(), Captain_Team_MyClientActivity.class);
                FinalContents.setQuanceng("1");
                FinalContents.setMySelf("0");
                intent.putExtra("client", "2");
                startActivity(intent);
                break;
//            TODO 登岛
            case R.id.team_ll3:
                intent = new Intent(getContext(), Captain_Team_MyClientActivity.class);
                FinalContents.setQuanceng("1");
                FinalContents.setMySelf("0");
                intent.putExtra("client", "3");
                startActivity(intent);
                break;
//            TODO 认筹
            case R.id.team_ll4:
                intent = new Intent(getContext(), Captain_Team_MyClientActivity.class);
                FinalContents.setQuanceng("1");
                FinalContents.setMySelf("0");
                intent.putExtra("client", "4");
                startActivity(intent);
                break;
//            TODO 成交
            case R.id.team_ll5:
                intent = new Intent(getContext(), Captain_Team_MyClientActivity.class);
                FinalContents.setQuanceng("1");
                FinalContents.setMySelf("0");
                intent.putExtra("client", "5");
                startActivity(intent);
                break;
//            TODO 失效
            case R.id.team_ll6:
                intent = new Intent(getContext(), Captain_Team_MyClientActivity.class);
                FinalContents.setQuanceng("1");
                FinalContents.setMySelf("0");
                intent.putExtra("client", "6");
                startActivity(intent);
                break;
//            TODO 总佣金
            case R.id.team_ll7:
                intent = new Intent(getContext(), Captain_Team_CommissionTheProjectEndActivity.class);
                startActivity(intent);
                break;
//            TODO 已结
            case R.id.team_ll8:
                intent = new Intent(getContext(), Captain_Team_CommissionTheProjectEndActivity.class);
                startActivity(intent);
                break;
//            TODO 未结
            case R.id.team_ll9:
                intent = new Intent(getContext(), Captain_Team_CommissionTheProjectEndActivity.class);
                startActivity(intent);
                break;
//            TODO 团队长（下）
            case R.id.team_ll13_1:
                team_ll14_1.setVisibility(View.VISIBLE);
                market_time_ll14.setVisibility(View.GONE);
                market_time_ll16.setVisibility(View.GONE);
                team_tv12_1.setTextColor(Color.parseColor("#334485"));
                market_time_tv12.setTextColor(Color.parseColor("#333333"));
                market_time_tv13.setTextColor(Color.parseColor("#333333"));
                state = "1";
                initDailyTurnover();
                break;

//            TODO 销售（下）
            case R.id.team_ll13:
                team_ll14_1.setVisibility(View.GONE);
                market_time_ll14.setVisibility(View.VISIBLE);
                market_time_ll16.setVisibility(View.GONE);
                team_tv12_1.setTextColor(Color.parseColor("#333333"));
                market_time_tv12.setTextColor(Color.parseColor("#334485"));
                market_time_tv13.setTextColor(Color.parseColor("#333333"));
                state = "2";
                initDailyTurnover();
                break;
//            TODO 顾问（下）
            case R.id.team_ll15:
                team_ll14_1.setVisibility(View.GONE);
                market_time_ll14.setVisibility(View.GONE);
                market_time_ll16.setVisibility(View.VISIBLE);
                team_tv12_1.setTextColor(Color.parseColor("#333333"));
                market_time_tv13.setTextColor(Color.parseColor("#334485"));
                market_time_tv12.setTextColor(Color.parseColor("#333333"));
                state = "3";
                initDailyTurnover();
                break;
//            TODO 时间选择1
            case R.id.team_time_tv1:
                initTime1_Date1();
                break;
//            TODO 时间选择2
            case R.id.team_time_tv2:
                initTime1_Date2();
                break;
//            TODO 时间选择3
            case R.id.team_time_tv3:
                initTime2_Date1();
                break;
//            TODO 时间选择4
            case R.id.team_time_tv4:
                initTime2_Date2();
                break;
//            TODO 时间选择5
            case R.id.team_time_tv5:
                initTime3_Date1();
                break;
//            TODO 时间选择6
            case R.id.team_time_tv6:
                initTime3_Date2();
                break;
            //            TODO 数据统计 时间选择 全部
            case R.id.team_rb1:
                market_time_ll10.setVisibility(View.GONE);
                type1 = "0";
                startDate1 = "";
                endDate1 = "";
                NewlyIncreased.setTag("0");
                initDataStatistics();
                break;

            //            TODO 数据统计 时间选择 昨天
            case R.id.team_rb2:
                market_time_ll10.setVisibility(View.GONE);
                type1 = "1";
                startDate1 = "";
                endDate1 = "";
                NewlyIncreased.setTag("1");
                initDataStatistics();
                break;

            //            TODO 数据统计 时间选择 七天
            case R.id.team_rb3:
                market_time_ll10.setVisibility(View.GONE);
                type1 = "2";
                startDate1 = "";
                endDate1 = "";
                NewlyIncreased.setTag("2");
                initDataStatistics();
                break;

            //            TODO 数据统计 时间选择 自定义
            case R.id.team_rb4:
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
            case R.id.team_rb5:
                market_time_ll11.setVisibility(View.GONE);
                type2 = "0";
                startDate2 = "";
                endDate2 = "";
                NewlyIncreased.setYJType("0");
                initTeamCommissions();
                break;

            //            TODO 财务数据 时间选择 昨天
            case R.id.team_rb6:
                market_time_ll11.setVisibility(View.GONE);
                type2 = "1";
                startDate2 = "";
                endDate2 = "";
                NewlyIncreased.setYJType("1");
                initTeamCommissions();
                break;

            //            TODO 财务数据 时间选择 七天
            case R.id.team_rb7:
                market_time_ll11.setVisibility(View.GONE);
                type2 = "2";
                startDate2 = "";
                endDate2 = "";
                NewlyIncreased.setYJType("2");
                initTeamCommissions();
                break;

            //            TODO 财务数据 时间选择 自定义
            case R.id.team_rb8:
                market_time_ll11.setVisibility(View.VISIBLE);
                type2 = "3";
                startDate2 = string;
                endDate2 = string;
                market_time_time_tv3.setText(string);
                market_time_time_tv4.setText(string);
                NewlyIncreased.setYJType("3");
                initTeamCommissions();
                break;

            //            TODO 成交TOP5 时间选择 全部
            case R.id.team_rb9:
                market_time_ll12.setVisibility(View.GONE);
                type3 = "0";
                startDate3 = "";
                endDate3 = "";
                initDailyTurnover();
                break;

            //            TODO 成交TOP5 时间选择 昨天
            case R.id.team_rb10:
                market_time_ll12.setVisibility(View.GONE);
                type3 = "1";
                startDate3 = "";
                endDate3 = "";
                initDailyTurnover();
                break;

            //            TODO 成交TOP5 时间选择 七天
            case R.id.team_rb11:
                market_time_ll12.setVisibility(View.GONE);
                type3 = "2";
                startDate3 = "";
                endDate3 = "";
                initDailyTurnover();
                break;

            //            TODO 成交TOP5 时间选择 自定义
            case R.id.team_rb12:
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

    //            TODO 数据统计 时间选择 开始时间
    private void initTime1_Date1(){
        Calendar selectedDate = Calendar.getInstance();//系统当前时间
        Calendar startDate = Calendar.getInstance();
        startDate.set(year, month, dayOfMonth-15);
        final Calendar endDate = Calendar.getInstance();
        endDate.set(year, month, dayOfMonth+15);
        TimePickerView pvTime = new TimePickerBuilder(getContext(), new OnTimeSelectListener() {
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

    //            TODO 数据统计 时间选择 结束时间
    private void initTime1_Date2(){
        Calendar selectedDate = Calendar.getInstance();//系统当前时间
        Calendar startDate = Calendar.getInstance();
        startDate.set(year, month, dayOfMonth-15);
        final Calendar endDate = Calendar.getInstance();
        endDate.set(year, month, dayOfMonth+15);
        TimePickerView pvTime = new TimePickerBuilder(getContext(), new OnTimeSelectListener() {
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

    //            TODO 财务数据 时间选择 开始时间
    private void initTime2_Date1(){
        Calendar selectedDate = Calendar.getInstance();//系统当前时间
        Calendar startDate = Calendar.getInstance();
        startDate.set(year, month, dayOfMonth-15);
        final Calendar endDate = Calendar.getInstance();
        endDate.set(year, month, dayOfMonth+15);
        TimePickerView pvTime = new TimePickerBuilder(getContext(), new OnTimeSelectListener() {
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

    //            TODO 财务数据 时间选择 开始时间
    private void initTime2_Date2(){
        Calendar selectedDate = Calendar.getInstance();//系统当前时间
        Calendar startDate = Calendar.getInstance();
        startDate.set(year, month, dayOfMonth-15);
        final Calendar endDate = Calendar.getInstance();
        endDate.set(year, month, dayOfMonth+15);
        TimePickerView pvTime = new TimePickerBuilder(getContext(), new OnTimeSelectListener() {
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

    //            TODO 成交TOP5单 时间选择 开始时间
    private void initTime3_Date1(){
        Calendar selectedDate = Calendar.getInstance();//系统当前时间
        Calendar startDate = Calendar.getInstance();
        startDate.set(year, month, dayOfMonth-15);
        final Calendar endDate = Calendar.getInstance();
        endDate.set(year, month, dayOfMonth+15);
        TimePickerView pvTime = new TimePickerBuilder(getContext(), new OnTimeSelectListener() {
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

    //            TODO 成交TOP5单 时间选择 开始时间
    private void initTime3_Date2(){
        Calendar selectedDate = Calendar.getInstance();//系统当前时间
        Calendar startDate = Calendar.getInstance();
        startDate.set(year, month, dayOfMonth-15);
        final Calendar endDate = Calendar.getInstance();
        endDate.set(year, month, dayOfMonth+15);
        TimePickerView pvTime = new TimePickerBuilder(getContext(), new OnTimeSelectListener() {
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

                    @SuppressLint("WrongConstant")
                    @Override
                    public void onNext(DailyTurnoverBean dailyTurnoverBean) {
                        if (dailyTurnoverBean.getData().size() == 0) {
                            all_no_information_S_S_S.setVisibility(View.VISIBLE);
                        } else {
                            all_no_information_S_S_S.setVisibility(View.GONE);
                            MyLinearLayoutManager layoutManager = new MyLinearLayoutManager(getContext());
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
        Log.i("加载次数", "加载:");

        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<MyTeam2Bean> clientCommissions = fzbInterface.getMyTeam2(FinalContents.getUserID());
        clientCommissions.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MyTeam2Bean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @SuppressLint("WrongConstant")
                    @Override
                    public void onNext(MyTeam2Bean myTeamBean) {
                        data = myTeamBean.getData();
//                        market_time_tv14.setText(myTeamBean.getData().getLeaderNum()+"");
//
//                        market_time_tv11.setText(myTeamBean.getData().getSalesNum()+"");
//                        market_time_tv1.setText(myTeamBean.getData().getCounselorNum()+"");
                        Log.i("广播", "myTeamBean.getData().getLeaderNum()：" + myTeamBean.getData().getLeaderNum());
                        Log.i("广播", "myTeamBean.getData().getData()：" + myTeamBean.getData().getSalesNum());
                        Log.i("广播", "myTeamBean.getData().getCounselorNum()：" + myTeamBean.getData().getCounselorNum());
                        EventBus.getDefault().post(new Fragnemt_SS("", "", myTeamBean.getData().getLeaderNum() + "", myTeamBean.getData().getSalesNum() + "", myTeamBean.getData().getCounselorNum() + ""));

                        NewlyIncreased.setLeaderNum(myTeamBean.getData().getLeaderNum());
                        NewlyIncreased.setSalesNum(myTeamBean.getData().getSalesNum());
                        NewlyIncreased.setCounselorNum(myTeamBean.getData().getCounselorNum());

                        market_time_tv2.setText(myTeamBean.getData().getDataStatistics().getReportNumber() + "");
                        market_time_tv3.setText(myTeamBean.getData().getDataStatistics().getAccessingNumber() + "");
                        market_time_tv4.setText(myTeamBean.getData().getDataStatistics().getIsIslandNumber() + "");
                        market_time_tv5.setText(myTeamBean.getData().getDataStatistics().getEarnestMoneyNumber() + "");
                        market_time_tv6.setText(myTeamBean.getData().getDataStatistics().getTradeNumber() + "");
                        market_time_tv7.setText(myTeamBean.getData().getDataStatistics().getInvalidNum() + "");

                        market_time_tv8.setText(myTeamBean.getData().getMoneyData().getTotalAmount() + "");
                        market_time_tv9.setText(myTeamBean.getData().getMoneyData().getAlreadyAmount() + "");
                        market_time_tv10.setText(myTeamBean.getData().getMoneyData().getNotAmount() + "");

                        FinalContents.setAgentId(FinalContents.getUserID());

//                        MyLinearLayoutManager layoutManager = new MyLinearLayoutManager(getContext());
//                        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//                        layoutManager.setScrollEnabled(false);
//                        market_time_rv.setLayoutManager(layoutManager);
//                        MyTeam2Adapter adapter = new MyTeam2Adapter(myTeamBean.getData().getDailyTurnover());
//                        market_time_rv.setNestedScrollingEnabled(false);
//                        market_time_rv.setAdapter(adapter);
//                        adapter.notifyDataSetChanged();
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
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden) {
            //TODO now visible to user 不显示fragment
        } else {
            onResume();
            //TODO now invisible to user 显示fragment
        }
    }

    @Override
    public void onSingleTouch() {

        int currentItem = vpager_one.getCurrentItem();
        if (currentItem == 0) {
            intent = new Intent(getContext(), Assistant_Teams_Activity.class);
            intent.putExtra("Iftz", "1");
            startActivity(intent);
        } else if (currentItem == 1) {
            intent = new Intent(getContext(), Assistant_Teams_Activity.class);
            intent.putExtra("Iftz", "2");
            startActivity(intent);
        } else if (currentItem == 2) {
            intent = new Intent(getContext(), Assistant_Teams_Activity.class);
            intent.putExtra("Iftz", "3");
            startActivity(intent);
        }
    }

    @Override
    public void onRefresh() {
        if (team_srl.isRefreshing()) {//如果正在刷新
            initView();
            initView();
            team_srl.setRefreshing(false);//取消刷新
        }
    }

    public String getTime2(Date date) {//可根据需要自行截取数据显示
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd");
        return format.format(date);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        FinalContents.setFragmentSS("0");
    }
}

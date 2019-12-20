package com.xcy.fzbcity.captain_team.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.api.FinalContents;
import com.xcy.fzbcity.all.api.NewlyIncreased;
import com.xcy.fzbcity.all.database.CommissionListBean;
import com.xcy.fzbcity.all.database.TeamCommissionsBean;
import com.xcy.fzbcity.all.persente.MyLinearLayoutManager;
import com.xcy.fzbcity.all.persente.SingleClick;
import com.xcy.fzbcity.all.persente.StatusBar;
import com.xcy.fzbcity.all.service.MyService;
import com.xcy.fzbcity.all.utils.CommonUtil;
import com.xcy.fzbcity.all.utils.KeyUtils;
import com.xcy.fzbcity.all.utils.ToastUtil;
import com.xcy.fzbcity.all.view.AllActivity;
import com.xcy.fzbcity.captain_team.adapter.Captain_Team_PopAdapter;
import com.xcy.fzbcity.captain_team.adapter.Captain_Team_TheProjectEndCommissionAdapter;

import java.util.ArrayList;
import java.util.List;

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

public class Captain_Team_CommissionTheProjectEndActivity extends AllActivity implements View.OnClickListener {


    RelativeLayout commission_the_project_end_return;
    ImageView commission_the_project_end_screen;
    EditText commission_the_project_end_et;

    TextView commission_the_project_end_tv2;
    TextView commission_the_project_end_tv3;
    TextView commission_the_project_end_tv4;
    TextView commission_the_project_end_all;

    LinearLayout commission_the_project_end_l1;
    LinearLayout commission_the_project_end_l2;
    LinearLayout commission_the_project_end_l3;
    LinearLayout commission_the_project_end_l4;
    LinearLayout commission_the_project_end_l5;
    LinearLayout commission_the_project_end_l6;

    ImageView all_no_information;
    RecyclerView commission_the_project_end_rv;


    String search = "";
    String status = "";
    String projectType = "3";
    String projectId = "";
    String startTime = "";
    String endTime = "";
    private PopupWindow p;
    private LinearLayout commission_the_project_end_linear;
    private PtrClassicFrameLayout commission_the_project_end_ptrclass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.captain_team_activity_commission_the_project_end);
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
        commission_the_project_end_return = findViewById(R.id.commission_the_project_end_return);
        commission_the_project_end_screen = findViewById(R.id.commission_the_project_end_screen);
        commission_the_project_end_et = findViewById(R.id.commission_the_project_end_et);
        commission_the_project_end_tv2 = findViewById(R.id.commission_the_project_end_tv2);
        commission_the_project_end_tv3 = findViewById(R.id.commission_the_project_end_tv3);
        commission_the_project_end_tv4 = findViewById(R.id.commission_the_project_end_tv4);
        commission_the_project_end_l1 = findViewById(R.id.commission_the_project_end_l1);
        commission_the_project_end_l2 = findViewById(R.id.commission_the_project_end_l2);
        commission_the_project_end_l3 = findViewById(R.id.commission_the_project_end_l3);
        commission_the_project_end_l4 = findViewById(R.id.commission_the_project_end_l4);
        commission_the_project_end_l5 = findViewById(R.id.commission_the_project_end_l5);
        commission_the_project_end_l6 = findViewById(R.id.commission_the_project_end_l6);
        all_no_information = findViewById(R.id.all_no_information);
        commission_the_project_end_rv = findViewById(R.id.commission_the_project_end_rv);
        commission_the_project_end_all = findViewById(R.id.commission_the_project_end_all);
        commission_the_project_end_linear = findViewById(R.id.commission_the_project_end_linear);
        commission_the_project_end_ptrclass = findViewById(R.id.commission_the_project_end_ptrclass);

        if (FinalContents.getIdentity().equals("60")) {
            commission_the_project_end_linear.setVisibility(View.VISIBLE);
        } else if (FinalContents.getIdentity().equals("61")) {
            commission_the_project_end_linear.setVisibility(View.GONE);
        }

        commission_the_project_end_ptrclass.setPtrHandler(new PtrHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                frame.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        commission_the_project_end_ptrclass.refreshComplete();
                        commission_the_project_end_ptrclass.setLastUpdateTimeKey("2017-2-10");
                        commission_the_project_end_et.setText("");
                        search = commission_the_project_end_et.getText().toString();
                        initData();
                        initDataBean();
                    }
                }, 1000);
            }

            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
            }
        });

        initData();
        initDataBean();

        if(FinalContents.getManageFlag().equals("0") || FinalContents.getIdentity().equals("63")){
            commission_the_project_end_screen.setVisibility(View.GONE);
        }else {
            commission_the_project_end_screen.setVisibility(View.VISIBLE);
        }


        commission_the_project_end_return.setOnClickListener(this);
        commission_the_project_end_screen.setOnClickListener(this);
        commission_the_project_end_l1.setOnClickListener(this);
        commission_the_project_end_l3.setOnClickListener(this);
        commission_the_project_end_l5.setOnClickListener(this);
        commission_the_project_end_all.setOnClickListener(this);

        commission_the_project_end_et.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                //如果actionId是搜索的id，则进行下一步的操作
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    // 当按了搜索之后关闭软键盘
                    KeyUtils.hideKeyboard(commission_the_project_end_et);
                    search = commission_the_project_end_et.getText().toString();
                    initDataBean();
                }
                return false;
            }
        });
    }

    @SingleClick(1000)
    @Override
    public void onClick(View view) {

        switch (view.getId()) {
//            TODO 返回上一层
            case R.id.commission_the_project_end_return:
                finish();
                break;
//                TODO 佣金设置
            case R.id.commission_the_project_end_screen:
                Intent intent = new Intent(Captain_Team_CommissionTheProjectEndActivity.this, Captain_Team_CommissionLevelActivity.class);
                startActivity(intent);
                break;
//                TODO 旅居房产
            case R.id.commission_the_project_end_l1:

                commission_the_project_end_l2.setVisibility(View.VISIBLE);
                commission_the_project_end_l4.setVisibility(View.GONE);
                commission_the_project_end_l6.setVisibility(View.GONE);
                projectType = "3";
                initDataBean();

                break;
//                TODO 海外房产
            case R.id.commission_the_project_end_l3:

                commission_the_project_end_l2.setVisibility(View.GONE);
                commission_the_project_end_l4.setVisibility(View.VISIBLE);
                commission_the_project_end_l6.setVisibility(View.GONE);
                projectType = "2";
                initDataBean();
                break;
//                TODO 城市房产
            case R.id.commission_the_project_end_l5:

                commission_the_project_end_l2.setVisibility(View.GONE);
                commission_the_project_end_l4.setVisibility(View.GONE);
                commission_the_project_end_l6.setVisibility(View.VISIBLE);
                projectType = "1";
                initDataBean();
                break;
//                TODO 佣金状态
            case R.id.commission_the_project_end_all:
                PopWindow();
                break;
        }

    }

    private void PopWindow() {

        final List<String> list = new ArrayList<>();
        list.add("全部");
        list.add("只看未结清");
        list.add("只看已结清");
        if(FinalContents.getIdentity().equals("63")){

        }else {
            list.add("只看自己");
        }
        list.add("只看调单");
        list.add("只看退单");


        OptionsPickerView pvOptions = new OptionsPickerBuilder(Captain_Team_CommissionTheProjectEndActivity.this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                //              展示选中数据
                commission_the_project_end_all.setText(list.get(options1));
                if(FinalContents.getIdentity().equals("63")){
                    if (options1 == 0) {
                        status = "";
                    } else if (options1 == 1) {
                        status = "1";
                    } else if (options1 == 2) {
                        status = "2";
                    } else if (options1 == 3) {
                        status = "4";
                    } else if (options1 == 4) {
                        status = "5";
                    }
                }else {
                    if (options1 == 0) {
                        status = "";
                    } else if (options1 == 1) {
                        status = "1";
                    } else if (options1 == 2) {
                        status = "2";
                    } else if (options1 == 3) {
                        status = "3";
                    } else if (options1 == 4) {
                        status = "4";
                    } else if (options1 == 5) {
                        status = "5";
                    }
                }
                initDataBean();
            }
        })
                .setSelectOptions(0)
                .setOutSideCancelable(false)//点击背的地方不消失
                .build();//创建
        //      把数据绑定到控件上面
        pvOptions.setPicker(list);
        //      展示
        pvOptions.show();

//        View contentView = LayoutInflater.from(this).inflate(R.layout.captain_team_item_popup, null);
//        //处理popWindow 显示内容
//        handleListView(contentView);
//        //创建并显示popWindow
//        p = new PopupWindow(contentView, WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
//        p.setTouchable(true);
//        p.setFocusable(true);
//        p.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.color)));
//        p.getContentView().measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
//        int xOff;
//        int buttonWidth = commission_the_project_end_all.getWidth();
//        int popupwindowWidth = p.getContentView().getMeasuredWidth();
//        xOff = buttonWidth - popupwindowWidth;
//        p.showAsDropDown(commission_the_project_end_all, xOff, 0);
//
//        p.setOnDismissListener(new PopupWindow.OnDismissListener() {
//            @Override
//            public void onDismiss() {
//
//            }
//        });
    }

    // TODO 填写数据
    private void handleListView(View contentView) {

        RecyclerView recyclerView = contentView.findViewById(R.id.rv_user);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        final List<String> list = new ArrayList<>();
        list.add("全部");
        list.add("只看未结清");
        list.add("只看已结清");
        if(FinalContents.getIdentity().equals("63")){

        }else {
            list.add("只看自己");
        }
        list.add("只看调单");
        list.add("只看退单");

        Captain_Team_PopAdapter popAdapter = new Captain_Team_PopAdapter(list);
        recyclerView.setAdapter(popAdapter);
        popAdapter.setOnItemClickListener(new Captain_Team_PopAdapter.OnItemClickLisenter() {
            @Override
            public void onItemClick(int postion) {

                if(FinalContents.getIdentity().equals("63")){
                    if (postion == 0) {
                        status = "";
                    } else if (postion == 1) {
                        status = "1";
                    } else if (postion == 2) {
                        status = "2";
                    } else if (postion == 3) {
                        status = "4";
                    } else if (postion == 4) {
                        status = "5";
                    }
                }else {
                    if (postion == 0) {
                        status = "";
                    } else if (postion == 1) {
                        status = "1";
                    } else if (postion == 2) {
                        status = "2";
                    } else if (postion == 3) {
                        status = "3";
                    } else if (postion == 4) {
                        status = "4";
                    } else if (postion == 5) {
                        status = "5";
                    }
                }


                commission_the_project_end_all.setText(list.get(postion));
                initDataBean();
                p.dismiss();
            }
        });

    }

    //    TODO 我的佣金上半部分数据填充
    private void initData() {
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<TeamCommissionsBean> userMessage = fzbInterface.getTeamCommissions(FinalContents.getUserID(), FinalContents.getUserID(), NewlyIncreased.getYJType(),NewlyIncreased.getYJstartDate(),NewlyIncreased.getYJendDate());
        userMessage.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<TeamCommissionsBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @SuppressLint("WrongConstant")
                    @Override
                    public void onNext(TeamCommissionsBean teamCommissionsBean) {
                        commission_the_project_end_tv2.setText(teamCommissionsBean.getData().getTotalAmount());
                        commission_the_project_end_tv3.setText(teamCommissionsBean.getData().getAlreadyAmount());
                        commission_the_project_end_tv4.setText(teamCommissionsBean.getData().getNotAmount());
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

    //TODO 我的佣金下半部分数据填充
    private void initDataBean() {
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<CommissionListBean> userMessage = fzbInterface.getCommissionList(FinalContents.getUserID(), projectType, search, status, "1000", NewlyIncreased.getYJType(),NewlyIncreased.getYJstartDate(),NewlyIncreased.getYJendDate());
        userMessage.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CommissionListBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @SuppressLint("WrongConstant")
                    @Override
                    public void onNext(CommissionListBean commissionListBean) {
                        if (commissionListBean.getCode().equals("1")) {
                            if (commissionListBean.getData() != null) {
                                CommissionListBean.DataBean commissionListBeanData = commissionListBean.getData();
                                List<CommissionListBean.DataBean.RowsBean> rowsBeanList = commissionListBeanData.getRows();
                                if (rowsBeanList.size() != 0) {
                                    commission_the_project_end_rv.setVisibility(View.VISIBLE);
                                    all_no_information.setVisibility(View.GONE);
                                    //在此处修改布局排列方向
                                    commission_the_project_end_rv.setVisibility(View.VISIBLE);
                                    MyLinearLayoutManager layoutManager = new MyLinearLayoutManager(Captain_Team_CommissionTheProjectEndActivity.this);
                                    layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                                    commission_the_project_end_rv.setLayoutManager(layoutManager);
                                    Captain_Team_TheProjectEndCommissionAdapter recyclerAdapter = new Captain_Team_TheProjectEndCommissionAdapter(rowsBeanList);
                                    commission_the_project_end_rv.setAdapter(recyclerAdapter);
                                    recyclerAdapter.notifyDataSetChanged();
                                }else {
                                    all_no_information.setVisibility(View.VISIBLE);
                                    commission_the_project_end_rv.setVisibility(View.GONE);
                                }

                            } else {
                                all_no_information.setVisibility(View.VISIBLE);
                                commission_the_project_end_rv.setVisibility(View.GONE);
                            }
                            hideInput();
                        } else {
                            all_no_information.setVisibility(View.VISIBLE);
                            commission_the_project_end_rv.setVisibility(View.GONE);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        all_no_information.setVisibility(View.VISIBLE);
                        commission_the_project_end_rv.setVisibility(View.GONE);
                        Log.i("列表数据获取错误", "错误" + e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

}

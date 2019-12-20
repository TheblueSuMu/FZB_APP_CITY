package com.xcy.fzbcity.captain_assistant.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
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
import com.nanchen.wavesidebar.WaveSideBarView;
import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.api.FinalContents;
import com.xcy.fzbcity.all.database.TeamMemberBean;
import com.xcy.fzbcity.all.persente.ContactModel;
import com.xcy.fzbcity.all.persente.LetterComparator;
import com.xcy.fzbcity.all.persente.PinnedHeaderDecoration;
import com.xcy.fzbcity.all.persente.SingleClick;
import com.xcy.fzbcity.all.persente.StatusBar;
import com.xcy.fzbcity.all.service.MyService;
import com.xcy.fzbcity.all.utils.CommonUtil;
import com.xcy.fzbcity.all.utils.ToastUtil;
import com.xcy.fzbcity.all.view.AllActivity;
import com.xcy.fzbcity.captain_team.adapter.Captain_Team_PopAdapter;
import com.xcy.fzbcity.captain_team.adapter.Captain_Team_TeamMemberAdapter;
import com.xcy.fzbcity.captain_team.view.Captain_Team_AddAConsultantActivity;
import com.xcy.fzbcity.captain_team.view.Captain_Team_AddSalesActivity;
import com.xcy.fzbcity.captain_team.view.Captain_Team_BatchModifyingActivity;
import com.xcy.fzbcity.captain_team.view.Captain_Team_SalesDetailsDetailsActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Assistant_Teams_Activity extends AllActivity implements View.OnClickListener, Captain_Team_TeamMemberAdapter.ItemOnClick {

    RelativeLayout team_member_img1;
    ImageView team_member_img2;

    TextView team_member_tv;

    EditText team_member_et;

    LinearLayout team_member_ll1;
    LinearLayout team_member_ll2;
    LinearLayout team_member_ll3;
    LinearLayout team_member_ll4;
    LinearLayout team_member_ll5;
    LinearLayout team_member_ll6;

    RecyclerView team_member_rv;

    WaveSideBarView mWaveSideBarView;
    private List<ContactModel> mContactModels;
    private PinnedHeaderDecoration decoration;
    Captain_Team_TeamMemberAdapter mAdapter;
    private Intent intent;
    private List<TeamMemberBean.DataBean.RowsBean> rows;
    int flag = 0;
    private PopupWindow p;
    private String status = "";
    private String string1 = "添加团队长";
    private String string2 = "批量修改团队长级别";
    int isnum = 0;
    private String iftz;
    private ImageView all_no_information;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assistant__teams_);
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

        if (FinalContents.getIdentity().equals("62")) {
            team_member_img2.setVisibility(View.GONE);
        }
        all_no_information = findViewById(R.id.all_no_information);
        team_member_img1 = findViewById(R.id.assistant_team_img1);
        team_member_img2 = findViewById(R.id.assistant_team_img2);

        team_member_tv = findViewById(R.id.assistant_team_tv);

        team_member_et = findViewById(R.id.assistant_team_et);

        team_member_ll1 = findViewById(R.id.assistant_team_ll1);
        team_member_ll2 = findViewById(R.id.assistant_team_ll2);
        team_member_ll3 = findViewById(R.id.assistant_team_ll3);
        team_member_ll4 = findViewById(R.id.assistant_team_ll4);
        team_member_ll5 = findViewById(R.id.assistant_team_ll5);
        team_member_ll6 = findViewById(R.id.assistant_team_ll6);

        team_member_rv = findViewById(R.id.assistant_team_rv);

        mWaveSideBarView = findViewById(R.id.assistant_team_side_bar);

        team_member_img1.setOnClickListener(this);
        team_member_img2.setOnClickListener(this);
        team_member_tv.setOnClickListener(this);
        team_member_ll1.setOnClickListener(this);
        team_member_ll3.setOnClickListener(this);
        team_member_ll5.setOnClickListener(this);

        mContactModels = new ArrayList<>();
        decoration = new PinnedHeaderDecoration();
        iftz = getIntent().getStringExtra("Iftz");
        if (iftz.equals("1")) {
            initData("", "1", "");
            string1 = "添加团队长";
            string2 = "批量修改团队长级别";
        } else if (iftz.equals("2")) {
            team_member_ll2.setVisibility(View.VISIBLE);
            team_member_ll4.setVisibility(View.GONE);
            team_member_ll6.setVisibility(View.GONE);
            initData("", "2", "");
            string1 = "添加销售";
            string2 = "";
        } else if (iftz.equals("3")) {
            team_member_ll2.setVisibility(View.GONE);
            team_member_ll4.setVisibility(View.VISIBLE);
            team_member_ll6.setVisibility(View.GONE);
            initData("", "3", "");
            string1 = "添加顾问";
            string2 = "";

        }


        team_member_et.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (KeyEvent.KEYCODE_ENTER == i && KeyEvent.ACTION_DOWN == keyEvent.getAction()) {
                    //处理事件
                    String s = team_member_et.getText().toString();
                    if (flag == 0) {
                        if (team_member_ll2.getVisibility() == View.VISIBLE) {
                            String s2 = team_member_tv.getText().toString();
                            if (s2.equals("全部")) {
                                initData(s, "2", "");
                            } else if (s2.equals("不看禁用")) {
                                initData(s, "2", "1");
                            } else if (s2.equals("只看禁用")) {
                                initData(s, "2", "0");
                            }
                        } else if (team_member_ll4.getVisibility() == View.VISIBLE) {
                            String s2 = team_member_tv.getText().toString();
                            if (s2.equals("全部")) {
                                initData(s, "3", "");
                            } else if (s2.equals("不看禁用")) {
                                initData(s, "3", "1");
                            } else if (s2.equals("只看禁用")) {
                                initData(s, "3", "0");
                            }
                        } else if (team_member_ll6.getVisibility() == View.VISIBLE) {
                            String s2 = team_member_tv.getText().toString();
                            if (s2.equals("全部")) {
                                initData(s, "1", "");
                            } else if (s2.equals("不看禁用")) {
                                initData(s, "1", "1");
                            } else if (s2.equals("只看禁用")) {
                                initData(s, "1", "0");
                            }
                        }
                        flag = 1;
                    }
                    return true;
                }
                return false;
            }
        });

    }

    @SingleClick(1000)
    @Override
    public void onClick(View view) {

        switch (view.getId()) {
//            TODO 返回上一级
            case R.id.assistant_team_img1:
                finish();
                break;
//            TODO 菜单栏
            case R.id.assistant_team_img2:
                PopWindow1();
                break;
//            TODO 全部
            case R.id.assistant_team_tv:
                PopWindow();
                break;
//            TODO 团队长 销售
            case R.id.assistant_team_ll1:
                if (FinalContents.getIdentity().equals("60")) {
                }
                string1 = "添加销售";
                string2 = "";
                team_member_ll2.setVisibility(View.VISIBLE);
                team_member_ll4.setVisibility(View.GONE);
                team_member_ll6.setVisibility(View.GONE);
                FinalContents.setXiaoShou("是");
                String s = team_member_et.getText().toString();
                String s2 = team_member_tv.getText().toString();
                if (s2.equals("全部")) {
                    initData(s, "2", "");
                } else if (s2.equals("不看禁用")) {
                    initData(s, "2", "1");
                } else if (s2.equals("只看禁用")) {
                    initData(s, "2", "0");
                }
                break;
//            TODO 销售 顾问
            case R.id.assistant_team_ll3:
                if (FinalContents.getIdentity().equals("61")) {
                }
                string1 = "添加顾问";
                string2 = "";
                FinalContents.setXiaoShou("不");
                team_member_ll2.setVisibility(View.GONE);
                team_member_ll6.setVisibility(View.GONE);
                team_member_ll4.setVisibility(View.VISIBLE);
                String s1 = team_member_et.getText().toString();
                String s3 = team_member_tv.getText().toString();
                if (s3.equals("全部")) {
                    initData(s1, "3", "");
                } else if (s3.equals("不看禁用")) {
                    initData(s1, "3", "1");
                } else if (s3.equals("只看禁用")) {
                    initData(s1, "3", "0");
                }
                break;
            //            TODO 团队长 团助
            case R.id.assistant_team_ll5:
                if (FinalContents.getIdentity().equals("63")) {
                }
                string1 = "添加团队长";
                string2 = "批量修改团队长级别";
                team_member_ll6.setVisibility(View.VISIBLE);
                team_member_ll2.setVisibility(View.GONE);
                team_member_ll4.setVisibility(View.GONE);
                FinalContents.setXiaoShou("是");
                String s4 = team_member_et.getText().toString();
                String s5 = team_member_tv.getText().toString();
                if (s5.equals("全部")) {
                    initData(s4, "1", "");
                } else if (s5.equals("不看禁用")) {
                    initData(s4, "1", "1");
                } else if (s5.equals("只看禁用")) {
                    initData(s4, "1", "0");
                }
                break;

        }

    }

    //TODO 弹窗
    private void PopWindow() {


        final List<String> list1 = new ArrayList<>();
        list1.add("全部");
        list1.add("不看禁用");
        list1.add("只看禁用");
        OptionsPickerView pvOptions = new OptionsPickerBuilder(Assistant_Teams_Activity.this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                team_member_tv.setText(list1.get(options1));
                String s = team_member_et.getText().toString();
                if (options1 == 0) {
                    if (team_member_ll2.getVisibility() == View.VISIBLE) {
                        initData(s, "2", "");
                    } else if (team_member_ll4.getVisibility() == View.VISIBLE) {
                        initData(s, "3", "");
                    } else if (team_member_ll6.getVisibility() == View.VISIBLE) {
                        initData(s, "1", "");
                    }
                } else if (options1 == 1) {
                    if (team_member_ll2.getVisibility() == View.VISIBLE) {
                        initData(s, "2", "1");
                    } else if (team_member_ll4.getVisibility() == View.VISIBLE) {
                        initData(s, "3", "1");
                    } else if (team_member_ll6.getVisibility() == View.VISIBLE) {
                        initData(s, "1", "1");
                    }
                } else if (options1 == 2) {
                    if (team_member_ll2.getVisibility() == View.VISIBLE) {
                        initData(s, "2", "0");
                    } else if (team_member_ll4.getVisibility() == View.VISIBLE) {
                        initData(s, "3", "0");
                    } else if (team_member_ll6.getVisibility() == View.VISIBLE) {
                        initData(s, "1", "0");
                    }
                }
            }
        }).setSelectOptions(0)
                .setOutSideCancelable(false)//点击背的地方不消失
                .build();//创建
        //      把数据绑定到控件上面
        pvOptions.setPicker(list1);
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
//        int buttonWidth = team_member_tv.getWidth();
//        int popupwindowWidth = p.getContentView().getMeasuredWidth();
//        xOff = buttonWidth - popupwindowWidth;
//        p.showAsDropDown(team_member_tv, xOff, 0);
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
        list.add("不看禁用");
        list.add("只看禁用");

        Captain_Team_PopAdapter popAdapter = new Captain_Team_PopAdapter(list);
        recyclerView.setAdapter(popAdapter);

        popAdapter.setOnItemClickListener(new Captain_Team_PopAdapter.OnItemClickLisenter() {
            @Override
            public void onItemClick(int postion) {
                String s = team_member_et.getText().toString();
                if (postion == 0) {
                    if (team_member_ll2.getVisibility() == View.VISIBLE) {
                        initData(s, "2", "");
                    } else if (team_member_ll4.getVisibility() == View.VISIBLE) {
                        initData(s, "3", "");
                    } else if (team_member_ll6.getVisibility() == View.VISIBLE) {
                        initData(s, "1", "");
                    }
                } else if (postion == 1) {
                    if (team_member_ll2.getVisibility() == View.VISIBLE) {
                        initData(s, "2", "1");
                    } else if (team_member_ll4.getVisibility() == View.VISIBLE) {
                        initData(s, "3", "1");
                    } else if (team_member_ll6.getVisibility() == View.VISIBLE) {
                        initData(s, "1", "1");
                    }
                } else if (postion == 2) {
                    if (team_member_ll2.getVisibility() == View.VISIBLE) {
                        initData(s, "2", "0");
                    } else if (team_member_ll4.getVisibility() == View.VISIBLE) {
                        initData(s, "3", "0");
                    } else if (team_member_ll6.getVisibility() == View.VISIBLE) {
                        initData(s, "1", "0");
                    }
                }
                team_member_tv.setText(list.get(postion));
                p.dismiss();
            }
        });

    }

    //TODO 弹窗
    private void PopWindow1() {


        final List<String> list1 = new ArrayList<>();
        list1.add(string1);
        if (string2.equals("")) {

        } else {
            list1.add(string2);
        }
        OptionsPickerView pvOptions = new OptionsPickerBuilder(Assistant_Teams_Activity.this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                if (options1 == 0) {
                    if (string1.equals("添加顾问")) {
                        FinalContents.setXiuGai("添加顾问");
                        FinalContents.setOwnerId("");
                        Intent intent = new Intent(Assistant_Teams_Activity.this, Captain_Team_AddAConsultantActivity.class);
                        startActivity(intent);
                    } else if (string1.equals("添加销售")) {
                        FinalContents.setXiuGai("添加销售");
                        FinalContents.setOwnerId("");
                        Intent intent = new Intent(Assistant_Teams_Activity.this, Captain_Team_AddSalesActivity.class);
                        startActivity(intent);
                    } else if (string1.equals("添加团队长")) {
                        FinalContents.setXiuGai("添加团队长");
                        FinalContents.setOwnerId("");
                        Intent intent = new Intent(Assistant_Teams_Activity.this, Assistant_Addteam_Activity.class);
                        startActivity(intent);
                    }
                } else if (options1 == 1) {
                    if (string2.equals("批量修改销售级别")) {
                        FinalContents.setXiuGai("批量修改销售级别");
                    } else if (string2.equals("批量修改顾问级别")) {
                        FinalContents.setXiuGai("批量修改顾问级别");
                    } else if (string2.equals("批量修改团队长级别")) {
                        FinalContents.setXiuGai("批量修改团队长级别");
                    }
                    Intent intent = new Intent(Assistant_Teams_Activity.this, Captain_Team_BatchModifyingActivity.class);
                    startActivity(intent);
                }
            }
        }).setSelectOptions(0)
                .setOutSideCancelable(false)//点击背的地方不消失
                .build();//创建
        //      把数据绑定到控件上面
        pvOptions.setPicker(list1);
        //      展示
        pvOptions.show();


//        View contentView = LayoutInflater.from(this).inflate(R.layout.captain_team_item_popup, null);
//        //处理popWindow 显示内容
//        handleListView1(contentView);
//        //创建并显示popWindow
//        p = new PopupWindow(contentView, WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
//        p.setTouchable(true);
//        p.setFocusable(true);
//        p.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.color)));
//        p.getContentView().measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
//        int xOff;
//        int buttonWidth = team_member_img2.getWidth();
//        int popupwindowWidth = p.getContentView().getMeasuredWidth();
//        xOff = buttonWidth - popupwindowWidth;
//        p.showAsDropDown(team_member_img2, xOff, 0);
//
//        p.setOnDismissListener(new PopupWindow.OnDismissListener() {
//            @Override
//            public void onDismiss() {
//
//            }
//        });
    }

    // TODO 填写数据
    private void handleListView1(View contentView) {

        RecyclerView recyclerView = contentView.findViewById(R.id.rv_user);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        final List<String> list = new ArrayList<>();
        list.add(string1);
        if (string2.equals("")) {

        } else {
            list.add(string2);
        }

        Captain_Team_PopAdapter popAdapter = new Captain_Team_PopAdapter(list);
        recyclerView.setAdapter(popAdapter);

        popAdapter.setOnItemClickListener(new Captain_Team_PopAdapter.OnItemClickLisenter() {
            @Override
            public void onItemClick(int postion) {
                if (postion == 0) {
                    if (string1.equals("添加顾问")) {
                        FinalContents.setXiuGai("添加顾问");
                        FinalContents.setOwnerId("");
                        Intent intent = new Intent(Assistant_Teams_Activity.this, Captain_Team_AddAConsultantActivity.class);
                        startActivity(intent);
                    } else if (string1.equals("添加销售")) {
                        FinalContents.setXiuGai("添加销售");
                        FinalContents.setOwnerId("");
                        Intent intent = new Intent(Assistant_Teams_Activity.this, Captain_Team_AddSalesActivity.class);
                        startActivity(intent);
                    } else if (string1.equals("添加团队长")) {
                        FinalContents.setXiuGai("添加团队长");
                        FinalContents.setOwnerId("");
                        Intent intent = new Intent(Assistant_Teams_Activity.this, Assistant_Addteam_Activity.class);
                        startActivity(intent);
                    }
                } else if (postion == 1) {
                    if (string2.equals("批量修改销售级别")) {
                        FinalContents.setXiuGai("批量修改销售级别");
                    } else if (string2.equals("批量修改顾问级别")) {
                        FinalContents.setXiuGai("批量修改顾问级别");
                    } else if (string2.equals("批量修改团队长级别")) {
                        FinalContents.setXiuGai("批量修改团队长级别");
                    }
                    Intent intent = new Intent(Assistant_Teams_Activity.this, Captain_Team_BatchModifyingActivity.class);
                    startActivity(intent);
                }
                p.dismiss();
            }
        });

    }

    //TODO 获取数据
    @SuppressLint("WrongConstant")
    private void initData(String searcName, String type, final String loginFlag) {
        mContactModels.clear();
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        team_member_rv.setLayoutManager(manager);
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Log.i("团队长列表", searcName);
        Log.i("团队长列表", type);
        Log.i("团队长列表", loginFlag);
        Log.i("团队长列表", FinalContents.getUserID());
        Log.i("团队长列表", FinalContents.getUserID());
        Observable<TeamMemberBean> teamMemberBeane = fzbInterface.getTeamMemberBeane(searcName, type, loginFlag, FinalContents.getUserID(), FinalContents.getUserID(), "1000");
        teamMemberBeane.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<TeamMemberBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(TeamMemberBean teamMemberBean) {
                        rows = teamMemberBean.getData().getRows();
                        if (rows.size() != 0) {
                            all_no_information.setVisibility(View.GONE);
                            team_member_rv.setVisibility(View.VISIBLE);
                            for (int i = 0; i < rows.size(); ++i) {
                                if (rows.get(i).getName().equals("")) {

                                } else {
                                    ContactModel contactModel = new ContactModel(rows.get(i).getName() + "@" + rows.get(i).getId());// + "@" + rows.get(i).getId()
                                    Log.i("TZ", "数据查看：" + rows.get(i).getName() + "@" + rows.get(i).getId());
                                    mContactModels.add(contactModel);
                                }
                            }
                            initDatas();
                            flag = 0;
                        }else {
                            team_member_rv.setVisibility(View.GONE);
                            all_no_information.setVisibility(View.VISIBLE);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        team_member_rv.setVisibility(View.GONE);
                        all_no_information.setVisibility(View.VISIBLE);
                        Log.i("MyCL", "TeamMemberActivity错误信息：" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    //TODO 通讯设置
    private void initDatas() {
// RecyclerView设置相关
        decoration.registerTypePinnedHeader(1, new PinnedHeaderDecoration.PinnedHeaderCreator() {
            @Override
            public boolean create(RecyclerView parent, int adapterPosition) {
                return true;
            }
        });
        team_member_rv.addItemDecoration(decoration);
        mAdapter = new Captain_Team_TeamMemberAdapter();
        Collections.sort(mContactModels, new LetterComparator());
        mAdapter.setContacts(mContactModels);
        mAdapter.setList(rows);
        mAdapter.setItemOnClick(this);
        team_member_rv.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();

// 侧边设置相关
        mWaveSideBarView.setOnSelectIndexItemListener(new WaveSideBarView.OnSelectIndexItemListener() {
            @Override
            public void onSelectIndexItem(String letter) {
                for (int i = 0; i < mContactModels.size(); i++) {
                    if (mContactModels.get(i).getIndex().equals(letter)) {
                        ((LinearLayoutManager) team_member_rv.getLayoutManager()).scrollToPositionWithOffset(i, 0);
                        return;
                    }
                }
            }
        });

    }

    //TODO item点击事件
    @Override
    public void itemClick(String itemName) {
        for (int i = 0; i < rows.size(); i++) {
            if ((rows.get(i).getName() + "@" + rows.get(i).getId()).equals(itemName)) {
                FinalContents.setAgentId(rows.get(i).getId());//
                FinalContents.setInforId(rows.get(i).getId());//
                Log.i("团助端修改", FinalContents.getAgentId());
                Log.i("团助端修改", FinalContents.getInforId());
                Intent intent = new Intent(Assistant_Teams_Activity.this, Captain_Team_SalesDetailsDetailsActivity.class);
                startActivity(intent);
            }
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        String s = team_member_et.getText().toString();
        if (team_member_ll2.getVisibility() == View.VISIBLE) {
            String s2 = team_member_tv.getText().toString();
            if (s2.equals("全部")) {
                initData(s, "2", "");
            } else if (s2.equals("不看禁用")) {
                initData(s, "2", "1");
            } else if (s2.equals("只看禁用")) {
                initData(s, "2", "0");
            }
        } else if (team_member_ll4.getVisibility() == View.VISIBLE) {
            String s2 = team_member_tv.getText().toString();
            if (s2.equals("全部")) {
                initData(s, "3", "");
            } else if (s2.equals("不看禁用")) {
                initData(s, "3", "1");
            } else if (s2.equals("只看禁用")) {
                initData(s, "3", "0");
            }
        } else if (team_member_ll6.getVisibility() == View.VISIBLE) {
            String s2 = team_member_tv.getText().toString();
            if (s2.equals("全部")) {
                initData(s, "1", "");
            } else if (s2.equals("不看禁用")) {
                initData(s, "1", "1");
            } else if (s2.equals("只看禁用")) {
                initData(s, "1", "0");
            }
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        FinalContents.setAgentId(FinalContents.getUserID());
    }
}

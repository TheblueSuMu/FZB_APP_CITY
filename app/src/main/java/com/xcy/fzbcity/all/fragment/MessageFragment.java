package com.xcy.fzbcity.all.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.msg.MsgService;
import com.netease.nimlib.sdk.msg.constant.SessionTypeEnum;
import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.adapter.MainTabPagerAdapter;
import com.xcy.fzbcity.all.api.FinalContents;
import com.xcy.fzbcity.all.modle.MainTab;
import com.xcy.fzbcity.all.myim.FadeInOutPageTransformer;
import com.xcy.fzbcity.all.persente.SingleClick;
import com.xcy.fzbcity.all.persente.StatusBar;


//TODO 消息界面
public class MessageFragment extends Fragment implements View.OnClickListener, ViewPager.OnPageChangeListener {
    private View view;

    LinearLayout new_information_ll_1;
    LinearLayout new_information_ll_2;
    LinearLayout new_information_ll_3;
    LinearLayout new_information_ll_4;

    private MainTabPagerAdapter adapter;

    private ViewPager pager;

    FrameLayout information_fl;

    private int scrollState;

    FragmentManager manager;
    FragmentTransaction transaction;
    TestInformeFragment testInformeFragment = new TestInformeFragment();
    private String type = "1";

    //
    public void setType(String type) {
        this.type = type;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        StatusBar.makeStatusBarTransparent(getActivity());
        view = inflater.inflate(R.layout.modulebroker_fragment_message, null);
        FinalContents.setHidden(true);
        initView();
        return view;
    }

    private void initView() {

        information_fl = view.findViewById(R.id.information_fl);
        pager = view.findViewById(R.id.main_tab_pager);

        new_information_ll_1 = view.findViewById(R.id.new_information_ll_1);
        new_information_ll_2 = view.findViewById(R.id.new_information_ll_2);
        new_information_ll_3 = view.findViewById(R.id.new_information_ll_3);
        new_information_ll_4 = view.findViewById(R.id.new_information_ll_4);

        pager.setVisibility(View.VISIBLE);
        adapter = new MainTabPagerAdapter(getActivity().getSupportFragmentManager(), getContext(), pager);
        pager.setOffscreenPageLimit(adapter.getCacheCount());
        pager.setPageTransformer(true, new FadeInOutPageTransformer());
        pager.setAdapter(adapter);
        pager.setOnPageChangeListener(this);



        new_information_ll_1.setOnClickListener(this);
        new_information_ll_3.setOnClickListener(this);


    }

    @SingleClick(1000)
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.new_information_ll_1:
                new_information_ll_2.setVisibility(View.VISIBLE);
                new_information_ll_4.setVisibility(View.GONE);
                initChat();
                break;
            case R.id.new_information_ll_3:
                new_information_ll_2.setVisibility(View.GONE);
                new_information_ll_4.setVisibility(View.VISIBLE);
                initInform();
                break;
        }
    }

    //通知
    private void initInform() {

        pager.setVisibility(View.GONE);
        information_fl.setVisibility(View.VISIBLE);

        manager = getActivity().getSupportFragmentManager();
        transaction = manager.beginTransaction();
        transaction.replace(R.id.information_fl, testInformeFragment);
        transaction.commit();

    }

    //聊天
    private void initChat() {


        pager.setVisibility(View.VISIBLE);
        information_fl.setVisibility(View.GONE);


    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//        tabs.onPageScrolled(position, positionOffset, positionOffsetPixels);
        adapter.onPageScrolled(position);
    }

    @Override
    public void onPageSelected(int position) {
//        tabs.onPageSelected(position);
        selectPage();
        enableMsgNotification(false);
    }

    @Override
    public void onPageScrollStateChanged(int state) {
//        tabs.onPageScrollStateChanged(state);
        scrollState = state;
        selectPage();
    }
    private void selectPage() {
        if (scrollState == ViewPager.SCROLL_STATE_IDLE) {
            adapter.onPageSelected(pager.getCurrentItem());
        }
    }

    /**
     * 设置最近联系人的消息为已读
     * <p>
     * account, 聊天对象帐号，或者以下两个值：
     * {@link MsgService#MSG_CHATTING_ACCOUNT_ALL} 目前没有与任何人对话，但能看到消息提醒（比如在消息列表界面），不需要在状态栏做消息通知
     * {@link MsgService#MSG_CHATTING_ACCOUNT_NONE} 目前没有与任何人对话，需要状态栏消息通知
     */
    private void enableMsgNotification(boolean enable) {
        boolean msg = (pager.getCurrentItem() != MainTab.RECENT_CONTACTS.tabIndex);
        if (enable | msg) {
            NIMClient.getService(MsgService.class).setChattingAccount(
                    MsgService.MSG_CHATTING_ACCOUNT_NONE, SessionTypeEnum.None);
        } else {
            NIMClient.getService(MsgService.class).setChattingAccount(
                    MsgService.MSG_CHATTING_ACCOUNT_ALL, SessionTypeEnum.None);
        }
    }

}

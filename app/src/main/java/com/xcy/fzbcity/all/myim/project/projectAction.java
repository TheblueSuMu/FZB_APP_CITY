package com.xcy.fzbcity.all.myim.project;

import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.netease.nim.uikit.business.session.actions.BaseAction;
import com.netease.nimlib.sdk.msg.MessageBuilder;
import com.netease.nimlib.sdk.msg.model.CustomMessageConfig;
import com.netease.nimlib.sdk.msg.model.IMMessage;
import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.adapter.RecyclerAdapter;
import com.xcy.fzbcity.all.api.FinalContents;
import com.xcy.fzbcity.all.modle.HotBean;
import com.xcy.fzbcity.all.modle.SetPhraseBean;
import com.xcy.fzbcity.all.myim.cyy.SetPhraseNewsAdapter;
import com.xcy.fzbcity.all.service.MyService;
import com.xcy.fzbcity.all.view.SearchInterfaceActivity;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class projectAction extends BaseAction implements View.OnClickListener {

    private RecyclerView recyclerView;
    private PopupWindow popupWindow;
    private LinearLayout search_img;
    private EditText search_edit_text;
    ImageView all_no_information;
    private List<HotBean.DataBean.RowsBean> hotlist = new ArrayList<>();
    private NewRecyclerAdapter recyclerAdapter;
    private String text;

    public projectAction() {
        super(R.drawable.xiangmu, R.string.input_panel_project);
    }

    @Override
    public void onClick() {

        View inflate1 = LayoutInflater.from(getActivity()).inflate(R.layout.activity_search_interface, null);
        popupWindow = new PopupWindow(inflate1, RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        //设置背景,这个没什么效果，不添加会报错
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        //设置点击弹窗外隐藏自身
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);

        popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setContentView(inflate1);
        popupWindow.setClippingEnabled(false);

        //设置位置
        popupWindow.showAtLocation(getActivity().getCurrentFocus(), Gravity.CENTER, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);


        recyclerView = inflate1.findViewById(R.id.search_recyler);
        search_img = inflate1.findViewById(R.id.search_img);
        all_no_information = inflate1.findViewById(R.id.all_no_information);
        search_edit_text = inflate1.findViewById(R.id.search_edit_text);

        search_img.setOnClickListener(this);

        initData();


        search_edit_text.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (KeyEvent.KEYCODE_ENTER == i && KeyEvent.ACTION_DOWN == keyEvent.getAction()) {
                    initData();
                    return true;
                }
                return false;
            }

        });

    }

    private void initData() {
        text = search_edit_text.getText().toString();
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<HotBean> hotBean = fzbInterface.getHotBean(FinalContents.getUserID(), FinalContents.getCityID(), "1", text, "1000");
        hotBean.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HotBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(HotBean hotBean) {

                        HotBean.DataBean hotBeanData = hotBean.getData();
                        hotlist = hotBeanData.getRows();
                        Log.i("调接口", "接收值" + hotlist.size());
                        //在此处修改布局排列方向
                        if (hotlist.size() == 0) {
                            all_no_information.setVisibility(View.VISIBLE);
                            recyclerView.setVisibility(View.GONE);
//                            recyclerAdapter.notifyDataSetChanged();
                        } else {
                            all_no_information.setVisibility(View.GONE);
                            recyclerView.setVisibility(View.VISIBLE);
                            LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
                            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                            recyclerView.setLayoutManager(layoutManager);
                            recyclerAdapter = new NewRecyclerAdapter(hotlist);
                            recyclerAdapter.setProject(FinalContents.getProject());
                            recyclerView.setAdapter(recyclerAdapter);
                            recyclerAdapter.notifyDataSetChanged();
                        }

                        recyclerAdapter.setOnItemClickListener(new NewRecyclerAdapter.OnItemClickLisenter() {
                            @Override
                            public void onItemClick(int postion) {

                                //项目发送
                                ProjectCustomAttachment attachment = new ProjectCustomAttachment();
                                attachment.setPid(hotlist.get(postion).getId());
                                attachment.setName(hotlist.get(postion).getProjectName());
                                attachment.setPrice(hotlist.get(postion).getMonetaryUnit());
                                attachment.setProjectType(hotlist.get(postion).getProductFeature());
                                attachment.setSquare("");
                                CustomMessageConfig config = new CustomMessageConfig();
                                config.enableHistory = false;
                                config.enableRoaming = false;
                                config.enableSelfSync = false;
                                IMMessage message = MessageBuilder.createCustomMessage(getAccount(), getSessionType(), "项目消息", attachment,config);
                                sendMessage(message);
                                popupWindow.dismiss();


                            }
                        });

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });


    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.search_img){
            popupWindow.dismiss();
        }
    }
}

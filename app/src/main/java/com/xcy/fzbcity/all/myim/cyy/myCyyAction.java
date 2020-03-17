package com.xcy.fzbcity.all.myim.cyy;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.netease.nim.uikit.api.NimUIKit;
import com.netease.nim.uikit.api.UIKitOptions;
import com.netease.nim.uikit.business.session.actions.BaseAction;
import com.netease.nim.uikit.impl.NimUIKitImpl;
import com.netease.nimlib.sdk.msg.MessageBuilder;
import com.netease.nimlib.sdk.msg.constant.SessionTypeEnum;
import com.netease.nimlib.sdk.msg.model.IMMessage;
import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.adapter.SetPhraseAdapter;
import com.xcy.fzbcity.all.api.FinalContents;
import com.xcy.fzbcity.all.modle.AddLanguageBean;
import com.xcy.fzbcity.all.modle.AmendUsefulExpressionsBean;
import com.xcy.fzbcity.all.modle.DeleteLanguageBean;
import com.xcy.fzbcity.all.modle.SetPhraseBean;
import com.xcy.fzbcity.all.service.MyService;
import com.xcy.fzbcity.all.utils.ToastUtil;
import com.xcy.fzbcity.all.view.SetPhraseAcitvity;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class myCyyAction extends BaseAction implements SetPhraseNewsAdapter.onMyItemClick, SetPhraseAdapter.onBianji, SetPhraseAdapter.onShanchu {

    //常用语列表查询
    SetPhraseNewsAdapter setPhraseAdapter;
    private RecyclerView item_alertdialog_rv1;
    private RelativeLayout item_alertdialog_rl1;
    private PopupWindow popupWindow;
    private List<SetPhraseBean.DataBean.CommonLanguageListBean> commonLanguageList;
    private PopupWindow popupWindowTwo;


    //自定义常用语查询
    RelativeLayout set_phrase_return;
    TextView set_phrase_add;
    RecyclerView set_phrase_rv;
    SetPhraseAdapter adapter;
    private PopupWindow popupWindowThree;

    //添加 修改常用语
    RelativeLayout amend_useful_expressions_return;
    TextView amend_useful_expressions_et_title;
    TextView amend_useful_expressions_bc;
    TextView amend_useful_expressions_tv;
    EditText amend_useful_expressions_et;
    private String languageId;
    private String languageContent;

    public myCyyAction() {
        super(R.drawable.chuangyongyu, R.string.input_panel_cyy);
    }

    @Override
    public void onClick() {

        initPopWindowOneData();


    }

    /**
     * 常用语列表弹窗
     */
    private void initPopWindowOneData() {

        View inflate1 = LayoutInflater.from(getActivity()).inflate(R.layout.item_alertdialog, null);
        popupWindow = new PopupWindow(inflate1, RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        popupWindow.setHeight(700);
        //设置背景,这个没什么效果，不添加会报错
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        //设置点击弹窗外隐藏自身
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        //设置位置
        popupWindow.showAtLocation(inflate1, Gravity.BOTTOM, 0, 0);

        item_alertdialog_rv1 = inflate1.findViewById(R.id.item_alertdialog_rv);
        item_alertdialog_rl1 = inflate1.findViewById(R.id.item_alertdialog_rl);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        item_alertdialog_rv1.setLayoutManager(manager);
        setPhraseAdapter = new SetPhraseNewsAdapter();
        setPhraseAdapter.setOnMyItemClick(this);
        initData();

        item_alertdialog_rl1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initPopWindowZDY();
            }
        });

    }
    /**
     * 常用语列表弹窗数据
     */
    private void initData() {

        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Log.i("打印","FinalContents.getUserID()：" + FinalContents.getUserID());
        Observable<SetPhraseBean> SetPhraseBean = fzbInterface.getSetPhraseBean(FinalContents.getUserID(), "1", "100");
        SetPhraseBean.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SetPhraseBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.i("常用语", "onSubscribe");
                    }

                    @Override
                    public void onNext(SetPhraseBean setPhraseBean) {
                        Log.i("常用语", "onNext");
                        commonLanguageList = setPhraseBean.getData().getCommonLanguageList();
                        setPhraseAdapter.setCommonLanguageList(myCyyAction.this.commonLanguageList);
                        item_alertdialog_rv1.setAdapter(setPhraseAdapter);
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onComplete() {
                        Log.i("常用语", "onComplete");
                    }
                });

    }

    /**
     * 发送常用语
     * @param position
     */
    @Override
    public void itemClick(int position) {
        UIKitOptions.buildForIndependentChatRoom().messageRightBackground = 0;
        IMMessage textMessage = createTextMessage(commonLanguageList.get(position).getContent());
        sendMessage(textMessage);
        commonLanguageList.clear();
        popupWindow.dismiss();

    }

    protected IMMessage createTextMessage(String text) {
        SessionTypeEnum sessionType = SessionTypeEnum.P2P;
        return MessageBuilder.createTextMessage(getAccount(), sessionType, text);
    }

    /**
     * 自定义弹窗列表
     */
    private void initPopWindowZDY() {

        popupWindow.dismiss();
        View inflate1 = LayoutInflater.from(getActivity()).inflate(R.layout.activity_set_phrase, null);
        popupWindowTwo = new PopupWindow(inflate1, RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        //设置背景,这个没什么效果，不添加会报错
        popupWindowTwo.setBackgroundDrawable(new BitmapDrawable());
        //设置点击弹窗外隐藏自身
        popupWindowTwo.setFocusable(true);
        popupWindowTwo.setOutsideTouchable(true);

        popupWindowTwo.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindowTwo.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindowTwo.setBackgroundDrawable(new BitmapDrawable());
        popupWindowTwo.setFocusable(true);
        popupWindowTwo.setOutsideTouchable(true);
        popupWindowTwo.setContentView(inflate1);
        popupWindowTwo.setClippingEnabled(false);

        //设置位置
        popupWindowTwo.showAtLocation(getActivity().getCurrentFocus(), Gravity.CENTER, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        set_phrase_return = inflate1.findViewById(R.id.set_phrase_return);
        set_phrase_add = inflate1.findViewById(R.id.set_phrase_add);
        set_phrase_rv = inflate1.findViewById(R.id.set_phrase_rv);

        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        set_phrase_rv.setLayoutManager(manager);
        adapter = new SetPhraseAdapter();
        adapter.setOnBianji(this);
        adapter.setOnShanchu(this);
        set_phrase_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindowTwo.dismiss();
                initPopWindowOneData();
            }
        });
        set_phrase_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                languageId = "";
                languageContent = "";
                initPopWindowThree();
            }
        });
        initPopWindowTwoData();

    }

    /**
     * 自定义弹窗数据
     */
    private void initPopWindowTwoData() {

        Log.i("常用语", "initData");
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Log.i("常用语", "FinalContents.getUserID():" + FinalContents.getUserID());
        Observable<SetPhraseBean> SetPhraseBean = fzbInterface.getSetPhraseBean(FinalContents.getUserID(), "1", "100");
        SetPhraseBean.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SetPhraseBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.i("常用语", "onSubscribe");
                    }

                    @Override
                    public void onNext(SetPhraseBean setPhraseBean) {
                        Log.i("常用语", "onNext");
                        commonLanguageList = setPhraseBean.getData().getCommonLanguageList();
                        adapter.setCommonLanguageList(commonLanguageList);
                        set_phrase_rv.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("常用语", "错误信息:" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.i("常用语", "onComplete");
                    }
                });

    }

    /**
     * 编辑
     * @param position
     */
    @Override
    public void onBianji(int position) {
        languageId = commonLanguageList.get(position).getId();
        languageContent = commonLanguageList.get(position).getContent();
        initPopWindowThree();
    }

    /**
     * 添加 修改常用语弹窗
     */
    private void initPopWindowThree() {

        popupWindowTwo.dismiss();
        View inflate1 = LayoutInflater.from(getActivity()).inflate(R.layout.activity_amend_useful_expressions, null);
        popupWindowThree = new PopupWindow(inflate1, RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        //设置背景,这个没什么效果，不添加会报错
        popupWindowThree.setBackgroundDrawable(new BitmapDrawable());
        //设置点击弹窗外隐藏自身
        popupWindowThree.setFocusable(true);
        popupWindowThree.setOutsideTouchable(true);

        popupWindowThree.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindowThree.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindowThree.setBackgroundDrawable(new BitmapDrawable());
        popupWindowThree.setFocusable(true);
        popupWindowThree.setOutsideTouchable(true);
        popupWindowThree.setContentView(inflate1);
        popupWindowThree.setClippingEnabled(false);

        //设置位置
        popupWindowThree.showAtLocation(getActivity().getCurrentFocus(), Gravity.CENTER, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        amend_useful_expressions_return = inflate1.findViewById(R.id.amend_useful_expressions_return);
        amend_useful_expressions_et_title = inflate1.findViewById(R.id.amend_useful_expressions_et_title);
        amend_useful_expressions_bc = inflate1.findViewById(R.id.amend_useful_expressions_bc);
        amend_useful_expressions_tv = inflate1.findViewById(R.id.amend_useful_expressions_tv);
        amend_useful_expressions_et = inflate1.findViewById(R.id.amend_useful_expressions_et);

        amend_useful_expressions_et.setText(languageContent);

        if(languageId.equals("")){
            amend_useful_expressions_et_title.setText("添加常用语");
        }else {
            amend_useful_expressions_et_title.setText("修改常用语");
        }

        amend_useful_expressions_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindowThree.dismiss();
                initPopWindowZDY();
            }
        });
        amend_useful_expressions_bc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initPopWindowThreeData();
            }
        });

        amend_useful_expressions_et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.i("常用语","afterTextChanged：" + s.toString());
                amend_useful_expressions_tv.setText(s.length() + "/100");
            }
        });

    }

    /**
     * 添加 修改常用语保存
     */
    private void initPopWindowThreeData() {

        if(languageId.equals("")){
            initAddBC();
        }else {
            initChangeBC();
        }

    }

    /**
     * 添加
     */
    private void initAddBC() {

        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Log.i("修改常用语","languageId:" + languageId);
        Log.i("修改常用语","amend_useful_expressions_et.getText().toString():" + amend_useful_expressions_et.getText().toString());
        Observable<AddLanguageBean> AddLanguageBean = fzbInterface.getAddLanguageBean(FinalContents.getUserID(),amend_useful_expressions_et.getText().toString());
        AddLanguageBean.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<AddLanguageBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(AddLanguageBean addLanguageBean) {
                        if(addLanguageBean.getData().getMessageCode().equals("1")){
                            ToastUtil.showToast(getActivity(),addLanguageBean.getData().getMessage());
                            popupWindowThree.dismiss();
                            initPopWindowZDY();
                        }else{
                            ToastUtil.showToast(getActivity(),addLanguageBean.getData().getMessage());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    /**
     * 编辑
     */
    private void initChangeBC() {

        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Log.i("修改常用语","languageId:" + languageId);
        Log.i("修改常用语","amend_useful_expressions_et.getText().toString():" + amend_useful_expressions_et.getText().toString());
        Observable<AmendUsefulExpressionsBean> AmendUsefulExpressionsActivity = fzbInterface.getAmendUsefulExpressionsBean(languageId,amend_useful_expressions_et.getText().toString());
        AmendUsefulExpressionsActivity.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<AmendUsefulExpressionsBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(AmendUsefulExpressionsBean amendUsefulExpressionsBean) {
                        if(amendUsefulExpressionsBean.getData().getMessageCode().equals("1")){
                            ToastUtil.showToast(getActivity(),amendUsefulExpressionsBean.getData().getMessage());
                            popupWindowThree.dismiss();
                            initPopWindowZDY();
                        }else{
                            ToastUtil.showToast(getActivity(),amendUsefulExpressionsBean.getData().getMessage());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("修改常用语","错误信息:" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    /**
     * 删除弹窗
     * @param position
     */
    @Override
    public void onShanchu(int position) {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(getActivity());
        View inflate = LayoutInflater.from(getActivity()).inflate(R.layout.binding_report, null, false);
        builder1.setView(inflate);
        final AlertDialog show = builder1.show();
        show.getWindow().setBackgroundDrawableResource(R.drawable.report_shape);

        WindowManager m = getActivity().getWindowManager();
        Display d = m.getDefaultDisplay();
        WindowManager.LayoutParams attributes = show.getWindow().getAttributes();
        attributes.width = (int) (d.getWidth() - 200);
        show.getWindow().setAttributes(attributes);
        TextView report_binding_title = inflate.findViewById(R.id.report_binding_title);
        TextView report_binding_confirm_tv = inflate.findViewById(R.id.report_binding_confirm_tv);
        TextView report_binding_cancel_tv = inflate.findViewById(R.id.report_binding_cancel_tv);
        RelativeLayout report_binding_cancel = inflate.findViewById(R.id.report_binding_cancel);
        RelativeLayout report_binding_confirm = inflate.findViewById(R.id.report_binding_confirm);
        report_binding_title.setText("是否删除");//内容
        report_binding_confirm_tv.setText("删除");
        report_binding_cancel_tv.setText("取消");
        report_binding_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show.dismiss();
            }
        });
        report_binding_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initShanChu(position);
                show.dismiss();
            }
        });
    }

    /**
     * 确定删除
     * @param position
     */
    private void initShanChu(int position) {

        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Log.i("刷新", "FinalContents.getCityID()");
        Observable<DeleteLanguageBean> DeleteLanguageBean = fzbInterface.getDeleteLanguageBean(commonLanguageList.get(position).getId());
        DeleteLanguageBean.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DeleteLanguageBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(DeleteLanguageBean deleteLanguageBean) {
                        if (deleteLanguageBean.getData().getMessageCode().equals("1")) {
                            ToastUtil.showToast(getActivity(), deleteLanguageBean.getData().getMessage());
                            initPopWindowTwoData();
                        }

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

}

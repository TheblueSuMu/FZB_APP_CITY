package com.xcy.fzbcity.all.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.adapter.SetPhraseAdapter;
import com.xcy.fzbcity.all.api.FinalContents;
import com.xcy.fzbcity.all.modle.DeleteLanguageBean;
import com.xcy.fzbcity.all.modle.SetPhraseBean;
import com.xcy.fzbcity.all.persente.StatusBar;
import com.xcy.fzbcity.all.service.MyService;
import com.xcy.fzbcity.all.utils.ToastUtil;

import java.io.Serializable;
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

public class SetPhraseAcitvity extends AllActivity implements View.OnClickListener, SetPhraseAdapter.onBianji, SetPhraseAdapter.onShanchu, Serializable {

    RelativeLayout set_phrase_return;
    TextView set_phrase_add;
    RecyclerView set_phrase_rv;
    SetPhraseAdapter adapter;
    private List<SetPhraseBean.DataBean.CommonLanguageListBean> commonLanguageList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_phrase);
        StatusBar.makeStatusBarTransparent(this);

        initDataView();

    }

    private void initDataView() {
        Log.i("常用语", "initDataView");
        set_phrase_return = findViewById(R.id.set_phrase_return);
        set_phrase_add = findViewById(R.id.set_phrase_add);
        set_phrase_rv = findViewById(R.id.set_phrase_rv);

        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        set_phrase_rv.setLayoutManager(manager);
        adapter = new SetPhraseAdapter();

        adapter.setOnBianji(this);
        adapter.setOnShanchu(this);

//        initData();

        set_phrase_return.setOnClickListener(this);
        set_phrase_add.setOnClickListener(this);

    }

    private void initData() {
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

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.set_phrase_return:
                finish();
                break;
            case R.id.set_phrase_add:
                Intent intent = new Intent(SetPhraseAcitvity.this, AmendUsefulExpressionsActivity.class);
                intent.putExtra("languageId", "");
                intent.putExtra("languageContent", "");
                startActivity(intent);
                break;
        }

    }

    /**
     * 编辑
     * @param position
     */
    @Override
    public void onBianji(int position) {
        Intent intent = new Intent(SetPhraseAcitvity.this, AmendUsefulExpressionsActivity.class);
        intent.putExtra("languageId", commonLanguageList.get(position).getId());
        intent.putExtra("languageContent", commonLanguageList.get(position).getContent());
        startActivity(intent);
    }

    /**
     * 删除弹窗
     * @param position
     */
    @Override
    public void onShanchu(int position) {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(SetPhraseAcitvity.this);
        View inflate = LayoutInflater.from(SetPhraseAcitvity.this).inflate(R.layout.binding_report, null, false);
        builder1.setView(inflate);
        final AlertDialog show = builder1.show();
        show.getWindow().setBackgroundDrawableResource(R.drawable.report_shape);

        WindowManager m = getWindowManager();
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
     * 删除
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
                            ToastUtil.showLongToast(SetPhraseAcitvity.this, deleteLanguageBean.getData().getMessage());
                            initData();
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

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("常用语", "SetPhraseActivityOnResume");
        initData();

    }
}

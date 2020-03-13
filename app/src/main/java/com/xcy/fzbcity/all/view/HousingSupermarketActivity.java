package com.xcy.fzbcity.all.view;

import android.content.Intent;
import android.graphics.Canvas;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.adapter.HousingSupermarketAdapter;
import com.xcy.fzbcity.all.api.FinalContents;
import com.xcy.fzbcity.all.api.RedEnvelopesAllTalk;
import com.xcy.fzbcity.all.modle.CustomerVisitorSumStatisticsBean;
import com.xcy.fzbcity.all.modle.ProjectSortBean;
import com.xcy.fzbcity.all.modle.RedBagSumStatisticsBean;
import com.xcy.fzbcity.all.modle.SupermarketBean;
import com.xcy.fzbcity.all.service.MyService;
import com.xcy.fzbcity.all.utils.ToastUtil;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HousingSupermarketActivity extends AllActivity implements View.OnClickListener {

    private LinearLayout housing_supermarket_return;
    private TextView housing_supermarket_hot_list;
    private RecyclerView housing_supermarket_recycler;
    private Button housing_supermarket_add_housing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_housing_supermarket);
        initfvb();
    }

    private void initfvb(){
        housing_supermarket_return = findViewById(R.id.housing_supermarket_return);
        housing_supermarket_hot_list = findViewById(R.id.housing_supermarket_hot_list);
        housing_supermarket_recycler = findViewById(R.id.housing_supermarket_recycler);
        housing_supermarket_add_housing = findViewById(R.id.housing_supermarket_add_housing);

        housing_supermarket_return.setOnClickListener(this);
        housing_supermarket_hot_list.setOnClickListener(this);
        housing_supermarket_add_housing.setOnClickListener(this);
        initData();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.housing_supermarket_return:
                //     TODO     返回
                finish();
                break;
            case R.id.housing_supermarket_hot_list:
                //     TODO     热推列表
                Intent hotintent = new Intent(HousingSupermarketActivity.this,HousingSupermarketHotListActivity.class);
                startActivity(hotintent);
                break;
            case R.id.housing_supermarket_add_housing:
                //     TODO     新增楼盘
                Intent intent = new Intent(HousingSupermarketActivity.this,HousingSupermarketAddHousingActivity.class);
                startActivity(intent);
                break;
        }
    }


    //  TODO    房源超市列表
    private void initData(){
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        final Observable<SupermarketBean> code = fzbInterface.getSupermarket(FinalContents.getUserID(), RedEnvelopesAllTalk.getWebshopId(),"");
        code.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SupermarketBean>() {

                    private HousingSupermarketAdapter housingSupermarketAdapter;

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(SupermarketBean supermarketBean) {
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(HousingSupermarketActivity.this);
                        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                        housing_supermarket_recycler.setLayoutManager(linearLayoutManager);
                        housingSupermarketAdapter = new HousingSupermarketAdapter(supermarketBean.getData().getRows());
                        housing_supermarket_recycler.setAdapter(housingSupermarketAdapter);
                        housingSupermarketAdapter.notifyDataSetChanged();
                        ItemTouchHelper.Callback mItemTouchCallBack = new ItemTouchHelper.Callback() {
                            /**
                             * 设置滑动类型标记
                             *
                             * @param recyclerView
                             * @param viewHolder
                             * @return
                             *          返回一个整数类型的标识，用于判断Item那种移动行为是允许的
                             */
                            @Override
                            public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                                return makeMovementFlags(ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT | ItemTouchHelper.DOWN | ItemTouchHelper.UP, 0);
                            }

                            /**
                             * Item是否支持长按拖动
                             *
                             * @return
                             *          true  支持长按操作
                             *          false 不支持长按操作
                             */
                            @Override
                            public boolean isLongPressDragEnabled() {
                                return true;
                            }

                            /**
                             * Item是否支持滑动
                             *
                             * @return
                             *          true  支持滑动操作
                             *          false 不支持滑动操作
                             */
                            @Override
                            public boolean isItemViewSwipeEnabled() {
                                return false;
                            }

                            @Override
                            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

                            }

                            /**
                             * 拖拽切换Item的回调
                             *
                             * @param recyclerView
                             * @param viewHolder
                             * @param target
                             * @return
                             *          如果Item切换了位置，返回true；反之，返回false
                             */
                            @Override
                            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                                housingSupermarketAdapter.move(viewHolder.getAdapterPosition(), target.getAdapterPosition());
                                return true;
                            }

                            /**
                             * Item被选中时候回调
                             *
                             * @param viewHolder
                             * @param actionState
                             *          当前Item的状态
                             *          ItemTouchHelper.ACTION_STATE_IDLE   闲置状态
                             *          ItemTouchHelper.ACTION_STATE_SWIPE  滑动中状态
                             *          ItemTouchHelper#ACTION_STATE_DRAG   拖拽中状态
                             */
                            @Override
                            public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
                                //  item被选中的操作
                                if(actionState != ItemTouchHelper.ACTION_STATE_IDLE) {
                                    viewHolder.itemView.setBackgroundResource(R.color.color);
                                }
                                super.onSelectedChanged(viewHolder, actionState);
                            }

                            /**
                             * 用户操作完毕或者动画完毕后会被调用
                             *
                             * @param recyclerView
                             * @param viewHolder
                             */
                            @Override
                            public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                                // 操作完毕后恢复颜色
                                viewHolder.itemView.setBackgroundResource(R.color.color);
                                initProjectSort();
                                super.clearView(recyclerView, viewHolder);
                            }
                        };
                        ItemTouchHelper mItemTouchHelper = new ItemTouchHelper(mItemTouchCallBack);

                        mItemTouchHelper.attachToRecyclerView(housing_supermarket_recycler);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("房源超市列表", "房源超市列表错误信息:" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void initProjectSort(){
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        final Observable<ProjectSortBean> code = fzbInterface.getProjectSort(FinalContents.getUserID(), RedEnvelopesAllTalk.getWebshopId(),"","");
        code.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ProjectSortBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ProjectSortBean projectSortBean) {
                        ToastUtil.showLongToast(HousingSupermarketActivity.this,projectSortBean.getData().getMessage());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("访客记录列表", "访客记录列表错误信息:" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}

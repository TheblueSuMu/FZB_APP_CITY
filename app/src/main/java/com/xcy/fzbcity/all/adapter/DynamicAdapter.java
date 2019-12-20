package com.xcy.fzbcity.all.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.api.FinalContents;
import com.xcy.fzbcity.all.database.LikeNumBean;
import com.xcy.fzbcity.all.modle.Dynamic2Bean;
import com.xcy.fzbcity.all.service.MyService;
import com.xcy.fzbcity.all.view.ProjectDetails;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DynamicAdapter extends RecyclerView.Adapter<DynamicAdapter.ViewHolder> {

    private List<Dynamic2Bean.DataBean.RowsBean> list;
    private Context context;
    LianxiClick lianxiClick;
    PingLun pingLun;
    FuZhi fuZhi;
    XiangMu xiangMu;
    TuPian tuPian;

    public void setTuPian(TuPian tuPian) {
        this.tuPian = tuPian;
    }

    private int builing = 0;

    public void setBuiling(int builing) {
        this.builing = builing;
    }

    public void setXiangMu(XiangMu xiangMu) {
        this.xiangMu = xiangMu;
    }

    public void setFuZhi(FuZhi fuZhi) {
        this.fuZhi = fuZhi;
    }

    public void setPingLun(PingLun pingLun) {
        this.pingLun = pingLun;
    }

    public void setDynamicClick(LianxiClick lianxiClick) {
        this.lianxiClick = lianxiClick;
    }

    public void setList(List<Dynamic2Bean.DataBean.RowsBean> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dynamic, parent, false);
        ViewHolder holder = new ViewHolder(view);
        context = parent.getContext();
        return holder;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

        Glide.with(context).load(FinalContents.getImageUrl() + list.get(position).getCreateBy().getPhoto()).into(holder.circle_img);

        if (list.get(position).getImg().equals("")) {
            holder.item_dynamic_ll.setVisibility(View.GONE);
        } else {
            holder.item_dynamic_ll.setVisibility(View.VISIBLE);
            Glide.with(context).load(FinalContents.getImageUrl() + list.get(position).getImg()).into(holder.circle_img_rv);
        }

        if (list.get(position).getIsLike().equals("0")) {
            Glide.with(context).load(R.mipmap.icon_like).into(holder.item_dynamic_zan);
        } else if (list.get(position).getIsLike().equals("1")) {
            Glide.with(context).load(R.mipmap.icon_2).into(holder.item_dynamic_zan);
        }
        holder.circle_like.setText(list.get(position).getLikeNum());


        Glide.with(context).load(FinalContents.getImageUrl()+ list.get(position).getProject().getProjectImg()).into(holder.circle_img_1);
        holder.circle_title.setText(list.get(position).getTitle());
        holder.circle_message.setText(list.get(position).getContent());
        holder.circle_time.setText(list.get(position).getCreateDate());
        holder.circle_name.setText(list.get(position).getProject().getProjectName());



        holder.circle_img_rv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tuPian.tp(position);
            }
        });

//TODO 点赞
        holder.circle_zan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Retrofit.Builder builder = new Retrofit.Builder();
                builder.baseUrl(FinalContents.getBaseUrl());
                builder.addConverterFactory(GsonConverterFactory.create());
                builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
                Retrofit build = builder.build();
                MyService fzbInterface = build.create(MyService.class);
                Observable<LikeNumBean> likeNum = fzbInterface.getLikeNum(list.get(position).getId(), FinalContents.getUserID(), "1");

                likeNum.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<LikeNumBean>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(LikeNumBean likeNumBean) {
                                holder.circle_like.setText("" + likeNumBean.getData().getLikeNum());
                                if (likeNumBean.getData().getMessage().equals("点赞成功")) {
                                    Glide.with(context).load(R.mipmap.icon_like).into(holder.item_dynamic_zan);
                                } else {
                                    Glide.with(context).load(R.mipmap.icon_2).into(holder.item_dynamic_zan);
                                }
                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.i("点赞","楼盘动态点赞错误信息：" + e.getMessage());
                            }

                            @Override
                            public void onComplete() {

                            }
                        });


            }
        });

//        TODO 复制
        holder.circle_fuzhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fuZhi.FuZhi(position);
            }
        });

//        TODO 评论
        holder.circle_pinglun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pingLun.pingClick(position);
            }
        });
//        TODO 联系
        holder.circle_lianxi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lianxiClick.Click(position);
            }
        });
//        TODO 项目跳转
        holder.dynamic_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (builing == 1) {
                    Intent intent = new Intent(context, ProjectDetails.class);
                    context.startActivity(intent);
                } else {
                    xiangMu.xm(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView circle_img;
        TextView circle_title;
        TextView circle_time;
        ImageView circle_img_rv;
        ImageView circle_img_1;
        ImageView item_dynamic_zan;
        TextView circle_message;
        TextView circle_name;
        TextView circle_like;
        LinearLayout circle_zan;
        TextView circle_pinglun;
        TextView circle_lianxi;
        TextView circle_fuzhi;
        LinearLayout dynamic_ll;
        LinearLayout item_dynamic_ll;

        public ViewHolder(View itemView) {
            super(itemView);
            //注意这里可能需要import com.example.lenovo.myrecyclerview.R; 才能使用R.id
            circle_img = (ImageView) itemView.findViewById(R.id.circle_img);
            circle_title = (TextView) itemView.findViewById(R.id.circle_title);
            circle_time = (TextView) itemView.findViewById(R.id.circle_time);
            circle_img_rv = itemView.findViewById(R.id.circle_img_rv);
            circle_img_1 = itemView.findViewById(R.id.circle_img_1);
            circle_message = (TextView) itemView.findViewById(R.id.circle_message);
            circle_name = (TextView) itemView.findViewById(R.id.circle_name);
            circle_like = itemView.findViewById(R.id.circle_like);
            circle_zan = itemView.findViewById(R.id.circle_zan);
            item_dynamic_zan = itemView.findViewById(R.id.item_dynamic_zan);
            circle_pinglun = itemView.findViewById(R.id.circle_pinglun);
            circle_lianxi = itemView.findViewById(R.id.circle_lianxi);
            circle_fuzhi = itemView.findViewById(R.id.circle_fuzhi);
            dynamic_ll = itemView.findViewById(R.id.dynamic_ll);
            item_dynamic_ll = itemView.findViewById(R.id.item_dynamic_ll);
        }
    }

    //TODO 接口
    public interface LianxiClick {
        void Click(int position);
    }

    public interface PingLun {
        void pingClick(int position);
    }

    public interface FuZhi {
        void FuZhi(int position);
    }

    public interface XiangMu {
        void xm(int position);
    }

    public interface TuPian {
        void tp(int position);
    }
}

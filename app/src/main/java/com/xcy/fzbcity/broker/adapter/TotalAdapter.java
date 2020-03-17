package com.xcy.fzbcity.broker.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.adapter.ImageAdapter;
import com.xcy.fzbcity.all.api.FinalContents;
import com.xcy.fzbcity.all.modle.TotalBean;
import com.xcy.fzbcity.all.modle.TotalZanBean;
import com.xcy.fzbcity.all.service.MyService;
import com.xcy.fzbcity.all.view.EconomicCircleParticularsActivity;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TotalAdapter extends RecyclerView.Adapter<TotalAdapter.ViewHolder> {
    private List<TotalBean.DataBean.RowsBean> list;
    private Context context;

    EPinLun ePinLun;
    EFuZi eFuZi;
    private List<String> arraylist;


    public void seteFuZi(EFuZi eFuZi) {
        this.eFuZi = eFuZi;
    }

    public void setePinLun(EPinLun ePinLun) {
        this.ePinLun = ePinLun;
    }

    public void setList(List<TotalBean.DataBean.RowsBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.broker_item_economic_circle,
                parent, false);
        ViewHolder holder = new ViewHolder(view);
        context = parent.getContext();
        return holder;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {


        if (FinalContents.getCityID().equals(FinalContents.getOldCityId())){
            holder.all_pinglun.setVisibility(View.VISIBLE);
            holder.all_zan.setVisibility(View.VISIBLE);
        }else {
            if(FinalContents.getIsMyTotal().equals("2")){
                holder.all_pinglun.setVisibility(View.VISIBLE);
                holder.all_zan.setVisibility(View.VISIBLE);
            }else {
                holder.all_pinglun.setVisibility(View.GONE);
                holder.all_zan.setVisibility(View.GONE);
            }

        }

        if (list.get(position).getIsLike().equals("0")) {
            Glide.with(holder.itemView.getContext()).load(R.mipmap.icon_2).into(holder.item_circle_like);
        } else {
            Glide.with(holder.itemView.getContext()).load(R.mipmap.icon_like).into(holder.item_circle_like);
        }
        FinalContents.setTargetId(list.get(position).getId());
        Glide.with(context).load(FinalContents.getImageUrl() + list.get(position).getCreateByPhoto()).into(holder.all_img);
        Log.i("MyCL", "圖片網址：" + list.get(position).getImgUrl());
        Log.i("MyCL", "头像網址：" + list.get(position).getCreateByPhoto());

        if (list.get(position).getImgUrl().equals("")) {
            holder.circle_ll.setVisibility(View.GONE);
        } else {
            holder.circle_ll.setVisibility(View.VISIBLE);
            arraylist = new ArrayList<>();
            String[] a  = list.get(position).getImgUrl().split("[|]");
            for (int i = 0; i < a.length; i++){
                arraylist.add(a[i]);
            }
            GridLayoutManager layoutManager = new GridLayoutManager(context,3);
            layoutManager.setOrientation(GridLayoutManager.VERTICAL);
            holder.all_img_rv.setLayoutManager(layoutManager);
            ImageAdapter imageAdapter = new ImageAdapter(arraylist);
            imageAdapter.setImageUrl(list.get(position).getImgUrl());
            holder.all_img_rv.setAdapter(imageAdapter);
            imageAdapter.notifyDataSetChanged();
        }




        if(list.get(position).getContent().equals("")){
            holder.item_economic_rl.setVisibility(View.GONE);
        }else {
            holder.item_economic_rl.setVisibility(View.VISIBLE);
        }
//        Glide.with(context).load("http://39.98.173.250:8080" + list.get(position).getImgUrl()).into(holder.all_img_rv);

        if(list.get(position).getContent().equals("")){
            holder.all_message.setVisibility(View.GONE);
        }else {
            holder.all_message.setVisibility(View.VISIBLE);
        }

        holder.all_title.setText(list.get(position).getCreateByName());
        holder.all_message.setText(list.get(position).getContent());
        holder.all_time.setText(list.get(position).getCreateDate());



        if (list.get(position).getLikeNum().equals("")) {
            holder.total_like.setText("0");
        } else {
            holder.total_like.setText("" + list.get(position).getLikeNum());
        }

        holder.all_zan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Retrofit.Builder builder = new Retrofit.Builder();
                builder.baseUrl(FinalContents.getBaseUrl());
                builder.addConverterFactory(GsonConverterFactory.create());
                builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
                Retrofit build = builder.build();
                MyService fzbInterface = build.create(MyService.class);
                Log.i("点赞",list.get(position).getId());
                Log.i("点赞",FinalContents.getUserID());
                Observable<TotalZanBean> likeNum = fzbInterface.getTotalLikeNum(list.get(position).getId(), FinalContents.getUserID(), "1");

                likeNum.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<TotalZanBean>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(TotalZanBean likeNumBean) {

                                if (likeNumBean.getData().getStatus().equals("0")) {
                                    Glide.with(holder.itemView.getContext()).load(R.mipmap.icon_like).into(holder.item_circle_like);
                                } else if (likeNumBean.getData().getStatus().equals("1")) {
                                    Glide.with(holder.itemView.getContext()).load(R.mipmap.icon_2).into(holder.item_circle_like);
                                }
                                holder.total_like.setText(likeNumBean.getData().getLikeNum() + "");

                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.i("点赞", "点赞错误信息：" + e.getMessage());
                            }

                            @Override
                            public void onComplete() {

                            }
                        });
            }
        });

        holder.all_pinglun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FinalContents.setPid(list.get(position).getId());
                ePinLun.pl(position);
            }
        });

        holder.all_fuzhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eFuZi.fz(position);
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (FinalContents.getCityID().equals(FinalContents.getOldCityId())){
                    FinalContents.setEconomicCircleID(list.get(position).getId());
                    Log.i("MyCL", "ID：" + list.get(position).getId());
                    Intent intent = new Intent(holder.itemView.getContext(), EconomicCircleParticularsActivity.class);
                    holder.itemView.getContext().startActivity(intent);
                }else {
                    if(FinalContents.getIsMyTotal().equals("2")){
                        FinalContents.setEconomicCircleID(list.get(position).getId());
                        Log.i("MyCL", "ID：" + list.get(position).getId());
                        Intent intent = new Intent(holder.itemView.getContext(), EconomicCircleParticularsActivity.class);
                        holder.itemView.getContext().startActivity(intent);
                    }
                }

            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView all_img;
        ImageView item_circle_like;
        TextView all_title;
        TextView all_time;
        RecyclerView all_img_rv;
        TextView all_message;
        //        TextView all_all;
        TextView total_like;
        LinearLayout all_fuzhi;
        LinearLayout all_pinglun;
        LinearLayout all_zan;
        LinearLayout circle_ll;

        RelativeLayout item_economic_rl;


        public ViewHolder(View itemView) {
            super(itemView);
            //注意这里可能需要import com.example.lenovo.myrecyclerview.R; 才能使用R.id
            all_img = (ImageView) itemView.findViewById(R.id.all_img);
            all_title = (TextView) itemView.findViewById(R.id.all_title);
            all_time = (TextView) itemView.findViewById(R.id.all_time);
            all_img_rv = itemView.findViewById(R.id.all_img_rv);
            all_message = (TextView) itemView.findViewById(R.id.all_message);
//            all_all =(TextView) itemView.findViewById(R.id.all_all);
            all_fuzhi = itemView.findViewById(R.id.all_fuzhi);
            all_pinglun = itemView.findViewById(R.id.all_pinglun);
            all_zan = itemView.findViewById(R.id.all_zan);
            total_like = itemView.findViewById(R.id.total_like);
            item_circle_like = itemView.findViewById(R.id.item_circle_like);
            circle_ll = itemView.findViewById(R.id.circle_ll);
            item_economic_rl = itemView.findViewById(R.id.item_economic_rl);

        }
    }

    public interface EPinLun {
        void pl(int position);
    }

    public interface EFuZi {
        void fz(int position);
    }


}

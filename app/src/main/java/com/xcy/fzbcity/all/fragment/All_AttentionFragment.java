package com.xcy.fzbcity.all.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.api.FinalContents;
import com.xcy.fzbcity.all.modle.SpellingDataBean;
import com.xcy.fzbcity.all.service.MyService;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class All_AttentionFragment extends AllFragment {

  private View view;
  private WebView attention_content;

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    view = inflater.inflate(R.layout.shopping_guide_fragment_attention, null);
    attention_content = view.findViewById(R.id.attention_content);
    initData();
    return view;
  }

  private void initData(){
    Retrofit.Builder builder = new Retrofit.Builder();
    builder.baseUrl(FinalContents.getBaseUrl());
    builder.addConverterFactory(GsonConverterFactory.create());
    builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
    Retrofit build = builder.build();
    MyService fzbInterface = build.create(MyService.class);
    Observable<SpellingDataBean> spellingDataBean = fzbInterface.getSpellingDataBean(FinalContents.getRouteTimeId());
    spellingDataBean.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Observer<SpellingDataBean>() {
              @Override
              public void onSubscribe(Disposable d) {

              }

              @Override
              public void onNext(SpellingDataBean spellingDataBean) {
                attention_content.loadDataWithBaseURL(null, "<head><meta name='viewport' content='width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0,user-scalable=no'> <style>img{max-width: 100%; width:auto; height:auto;}</style></head>"+spellingDataBean.getData().getMatter().getMatterComment(), "text/html", "utf-8", null);
              }

              @Override
              public void onError(Throwable e) {
                Log.i("打印拼团详情数据", "错误" + e);
              }

              @Override
              public void onComplete() {

              }
            });
  }

}
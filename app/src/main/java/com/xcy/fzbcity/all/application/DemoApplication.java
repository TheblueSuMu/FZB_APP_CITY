package com.xcy.fzbcity.all.application;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import androidx.multidex.MultiDex;

import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;
import com.mob.MobSDK;
import com.tencent.bugly.crashreport.CrashReport;
import com.xcy.fzbcity.all.api.FinalContents;
import com.xcy.fzbcity.all.api.XCrashHandlerUtils;
import com.xcy.fzbcity.all.fragment.ComprehensiveFragment;
import com.xcy.fzbcity.all.fragment.CountryFragment;
import com.xcy.fzbcity.all.fragment.HomeFragment;
import com.xcy.fzbcity.all.fragment.HouseTypeFragment;
import com.xcy.fzbcity.all.fragment.MessageFragment;
import com.xcy.fzbcity.all.fragment.PriceFragment;
import com.xcy.fzbcity.all.fragment.ScreeningFragment;
import com.xcy.fzbcity.all.modle.CityBean;
import com.xcy.fzbcity.all.modle.HotBean;
import com.xcy.fzbcity.all.modle.ImgData;
import com.xcy.fzbcity.all.modle.MessageBean2;
import com.xcy.fzbcity.all.modle.NationBean;
import com.xcy.fzbcity.all.modle.NewsBean;
import com.xcy.fzbcity.broker.fragment.DFragment;
import com.xcy.fzbcity.broker.fragment.EFragment;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.lljjcoder.style.citylist.utils.CityListLoader;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

public class DemoApplication extends Application {

    public DFragment dFragment;
    public EFragment eFragment;
    public MessageFragment message_fragment;
    public HomeFragment home_fragment;

    public List<NewsBean.DataBean> newsList;
    public List<ImgData.DataBean> imglist;
    public List<MessageBean2.DataBean.RowsBean> messagelist;
    public List<CityBean.DataBean> citylist;
    public List<HotBean.DataBean.RowsBean> hotlist;

    public ComprehensiveFragment comprehensiveFragment;
    public CountryFragment countryFragment;
    public PriceFragment priceFragment;
    public HouseTypeFragment houseTypeFragment;
    public ScreeningFragment screeningFragment;
    private SharedPreferences.Editor editor;
    private SharedPreferences pref;
    public List<NationBean.DataBean> nationlist;
    public List<ImgData.DataBean> imagelist;

    //在自己的Application中添加如下代码
    private RefWatcher refWatcher;

    @Override
    public void onCreate() {
        super.onCreate();

        /**
         * 预先加载一级列表所有城市的数据
         */
        CityListLoader.getInstance().loadCityData(this);

        /**
         * 预先加载三级列表显示省市区的数据
         */
        CityListLoader.getInstance().loadProData(this);

        refWatcher = LeakCanary.install(this);

        editor = getSharedPreferences("data", MODE_PRIVATE).edit();
        pref = getSharedPreferences("data", MODE_PRIVATE);
        //在使用SDK各组件之前初始化context信息，传入ApplicationContext
        SDKInitializer.initialize(this);
        //自4.3.0起，百度地图SDK所有接口均支持百度坐标和国测局坐标，用此方法设置您使用的坐标类型.
        //包括BD09LL和GCJ02两种坐标，默认是BD09LL坐标。
        SDKInitializer.setCoordType(CoordType.BD09LL);

        SDKInitializer.initialize(getApplicationContext());

        Context context = getApplicationContext();
// 获取当前包名
        String packageName = context.getPackageName();
// 获取当前进程名
        String processName = getProcessName(android.os.Process.myPid());
// 设置是否为上报进程
        CrashReport.UserStrategy strategy = new CrashReport.UserStrategy(context);
        strategy.setUploadProcess(processName == null || processName.equals(packageName));
        // 初始化Bugly
        CrashReport.initCrashReport(context, "426b896238", false, strategy);

        XCrashHandlerUtils.getInstance().init(this);
//        CrashHandler crashHandler = new CrashHandler();
//        crashHandler.init(this);
        MobSDK.init(this);

        MultiDex.install(this);

        setCitylist(new ArrayList<CityBean.DataBean>());
        setdFragment(new DFragment());
        seteFragment(new EFragment());
        setHome_fragment(new HomeFragment());
        setHotlist(new ArrayList<HotBean.DataBean.RowsBean>());
        setImglist(new ArrayList<ImgData.DataBean>());
        setMessage_fragment(new MessageFragment());
        setMessagelist(new ArrayList<MessageBean2.DataBean.RowsBean>());
        setNewsList(new ArrayList<NewsBean.DataBean>());

        setComprehensiveFragment(new ComprehensiveFragment());
        setCountryFragment(new CountryFragment(FinalContents.getProjectType()));
        setPriceFragment(new PriceFragment());
        setHouseTypeFragment(new HouseTypeFragment());
        setScreeningFragment(new ScreeningFragment());
        setNationlist(new ArrayList<NationBean.DataBean>());
        setImagelist(new ArrayList<ImgData.DataBean>());
    }

    //在自己的Application中添加如下代码
    public static RefWatcher getRefWatcher(Context context) {
        DemoApplication application = (DemoApplication) context.getApplicationContext();
        return application.refWatcher;
    }

    /**
     * 获取进程号对应的进程名
     *
     * @param pid 进程号
     * @return 进程名
     */
    private static String getProcessName(int pid) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("/proc/" + pid + "/cmdline"));
            String processName = reader.readLine();
            if (!TextUtils.isEmpty(processName)) {
                processName = processName.trim();
            }
            return processName;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
        return null;
    }

    public ComprehensiveFragment getComprehensiveFragment() {
        return comprehensiveFragment;
    }

    public void setComprehensiveFragment(ComprehensiveFragment comprehensiveFragment) {
        this.comprehensiveFragment = comprehensiveFragment;
    }

    public CountryFragment getCountryFragment() {
        return countryFragment;
    }

    public void setCountryFragment(CountryFragment countryFragment) {
        this.countryFragment = countryFragment;
    }

    public PriceFragment getPriceFragment() {
        return priceFragment;
    }

    public void setPriceFragment(PriceFragment priceFragment) {
        this.priceFragment = priceFragment;
    }

    public HouseTypeFragment getHouseTypeFragment() {
        return houseTypeFragment;
    }

    public void setHouseTypeFragment(HouseTypeFragment houseTypeFragment) {
        this.houseTypeFragment = houseTypeFragment;
    }

    public ScreeningFragment getScreeningFragment() {
        return screeningFragment;
    }

    public void setScreeningFragment(ScreeningFragment screeningFragment) {
        this.screeningFragment = screeningFragment;
    }

    public List<NationBean.DataBean> getNationlist() {
        return nationlist;
    }

    public void setNationlist(List<NationBean.DataBean> nationlist) {
        this.nationlist = nationlist;
    }

    public List<ImgData.DataBean> getImagelist() {
        return imagelist;
    }

    public void setImagelist(List<ImgData.DataBean> imagelist) {
        this.imagelist = imagelist;
    }

    public DFragment getdFragment() {
        return dFragment;
    }

    public void setdFragment(DFragment dFragment) {
        this.dFragment = dFragment;
    }

    public EFragment geteFragment() {
        return eFragment;
    }

    public void seteFragment(EFragment eFragment) {
        this.eFragment = eFragment;
    }

    public MessageFragment getMessage_fragment() {
        return message_fragment;
    }

    public void setMessage_fragment(MessageFragment message_fragment) {
        this.message_fragment = message_fragment;
    }

    public HomeFragment getHome_fragment() {
        return home_fragment;
    }

    public void setHome_fragment(HomeFragment home_fragment) {
        this.home_fragment = home_fragment;
    }

    public List<NewsBean.DataBean> getNewsList() {
        return newsList;
    }

    public void setNewsList(List<NewsBean.DataBean> newsList) {
        this.newsList = newsList;
    }

    public List<ImgData.DataBean> getImglist() {
        return imglist;
    }

    public void setImglist(List<ImgData.DataBean> imglist) {
        this.imglist = imglist;
    }

    public List<MessageBean2.DataBean.RowsBean> getMessagelist() {
        return messagelist;
    }

    public void setMessagelist(List<MessageBean2.DataBean.RowsBean> messagelist) {
        this.messagelist = messagelist;
    }

    public List<CityBean.DataBean> getCitylist() {
        return citylist;
    }

    public void setCitylist(List<CityBean.DataBean> citylist) {
        this.citylist = citylist;
    }

    public List<HotBean.DataBean.RowsBean> getHotlist() {
        return hotlist;
    }

    public void setHotlist(List<HotBean.DataBean.RowsBean> hotlist) {
        this.hotlist = hotlist;
    }
}
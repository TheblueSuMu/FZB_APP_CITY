package com.xcy.fzbcity.all.api;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.amap.api.maps.model.LatLng;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.modle.ProjectDetailsBean;
import com.xcy.fzbcity.all.modle.ShareLogSaveBean;
import com.xcy.fzbcity.all.service.MyService;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.onekeyshare.OnekeyShare;
import cn.sharesdk.wechat.favorite.WechatFavorite;
import cn.sharesdk.wechat.friends.Wechat;
import cn.sharesdk.wechat.moments.WechatMoments;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 创建：Sun
 * 时间：2019/7/6
 */
public class FinalContents {
    public static final String SP_FILE_NAME = "fzb";
    public static final String SP_LOGIN_NAME = "fzbLogin";

    static String ImageUrl = "http://39.98.237.23:8080";
//        static String ImageUrl = "http://39.98.237.23:8080";
//        static String ImageUrl = "http://192.168.0.121:8080";
//    static String ImageUrl = "http://39.98.173.250:8081";
//    static String ImageUrl = "http://test.fangzuobiao.com:88";
//    static String ImageUrl = "http://yanshi.fangzuobiao.com:80";
//    static String ImageUrl = "http://sojo.fangzuobiao.com:88";
//    static String ImageUrl = "http://39.100.13.183:8080";       //      TODO    IP版
//    static String ImageUrl = "http://city1.fangzuobiao.com:88";       //      TODO     域名版
//    static String ImageUrl = "http://city.fangzuobiao.com:88";       //      TODO     域名版
//    static String ImageUrl = "http://39.98.237.23:8082";       //      TODO     远程版
//    static String ImageUrl = "http://39.98.209.175:8181";       //      TODO     城市版

//    static String ImageUrl = "http://39.100.13.183:8080";
//    static String ImageUrl = "http://39.98.224.67:8080";
//    static String ImageUrl = "http://admin.fangzuobiao.com:88";

        static String AdminUrl = "http://39.98.237.23:8080";
    //    static String AdminUrl = "http://admin.fangzuobiao.com:88";
//    static String AdminUrl = "http://city1.fangzuobiao.com:88";
//    static String AdminUrl = "http://city.fangzuobiao.com:88";
//    static String AdminUrl = "http://yanshi.fangzuobiao.com:88";
//    static String AdminUrl = "http://sojo.fangzuobiao.com:88";
//    static String AdminUrl = "http://test.fangzuobiao.com:88";
//    static String AdminUrl = "http://39.98.209.175:8181";

    public static String getAdminUrl() {
        return AdminUrl;
    }

    public static void setAdminUrl(String adminUrl) {
        AdminUrl = adminUrl;
    }

    public static String getImageUrl() {
        return ImageUrl;
    }

    public static void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }

        static String BaseUrl = "http://39.98.237.23:8080/fangfang/app/v2/";
//        static String BaseUrl = "http://39.98.237.23:8080/fangfang/app/v1/";
//        static String BaseUrl = "http://39.98.209.175:8181/fangfang/app/v1/";
//        static String BaseUrl = "http://39.98.173.250:8082/fangfang/app/v1/";
//        static String BaseUrl = "http://192.168.0.121:8080/fangfang/app/v1/";
//    static String BaseUrl = "http://39.98.173.250:8081/fangfang/app/v1/";
//    static String BaseUrl = "http://test.fangzuobiao.com:88/fangfang/app/v1/";
//    static String BaseUrl = "http://yanshi.fangzuobiao.com:80/fangfang/app/v1/";
//    static String BaseUrl = "http://sojo.fangzuobiao.com:88/fangfang/app/v1/";
//    static String BaseUrl = "http://39.100.13.183:8080/fangfang/app/v1/";      //      TODO    IP版
//    static String BaseUrl = "http://city1.fangzuobiao.com:88/fangfang/app/v1/";      //      TODO    域名版
//    static String BaseUrl = "http://city.fangzuobiao.com:88/fangfang/app/v1/";      //      TODO    域名版
//    static String BaseUrl = "http://39.98.237.23:8082/fangfang/app/v1/";      //      TODO    远程版
//    static String BaseUrl = "http://39.98.224.67:8080/fangfang/app/v1/";
//    static String BaseUrl = "http://admin.fangzuobiao.com:88/fangfang/app/v1/";
//    static String BaseUrl = "http://39.98.209.175:8181/fangfang/app/v1/";      //      TODO    城市版

    static String userID = "";
    static String projectID = "";
    static String cityID = "";
    static String preparationId = "";
    static String EconomicCircleID = "";
    static String TargetId = "";
    static String Pid = "";
    static String ProjectType = "";
    static String CustomerID = "";
    static String ClientName = "";
    static String ProjectSearchID = "";
    static String ProjectName = "";
    static boolean checked = false;
    static boolean checked2 = false;
    static Double o = 0.0;
    static Double d = 0.0;
    static Double o1 = 0.0;
    static Double d1 = 0.0;
    static String NUM = "0";
    static String ComprehensiveSorting = "";
    static String projectLabel = "";
    static String nation;
    static String projectPriceStart = "";
    static String projectPriceEnd = "";
    static String apartment = "";
    static String areaSection = "";
    static String ffProjectTrait = "";
    static String procuctType = "";
    static String fitmentState = "";
    static String MessageIssueNum = "0";
    static String JJ = "";
    static int numS = 0;
    static String commissionId = "";
    static String guideRuleId = "";
    static String ratioId = "";
    static String ownerId = "";
    static String levelId = "";
    static String agentId = "";
    static String identity = "";
    static String routeTimeId = "";
    static String landingId = "";


    static String StoreList = "1";
    static String StoreId = "";
    static String CityName = "";
    static String MyAddType = "";
    static String Addtype1 = "";
    static String Addtype2 = "";
    static String YiChang = "";
    static String CompanyManageId = "";
    static String StoreManageId = "";

    static String BorkerChange = "";
    static String StoreChange = "";
    static String Image1 = "";
    static String Image2 = "";

    static String project = "";

    static String zhuanyuan = "";

    static String quanceng = "";

    static String endStart = "";

    static String delete = "";

    static String tiaozhuang = "";

    static String zyHome = "";

    static String myClientType = "";

    static String tiaodan = "";

    static String ChengJao = "";

    static String DengLu = "";

    static String ZhuanAn = "";

    static String UserName = "";

    static String XiuGai = "";

    static String XiaoShou = "";

    static String XSName = "";

    static String XSTeamName = "";

    static String ParentName = "";

    static String InforId = "";

    static String YongJin = "";

    static String DaoGou = "";

    static String JuJue = "";

    static String OwnerId001 = "";
    static String OwnerId002 = "";

    static String IPhone = "";

    static String MySelf = "";
    static String CompanyId = "";

    static String Details = "";

    static String JJrID = "";

    static int Index = -1;
    static String ManageFlag = "";

    static String TalkToolId = "";

    static String NewID = "";

    static String DengDao = "";

    static String VersionNumBer = "1.0";

    static String FragmentSS = "0";

    static String FragmentSSS = "0";

    static String ClientPhone = "";

    static String IfCity = "";

    static String IfCityType = "";

    static String CityIs = "";

    static String OldCityId = "";

    static String NationalCityName = "";

    static LatLng MylatLng = null;

    static String NationalCityNameSearch = "";

    static String IfSignIn = "";

    static String SignInName = "";

    static String OldCityName = "";

    static String isMyTotal = "";

    public static String getIsMyTotal() {
        return isMyTotal;
    }

    public static void setIsMyTotal(String isMyTotal) {
        FinalContents.isMyTotal = isMyTotal;
    }

    public static String getOldCityName() {
        return OldCityName;
    }

    public static void setOldCityName(String oldCityName) {
        OldCityName = oldCityName;
    }


    public static String getSignInName() {
        return SignInName;
    }

    public static void setSignInName(String signInName) {
        SignInName = signInName;
    }

    public static String getIfSignIn() {
        return IfSignIn;
    }

    public static void setIfSignIn(String ifSignIn) {
        IfSignIn = ifSignIn;
    }

    static List<ProjectDetailsBean.DataBean.ProjectListVoBean.FfAttacheListBean> ffAttacheList;

    public static List<ProjectDetailsBean.DataBean.ProjectListVoBean.FfAttacheListBean> getFfAttacheList() {
        return ffAttacheList;
    }

    public static void setFfAttacheList(List<ProjectDetailsBean.DataBean.ProjectListVoBean.FfAttacheListBean> ffAttacheList) {
        FinalContents.ffAttacheList = ffAttacheList;
    }

    public static String getNationalCityNameSearch() {
        return NationalCityNameSearch;
    }

    public static void setNationalCityNameSearch(String nationalCityNameSearch) {
        NationalCityNameSearch = nationalCityNameSearch;
    }

    public static LatLng getMylatLng() {
        return MylatLng;
    }

    public static void setMylatLng(LatLng mylatLng) {
        MylatLng = mylatLng;
    }

    public static String getNationalCityName() {
        return NationalCityName;
    }

    public static void setNationalCityName(String nationalCityName) {
        NationalCityName = nationalCityName;
    }

    public static String getOldCityId() {
        return OldCityId;
    }

    public static void setOldCityId(String oldCityId) {
        OldCityId = oldCityId;
    }

    public static String getCityIs() {
        return CityIs;
    }

    public static void setCityIs(String cityIs) {
        CityIs = cityIs;
    }

    public static String getIfCityType() {
        return IfCityType;
    }

    public static void setIfCityType(String ifCityType) {
        IfCityType = ifCityType;
    }

    public static String getIfCity() {
        return IfCity;
    }

    public static void setIfCity(String ifCity) {
        IfCity = ifCity;
    }

    public static String getClientPhone() {
        return ClientPhone;
    }

    public static void setClientPhone(String clientPhone) {
        ClientPhone = clientPhone;
    }

    public static String getFragmentSSS() {
        return FragmentSSS;
    }

    public static void setFragmentSSS(String fragmentSSS) {
        FragmentSSS = fragmentSSS;
    }

    public static String getFragmentSS() {
        return FragmentSS;
    }

    public static void setFragmentSS(String fragmentSS) {
        FragmentSS = fragmentSS;
    }

    static boolean Hidden = true;

    static String Clean = "";

    public static String getClean() {
        return Clean;
    }

    public static void setClean(String clean) {
        Clean = clean;
    }

    public static boolean isHidden() {
        return Hidden;
    }

    public static void setHidden(boolean hidden) {
        Hidden = hidden;
    }

    public static String getVersionNumBer() {
        return VersionNumBer;
    }

    public static void setVersionNumBer(String versionNumBer) {
        VersionNumBer = versionNumBer;
    }

    static boolean luo = true;

    public static boolean isLuo() {
        return luo;
    }

    public static void setLuo(boolean luo) {
        FinalContents.luo = luo;
    }

    public static String getDengDao() {
        return DengDao;
    }

    public static void setDengDao(String dengDao) {
        DengDao = dengDao;
    }

    public static String getNewID() {
        return NewID;
    }

    public static void setNewID(String newID) {
        NewID = newID;
    }

    public static String getTalkToolId() {
        return TalkToolId;
    }

    public static void setTalkToolId(String talkToolId) {
        TalkToolId = talkToolId;
    }

    public static String getManageFlag() {
        return ManageFlag;
    }

    public static void setManageFlag(String manageFlag) {
        ManageFlag = manageFlag;
    }

    public static int getIndex() {
        return Index;
    }

    public static void setIndex(int index) {
        Index = index;
    }

    public static String getJJrID() {
        return JJrID;
    }

    public static void setJJrID(String JJrID) {
        FinalContents.JJrID = JJrID;
    }

    public static String getDetails() {
        return Details;
    }

    public static void setDetails(String details) {
        Details = details;
    }

    public static String getCompanyId() {
        return CompanyId;
    }

    public static void setCompanyId(String companyId) {
        CompanyId = companyId;
    }

    public static String getMySelf() {
        return MySelf;
    }

    public static void setMySelf(String mySelf) {
        MySelf = mySelf;
    }

    public static String getIPhone() {
        return IPhone;
    }

    public static void setIPhone(String IPhone) {
        FinalContents.IPhone = IPhone;
    }

    public static String getOwnerId001() {
        return OwnerId001;
    }

    public static void setOwnerId001(String ownerId001) {
        OwnerId001 = ownerId001;
    }

    public static String getOwnerId002() {
        return OwnerId002;
    }

    public static void setOwnerId002(String ownerId002) {
        OwnerId002 = ownerId002;
    }

    public static String getJuJue() {
        return JuJue;
    }

    public static void setJuJue(String juJue) {
        JuJue = juJue;
    }

    public static String getDaoGou() {
        return DaoGou;
    }

    public static void setDaoGou(String daoGou) {
        DaoGou = daoGou;
    }

    public static String getYongJin() {
        return YongJin;
    }

    public static void setYongJin(String yongJin) {
        YongJin = yongJin;
    }

    public static String getInforId() {
        return InforId;
    }

    public static void setInforId(String inforId) {
        InforId = inforId;
    }

    public static String getParentName() {
        return ParentName;
    }

    public static void setParentName(String parentName) {
        ParentName = parentName;
    }

    public static String getXSName() {
        return XSName;
    }

    public static void setXSName(String XSName) {
        FinalContents.XSName = XSName;
    }

    public static String getXSTeamName() {
        return XSTeamName;
    }

    public static void setXSTeamName(String XSTeamName) {
        FinalContents.XSTeamName = XSTeamName;
    }

    public static String getXiaoShou() {
        return XiaoShou;
    }

    public static void setXiaoShou(String xiaoShou) {
        XiaoShou = xiaoShou;
    }

    public static String getXiuGai() {
        return XiuGai;
    }

    public static void setXiuGai(String xiuGai) {
        XiuGai = xiuGai;
    }

    public static String getUserName() {
        return UserName;
    }

    public static void setUserName(String userName) {
        UserName = userName;
    }

    public static String getZhuanAn() {
        return ZhuanAn;
    }

    public static void setZhuanAn(String zhuanAn) {
        ZhuanAn = zhuanAn;
    }

    public static String getDengLu() {
        return DengLu;
    }

    public static void setDengLu(String dengLu) {
        DengLu = dengLu;
    }

    public static String getChengJao() {
        return ChengJao;
    }

    public static void setChengJao(String chengJao) {
        ChengJao = chengJao;
    }

    public static String getTiaodan() {
        return tiaodan;
    }

    public static void setTiaodan(String tiaodan) {
        FinalContents.tiaodan = tiaodan;
    }

    public static String getMyClientType() {
        return myClientType;
    }

    public static void setMyClientType(String myClientType) {
        FinalContents.myClientType = myClientType;
    }

    public static String getZyHome() {
        return zyHome;
    }

    public static void setZyHome(String zyHome) {
        FinalContents.zyHome = zyHome;
    }

    public static String getTiaozhuang() {
        return tiaozhuang;
    }

    public static void setTiaozhuang(String tiaozhuang) {
        FinalContents.tiaozhuang = tiaozhuang;
    }

    public static String getDelete() {
        return delete;
    }

    public static void setDelete(String delete) {
        FinalContents.delete = delete;
    }

    public static String getEndStart() {
        return endStart;
    }

    public static void setEndStart(String endStart) {
        FinalContents.endStart = endStart;
    }

    public static String getQuanceng() {
        return quanceng;
    }

    public static void setQuanceng(String quanceng) {
        FinalContents.quanceng = quanceng;
    }

    public static String getZhuanyuan() {
        return zhuanyuan;
    }

    public static void setZhuanyuan(String zhuanyuan) {
        FinalContents.zhuanyuan = zhuanyuan;
    }

    public static String getProject() {
        return project;
    }

    public static void setProject(String project) {
        FinalContents.project = project;
    }

    public static String getImage1() {
        return Image1;
    }

    public static void setImage1(String image1) {
        Image1 = image1;
    }

    public static String getImage2() {
        return Image2;
    }

    public static void setImage2(String image2) {
        Image2 = image2;
    }

    public static String getStoreChange() {
        return StoreChange;
    }

    public static void setStoreChange(String storeChange) {
        StoreChange = storeChange;
    }

    public static String getBorkerChange() {
        return BorkerChange;
    }

    public static void setBorkerChange(String borkerChange) {
        BorkerChange = borkerChange;
    }

    public static String getYiChang() {
        return YiChang;
    }

    public static void setYiChang(String yiChang) {
        YiChang = yiChang;
    }

    public static String getCompanyManageId() {
        return CompanyManageId;
    }

    public static void setCompanyManageId(String companyManageId) {
        CompanyManageId = companyManageId;
    }

    public static String getStoreManageId() {
        return StoreManageId;
    }

    public static void setStoreManageId(String storeManageId) {
        StoreManageId = storeManageId;
    }

    public static String getAddtype1() {
        return Addtype1;
    }

    public static void setAddtype1(String addtype1) {
        Addtype1 = addtype1;
    }

    public static String getAddtype2() {
        return Addtype2;
    }

    public static void setAddtype2(String addtype2) {
        Addtype2 = addtype2;
    }

    public static String getMyAddType() {
        return MyAddType;
    }

    public static void setMyAddType(String myAddType) {
        MyAddType = myAddType;
    }

    public static String getCityName() {
        return CityName;
    }

    public static void setCityName(String cityName) {
        CityName = cityName;
    }


    public static String getStoreId() {
        return StoreId;
    }

    public static void setStoreId(String storeId) {
        StoreId = storeId;
    }

    public static String getStoreList() {
        return StoreList;
    }

    public static void setStoreList(String storeList) {
        StoreList = storeList;
    }

    public static String getLandingId() {
        return landingId;
    }

    public static void setLandingId(String landingId) {
        FinalContents.landingId = landingId;
    }

    public static String getRouteTimeId() {
        return routeTimeId;
    }

    public static void setRouteTimeId(String routeTimeId) {
        FinalContents.routeTimeId = routeTimeId;
    }

    public static String getIdentity() {
        return identity;
    }

    public static void setIdentity(String identity) {
        FinalContents.identity = identity;
    }

    public static String getAgentId() {
        return agentId;
    }

    public static void setAgentId(String agentId) {
        FinalContents.agentId = agentId;
    }

    public static String getRatioId() {
        return ratioId;
    }

    public static void setRatioId(String ratioId) {
        FinalContents.ratioId = ratioId;
    }

    public static String getOwnerId() {
        return ownerId;
    }

    public static void setOwnerId(String ownerId) {
        FinalContents.ownerId = ownerId;
    }

    public static String getLevelId() {
        return levelId;
    }

    public static void setLevelId(String levelId) {
        FinalContents.levelId = levelId;
    }

    public static String getGuideRuleId() {
        return guideRuleId;
    }

    public static void setGuideRuleId(String guideRuleId) {
        FinalContents.guideRuleId = guideRuleId;
    }

    public static String getCommissionId() {
        return commissionId;
    }

    public static void setCommissionId(String commissionId) {
        FinalContents.commissionId = commissionId;
    }

    public static int getNumS() {
        return numS;
    }

    public static void setNumS(int numS) {
        FinalContents.numS = numS;
    }

    public static String getJJ() {
        return JJ;
    }

    public static void setJJ(String JJ) {
        FinalContents.JJ = JJ;
    }

    static String Status = "";

    public static String getStatus() {
        return Status;
    }

    public static void setStatus(String status) {
        Status = status;
    }

    public static String getMessageIssueNum() {
        return MessageIssueNum;
    }

    public static void setMessageIssueNum(String messageIssueNum) {
        MessageIssueNum = messageIssueNum;
    }

    public static String getAreaSection() {
        return areaSection;
    }

    public static void setAreaSection(String areaSection) {
        FinalContents.areaSection = areaSection;
    }

    public static String getFfProjectTrait() {
        return ffProjectTrait;
    }

    public static void setFfProjectTrait(String ffProjectTrait) {
        FinalContents.ffProjectTrait = ffProjectTrait;
    }

    public static String getProcuctType() {
        return procuctType;
    }

    public static void setProcuctType(String procuctType) {
        FinalContents.procuctType = procuctType;
    }

    public static String getFitmentState() {
        return fitmentState;
    }

    public static void setFitmentState(String fitmentState) {
        FinalContents.fitmentState = fitmentState;
    }

    public static String getApartment() {
        return apartment;
    }

    public static void setApartment(String apartment) {
        FinalContents.apartment = apartment;
    }

    public static String getProjectPriceStart() {
        return projectPriceStart;
    }

    public static void setProjectPriceStart(String projectPriceStart) {
        FinalContents.projectPriceStart = projectPriceStart;
    }

    public static String getProjectPriceEnd() {
        return projectPriceEnd;
    }

    public static void setProjectPriceEnd(String projectPriceEnd) {
        FinalContents.projectPriceEnd = projectPriceEnd;
    }

    public static String getNation() {
        return nation;
    }

    public static void setNation(String nation) {
        FinalContents.nation = nation;
    }

    public static String getProjectLabel() {
        return projectLabel;
    }

    public static void setProjectLabel(String projectLabel) {
        FinalContents.projectLabel = projectLabel;
    }

    public static String getComprehensiveSorting() {
        return ComprehensiveSorting;
    }

    public static void setComprehensiveSorting(String comprehensiveSorting) {
        ComprehensiveSorting = comprehensiveSorting;
    }

    public static String getNUM() {
        return NUM;
    }

    public static void setNUM(String NUM) {
        FinalContents.NUM = NUM;
    }


    public static Double getO1() {
        return o1;
    }

    public static void setO1(Double o1) {
        FinalContents.o1 = o1;
    }

    public static Double getD1() {
        return d1;
    }

    public static void setD1(Double d1) {
        FinalContents.d1 = d1;
    }

    public static Double getO() {
        return o;
    }

    public static void setO(Double o) {
        FinalContents.o = o;
    }

    public static Double getD() {
        return d;
    }

    public static void setD(Double d) {
        FinalContents.d = d;
    }

    static String IFSP = "0";

    public static String getIFSP() {
        return IFSP;
    }

    public static void setIFSP(String IFSP) {
        FinalContents.IFSP = IFSP;
    }


    public static boolean isChecked2() {
        return checked2;
    }

    public static void setChecked2(boolean checked2) {
        FinalContents.checked2 = checked2;
    }

    public static boolean isChecked() {
        return checked;
    }

    public static void setChecked(boolean checked) {
        FinalContents.checked = checked;
    }

    public static String getProjectSearchID() {
        return ProjectSearchID;
    }

    public static void setProjectSearchID(String projectSearchID) {
        ProjectSearchID = projectSearchID;
    }

    public static String getProjectName() {
        return ProjectName;
    }

    public static void setProjectName(String projectName) {
        ProjectName = projectName;
    }

    public static String getClientName() {
        return ClientName;
    }

    public static void setClientName(String clientName) {
        ClientName = clientName;
    }

    public static String getCustomerID() {
        return CustomerID;
    }

    public static void setCustomerID(String customerID) {
        CustomerID = customerID;
    }

    public static String getProjectType() {
        return ProjectType;
    }

    public static void setProjectType(String projectType) {
        ProjectType = projectType;
    }


    public static String getPid() {
        return Pid;
    }

    public static void setPid(String pid) {
        Pid = pid;
    }

    public static String getTargetId() {
        return TargetId;
    }

    public static void setTargetId(String targetId) {
        TargetId = targetId;
    }

    public static String getEconomicCircleID() {
        return EconomicCircleID;
    }

    public static void setEconomicCircleID(String economicCircleID) {
        EconomicCircleID = economicCircleID;
    }

    public static String getPreparationId() {
        return preparationId;
    }

    public static void setPreparationId(String preparationId) {
        FinalContents.preparationId = preparationId;
    }

    public static String getBaseUrl() {
        return BaseUrl;
    }

    public static void setBaseUrl(String baseUrl) {
        BaseUrl = baseUrl;
    }

    public static String getUserID() {
        return userID;
    }

    public static void setUserID(String userID) {
        FinalContents.userID = userID;
    }

    public static String getProjectID() {
        return projectID;
    }

    public static void setProjectID(String projectID) {
        FinalContents.projectID = projectID;
    }

    public static String getCityID() {
        return cityID;
    }

    public static void setCityID(String cityID) {
        FinalContents.cityID = cityID;
    }

    public static void showShare(String platform,String title, String titleUrl, String text, String imagePath, String url, Context context) {
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();

        oks.setPlatform(platform);
        // title标题，微信、QQ和QQ空间等平台使用
        oks.setTitle(title);
        // titleUrl QQ和QQ空间跳转链接
        oks.setTitleUrl(titleUrl);
        // text是分享文本，所有平台都需要这个字段
        oks.setText(text);
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        if(imagePath != null &&imagePath.contains("/sdcard/")){
            //imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
            Resources res = context.getResources();
            Bitmap bmp = BitmapFactory.decodeResource(res, R.mipmap.logo_garden);
            oks.setImageData(bmp);
        }else if(imagePath != null){
            oks.setImageUrl(imagePath); //网络地址
        }else if (imagePath.equals("") || imagePath == null ){
            oks.setImageUrl(FinalContents.getImageUrl()+"/fangfang/static/common/images/logo.png");
        }
        oks.setCallback(new PlatformActionListener() {
            @Override
            public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                // TODO 分享成功后的操作或者提示
                if (platform.equals(WechatMoments.NAME)) {
                    initShare("微信朋友圈");
                } else if (platform.equals(Wechat.NAME)) {
                    initShare("微信朋友");
                }else if (platform.equals(WechatFavorite.NAME)){
                    initShare("微信收藏");
                }
            }

            @Override
            public void onError(Platform platform, int i, Throwable throwable) {
                // TODO 失败，打印throwable为错误码
            }

            @Override
            public void onCancel(Platform platform, int i) {
                // TODO 分享取消操作
            }
        });
        // url在微信、微博，Facebook等平台中使用
        oks.setUrl(url);
        // 启动分享GUI
        oks.show(context);


    }

    public static void initShare(String shareType){
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<ShareLogSaveBean> clientFragment = fzbInterface.getShareLogSave(shareType,FinalContents.getProjectID(),FinalContents.getUserID());
        clientFragment.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ShareLogSaveBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ShareLogSaveBean shareLogSaveBean) {
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("分享", "分享记录错误信息" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    /**
     * 将URL转化成bitmap形式
     *
     * @param url
     * @return bitmap type
     */
    public final static Bitmap returnBitMap(String url) {
        URL myFileUrl;
        Bitmap bitmap = null;
        try {
            myFileUrl = new URL(url);
            HttpURLConnection conn;
            conn = (HttpURLConnection) myFileUrl.openConnection();
            conn.setDoInput(true);
            conn.connect();
            InputStream is = conn.getInputStream();
            bitmap = BitmapFactory.decodeStream(is);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    /**
     * Bitmap转换成byte[]并且进行压缩,压缩到不大于maxkb
     *
     * @param bitmap
     * @param maxkb
     * @return
     */
    public static byte[] bitmap2Bytes(Bitmap bitmap, int maxkb) {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, output);
        int options = 100;
        while (output.toByteArray().length > maxkb && options != 10) {
            output.reset(); //清空output
            bitmap.compress(Bitmap.CompressFormat.JPEG, options, output);//这里压缩options%，把压缩后的数据存放到output中
            options -= 10;
        }
        return output.toByteArray();
    }
}
package com.xcy.fzbcity.all.service;

import com.xcy.fzbcity.all.adapter.CompaniesBean;
import com.xcy.fzbcity.all.adapter.ProjectsBean;
import com.xcy.fzbcity.all.adapter.StoresBean;
import com.xcy.fzbcity.all.database.AddBrokerBean;
import com.xcy.fzbcity.all.database.AddCompanyBean;
import com.xcy.fzbcity.all.database.AgentDetailsBean;
import com.xcy.fzbcity.all.database.AgentListBean;
import com.xcy.fzbcity.all.database.AppPackageBean;
import com.xcy.fzbcity.all.database.BorkerageDownBean;
import com.xcy.fzbcity.all.database.BorkerageUpBean;
import com.xcy.fzbcity.all.database.BrokerBean;
import com.xcy.fzbcity.all.database.BrokerChangeBean;
import com.xcy.fzbcity.all.database.BrokerSaveBean;
import com.xcy.fzbcity.all.database.BrokersListBean;
import com.xcy.fzbcity.all.database.CaptainBean;
import com.xcy.fzbcity.all.database.ClientCommissionsBean;
import com.xcy.fzbcity.all.database.ColleagueBean;
import com.xcy.fzbcity.all.database.CommissionLevelAdapterAddBean;
import com.xcy.fzbcity.all.database.CommissionLevelDeleteBean;
import com.xcy.fzbcity.all.database.CommissionLevelSelectBean;
import com.xcy.fzbcity.all.database.CommissionListBean;
import com.xcy.fzbcity.all.database.DailyTurnoverBean;
import com.xcy.fzbcity.all.database.DataNumBean;
import com.xcy.fzbcity.all.database.DataStatisticsBean;
import com.xcy.fzbcity.all.database.ExemplaryUserBean;
import com.xcy.fzbcity.all.database.FinanceBean;
import com.xcy.fzbcity.all.database.LikeNumBean;
import com.xcy.fzbcity.all.database.MyDataBean;
import com.xcy.fzbcity.all.database.MyTeamBean;
import com.xcy.fzbcity.all.database.RatioByOwnerIdBean;
import com.xcy.fzbcity.all.database.StoreListBean;
import com.xcy.fzbcity.all.database.TeamCommissionsBean;
import com.xcy.fzbcity.all.database.TeamLeaderLevelBean;
import com.xcy.fzbcity.all.database.TeamMemberBean;
import com.xcy.fzbcity.all.database.TradeAuditBean;
import com.xcy.fzbcity.all.modle.*;
import com.xcy.fzbcity.all.persente.LevelBean;
import com.xcy.fzbcity.project_attache.adapter.MyDataStoreBean;
import com.xcy.fzbcity.shopping_guide.MyClientFragmentBean;

import org.json.JSONObject;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface MyService {

    //http://192.168.79.28:8080/webapi/post?username=itheima&password=123
    //@POST标注当前方法 使用POST请求
    //@Field用来标注post请求参数名
    //@FormUrlEncoded与@Post配合使用，要求对post的参数进行网络编码

    //关于房坐标 版本更新
    @POST("commonSelect/appPackage")
    Observable<AppPackageBean> getAppPackage(@Query("appType") String appType, @Query("appPackage") String appPackage, @Query("appVeriosn") String appVeriosn);

    //顾问/销售用户Id获取用户信息
    @POST("layersSelect/getSysUser")
    Observable<GWDataBean> getGWDataBean(@Query("userId") String userId, @Query("sysUserId") String sysUserId);

    //经纪人根据用户Id获取用户信息
    @POST("commonSelect/getUserInfo")
    Observable<UserMessageBean> getUserMessage(@Query("userId") String userId);

    //圈层端（团队长）用户Id获取用户信息
    @POST("layersSelect/getSysUser")
    Observable<TZBean> getTZBean(@Query("userId") String userId, @Query("sysUserId") String sysUserId);

    //销售用户Id获取用户信息
    @POST("commonSelect/getUserInfo")
    Observable<XSDataBean> getXSDataBean(@Query("userId") String userId);

    //圈层端（团队长）用户Id获取用户信息
    @POST("layersSelect/getSysUser")
    Observable<ZhangBingDataBean> getZhangBingBean(@Query("userId") String userId, @Query("sysUserId") String sysUserId);

    //专员/专案用户Id获取用户信息
    @POST("commonSelect/getUserInfo")
    Observable<ZYDataBean> getZYDataBean(@Query("userId") String userId);


    @POST("/list")
    Call<JSONObject> loadRepo();

    //专案端首页数据
    @POST("specialSelect/findData?")
    Observable<HomeBean> getHomeList(@Query("userId") String userId, @Query("beforeDate") String beforeDate, @Query("afterDate") String afterDate, @Query("type") String type, @Query("pageSize") String pageSize);

    //专案端详情财务数据
    @POST("specialSelect/receivableMoneySingle?")
    Observable<OperationBean> getOperationList(@Query("userId") String userId, @Query("projectId") String projectId, @Query("beforeDate") String beforeDate, @Query("afterDate") String afterDate, @Query("type") String type);

    //专案端详情运营数据
    @POST("specialSelect/operationSingle?")
    Observable<com.xcy.fzbcity.all.modle.FinanceBean> getFinanceList(@Query("userId") String userId, @Query("projectId") String projectId, @Query("beforeDate") String beforeDate, @Query("afterDate") String afterDate, @Query("type") String type, @Query("tag") String tag);

    //专案端详情趋势图数据
    @POST("specialSelect/businessTrendSingle?")
    Observable<BusinessBean> getBusinesseList(@Query("userId") String userId, @Query("projectId") String projectId, @Query("beforeDate") String beforeDate, @Query("afterDate") String afterDate, @Query("type") String type, @Query("status") String status);

    //专案端首页列表数据
    @POST("specialSelect/specialList?")
    Observable<SideHomeBean> getHomeBeanList(@Query("userId") String userId, @Query("projectType") String projectType, @Query("pageSize") String pageSize);

    //专案端我发起的审核列表数据
    @POST("specialSelect/myExaminelist??")
    Observable<InitiatedBean> getMyExaminelist(@Query("userId") String userId, @Query("type") String type, @Query("status") String status, @Query("pageSize") String pageSize);

    //专案端我发起的审核未处理
    @POST("specialSelect/myExamineNum??")
    Observable<MyExamineNumBean> getMyExamineNum(@Query("userId") String userId, @Query("type") String type, @Query("pageSize") String pageSize);

    //专案端详情页所有数据
    @POST("specialSelect/specialDetail?")
    Observable<DetailsBean> getDetailsBeanList(@Query("userId") String userId, @Query("projectId") String projectId);

    //专案端 佣金上半部分数据
    @POST("specialSelect/findReceivableMoneyCount?")
    Observable<MoneyCountBean> getMoneyCountList(@Query("userId") String userId, @Query("projectId") String projectId, @Query("type") String type, @Query("startDate") String startDate, @Query("endDate") String endDate);

    //专案端 佣金下半部分数据
    @POST("specialSelect/findReceivableMoneyList?")
    Observable<ReceivableBean> getMoneyList(@Query("userId") String userId, @Query("search") String search, @Query("status") String status, @Query("projectType") String projectType, @Query("projectId") String projectId, @Query("startTime") String startTime, @Query("endTime") String endTime, @Query("pageSize") String pageSize, @Query("type") String type);

    //专案端 申请登岛添加的数据
    @POST("nodeUpdate/islandSave")
    Observable<LandSaveBean> getIslandSave(@Query("preparationId") String preparationId, @Query("routeId") String routeId, @Query("projectType") String projectType,
                                           @Query("isColleague") String isColleague, @Query("colleagueUserNum") String colleagueUserNum, @Query("idNumber") String idNumber,
                                           @Query("age") String age, @Query("gender") String gender, @Query("passportNumber") String passportNumber,
                                           @Query("passportImg") String passportImg, @Query("route") String route, @Query("islandTime") String islandTime,
                                           @Query("isPay") String isPay, @Query("sumCost") String sumCost, @Query("landingImg") String landingImg,
                                           @Query("city") String city, @Query("occupation") String occupation, @Query("focus") String focus,
                                           @Query("intentionalBuilding") String intentionalBuilding, @Query("paymentMethod") String paymentMethod, @Query("hasDecision") String hasDecision,
                                           @Query("resistance") String resistance, @Query("objective") String objective, @Query("idealArea") String idealArea,

                                           @Query("userId") String userId, @Query("jsonStr") String jsonStr, @Query("customerName") String customerName);

    //专案端拒絕記錄
    @POST("specialSelect/myExaminelist")
    Observable<InitiatedBean> getExaminelistBean(@Query("userId") String userId, @Query("type") String type, @Query("status") String status, @Query("pageSize") String pageSize);

    //专案端待我审核的拒絕記錄
    @POST("specialSelect/toAuditList")
    Observable<CheckPendingBean> getToAuditList(@Query("userId") String userId, @Query("type") String type, @Query("status") String status, @Query("pageSize") String pageSize);

    //专案端 补全信息 申请登岛添加的数据
    @POST("nodeUpdate/landUpdate")
    Observable<LandSaveBean> getlandUpdate(@Query("id") String id, @Query("preparationId") String preparationId, @Query("gender") String gender, @Query("idNumber") String idNumber, @Query("age") String age, @Query("passportNumber") String passportNumber, @Query("passportImg") String passportImg, @Query("route") String route, @Query("routeId") String routeId, @Query("colleagueUserNum") int colleagueUserNum, @Query("sumCost") String sumCost, @Query("landingImg") String landingImg, @Query("city") String city, @Query("occupation") String occupation, @Query("focus") String focus, @Query("intentionalBuilding") String intentionalBuilding, @Query("paymentMethod") String paymentMethod, @Query("hasDecision") String hasDecision, @Query("resistance") String resistance, @Query("objective") String objective, @Query("idealArea") String idealArea, @Query("userId") String userId);

    //专案端 补全信息 同行人 修改的数据
    @POST("nodeUpdate/colleagueUpdate")
    Observable<LandSaveBean> getColleagueUpdate(@Query("id") String id, @Query("landingId") String landingId, @Query("preparationId") String preparationId, @Query("fullName") String fullName, @Query("phone") String phone, @Query("idNumber") String idNumber, @Query("passportNumber") String passportNumber, @Query("passportImg") String passportImg, @Query("gender") String gender, @Query("relation") String relation, @Query("city") String city, @Query("occupation") String occupation, @Query("focus") String focus, @Query("intentionalBuilding") String intentionalBuilding, @Query("paymentMethod") String paymentMethod, @Query("hasDecision") String hasDecision, @Query("resistance") String resistance, @Query("objective") String objective, @Query("idealArea") String idealArea, @Query("userId") String userId);

    //专案端 成交添加的数据
    @POST("nodeUpdate/tradeSave")
    Observable<TradeSaveBean> getTradeSave(@Query("id") String id, @Query("projectId") String projectId, @Query("economicId") String economicId, @Query("preparationId") String preparationId, @Query("customerId") String customerId, @Query("roomNumber") String roomNumber, @Query("apartment") String apartment, @Query("area") String area, @Query("price") String price, @Query("totalPrice") String totalPrice, @Query("paymentMethod") String paymentMethod, @Query("commissionId") String commissionId, @Query("procuctType") String procuctType, @Query("gender") String gender, @Query("relation") String relation, @Query("fullName") String fullName, @Query("phone") String phone, @Query("idNumber") String idNumber, @Query("userId") String userId, @Query("tradeDateStr") String tradeDateStr);

    //专案端 一键成交添加的数据
    @POST("specialUpdate/oneKeyTrade")
    Observable<OneKeyTradeBean> getOneKeyTrade(@Query("procuctType") String procuctType, @Query("fullName") String fullName, @Query("gender") String gender, @Query("phone") String phone, @Query("idNumber") String idNumber, @Query("roomNumber") String roomNumber, @Query("apartment") String apartment, @Query("area") String area, @Query("price") String price, @Query("totalPrice") String totalPrice, @Query("paymentMethod") String paymentMethod, @Query("oneKeyComment") String oneKeyComment, @Query("commissionId") String commissionId, @Query("projectId") String projectId, @Query("userId") String userId, @Query("agentName") String agentName, @Query("agentPhone") String agentPhone, @Query("tradeDateStr") String tradeDateStr);


    //专案端 填写退单说明
    @POST("specialUpdate/chargebackApply")
    Observable<ListOfBean> getChargebackApply(@Query("userId") String userId, @Query("preparationId") String preparationId, @Query("explain") String explain);

    //专案端 认筹添加的数据
    @POST("nodeUpdate/earnestMoneySave")
    Observable<ConfessBean> getEarnestMoneySave(@Query("id") String id, @Query("preparationId") String preparationId, @Query("customerId") String customerId, @Query("projectId") String projectId, @Query("fullName") String fullName, @Query("phone") String phone, @Query("idNumber") String idNumber, @Query("intentionPier") String intentionPier, @Query("apartment") String apartment, @Query("intentionalArea") String intentionalArea, @Query("recognizeTimeStr") String recognizeTime, @Query("relation") String relation, @Query("gender") String gender, @Query("userId") String userId);

    //专案端 调单添加的数据
    @POST("specialUpdate/adjustApplySave")
    Observable<TradeSaveBean> getAdjustApplySave(@Query("preparationId") String preparationId, @Query("customerId") String customerId, @Query("roomNumber") String roomNumber, @Query("apartment") String apartment, @Query("area") String area, @Query("price") String price, @Query("totalPrice") String totalPrice, @Query("paymentMethod") String paymentMethod, @Query("commissionId") String commissionId, @Query("procuctType") String procuctType, @Query("gender") String gender, @Query("relation") String relation, @Query("fullName") String fullName, @Query("phone") String phone, @Query("idNumber") String idNumber, @Query("userId") String userId, @Query("projectId") String projectId, @Query("economicId") String economicId, @Query("tradeDateStr") String tradeDateStr);


    //专案端 查询登岛路线数据
    @POST("nodeSelect/getlandLine")
    Observable<GetLandLineBean> getlandLine(@Query("userId") String userId, @Query("projectId") String projectId);

    //专案端 登岛路线时间数据
    @POST("nodeSelect/getlandLinetime")
    Observable<GetLandLineTimeBean> getlandLinetime(@Query("routeid") String routeid, @Query("projectId") String projectId);

    //报备流程详情
    @POST("commonSelect/reportProcessDetails")
    Observable<com.xcy.fzbcity.all.modle.MyClientFragmentBean> getClientFragmentBean(@Query("userId") String userId, @Query("preparationId") String preparationId);

    //点击成交回显数据
    @POST("nodeSelect/findTrade")
    Observable<FindTradeBean> getFindTrade(@Query("preparationId") String preparationId);

    //一键成交 查询经纪人信息
    @POST("commonSelect/getPhoneByUser")
    Observable<PhoneByUserBean> getPhoneByUser(@Query("userId") String userId, @Query("phone") String phone, @Query("name") String name);

    //个人信息 修改姓名
    @POST("commonUpdate/updateName")
    Observable<ChangeNameBean> getUpdateName(@Query("userId") String userId, @Query("name") String name);

    //点击调单回显数据
    @POST("nodeSelect/findAdjustApply")
    Observable<FindAdjustApplyBean> getFindAdjustApply(@Query("userId") String userId, @Query("preparationId") String preparationId);

    //字典数据获取
    @POST("commonSelect/getDictList")
    Observable<DictListBean> getDictList(@Query("userId") String userId, @Query("type") String type);

    //待我审核列表
    @POST("specialSelect/toAuditList")
    Observable<CheckPendingBean> getToAuditList(@Query("userId") String userId, @Query("type") String type, @Query("status") String status);

    //佣金
    @POST("nodeSelect/timeRange")
    Observable<com.xcy.fzbcity.all.modle.BrokerBean> getTimeRange(@Query("currentDate") String currentDate, @Query("projectId") String projectId, @Query("agentId") String agentId);

    //专案端用户个人信息数据
    @POST("commonSelect/getUserInfo?")
    Observable<UserBean> getUserBean(@Query("userId") String userId);

    //专案端发布楼盘动态数据
    @POST("specialUpdate/addHousesDynamic?")
    Observable<MessageIssueBean> getAddHousesDynamic(@Query("title") String title, @Query("content") String content, @Query("img") String img, @Query("userId") String userId, @Query("projectId") String projectId,@Query("type") String type);

    //专案端待我审核列表
    @POST("specialSelect/toAuditList?")
    Observable<CheckPendingBean> getToAuditList(@Query("userId") String userId, @Query("projectId") String projectId, @Query("type") String type, @Query("status") int status, @Query("pageSize") String pageSize, @Query("tag") String tag, @Query("startDate") String startDate, @Query("endDate") String endDate);

    //专案端待我审核未处理数量
    @POST("specialSelect/toAuditNum?")
    Observable<ToAuditNumBean> getToAuditNum(@Query("userId") String userId, @Query("projectId") String projectId, @Query("type") String type, @Query("pageSize") String pageSize, @Query("tag") String tag, @Query("startDate") String startDate, @Query("endDate") String endDate);


    //首页城市列表数据
    @POST("commonSelect/findCityForParentId")
    Observable<CityBean> getCityList();

    //首页轮播图数据
    @POST("commonSelect/newsList?")
    Observable<ImgData> getBannerList(@Query("userId") String userId, @Query("city") String city, @Query("projectType") String projectType, @Query("arrposid") String arrposid);

    //首页轮播图列表详情数据
    @POST("commonSelect/newsDetails?")
    Observable<NewsDetailsBean> getNewsDetails(@Query("cityCompanyId") String cityCompanyId,@Query("userId") String userId, @Query("id") String id);

    //首页新闻头条数据
    @POST("commonSelect/messageList?")
    Observable<MessageBean2> getMessageTextList(@Query("userId") String userId, @Query("city") String city, @Query("type") String type);

    //首页热门列表数据
    @POST("commonSelect/projectList?")
    Observable<HotBean> getHotList(@Query("userId") String userId, @Query("city") String city, @Query("hot") String hot, @Query("pageSize") String pageSize);

    //消息界面楼盘动态数据
    @POST("commonSelect/projectHousesDynamicsList?")
    Observable<DynamicBean> getDynamicBeanList(@Query("userId") String userId, @Query("pageSize") String pageSize);

    //消息界面楼盘动态数据
    @POST("commonSelect/housesDynamicList")
    Observable<Dynamic2Bean> getDynamicBeanList2(@Query("cityCompanyId") String cityCompanyId, @Query("userId") String userId, @Query("pageSize") String pageSize);

    //消息界面通知+房客数据
    @POST("commonSelect/messageList?")
    Observable<MessageBean> getMessageBeanList(@Query("userId") String userId, @Query("city") String city, @Query("type") String type, @Query("pageSize") String pageSize);

    //消息界面通知+房客数据
    @POST("commonSelect/messageList?")
    Observable<GoodNewsBean> getGoodNewsBeanList(@Query("userId") String userId, @Query("city") String city, @Query("type") String type, @Query("pageSize") String pageSize);

    //更多信息数据
    @POST("commonSelect/projectMoreInfo")
    Observable<MoreBean> getProjectMoreInfo(@Query("id") String id);

    //海外筛选 楼盘特色/项目标签
    @POST("commonSelect/label")
    Observable<LabelBean> getLabel(@Query("projectType") String projectType, @Query("userId") String userId, @Query("type") String type, @Query("city") String city);

    //项目详情 户型解析
    @POST("commonSelect/familyInfo")
    Observable<FamilyInfoBean> getFamilyInfo(@Query("familyId") String familyId);

    //项目详情相册
    @POST("commonSelect/projectPhoto")
    Observable<PhotoBean> getProjectPhoto(@Query("userId") String userId, @Query("projectId") String projectId);

    //列表数据
    @POST("commonSelect/projectList?")
    Observable<HotBean> getList(@Query("userId") String userId, @Query("city") String city, @Query("comprehensiveSorting") String comprehensiveSorting, @Query("projectLabel") String projectLabel, @Query("projectType") String projectType, @Query("cityType") String cityType, @Query("nation") String nation, @Query("projectPriceStart") String projectPriceStart, @Query("projectPriceEnd") String projectPriceEnd, @Query("apartment") String apartment, @Query("areaSection") String areaSection, @Query("ffProjectTrait") String ffProjectTrait, @Query("procuctType") String procuctType, @Query("fitmentState") String fitmentState, @Query("searchName") String searchName, @Query("pageSize") String pageSize);

    //我的登录
    @POST("commonSelect/login")
    Observable<LoginUserBean> getLogin(@Query("username") String username, @Query("password") String password, @Query("type") String type);

    //验证码发送
    @POST("commonSelect/sendCode")
    Observable<CodeBean> getSendCode(@Query("phone") String phone,@Query("type") String type);

    //修改密码
    @POST("commonUpdate/findBackPassword")
    Observable<ForgetBean> getFindBackPassword(@Query("inputCode") String inputCode, @Query("phone") String phone, @Query("password") String password);

    //手机绑定微信
    @POST("commonSelect/wechatBinding")
    Observable<WechatBindingBean> getWechatBinding(@Query("userId") String userId, @Query("userPhone") String userPhone, @Query("captcha") String captcha, @Query("type") String type, @Query("wechatData") String wechatData);

    //手机绑定微信
    @POST("commonSelect/isWeChat")
    Observable<WechatBindingBean> getIsWeChat(@Query("userId") String userId);

    //是否上线
    @POST("commonSelect/online")
    Observable<OnLineBean> getOnLine(@Query("appType") String appType,@Query("appVeriosn") String appVeriosn);

    //身份查询
    @POST("commonSelect/userIdentity")
    Observable<UserIdentity> getUserIdentity(@Query("username") String username, @Query("password") String password, @Query("type") String type);

    //专案端登录
    @POST("commonSelect/login")
    Observable<ExemplaryUserBean> getExemplaryLogin(@Query("username") String username, @Query("password") String password, @Query("type") String type);

    //我的佣金上半部
    @POST("ordinarySelect/amountSummary")
    Observable<BorkerageUpBean> getBrokerageUp(@Query("userId") String userId);

    //我的佣金下半部
    @POST("ordinarySelect/amountList")
    Observable<BorkerageDownBean> getBrokerageDown(@Query("userId") String userId, @Query("projectType") String projectType, @Query("search") String search, @Query("pageSize") String pageSize);

    //我的报备数据请求
    @POST("nodeSelect/reportBefore")
    Observable<ReportBean> getReportBean(@Query("userId") String userId);

    //我的报备数据请求
    @POST("nodeSelect/isIdNumber")
    Observable<IdNumberBean> getIdNumber(@Query("projectId") String projectId);

    //我的报备数据提交
    @POST("nodeUpdate/reportSave")
    Observable<ChangePhoneBean> getReportPostBean(@Query("customerId") String customerId, @Query("procuctType") String procuctType, @Query("areaSection") String areaSection, @Query("purpose") String purpose, @Query("projectLabel") String projectLabel, @Query("priceMin") String priceMin, @Query("priceMax") String priceMax, @Query("projectId") String projectId, @Query("intentionalRegion") String intentionalRegion, @Query("guideRuleId") String guideRuleId, @Query("isIsland") String isIsland, @Query("idCard") String idCard, @Query("remarks") String remarks, @Query("landingStartDate") String landingStartDate, @Query("landingEndDate") String landingEndDate, @Query("userId") String userId, @Query("customerName") String customerName, @Query("customerPhone") String customerPhone);

    //我的佣金和客户量
    @POST("ordinarySelect/myDataCount")
    Observable<ClientCommissionsBean> getClientCommissions(@Query("userId") String userId);

    //修改密码
    @POST("commonUpdate/updatePassword")
    Observable<ChangePassowrdBean> getChangePassword(@Query("userId") String userId, @Query("oldPassword") String oldPassword, @Query("newPassword") String newPassword);

    //修改性别
    @POST("commonUpdate/updateUser")
    Observable<ChangeSexBean> getChangeSex(@Query("userId") String userId, @Query("sex") String sex);

    //修改昵称
    @POST("commonUpdate/updateUser")
    Observable<NickNameBean> getNickName(@Query("userId") String userId, @Query("name") String name, @Query("sex") String sex, @Query("photo") String photo, @Query("industry") String industry);

    //免责声明/
    @POST("commonSelect/relevantManageDetails")
    Observable<DisclaimerBean> getDisclaimer(@Query("type") String type, @Query("userId") String userId);

    //经济圈
    @POST("ordinarySelect/economicCircleDetails")
    Observable<EconomicCircleBean> getEconomicCircle(@Query("userId") String userId, @Query("circleId") String circleId);

    //项目详情 楼盘走势图
    @POST("commonSelect/projectHousesTrendList")
    Observable<ProjectHousesTrendListBean> getProjectHousesTrendListBean(@Query("userId") String userId, @Query("projectId") String projectId);

    //项目卖点
    @POST("commonSelect/projectTalkToolList")
    Observable<SellingPointsBean> getSellingPoints(@Query("type") String type, @Query("userId") String userId, @Query("projectId") String projectId);

    //拓客工具项目列表详情
    @POST("commonSelect/projectTalkToolShare")
    Observable<ProjectTalkToolShareBean> getProjectTalkToolShare(@Query("userId") String userId, @Query("talkToolId") String talkToolId);

    //客户列表页 报备-到访-登岛-认筹-成交-失效
    @POST("commonSelect/reportProcessList")
    Observable<ClientFragmentBean> getClientFragment(@Query("userId") String userId, @Query("projectId") String projectId, @Query("searchName") String searchName, @Query("type") String type, @Query("pageSize") String pageSize, @Query("tag") String tag, @Query("startDate") String startDate, @Query("endDate") String endDate);

    //未读消息数据
    @POST("commonSelect/reportNoReadList")
    Observable<ReportNoReadListBean> getReportNoReadList(@Query("userId") String userId, @Query("projectId") String projectId, @Query("tag") String tag, @Query("startDate") String startDate, @Query("endDate") String endDate, @Query("searchName") String searchName, @Query("teamTag") String teamTag);

    //圈层未读消息数据
    @POST("layersSelect/getreportNoRead")
    Observable<ReportNoReadListBean> getreportNoRead(@Query("agentId") String agentId,@Query("search") String search,@Query("userId") String userId,  @Query("type") String type,  @Query("tag") String tag, @Query("startDate") String startDate, @Query("endDate") String endDate);


    //项目进度未读消息返回数据
    @POST("nodeUpdate/readRecord")
    Observable<ReadRecordBean> getReadRecord(@Query("userId") String userId, @Query("preparationId") String preparationId, @Query("status") String status);


//    //根据用户Id获取用户信息
//    @POST("commonSelect/getUserInfo")
//    Observable<UserMessageBean> getUserMessage(@Query("userId") String userId);

    //用户反馈
    @POST("commonUpdate/userFeedback")
    Observable<FeedBackBean> getFeedBack(@Query("feedback") String feedback, @Query("img") String img, @Query("userId") String userId);

    //未成交接口
    @POST("shoppingUpdate/noTrade")
    Observable<FeedBackBean> getNoTrade(@Query("userId") String userId, @Query("reason") String reason, @Query("landingId") String landingId, @Query("preparationId") String preparationId);

    //发送验证码
    @POST("commonSelect/sendCode")
    Observable<VerificationBean> getCode(@Query("phone") String phone);

    //定价通知
    @POST("commonUpdate/addRemind")
    Observable<RemindBean> getRemindBean(@Query("projectId") String projectId, @Query("remindType") String remindType, @Query("userId") String userId);

    //更换手机号验证验证码
    @POST("commonUpdate/updatePhone")
    Observable<ChangePhoneBean> getChangePhone(@Query("inputCode") String inputCode, @Query("phone") String phone, @Query("userId") String userId);

    //添加客户
    @POST("commonUpdate/addCustomer")
    Observable<AddClientBean> getAddClient(@Query("customerName") String customerName, @Query("customerImg") String customerImg, @Query("contacts1") String contacts1,
                                           @Query("contactsPhone1") String contactsPhone1, @Query("contacts2") String contacts2, @Query("contactsPhone2") String contactsPhone2,
                                           @Query("contacts3") String contacts3, @Query("contactsPhone3") String contactsPhone3, @Query("userId") String userId);

    //导入通讯录
    @POST("commonUpdate/customerImport")
    Observable<AddPhoneBean> getCustomerImport(@Query("jsonStr") String jsonStr, @Query("userId") String userId);

    //客户列表
    @POST("commonSelect/customerList")
    Observable<ClientBean> getClient(@Query("searchName") String searchName, @Query("userId") String userId, @Query("pageSize") String pageSize);

    //创建经济圈
    @POST("ordinaryUpdate/economicCircleSave")
    Observable<DiscussBean> getDiscuss(@Query("userId") String userId, @Query("content") String content, @Query("imgUrl") String imgUrl);

    //客户列表
    @POST("commonUpdate/updateHeadPhoto")
    Observable<PersonalPhotoBean> getPersonalPhoto(@Query("userId") String userId, @Query("photo") String photo);

    //圖片上傳
    @Multipart
    @POST("commonSelect/upload")
    Observable<AddPhotoBean> getAddPhoto(@Query("userId") String userId, @Query("uploadPath") String uploadPath, @Part MultipartBody.Part file);

    //圖片批量上傳
    @Multipart
    @POST("commonSelect/batchUpload")
    Observable<AddPhotoBeanS> getAddPhotoS(@Query("userId") String userId, @Query("uploadPath") String uploadPath, @Part List<MultipartBody.Part> partLis);

    //修改密码
    @POST("commonUpdate/updatePassword")
    Observable<NickNameBean> getUpdatePassword(@Query("userId") String userId, @Query("oldPassword") String oldPassword, @Query("newPassword") String newPassword);

    //点赞数据
    @POST("commonUpdate/housesDynamicCommentLike")
    Observable<LikeNumBean> getLikeNum(@Query("targetId") String targetId, @Query("userId") String userId, @Query("type") String type);


    //获取楼盘动态详情
    @POST("commonSelect/housesDynamicDetails")
    Observable<DynamicDetailsBean> getDynamicDetailsBean(@Query("housesDynamicId") String housesDynamicId, @Query("userId") String userId);

    //评论添加
    @POST("commonUpdate/housesDynamicCommentLike")
    Observable<CommentBean> getHousesDynamicCommentLike(@Query("type") String type, @Query("comment") String comment, @Query("targetId") String targetId, @Query("pid") String pid, @Query("userId") String userId);

    @POST("ordinaryUpdate/economicCircleCommentLike")
    Observable<TotalZanBean> getTotalLikeNum(@Query("targetId") String targetId, @Query("userId") String userId, @Query("type") String type);

    //专案端到访添加的数据
    @POST("nodeUpdate/visitSave")
    Observable<VisitSaveBean> getVisitSave(@Query("preparationId") String preparationId, @Query("location") String location, @Query("locationName") String locationName, @Query("accessingImg") String accessingImg, @Query("userId") String userId);

    //    TODO 20190919 修改
//
    @POST("commonSelect/projectCityNationlist")
    Observable<CountryBean> getCountryBean(@Query("userId") String userId, @Query("city") String city, @Query("projectType") String projectType, @Query("pageSize") String pageSize);

    //
    @POST("ordinarySelect/economicCircleList")
    Observable<TotalBean> getTotalBean(@Query("userId") String userId, @Query("city") String city, @Query("currUserId") String currUserId, @Query("pageSize") String pageSize);

    //
    @POST("ordinarySelect/economicCircleList")
    Observable<TotalBean> getTBean(@Query("userId") String userId, @Query("city") String city, @Query("pageSize") String pageSize);

    //
    @POST("commonSelect/projectPhoto")
    Observable<PhotoBean> getPhotoBean(@Query("userId") String userId, @Query("projectId") String projectId);

    //
    @POST("commonSelect/housesDynamicList")
    Observable<Dynamic2Bean> getDynamicBean(@Query("userId") String userId, @Query("cityCompanyId") String cityCompanyId, @Query("projectId") String projectId, @Query("pageSize") String pageSize);

    //
    @POST("commonSelect/projectBuildingInfo")
    Observable<BuildingBean> getBuildingBean(@Query("userId") String userId, @Query("id") String id);

    //
    @POST("commonSelect/customerDetails")
    Observable<ClientParticularsBean> getClientParticularsBean(@Query("userId") String userId, @Query("customerId") String customerId);

    //
    @POST("commonSelect/projectList")
    Observable<HotBean> getHotBean(@Query("userId") String userId, @Query("city") String city, @Query("pageSize") String pageSize);

    @POST("commonSelect/myCollection")
    Observable<HotBean> getMyCollection(@Query("userId") String userId, @Query("projectType") String projectType);

    //
    //
    @POST("commonSelect/customerDetails")
    Observable<MakeABargainBean> getMakeABargainBean(@Query("userId") String userId, @Query("customerId") String customerId);

    //
    @POST("ordinaryUpdate/economicCircleCommentLike")
    Observable<CircleBean> getCircleBean(@Query("type") String type, @Query("comment") String comment, @Query("targetId") String targetId, @Query("pid") String pid, @Query("userId") String userId);

    //
    @POST("ordinarySelect/economicCircleDetails")
    Observable<EconomicCircleBean> getEconomicCircleBean(@Query("userId") String userId, @Query("circleId") String circleId);

    //
    @POST("commonSelect/customerDetails")
    Observable<FailureBean> getFailureBean(@Query("userId") String userId, @Query("customerId") String customerId);

    //
    @POST("commonSelect/projectCityNationlist")
    Observable<NationBean> getNationBean(@Query("userId") String userId, @Query("city") String city, @Query("projectType") String projectType);

    //
    @POST("commonSelect/projectDetails")
    Observable<ProjectDetailsBean> getProjectDetailsBean(@Query("userId") String userId, @Query("id") String id);

    //
    @POST("commonUpdate/projectCollectSave")
    Observable<CollectBean> getCollectBean(@Query("projectId") String projectId, @Query("userId") String userId, @Query("type") String type);

    //
    @POST("commonSelect/projectPropertyHouseList")
    Observable<HouseBean> getHouseBean(@Query("userId") String userId, @Query("projectId") String projectId, @Query("status") String status, @Query("pageSize") String pageSize);

    //
    @POST("commonSelect/projectList")
    Observable<HotBean> getHotBean(@Query("userId") String userId, @Query("city") String city, @Query("nation") String nation, @Query("pageSize") String pageSize);

    //    //
    @POST("commonSelect/projectList")
    Observable<HotBean> getHotBean(@Query("userId") String userId, @Query("city") String city, @Query("projectType") String projectType, @Query("searchName") String searchName, @Query("pageSize") String pageSize);

    //
    @POST("commonSelect/reportProcessDetails")
    Observable<MakeABargainBean> getMBBean(@Query("userId") String userId, @Query("preparationId") String preparationId);

    //
    @POST("commonSelect/customerDetails")
    Observable<MakeABargainBean> getMBargainBean(@Query("userId") String userId, @Query("customerId") String customerId);

    //
    @POST("commonSelect/routePlanningList")
    Observable<SpellingMassTimeBean> getSpellingMassTimeBean(@Query("projectId") String projectId, @Query("pageSize") String pageSize);

    //
    @POST("commonSelect/routePlanningDetail")
    Observable<SpellingDataBean> getSpellingDataBean(@Query("routeTimeId") String routeTimeId);

    //项目进度详情
    @POST("commonSelect/reportProcessDetails")
    Observable<ReportProcessDetailsBean> getReportProcessDetails(@Query("userId") String userId, @Query("preparationId") String preparationId);

    //点击补全信息 获取数据回显
    @POST("nodeSelect/getLand")
    Observable<LandBean> getLand(@Query("userId") String userId, @Query("preparationId") String preparationId);

    //点击补全信息 获取同行人 数据回显
    @POST("nodeSelect/getColleague")
    Observable<ColleagueBean> getColleague(@Query("userId") String userId, @Query("id") String id);

    //待我审核 通过或拒绝
    @POST("specialUpdate/reportAndVisitAudit")
    Observable<CBean> getReportAndVisitAudit(@Query("userId") String userId, @Query("preparationId") String preparationId, @Query("maxStatus") String maxStatus, @Query("minStatus") String minStatus, @Query("reason") String reason);

    //待我审核 认筹数据
    @POST("specialUpdate/earnestMoneyAudit")
    Observable<CheckBean> getEarnestMoneyAudit(@Query("userId") String userId, @Query("preparationId") String preparationId, @Query("isUpdate") String isUpdate);

    //待我审核 认筹修改数据
    @POST("specialUpdate/earnestMoneyAudit")
    Observable<EarnestMoneyAuditBean> getEarnestMoneyAuditBean(@Query("userId") String userId, @Query("preparationId") String preparationId, @Query("isUpdate") String isUpdate);

    //待我审核 成交数据
    @POST("specialUpdate/tradeAudit")
    Observable<CheckBean> getTradeAudit(@Query("userId") String userId, @Query("preparationId") String preparationId, @Query("isUpdate") String isUpdate);

    //待我审核 成交成交数据
    @POST("specialUpdate/tradeAudit")
    Observable<TradeAuditBean> getTradeAuditBean(@Query("userId") String userId, @Query("preparationId") String preparationId, @Query("isUpdate") String isUpdate);

    //圈层端我的界面数据
    @POST("layersSelect/myData")
    Observable<MyDataBean> getMyData(@Query("userId") String userId);

    //圈层端登录
    @POST("commonSelect/login")
    Observable<CaptainBean> getCaptainLogin(@Query("username") String username, @Query("password") String password, @Query("type") String type);

    //圈层端我的团队界面数据
    @POST("layersSelect/myTeam")
    Observable<MyTeamBean> getMyTeam(@Query("userId") String userId);

    //圈层 报备流程数据
    @POST("layersSelect/getreportProcess")
    Observable<ReportProcessBean> getReportProcess(@Query("agentId") String agentId, @Query("status") String status, @Query("search") String search, @Query("userId") String userId, @Query("pageSize") String pageSize, @Query("type") String type, @Query("tag") String tag, @Query("startDate") String startDate, @Query("endDate") String endDate);

    //圈层端 团队长详情 项目佣金
    @POST("layersSelect/teamLeaderAmount")
    Observable<TeamLeaderAmountBean> getTeamLeaderAmount(@Query("userId") String userId, @Query("search") String search);

    //圈层端我的团队 数据统计
    @POST("layersSelect/dataStatistics")
    Observable<DataStatisticsBean> getDataStatistics(@Query("userId") String userId, @Query("agentId") String agentId, @Query("type") String type, @Query("startDate") String startDate, @Query("endDate") String endDate, @Query("tag") String tag);

    //圈层端我的团队 成交TOP5单
    @POST("layersSelect/dailyTurnover")
    Observable<DailyTurnoverBean> getDailyTurnover(@Query("userId") String userId, @Query("agentId") String agentId, @Query("state") String state, @Query("type") String type, @Query("startDate") String startDate, @Query("endDate") String endDate);

    //圈层端我的团队界面数据
    @POST("layersSelect/myTeam")
    Observable<MyTeam2Bean> getMyTeam2(@Query("userId") String userId);

    //圈层端添加销售 查询团队长级别信息
    @POST("layersSelect/getSysUser")
    Observable<SysUser3Bean> getSysUser3(@Query("userId") String userId, @Query("sysUserId") String sysUserId);

    //圈层端添加销售 根据拥有者查询佣金比例列表数据
    @POST("layersSelect/getTeamLeaderLevel")
    Observable<RatioByOwnerId2Bean> getRatioByOwnerId2(@Query("userId") String userId);

    //圈层端我的团队 团队佣金
    @POST("layersSelect/teamCommissions")
    Observable<TeamCommissionsBean> getTeamCommissions(@Query("userId") String userId, @Query("agentId") String agentId, @Query("type") String type, @Query("startDate") String startDate, @Query("endDate") String endDate);

    //圈层端团队佣金 佣金列表
    @POST("layersSelect/commissionList")
    Observable<CommissionListBean> getCommissionList(@Query("userId") String userId, @Query("projectType") String projectType, @Query("search") String search, @Query("status") String status, @Query("pageSize") String pageSize, @Query("tag") String tag, @Query("startDate") String startDate, @Query("endDate") String endDate);

    //圈层端添加销售 查询团队长级别信息
    @POST("layersSelect/getTeamLeaderLevel")
    Observable<TeamLeaderLevelBean> getTeamLeaderLevel(@Query("userId") String userId);

    //圈层端添加销售 查询团队长级别信息
    @POST("layersSelect/getSysUser")
    Observable<SysUser2Bean> getSysUser(@Query("userId") String userId, @Query("sysUserId") String sysUserId);

    //圈层端添加销售 根据拥有者查询佣金比例列表数据
    @POST("layersSelect/getRatioByOwnerId")
    Observable<RatioByOwnerIdBean> getRatioByOwnerId(@Query("userId") String userId, @Query("ownerId") String ownerId);

    //圈层端添加销售 确认数据
    @POST("layersUpdate/brokerSave")
    Observable<BrokerSaveBean> getBrokerSave(@Query("id") String id, @Query("industry") String industry, @Query("name") String name, @Query("phone") String phone, @Query("loginName") String loginName, @Query("password") String password, @Query("loginFlag") String loginFlag, @Query("manageFlag") String manageFlag, @Query("userId") String userId, @Query("ratioId") String ratioId, @Query("ownerId") String ownerId, @Query("type") String type, @Query("levelId") String levelId);

    //圈层端添加销售 根据拥有者查询佣金比例列表数据
    @POST("layersSelect/agentList")
    Observable<AgentListBean> getAgentList(@Query("searcName") String searcName, @Query("type") String type, @Query("loginFlag") String loginFlag, @Query("userId") String userId, @Query("agentId") String agentId);

    //佣金级别查询
    @POST("layersSelect/getRatioByOwnerId")
    Observable<CommissionLevelSelectBean> getCommissionLevelSelece(@Query("userId") String userId, @Query("ownerId") String ownerId);

    //佣金级别删除
    @POST("layersUpdate/delRatio")
    Observable<CommissionLevelDeleteBean> getCommissionLevelDelete(@Query("userId") String userId, @Query("ratioId") String ratioId);

    //团队人员
    @POST("layersSelect/agentList")
    Observable<TeamMemberBean> getTeamMemberBeane(@Query("searcName") String searcName, @Query("type") String type, @Query("loginFlag") String loginFlag, @Query("userId") String userId, @Query("agentId") String agentId, @Query("pageSize") String pageSize);

    //专案 退筹
    @POST("specialUpdate/refundMoneyApply")
    Observable<BackToBean> getRefundMoneyApply(@Query("preparationId") String preparationId, @Query("earnestComment") String earnestComment, @Query("userId") String userId);

    //佣金级别添加
    @POST("layersUpdate/saveRatio")
    Observable<CommissionLevelAdapterAddBean> getCommissionLevelAdd(@Query("id") String id, @Query("owner") String owner, @Query("name") String name, @Query("percent") String percent, @Query("type") String type, @Query("userId") String userId);

    //圈层端销售详情 界面数据
    @POST("layersSelect/agentDetails")
    Observable<AgentDetailsBean> getAgentDetails(@Query("userId") String userId, @Query("agentId") String agentId);

    //圈层端销售详情 界面数据
    @POST("layersUpdate/setLevel")
    Observable<LevelBean> getLevelBean(@Query("userId") String userId, @Query("ratioId") String ratioId, @Query("ids") String ids);


    //导购端任务列表数据
    @POST("shoppingSelect/routetimeList?")
    Observable<TaskListBean> getRouteTimeList(@Query("userId") String userId, @Query("taskStatus") String taskStatus, @Query("pageSize") String pageSize);

    //导购端任务详情数据
    @POST("shoppingSelect/routetimeDetails?")
    Observable<TaskDetailsBean> getRoutetimeDetails(@Query("userId") String userId, @Query("routeTimeId") String routeTimeId);

    //导购端完成任务提交
    @POST("shoppingUpdate/completeTask?")
    Observable<JourneyBean> getCompleteTask(@Query("userId") String userId, @Query("routePlanningId") String routePlanningId);

    //导购端客户列表数据
    @POST("shoppingSelect/customerList?")
    Observable<CustomerListBean> getcustomerList(@Query("userId") String userId, @Query("routeTimeId") String routeTimeId, @Query("searchName") String searchName, @Query("pageSize") String pageSize);

    //导购报备流程详情
    @POST("shoppingSelect/customerDetails")
    Observable<MyClientFragmentBean> getClientFragmentBeanDao(@Query("userId") String userId, @Query("customerId") String customerId, @Query("landingId") String landingId, @Query("preparationId") String preparationId);

    // TODO 专员

    // 专员端报备流程数据请求
    @POST("commissionerSelect/processData")
    Observable<ProcessDataBean> getProcessData(@Query("storeId") String storeId, @Query("agentId") String agentId, @Query("status") String status, @Query("search") String search, @Query("userId") String userId, @Query("pageSize") String pageSize, @Query("type") String tag, @Query("startDate") String startDate, @Query("endDate") String endDate);

//    //专员用户Id获取用户信息
//    @POST("commonSelect/getUserInfo")
//    Observable<ZYDataBean> getZYDataBean(@Query("userId") String userId);

    //门店合作/取消合作
    @POST("commissionerUpdate/storeCooperation")
    Observable<CooperationBean> getCooperationBean(@Query("storeId") String storeId, @Query("userId") String userId, @Query("flag") String flag);

    //门店统计
    @POST("commissionerSelect/findData")
    Observable<DBean> getDBean(@Query("userId") String userId);

    //门店列表
    @POST("commissionerSelect/processData")
    Observable<FragmentClient> getFragmentClient(@Query("userId") String userId, @Query("status") String status);

    //数据切换
    @POST("commissionerSelect/dataStatistics")
    Observable<DataNumBean> getDataNum(@Query("userId") String userId, @Query("storeId") String storeId, @Query("agentId") String agentId, @Query("tag") String tag, @Query("type") String type,
                                       @Query("startDate") String startDate, @Query("endDate") String endDate);

    //佣金数据切换
    @POST("commissionerSelect/moneyData")
    Observable<FinanceBean> getFinanceBean(@Query("userId") String userId, @Query("companyId") String companyId, @Query("storeId") String storeId, @Query("agentId") String agentId,
                                           @Query("type") String type, @Query("startDate") String startDate, @Query("endDate") String endDate);

    //趋势图
    @POST("commissionerSelect/viewData")
    Observable<TendentcyBean> getTendentcy(@Query("status") String status, @Query("type") String type, @Query("userId") String userId);

    //佣金跟进
    @POST("commissionerSelect/amountlist")
    Observable<CommissionListBean> getCommissionListBean(@Query("userId") String userId, @Query("projectType") String projectType, @Query("companyId") String companyId, @Query("storeId") String storeId, @Query("agentId") String agentId, @Query("search") String search, @Query("status") String status, @Query("pageSize") String pageSize, @Query("type") String type, @Query("startDate") String startDate, @Query("endDate") String endDate);

    //经纪人列表
    @POST("commissionerSelect/agentList")
    Observable<BrokersListBean> getBrokersListBean(@Query("companyId") String companyId, @Query("storeId") String storeId, @Query("search") String search, @Query("status") String status, @Query("userId") String userId, @Query("pageSize") String pageSize, @Query("type") String type, @Query("startDate") String startDate, @Query("endDate") String endDate);

    //门店列表
    @POST("commissionerSelect/storeList")
    Observable<StoreListBean> getStoreList(@Query("companyId") String companyId, @Query("search") String search, @Query("status") String status, @Query("userId") String userId, @Query("pageSize") String pageSize, @Query("type") String type, @Query("startDate") String startDate, @Query("endDate") String endDate);

    //公司列表
    @POST("commissionerSelect/companyList")
    Observable<StoreListBean> getCompanList(@Query("companyId") String companyId, @Query("search") String search, @Query("status") String status, @Query("userId") String userId, @Query("pageSize") String pageSize);

    //门店详情
    @POST("commissionerSelect/storeDetails")
    Observable<CompanyDetailsBean> getCompanyDetailsBean(@Query("storeId") String storeId, @Query("userId") String userId);
    //专员详情

    //添加经纪公司
    @POST("commissionerUpdate/companyAdd")
    Observable<AddCompanyBean> getAddCompanyBean(@Query("id") String id, @Query("companyName") String companyName, @Query("area") String area, @Query("address") String address, @Query("location") String location,
                                                 @Query("userName") String userName, @Query("phone") String phone,
                                                 @Query("flag") String flag, @Query("userId") String userId);

    //添加经纪门店
    @POST("commissionerUpdate/storeSave")
    Observable<AddStoreBean> getAddStoreBean(@Query("id") String id, @Query("storeNum") String storeNum, @Query("storeName") String storeName, @Query("area") String area,
                                             @Query("address") String address, @Query("location") String location, @Query("storeRise") String storeRise, @Query("storeImg") String storeImg,
                                             @Query("flag") String flag, @Query("state") String state, @Query("companyId") String companyId, @Query("userId") String userId);

    //添加经纪人
    @POST("commissionerUpdate/sysUserSave")
    Observable<AddBrokerBean> getAddBrokerBean(@Query("id") String id, @Query("identity") String identity, @Query("name") String name, @Query("phone") String phone, @Query("loginName") String loginName,
                                               @Query("password") String password, @Query("userId") String userId, @Query("companyManageId") String companyManageId, @Query("storeManageId") String storeManageId,
                                               @Query("isCover") String isCover);

    //佣金上半部
    @POST("commissionerSelect/moneyData")
    Observable<CommissionUpBean> getcommissionUpBean(@Query("userId") String userId, @Query("companyId") String companyId, @Query("storeId") String storeId, @Query("agentId") String agentId, @Query("type") String type, @Query("startDate") String startDate, @Query("endDate") String endDate);

    //修改经纪人
    @POST("commissionerSelect/getAgent")
    Observable<BrokerChangeBean> getBrokerChangeBean(@Query("userId") String userId, @Query("agentId") String agentId);

    //修改门店
    @POST("commissionerSelect/getStoreManage")
    Observable<StoreChangeBean> getStoreChangeBean(@Query("userId") String userId, @Query("storeId") String storeId);

    //公司详情
    @POST("commissionerSelect/companyDetails")
    Observable<CompanyBean> getCompanyDetails(@Query("userId") String userId, @Query("companyId") String companyId);

    //公司详情-数据统计
    @POST("commissionerSelect/companyData")
    Observable<CompanyDataBean> getCompanyData(@Query("userId") String userId, @Query("companyId") String companyId, @Query("type") String type, @Query("startDate") String startDate, @Query("endDate") String endDate);

    //根据经济公司Id获取经济公司数据
    @POST("commissionerSelect/getComapnyManage")
    Observable<ComapnyManage> getComapnyManage(@Query("userId") String userId, @Query("companyId") String companyId);

    //根据经济公司Id获取经济公司数据
    @POST("commissionerSelect/agentDetails")
    Observable<BrokerBean> getEAgentDetails(@Query("agentId") String agentId, @Query("userId") String userId);

    //经纬度转坐标
    @POST("commonSelect/toAddress")
    Observable<ChangeAddress> getChangeAddress(@Query("longitude") String longitude, @Query("latitude") String latitude);

    //经纪门店打卡
    @POST("commissionerUpdate/storeClock")
    Observable<ClockInBean> getClockIn(@Query("storeId") String storeId, @Query("address") String address, @Query("location") String location, @Query("img") String img, @Query("type") String type, @Query("userId") String userId);

    //获取打卡记录
    @POST("commissionerSelect/storeClockRecord")
    Observable<RecordBean> getRecord(@Query("userId") String userId, @Query("storeId") String storeId, @Query("search") String search, @Query("tag") String tag);

    //我的界面/打卡记录
    @POST("commissionerSelect/myDate")
    Observable<MyDataStoreBean> getMyDataStore(@Query("userId") String userId);

    //分享记录
    @POST("commonUpdate/shareLogSave")
    Observable<ShareLogSaveBean> getShareLogSave(@Query("shareType") String shareType,@Query("projectId") String projectId,@Query("userId") String userId);

    //门店编号
    @POST("commissionerUpdate/setStoreNum")
    Observable<AddStoreNumBean> getAddStoreNum(@Query("userId") String userId);

    //账号注销功能
    @POST("commonUpdate/updateLoginFlag")
    Observable<UpdateLoginFlag> getUpdateLoginFlag(@Query("userId") String userId, @Query("sysUserId") String sysUserId);

    //注册城市列表
    @POST("commonSelect/registerCityCompanyList")
    Observable<SignInCityBean> getSignInCityBean(@Query("pageSize") String pageSize,@Query("name") String name);

    //注册经纪公司列表
    @POST("commonSelect/registerCompanyList")
    Observable<SignInCompanyBean> getSignInCompanyBean(@Query("parentId") String parentId, @Query("type") String type,@Query("name") String name,@Query("pageSize") String pageSize);

    //注册经纪门店列表
    @POST("commonSelect/registerStoreList")
    Observable<SignInStoreBean> getSignInStoreBean(@Query("parentId") String parentId,@Query("name") String name,@Query("pageSize") String pageSize);

    //注册
    @POST("commonUpdate/userRegister")
    Observable<SignInBean> getSignInBean(@Query("storeId") String storeId, @Query("type") String type, @Query("name") String name, @Query("phone") String phone, @Query("loginName") String loginName);

    //项目列表数据
    @POST("commonSelect/mapFindProjectList")
    Observable<ProjectsBean> getProjectsBean(@Query("city") String city, @Query("searchName") String searchName, @Query("userId") String userId, @Query("cityType") String cityType, @Query("pageSize") String pageSize);

    //门店列表数据
    @POST("commonSelect/mapFindStoreList")
    Observable<StoresBean> getStoresBean(@Query("userId") String userId, @Query("status") String status, @Query("companyId") String companyId, @Query("search") String search, @Query("pageSize") String pageSize);

    //公司列表数据
    @POST("commonSelect/mapFindCompanyList")
    Observable<CompaniesBean> getCompaniesBean(@Query("userId") String userId, @Query("search") String search, @Query("status") String status, @Query("pageSize") String pageSize);

    //      TODO    拓客功能 接口
    //检查店铺或者项目是否已发过红包
    @POST("ordinarySelect/checkRedbagPayProject")
    Observable<CheckRedbagPayProjectBean> getCheckRedbagPayProject(@Query("userId") String userId,@Query("projectId") String projectId,@Query("webshopId") String webshopId);

    //红包下单接口
    @POST("ordinaryUpdate/rechargeRedbag")
    Observable<RechargeRedbagBean> getRechargeRedbag(@Query("userId") String userId,@Query("projectId") String projectId,@Query("webshopId") String webshopId,@Query("cityCompanyId") String cityCompanyId,@Query("denomination") String denomination,@Query("quantity") String quantity,@Query("assistanceNum") String assistanceNum);

    //红包记录统计总领红包金额、总获客数、总发放金额
    @POST("ordinarySelect/redbagSumStatistics")
    Observable<RedBagSumStatisticsBean> getRedbagSumStatistics(@Query("userId") String userId);

    //红包记录页兑付列表
    @POST("ordinarySelect/redbagStatistics")
    Observable<RedbagStatisticsBean> getRedbagStatistics(@Query("userId") String userId,@Query("pageSize") String pageSize,@Query("pageNo") String pageNo);

    //房源超市接口
    @POST("ordinarySelect/supermarket")
    Observable<SupermarketBean> getSupermarket(@Query("userId") String userId,@Query("webshopId") String webshopId,@Query("hotPush") String hotPush);

    //网店添加项目
    @POST("ordinaryUpdate/projectAdd")
    Observable<ProjectAddBean> getProjectAdd(@Query("userId") String userId,@Query("projectId") String projectId,@Query("webshopId") String webshopId);

    //朋友圈助手
    @POST("ordinarySelect/friendsAssistant")
    Observable<FriendsAssistantBean> getFriendsAssistant(@Query("userId") String userId,@Query("classType") String classType);

    //生成微信小程序二维码
    @POST("commonSelect/appletWechatImage")
    Observable<AppletWechatImageBean> getAppletWechatImage(@Query("userId") String userId);

    //领取记录与领取详情共用接口
    @POST("ordinarySelect/redbagReceiveRecord")
    Observable<RedbagReceiveRecordBean> getRedbagReceiveRecord(@Query("userId") String userId,@Query("redbagType") String redbagType,@Query("timeType") String timeType,@Query("searchName") String searchName,@Query("pageSize") String pageSize,@Query("pageNo") String pageNo);

    //优惠活动列表
    @POST("ordinarySelect/preferentialActList")
    Observable<PreferentialActListBean> getPreferentialActList(@Query("userId") String userId,@Query("type") String type);
}

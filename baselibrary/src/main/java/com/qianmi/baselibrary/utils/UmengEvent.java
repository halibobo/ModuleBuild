package com.qianmi.baselibrary.utils;

/**
 * 友盟统计事件
 * Created by zhangxitao on 15/12/31.
 */
public interface UmengEvent {
    String loginOk = "loginOk";
    String experienceLoginOk = "experienceLoginOk";
    String experienceLoginFail = "experienceLoginFail";

    String loginFail = "loginFail";
    String pointAdd = "pointAdd";
    String pointEdit = "pointEdit";
    String pointMark = "pointMark";
    String pointRegister = "pointRegister";
    String signin = "signin";
    String signout = "signout";
    String tempVisitFromSign = "tempVisitFromSign";
    String tempVisit = "tempVisit";
    String planVisit = "planVisit";
    String planOverView = "planOverView";//查看计划纵览
    String visitSubmit = "visitSubmit";
    String planSubmit = "planSubmit";//提交一个计划拜访
    String planUpdate = "planUpdate";
    String planCreate = "planCreate";//进入计划拜访增加页面
    String selectProduct = "selectProduct";
    String orderStart = "orderStart";
    String orderstep1 = "orderstep1";
    String orderstep2 = "orderstep2";
    String orderstep3 = "orderstep3";
    String messageDetail = "messageDetail";
    String addNewAddress = "addNewAddress";
    String clickDataTaskMainPage = "clickDataTaskMainPage";
    String clickDataPointMainPage = "clickDataPointMainPage";
    String clickDataOrderMainPage = "clickDataOrderMainPage";
    String nearbyPointCheck = "nearbyPointCheck";//查看附近网点
    String trial = "trial";
    String clickTrialOnLogin = "clickTrialOnLogin";//在登录页面上点击了试用
    String clickRegOnLogin = "clickRegOnLogin";//在登录页面上点击了注册
    String updateAvatar = "updateAvatar";//更新或者上传头像
    String DRegStep1 = "DRegStep1";
    String DRegStep2 = "DRegStep2";
    String DRegStep3 = "DRegStep3";
    String DRegStep4 = "DRegStep4";
    String DataRankPoint = "DataRankPoint";//查看网点分析
    String DataRankTopSalesGoods = "DataRankTopSalesGoods";//查看热销商品
    String DataRankSalers = "DataRankSalers";//查看员工绩效
    String DataTeamTarget = "DataTeamTarget";//查看部门绩效
    String QuotationList = "QuotationList";//查看所有报价单列表
    String QuotationCreate = "QuotationCreate";//查看报价单详情
    String QuotationShare = "QuotationShare";//分享报价单
    String PromotionList = "PromotionList";//查看所有营销活动
    String PromotionDetail = "PromotionDetail";//查看营销活动详情
    String PromotionShare = "PromotionShare";//分享营销活动
    String YDHBuyEnter = "YDHBuyEnter";//云订货购买页面进入
    String YDHBuySuccess = "YDHBuySuccess";//云订货购买开通成功
    String newVersionGuide = "newVersionGuide";//查看新版本介绍
    String checkSignRecord = "checkSignRecord";//查看签到记录
}

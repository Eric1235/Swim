package com.scnu.swimmingtrainingsystem.util;

import android.content.Context;
import android.content.Intent;

import com.scnu.swimmingtrainingsystem.activity.AboutUsActivity;
import com.scnu.swimmingtrainingsystem.activity.AthleteActivity;
import com.scnu.swimmingtrainingsystem.activity.EachTimeScoreActivity;
import com.scnu.swimmingtrainingsystem.activity.HomeActivity;
import com.scnu.swimmingtrainingsystem.activity.LoginActivity;
import com.scnu.swimmingtrainingsystem.activity.ModifyPassActivity;
import com.scnu.swimmingtrainingsystem.activity.MyApplication;
import com.scnu.swimmingtrainingsystem.activity.OtherFunctionActivity;
import com.scnu.swimmingtrainingsystem.activity.QueryScoreActivity;
import com.scnu.swimmingtrainingsystem.activity.QuestionHelpActivity;
import com.scnu.swimmingtrainingsystem.activity.ShowScoreActivity;
import com.scnu.swimmingtrainingsystem.activity.TimerActivity;

/**
 * Created by lixinkun on 15/12/14.
 * 程序跳转控制器
 */
public class AppController {

    /**
     * 跳转到运动员界面
     * @param context
     */
    public static void gotoAthlete(Context context){
        Intent i = new Intent(context, AthleteActivity.class);
        context.startActivity(i);
    }

    /**
     * 跳转到成绩查询界面
     * @param context
     */
    public static void gotoQueryScore(Context context){
        Intent i = new Intent(context, QueryScoreActivity.class);
        context.startActivity(i);
    }

    /**
     * 跳转到小功能界面
     * @param context
     */
    public static void gotoOtherFunction(Context context){
        Intent i = new Intent(context, OtherFunctionActivity.class);
        context.startActivity(i);
    }

    /**
     * 跳转到修改密码界面
     * @param context
     */
    public static void gotoModifyPwd(Context context){
        Intent i = new Intent(context, ModifyPassActivity.class);
        context.startActivity(i);
    }

    public static void gotoAboutUs(Context context){
        Intent i = new Intent(context, AboutUsActivity.class);
        context.startActivity(i);
    }

    public static void gotoQuestionHelp(Context context){
        Intent i = new Intent(context, QuestionHelpActivity.class);
        context.startActivity(i);
    }

    public static void gotoLogin(Context context){
        Intent i = new Intent(context, LoginActivity.class);
        context.startActivity(i);
    }

    public static void gotoHomeActivity(Context context){
        Intent i = new Intent(context, HomeActivity.class);
        context.startActivity(i);
    }

    public static void gotoShowScoreActivity(Context context){
        Intent intent = new Intent(context,
                ShowScoreActivity.class);
        context.startActivity(intent);
    }

    public static void gotoTimerActivity(Context context){
        Intent i = new Intent(context, TimerActivity.class);
        context.startActivity(i);
    }

    public static void gotoEachTimeScoreActivity(Context context){
        Intent i = new Intent(context, EachTimeScoreActivity.class);
        context.startActivity(i);
    }



    /**
     * 重置app
     * @param app
     */
    public static void reset(MyApplication app){
        app.getMap().put(Constants.CURRENT_SWIM_TIME, 0);
        app.getMap().put(Constants.PLAN_ID, 0);
        app.getMap().put(Constants.TEST_DATE, "");
        app.getMap().put(Constants.DRAG_NAME_LIST, null);
        app.getMap().put(Constants.CURRENT_USER_ID, "");
        app.getMap().put(Constants.IS_CONNECT_SERVER, true);
        app.getMap().put(Constants.COMPLETE_NUMBER, 0);
        app.getMap().put(Constants.INTERVAL, 0);
    }

    //登出的时候初始化数据
    public static void logout(Context context){
        SpUtil.SaveLoginInfo(context,false);
        SpUtil.saveLoginSucceed(context, false);
        SpUtil.saveSelectedPool(context, 0);
        SpUtil.saveSelectedStroke(context, 0);
        SpUtil.saveUID(context, 0);
        SpUtil.saveUserId(context, 0);
        SpUtil.saveDistance(context, "", "");
//        SpUtil.SaveLoginInfo(context, "", "");
    }

}

package com.scnu.swimmingtrainingsystem.util;

import android.content.Context;
import android.content.Intent;

import com.scnu.swimmingtrainingsystem.activity.AthleteActivity;
import com.scnu.swimmingtrainingsystem.activity.LoginActivity;
import com.scnu.swimmingtrainingsystem.activity.ModifyPassActivity;
import com.scnu.swimmingtrainingsystem.activity.OtherFunctionActivity;
import com.scnu.swimmingtrainingsystem.activity.QueryScoreActivity;
import com.scnu.swimmingtrainingsystem.activity.QuestionHelpActivity;

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
        Intent i = new Intent(context, ModifyPassActivity.class);
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

}

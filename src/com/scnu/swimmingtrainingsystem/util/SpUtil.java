package com.scnu.swimmingtrainingsystem.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * 保存全局数据
 * Created by lixinkun on 15/12/20.
 */
public class SpUtil {

    /**
     * 存储用户是否已经登录
     * @param context
     * @param isLogin
     */
    public static void saveLoginSucceed(Context context,boolean isLogin){
        SharedPreferences sp = context.getSharedPreferences(
                Constants.LOGININFO, context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean(Constants.LOGIN_SUCCEED,isLogin);
        editor.commit();
    }

    /**
     * 全局获取用户是否已经登录
     * @param context
     * @return
     */
    public static boolean getIsLogin(Context context){
        SharedPreferences sp = context.getSharedPreferences(
                Constants.LOGININFO, context.MODE_PRIVATE);
        boolean isLogin = sp.getBoolean(Constants.LOGIN_SUCCEED, false);
        return isLogin;
    }


    /**
     * 持久存储uid
     * @param context
     * @param uid
     */
    public static void saveUID(Context context,int uid){
        SharedPreferences sp = context.getSharedPreferences(
                Constants.LOGININFO, context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt(Constants.USER_ID,uid);
        editor.commit();
    }

    /**
     * 获取用户id
     * @param context
     * @return
     */
    public static int getUID(Context context){
        SharedPreferences sp = context.getSharedPreferences(Constants.LOGININFO,context.MODE_PRIVATE);
        int uid = sp.getInt(Constants.USER_ID, 0);
        return uid;
    }

    /**
     * 存储uid
     * @param context
     * @param uid
     */
    public static void saveUserId(Context context,int uid){
        SharedPreferences sp = context.getSharedPreferences(
                Constants.LOGININFO, context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt(Constants.CURRENT_USER_ID,uid);
        editor.commit();
    }

    /**
     * 是否第一次启动
     *
     * @param context
     * @param isFirst
     */
    @SuppressWarnings("static-access")
    public static void SaveLoginInfo(Context context, boolean isFirst) {
        SharedPreferences sp = context.getSharedPreferences(
                Constants.LOGININFO, context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean("isFirst", isFirst);
        editor.commit();
    }

    /**
     * 保存登录信息到SharedPreferences
     *
     * @param context
     * @param username
     * @param password
     */
    public static void SaveLoginInfo(Context context, String username,
                                     String password) {
        SharedPreferences sp = context.getSharedPreferences(
                Constants.LOGININFO, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("username", username);
        editor.putString("password", password);
        editor.commit();
    }

    public static String getLoginedUserName(Context context){
        SharedPreferences sp = context.getSharedPreferences(
                Constants.LOGININFO, Context.MODE_PRIVATE);
        String name = sp.getString("username", null);
        return name;
    }

    public static String getLoginedPassword(Context context){
        SharedPreferences sp = context.getSharedPreferences(
                Constants.LOGININFO, Context.MODE_PRIVATE);
        String password = sp.getString("password", null);
        return password;
    }

    public static void SaveLoginInfo(Context context, String host, String ip,
                                     String port) {
        SharedPreferences sp = context.getSharedPreferences(
                Constants.LOGININFO, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("hostInfo", host);
        editor.putString("ip", ip);
        editor.putString("port", port);
        editor.commit();
    }

    public static void saveIsThisUserFirstLogin(Context context, boolean first) {
        SharedPreferences sp = context.getSharedPreferences(
                Constants.LOGININFO, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean(Constants.IS_THIS_USER_FIRST_LOGIN, first);
        editor.commit();
    }

    /**
     * 记录选择的泳池大小
     *
     * @param context
     * @param position
     */
    public static void saveSelectedPool(Context context, int position) {
        SharedPreferences sp = context.getSharedPreferences(
                Constants.LOGININFO, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt(Constants.SELECTED_POOL, position);
        editor.commit();
    }

    /**
     * 获取选择的泳姿
     * @param context
     * @return
     */
    public static int getSelectedPosition(Context context){
        SharedPreferences sp = context.getSharedPreferences(
                Constants.LOGININFO, Context.MODE_PRIVATE);
        int selectedPosition = sp.getInt(Constants.SELECTED_POOL, 0);
        return selectedPosition;
    }

    /**
     *存储选择的泳装
     * @param context
     * @param position
     */
    public static void saveSelectedStroke(Context context,int position){
        SharedPreferences sp = context.getSharedPreferences(Constants.LOGININFO, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt(Constants.SELECTED_STROKE, position);
        editor.commit();
    }

    /**
     * 获取计时间隔
     * @param context
     * @return
     */
    public static String getSwimInterval(Context context){
        SharedPreferences sp = context.getSharedPreferences(Constants.LOGININFO, Context.MODE_PRIVATE);
        String interval = sp.getString(Constants.INTERVAL, "");
        return interval;
    }

    /**
     * 记录预计的游泳总距离
     *
     * @param context
     * @param distance
     */
    public static void saveDistance(Context context, String distance,
                                    String interval) {
        SharedPreferences sp = context.getSharedPreferences(
                Constants.LOGININFO, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(Constants.SWIM_DISTANCE, distance);
        editor.putString(Constants.INTERVAL, interval);
        editor.commit();
    }

    /**
     * 获取游泳
     * @param context
     * @return
     */
    public static String getDistance(Context context){
        SharedPreferences sp = context.getSharedPreferences(
                Constants.LOGININFO, Context.MODE_PRIVATE);
        String distance = sp.getString(Constants.SWIM_DISTANCE, "");
        return distance;
    }

    /**
     * 保存当前成绩状态，留到统计时进行调整
     *
     * @param context
     * @param i
     *            第几趟
     * @param crrentDistance
     * @param scoreString
     * @param athleteString
     */
    public static void saveCurrentScoreAndAthlete(Context context, int i,
                                                  int crrentDistance, String scoreString, String athleteString,String athleteIDString) {
        SharedPreferences sp = context.getSharedPreferences(
                Constants.LOGININFO, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt(Constants.CURRENT_DISTANCE + i, crrentDistance);
        editor.putString(Constants.SCORESJSON + i, scoreString);
        editor.putString(Constants.ATHLETEJSON + i, athleteString);
        editor.putString(Constants.ATHLETEIDJSON + i,athleteIDString);
        editor.commit();
    }

    public static void saveSelectedAthlete(Context context, String mapJson) {
        SharedPreferences sp = context.getSharedPreferences(
                Constants.LOGININFO, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("mapConfig", mapJson);
        editor.commit();
    }

    /**
     * 记录是否第一次打开应用的运动员Activity
     *
     * @param context
     * @param isFirst
     */
    public static void initAthletes(Context context, boolean isFirst) {
        SharedPreferences sp = context.getSharedPreferences(
                Constants.LOGININFO, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean(Constants.FISRTOPENATHLETE, isFirst);
        editor.commit();
    }

    /**
     * 记录是否第一次打开应用的运动员Activity
     *
     * @param context
     * @param isFirst
     */
    public static void initPlans(Context context, boolean isFirst) {
        SharedPreferences sp = context.getSharedPreferences(
                Constants.LOGININFO, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean(Constants.FISRTOPENPLAN, isFirst);
        editor.commit();
    }

    /**
     * 用户是否第一次登陆
     * @param context
     * @return
     */
    public boolean getUserFirstLogin(Context context){
        SharedPreferences sp = context.getSharedPreferences(Constants.LOGININFO,
                Context.MODE_PRIVATE);
        boolean userFirstLogin = sp.getBoolean(Constants.IS_THIS_USER_FIRST_LOGIN, false);
        return userFirstLogin;
    }

    /**
     * 是否是第一次打开athlete
     * @param context
     * @return
     */
    public boolean getIsFirst(Context context){
        SharedPreferences sp = context.getSharedPreferences(Constants.LOGININFO,
                Context.MODE_PRIVATE);
        boolean isFirst = sp.getBoolean(Constants.FISRTOPENATHLETE,false);
        return isFirst;
    }

    /**
     * 获取map配置
     * @param context
     * @return
     */
    public static String getMapConfig(Context context){
        SharedPreferences sp = context.getSharedPreferences(Constants.LOGININFO,
                Context.MODE_PRIVATE);
        String mapConfigString = sp.getString("mapConfig", "");
        return mapConfigString;
    }

//    // 保存是第几次计时，提醒用户是第几次计时之中
//    mMap.put(Constants.CURRENT_SWIM_TIME, 0);
//    // 保存所选的计划ID
//    mMap.put(Constants.PLAN_ID, 0);
//    // 保存开始计时的日期
//    mMap.put(Constants.TEST_DATE, "");
//    // 保存手动匹配计时按名次排行的运动员名字,方便除第一趟计时外不用再次拖动运动员进行排行
//    mMap.put(Constants.DRAG_NAME_LIST, null);
//    // 保存当前登录的用户id
//    mMap.put(Constants.CURRENT_USER_ID, "");
//    // 保存打开登录页面时检查是否可以连接服务器的状态
//    mMap.put(Constants.IS_CONNECT_SERVER, true);
//    //保存匹配完成的成绩（趟数）
//    mMap.put(Constants.COMPLETE_NUMBER, 0);
//    //保存每次计时时的间隔距离
//    mMap.put(Constants.INTERVAL, "");
}

package com.scnu.swimmingtrainingsystem.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import com.scnu.swimmingtrainingsystem.R;
import com.scnu.swimmingtrainingsystem.model.Athlete;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 其他工具类
 * 
 * @author Littleyte
 * 
 */
@SuppressLint("DefaultLocale")
public class CommonUtils {


	/**
	 * 获取运动员的id
	 * @param lists
	 * @return
	 */
	public static List<Integer> getAthleteIdsByAthletes(List<Athlete> lists){
		List<Integer> ids = new ArrayList<Integer>();
		for(Athlete a : lists){
			ids.add(a.getAid());
		}
		return ids;
	}

	/**
	 * 获取运动员的名字
	 * @param lists
	 * @return
	 */
	public static List<String> getAthleteNamesByAthletes(List<Athlete> lists){
		List<String> names = new ArrayList<String>();
		for(Athlete a : lists){
			names.add(a.getName());
		}
		return  names;
	}

	/**
	 * 将一个运动员的多次成绩综合统计
	 * 
	 * @param list
	 * @return
	 */
	public static String scoreSum(List<String> list) {
		int hour = 0;
		int minute = 0;
		int second = 0;
		int millisecond = 0;
		for (String s : list) {
			int msc = Integer.parseInt(s.substring(9)) * 10;
			millisecond += msc;

			int sec = Integer.parseInt(s.substring(5, 7));
			second += sec;

			int min = Integer.parseInt(s.substring(2, 4));
			minute += min;

			int h = Integer.parseInt(s.substring(0, 1));
			hour += h;
		}
		second += millisecond / 1000;
		millisecond = millisecond % 1000 / 10;
		minute += second / 60;
		second = second % 60;
		hour += minute / 60;
		minute = minute % 60;
		return String.format("%1$01d:%2$02d'%3$02d''%4$02d", hour, minute,
				second, millisecond);
	}

	public static String getScoreSubtraction(String s1, String s2) {
		int Subtraction = timeString2TimeInt(s1) - timeString2TimeInt(s2);
		return timeInt2TimeString(Subtraction);

	}

	/**
	 * 将时间字符串转化成毫秒数
	 * 
	 * @param timeString
	 * @return
	 */
	public static int timeString2TimeInt(String timeString) {
		int msc = Integer.parseInt(timeString.substring(9)) * 10;
		int sec = Integer.parseInt(timeString.substring(5, 7));
		int min = Integer.parseInt(timeString.substring(2, 4));
		int hour = Integer.parseInt(timeString.substring(0, 1));
		int totalMsec = msc + sec * 1000 + min * 60000 + hour * 3600000;
		return totalMsec;

	}

	@SuppressLint("DefaultLocale")
	public static String timeInt2TimeString(int totalMsec) {
		// 秒数
		long time_count_s = totalMsec / 1000;
		// 小时数
		long hour = time_count_s / 3600;
		// 分
		long min = time_count_s / 60 - hour * 60;
		// 秒
		long sec = time_count_s - hour * 3600 - min * 60;
		// 毫秒
		long msec = totalMsec % 1000 / 10;

		return String.format("%1$01d:%2$02d'%3$02d''%4$02d", hour, min, sec,
				msec);
		// %1$01d:%2$02d'%3$ 03d''%4$ 03d
	}

	/**
	 * 自定义显示Toast
	 * 
	 * @param context
	 * @param mToast
	 * @param text
	 */
	public static void showToast(Context context, Toast mToast, String text) {
		if (mToast == null) {
			mToast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
			mToast.setGravity(Gravity.CENTER, 0, 0);
			View view = mToast.getView();
			view.setBackgroundResource(R.drawable.bg_toast);
			mToast.setView(view);
		} else {
			mToast.setText(text);
			mToast.setDuration(Toast.LENGTH_SHORT);
		}
		mToast.show();
	}

	private static long lastClickTime;

	/**
	 * 防止快速的重复点击出现
	 * 
	 * @return
	 */
	public static boolean isFastDoubleClick() {
		long time = System.currentTimeMillis();
		long timeD = time - lastClickTime;
		if (0 < timeD && timeD < 800) {
			return true;
		}
		lastClickTime = time;
		return false;
	}

	public static void removeAthleteFromList(List<Athlete> list,Athlete a){
		int aid = a.getAid();
		int size = list.size();
		for(int i = 0 ; i < size ; i++){
			if(list.get(i).getAid() == aid){
				list.remove(i);
				break;
			}
		}

	}

	public static boolean ListContainsAthlete(List<Athlete> list,Athlete a){
		int aid = a.getAid();
		int size = list.size();
		for(int i = 0 ; i < size ; i++){
			if(list.get(i).getAid() == aid){
				return true;
			}
		}
		return false;
	}

	public static boolean isMobileNO(String mobiles) {
		Pattern p = Pattern
				.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
		Matcher m = p.matcher(mobiles);
		return m.matches();
	}

	public static boolean isEmail(String email) {
		String str = "^([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)*@([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)+[\\.][A-Za-z]{2,3}([\\.][A-Za-z]{2})?$";
		Pattern p = Pattern.compile(str);
		Matcher m = p.matcher(email);
		return m.matches();
	}
}

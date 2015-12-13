package com.scnu.swimmingtrainingsystem.util;

import com.scnu.swimmingtrainingsystem.R;

/**
 * 软件配置
 * @author lixinkun
 *
 * 2015年12月7日
 */
public class AppConfig {

	/**
	 * 根据id去获取数据
	 * @param id
	 * @return
	 */
	public static int getFunctionStrById(int id){
		switch (id) {
		case 0:
			return R.string.athlete;
		case 1:
			return R.string.timer;
		case 2:
			return R.string.otherfunctions;
		case 3:
			return R.string.queryscore;
		case 4:
			return R.string.setting;
		default:
			return 0;
		}
	}
}

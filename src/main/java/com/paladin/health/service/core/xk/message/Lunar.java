package com.paladin.health.service.core.xk.message;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 提供一些农历相关信息
 *
 */
public class Lunar {

	private final static int[] lunarInfo = { 0x4bd8, 0x4ae0, 0xa570, 0x54d5, 0xd260, 0xd950, 0x5554, 0x56af, 0x9ad0, 0x55d2, 0x4ae0, 0xa5b6, 0xa4d0, 0xd250,
			0xd295, 0xb54f, 0xd6a0, 0xada2, 0x95b0, 0x4977, 0x497f, 0xa4b0, 0xb4b5, 0x6a50, 0x6d40, 0xab54, 0x2b6f, 0x9570, 0x52f2, 0x4970, 0x6566, 0xd4a0,
			0xea50, 0x6a95, 0x5adf, 0x2b60, 0x86e3, 0x92ef, 0xc8d7, 0xc95f, 0xd4a0, 0xd8a6, 0xb55f, 0x56a0, 0xa5b4, 0x25df, 0x92d0, 0xd2b2, 0xa950, 0xb557,
			0x6ca0, 0xb550, 0x5355, 0x4daf, 0xa5b0, 0x4573, 0x52bf, 0xa9a8, 0xe950, 0x6aa0, 0xaea6, 0xab50, 0x4b60, 0xaae4, 0xa570, 0x5260, 0xf263, 0xd950,
			0x5b57, 0x56a0, 0x96d0, 0x4dd5, 0x4ad0, 0xa4d0, 0xd4d4, 0xd250, 0xd558, 0xb540, 0xb6a0, 0x95a6, 0x95bf, 0x49b0, 0xa974, 0xa4b0, 0xb27a, 0x6a50,
			0x6d40, 0xaf46, 0xab60, 0x9570, 0x4af5, 0x4970, 0x64b0, 0x74a3, 0xea50, 0x6b58, 0x5ac0, 0xab60, 0x96d5, 0x92e0, 0xc960, 0xd954, 0xd4a0, 0xda50,
			0x7552, 0x56a0, 0xabb7, 0x25d0, 0x92d0, 0xcab5, 0xa950, 0xb4a0, 0xbaa4, 0xad50, 0x55d9, 0x4ba0, 0xa5b0, 0x5176, 0x52bf, 0xa930, 0x7954, 0x6aa0,
			0xad50, 0x5b52, 0x4b60, 0xa6e6, 0xa4e0, 0xd260, 0xea65, 0xd530, 0x5aa0, 0x76a3, 0x96d0, 0x4afb, 0x4ad0, 0xa4d0, 0xd0b6, 0xd25f, 0xd520, 0xdd45,
			0xb5a0, 0x56d0, 0x55b2, 0x49b0, 0xa577, 0xa4b0, 0xaa50, 0xb255, 0x6d2f, 0xada0, 0x4b63, 0x937f, 0x49f8, 0x4970, 0x64b0, 0x68a6, 0xea5f, 0x6b20,
			0xa6c4, 0xaaef, 0x92e0, 0xd2e3, 0xc960, 0xd557, 0xd4a0, 0xda50, 0x5d55, 0x56a0, 0xa6d0, 0x55d4, 0x52d0, 0xa9b8, 0xa950, 0xb4a0, 0xb6a6, 0xad50,
			0x55a0, 0xaba4, 0xa5b0, 0x52b0, 0xb273, 0x6930, 0x7337, 0x6aa0, 0xad50, 0x4b55, 0x4b6f, 0xa570, 0x54e4, 0xd260, 0xe968, 0xd520, 0xdaa0, 0x6aa6,
			0x56df, 0x4ae0, 0xa9d4, 0xa4d0, 0xd150, 0xf252, 0xd520 };

	private final static String[] lunarString1 = { "零", "一", "二", "三", "四", "五", "六", "七", "八", "九" };
	private final static String[] lunarString2 = { "初", "十", "廿", "卅", "正", "腊", "冬", "闰" };

	/**
	 * 返回农历年闰月月份
	 *
	 * @param lunarYear
	 *            指定农历年份(数字)
	 * @return 该农历年闰月的月份(数字,没闰返回0)
	 */
	private static int getLunarLeapMonth(int lunarYear) {
		// 数据表中,每个农历年用16bit来表示,
		// 前12bit分别表示12个月份的大小月,最后4bit表示闰月
		// 若4bit全为1或全为0,表示没闰, 否则4bit的值为闰月月份
		int leapMonth = Lunar.lunarInfo[lunarYear - 1900] & 0xf;
		leapMonth = (leapMonth == 0xf ? 0 : leapMonth);
		return leapMonth;
	}

	/**
	 * 返回农历年闰月的天数
	 *
	 * @param lunarYear
	 *            指定农历年份(数字)
	 * @return 该农历年闰月的天数(数字)
	 */
	private static int getLunarLeapDays(int lunarYear) {
		// 下一年最后4bit为1111,返回30(大月)
		// 下一年最后4bit不为1111,返回29(小月)
		// 若该年没有闰月,返回0
		return Lunar.getLunarLeapMonth(lunarYear) > 0 ? ((Lunar.lunarInfo[lunarYear - 1899] & 0xf) == 0xf ? 30 : 29) : 0;
	}

	/**
	 * 返回农历年的总天数
	 *
	 * @param lunarYear
	 *            指定农历年份(数字)
	 * @return 该农历年的总天数(数字)
	 */
	private static int getLunarYearDays(int lunarYear) {
		// 按小月计算,农历年最少有12 * 29 = 348天
		int daysInLunarYear = 348;
		// 数据表中,每个农历年用16bit来表示,
		// 前12bit分别表示12个月份的大小月,最后4bit表示闰月
		// 每个大月累加一天
		for (int i = 0x8000; i > 0x8; i >>= 1) {
			daysInLunarYear += ((Lunar.lunarInfo[lunarYear - 1900] & i) != 0) ? 1 : 0;
		}
		// 加上闰月天数
		daysInLunarYear += Lunar.getLunarLeapDays(lunarYear);

		return daysInLunarYear;
	}

	/**
	 * 返回农历年正常月份的总天数
	 *
	 * @param lunarYear
	 *            指定农历年份(数字)
	 * @param lunarMonth
	 *            指定农历月份(数字)
	 * @return 该农历年闰月的月份(数字,没闰返回0)
	 */
	private static int getLunarMonthDays(int lunarYear, int lunarMonth) {
		// 数据表中,每个农历年用16bit来表示,
		// 前12bit分别表示12个月份的大小月,最后4bit表示闰月
		int daysInLunarMonth = ((Lunar.lunarInfo[lunarYear - 1900] & (0x10000 >> lunarMonth)) != 0) ? 30 : 29;
		return daysInLunarMonth;
	}

	private Calendar solar;
	private int lunarYear;
	private int lunarMonth;
	private int lunarDay;
	private boolean isLeap;
	private boolean isLeapYear;
	private int maxDayInMonth = 29;

	/**
	 * 通过 Date 对象构建农历信息
	 *
	 * @param date
	 *            指定日期对象
	 */
	public Lunar(Date date) {
		if (date == null) {
			date = new Date();
		}
		this.init(date.getTime());
	}

	/**
	 * 构建当前时间对象
	 */
	public Lunar() {
		this.init(System.currentTimeMillis());
	}

	/**
	 * 通过 TimeInMillis 构建农历信息
	 *
	 * @param TimeInMillis
	 */
	public Lunar(long TimeInMillis) {
		this.init(TimeInMillis);
	}

	private void init(long TimeInMillis) {
		this.solar = Calendar.getInstance();
		this.solar.setTimeInMillis(TimeInMillis);
		Calendar baseDate = new GregorianCalendar(1900, 0, 31);
		long offset = (TimeInMillis - baseDate.getTimeInMillis()) / 86400000;
		// 按农历年递减每年的农历天数，确定农历年份
		this.lunarYear = 1900;
		int daysInLunarYear = Lunar.getLunarYearDays(this.lunarYear);
		while (this.lunarYear < 2100 && offset >= daysInLunarYear) {
			offset -= daysInLunarYear;
			daysInLunarYear = Lunar.getLunarYearDays(++this.lunarYear);
		}
		// 农历年数字

		// 按农历月递减每月的农历天数，确定农历月份
		int lunarMonth = 1;
		// 所在农历年闰哪个月,若没有返回0
		int leapMonth = Lunar.getLunarLeapMonth(this.lunarYear);
		// 是否闰年
		this.isLeapYear = leapMonth > 0;
		// 闰月是否递减
		boolean leapDec = false;
		boolean isLeap = false;
		int daysInLunarMonth = 0;
		while (lunarMonth < 13 && offset > 0) {
			if (isLeap && leapDec) { // 如果是闰年,并且是闰月
				// 所在农历年闰月的天数
				daysInLunarMonth = Lunar.getLunarLeapDays(this.lunarYear);
				leapDec = false;
			} else {
				// 所在农历年指定月的天数
				daysInLunarMonth = Lunar.getLunarMonthDays(this.lunarYear, lunarMonth);
			}
			if (offset < daysInLunarMonth) {
				break;
			}
			offset -= daysInLunarMonth;

			if (leapMonth == lunarMonth && isLeap == false) {
				// 下个月是闰月
				leapDec = true;
				isLeap = true;
			} else {
				// 月份递增
				lunarMonth++;
			}
		}
		this.maxDayInMonth = daysInLunarMonth;
		// 农历月数字
		this.lunarMonth = lunarMonth;
		// 是否闰月
		this.isLeap = (lunarMonth == leapMonth && isLeap);
		// 农历日数字
		this.lunarDay = (int) offset + 1;
	}

	/**
	 * 返回农历日期字符串
	 *
	 * @return 农历日期字符串
	 */
	public String getLunarDayString() {
		return Lunar.getLunarDayString(this.lunarDay);
	}

	/**
	 * 返回农历日期字符串
	 *
	 * @return 农历日期字符串
	 */
	public String getLunarMonthString() {
		return (this.isLeap() ? "闰" : "") + Lunar.getLunarMonthString(this.lunarMonth);
	}

	/**
	 * 返回农历表示字符串
	 *
	 * @return 农历字符串(例:正月初三)
	 */
	public String getLunarDateString() {
		return this.getLunarMonthString() + "月" + this.getLunarDayString() + "日";
	}

	/**
	 * 农历年是否是闰月
	 *
	 * @return 农历年是否是闰月
	 */
	public boolean isLeap() {
		return isLeap;
	}

	/**
	 * 农历年是否是闰年
	 *
	 * @return 农历年是否是闰年
	 */
	public boolean isLeapYear() {
		return isLeapYear;
	}

	/**
	 * 当前农历月是否是大月
	 *
	 * @return 当前农历月是大月
	 */
	public boolean isBigMonth() {
		return this.getMaxDayInMonth() > 29;
	}

	/**
	 * 当前农历月有多少天
	 *
	 * @return 当前农历月有多少天
	 */
	public int getMaxDayInMonth() {
		return this.maxDayInMonth;
	}

	/**
	 * 农历日期
	 *
	 * @return 农历日期
	 */
	public int getLunarDay() {
		return lunarDay;
	}

	/**
	 * 农历月份
	 *
	 * @return 农历月份
	 */
	public int getLunarMonth() {
		return lunarMonth;
	}

	/**
	 * 农历年份
	 *
	 * @return 农历年份
	 */
	public int getLunarYear() {
		return lunarYear;
	}


	/**
	 * 返回指定数字的农历月份表示字符串
	 *
	 * @param lunarMonth
	 *            农历月份(数字)
	 * @return 农历月份字符串 (例:正)
	 */
	private static String getLunarMonthString(int lunarMonth) {
		String lunarMonthString = "";
		if (lunarMonth == 1) {
			lunarMonthString = Lunar.lunarString2[4];
		} else {
			if (lunarMonth > 9) {
				lunarMonthString += Lunar.lunarString2[1];
			}
			if (lunarMonth % 10 > 0) {
				lunarMonthString += Lunar.lunarString1[lunarMonth % 10];
			}
		}
		return lunarMonthString;
	}

	/**
	 * 返回指定数字的农历日表示字符串
	 *
	 * @param lunarDay
	 *            农历日(数字)
	 * @return 农历日字符串 (例: 廿一)
	 */
	private static String getLunarDayString(int lunarDay) {
		if (lunarDay < 1 || lunarDay > 30) {
			return "";
		}
		int i1 = lunarDay / 10;
		int i2 = lunarDay % 10;
		String c1 = Lunar.lunarString2[i1];
		String c2 = Lunar.lunarString1[i2];
		if (lunarDay < 11) {
			c1 = Lunar.lunarString2[0];
		}
		if (i2 == 0) {
			c2 = Lunar.lunarString2[1];
		}
		return c1 + c2;
	}
}

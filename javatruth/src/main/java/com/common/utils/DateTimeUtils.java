package com.common.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * 日期与时间的帮助类，提供静态方法，不可以实例化。
 * 
 * @author huangshipiao
 * 
 */
public class DateTimeUtils {
	/**
	 * 禁止实例化
	 */
	private DateTimeUtils() {
	}
	public static String YYYYMMDDHHMMSS="yyyy-MM-dd HH:mm:ss";
	
	public static String YYYYMMDD="yyyy-MM-dd";
	
	public static Date switchDate(String startDate) {
		java.util.Date dt = new java.util.Date();
		SimpleDateFormat sdf = new SimpleDateFormat(YYYYMMDDHHMMSS);
		try {
			dt = sdf.parse(startDate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dt;
	}
	/**
	 * 
	 * @return Date yyyy-MM-dd HH:mm:ss
	 */
	public static Date getCurrentDate() {

		SimpleDateFormat sdf = new SimpleDateFormat(YYYYMMDDHHMMSS);
		java.util.Date dt = new java.util.Date();
		String currentsysdate = sdf.format(dt);

		try {
			dt = sdf.parse(currentsysdate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dt;
	}
	/**
	 * 格式化当前时间
	 * @author huangshipiao
	 * @time   2015-9-9 下午3:22:17 
	 * @param format
	 * @return
	 */
	public static Date getCurrentDateFormat(String format) {

		SimpleDateFormat sdf = new SimpleDateFormat(format);
		java.util.Date dt = new java.util.Date();
		String currentsysdate = sdf.format(dt);

		try {
			dt = sdf.parse(currentsysdate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dt;
	}
	/**
	 * 格式化当前时间为字符串
	 * @author huangshipiao
	 * @time   2015-9-9 下午3:23:14 
	 * @param format
	 * @return
	 */
	public static String getCurrentDateStrFormat(String format) {

		SimpleDateFormat sdf = new SimpleDateFormat(format);
		java.util.Date dt = new java.util.Date();
		String currentsysdate = sdf.format(dt);

		return currentsysdate;
	}
	
	/**
	 * 格式化昨日时间
	 * @author huangshipiao
	 * @time   2016-2-22 上午11:22:22 
	 * @param format
	 * @return
	 */
	public static Date getYesterdayDateFormat(String format) {

		SimpleDateFormat sdf = new SimpleDateFormat(format);
		java.util.Date dt = new java.util.Date(new Date().getTime()-24*60*60*1000);
		String currentsysdate = sdf.format(dt);

		try {
			dt = sdf.parse(currentsysdate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dt;
	}
	/**
	 * 
	 * @return String "yyyy-MM-dd"
	 */
	public static String getCurrentDateStr() {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date dt = new java.util.Date();
		String currentsysdate = sdf.format(dt);

		return currentsysdate;
	}

	/**
	 * 格式化日期为指定格式
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public static Date formatDate(Date date, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		String currentsysdate = sdf.format(date);
		java.util.Date dt = new java.util.Date();
		try {
			dt = sdf.parse(currentsysdate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dt;
	}

	/**
	 * 格式化日期为指定格式
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public static Date formatDate(String date, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		java.util.Date dt = new java.util.Date();
		try {
			dt = sdf.parse(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dt;
	}

	/**
	 * 格式化日期为指定格式，并转换为字符串输出
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public static String formatDateStr(Date date, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		String currentsysdate = sdf.format(date);

		return currentsysdate;
	}

	/**
	 * 获取随机数字符串
	 * 
	 * @param seed
	 *            随机种子
	 * @return 随机数字符串
	 */
	private static String getRandomStr(Integer seed) {
		Random ran = new Random();
		if (seed == null) {
			seed = ran.nextInt(999999999);
		}
		String newSeed = System.currentTimeMillis() + "" + Math.abs(ran.nextInt(1999999999) + ran.nextInt(1999999999)) + seed.toString();
		if (newSeed.length() > 18) {
			newSeed = newSeed.substring(9, 17) + newSeed.substring(newSeed.length() - 10);
		}
		Random random = new Random(new Long(newSeed));
		return ("" + Math.abs(random.nextLong()));
	}

	/**
	 * 获取 length 位数的随机码（纯数字）
	 * 
	 * @param seed
	 *            随机种子
	 * @param length
	 *            随机数位数(max = 18)
	 * @return length 位数的随机码（纯数字）
	 */
	public static final String getRandomCode(Integer seed, Integer length) {
		length = length > 18 ? 18 : length;
		String code = getRandomStr(seed);
		if (code.length() > length) {
			return code.substring(code.length() - length);
		}
		return code;
	}

	/**
	 * 获取 length 位数的随机码（纯数字）
	 * 
	 * @param length
	 *            随机数位数(max = 18)
	 * @return length 位数的随机码（纯数字）
	 */
	public static final String getRandomCode(Integer length) {
		length = length > 18 ? 18 : length;
		String code = getRandomStr(null);
		if (code.length() > length) {
			return code.substring(code.length() - length);
		}
		return code;
	}

	/**
	 * 获取第i天（日期时间）
	 * 
	 * @param i
	 *            （i为0时则获取当天）
	 * @param format
	 *            日期格式
	 * 
	 * @return
	 */
	public static String getDate(int i, String format) {
		Date tomorrow = new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * i);
		DateFormat df = new SimpleDateFormat(format);
		return df.format(tomorrow);
	}

	/**
	 * 长度大于17位数的随机数生成（订单号|流水号）
	 * 
	 * @param lenght
	 *            长度 （必须大于17位）
	 * @return
	 */
	public static final String genOrderNo(int lenght) {
		StringBuffer rc = new StringBuffer();
		int size = 1;
		if (lenght > 17) {
			size = lenght - 17;
		}
		rc.append(getDate(0, "yyyyMMddHHmmssSSS")).append(getRandomCode(size));
		return rc.toString();
	}

	/**
	 * 生成18位订单|流水号
	 * 
	 * @return
	 */
	public static final String genSerialNo() {
		StringBuffer rc = new StringBuffer();
		Random ran = new Random();
		rc.append(getDate(0, "yyyyMMddHHmmssSSS")).append(ran.nextInt(10));
		return rc.toString();
	}
	/**
	 * 计算某个时间 与当前时间的相差多少秒
	 * @author huangshipiao
	 * @time   2015-12-25 上午9:56:13 
	 * @param startDate
	 * @return
	 */
	public static int calLastedTime(Date startDate) {
		  long a = new Date().getTime();
		  long b = startDate.getTime();
		  int c = (int)((a - b) / 1000);
		  return c;
	}
	
	public static void main(String[] args) {

	}
}
